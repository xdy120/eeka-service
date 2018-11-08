package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockInOrderDetailService;
import com.greatonce.oms.biz.stock.StockInOrderService;
import com.greatonce.oms.dao.stock.StockInOrderDetailDao;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.domain.stock.StockInOrderDetail;
import com.greatonce.oms.query.stock.StockInOrderDetailQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 入库单明细. StockInOrderDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class StockInOrderDetailServiceImpl extends
    AbstractDetailService<StockInOrder, StockInOrderDetail, StockInOrderDetailQuery> implements
    StockInOrderDetailService {

  @Autowired
  private StockInOrderDetailDao dao;
  @Resource
  private IdGenerator stockInDetailIdGenerator;
  @Autowired
  private StockInOrderService stockInOrderService;

  @Override
  protected QueryDao<StockInOrderDetail, StockInOrderDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<StockInOrderDetail> getDetails(StockInOrder stockInOrder) {
    return stockInOrder.getDetails();
  }

  @Override
  protected BizService<StockInOrder, ? extends Query> getMainService() {
    return stockInOrderService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockInDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockInOrderDetail detail) {
    super.initDefaultValue(detail);
    if (detail.getInSubstandardQuantity() == null) {
      detail.setInSubstandardQuantity(0);
    }
  }

  @Override
  public List<StockInOrderDetail> listDetails(Long stockInOrderId) {
    StockInOrderDetail eg = new StockInOrderDetail();
    eg.setStockInOrderId(stockInOrderId);
    return listExample(eg);
  }
}