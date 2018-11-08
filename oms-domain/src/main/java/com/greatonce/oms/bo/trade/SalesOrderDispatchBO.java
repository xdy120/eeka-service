package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/6
 */
public class SalesOrderDispatchBO extends VersionBO {

  private List<SalesOrderDetail> details;

  public List<SalesOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(List<SalesOrderDetail> details) {
    this.details = details;
  }
}
