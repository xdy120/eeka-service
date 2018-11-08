package com.greatonce.oms.domain.vip;

import com.greatonce.oms.domain.BaseDO;
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
public class VipDispatchSchedule extends BaseDO {
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
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


  @Override
  public void setPrimaryKey(Long pk) {
    this.vipDispatchScheduleId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.vipDispatchScheduleId;
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
   * 数量.
   */
  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  /**
   * 数量.
   */
  public Integer getQuantity() {
    return this.quantity;
  }

  /**
   * 拣货单明细.
   */
  public void setVipDispatchDetailId(Long vipDispatchDetailId) {
    this.vipDispatchDetailId = vipDispatchDetailId;
  }

  /**
   * 拣货单明细.
   */
  public Long getVipDispatchDetailId() {
    return this.vipDispatchDetailId;
  }

  /**
   * 配货档期关联id.
   */
  public void setVipDispatchScheduleId(Long vipDispatchScheduleId) {
    this.vipDispatchScheduleId = vipDispatchScheduleId;
  }

  /**
   * 配货档期关联id.
   */
  public Long getVipDispatchScheduleId() {
    return this.vipDispatchScheduleId;
  }

  /**
   * 档期明细id.
   */
  public void setVipScheduleDetailId(Long vipScheduleDetailId) {
    this.vipScheduleDetailId = vipScheduleDetailId;
  }

  /**
   * 档期明细id.
   */
  public Long getVipScheduleDetailId() {
    return this.vipScheduleDetailId;
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
}