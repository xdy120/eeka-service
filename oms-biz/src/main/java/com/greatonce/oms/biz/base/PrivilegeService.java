package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.admin.MenuOperation;
import com.greatonce.oms.domain.base.Privilege;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.query.base.PrivilegeQuery;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * 权限.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-30
 */
public interface PrivilegeService extends BizService<Privilege, PrivilegeQuery> {

  /**
   * 获取用户权限.
   *
   * @param userId 用户ID
   * @param type 权限类型
   */
  List<Privilege> listUserPrivilege(Long userId, PrivilegeType type);

  /**
   * 获取用户模块.
   *
   * @param userId 用户ID
   */
  List<MenuOperation> listUserModule(Long userId);

  /**
   * 获取用户模块下一级菜单.
   *
   * @param userId 用户ID
   * @param moduleId 模块ID
   */
  List<MenuOperation> listUserMenu(Long userId, Long moduleId);

  /**
   * 获取用户菜单项.
   *
   * @param userId 用户ID
   * @param menuId 菜单ID
   */
  List<MenuOperation> listUserMenuItem(Long userId, Long menuId);

  /**
   * 获取用户菜单下的操作.
   *
   * @param userId 用户ID
   * @param menuItemId 菜单ID
   */
  List<MenuOperation> listUserOperator(Long userId, Long menuItemId);

  /**
   * 获取用户的权限ID.
   *
   * @param userId 用户id
   * @param privilegeType 权限类型
   * @return ID集合
   */
  List<Long> listItems(Long userId, PrivilegeType privilegeType);

  /**
   * 过滤权限.
   *
   * @param list 集合
   * @param function ID函数
   * @param privilegeType 权限类型
   * @param <T> 泛型
   */
  <T> void filter(Long userId, List<T> list, Function<T, Long> function,
      PrivilegeType privilegeType);


  /**
   * 保存角色权限.
   */
  void saveRolePrivilege(Long roleId, List<Privilege> privileges);

  /**
   * 根据角色删除权限.
   *
   * @param roleId 权限ID
   */
  void removeByRoleId(Long roleId);
}