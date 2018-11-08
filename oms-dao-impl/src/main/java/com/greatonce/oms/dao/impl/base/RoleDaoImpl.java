package com.greatonce.oms.dao.impl.base;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.base.RoleDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Role;
import com.greatonce.oms.query.base.RoleQuery;
import org.springframework.stereotype.Repository;

/**
 * Role <br/>
 * 角色
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class RoleDaoImpl extends AbstractOmsDao<Role,RoleQuery> implements RoleDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.RoleMapper";
    }

    @Override
    public PageList<Role> listPage(RoleQuery filter, int page, int pageSize) {
        return getSqlSessionDecorator().selectList(this.getStatement("listPageLike"), filter,page,pageSize);
    }
}