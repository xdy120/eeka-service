package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.stock.StockOutExportBO;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.query.stock.StockOutOrderQuery;

/**
 * StockOutOrder <br/>
 * 出库单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface StockOutOrderDao extends QueryDao<StockOutOrder, StockOutOrderQuery> {

  PageList<StockOutExportBO> exportListStockOut(StockOutOrderQuery query, Integer page,
      Integer pageSize);
}