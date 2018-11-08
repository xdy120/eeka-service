package com.greatonce.oms.bridge.wms.impl.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.AbstractDeliveryOrderBridge;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenDeliveryOrderType;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest.DeliveryOrderDetail;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderQueryRequest;
import com.greatonce.oms.bridge.wms.request.OrderProcessQueryRequest;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderQueryResponse.WmsDeliveryOrderStatus;
import com.greatonce.oms.bridge.wms.response.OrderProcessQueryResponse;
import com.greatonce.oms.domain.enums.WmsType;
import com.qimen.api.request.DeliveryorderCreateRequest;
import com.qimen.api.request.DeliveryorderCreateRequest.DeliveryOrder;
import com.qimen.api.request.DeliveryorderCreateRequest.Insurance;
import com.qimen.api.request.DeliveryorderCreateRequest.Invoice;
import com.qimen.api.request.DeliveryorderCreateRequest.OrderLine;
import com.qimen.api.request.DeliveryorderCreateRequest.ReceiverInfo;
import com.qimen.api.request.DeliveryorderCreateRequest.SenderInfo;
import com.qimen.api.request.DeliveryorderQueryRequest;
import com.qimen.api.request.OrderCancelRequest;
import com.qimen.api.request.OrderprocessQueryRequest;
import com.qimen.api.response.DeliveryorderCreateResponse;
import com.qimen.api.response.DeliveryorderQueryResponse;
import com.qimen.api.response.OrderprocessQueryResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 奇门发货单接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
@Component
public class QimenDeliveryOrderBridge extends AbstractDeliveryOrderBridge {

  @Autowired
  protected QimenWms qimenWms;

  @Override
  protected DeliveryOrderQueryResponse doQueryOrder(DeliveryOrderQueryRequest request) {
    try {
      DeliveryorderQueryRequest req = new DeliveryorderQueryRequest();
      req.setDeliveryOrderCode(request.getOmsCode());
      req.setDeliveryOrderId(request.getWmsCode());
      final DeliveryorderQueryResponse rsp = qimenWms.call(request.getWarehouse(), req);
      DeliveryOrderQueryResponse response = new DeliveryOrderQueryResponse(request);
      if (rsp.getDeliveryOrder() != null) {
        response.setExists(true);
        if (!Assert.isEmpty(rsp.getDeliveryOrder().getStatus())) {
          response.setStatus(WmsDeliveryOrderStatus.valueOf(rsp.getDeliveryOrder().getStatus()));
        }
      }
      return response;
    } catch (Exception e) {
      return new DeliveryOrderQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected DeliveryOrderCreateResponse doCreateOrder(DeliveryOrderCreateRequest request) {
    try {
      DeliveryorderCreateRequest req = new DeliveryorderCreateRequest();
      DeliveryOrder deliveryOrder = buildDeliveryOrder(request);
      req.setDeliveryOrder(deliveryOrder);
      req.setOrderLines(buildOrderLines(request, deliveryOrder));
      req.setExtendProps(buildExtendProps(request));

      DeliveryorderCreateResponse rsp = qimenWms.call(request.getWarehouse(), req);
      return new DeliveryOrderCreateResponse(request, rsp.getDeliveryOrderId());
    } catch (WmsException e) {
      return new DeliveryOrderCreateResponse(request, false, e.getMessage());
    }
  }

  protected DeliveryOrder buildDeliveryOrder(DeliveryOrderCreateRequest request) {
    DeliveryOrder deliveryOrder = createDeliveryOrder(request);
    deliveryOrder.setDeliveryOrderCode(request.getOmsCode());
    deliveryOrder.setOrderType(QimenConverter.toDeliveryOrderTypeString(request.getOrderType()));
    deliveryOrder.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
    deliveryOrder.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
    deliveryOrder.setOrderFlag(request.getOrderFlag());
    deliveryOrder.setSourcePlatformCode(QimenConverter.toMallTypeString(request.getMallType()));
    deliveryOrder.setSourcePlatformName(request.getMallType().caption());
    deliveryOrder.setCreateTime(DateTimeUtil.format(request.getCreatedTime()));
    deliveryOrder.setPlaceOrderTime(DateTimeUtil.format(request.getMallCreatedTime()));
    deliveryOrder.setPayTime(DateTimeUtil.format(request.getMallPaidTime()));
    deliveryOrder.setOperateTime(DateTimeUtil.format(LocalDateTime.now()));
    deliveryOrder.setShopNick(request.getStoreName());
    deliveryOrder.setShopName(request.getStoreName());
    deliveryOrder.setShopCode(request.getStoreCode());
    deliveryOrder.setBuyerNick(request.getBuyer());
    deliveryOrder.setSellerNick(request.getSenderName());
    deliveryOrder.setTotalAmount(String.valueOf(request.getTotalAmount()));
    deliveryOrder.setItemAmount(String.valueOf(request.getTotalAmount()));
    deliveryOrder.setDiscountAmount(String.valueOf(request.getDiscountAmount()));
    deliveryOrder.setFreight(String.valueOf(request.getExpressFee()));
    deliveryOrder.setArAmount(String.valueOf(request.getCodAmount()));
    deliveryOrder.setGotAmount(String.valueOf(request.getPaidAmount()));
    deliveryOrder.setServiceFee(String.valueOf(request.getCodFee()));
    deliveryOrder.setLogisticsCode(request.getExpressCode());
    deliveryOrder.setLogisticsName(request.getExpressName());
    deliveryOrder.setExpressCode(request.getExpressNo());
    deliveryOrder.setLogisticsAreaCode(request.getExpressAreaCode());
    deliveryOrder.setIsUrgency(QimenConverter.toBooleanString(request.isUrgency()));
    deliveryOrder.setInvoiceFlag(QimenConverter.toBooleanString(request.isNeedInvoice()));
    deliveryOrder.setInsuranceFlag(QimenConverter.toBooleanString(request.isInsurance()));
    deliveryOrder.setSellerMessage(request.getSellerMemo());
    deliveryOrder.setBuyerMessage(request.getBuyerMemo());
    deliveryOrder.setRemark(request.getRemark());
    deliveryOrder.setServiceCode(String.valueOf(request.getCodFee()));

    deliveryOrder.setSenderInfo(buildSenderInfo(request));
    deliveryOrder.setReceiverInfo(buildReceiverInfo(request));
    deliveryOrder.setInvoices(buildInvoices(request));
    deliveryOrder.setInsurance(buildInsurance(request));

    return deliveryOrder;
  }

  protected DeliveryOrder createDeliveryOrder(DeliveryOrderCreateRequest request) {
    return new DeliveryOrder();
  }


  protected Map buildExtendProps(DeliveryOrderCreateRequest request) {
    WmsUtil.putExtendProp(request, "consolidationCode", request.getConsolidationCode());
    WmsUtil.putExtendProp(request, "consolidationName", request.getConsolidationName());
    WmsUtil.putExtendProp(request, "sortation", request.getSortation());
    WmsUtil.putExtendProp(request, "routeCode", request.getRouteCode());
    return request.getExtendProps();
  }

  protected ArrayList<OrderLine> buildOrderLines(DeliveryOrderCreateRequest request,
      DeliveryOrder deliveryOrder) {
    ArrayList<OrderLine> orderLines = new ArrayList<>(request.getDetails().size());
    int index = 1;
    for (DeliveryOrderDetail detail : request.getDetails()) {
      if (detail.getQuantity() == 0) {
        continue;
      }
      OrderLine orderLine = new OrderLine();
      orderLine.setSourceOrderCode(detail.getTradeId());
      orderLine.setSubSourceOrderCode(detail.getOid());
      orderLine.setPayNo(detail.getPayNo());
      orderLine.setOwnerCode(deliveryOrder.getOwnerCode());
      orderLine.setItemCode(detail.getSkuCode());
      orderLine.setItemId(detail.getWmsSkuId());
      orderLine.setItemName(detail.getProductName());
      orderLine.setPlanQty(String.valueOf(detail.getQuantity()));
      orderLine.setActualPrice(String.valueOf(detail.getPrice()));
      orderLine.setSettlementAmount(String.valueOf(detail.getAmount()));
      orderLine.setOrderLineNo(String.valueOf(index++));
      orderLines.add(orderLine);
    }
    return orderLines;
  }

  protected Insurance buildInsurance(DeliveryOrderCreateRequest request) {
    if (!request.isInsurance()) {
      return null;
    }
    Insurance insurance = new Insurance();
    insurance.setAmount(String.valueOf(request.getInsuranceAmount()));
    insurance.setType(request.getInsuranceType());
    return insurance;
  }

  protected ArrayList<Invoice> buildInvoices(DeliveryOrderCreateRequest request) {
    if (!request.isNeedInvoice()) {
      return null;
    }
    Invoice invoice = new Invoice();
    invoice.setType(request.getInvoiceType());
    invoice.setHeader(request.getInvoiceHeader());
    invoice.setAmount(request.getInvoiceAmount());
    invoice.setContent(request.getInvoiceContent());
    ArrayList<Invoice> invoices = new ArrayList<>();
    invoices.add(invoice);
    return invoices;
  }

  protected ReceiverInfo buildReceiverInfo(DeliveryOrderCreateRequest request) {
    ReceiverInfo receiverInfo = new ReceiverInfo();
    receiverInfo.setName(request.getReceiverName());
    receiverInfo.setTel(request.getReceiverTelephone());
    receiverInfo.setMobile(request.getReceiverMobile());
    receiverInfo.setCountryCode(request.getReceiverCountryCode());
    receiverInfo.setProvince(request.getReceiverProvince());
    receiverInfo.setCity(request.getReceiverCity());
    receiverInfo.setArea(request.getReceiverDistrict());

    receiverInfo.setCompany(WmsUtil.valueOrEmpty(request.getReceiverName()));
    receiverInfo.setZipCode(WmsUtil.valueOrEmpty(request.getReceiverZipCode()));
    receiverInfo.setTown(WmsUtil.valueOrEmpty(request.getReceiverTown()));
    receiverInfo.setEmail(WmsUtil.valueOrEmpty(request.getReceiverEmail()));
    receiverInfo.setDetailAddress(request.getReceiverAddress());
    return receiverInfo;
  }

  protected SenderInfo buildSenderInfo(DeliveryOrderCreateRequest request) {
    SenderInfo senderInfo = new SenderInfo();
    senderInfo.setTel(WmsUtil.valueOrEmpty(request.getSenderTelephone()));
    senderInfo.setMobile(WmsUtil.valueOrEmpty(request.getSenderMobile()));
    senderInfo.setCountryCode(WmsUtil.valueOrEmpty(request.getSenderCountry()));
    senderInfo.setProvince(WmsUtil.valueOrEmpty(request.getSenderProvince()));
    senderInfo.setCity(WmsUtil.valueOrEmpty(request.getSenderCity()));
    senderInfo.setArea(WmsUtil.valueOrEmpty(request.getSenderDistrict()));
    senderInfo.setCompany(WmsUtil.valueOrEmpty(request.getSenderCompany()));
    senderInfo.setName(WmsUtil.valueOrEmpty(request.getSenderName()));
    senderInfo.setTown(WmsUtil.valueOrEmpty(request.getSenderTown()));
    senderInfo.setEmail(WmsUtil.valueOrEmpty(request.getSenderEmail()));
    senderInfo.setZipCode(WmsUtil.valueOrEmpty(request.getSenderZipCode()));
    senderInfo.setDetailAddress(request.getSenderAddress());
    return senderInfo;
  }


  @Override
  protected DeliveryOrderCancelResponse doCancelOrder(DeliveryOrderCancelRequest request) {
    try {
      OrderCancelRequest req = buildOrderCancelRequest(request);
      qimenWms.call(request.getWarehouse(), req);
      return new DeliveryOrderCancelResponse(request);
    } catch (WmsException e) {
      return new DeliveryOrderCancelResponse(request, false, e.getMessage());
    }
  }

  protected OrderCancelRequest buildOrderCancelRequest(DeliveryOrderCancelRequest request) {
    OrderCancelRequest req = new OrderCancelRequest();
    req.setCustomerId(request.getWarehouse().getWmsApp().getCustomerId());
    req.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
    req.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
    req.setOrderCode(request.getOmsCode());
    req.setOrderId(request.getWmsCode());
    req.setOrderType(QimenDeliveryOrderType.JYCK.toString());
    req.setCancelReason(request.getReason());
    return req;
  }


  @Override
  public OrderProcessQueryResponse doQueryOrderProcess(OrderProcessQueryRequest request) {
    try {
      OrderprocessQueryRequest req = new OrderprocessQueryRequest();
      req.setOrderType(QimenConverter.toDeliveryOrderTypeString(request.getOrderType()));
      req.setOrderCode(request.getOmsCode());
      req.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
      req.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      OrderprocessQueryResponse rsp = qimenWms.call(request.getWarehouse(), req);

      OrderProcessQueryResponse response = new OrderProcessQueryResponse(request);
      if (!Assert.isNull(rsp.getOrderProcess()) && !Assert
          .isEmpty(rsp.getOrderProcess().getProcesses())) {
        List<OrderProcessQueryResponse.Process> processList = new ArrayList<>();
        for (OrderprocessQueryResponse.Process process : rsp.getOrderProcess().getProcesses()) {
          OrderProcessQueryResponse.Process temp = new OrderProcessQueryResponse.Process();
          temp.setOperatorName(process.getOperatorName());
          temp.setOperateTime(process.getOperateTime());
          temp.setOperateInfo(process.getOperateInfo());
          temp.setRemark(process.getRemark());
          processList.add(temp);
        }
        response.setProcesses(processList);
      }
      return response;
    } catch (WmsException e) {
      return new OrderProcessQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
