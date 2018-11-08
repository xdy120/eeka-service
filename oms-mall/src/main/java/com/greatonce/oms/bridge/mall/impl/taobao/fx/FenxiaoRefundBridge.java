package com.greatonce.oms.bridge.mall.impl.taobao.fx;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.bridge.mall.impl.AbstractRefundBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.taobao.TaobaoMall;
import com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain.JdpFxRefund;
import com.greatonce.oms.bridge.mall.request.RefundGetRequest;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.bridge.mall.response.RefundGetResponse;
import com.greatonce.oms.bridge.mall.response.RefundQueryResponse;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.request.FenxiaoRefundGetRequest;
import com.taobao.api.request.FenxiaoRefundQueryRequest;
import com.taobao.api.response.FenxiaoRefundGetResponse;
import com.taobao.api.response.FenxiaoRefundQueryResponse;
import com.taobao.api.response.FenxiaoRefundQueryResponse.RefundDetail;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class FenxiaoRefundBridge extends AbstractRefundBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(FenxiaoRefundBridge.class);
  public static final String CLOUD_PUSH_REFUND_GET_MS = "com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.mappers.JdpFxRefundMapper.list";
  public static final String CLOUD_PUSH_REFUND_QUERY_MS = "com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.mappers.JdpFxRefundMapper.list";

  @Autowired
  @Qualifier("rdsSqlSessionDecorator")
  private SqlSessionDecorator sqlSessionDecorator;
  @Autowired
  private TaobaoMall mall;

  private final int pageSize = 50;
  private static final Map<Integer, MallRefundStatus> MALL_REFUND_STATUS_MAP = InitEnumMap.MALL_REFUND_STATUS_MAP;


  @Override
  protected RefundQueryResponse doQueryRefund(RefundQueryRequest request) {

    RefundQueryResponse response = new RefundQueryResponse(request);
    List<MallRefundOrderInfo> refundOrders = new ArrayList<>();
    try {
      if (!Assert.isNull(request.getStore().getSetting()) && Assert
          .isTrue(request.getStore().getSetting().isTaobaoOrderCloudPush())) {
        response = tbFenxiaoRefundByCloudPush(request, refundOrders);
      } else {
        response = tbFenxiaoRefundByApi(request, refundOrders);
      }
      response.setOrders(refundOrders);
    } catch (Exception e) {
      response.setSuccess(false);
      response.setResult("下载订单异常");
      LOGGER.error("下载订单异常:{}", e.getMessage());
    }
    return response;
  }

  //云推
  private RefundQueryResponse tbFenxiaoRefundByCloudPush(RefundQueryRequest request,
      List<MallRefundOrderInfo> refundOrders)
      throws ApiException {
    RefundQueryResponse response = new RefundQueryResponse(request);
    HashMap<String, Object> param = new HashMap<>();
    param.put("supplierNick", request.getStore().getNickname());
    param.put("jdpModifiedBegin", request.getBeginTime());
    param.put("jdpModifiedEnd", request.getEndTime());
    PageList<JdpFxRefund> list = sqlSessionDecorator.selectList(CLOUD_PUSH_REFUND_QUERY_MS,
        param, request.getPage(), pageSize);
    //是否还有下一次
    response.setHasNext(request.getPage() < MallUtil.calcTotalPage(pageSize, list.getTotal()));

    List<JdpFxRefund> listData = list.getData();
    if (Assert.isEmpty(listData)) {
      response.setHasNext(false);
      response.setSuccess(true);
      response.setResult("分销售后单找不到");
      LOGGER.debug("找不到分销售后单");
      return response;
    }

    for (JdpFxRefund item : listData) {
      FenxiaoRefundGetResponse fxResponse = TaobaoUtils
          .parseResponse(item.getJdpResponse(), FenxiaoRefundGetResponse.class);
      refundOrders.add(convertGetRefund(fxResponse.getRefundDetail()));
    }
    if (Assert.isEmpty(refundOrders)) {
      response.setHasNext(false);
      response.setSuccess(false);
      response.setResult("分销售后解析失败");
      LOGGER.debug("分销售后单解析失败");
      return response;
    }
    return response;
  }

  //api
  private RefundQueryResponse tbFenxiaoRefundByApi(RefundQueryRequest request,
      List<MallRefundOrderInfo> refundOrders) {
    RefundQueryResponse response = new RefundQueryResponse(request);
    FenxiaoRefundQueryRequest fxRequest = new FenxiaoRefundQueryRequest();
    fxRequest.setStartDate(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
    fxRequest.setEndDate(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
    fxRequest.setPageSize((long) pageSize);
    fxRequest.setPageNo((long) request.getPage());
    FenxiaoRefundQueryResponse fxResponse = mall.call(request.getStore(), fxRequest);
    if (Assert.isEmpty(fxResponse.getRefundList())) {
      response.setSuccess(true);
      response.setResult("没有找到分销售后单");
      LOGGER.debug("没有找到分销售后单");
      return response;
    }
    response.setHasNext(
        request.getPage() < MallUtil.calcTotalPage(pageSize, fxResponse.getTotalResults()));
    for (RefundDetail item : fxResponse.getRefundList()) {
      refundOrders.add(convertQueryRefund(item));
    }
    return response;
  }

  private MallRefundOrderInfo convertQueryRefund(FenxiaoRefundQueryResponse.RefundDetail refund) {

    MallRefundOrderInfo refundOrder = new MallRefundOrderInfo();
    refundOrder.setTradeId(String.valueOf(refund.getPurchaseOrderId()));
    refundOrder.setRefundId(String.valueOf(refund.getSubOrderId()));
    refundOrder.setOid(String.valueOf(refund.getSubOrderId()));
    refundOrder.setBuyerNick(refund.getDistributorNick());
    refundOrder.setSellerNick(refund.getSupplierNick());
    refundOrder.setRefundTime(DateTimeUtil.toLocalDateTime(refund.getRefundCreateTime()));
    refundOrder.setCreatedTime(DateTimeUtil.toLocalDateTime(refund.getRefundCreateTime()));
    refundOrder.setModifiedTime(
        refund.getModified() == null ? null : DateTimeUtil.toLocalDateTime(refund.getModified()));
    refundOrder.setReason(refund.getRefundReason());
    refundOrder.setDescription(refund.getRefundDesc());
    refundOrder.setApplyStatus(MALL_REFUND_STATUS_MAP
        .getOrDefault(refund.getRefundStatus().intValue(), MallRefundStatus.UNKNOWN));
    refundOrder.setRefundAmount(Double.valueOf(refund.getRefundFee()));
    refundOrder.setHasGoodReturn(refund.getIsReturnGoods());
    refundOrder.setWhole(false);

    return refundOrder;
  }


  /**
   * 转换售后单
   */
  private MallRefundOrderInfo convertGetRefund(FenxiaoRefundGetResponse.RefundDetail refund) {

    MallRefundOrderInfo refundOrder = new MallRefundOrderInfo();
    refundOrder.setTradeId(String.valueOf(refund.getPurchaseOrderId()));
    refundOrder.setRefundId(String.valueOf(refund.getSubOrderId()));
    refundOrder.setOid(String.valueOf(refund.getSubOrderId()));
    refundOrder.setBuyerNick(refund.getDistributorNick());
    refundOrder.setSellerNick(refund.getSupplierNick());
    refundOrder.setRefundTime(DateTimeUtil.toLocalDateTime(refund.getRefundCreateTime()));
    refundOrder.setCreatedTime(DateTimeUtil.toLocalDateTime(refund.getRefundCreateTime()));
    refundOrder.setModifiedTime(
        refund.getModified() == null ? null : DateTimeUtil.toLocalDateTime(refund.getModified()));
    refundOrder.setReason(refund.getRefundReason());
    refundOrder.setDescription(refund.getRefundDesc());
    refundOrder.setApplyStatus(MALL_REFUND_STATUS_MAP
        .getOrDefault(refund.getRefundStatus().intValue(), MallRefundStatus.UNKNOWN));
    refundOrder.setRefundAmount(Double.valueOf(refund.getRefundFee()));
    refundOrder.setHasGoodReturn(refund.getIsReturnGoods());
    refundOrder.setWhole(false);

    return refundOrder;
  }

  @Override
  protected RefundGetResponse doGetRefund(RefundGetRequest request) {
    try {
      FenxiaoRefundGetResponse.RefundDetail refundDetail;
      if (!Assert.isNull(request.getStore().getSetting()) && Assert
          .isTrue(request.getStore().getSetting().isTaobaoOrderCloudPush())) {

        Map<String, Object> map = new HashMap<>(1);
        map.put("subOrderId", request.getRefundId());
        JdpFxRefund fxRefund = sqlSessionDecorator.selectOne(CLOUD_PUSH_REFUND_GET_MS, map);
        if (Assert.isNull(fxRefund)) {
          return new RefundGetResponse(request, true, "找不到售后单");
        }
        FenxiaoRefundGetResponse fxResponse = TaobaoUtils
            .parseResponse(fxRefund.getJdpResponse(), FenxiaoRefundGetResponse.class);
        refundDetail = fxResponse.getRefundDetail();
      } else {
        FenxiaoRefundGetRequest getRequest = new FenxiaoRefundGetRequest();
        getRequest.setSubOrderId(Long.valueOf(request.getRefundId()));
        FenxiaoRefundGetResponse getResponse = mall.call(request.getStore(), getRequest);

        refundDetail = getResponse.getRefundDetail();
      }
      MallRefundOrderInfo refund = convertGetRefund(refundDetail);
      if (Assert.isNull(refund)) {
        return new RefundGetResponse(request, false, "解析退款单结果失败");
      }
      return new RefundGetResponse(request, refund);
    } catch (Exception e) {
      LOGGER.error("下载退款单异常");
      return new RefundGetResponse(request, false, "下载退款单异常");
    }
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO_FX};
  }
}
