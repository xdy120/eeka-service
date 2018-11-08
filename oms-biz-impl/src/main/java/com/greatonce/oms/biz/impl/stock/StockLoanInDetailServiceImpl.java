package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.stock.StockLoanInDetailService;
import com.greatonce.oms.biz.stock.StockLoanInService;
import com.greatonce.oms.dao.stock.StockLoanInDetailDao;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.stock.StockLoanInDetail;
import com.greatonce.oms.query.stock.StockLoanInDetailQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 还入单明细 StockLoanInDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */

@Service
public class StockLoanInDetailServiceImpl extends
    AbstractDetailService<StockLoanIn, StockLoanInDetail, StockLoanInDetailQuery> implements
    StockLoanInDetailService {

  @Autowired
  private StockLoanInDetailDao dao;
  @Autowired
  private StockLoanInService stockLoanInService;
  @Resource
  private IdGenerator StockLoanInDetailIdGenerator;

  @Override
  public IdGenerator getIdGenerator() {
    return this.StockLoanInDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockLoanInDetail detail) {
    super.initDefaultValue(detail);
    detail.setInQuantity(0);
    detail.setInSubstandardQuantity(0);
  }

  @Override
  protected BizService<StockLoanIn, ? extends Query> getMainService() {
    return this.stockLoanInService;
  }

  @Override
  protected QueryDao<StockLoanInDetail, StockLoanInDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<StockLoanInDetail> getDetails(StockLoanIn stockLoanIn) {
    return stockLoanIn.getDetails();
  }

  @Override
  public int batchCreate(Collection<? extends StockLoanInDetail> list) {
    list.forEach(e -> {
      initDefaultValue(e);
    });
    return insertBatch(list);
  }

  @Override
  public List<StockLoanInDetail> listDetails(Long stockLoanInId) {
    StockLoanInDetailQuery StockLoanInDetailFilter = new StockLoanInDetailQuery();
    StockLoanInDetailFilter.setStockLoanInId(stockLoanInId);
    return list(StockLoanInDetailFilter);
  }
}