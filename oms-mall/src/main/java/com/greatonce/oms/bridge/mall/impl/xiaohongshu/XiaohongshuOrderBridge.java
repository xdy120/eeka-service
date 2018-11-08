package com.greatonce.oms.bridge.mall.impl.xiaohongshu;

import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.WebUtil;
import com.greatonce.oms.bo.mall.MallSalesOrderDetailInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderDiscountInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderPaymentInfo;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractOrderBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.xiaohongshu.response.XiaohongshuOrderDetailResponse;
import com.greatonce.oms.bridge.mall.impl.xiaohongshu.response.XiaohongshuOrderQueryResponse;
import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;
import com.greatonce.oms.bridge.mall.response.OrderDeliveryResponse;
import com.greatonce.oms.bridge.mall.response.OrderGetResponse;
import com.greatonce.oms.bridge.mall.response.OrderQueryResponse;
import com.greatonce.oms.bridge.mall.response.OrderReceiverInfoUpdateResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.DiscountType;
import com.greatonce.oms.domain.enums.trade.PayType;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * CREATED by wangc on 2017/5/24.
 * Updated by liangcc on 2018/3/11.
 */
@Component
public class XiaohongshuOrderBridge extends AbstractOrderBridge {

  static final int PAGE_SIZE = 100;

  private static final Logger logger = LoggerFactory.getLogger(XiaohongshuOrderBridge.class);
  private static final Map<String, MallSalesOrderStatus> MALL_SALES_ORDER_STATUS_MAP = new HashMap<>();
  private static final Map<MallSalesOrderStatus, String> OMS_SALES_ORDER_STATUS_MAP = new HashMap<>();

  static {
    MALL_SALES_ORDER_STATUS_MAP
        .put("WAIT_SELLER_SEND_GOODS", MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS);
    MALL_SALES_ORDER_STATUS_MAP.put("WAIT_BUYER_PAY", MallSalesOrderStatus.WAIT_BUYER_PAY);
    MALL_SALES_ORDER_STATUS_MAP
        .put("WAIT_BUYER_CONFIRM_GOODS", MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS);
    MALL_SALES_ORDER_STATUS_MAP.put("TRADE_CLOSED", MallSalesOrderStatus.TRADE_CLOSE);
    MALL_SALES_ORDER_STATUS_MAP.put("TRADE_CLOSED_BY_TAOBAO", MallSalesOrderStatus.TRADE_CLOSE);
    MALL_SALES_ORDER_STATUS_MAP.put("TRADE_FINISHED", MallSalesOrderStatus.TRADE_FINISHED);

    OMS_SALES_ORDER_STATUS_MAP
        .put(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS, "WAIT_SELLER_SEND_GOODS");
    OMS_SALES_ORDER_STATUS_MAP.put(MallSalesOrderStatus.TRADE_CLOSE, "TRADE_CLOSED");
    OMS_SALES_ORDER_STATUS_MAP.put(MallSalesOrderStatus.TRADE_FINISHED, "TRADE_FINISHED");
  }

  @Override
  protected OrderQueryResponse doQueryOrder(OrderQueryRequest request) {
    try {
      OrderQueryResponse response = new OrderQueryResponse(request);
      //初始化查询
      Map<String, String> map = new HashMap<>();
      map.put("start_time",
          String.valueOf(request.getBeginTime().toEpochSecond(ZoneOffset.of("+8"))));
      map.put("end_time", String.valueOf(request.getEndTime().toEpochSecond(ZoneOffset.of("+8"))));
      map.put("status", toMallStatus(request.getStatus()));
      map.put("page_no", String.valueOf(request.getPage()));
      map.put("page_size", String.valueOf(PAGE_SIZE));
      String result = getOrderList(map, request.getStore(), "ark/open_api/v0/packages");
      if (!Assert.isEmpty(result)) {
        XiaohongshuOrderQueryResponse orderQueryResponse = JSONObject
            .parseObject(result, XiaohongshuOrderQueryResponse.class);
        orderQueryResponse(request, response, result, orderQueryResponse);
        int totalPage = orderQueryResponse.getData().getTotal_page();
        response.setHasNext(request.getPage() < totalPage);
                /*if("waiting".equals(toMallStatus(request.getStatus()))){
                    orderQueryResponse(request, response, result, orderQueryResponse);
                }else if("received".equals(toMallStatus(request.getStatus()))){
                    //更新订单状态为已完成
                    if(orderQueryResponse.getSuccess()) {
                        //获取订单列表
                        XiaohongshuOrderQueryResponse.Data data = orderQueryResponse.getData();
                        List<XiaohongshuOrderQueryResponse.Data.Package> paceages = data.getPackage_list();
                        if (!paceages.isEmpty()) {
                            List<MallSalesOrderInfo> odsSaleOrders = new ArrayList<>(paceages.size());
                            for (XiaohongshuOrderQueryResponse.Data.Package pk : paceages) {
                                MallSalesOrderInfo orderInfo = new MallSalesOrderInfo();
                                orderInfo.setTradeId(pk.getPackage_id());
                                convertOmsStatus(pk.getStatus(),orderInfo);
                                orderInfo.setFinishedTime(ConvertUtil.toLocalDateTime(String.valueOf(pk.getConfirm_time()), LocalDateTime.now()));
                                odsSaleOrders.add(orderInfo);
                            }
                        }
                    }
                }*/
      }
      return response;
    } catch (Exception e) {
      logger.error("异常信息", e);
      return new OrderQueryResponse(request, false, e.getMessage());
    }
  }

  private void orderQueryResponse(OrderQueryRequest request, OrderQueryResponse response,
      String result, XiaohongshuOrderQueryResponse orderQueryResponse) throws IOException {
    if (orderQueryResponse.getSuccess()) {
      //获取订单列表
      XiaohongshuOrderQueryResponse.Data data = orderQueryResponse.getData();
      List<XiaohongshuOrderQueryResponse.Data.Package> paceages = data.getPackage_list();
      if (!paceages.isEmpty()) {
        for (XiaohongshuOrderQueryResponse.Data.Package pk : paceages) {
          String orderId = pk.getPackage_id();//订单ID
          //通过订单ID查询订单明细
          String resultDetail = getOrderList(null, request.getStore(),
              "ark/open_api/v0/packages/" + orderId);
          if (!Assert.isEmpty(resultDetail)) {
            XiaohongshuOrderDetailResponse orderDetailResponse = JSONObject
                .parseObject(resultDetail, XiaohongshuOrderDetailResponse.class);
            if (orderQueryResponse.getSuccess()) {
              XiaohongshuOrderDetailResponse.Data itemData = orderDetailResponse.getData();
              List<XiaohongshuOrderDetailResponse.Data.Item> items = itemData.getItem_list();
              List<MallSalesOrderInfo> odsSaleOrders = new ArrayList<>(items.size());
              odsSaleOrders.add(convertOrder(itemData, items, request.getStore(),
                  request.getStoreDownloadConfig()));
              response.setOrders(odsSaleOrders);
            }
          }
        }
      }
    }
  }

  /**
   * 订单查询
   */
  private String getOrderList(Map<String, String> map, Store store, String url) throws IOException {
    //小红书订单详情查询
    //String host = "http://flssandbox.xiaohongshu.com/"; //测试
    String host = "https://ark.xiaohongshu.com/"; //生产
    //数据签名
    Map<String, String> params = new HashMap<>();
    String timestamp = String.valueOf(MallUtil.getUnixTimestamp());
    params.put("timestamp", timestamp);
    params.put("app-key", store.getMallApp().getAppKey());
    params.put("app-key", "d7309f0b72");
    if (!Assert.isEmpty(map)) {
      params.putAll(map);
    }
    //String sign = MallUtil.xiaohongshuCreateSign(params,"/"+url,store.getMallApp().getAppSecret());
    String sign = MallUtil
        .xiaohongshuCreateSign(params, "/" + url, "03f7fc02ecfc4c407cca5b4102053edf");
    //Map<String, String> header = requestHeader(timestamp, sign,store.getMallApp().getAppKey());
    Map<String, String> header = requestHeader(timestamp, sign, "d7309f0b72");
    return WebUtil.doGet(host + url, map, "UTF-8", header);
  }

  private Map<String, String> requestHeader(String timestamp, String sign, String appKey) {
    //报文头
    Map<String, String> header = new HashMap<>();
    header.put("timestamp", timestamp);
    header.put("app-key", appKey);
    header.put("sign", sign);
    return header;
  }

  /**
   * 下载单个订单
   */
  @Override
  protected OrderGetResponse doGetOrder(OrderGetRequest request) {
    return null;
  }

  @Override
  protected OrderDeliveryResponse doOrderDelivery(OrderDeliveryRequest request) {
    return null;
  }

  @Override
  protected OrderDeliveryResponse doOrderResetExpress(OrderDeliveryRequest request) {
    return null;
  }

  private MallSalesOrderInfo convertOrder(XiaohongshuOrderDetailResponse.Data itemData,
      List<XiaohongshuOrderDetailResponse.Data.Item> items, Store store, StoreDownloadConfig dc) {
    MallSalesOrderInfo salesOrderInfo = new MallSalesOrderInfo();
    //时间信息
    salesOrderInfo.setCreatedTime(DateTimeUtil.parserTimestamp(itemData.getTime()));
    salesOrderInfo.setModifiedTime(ConvertUtil.toLocalDateTime("", LocalDateTime.now()));
    salesOrderInfo.setPaidTime(DateTimeUtil.parserTimestamp(itemData.getPay_time()));
    salesOrderInfo.setFinishedTime(DateTimeUtil.parserTimestamp(itemData.getConfirm_time()));
    //收货信息
    salesOrderInfo.setContact(itemData.getReceiver_name());
    salesOrderInfo.setProvince(itemData.getProvince());
    salesOrderInfo.setCity(itemData.getCity());
    salesOrderInfo.setDistrict(itemData.getDistrict());
    salesOrderInfo.setAddress(itemData.getReceiver_address());
    salesOrderInfo.setMobile(itemData.getReceiver_phone());
    salesOrderInfo.setTelephone(itemData.getReceiver_phone());
    //订单信息
    salesOrderInfo.setTradeId(itemData.getPackage_id());
    salesOrderInfo.setBuyerMemo(null);
    salesOrderInfo.setSellerMemo(null);
    salesOrderInfo.setSellerNick(store.getStoreName());
    salesOrderInfo.setBuyerNick(itemData.getReceiver_name());

    //主订单金额信息
    salesOrderInfo.setExpressFee(null);
    salesOrderInfo.setSellingAmount(itemData.getPay_amount());
    salesOrderInfo.setActualAmount(itemData.getPay_amount());
    salesOrderInfo
        .setSettlementAmount(ConvertUtil.toDouble(itemData.getOrder_declared_amount(), 0));
    //订单状态
    convertOmsStatus(itemData.getStatus(), salesOrderInfo);
    //商品明细
    convertDetail(itemData, items, salesOrderInfo);
    //支付信息
    convertPayment(itemData, salesOrderInfo);
    //优惠信息
    convertDiscount(items, salesOrderInfo);

    return salesOrderInfo;
  }

  protected String toMallStatus(MallSalesOrderStatus status) {
    switch (status) {
      case WAIT_SELLER_SEND_GOODS:
        return "waiting";
      case WAIT_BUYER_CONFIRM_GOODS:
        return "shipped";
      case TRADE_FINISHED:
        return "received";
      default:
        return "waiting";
    }
  }


  /**
   * 修改订单状态
   */
  private void convertOmsStatus(String status, MallSalesOrderInfo salesOrderInfo) {
    switch (status) {
      case "waiting":
        salesOrderInfo.setStatus(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS);
        break;
      case "shipped":
        salesOrderInfo.setStatus(MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS);
        break;
      case "received":
        salesOrderInfo.setStatus(MallSalesOrderStatus.TRADE_BUYER_SIGNED);
        break;
      default:
        salesOrderInfo.setStatus(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS);
        break;
    }
  }

  /**
   * 转换封装订单详情
   */
  private void convertDetail(XiaohongshuOrderDetailResponse.Data itemData,
      List<XiaohongshuOrderDetailResponse.Data.Item> items, MallSalesOrderInfo mallSalesOrderInfo) {
    List<MallSalesOrderDetailInfo> saleOrderDetailList;
    MallSalesOrderDetailInfo mallSalesOrderDetailInfo;
    if (items != null && !items.isEmpty()) {
      saleOrderDetailList = new ArrayList<>(items.size());
      for (XiaohongshuOrderDetailResponse.Data.Item order : items) {
        mallSalesOrderDetailInfo = new MallSalesOrderDetailInfo();
        mallSalesOrderDetailInfo.setStatus(toSalesOrderDetailInfoStatus(itemData.getStatus()));
        //商品信息
        mallSalesOrderDetailInfo.setMallProductId(order.getSkucode());
        mallSalesOrderDetailInfo.setMallProductName(order.getItem_name());
        mallSalesOrderDetailInfo.setOuterCode(order.getSpecification());
        mallSalesOrderDetailInfo.setMallSkuId(order.getSkucode());
        mallSalesOrderDetailInfo.setOuterSkuCode(order.getBarcode());
        mallSalesOrderDetailInfo.setMallSkuName(order.getSpecification());
        mallSalesOrderDetailInfo.setQuantity(ConvertUtil.toInt(order.getQty().toString()));
        //价格信息
        mallSalesOrderDetailInfo.setSellingPrice(ConvertUtil.toDouble(order.getPrice(), 0));
        mallSalesOrderDetailInfo
            .setActualSellingPrice(ConvertUtil.toDouble(order.getPay_price(), 0));
        //金额信息
        mallSalesOrderDetailInfo.setSellingAmount(itemData.getPay_amount());
        mallSalesOrderDetailInfo.setActualSellingAmount(itemData.getPay_amount());
        mallSalesOrderDetailInfo.setSettlementAmount(itemData.getPay_amount());
        saleOrderDetailList.add(mallSalesOrderDetailInfo);
      }
      mallSalesOrderInfo.setDetails(saleOrderDetailList);
    }
  }

  /**
   * 详情状态转换
   */
  private MallSalesOrderDetailStatus toSalesOrderDetailInfoStatus(String status) {
    switch (status) {
      case "waiting":
        return MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS;
      case "shipped":
        return MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS;
      case "received":
        return MallSalesOrderDetailStatus.TRADE_FINISHED;
      default:
        return MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS;
    }
  }

  /**
   * 转换封装支付信息
   */
  private void convertPayment(XiaohongshuOrderDetailResponse.Data itemData,
      MallSalesOrderInfo salesOrderInfo) {
    List<MallSalesOrderPaymentInfo> paymentInfoList = new ArrayList<>();
    //判断是否货到付款
    PayType payType = toOmsPayType(PayType.ONLINE_PAY.name());
    paymentInfoList.add(new MallSalesOrderPaymentInfo(itemData.getPay_amount(), payType,
        DateTimeUtil.parserTimestamp(itemData.getPay_time())));
    salesOrderInfo.setPayments(paymentInfoList);
  }

  /**
   * 转换封装优惠信息,找到订单中每件优惠的商品，并计算他们的实际销售金额
   */
  private void convertDiscount(List<XiaohongshuOrderDetailResponse.Data.Item> items,
      MallSalesOrderInfo salesOrderInfo) {
    List<MallSalesOrderDiscountInfo> discountInfoList = new ArrayList<>(items.size());
    for (XiaohongshuOrderDetailResponse.Data.Item item : items) {
      //小红书没办法判断优惠类型，只能为其他优惠
      MallSalesOrderDiscountInfo discountInfo = new MallSalesOrderDiscountInfo(
          ConvertUtil.toDouble(item.getMerchant_discount(), 0) + ConvertUtil
              .toDouble(String.valueOf(item.getRed_discount()), 0), null, null,
          DiscountType.STORE_DISCOUNT);
      discountInfoList.add(discountInfo);
    }
    salesOrderInfo.setDiscounts(discountInfoList);
  }


  protected PayType toOmsPayType(String mallPaytype) {
    return mallPaytype.equals("cod") ? PayType.COD_PAY : PayType.ALI_PAY;
  }

  @Override
  public OrderDeliveryResponse orderDelivery(OrderDeliveryRequest request) {
    return null;
  }

  @Override
  protected OrderReceiverInfoUpdateResponse doUpdateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request) {
    throw new MallException("平台暂不支持！");
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.XIAOHONGSHU};
  }
}

