package com.greatonce.oms.bridge.wms.impl.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.AbstractStockInOrderBridge;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.bridge.wms.request.StockInOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockInOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse.WmsStockInOrderStatus;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.util.FormatUtil;
import com.qimen.api.request.EntryorderCreateRequest;
import com.qimen.api.request.EntryorderCreateRequest.EntryOrder;
import com.qimen.api.request.EntryorderCreateRequest.OrderLine;
import com.qimen.api.request.EntryorderCreateRequest.ReceiverInfo;
import com.qimen.api.request.EntryorderCreateRequest.SenderInfo;
import com.qimen.api.request.EntryorderQueryRequest;
import com.qimen.api.response.EntryorderCreateResponse;
import com.qimen.api.response.EntryorderQueryResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 奇门入库单接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-08-21
 * @link @link taobao.qimen.entryorder.query
 */
@Component
public class QimenStockInOrderBridge extends AbstractStockInOrderBridge {

  @Autowired
  protected QimenWms qimenWms;

  @Override
  protected StockInOrderQueryResponse doQueryOrder(StockInOrderQueryRequest request) {
    try {
      EntryorderQueryRequest req = new EntryorderQueryRequest();
      req.setEntryOrderCode(request.getOmsCode());
      req.setEntryOrderId(request.getWmsCode());
      final EntryorderQueryResponse rsp = qimenWms.call(request.getWarehouse(), req);
      final StockInOrderQueryResponse response = new StockInOrderQueryResponse(request);
      if (rsp.getEntryOrder() != null) {
        response.setExists(true);
        if (!Assert.isEmpty(rsp.getEntryOrder().getStatus())) {
          response.setStatus(WmsStockInOrderStatus.valueOf(rsp.getEntryOrder().getStatus()));
        }
      }
      return response;
    } catch (Exception e) {
      return new StockInOrderQueryResponse(request, false, e.getMessage());
    }
  }

  @Override
  protected StockInOrderCreateResponse doCreateOrder(StockInOrderCreateRequest request) {
    try {
      EntryorderCreateRequest req = new EntryorderCreateRequest();
      EntryOrder entryOrder = buildEntryOrder(request);
      req.setEntryOrder(entryOrder);
      req.setExtendProps(buildExtendProps(request));
      req.setOrderLines(buildOrderLines(request, entryOrder));
      EntryorderCreateResponse rsp = qimenWms.call(request.getWarehouse(), req);
      return new StockInOrderCreateResponse(request, rsp.getEntryOrderId());
    } catch (WmsException e) {
      return new StockInOrderCreateResponse(request, false, e.getMessage());
    }
  }

  protected List<OrderLine> buildOrderLines(StockInOrderCreateRequest request,
      EntryOrder entryOrder) {
    List<OrderLine> orderLineList = new ArrayList<>(request.getDetails().size());
    int index = 1;
    for (StockInOrderCreateRequest.StockInOrderDetail detail : request.getDetails()) {
      if (detail.getQuantity() == 0) {
        continue;
      }
      OrderLine orderLine = new OrderLine();
      orderLine.setProductCode(detail.getProductCode());
      orderLine.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      orderLine.setItemCode(detail.getSkuCode());
      orderLine.setItemId(detail.getWmsSkuId());
      orderLine.setItemName(detail.getProductName());
      orderLine.setPlanQty((long) detail.getQuantity());
      orderLine.setQuantity(String.valueOf(detail.getQuantity()));
      orderLine.setSkuProperty(detail.getSkuName());
      orderLine.setPurchasePrice(String.valueOf(detail.getPurchasePrice()));
      orderLine.setRetailPrice(FormatUtil.formatDouble(detail.getSellingPrice()));
      orderLine.setAmount(FormatUtil.formatDouble(detail.getSellingAmount()));
      orderLine.setWarehouseCode(entryOrder.getWarehouseCode());
      orderLine.setOrderLineNo(String.valueOf(index++));
      orderLineList.add(orderLine);
    }
    return orderLineList;
  }

  protected EntryOrder buildEntryOrder(StockInOrderCreateRequest request) {
    EntryOrder entryOrder = new EntryOrder();
    entryOrder.setEntryOrderCode(request.getOmsCode());
    entryOrder.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
    entryOrder.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
    entryOrder.setOrderCreateTime(DateTimeUtil.format(request.getCreatedTime()));
    entryOrder.setOrderType(QimenConverter.toInOrderTypeString(request.getOrderType()));
    entryOrder.setExpectStartTime(DateTimeUtil.format(request.getExpectStartTime()));
    entryOrder.setExpectEndTime(DateTimeUtil.format(request.getExpectEndTime()));
    entryOrder.setLogisticsCode(request.getExpressCode());
    entryOrder.setLogisticsName(request.getExpressName());
    entryOrder.setExpressCode(request.getExpressCode());
    entryOrder.setSupplierCode(request.getSupplierCode());
    entryOrder.setSupplierName(request.getSupplierName());
    entryOrder.setOperateTime(DateTimeUtil.format(LocalDateTime.now()));
    entryOrder.setPurchaseOrderCode(request.getPurchaseOrderCode());
    entryOrder.setRemark(request.getRemark());

    entryOrder.setSenderInfo(buildSenderInfo(request));
    entryOrder.setReceiverInfo(buildReceiverInfo(request));
    return entryOrder;
  }

  protected ReceiverInfo buildReceiverInfo(StockInOrderCreateRequest request) {
    ReceiverInfo receiverInfo = new ReceiverInfo();
    receiverInfo.setCompany(WmsUtil.valueOrEmpty(request.getReceiverName()));
    receiverInfo.setName(WmsUtil.valueOrEmpty(request.getReceiverName()));
    receiverInfo.setZipCode(WmsUtil.valueOrEmpty(request.getReceiverZipCode()));
    receiverInfo.setTel(WmsUtil.valueOrEmpty(request.getReceiverTelephone()));
    receiverInfo.setMobile(WmsUtil.valueOrEmpty(request.getReceiverMobile()));
    receiverInfo.setEmail(WmsUtil.valueOrEmpty(request.getReceiverEmail()));
    receiverInfo.setCountryCode(WmsUtil.valueOrEmpty(request.getReceiverCountry()));
    receiverInfo.setProvince(WmsUtil.valueOrEmpty(request.getReceiverProvince()));
    receiverInfo.setCity(WmsUtil.valueOrEmpty(request.getReceiverCity()));
    receiverInfo.setArea(WmsUtil.valueOrEmpty(request.getReceiverArea()));
    receiverInfo.setTown(WmsUtil.valueOrEmpty(request.getReceiverTown()));
    receiverInfo.setDetailAddress(WmsUtil.valueOrEmpty(request.getReceiverAddress()));
    receiverInfo.setRemark(WmsUtil.valueOrEmpty(request.getReceiverRemark()));
    return receiverInfo;
  }

  protected SenderInfo buildSenderInfo(StockInOrderCreateRequest request) {
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
    senderInfo.setRemark(WmsUtil.valueOrEmpty(request.getSenderRemark()));
    return senderInfo;
  }

  protected Map buildExtendProps(StockInOrderCreateRequest request) {
    return request.getExtendProps();
  }

  @Override
  protected StockInOrderCancelResponse doCancelOrder(StockInOrderCancelRequest request) {
    try {
      com.qimen.api.request.OrderCancelRequest req = new com.qimen.api.request.OrderCancelRequest();
      req.setCustomerId(request.getWarehouse().getWmsApp().getCustomerId());
      req.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
      req.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      req.setOrderCode(request.getOmsCode());
      req.setOrderId(request.getWmsCode());
      req.setOrderType(QimenConverter.toInOrderTypeString(request.getOrderType()));
      req.setCancelReason(request.getReason());
      qimenWms.call(request.getWarehouse(), req);
      return new StockInOrderCancelResponse(request);
    } catch (WmsException e) {
      return new StockInOrderCancelResponse(request, false, e.getMessage());
    }
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
