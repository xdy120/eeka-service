package com.greatonce.oms.dao.impl.stock;

import org.springframework.stereotype.Repository;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockInBatchDetailDao;
import com.greatonce.oms.domain.stock.StockInBatchDetail;
import com.greatonce.oms.query.stock.StockInBatchDetailQuery;

/**
 * 入库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockInBatchDetailDaoImpl extends AbstractOmsDao<StockInBatchDetail,StockInBatchDetailQuery> implements StockInBatchDetailDao {

  /**
   * 获取XML名称.
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockInBatchDetailMapper";
  }

}