package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.WarehouseRelationService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.WarehouseRelationDao;
import com.greatonce.oms.domain.base.WarehouseRelation;
import com.greatonce.oms.query.base.WarehouseRelationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * WarehouseRelation <br/> 仓库关系.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-05-02
 */

@Service
public class WarehouseRelationServiceImpl extends
    AbstractService<WarehouseRelation, WarehouseRelationQuery> implements
    WarehouseRelationService {

  @Autowired
  private WarehouseRelationDao dao;

  @Override
  protected QueryDao<WarehouseRelation, WarehouseRelationQuery> getDAO() {
    return this.dao;
  }
}