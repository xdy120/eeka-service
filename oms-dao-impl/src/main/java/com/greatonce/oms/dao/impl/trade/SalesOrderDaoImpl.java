package com.greatonce.oms.dao.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.SalesOrderDao;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * SalesOrder <br/>
 * 销售订单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class SalesOrderDaoImpl extends AbstractOmsDao<SalesOrder, SalesOrderQuery> implements
    SalesOrderDao {

  private static final String LIST_SUB_INFO = "listSubInfo";
  private static final String LIST_SUB_INFO_PAGE = "listSubInfoPage";
  private static final String GET_SUB_INFO = "getSubInfo";
  private static final String GET_SIMPLE_INFO = "getSimpleInfo";
  private static final String GET_DISPATCH_INFO = "getDispatchInfo";
  private static final String LIST_WAITING_DISPATCH_ORDER = "listWaitingDispatchByMD5";

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.trade.SalesOrderMapper";
  }

  @Override
  public SalesOrder getSubInfo(Long id) {
    return getSqlSessionDecorator().selectOne(getStatement(GET_SUB_INFO), id);
  }

  @Override
  public SalesOrder getSimpleInfo(Long id) {
    return getSqlSessionDecorator().selectOne(getStatement(GET_SIMPLE_INFO), id);
  }

  @Override
  public List<SalesOrder> listSubInfo(SalesOrderQuery filter) {
    return getSqlSessionDecorator().selectList(getStatement(LIST_SUB_INFO), filter);
  }

  @Override
  public PageList<SalesOrder> listSubInfoPage(SalesOrderQuery filter, int page, int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement(LIST_SUB_INFO_PAGE), filter, page, pageSize);
  }

  @Override
  public int updateHold(SalesOrder order) {
    return getSqlSessionDecorator().update(getStatement("updateHold"), order);
  }

  @Override
  public int updateUrgent(SalesOrder order) {
    return getSqlSessionDecorator().update(getStatement("updateUrgent"), order);
  }

  @Override
  public int updateManual(SalesOrder order) {
    return getSqlSessionDecorator().update(getStatement("updateManual"), order);
  }

  @Override
  public int updateVersion(Long salesOrderId, Integer version) {
    SalesOrder salesOrder = new SalesOrder();
    salesOrder.setSalesOrderId(salesOrderId);
    salesOrder.setVersion(version);
    return update(salesOrder);
  }

  @Override
  public SalesOrder getDispatchInfo(Long salesOrderId) {
    return getSqlSessionDecorator().selectOne(getStatement(GET_DISPATCH_INFO), salesOrderId);
  }

  @Override
  public List<SalesOrder> listWaitingDispatchByMD5(String mergeMD5) {
    return getSqlSessionDecorator().selectList(getStatement(LIST_WAITING_DISPATCH_ORDER), mergeMD5);
  }

  @Override
  public List<SalesOrder> listDetail(SalesOrderQuery filter) {
    return getSqlSessionDecorator().selectList(getStatement("listDetail"), filter);
  }

  @Override
  public List<Long> listMergeOrderId(String mergeFlag) {
    return getSqlSessionDecorator().selectList(getStatement("listMergeOrderId"), mergeFlag);
  }

  @Override
  public PageList<SalesOrder> stockOccupancyDetail(StockQuery stockQuery, int pageNo,
      int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("stockOccupancyDetail"), stockQuery, pageNo, pageSize);
  }

  @Override
  public List<Long> listOOSRedispatchSalesOrderIds() {
    return getSqlSessionDecorator().selectList(getStatement("listOOSRedispatchIds"));
  }

  @Override
  public void clearOos() {
    getSqlSessionDecorator().update(getStatement("clearOos"));
  }

  @Override
  public List<SalesOrder> listMallDeliveryAbnormalOrders() {
    return getSqlSessionDecorator().selectList(getStatement("listMallDeliveryAbnormalOrders"));
  }

  @Override
  public List<SalesOrder> listForBatch(SalesOrderQuery filter) {
    return getSqlSessionDecorator().selectList(getStatement("listForBatch"), filter);
  }

  @Override
  public Long getSalesOrderIdByStoreIdAndTradeId(Long storeId, String tradeId) {
    Map<String, Object> map = new HashMap<>(2);
    map.put("storeId", storeId);
    map.put("tradeId", tradeId);
    return getSqlSessionDecorator()
        .selectOne(getStatement("getSalesOrderIdByStoreIdAndTradeId"), map);
  }

  @Override
  public int updateSuggestExpress(SalesOrder update) {
    return getSqlSessionDecorator().update(getStatement("updateSuggestExpress"), update);
  }
}