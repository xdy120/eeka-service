package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.ThirdAppAuthService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.ThirdAppAuthDao;
import com.greatonce.oms.domain.admin.ThirdAppAuth;
import com.greatonce.oms.query.admin.ThirdAppAuthQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ThirdAppAuth <br/>
 * 第三方应用授权
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ThirdAppAuthServiceImpl extends
    AbstractService<ThirdAppAuth, ThirdAppAuthQuery> implements ThirdAppAuthService {

  @Autowired
  private ThirdAppAuthDao dao;

  @Override
  protected QueryDao<ThirdAppAuth, ThirdAppAuthQuery> getDAO() {
    return this.dao;
  }

}