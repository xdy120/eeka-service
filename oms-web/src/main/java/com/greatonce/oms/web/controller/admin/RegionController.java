package com.greatonce.oms.web.controller.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.TreeBizService;
import com.greatonce.oms.biz.admin.RegionService;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.query.admin.RegionQuery;
import com.greatonce.oms.web.controller.FullListController;
import com.greatonce.oms.web.controller.TreeController;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Coby
 * @version 2017-11-30
 */
@RestController
@RequestMapping("/admin/region")
@CrossOrigin
public class RegionController implements FullListController<Region, RegionQuery>,
    TreeController<Region> {

  @Autowired
  private RegionService regionService;

  @Override
  public BizService<Region, RegionQuery> getBizService() {
    return regionService;
  }

  @Override
  public TreeBizService<Region> getTreeBizService() {
    return regionService;
  }

  @GetMapping("/all")
  List<Region> listAll() {
    return regionService.listAll();
  }

  @GetMapping("/simple")
  List<Region> listSimple() {
    return regionService.listSimple();
  }
}
