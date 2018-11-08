package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.ThirdAppAuthDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.ThirdAppAuth;
import com.greatonce.oms.query.admin.ThirdAppAuthQuery;
import org.springframework.stereotype.Repository;

/**
 * ThirdAppAuth <br/>
 * 第三方应用授权
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class ThirdAppAuthDaoImpl extends AbstractAdminDao<ThirdAppAuth,ThirdAppAuthQuery> implements ThirdAppAuthDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.admin.ThirdAppAuthMapper";
    }

}