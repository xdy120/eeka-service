package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.RoleUserService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.RoleUserDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.RoleUser;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.RoleUserQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * RoleUser <br/> 角色用户关系.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class RoleUserServiceImpl extends AbstractService<RoleUser, RoleUserQuery> implements
    RoleUserService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_USER);
  private static final Logger LOGGER = LoggerFactory.getLogger(RoleUserServiceImpl.class);

  @Autowired
  RoleUserDao dao;

  @Override
  protected QueryDao<RoleUser, RoleUserQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public void removeByRoleId(Long roleId) {
    RoleUser eg = new RoleUser();
    eg.setRoleId(roleId);
    deleteByExample(eg);
  }

  @Override
  @CacheEvict(value = "PRIVILEGE", allEntries = true)
  public int batchCreate(Collection<? extends RoleUser> collection) {
    RoleUser eg = new RoleUser();
    eg.setUserId(collection.stream().findFirst().get().getUserId());
    try {
      collection.forEach(this::initDefaultValue);
      int count = getTransactionTemplate().executeWithResult(() -> {
        getDAO().deleteByExample(eg);
        return insertBatch(collection);
      });
      BIZ_LOGGER.log(eg.getUserId(), "设置角色");
      return count;
    } catch (Exception e) {
      LOGGER.error("用户设置角色失败,角色信息:{}", JsonUtil.toJson(collection));
      LOGGER.error("用户设置角色失败,堆栈信息:", e);
      throw new OmsException("用户设置角色失败");
    }
  }

  @Override
  public int batchModify(Collection<? extends RoleUser> collection) {
    return updateBatch(collection);
  }
}