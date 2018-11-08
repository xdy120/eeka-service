package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.ExpressRegionService;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.ExpressWmsMappingService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.dao.base.ExpressDao;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.ExpressRegion;
import com.greatonce.oms.domain.base.ExpressWmsMapping;
import com.greatonce.oms.domain.base.WaybillSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.ExpressQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * Express <br/> 快递.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class ExpressServiceImpl extends AbstractEnableService<Express, ExpressQuery> implements
    ExpressService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_EXPRESS);
  private static final String CACHE_NAME = "EXPRESS";
  @Autowired
  private ExpressDao dao;
  @Autowired
  private ExpressWmsMappingService expressWmsMappingService;

  @Override
  protected QueryDao<Express, ExpressQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(Express entity) {
    super.initDefaultValue(entity);
    if (entity.getOrderId() == null) {
      entity.setOrderId(0);
    }
    entity.setEnable(true);
  }

  @Override
  public int create(Express entity) {
    return super.create(entity);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'ID_'+#id")
  public Express getByKey(Long id) {
    Express express = dao.getByKey(id);
    if(!Assert.isEmpty(express.getWaybillSetting())){
      express.setSetting(JsonUtil.toObject(express.getWaybillSetting(), WaybillSetting.class));
    }
    return express;
  }

  @Override
  protected void validateCreate(Express entity) {
    Express exists = getEffectiveByCode(entity.getExpressCode());
    Assert.notTrue(exists == null, "快递编码已存在");
  }

  @Override
  protected void validateModify(Express entity) {
    Express exists = getEffectiveByCode(entity.getExpressCode());
    Assert
        .notTrue(exists == null || entity.getExpressId().equals(exists.getExpressId()), "快递编码已存在");
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int modify(Express entity) {
    return super.modify(entity);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int remove(Express entity) {
    return super.remove(entity);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'WMS_MAP_'+#wmsAppId+#wmsExpressCode")
  public Express getByWmsExpressCode(Long wmsAppId, String wmsExpressCode) {
    ExpressWmsMapping eg = new ExpressWmsMapping();
    eg.setWmsAppId(wmsAppId);
    eg.setOuterCode(wmsExpressCode);
    ExpressWmsMapping mapping = expressWmsMappingService.getByExample(eg);
    if (mapping == null) {
      return getEffectiveByCode(wmsExpressCode);
    }
    return getByKey(mapping.getExpressId());
  }

  /**
   * 获取快递.
   *
   * @param expressCode not null
   * @return Express
   */
  @CacheableNotNull(value = CACHE_NAME, key = "'CODE_'+#expressCode")
  public Express getEffectiveByCode(String expressCode) {
    Express eg = new Express();
    eg.setExpressCode(expressCode);
    eg.setEnable(true);
    return getByExample(eg);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'NAME_'+#name")
  public Express getByName(String name) {
    Express eg = new Express();
    eg.setExpressName(name);
    eg.setEnable(true);
    return getByExample(eg);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'MAP_WMS_'+#wmsAppId+#expressId")
  public String getWmsExpressCode(Long wmsAppId, Long expressId) {
    Express express = getByKey(expressId);
    ExpressWmsMapping eg = new ExpressWmsMapping();
    eg.setWmsAppId(wmsAppId);
    eg.setExpressId(expressId);
    ExpressWmsMapping mapping = expressWmsMappingService.getByExample(eg);
    if (mapping == null) {
      return express.getExpressCode();
    }
    return mapping.getOuterCode();
  }
}