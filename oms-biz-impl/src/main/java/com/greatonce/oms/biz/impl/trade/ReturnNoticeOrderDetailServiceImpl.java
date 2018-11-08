package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.dao.trade.ReturnNoticeOrderDetailDao;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.domain.trade.ReturnNoticeOrderDetail;
import com.greatonce.oms.query.trade.ReturnNoticeOrderDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退货通知单明细. ReturnNoticeOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ReturnNoticeOrderDetailServiceImpl extends
    AbstractDetailService<ReturnNoticeOrder, ReturnNoticeOrderDetail, ReturnNoticeOrderDetailQuery> implements
    ReturnNoticeOrderDetailService {

  @Autowired
  private ReturnNoticeOrderDetailDao dao;
  @Autowired
  private ReturnNoticeOrderService returnNoticeOrderService;
  @Resource
  private IdGenerator returnNoticeOrderDetailIdGenerator;

  @Override
  public IdGenerator getIdGenerator() {
    return returnNoticeOrderDetailIdGenerator;
  }

  @Override
  protected BizService<ReturnNoticeOrder, ? extends Query> getMainService() {
    return returnNoticeOrderService;
  }

  @Override
  protected QueryDao<ReturnNoticeOrderDetail, ReturnNoticeOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<ReturnNoticeOrderDetail> getDetails(ReturnNoticeOrder returnNoticeOrder) {
    return returnNoticeOrder.getDetails();
  }
}