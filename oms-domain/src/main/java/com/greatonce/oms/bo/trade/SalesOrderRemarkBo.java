package com.greatonce.oms.bo.trade;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/13
 */
public class SalesOrderRemarkBo extends SalesOrderBO {

  private Long salesOrderId;
  private String remark;
  private Integer version;

  public Long getSalesOrderId() {
    return salesOrderId;
  }

  public void setSalesOrderId(Long salesOrderId) {
    this.salesOrderId = salesOrderId;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  @Override
  public Integer getVersion() {
    return version;
  }

  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }
}
