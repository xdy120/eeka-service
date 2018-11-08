package com.greatonce.oms.dao.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.query.trade.DispatchOrderDetailQuery;

import java.util.List;

/**
* DispatchOrderDetail <br/>
* 配货单明细
*
* @author code-generator
* @author Shenzhen Greatonce Co Ltd
* @version 3.0
*/

public interface DispatchOrderDetailDao extends QueryDao<DispatchOrderDetail,DispatchOrderDetailQuery> {

    List<DispatchOrderDetail> listSimple(Long dispatchOrderId);
}
