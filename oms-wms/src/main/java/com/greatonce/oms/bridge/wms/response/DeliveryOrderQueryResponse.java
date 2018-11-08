package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.DeliveryOrderQueryRequest;

/**
 * 发货单查询.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-21
 */
public class DeliveryOrderQueryResponse extends WmsResponse<DeliveryOrderQueryRequest> {

  private boolean exists;
  private WmsDeliveryOrderStatus status;

  public boolean isExists() {
    return exists;
  }

  public void setExists(boolean exists) {
    this.exists = exists;
  }

  public WmsDeliveryOrderStatus getStatus() {
    return status;
  }

  public void setStatus(WmsDeliveryOrderStatus status) {
    this.status = status;
  }

  public DeliveryOrderQueryResponse(DeliveryOrderQueryRequest request) {
    super(request);
  }

  public DeliveryOrderQueryResponse(DeliveryOrderQueryRequest request, boolean success,
      String message) {
    super(request, success, message);
  }

  public enum WmsDeliveryOrderStatus {
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
