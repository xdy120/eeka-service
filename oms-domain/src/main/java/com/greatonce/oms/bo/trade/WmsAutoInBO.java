package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.enums.stock.StockType;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/23
 */
public class WmsAutoInBO extends VersionBO {
  /**
   * OMS单据号
   */
  private String omsOrderCode;
  /**
   * WMS单据号
   */
  private String wmsOrderCode;
  /**
   * WMS批次号
   */
  private String wmsBatchCode;
  /**
   * 入库时间
   */
  private LocalDateTime inTime;
  /**
   * 入库明细
   */
  private List<WmsAutoInDetail> details;

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

  public LocalDateTime getInTime() {
    return inTime;
  }

  public void setInTime(LocalDateTime inTime) {
    this.inTime = inTime;
  }

  public List<WmsAutoInDetail> getDetails() {
    return details;
  }

  public void setDetails(List<WmsAutoInDetail> details) {
    this.details = details;
  }

  public static class WmsAutoInDetail {

    /**
     * 规格ID
     */
    private Long skuId;
    /**
     * 规格编码
     */
    private String skuCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 入库数量
     */
    private Integer inQuantity;
    /**
     * 待处理数量
     */
    private Integer pendingQuantity;
    /**
     * 库存类型（正次品）
     */
    private StockType stockType;

    public Long getSkuId() {
      return skuId;
    }

    public void setSkuId(Long skuId) {
      this.skuId = skuId;
    }

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

    public Integer getInQuantity() {
      return inQuantity;
    }

    public void setInQuantity(Integer inQuantity) {
      this.inQuantity = inQuantity;
    }

    public Integer getPendingQuantity() {
      return pendingQuantity;
    }

    public void setPendingQuantity(Integer pendingQuantity) {
      this.pendingQuantity = pendingQuantity;
    }

    public StockType getStockType() {
      return stockType;
    }

    public void setStockType(StockType stockType) {
      this.stockType = stockType;
    }
  }
}
