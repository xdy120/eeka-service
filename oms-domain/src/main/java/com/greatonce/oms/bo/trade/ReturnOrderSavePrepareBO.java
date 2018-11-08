package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca Date: 2018-06-05 Time: 15:08 Description:
 */
public class ReturnOrderSavePrepareBO {


  private String returnType;

  private Long inVirtualWarehouseId;

  private String inVirtualWarehouseName;

  private String expressNo;

  private Store store;

  private SalesOrder salesOrder;

  private ReturnOrder oldReturnOrder;

  private Boolean isRefund;

  private Map<String, RefundApplyOrder> refundApplys;

  private String remark;

  private String boxNo;

  private Boolean isExchange;

  private Map<String, ExchangeApplyOrder> exchangeApplys;

  private List<InDetail> inDetails;

  private List<OutDetail> outDetails;

  private String nickName;

  /**
   * 最终要保存的 退换货单
   **/
  private ReturnOrder returnOrder;


  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public Boolean getRefund() {
    return isRefund;
  }

  public void setRefund(Boolean refund) {
    isRefund = refund;
  }

  public Boolean getExchange() {
    return isExchange;
  }

  public void setExchange(Boolean exchange) {
    isExchange = exchange;
  }

  public ReturnOrder getOldReturnOrder() {
    return oldReturnOrder;
  }

  public void setOldReturnOrder(ReturnOrder oldReturnOrder) {
    this.oldReturnOrder = oldReturnOrder;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public SalesOrder getSalesOrder() {
    return salesOrder;
  }

  public void setSalesOrder(SalesOrder salesOrder) {
    this.salesOrder = salesOrder;
  }

  public ReturnOrder getReturnOrder() {
    return returnOrder;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public void setReturnOrder(ReturnOrder returnOrder) {
    this.returnOrder = returnOrder;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }

  public Long getInVirtualWarehouseId() {
    return inVirtualWarehouseId;
  }

  public void setInVirtualWarehouseId(Long inVirtualWarehouseId) {
    this.inVirtualWarehouseId = inVirtualWarehouseId;
  }

  public String getInVirtualWarehouseName() {
    return inVirtualWarehouseName;
  }

  public void setInVirtualWarehouseName(String inVirtualWarehouseName) {
    this.inVirtualWarehouseName = inVirtualWarehouseName;
  }

  public List<InDetail> getInDetails() {
    return inDetails;
  }

  public void setInDetails(List<InDetail> inDetails) {
    this.inDetails = inDetails;
  }

  public List<OutDetail> getOutDetails() {
    return outDetails;
  }

  public void setOutDetails(List<OutDetail> outDetails) {
    this.outDetails = outDetails;
  }

  public String getBoxNo() {
    return boxNo;
  }

  public void setBoxNo(String boxNo) {
    this.boxNo = boxNo;
  }

  public Map<String, RefundApplyOrder> getRefundApplys() {
    return refundApplys;
  }

  public void setRefundApplys(
      Map<String, RefundApplyOrder> refundApplys) {
    this.refundApplys = refundApplys;
  }

  public Map<String, ExchangeApplyOrder> getExchangeApplys() {
    return exchangeApplys;
  }

  public void setExchangeApplys(
      Map<String, ExchangeApplyOrder> exchangeApplys) {
    this.exchangeApplys = exchangeApplys;
  }
}
