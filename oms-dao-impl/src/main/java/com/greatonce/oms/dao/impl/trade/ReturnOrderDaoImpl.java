package com.greatonce.oms.dao.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.trade.ExchangeOccupancyBO;
import com.greatonce.oms.bo.trade.ReturnOrderExportBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.trade.ReturnOrderDao;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ReturnOrder <br/>
 * 退换货单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ReturnOrderDaoImpl extends AbstractOmsDao<ReturnOrder,ReturnOrderQuery> implements ReturnOrderDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.trade.ReturnOrderMapper";
    }

    @Override
    public List<Long> listIdByExample(ReturnOrder returnOrder) {
        return getSqlSessionDecorator().selectList(getStatement("listIdByExample"),returnOrder);
    }

    @Override
    public ReturnOrder getOrderAndDetailByKey(Long returnOrderId) {
        return getSqlSessionDecorator().selectOne(getStatement("getOrderAndDetailByKey"),returnOrderId);
    }

    @Override
    public PageList<ReturnOrder> listPage(ReturnOrderQuery returnOrderQuery, int page, int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("advanceQuery"),returnOrderQuery,page,pageSize);
    }

    @Override
    public List<ReturnOrder> getBySalesOrderIdAndSkuCode(Long salesOrderId){
        return getSqlSessionDecorator().selectList(getStatement("getBySalesOrderIdAndSkuCode"),salesOrderId);
    }

    @Override
    public PageList<ExchangeOccupancyBO> getStockOccupancyDetail(StockQuery stockQuery, int page,
        int pageSize) {
        return getSqlSessionDecorator()
            .selectList(getStatement("stockOccupancyDetail"), stockQuery, page, pageSize);
    }

    @Override
    public PageList<ReturnOrderExportBO> listExportReturn(ReturnOrderQuery query, int page,
        int pageSize) {
        return getSqlSessionDecorator().selectList(getStatement("listExportReturn"), query, page, pageSize);
    }


}