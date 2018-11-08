package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.query.trade.DispatchOrderDetailQuery;
import java.util.Collection;
import java.util.List;

/**
 * DispatchOrderDetail <br/>
 * 配货单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface DispatchOrderDetailService extends
    BatchBizService<DispatchOrderDetail, DispatchOrderDetailQuery> {

  /**
   * 取消明细
   *
   * @param detail 明细
   */
  void cancel(DispatchOrder dispatchOrder, DispatchOrderDetail detail);

  /**
   * 取消整单
   */
  void cancel(DispatchOrder dispatchOrder);

  /**
   * 获取简单信息
   *
   * @param dispatchOrderId 配货单ID
   * @return 配货单明细
   */
  List<DispatchOrderDetail> listSimple(Long dispatchOrderId);

  /**
   * 获取配货单明细
   */
  List<DispatchOrderDetail> listByDispatchOrderId(Long dispatchOrderId);

  /**
   * 整单发货
   */
  void delivery(DispatchOrder dispatchOrder, Collection<? extends DispatchOrderDetail> details);
}