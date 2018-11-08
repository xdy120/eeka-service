package com.greatonce.oms.domain.base.setting;

import java.io.Serializable;

/**
 * 订单配置.
 *
 * @author ginta
 */
public class SalesOrderSetting implements Serializable {

  /**
   * 构造方法.
   */
  public SalesOrderSetting() {
    this.salesMessageDeleteRule = SalesMessageDeleteRule.REPLACE;
    this.stopUpdateSalesMessageTime = StopUpdateSalesMessageTime.PREPARED;
    this.orderMergeRule = OrderMergeRule.SAME_STORE;
    this.autoPrepareOnSplit = true;
    this.waitingPrepareTime = 1;
    this.manualPrepareFirst = true;
  }

  /**
   * 卖家留言更新方式.
   */
  private SalesMessageDeleteRule salesMessageDeleteRule;

  /**
   * 停止更新卖家留言时间.
   */
  private StopUpdateSalesMessageTime stopUpdateSalesMessageTime;

  /**
   * 订单合单规则.
   */
  private OrderMergeRule orderMergeRule;

  /**
   * 预售通配符.
   */
  private String presellWildcard;

  /**
   * 配货等等时间.
   */
  private Integer waitingPrepareTime;

  /**
   * 手工配货抢占库存.
   */
  private Boolean manualPrepareFirst;

  /**
   * 订单拆分后自动配货.
   */
  private Boolean autoPrepareOnSplit;

  public SalesMessageDeleteRule getSalesMessageDeleteRule() {
    return salesMessageDeleteRule;
  }

  public void setSalesMessageDeleteRule(SalesMessageDeleteRule salesMessageDeleteRule) {
    this.salesMessageDeleteRule = salesMessageDeleteRule;
  }

  public StopUpdateSalesMessageTime getStopUpdateSalesMessageTime() {
    return stopUpdateSalesMessageTime;
  }

  public void setStopUpdateSalesMessageTime(StopUpdateSalesMessageTime stopUpdateSalesMessageTime) {
    this.stopUpdateSalesMessageTime = stopUpdateSalesMessageTime;
  }

  public OrderMergeRule getOrderMergeRule() {
    return orderMergeRule;
  }

  public void setOrderMergeRule(OrderMergeRule orderMergeRule) {
    this.orderMergeRule = orderMergeRule;
  }

  public String getPresellWildcard() {
    return presellWildcard;
  }

  public void setPresellWildcard(String presellWildcard) {
    this.presellWildcard = presellWildcard;
  }

  public Integer getWaitingPrepareTime() {
    return waitingPrepareTime;
  }

  public void setWaitingPrepareTime(Integer waitingPrepareTime) {
    this.waitingPrepareTime = waitingPrepareTime;
  }

  public Boolean isManualPrepareFirst() {
    return manualPrepareFirst;
  }

  public void setManualPrepareFirst(Boolean manualPrepareFirst) {
    this.manualPrepareFirst = manualPrepareFirst;
  }

  public Boolean getAutoPrepareOnSplit() {
    return autoPrepareOnSplit;
  }

  public void setAutoPrepareOnSplit(Boolean autoPrepareOnSplit) {
    this.autoPrepareOnSplit = autoPrepareOnSplit;
  }

  /**
   * 卖家留言更新方式.
   **/
  public enum SalesMessageDeleteRule {
    /**
     * 新增卖家留言，并删除之前的卖家留言.
     **/
    REPLACE,
    /**
     * 新增卖家留言，并保留之前的卖家留言.
     **/
    APPEND,
  }

  /**
   * 停止更新卖家留言时间.
   **/
  public enum StopUpdateSalesMessageTime {
    /**
     * 订单生成后.
     **/
    CREATED,
    /**
     * 订单配货后.
     **/
    PREPARED,
    /**
     * 订单发货后.
     **/
    DELIVERED,
  }

  /**
   * 订单合单规则.
   **/
  public enum OrderMergeRule {
    /**
     * 同一个会员即可合并.
     **/
    NONE,
    /**
     * 同一个店铺的同一个会员才能合并.
     **/
    SAME_STORE
  }

}
