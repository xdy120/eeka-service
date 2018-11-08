package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockLoanInDetailDao;
import com.greatonce.oms.domain.stock.StockLoanInDetail;
import com.greatonce.oms.query.stock.StockLoanInDetailQuery;
import java.util.Collection;
import org.springframework.stereotype.Repository;

/**
 * 还入单明细. StockLoanInDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockLoanInDetailDaoImpl extends
    AbstractOmsDao<StockLoanInDetail, StockLoanInDetailQuery> implements StockLoanInDetailDao {

  /**
   * 获取XML名称.
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockLoanInDetailMapper";
  }
}