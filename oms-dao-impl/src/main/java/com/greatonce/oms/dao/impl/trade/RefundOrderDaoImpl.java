package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.RefundOrderDao;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.query.trade.RefundOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * RefundOrder <br/>
 * 退款单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class RefundOrderDaoImpl extends AbstractOmsDao<RefundOrder,RefundOrderQuery> implements RefundOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.RefundOrderMapper";
    }

}