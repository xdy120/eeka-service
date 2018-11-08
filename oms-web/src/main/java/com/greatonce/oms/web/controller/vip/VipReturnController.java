package com.greatonce.oms.web.controller.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.vip.VipReturnService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipRefundOrderDownloadBO;
import com.greatonce.oms.bo.vip.VipReturnScanBO;
import com.greatonce.oms.bo.vip.VipReturnSignBO;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.query.vip.VipReturnQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/return")
@CrossOrigin
public class VipReturnController implements PageListController<VipReturn, VipReturnQuery> {

  @Autowired
  private VipReturnService vipReturnService;

  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<VipReturn, VipReturnQuery> getBizService() {
    return vipReturnService;
  }

  @GetMapping
  public PageList<VipReturn> listPage(VipReturnQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(filter, VipReturnQuery::getStoreIds, filter::setStoreIds);
    return vipReturnService.listPage(filter, page, pageSize);
  }

  @PutMapping(path = "{id}/sign")
  public void sign(@PathVariable("id") Long id, @RequestBody VipReturnSignBO bo) {
    VipReturn vipReturn = vipReturnService.getByKey(id);
    vipReturnService.sign(vipReturn, bo);
  }

  @PutMapping(path = "{id}/scan")
  public void scan(@PathVariable("id") Long id, @RequestBody VipReturnScanBO bo) {
    VipReturn vipReturn = vipReturnService.getByKey(id);
    vipReturnService.scan(vipReturn, bo);
  }

  @PutMapping(path = "{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipReturn vipReturn = vipReturnService.getByKey(id);
    vipReturnService.audit(vipReturn, bo);
  }

  @PutMapping(path = "{id}/notice")
  public void createNotice(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipReturn vipReturn = vipReturnService.getByKey(id);
    vipReturnService.createNotice(vipReturn, bo);
  }

  @RequestMapping(path = "{id}/match", method = RequestMethod.PUT)
  public void match(@PathVariable("id") Long id) {
    VipReturn vipReturn = vipReturnService.getByKey(id);
    vipReturnService.match(vipReturn);
  }

  @GetMapping("/exportSku/{fileName}")
  public void exportReturn(@PathVariable("fileName") String fileName, VipReturnQuery query) {
    vipReturnService.exportReturn(fileName, query);
  }

  @PutMapping(path = "{id}/recalculateAmount")
  public void recalculateAmount(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipReturn vipReturn = vipReturnService.getByKey(id);
    vipReturnService.recalculateAmount(vipReturn, bo);
  }

  @PostMapping(path = "/download")
  public void download(@RequestBody VipRefundOrderDownloadBO bo) {
    vipReturnService.automaticDownload(bo);
  }

}