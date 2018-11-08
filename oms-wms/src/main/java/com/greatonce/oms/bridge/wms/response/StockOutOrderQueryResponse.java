package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.StockOutOrderQueryRequest;

/**
 * 发货单查询.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-21
 */
public class StockOutOrderQueryResponse extends WmsResponse<StockOutOrderQueryRequest> {

  private boolean exists;
  private WmsStockOutOrderStatus status;

  public boolean isExists() {
    return exists;
  }

  public void setExists(boolean exists) {
    this.exists = exists;
  }

  public WmsStockOutOrderStatus getStatus() {
    return status;
  }

  public void setStatus(
      WmsStockOutOrderStatus status) {
    this.status = status;
  }

  public StockOutOrderQueryResponse(StockOutOrderQueryRequest request) {
    super(request);
  }

  public StockOutOrderQueryResponse(StockOutOrderQueryRequest request, boolean success,
      String message) {
    super(request, success, message);
  }

  public enum WmsStockOutOrderStatus {
    /**
     * 未开始处理.
     */
    NEW,
    /**
     * 仓库接单.
     */
    ACCEPT,
    /**
     * 部分发货完成.
     */
    PARTDELIVERED,
    /**
     * 发货完成.
     */
    DELIVERED,

    /**
     * 异常.
     */
    EXCEPTION,

    /**
     * 取消.
     */
    CANCELED,
    /**
     * 关闭.
     */
    CLOSED,
    /**
     * 拒单.
     */
    REJECT,
    /**
     * 取消失败.
     */
    CANCELEDFAIL
  }
}
