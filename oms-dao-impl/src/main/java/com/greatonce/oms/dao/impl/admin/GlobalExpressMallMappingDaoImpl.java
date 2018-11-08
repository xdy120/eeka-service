package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.GlobalExpressMallMappingDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.GlobalExpressMallMapping;
import com.greatonce.oms.query.admin.GlobalExpressMallMappingQuery;
import org.springframework.stereotype.Repository;

/**
 * GlobalExpressMallMapping <br/>
 * 全局快递商城映射
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class GlobalExpressMallMappingDaoImpl extends AbstractAdminDao<GlobalExpressMallMapping, GlobalExpressMallMappingQuery> implements GlobalExpressMallMappingDao {

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.admin.GlobalExpressMallMappingMapper";
    }

}