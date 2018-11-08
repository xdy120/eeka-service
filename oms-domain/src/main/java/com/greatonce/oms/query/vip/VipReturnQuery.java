package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.vip.VipReturnStatus;
import com.greatonce.oms.domain.enums.vip.VipSignStatus;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品退供单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipReturnQuery extends Query {
  /**
   * 箱数.
   */
  private Integer boxQuantity;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 创建人.
   */
  private String creator;
  /**
   * 是否异常.
   */
  private Boolean abnormal;
  /**
   * 唯品价异常.
   */
  private Boolean vipPriceAbnormal;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * .
   */
  private List<String> outerCodes;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 退货时间开始.
   */
  private LocalDateTime returnTimeBegin;
  /**
   * 退货时间结束.
   */
  private LocalDateTime returnTimeEnd;
  /**
   * 扫描数量.
   */
  private Integer scanQuantity;
  /**
   * 签收时间开始.
   */
  private LocalDateTime signTimeBegin;
  /**
   * 签收时间结束.
   */
  private LocalDateTime signTimeEnd;
  /**
   * 签收人.
   */
  private String signer;
  /**
   * 规格数量.
   */
  private Integer skuQuantity;
  /**
   * 状态.
   */
  private VipReturnStatus status;
  /**
   * .
   */
  private List<VipReturnStatus> statuses;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * .
   */
  private List<Long> storeIds;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 金额.
   */
  private Double vipAmount;
  /**
   * 唯品退供编码.
   */
  private String vipReturnCode;
  /**
   * .
   */
  private List<String> vipReturnCodes;
  /**
   * 唯品退供单id.
   */
  private Long vipReturnId;
  /**
   * 唯品退货类型.
   */
  private String vipReturnType;
  /**
   * 唯品签收类型.
   */
  private VipSignStatus vipSignStatus;
  /**
   * .
   */
  private List<VipSignStatus> vipSignStatuses;
  /**
   * 唯品仓编码.
   */
  private String vipWarehouseCode;
  /**
   * 唯品仓名称.
   */
  private String vipWarehouseName;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;
  /**
   * 虚拟仓名称.
   */
  private String virtualWarehouseName;


  /**
   * 箱数.
   * @param boxQuantity 箱数
   */
  public void setBoxQuantity(Integer boxQuantity) {
    this.boxQuantity = boxQuantity;
  }

  /**
   * 箱数.
   * @return 箱数
   */
  public Integer getBoxQuantity() {
      return this.boxQuantity;
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
   * 创建人.
   * @param creator 创建人
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   * @return 创建人
   */
  public String getCreator() {
      return this.creator;
  }

  /**
   * 是否异常.
   * @param abnormal 是否异常
   */
  public void setAbnormal(Boolean abnormal) {
    this.abnormal = abnormal;
  }

  /**
   * 是否异常.
   * @return 是否异常
   */
  public Boolean isAbnormal() {
      return this.abnormal;
  }

  /**
   * 唯品价异常.
   * @param vipPriceAbnormal 唯品价异常
   */
  public void setVipPriceAbnormal(Boolean vipPriceAbnormal) {
    this.vipPriceAbnormal = vipPriceAbnormal;
  }

  /**
   * 唯品价异常.
   * @return 唯品价异常
   */
  public Boolean isVipPriceAbnormal() {
      return this.vipPriceAbnormal;
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
   * 外部单号.
   * @param outerCode 外部单号
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   * @return 外部单号
   */
  public String getOuterCode() {
      return this.outerCode;
  }

  /**
   * .
   * @param outerCodes 
   */
  public void setOuterCodes(List<String> outerCodes) {
    this.outerCodes = outerCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getOuterCodes() {
      return this.outerCodes;
  }

  /**
   * 数量.
   * @param quantity 数量
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   * @return 数量
   */
  public Integer getQuantity() {
      return this.quantity;
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
   * 退货时间开始.
   * @param returnTimeBegin 开始.
   */
  public void setReturnTimeBegin(LocalDateTime returnTimeBegin) {
    this.returnTimeBegin = returnTimeBegin;
  }

  /**
   * 退货时间开始.
   * @return 退货时间开始
   */
  public LocalDateTime getReturnTimeBegin() {
    return this.returnTimeBegin;
  }

  /**
   * 退货时间结束.
   * @param returnTimeEnd 结束
   */
  public void setReturnTimeEnd(LocalDateTime returnTimeEnd) {
    this.returnTimeEnd = returnTimeEnd;
  }

  /**
   * 退货时间结束.
   * @return 退货时间结束
   */
  public LocalDateTime getReturnTimeEnd() {
    return this.returnTimeEnd;
  }

  /**
   * 扫描数量.
   * @param scanQuantity 扫描数量
   */
  public void setScanQuantity(Integer scanQuantity) {
    this.scanQuantity = scanQuantity;
  }

  /**
   * 扫描数量.
   * @return 扫描数量
   */
  public Integer getScanQuantity() {
      return this.scanQuantity;
  }

  /**
   * 签收时间开始.
   * @param signTimeBegin 开始.
   */
  public void setSignTimeBegin(LocalDateTime signTimeBegin) {
    this.signTimeBegin = signTimeBegin;
  }

  /**
   * 签收时间开始.
   * @return 签收时间开始
   */
  public LocalDateTime getSignTimeBegin() {
    return this.signTimeBegin;
  }

  /**
   * 签收时间结束.
   * @param signTimeEnd 结束
   */
  public void setSignTimeEnd(LocalDateTime signTimeEnd) {
    this.signTimeEnd = signTimeEnd;
  }

  /**
   * 签收时间结束.
   * @return 签收时间结束
   */
  public LocalDateTime getSignTimeEnd() {
    return this.signTimeEnd;
  }

  /**
   * 签收人.
   * @param signer 签收人
   */
  public void setSigner(String signer) {
    this.signer = signer == null ? null : signer.trim();
  }

  /**
   * 签收人.
   * @return 签收人
   */
  public String getSigner() {
      return this.signer;
  }

  /**
   * 规格数量.
   * @param skuQuantity 规格数量
   */
  public void setSkuQuantity(Integer skuQuantity) {
    this.skuQuantity = skuQuantity;
  }

  /**
   * 规格数量.
   * @return 规格数量
   */
  public Integer getSkuQuantity() {
      return this.skuQuantity;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(VipReturnStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public VipReturnStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<VipReturnStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipReturnStatus> getStatuses() {
      return this.statuses;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }

  /**
   * .
   * @param storeIds 
   */
  public void setStoreIds(List<Long> storeIds) {
    this.storeIds = storeIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getStoreIds() {
      return this.storeIds;
  }

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
  }

  /**
   * 版本.
   * @param version 版本
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   * @return 版本
   */
  public Integer getVersion() {
      return this.version;
  }

  /**
   * 金额.
   * @param vipAmount 金额
   */
  public void setVipAmount(Double vipAmount) {
    this.vipAmount = vipAmount;
  }

  /**
   * 金额.
   * @return 金额
   */
  public Double getVipAmount() {
      return this.vipAmount;
  }

  /**
   * 唯品退供编码.
   * @param vipReturnCode 唯品退供编码
   */
  public void setVipReturnCode(String vipReturnCode) {
    this.vipReturnCode = vipReturnCode == null ? null : vipReturnCode.trim();
  }

  /**
   * 唯品退供编码.
   * @return 唯品退供编码
   */
  public String getVipReturnCode() {
      return this.vipReturnCode;
  }

  /**
   * .
   * @param vipReturnCodes 
   */
  public void setVipReturnCodes(List<String> vipReturnCodes) {
    this.vipReturnCodes = vipReturnCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getVipReturnCodes() {
      return this.vipReturnCodes;
  }

  /**
   * 唯品退供单id.
   * @param vipReturnId 唯品退供单id
   */
  public void setVipReturnId(Long vipReturnId) {
    this.vipReturnId = vipReturnId;
  }

  /**
   * 唯品退供单id.
   * @return 唯品退供单id
   */
  public Long getVipReturnId() {
      return this.vipReturnId;
  }

  /**
   * 唯品退货类型.
   * @param vipReturnType 唯品退货类型
   */
  public void setVipReturnType(String vipReturnType) {
    this.vipReturnType = vipReturnType == null ? null : vipReturnType.trim();
  }

  /**
   * 唯品退货类型.
   * @return 唯品退货类型
   */
  public String getVipReturnType() {
      return this.vipReturnType;
  }

  /**
   * 唯品签收类型.
   * @param vipSignStatus 唯品签收类型
   */
  public void setVipSignStatus(VipSignStatus vipSignStatus) {
    this.vipSignStatus = vipSignStatus;
  }

  /**
   * 唯品签收类型.
   * @return 唯品签收类型
   */
  public VipSignStatus getVipSignStatus() {
      return this.vipSignStatus;
  }

  /**
   * .
   * @param vipSignStatuses 
   */
  public void setVipSignStatuses(List<VipSignStatus> vipSignStatuses) {
    this.vipSignStatuses = vipSignStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipSignStatus> getVipSignStatuses() {
      return this.vipSignStatuses;
  }

  /**
   * 唯品仓编码.
   * @param vipWarehouseCode 唯品仓编码
   */
  public void setVipWarehouseCode(String vipWarehouseCode) {
    this.vipWarehouseCode = vipWarehouseCode == null ? null : vipWarehouseCode.trim();
  }

  /**
   * 唯品仓编码.
   * @return 唯品仓编码
   */
  public String getVipWarehouseCode() {
      return this.vipWarehouseCode;
  }

  /**
   * 唯品仓名称.
   * @param vipWarehouseName 唯品仓名称
   */
  public void setVipWarehouseName(String vipWarehouseName) {
    this.vipWarehouseName = vipWarehouseName == null ? null : vipWarehouseName.trim();
  }

  /**
   * 唯品仓名称.
   * @return 唯品仓名称
   */
  public String getVipWarehouseName() {
      return this.vipWarehouseName;
  }

  /**
   * 虚拟仓id.
   * @param virtualWarehouseId 虚拟仓id
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   * @return 虚拟仓id
   */
  public Long getVirtualWarehouseId() {
      return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   * @param virtualWarehouseName 虚拟仓名称
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   * @return 虚拟仓名称
   */
  public String getVirtualWarehouseName() {
      return this.virtualWarehouseName;
  }
}