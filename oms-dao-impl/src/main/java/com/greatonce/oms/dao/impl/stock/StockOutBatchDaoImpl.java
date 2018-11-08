package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockOutBatchDao;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.query.stock.StockOutBatchQuery;
import org.springframework.stereotype.Repository;

/**
 * StockOutBatch <br/>
 * 出库批次表
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

@Repository
public class StockOutBatchDaoImpl extends
    AbstractOmsDao<StockOutBatch, StockOutBatchQuery> implements StockOutBatchDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockOutBatchMapper";
  }

}