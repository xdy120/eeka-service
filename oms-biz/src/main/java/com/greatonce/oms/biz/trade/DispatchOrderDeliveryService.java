package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.query.trade.DispatchOrderDeliveryQuery;

import java.util.List;

/**
 * DispatchOrderDelivery <br/>
 * 配货单发货信息
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface DispatchOrderDeliveryService extends
    BatchBizService<DispatchOrderDelivery, DispatchOrderDeliveryQuery> {

  /**
   * 获取配货单发货明细
   */
  List<DispatchOrderDelivery> listByDispatchOrderId(Long dispatchOrderId);
}