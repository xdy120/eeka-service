package com.greatonce.oms.web.controller.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipReturnNoticeCancelBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.vip.VipReturnNoticeStatus;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.query.vip.VipReturnNoticeQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vip/return/notice")
@CrossOrigin
public class VipReturnNoticeController implements
    PageListController<VipReturnNotice, VipReturnNoticeQuery> {

  @Autowired
  private VipReturnNoticeService vipReturnNoticeService;

  @Autowired
  private StoreService storeService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<VipReturnNotice, VipReturnNoticeQuery> getBizService() {
    return vipReturnNoticeService;
  }

  @GetMapping
  public PageList<VipReturnNotice> listPage(VipReturnNoticeQuery filter,
      @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserStoreIds(filter, VipReturnNoticeQuery::getStoreIds, filter::setStoreIds);
    return vipReturnNoticeService.listPage(filter, page, pageSize);
  }

  @PutMapping(path = "{id}/cancel")
  public void cancel(@PathVariable("id") Long id, @RequestBody VipReturnNoticeCancelBO bo) {
    VipReturnNotice vipReturnNotice = vipReturnNoticeService.getByKey(id);
    vipReturnNoticeService.cancel(vipReturnNotice, bo);
  }

  @PutMapping(path = "{id}/noticeWms")
  public void noticeWms(@PathVariable("id") Long id) {
    VipReturnNotice vipDispatch = vipReturnNoticeService.getByKey(id);
    vipReturnNoticeService.noticeWms(vipDispatch);
    if (vipDispatch.getStatus() == VipReturnNoticeStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @Override
  @RequestMapping(method = RequestMethod.POST)
  public VipReturnNotice insert(@RequestBody VipReturnNotice vipReturnNotice) {
    getBizService().create(vipReturnNotice);
    return vipReturnNotice;
  }

  @PutMapping(path = "{id}/recalculateAmount")
  public void recalculateAmount(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipReturnNotice vipReturnNotice = vipReturnNoticeService.getByKey(id);
    vipReturnNoticeService.recalculateAmount(vipReturnNotice, bo);
  }

  @PutMapping("/{id}/finish")
  public void end(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    VipReturnNotice notice = vipReturnNoticeService.getByKey(id);
    validateVersion(bo, notice);
    vipReturnNoticeService.finish(notice, bo);
  }

  @PutMapping(path = "{id}/reposting")
  public void reposting(@PathVariable("id") Long id) {
    VipReturnNotice vipReturnNotice = vipReturnNoticeService.getByKey(id);
    vipReturnNoticeService.reposting(vipReturnNotice);
  }

  @GetMapping("/exportVipReturnNotice/{fileName}")
  public void exportVipReturnNotice(@PathVariable("fileName") String fileName,
      VipReturnNoticeQuery query) {
    vipReturnNoticeService.exportVipReturnNotice(fileName, query);
  }

}
