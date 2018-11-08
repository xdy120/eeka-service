package com.greatonce.oms.bridge.mall.impl.taobao.jx;

import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractOrderBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.taobao.TaobaoMall;
import com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain.JdpJxTrade;
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
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.PayType;
import com.taobao.api.domain.DealerOrder;
import com.taobao.api.domain.Receiver;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.request.FenxiaoDealerRequisitionorderGetRequest;
import com.taobao.api.request.FenxiaoDealerRequisitionorderQueryRequest;
import com.taobao.api.response.FenxiaoDealerRequisitionorderGetResponse;
import com.taobao.api.response.FenxiaoDealerRequisitionorderQueryResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * company:Shenzhen Greatonce Co Ltd author:wangcan create date:2017/6/17 * remark:
 */
@Component
public class JingxiaoOrderBridge extends AbstractOrderBridge {

  static final Long PAGE_SIZE = 100L;
  @Autowired
  TaobaoMall mall;
  @Autowired
  @Qualifier("rdsSqlSessionDecorator")
  SqlSessionDecorator sqlSessionDecorator;

  protected String toMallStatus(MallSalesOrderStatus status) {
    switch (status) {
      case WAIT_SELLER_SEND_GOODS:
        return "7";
      case WAIT_BUYER_PAY:
        return "5";
      case WAIT_BUYER_CONFIRM_GOODS:
        return "8";
      case TRADE_FINISHED:
        return "9";
      case TRADE_CLOSE:
        return "10";
      default:
        return "7";
    }
  }

  protected MallSalesOrderStatus toOmsStatus(String status) {
    //WAIT_FOR_SUPPLIER_AUDIT1：分销商提交申请，待供应商审核；SUPPLIER_REFUSE：供应商驳回申请，待分销商确认；WAIT_FOR_APPLIER_AUDIT：供应商修改后，待分销商确认；WAIT_FOR_SUPPLIER_AUDIT2：分销商拒绝修改，待供应商再审核；BOTH_AGREE_WAIT_PAY：审核通过下单成功，待分销商付款WAIT_FOR_SUPPLIER_DELIVER：付款成功，待供应商发货；WAIT_FOR_APPLIER_STORAGE：供应商发货，待分销商收货；TRADE_FINISHED：分销商收货，交易成功；TRADE_CLOSED：经销采购单关闭。
    switch (status) {
      case "BOTH_AGREE_WAIT_PAY":
        return MallSalesOrderStatus.WAIT_BUYER_PAY;
      case "WAIT_FOR_SUPPLIER_DELIVER":
        return MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS;
      case "WAIT_FOR_APPLIER_STORAGE":
        return MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS;
      case "TRADE_FINISHED":
        return MallSalesOrderStatus.TRADE_FINISHED;
      case "TRADE_CLOSED":
        return MallSalesOrderStatus.TRADE_CLOSE;
      default:
        return MallSalesOrderStatus.UNKNOWN;
    }
  }

  @Override
  protected OrderQueryResponse doQueryOrder(OrderQueryRequest request) {
    try {
      OrderQueryResponse response = new OrderQueryResponse(request);
      //todo 云推设置
      if (request.getStoreDownloadConfig() != null) {
        //是云推从数据库里读
        Map<String, Object> map = new HashMap<>();
        map.put("dealerOrderId", request.getTradeId());
        map.put("orderStatus", toMallStatus(request.getStatus()));
        map.put("jdpModifiedBegin", request.getBeginTime());
        map.put("jdpModifiedEnd", request.getBeginTime());
        List<JdpJxTrade> JdpJxTrades = sqlSessionDecorator.selectList(
            "com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.mappers.JdpJxTradeMapper.list",
            map);
        if (JdpJxTrades != null && !JdpJxTrades.isEmpty()) {
          List<MallSalesOrderInfo> odsSaleOrders = new ArrayList<>();
          for (JdpJxTrade jdpJxTrade : JdpJxTrades) {
            FenxiaoDealerRequisitionorderGetResponse JxResponse = TaobaoUtils
                .parseResponse(jdpJxTrade.getJdpResponse(),
                    FenxiaoDealerRequisitionorderGetResponse.class);
            if (JxResponse != null && JxResponse.getDealerOrders() != null && !JxResponse
                .getDealerOrders().isEmpty()) {
              for (DealerOrder dealerOrder : JxResponse.getDealerOrders()) {
                odsSaleOrders.add(convertOrder(dealerOrder, request.getStore(),
                    request.getStoreDownloadConfig()));
              }
            }
            response.setOrders(odsSaleOrders);
          }
        }
      } else {
        FenxiaoDealerRequisitionorderGetRequest JxRequest = new FenxiaoDealerRequisitionorderGetRequest();
        JxRequest.setStartDate(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
        JxRequest.setEndDate(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
        JxRequest.setPageNo(Long.valueOf(request.getPage()));
        JxRequest.setPageSize(PAGE_SIZE);
        JxRequest.setOrderStatus(Long.parseLong(toMallStatus(request.getStatus())));
        JxRequest.setIdentity(1L);
        FenxiaoDealerRequisitionorderGetResponse JxResponse = mall
            .call(request.getStore(), JxRequest);
        Integer totalCount = Integer.parseInt(String.valueOf(JxResponse.getTotalResults()));
        if (totalCount > 0) {
          response.setHasNext(request.getPage() < MallUtil.calcTotalPage(PAGE_SIZE, totalCount));
          List<MallSalesOrderInfo> orders = new ArrayList<>();
          if (JxResponse != null && JxResponse.getDealerOrders() != null && !JxResponse
              .getDealerOrders().isEmpty()) {
            for (DealerOrder dealerOrder : JxResponse.getDealerOrders()) {
              orders.add(
                  convertOrder(dealerOrder, request.getStore(), request.getStoreDownloadConfig()));
            }
            response.setOrders(orders);
          }
        }
      }
      return response;
    } catch (Exception e) {
      return new OrderQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected OrderGetResponse doGetOrder(OrderGetRequest request) {
    //不用判断云推  直接去下有可能淘宝都没推送过来
    try {
      FenxiaoDealerRequisitionorderQueryRequest req = new FenxiaoDealerRequisitionorderQueryRequest();
      req.setDealerOrderIds(request.getTradeId());
      FenxiaoDealerRequisitionorderQueryResponse JxResponse = mall.call(request.getStore(), req);
      return new OrderGetResponse(request,
          convertOrder(JxResponse.getDealerOrders().get(0), request.getStore(),
              request.getStoreDownloadConfig()));
    } catch (MallException e) {
      return new OrderGetResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected OrderDeliveryResponse doOrderDelivery(OrderDeliveryRequest request) {
    return null;
  }

  @Override
  protected OrderDeliveryResponse doOrderResetExpress(OrderDeliveryRequest request) {
    return null;
  }

  private MallSalesOrderInfo convertOrder(DealerOrder dealerOrder, Store store,
      StoreDownloadConfig dc) {
    MallSalesOrderInfo odsSaleOrder = new MallSalesOrderInfo();
//        odsSaleOrder.setShippingType(ShippingType.FREE);
//        //odsSaleOrder.setTradeFrom();
//        odsSaleOrder.setTradeId(dealerOrder.getDealerOrderId() + "");
//        odsSaleOrder.setStoreOrder(checkIsStoreOrder(dc));
//        odsSaleOrder.setCreateDate(dealerOrder.getAppliedTime());
//        odsSaleOrder.setModifyDate(dealerOrder.getModifiedTime());
//        odsSaleOrder.setBuyerNick(dealerOrder.getApplierNick());
//        odsSaleOrder.setSellerMemo(dealerOrder.getSupplierMemo());
//        odsSaleOrder.setSellerNick(dealerOrder.getSupplierNick());
//        odsSaleOrder.setAlipayNo(dealerOrder.getAlipayNo());
//        odsSaleOrder.setBuyerAlipayNo(dealerOrder.getAlipayNo());
//        odsSaleOrder.setPayDate(dealerOrder.getPayTime());
//        odsSaleOrder.setTotalAmount(Double.valueOf(dealerOrder.getTotalPrice()));
//        odsSaleOrder.setPayAmount(Double.valueOf(dealerOrder.getTotalPrice()));
    Receiver receiver = dealerOrder.getReceiver();
//        odsSaleOrder.setReceiverName(receiver.getName());
    odsSaleOrder.setMobile(receiver.getMobilePhone());
    odsSaleOrder.setTelephone(receiver.getPhone());
    odsSaleOrder.setDistrict("中国");
    odsSaleOrder.setProvince(receiver.getState());
    odsSaleOrder.setCity(receiver.getCity());
    odsSaleOrder.setDistrict(receiver.getDistrict());
    odsSaleOrder.setAddress(
        receiver.getState() + " " + receiver.getCity() + " " + receiver.getDistrict() + " "
            + receiver.getAddress());
//        List<DealerOrderDetail> dealerOrderDetailList = dealerOrder.getDealerOrderDetails();
//        List<OdsSaleOrderDetail> saleOrderDetailList = new ArrayList<OdsSaleOrderDetail>();
//        for (DealerOrderDetail dealerOrderDetail : dealerOrderDetailList) {
//            OdsSaleOrderDetail odsSaleOrderDetail = new OdsSaleOrderDetail();
//            odsSaleOrderDetail.setDetailId(UUID.randomUUID().toString());
//            odsSaleOrderDetail.setTitle(dealerOrderDetail.getProductTitle());
//            //odsSaleOrderDetail.setStatus(toOmsOrderDetailStatus(order.getStatus()));
//            //odsSaleOrderDetail.setMallProductCode();//平台的商品编号
//            odsSaleOrderDetail.setPlatformNumberId(dealerOrderDetail.getSkuId().toString());//平台商品id
//            odsSaleOrderDetail.setMallSkuCode(dealerOrderDetail.getSkuNumber());//库存单位Sku编号/
//            odsSaleOrderDetail.setOuterCode(dealerOrderDetail.getSkuId().toString());//平台商品代码/
//            odsSaleOrderDetail.setOuterSkuCode(dealerOrderDetail.getSkuNumber());//平台商品SKU/
//            //odsSaleOrderDetail.setSkuName(order.getSkuPropertiesName());//SKU属性/
//            odsSaleOrderDetail.setPrice(Double.valueOf(dealerOrderDetail.getOriginalPrice()));//商品价格
//            odsSaleOrderDetail.setTotalFee(Double.valueOf(dealerOrderDetail.getPriceCount()));//订单明细金额
//            odsSaleOrderDetail.setPayment(Double.valueOf(dealerOrderDetail.getPriceCount()));//商品支付金额
//            //odsSaleOrderDetail.setDiscountFee(Double.valueOf(order.getDiscountFee()));//商品优惠金额
//            odsSaleOrderDetail.setQuantity(1);//数量
//            saleOrderDetailList.add(odsSaleOrderDetail);
//        }
//        odsSaleOrder.setDetails(saleOrderDetailList);
    odsSaleOrder.setPayments(createSinglePayment(PayType.ALI_PAY, odsSaleOrder));
    return odsSaleOrder;
  }

  @Override
  protected OrderReceiverInfoUpdateResponse doUpdateOrderReceiverInfo(
      OrderReceiverInfoUpdateRequest request) {
    throw new MallException("平台暂不支持！");
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO_JX};
  }
}
