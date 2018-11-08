package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.List;

/**
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/9/21
 */
public class SalesOrderDetailSplitBO extends SalesOrderDetailBO {

  private List<SalesOrderDetail> splitDetails;

  public List<SalesOrderDetail> getSplitDetails() {
    return splitDetails;
  }

  public void setSplitDetails(List<SalesOrderDetail> splitDetails) {
    this.splitDetails = splitDetails;
  }
}
