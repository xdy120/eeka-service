package com.greatonce.oms.bridge.wms.qimen.request;

import com.greatonce.core.supports.xml.JaxbMapAdapter;
import com.greatonce.core.util.Assert;
import com.taobao.api.internal.mapping.ApiField;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * 库存盘点请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-03
 * @link taobao.qimen.inventory.report
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class OmsInventoryReportRequest implements OmsBaseQimenRequest {

  private String checkOrderCode;
  private String checkOrderId;
  private String checkTime;
  private Long currentPage;
  @XmlJavaTypeAdapter(value = JaxbMapAdapter.class)
  @XmlElement
  private Map<String, String> extendProps;
  @XmlElementWrapper(name = "items")
  @XmlElement(name = "item")
  private List<OmsInventoryReportItem> items;
  private String orderType;
  private String outBizCode;
  private String ownerCode;
  private Long pageSize;
  private String remark;
  private Long totalPage;
  private String warehouseCode;

  @Override
  public String getOrderCode() {
    return this.checkOrderCode;
  }

  @Override
  public String getOutCode() {
    return this.checkOrderId;
  }

  public void setCheckOrderCode(String checkOrderCode) {
    this.checkOrderCode = checkOrderCode;
  }

  public String getCheckOrderCode() {
    return this.checkOrderCode;
  }

  public void setCheckOrderId(String checkOrderId) {
    this.checkOrderId = checkOrderId;
  }

  public String getCheckOrderId() {
    return this.checkOrderId;
  }

  public void setCheckTime(String checkTime) {
    this.checkTime = checkTime;
  }

  public String getCheckTime() {
    return this.checkTime;
  }

  public void setCurrentPage(Long currentPage) {
    this.currentPage = currentPage;
  }

  public Long getCurrentPage() {
    return this.currentPage;
  }

  public void setExtendProps(Map<String, String>  extendProps) {
    this.extendProps = extendProps;
  }

  public Map<String, String>  getExtendProps() {
    return this.extendProps;
  }

  public void setItems(List<OmsInventoryReportItem> items) {
    this.items = items;
  }

  public List<OmsInventoryReportItem> getItems() {
    return this.items;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

  public String getOrderType() {
    return this.orderType;
  }

  public void setOutBizCode(String outBizCode) {
    this.outBizCode = outBizCode;
  }

  @Override
  public String getOutBizCode() {
    return Assert.isEmpty(this.outBizCode) ? this.checkOrderCode : this.outBizCode;
  }

  public void setOwnerCode(String ownerCode) {
    this.ownerCode = ownerCode;
  }

  @Override
  public String getOwnerCode() {
    return this.ownerCode;
  }

  public void setPageSize(Long pageSize) {
    this.pageSize = pageSize;
  }

  public Long getPageSize() {
    return this.pageSize;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getRemark() {
    return this.remark;
  }

  public void setTotalPage(Long totalPage) {
    this.totalPage = totalPage;
  }

  public Long getTotalPage() {
    return this.totalPage;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  @Override
  public String getWarehouseCode() {
    return this.warehouseCode;
  }

  @XmlAccessorType(XmlAccessType.FIELD)
  @XmlRootElement(name = "item")
  public static class OmsInventoryReportItem {

    @ApiField("batchCode")
    private String batchCode;
    @ApiField("expireDate")
    private String expireDate;
    @ApiField("inventoryType")
    private String inventoryType;
    @ApiField("itemCode")
    private String itemCode;
    @ApiField("itemId")
    private String itemId;
    @ApiField("ownerCode")
    private String ownerCode;
    @ApiField("produceCode")
    private String produceCode;
    @ApiField("productDate")
    private String productDate;
    @ApiField("quantity")
    private Long quantity;
    @ApiField("remark")
    private String remark;
    @ApiField("snCode")
    private String snCode;
    @ApiField("totalQty")
    private Long totalQty;
    @ApiField("warehouseCode")
    private String warehouseCode;

    public OmsInventoryReportItem() {
    }

    public String getBatchCode() {
      return this.batchCode;
    }

    public void setBatchCode(String batchCode) {
      this.batchCode = batchCode;
    }

    public String getExpireDate() {
      return this.expireDate;
    }

    public void setExpireDate(String expireDate) {
      this.expireDate = expireDate;
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

    public String getOwnerCode() {
      return this.ownerCode;
    }

    public void setOwnerCode(String ownerCode) {
      this.ownerCode = ownerCode;
    }

    public String getProduceCode() {
      return this.produceCode;
    }

    public void setProduceCode(String produceCode) {
      this.produceCode = produceCode;
    }

    public String getProductDate() {
      return this.productDate;
    }

    public void setProductDate(String productDate) {
      this.productDate = productDate;
    }

    public Long getQuantity() {
      return this.quantity;
    }

    public void setQuantity(Long quantity) {
      this.quantity = quantity;
    }

    public String getRemark() {
      return this.remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getSnCode() {
      return this.snCode;
    }

    public void setSnCode(String snCode) {
      this.snCode = snCode;
    }

    public Long getTotalQty() {
      return this.totalQty;
    }

    public void setTotalQty(Long totalQty) {
      this.totalQty = totalQty;
    }

    public String getWarehouseCode() {
      return this.warehouseCode;
    }

    public void setWarehouseCode(String warehouseCode) {
      this.warehouseCode = warehouseCode;
    }
  }
}
