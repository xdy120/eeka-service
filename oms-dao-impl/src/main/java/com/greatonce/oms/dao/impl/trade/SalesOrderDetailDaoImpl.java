package com.greatonce.oms.dao.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.SalesOrderDetailDao;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.query.trade.SalesOrderDetailQuery;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * SalesOrderDetail <br/>
 * 销售订单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class SalesOrderDetailDaoImpl extends
    AbstractOmsDao<SalesOrderDetail, SalesOrderDetailQuery> implements SalesOrderDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.trade.SalesOrderDetailMapper";
  }

  @Override
  public int replace(Map<String, SalesOrderDetail> map) {
    return getSqlSessionDecorator().update(getStatement("replaceBySku"), map);
  }

  @Override
  public List<SalesOrderDetail> listSimple(SalesOrderDetailQuery example) {
    return getSqlSessionDecorator().selectList(getStatement("listSimple"), example);
  }

  /**
   * 获取未退款的普通订单
   */
  @Override
  public List<SalesOrderDetail> listSimpleNormal(Long salesOrderId) {
    return getSqlSessionDecorator().selectList(getStatement("listSimpleNormal"), salesOrderId);
  }

  @Override
  public List<SalesOrderDetail> listToMallDeliveryDetailsInfo(Long salesOrderId) {
    return getSqlSessionDecorator()
        .selectList(getStatement("listToMallDeliveryDetailsInfo"), salesOrderId);
  }

  @Override
  public void clearOos() {
    getSqlSessionDecorator().update(getStatement("clearOos"));
  }

  @Override
  public List<SalesOrderDetail> listDispatchableBySalesOrderId(Long salesOrderId) {
    return getSqlSessionDecorator().selectList("listDispatchableBySalesOrderId", salesOrderId);
  }

  @Override
  public List<SalesOrderDetail> list(SalesOrderDetailQuery salesOrderDetailQuery) {
    return getSqlSessionDecorator().selectList(getStatement("listExisting"), salesOrderDetailQuery);
  }

  @Override
  public List<SalesOrderDetail> listExample(SalesOrderDetail salesOrderDetail) {
    return getSqlSessionDecorator()
        .selectList(getStatement("listByExampleExisting"), salesOrderDetail);
  }

  @Override
  public PageList<SalesOrderDetail> listPage(SalesOrderDetailQuery salesOrderDetailQuery, int page,
      int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("listPageExisting"), salesOrderDetailQuery, page, pageSize);
  }

  @Override
  public PageList<SalesOrderDetail> listExamplePage(SalesOrderDetail salesOrderDetail, int page,
      int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("listPageByExampleExisting"), salesOrderDetail, page, pageSize);
  }

  @Override
  public List<SalesOrderDetail> listField(String fields,
      SalesOrderDetailQuery salesOrderDetailQuery) {
    return getSqlSessionDecorator()
        .selectList(getStatement("listFieldExisting"), salesOrderDetailQuery);
  }

  @Override
  public PageList<SalesOrderDetail> listFieldPage(String fields,
      SalesOrderDetailQuery salesOrderDetailQuery, int page, int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("listPageFieldExisting"), salesOrderDetailQuery, page, pageSize);
  }
}