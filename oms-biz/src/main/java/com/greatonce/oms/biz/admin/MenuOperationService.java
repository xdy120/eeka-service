package com.greatonce.oms.biz.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.admin.MenuOperation;
import com.greatonce.oms.query.admin.MenuOperationQuery;

import java.util.List;

/**
 * MenuOperation <br/>
 * 菜单
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface MenuOperationService extends BizService<MenuOperation,MenuOperationQuery>{

    List<MenuOperation> listSimpleByExample(MenuOperation example);

    List<MenuOperation> listAll();

    List<MenuOperation> listModule();

    List<MenuOperation> listMenu(Long moduleId);

    List<MenuOperation> listMenuItem(Long menuId);

    List<MenuOperation> listOperator(Long menuItemId);
}