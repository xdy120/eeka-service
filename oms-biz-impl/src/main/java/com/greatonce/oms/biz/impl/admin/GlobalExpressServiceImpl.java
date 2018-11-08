package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.GlobalExpressService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.GlobalExpressDao;
import com.greatonce.oms.domain.admin.GlobalExpress;
import com.greatonce.oms.query.admin.GlobalExpressQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GlobalExpress <br/>
 * 全局快递
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class GlobalExpressServiceImpl extends
    AbstractService<GlobalExpress, GlobalExpressQuery> implements GlobalExpressService {

  @Autowired
  private GlobalExpressDao dao;

  @Override
  protected QueryDao<GlobalExpress, GlobalExpressQuery> getDAO() {
    return this.dao;
  }

}