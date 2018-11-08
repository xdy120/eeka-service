package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.query.stock.StockOccupancyQuery;

/**
 * StockOccupancy <br/>
 * 库存占用
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface StockOccupancyDao extends QueryDao<StockOccupancy, StockOccupancyQuery> {

  /**
   * 调整占用数量，此方法是在原数量上调整
   */
  int adjustQuantity(StockOccupancy occupancy);

  /**
   * 更新占用数量,覆盖SKU与数量
   */
  int updateQuantity(StockOccupancy occupancy);

  /**
   * 更新占用仓库
   */
  int updateOccupancy(StockOccupancy occupancy);
}