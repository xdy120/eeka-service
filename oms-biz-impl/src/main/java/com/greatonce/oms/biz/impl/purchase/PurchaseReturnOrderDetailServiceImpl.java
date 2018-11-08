package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderService;
import com.greatonce.oms.dao.purchase.PurchaseReturnOrderDetailDao;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderDetailQuery;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采购退货明细. PurchaseReturnOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class PurchaseReturnOrderDetailServiceImpl extends
    AbstractDetailService<PurchaseReturnOrder, PurchaseReturnOrderDetail, PurchaseReturnOrderDetailQuery> implements
    PurchaseReturnOrderDetailService {

  @Autowired
  private PurchaseReturnOrderDetailDao dao;
  @Resource
  private IdGenerator purchaseReturnOrderDetailIdGenerator;
  @Autowired
  private PurchaseReturnOrderService purchaseReturnOrderService;

  @Override
  protected QueryDao<PurchaseReturnOrderDetail, PurchaseReturnOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<PurchaseReturnOrderDetail> getDetails(PurchaseReturnOrder purchaseReturnOrder) {
    return purchaseReturnOrder.getDetails();
  }

  @Override
  protected BizService<PurchaseReturnOrder, ? extends Query> getMainService() {
    return purchaseReturnOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return purchaseReturnOrderDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(PurchaseReturnOrderDetail record) {
    super.initDefaultValue(record);
    record.setOutQuantity(0);
    record.setNoticeQuantity(0);
  }

  @Override
  public int batchCreate(Collection<? extends PurchaseReturnOrderDetail> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public List<PurchaseReturnOrderDetail> listAvailable(Long purchaseReturnOrderId) {
    return dao.listAvailable(purchaseReturnOrderId);
  }

  @Override
  public List<PurchaseReturnOrderDetail> listSaleable(Long purchaseReturnOrderId) {
    return dao.listSaleable(purchaseReturnOrderId);
  }

  @Override
  public List<PurchaseReturnOrderDetail> listDetails(Long purchaseReturnOrderId) {
    PurchaseReturnOrderDetail eg = new PurchaseReturnOrderDetail();
    eg.setPurchaseReturnOrderId(purchaseReturnOrderId);
    return listExample(eg);
  }
}