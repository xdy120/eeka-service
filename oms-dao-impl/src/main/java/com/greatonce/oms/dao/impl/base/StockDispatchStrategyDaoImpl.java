package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.StockDispatchStrategyDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.query.base.StockDispatchStrategyQuery;
import org.springframework.stereotype.Repository;

/**
 * StockDispatchStrategy <br/>
 * 库存配货策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockDispatchStrategyDaoImpl extends
    AbstractOmsDao<StockDispatchStrategy, StockDispatchStrategyQuery> implements
    StockDispatchStrategyDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.base.StockDispatchStrategyMapper";
  }

  @Override
  public int customUpdate(StockDispatchStrategy record) {
    return getSqlSessionDecorator().update(getStatement("customUpdate"), record);
  }
}