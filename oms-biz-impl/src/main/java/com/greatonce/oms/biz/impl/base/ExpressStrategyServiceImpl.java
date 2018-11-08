package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.ExpressStrategyService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.ExpressStrategyDao;
import com.greatonce.oms.domain.base.ExpressStrategy;
import com.greatonce.oms.domain.base.ExpressStrategyRule;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.ExpressStrategyQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * ExpressStrategy <br/> 快递策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-05-12
 */

@Service
public class ExpressStrategyServiceImpl extends
    AbstractService<ExpressStrategy, ExpressStrategyQuery> implements ExpressStrategyService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_EXPRESS_STRATEGY);
  private static final String CACHE_NAME = "EXPRESS_STRATEGY";
  private static final String STOCK_STRATEGY_CACHE_NAME = "STOCK_STRATEGY";


  @Autowired
  private ExpressStrategyDao dao;

  @Override
  protected QueryDao<ExpressStrategy, ExpressStrategyQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(ExpressStrategy entity) {
    super.initDefaultValue(entity);
    if (Assert.isEmpty(entity.getSettingJson())) {
      entity.setSettingJson("{}");
    }
  }

  @Override
  @CacheEvict(value = {CACHE_NAME, STOCK_STRATEGY_CACHE_NAME}, allEntries = true)
  public int modify(ExpressStrategy record) {
    return update(record);
  }

  @Override
  @CacheEvict(value = {CACHE_NAME, STOCK_STRATEGY_CACHE_NAME}, allEntries = true)
  public int remove(ExpressStrategy record) {
    return delete(record.getExpressStrategyId());
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'ID_'+#id")
  public ExpressStrategy getByKey(Long id) {
    ExpressStrategy expressStrategy = super.getByKey(id);
    expressStrategy
        .setRule(JsonUtil.toObject(expressStrategy.getSettingJson(), ExpressStrategyRule.class));
    return expressStrategy;
  }
}