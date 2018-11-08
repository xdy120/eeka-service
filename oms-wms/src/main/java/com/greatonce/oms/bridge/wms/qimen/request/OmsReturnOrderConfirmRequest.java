package com.greatonce.oms.bridge.wms.qimen.request;

import com.greatonce.core.supports.xml.JaxbMapAdapter;
import com.qimen.api.request.ReturnorderConfirmRequest;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsReturnOrderConfirmRequest implements OmsBaseQimenRequest {

  @Override
  public String getOwnerCode() {
    return this.getReturnOrder().getOwnerCode();
  }

  @Override
  public String getOrderCode() {
    return this.getReturnOrder().getReturnOrderCode();
  }

  @Override
  public String getOutCode() {
    return this.returnOrder.getReturnOrderId();
  }

  @Override
  public String getWarehouseCode() {
    return this.getReturnOrder().getWarehouseCode();
  }

  @Override
  public String getOutBizCode() {
    return this.getReturnOrder().getOutBizCode();
  }

  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;
  @XmlElementWrapper(name = "orderLines")
  @XmlElement(name = "orderLine")
  private List<OmsReturnOrderOrderLine> orderLines;
  @XmlElement(name = "returnOrder")
  private OmsReturnOrder returnOrder;

  public Map<String, String> getExtendProps() {
    return extendProps;
  }

  public void setExtendProps(Map<String, String> extendProps) {
    this.extendProps = extendProps;
  }

  public List<OmsReturnOrderOrderLine> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(List<OmsReturnOrderOrderLine> orderLines) {
    this.orderLines = orderLines;
  }

  public OmsReturnOrder getReturnOrder() {
    return returnOrder;
  }

  public void setReturnOrder(OmsReturnOrder returnOrder) {
    this.returnOrder = returnOrder;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "returnOrder")
  public static class OmsReturnOrder {

    private String actionType;
    private String buyerNick;
    private String earliestArrivalTime;
    private String expressCode;
    private String logisticsCode;
    private String logisticsName;
    private String orderConfirmTime;
    private String orderFlag;
    private String orderType;
    private String outBizCode;
    private String ownerCode;
    private String planArrivalTime;
    private String preDeliveryOrderCode;
    private String preDeliveryOrderId;
    private String refOrderCode;
    private String remark;
    private String returnOrderCode;
    private String returnOrderId;
    private String returnOrderStatus;
    private String returnReason;
    private ReturnorderConfirmRequest.SenderInfo senderInfo;
    private String sourceOrderCode;
    private String supplierCode;
    private String supplierName;
    private String warehouseCode;

    public String getActionType() {
      return this.actionType;
    }

    public void setActionType(String actionType) {
      this.actionType = actionType;
    }

    public String getBuyerNick() {
      return this.buyerNick;
    }

    public void setBuyerNick(String buyerNick) {
      this.buyerNick = buyerNick;
    }

    public String getEarliestArrivalTime() {
      return this.earliestArrivalTime;
    }

    public void setEarliestArrivalTime(String earliestArrivalTime) {
      this.earliestArrivalTime = earliestArrivalTime;
    }

    public String getExpressCode() {
      return this.expressCode;
    }

    public void setExpressCode(String expressCode) {
      this.expressCode = expressCode;
    }

    public String getLogisticsCode() {
      return this.logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
      this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
      return this.logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
      this.logisticsName = logisticsName;
    }

    public String getOrderConfirmTime() {
      return this.orderConfirmTime;
    }

    public void setOrderConfirmTime(String orderConfirmTime) {
      this.orderConfirmTime = orderConfirmTime;
    }

    public String getOrderFlag() {
      return this.orderFlag;
    }

    public void setOrderFlag(String orderFlag) {
      this.orderFlag = orderFlag;
    }

    public String getOrderType() {
      return this.orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getOutBizCode() {
      return this.outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
      this.outBizCode = outBizCode;
    }

    public String getOwnerCode() {
      return this.ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }

    public String getPlanArrivalTime() {
      return this.planArrivalTime;
    }

    public void setPlanArrivalTime(String planArrivalTime) {
      this.planArrivalTime = planArrivalTime;
    }

    public String getPreDeliveryOrderCode() {
      return this.preDeliveryOrderCode;
    }

    public void setPreDeliveryOrderCode(String preDeliveryOrderCode) {
      this.preDeliveryOrderCode = preDeliveryOrderCode;
    }

    public String getPreDeliveryOrderId() {
      return this.preDeliveryOrderId;
    }

    public void setPreDeliveryOrderId(String preDeliveryOrderId) {
      this.preDeliveryOrderId = preDeliveryOrderId;
    }

    public String getRefOrderCode() {
      return this.refOrderCode;
    }

    public void setRefOrderCode(String refOrderCode) {
      this.refOrderCode = refOrderCode;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getReturnOrderCode() {
      return this.returnOrderCode;
    }

    public void setReturnOrderCode(String returnOrderCode) {
      this.returnOrderCode = returnOrderCode;
    }

    public String getReturnOrderId() {
      return this.returnOrderId;
    }

    public void setReturnOrderId(String returnOrderId) {
      this.returnOrderId = returnOrderId;
    }

    public String getReturnOrderStatus() {
      return this.returnOrderStatus;
    }

    public void setReturnOrderStatus(String returnOrderStatus) {
      this.returnOrderStatus = returnOrderStatus;
    }

    public String getReturnReason() {
      return this.returnReason;
    }

    public void setReturnReason(String returnReason) {
      this.returnReason = returnReason;
    }

    public ReturnorderConfirmRequest.SenderInfo getSenderInfo() {
      return this.senderInfo;
    }

    public void setSenderInfo(ReturnorderConfirmRequest.SenderInfo senderInfo) {
      this.senderInfo = senderInfo;
    }

    public String getSourceOrderCode() {
      return this.sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
      this.sourceOrderCode = sourceOrderCode;
    }

    public String getSupplierCode() {
      return this.supplierCode;
    }

    public void setSupplierCode(String supplierCode) {
      this.supplierCode = supplierCode;
    }

    public String getSupplierName() {
      return this.supplierName;
    }

    public void setSupplierName(String supplierName) {
      this.supplierName = supplierName;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "orderLine")
  public static class OmsReturnOrderOrderLine {

    private String actualPrice;
    private String actualQty;
    private String amount;
    private String batchCode;
    private String color;
    private String deliveryOrderId;
    private String discount;
    private String discountAmount;
    private String discountPrice;
    private String exceptionQty;
    private String expireDate;
    private String extCode;
    private String inventoryType;
    private String itemCode;
    private String itemId;
    private String itemName;
    private String locationCode;
    private String moveInLocation;
    private String moveOutLocation;
    private String orderLineNo;
    private String orderSourceCode;
    private String outBizCode;
    private String ownerCode;
    private String payNo;
    private Long planQty;
    private String produceCode;
    private String productCode;
    private String productDate;
    private String purchasePrice;
    private String qrCode;
    private String quantity;
    private String referencePrice;
    private String remark;
    private String retailPrice;
    private String settlementAmount;
    private String size;
    private String skuProperty;
    private String sourceOrderCode;
    private String standardAmount;
    private String standardPrice;
    private String status;
    private String stockInQty;
    private String stockOutQty;
    private String subDeliveryOrderId;
    private String subSourceCode;
    private String subSourceOrderCode;
    private String taobaoItemCode;
    private String warehouseCode;

    public String getActualPrice() {
      return this.actualPrice;
    }

    public void setActualPrice(String actualPrice) {
      this.actualPrice = actualPrice;
    }

    public String getActualQty() {
      return this.actualQty;
    }

    public void setActualQty(String actualQty) {
      this.actualQty = actualQty;
    }

    public String getAmount() {
      return this.amount;
    }

    public void setAmount(String amount) {
      this.amount = amount;
    }

    public String getBatchCode() {
      return this.batchCode;
    }

    public void setBatchCode(String batchCode) {
      this.batchCode = batchCode;
    }

    public String getColor() {
      return this.color;
    }

    public void setColor(String color) {
      this.color = color;
    }

    public String getDeliveryOrderId() {
      return this.deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
      this.deliveryOrderId = deliveryOrderId;
    }

    public String getDiscount() {
      return this.discount;
    }

    public void setDiscount(String discount) {
      this.discount = discount;
    }

    public String getDiscountAmount() {
      return this.discountAmount;
    }

    public void setDiscountAmount(String discountAmount) {
      this.discountAmount = discountAmount;
    }

    public String getDiscountPrice() {
      return this.discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
      this.discountPrice = discountPrice;
    }

    public String getExceptionQty() {
      return this.exceptionQty;
    }

    public void setExceptionQty(String exceptionQty) {
      this.exceptionQty = exceptionQty;
    }

    public String getExpireDate() {
      return this.expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
    }

    public String getExtCode() {
      return this.extCode;
    }

    public void setExtCode(String extCode) {
      this.extCode = extCode;
    }

    public String getInventoryType() {
      return this.inventoryType;
    }

    public void setInventoryType(String inventoryType) {
      this.inventoryType = inventoryType;
    }

    public String getItemCode() {
      return this.itemCode;
    }

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public String getItemId() {
      return this.itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public String getItemName() {
      return this.itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
    }

    public String getLocationCode() {
      return this.locationCode;
    }

    public void setLocationCode(String locationCode) {
      this.locationCode = locationCode;
    }

    public String getMoveInLocation() {
      return this.moveInLocation;
    }

    public void setMoveInLocation(String moveInLocation) {
      this.moveInLocation = moveInLocation;
    }

    public String getMoveOutLocation() {
      return this.moveOutLocation;
    }

    public void setMoveOutLocation(String moveOutLocation) {
      this.moveOutLocation = moveOutLocation;
    }

    public String getOrderLineNo() {
      return this.orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
      this.orderLineNo = orderLineNo;
    }

    public String getOrderSourceCode() {
      return this.orderSourceCode;
    }

    public void setOrderSourceCode(String orderSourceCode) {
      this.orderSourceCode = orderSourceCode;
    }

    public String getOutBizCode() {
      return this.outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
      this.outBizCode = outBizCode;
    }

    public String getOwnerCode() {
      return this.ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }

    public String getPayNo() {
      return this.payNo;
    }

    public void setPayNo(String payNo) {
      this.payNo = payNo;
    }

    public Long getPlanQty() {
      return this.planQty;
    }

    public void setPlanQty(Long planQty) {
      this.planQty = planQty;
    }

    public String getProduceCode() {
      return this.produceCode;
    }

    public void setProduceCode(String produceCode) {
      this.produceCode = produceCode;
    }

    public String getProductCode() {
      return this.productCode;
    }

    public void setProductCode(String productCode) {
      this.productCode = productCode;
    }

    public String getProductDate() {
      return this.productDate;
    }

    public void setProductDate(String productDate) {
      this.productDate = productDate;
    }

    public String getPurchasePrice() {
      return this.purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
      this.purchasePrice = purchasePrice;
    }

    public String getQrCode() {
      return this.qrCode;
    }

    public void setQrCode(String qrCode) {
      this.qrCode = qrCode;
    }

    public String getQuantity() {
      return this.quantity;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }

    public String getReferencePrice() {
      return this.referencePrice;
    }

    public void setReferencePrice(String referencePrice) {
      this.referencePrice = referencePrice;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getRetailPrice() {
      return this.retailPrice;
    }

    public void setRetailPrice(String retailPrice) {
      this.retailPrice = retailPrice;
    }

    public String getSettlementAmount() {
      return this.settlementAmount;
    }

    public void setSettlementAmount(String settlementAmount) {
      this.settlementAmount = settlementAmount;
    }

    public String getSize() {
      return this.size;
    }

    public void setSize(String size) {
      this.size = size;
    }

    public String getSkuProperty() {
      return this.skuProperty;
    }

    public void setSkuProperty(String skuProperty) {
      this.skuProperty = skuProperty;
    }

    public String getSourceOrderCode() {
      return this.sourceOrderCode;
    }

    public void setSourceOrderCode(String sourceOrderCode) {
      this.sourceOrderCode = sourceOrderCode;
    }

    public String getStandardAmount() {
      return this.standardAmount;
    }

    public void setStandardAmount(String standardAmount) {
      this.standardAmount = standardAmount;
    }

    public String getStandardPrice() {
      return this.standardPrice;
    }

    public void setStandardPrice(String standardPrice) {
      this.standardPrice = standardPrice;
    }

    public String getStatus() {
      return this.status;
    }

    public void setStatus(String status) {
      this.status = status;
    }

    public String getStockInQty() {
      return this.stockInQty;
    }

    public void setStockInQty(String stockInQty) {
      this.stockInQty = stockInQty;
    }

    public String getStockOutQty() {
      return this.stockOutQty;
    }

    public void setStockOutQty(String stockOutQty) {
      this.stockOutQty = stockOutQty;
    }

    public String getSubDeliveryOrderId() {
      return this.subDeliveryOrderId;
    }

    public void setSubDeliveryOrderId(String subDeliveryOrderId) {
      this.subDeliveryOrderId = subDeliveryOrderId;
    }

    public String getSubSourceCode() {
      return this.subSourceCode;
    }

    public void setSubSourceCode(String subSourceCode) {
      this.subSourceCode = subSourceCode;
    }

    public String getSubSourceOrderCode() {
      return this.subSourceOrderCode;
    }

    public void setSubSourceOrderCode(String subSourceOrderCode) {
      this.subSourceOrderCode = subSourceOrderCode;
    }

    public String getTaobaoItemCode() {
      return this.taobaoItemCode;
    }

    public void setTaobaoItemCode(String taobaoItemCode) {
      this.taobaoItemCode = taobaoItemCode;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }
  }
}
