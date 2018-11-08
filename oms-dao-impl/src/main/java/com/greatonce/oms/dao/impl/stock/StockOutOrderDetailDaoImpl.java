package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockOutOrderDetailDao;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.query.stock.StockOutOrderDetailQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * StockOutOrderDetail <br/> 出库单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class StockOutOrderDetailDaoImpl extends
    AbstractOmsDao<StockOutOrderDetail, StockOutOrderDetailQuery> implements
    StockOutOrderDetailDao {


  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockOutOrderDetailMapper";
  }

  @Override
  public List<StockOutOrderDetail> listAvailable(Long stockOutOrderId) {
    return getSqlSessionDecorator().selectList(getStatement("listAvailable"), stockOutOrderId);
  }

  @Override
  public List<StockOutOrderDetail> listSaleable(Long stockOutOrderId) {
    return this.getSqlSessionDecorator()
        .selectList(this.getStatement("listSaleable"), stockOutOrderId);
  }
}