package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.FieldService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.FieldDao;
import com.greatonce.oms.domain.admin.Field;
import com.greatonce.oms.query.admin.FieldQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Field <br/>
 * 字段
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-14
 */
@Service
public class FieldServiceImpl extends AbstractService<Field, FieldQuery> implements FieldService {

  @Autowired
  private FieldDao dao;

  @Override
  protected QueryDao<Field, FieldQuery> getDAO() {
    return this.dao;
  }

}