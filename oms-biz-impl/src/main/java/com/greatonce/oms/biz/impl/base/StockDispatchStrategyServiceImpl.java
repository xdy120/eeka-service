package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.ExpressStrategyService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.dao.base.StockDispatchStrategyDao;
import com.greatonce.oms.domain.base.ExpressStrategy;
import com.greatonce.oms.domain.base.ExpressStrategyRule.StrategyExpress;
import com.greatonce.oms.domain.base.StockDispatchRule;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.StockDispatchStrategyQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * StockDispatchStrategy <br/> 库存配货策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-21
 */

@Service
public class StockDispatchStrategyServiceImpl extends
    AbstractEnableService<StockDispatchStrategy, StockDispatchStrategyQuery> implements
    StockDispatchStrategyService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_STOCK_DISPATCH_STRATEGY);

  private static final String CACHE_NAME = "STOCK_STRATEGY";

  @Autowired
  private StockDispatchStrategyDao dao;
  @Autowired
  private ExpressStrategyService expressStrategyService;

  @Override
  protected QueryDao<StockDispatchStrategy, StockDispatchStrategyQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(StockDispatchStrategy entity) {
    super.initDefaultValue(entity);
    entity.setEnable(true);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'DID_'+#entity.stockDispatchStrategyId"),
      @CacheEvict(value = CACHE_NAME, key = "'DIDE_'+#entity.stockDispatchStrategyId")
  })
  public int enable(StockDispatchStrategy entity) {
    return super.enable(entity);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'DID_'+#entity.stockDispatchStrategyId"),
      @CacheEvict(value = CACHE_NAME, key = "'DIDE_'+#entity.stockDispatchStrategyId")
  })
  public int disable(StockDispatchStrategy entity) {
    return super.disable(entity);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'DID_'+#id")
  public StockDispatchStrategy getByKey(Long id) {
    StockDispatchStrategy strategy = super.getByKey(id);
    strategy.setRule(JsonUtil.toObject(strategy.getSettingJson(), StockDispatchRule.class));
    Map<Long, ExpressStrategy> expressStrategyMap = new HashMap<>(1);
    for (StockDispatchWarehouse stockDispatchWarehouse : strategy.getRule().getWarehouses()) {
      ExpressStrategy expressStrategy =
          expressStrategyMap.get(stockDispatchWarehouse.getExpressStrategyId());
      if (expressStrategy == null) {
        expressStrategy =
            expressStrategyService.getByKey(stockDispatchWarehouse.getExpressStrategyId());
        if(!Assert.isEmpty(expressStrategy.getRule().getExpresses())){
          Collections.sort(expressStrategy.getRule().getExpresses());
        }
        expressStrategyMap.put(expressStrategy.getExpressStrategyId(), expressStrategy);
      }
      stockDispatchWarehouse.setExpressStrategy(expressStrategy);
    }
    Collections.sort(strategy.getRule().getWarehouses());
    return strategy;
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'DID_'+#record.stockDispatchStrategyId"),
      @CacheEvict(value = CACHE_NAME, key = "'DIDE_'+#record.stockDispatchStrategyId")
  })
  public int modify(StockDispatchStrategy record) {
    int count = dao.customUpdate(record);
    BIZ_LOGGER.log(record.getStockDispatchStrategyId(), BizLogger.UPDATE);
    return count;
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'DID_'+#record.stockDispatchStrategyId"),
      @CacheEvict(value = CACHE_NAME, key = "'DIDE_'+#record.stockDispatchStrategyId")
  })
  public int remove(StockDispatchStrategy record) {
    return super.remove(record);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'DIDE_'+#strategyId")
  public List<StrategyExpress> listExpresses(Long strategyId) {
    final StockDispatchStrategy strategy = getByKey(strategyId);
    Map<Long, StrategyExpress> map = new HashMap<>();
    for (StockDispatchWarehouse warehouse : strategy.getRule().getWarehouses()) {
      for (StrategyExpress express : warehouse.getExpressStrategy().getRule().getExpresses()) {
        if (!map.containsKey(express.getExpressId())) {
          map.put(express.getExpressId(), express);
        }
      }
    }
    return new ArrayList<>(map.values());
  }
}