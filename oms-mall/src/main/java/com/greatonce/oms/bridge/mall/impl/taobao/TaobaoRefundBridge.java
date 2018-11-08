package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.bridge.mall.impl.AbstractRefundBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain.JdpTbRefund;
import com.greatonce.oms.bridge.mall.request.RefundAgreeRequest;
import com.greatonce.oms.bridge.mall.request.RefundAuditRequest;
import com.greatonce.oms.bridge.mall.request.RefundGetRequest;
import com.greatonce.oms.bridge.mall.request.RefundQueryRequest;
import com.greatonce.oms.bridge.mall.response.RefundAgreeResponse;
import com.greatonce.oms.bridge.mall.response.RefundAuditResponse;
import com.greatonce.oms.bridge.mall.response.RefundGetResponse;
import com.greatonce.oms.bridge.mall.response.RefundQueryResponse;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundPhase;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.taobao.api.ApiException;
import com.taobao.api.domain.Refund;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.request.NextoneLogisticsWarehouseUpdateRequest;
import com.taobao.api.request.RdcAligeniusSendgoodsCancelRequest;
import com.taobao.api.request.RdcAligeniusSendgoodsCancelRequest.CancelGoodsDto;
import com.taobao.api.request.RefundsReceiveGetRequest;
import com.taobao.api.request.RpRefundReviewRequest;
import com.taobao.api.request.RpRefundsAgreeRequest;
import com.taobao.api.response.RefundsReceiveGetResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 淘宝退款接口
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
@Component
public class TaobaoRefundBridge extends AbstractRefundBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(TaobaoRefundBridge.class);
  static final int PAGE_SIZE = 100;
  static final String REFUND_STATEMENT = "com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.mappers.JdpTbRefundMapper.list";
  static final String REFUND_FIELDS = "refund_id, tid, oid, total_fee, buyer_nick, seller_nick, created, modified, order_status, status, good_status, has_good_return, refund_fee, payment, reason, desc, title, num, company_name, sid, refund_phase, refund_version, sku, attribute, outer_id, operation_contraint";
  static final String REFUND_GET_FIELDS = "shipping_type, cs_status, advance_status, split_taobao_fee, split_seller_fee, refund_id, tid, oid, alipay_no, total_fee, buyer_nick, seller_nick, created, modified,order_status,  status, good_status, has_good_return, refund_fee, payment, reason, desc, title, price, num, good_return_time, company_name, sid, address, num_iid, refund_phase, refund_version, sku, attribute, outer_id, operation_contraint, buyer_open_uid";
  @Autowired
  private TaobaoMall mall;
  @Resource
  private SqlSessionDecorator rdsSqlSessionDecorator;

  private static final Map<String, MallRefundStatus> OMS_REFUND_STATUS = new HashMap<>(6);

  static {
    OMS_REFUND_STATUS.put("WAIT_SELLER_AGREE", MallRefundStatus.WAIT_SELLER_AGREE);
    OMS_REFUND_STATUS.put("WAIT_BUYER_RETURN_GOODS", MallRefundStatus.WAIT_BUYER_RETURN_GOODS);
    OMS_REFUND_STATUS.put("WAIT_SELLER_CONFIRM_GOODS", MallRefundStatus.WAIT_SELLER_CONFIRM_GOODS);
    OMS_REFUND_STATUS.put("SELLER_REFUSE_BUYER", MallRefundStatus.SELLER_REFUSE_BUYER);
    OMS_REFUND_STATUS.put("CLOSED", MallRefundStatus.CLOSED);
    OMS_REFUND_STATUS.put("SUCCESS", MallRefundStatus.SUCCESS);
  }


  /**
   * @return 卖家同意退款
   */
  @Override
  protected RefundAgreeResponse doAgree(RefundAgreeRequest request) {
    try {
      RefundApplyOrder refundApplyOrder = request.getRefundApplyOrder();
      if (Assert.isTrue(request.getStore().getSetting().isTaobaoEnableAG())) {
        RdcAligeniusSendgoodsCancelRequest req = new RdcAligeniusSendgoodsCancelRequest();
        CancelGoodsDto goodsDto = new CancelGoodsDto();
        goodsDto.setRefundId(Long.valueOf(refundApplyOrder.getMallRefundId()));
        goodsDto.setTid(Long.valueOf(refundApplyOrder.getTradeId()));
        goodsDto.setStatus("SUCCESS");
        goodsDto.setMsg("OMS自动拦截发货");
        req.setParam(goodsDto);
        mall.call(request.getStore(), req);
        return new RefundAgreeResponse(request);
      } else {
        RpRefundsAgreeRequest req = new RpRefundsAgreeRequest();
        req.setRefundInfos(
            refundApplyOrder.getMallRefundId() + "|" + refundApplyOrder.getApplyAmount() + "|"
                + refundApplyOrder.getMallRefundVersion() + "|" + (
                refundApplyOrder.getMallRefundPhase() == MallRefundPhase.ON_SALE ? "onsale"
                    : "aftersale"));
        mall.call(request.getStore(), req);
        return new RefundAgreeResponse(request);
      }
    } catch (Exception e) {
      return new RefundAgreeResponse(request, false, e.getMessage());
    }
  }

  /**
   * @return 审核退款单，标志是否可用于批量退款，目前仅支持天猫订单
   */
  @Override
  protected RefundAuditResponse doAudit(RefundAuditRequest request) {
    try {
      if (Assert.isTrue(request.getStore().getSetting().isTaobaoEnableAG())) {
        NextoneLogisticsWarehouseUpdateRequest req = new NextoneLogisticsWarehouseUpdateRequest();
        req.setRefundId(Long.parseLong(request.getRefundApplyOrder().getMallRefundId()));
        req.setWarehouseStatus(1L);
        mall.call(request.getStore(), req);
      } else {
        RpRefundReviewRequest req = new RpRefundReviewRequest();
        req.setRefundId(Long.parseLong(request.getRefundApplyOrder().getMallRefundId()));
        req.setOperator(request.getOperator());
        req.setRefundPhase(
            request.getRefundApplyOrder().getMallRefundPhase() == MallRefundPhase.ON_SALE
                ? "onsale"
                : "aftersale");
        req.setRefundVersion(
            Long.parseLong(request.getRefundApplyOrder().getMallRefundVersion()));
        req.setResult(true);
        req.setMessage(request.getReason());
        mall.call(request.getStore(), req);
      }
      return new RefundAuditResponse(request);
    } catch (Exception e) {
      return new RefundAuditResponse(request, false, e.getMessage());
    }
  }

  /**
   * @return 查询卖家收到的退款列表
   */
  @Override
  protected RefundQueryResponse doQueryRefund(RefundQueryRequest request) {
    try {
      RefundQueryResponse response;
      if (Assert.isTrue(request.getStore().getSetting().isTaobaoOrderCloudPush())) {
        response = taoBaoCloudPush(request);
      } else {
        response = queryByApi(request);
      }
      return response;
    } catch (ApiException e) {
      return new RefundQueryResponse(request, false, e.getErrMsg());
    }
  }

  private RefundQueryResponse taoBaoCloudPush(RefundQueryRequest request) throws ApiException {
    RefundQueryResponse response = new RefundQueryResponse(request);
    Map<String, Object> map = new HashMap<>(3);
    map.put("sellerNick", request.getStore().getNickname());
    map.put("jdpModifiedBegin", request.getBeginTime());
    map.put("jdpModifiedEnd", request.getEndTime());
    PageList<JdpTbRefund> list = rdsSqlSessionDecorator
        .selectList(REFUND_STATEMENT, map, request.getPage(), PAGE_SIZE);
    if (list != null && list.getTotal() > 0) {
      response
          .setHasNext(request.getPage() < MallUtil.calcTotalPage(PAGE_SIZE, request.getPage()));
      //存转换的订单
      List<MallRefundOrderInfo> orders = new ArrayList<>();
      for (JdpTbRefund tbRefund : list.getData()) {
        com.taobao.api.response.RefundGetResponse tbResponse = TaobaoUtils
            .parseResponse(tbRefund.getJdpResponse(),
                com.taobao.api.response.RefundGetResponse.class);
        orders.add(convertRefund(tbResponse.getRefund()));
      }
      response.setOrders(orders);
    }
    return response;
  }

  private RefundQueryResponse queryByApi(RefundQueryRequest request) {
    RefundQueryResponse response = new RefundQueryResponse(request);
    RefundsReceiveGetRequest getRequest = new RefundsReceiveGetRequest();
    getRequest.setFields(REFUND_FIELDS);
    getRequest.setStartModified(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
    getRequest.setEndModified(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
    getRequest.setPageNo((long) request.getPage());
    getRequest.setPageSize((long) PAGE_SIZE);
    getRequest.setUseHasNext(true);
    RefundsReceiveGetResponse getResponse = mall.call(request.getStore(), getRequest);
    if (getResponse.isSuccess()) {
      response.setHasNext(getResponse.getHasNext());
      if (!Assert.isEmpty(getResponse.getRefunds())) {
        List<MallRefundOrderInfo> orders = new ArrayList<>();
        for (Refund refund : getResponse.getRefunds()) {
          orders.add(convertRefund(refund));
        }
        response.setOrders(orders);
      }
    }
    return response;
  }

  /**
   * @return 获取单笔退单详情
   */
  @Override
  protected RefundGetResponse doGetRefund(RefundGetRequest request) {
    try {
      if (Assert.isTrue(request.getStore().getSetting().isTaobaoOrderCloudPush())) {
        Map<String, Object> map = new HashMap<>(1);
        map.put("refundId", request.getRefundId());
        JdpTbRefund tbRefund = rdsSqlSessionDecorator.selectOne(REFUND_STATEMENT, map);
        com.taobao.api.response.RefundGetResponse response = TaobaoUtils
            .parseResponse(tbRefund.getJdpResponse(),
                com.taobao.api.response.RefundGetResponse.class);
        return new RefundGetResponse(request, convertRefund(response.getRefund()));
      } else {
        com.taobao.api.request.RefundGetRequest getRequest = new com.taobao.api.request.RefundGetRequest();

        getRequest.setFields(REFUND_GET_FIELDS);
        getRequest.setRefundId(Long.parseLong(request.getRefundId()));
        com.taobao.api.response.RefundGetResponse response = mall
            .call(request.getStore(), getRequest);
        return new RefundGetResponse(request, convertRefund(response.getRefund()));
      }
    } catch (ApiException e) {
      return new RefundGetResponse(request, false, e.getErrMsg());
    }
  }

  public MallRefundOrderInfo convertRefund(Refund refund) {
    MallRefundOrderInfo refundOrder = new MallRefundOrderInfo();
    try {
      refundOrder.setTradeId(String.valueOf(refund.getTid()));
      refundOrder.setRefundId(String.valueOf(refund.getRefundId()));
      refundOrder.setOid(String.valueOf(refund.getOid()));
      refundOrder.setBuyerNick(refund.getBuyerNick());
      refundOrder.setSellerNick(refund.getSellerNick());
      refundOrder.setRefundTime(ConvertUtil.toLocalDateTime(refund.getCreated()));
      refundOrder.setModifiedTime(
          refund.getModified() == null ? null : DateTimeUtil.toLocalDateTime(refund.getModified()));
      refundOrder.setCreatedTime(ConvertUtil.toLocalDateTime(refund.getCreated()));  //与退款时间一致
      refundOrder.setReason(refund.getReason());
      refundOrder.setDescription(refund.getDesc());
      refundOrder.setTitle(refund.getTitle());
      refundOrder.setQuantity(Assert.isNull(refund.getNum()) ? 0 : refund.getNum().intValue());
      refundOrder.setExpressName(refund.getCompanyName());
      refundOrder.setExpressNo(refund.getSid());
      refundOrder.setOrderStatus(toOmsTradeStatus(refund.getOrderStatus()));
      refundOrder.setApplyStatus(
          OMS_REFUND_STATUS.getOrDefault(refund.getStatus(), MallRefundStatus.UNKNOWN));
      refundOrder.setRefundPhase(toOmsRefundPhase(refund.getRefundPhase()));
      refundOrder.setGoodsStatus(toOmsGoodsStatus(refund.getGoodStatus()));
      refundOrder.setRefundAmount(Double.valueOf(refund.getRefundFee()));
      refundOrder.setHasGoodReturn(refund.getHasGoodReturn());
      refundOrder.setVersion(String.valueOf(refund.getRefundVersion()));
      refundOrder.setWhole(false);
    } catch (Exception e) {
      LOGGER.info("退款封装数据异常,参数:", refund);
      LOGGER.info("退款封装数据异常,堆栈信息", e);
    }
    return refundOrder;
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TMALL, MallType.TMALL_HK};
  }
}
