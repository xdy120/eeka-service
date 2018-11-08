package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.trade.SalesOrderDetail;

/**
 * @author buer
 * @version 2017-12-27 19:19
 */
public class SalesOrderRemoveGiftBO extends VersionBO {

  private SalesOrderDetail gift;

  public SalesOrderDetail getGift() {
    return gift;
  }

  public void setGift(SalesOrderDetail gift) {
    this.gift = gift;
  }
}
