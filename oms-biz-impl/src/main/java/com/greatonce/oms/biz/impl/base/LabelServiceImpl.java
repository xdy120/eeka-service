package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.LabelService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.LabelDao;
import com.greatonce.oms.domain.base.Label;
import com.greatonce.oms.query.base.LabelQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Label <br/> 标签.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class LabelServiceImpl extends AbstractService<Label, LabelQuery> implements LabelService {

  @Autowired
  LabelDao dao;

  @Override
  protected QueryDao<Label, LabelQuery> getDAO() {
    return this.dao;
  }
}