package com.greatonce.oms.dao.purchase;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseOrderDetailQuery;
import java.util.List;

/**
 * PurchaseOrderDetail <br/> 采购订单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface PurchaseOrderDetailDao extends
    QueryDao<PurchaseOrderDetail, PurchaseOrderDetailQuery> {

  List<PurchaseOrderDetail> listDetailPrint(PurchaseOrderDetailQuery q);
}