package com.greatonce.oms.domain.trade;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.trade.ReturnNoticeOrderStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 退货通知单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ReturnNoticeOrder extends VersionDO {
  /**
   * 审核时间.
   */
  private LocalDateTime auditedTime;
  /**
   * 审核人.
   */
  private String auditor;
  /**
   * 箱码.
   */
  private String boxNo;
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
   * 退换货通知单编码.
   */
  private String returnNoticeOrderCode;
  /**
   * 退换货通知单id.
   */
  private Long returnNoticeOrderId;
  /**
   * 状态.
   */
  private ReturnNoticeOrderStatus status;
  /**
   * 版本.
   */
  private Integer version;
  /**
   * 实体仓id.
   */
  private Long warehouseId;
  /**
   * 实体仓名称.
   */
  private String warehouseName;

  /**
   * 明细.
   */
  private List<ReturnNoticeOrderDetail> details;

  @Override
  public void setPrimaryKey(Long pk) {
    this.returnNoticeOrderId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.returnNoticeOrderId;
  }


  /**
   * 审核时间.
   */
  public void setAuditedTime(LocalDateTime auditedTime) {
    this.auditedTime = auditedTime;
  }

  /**
   * 审核时间.
   */
  public LocalDateTime getAuditedTime() {
    return this.auditedTime;
  }

  /**
   * 审核人.
   */
  public void setAuditor(String auditor) {
    this.auditor = auditor == null ? null : auditor.trim();
  }

  /**
   * 审核人.
   */
  public String getAuditor() {
    return this.auditor;
  }

  /**
   * 箱码.
   */
  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo == null ? null : boxNo.trim();
  }

  /**
   * 箱码.
   */
  public String getBoxNo() {
    return this.boxNo;
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
   * 退换货通知单编码.
   */
  public void setReturnNoticeOrderCode(String returnNoticeOrderCode) {
    this.returnNoticeOrderCode = returnNoticeOrderCode == null ? null : returnNoticeOrderCode.trim();
  }

  /**
   * 退换货通知单编码.
   */
  public String getReturnNoticeOrderCode() {
    return this.returnNoticeOrderCode;
  }


  /**
   * 退换货通知单id.
   */
  public void setReturnNoticeOrderId(Long returnNoticeOrderId) {
    this.returnNoticeOrderId = returnNoticeOrderId;
  }

  /**
   * 退换货通知单id.
   */
  public Long getReturnNoticeOrderId() {
    return this.returnNoticeOrderId;
  }



  /**
   * 状态.
   */
  public void setStatus(ReturnNoticeOrderStatus status) {
    this.status = status;
  }

  /**
   * 状态.
   */
  public ReturnNoticeOrderStatus getStatus() {
    return this.status;
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
   * 明细.
   */
  public void setDetails(List<ReturnNoticeOrderDetail> details) {
    this.details = details;
  }

  /**
   * 明细.
   */
  public List<ReturnNoticeOrderDetail> getDetails() {
    return this.details;
  }
}