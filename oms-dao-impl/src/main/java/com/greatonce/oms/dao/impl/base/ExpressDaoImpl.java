package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.ExpressDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.query.base.ExpressQuery;
import org.springframework.stereotype.Repository;

/**
 * Express <br/>
 * 快递
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ExpressDaoImpl extends AbstractOmsDao<Express,ExpressQuery> implements ExpressDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.ExpressMapper";
    }
    
}