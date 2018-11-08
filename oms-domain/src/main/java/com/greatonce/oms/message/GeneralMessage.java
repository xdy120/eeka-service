package com.greatonce.oms.message;

import com.greatonce.oms.domain.enums.OmsModule;
import java.text.MessageFormat;

/**
 * 通用消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
public class GeneralMessage extends Message {

  private final EventType eventType;
  private final OmsModule module;
  private final String key;
  private final Long dataId;

  /**
   * 构造方法.
   *
   * @param module 模块
   * @param dataId 数据数据id
   * @param eventType 事件类型
   */
  public GeneralMessage(OmsModule module, Long dataId, EventType eventType) {
    this.module = module;
    this.eventType = eventType;
    this.dataId = dataId;
    this.key = MessageFormat
        .format("oms.{0}.{1}", module.getPath(), eventType.name().toLowerCase());
  }

  public EventType getEventType() {
    return eventType;
  }


  @Override
  public String routingKey() {
    return this.key;
  }

  public Long getDataId() {
    return dataId;
  }

  public OmsModule getModule() {
    return module;
  }

  /**
   * 事件类型.
   */
  public enum EventType {
    /**
     * 创建.
     */
    CREATED,
    /**
     * 修改.
     */
    MODIFIED,
    /**
     * 删除.
     */
    REMOVED,
    /**
     * 启用.
     */
    ENABLED,
    /**
     * 禁用.
     */
    DISABLED,
    /**
     * 作废.
     */
    INVALID,
    /**
     * 审核.
     */
    AUDITED,
    /**
     * 复核.
     */
    REVIEWED,
    /**
     * 通知.
     */
    NOTICED,
    /**
     * 通知失败.
     */
    NOTICE_FAILED,
    /**
     * 完结.
     */
    FINISHED,
    /**
     * 关闭.
     */
    CLOSED,
    /**
     * 取消.
     */
    CANCELED,
    /**
     * 发货.
     */
    DELIVERED,
    /**
     * 入库.
     */
    IN,
    /**
     * 出库.
     */
    OUT
  }
}
