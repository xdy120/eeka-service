package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 仓库发货BO.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/23
 */
public class WmsAutoOutBO extends VersionBO {

  /**
   * OMS单据号.
   */
  private String omsOrderCode;
  /**
   * WMS单据号.
   */
  private String wmsOrderCode;

  /**
   * WMS批次号.
   */
  private String wmsBatchCode;

  /**
   * 是否发货结束.
   */
  private Boolean deliveryFinish;

  /**
   * 发货仓库.
   */
  private String deliveryWarehouseCode;

  /**
   * 出库时间
   */
  private LocalDateTime outTime;

  /**
   * 出库包裹.
   */
  private List<WmsAutoOutPackage> packages;

  private List<WmsAutoOutDetail> details;

  public String getOmsOrderCode() {
    return omsOrderCode;
  }

  public void setOmsOrderCode(String omsOrderCode) {
    this.omsOrderCode = omsOrderCode;
  }

  public String getWmsOrderCode() {
    return wmsOrderCode;
  }

  public void setWmsOrderCode(String wmsOrderCode) {
    this.wmsOrderCode = wmsOrderCode;
  }

  public String getWmsBatchCode() {
    return wmsBatchCode;
  }

  public void setWmsBatchCode(String wmsBatchCode) {
    this.wmsBatchCode = wmsBatchCode;
  }

  public Boolean isDeliveryFinish() {
    return deliveryFinish;
  }

  public void setDeliveryFinish(Boolean deliveryFinish) {
    this.deliveryFinish = deliveryFinish;
  }

  public String getDeliveryWarehouseCode() {
    return deliveryWarehouseCode;
  }

  public void setDeliveryWarehouseCode(String deliveryWarehouseCode) {
    this.deliveryWarehouseCode = deliveryWarehouseCode;
  }

  public LocalDateTime getOutTime() {
    return outTime;
  }

  public void setOutTime(LocalDateTime outTime) {
    this.outTime = outTime;
  }

  public List<WmsAutoOutPackage> getPackages() {
    return packages;
  }

  public void setPackages(List<WmsAutoOutPackage> packages) {
    this.packages = packages;
  }

  public List<WmsAutoOutDetail> getDetails() {
    return details;
  }

  public void setDetails(List<WmsAutoOutDetail> details) {
    this.details = details;
  }

  /**
   * 发货包裹.
   */
  public static class WmsAutoOutPackage {

    private String wmsExpressCode;
    /**
     * 快递单号.
     */
    private String expressNo;
    /**
     * 发货时间.
     */
    private LocalDateTime deliveryTime;
    /**
     * 发货明细.
     */
    private List<WmsAutoOutDetail> details;

    public String getWmsExpressCode() {
      return wmsExpressCode;
    }

    public void setWmsExpressCode(String wmsExpressCode) {
      this.wmsExpressCode = wmsExpressCode;
    }

    public String getExpressNo() {
      return expressNo;
    }

    public void setExpressNo(String expressNo) {
      this.expressNo = expressNo;
    }

    public LocalDateTime getDeliveryTime() {
      return deliveryTime;
    }

    public void setDeliveryTime(LocalDateTime deliveryTime) {
      this.deliveryTime = deliveryTime;
    }

    public List<WmsAutoOutDetail> getDetails() {
      return details;
    }

    public void setDetails(List<WmsAutoOutDetail> details) {
      this.details = details;
    }
  }

  /**
   * 发货明细.
   */
  public static class WmsAutoOutDetail {

    /**
     * 规格编码.
     */
    private String skuCode;
    /**
     * 备注.
     */
    private String remark;
    /**
     * 箱码.
     */
    private String boxNo;
    /**
     * 入库数量.
     */
    private Integer outQuantity;

    public String getSkuCode() {
      return skuCode;
    }

    public void setSkuCode(String skuCode) {
      this.skuCode = skuCode;
    }

    public String getRemark() {
      return remark;
    }

    public void setRemark(String remark) {
      this.remark = remark;
    }

    public String getBoxNo() {
      return boxNo;
    }

    public void setBoxNo(String boxNo) {
      this.boxNo = boxNo;
    }

    public Integer getOutQuantity() {
      return outQuantity;
    }

    public void setOutQuantity(Integer outQuantity) {
      this.outQuantity = outQuantity;
    }
  }
}
