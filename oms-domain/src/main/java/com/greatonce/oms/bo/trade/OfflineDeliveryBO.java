package com.greatonce.oms.bo.trade;

/**
 * @author buer
 * @version 2018-01-06 10:31
 */
public class OfflineDeliveryBO extends DispatchBO<DispatchDetailBO> {

  private boolean giftFlag;

  public boolean isGiftFlag() {
    return giftFlag;
  }

  public void setGiftFlag(boolean giftFlag) {
    this.giftFlag = giftFlag;
  }
}
