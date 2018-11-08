package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * 快递通知请求
 * @author Lcc
 * @version 2018/7/3 18:28
 */
public class EekaExpressNoticeRequest extends OmsQimenCustomRequest {

  /**
   * 配货单号
   */
  private String dispatchOrderCode;
  /**
   * 快递编码
   */
  private String expressCode;
  /**
   * 快递单号
   */
  private String expressNo;
  /**
   * 仓库编码
   */
  private String warehouseCode;

  public String getDispatchOrderCode() {
    return dispatchOrderCode;
  }

  public void setDispatchOrderCode(String dispatchOrderCode) {
    this.dispatchOrderCode = dispatchOrderCode;
  }

  public String getExpressCode() {
    return expressCode;
  }

  public void setExpressCode(String expressCode) {
    this.expressCode = expressCode;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  @Override
  public String toString() {
    return "QimenExpressNoticeRequest{" +
        "dispatchOrderCode='" + dispatchOrderCode + '\'' +
        ", expressCode='" + expressCode + '\'' +
        ", expressNo='" + expressNo + '\'' +
        '}';
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public String getWarehouseCode() {
    return warehouseCode;
  }
}
