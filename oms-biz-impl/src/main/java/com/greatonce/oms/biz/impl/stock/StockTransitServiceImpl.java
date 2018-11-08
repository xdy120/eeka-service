package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.stock.StockTransitService;
import com.greatonce.oms.dao.stock.StockTransitDao;
import com.greatonce.oms.domain.stock.StockTransit;
import com.greatonce.oms.query.stock.StockTransitQuery;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 在途库存. StockTransit <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class StockTransitServiceImpl extends
    AbstractService<StockTransit, StockTransitQuery> implements StockTransitService {

  @Autowired
  private StockTransitDao dao;
  @Autowired
  private IdGenerator stockTransitIdGenerator;

  @Override
  protected QueryDao<StockTransit, StockTransitQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockTransitIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockTransit stockTransit) {
    super.initDefaultValue(stockTransit);
  }

  @Override
  public int batchCreate(Collection<? extends StockTransit> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends StockTransit> collection) {
    return updateBatch(collection);
  }

  @Override
  public void deleteByPurchaseId(Long purchaseOrderId) {
    //todo 删除采购单的在途
  }
}