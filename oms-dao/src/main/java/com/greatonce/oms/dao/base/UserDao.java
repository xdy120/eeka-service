package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.query.base.UserQuery;

/**
 * User <br/>
 * 用户
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface UserDao extends QueryDao<User, UserQuery> {

  int countUsers(Long departmentId);

  boolean checkExists(User user);
}