package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.WarehouseRegionService;
import com.greatonce.oms.domain.base.WarehouseRegion;
import com.greatonce.oms.query.base.WarehouseRegionQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/base/warehouse/region")
@CrossOrigin
public class WarehouseRegionController implements
    FullListController<WarehouseRegion, WarehouseRegionQuery> {

  @Autowired
  WarehouseRegionService warehouseRegionService;

  @Override
  public BizService<WarehouseRegion, WarehouseRegionQuery> getBizService() {
    return warehouseRegionService;
  }


  @PostMapping(path = "/{warehouseId}/save")
  public void saveWarehouseRegions(@PathVariable("warehouseId") Long warehouseId,
      @RequestBody List<WarehouseRegion> regions) {
    warehouseRegionService.saveWarehouseRegions(warehouseId, regions);
  }

  @GetMapping(path = "simple")
  public List<WarehouseRegion> listSimple(WarehouseRegionQuery filter) {
    return warehouseRegionService.listRegion(filter);
  }
}


