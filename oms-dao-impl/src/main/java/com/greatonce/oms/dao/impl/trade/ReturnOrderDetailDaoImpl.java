package com.greatonce.oms.dao.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.ReturnOrderDetailDao;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.query.trade.ReturnOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * ReturnOrderDetail <br/>
 * 退换货单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ReturnOrderDetailDaoImpl extends AbstractOmsDao<ReturnOrderDetail,ReturnOrderDetailQuery> implements ReturnOrderDetailDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.ReturnOrderDetailMapper";
    }

    @Override
    public PageList<ReturnOrderDetail> detailListPage(ReturnOrderQuery query, int page, int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("detailListPage"), query, page, pageSize);
    }
    
}