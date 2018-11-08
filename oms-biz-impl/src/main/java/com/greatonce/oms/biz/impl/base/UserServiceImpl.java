package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.SecurityUtil;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.dao.base.UserDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.UserQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * User <br/> 用户.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class UserServiceImpl extends AbstractEnableService<User, UserQuery> implements UserService {

  @Value("${oms.system.default.password:oms888888}")
  private String defaultPassword;

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_USER);
  @Autowired
  private UserDao dao;

  @Override
  protected QueryDao<User, UserQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(User entity) {
    super.initDefaultValue(entity);
    entity.setLoginPassword(SecurityUtil.md5Hex(defaultPassword));
    entity.setEnable(true);
    entity.setSystem(false);
  }

  @Override
  protected void validateCreate(User entity) {
    Assert.notFalse(dao.checkExists(entity), "登录名或昵称已存在");
  }

  @Override
  public User login(String loginName, String loginPassword) {
    User eg = new User();
    eg.setLoginName(loginName);
    eg.setLoginPassword(SecurityUtil.md5Hex(loginPassword));
    return getDAO().getByExample(eg);
  }

  @Override
  public void modifyPassword(Long userId, String pwd, String newPwd) {
    User eg = new User();
    eg.setUserId(userId);
    eg.setLoginPassword(SecurityUtil.md5Hex(pwd));
    User u = getDAO().getByExample(eg);
    Assert.notNull(u, "原始密码校验不通过！");
    u.setLoginPassword(SecurityUtil.md5Hex(newPwd));
    modify(u);
  }

  @Override
  public void initPassword(Long userId) {
    User eg = new User();
    eg.setUserId(userId);
    eg.setLoginPassword(SecurityUtil.md5Hex(defaultPassword));
    modify(eg);
  }

  @Override
  public List<User> list(UserQuery userQuery) {
    if (userQuery == null) {
      userQuery = new UserQuery();
    }
    userQuery.setSystem(false);
    return super.list(userQuery);
  }

  @Override
  public PageList<User> listPage(UserQuery userQuery, int page, int pageSize) {
    if (userQuery == null) {
      userQuery = new UserQuery();
    }
    userQuery.setSystem(false);
    return super.listPage(userQuery, page, pageSize);
  }

  @Override
  public int countUsers(Long departmentId) {
    return dao.countUsers(departmentId);
  }

  @Override
  public void validatePassword(Long userId) {
    User user = new User();
    user.setUserId(userId);
    user.setLoginPassword(SecurityUtil.md5Hex(defaultPassword));
    user = getDAO().getByExample(user);
    if (!Assert.isNull(user)) {
      throw new OmsException("当前登录密码与默认密码相同，请修改密码");
    }
  }
}