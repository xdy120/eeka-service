package com.greatonce.oms.bridge.mall.impl.jd;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.mall.MallSalesOrderDetailInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderDiscountInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInvoiceInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderPaymentInfo;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractOrderBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;
import com.greatonce.oms.bridge.mall.response.OrderDeliveryResponse;
import com.greatonce.oms.bridge.mall.response.OrderGetResponse;
import com.greatonce.oms.bridge.mall.response.OrderQueryResponse;
import com.greatonce.oms.bridge.mall.response.OrderReceiverInfoUpdateResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.DiscountType;
import com.greatonce.oms.domain.enums.trade.InvoiceType;
import com.greatonce.oms.domain.enums.trade.PayType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.CouponDetail;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.ItemInfo;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.OrderSearchInfo;
import com.jd.open.api.sdk.domain.order.OrderQueryJsfService.VatIncoiceInfo;
import com.jd.open.api.sdk.request.etms.EtmsWaybillSendRequest;
import com.jd.open.api.sdk.request.order.OrderLbpOutstorageRequest;
import com.jd.open.api.sdk.request.order.OrderLbpWaybillUpdateRequest;
import com.jd.open.api.sdk.request.order.OrderSopOutstorageRequest;
import com.jd.open.api.sdk.request.order.OrderSopWaybillUpdateRequest;
import com.jd.open.api.sdk.request.order.OrderSoplOutstorageRequest;
import com.jd.open.api.sdk.request.order.OrderSoplWaybillUpdateRequest;
import com.jd.open.api.sdk.request.order.PopOrderGetRequest;
import com.jd.open.api.sdk.request.order.PopOrderSearchRequest;
import com.jd.open.api.sdk.request.order.PopOrderShipmentRequest;
import com.jd.open.api.sdk.response.AbstractResponse;
import com.jd.open.api.sdk.response.etms.EtmsWaybillSendResponse;
import com.jd.open.api.sdk.response.order.OrderLbpOutstorageResponse;
import com.jd.open.api.sdk.response.order.OrderLbpWaybillUpdateResponse;
import com.jd.open.api.sdk.response.order.OrderSopOutstorageResponse;
import com.jd.open.api.sdk.response.order.OrderSopWaybillUpdateResponse;
import com.jd.open.api.sdk.response.order.OrderSoplOutstorageResponse;
import com.jd.open.api.sdk.response.order.OrderSoplWaybillUpdateResponse;
import com.jd.open.api.sdk.response.order.PopOrderGetResponse;
import com.jd.open.api.sdk.response.order.PopOrderSearchResponse;
import com.jd.open.api.sdk.response.order.PopOrderShipmentResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JdOrderBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/9
 */
@Component
public class JdOrderBridge extends AbstractOrderBridge {

  private static final int PAGE_SIZE = 100;
  private static final Logger LOGGER = LoggerFactory.getLogger(JdOrderBridge.class);
  private static final Pattern INVOICE_PATTERN = Pattern
      .compile("发票类型:(?<type>[^;]+);发票抬头:(?<title>[^;]+);发票内容:(?<content>[^;]+)");
  private static final Pattern PAY_TYPE_PATTERN = Pattern.compile("-(?<paytype>.*)");
  private static final Pattern SOURCE_TRADE_PATTERN = Pattern.compile("原订单号:(?<tradeId>[\\d]+)");
  private static final String QUERY_ORDER_FIELDS = "orderId,venderId,orderType,payType,orderTotalPrice,orderSellerPrice,orderPayment,freightPrice,sellerDiscount,orderState,orderStateRemark,deliveryType,invoiceInfo,invoiceCode,orderRemark,orderStartTime,orderEndTime,consigneeInfo,itemInfoList,couponDetailList,venderRemark,balanceUsed,pin,returnOrder,paymentConfirmTime,waybill,logisticsId,vatInfo,modified,directParentOrderId,parentOrderId,customs,customsModel,orderSource,storeOrder,idSopShipmenttype,scDT,serviceFee,pauseBizInfo,taxFee,tuiHuoWuYou,orderSign,storeId";
  private static final Map<String, PayType> PAY_TYPE_MAP = new HashMap<>(5);
  private static final Map<String, PayType> MALL_COUPON_MAP = new HashMap<>(4);
  private static final Map<String, DiscountType> MERCHANT_COUPON_MAP = new HashMap<>(6);
  private static final Map<MallSalesOrderStatus, String> MALL_STATS_MAP = new HashMap<>(2);
  private static final Map<String, MallSalesOrderStatus> MALL_SALES_ORDER_STATUS_MAP = new HashMap<>(
      4);

  static {
    PAY_TYPE_MAP.put("货到付款", PayType.COD_PAY);
    PAY_TYPE_MAP.put("邮局汇款", PayType.POSTAL);
    PAY_TYPE_MAP.put("在线支付", PayType.ONLINE_PAY);
    PAY_TYPE_MAP.put("公司转账", PayType.UNION_PAY);
    PAY_TYPE_MAP.put("银行卡转账", PayType.UNION_PAY);

    MALL_COUPON_MAP.put("礼品卡", PayType.GIFT_CARD);
    MALL_COUPON_MAP.put("京豆", PayType.JINGDOU);
    MALL_COUPON_MAP.put("红包", PayType.RED_PACKET);
    MALL_COUPON_MAP.put("京东券", PayType.COUPON);

    MERCHANT_COUPON_MAP.put("套装", DiscountType.SUIT);
    MERCHANT_COUPON_MAP.put("闪团", DiscountType.FLASH_GROUP);
    MERCHANT_COUPON_MAP.put("团购", DiscountType.GROUP_PURCHASE);
    MERCHANT_COUPON_MAP.put("单品", DiscountType.SINGLE_PRODUCT);
    MERCHANT_COUPON_MAP.put("满返满送", DiscountType.FULL_BACK_FULL_SEND);
    MERCHANT_COUPON_MAP.put("店铺", DiscountType.STORE_DISCOUNT);

    MALL_STATS_MAP.put(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS, "WAIT_SELLER_STOCK_OUT");
    MALL_STATS_MAP.put(MallSalesOrderStatus.TRADE_FINISHED, "FINISHED_L");
  }


  @Autowired
  private JdMall mall;

  /**
   * 订单列表
   */
  @Override
  public OrderQueryResponse doQueryOrder(OrderQueryRequest request) {
    try {
      PopOrderSearchRequest req = new PopOrderSearchRequest();
      req.setOptionalFields(QUERY_ORDER_FIELDS);
      req.setStartDate(String.valueOf(DateTimeUtil.format(request.getBeginTime())));
      req.setEndDate(String.valueOf(DateTimeUtil.format(request.getEndTime())));
      req.setPage(String.valueOf(request.getPage()));
      req.setPageSize(String.valueOf(PAGE_SIZE));
      req.setOrderState(MALL_STATS_MAP.getOrDefault(request.getStatus(), "WAIT_SELLER_STOCK_OUT"));
      PopOrderSearchResponse resp = mall.call(request.getStore(), req, true);
      OrderQueryResponse response = new OrderQueryResponse(request);
      int pages = MallUtil
          .calcTotalPage(PAGE_SIZE, resp.getSearchorderinfoResult().getOrderTotal());
      if (pages > 0) {
        response.setHasNext(request.getPage() < pages);
        List<MallSalesOrderInfo> list = convertSaleOrder(
            resp.getSearchorderinfoResult().getOrderInfoList());
        response.setOrders(list);
      }
      return response;
    } catch (Exception e) {
      LOGGER.error("京东解析订单失败!", e);
      return new OrderQueryResponse(request, false, e.getMessage());
    }
  }

  /**
   * 单个订单明细
   */
  @Override
  public OrderGetResponse doGetOrder(OrderGetRequest request) {
    try {
      PopOrderGetRequest getRequest = new PopOrderGetRequest();
      getRequest.setOptionalFields(QUERY_ORDER_FIELDS);
      getRequest.setOrderId(Long.parseLong(request.getTradeId()));
      PopOrderGetResponse response = mall.call(request.getStore(), getRequest, true);
      if (response.getOrderDetailInfo().getApiResult().getSuccess()) {
        OrderSearchInfo orderInfo = response.getOrderDetailInfo().getOrderInfo();
        if (!Assert.isNull(orderInfo)) {
          if ("京仓订单".equals(orderInfo.getStoreOrder()) && Assert.isEmpty(orderInfo.getWaybill())) {
            LOGGER.info("京仓订单{},未发货前不进入系统", orderInfo.getOrderId());
            return new OrderGetResponse(request, false, "京仓订单未发货前不进入系统");
          }else {
            MallSalesOrderInfo mallSalesOrderInfo = convertOrder(orderInfo);
            return new OrderGetResponse(request, mallSalesOrderInfo);
          }
        } else {
          return new OrderGetResponse(request, false, "未找到商品");
        }
      } else {
        return new OrderGetResponse(request, false,
            response.getOrderDetailInfo().getApiResult().getChineseErrCode());
      }
    } catch (Exception e) {
      return new OrderGetResponse(request, false, e.getMessage());
    }
  }

  /**
   * 订单发货
   */
  @Override
  protected OrderDeliveryResponse doOrderDelivery(OrderDeliveryRequest req) {
    try {
      Store store = req.getStore();
      switch (store.getSetting().getJingdongMode()) {
        case SOP:
          return sopSend(req);
        default:
          return new OrderDeliveryResponse(req, false,
              "京东只支持SOP/SOPL/LBP三种模式发货。请确认店铺设置-发货模式是否设置正确");
      }
    } catch (Exception e) {
      LOGGER.error("订单:{},发货失败,原因:", req.getSalesOrder(), e);
      return new OrderDeliveryResponse(req, false, e.getMessage());
    }
  }

  private List<MallSalesOrderInfo> convertSaleOrder(List<OrderSearchInfo> listTrade) {
    List<MallSalesOrderInfo> orders = new ArrayList<>(listTrade.size());
    for (OrderSearchInfo trade : listTrade) {
      if ("京仓订单".equals(trade.getStoreOrder()) && Assert.isEmpty(trade.getWaybill())) {
        LOGGER.info("京仓订单{},未发货前不进入系统", trade.getOrderId());
        continue;
      }
      MallSalesOrderInfo mallSalesOrderInfo = convertOrder(trade);
      if (!Assert.isNull(mallSalesOrderInfo)) {
        orders.add(mallSalesOrderInfo);
      }
    }
    return orders;
  }

  private MallSalesOrderInfo convertOrder(OrderSearchInfo trade) {
    MallSalesOrderInfo salesOrderInfo = new MallSalesOrderInfo();
    //时间信息
    salesOrderInfo.setCreatedTime(ConvertUtil.toLocalDateTime(trade.getOrderStartTime()));
    salesOrderInfo.setModifiedTime(ConvertUtil.toLocalDateTime(trade.getModified()));
    salesOrderInfo.setPaidTime(
        ConvertUtil.toLocalDateTime(trade.getPaymentConfirmTime(), LocalDateTime.now()));
    salesOrderInfo.setFinishedTime(ConvertUtil.toLocalDateTime(trade.getOrderEndTime(), null));
    //收货信息
    salesOrderInfo.setContact(trade.getConsigneeInfo().getFullname());
    salesOrderInfo.setProvince(trade.getConsigneeInfo().getProvince());
    salesOrderInfo.setCity(trade.getConsigneeInfo().getCity());
    salesOrderInfo.setDistrict(trade.getConsigneeInfo().getCounty());
    salesOrderInfo.setAddress(trade.getConsigneeInfo().getFullAddress());
    salesOrderInfo.setMobile(trade.getConsigneeInfo().getMobile());
    salesOrderInfo.setTelephone(trade.getConsigneeInfo().getTelephone());
    //订单信息
    salesOrderInfo.setTradeId(trade.getOrderId());
    salesOrderInfo.setBuyerMemo(trade.getOrderRemark());
    salesOrderInfo.setSellerMemo(trade.getVenderRemark());
    salesOrderInfo.setSellerNick(trade.getVenderId());
    salesOrderInfo.setBuyerNick(trade.getPin());
    salesOrderInfo.setThirdDelivery(!Assert.isEmpty(trade.getStoreOrder()));
    salesOrderInfo.setExchangeOrder("1".equals(trade.getReturnOrder()));
    salesOrderInfo.setFreightRiskFee(ConvertUtil.toDouble(trade.getTuiHuoWuYou(), 0));
    //京东订单总金额 = 订单金额（不减优惠，不加运费服务费税费）
    salesOrderInfo.setSellingAmount(ConvertUtil.toDouble(trade.getOrderTotalPrice()));
    //京东的用户实际支付金额为在线支付+余额支付
    salesOrderInfo.setActualAmount(ConvertUtil.toDouble(trade.getOrderPayment(), 0) + ConvertUtil
        .toDouble(trade.getBalanceUsed(), 0));
    //结算金额为订单总金额-商家优惠金额
    salesOrderInfo.setSettlementAmount(
        salesOrderInfo.getSellingAmount() - ConvertUtil.toDouble(trade.getSellerDiscount(), 0));

    //订单状态
    salesOrderInfo.setStatus(JdConverter.toOmsOrderStatus(trade.getOrderState()));
    //商品明细
    convertDetail(trade, salesOrderInfo);
    //支付信息
    convertPayment(trade, salesOrderInfo);
    //优惠信息
    convertDiscount(trade, salesOrderInfo);
    //发票信息
    convertInvoice(trade, salesOrderInfo);
    //换货订单设置原单id
    setSourceTradeId(salesOrderInfo);

    parseMunicipality(salesOrderInfo);
    return salesOrderInfo;
  }

  private void setSourceTradeId(MallSalesOrderInfo salesOrderInfo) {
    if (!salesOrderInfo.isExchangeOrder()) {
      return;
    }
    if (!Assert.isEmpty(salesOrderInfo.getBuyerMemo())) {
      String buyerMemo = salesOrderInfo.getBuyerMemo();
      Matcher matcher = SOURCE_TRADE_PATTERN.matcher(buyerMemo);
      if (matcher.find()) {
        salesOrderInfo.setSourceTradeId(matcher.group("tradeId"));
      }
    }
  }

  private void convertDetail(OrderSearchInfo trade, MallSalesOrderInfo salesOrderInfo) {
    List<ItemInfo> itemInfoList = trade.getItemInfoList();
    List<MallSalesOrderDetailInfo> detailInfoList = new ArrayList<>(itemInfoList.size());
    MallSalesOrderDetailInfo orderDetailInfo;
    for (ItemInfo item : itemInfoList) {
      orderDetailInfo = new MallSalesOrderDetailInfo();
      orderDetailInfo.setSellingPrice(ConvertUtil.toDouble(item.getJdPrice()));
      orderDetailInfo.setActualSellingPrice(orderDetailInfo.getSellingPrice());
      orderDetailInfo.setMallProductId(item.getWareId());
      orderDetailInfo.setMallProductName(item.getSkuName());
      orderDetailInfo.setOuterCode(item.getProductNo());
      orderDetailInfo.setMallSkuId(item.getSkuId());
      orderDetailInfo.setOuterSkuCode(item.getOuterSkuId());
      orderDetailInfo.setQuantity(ConvertUtil.toInt(item.getItemTotal()));
      orderDetailInfo
          .setSellingAmount(orderDetailInfo.getSellingPrice() * orderDetailInfo.getQuantity());
      orderDetailInfo.setActualSellingAmount(orderDetailInfo.getSellingAmount());
      orderDetailInfo.setStatus(JdConverter.toOmsOrderDetailStatus(trade.getOrderState()));
      orderDetailInfo.setRefundStatus(MallRefundStatus.NO_REFUND);
      detailInfoList.add(orderDetailInfo);
    }
    salesOrderInfo.setDetails(detailInfoList);
  }

  private void convertPayment(OrderSearchInfo trade, MallSalesOrderInfo salesOrderInfo) {
    List<MallSalesOrderPaymentInfo> paymentInfoList = new ArrayList<>();
    salesOrderInfo.setExpressFee(ConvertUtil.toDouble(trade.getFreightPrice()));
    Double balance = ConvertUtil.toDouble(trade.getBalanceUsed(), 0);
    if (balance > 0) {
      paymentInfoList.add(
          new MallSalesOrderPaymentInfo(balance, PayType.BALANCE, salesOrderInfo.getPaidTime()));
    }
    PayType payType = toOmsPayType(trade.getPayType());
    if (payType == PayType.COD_PAY) {
      salesOrderInfo.setCod(true);
      salesOrderInfo.setCodAmount(ConvertUtil.toDouble(trade.getOrderPayment()));
      paymentInfoList.add(new MallSalesOrderPaymentInfo(salesOrderInfo.getCodAmount(), payType,
          salesOrderInfo.getPaidTime()));
    } else {
      paymentInfoList.add(new MallSalesOrderPaymentInfo(salesOrderInfo.getActualAmount(), payType,
          salesOrderInfo.getPaidTime()));
    }
    salesOrderInfo.setPayments(paymentInfoList);
  }

  private void convertDiscount(OrderSearchInfo trade, MallSalesOrderInfo salesOrderInfo) {
    List<CouponDetail> couponDetailList = trade.getCouponDetailList();
    //商城优惠详情集合
    List<MallSalesOrderDiscountInfo> discountInfoList = new ArrayList<>(couponDetailList.size());
    if (!couponDetailList.isEmpty()) {
      boolean match;
      //总的商家整单优惠
      double discountTotal = 0D;
      Map<String, CouponDetail> productDiscountMap = new HashMap<>(couponDetailList.size());
      for (CouponDetail couponDetail : couponDetailList) {
        if (Assert.isEmpty(couponDetail.getCouponPrice())) {
          continue;
        }
        //计算商品优惠
        //skuid不为空存入详情map
        if (!Assert.isEmpty(couponDetail.getSkuId())) {
          productDiscountMap.put(couponDetail.getSkuId(), couponDetail);
        }
        //为空则加到整单优惠中
        else {
          discountTotal += ConvertUtil.toDouble(couponDetail.getCouponPrice());
        }
        //优惠旗帜，用来判断下面是否有优惠
        match = false;
        //判断优惠支付方式（京豆/礼品卡/京东券），添加到支付方式中
        for (Map.Entry<String, PayType> entry : MALL_COUPON_MAP.entrySet()) {
          if (couponDetail.getCouponType().contains(entry.getKey())) {
            salesOrderInfo.getPayments().add(new MallSalesOrderPaymentInfo(
                ConvertUtil.toDouble(couponDetail.getCouponPrice(), 0), entry.getValue(),
                salesOrderInfo.getPaidTime()));
            match = true;
            break;
          }
        }
        //判断优惠类型
        if (!match) {
          for (Map.Entry<String, DiscountType> entry : MERCHANT_COUPON_MAP.entrySet()) {
            if (couponDetail.getCouponType().contains(entry.getKey())) {
              //添加优惠金额和优惠类型
              final MallSalesOrderDiscountInfo discountInfo = new MallSalesOrderDiscountInfo(
                  ConvertUtil.toDouble(couponDetail.getCouponPrice(), 0), entry.getValue());
              discountInfo.setDiscountName(entry.getValue().caption());
              discountInfoList.add(discountInfo);
              break;
            }
          }
        }
      }
      MallSalesOrderDetailInfo orderDetailInfo;
      double amount = 0;
      //遍历子订单集合，并计算实际销售金额
      for (int i = 0; i < salesOrderInfo.getDetails().size(); i++) {
        orderDetailInfo = salesOrderInfo.getDetails().get(i);
        if (productDiscountMap.containsKey(orderDetailInfo.getMallSkuId())) {
          CouponDetail couponDetail = productDiscountMap.get(orderDetailInfo.getMallSkuId());
          BigDecimal couponDetailPrice = new BigDecimal(couponDetail.getCouponPrice())
              .divide(new BigDecimal(orderDetailInfo.getQuantity()), 2, BigDecimal.ROUND_HALF_UP);
          //实际销售价
          orderDetailInfo
              .setActualSellingPrice(new BigDecimal(orderDetailInfo.getActualSellingPrice())
                  .subtract(new BigDecimal(couponDetailPrice.doubleValue()))
                  .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
          //实际销售金额
          orderDetailInfo
              .setActualSellingAmount(new BigDecimal(orderDetailInfo.getActualSellingAmount())
                  .subtract(new BigDecimal(couponDetail.getCouponPrice()))
                  .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        }
        double shareAmount = 0.0D;
        if (discountTotal > 0) {
          if (i < salesOrderInfo.getDetails().size() - 1) {

            double rate =
                (orderDetailInfo.getSellingPrice() * orderDetailInfo.getQuantity()) / salesOrderInfo
                    .getSellingAmount();
            shareAmount = new BigDecimal(discountTotal * (rate)).setScale(2, RoundingMode.UP)
                .doubleValue();
            //单品 + 分摊 = 总折扣
            orderDetailInfo.setDiscountAmount(
                new BigDecimal(orderDetailInfo.getDiscountAmount()).add(new BigDecimal(shareAmount))
                    .doubleValue());

            amount += shareAmount;
          } else {
            //最后一件商品优惠金额 = 总优惠 - 此前每轮加起来的优惠金额
            shareAmount = discountTotal - amount;
            amount += shareAmount;
            orderDetailInfo.setDiscountAmount(new BigDecimal(orderDetailInfo.getDiscountAmount())
                .add(new BigDecimal(discountTotal)).subtract(new BigDecimal(amount)).doubleValue());
          }
        }
        orderDetailInfo.setDiscountAmount(
            new BigDecimal(orderDetailInfo.getDiscountAmount() + shareAmount)
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        orderDetailInfo.setSettlementAmount(
            new BigDecimal(orderDetailInfo.getActualSellingAmount() - shareAmount)
                .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
      }
      //封装优惠集合
      salesOrderInfo.setDiscounts(discountInfoList);
    }
  }

  private void convertInvoice(OrderSearchInfo trade, MallSalesOrderInfo salesOrderInfo) {
    VatIncoiceInfo vatInvoiceInfo = trade.getVatInfo();
    String invoice = trade.getInvoiceInfo();
    if (!Assert.isEmpty(invoice)) {
      Matcher matcher = INVOICE_PATTERN.matcher(invoice);
      if (matcher.find()) {
        MallSalesOrderInvoiceInfo invoiceInfo = new MallSalesOrderInvoiceInfo(
            matcher.group("title"), matcher.group("content"), salesOrderInfo.getActualAmount());
        invoiceInfo.setInvoiceType(
            "普通发票".equalsIgnoreCase(matcher.group("type")) ? InvoiceType.NORMAL
                : InvoiceType.VALUE_ADD);
        if (vatInvoiceInfo != null) {
          invoiceInfo.setTaxpayerId(vatInvoiceInfo.getVatNo());
          invoiceInfo.setGmfAddress(vatInvoiceInfo.getAddressRegIstered());
          invoiceInfo.setGmfMobile(vatInvoiceInfo.getPhoneRegIstered());
          invoiceInfo.setGmfBankName(vatInvoiceInfo.getDepositBank());
          invoiceInfo.setGmfBankNo(vatInvoiceInfo.getBankAccount());
          invoiceInfo.setInvoiceType(InvoiceType.VALUE_ADD);
        }
        salesOrderInfo.setInvoice(invoiceInfo);
      }
    }
  }

  private PayType toOmsPayType(String type) {
    Matcher matcher = PAY_TYPE_PATTERN.matcher(type);
    if (matcher.find()) {
      String payType = matcher.group("paytype");
      if (PAY_TYPE_MAP.containsKey(payType)) {
        return PAY_TYPE_MAP.get(payType);
      }
    }
    return PayType.OTHER;
  }

  /**
   * SOP出库
   */
  private OrderDeliveryResponse sopSend(OrderDeliveryRequest req) {
    try {
      SalesOrder salesOrder = req.getSalesOrder();
      if (Assert.isTrue(salesOrder.getSub().isCod()) && "2087".equals(req.getMallExpressCode())) {
        sopCodSend(req);
      }
      PopOrderShipmentRequest request = new PopOrderShipmentRequest();
      request.setOrderId(ConvertUtil.toLong(salesOrder.getTradeId()));
      request.setLogiCoprId(req.getMallExpressId());
      request.setLogiNo(req.getExpressNo());

      PopOrderShipmentResponse response = mall.call(req.getStore(), request, false);

      LOGGER.info("京东订单[{}], sopSend发货返回信息:[{}][{}], 入参(OrderId:{}, LogiCoprId:{}, LogiNo:{})",
          salesOrder.getTradeId(), response.getCode(),
          response.getMsg(), request.getOrderId(), request.getLogiCoprId(), request.getLogiNo()
      );
      return result(response, req);
    } catch (Exception e) {
      LOGGER.error("订单:{},发货失败,原因:", req.getSalesOrder(), e);
      return new OrderDeliveryResponse(req, false, e.getMessage());
    }
  }

  private OrderDeliveryResponse result(AbstractResponse response, OrderDeliveryRequest req) {
    if (("0").equals(response.getCode())) {
      return new OrderDeliveryResponse(req);
    } else if ("10400001".equals(response.getCode())) {
      return new OrderDeliveryResponse(req, true, response.getMsg());
    } else {
      return new OrderDeliveryResponse(req, false, response.getMsg());
    }
  }


  /**
   * SOP出库时货到付款处理
   */
  private void sopCodSend(OrderDeliveryRequest req) throws Exception {
    Store store = req.getStore();
    SalesOrder salesOrder = req.getSalesOrder();
    EtmsWaybillSendRequest request = new EtmsWaybillSendRequest();
    request.setDeliveryId(req.getExpressNo());
    request.setSalePlat("0010001"); // 京东
    request.setCustomerCode(store.getSetting().getVendorCode());// 商家编码
    request.setOrderId(salesOrder.getTradeId());
    request.setThrOrderId(salesOrder.getTradeId());
    request.setSenderName(store.getStoreName());
    request.setSenderMobile(store.getTelephone()); // 公司电话
    request.setSenderAddress(store.getAddress());  // 公司地址
    request.setReceiveName(salesOrder.getContact());
    request.setReceiveMobile(salesOrder.getMobile());
    request.setProvince(salesOrder.getProvinceName());
    request.setCity(salesOrder.getCityName());
    request.setCounty(salesOrder.getDistrictName());
    request.setReceiveAddress(salesOrder.getAddress());
    request.setWeight(0D);
    request.setVloumn(0D);
    request.setCollectionValue(salesOrder.getSub().isCod() ? 1 : 0); // 1为代收货款
    if (salesOrder.getSub().isCod()) {
      request.setCollectionMoney(salesOrder.getCodAmount());
    }
    request.setPackageCount(1);//包裹数量

    HashMap<String, String> reqMap = new HashMap<String, String>();
    reqMap.put("DeliveryId", request.getDeliveryId());
    reqMap.put("SalePlat", request.getSalePlat());
    reqMap.put("CustomerCode", request.getCustomerCode());
    reqMap.put("OrderId", request.getOrderId());
    reqMap.put("ThrOrderId", request.getThrOrderId());
    reqMap.put("SenderName", request.getSenderName());
    reqMap.put("SenderMobile", request.getSenderMobile());
    reqMap.put("SenderAddress", request.getSenderAddress());
    reqMap.put("ReceiveName", request.getReceiveName());

    reqMap.put("ReceiveMobile", request.getReceiveMobile());
    reqMap.put("ReceiveAddress", request.getReceiveAddress());
    reqMap.put("Weight", String.valueOf(request.getWeight()));
    reqMap.put("Vloumn", String.valueOf(request.getVloumn()));
    reqMap.put("CollectionValue", String.valueOf(request.getCollectionValue()));
    reqMap.put("CollectionMoney", String.valueOf(request.getCollectionMoney()));
    reqMap.put("PackageCount", String.valueOf(request.getPackageCount()));

    EtmsWaybillSendResponse response = mall.call(store, request, false);
    LOGGER.info("订单[{}]sopCodSend发货返回结果, Code:{}, Msg:{}. 请求参数:{}",
        salesOrder.getTradeId(), response.getCode(), response.getMsg(), reqMap.toString());

    if (!"0".equals(response.getCode())) {
      LOGGER.error("京东发货失败,物流接单接口错误信息{},{},{}", response.getCode(), response.getMsg(),
          req.toString());
      throw new Exception(response.getMsg());
    }
  }

  private void parseMunicipality(MallSalesOrderInfo orderInfo) {
    //直辖市处理，京东的直辖市给的是 重庆/区/镇的格式，需要转为重庆/重庆市/区的格式
    if (MallUtil.isMunicipality(orderInfo.getProvince())) {
      orderInfo.setDistrict(orderInfo.getCity());
      orderInfo.setCity(orderInfo.getProvince() + "市");
    }
  }

  @Override
  protected OrderDeliveryResponse doOrderResetExpress(OrderDeliveryRequest req) {
    Store store = req.getStore();
    switch (store.getSetting().getJingdongMode()) {
      case SOP:
        return sopUpdateSend(req);
      default:
        return new OrderDeliveryResponse(req, false,
            "京东只支持SOP/SOPL/LBP三种模式发货。请确认店铺设置-发货模式是否设置正确");
    }
  }

  private OrderDeliveryResponse sopUpdateSend(OrderDeliveryRequest req) {
    try {
      SalesOrder salesOrder = req.getSalesOrder();
      OrderSopWaybillUpdateRequest request = new OrderSopWaybillUpdateRequest();
      request.setOrderId(salesOrder.getTradeId());
      request.setLogisticsId(req.getMallExpressId());
      request.setWaybill(req.getExpressNo());
      OrderSopWaybillUpdateResponse response = mall.call(req.getStore(), request, false);
      LOGGER.info("京东订单[{}], sopSend修改发货信息:[{}][{}], 入参(OrderId:{}, LogisticsId:{}, WayBill:{})",
          salesOrder.getTradeId(), response.getCode(),
          response.getMsg(), request.getOrderId(), request.getLogisticsId(), request.getWaybill());
      return result(response, req);
    } catch (Exception e) {
      LOGGER.error("订单:{},修改发货失败,原因:", req.getSalesOrder(), e);
      return new OrderDeliveryResponse(req, false, e.getMessage());
    }
  }

  @Override
  protected OrderReceiverInfoUpdateResponse doUpdateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request) {
    throw new MallException("平台暂不支持！");
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.JD};
  }
}

