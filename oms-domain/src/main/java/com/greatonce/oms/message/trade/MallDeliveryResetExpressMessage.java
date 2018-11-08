package com.greatonce.oms.message.trade;


import com.greatonce.oms.message.Message;
import java.util.List;


public class MallDeliveryResetExpressMessage extends Message {

  private final Long dispatchOrderId;
  private final String resetExpressNo;
  private final String mallExpressId;
  private final String mallExpressCode;
  private final String mallExpressName;
  private final List<Long> resetDetailIds;

  public MallDeliveryResetExpressMessage(Long dispatchOrderId, String resetExpressNo,
      String mallExpressId, String mallExpressCode, String mallExpressName,
      List<Long> resetDetailIds) {
    this.dispatchOrderId = dispatchOrderId;
    this.resetExpressNo = resetExpressNo;
    this.mallExpressId = mallExpressId;
    this.mallExpressCode = mallExpressCode;
    this.mallExpressName = mallExpressName;
    this.resetDetailIds = resetDetailIds;
  }

  @Override
  public String routingKey() {
    return "oms.trade.mall.delivery.express.reset";
  }

  public Long getDispatchOrderId() {
    return dispatchOrderId;
  }

  public String getResetExpressNo() {
    return resetExpressNo;
  }

  public String getMallExpressCode() {
    return mallExpressCode;
  }

  public String getMallExpressName() {
    return mallExpressName;
  }

  public String getMallExpressId() {
    return mallExpressId;
  }

  public List<Long> getResetDetailIds() {
    return resetDetailIds;
  }
}
