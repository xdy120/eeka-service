package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.RoleService;
import com.greatonce.oms.domain.base.Role;
import com.greatonce.oms.query.base.RoleQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangc on 2017/10/24.
 */
@RestController
@RequestMapping("/base/role")
@CrossOrigin
public class RoleController implements PageListController<Role, RoleQuery> {

  @Resource
  RoleService roleService;

  @Override
  public BizService<Role, RoleQuery> getBizService() {
    return roleService;
  }

}
