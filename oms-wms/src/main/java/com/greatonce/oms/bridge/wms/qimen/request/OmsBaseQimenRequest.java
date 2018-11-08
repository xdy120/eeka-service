package com.greatonce.oms.bridge.wms.qimen.request;

/**
 * 奇门请求基础实体.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/6
 */
public interface OmsBaseQimenRequest extends OmsQimenRequest {

  /**
   * 货主编码.
   */
  String getOwnerCode();

  /**
   * 单据编号.
   */
  String getOrderCode();

  /**
   * 外部单据编号.
   */
  String getOutCode();

  /**
   * 仓库编码.
   */
  String getWarehouseCode();

  /**
   * 外部编码.
   */
  String getOutBizCode();
}
