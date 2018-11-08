package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.dao.trade.ReturnOrderDetailDao;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.query.trade.ReturnOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退换货单明细. ReturnOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ReturnOrderDetailServiceImpl extends
    AbstractDetailService<ReturnOrder, ReturnOrderDetail, ReturnOrderDetailQuery> implements
    ReturnOrderDetailService {

  @Autowired
  private ReturnOrderDetailDao dao;
  @Autowired
  private ReturnOrderService returnOrderService;
  @Resource
  private IdGenerator returnOrderDetailIdGenerator;


  @Override
  protected QueryDao<ReturnOrderDetail, ReturnOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<ReturnOrderDetail> getDetails(ReturnOrder returnOrder) {
    return returnOrder.getDetails();
  }

  @Override
  protected BizService<ReturnOrder, ? extends Query> getMainService() {
    return returnOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return returnOrderDetailIdGenerator;
  }

  @Override
  public PageList<ReturnOrderDetail> detailListPage(ReturnOrderQuery query, int page,
      int pageSize) {
    validatePageParameter(page, pageSize);
    return dao.detailListPage(query, page, pageSize);
  }

  @Override
  public List<ReturnOrderDetail> listByReturnOrderId(Long returnOrderId) {
    ReturnOrderDetail eg = new ReturnOrderDetail();
    eg.setReturnOrderId(returnOrderId);
    return listExample(eg);
  }
}