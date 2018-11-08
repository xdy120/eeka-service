package com.greatonce.oms.dao.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.vip.VipSalesOrderDetail;
import com.greatonce.oms.query.vip.VipSalesOrderDetailQuery;
import java.util.List;

/**
 * VipSalesOrderDetail <br/> 唯品销售单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

public interface VipSalesOrderDetailDao extends
    QueryDao<VipSalesOrderDetail, VipSalesOrderDetailQuery> {

  List<String> listTradeIdsByStoreId(Long storeId);
}
