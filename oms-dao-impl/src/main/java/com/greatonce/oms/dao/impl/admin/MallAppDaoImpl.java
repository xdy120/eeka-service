package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.MallAppDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.MallApp;
import com.greatonce.oms.query.admin.MallAppQuery;
import org.springframework.stereotype.Repository;

/**
 * MallApp <br/>
 * 商城应用
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

@Repository
public class MallAppDaoImpl extends AbstractAdminDao<MallApp,MallAppQuery> implements MallAppDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.admin.MallAppMapper";
    }
    
}