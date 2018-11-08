package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.WarehouseRegionDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.WarehouseRegion;
import com.greatonce.oms.query.base.WarehouseRegionQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * WarehouseRegion <br/>
 * 仓库区域
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class WarehouseRegionDaoImpl extends AbstractOmsDao<WarehouseRegion,WarehouseRegionQuery> implements WarehouseRegionDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.WarehouseRegionMapper";
    }

    public int deleteRegion(Object id) {
        return this.getSqlSessionDecorator().delete(this.getStatement("deleteRegion"), id);
    }

    public List<WarehouseRegion> listRegion(WarehouseRegionQuery warehouseRegionQuery) {
        return getSqlSessionDecorator().selectList(getStatement("listColumns"),warehouseRegionQuery);
    }
}