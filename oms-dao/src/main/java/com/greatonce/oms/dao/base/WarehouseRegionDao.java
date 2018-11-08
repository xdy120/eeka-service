package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.WarehouseRegion;
import com.greatonce.oms.query.base.WarehouseRegionQuery;

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

public interface WarehouseRegionDao extends QueryDao<WarehouseRegion,WarehouseRegionQuery>{
     int deleteRegion(Object id);

     List<WarehouseRegion> listRegion(WarehouseRegionQuery warehouseRegionQuery);
}