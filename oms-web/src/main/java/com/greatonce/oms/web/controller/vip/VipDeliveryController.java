package com.greatonce.oms.web.controller.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.vip.VipDeliveryService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipDispatchOrderBO;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.query.vip.VipDeliveryQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/delivery")
@CrossOrigin
public class VipDeliveryController implements PageListController<VipDelivery, VipDeliveryQuery> {

  @Autowired
  private VipDeliveryService vipDeliveryService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<VipDelivery, VipDeliveryQuery> getBizService() {
    return vipDeliveryService;
  }

  @GetMapping
  public PageList<VipDelivery> listPage(VipDeliveryQuery query, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(query, VipDeliveryQuery::getStoreIds, query::setStoreIds);
    return vipDeliveryService.listPage(query, page, pageSize);
  }

  @PutMapping(path = "{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipDelivery vipDelivery = vipDeliveryService.getByKey(id);
    vipDeliveryService.audit(vipDelivery, bo);
  }

  @PutMapping(path = "{id}/delivery")
  public void delivery(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipDelivery vipDelivery = vipDeliveryService.getByKey(id);
    vipDeliveryService.delivery(vipDelivery, bo);
  }

  @PutMapping(path = "{id}/invalid")
  public void invalid(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipDelivery vipDelivery = vipDeliveryService.getByKey(id);
    vipDeliveryService.invalid(vipDelivery, bo);
  }

  @GetMapping(path = "{id}/query/dispatch")
  public List<VipDispatchOrderBO> queryDispatch(VipDeliveryQuery query) {
    return vipDeliveryService.queryDispatch(query);
  }

  @PostMapping("/exportSku/{fileName}")
  public void exportCombination(@PathVariable("fileName") String fileName,
      @RequestBody VipDeliveryQuery query) {
    vipDeliveryService.exportDelivery(fileName, query);
  }

  @PutMapping(path = "{id}/cancel")
  public void cancel(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipDelivery vipDelivery = vipDeliveryService.getByKey(id);
    vipDeliveryService.cancel(vipDelivery, bo);
  }

  @PutMapping(path = "{id}/manualDelivery")
  public void manualDelivery(@PathVariable("id") Long id,@RequestBody VersionBO bo){
    VipDelivery vipDelivery = vipDeliveryService.getByKey(id);
    vipDeliveryService.manualDelivery(vipDelivery,bo);
  }
}
