package com.greatonce.oms.web.controller.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.stock.StockVirtualAllotService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.stock.StockVirtualAllot;
import com.greatonce.oms.query.stock.StockVirtualAllotQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2018/1/17.
 */
@RestController
@RequestMapping(value = "/stock/allot/virtual")
@CrossOrigin
public class StoreVirtualAllotController implements
    PageListController<StockVirtualAllot, StockVirtualAllotQuery> {

  @Resource
  StockVirtualAllotService stockVirtualAllotService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<StockVirtualAllot, StockVirtualAllotQuery> getBizService() {
    return stockVirtualAllotService;
  }

  @RequestMapping(path = "/{id}/audit", method = RequestMethod.PUT)
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    StockVirtualAllot allot = stockVirtualAllotService.getByKey(id);
    stockVirtualAllotService.audit(allot, bo);
  }

  @RequestMapping(path = "/{id}/invalid", method = RequestMethod.PUT)
  public void invalid(@PathVariable("id") Long id,
      @RequestBody VersionBO bo) {
    StockVirtualAllot allot = stockVirtualAllotService.getByKey(id);

    stockVirtualAllotService.invalid(allot, bo);
  }

  @GetMapping
  public PageList<StockVirtualAllot> listPage(StockVirtualAllotQuery filter,
      @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil
        .addUserVirtualWarehouseIds(filter, StockVirtualAllotQuery::getOutVirtualWarehouseIds,
            filter::setOutVirtualWarehouseIds);
    return stockVirtualAllotService.listPage(filter, page, pageSize);
  }

  @PostMapping("/{allotId}/export/{fileName}")
  public void exportAllot(@PathVariable("allotId") Long allotId,
      @PathVariable("fileName") String fileName) {
    stockVirtualAllotService.exportAllot(allotId, fileName);
  }

}
