package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.PrivilegeService;
import com.greatonce.oms.biz.base.RoleService;
import com.greatonce.oms.biz.base.RoleUserService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.RoleDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Role;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.RoleQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Role <br/> 角色.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class RoleServiceImpl extends AbstractService<Role, RoleQuery> implements RoleService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_ROLE);
  private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
  @Autowired
  private RoleDao dao;
  @Resource
  private RoleUserService roleUserService;
  @Resource
  private PrivilegeService privilegeService;

  @Override
  protected QueryDao<Role, RoleQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected void initDefaultValue(Role entity) {
    super.initDefaultValue(entity);
    entity.setSystem(false);
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }


  @Override
  public List<Role> list(RoleQuery roleQuery) {
    if (roleQuery == null) {
      roleQuery = new RoleQuery();
    }
    roleQuery.setSystem(false);
    return super.list(roleQuery);
  }

  @Override
  public PageList<Role> listPage(RoleQuery roleQuery, int page, int pageSize) {
    if (roleQuery == null) {
      roleQuery = new RoleQuery();
    }
    roleQuery.setSystem(false);
    return super.listPage(roleQuery, page, pageSize);
  }

  @Override
  public int remove(Role record) {
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        privilegeService.removeByRoleId(record.getRoleId());
        roleUserService.removeByRoleId(record.getRoleId());
        return delete(record.getRoleId());
      });
      BIZ_LOGGER.log(record.getRoleId(), BizLogger.DELETE);
      return count;
    } catch (Exception e) {
      LOGGER.error("删除角色失败,角色信息:{}", JsonUtil.toJson(record));
      LOGGER.error("删除角色失败,堆栈信息:", e);
      throw new OmsException("删除角色失败,请联系管理员");
    }
  }
}