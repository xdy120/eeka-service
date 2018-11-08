package com.greatonce.oms.dao.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.trade.ReturnNoticeOrderExportBO;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.query.trade.ReturnNoticeOrderQuery;

/**
 * ReturnNoticeOrder <br/>
 * 退货通知单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface ReturnNoticeOrderDao extends QueryDao<ReturnNoticeOrder,ReturnNoticeOrderQuery>{

  PageList<ReturnNoticeOrderExportBO> listExportReturnNoticeOrder(ReturnNoticeOrderQuery query, int page, int pageSize);
}