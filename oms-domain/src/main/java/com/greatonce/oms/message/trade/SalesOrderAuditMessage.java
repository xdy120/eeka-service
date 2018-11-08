package com.greatonce.oms.message.trade;

/**
 * 销售单已审核消息.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
public class SalesOrderAuditMessage extends SalesOrderMessage {

  /**
   * 延迟分钟.
   */
  private final Integer delayMinutes;

  public SalesOrderAuditMessage(Long salesOrderId) {
    this(salesOrderId, null);
  }

  public SalesOrderAuditMessage(Long salesOrderId, Integer delayMinutes) {
    super(salesOrderId,
        delayMinutes != null && delayMinutes > 0 ? "audit.delay." + delayMinutes : "audit");
    this.delayMinutes = delayMinutes;
  }
}
