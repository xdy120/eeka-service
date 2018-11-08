package com.greatonce.oms.bo.report;

public class SalesStatisticsBO {

  private String storeName;
  private Integer quantity;
  private Double markedPriceTotal;
  private Double settlementAmountTotal;
  private Double discounAmountTotal;
  private Integer returnQuantity;
  private Double returnAmountTotal;
  private Double refundRate;


  public String getStoreName() {
    return storeName;
  }

  public void setStoreName(String storeName) {
    this.storeName = storeName;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Double getMarkedPriceTotal() {
    return markedPriceTotal;
  }

  public void setMarkedPriceTotal(Double markedPriceTotal) {
    this.markedPriceTotal = markedPriceTotal;
  }

  public Double getSettlementAmountTotal() {
    return settlementAmountTotal;
  }

  public void setSettlementAmountTotal(Double settlementAmountTotal) {
    this.settlementAmountTotal = settlementAmountTotal;
  }

  public Double getDiscounAmountTotal() {
    return discounAmountTotal;
  }

  public void setDiscounAmountTotal(Double discounAmountTotal) {
    this.discounAmountTotal = discounAmountTotal;
  }

  public Integer getReturnQuantity() {
    return returnQuantity;
  }

  public void setReturnQuantity(Integer returnQuantity) {
    this.returnQuantity = returnQuantity;
  }

  public Double getReturnAmountTotal() {
    return returnAmountTotal;
  }

  public void setReturnAmountTotal(Double returnAmountTotal) {
    this.returnAmountTotal = returnAmountTotal;
  }

  public Double getRefundRate() {
    return refundRate;
  }

  public void setRefundRate(Double refundRate) {
    this.refundRate = refundRate;
  }
}
