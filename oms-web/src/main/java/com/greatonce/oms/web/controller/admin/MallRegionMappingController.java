package com.greatonce.oms.web.controller.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.admin.MallRegionMappingService;
import com.greatonce.oms.domain.admin.MallRegionMapping;
import com.greatonce.oms.query.admin.MallRegionMappingQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Coby
 * @version 2017-11-30
 */
@RestController
@RequestMapping("/admin/mallRegionMapping")
@CrossOrigin
public class MallRegionMappingController implements
    PageListController<MallRegionMapping, MallRegionMappingQuery> {

  @Autowired
  private MallRegionMappingService mallRegionMappingService;

  @Override
  public BizService<MallRegionMapping, MallRegionMappingQuery> getBizService() {
    return mallRegionMappingService;
  }
}
