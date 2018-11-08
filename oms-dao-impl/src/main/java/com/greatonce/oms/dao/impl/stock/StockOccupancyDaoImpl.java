package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockOccupancyDao;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.query.stock.StockOccupancyQuery;
import org.springframework.stereotype.Repository;

/**
 * StockOccupancy <br/>
 * 库存占用
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class StockOccupancyDaoImpl extends
    AbstractOmsDao<StockOccupancy, StockOccupancyQuery> implements StockOccupancyDao {

  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockOccupancyMapper";
  }

  @Override
  public int adjustQuantity(StockOccupancy occupancy) {
    return getSqlSessionDecorator().update(getStatement("adjustQuantity"), occupancy);
  }

  @Override
  public int updateQuantity(StockOccupancy occupancy) {
    return getSqlSessionDecorator().update(getStatement("updateQuantity"), occupancy);
  }

  @Override
  public int updateOccupancy(StockOccupancy occupancy) {
    return getSqlSessionDecorator().update(getStatement("updateOccupancy"), occupancy);
  }
}