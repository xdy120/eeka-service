package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.ExpressWmsMappingService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.ExpressWmsMappingDao;
import com.greatonce.oms.domain.base.ExpressWmsMapping;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.ExpressWmsMappingQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * ExpressWmsMapping <br/> 仓库快递映射.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ExpressWmsMappingServiceImpl extends
    AbstractService<ExpressWmsMapping, ExpressWmsMappingQuery> implements
    ExpressWmsMappingService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_EXPRESS_WMS_MAPPING);

  private static final String CACHE_NAME = "EXPRESS";

  @Autowired
  private ExpressWmsMappingDao dao;

  @Override
  protected QueryDao<ExpressWmsMapping, ExpressWmsMappingQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  /**
   * 获取快递映射.
   *
   * @param outerCode not null
   * @return ExpressWmsMapping
   */
  public ExpressWmsMapping getByOuterCode(String outerCode) {
    ExpressWmsMapping eg = new ExpressWmsMapping();
    eg.setOuterCode(outerCode);
    return getByExample(eg);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int modify(ExpressWmsMapping entity) {
    return super.modify(entity);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int remove(ExpressWmsMapping entity) {
    return super.remove(entity);
  }
}