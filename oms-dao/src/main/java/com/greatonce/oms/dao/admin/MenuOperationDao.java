package com.greatonce.oms.dao.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.admin.MenuOperation;
import com.greatonce.oms.query.admin.MenuOperationQuery;

import java.util.List;

/**
 * MenuOperation <br/>
 * 菜单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

public interface MenuOperationDao extends QueryDao<MenuOperation,MenuOperationQuery>{

    List<MenuOperation> listSimpleByExample(MenuOperation example);
}