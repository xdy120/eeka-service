package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.bo.base.UserModifyPasswordBO;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.query.base.UserQuery;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("base/user")
@CrossOrigin
public class UserController implements PageListController<User, UserQuery>,
    EnableController<User, UserQuery> {

  @Resource
  private UserService userService;

  @Override
  public BizService<User, UserQuery> getBizService() {
    return userService;
  }

  @GetMapping("/department/{departmentId}")
  public int countUsers(@PathVariable("departmentId") Long departmentId) {
    return userService.countUsers(departmentId);
  }

  @PutMapping("/{userId}/modifyPwd")
  public void modifyPassword(@PathVariable("userId") Long userId,
      @RequestBody UserModifyPasswordBO bo) {
    userService.modifyPassword(userId, bo.getPwd(), bo.getNewPwd());
  }

  @PutMapping("/{userId}/initPwd")
  public void initPassword(@PathVariable("userId") Long userId) {
    userService.initPassword(userId);
  }

  @GetMapping("/{userId}/validatePwd")
  public void validatePassword(@PathVariable("userId") Long userId) {
    userService.validatePassword(userId);
  }

}
