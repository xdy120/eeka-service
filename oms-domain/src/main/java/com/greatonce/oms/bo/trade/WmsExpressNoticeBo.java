package com.greatonce.oms.bo.trade;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.trade.DispatchOrder;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/7
 */
public class WmsExpressNoticeBo extends VersionBO<DispatchOrder> {

  private Express express;
  private String expressNo;

  public Express getExpress() {
    return express;
  }

  public void setExpress(Express express) {
    this.express = express;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }
}
