package com.greatonce.oms.dao.impl.purchase;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.purchase.PurchaseReturnOrderDetailDao;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderDetailQuery;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * PurchaseReturnOrderDetail <br/> 采购退货明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class PurchaseReturnOrderDetailDaoImpl extends
    AbstractOmsDao<PurchaseReturnOrderDetail, PurchaseReturnOrderDetailQuery> implements
    PurchaseReturnOrderDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.purchase.PurchaseReturnOrderDetailMapper";
  }

  @Override
  public List<PurchaseReturnOrderDetail> listAvailable(Long purchaseReturnOrderId) {
    return getSqlSessionDecorator().selectList(getStatement("listAvailable"), purchaseReturnOrderId);
  }

  @Override
  public List<PurchaseReturnOrderDetail> listSaleable(Long purchaseReturnOrderId) {
    return this.getSqlSessionDecorator().selectList(getStatement("listSaleable"), purchaseReturnOrderId);
  }
}