package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.GlobalExpressMallMappingService;
import com.greatonce.oms.biz.admin.GlobalExpressService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.GlobalExpressMallMappingDao;
import com.greatonce.oms.domain.admin.GlobalExpress;
import com.greatonce.oms.domain.admin.GlobalExpressMallMapping;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.query.admin.GlobalExpressMallMappingQuery;
import com.greatonce.oms.util.CacheableNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 全局快递商城映射.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-05-02
 */

@Service
public class GlobalExpressMallMappingServiceImpl extends
    AbstractService<GlobalExpressMallMapping, GlobalExpressMallMappingQuery> implements
    GlobalExpressMallMappingService {

  private static final String CACHE_NAME = "EXPRESS_MALL_MAPPING";
  @Autowired
  private GlobalExpressMallMappingDao dao;
  @Autowired
  private GlobalExpressService globalExpressService;

  @Override
  protected QueryDao<GlobalExpressMallMapping, GlobalExpressMallMappingQuery> getDAO() {
    return this.dao;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "#entity.mallType.toString()+'_'+#entity.globalExpressId")
  public int modify(GlobalExpressMallMapping entity) {
    return super.modify(entity);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "#entity.mallType.toString()+'_'+#entity.globalExpressId")
  public int remove(GlobalExpressMallMapping entity) {
    return super.remove(entity);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "#mallType.toString()+'_'+#globalExpressId")
  public GlobalExpressMallMapping getMallExpressMapping(Long globalExpressId, MallType mallType) {
    GlobalExpressMallMapping eg = new GlobalExpressMallMapping();
    eg.setGlobalExpressId(globalExpressId);
    eg.setMallType(mallType);
    GlobalExpressMallMapping mapping = getByExample(eg);
    if(mapping == null){
      GlobalExpress globalExpress = globalExpressService.getByKey(globalExpressId);
      mapping = new GlobalExpressMallMapping();
      mapping.setOuterCode(globalExpress.getGlobalExpressCode());
      mapping.setOuterName(globalExpress.getGlobalExpressName());
    }
    return mapping;
  }
}