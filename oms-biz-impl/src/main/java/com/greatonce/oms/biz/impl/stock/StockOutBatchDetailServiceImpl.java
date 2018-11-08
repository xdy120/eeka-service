package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockOutBatchDetailService;
import com.greatonce.oms.biz.stock.StockOutBatchService;
import com.greatonce.oms.dao.stock.StockOutBatchDetailDao;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.query.stock.StockOutBatchDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 出库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-10-16
 */
@Service
public class StockOutBatchDetailServiceImpl extends
    AbstractDetailService<StockOutBatch, StockOutBatchDetail, StockOutBatchDetailQuery> implements
    StockOutBatchDetailService {

  @Resource
  private IdGenerator stockOutBatchDetailIdGenerator;
  @Autowired
  private StockOutBatchDetailDao dao;
  @Autowired
  private StockOutBatchService stockOutBatchService;

  @Override
  protected QueryDao<StockOutBatchDetail, StockOutBatchDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<StockOutBatchDetail> getDetails(StockOutBatch stockOutBatch) {
    return stockOutBatch.getDetails();
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockOutBatchDetailIdGenerator;
  }

  @Override
  protected BizService<StockOutBatch, ? extends Query> getMainService() {
    return stockOutBatchService;
  }

  @Override
  protected void initDefaultValue(StockOutBatchDetail entity) {
    super.initDefaultValue(entity);
    if (entity.getOutQuantity() == null) {
      entity.setOutQuantity(0);
    }
    if (entity.getOutAmount() == null) {
      entity.setOutAmount(0D);
    }
  }

  @Override
  public List<StockOutBatchDetail> listDetails(Long stockOutBatchId) {
    StockOutBatchDetail eg = new StockOutBatchDetail();
    eg.setStockOutBatchId(stockOutBatchId);
    return listExample(eg);
  }
}