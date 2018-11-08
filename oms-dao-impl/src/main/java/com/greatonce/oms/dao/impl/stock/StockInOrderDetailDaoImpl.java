package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockInOrderDetailDao;
import com.greatonce.oms.domain.stock.StockInOrderDetail;
import com.greatonce.oms.query.stock.StockInOrderDetailQuery;
import java.util.Collection;
import org.springframework.stereotype.Repository;

/**
 * StockInOrderDetail <br/> 入库单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class StockInOrderDetailDaoImpl extends
    AbstractOmsDao<StockInOrderDetail, StockInOrderDetailQuery> implements StockInOrderDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockInOrderDetailMapper";
  }
}