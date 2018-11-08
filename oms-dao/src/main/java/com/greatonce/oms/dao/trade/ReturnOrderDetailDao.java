package com.greatonce.oms.dao.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.query.trade.ReturnOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;

/**
 * ReturnOrderDetail <br/>
 * 退换货单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface ReturnOrderDetailDao extends QueryDao<ReturnOrderDetail,ReturnOrderDetailQuery>{



  PageList<ReturnOrderDetail> detailListPage(ReturnOrderQuery query, int page, int pageSize);
}