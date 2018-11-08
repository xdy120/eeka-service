package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.RefundApplyOrderDao;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.query.trade.RefundApplyOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * RefundApplyOrder <br/>
 * 售后申请单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class RefundApplyOrderDaoImpl extends AbstractOmsDao<RefundApplyOrder,RefundApplyOrderQuery> implements RefundApplyOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.RefundApplyOrderMapper";
    }

}