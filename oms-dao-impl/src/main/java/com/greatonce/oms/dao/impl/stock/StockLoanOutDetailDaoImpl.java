package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockLoanOutDetailDao;
import com.greatonce.oms.domain.stock.StockLoanOutDetail;
import com.greatonce.oms.query.stock.StockLoanOutDetailQuery;
import com.greatonce.oms.query.stock.StockLoanOutQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 借出单明细. StockLoanOutDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockLoanOutDetailDaoImpl extends
    AbstractOmsDao<StockLoanOutDetail, StockLoanOutDetailQuery> implements StockLoanOutDetailDao {

  /**
   * 获取XML名称.
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockLoanOutDetailMapper";
  }

  @Override
  public List<StockLoanOutDetail> listAvailable(Long stockLoanOutId) {
    return getSqlSessionDecorator().selectList(getStatement("listAvailable"), stockLoanOutId);
  }

  @Override
  public List<StockLoanOutDetail> listSaleable(Long stockLoanOutId) {
    return getSqlSessionDecorator().selectList(getStatement("listSaleable"), stockLoanOutId);
  }
}