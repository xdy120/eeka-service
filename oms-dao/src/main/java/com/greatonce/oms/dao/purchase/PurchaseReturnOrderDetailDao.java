package com.greatonce.oms.dao.purchase;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderDetailQuery;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;
import java.util.List;

/**
 * PurchaseReturnOrderDetail <br/> 采购退货明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface PurchaseReturnOrderDetailDao extends
    QueryDao<PurchaseReturnOrderDetail, PurchaseReturnOrderDetailQuery> {

  List<PurchaseReturnOrderDetail> listAvailable(Long purchaseReturnOrderId);

  List<PurchaseReturnOrderDetail> listSaleable(Long purchaseReturnOrderId);
}