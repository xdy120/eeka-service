package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.ThirdAppService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.ThirdAppDao;
import com.greatonce.oms.domain.admin.ThirdApp;
import com.greatonce.oms.query.admin.ThirdAppQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ThirdApp <br/>
 * 第三方应用
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ThirdAppServiceImpl extends AbstractService<ThirdApp, ThirdAppQuery> implements
    ThirdAppService {

  @Autowired
  private ThirdAppDao dao;

  @Override
  protected QueryDao<ThirdApp, ThirdAppQuery> getDAO() {
    return this.dao;
  }

}