package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品配货档期.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipDispatchScheduleQuery extends Query {
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 出库数量.
   */
  private Integer outQuantity;
  /**
   * 数量.
   */
  private Integer quantity;
  /**
   * 拣货单明细.
   */
  private Long vipDispatchDetailId;
  /**
   * 配货档期关联id.
   */
  private Long vipDispatchScheduleId;
  /**
   * 档期明细id.
   */
  private Long vipScheduleDetailId;
  /**
   * 虚拟仓id.
   */
  private Long virtualWarehouseId;


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
   * 拣货单明细.
   * @param vipDispatchDetailId 拣货单明细
   */
  public void setVipDispatchDetailId(Long vipDispatchDetailId) {
    this.vipDispatchDetailId = vipDispatchDetailId;
  }

  /**
   * 拣货单明细.
   * @return 拣货单明细
   */
  public Long getVipDispatchDetailId() {
      return this.vipDispatchDetailId;
  }

  /**
   * 配货档期关联id.
   * @param vipDispatchScheduleId 配货档期关联id
   */
  public void setVipDispatchScheduleId(Long vipDispatchScheduleId) {
    this.vipDispatchScheduleId = vipDispatchScheduleId;
  }

  /**
   * 配货档期关联id.
   * @return 配货档期关联id
   */
  public Long getVipDispatchScheduleId() {
      return this.vipDispatchScheduleId;
  }

  /**
   * 档期明细id.
   * @param vipScheduleDetailId 档期明细id
   */
  public void setVipScheduleDetailId(Long vipScheduleDetailId) {
    this.vipScheduleDetailId = vipScheduleDetailId;
  }

  /**
   * 档期明细id.
   * @return 档期明细id
   */
  public Long getVipScheduleDetailId() {
      return this.vipScheduleDetailId;
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
}