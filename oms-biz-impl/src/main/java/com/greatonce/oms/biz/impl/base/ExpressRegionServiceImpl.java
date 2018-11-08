package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.ExpressRegionService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.ExpressRegionDao;
import com.greatonce.oms.domain.base.ExpressRegion;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.ExpressRegionQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * ExpressRegion <br/> 快递区域.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ExpressRegionServiceImpl extends
    AbstractService<ExpressRegion, ExpressRegionQuery> implements ExpressRegionService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_EXPRESS);
  private static final String CACHE_NAME = "EXPRESS_REGION";

  @Autowired
  private ExpressRegionDao dao;

  @Override
  protected QueryDao<ExpressRegion, ExpressRegionQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public void saveExpressRegions(Long expressId, List<ExpressRegion> regions) {
    Assert.notNull(expressId, "快递不能为空！");
    Assert.notEmpty(regions, "区域不能为空！");
    ExpressRegion eg = new ExpressRegion();
    eg.setExpressId(expressId);
    regions.forEach(this::initDefaultValue);
    getTransactionTemplate().execute(() -> {
      deleteByExample(eg);
      insertBatch(regions);
    });
    BIZ_LOGGER.log(expressId, "配置快递区域");
  }

  public List<ExpressRegion> listRegion(ExpressRegionQuery filter) {
    return dao.listRegion(filter);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'EXPRESS_ID_'+#expressId+'_REGION_ID_'+#regionId")
  public ExpressRegion getByRegionIdAndExpressId(Long regionId, Long expressId) {
    return dao.getByRegionIdAndExpressId(regionId, expressId);
  }
}