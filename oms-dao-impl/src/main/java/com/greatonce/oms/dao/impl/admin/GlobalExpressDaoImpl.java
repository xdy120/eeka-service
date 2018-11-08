package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.GlobalExpressDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.GlobalExpress;
import com.greatonce.oms.query.admin.GlobalExpressQuery;
import org.springframework.stereotype.Repository;

/**
 * GlobalExpress <br/>
 * 全局快递
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class GlobalExpressDaoImpl extends AbstractAdminDao<GlobalExpress,GlobalExpressQuery> implements GlobalExpressDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.admin.GlobalExpressMapper";
    }

}