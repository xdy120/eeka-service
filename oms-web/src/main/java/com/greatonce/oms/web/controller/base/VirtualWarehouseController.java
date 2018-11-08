package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.query.base.VirtualWarehouseQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/base/warehouse/virtual")
@CrossOrigin
public class VirtualWarehouseController implements
    EnableController<VirtualWarehouse, VirtualWarehouseQuery>,
    FullListController<VirtualWarehouse, VirtualWarehouseQuery> {

  @Autowired
  VirtualWarehouseService virtualWarehouseService;

  @Override
  public BizService<VirtualWarehouse, VirtualWarehouseQuery> getBizService() {
    return virtualWarehouseService;
  }

  @GetMapping("/effective")
  public List<VirtualWarehouse> listEffective() {
    VirtualWarehouse eg = new VirtualWarehouse();
    eg.setEnable(true);
    return virtualWarehouseService.listExample(eg);
  }

  @GetMapping("/my")
  public List<VirtualWarehouse> listUserStore() {
    return virtualWarehouseService.listUserVirtualWarehouse(BizContext.getUserId());
  }
}
