package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.query.stock.StockOutOrderDetailQuery;
import java.util.List;

/**
 * StockOutOrderDetail <br/> 出库单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface StockOutOrderDetailDao extends
    QueryDao<StockOutOrderDetail, StockOutOrderDetailQuery> {

  List<StockOutOrderDetail> listAvailable(Long stockOutOrderId);

  List<StockOutOrderDetail> listSaleable(Long stockOutOrderId);
}