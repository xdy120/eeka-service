package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.mall.MallExchangeOrderInfo;
import com.greatonce.oms.bridge.mall.impl.AbstractExchangeBridge;
import com.greatonce.oms.bridge.mall.request.*;
import com.greatonce.oms.bridge.mall.response.*;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundPhase;
import com.taobao.api.request.*;
import com.taobao.api.response.TmallExchangeGetResponse;
import com.taobao.api.response.TmallExchangeReceiveGetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 淘宝换货单接口
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @author changan.cheng
 * @version 2018/6/2
 */
@Component
public class TaobaoExchangeBridge extends AbstractExchangeBridge {

  static final int PAGE_SIZE = 100;
  /**
   * 查询单笔换货单详情返回字段
   */
  static final String EXCHANGE_GET = "dispute_id, bizorder_id, num, buyer_nick, status, created, modified, reason, title, buyer_logistic_no, seller_logistic_no, bought_sku, exchange_sku, buyer_address, address, buyer_phone, buyer_logistic_name, seller_logistic_name, alipay_no, buyer_name, seller_nick";
  /**
   * 查询 卖家换货单列表 返回字段
   */
  static final String EXCHANGE_RECEIVE_GET = "dispute_id, bizorder_id, num, buyer_nick, status, created, modified, reason, title, buyer_logistic_no, seller_logistic_no, bought_sku, exchange_sku, buyer_address, address, buyer_phone, buyer_logistic_name, seller_logistic_name, alipay_no, buyer_name, seller_nick";
  /**
   * 拒绝换货的返回字段
   */
  static final String EXCHANGE_REFUSE = "dispute_id, bizorder_id, modified, status";
  /**
   * 同意换货的返回字段
   */
  static final String EXCHANGE_AGREE = "dispute_id, bizorder_id, modified, status";
  @Autowired
  TaobaoMall mall;
  @Resource
  SqlSessionDecorator rdsSqlSessionDecorator;

  /**
   * @return 卖家同意换货
   */
  @Override
  protected ExchangeAgreeResponse doAgree(ExchangeAgreeRequest request) {
    try {
      TmallExchangeAgreeRequest agreeRequest = new TmallExchangeAgreeRequest();
      agreeRequest.setDisputeId(request.getDisputeId());
      agreeRequest.setAddressId(request.getAddressId());
      agreeRequest.setFields(EXCHANGE_AGREE);
      ExchangeAgreeResponse exchangeAgreeResponse = new ExchangeAgreeResponse(request);
      exchangeAgreeResponse.setSuccess(true);
      return exchangeAgreeResponse;
    } catch (Exception e) {
      return new ExchangeAgreeResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected ExchangeRefuseResponse doRefuse(ExchangeRefuseRequest request) {
    try {
      TmallExchangeRefuseRequest refuseRequest = new TmallExchangeRefuseRequest();
      refuseRequest.setDisputeId(request.getDisputeId());
      refuseRequest.setSellerRefuseReasonId(request.getReasonId());
      refuseRequest.setFields(EXCHANGE_REFUSE);
      ExchangeRefuseResponse refuseResponse = new ExchangeRefuseResponse(request);
//            TmallExchangeRefuseResponse call = mall.call(request.getStore(), refuseRequest); todo
//            refuseResponse.setSuccess("SUCCESS".equalsIgnoreCase(call.getResult().getMessage()));
      refuseResponse.setSuccess(true);
      return refuseResponse;
    } catch (Exception e) {
      return new ExchangeRefuseResponse(request, false, e.getMessage());
    }
  }

  /**
   * @param request
   * @return
   */
  @Override
  protected ExchangeAuditResponse doAudit(ExchangeAuditRequest request) {
    try {
      //todo  没有找到 审核的接口
      RpRefundReviewRequest req = new RpRefundReviewRequest();
      req.setRefundId(Long.parseLong(request.getRefundApplyOrder().getMallRefundId()));
      req.setOperator(request.getOperator());
      req.setRefundPhase(
          request.getRefundApplyOrder().getMallRefundPhase() == MallRefundPhase.ON_SALE ? "onsale"
              : "aftersale");
      req.setRefundVersion(Long.parseLong(request.getRefundApplyOrder().getMallRefundVersion()));
      req.setResult(true);
      req.setMessage(request.getReason());

      mall.call(request.getStore(), req);
      return new ExchangeAuditResponse(request);
    } catch (Exception e) {
      return new ExchangeAuditResponse(request, false, e.getMessage());
    }
  }

  /**
   * @return 卖家查询换货列表
   */
  @Override
  protected ExchangeQueryResponse doQueryExchange(ExchangeQueryRequest request) {
    try {
      ExchangeQueryResponse response = new ExchangeQueryResponse(request);
      TmallExchangeReceiveGetRequest getRequest = new TmallExchangeReceiveGetRequest();
      getRequest.setFields(EXCHANGE_RECEIVE_GET);
      getRequest.setStartGmtModifiedTime(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
      getRequest.setEndGmtModifedTime(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
      getRequest.setPageNo((long) request.getPage());
      getRequest.setPageSize((long) PAGE_SIZE);

      TmallExchangeReceiveGetResponse getResponse = mall.call(request.getStore(), getRequest);
      if (getResponse.isSuccess()) {
        response.setHasNext(getResponse.getHasNext());
        List<MallExchangeOrderInfo> orders = new ArrayList<>();
        if (getResponse.getResults() != null) {
          for (TmallExchangeReceiveGetResponse.Exchange exchange : getResponse.getResults()) {
            orders.add(convertExchange(exchange, request.getStore()));
          }
        }
        response.setOrders(orders);
      }
      return response;
    } catch (Exception e) {
      return new ExchangeQueryResponse(request, false, e.getMessage());
    }
  }

  /**
   * @return 获取单笔退单详情
   */
  @Override
  protected ExchangeGetResponse doGetExchange(ExchangeGetRequest request) {
    try {

      TmallExchangeGetRequest getRequest = new TmallExchangeGetRequest();
      // 返回的字段
      getRequest.setFields(EXCHANGE_GET);
      //换货单号 id
      getRequest.setDisputeId(Long.parseLong(request.getExchangeId()));
      TmallExchangeGetResponse response = mall.call(request.getStore(), getRequest);
      return new ExchangeGetResponse(request, convertExchange(response.getResult().getExchange()));
    } catch (Exception e) {
      return new ExchangeGetResponse(request, false, e.getMessage());
    }
  }


  /**
   * 转换单笔换货单
   */
  public MallExchangeOrderInfo convertExchange(TmallExchangeGetResponse.Exchange exchange) {
    MallExchangeOrderInfo exchangeOrder = new MallExchangeOrderInfo();
    exchangeOrder.setExchangeId(exchange.getDisputeId());
    exchangeOrder.setOid(exchange.getBizOrderId());
    exchangeOrder.setBuyerNick(exchange.getBuyerNick());
    exchangeOrder.setSellerNick(exchange.getSellerNick());
    exchangeOrder.setModifiedTime(DateTimeUtil.toLocalDateTime(exchange.getModified()));
    exchangeOrder.setReason(exchange.getReason());
    exchangeOrder.setDescription(exchange.getDesc());
    exchangeOrder.setPayNo(exchange.getAlipayNo());
    exchangeOrder.setBuyerExpressNo(exchange.getBuyerLogisticNo());
    exchangeOrder.setBuyerExpressName(exchange.getBuyerLogisticName());
    exchangeOrder.setSellerExpressNo(exchange.getSellerLogisticNo());
    exchangeOrder.setSellerExpressName(exchange.getSellerLogisticName());
    exchangeOrder.setMobile(exchange.getBuyerPhone());
    exchangeOrder.setContact(exchange.getBuyerName());
    exchangeOrder.setCreatedTime(DateTimeUtil.toLocalDateTime(exchange.getCreated()));
    exchangeOrder.setVersion(String.valueOf(exchange.getRefundVersion()));
    exchangeOrder.setInMallProductName(exchange.getTitle());
    exchangeOrder.setInMallSkuId(exchange.getBoughtSku());
    exchangeOrder.setOutMallSkuId(exchange.getExchangeSku());
    String buyerAddress = exchange.getBuyerAddress();
    setAddress(exchangeOrder, buyerAddress);
    exchangeOrder.setExchangeStatus(exchange.getStatus());
    return exchangeOrder;
  }


  /**
   * 换货单列表
   */
  public MallExchangeOrderInfo convertExchange(TmallExchangeReceiveGetResponse.Exchange exchange,
      Store store) {

    TmallExchangeGetRequest getRequest = new TmallExchangeGetRequest();
    // 返回的字段
    getRequest.setFields(EXCHANGE_GET);
    //换货单号 id
    getRequest.setDisputeId(Long.valueOf(exchange.getDisputeId()));
    TmallExchangeGetResponse response = mall.call(store, getRequest);

    MallExchangeOrderInfo exchangeOrder = new MallExchangeOrderInfo();
    exchangeOrder.setExchangeId(exchange.getDisputeId());
    exchangeOrder.setOid(exchange.getBizOrderId());
    exchangeOrder.setBuyerNick(exchange.getBuyerNick());
    exchangeOrder.setSellerNick(exchange.getSellerNick());
    exchangeOrder.setModifiedTime(DateTimeUtil.toLocalDateTime(exchange.getModified()));
    exchangeOrder.setReason(exchange.getReason());
    exchangeOrder.setPayNo(exchange.getAlipayNo());
    exchangeOrder.setBuyerExpressNo(exchange.getBuyerLogisticNo());
    exchangeOrder.setBuyerExpressName(exchange.getBuyerLogisticName());
    exchangeOrder.setSellerExpressNo(exchange.getSellerLogisticNo());
    exchangeOrder.setSellerExpressName(exchange.getSellerLogisticName());
    exchangeOrder.setMobile(exchange.getBuyerPhone());
    exchangeOrder.setContact(exchange.getBuyerName());
    exchangeOrder.setCreatedTime(DateTimeUtil.toLocalDateTime(exchange.getCreated()));
    exchangeOrder.setInMallProductName(exchange.getTitle());
    exchangeOrder.setInMallSkuId(exchange.getBoughtSku());
    exchangeOrder.setOutMallSkuId(exchange.getExchangeSku());
    String buyerAddress = exchange.getBuyerAddress();
    setAddress(exchangeOrder, buyerAddress);
    exchangeOrder.setExchangeStatus(exchange.getStatus());
    exchangeOrder.setQuantity(Integer.valueOf(exchange.getNum().toString()));

    exchangeOrder.setVersion(response.getResult().getExchange().getRefundVersion() == null ? "0"
        : String.valueOf(response.getResult().getExchange().getRefundVersion()));
    return exchangeOrder;
  }


  /**
   * 切割地址
   */
  private void setAddress(MallExchangeOrderInfo orderInfo, String addressStr) {
    String[] address = addressStr.split("\\^\\^\\^");
    if (address.length == 0) {
      return;
    }
    //省
    orderInfo.setProvince(address[0]);
    if (address.length >= 2) {
      //市
      orderInfo.setCity(address[1]);
    }
    if (address.length >= 3) {
      //区
      orderInfo.setDistrict(address[2]);
    }
    if (address.length >= 4) {
      //详细地址
      orderInfo.setAddress(address[3]);
    }
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TMALL, MallType.TMALL_HK, MallType.TAOBAO_FX,
        MallType.TAOBAO_JX};
  }
}
