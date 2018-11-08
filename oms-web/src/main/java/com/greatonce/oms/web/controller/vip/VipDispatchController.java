package com.greatonce.oms.web.controller.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipDispatchBindDeliveryBO;
import com.greatonce.oms.bo.vip.VipDispatchCancelBO;
import com.greatonce.oms.bo.vip.VipPickOrderDownloadBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.vip.VipDispatchStatus;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.query.vip.VipDispatchQuery;
import com.greatonce.oms.web.controller.CommonController;
import com.greatonce.oms.web.controller.ControllerUtil;
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
@RequestMapping("/vip/dispatch")
@CrossOrigin
public class VipDispatchController implements CommonController<VipDispatch, VipDispatchQuery> {

  @Autowired
  private VipDispatchService vipDispatchService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<VipDispatch, VipDispatchQuery> getBizService() {
    return vipDispatchService;
  }

  @GetMapping
  public PageList<VipDispatch> listPage(VipDispatchQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(filter, VipDispatchQuery::getStoreIds, filter::setStoreIds);
    return vipDispatchService.listPage(filter, page, pageSize);
  }

  @RequestMapping(path = "{id}/bindDelivery", method = RequestMethod.PUT)
  public void bindDelivery(@PathVariable("id") Long id, @RequestBody VipDispatchBindDeliveryBO bo) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    validateVersion(bo, vipDispatch);
    vipDispatchService.bindDelivery(vipDispatch, bo);
  }

  @RequestMapping(path = "{id}/match", method = RequestMethod.PUT)
  public void match(@PathVariable("id") Long id) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    vipDispatchService.match(vipDispatch);
  }

  @RequestMapping(path = "{id}/finish", method = RequestMethod.PUT)
  public void finish(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    validateVersion(bo, vipDispatch);
    vipDispatchService.finish(vipDispatch, bo);
  }

  @PutMapping(path = "{id}/cancel")
  public void cancel(@PathVariable("id") Long id, @RequestBody VipDispatchCancelBO bo) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    validateVersion(bo, vipDispatch);
    vipDispatchService.cancel(vipDispatch, bo);
  }

  @PutMapping(path = "{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long id) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    vipDispatchService.noticeWms(vipDispatch);
    if (vipDispatch.getStatus() == VipDispatchStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @PostMapping("/exportSku/{fileName}")
  public void exportDispatch(@PathVariable("fileName") String fileName,
      @RequestBody VipDispatchQuery query) {
    vipDispatchService.exportDispatch(fileName, query);
  }

  @PutMapping(path = "{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    validateVersion(bo, vipDispatch);
    vipDispatchService.audit(vipDispatch, bo);
  }

  @PostMapping(path = "/download")
  public void download(@RequestBody VipPickOrderDownloadBO bo) {
    vipDispatchService.automaticDownload(bo);
  }

  @PutMapping(path = "{id}/recalculateAmount")
  public void recalculateAmount(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    validateVersion(bo, vipDispatch);
    vipDispatchService.recalculateAmount(vipDispatch, bo);
  }

  @PutMapping(path = "{id}/reposting")
  public void reposting(@PathVariable("id") Long id) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    vipDispatchService.reposting(vipDispatch);
  }

  @GetMapping(path = "{id}/replenishment")
  public VipDispatch replenishment(@PathVariable("id") Long id) {
    VipDispatch vipDispatch = vipDispatchService.getByKey(id);
    if (vipDispatch == null) {
      throw new OmsException("拣货单不存在，请检查单据！");
    }
    return vipDispatchService.replenishment(vipDispatch);
  }
}
