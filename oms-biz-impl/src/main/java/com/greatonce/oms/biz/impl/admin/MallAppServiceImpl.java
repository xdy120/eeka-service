package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.MallAppService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.MallAppDao;
import com.greatonce.oms.domain.admin.MallApp;
import com.greatonce.oms.query.admin.MallAppQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MallApp <br/>
 * 商城应用
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-14
 */

@Service
public class MallAppServiceImpl extends AbstractService<MallApp, MallAppQuery> implements
    MallAppService {

  @Autowired
  private MallAppDao dao;

  @Override
  protected QueryDao<MallApp, MallAppQuery> getDAO() {
    return this.dao;
  }

}