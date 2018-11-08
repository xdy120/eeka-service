package com.greatonce.oms.dao.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.trade.ReturnNoticeOrderExportBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.ReturnNoticeOrderDao;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.query.trade.ReturnNoticeOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * ReturnNoticeOrder <br/>
 * 退货通知单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ReturnNoticeOrderDaoImpl extends AbstractOmsDao<ReturnNoticeOrder,ReturnNoticeOrderQuery> implements ReturnNoticeOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.ReturnNoticeOrderMapper";
    }

    @Override
    public PageList<ReturnNoticeOrder> listPage(ReturnNoticeOrderQuery returnNoticeOrderQuery, int page, int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("listPageCustom"),returnNoticeOrderQuery,page,pageSize);
    }

    @Override
    public PageList<ReturnNoticeOrderExportBO> listExportReturnNoticeOrder(
        ReturnNoticeOrderQuery query, int page, int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("listExportReturnNoticeOrder"),query,page,pageSize);
    }
}