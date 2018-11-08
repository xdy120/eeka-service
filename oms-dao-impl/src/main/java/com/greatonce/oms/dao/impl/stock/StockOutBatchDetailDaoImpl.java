package com.greatonce.oms.dao.impl.stock;

import org.springframework.stereotype.Repository;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockOutBatchDetailDao;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.query.stock.StockOutBatchDetailQuery;

/**
 * 出库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockOutBatchDetailDaoImpl extends AbstractOmsDao<StockOutBatchDetail,StockOutBatchDetailQuery> implements StockOutBatchDetailDao {

  /**
   * 获取XML名称.
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockOutBatchDetailMapper";
  }

}