package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.stock.StockLoanOutDetail;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.query.stock.StockLoanOutDetailQuery;
import com.greatonce.oms.query.stock.StockLoanOutQuery;
import com.greatonce.oms.query.stock.StockOutOrderQuery;
import java.util.List;
import java.util.Set;

/**
 * 借出单明细. StockLoanOutDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockLoanOutDetailDao extends
    QueryDao<StockLoanOutDetail, StockLoanOutDetailQuery> {

  List<StockLoanOutDetail> listAvailable(Long stockLoanOutId);

  List<StockLoanOutDetail> listSaleable(Long stockLoanOutId);
}
