package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.stock.StockLoanOutBO;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.query.stock.StockLoanOutQuery;

/**
 * 借出单. StockLoanOut <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockLoanOutDao extends QueryDao<StockLoanOut, StockLoanOutQuery> {

  PageList<StockLoanOutBO> listStatistics(StockLoanOutQuery stockLoanOutQuery, int page,
      int pageSize);

}
