package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.PrivilegeService;
import com.greatonce.oms.domain.base.Privilege;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.query.base.PrivilegeQuery;
import com.greatonce.oms.web.controller.FullListController;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/2.
 */
@RestController
@RequestMapping("/base/privilege")
@CrossOrigin
public class PrivilegeController implements FullListController<Privilege, PrivilegeQuery> {

  @Resource
  PrivilegeService privilegeService;

  @Override
  public BizService<Privilege, PrivilegeQuery> getBizService() {
    return privilegeService;
  }

  @PostMapping(path = "/{roleId}/save")
  public void saveRolePrivilege(@PathVariable("roleId") Long roleId,
      @RequestBody List<Privilege> privileges) {
    privilegeService.saveRolePrivilege(roleId, privileges);
  }

  @GetMapping(path = "/{roleId}/{type}")
  public List<Privilege> list(@PathVariable("roleId") Long roleId,
      @PathVariable("type") List<PrivilegeType> type) {
    PrivilegeQuery privilege = new PrivilegeQuery();
    privilege.setRoleId(roleId);
    privilege.setPrivilegeTypes(type);
    return privilegeService.list(privilege);
  }

}
