package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.WarehouseRegion;
import com.greatonce.oms.query.base.WarehouseRegionQuery;

import java.util.List;

/**
 * WarehouseRegion <br/>
 * oms_warehouse_region
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-10-30
 */
public interface WarehouseRegionService extends BizService<WarehouseRegion,WarehouseRegionQuery>{
    boolean validate(Long warehouseId, Long countryId, Long provinceId, Long cityId, Long districtId);

    void saveWarehouseRegions(Long warehouseId, List<WarehouseRegion> regions);

    List<WarehouseRegion> listRegion(WarehouseRegionQuery filter);
}