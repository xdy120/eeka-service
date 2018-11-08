package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.admin.MallRegionMappingService;
import com.greatonce.oms.biz.admin.RegionService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.bo.admin.RegionMatchBO;
import com.greatonce.oms.dao.admin.RegionDao;
import com.greatonce.oms.domain.admin.MallRegionMapping;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.admin.RegionQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * Region <br/> 区域表
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class RegionServiceImpl extends AbstractService<Region, RegionQuery> implements
    RegionService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_REGION);

  private static final String CACHE_NAME = "REGION";

  @Autowired
  private RegionDao dao;
  @Autowired
  private MallRegionMappingService mallRegionMappingService;
  @Autowired
  private IdGenerator mallRegionMappingIdGenerator;

  @Override
  protected QueryDao<Region, RegionQuery> getDAO() {
    return this.dao;
  }


  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'REGION_'+#parentId+'_'+#name")
  public Region getByNameAndLevel(Long parentId, String name, Integer level) {
    Region eg = new Region();
    eg.setParentId(parentId);
    eg.setRegionName(name);
    eg.setLevel(level);
    return dao.getTranslateRegionInfo(eg);
  }

  @Override
  public List<Region> listAll() {
    List<Region> list = list(null);
    return getRegion(list);
  }

  @Override
  public List<Region> listSimple() {
    List<Region> list = dao.listSimple(null);
    return getRegion(list);
  }

  public List<Region> getRegion(List<Region> list) {
    List<Region> level1 = new ArrayList<>(40);
    Map<Long, Region> map = CollectionUtil.listToMap(list, Region::getRegionId);
    Region parent;
    for (Region region : list) {
      if (region.getParentId() == 0) {
        level1.add(region);
      } else {
        parent = map.get(region.getParentId());
        if (parent != null) {
          if (parent.getChildren() == null) {
            parent.setChildren(new ArrayList<>(20));
          }
          parent.getChildren().add(region);
        }
      }
    }
    return level1;
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'REGION_'+#record.parentId+'_'+#record.regionName"),
      @CacheEvict(value = CACHE_NAME, key = "'REGION_'+#record.regionId")
  })
  public int modify(Region record) {
    int count = update(record);
    BIZ_LOGGER.log(record.getRegionId(), BizLogger.UPDATE, record.getRegionName());
    return count;
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'REGION_'+#record.parentId+'_'+#record.regionName"),
      @CacheEvict(value = CACHE_NAME, key = "'REGION_'+#record.regionId")
  })
  public int remove(Region record) {
    int count = getTransactionTemplate().executeWithResult(() -> {
      int num = delete(record.getRegionId());
      updateChildrenQuantity(record.getParentId(), -1);
      return num;
    });
    BIZ_LOGGER.log(record.getRegionId(), BizLogger.DELETE, record.getRegionName());
    return count;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'REGION_'+#id")
  public Region getByKey(Long id) {
    return super.getByKey(id);
  }

  @Override
  public int updateChildrenQuantity(Long id, int quantity) {
    return dao.updateChildrenQuantity(id, quantity);
  }

  @Override
  public int create(Region record) {
    Assert.notNull(record.getParentId(), "父分类不能为空！");
    record.setRegionId(getIdGenerator().next());
    record.setChildrenQuantity(0);
    Region parent = getByKey(record.getParentId());
    record.setLevel(parent == null ? 0 : parent.getLevel() + 1);
    int count = getTransactionTemplate().executeWithResult(() -> {
      int num = insert(record);
      updateChildrenQuantity(record.getParentId(), 1);
      return num;
    });
    BIZ_LOGGER.log(record.getRegionId(), BizLogger.ADD, record.getRegionName());
    return count;
  }

  @Override
  public List<Region> listChildren(Long parentId) {
    Region eg = new Region();
    eg.setParentId(parentId);
    return getDAO().listExample(eg);
  }

  @Override
  public RegionMatchBO matchRegion(String country, String province, String city, String district) {
    Region regionCountry = new Region();
    regionCountry.setRegionId(0L);
    regionCountry = matchRegion(regionCountry, "中国", null, 1);
    Region regionProvince = matchRegion(regionCountry, province, "省", 2);
    Region regionCity = matchRegion(regionProvince, city, "市", 3);
    Region regionDistrict = matchRegion(regionCity, district, "区", 4);

    RegionMatchBO result = new RegionMatchBO();
    result.setCountry(regionCountry);
    result.setProvince(regionProvince);
    result.setCity(regionCity);
    result.setDistrict(regionDistrict);
    //匹配成功直接返回成功
    if (regionProvince != null && regionCity != null && regionDistrict != null) {
      result.setMatch(true);
      return result;
    }
    String regionAlias = province.concat(">".concat(city.concat(">".concat(district))));

    MallRegionMapping eg = new MallRegionMapping();
    eg.setMallRegionAlias(regionAlias);
    MallRegionMapping regionMapping;
    synchronized (("ADDRESS_LOCK_" + regionAlias).intern()) {
      regionMapping = mallRegionMappingService.getByExample(eg);
      if (regionMapping == null) {
        //找不到映射返回失败
        eg.setCreatedTime(LocalDateTime.now());
        eg.setMallRegionMappingId(mallRegionMappingIdGenerator.next());
        mallRegionMappingService.create(eg);
        return result;
      }
      if (regionMapping.getDistrictId() == null || regionMapping.getCityId() == null
          || regionMapping.getProvinceId() == null) {
        return result;
      }
    }
    //找到映射返回成功
    result.setMapping(regionMapping);
    result.setMatch(true);
    result.setMatchMapping(true);
    return result;
  }

  /**
   * 通过name匹配，返回国省市区的id
   */
  private Region matchRegion(Region parent, String name, String suffix, Integer level) {
    if (Assert.isEmpty(name) || Assert.isNull(parent)) {
      return null;
    }
    Region region = getByNameAndLevel(parent.getRegionId(), name, level);
    if (region == null) {
      if (!Assert.isEmpty(suffix) && !name.endsWith(suffix)) {
        region = getByNameAndLevel(parent.getRegionId(), name.concat(suffix), level);
      }
    }
    return region;
  }
}