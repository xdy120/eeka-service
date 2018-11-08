package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.WarehouseRelationDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.WarehouseRelation;
import com.greatonce.oms.query.base.WarehouseRelationQuery;
import org.springframework.stereotype.Repository;

/**
 * WarehouseRelation <br/>
 * 仓库关系
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class WarehouseRelationDaoImpl extends AbstractOmsDao<WarehouseRelation,WarehouseRelationQuery> implements WarehouseRelationDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.WarehouseRelationMapper";
    }

}