package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import java.time.LocalDate;

/**
 * @author buer
 * @version 2017-12-27 19:45
 */
public class SalesOrderHoldBO extends VersionBO {

  private LocalDate holdDate;

  public LocalDate getHoldDate() {
    return holdDate;
  }

  public void setHoldDate(LocalDate holdDate) {
    this.holdDate = holdDate;
  }
}
