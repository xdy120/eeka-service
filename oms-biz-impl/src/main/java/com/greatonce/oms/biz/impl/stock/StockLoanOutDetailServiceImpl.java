package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockLoanOutDetailService;
import com.greatonce.oms.biz.stock.StockLoanOutService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.dao.stock.StockLoanOutDetailDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.domain.stock.StockLoanOutDetail;
import com.greatonce.oms.query.stock.StockLoanOutDetailQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 借出单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */

@Service
public class StockLoanOutDetailServiceImpl extends
    AbstractDetailService<StockLoanOut, StockLoanOutDetail, StockLoanOutDetailQuery> implements
    StockLoanOutDetailService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockLoanOutDetailServiceImpl.class);

  @Autowired
  private StockLoanOutDetailDao dao;
  @Autowired
  private StockLoanOutService stockLoanOutService;
  @Resource
  private IdGenerator stockLoanOutDetailIdGenerator;
  @Autowired
  private StockOccupancyService stockOccupancyService;

  @Override
  public IdGenerator getIdGenerator() {
    return this.stockLoanOutDetailIdGenerator;
  }

  @Override
  protected BizService<StockLoanOut, ? extends Query> getMainService() {
    return this.stockLoanOutService;
  }

  @Override
  protected QueryDao<StockLoanOutDetail, StockLoanOutDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<StockLoanOutDetail> getDetails(StockLoanOut stockLoanOut) {
    return stockLoanOut.getDetails();
  }

  @Override
  protected void initDefaultValue(StockLoanOutDetail detail) {
    super.initDefaultValue(detail);
    detail.setOutQuantity(0);
    detail.setNoticeQuantity(0);
    detail.setSurplusQuantity((detail.getNoticeQuantity()));
    detail.setReturnQuantity(0);
  }

  @Override
  public int batchCreate(Collection<? extends StockLoanOutDetail> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public List<StockLoanOutDetail> listAvailable(Long stockLoanOutId) {
    return dao.listAvailable(stockLoanOutId);
  }

  @Override
  public List<StockLoanOutDetail> listSaleable(Long stockLoanOutId) {
    return dao.listSaleable(stockLoanOutId);
  }

  @Override
  public List<StockLoanOutDetail> listDetails(Long stockLoanOutId) {
    StockLoanOutDetailQuery filter = new StockLoanOutDetailQuery();
    filter.setStockLoanOutId(stockLoanOutId);
    return list(filter);
  }
}