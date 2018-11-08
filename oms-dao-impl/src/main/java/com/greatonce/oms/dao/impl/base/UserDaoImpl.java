package com.greatonce.oms.dao.impl.base;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.dao.base.UserDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.query.base.UserQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * User <br/>
 * 用户
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class UserDaoImpl extends AbstractOmsDao<User, UserQuery> implements UserDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.base.UserMapper";
  }

  @Override
  public int countUsers(Long departmentId) {
    return this.getSqlSessionDecorator().selectOne(getStatement("countUsers"), departmentId);
  }

  @Override
  public boolean checkExists(User user) {
    Integer val = this.getSqlSessionDecorator()
        .<Integer>selectOne(getStatement("checkExists"), user);
    return val != null && val > 0;
  }
}