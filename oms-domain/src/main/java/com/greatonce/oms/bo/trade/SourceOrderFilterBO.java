package com.greatonce.oms.bo.trade;

import com.greatonce.oms.query.trade.SalesOrderQuery;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca
 * Date: 2018-05-30
 * Time: 11:24
 * Description:
 */
public class SourceOrderFilterBO {

  private SalesOrderQuery salesOrderQuery;

  private String expressNo;

  public SalesOrderQuery getSalesOrderQuery() {
    return salesOrderQuery;
  }

  public void setSalesOrderQuery(SalesOrderQuery salesOrderQuery) {
    this.salesOrderQuery = salesOrderQuery;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }
}
