package com.greatonce.oms.dao.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.database.PageList;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.query.trade.DispatchOrderQuery;

import java.util.Collection;
import java.util.List;

/**
* DispatchOrder <br/>
* 配货单
*
* @author code-generator
* @author Shenzhen Greatonce Co Ltd
* @version 3.0
*/

public interface DispatchOrderDao extends QueryDao<DispatchOrder,DispatchOrderQuery> {

    List<DispatchOrder> listBySalesOrderId(Long salesOrderId);

    DispatchOrder getSimpleInfo(Long dispatchOrderId);

    PageList<DispatchOrder> listPageByConditions(DispatchOrderQuery filter, int page, int pageSize);

    List<DispatchOrder> listBySalesOrderDetailId(Long salesOrderId, Collection<Long> salesOrdreDetailIds);
}
