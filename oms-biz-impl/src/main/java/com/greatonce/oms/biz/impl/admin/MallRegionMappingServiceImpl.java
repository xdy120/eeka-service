package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.MallRegionMappingService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.MallRegionMappingDao;
import com.greatonce.oms.domain.admin.MallRegionMapping;
import com.greatonce.oms.query.admin.MallRegionMappingQuery;
import com.greatonce.oms.util.CacheableNotNull;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * MallRegionMapping <br/>
 * 商城区域映射
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class MallRegionMappingServiceImpl extends
    AbstractService<MallRegionMapping, MallRegionMappingQuery> implements MallRegionMappingService {

  @Autowired
  private MallRegionMappingDao dao;

  @Override
  protected QueryDao<MallRegionMapping, MallRegionMappingQuery> getDAO() {
    return this.dao;
  }

  @Override
  @CacheEvict(value = "baseCache", key = "'MALLREGIONMAPPING_'+#entity.mallRegionMappingId")
  public int modify(MallRegionMapping entity) {
    return update(entity);
  }

  @Override
  @CacheEvict(value = "baseCache", key = "'MALLREGIONMAPPING_'+#entity.mallRegionMappingId")
  public int remove(MallRegionMapping entity) {
    return delete(entity.getMallRegionMappingId());
  }

  @Override
  @CacheableNotNull(value = "baseCache", key = "'MALLREGIONMAPPING_'+#id")
  public MallRegionMapping getByKey(Long id) {
    return super.getByKey(id);
  }

  @Override
  public int batchCreate(Collection<? extends MallRegionMapping> collection) {
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends MallRegionMapping> collection) {
    return updateBatch(collection);
  }
}