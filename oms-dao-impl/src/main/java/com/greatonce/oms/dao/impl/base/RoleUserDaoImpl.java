package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.RoleUserDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.RoleUser;
import com.greatonce.oms.query.base.RoleUserQuery;
import org.springframework.stereotype.Repository;

/**
 * RoleUser <br/>
 * 角色用户关系
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class RoleUserDaoImpl extends AbstractOmsDao<RoleUser,RoleUserQuery> implements RoleUserDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.RoleUserMapper";
    }



}