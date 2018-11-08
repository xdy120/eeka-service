package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.query.trade.SalesOrderDetailQuery;
import java.util.Collection;
import java.util.List;

/**
 * SalesOrderDetail <br/>
 * 销售订单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesOrderDetailService extends
    DetailService<SalesOrder, SalesOrderDetail, SalesOrderDetailQuery> {

  /**
   * 获取所有明细
   */
  List<SalesOrderDetail> listBySalesOrderId(Long salesOrderId);

  /**
   * 获取明细简要信息
   */
  List<SalesOrderDetail> listSimpleBySalesOrderId(Long salesOrderId);

  /**
   * 获取普通明细
   */
  List<SalesOrderDetail> listNormalBySalesOrderId(Long salesOrderId);

  /**
   * 获取普通明细简要信息
   */
  List<SalesOrderDetail> listSimpleNormal(Long salesOrderId);

  /**
   * 获取子订单明细
   */
  List<SalesOrderDetail> listByMallDetailId(Long salesOrderId, String mallDetailId);

  /**
   * 确认明细退款
   *
   * @param salesOrder 订单
   * @param detail 商品明细
   */
  void refund(SalesOrder salesOrder, SalesOrderDetail detail);

  /**
   * 确认多个明细退款
   *
   * @param salesOrder 订单
   * @param details 商品明细
   */
  void refund(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details);

  /**
   * 申请明细退款
   *
   * @param salesOrder 订单
   * @param detail 商品明细
   */
  void applyRefund(SalesOrder salesOrder, SalesOrderDetail detail);

  /**
   * 申请多个明细退款
   *
   * @param salesOrder 订单
   * @param details 商品明细
   */
  void applyRefund(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details);

  /**
   * 取消明细退款
   *
   * @param salesOrder 订单
   * @param detail 商品明细
   */
  void cancelRefund(SalesOrder salesOrder, SalesOrderDetail detail);

  /**
   * 取消多个明细退款
   *
   * @param salesOrder 订单
   * @param details 商品明细
   */
  void cancelRefund(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details);

  /**
   * 整单退款
   *
   * @param salesOrder 订单
   */
  void refund(SalesOrder salesOrder);

  /**
   * 取消整单退款
   *
   * @param salesOrder 订单
   */
  void cancelRefund(SalesOrder salesOrder);

  /**
   * 作废明细
   *
   * @param salesOrder 订单
   * @param detail 商品明细
   */
  void invalid(SalesOrder salesOrder, SalesOrderDetail detail);

  /**
   * 整单作废
   *
   * @param salesOrder 订单
   */
  void invalid(SalesOrder salesOrder);

  /**
   * 替换商品
   *
   * @param source 原商品
   * @param target 新商品
   */
  void replace(SalesOrder salesOrder, SalesOrderDetail source, SalesOrderDetail target);

  /**
   * 替换商品
   *
   * @param source 原商品
   * @param targets 新商品集合
   */
  void replace(SalesOrder salesOrder, SalesOrderDetail source, List<SalesOrderDetail> targets);


  /**
   * 删除赠品
   *
   * @param source 原赠品
   */
  void delete(SalesOrder salesOrder, SalesOrderDetail source);

  /**
   * 重置
   */
  void reset(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details);

  /**
   * 仓库发货
   */
  void wmsDelivery(SalesOrder salesOrder, Collection<? extends Long> detailIds);

  /**
   * 获取订单明细  未配货 普通明细 未退款
   */
  List<SalesOrderDetail> getSalesOrderDetails(Long salesOrderId);


  /**
   * 配货
   */
  void dispatch(Collection<? extends SalesOrderDetail> details);

  /**
   * 取消配货
   */
  void cancelDispatch(SalesOrder salesOrder, Collection<? extends SalesOrderDetail> details);

  /**
   * 获取平台发货明细.
   */
  List<SalesOrderDetail> listToMallDeliveryDetailsInfo(Long salesOrderId);

  /**
   * 清除缺货标记.
   */
  void clearOos();

  /**
   * 获取可配货明细.
   */
  List<SalesOrderDetail> listDispatchableBySalesOrderId(Long salesOrderId);
}