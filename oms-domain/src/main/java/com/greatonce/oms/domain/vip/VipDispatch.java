package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.VersionDO;
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
public class VipDispatch extends VersionDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 最后出库时间.
   */
  private LocalDateTime lastOutTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 外部单号.
   */
  private String outerCode;
  /**
   * 拣货单号.
   */
  private String pickingCode;
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
   * 店铺id.
   */
  private Long storeId;
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
   * 发货单id.
   */
  private Long vipDeliveryId;
  /**
   * 配货单编码.
   */
  private String vipDispatchCode;
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
   * ${field.comment}.
   */
  private List<VipDispatchDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.vipDispatchId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipDispatchId;
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
   * 创建人.
   */
  public void setCreator(String creator) {
    this.creator = creator == null ? null : creator.trim();
  }

  /**
   * 创建人.
   */
  public String getCreator() {
    return this.creator;
  }

  /**
   * 是否异常.
   */
  public void setAbnormal(Boolean abnormal) {
    this.abnormal = abnormal;
  }

  /**
   * 是否异常.
   */
  public Boolean isAbnormal() {
    return this.abnormal;
  }

  /**
   * 唯品价异常.
   */
  public void setVipPriceAbnormal(Boolean vipPriceAbnormal) {
    this.vipPriceAbnormal = vipPriceAbnormal;
  }

  /**
   * 唯品价异常.
   */
  public Boolean isVipPriceAbnormal() {
    return this.vipPriceAbnormal;
  }

  /**
   * 最后出库时间.
   */
  public void setLastOutTime(LocalDateTime lastOutTime) {
    this.lastOutTime = lastOutTime;
  }

  /**
   * 最后出库时间.
   */
  public LocalDateTime getLastOutTime() {
    return this.lastOutTime;
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
   * 通知数量.
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   */
  public Integer getNoticeQuantity() {
    return this.noticeQuantity;
  }

  /**
   * 出库数量.
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   */
  public Integer getOutQuantity() {
    return this.outQuantity;
  }

  /**
   * 出库状态.
   */
  public void setOutStatus(OutStatus outStatus) {
    this.outStatus = outStatus;
  }

  /**
   * 出库状态.
   */
  public OutStatus getOutStatus() {
    return this.outStatus;
  }


  /**
   * 外部单号.
   */
  public void setOuterCode(String outerCode) {
    this.outerCode = outerCode == null ? null : outerCode.trim();
  }

  /**
   * 外部单号.
   */
  public String getOuterCode() {
    return this.outerCode;
  }

  /**
   * 拣货单号.
   */
  public void setPickingCode(String pickingCode) {
    this.pickingCode = pickingCode == null ? null : pickingCode.trim();
  }

  /**
   * 拣货单号.
   */
  public String getPickingCode() {
    return this.pickingCode;
  }


  /**
   * 过账状态.
   */
  public void setPostStatus(PostStatus postStatus) {
    this.postStatus = postStatus;
  }

  /**
   * 过账状态.
   */
  public PostStatus getPostStatus() {
    return this.postStatus;
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
   * 状态.
   */
  public void setStatus(VipDispatchStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipDispatchStatus getStatus() {
    return this.status;
  }


  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }


  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }

  /**
   * 版本.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   */
  @Override
  public Integer getVersion() {
    return this.version;
  }

  /**
   * 金额.
   */
  public void setVipAmount(Double vipAmount) {
    this.vipAmount = vipAmount;
  }

  /**
   * 金额.
   */
  public Double getVipAmount() {
    return this.vipAmount;
  }

  /**
   * 发货单编码.
   */
  public void setVipDeliveryCode(String vipDeliveryCode) {
    this.vipDeliveryCode = vipDeliveryCode == null ? null : vipDeliveryCode.trim();
  }

  /**
   * 发货单编码.
   */
  public String getVipDeliveryCode() {
    return this.vipDeliveryCode;
  }


  /**
   * 发货单id.
   */
  public void setVipDeliveryId(Long vipDeliveryId) {
    this.vipDeliveryId = vipDeliveryId;
  }

  /**
   * 发货单id.
   */
  public Long getVipDeliveryId() {
    return this.vipDeliveryId;
  }

  /**
   * 配货单编码.
   */
  public void setVipDispatchCode(String vipDispatchCode) {
    this.vipDispatchCode = vipDispatchCode == null ? null : vipDispatchCode.trim();
  }

  /**
   * 配货单编码.
   */
  public String getVipDispatchCode() {
    return this.vipDispatchCode;
  }


  /**
   * 配货单id.
   */
  public void setVipDispatchId(Long vipDispatchId) {
    this.vipDispatchId = vipDispatchId;
  }

  /**
   * 配货单id.
   */
  public Long getVipDispatchId() {
    return this.vipDispatchId;
  }

  /**
   * 唯品仓编码.
   */
  public void setVipWarehouseCode(String vipWarehouseCode) {
    this.vipWarehouseCode = vipWarehouseCode == null ? null : vipWarehouseCode.trim();
  }

  /**
   * 唯品仓编码.
   */
  public String getVipWarehouseCode() {
    return this.vipWarehouseCode;
  }

  /**
   * 唯品仓名称.
   */
  public void setVipWarehouseName(String vipWarehouseName) {
    this.vipWarehouseName = vipWarehouseName == null ? null : vipWarehouseName.trim();
  }

  /**
   * 唯品仓名称.
   */
  public String getVipWarehouseName() {
    return this.vipWarehouseName;
  }

  /**
   * 虚拟仓id.
   */
  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  /**
   * 虚拟仓id.
   */
  public Long getVirtualWarehouseId() {
    return this.virtualWarehouseId;
  }

  /**
   * 虚拟仓名称.
   */
  public void setVirtualWarehouseName(String virtualWarehouseName) {
    this.virtualWarehouseName = virtualWarehouseName == null ? null : virtualWarehouseName.trim();
  }

  /**
   * 虚拟仓名称.
   */
  public String getVirtualWarehouseName() {
    return this.virtualWarehouseName;
  }

  /**
   * 实体仓id.
   */
  public void setWarehouseId(Long warehouseId) {
    this.warehouseId = warehouseId;
  }

  /**
   * 实体仓id.
   */
  public Long getWarehouseId() {
    return this.warehouseId;
  }

  /**
   * 实体仓名称.
   */
  public void setWarehouseName(String warehouseName) {
    this.warehouseName = warehouseName == null ? null : warehouseName.trim();
  }

  /**
   * 实体仓名称.
   */
  public String getWarehouseName() {
    return this.warehouseName;
  }

  /**
   * ${field.comment}.
   */
  public void setDetails(List<VipDispatchDetail> details) {
    this.details = details;
  }

  /**
   * ${field.comment}.
   */
  public List<VipDispatchDetail> getDetails() {
    return this.details;
  }
}