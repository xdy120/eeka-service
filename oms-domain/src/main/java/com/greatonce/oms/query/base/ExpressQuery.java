package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class ExpressQuery extends Query {
  /**
   * 地址.
   */
  private String address;
  /**
   * 支持货到付款类型.
   */
  private CodType codType;
  /**
   * .
   */
  private List<CodType> codTypes;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 快递编号.
   */
  private String expressCode;
  /**
   * .
   */
  private List<String> expressCodes;
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
   * .
   */
  private List<ExpressUse> expressUses;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 地址.
   * @param address 地址
   */
  public void setAddress(String address) {
    this.address = address == null ? null : address.trim();
  }

  /**
   * 地址.
   * @return 地址
   */
  public String getAddress() {
      return this.address;
  }

  /**
   * 支持货到付款类型.
   * @param codType 支持货到付款类型
   */
  public void setCodType(CodType codType) {
    this.codType = codType;
  }

  /**
   * 支持货到付款类型.
   * @return 支持货到付款类型
   */
  public CodType getCodType() {
      return this.codType;
  }

  /**
   * .
   * @param codTypes 
   */
  public void setCodTypes(List<CodType> codTypes) {
    this.codTypes = codTypes;
  }

  /**
   * .
   * @return 
   */
  public List<CodType> setCodTypes() {
      return this.codTypes;
  }

  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 快递编号.
   * @param expressCode 快递编号
   */
  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode == null ? null : expressCode.trim();
  }

  /**
   * 快递编号.
   * @return 快递编号
   */
  public String getExpressCode() {
      return this.expressCode;
  }

  /**
   * .
   * @param expressCodes 
   */
  public void setExpressCodes(List<String> expressCodes) {
    this.expressCodes = expressCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getExpressCodes() {
      return this.expressCodes;
  }

  /**
   * 快递id.
   * @param expressId 快递id
   */
  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  /**
   * 快递id.
   * @return 快递id
   */
  public Long getExpressId() {
      return this.expressId;
  }

  /**
   * 快递名称.
   * @param expressName 快递名称
   */
  public void setExpressName(String expressName) {
    this.expressName = expressName == null ? null : expressName.trim();
  }

  /**
   * 快递名称.
   * @return 快递名称
   */
  public String getExpressName() {
      return this.expressName;
  }

  /**
   * 快递类型.
   * @param expressType 快递类型
   */
  public void setExpressType(ExpressType expressType) {
    this.expressType = expressType;
  }

  /**
   * 快递类型.
   * @return 快递类型
   */
  public ExpressType getExpressType() {
      return this.expressType;
  }

  /**
   * 快递用途.
   * @param expressUse 快递用途
   */
  public void setExpressUse(ExpressUse expressUse) {
    this.expressUse = expressUse;
  }

  /**
   * 快递用途.
   * @return 快递用途
   */
  public ExpressUse getExpressUse() {
      return this.expressUse;
  }

  /**
   * .
   * @param expressUses 
   */
  public void setExpressUses(List<ExpressUse> expressUses) {
    this.expressUses = expressUses;
  }

  /**
   * .
   * @return 
   */
  public List<ExpressUse> getExpressUses() {
      return this.expressUses;
  }

  /**
   * 标准快递id.
   * @param globalExpressId 标准快递id
   */
  public void setGlobalExpressId(Long globalExpressId) {
    this.globalExpressId = globalExpressId;
  }

  /**
   * 标准快递id.
   * @return 标准快递id
   */
  public Long getGlobalExpressId() {
      return this.globalExpressId;
  }

  /**
   * 全局快递名称.
   * @param globalExpressName 全局快递名称
   */
  public void setGlobalExpressName(String globalExpressName) {
    this.globalExpressName = globalExpressName == null ? null : globalExpressName.trim();
  }

  /**
   * 全局快递名称.
   * @return 全局快递名称
   */
  public String getGlobalExpressName() {
      return this.globalExpressName;
  }

  /**
   * 是否校验范围.
   * @param checkRegion 是否校验范围
   */
  public void setCheckRegion(Boolean checkRegion) {
    this.checkRegion = checkRegion;
  }

  /**
   * 是否校验范围.
   * @return 是否校验范围
   */
  public Boolean isCheckRegion() {
      return this.checkRegion;
  }

  /**
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * 排序.
   * @param orderId 排序
   */
  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  /**
   * 排序.
   * @return 排序
   */
  public Integer getOrderId() {
      return this.orderId;
  }

  /**
   * 备注.
   * @param remark 备注
   */
  public void setRemark(String remark) {
    this.remark = remark == null ? null : remark.trim();
  }

  /**
   * 备注.
   * @return 备注
   */
  public String getRemark() {
      return this.remark;
  }

  /**
   * 电话.
   * @param telephone 电话
   */
  public void setTelephone(String telephone) {
    this.telephone = telephone == null ? null : telephone.trim();
  }

  /**
   * 电话.
   * @return 电话
   */
  public String getTelephone() {
      return this.telephone;
  }

  /**
   * 电子面单设置.
   * @param waybillSetting 电子面单设置
   */
  public void setWaybillSetting(String waybillSetting) {
    this.waybillSetting = waybillSetting == null ? null : waybillSetting.trim();
  }

  /**
   * 电子面单设置.
   * @return 电子面单设置
   */
  public String getWaybillSetting() {
      return this.waybillSetting;
  }

  /**
   * 电子面单类型.
   * @param waybillType 电子面单类型
   */
  public void setWaybillType(WaybillType waybillType) {
    this.waybillType = waybillType;
  }

  /**
   * 电子面单类型.
   * @return 电子面单类型
   */
  public WaybillType getWaybillType() {
      return this.waybillType;
  }
}