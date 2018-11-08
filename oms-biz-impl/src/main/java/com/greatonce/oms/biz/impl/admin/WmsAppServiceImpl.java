package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.WmsAppDao;
import com.greatonce.oms.domain.admin.WmsApp;
import com.greatonce.oms.query.admin.WmsAppQuery;
import com.greatonce.oms.util.CacheableNotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * WmsApp <br/>
 * 仓库应用
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class WmsAppServiceImpl extends AbstractService<WmsApp, WmsAppQuery> implements
    WmsAppService {

  private static final String CACHE_NAME = "WMS_APP";
  @Autowired
  private WmsAppDao dao;

  @Override
  protected QueryDao<WmsApp, WmsAppQuery> getDAO() {
    return this.dao;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'WMS_APP_'+#customerId")
  public WmsApp getByCustomerId(String customerId) {
    WmsApp eg = new WmsApp();
    eg.setCustomerId(customerId);
    return getByExample(eg);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'WMS_APP_'+#entity.customerId")
  public int modify(WmsApp entity) {
    return super.modify(entity);
  }
}