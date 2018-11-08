package com.greatonce.oms.bridge.wms.qimen.request;

import com.greatonce.core.supports.xml.JaxbMapAdapter;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * OmsDeliveryorderConfirmRequest
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/6
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsDeliveryOrderConfirmRequest implements OmsBaseQimenRequest {

  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;
  private OmsDeliveryOrder deliveryOrder;
  @XmlElementWrapper(name = "orderLines")
  @XmlElement(name = "orderLine")
  private List<OmsDeliveryOrderOrderLine> orderLines;
  @XmlElementWrapper(name = "packages")
  @XmlElement(name = "package")
  private List<OmsDeliveryOrderPackage> packages;

  @Override
  public String getOwnerCode() {
    return this.getDeliveryOrder().getOwnerCode();
  }

  @Override
  public String getOrderCode() {
    return this.getDeliveryOrder().getDeliveryOrderCode();
  }

  @Override
  public String getOutCode() {
    return this.deliveryOrder.getDeliveryOrderId();
  }

  @Override
  public String getWarehouseCode() {
    return this.getDeliveryOrder().getWarehouseCode();
  }

  @Override
  public String getOutBizCode() {
    return this.getDeliveryOrder().getOutBizCode();
  }

  public OmsDeliveryOrder getDeliveryOrder() {
    return deliveryOrder;
  }

  public void setDeliveryOrder(OmsDeliveryOrder deliveryOrder) {
    this.deliveryOrder = deliveryOrder;
  }

  public Map<String, String> getExtendProps() {
    return extendProps;
  }

  public void setExtendProps(Map<String, String> extendProps) {
    this.extendProps = extendProps;
  }

  public List<OmsDeliveryOrderOrderLine> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(List<OmsDeliveryOrderOrderLine> orderLines) {
    this.orderLines = orderLines;
  }

  public List<OmsDeliveryOrderPackage> getPackages() {
    return packages;
  }

  public void setPackages(List<OmsDeliveryOrderPackage> packages) {
    this.packages = packages;
  }


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "deliveryOrder")
  public static class OmsDeliveryOrder {

    private Long confirmType;
    private String deliveryOrderCode;
    private String deliveryOrderId;
    private String expressCode;
    private String invoiceFlag;
    private String logisticsCode;
    private String logisticsName;
    private String operateTime;
    private String operatorName;
    private String orderConfirmTime;
    private String orderType;
    private String outBizCode;
    private String ownerCode;
    private String warehouseCode;

    @XmlElementWrapper(name = "orderLines")
    @XmlElement(name = "orderLine")
    private List<OmsDeliveryOrderOrderLine> orderLines;

    public Long getConfirmType() {
      return confirmType;
    }

    public void setConfirmType(Long confirmType) {
      this.confirmType = confirmType;
    }

    public String getDeliveryOrderCode() {
      return deliveryOrderCode;
    }

    public void setDeliveryOrderCode(String deliveryOrderCode) {
      this.deliveryOrderCode = deliveryOrderCode;
    }

    public String getDeliveryOrderId() {
      return deliveryOrderId;
    }

    public void setDeliveryOrderId(String deliveryOrderId) {
      this.deliveryOrderId = deliveryOrderId;
    }

    public String getExpressCode() {
      return expressCode;
    }

    public void setExpressCode(String expressCode) {
      this.expressCode = expressCode;
    }

    public String getInvoiceFlag() {
      return invoiceFlag;
    }

    public void setInvoiceFlag(String invoiceFlag) {
      this.invoiceFlag = invoiceFlag;
    }

    public String getLogisticsCode() {
      return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
      this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
      return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
      this.logisticsName = logisticsName;
    }

    public String getOperateTime() {
      return operateTime;
    }

    public void setOperateTime(String operateTime) {
      this.operateTime = operateTime;
    }

    public String getOperatorName() {
      return operatorName;
    }

    public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
    }

    public String getOrderConfirmTime() {
      return orderConfirmTime;
    }

    public void setOrderConfirmTime(String orderConfirmTime) {
      this.orderConfirmTime = orderConfirmTime;
    }

    public List<OmsDeliveryOrderOrderLine> getOrderLines() {
      return orderLines;
    }

    public void setOrderLines(List<OmsDeliveryOrderOrderLine> orderLines) {
      this.orderLines = orderLines;
    }

    public String getOrderType() {
      return orderType;
    }

    public void setOrderType(String orderType) {
      this.orderType = orderType;
    }

    public String getOutBizCode() {
      return outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
      this.outBizCode = outBizCode;
    }

    public String getOwnerCode() {
      return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }

    public String getWarehouseCode() {
      return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }
  }


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "package")
  public static class OmsDeliveryOrderPackage {

    private String logisticsCode;
    private String expressCode;
    private double weight;
    @XmlElementWrapper(name = "items")
    @XmlElement(name = "item")
    private List<OmsDeliveryOrderPackageItem> items;

    public String getLogisticsCode() {
      return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
      this.logisticsCode = logisticsCode;
    }

    public String getExpressCode() {
      return expressCode;
    }

    public void setExpressCode(String expressCode) {
      this.expressCode = expressCode;
    }

    public double getWeight() {
      return weight;
    }

    public void setWeight(double weight) {
      this.weight = weight;
    }

    public List<OmsDeliveryOrderPackageItem> getItems() {
      return items;
    }

    public void setItems(List<OmsDeliveryOrderPackageItem> items) {
      this.items = items;
    }
  }


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "item")
  public static class OmsDeliveryOrderPackageItem {

    private String itemCode;
    private String itemId;
    private String quantity;

    public String getItemCode() {
      return itemCode;
    }

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public String getItemId() {
      return itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public String getQuantity() {
      return quantity;
    }

    public void setQuantity(String quantity) {
      this.quantity = quantity;
    }
  }


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "orderLine")
  public static class OmsDeliveryOrderOrderLine {

    private String inventoryType;
    private String itemCode;
    private String itemId;
    private String orderLineNo;
    private String ownerCode;
    private String actualQty;
    private String stockOutQty;

    public String getInventoryType() {
      return inventoryType;
    }

    public void setInventoryType(String inventoryType) {
      this.inventoryType = inventoryType;
    }

    public String getItemCode() {
      return itemCode;
    }

    public void setItemCode(String itemCode) {
      this.itemCode = itemCode;
    }

    public String getItemId() {
      return itemId;
    }

    public void setItemId(String itemId) {
      this.itemId = itemId;
    }

    public String getOrderLineNo() {
      return orderLineNo;
    }

    public void setOrderLineNo(String orderLineNo) {
      this.orderLineNo = orderLineNo;
    }

    public String getOwnerCode() {
      return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }

    public String getActualQty() {
      return actualQty;
    }

    public void setActualQty(String actualQty) {
      this.actualQty = actualQty;
    }

    public String getStockOutQty() {
      return stockOutQty;
    }

    public void setStockOutQty(String stockOutQty) {
      this.stockOutQty = stockOutQty;
    }
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "entryOrder")
  public static class OmsEntryOrder {

    private String remark;
    private String ownerCode;
    private Long confirmType;
    private String entryOrderCode;
    private String entryOrderId;
    private String entryOrderType;
    private String expressCode;
    private String logisticsCode;
    private String logisticsName;
    private String operateTime;
    private String operatorName;
    private String outBizCode;
    private String warehouseCode;
    private String warehouseName;

    public Long getConfirmType() {
      return confirmType;
    }

    public void setConfirmType(Long confirmType) {
      this.confirmType = confirmType;
    }

    public String getEntryOrderCode() {
      return entryOrderCode;
    }

    public void setEntryOrderCode(String entryOrderCode) {
      this.entryOrderCode = entryOrderCode;
    }

    public String getEntryOrderId() {
      return entryOrderId;
    }

    public void setEntryOrderId(String entryOrderId) {
      this.entryOrderId = entryOrderId;
    }

    public String getEntryOrderType() {
      return entryOrderType;
    }

    public void setEntryOrderType(String entryOrderType) {
      this.entryOrderType = entryOrderType;
    }

    public String getExpressCode() {
      return expressCode;
    }

    public void setExpressCode(String expressCode) {
      this.expressCode = expressCode;
    }

    public String getLogisticsCode() {
      return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
      this.logisticsCode = logisticsCode;
    }

    public String getLogisticsName() {
      return logisticsName;
    }

    public void setLogisticsName(String logisticsName) {
      this.logisticsName = logisticsName;
    }

    public String getOperateTime() {
      return operateTime;
    }

    public void setOperateTime(String operateTime) {
      this.operateTime = operateTime;
    }

    public String getOperatorName() {
      return operatorName;
    }

    public void setOperatorName(String operatorName) {
      this.operatorName = operatorName;
    }

    public String getOutBizCode() {
      return outBizCode;
    }

    public void setOutBizCode(String outBizCode) {
      this.outBizCode = outBizCode;
    }

    public String getWarehouseCode() {
      return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }

    public String getWarehouseName() {
      return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
      this.warehouseName = warehouseName;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getOwnerCode() {
      return ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }
  }
}
