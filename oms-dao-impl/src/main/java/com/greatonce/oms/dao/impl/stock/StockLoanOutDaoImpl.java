package com.greatonce.oms.dao.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.stock.StockLoanOutBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockLoanOutDao;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.query.stock.StockLoanOutQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Repository;

/**
 * 借出单. StockLoanOut <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockLoanOutDaoImpl extends AbstractOmsDao<StockLoanOut, StockLoanOutQuery> implements
    StockLoanOutDao {

  /**
   * 获取XML名称.
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockLoanOutMapper";
  }



  @Override
  public PageList<StockLoanOut> listPage(StockLoanOutQuery stockLoanOutQuery, int page,
      int pageSize) {
    return this.getSqlSessionDecorator()
        .selectList(this.getStatement("listCustomPage"), stockLoanOutQuery, page, pageSize);
  }

  @Override
  public PageList<StockLoanOutBO> listStatistics(StockLoanOutQuery stockLoanOutQuery, int page,
      int pageSize) {
    return this.getSqlSessionDecorator()
        .selectList(this.getStatement("listStatistics"), stockLoanOutQuery, page, pageSize);
  }

}