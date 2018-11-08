package com.greatonce.oms.dao.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.query.trade.SalesOrderDetailQuery;
import java.util.List;
import java.util.Map;

/**
 * SalesOrderDetail <br/>
 * 销售订单明细
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SalesOrderDetailDao extends QueryDao<SalesOrderDetail, SalesOrderDetailQuery> {

  int replace(Map<String, SalesOrderDetail> detail);

  List<SalesOrderDetail> listSimple(SalesOrderDetailQuery detail);

  List<SalesOrderDetail> listSimpleNormal(Long salesOrderId);

  List<SalesOrderDetail> listToMallDeliveryDetailsInfo(Long salesOrderId);

  void clearOos();

  List<SalesOrderDetail> listDispatchableBySalesOrderId(Long salesOrderId);
}