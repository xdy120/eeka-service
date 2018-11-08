package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.WmsAppDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.WmsApp;
import com.greatonce.oms.query.admin.WmsAppQuery;
import org.springframework.stereotype.Repository;

/**
 * WmsApp <br/>
 * 仓库应用
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class WmsAppDaoImpl extends AbstractAdminDao<WmsApp,WmsAppQuery> implements WmsAppDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.admin.WmsAppMapper";
    }

}