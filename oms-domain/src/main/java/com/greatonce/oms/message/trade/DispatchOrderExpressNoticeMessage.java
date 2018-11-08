package com.greatonce.oms.message.trade;

import java.util.List;

/**
 * @author Lcc
 * @version 2018/7/4 9:58
 */
public class DispatchOrderExpressNoticeMessage extends DispatchOrderMessage {

  /**
   * 平台快递ID.
   */
  private final String mallExpressId;
  /**
   * 平台快递编码.
   */
  private final String mallExpressCode;
  /**
   * 平台快递名称.
   */
  private final String mallExpressName;
  /**
   * 运单编号.
   */
  private final String expressNo;
  /**
   * OMS快递名称.
   */
  private final String expressName;
  /**
   * 进行物流通知的订单明细id.
   */
  private final List<Long> noticeDetailIds;

  public DispatchOrderExpressNoticeMessage(Long dispatchOrderId, String mallExpressId,
      String mallExpressCode, String mallExpressName, String expressNo, String expressName,
      List<Long> noticeDetailIds) {
    super(dispatchOrderId, "wms.express.notice");
    this.mallExpressId = mallExpressId;
    this.mallExpressCode = mallExpressCode;
    this.mallExpressName = mallExpressName;
    this.expressNo = expressNo;
    this.expressName = expressName;
    this.noticeDetailIds = noticeDetailIds;
  }

  public String getMallExpressCode() {
    return mallExpressCode;
  }

  public String getMallExpressName() {
    return mallExpressName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public String getExpressName() {
    return expressName;
  }

  public String getMallExpressId() {
    return mallExpressId;
  }

  public List<Long> getNoticeDetailIds() {
    return noticeDetailIds;
  }
}
