package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.stock.StockInBatchDetailService;
import com.greatonce.oms.biz.stock.StockInBatchService;
import com.greatonce.oms.dao.stock.StockInBatchDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.stock.StockInBatch;
import com.greatonce.oms.domain.stock.StockInBatchDetail;
import com.greatonce.oms.query.stock.StockInBatchQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 入库批次表.
 * StockInBatch <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-14
 */

@Service
public class StockInBatchServiceImpl extends
    AbstractService<StockInBatch, StockInBatchQuery> implements StockInBatchService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK_IN_BATCH);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockInBatchServiceImpl.class);

  @Resource
  private IdGenerator stockInBatchIdGenerator;
  @Autowired
  private StockInBatchDao dao;
  @Autowired
  private StockInBatchDetailService stockInBatchDetailService;

  @Override
  protected QueryDao<StockInBatch, StockInBatchQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockInBatchIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(StockInBatch entity) {
    super.initDefaultValue(entity);
    if (entity.getPostStatus() == null) {
      entity.setPostStatus(PostStatus.UN_POST);
    }
    if (!Assert.isEmpty(entity.getDetails())) {
      for (StockInBatchDetail detail : entity.getDetails()) {
        detail.setStockInBatchId(entity.getStockInBatchId());
      }
    }
  }

  @Override
  public int create(StockInBatch entity) {
    initDefaultValue(entity);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        stockInBatchDetailService.batchCreate(entity.getDetails());
        return insert(entity);
      });
      BIZ_LOGGER.log(entity.getStockInBatchId(), BizLogger.ADD);
      return count;
    } catch (Exception e) {
      LOGGER.error("新增入库批次失败, {}", JsonUtil.toJson(entity));
      LOGGER.error("新增入库批次失败: {}", e);
      throw new OmsException("新增入库批次失败");
    }
  }
}