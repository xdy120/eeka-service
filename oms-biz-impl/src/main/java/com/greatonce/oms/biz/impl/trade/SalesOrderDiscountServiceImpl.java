package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.trade.SalesOrderDiscountService;
import com.greatonce.oms.dao.trade.SalesOrderDiscountDao;
import com.greatonce.oms.domain.trade.SalesOrderDiscount;
import com.greatonce.oms.query.trade.SalesOrderDiscountQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 销售订单优惠明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class SalesOrderDiscountServiceImpl extends
    AbstractService<SalesOrderDiscount, SalesOrderDiscountQuery> implements
    SalesOrderDiscountService {

  @Autowired
  private SalesOrderDiscountDao dao;
  @Resource
  private IdGenerator salesOrderDiscountIdGenerator;

  @Override
  protected QueryDao<SalesOrderDiscount, SalesOrderDiscountQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return salesOrderDiscountIdGenerator;
  }

  @Override
  public List<SalesOrderDiscount> listBySalesOrderId(Long salesOrderId) {
    SalesOrderDiscount eg = new SalesOrderDiscount();
    eg.setSalesOrderId(salesOrderId);
    return listExample(eg);
  }

  @Override
  public int batchCreate(Collection<? extends SalesOrderDiscount> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends SalesOrderDiscount> collection) {
    return updateBatch(collection);
  }
}