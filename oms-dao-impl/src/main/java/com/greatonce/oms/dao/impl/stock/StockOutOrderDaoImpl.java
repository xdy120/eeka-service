package com.greatonce.oms.dao.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.stock.StockOutExportBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockOutOrderDao;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.query.stock.StockOutOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * StockOutOrder <br/>
 * 出库单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class StockOutOrderDaoImpl extends
    AbstractOmsDao<StockOutOrder, StockOutOrderQuery> implements StockOutOrderDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockOutOrderMapper";
  }

  @Override
  public PageList<StockOutOrder> listPage(StockOutOrderQuery query, int page,
      int pageSize) {
    return getSqlSessionDecorator().selectList(getStatement("advanceQuery"), query, page, pageSize);
  }

  @Override
  public PageList<StockOutExportBO> exportListStockOut(StockOutOrderQuery query, Integer page,
      Integer pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("exportListStockOut"), query, page, pageSize);
  }
}