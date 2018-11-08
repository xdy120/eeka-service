package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.WarehouseDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.query.base.WarehouseQuery;
import org.springframework.stereotype.Repository;

/**
 * Warehouse <br/>
 * 仓库
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class WarehouseDaoImpl extends AbstractOmsDao<Warehouse,WarehouseQuery> implements WarehouseDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.WarehouseMapper";
    }
    
}