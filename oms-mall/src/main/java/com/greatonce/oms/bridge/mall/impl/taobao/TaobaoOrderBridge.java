package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.MathUtil;
import com.greatonce.oms.bo.mall.MallSalesOrderDetailInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderDiscountInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInvoiceInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderPaymentInfo;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.bridge.mall.impl.AbstractOrderBridge;
import com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain.JdpTbTrade;
import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;
import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest;
import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest.OrderEvent;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;
import com.greatonce.oms.bridge.mall.response.OrderDeliveryResponse;
import com.greatonce.oms.bridge.mall.response.OrderEventUpdateResponse;
import com.greatonce.oms.bridge.mall.response.OrderGetResponse;
import com.greatonce.oms.bridge.mall.response.OrderQueryResponse;
import com.greatonce.oms.bridge.mall.response.OrderReceiverInfoUpdateResponse;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.admin.MallApp;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.mall.MallStepOrderStatus;
import com.greatonce.oms.domain.enums.trade.DiscountType;
import com.greatonce.oms.domain.enums.trade.PayType;
import com.greatonce.oms.domain.enums.trade.SalesOrderCreateType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.Order;
import com.taobao.api.domain.PromotionDetail;
import com.taobao.api.domain.Trade;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.request.LogisticsConsignResendRequest;
import com.taobao.api.request.LogisticsDummySendRequest;
import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.request.LogisticsOnlineSendRequest;
import com.taobao.api.request.QimenEventProduceRequest;
import com.taobao.api.request.TmallExchangeConsigngoodsRequest;
import com.taobao.api.request.TmallExchangeReturngoodsAgreeRequest;
import com.taobao.api.request.TradeFullinfoGetRequest;
import com.taobao.api.request.TradeShippingaddressUpdateRequest;
import com.taobao.api.request.TradesSoldIncrementGetRequest;
import com.taobao.api.response.LogisticsConsignResendResponse;
import com.taobao.api.response.LogisticsDummySendResponse;
import com.taobao.api.response.LogisticsOfflineSendResponse;
import com.taobao.api.response.LogisticsOnlineSendResponse;
import com.taobao.api.response.TmallExchangeConsigngoodsResponse;
import com.taobao.api.response.TmallExchangeReturngoodsAgreeResponse;
import com.taobao.api.response.TradeFullinfoGetResponse;
import com.taobao.api.response.TradeShippingaddressUpdateResponse;
import com.taobao.api.response.TradesSoldIncrementGetResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 淘宝订单查询/转换成商城通用格式类.
 *
 * @author lcc
 */
@Component
public class TaobaoOrderBridge extends AbstractOrderBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(TaobaoOrderBridge.class);
  private static final int PAGE_SIZE = 100;
  @Autowired
  private TaobaoMall mall;
  @Autowired
  @Qualifier("rdsSqlSessionDecorator")
  private SqlSessionDecorator rdsSqlSessionDecorator;
  @Value("${oms.mall.tb.stress-testing:false}")
  private boolean stressTesting;
  @Autowired
  private TaobaoSecurityBridge securityBridge;

  /**
   * 下载订单.
   *
   * 1.根据店铺下载配置从云推或者接口下载 2.分页下载，每页数量100 3.下载到ods订单后，处理并转化为MallSalesOrder
   */
  @Override
  protected OrderQueryResponse doQueryOrder(OrderQueryRequest request) {
    OrderQueryResponse response = new OrderQueryResponse(request);
    if (Assert.isTrue(request.getStore().getSetting().isTaobaoOrderCloudPush())) {
      queryOrderByCloudPush(request, response);
    } else {
      queryOrderByApi(request, response);
    }
    return response;
  }

  /**
   * 云推方式下载.
   */
  private void queryOrderByCloudPush(OrderQueryRequest request, OrderQueryResponse response) {
    Map<String, Object> map = new HashMap<>(5);
    map.put("tid", request.getTradeId());
    map.put("status", toMallStatus(request.getStatus()));
    map.put("jdpModifiedBegin", request.getBeginTime());
    map.put("jdpModifiedEnd", request.getEndTime());
    map.put("sellerNick", request.getStore().getNickname());
    int page = request.getPage();
    PageList<JdpTbTrade> jdpTbTradePageList =
        rdsSqlSessionDecorator.selectList(
            "com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.mappers.JdpTbTradeMapper.list",
            map, page, PAGE_SIZE);
    //处理结果
    List<JdpTbTrade> jdpTbTrades = jdpTbTradePageList.getData();
    if (Assert.isEmpty(jdpTbTrades)) {
      response.setOrders(null);
      response.setHasNext(false);
      return;
    }
    List<MallSalesOrderInfo> odsSaleOrders = new ArrayList<>(jdpTbTrades.size());
    for (JdpTbTrade jdpTbTrade : jdpTbTrades) {
      if (jdpTbTrade != null) {
        TradeFullinfoGetResponse tresponse = null;
        try {
          tresponse = TaobaoUtils
              .parseResponse(jdpTbTrade.getJdpResponse(), TradeFullinfoGetResponse.class);
        } catch (ApiException e) {
          LOGGER.info("淘宝订单结果解析失败：{}", e.getErrMsg());
        }
        if (tresponse != null) {
          Trade trade = tresponse.getTrade();
          if (Assert.isTrue(trade.getIsShShip())
              && convertOmsStatus(trade) == MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS) {
            LOGGER.info("菜鸟发货订单{}在未发货前不进入系统", trade.getTid());
            continue;
          }
          MallSalesOrderInfo mallSalesOrderInfo = convertOrder(trade);
          odsSaleOrders.add(mallSalesOrderInfo);
        }
      } else {
        LOGGER.error("淘宝查询失败！{}", JsonUtil.toJson(map));
      }
    }
    if (!Assert.isEmpty(odsSaleOrders)) {
      //解密会员昵称
      bathDecryptBuyerNicks(odsSaleOrders, request);
    }
    response.setHasNext(odsSaleOrders.size() == PAGE_SIZE);
    response.setOrders(odsSaleOrders);
  }

  /**
   * 根据淘宝接口方式下载.
   */
  private void queryOrderByApi(OrderQueryRequest request, OrderQueryResponse response) {
    TradesSoldIncrementGetRequest req = new TradesSoldIncrementGetRequest();
    req.setFields("tid");
    req.setStartModified(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
    req.setEndModified(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
    req.setPageNo((long) request.getPage());
    req.setPageSize((long) PAGE_SIZE);
    req.setStatus(toMallStatus(request.getStatus()));
    req.setUseHasNext(true);
    int i = 0;
    String err = null;
    TradesSoldIncrementGetResponse incrementGetResponse = null;
    while (i++ < 3) {
      try {
        incrementGetResponse = mall.call(request.getStore(), req);
        if (incrementGetResponse.isSuccess()) {
          break;
        }
      } catch (MallException e) {
        err = e.getMessage();
        LOGGER.info("下载订单列表失败，重试第{}次...{},", i, request);
      }
    }
    if (incrementGetResponse == null || !incrementGetResponse.isSuccess()) {
      response.setSuccess(false);
      response.setResult(incrementGetResponse != null ? incrementGetResponse.getSubMsg() : err);
      return;
    }
    if (!Assert.isEmpty(incrementGetResponse.getTrades())) {
      List<MallSalesOrderInfo> odsSaleOrders = new ArrayList<>(
          incrementGetResponse.getTrades().size());
      OrderGetRequest orderGetRequest = new OrderGetRequest(request.getStore());
      OrderGetResponse orderGetResponse;
      for (Trade trade : incrementGetResponse.getTrades()) {
        orderGetRequest.setTradeId(String.valueOf(trade.getTid()));
        orderGetResponse = doGetOrder(orderGetRequest);
        if (orderGetResponse.isSuccess()) {
          odsSaleOrders.add(orderGetResponse.getOrder());
        }
      }
      response.setOrders(odsSaleOrders);
      response.setHasNext(incrementGetResponse.getHasNext());
    }
  }

  /**
   * 根据tid下载单个订单，下载完成后转化成mallsalesorder
   */
  @Override
  protected OrderGetResponse doGetOrder(OrderGetRequest request) {
    int i = 0;
    String err = null;
    while (i++ < 3) {
      try {
        TradeFullinfoGetRequest req = new TradeFullinfoGetRequest();
        req.setFields(
            "seller_nick, buyer_nick, title, type, created, tid, status, payment, adjust_fee, post_fee, total_fee, pay_time, end_time, modified, consign_time, received_payment, commission_fee, buyer_memo, seller_memo, alipay_no,alipay_id,buyer_message, num_iid, num, price, buyer_alipay_no, receiver_name, receiver_state, receiver_city, receiver_district, receiver_address, receiver_zip, receiver_mobile, receiver_phone,seller_flag, seller_alipay_no, seller_mobile, seller_phone, seller_name, seller_email, available_confirm_fee, has_post_fee, timeout_action_time, snapshot_url, cod_fee, cod_status, shipping_type, trade_memo, is_3D,buyer_email,buyer_area, trade_from,is_lgtype,is_force_wlb,is_brand_sale,buyer_cod_fee,discount_fee,seller_cod_fee,express_agency_fee,invoice_name,service_orders,credit_cardfee,step_trade_status,step_paid_fee,mark_desc,eticket_ext,send_time,is_daixiao,is_part_consign,trade_from,order_from,orders");
        req.setTid(Long.valueOf(request.getTradeId()));
        TradeFullinfoGetResponse response = mall.call(request.getStore(), req);
        if (!response.isSuccess()) {
          return new OrderGetResponse(request, false, "订单下载失败");
        }
        Trade trade = response.getTrade();
        if (Assert.isTrue(trade.getIsShShip())
            && convertOmsStatus(trade) == MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS) {
          return new OrderGetResponse(request, false, "菜鸟发货订单未发货时不进入系统");
        }
        MallSalesOrderInfo mallSalesOrderInfo = convertOrder(trade);
        //解密买家昵称
        String decryptNick = securityBridge
            .decrypt(request.getStore(), mallSalesOrderInfo.getBuyerNick(), DataType.NICK);
        mallSalesOrderInfo.setBuyerNick(decryptNick);
        return new OrderGetResponse(request, mallSalesOrderInfo);
      } catch (Exception e) {
        err = e.getMessage();
        LOGGER.error("获取订单{}失败，重试第{}次..", request.getTradeId(), i);
        LOGGER.error("获取订单失败，异常：", e);
      }
    }
    return new OrderGetResponse(request, false, err);
  }

  /**
   * 批量解密买家昵称.
   */
  private void bathDecryptBuyerNicks(List<MallSalesOrderInfo> odsSaleOrders,
      OrderQueryRequest request) {
    List<String> buyerNicks =
        odsSaleOrders.stream().map(MallSalesOrderInfo::getBuyerNick).collect(Collectors.toList());
    Map<String, String> values =
        securityBridge.decrypt(request.getStore(), buyerNicks, DataType.NICK);
    odsSaleOrders.forEach(ods -> {
      String decryptNick = values.get(ods.getBuyerNick());
      ods.setBuyerNick(decryptNick);
    });
  }

  private MallSalesOrderInfo convertOrder(Trade trade) {
    MallSalesOrderInfo salesOrderInfo = new MallSalesOrderInfo();
    //时间信息
    salesOrderInfo.setCreatedTime(ConvertUtil.toLocalDateTime(trade.getCreated(), null));
    salesOrderInfo.setModifiedTime(ConvertUtil.toLocalDateTime(trade.getModified(), null));
    salesOrderInfo.setPaidTime(ConvertUtil.toLocalDateTime(trade.getPayTime(), null));
    salesOrderInfo.setFinishedTime(ConvertUtil.toLocalDateTime(trade.getEndTime(), null));
    //天猫规则：默认国内地址的国家为空，如果不为空就是外国订单
    if (!Assert.isEmpty(trade.getReceiverCountry()) && !trade.getReceiverCountry().equals("中国")) {
      salesOrderInfo.setCountry(trade.getReceiverCountry());
      salesOrderInfo.setForeign(true);
    }
    //收货信息
    salesOrderInfo.setProvince(trade.getReceiverState());
    salesOrderInfo.setCity(
        Assert.isEmpty(trade.getReceiverCity()) || Assert.isEmpty(trade.getReceiverCity().trim())
            ? "其它市" : trade.getReceiverCity());
    salesOrderInfo.setDistrict(Assert.isEmpty(trade.getReceiverDistrict()) || Assert
        .isEmpty(trade.getReceiverDistrict().trim()) ? "其它区" : trade.getReceiverDistrict());
    salesOrderInfo.setAddress(trade.getReceiverAddress());
    salesOrderInfo.setContact(trade.getReceiverName());
    salesOrderInfo.setMobile(trade.getReceiverMobile());
    salesOrderInfo.setTelephone(trade.getReceiverPhone());
    //订单信息
    salesOrderInfo.setCreateType(SalesOrderCreateType.DOWNLOAD);
    salesOrderInfo.setTradeId(String.valueOf(trade.getTid()));
    salesOrderInfo.setBuyerMemo(trade.getBuyerMessage());
    salesOrderInfo.setSellerMemo(trade.getSellerMemo());
    salesOrderInfo.setSellerNick(trade.getSellerNick());
    salesOrderInfo.setBuyerNick(trade.getBuyerNick());
    //主订单金额信息
    salesOrderInfo.setExpressFee(ConvertUtil.toDouble(trade.getPostFee()));
    salesOrderInfo.setFreightRiskFee(0D);
    salesOrderInfo.setSellingAmount(ConvertUtil.toDouble(trade.getTotalFee()));
    salesOrderInfo.setActualAmount(ConvertUtil.toDouble(trade.getPayment()));
    salesOrderInfo
        .setSettlementAmount(salesOrderInfo.getActualAmount() - salesOrderInfo.getExpressFee());
    //订单状态
    salesOrderInfo.setStatus(convertOmsStatus(trade));
    salesOrderInfo.setStepTradeStatus(toStepTradeStatus(trade));
    //优惠信息
    convertDiscount(trade, salesOrderInfo);
    //商品明细
    convertDetail(trade, salesOrderInfo);
    //支付信息
    convertPayment(trade, salesOrderInfo);
    //发票信息
    convertInvoice(trade, salesOrderInfo);

    return salesOrderInfo;
  }

  /**
   * 预付状态转换.
   */
  private MallStepOrderStatus toStepTradeStatus(Trade trade) {
    if (Assert.isEmpty(trade.getStepTradeStatus())) {
      return null;
    }
    switch (trade.getStepTradeStatus()) {
      case "FRONT_PAID_FINAL_NOPAID":
        return MallStepOrderStatus.DEPOSIT;
      case "FRONT_PAID_FINAL_PAID":
        return MallStepOrderStatus.RETAINAGE;
    }
    return null;
  }

  private String toMallStatus(MallSalesOrderStatus status) {
    String mallStatus = TaobaoConvertersMap.MALL_SALES_ORDER_STATUS_MAP.get(status);
    return mallStatus != null ? mallStatus : "WAIT_SELLER_SEND_GOODS";
  }

  /**
   * 修改订单状态
   */
  private MallSalesOrderStatus convertOmsStatus(Trade trade) {
    MallSalesOrderStatus mallSalesOrderStatus =
        TaobaoConvertersMap.ODS_ORDER_STATUS_MAP.get(trade.getStatus());
    return mallSalesOrderStatus != null ? mallSalesOrderStatus : MallSalesOrderStatus.UNKNOWN;
  }

  /**
   * 转换封装订单详情
   */
  private void convertDetail(Trade trade, MallSalesOrderInfo mallSalesOrderInfo) {
    List<MallSalesOrderDetailInfo> saleOrderDetailList;
    List<Order> orders = trade.getOrders();
    MallSalesOrderDetailInfo mallSalesOrderDetailInfo;
    double discountCounting = 0D;
    int circleFlag = 0;
    if (orders != null && !orders.isEmpty()) {
      saleOrderDetailList = new ArrayList<>(orders.size());
      for (Order order : orders) {
        circleFlag++;
        mallSalesOrderDetailInfo = new MallSalesOrderDetailInfo();
        mallSalesOrderDetailInfo.setOid(String.valueOf(order.getOid()));
        mallSalesOrderDetailInfo.setStatus(toSalesOrderDetailInfoStatus(order.getStatus()));
        mallSalesOrderDetailInfo.setRefundStatus(toMallDetailRefundStatus(order.getRefundStatus()));
        if (!Assert.isEmpty(order.getEstimateConTime())) {
          mallSalesOrderDetailInfo.setMallPresellDeliveryRequiring(order.getEstimateConTime());
        }
        //商品信息
        mallSalesOrderDetailInfo.setDeliveried(!Assert.isEmpty(order.getLogisticsCompany()));
        mallSalesOrderDetailInfo.setMallProductId(order.getNumIid().toString());
        mallSalesOrderDetailInfo.setMallProductName(order.getTitle());
        mallSalesOrderDetailInfo.setOuterCode(order.getOuterIid());
        mallSalesOrderDetailInfo.setMallSkuId(order.getSkuId());
        mallSalesOrderDetailInfo.setOuterSkuCode(order.getOuterSkuId());
        mallSalesOrderDetailInfo.setMallSkuName(order.getSkuPropertiesName());
        mallSalesOrderDetailInfo.setQuantity(ConvertUtil.toInt(order.getNum().toString()));
        //价格信息
        mallSalesOrderDetailInfo.setSellingPrice(ConvertUtil.toDouble(order.getPrice()));
        mallSalesOrderDetailInfo.setActualSellingPrice(
            MathUtil.divide(order.getTotalFee(), String.valueOf(order.getNum())));
        //金额信息
        mallSalesOrderDetailInfo.setSellingAmount(
            mallSalesOrderDetailInfo.getSellingPrice() * mallSalesOrderDetailInfo.getQuantity());
        mallSalesOrderDetailInfo.setActualSellingAmount(ConvertUtil.toDouble(order.getTotalFee()));
        //最后一条的优惠用总优惠减
        if (circleFlag == orders.size()) {
          mallSalesOrderDetailInfo.setDiscountAmount(
              MathUtil.minus(trade.getDiscountFee(), String.valueOf(discountCounting)));
        } else {
          //order.getPartMjzDiscount()为淘宝已经计算的优惠分摊，如果不为空就直接用
          mallSalesOrderDetailInfo.setDiscountAmount(
              !Assert.isEmpty(order.getPartMjzDiscount()) ? ConvertUtil
                  .toDouble(order.getPartMjzDiscount()) :
                  new BigDecimal(trade.getDiscountFee())
                      .multiply(new BigDecimal(mallSalesOrderDetailInfo.getSellingPrice()))
                      .divide(new BigDecimal(mallSalesOrderInfo.getSellingAmount()), 2,
                          BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP)
                      .doubleValue());
          discountCounting += mallSalesOrderDetailInfo.getDiscountAmount();
        }
        mallSalesOrderDetailInfo.setSettlementAmount(
            !Assert.isNull(order.getDivideOrderFee()) ? ConvertUtil
                .toDouble(order.getDivideOrderFee()) :
                MathUtil.minus(mallSalesOrderDetailInfo.getActualSellingAmount(),
                    mallSalesOrderDetailInfo.getDiscountAmount()));
        saleOrderDetailList.add(mallSalesOrderDetailInfo);
      }
      mallSalesOrderInfo.setDetails(saleOrderDetailList);
    }
  }

  /**
   * 明细退款状态转换
   */
  private MallRefundStatus toMallDetailRefundStatus(String refundStatus) {
    if (Assert.isEmpty(refundStatus)) {
      return MallRefundStatus.UNKNOWN;
    }
    MallRefundStatus mallRefundStatus = TaobaoConvertersMap.MALL_REFUND_STATUS_MAP
        .get(refundStatus);
    return mallRefundStatus != null ? mallRefundStatus : MallRefundStatus.UNKNOWN;
  }

  /**
   * 详情状态转换
   */
  private MallSalesOrderDetailStatus toSalesOrderDetailInfoStatus(String status) {
    if (Assert.isEmpty(status)) {
      return MallSalesOrderDetailStatus.UNKNOWN;
    }
    MallSalesOrderDetailStatus mallDetailStatus =
        TaobaoConvertersMap.MALL_SALES_ORDER_DETAIL_STATUS_MAP.get(status);
    return mallDetailStatus != null ? mallDetailStatus : MallSalesOrderDetailStatus.UNKNOWN;
  }

  /**
   * 转换封装支付信息
   */
  private void convertPayment(Trade trade, MallSalesOrderInfo salesOrderInfo) {
    List<MallSalesOrderPaymentInfo> paymentInfoList = new ArrayList<>();
    salesOrderInfo.setExpressFee(ConvertUtil.toDouble(trade.getPostFee()));
    //判断是否货到付款
    PayType payType = trade.getType().equals("cod") ? PayType.COD_PAY : PayType.ALI_PAY;
    if (payType == PayType.COD_PAY) {
      salesOrderInfo.setCod(true);
      salesOrderInfo.setCodAmount(salesOrderInfo.getActualAmount());
    }
    //淘宝订单中货到付款金额也是实付金额
    paymentInfoList.add(new MallSalesOrderPaymentInfo(salesOrderInfo.getActualAmount(), payType,
        salesOrderInfo.getPaidTime()));
    salesOrderInfo.setPayments(paymentInfoList);
  }

  /**
   * 转换封装优惠信息,找到订单中每件优惠的商品，并计算他们的实际销售金额
   */
  private void convertDiscount(Trade trade, MallSalesOrderInfo salesOrderInfo) {
    //等待转化的淘宝优惠详情
    List<PromotionDetail> promotionDetails = trade.getPromotionDetails();
    if (!Assert.isEmpty(promotionDetails)) {
      List<MallSalesOrderDiscountInfo> discountInfoList = new ArrayList<>(promotionDetails.size());
      //遍历优惠信息集合
      for (PromotionDetail detail : promotionDetails) {
        if (Assert.isEmpty(detail.getDiscountFee())) {
          continue;
        }

        MallSalesOrderDiscountInfo mallDiscountInfo = new MallSalesOrderDiscountInfo();
        mallDiscountInfo.setAmount(ConvertUtil.toDouble(detail.getDiscountFee()));
        mallDiscountInfo.setDiscountName(detail.getPromotionDesc());
        mallDiscountInfo.setDiscountId(detail.getPromotionId());
        mallDiscountInfo.setMallPaidTime(salesOrderInfo.getPaidTime());
        //淘宝优惠类型为其他优惠
        mallDiscountInfo.setType(DiscountType.OTHER);

        discountInfoList.add(mallDiscountInfo);
      }
      salesOrderInfo.setDiscounts(discountInfoList);
    }
  }

  /**
   * 转换发票信息
   */
  private void convertInvoice(Trade trade, MallSalesOrderInfo salesOrderInfo) {

    //淘宝订单没办法判断是普通发票还是增值税发票
    MallSalesOrderInvoiceInfo invoiceInfo = new MallSalesOrderInvoiceInfo(trade.getInvoiceName(),
        trade.getInvoiceType(), Double.valueOf(trade.getPayment()));
    salesOrderInfo.setInvoice(invoiceInfo);
  }

  /**
   * 天猫发货逻辑： 如果订单号非数字不处理 如果订单是费用订单走虚拟发货 如果是到付，走到付接口 非到付的看是否换货，是换货走换货发货接口 其他的走线下发货
   */
  @Override
  protected OrderDeliveryResponse doOrderDelivery(OrderDeliveryRequest request) {
    if (stressTesting) {
      MallApp mallApp = request.getStore().getMallApp();
      TaobaoClient client = new DefaultTaobaoClient("http://mockgw.hz.taeapp.com/gw",
          mallApp.getAppKey(), mallApp.getAppSecret());
      LogisticsOfflineSendRequest req = new LogisticsOfflineSendRequest();
      req.setTid(Long.valueOf(request.getSalesOrder().getTradeId()));
      req.setOutSid("YTO001");
      req.setCompanyCode("YTO");
      try {
        final LogisticsOfflineSendResponse response = client
            .execute(req, request.getStore().getAccessToken());
        LOGGER.info("压测发货：{},{}", request.getStore().getStoreName(),
            request.getSalesOrder().getTradeId());
        return new OrderDeliveryResponse(request, response.isSuccess(), response.getSubMsg());
      } catch (ApiException e) {
        return new OrderDeliveryResponse(request, false, e.getSubErrMsg());
      }
    } else {
      SalesOrder salesOrder = request.getSalesOrder();
      if (!Assert.isNumber(salesOrder.getTradeId())) {
        LOGGER.error("订单{}的交易号{}非数字，不处理", salesOrder.getSalesOrderCode(), salesOrder.getTradeId());
        return new OrderDeliveryResponse(request, false, "订单号非数字");
      }
      if (Assert.isTrue(salesOrder.getSub().isCod())) {
        return onlineSend(request);
      }
      switch (salesOrder.getSub().getSalesOrderType()) {
        case EXPENSE:
          return dummySend(request);
        case EXCHANGE:
          //判断是否有换货id，如果有就走换货，没有就走线下
          boolean isExchange = !Assert.isEmpty(salesOrder.getDetails()) &&
              salesOrder.getDetails().stream().anyMatch(x -> !Assert.isEmpty(x.getExchangeId()));
          if (isExchange) {
            LOGGER.info("交易号：{}的换货订单{}进行平台换货发货", salesOrder.getTradeId(),
                salesOrder.getSalesOrderCode());
            return exchangeReturngood(request);
          } else {
            LOGGER.info("交易号：{}的换货订单{}明细中没有换货单id，无法平台发货", salesOrder.getTradeId(),
                salesOrder.getSalesOrderCode());
            return new OrderDeliveryResponse(request);
          }
        default:
          return offlineSend(request);
      }
    }
  }

  /**
   * 发货后修改快递.
   */
  @Override
  protected OrderDeliveryResponse doOrderResetExpress(OrderDeliveryRequest request) {
    SalesOrder salesOrder = request.getSalesOrder();
    LogisticsConsignResendRequest mallResetExpressRequest = new LogisticsConsignResendRequest();
    mallResetExpressRequest.setTid(Long.valueOf(salesOrder.getTradeId()));
    mallResetExpressRequest.setOutSid(request.getExpressNo());
    mallResetExpressRequest.setCompanyCode(request.getMallExpressCode());
    if (request.isPartDelivery()) {
      //拼接子订单id
      StringBuilder builder = new StringBuilder();
      request.getDetails().stream()
          .filter(x -> !Assert.isEmpty(x.getSalesOrderDetail().getMallDetailId()))
          .forEach(x -> builder.append(x.getSalesOrderDetail().getMallDetailId()).append(","));
      builder.deleteCharAt(builder.length() - 1);

      mallResetExpressRequest.setIsSplit(1L);
      mallResetExpressRequest.setSubTid(builder.toString());
    }
    LogisticsConsignResendResponse response =
        mall.call(request.getStore(), mallResetExpressRequest, false);
    return new OrderDeliveryResponse(request, response.isSuccess(), response.getMsg());
  }

  @Override
  protected OrderEventUpdateResponse doNoticeOrderEvent(OrderEventUpdateRequest request) {
    if (Assert.isNull(request.getOrderEvent())) {
      throw new OmsException("事件状态不能为空");
    }
    if (Assert.isNull(request.getTradeId())) {
      throw new OmsException("订单号不能为空");
    }
    QimenEventProduceRequest req = new QimenEventProduceRequest();
    req.setStatus(reverseOrderEvent(request.getOrderEvent()));
    req.setTid(request.getTradeId());
    req.setCreate(request.getCreateTime());
    if (stressTesting) {
//      req.putOtherTextParam("tb_eagleeyex_t", "1");
      MallApp mallApp = request.getStore().getMallApp();
      TaobaoClient client = new DefaultTaobaoClient("http://mockgw.hz.taeapp.com/gw",
          mallApp.getAppKey(), mallApp.getAppSecret());
      try {
        client.execute(req, request.getStore().getAccessToken());
      } catch (ApiException e) {
        throw new MallException("回传订单状态失败！" + e.getSubErrMsg());
      }
    } else {
      mall.call(request.getStore(), req);
    }
    return new OrderEventUpdateResponse(request);
  }

  /**
   * 转换事件状态
   */
  private String reverseOrderEvent(OrderEvent orderEvent) {
    return TaobaoConvertersMap.ORDER_EVENT_MAP.get(orderEvent);
  }

  /**
   * 虚拟发货
   */
  private OrderDeliveryResponse dummySend(OrderDeliveryRequest request) {
    LogisticsDummySendRequest dummySendRequest = new LogisticsDummySendRequest();
    dummySendRequest.setTid(Long.valueOf(request.getSalesOrder().getTradeId()));
    LogisticsDummySendResponse response = mall.call(request.getStore(), dummySendRequest, false);
    if (response.isSuccess()) {
      return new OrderDeliveryResponse(request);
    } else if (TaobaoConvertersMap.DELIVERED_CODE_MAP.get("dummy").equals(response.getSubCode())) {
      return new OrderDeliveryResponse(request, true, "delivered");
    } else {
      return new OrderDeliveryResponse(request, false, response.getMsg());
    }
  }

  /**
   * 换货发货 先确认收货，再发货
   */
  private OrderDeliveryResponse exchangeReturngood(OrderDeliveryRequest request) {
    SalesOrder salesOrder = request.getSalesOrder();
    List<String> exchangeIds = salesOrder.getDetails().stream()
        .filter(x -> !Assert.isEmpty(x.getExchangeId())).map(SalesOrderDetail::getExchangeId)
        .collect(Collectors.toList());
    for (String exchangeId : exchangeIds) {
      TmallExchangeReturngoodsAgreeRequest agreeRequest = new TmallExchangeReturngoodsAgreeRequest();
      agreeRequest.setDisputeId(Long.valueOf(exchangeId));
      agreeRequest.setFields("dispute_id,bizorder_id, modified, status");
      TmallExchangeReturngoodsAgreeResponse agreeResponse = mall
          .call(request.getStore(), agreeRequest, false);
      if (!agreeResponse.isSuccess()) {
        return new OrderDeliveryResponse(request, false, agreeResponse.getMsg());
      }
      TmallExchangeConsigngoodsRequest consigngoodsRequest = new TmallExchangeConsigngoodsRequest();
      consigngoodsRequest.setDisputeId(Long.valueOf(exchangeId));
      consigngoodsRequest.setLogisticsCompanyName(request.getMallExpressName());
      consigngoodsRequest.setLogisticsNo(request.getExpressNo());
      consigngoodsRequest.setLogisticsType(200L);
      consigngoodsRequest.setFields("dispute_id, bizorder_id, status, modified");
      TmallExchangeConsigngoodsResponse consigngoodsResponse = mall
          .call(request.getStore(), consigngoodsRequest, false);
      if (!consigngoodsResponse.isSuccess()) {
        return new OrderDeliveryResponse(request, false, consigngoodsResponse.getMsg());
      }
    }
    return new OrderDeliveryResponse(request);
  }

  /**
   * 线上发货（支持到付）
   */
  private OrderDeliveryResponse onlineSend(OrderDeliveryRequest request) {
    LogisticsOnlineSendRequest req = new LogisticsOnlineSendRequest();
    req.setTid(Long.valueOf(request.getSalesOrder().getTradeId()));
    req.setOutSid(request.getExpressNo());
    req.setCompanyCode(request.getMallExpressCode());
    LogisticsOnlineSendResponse response = mall.call(request.getStore(), req, false);
    if (response.isSuccess()) {
      return new OrderDeliveryResponse(request);
    } else if (TaobaoConvertersMap.DELIVERED_CODE_MAP.get("online").equals(response.getSubCode())) {
      return new OrderDeliveryResponse(request, true, "delivered");
    } else {
      return new OrderDeliveryResponse(request, false, response.getMsg());
    }
  }

  /**
   * 线下发货 如果订单是部分发货，则平台为拆单
   */
  private OrderDeliveryResponse offlineSend(OrderDeliveryRequest request) {
    SalesOrder salesOrder = request.getSalesOrder();
    LogisticsOfflineSendRequest req = new LogisticsOfflineSendRequest();
    req.setTid(Long.valueOf(salesOrder.getTradeId()));
    req.setOutSid(request.getExpressNo());
    req.setCompanyCode(request.getMallExpressCode());
    if (request.isPartDelivery()) {
      req.setIsSplit(1L);
      //拼接拆单订单id
      StringBuilder sb = new StringBuilder();
      for (OrderDeliveryRequest.OrderDeliveryDetail detail : request.getDetails()) {
        if (Assert.isEmpty(detail.getSalesOrderDetail().getMallDetailId())) {
          continue;
        }
        sb.append(",").append(detail.getSalesOrderDetail().getMallDetailId());
      }
      sb.delete(0, 1);
      req.setSubTid(sb.toString());
      LOGGER.info("订单{}平台拆单发货", salesOrder.getSalesOrderCode());
    }
    LogisticsOfflineSendResponse response = mall.call(request.getStore(), req, false);
    if (response.isSuccess()) {
      return new OrderDeliveryResponse(request);
    } else if (TaobaoConvertersMap.DELIVERED_CODE_MAP.get("offline")
        .equals(response.getSubCode())) {
      return new OrderDeliveryResponse(request, true, "delivered");
    } else {
      return new OrderDeliveryResponse(request, false, response.getMsg());
    }
  }

  public OrderReceiverInfoUpdateResponse doUpdateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request) {
    OrderReceiverInfoUpdateResponse response = new OrderReceiverInfoUpdateResponse(request, true,
        "成功");

    TradeShippingaddressUpdateRequest req = new TradeShippingaddressUpdateRequest();
    req.setTid(Long.valueOf(request.getTradeId()));
    req.setReceiverName(request.getContact());
    req.setReceiverPhone(request.getTelephone());
    req.setReceiverMobile(request.getMobile());
    req.setReceiverState(request.getProvinceName());
    req.setReceiverCity(request.getCityName());
    req.setReceiverDistrict(request.getDistrictName());
    req.setReceiverAddress(request.getAddress());
    req.setReceiverZip(request.getZipcode());
    TradeShippingaddressUpdateResponse resp = mall.call(request.getStore(), req, false);
    if (!resp.isSuccess()) {
      LOGGER.error("调用淘宝收货人信息修改接口失败。请求：{}；错误信息：{}", JsonUtil.toJson(request), resp.getSubMsg());
      response.setSuccess(false);
      response.setResult(resp.getSubMsg());
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.info("调用淘宝收货人信息修改接口成功。交易号：{}", request.getTradeId());
    }
    return response;
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TMALL, MallType.TMALL_HK};
  }
}

