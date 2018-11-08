package com.greatonce.oms.bridge.mall.impl.taobao.fx;//package com.greatonce.oms.bridge.mall.impl.taobao.fx;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.mall.MallSalesOrderDetailInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderPaymentInfo;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractOrderBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.taobao.TaobaoMall;
import com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain.JdpFxTrade;
import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;
import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;
import com.greatonce.oms.bridge.mall.response.OrderDeliveryResponse;
import com.greatonce.oms.bridge.mall.response.OrderGetResponse;
import com.greatonce.oms.bridge.mall.response.OrderQueryResponse;
import com.greatonce.oms.bridge.mall.response.OrderReceiverInfoUpdateResponse;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.mall.MallRefundStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.PayType;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SourceType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.request.FenxiaoOrdersGetRequest;
import com.taobao.api.request.LogisticsOfflineSendRequest;
import com.taobao.api.response.FenxiaoOrdersGetResponse;
import com.taobao.api.response.LogisticsOfflineSendResponse;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/6/10
 * remark:
 */
@Component
public class FenxiaoOrderBridge extends AbstractOrderBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(FenxiaoOrderBridge.class);

  @Autowired
  private TaobaoMall mall;
  @Autowired
  @Qualifier("rdsSqlSessionDecorator")
  private SqlSessionDecorator sqlSessionDecorator;

  private final int pageSize = 50;
  private static final Map<MallSalesOrderStatus,String> MALL_STATUS_MAP= InitEnumMap.MALL_STATUS_MAP;
  private static final Map<String,PayType> OMS_PAY_TYPE = InitEnumMap.OMS_PAY_TYPE;
  private static final Map<String,MallSalesOrderStatus> ORDER_STATUS_MAP = InitEnumMap.ORDER_STATUS_MAP;
  private static final Map<String,MallSalesOrderDetailStatus> MALL_SALES_ORDER_DETAIL_STATUS_MAP = InitEnumMap.MALL_SALES_ORDER_DETAIL_STATUS_MAP;


  /**
   * 查询订单列表
   *
   * @param request
   * @return
   */
  @Override
  protected OrderQueryResponse doQueryOrder(OrderQueryRequest request) {

    OrderQueryResponse response;
    if (!Assert.isNull(request.getStore().getSetting()) && Assert
        .isTrue(request.getStore().getSetting().isTaobaoOrderCloudPush())) {
      response = tbFenxiaoOrderByCloudPush(request);
    } else {
      response = tbFenxiaoOrderByApi(request);
    }
    return response;
  }

  private OrderQueryResponse tbFenxiaoOrderByCloudPush(OrderQueryRequest request){
    OrderQueryResponse response = new OrderQueryResponse(request);
    //云推
    HashMap<String, Object> param = new HashMap<>();
    param.put("status", request.getStatus());
    param.put("jdpModifiedBegin", request.getBeginTime());
    param.put("jdpModifiedEnd", request.getEndTime());

    PageList<JdpFxTrade> jdpFxTrades = sqlSessionDecorator.selectList(
        "com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.mappers.JdpFxTradeMapper.list",
        param,request.getPage(),pageSize);

    response.setHasNext(request.getPage() < MallUtil.calcTotalPage(pageSize, jdpFxTrades.getTotal()));

    List<MallSalesOrderInfo> salesOrderInfos = new ArrayList<>();
    if (!Assert.isEmpty(jdpFxTrades.getData())) {
      try {
        for (JdpFxTrade jdpFxTrade : jdpFxTrades.getData()) {
          FenxiaoOrdersGetResponse ordersGetResponse = TaobaoUtils
              .parseResponse(jdpFxTrade.getJdpResponse(), FenxiaoOrdersGetResponse.class);
          if (!Assert.isNull(ordersGetResponse)) {
            FenxiaoOrdersGetResponse.TopDpOrderDo topDpOrderDo = ordersGetResponse
                .getPurchaseOrders().get(0);
            salesOrderInfos.add(convertOrder(topDpOrderDo));
          }
        }
      } catch (ApiException e) {
        LOGGER.error("淘宝分销订单结果解析失败：{}", e);
        response.setResult("淘宝分销订单结果解析失败");
        response.setSuccess(false);
        return response;
      }
      if (Assert.isEmpty(salesOrderInfos)) {
        response.setResult("解析淘宝分销订单下载结果失败");
        response.setSuccess(true);
        return response;
      }
      response.setOrders(salesOrderInfos);
    } else {
      response.setResult("下载分销订单失败，找不到或无订单");
      response.setSuccess(true);
      return response;
    }
    return response;
  }

  private OrderQueryResponse tbFenxiaoOrderByApi(OrderQueryRequest request){
    //调用api
    OrderQueryResponse response = new OrderQueryResponse(request);
    FenxiaoOrdersGetRequest req = new FenxiaoOrdersGetRequest();
    req.setStatus(MALL_STATUS_MAP.getOrDefault(request.getStatus(),"WAIT_SELLER_SEND_GOODS"));
//      req.setFields();  不填查询所有字段
    req.setPageNo((long) request.getPage());
    req.setPageSize((long)pageSize);
    req.setStartCreated(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
    req.setEndCreated(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
    req.setTimeType("update_time_type"); //指定时间是 更新时间的范围
    FenxiaoOrdersGetResponse fxResponse = mall.call(request.getStore(), req);
    int totalCount = Integer.parseInt(String.valueOf(fxResponse.getTotalResults()));
    if (totalCount > 0) {
      response.setHasNext(request.getPage() < MallUtil.calcTotalPage(pageSize,totalCount));
      List<MallSalesOrderInfo> orders = new ArrayList<>();
      if (!Assert.isNull(fxResponse) && !Assert.isEmpty(fxResponse.getPurchaseOrders())) {
        for (FenxiaoOrdersGetResponse.TopDpOrderDo purchaseOrder : fxResponse
            .getPurchaseOrders()) {
          orders.add(convertOrder(purchaseOrder));
        }
      }
      if (Assert.isEmpty(orders)) {
        response.setSuccess(true);
        response.setResult("解析淘宝订单失败");
        return response;
      }
      response.setOrders(orders);
    } else {
      response.setSuccess(true);
      response.setResult("下载分销订单数量为空");
      return response;
    }
    return response;
  }


  /**
   * 查询单个订单详情
   *
   * @param request
   * @return
   */
  @Override
  protected OrderGetResponse doGetOrder(OrderGetRequest request) {
    if (!Assert.isNumber(request.getTradeId())) {
      throw new OmsException("淘宝分销店铺的交易号为纯数字");
    }
    try {

      FenxiaoOrdersGetRequest req = new FenxiaoOrdersGetRequest();
      req.setPurchaseOrderId(Long.valueOf(request.getTradeId()));

      FenxiaoOrdersGetResponse response = mall.call(request.getStore(), req);

      List<FenxiaoOrdersGetResponse.TopDpOrderDo> getResponse = response.getPurchaseOrders();
      if (Assert.isEmpty(getResponse)){
        LOGGER.debug("分销订单找不到详情,交易号:{}",request.getTradeId());
        return new OrderGetResponse(request,true,"分销订单找不到");
      }
      //根据分销号,只能查出一个,如果多个异常
      if (getResponse.size() > 1){
        LOGGER.warn("根据交易号,找到的分销单详情信息异常,交易号:",request.getTradeId());
        return new OrderGetResponse(request,false,"根据交易号,找到的分销单详情信息异常");
      }
      MallSalesOrderInfo mallSalesOrderInfo = convertOrder(getResponse.get(0));
      return new OrderGetResponse(request,mallSalesOrderInfo);
    }catch (Exception e){
      LOGGER.error("分销订单详情下载失败,交易号:{}",request.getTradeId());
      return new OrderGetResponse(request,false,"分销订单详情下载失败");
    }
  }

  /**
   * 订单发货  线下发货
   *
   * @param request
   * @return
   */
  @Override
  protected OrderDeliveryResponse doOrderDelivery(OrderDeliveryRequest request) {
    SalesOrder salesOrder = request.getSalesOrder();
    LogisticsOfflineSendRequest req = new LogisticsOfflineSendRequest();
    req.setTid(Long.valueOf(salesOrder.getTradeId()));
    req.setOutSid(request.getExpressNo());
    req.setCompanyCode(request.getMallExpressCode());
    if (request.isPartDelivery()) {
      req.setIsSplit(1L);
      //拼接拆单订单id
      List<SalesOrderDetail> deliveriedDetail = salesOrder.getDetails().stream()
          .filter(x -> SalesOrderDetailStatus.DELIVERED.equals(x.getStatus()))
          .collect(Collectors.toList());
      StringBuilder sb = new StringBuilder();
      deliveriedDetail.forEach(x -> sb.append(",").append(x.getMallDetailId()));
      sb.delete(0, 1);
      req.setSubTid(sb.toString());
      LOGGER.debug("分销订单{}平台拆单发货", salesOrder.getSalesOrderCode());
    }
    LogisticsOfflineSendResponse response = mall.call(request.getStore(), req);
    if (response.isSuccess()) {
      return new OrderDeliveryResponse(request);
    } else if ("isv.logistics-online-service-error:B27".equals(response.getSubCode())) {
      return new OrderDeliveryResponse(request, false, "delivered");
    } else {
      LOGGER
          .debug("分销订单{}线下发货失败,原因：{}", request.getSalesOrder().getSalesOrderCode(), response.getMsg());
      return new OrderDeliveryResponse(request, false, response.getMsg());
    }
  }

  /**
   * 重置快递的信息
   *
   * @param request
   * @return
   */
  @Override
  protected OrderDeliveryResponse doOrderResetExpress(OrderDeliveryRequest request) {
    return new OrderDeliveryResponse(request,true,"暂未实现");
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO_FX};
  }


  private MallSalesOrderInfo convertOrder(FenxiaoOrdersGetResponse.TopDpOrderDo purchaseOrder) {
    MallSalesOrderInfo mallSalesOrderInfo = new MallSalesOrderInfo();
    //时间信息
    mallSalesOrderInfo
        .setFinishedTime(ConvertUtil.toLocalDateTime(purchaseOrder.getEndTime(), null));
    mallSalesOrderInfo
        .setCreatedTime(ConvertUtil.toLocalDateTime(purchaseOrder.getCreated(), null));
    mallSalesOrderInfo
        .setModifiedTime(ConvertUtil.toLocalDateTime(purchaseOrder.getModified(), null));
    mallSalesOrderInfo.setPaidTime(ConvertUtil.toLocalDateTime(purchaseOrder.getPayTime(), null));

    //买家信息
    FenxiaoOrdersGetResponse.TopReceiverDo receiver = purchaseOrder.getReceiver();
    if (receiver != null) {
      mallSalesOrderInfo.setContact(receiver.getName());
      mallSalesOrderInfo.setProvince(receiver.getState());
      mallSalesOrderInfo.setCity(receiver.getCity());
      mallSalesOrderInfo
          .setDistrict(Assert.isEmpty(receiver.getDistrict()) ? "其它区" : receiver.getDistrict());
      mallSalesOrderInfo.setAddress(
          receiver.getState() + " " + receiver.getCity() + " " + mallSalesOrderInfo.getDistrict()
              + " " + receiver.getAddress());
      mallSalesOrderInfo.setZipCode(receiver.getZip());
      mallSalesOrderInfo.setMobile(receiver.getMobilePhone());
      mallSalesOrderInfo.setTelephone(receiver.getPhone());
      mallSalesOrderInfo.setBuyerNick(purchaseOrder.getBuyerNick());
      mallSalesOrderInfo.setBuyerMemo(purchaseOrder.getMemo());
    }
    //订单信息
    mallSalesOrderInfo.setTradeId(purchaseOrder.getFenxiaoId().toString());
    mallSalesOrderInfo.setSellerMemo(purchaseOrder.getSupplierMemo());
    mallSalesOrderInfo.setSellerNick(purchaseOrder.getSupplierUsername());
    mallSalesOrderInfo.setBuyerNick(purchaseOrder.getDistributorUsername());

    //支付信息
    mallSalesOrderInfo.setBuyerAlipayNo(purchaseOrder.getAlipayNo());
    convertPayment(purchaseOrder, mallSalesOrderInfo);

//  odsSaleOrder.settype(toOmsShippingType(purchaseOrder.getShipping()));

    //订单状态

    mallSalesOrderInfo.setStatus(ORDER_STATUS_MAP.getOrDefault(purchaseOrder.getStatus(),MallSalesOrderStatus.UNKNOWN));

    mallSalesOrderInfo.setSourceType(SourceType.PC);
    mallSalesOrderInfo.setDistributionTradeId(purchaseOrder.getTcOrderId() + "");

    //快递-->此处应该为空
    mallSalesOrderInfo.setExpressName(purchaseOrder.getLogisticsCompanyName());
    mallSalesOrderInfo.setExpressNo(purchaseOrder.getLogisticsId());

    //订单明细
    List<MallSalesOrderDetailInfo> detailInfos = convertDetail(purchaseOrder);
    mallSalesOrderInfo.setDetails(detailInfos);

    //主订单金额信息
    mallSalesOrderInfo.setExpressFee(ConvertUtil.toDouble(purchaseOrder.getPostFee()));
    //分销商 给 供应商  的商品的总钱数
    mallSalesOrderInfo.setSellingAmount(ConvertUtil.toDouble(purchaseOrder.getTotalFee()));
    //分销商 给 供应商  还要算上运费
    mallSalesOrderInfo.setActualAmount(ConvertUtil.toDouble(purchaseOrder.getDistributorPayment()));
    //结算价 和实际金额 一致
    mallSalesOrderInfo.setSettlementAmount(mallSalesOrderInfo.getActualAmount());

    //分销金额
    Double disAmount = mallSalesOrderInfo.getDetails().stream().map(x -> x.getDistributionAmount())
        .reduce((a, b) -> a + b).get();
    mallSalesOrderInfo.setDistributionAmount(disAmount);

    return mallSalesOrderInfo;
  }

  /**
   * 转换封装支付信息
   */
  private void convertPayment(FenxiaoOrdersGetResponse.TopDpOrderDo purchaseOrder,
      MallSalesOrderInfo mallSalesOrderInfo) {
    List<MallSalesOrderPaymentInfo> paymentInfoList = new ArrayList<>();
    //支付方式
    PayType payType = OMS_PAY_TYPE.getOrDefault(purchaseOrder.getPayType(),PayType.OTHER);

    //分销不支持到付
    mallSalesOrderInfo.setCod(false);

    //淘宝订单中货到付款金额也是实付金额
    paymentInfoList.add(new MallSalesOrderPaymentInfo(mallSalesOrderInfo.getActualAmount(), payType,
        mallSalesOrderInfo.getPaidTime()));
    mallSalesOrderInfo.setPayments(paymentInfoList);
  }

  /**
   * 订单明细
   */
  private List<MallSalesOrderDetailInfo> convertDetail(
      FenxiaoOrdersGetResponse.TopDpOrderDo purchaseOrder) {
    List<FenxiaoOrdersGetResponse.SubOrderDetail> subPurchaseOrderList = purchaseOrder
        .getSubPurchaseOrders();
    List<MallSalesOrderDetailInfo> saleOrderDetailList = new ArrayList<>();
    for (FenxiaoOrdersGetResponse.SubOrderDetail subPurchaseOrder : subPurchaseOrderList) {
      MallSalesOrderDetailInfo info = new MallSalesOrderDetailInfo();
      info.setOid(String.valueOf(subPurchaseOrder.getFenxiaoId()));
//      info.setPresellDeliveryDate();  无预计发货时间
//      info.setShopDelivery(false);
//      info.setShopCode("");           同上
      info.setModifiedTime(LocalDateTime.now());
      info.setStatus(MALL_SALES_ORDER_DETAIL_STATUS_MAP.getOrDefault(subPurchaseOrder.getStatus(),MallSalesOrderDetailStatus.UNKNOWN));
      //product
      info.setMallProductId(String.valueOf(subPurchaseOrder.getItemId()));
      info.setMallProductName(subPurchaseOrder.getTitle());
      info.setOuterCode(subPurchaseOrder.getItemOuterId());
      //sku
      info.setMallSkuId(String.valueOf(subPurchaseOrder.getSkuId()));
      info.setMallSkuName(subPurchaseOrder.getSkuProperties());
      info.setOuterSkuCode(subPurchaseOrder.getSkuOuterId());

      info.setQuantity(Integer.valueOf(String.valueOf(subPurchaseOrder.getNum())));
      info.setRefundStatus(MallRefundStatus.NO_REFUND);
      if ("TRADE_CLOSED".equalsIgnoreCase(subPurchaseOrder.getOrder200Status())
          || "TRADE_CLOSED_BY_TAOBAO".equalsIgnoreCase(subPurchaseOrder.getOrder200Status())) {
        info.setRefundStatus(MallRefundStatus.SUCCESS);
      }
      //分销实际金额
      info.setDistributionAmount(ConvertUtil.toDouble(subPurchaseOrder.getBuyerPayment(), 0D));
      info.setDistributionPrice(new BigDecimal(info.getDistributionAmount())
          .divide(new BigDecimal(info.getQuantity()), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
      //吊牌价--->供应商给分销商的价格
      info.setSellingPrice(ConvertUtil.toDouble(subPurchaseOrder.getPrice(), 0D));
      info.setSellingAmount(
          new BigDecimal(info.getSellingPrice()).multiply(new BigDecimal(info.getQuantity()))
              .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
      //实际金额
      info.setActualSellingAmount(ConvertUtil.toDouble(subPurchaseOrder.getDistributorPayment()));
      info.setActualSellingPrice(new BigDecimal(info.getActualSellingAmount())
          .divide(new BigDecimal(info.getQuantity()), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
      //优惠价
      //两种优惠 分销不算优惠
      BigDecimal one = Assert.isNull(subPurchaseOrder.getTcDiscountFee()) ? new BigDecimal("0")
          : new BigDecimal(subPurchaseOrder.getTcDiscountFee())
              .divide(new BigDecimal("100").setScale(2, BigDecimal.ROUND_HALF_UP));
      BigDecimal two = Assert.isNull(subPurchaseOrder.getDiscountFee()) ? new BigDecimal("0")
          : new BigDecimal(subPurchaseOrder.getDiscountFee());
//      info.setDiscountAmount(); 没有优惠
      //结算价 就是分销商的实付金额
      info.setSettlementAmount(info.getActualSellingAmount());

      //将明细放入list中
      saleOrderDetailList.add(info);
    }
    return saleOrderDetailList;
  }

  @Override
  protected OrderReceiverInfoUpdateResponse doUpdateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request) {
    throw new MallException("平台暂不支持！");
  }
}