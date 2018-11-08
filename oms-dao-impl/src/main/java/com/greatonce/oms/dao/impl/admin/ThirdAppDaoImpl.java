package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.ThirdAppDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.ThirdApp;
import com.greatonce.oms.query.admin.ThirdAppQuery;
import org.springframework.stereotype.Repository;

/**
 * ThirdApp <br/>
 * 第三方应用
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class ThirdAppDaoImpl extends AbstractAdminDao<ThirdApp,ThirdAppQuery> implements ThirdAppDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.admin.ThirdAppMapper";
    }

}