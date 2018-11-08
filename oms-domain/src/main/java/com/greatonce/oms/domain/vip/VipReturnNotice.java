package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.VersionDO;
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
public class VipReturnNotice extends VersionDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 唯品价异常.
   */
  private Boolean vipPriceAbnormal;
  /**
   * 最后入库时间.
   */
  private LocalDateTime lastInTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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
   * 状态.
   */
  private VipReturnNoticeStatus status;
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
   * 唯品通知单编码.
   */
  private String vipReturnNoticeCode;
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
   * ${field.comment}.
   */
  private List<VipReturnNoticeDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.vipReturnNoticeId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipReturnNoticeId;
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
   * 入库正品数量.
   */
  public void setInQuantity(Integer inQuantity) {
    this.inQuantity = inQuantity;
  }

  /**
   * 入库正品数量.
   */
  public Integer getInQuantity() {
    return this.inQuantity;
  }

  /**
   * 入库状态.
   */
  public void setInStatus(InStatus inStatus) {
    this.inStatus = inStatus;
  }

  /**
   * 入库状态.
   */
  public InStatus getInStatus() {
    return this.inStatus;
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
   * 最后入库时间.
   */
  public void setLastInTime(LocalDateTime lastInTime) {
    this.lastInTime = lastInTime;
  }

  /**
   * 最后入库时间.
   */
  public LocalDateTime getLastInTime() {
    return this.lastInTime;
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
  public void setStatus(VipReturnNoticeStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public VipReturnNoticeStatus getStatus() {
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
   * 唯品通知单编码.
   */
  public void setVipReturnNoticeCode(String vipReturnNoticeCode) {
    this.vipReturnNoticeCode = vipReturnNoticeCode == null ? null : vipReturnNoticeCode.trim();
  }

  /**
   * 唯品通知单编码.
   */
  public String getVipReturnNoticeCode() {
    return this.vipReturnNoticeCode;
  }


  /**
   * 唯品通知单id.
   */
  public void setVipReturnNoticeId(Long vipReturnNoticeId) {
    this.vipReturnNoticeId = vipReturnNoticeId;
  }

  /**
   * 唯品通知单id.
   */
  public Long getVipReturnNoticeId() {
    return this.vipReturnNoticeId;
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
  public void setDetails(List<VipReturnNoticeDetail> details) {
    this.details = details;
  }

  /**
   * ${field.comment}.
   */
  public List<VipReturnNoticeDetail> getDetails() {
    return this.details;
  }
}