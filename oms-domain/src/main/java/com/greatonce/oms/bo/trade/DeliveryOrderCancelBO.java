package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.enums.OrderType;
import java.time.LocalDateTime;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by wangcf on 2018/4/18.
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class DeliveryOrderCancelBO {

  /**
   * 仓库编码
   */
  private String wareHouseCode;

  /**
   * 取消原因
   */
  private String reason;
  /**
   * 单据类型
   */
  private OrderType orderType;

  /**
   * oms系统单号
   */
  private String omsCode;
  /**
   * wms系统单号
   */
  private String wmsCode;
  /**
   * 上游系统单号
   */
  private String sourceCode;
  /**
   * 创建时间
   */
  private LocalDateTime createdTime;
  /**
   * 修改时间
   */
  private LocalDateTime modifiesTime;
  /**
   * 备注
   */
  private String remark;

  private String orderFlag;

  private String batchCode;

  public String getWareHouseCode() {
    return wareHouseCode;
  }

  public void setWareHouseCode(String wareHouseCode) {
    this.wareHouseCode = wareHouseCode;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }

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

  public LocalDateTime getModifiesTime() {
    return modifiesTime;
  }

  public void setModifiesTime(LocalDateTime modifiesTime) {
    this.modifiesTime = modifiesTime;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getOrderFlag() {
    return orderFlag;
  }

  public void setOrderFlag(String orderFlag) {
    this.orderFlag = orderFlag;
  }

  public String getBatchCode() {
    return batchCode;
  }

  public void setBatchCode(String batchCode) {
    this.batchCode = batchCode;
  }
}
