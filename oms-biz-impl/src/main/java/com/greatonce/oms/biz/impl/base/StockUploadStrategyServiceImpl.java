package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.StockUploadStrategyService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.StockUploadStrategyDao;
import com.greatonce.oms.domain.base.StockUploadRule;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.domain.base.StockUploadWarehouse;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.StockUploadStrategyQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * StockUploadStrategy <br/> 库存上传策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-21
 */

@Service
public class StockUploadStrategyServiceImpl extends
    AbstractService<StockUploadStrategy, StockUploadStrategyQuery> implements
    StockUploadStrategyService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_STOCK_UPLOAD_STRATEGY);

  private static final String CACHE_NAME = "STOCK_STRATEGY";

  @Autowired
  private StockUploadStrategyDao dao;

  @Override
  protected QueryDao<StockUploadStrategy, StockUploadStrategyQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'WAREHOUSE_UID_'+#id")
  public Map<Long, StockUploadWarehouse> getSettingStockUploadWarehouseMap(Long id) {
    StockUploadStrategy config = getByKey(id);
    Map<Long, StockUploadWarehouse> warehouseMap = new HashMap<>();
    if (null != config && null != config.getRule().getWarehouses()) {
      config.getRule().getWarehouses().forEach(x -> warehouseMap.put(x.getVirtualWarehouseId(), x));
    }
    return warehouseMap;
  }


  @Override
  public Set<Long> getUploadWarehouseIds() {
    List<StockUploadStrategy> list = dao.listByIsUploadIsManualUpload();
    Set<Long> warehouseIds = new HashSet<>();
    list.forEach(
        x -> x.getRule().getWarehouses().forEach(w -> warehouseIds.add(w.getVirtualWarehouseId())));
    return warehouseIds;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'USID_'+#storeId")
  public List<StockUploadStrategy> listByStoreId(Long storeId) {
    StockUploadStrategy eg = new StockUploadStrategy();
    eg.setStoreId(storeId);
    List<StockUploadStrategy> strategies = dao.listExample(eg);
    if (!Assert.isEmpty(strategies)) {
      strategies
          .forEach(x -> x.setRule(JsonUtil.toObject(x.getSettingJson(), StockUploadRule.class)));
    }
    return strategies;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'UID_'+#id")
  public StockUploadStrategy getByKey(Long id) {
    StockUploadStrategy stockUpload = super.getByKey(id);
    stockUpload.setRule(JsonUtil.toObject(stockUpload.getSettingJson(), StockUploadRule.class));
    return stockUpload;
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'UID_'+#record.stockUploadStrategyId"),
      @CacheEvict(value = CACHE_NAME, key = "'USID_'+#record.storeId"),
      @CacheEvict(value = CACHE_NAME, key = "'WAREHOUSE_UID_'+#record.stockUploadStrategyId")
  })
  public int modify(StockUploadStrategy record) {
    return update(record);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'UID_'+#record.stockUploadStrategyId"),
      @CacheEvict(value = CACHE_NAME, key = "'USID_'+#record.storeId"),
      @CacheEvict(value = CACHE_NAME, key = "'WAREHOUSE_UID_'+#record.stockUploadStrategyId"),
  })
  public int remove(StockUploadStrategy record) {
    return super.remove(record);
  }
}