package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.query.base.WarehouseQuery;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/base/warehouse")
@CrossOrigin
public class WarehouseController implements EnableController<Warehouse, WarehouseQuery>,
    PageListController<Warehouse, WarehouseQuery> {

  @Autowired
  WarehouseService warehouseService;

  @Override
  public BizService<Warehouse, WarehouseQuery> getBizService() {
    return warehouseService;
  }

  @GetMapping("/effective")
  public List<Warehouse> listEffective() {
    Warehouse eg = new Warehouse();
    eg.setEnable(true);
    return warehouseService.listExample(eg);
  }
}


