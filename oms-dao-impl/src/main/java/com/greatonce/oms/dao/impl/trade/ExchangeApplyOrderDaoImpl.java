package com.greatonce.oms.dao.impl.trade;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.ExchangeApplyOrderDao;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.query.trade.ExchangeApplyOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * ExchangeApplyOrder <br/>
 * 换货申请单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class ExchangeApplyOrderDaoImpl extends AbstractOmsDao<ExchangeApplyOrder,ExchangeApplyOrderQuery> implements ExchangeApplyOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.ExchangeApplyOrderMapper";
    }

}