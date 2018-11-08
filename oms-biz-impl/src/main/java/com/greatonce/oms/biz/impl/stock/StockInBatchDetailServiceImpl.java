package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockInBatchDetailService;
import com.greatonce.oms.biz.stock.StockInBatchService;
import com.greatonce.oms.dao.stock.StockInBatchDetailDao;
import com.greatonce.oms.domain.stock.StockInBatch;
import com.greatonce.oms.domain.stock.StockInBatchDetail;
import com.greatonce.oms.query.stock.StockInBatchDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 入库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-10-16
 */
@Service
public class StockInBatchDetailServiceImpl extends
    AbstractDetailService<StockInBatch, StockInBatchDetail, StockInBatchDetailQuery> implements
    StockInBatchDetailService {

  @Resource
  private IdGenerator stockInBatchDetailIdGenerator;
  @Autowired
  private StockInBatchDetailDao dao;
  @Autowired
  private StockInBatchService stockInBatchService;

  @Override
  protected BizService<StockInBatch, ? extends Query> getMainService() {
    return stockInBatchService;
  }

  @Override
  protected QueryDao<StockInBatchDetail, StockInBatchDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<StockInBatchDetail> getDetails(StockInBatch stockInBatch) {
    return stockInBatch.getDetails();
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockInBatchDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockInBatchDetail entity) {
    super.initDefaultValue(entity);
    if (entity.getInAmount() == null) {
      entity.setInAmount(0d);
    }
    if (entity.getInQuantity() == null) {
      entity.setInQuantity(0);
    }
    if (entity.getInSubstandardQuantity() == null) {
      entity.setInSubstandardQuantity(0);
    }
  }

  @Override
  public List<StockInBatchDetail> listDetails(Long stockInBatchId) {
    StockInBatchDetail eg = new StockInBatchDetail();
    eg.setStockInBatchId(stockInBatchId);
    return listExample(eg);
  }
}