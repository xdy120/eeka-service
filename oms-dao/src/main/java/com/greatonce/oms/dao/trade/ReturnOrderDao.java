package com.greatonce.oms.dao.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.trade.ExchangeOccupancyBO;
import com.greatonce.oms.bo.trade.ReturnOrderExportBO;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;

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

public interface ReturnOrderDao extends QueryDao<ReturnOrder,ReturnOrderQuery>{

    //按条件查询当前单据
    List<Long> listIdByExample(ReturnOrder returnOrder);

    ReturnOrder getOrderAndDetailByKey(Long returnOrderId);

    List<ReturnOrder> getBySalesOrderIdAndSkuCode(Long salesOrderId);

    PageList<ExchangeOccupancyBO> getStockOccupancyDetail(StockQuery stockQuery, int page, int pageSize);

    PageList<ReturnOrderExportBO> listExportReturn(ReturnOrderQuery query, int page, int pageSize);
}