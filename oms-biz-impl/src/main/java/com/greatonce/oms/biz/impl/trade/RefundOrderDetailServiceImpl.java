package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.trade.RefundOrderDetailService;
import com.greatonce.oms.biz.trade.RefundOrderService;
import com.greatonce.oms.dao.trade.RefundOrderDetailDao;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.domain.trade.RefundOrderDetail;
import com.greatonce.oms.query.trade.RefundOrderDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退款单明细.
 * RefundOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class RefundOrderDetailServiceImpl extends
    AbstractDetailService<RefundOrder, RefundOrderDetail, RefundOrderDetailQuery> implements
    RefundOrderDetailService {

  @Autowired
  RefundOrderDetailDao dao;
  @Autowired
  RefundOrderService refundOrderService;
  @Resource
  IdGenerator refundOrderDetailIdGenerator;

  @Override
  protected QueryDao<RefundOrderDetail, RefundOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<RefundOrderDetail> getDetails(RefundOrder refundOrder) {
    return refundOrder.getDetails();
  }

  @Override
  protected BizService<RefundOrder, ? extends Query> getMainService() {
    return refundOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return refundOrderDetailIdGenerator;
  }
}
