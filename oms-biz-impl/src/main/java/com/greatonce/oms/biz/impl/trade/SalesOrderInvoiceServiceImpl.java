package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.trade.SalesOrderInvoiceService;
import com.greatonce.oms.dao.trade.SalesOrderInvoiceDao;
import com.greatonce.oms.domain.enums.trade.InvoiceType;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import com.greatonce.oms.query.trade.SalesOrderInvoiceQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 销售订单发票明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class SalesOrderInvoiceServiceImpl extends
    AbstractService<SalesOrderInvoice, SalesOrderInvoiceQuery> implements
    SalesOrderInvoiceService {

  @Autowired
  private SalesOrderInvoiceDao dao;
  @Resource
  private IdGenerator salesOrderInvoiceIdGenerator;

  @Override
  protected QueryDao<SalesOrderInvoice, SalesOrderInvoiceQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return salesOrderInvoiceIdGenerator;
  }

  @Override
  public List<SalesOrderInvoice> listBySalesOrderId(Long salesOrderId) {
    SalesOrderInvoice eg = new SalesOrderInvoice();
    eg.setSalesOrderId(salesOrderId);
    return listExample(eg);
  }

  @Override
  public int batchCreate(Collection<? extends SalesOrderInvoice> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends SalesOrderInvoice> collection) {
    return updateBatch(collection);
  }

  @Override
  public int modify(SalesOrderInvoice entity) {
    if (entity.getInvoiceType() == InvoiceType.NORMAL) {
      entity.setGmfBankName("");
      entity.setGmfMobile("");
      entity.setGmfAddress("");
      entity.setGmfBankNo("");
    }
    return update(entity);
  }
}