package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.stock.DispatchStockInfoBO;
import com.greatonce.oms.bo.stock.StockCheckResultBO;
import com.greatonce.oms.bo.stock.StockQueryBO;
import com.greatonce.oms.bo.stock.StockRatio;
import com.greatonce.oms.bo.stock.StockSumBO;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.query.stock.StockQuery;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Stock <br/>
 * 库存
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface StockDao extends QueryDao<Stock, StockQuery> {

  PageList<StockQueryBO> queryStock(StockQuery stockQuery, int pageNo, int pageSize);

  int adjustQuantity(Long skuId, Long virtualWarehouseId, int quantity);

  int coverQuantity(Long skuId, Long virtualWarehouseId, int quantity);

  StockCheckResultBO getStockCheckResult(Long skuId, Long virtualWarehouseId,
      LocalDateTime sortTime);

  List<DispatchStockInfoBO> listDispatchStock(Collection<Long> skuIds,
      Collection<Long> virtualWarehouseIds, LocalDateTime paidTime);

  StockCheckResultBO queryStockQuantity(Long skuId, Long virtualWarehouseId);

  List<StockRatio> queryStockRatio(List<Stock> stocks, Long type);

  List<StockSumBO> listSkuStockInfo(Long skuId, List<Long> virtualWarehouseIds);

  /**
   * 调整在途数量.
   *
   * @param skuId 规格ID
   * @param virtualWarehouseId 虚拟仓ID
   * @param quantity 正+负—
   */
  int adjustTransitQuantity(Long skuId, Long virtualWarehouseId, int quantity);

  /**
   * 在途入库.
   *
   * @param skuId 规格ID
   * @param virtualWarehouseId 虚拟仓ID
   * @param quantity 入库数量
   */
  int transitIn(Long skuId, Long virtualWarehouseId, int quantity);

}