package com.greatonce.oms.dao.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.stock.DispatchStockInfoBO;
import com.greatonce.oms.bo.stock.StockCheckResultBO;
import com.greatonce.oms.bo.stock.StockQueryBO;
import com.greatonce.oms.bo.stock.StockRatio;
import com.greatonce.oms.bo.stock.StockSumBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockDao;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.query.stock.StockQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * 库存.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class StockDaoImpl extends AbstractOmsDao<Stock, StockQuery> implements StockDao {

  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockMapper";
  }

  @Override
  public int adjustQuantity(Long skuId, Long virtualWarehouseId, int quantity) {
    Stock stock = new Stock();
    stock.setSkuId(skuId);
    stock.setVirtualWarehouseId(virtualWarehouseId);
    stock.setQuantity(quantity);
    return getSqlSessionDecorator().update(getStatement("adjustQuantity"), stock);
  }

  @Override
  public int coverQuantity(Long skuId, Long virtualWarehouseId, int quantity) {
    Stock stock = new Stock();
    stock.setSkuId(skuId);
    stock.setVirtualWarehouseId(virtualWarehouseId);
    stock.setQuantity(quantity);
    return getSqlSessionDecorator().update(getStatement("coverQuantity"), stock);
  }

  @Override
  public StockCheckResultBO getStockCheckResult(Long skuId, Long virtualWarehouseId,
      LocalDateTime sortTime) {
    Map<String, Object> map = new HashMap<>();
    map.put("skuId", skuId);
    map.put("virtualWarehouseId", virtualWarehouseId);
    map.put("sortTime", sortTime);
    return getSqlSessionDecorator().selectOne(getStatement("getStockCheckResult"), map);
  }

  @Override
  public PageList<StockQueryBO> queryStock(StockQuery stockQuery, int page, int pageSize) {
    Map<String, Object> params = new HashMap<>(3);
    params.put("offset", (page - 1) * pageSize);
    params.put("rows", pageSize);
    params.put("query", stockQuery);
    int total = 0;
    if (page == 1) {
      total = getSqlSessionDecorator().selectOne(getStatement("countStock"), params);
      if (total == 0) {
        return new PageList<>(pageSize, page, 0, null);
      }
    }
    final List<StockQueryBO> list = getSqlSessionDecorator()
        .selectList(getStatement("queryStock"), params);
    return new PageList<>(pageSize, page, total, list);
  }

  @Override
  public List<DispatchStockInfoBO> listDispatchStock(Collection<Long> skuIds,
      Collection<Long> virtualWarehouseIds, LocalDateTime paidTime) {
    Map<String, Object> map = new HashMap<>(3);
    map.put("skuIds", skuIds);
    map.put("virtualWarehouseIds", virtualWarehouseIds);
    map.put("sortTime", paidTime);
    return getSqlSessionDecorator().selectList(getStatement("listDispatchStock"), map);
  }

  @Override
  public StockCheckResultBO queryStockQuantity(Long skuId, Long virtualWarehouseId) {
    Map<String, Object> map = new HashMap<>();
    map.put("skuId", skuId);
    map.put("virtualWarehouseId", virtualWarehouseId);
    return getSqlSessionDecorator().selectOne(getStatement("getStockQuantityResult"), map);
  }

  @Override
  public List<StockRatio> queryStockRatio(List<Stock> stocks, Long type) {
    StockQuery stockQuery = new StockQuery();
    List<Long> list = new ArrayList<>();
    for (Stock s : stocks) {
      list.add(s.getSkuId());
    }
    stockQuery.setSkuIds(list);
    return null;
  }

  @Override
  public List<StockSumBO> listSkuStockInfo(Long skuId, List<Long> virtualWarehouseIds) {
    HashMap<String, Object> map = new HashMap<>(2);
    map.put("skuId", skuId);
    map.put("virtualWarehouseIds", virtualWarehouseIds);
    return getSqlSessionDecorator().selectList(getStatement("listSkuStockInfo"), map);
  }

  @Override
  public int transitIn(Long skuId, Long virtualWarehouseId, int quantity) {
    Stock stock = new Stock();
    stock.setSkuId(skuId);
    stock.setVirtualWarehouseId(virtualWarehouseId);
    stock.setQuantity(quantity);
    stock.setTransitQuantity(-quantity);
    return getSqlSessionDecorator().update(getStatement("transitIn"), stock);
  }

  @Override
  public int adjustTransitQuantity(Long skuId, Long virtualWarehouseId, int quantity) {
    Stock stock = new Stock();
    stock.setSkuId(skuId);
    stock.setVirtualWarehouseId(virtualWarehouseId);
    stock.setTransitQuantity(quantity);
    return getSqlSessionDecorator().update(getStatement("adjustTransitQuantity"), stock);
  }

}