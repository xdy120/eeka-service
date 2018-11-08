package com.greatonce.oms.dao.impl.purchase;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.purchase.PurchaseOrderDetailDao;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseOrderDetailQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * PurchaseOrderDetail <br/> 采购订单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class PurchaseOrderDetailDaoImpl extends
    AbstractOmsDao<PurchaseOrderDetail, PurchaseOrderDetailQuery> implements
    PurchaseOrderDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.purchase.PurchaseOrderDetailMapper";
  }

  @Override
  public List<PurchaseOrderDetail> listDetailPrint(PurchaseOrderDetailQuery q) {
    return this.getSqlSessionDecorator().selectList(this.getStatement("listDetailPrint"), q);
  }
}