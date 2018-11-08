package com.greatonce.oms.biz.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.trade.AutoDispatchBO;
import com.greatonce.oms.bo.trade.DispatchOrderCancelBO;
import com.greatonce.oms.bo.trade.DispatchOrderCancelDetailBO;
import com.greatonce.oms.bo.trade.ManualDeliveryBO;
import com.greatonce.oms.bo.trade.ManualDispatchBO;
import com.greatonce.oms.bo.trade.OfflineDeliveryBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.trade.WmsExpressNoticeBo;
import com.greatonce.oms.bo.trade.WmsLogBO;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.query.trade.DispatchOrderQuery;
import java.util.Collection;
import java.util.List;

/**
 * 配货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface DispatchOrderService extends BizService<DispatchOrder, DispatchOrderQuery> {

  /**
   * 获取简单信息.
   */
  DispatchOrder getSimpleInfo(Long dispatchOrderId);

  /**
   * 根据单号获取配货单.
   */
  DispatchOrder getByCode(String dispatchOrderCode);

  /**
   * 获取订单对应的配货单.
   */
  List<DispatchOrder> listBySalesOrderId(Long salesOrderId);

  /**
   * 获取订单对应的配货单.
   */
  List<DispatchOrder> listBySalesOrderId(List<Long> salesOrderId);

  /**
   * 获取订单明细对应的配货单.
   */
  List<DispatchOrder> listBySalesOrderDetailId(Long salesOrderId, Collection<Long> salesOrderIds);

  /**
   * 取消（如果通知了WMS，先调用WMS取消）.
   *
   * @param dispatchOrder 配货单
   * @param bo 取消对象
   */
  void cancel(DispatchOrder dispatchOrder, DispatchOrderCancelBO bo);

  /**
   * 取消明细.
   *
   * @param dispatchOrder 配货单
   * @param bo 明细对象
   */
  void cancel(DispatchOrder dispatchOrder, DispatchOrderCancelDetailBO bo);

  /**
   * OMS取消.
   */
  void cancelOms(DispatchOrder dispatchOrder, DispatchOrderCancelBO bo);

  /**
   * 手工发货.
   *
   * @param dispatchOrder 配货单
   * @param bo 手工发货对象
   */
  void manualDelivery(DispatchOrder dispatchOrder, ManualDeliveryBO bo);

  /**
   * wms发货.
   *
   * @param dispatchOrder 配货单
   * @param bo wms自动发货对象
   */
  void wmsAutoDelivery(DispatchOrder dispatchOrder, WmsAutoOutBO bo);


  /**
   * 通知wms.
   */
  void noticeWms(DispatchOrder dispatchOrder);


  /**
   * 手工配货.
   */
  void manualDispatch(SalesOrder salesOrder, ManualDispatchBO bo);


  /**
   * 自动配货.
   */
  void autoDispatch(DispatchOrder dispatchOrder, AutoDispatchBO bo);

  /**
   * 根据条件获取配货单.
   */
  PageList<DispatchOrder> listPageByConditions(DispatchOrderQuery filter, int page, int pageSize);

  /**
   * 线下配货.
   */
  void offlineDelivery(SalesOrder salesOrder, OfflineDeliveryBO bo);

  /**
   * wms物流通知.
   */
  void wmsExpressNotice(DispatchOrder dispatchOrder, WmsExpressNoticeBo bo);

  /**
   * 简单更新配货单.
   */
  void sinpleUpdate(DispatchOrder updateInfo);

  /**
   * 重新过账.
   */
  void reposting(DispatchOrder dispatchOrder);

  /**
   * 获取电子面单.
   */
  void getWayBill(DispatchOrder dispatchOrder, Express express);

  List<WmsLogBO> listWmsLog(Long dispatchOrderId);

}