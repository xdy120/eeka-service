package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.SalesOrderInvoiceDao;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import com.greatonce.oms.query.trade.SalesOrderInvoiceQuery;
import org.springframework.stereotype.Repository;

/**
 * SalesOrderInvoice <br/>
 * 销售订单发票明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class SalesOrderInvoiceDaoImpl extends AbstractOmsDao<SalesOrderInvoice,SalesOrderInvoiceQuery> implements SalesOrderInvoiceDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.SalesOrderInvoiceMapper";
    }
    
}