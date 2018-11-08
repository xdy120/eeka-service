package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.VirtualWarehouseDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.query.base.VirtualWarehouseQuery;
import org.springframework.stereotype.Repository;

/**
 * VirtualWarehouse <br/>
 * 虚拟仓库
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VirtualWarehouseDaoImpl extends AbstractOmsDao<VirtualWarehouse,VirtualWarehouseQuery> implements VirtualWarehouseDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.VirtualWarehouseMapper";
    }

}