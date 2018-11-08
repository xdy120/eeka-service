package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.RefundOrderDetailDao;
import com.greatonce.oms.domain.trade.RefundOrderDetail;
import com.greatonce.oms.query.trade.RefundOrderDetailQuery;
import org.springframework.stereotype.Repository;

/**
 * RefundOrderDetail <br/>
 * 退款单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class RefundOrderDetailDaoImpl extends AbstractOmsDao<RefundOrderDetail,RefundOrderDetailQuery> implements RefundOrderDetailDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.RefundOrderDetailMapper";
    }
    
}