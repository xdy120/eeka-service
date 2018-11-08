package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.trade.ReturnOrderOutDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.dao.trade.ReturnOrderOutDetailDao;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderOutDetail;
import com.greatonce.oms.query.trade.ReturnOrderOutDetailQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 退换货单换出商品. ReturnOrderOutDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ReturnOrderOutDetailServiceImpl extends
    AbstractDetailService<ReturnOrder, ReturnOrderOutDetail, ReturnOrderOutDetailQuery> implements
    ReturnOrderOutDetailService {

  @Autowired
  private ReturnOrderService returnOrderService;
  @Autowired
  private StockOccupancyService occupancyService;

  @Autowired
  private ReturnOrderOutDetailDao dao;
  @Resource
  private IdGenerator returnOrderOutDetailIdGenerator;

  @Override
  protected BizService<ReturnOrder, ? extends Query> getMainService() {
    return returnOrderService;
  }

  @Override
  protected QueryDao<ReturnOrderOutDetail, ReturnOrderOutDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<ReturnOrderOutDetail> getDetails(ReturnOrder returnOrder) {
    return returnOrder.getOutDetails();
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.returnOrderOutDetailIdGenerator;
  }

  @Override
  public int replaceOutDetail(ReturnOrderOutDetail entity) {
    return getTransactionTemplate().executeWithResult(() -> {
      occupancyService.updateSku(entity.getReturnOrderId(), entity.getReturnOrderOutDetailId(),
          StockOccupancyType.RETURN_ORDER, entity.getSkuId(), entity.getSkuCode(),
          entity.getQuantity());
      return super.modify(entity);
    });
  }

}