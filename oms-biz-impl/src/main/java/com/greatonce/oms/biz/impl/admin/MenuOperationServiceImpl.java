package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.admin.MenuOperationService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.MenuOperationDao;
import com.greatonce.oms.domain.admin.MenuOperation;
import com.greatonce.oms.domain.enums.MenuItemType;
import com.greatonce.oms.query.admin.MenuOperationQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MenuOperation <br/>
 * 菜单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-14
 */
@Service
public class MenuOperationServiceImpl extends
    AbstractService<MenuOperation, MenuOperationQuery> implements MenuOperationService {

  @Autowired
  private MenuOperationDao dao;

  @Override
  protected QueryDao<MenuOperation, MenuOperationQuery> getDAO() {
    return this.dao;
  }

  @Override
  public List<MenuOperation> listSimpleByExample(MenuOperation example) {
    return dao.listSimpleByExample(example);
  }

  @Override
  public List<MenuOperation> listAll() {
    List<MenuOperation> list = list(null);
    List<MenuOperation> level1 = new ArrayList<>(10);
    Map<Long, MenuOperation> map = CollectionUtil.listToMap(list, x -> x.getItemId());
    MenuOperation parent;
    for (MenuOperation menuOperation : list) {
      if (menuOperation.getParentId() == 0) {
        level1.add(menuOperation);
      } else {
        parent = map.get(menuOperation.getParentId());
        if (parent.getChildren() == null) {
          parent.setChildren(new ArrayList<>(5));
        }
        parent.getChildren().add(menuOperation);
      }
    }
    return level1;
  }


  @Override
  public List<MenuOperation> listModule() {
    MenuOperation eg = new MenuOperation();
    eg.setItemType(MenuItemType.MODULE);
    eg.setSystem(false);
    return listSimpleByExample(eg);
  }

  @Override
  public List<MenuOperation> listMenu(Long moduleId) {
    MenuOperation eg = new MenuOperation();
    eg.setItemType(MenuItemType.MENU);
    eg.setParentId(moduleId);
    eg.setSystem(false);
    return listSimpleByExample(eg);
  }

  @Override
  public List<MenuOperation> listMenuItem(Long menuId) {
    MenuOperation eg = new MenuOperation();
    eg.setItemType(MenuItemType.MENU_ITEM);
    eg.setParentId(menuId);
    eg.setSystem(false);
    return listSimpleByExample(eg);
  }

  @Override
  public List<MenuOperation> listOperator(Long menuItemId) {
    MenuOperation eg = new MenuOperation();
    eg.setItemType(MenuItemType.OPERATOR);
    eg.setParentId(menuItemId);
    eg.setSystem(false);
    return listSimpleByExample(eg);
  }
}