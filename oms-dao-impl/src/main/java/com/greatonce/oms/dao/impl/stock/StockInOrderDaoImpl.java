package com.greatonce.oms.dao.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockInOrderDao;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.query.stock.StockInOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * StockInOrder <br/>
 * 入库单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class StockInOrderDaoImpl extends AbstractOmsDao<StockInOrder, StockInOrderQuery> implements
    StockInOrderDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockInOrderMapper";
  }

  @Override
  public PageList<StockInOrder> listPage(StockInOrderQuery stockInOrderQuery, int page,
      int pageSize) {
    return this.getSqlSessionDecorator().selectList(this.getStatement("advanceQuery"), stockInOrderQuery, page, pageSize);
  }
}