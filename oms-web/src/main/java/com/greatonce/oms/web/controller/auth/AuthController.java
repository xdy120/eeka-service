package com.greatonce.oms.web.controller.auth;

import com.greatonce.oms.biz.base.PrivilegeService;
import com.greatonce.oms.domain.admin.MenuOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-10-21 10:39
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

  @Autowired
  PrivilegeService privilegeService;

  @GetMapping(path = "/{userId}/module")
  public List<MenuOperation> listModule(@PathVariable("userId") Long userId) {
    return privilegeService.listUserModule(userId);
  }

  @GetMapping(path = "/{userId}/module/{moduleId}/menu")
  public List<MenuOperation> listMenu(@PathVariable("userId") Long userId,
      @PathVariable("moduleId") Long moduleId) {
    return privilegeService.listUserMenu(userId, moduleId);
  }

  @GetMapping(path = "/{userId}/menu/{menuId}/menuItem")
  public List<MenuOperation> listMenuItem(@PathVariable("userId") Long userId,
      @PathVariable("menuId") Long menuId) {
    return privilegeService.listUserMenuItem(userId, menuId);
  }

  @GetMapping(path = "/{userId}/menuItem/{menuItemId}/operator")
  public List<MenuOperation> listOperator(@PathVariable("userId") Long userId,
      @PathVariable("menuItemId") Long menuItemId) {
    return privilegeService.listUserOperator(userId, menuItemId);
  }
}
