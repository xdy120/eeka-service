package com.greatonce.oms.web.controller.admin;

import com.greatonce.oms.biz.admin.OrganizationService;
import com.greatonce.oms.domain.admin.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangc on 2017/11/2.
 */
@RestController
@RequestMapping("/admin/organization")
@CrossOrigin
public class OrganizationController {

  @Autowired
  private OrganizationService organizationService;

  @RequestMapping(method = RequestMethod.GET, path = "/domain/{domain}")
  public Organization list(@PathVariable("domain") String domain) {
    return organizationService.getSimpleByDomain(domain);
  }
}
