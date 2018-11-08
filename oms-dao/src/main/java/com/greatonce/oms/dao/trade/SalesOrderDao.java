package com.greatonce.oms.dao.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import java.util.List;

/**
 * 销售订单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesOrderDao extends QueryDao<SalesOrder, SalesOrderQuery> {

  /**
   * 获取带SUB信息的订单.
   *
   * @param id 订单ID
   * @return SalesOrder
   */
  SalesOrder getSubInfo(Long id);

  /**
   * 获取带简单信息.
   *
   * @param id 订单ID
   * @return SalesOrder
   */
  SalesOrder getSimpleInfo(Long id);

  /**
   * 获取带SUB信息的订单.
   *
   * @param filter 过滤条件
   * @return List<SalesOrder>
   */
  List<SalesOrder> listSubInfo(SalesOrderQuery filter);

  /**
   * 获取带SUB信息的订单.
   *
   * @param filter 过滤条件
   * @param page 页码
   * @param pageSize 页大小
   * @return PageList<SalesOrder>
   */
  PageList<SalesOrder> listSubInfoPage(SalesOrderQuery filter, int page, int pageSize);

  /**
   * 更新留单.
   *
   * @param order 订单，需要设置hold与holdDate
   * @return int
   */
  int updateHold(SalesOrder order);

  /**
   * 更新加急.
   *
   * @param order 订单，需要设置urgent
   * @return int
   */
  int updateUrgent(SalesOrder order);

  /**
   * 更新手工处理.
   *
   * @param order 订单，需要设置manual
   * @return int
   */
  int updateManual(SalesOrder order);

  /**
   * 更新版本.
   *
   * @param salesOrderId 订单ID
   * @param version 版本
   */
  int updateVersion(Long salesOrderId, Integer version);

  /**
   * 获取用于配货的信息.
   */
  SalesOrder getDispatchInfo(Long salesOrderId);

  /**
   * 用MD5合单码获取待配货的订单.
   */
  List<SalesOrder> listWaitingDispatchByMD5(String mergeMD5);

  List<SalesOrder> listDetail(SalesOrderQuery filter);

  /**
   * 根据合单标记返回待合单id.
   */
  List<Long> listMergeOrderId(String mergeFlag);

  /**
   * 库存占用销售单明细.
   */
  PageList<SalesOrder> stockOccupancyDetail(StockQuery stockQuery, int pageNo, int pageSize);

  /**
   * 查询缺货可重配的订单id.
   */
  List<Long> listOOSRedispatchSalesOrderIds();

  /**
   * 清除缺货标记.
   */
  void clearOos();

  /**
   * 查询疑似平台未回传订单.
   */
  List<SalesOrder> listMallDeliveryAbnormalOrders();

  /**
   * 根据es搜索结果，查询订单列表.
   */
  List<SalesOrder> listForBatch(SalesOrderQuery filter);

  /**
   * 根据店铺id和交易号获取订单id.
   */
  Long getSalesOrderIdByStoreIdAndTradeId(Long storeId, String tradeId);

  /**
   * 更新推荐仓库
   */
  int updateSuggestExpress(SalesOrder update);
}