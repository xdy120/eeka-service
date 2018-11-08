package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.trade.SalesOrderPaymentService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.dao.trade.SalesOrderPaymentDao;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderPayment;
import com.greatonce.oms.query.trade.SalesOrderPaymentQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 销售订单支付明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-01-02
 */
@Service
public class SalesOrderPaymentServiceImpl extends
    AbstractDetailService<SalesOrder, SalesOrderPayment, SalesOrderPaymentQuery> implements
    SalesOrderPaymentService {

  @Autowired
  private SalesOrderPaymentDao dao;
  @Autowired
  private SalesOrderService salesOrderService;
  @Resource
  private IdGenerator salesOrderPaymentIdGenerator;

  @Override
  protected BizService<SalesOrder, ? extends Query> getMainService() {
    return salesOrderService;
  }

  @Override
  protected QueryDao<SalesOrderPayment, SalesOrderPaymentQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<SalesOrderPayment> getDetails(SalesOrder salesOrder) {
    return salesOrder.getPayments();
  }

  @Override
  public IdGenerator getIdGenerator() {
    return salesOrderPaymentIdGenerator;
  }

  @Override
  public List<SalesOrderPayment> listBySalesOrderId(Long salesOrderId) {
    SalesOrderPayment eg = new SalesOrderPayment();
    eg.setSalesOrderId(salesOrderId);
    return listExample(eg);
  }
}