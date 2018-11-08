package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.SalesOrderDiscountDao;
import com.greatonce.oms.domain.trade.SalesOrderDiscount;
import com.greatonce.oms.query.trade.SalesOrderDiscountQuery;
import org.springframework.stereotype.Repository;

/**
 * SalesOrderDiscount <br/>
 * 销售订单优惠明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SalesOrderDiscountDaoImpl extends AbstractOmsDao<SalesOrderDiscount,SalesOrderDiscountQuery> implements SalesOrderDiscountDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.SalesOrderDiscountMapper";
    }
    
}