package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.GlobalSettingService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.GlobalSettingDao;
import com.greatonce.oms.domain.admin.GlobalSetting;
import com.greatonce.oms.query.admin.GlobalSettingQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GlobalSetting <br/>
 * 全局配置
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class GlobalSettingServiceImpl extends
    AbstractService<GlobalSetting, GlobalSettingQuery> implements GlobalSettingService {

  @Autowired
  private GlobalSettingDao dao;

  @Override
  protected QueryDao<GlobalSetting, GlobalSettingQuery> getDAO() {
    return this.dao;
  }

}