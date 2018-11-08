package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.ReturnOrderQueryRequest;

/**
 * 发货单查询请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-21
 */
public class ReturnOrderQueryResponse extends WmsResponse<ReturnOrderQueryRequest> {
  private boolean exists;
  private WmsReturnOrderStatus status;

  public boolean isExists() {
    return exists;
  }

  public void setExists(boolean exists) {
    this.exists = exists;
  }

  public WmsReturnOrderStatus getStatus() {
    return status;
  }

  public void setStatus(
      WmsReturnOrderStatus status) {
    this.status = status;
  }

  public ReturnOrderQueryResponse(ReturnOrderQueryRequest request) {
    super(request);
  }

  public ReturnOrderQueryResponse(ReturnOrderQueryRequest request, boolean success,
      String message) {
    super(request, success, message);
  }


  public enum WmsReturnOrderStatus {
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
