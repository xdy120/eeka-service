package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderService;
import com.greatonce.oms.dao.purchase.PurchaseNoticeOrderDetailDao;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrderDetail;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采购入库通知单明细. PurchaseNoticeOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class PurchaseNoticeOrderDetailServiceImpl extends
    AbstractDetailService<PurchaseNoticeOrder, PurchaseNoticeOrderDetail, PurchaseNoticeOrderDetailQuery> implements
    PurchaseNoticeOrderDetailService {

  @Autowired
  PurchaseNoticeOrderDetailDao dao;
  @Resource
  IdGenerator purchaseNoticeOrderDetailIdGenerator;
  @Autowired
  PurchaseNoticeOrderService purchaseNoticeOrderService;

  @Override
  protected QueryDao<PurchaseNoticeOrderDetail, PurchaseNoticeOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<PurchaseNoticeOrderDetail> getDetails(PurchaseNoticeOrder purchaseNoticeOrder) {
    return purchaseNoticeOrder.getDetails();
  }

  @Override
  protected BizService<PurchaseNoticeOrder, ? extends Query> getMainService() {
    return purchaseNoticeOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return purchaseNoticeOrderDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(PurchaseNoticeOrderDetail detail) {
    super.initDefaultValue(detail);
    detail.setInQuantity(0);
    detail.setInSubstandardQuantity(0);
  }

  @Override
  public List<PurchaseNoticeOrderDetail> listDetails(Long purchaseNoticeOrderId) {
    PurchaseNoticeOrderDetail eg = new PurchaseNoticeOrderDetail();
    eg.setPurchaseNoticeOrderId(purchaseNoticeOrderId);
    return listExample(eg);
  }
}