package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.ExpressRegionService;
import com.greatonce.oms.domain.base.ExpressRegion;
import com.greatonce.oms.query.base.ExpressRegionQuery;
import com.greatonce.oms.web.controller.FullListController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/express/region")
@CrossOrigin
public class ExpressRegionController implements
    FullListController<ExpressRegion, ExpressRegionQuery> {

  @Autowired
  private ExpressRegionService expressRegionService;

  @Override
  public BizService<ExpressRegion, ExpressRegionQuery> getBizService() {
    return expressRegionService;
  }

  @PostMapping(path = "/{expressId}/save")
  public void saveWarehouseRegions(@PathVariable("expressId") Long expressId,
      @RequestBody List<ExpressRegion> regions) {
    expressRegionService.saveExpressRegions(expressId, regions);
  }

  @GetMapping(path = "simple")
  public List<ExpressRegion> listSimple(ExpressRegionQuery filter) {
    return expressRegionService.listRegion(filter);
  }
}
