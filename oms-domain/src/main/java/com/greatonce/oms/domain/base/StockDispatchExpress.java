package com.greatonce.oms.domain.base;


import java.io.Serializable;

/**
 * 配货快递.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/23
 */
public class StockDispatchExpress implements Serializable, Comparable<StockDispatchExpress> {

  private Long expressId;
  private String expressName;
  private Integer priorityNo;

  public Long getExpressId() {
    return expressId;
  }

  public void setExpressId(Long expressId) {
    this.expressId = expressId;
  }

  public String getExpressName() {
    return expressName;
  }

  public void setExpressName(String expressName) {
    this.expressName = expressName;
  }

  public Integer getPriorityNo() {
    return priorityNo;
  }

  public void setPriorityNo(Integer priorityNo) {
    this.priorityNo = priorityNo;
  }

  @Override
  public int compareTo(StockDispatchExpress o) {
    return this.priorityNo.compareTo(o.priorityNo);
  }
}
