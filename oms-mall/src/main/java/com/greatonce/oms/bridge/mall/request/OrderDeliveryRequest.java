package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/19
 */
public class OrderDeliveryRequest extends MallRequest {

  private boolean partDelivery;
  /**
   * 商城快递id
   */
  private String mallExpressId;
  /**
   * 商城快递编码
   */
  private String mallExpressCode;
  /**
   * 商城快递名称
   */
  private String mallExpressName;
  /**
   * 快递单号（默认取明细第一个）
   */
  private String expressNo;
  /**
   * 订单
   */
  private SalesOrder salesOrder;
  /**
   * 发货明细
   */
  private List<OrderDeliveryDetail> details;

  public boolean isPartDelivery() {
    return partDelivery;
  }

  public void setPartDelivery(boolean partDelivery) {
    this.partDelivery = partDelivery;
  }

  public SalesOrder getSalesOrder() {
    return salesOrder;
  }

  public void setSalesOrder(SalesOrder salesOrder) {
    this.salesOrder = salesOrder;
  }

  public String getMallExpressCode() {
    return mallExpressCode;
  }

  public String getMallExpressId() {
    return mallExpressId;
  }

  public void setMallExpressId(String mallExpressId) {
    this.mallExpressId = mallExpressId;
  }

  public void setMallExpressCode(String mallExpressCode) {
    this.mallExpressCode = mallExpressCode;
  }

  public String getMallExpressName() {
    return mallExpressName;
  }

  public void setMallExpressName(String mallExpressName) {
    this.mallExpressName = mallExpressName;
  }

  public String getExpressNo() {
    return expressNo;
  }

  public void setExpressNo(String expressNo) {
    this.expressNo = expressNo;
  }

  public List<OrderDeliveryDetail> getDetails() {
    return details;
  }

  public void setDetails(List<OrderDeliveryDetail> details) {
    this.details = details;
  }

  public OrderDeliveryRequest(Store store) {
    super(store);
  }

  public static class OrderDeliveryDetail {

    /**
     * 商城快递编码
     */
    private String mallExpressCode;
    /**
     * 商城快递名称
     */
    private String mallExpressName;
    /**
     * 快递单号（默认取明细第一个）
     */
    private String expressNo;
    /**
     * 订单明细
     */
    private SalesOrderDetail salesOrderDetail;

    public String getMallExpressCode() {
      return mallExpressCode;
    }

    public void setMallExpressCode(String mallExpressCode) {
      this.mallExpressCode = mallExpressCode;
    }

    public String getMallExpressName() {
      return mallExpressName;
    }

    public void setMallExpressName(String mallExpressName) {
      this.mallExpressName = mallExpressName;
    }

    public String getExpressNo() {
      return expressNo;
    }

    public void setExpressNo(String expressNo) {
      this.expressNo = expressNo;
    }

    public SalesOrderDetail getSalesOrderDetail() {
      return salesOrderDetail;
    }

    public void setSalesOrderDetail(SalesOrderDetail salesOrderDetail) {
      this.salesOrderDetail = salesOrderDetail;
    }
  }
}
