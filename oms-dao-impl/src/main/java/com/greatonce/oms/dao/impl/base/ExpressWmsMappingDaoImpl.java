package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.ExpressWmsMappingDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.ExpressWmsMapping;
import com.greatonce.oms.query.base.ExpressWmsMappingQuery;
import org.springframework.stereotype.Repository;

/**
 * ExpressWmsMapping <br/>
 * 仓库快递映射
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ExpressWmsMappingDaoImpl extends AbstractOmsDao<ExpressWmsMapping,ExpressWmsMappingQuery> implements ExpressWmsMappingDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.ExpressWmsMappingMapper";
    }
    
}