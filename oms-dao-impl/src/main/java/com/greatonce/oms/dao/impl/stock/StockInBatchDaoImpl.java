package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockInBatchDao;
import com.greatonce.oms.domain.stock.StockInBatch;
import com.greatonce.oms.query.stock.StockInBatchQuery;
import org.springframework.stereotype.Repository;

/**
 * StockInBatch <br/>
 * 入库批次表
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

@Repository
public class StockInBatchDaoImpl extends AbstractOmsDao<StockInBatch, StockInBatchQuery> implements
    StockInBatchDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockInBatchMapper";
  }

}