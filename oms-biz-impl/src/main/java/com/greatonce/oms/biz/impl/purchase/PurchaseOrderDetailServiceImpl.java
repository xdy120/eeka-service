package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.purchase.PurchaseOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseOrderService;
import com.greatonce.oms.dao.purchase.PurchaseOrderDetailDao;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseOrderDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采购订单明细 明细可重复. PurchaseOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class PurchaseOrderDetailServiceImpl extends
    AbstractDetailService<PurchaseOrder, PurchaseOrderDetail, PurchaseOrderDetailQuery> implements
    PurchaseOrderDetailService {

  @Autowired
  private PurchaseOrderDetailDao dao;
  @Resource
  private IdGenerator purchaseOrderDetailIdGenerator;
  @Autowired
  private PurchaseOrderService purchaseOrderService;

  @Override
  protected QueryDao<PurchaseOrderDetail, PurchaseOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<PurchaseOrderDetail> getDetails(PurchaseOrder purchaseOrder) {
    return purchaseOrder.getDetails();
  }

  @Override
  protected BizService<PurchaseOrder, ? extends Query> getMainService() {
    return purchaseOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return purchaseOrderDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(PurchaseOrderDetail detail) {
    super.initDefaultValue(detail);
    detail.setPurchaseOrderDetailId(purchaseOrderDetailIdGenerator.next());
    detail.setNoticeQuantity(0);
    detail.setInQuantity(0);
    detail.setInSubstandardQuantity(0);
  }

  @Override
  public List<PurchaseOrderDetail> listDetailPrint(PurchaseOrderDetailQuery query) {
    return dao.listDetailPrint(query);
  }

  @Override
  public List<PurchaseOrderDetail> listDetails(Long purchaseOrderId) {
    PurchaseOrderDetail eg = new PurchaseOrderDetail();
    eg.setPurchaseOrderId(purchaseOrderId);
    return listExample(eg);
  }
}