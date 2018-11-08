package com.greatonce.oms.bridge.wms.impl.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.AbstractStockOutOrderBridge;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse.WmsStockOutOrderStatus;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.util.FormatUtil;
import com.qimen.api.request.StockoutCreateRequest;
import com.qimen.api.request.StockoutCreateRequest.DeliveryOrder;
import com.qimen.api.request.StockoutCreateRequest.OrderLine;
import com.qimen.api.request.StockoutCreateRequest.ReceiverInfo;
import com.qimen.api.request.StockoutCreateRequest.SenderInfo;
import com.qimen.api.request.StockoutQueryRequest;
import com.qimen.api.response.StockoutCreateResponse;
import com.qimen.api.response.StockoutQueryResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 奇门出库单接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-08-16
 * @link taobao.qimen.stockout.query
 */
@Component
public class QimenStockOutOrderBridge extends AbstractStockOutOrderBridge {

  @Autowired
  protected QimenWms qimenWms;

  @Override
  protected StockOutOrderQueryResponse doQueryOrder(StockOutOrderQueryRequest request) {
    try {
      StockoutQueryRequest req = new StockoutQueryRequest();
      req.setDeliveryOrderCode(request.getOmsCode());
      req.setDeliveryOrderId(request.getWmsCode());
      final StockoutQueryResponse rsp = qimenWms.call(request.getWarehouse(), req);
      final StockOutOrderQueryResponse response = new StockOutOrderQueryResponse(request);
      if (rsp.getDeliveryOrder() != null) {
        response.setExists(true);
        if (!Assert.isEmpty(rsp.getDeliveryOrder().getStatus())) {
          response.setStatus(WmsStockOutOrderStatus.valueOf(rsp.getDeliveryOrder().getStatus()));
        }
      }
      return response;
    } catch (Exception e) {
      return new StockOutOrderQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected StockOutOrderCreateResponse doCreateOrder(StockOutOrderCreateRequest request) {
    try {
      StockoutCreateRequest req = new StockoutCreateRequest();
      DeliveryOrder deliveryOrder = buildDeliveryOrder(request);

      req.setDeliveryOrder(deliveryOrder);
      req.setOrderLines(buildOrderLines(request, deliveryOrder));
      req.setExtendProps(buildExtendProps(request));
      StockoutCreateResponse rsp = qimenWms.call(request.getWarehouse(), req);
      return new StockOutOrderCreateResponse(request, rsp.getDeliveryOrderId());
    } catch (WmsException e) {
      return new StockOutOrderCreateResponse(request, false, e.getMessage());
    }

  }

  protected Map buildExtendProps(StockOutOrderCreateRequest request) {
    return request.getExtendProps();
  }

  protected List<OrderLine> buildOrderLines(StockOutOrderCreateRequest request,
      DeliveryOrder deliveryOrder) {
    List<OrderLine> orderLineList = new ArrayList<>(request.getDetails().size());
    int index = 1;
    for (StockOutOrderCreateRequest.StockOutOrderDetail detail : request.getDetails()) {
      if (detail.getQuantity() == 0) {
        continue;
      }
      OrderLine orderLine = new OrderLine();
      orderLine.setOwnerCode(deliveryOrder.getOwnerCode());
      orderLine.setItemCode(detail.getSkuCode());
      orderLine.setItemId(detail.getWmsSkuId());
      orderLine.setItemName(detail.getProductName());
      orderLine.setPlanQty(String.valueOf(detail.getQuantity()));
      orderLine.setQuantity(String.valueOf(detail.getQuantity()));
      orderLine.setRetailPrice(FormatUtil.formatDouble(detail.getSellingPrice()));
      orderLine.setAmount(FormatUtil.formatDouble(detail.getSellingAmount()));
      orderLine.setOrderLineNo(String.valueOf(index++));
      orderLineList.add(orderLine);
    }
    return orderLineList;
  }

  private ReceiverInfo buildReceiverInfo(StockOutOrderCreateRequest request) {
    ReceiverInfo receiverInfo = new ReceiverInfo();
    receiverInfo.setCompany(WmsUtil.valueOrEmpty(request.getReceiverName()));
    receiverInfo.setName(WmsUtil.valueOrEmpty(request.getReceiverName()));
    receiverInfo.setZipCode(WmsUtil.valueOrEmpty(request.getReceiverZipCode()));
    receiverInfo.setTel(WmsUtil.valueOrEmpty(request.getReceiverTelephone()));
    receiverInfo.setMobile(WmsUtil.valueOrEmpty(request.getReceiverMobile()));
    receiverInfo.setIdType(WmsUtil.valueOrEmpty(request.getReceiverIdType()));
    receiverInfo.setIdNumber(WmsUtil.valueOrEmpty(request.getReceiverId()));
    receiverInfo.setEmail(WmsUtil.valueOrEmpty(request.getReceiverEmail()));
    receiverInfo.setCountryCode(WmsUtil.valueOrEmpty(request.getReceiverCountry()));
    receiverInfo.setProvince(WmsUtil.valueOrEmpty(request.getReceiverProvince()));
    receiverInfo.setCity(WmsUtil.valueOrEmpty(request.getReceiverCity()));
    receiverInfo.setArea(WmsUtil.valueOrEmpty(request.getReceiverArea()));
    receiverInfo.setTown(WmsUtil.valueOrEmpty(request.getReceiverTown()));
    receiverInfo.setDetailAddress(WmsUtil.valueOrEmpty(request.getReceiverAddress()));
    return receiverInfo;
  }

  protected SenderInfo buildSenderInfo(StockOutOrderCreateRequest request) {
    SenderInfo senderInfo = new SenderInfo();
    senderInfo.setCompany(WmsUtil.valueOrEmpty(request.getSenderCompany()));
    senderInfo.setName(WmsUtil.valueOrEmpty(request.getSenderName()));
    senderInfo.setZipCode(WmsUtil.valueOrEmpty(request.getSenderZipCode()));
    senderInfo.setTel(WmsUtil.valueOrEmpty(request.getSenderTelephone()));
    senderInfo.setMobile(WmsUtil.valueOrEmpty(request.getSenderMobile()));
    senderInfo.setEmail(WmsUtil.valueOrEmpty(request.getSenderEmail()));
    senderInfo.setCountryCode(WmsUtil.valueOrEmpty(request.getSenderCountry()));
    senderInfo.setProvince(WmsUtil.valueOrEmpty(request.getSenderProvince()));
    senderInfo.setCity(WmsUtil.valueOrEmpty(request.getSenderCity()));
    senderInfo.setArea(WmsUtil.valueOrEmpty(request.getSenderArea()));
    senderInfo.setTown(WmsUtil.valueOrEmpty(request.getSenderTown()));
    senderInfo.setDetailAddress(WmsUtil.valueOrEmpty(request.getSenderAddress()));
    return senderInfo;
  }

  protected DeliveryOrder buildDeliveryOrder(StockOutOrderCreateRequest request) {
    DeliveryOrder deliveryOrder = new DeliveryOrder();
    deliveryOrder.setTotalOrderLines((long) request.getDetails().size());
    deliveryOrder.setDeliveryOrderCode(request.getOmsCode());
    deliveryOrder.setOrderType(QimenConverter.toOutOrderTypeString(request.getOrderType()));
    deliveryOrder.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
    deliveryOrder.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
    deliveryOrder.setCreateTime(DateTimeUtil.format(request.getCreatedTime()));
    deliveryOrder.setLogisticsCode(request.getExpressCode());
    deliveryOrder.setLogisticsName(request.getExpressName());
    deliveryOrder.setSupplierCode(request.getSupplierCode());
    deliveryOrder.setSupplierName(request.getSupplierName());
    deliveryOrder.setShopCode(request.getStoreCode());
    deliveryOrder.setShopName(request.getStoreName());
    deliveryOrder.setTransportMode(request.getTransportMode());
    deliveryOrder.setRemark(request.getRemark());

    deliveryOrder.setSenderInfo(buildSenderInfo(request));
    deliveryOrder.setReceiverInfo(buildReceiverInfo(request));
    return deliveryOrder;
  }

  @Override
  protected StockOutOrderCancelResponse doCancelOrder(StockOutOrderCancelRequest request) {
    try {
      com.qimen.api.request.OrderCancelRequest req = new com.qimen.api.request.OrderCancelRequest();
      req.setCustomerId(request.getWarehouse().getWmsApp().getCustomerId());
      req.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
      req.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      req.setOrderCode(request.getOmsCode());
      req.setOrderId(request.getWmsCode());
      req.setOrderType(QimenConverter.toOutOrderTypeString(request.getOrderType()));
      req.setCancelReason(request.getReason());
      qimenWms.call(request.getWarehouse(), req);
      return new StockOutOrderCancelResponse(request);
    } catch (WmsException e) {
      return new StockOutOrderCancelResponse(request, false, e.getMessage());
    }
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
