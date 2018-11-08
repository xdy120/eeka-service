package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import java.util.List;

/**
 * @author buer
 * @version 2018-01-10 16:00
 */
public class SalesOrderResetDetailBO extends VersionBO {

  private List<Long> salesOrderDetailIds;

  public List<Long> getSalesOrderDetailIds() {
    return salesOrderDetailIds;
  }

  public void setSalesOrderDetailIds(List<Long> salesOrderDetailIds) {
    this.salesOrderDetailIds = salesOrderDetailIds;
  }
}
