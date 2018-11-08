package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.MenuOperationDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.MenuOperation;
import com.greatonce.oms.query.admin.MenuOperationQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * MenuOperation <br/>
 * 菜单
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class MenuOperationDaoImpl extends AbstractAdminDao<MenuOperation,MenuOperationQuery> implements MenuOperationDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.admin.MenuOperationMapper";
    }

    @Override
    public List<MenuOperation> listSimpleByExample(MenuOperation example){
        return getSqlSessionDecorator().selectList(getStatement("listSimpleByExample"), example);
    }

    @Override
    public List<MenuOperation> list(MenuOperationQuery menuOperationQuery) {
        return getSqlSessionDecorator().selectList(getStatement("listASC"),menuOperationQuery);
    }
}