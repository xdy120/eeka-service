package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.query.base.StoreDownloadConfigQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-10-21 13:54
 */
@RestController
@RequestMapping("/base/store/download/config")
@CrossOrigin
public class StoreDownloadConfigController implements
    FullListController<StoreDownloadConfig, StoreDownloadConfigQuery> {

  @Autowired
  private StoreDownloadConfigService storeDownloadConfigService;

  @Override
  public BizService<StoreDownloadConfig, StoreDownloadConfigQuery> getBizService() {
    return storeDownloadConfigService;
  }

  @PutMapping(path = "{id}/disable")
  public void disable(@PathVariable("id") Long id) {
    StoreDownloadConfig entity = getBizService().getByKey(id);
    storeDownloadConfigService.disable(entity);
  }

  @PutMapping(path = "{id}/enable")
  public void enable(@PathVariable("id") Long id) {
    StoreDownloadConfig entity = getBizService().getByKey(id);
    storeDownloadConfigService.enable(entity);
  }

  @PutMapping(path = "{id}/enableSupplement")
  public void enableSupplement(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    storeDownloadConfigService.enableSupplement(id, bo);
  }

  @PutMapping(path = "{id}/disableSupplement")
  public void disableSupplement(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    storeDownloadConfigService.disableSupplement(id, bo);
  }
}
