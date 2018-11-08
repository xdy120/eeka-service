package com.greatonce.oms.biz.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.query.trade.ReturnOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import java.util.List;

/**
 * ReturnOrderDetail <br/>
 * 退换货单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */
public interface ReturnOrderDetailService extends DetailService<ReturnOrder,ReturnOrderDetail,ReturnOrderDetailQuery> {

  PageList<ReturnOrderDetail> detailListPage(ReturnOrderQuery query, int page, int pageSize);

  List<ReturnOrderDetail> listByReturnOrderId(Long returnOrderId);
}