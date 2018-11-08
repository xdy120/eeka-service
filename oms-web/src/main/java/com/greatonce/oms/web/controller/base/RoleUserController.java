package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.base.RoleUserService;
import com.greatonce.oms.domain.base.RoleUser;
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
 * Created by wangc on 2017/10/31.
 */
@RestController
@RequestMapping("/base/roleUser")
@CrossOrigin
public class RoleUserController {

  @Resource
  RoleUserService roleUserService;

  @PostMapping(path = "batch")
  public void batch(@RequestBody List<RoleUser> roleUsers) {
    roleUserService.batchCreate(roleUsers);
  }

  @GetMapping(path = "/user/{userId}")
  public List<RoleUser> listRoleByUserId(@PathVariable("userId") Long userId) {
    RoleUser roleUser = new RoleUser();
    roleUser.setUserId(userId);
    return roleUserService.listExample(roleUser);
  }
}
