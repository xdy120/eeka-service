package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.vip.VipReturnNoticeStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品退供通知单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipReturnNoticeQuery extends Query {
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
   * 入库正品数量.
   */
  private Integer inQuantity;
  /**
   * 入库状态.
   */
  private InStatus inStatus;
  /**
   * .
   */
  private List<InStatus> inStatuses;
  /**
   * 唯品价异常.
   */
  private Boolean vipPriceAbnormal;
  /**
   * 最后入库时间开始.
   */
  private LocalDateTime lastInTimeBegin;
  /**
   * 最后入库时间结束.
   */
  private LocalDateTime lastInTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 通知数量.
   */
  private Integer noticeQuantity;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 过账状态.
   */
  private PostStatus postStatus;
  /**
   * 备注.
   */
  private String remark;
  /**
   * .
   */
  private List<String> skuCodes;
  /**
   * 状态.
   */
  private VipReturnNoticeStatus status;
  /**
   * .
   */
  private List<VipReturnNoticeStatus> statuses;
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
   * .
   */
  private List<String> vipReturnCodes;
  /**
   * 唯品通知单编码.
   */
  private String vipReturnNoticeCode;
  /**
   * .
   */
  private List<String> vipReturnNoticeCodes;
  /**
   * 唯品通知单id.
   */
  private Long vipReturnNoticeId;
  /**
   * 唯品仓编码.
   */
  private String vipWarehouseCode;
  /**
   * 唯品仓名称.
   */
  private String vipWarehouseName;
  /**
   * 实体仓id.
   */
  private Long warehouseId;
  /**
   * 实体仓名称.
   */
  private String warehouseName;


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
   * 入库正品数量.
   * @param inQuantity 入库正品数量
   */
  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  /**
   * 入库正品数量.
   * @return 入库正品数量
   */
  public Integer getInQuantity() {
      return this.inQuantity;
  }

  /**
   * 入库状态.
   * @param inStatus 入库状态
   */
  public void setInStatus(InStatus inStatus) {
    this.inStatus = inStatus;
  }

  /**
   * 入库状态.
   * @return 入库状态
   */
  public InStatus getInStatus() {
      return this.inStatus;
  }

  /**
   * .
   * @param inStatuses 
   */
  public void setInStatuses(List<InStatus> inStatuses) {
    this.inStatuses = inStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<InStatus> getInStatuses() {
      return this.inStatuses;
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
   * 最后入库时间开始.
   * @param lastInTimeBegin 开始.
   */
  public void setLastInTimeBegin(LocalDateTime lastInTimeBegin) {
    this.lastInTimeBegin = lastInTimeBegin;
  }

  /**
   * 最后入库时间开始.
   * @return 最后入库时间开始
   */
  public LocalDateTime getLastInTimeBegin() {
    return this.lastInTimeBegin;
  }

  /**
   * 最后入库时间结束.
   * @param lastInTimeEnd 结束
   */
  public void setLastInTimeEnd(LocalDateTime lastInTimeEnd) {
    this.lastInTimeEnd = lastInTimeEnd;
  }

  /**
   * 最后入库时间结束.
   * @return 最后入库时间结束
   */
  public LocalDateTime getLastInTimeEnd() {
    return this.lastInTimeEnd;
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
   * 通知数量.
   * @param noticeQuantity 通知数量
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   * @return 通知数量
   */
  public Integer getNoticeQuantity() {
      return this.noticeQuantity;
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
   * 过账状态.
   * @param postStatus 过账状态
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   * @return 过账状态
   */
  public PostStatus getPostStatus() {
      return this.postStatus;
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
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
  }

  /**
   * 状态.
   * @param status 状态
   */
  public void setStatus(VipReturnNoticeStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public VipReturnNoticeStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<VipReturnNoticeStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipReturnNoticeStatus> getStatuses() {
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
   * 唯品通知单编码.
   * @param vipReturnNoticeCode 唯品通知单编码
   */
  public void setVipReturnNoticeCode(String vipReturnNoticeCode) {
    this.vipReturnNoticeCode = vipReturnNoticeCode == null ? null : vipReturnNoticeCode.trim();
  }

  /**
   * 唯品通知单编码.
   * @return 唯品通知单编码
   */
  public String getVipReturnNoticeCode() {
      return this.vipReturnNoticeCode;
  }

  /**
   * .
   * @param vipReturnNoticeCodes 
   */
  public void setVipReturnNoticeCodes(List<String> vipReturnNoticeCodes) {
    this.vipReturnNoticeCodes = vipReturnNoticeCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getVipReturnNoticeCodes() {
      return this.vipReturnNoticeCodes;
  }

  /**
   * 唯品通知单id.
   * @param vipReturnNoticeId 唯品通知单id
   */
  public void setVipReturnNoticeId(Long vipReturnNoticeId) {
    this.vipReturnNoticeId = vipReturnNoticeId;
  }

  /**
   * 唯品通知单id.
   * @return 唯品通知单id
   */
  public Long getVipReturnNoticeId() {
      return this.vipReturnNoticeId;
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
   * 实体仓id.
   * @param warehouseId 实体仓id
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 实体仓id.
   * @return 实体仓id
   */
  public Long getWarehouseId() {
      return this.warehouseId;
  }

  /**
   * 实体仓名称.
   * @param warehouseName 实体仓名称
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 实体仓名称.
   * @return 实体仓名称
   */
  public String getWarehouseName() {
      return this.warehouseName;
  }
}