package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockLoanInDao;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.query.stock.StockLoanInQuery;
import org.springframework.stereotype.Repository;

/**
 * 还入单. StockLoanIn <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockLoanInDaoImpl extends AbstractOmsDao<StockLoanIn, StockLoanInQuery> implements
    StockLoanInDao {

  /**
   * 获取XML名称.
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockLoanInMapper";
  }
}