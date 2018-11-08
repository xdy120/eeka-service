package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.stock.StockOutBatchDetailService;
import com.greatonce.oms.biz.stock.StockOutBatchService;
import com.greatonce.oms.dao.stock.StockOutBatchDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.query.stock.StockOutBatchQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StockOutBatch <br/> 出库批次表
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-14
 */

@Service
public class StockOutBatchServiceImpl extends
    AbstractService<StockOutBatch, StockOutBatchQuery> implements StockOutBatchService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK_OUT_BATCH);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockOutBatchServiceImpl.class);

  @Resource
  private IdGenerator stockOutBatchIdGenerator;
  @Autowired
  private StockOutBatchDao dao;
  @Autowired
  private StockOutBatchDetailService stockOutBatchDetailService;

  @Override
  protected QueryDao<StockOutBatch, StockOutBatchQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockOutBatchIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public int create(StockOutBatch entity) {
    initDefaultValue(entity);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        stockOutBatchDetailService.batchCreate(entity.getDetails());
        return insert(entity);
      });
      BIZ_LOGGER.log(entity.getStockOutBatchId(), BizLogger.ADD);
      return count;
    } catch (Exception e) {
      LOGGER.error("新增出库批次失败, {}", JsonUtil.toJson(entity));
      LOGGER.error("新增出库批次失败: {}", e);
      throw new OmsException("新增出库批次失败");
    }
  }

  @Override
  protected void initDefaultValue(StockOutBatch entity) {
    super.initDefaultValue(entity);
    entity.setPostStatus(PostStatus.UN_POST);
    if (!Assert.isEmpty(entity.getDetails())) {
      for (StockOutBatchDetail detail : entity.getDetails()) {
        detail.setStockOutBatchId(entity.getStockOutBatchId());
      }
    }
  }
}