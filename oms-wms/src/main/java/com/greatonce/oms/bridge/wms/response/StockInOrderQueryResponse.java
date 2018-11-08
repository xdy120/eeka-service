package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.StockInOrderQueryRequest;

/**
 * 发货单查询.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-21
 */
public class StockInOrderQueryResponse extends WmsResponse<StockInOrderQueryRequest> {
  private boolean exists;
  private WmsStockInOrderStatus status;

  public boolean isExists() {
    return exists;
  }

  public void setExists(boolean exists) {
    this.exists = exists;
  }

  public WmsStockInOrderStatus getStatus() {
    return status;
  }

  public void setStatus(
      WmsStockInOrderStatus status) {
    this.status = status;
  }

  public StockInOrderQueryResponse(StockInOrderQueryRequest request) {
    super(request);
  }

  public StockInOrderQueryResponse(StockInOrderQueryRequest request, boolean success,
      String message) {
    super(request, success, message);
  }

  public enum WmsStockInOrderStatus {
    /**
     * 未开始处理.
     */
    NEW,
    /**
     * 仓库接单.
     */
    ACCEPT,
    /**
     * 部分收货完成.
     */
    PARTFULFILLED,
    /**
     * 收货完成.
     */
    FULFILLED,

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
    CANCELEDFAIL;
  }
}
