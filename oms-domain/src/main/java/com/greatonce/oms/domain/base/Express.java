package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.enums.ExpressUse;
import com.greatonce.oms.domain.enums.CodType;
import com.greatonce.oms.domain.enums.WaybillType;
import com.greatonce.oms.domain.enums.ExpressType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 快递.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Express extends EnableDO {
  /**
   * 地址.
   */
  private String address;
  /**
   * 支持货到付款类型.
   */
  private CodType codType;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 快递编号.
   */
  private String expressCode;
  /**
   * 快递id.
   */
  private Long expressId;
  /**
   * 快递名称.
   */
  private String expressName;
  /**
   * 快递类型.
   */
  private ExpressType expressType;
  /**
   * 快递用途.
   */
  private ExpressUse expressUse;
  /**
   * 标准快递id.
   */
  private Long globalExpressId;
  /**
   * 全局快递名称.
   */
  private String globalExpressName;
  /**
   * 是否校验范围.
   */
  private Boolean checkRegion;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 排序.
   */
  private Integer orderId;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 电话.
   */
  private String telephone;
  /**
   * 电子面单设置.
   */
  private String waybillSetting;
  /**
   * 电子面单类型.
   */
  private WaybillType waybillType;

  /**
   * 电子面单配置.
   */
  private WaybillSetting setting;

  @Override
  public void setPrimaryKey(Long pk) {
    this.expressId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.expressId;
  }


  /**
   * 地址.
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   */
  public String getAddress() {
    return this.address;
  }

  /**
   * 支持货到付款类型.
   */
  public void setCodType(CodType codType) {
    this.codType = codType;
  }

  /**
   * 支持货到付款类型.
   */
  public CodType getCodType() {
    return this.codType;
  }


  /**
   * 创建时间.
   */
  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  /**
   * 快递编号.
   */
  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode == null ? null : expressCode.trim();
  }

  /**
   * 快递编号.
   */
  public String getExpressCode() {
    return this.expressCode;
  }


  /**
   * 快递id.
   */
  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  /**
   * 快递id.
   */
  public Long getExpressId() {
    return this.expressId;
  }

  /**
   * 快递名称.
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   */
  public String getExpressName() {
    return this.expressName;
  }

  /**
   * 快递类型.
   */
  public void setExpressType(ExpressType expressType) {
    this.expressType = expressType;
  }

  /**
   * 快递类型.
   */
  public ExpressType getExpressType() {
    return this.expressType;
  }

  /**
   * 快递用途.
   */
  public void setExpressUse(ExpressUse expressUse) {
    this.expressUse = expressUse;
  }

  /**
   * 快递用途.
   */
  public ExpressUse getExpressUse() {
    return this.expressUse;
  }


  /**
   * 标准快递id.
   */
  public void setGlobalExpressId(Long globalExpressId) {
    this.globalExpressId = globalExpressId;
  }

  /**
   * 标准快递id.
   */
  public Long getGlobalExpressId() {
    return this.globalExpressId;
  }

  /**
   * 全局快递名称.
   */
  public void setGlobalExpressName(String globalExpressName) {
    this.globalExpressName = globalExpressName == null ? null : globalExpressName.trim();
  }

  /**
   * 全局快递名称.
   */
  public String getGlobalExpressName() {
    return this.globalExpressName;
  }

  /**
   * 是否校验范围.
   */
  public void setCheckRegion(Boolean checkRegion) {
    this.checkRegion = checkRegion;
  }

  /**
   * 是否校验范围.
   */
  public Boolean isCheckRegion() {
    return this.checkRegion;
  }

  /**
   * 是否启用.
   */
  @Override
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  @Override
  public Boolean isEnable() {
    return this.enable;
  }

  /**
   * 更新时间.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * 排序.
   */
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  /**
   * 排序.
   */
  public Integer getOrderId() {
    return this.orderId;
  }

  /**
   * 备注.
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   */
  public String getRemark() {
    return this.remark;
  }

  /**
   * 电话.
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   */
  public String getTelephone() {
    return this.telephone;
  }

  /**
   * 电子面单设置.
   */
  public void setWaybillSetting(String waybillSetting) {
    this.waybillSetting = waybillSetting == null ? null : waybillSetting.trim();
  }

  /**
   * 电子面单设置.
   */
  public String getWaybillSetting() {
    return this.waybillSetting;
  }

  /**
   * 电子面单类型.
   */
  public void setWaybillType(WaybillType waybillType) {
    this.waybillType = waybillType;
  }

  /**
   * 电子面单类型.
   */
  public WaybillType getWaybillType() {
    return this.waybillType;
  }

  /**
   * 电子面单配置.
   */
  public void setSetting(WaybillSetting setting) {
    this.setting = setting;
  }

  /**
   * 电子面单配置.
   */
  public WaybillSetting getSetting() {
    return this.setting;
  }
}