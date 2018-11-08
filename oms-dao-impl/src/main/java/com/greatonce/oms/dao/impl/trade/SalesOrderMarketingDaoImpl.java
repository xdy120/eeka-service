package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.SalesOrderMarketingDao;
import com.greatonce.oms.domain.trade.SalesOrderMarketing;
import com.greatonce.oms.query.trade.SalesOrderMarketingQuery;
import org.springframework.stereotype.Repository;

/**
 * SalesOrderMarketing <br/>
 * 销售订单活动表
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SalesOrderMarketingDaoImpl extends AbstractOmsDao<SalesOrderMarketing,SalesOrderMarketingQuery> implements SalesOrderMarketingDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.SalesOrderMarketingMapper";
    }
    
}