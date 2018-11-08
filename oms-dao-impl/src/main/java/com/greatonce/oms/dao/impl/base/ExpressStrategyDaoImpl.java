package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.ExpressStrategyDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.ExpressStrategy;
import com.greatonce.oms.query.base.ExpressStrategyQuery;
import org.springframework.stereotype.Repository;

/**
 * ExpressStrategy <br/>
 * 快递策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class ExpressStrategyDaoImpl extends AbstractOmsDao<ExpressStrategy,ExpressStrategyQuery> implements ExpressStrategyDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.base.ExpressStrategyMapper";
    }

}