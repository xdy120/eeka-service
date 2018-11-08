package com.greatonce.oms.biz.trade;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderOutDetail;
import com.greatonce.oms.query.trade.ReturnOrderOutDetailQuery;

/**
 * ReturnOrderOutDetail <br/>
 * 退换货单换出商品
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ReturnOrderOutDetailService extends
    DetailService<ReturnOrder, ReturnOrderOutDetail, ReturnOrderOutDetailQuery> {

  int replaceOutDetail(ReturnOrderOutDetail detail);
}