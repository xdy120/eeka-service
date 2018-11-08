package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.query.base.UserQuery;

/**
 * 用户.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-30
 */
public interface UserService extends BizService<User, UserQuery>, EnableBizService<User> {

  int countUsers(Long departmentId);

  User login(String loginName, String loginPassword);

  void modifyPassword(Long userId, String pwd, String newPwd);

  void initPassword(Long userId);

  void validatePassword(Long userId);
}