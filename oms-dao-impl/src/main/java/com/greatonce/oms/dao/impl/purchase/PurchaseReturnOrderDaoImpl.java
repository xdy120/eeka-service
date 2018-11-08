package com.greatonce.oms.dao.impl.purchase;


import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.purchase.PurchaseReturnOrderDao;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * PurchaseReturnOrder <br/>
 * 采购退货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class PurchaseReturnOrderDaoImpl extends
    AbstractOmsDao<PurchaseReturnOrder, PurchaseReturnOrderQuery> implements
    PurchaseReturnOrderDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.purchase.PurchaseReturnOrderMapper";
  }
}