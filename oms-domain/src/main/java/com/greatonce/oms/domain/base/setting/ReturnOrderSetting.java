package com.greatonce.oms.domain.base.setting;

import java.io.Serializable;

/**
 * 退换货单配置.
 */
public class ReturnOrderSetting implements Serializable {

  /**
   * 构造方法.
   */
  public ReturnOrderSetting() {
    this.jdOrgBalanceMoney = false;
    this.alertExistReturnOrder = true;
    this.useAfterSalesAmount = false;
    this.enableReturnReason = false;
    this.onlyChangeSameParagraph = true;
    this.autoAudit = false;
    this.alertExistChangeOrder = false;
    this.alertExistRefundSlip = false;
  }

  /**
   * 自动下载的京东换货订单的【结算金额】取原销售订单的结算金额.
   */
  private Boolean jdOrgBalanceMoney;

  /**
   * 退货扫描时，如果订单已经存在【退换货单】，则系统进行提示.
   */
  private Boolean alertExistReturnOrder;

  /**
   * 优先使用售后申请金额：退货扫描时，如果扫入商品有退款申请，则使用退款申请的金额作为【实退金额】，否则使用订单中的商品结算金额作为【实退金额】.
   */
  private Boolean useAfterSalesAmount;

  /**
   * 启用扫描退货原因：退货扫描时，扫描退入商品后，将【光标】跳入退货原因输入框中，否则【光标】将留在规格编码输入框中.
   */
  private Boolean enableReturnReason;

  /**
   * 启用换货只能换同款：退货扫描时，换出商品与退入商品必须属于同一个款，且换出商品的数量和金额不能大于退入商品的数量和金额.
   */
  private Boolean onlyChangeSameParagraph;

  /**
   * 复核退换货单生成的【换货订单】，允许系统自动审核.
   */
  private Boolean autoAudit;

  /**
   * 复核退换货单生成换货订单时，如果订单已存在【换货订单】类型的订单，则系统进行提示.
   */
  private Boolean alertExistChangeOrder;

  /**
   * 复核退换货单. 或 手工新增生成【退款单】时，如果订单已存在退款单，则系统进行提示
   */
  private Boolean alertExistRefundSlip;

  public Boolean getJdOrgBalanceMoney() {
    return jdOrgBalanceMoney;
  }

  public void setJdOrgBalanceMoney(Boolean jdOrgBalanceMoney) {
    this.jdOrgBalanceMoney = jdOrgBalanceMoney;
  }

  public Boolean getAlertExistReturnOrder() {
    return alertExistReturnOrder;
  }

  public void setAlertExistReturnOrder(Boolean alertExistReturnOrder) {
    this.alertExistReturnOrder = alertExistReturnOrder;
  }

  public Boolean getUseAfterSalesAmount() {
    return useAfterSalesAmount;
  }

  public void setUseAfterSalesAmount(Boolean useAfterSalesAmount) {
    this.useAfterSalesAmount = useAfterSalesAmount;
  }

  public Boolean getEnableReturnReason() {
    return enableReturnReason;
  }

  public void setEnableReturnReason(Boolean enableReturnReason) {
    this.enableReturnReason = enableReturnReason;
  }

  public Boolean getOnlyChangeSameParagraph() {
    return onlyChangeSameParagraph;
  }

  public void setOnlyChangeSameParagraph(Boolean onlyChangeSameParagraph) {
    this.onlyChangeSameParagraph = onlyChangeSameParagraph;
  }

  public Boolean getAutoAudit() {
    return autoAudit;
  }

  public void setAutoAudit(Boolean autoAudit) {
    this.autoAudit = autoAudit;
  }

  public Boolean getAlertExistChangeOrder() {
    return alertExistChangeOrder;
  }

  public void setAlertExistChangeOrder(Boolean alertExistChangeOrder) {
    this.alertExistChangeOrder = alertExistChangeOrder;
  }

  public Boolean getAlertExistRefundSlip() {
    return alertExistRefundSlip;
  }

  public void setAlertExistRefundSlip(Boolean alertExistRefundSlip) {
    this.alertExistRefundSlip = alertExistRefundSlip;
  }

}
