package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.SalesOrderSubDao;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.query.trade.SalesOrderSubQuery;
import org.springframework.stereotype.Repository;

/**
 * SalesOrderSub <br/>
 * 订单附属信息
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SalesOrderSubDaoImpl extends AbstractOmsDao<SalesOrderSub,SalesOrderSubQuery> implements SalesOrderSubDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.SalesOrderSubMapper";
    }
    
}