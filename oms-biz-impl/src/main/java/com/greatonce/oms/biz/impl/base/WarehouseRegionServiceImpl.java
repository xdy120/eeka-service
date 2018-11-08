package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.WarehouseRegionService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.WarehouseRegionDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.base.WarehouseRegion;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.WarehouseRegionQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


/**
 * WarehouseRegion <br/> 仓库区域.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class WarehouseRegionServiceImpl extends
    AbstractService<WarehouseRegion, WarehouseRegionQuery> implements WarehouseRegionService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_WAREHOUSE);
  private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseRegionServiceImpl.class);
  private static final String CACHE_NAME = "WAREHOUSE_REGION";
  @Autowired
  private WarehouseRegionDao dao;
  @Autowired
  private WarehouseService warehouseService;

  @Override
  protected QueryDao<WarehouseRegion, WarehouseRegionQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'WRV_'+#warehouseId+#provinceId+#cityId+#districtId")
  public boolean validate(Long warehouseId, Long countryId, Long provinceId, Long cityId,
      Long districtId) {
    WarehouseRegion example = new WarehouseRegion();
    example.setWarehouseId(warehouseId);
    if (districtId != null) {
      example.setRegionId(districtId);
      example.setLevel(4);
      WarehouseRegion warehouseRegion = dao.getByExample(example);
      if (warehouseRegion != null) {
        return true;
      }
    }
    if (cityId != null) {
      example.setRegionId(cityId);
      example.setLevel(3);
      WarehouseRegion warehouseRegion = dao.getByExample(example);
      if (warehouseRegion != null) {
        return true;
      }
    }
    if (provinceId != null) {
      example.setRegionId(provinceId);
      example.setLevel(2);
      WarehouseRegion warehouseRegion = dao.getByExample(example);
      if (warehouseRegion != null) {
        return true;
      }
    }
    if (countryId != null) {
      example.setRegionId(countryId);
      example.setLevel(1);
      WarehouseRegion warehouseRegion = dao.getByExample(example);
      return warehouseRegion != null;
    }
    return false;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'WRV_'+#warehouseId+'*'")
  public void saveWarehouseRegions(Long warehouseId, List<WarehouseRegion> regions) {
    Assert.notNull(warehouseId, "仓库不能为空！");
    Assert.notEmpty(regions, "区域不能为空！");
    regions.forEach(x -> x.setWarehouseId(warehouseId));
    Warehouse warehouse = warehouseService.getByKey(warehouseId);
    WarehouseRegion eg = new WarehouseRegion();
    eg.setWarehouseId(warehouseId);
    try {
      getTransactionTemplate().execute(() -> {
        deleteByExample(eg);
        insertBatch(regions);
      });
      BIZ_LOGGER.log(warehouseId, "配置仓库区域", "仓库：{}", warehouse.getWarehouseName());
    } catch (Exception e) {
      LOGGER.error("保存仓库区域失败！{},{}", warehouseId, JsonUtil.toJson(regions));
      LOGGER.error("保存仓库区域失败！", e);
      throw new OmsException("保存仓库区域失败");
    }
  }

  public List<WarehouseRegion> listRegion(WarehouseRegionQuery filter) {
    return dao.listRegion(filter);
  }
}