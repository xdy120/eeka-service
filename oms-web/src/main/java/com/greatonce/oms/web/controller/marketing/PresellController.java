package com.greatonce.oms.web.controller.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.marketing.PresellDetailService;
import com.greatonce.oms.biz.marketing.PresellService;
import com.greatonce.oms.biz.marketing.PresellStoreService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.marketing.PresellStore;
import com.greatonce.oms.query.marketing.PresellQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/12/5.
 */
@RestController
@RequestMapping(value = "/marketing/presell")
@CrossOrigin
public class PresellController implements PageListController<Presell, PresellQuery> {

  @Autowired
  private PresellService presellService;
  @Autowired
  private PresellDetailService presellDetailService;
  @Autowired
  private PresellStoreService presellStoreService;

  @Override
  public BizService<Presell, PresellQuery> getBizService() {
    return presellService;
  }

  @Override
  @GetMapping
  public PageList<Presell> listPage(PresellQuery presellQuery, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    return presellService.listPage(presellQuery, page, pageSize);
  }

  @PutMapping(path = "{id}/begin")
  public void begin(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Presell presell = presellService.getByKey(id);
    presellService.begin(presell, bo);
  }

  @PutMapping(path = "{id}/{presellStoreId}/retry")
  public void retry(@PathVariable("id") Long id,
      @PathVariable("presellStoreId") Long presellStoreId) {
    Presell presell = presellService.getByKey(id);
    PresellStore presellStore = presellStoreService.getByKey(presellStoreId);
    presellService.retry(presell, presellStore);
  }

  @PutMapping(path = "{id}/end")
  public void end(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Presell presell = presellService.getByKey(id);
    presellService.end(presell, bo);
  }

  @PutMapping(path = "{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Presell presell = presellService.getByKey(id);
    presellService.audit(presell, bo);
  }

  @PutMapping(path = "/{id}/invalid")
  public void invalid(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Presell presell = presellService.getByKey(id);
    presellService.invalid(presell, bo);
  }

  @GetMapping(path = "/exportPresell/{fileName}")
  public void exportExcel(PresellQuery query, @PathVariable("fileName") String fileName) {
    presellDetailService.exportPresell(fileName, query);
  }
}
