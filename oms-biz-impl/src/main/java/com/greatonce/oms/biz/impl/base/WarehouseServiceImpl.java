package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.dao.base.WarehouseDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.WarehouseQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;


/**
 * Warehouse <br/> 仓库.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class WarehouseServiceImpl extends
    AbstractEnableService<Warehouse, WarehouseQuery> implements
    WarehouseService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_WAREHOUSE);
  private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseServiceImpl.class);
  private static final String CACHE_NAME = "WAREHOUSE";

  @Autowired
  private WarehouseDao dao;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private WmsAppService wmsAppService;

  @Override
  protected QueryDao<Warehouse, WarehouseQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void validateCreate(Warehouse entity) {
    Assert.notTrue(this.getEffectiveByCode(entity.getWarehouseCode()) == null, "实体仓编码已存在");
  }

  @Override
  protected void validateModify(Warehouse entity) {
    Warehouse exists = this.getEffectiveByCode(entity.getWarehouseCode());
    Assert.notTrue(exists == null || exists.getWarehouseId().equals(entity.getWarehouseId()),
        "实体仓编码已存在");
  }

  @Override
  protected void initDefaultValue(Warehouse entity) {
    super.initDefaultValue(entity);
    entity.setEnable(true);
    entity.setMatchRegion(Assert.isTrue(entity.isMatchRegion()));
    if (!Assert.isEmpty(entity.getVirtualWarehouses())) {
      entity.getVirtualWarehouses().forEach(x -> {
        x.setWarehouseId(entity.getWarehouseId());
        x.setWarehouseName(entity.getWarehouseName());
      });
    }
  }

  @Override
  public int create(Warehouse record) {
    try {
      initDefaultValue(record);
      int count = getTransactionTemplate().executeWithResult(() -> {
        if (!Assert.isEmpty(record.getVirtualWarehouses())) {
          for (VirtualWarehouse virtualWarehouse : record.getVirtualWarehouses()) {
            virtualWarehouseService.create(virtualWarehouse);
          }
        }
        return insert(record);
      });
      BIZ_LOGGER.log(record.getWarehouseId(), BizLogger.ADD);
      return count;
    } catch (RuntimeException e) {
      LOGGER.error("仓库创建失败，{}", JsonUtil.toJson(record));
      LOGGER.error("仓库创建失败", e);
      throw new OmsException("仓库创建失败！");
    }
  }

  @Override
  @WarehouseCacheEvict
  public int modify(Warehouse warehouse) {
    return super.modify(warehouse);
  }

  @Override
  @WarehouseCacheEvict
  public int enable(Warehouse warehouse) {
    return super.enable(warehouse);
  }

  @Override
  @WarehouseCacheEvict
  public int disable(Warehouse warehouse) {
    return super.disable(warehouse);
  }

  @Override
  @WarehouseCacheEvict
  public int remove(Warehouse warehouse) {
    return delete(warehouse.getWarehouseId());
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'ID_'+#id")
  public Warehouse getByKey(Long id) {
    Warehouse warehouse = super.getByKey(id);
    if (warehouse != null && warehouse.getWmsAppId() != null) {
      warehouse.setWmsApp(wmsAppService.getByKey(warehouse.getWmsAppId()));
    }
    return warehouse;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'CODE_'+#warehouseCode")
  public Warehouse getEffectiveByCode(String warehouseCode) {
    Warehouse eg = new Warehouse();
    eg.setWarehouseCode(warehouseCode);
    eg.setEnable(true);
    Warehouse warehouse = getByExample(eg);
    if (warehouse != null && warehouse.getWmsAppId() != null) {
      warehouse.setWmsApp(wmsAppService.getByKey(warehouse.getWmsAppId()));
    }
    return warehouse;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'EFFECTIVE_ALL'")
  public Map<String, Warehouse> listEffective() {
    Warehouse eg = new Warehouse();
    eg.setEnable(true);
    List<Warehouse> list = listExample(eg);
    list.forEach(w -> w.setWmsApp(wmsAppService.getByKey(w.getWmsAppId())));
    return CollectionUtil.listToMap(list,
        x -> Assert.isEmpty(x.getOuterCode()) ? x.getWarehouseCode() : x.getOuterCode());
  }


  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'ID_'+#warehouse.warehouseId"),
      @CacheEvict(value = CACHE_NAME, key = "'CODE_'+#warehouse.warehouseCode"),
      @CacheEvict(value = CACHE_NAME, key = "'EFFECTIVE_ALL'")
  })
  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @interface WarehouseCacheEvict {

  }
}