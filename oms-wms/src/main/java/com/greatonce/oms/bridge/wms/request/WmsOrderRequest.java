package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OrderType;
import java.time.LocalDateTime;

/**
 * WMS单据请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/2
 */
public class WmsOrderRequest extends WmsRequest {

  public WmsOrderRequest(Warehouse warehouse) {
    super(warehouse);
  }

  /**
   * oms系统单号.
   */
  private String omsCode;
  /**
   * wms系统单号.
   */
  private String wmsCode;
  /**
   * 上游系统单号.
   */
  private String sourceCode;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 修改时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 订单类型.
   */
  private OrderType orderType;

  public String getOmsCode() {
    return omsCode;
  }

  public void setOmsCode(String omsCode) {
    this.omsCode = omsCode;
  }

  public String getWmsCode() {
    return wmsCode;
  }

  public void setWmsCode(String wmsCode) {
    this.wmsCode = wmsCode;
  }

  public String getSourceCode() {
    return sourceCode;
  }

  public void setSourceCode(String sourceCode) {
    this.sourceCode = sourceCode;
  }

  public LocalDateTime getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  public LocalDateTime getModifiedTime() {
    return modifiedTime;
  }

  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }
}
