package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.List;

/**
 * @author buer
 * @version 2018-01-06 10:03
 */
public class SalesOrderDetailBatchBO extends VersionBO {

  private List<SalesOrderDetail> details;

  public List<SalesOrderDetail> getDetails() {
    return details;
  }

  public void setDetails(List<SalesOrderDetail> details) {
    this.details = details;
  }
}
