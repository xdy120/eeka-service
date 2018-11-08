package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.admin.MenuOperationService;
import com.greatonce.oms.biz.base.PrivilegeService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.PrivilegeDao;
import com.greatonce.oms.domain.admin.MenuOperation;
import com.greatonce.oms.domain.base.Privilege;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.query.base.PrivilegeQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;


/**
 * Privilege <br/> 权限.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class PrivilegeServiceImpl extends AbstractService<Privilege, PrivilegeQuery> implements
    PrivilegeService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_ROLE);
  private static final String CACHE_NAME = "PRIVILEGE";


  @Autowired
  private PrivilegeDao dao;
  @Autowired
  private MenuOperationService menuOperationService;

  @Override
  protected QueryDao<Privilege, PrivilegeQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'USER_PRIVILEGE_'+#userId")
  public List<Privilege> listUserPrivilege(Long userId, PrivilegeType type) {
    return dao.listUserPrivilege(userId, type);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'USER_PRIVILEGE_MODEL_'+#userId")
  public List<MenuOperation> listUserModule(Long userId) {
    List<MenuOperation> modules = menuOperationService.listModule();
    filter(userId, modules, MenuOperation::getItemId, PrivilegeType.MODULE);
    return modules;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'USER_PRIVILEGE_MENU_'+#userId+#moduleId")
  public List<MenuOperation> listUserMenu(Long userId, Long moduleId) {
    List<MenuOperation> menus = menuOperationService.listMenu(moduleId);
    filter(userId, menus, MenuOperation::getItemId, PrivilegeType.MENU);
    return menus;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'USER_PRIVILEGE_MENU_ITEM'+#userId+#menuId")
  public List<MenuOperation> listUserMenuItem(Long userId, Long menuId) {
    List<MenuOperation> menus = menuOperationService.listMenuItem(menuId);
    filter(userId, menus, MenuOperation::getItemId, PrivilegeType.MENU_ITEM);
    return menus;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'USER_PRIVILEGE_OPERATOR_'+#userId+#menuItemId")
  public List<MenuOperation> listUserOperator(Long userId, Long menuItemId) {
    List<MenuOperation> operations = menuOperationService.listOperator(menuItemId);
    filter(userId, operations, MenuOperation::getItemId, PrivilegeType.OPERATOR);
    return operations;
  }

  @Override
  public List<Long> listItems(Long userId, PrivilegeType privilegeType) {
    return listUserPrivilege(userId, privilegeType).stream().map(Privilege::getItemId).distinct()
        .collect(Collectors.toList());
  }

  @Override

  public <T> void filter(Long userId, List<T> list, Function<T, Long> function,
      PrivilegeType privilegeType) {
    List<Privilege> privileges = listUserPrivilege(userId, privilegeType);
    Set<Long> itemIds = privileges.stream().map(Privilege::getItemId).collect(Collectors.toSet());
    list.removeIf(x -> !itemIds.contains(function.apply(x)));
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public void saveRolePrivilege(Long roleId, List<Privilege> privileges) {
    Assert.notNull(roleId, "角色不能为空！");
    Assert.notEmpty(privileges, "角色权限不能为空！");
    Privilege eg = new Privilege();
    eg.setRoleId(roleId);
    privileges.forEach(this::initDefaultValue);
    getTransactionTemplate().execute(() -> {
      privileges.stream().map(Privilege::getPrivilegeType).distinct().forEach(x -> {
        eg.setPrivilegeType(x);
        deleteByExample(eg);
      });
      insertBatch(privileges);
    });

    BIZ_LOGGER.log(roleId, "授权");
  }

  @Override
  public void removeByRoleId(Long roleId) {
    Privilege eg = new Privilege();
    eg.setRoleId(roleId);
    deleteByExample(eg);
  }
}