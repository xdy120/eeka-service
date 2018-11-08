package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.vip.VipDispatchStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品配货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipDispatchQuery extends Query {
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
   * 最后出库时间开始.
   */
  private LocalDateTime lastOutTimeBegin;
  /**
   * 最后出库时间结束.
   */
  private LocalDateTime lastOutTimeEnd;
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
   * 出库数量.
   */
  private Integer outQuantity;
  /**
   * 出库状态.
   */
  private OutStatus outStatus;
  /**
   * .
   */
  private List<OutStatus> outStatuses;
  /**
   * 外部单号.
   */
  private String outerCode;
  /**
   * 拣货单号.
   */
  private String pickingCode;
  /**
   * .
   */
  private List<String> pickingCodes;
  /**
   * 过账状态.
   */
  private PostStatus postStatus;
  /**
   * 备注.
   */
  private String remark;
  /**
   * 状态.
   */
  private VipDispatchStatus status;
  /**
   * .
   */
  private List<VipDispatchStatus> statuses;
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
   * 发货单编码.
   */
  private String vipDeliveryCode;
  /**
   * .
   */
  private List<String> vipDeliveryCodes;
  /**
   * 发货单id.
   */
  private Long vipDeliveryId;
  /**
   * 配货单编码.
   */
  private String vipDispatchCode;
  /**
   * .
   */
  private List<String> vipDispatchCodes;
  /**
   * 配货单id.
   */
  private Long vipDispatchId;
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
   * 最后出库时间开始.
   * @param lastOutTimeBegin 开始.
   */
  public void setLastOutTimeBegin(LocalDateTime lastOutTimeBegin) {
    this.lastOutTimeBegin = lastOutTimeBegin;
  }

  /**
   * 最后出库时间开始.
   * @return 最后出库时间开始
   */
  public LocalDateTime getLastOutTimeBegin() {
    return this.lastOutTimeBegin;
  }

  /**
   * 最后出库时间结束.
   * @param lastOutTimeEnd 结束
   */
  public void setLastOutTimeEnd(LocalDateTime lastOutTimeEnd) {
    this.lastOutTimeEnd = lastOutTimeEnd;
  }

  /**
   * 最后出库时间结束.
   * @return 最后出库时间结束
   */
  public LocalDateTime getLastOutTimeEnd() {
    return this.lastOutTimeEnd;
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
   * 出库数量.
   * @param outQuantity 出库数量
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   * @return 出库数量
   */
  public Integer getOutQuantity() {
      return this.outQuantity;
  }

  /**
   * 出库状态.
   * @param outStatus 出库状态
   */
  public void setOutStatus(OutStatus outStatus) {
    this.outStatus = outStatus;
  }

  /**
   * 出库状态.
   * @return 出库状态
   */
  public OutStatus getOutStatus() {
      return this.outStatus;
  }

  /**
   * .
   * @param outStatuses 
   */
  public void setOutStatuses(List<OutStatus> outStatuses) {
    this.outStatuses = outStatuses;
  }

  /**
   * .
   * @return 
   */
  public List<OutStatus> getOutStatuses() {
      return this.outStatuses;
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
   * 拣货单号.
   * @param pickingCode 拣货单号
   */
  public void setPickingCode(String pickingCode) {
    this.pickingCode = pickingCode == null ? null : pickingCode.trim();
  }

  /**
   * 拣货单号.
   * @return 拣货单号
   */
  public String getPickingCode() {
      return this.pickingCode;
  }

  /**
   * .
   * @param pickingCodes 
   */
  public void setPickingCodes(List<String> pickingCodes) {
    this.pickingCodes = pickingCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getPickingCodes() {
      return this.pickingCodes;
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
   * 状态.
   * @param status 状态
   */
  public void setStatus(VipDispatchStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   * @return 状态
   */
  public VipDispatchStatus getStatus() {
      return this.status;
  }

  /**
   * .
   * @param statuses 
   */
  public void setStatuses(List<VipDispatchStatus> statuses) {
    this.statuses = statuses;
  }

  /**
   * .
   * @return 
   */
  public List<VipDispatchStatus> getStatuses() {
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
   * 发货单编码.
   * @param vipDeliveryCode 发货单编码
   */
  public void setVipDeliveryCode(String vipDeliveryCode) {
    this.vipDeliveryCode = vipDeliveryCode == null ? null : vipDeliveryCode.trim();
  }

  /**
   * 发货单编码.
   * @return 发货单编码
   */
  public String getVipDeliveryCode() {
      return this.vipDeliveryCode;
  }

  /**
   * .
   * @param vipDeliveryCodes 
   */
  public void setVipDeliveryCodes(List<String> vipDeliveryCodes) {
    this.vipDeliveryCodes = vipDeliveryCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getVipDeliveryCodes() {
      return this.vipDeliveryCodes;
  }

  /**
   * 发货单id.
   * @param vipDeliveryId 发货单id
   */
  public void setVipDeliveryId(Long vipDeliveryId) {
    this.vipDeliveryId = vipDeliveryId;
  }

  /**
   * 发货单id.
   * @return 发货单id
   */
  public Long getVipDeliveryId() {
      return this.vipDeliveryId;
  }

  /**
   * 配货单编码.
   * @param vipDispatchCode 配货单编码
   */
  public void setVipDispatchCode(String vipDispatchCode) {
    this.vipDispatchCode = vipDispatchCode == null ? null : vipDispatchCode.trim();
  }

  /**
   * 配货单编码.
   * @return 配货单编码
   */
  public String getVipDispatchCode() {
      return this.vipDispatchCode;
  }

  /**
   * .
   * @param vipDispatchCodes 
   */
  public void setVipDispatchCodes(List<String> vipDispatchCodes) {
    this.vipDispatchCodes = vipDispatchCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getVipDispatchCodes() {
      return this.vipDispatchCodes;
  }

  /**
   * 配货单id.
   * @param vipDispatchId 配货单id
   */
  public void setVipDispatchId(Long vipDispatchId) {
    this.vipDispatchId = vipDispatchId;
  }

  /**
   * 配货单id.
   * @return 配货单id
   */
  public Long getVipDispatchId() {
      return this.vipDispatchId;
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