package com.greatonce.oms.bridge.mall.impl.vip;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipCancelOrderQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipOrderQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipCancelOrderQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse.VipOrder;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse.VipOrderDetail;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipSalesSourceType;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.vip.osp.sdk.exception.OspException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vipapis.inventory.CancelledOrdersResponse;
import vipapis.inventory.InventoryCancelledOrdersRequest;
import vipapis.inventory.InventoryOccupiedOrdersRequest;
import vipapis.inventory.InventoryServiceHelper.InventoryServiceClient;
import vipapis.inventory.OccupiedOrder;
import vipapis.inventory.OccupiedOrderDetail;
import vipapis.inventory.OccupiedOrderResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * VipOrderBridge
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/27
 */
@Component
public class VipOrderBridge {

  private static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  private static final int PAGE_SIZE = 50;
  private final InventoryServiceClient inventoryClient = new InventoryServiceClient();
  @Autowired
  private VipMall mall;

  /**
   * 查询订单.
   */
  public VipOrderQueryResponse queryOrder(VipOrderQueryRequest request) {
    try {
      //初始化唯品商场上下文
      mall.initContext(request.getStore());
      //获取实时订单请求
      InventoryOccupiedOrdersRequest req = new InventoryOccupiedOrdersRequest();
      //获取一个特定的时间段
      req.setVendor_id(Long.valueOf(request.getStore().getSetting().getVipVendorId()));
      req.setSt_query_time(DateTimeUtil.toTimestamp(request.getBeginTime()));
      req.setEt_query_time(DateTimeUtil.toTimestamp(request.getEndTime()));
      req.setPage(request.getPage());
      req.setLimit(PAGE_SIZE);
      //返回包含所有占位订单的响应
      OccupiedOrderResponse occupiedOrderResponse =
          inventoryClient.getInventoryOccupiedOrders(req);
      MALL_LOGGER.info(request.getStore(),"api:vipOrderQuery,request:{},response:{}",
          JsonUtil.toJson(req),JsonUtil.toJson(occupiedOrderResponse));
      //创建一个新的唯品会订单查询响应
      VipOrderQueryResponse response = new VipOrderQueryResponse(request);
      response.setHasNext(occupiedOrderResponse.getHas_next());
      //如果该货位有此商品
      if (!Assert.isEmpty(occupiedOrderResponse.getOccupied_orders())) {
        //创建一个唯品会订单的集合
        List<VipOrder> orders = new ArrayList<>(occupiedOrderResponse.getOccupied_orders().size());
        for (OccupiedOrder occupiedOrder : occupiedOrderResponse.getOccupied_orders()) {
          VipOrder vipOrder = new VipOrder();
          //设置Vip订单编号
          vipOrder.setOrderNo(occupiedOrder.getOccupied_order_sn());
          //设置Vip订单的详细信息
          List<VipOrderDetail> vipOrderDetails =
              new ArrayList<>(occupiedOrder.getBarcodes().size());
          for (OccupiedOrderDetail detail : occupiedOrder.getBarcodes()) {
            VipOrderDetail vipOrderDetail = new VipOrderDetail();
            //条码
            vipOrderDetail.setBarcode(detail.getBarcode());
            //数量
            vipOrderDetail.setQuantity(detail.getAmount());
            //出售凭单
            vipOrderDetail.setSalesNo(String.valueOf(detail.getSales_no()));
            //派发时间
            vipOrderDetail.setPayTime(DateTimeUtil.parserLocalDateTime(detail.getCreate_time()));
            //销售来源
            vipOrderDetail.setSalesSource(parseSalesSource(detail.getSales_source_indicator()));
            vipOrderDetails.add(vipOrderDetail);
          }
          vipOrder.setDetails(vipOrderDetails);
          orders.add(vipOrder);
        }
        //将当前订单集合设置到VIP订单查询响应里
        response.setOrders(orders);
      }
      return response;
    } catch (OspException e) {
      return new VipOrderQueryResponse(request, false, e.getReturnMessage());
    }
  }

  //获取唯品会销售来源类型
  private VipSalesSourceType parseSalesSource(Integer source) {
    switch (source) {
      case 0:
        return VipSalesSourceType.SCHEDULE;
      case 1:
        return VipSalesSourceType.SHOP;
      default:
        return VipSalesSourceType.UNKNOWN;
    }
  }

  /**
   * 实时商品订单：获取已取消订单请求.
   */
  public VipCancelOrderQueryResponse queryCanceledOrders(VipCancelOrderQueryRequest request) {
    try {
      mall.initContext(request.getStore());
      //已取消订单请求
      InventoryCancelledOrdersRequest ordersRequest = new InventoryCancelledOrdersRequest();
      ordersRequest.setVendor_id(Long.valueOf(request.getStore().getSetting().getVipVendorId()));

      ordersRequest.setCooperation_no(request.getStore().getSetting().getVipCooperationNo());
      //获取该订单开始时间
      ordersRequest.setSt_query_time(DateTimeUtil.toTimestamp(request.getBeginTime()));
      //结束时间
      ordersRequest.setEt_query_time(DateTimeUtil.toTimestamp(request.getEndTime()));
      //设置页
      ordersRequest.setPage(request.getPage());
      //设置分页规则
      ordersRequest.setLimit(PAGE_SIZE);
      //已取消订单响应
      CancelledOrdersResponse cancelledOrdersResponse =
          inventoryClient.getInventoryCancelledOrders(ordersRequest);
      MALL_LOGGER.info(request.getStore(),"api:vipCancelOrderQuery,request:{},response:{}",
          JsonUtil.toJson(ordersRequest),JsonUtil.toJson(cancelledOrdersResponse));
      VipCancelOrderQueryResponse response = new VipCancelOrderQueryResponse(request);
      response.setHasNext(cancelledOrdersResponse.getHas_next());
      //判断查询结果是否为空
      if (cancelledOrdersResponse.getOccupied_orders() != null && !cancelledOrdersResponse
          .getOccupied_orders().isEmpty()) {
        List<VipCancelOrderQueryResponse.VipOrder> orders = new ArrayList<>(
            cancelledOrdersResponse.getOccupied_orders().size());
        for (OccupiedOrder occupiedOrder : cancelledOrdersResponse.getOccupied_orders()) {
          VipCancelOrderQueryResponse.VipOrder vipOrder = new VipCancelOrderQueryResponse.VipOrder();
          vipOrder.setOrderNo(occupiedOrder.getOccupied_order_sn());
          List<VipCancelOrderQueryResponse.VipOrderDetail> vipOrderDetails = new ArrayList<>();
          for (OccupiedOrderDetail detail : occupiedOrder.getBarcodes()) {
            VipCancelOrderQueryResponse.VipOrderDetail vipOrderDetail = new VipCancelOrderQueryResponse.VipOrderDetail();
            vipOrderDetail.setBarcode(detail.getBarcode());
            vipOrderDetail.setQuantity(detail.getAmount());
            vipOrderDetail.setSalesNo(String.valueOf(detail.getSales_no()));
            vipOrderDetail.setSalesSource(parseSalesSource(detail.getSales_source_indicator()));
            vipOrderDetails.add(vipOrderDetail);
          }
          vipOrder.setDetails(vipOrderDetails);
          orders.add(vipOrder);
        }
        response.setOrders(orders);
      }
      return response;
    } catch (Exception e) {
      return new VipCancelOrderQueryResponse(request, false, e.getMessage());
    }
  }

}
