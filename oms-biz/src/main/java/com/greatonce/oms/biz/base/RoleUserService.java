package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.base.RoleUser;
import com.greatonce.oms.query.base.RoleUserQuery;

/**
 * RoleUser <br/>
 * ???????
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-30
 */
public interface RoleUserService extends BatchBizService<RoleUser, RoleUserQuery> {

  void removeByRoleId(Long roleId);
}