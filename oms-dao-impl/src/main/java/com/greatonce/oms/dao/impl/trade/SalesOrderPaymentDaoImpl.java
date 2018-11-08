package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.SalesOrderPaymentDao;
import com.greatonce.oms.domain.trade.SalesOrderPayment;
import com.greatonce.oms.query.trade.SalesOrderPaymentQuery;
import org.springframework.stereotype.Repository;

/**
 * SalesOrderPayment <br/>
 * 销售订单支付明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class SalesOrderPaymentDaoImpl extends AbstractOmsDao<SalesOrderPayment,SalesOrderPaymentQuery> implements SalesOrderPaymentDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.SalesOrderPaymentMapper";
    }

}