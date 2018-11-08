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

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsEntryOrderConfirmRequest implements OmsBaseQimenRequest {

  private OmsEntryOrder entryOrder;
  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;
  @XmlElementWrapper(name = "orderLines")
  @XmlElement(name = "orderLine")
  private List<OmsEntryOrderOrderLine> orderLines;

  public OmsEntryOrder getEntryOrder() {
    return entryOrder;
  }

  public void setEntryOrder(OmsEntryOrder entryOrder) {
    this.entryOrder = entryOrder;
  }

  public Map<String, String> getExtendProps() {
    return extendProps;
  }

  public void setExtendProps(Map<String, String> extendProps) {
    this.extendProps = extendProps;
  }

  public List<OmsEntryOrderOrderLine> getOrderLines() {
    return orderLines;
  }

  public void setOrderLines(List<OmsEntryOrderOrderLine> orderLines) {
    this.orderLines = orderLines;
  }

  @Override
  public String getOwnerCode() {
    return this.entryOrder.getOwnerCode();
  }

  @Override
  public String getOrderCode() {
    return this.entryOrder.getEntryOrderCode();
  }

  @Override
  public String getOutCode() {
    return this.entryOrder.getEntryOrderId();
  }

  @Override
  public String getWarehouseCode() {
    return this.entryOrder.getWarehouseCode();
  }

  @Override
  public String getOutBizCode() {
    return this.entryOrder.getOutBizCode();
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


  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "orderLine")
  public static class OmsEntryOrderOrderLine {

    private Long actualQty;
    private String inventoryType;
    private String itemCode;
    private String itemId;
    private String itemName;
    private String orderLineNo;
    private String ownerCode;
    private String stockInQty;
    private String warehouseCode;

    public Long getActualQty() {
      return actualQty;
    }

    public void setActualQty(Long actualQty) {
      this.actualQty = actualQty;
    }

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

    public String getItemName() {
      return itemName;
    }

    public void setItemName(String itemName) {
      this.itemName = itemName;
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

    public String getStockInQty() {
      return stockInQty;
    }

    public void setStockInQty(String stockInQty) {
      this.stockInQty = stockInQty;
    }

    public String getWarehouseCode() {
      return warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }
  }

}
