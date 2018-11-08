package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.trade.SalesOrderMarketingService;
import com.greatonce.oms.dao.trade.SalesOrderMarketingDao;
import com.greatonce.oms.domain.trade.SalesOrderMarketing;
import com.greatonce.oms.query.trade.SalesOrderMarketingQuery;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 销售订单活动表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class SalesOrderMarketingServiceImpl extends
    AbstractService<SalesOrderMarketing, SalesOrderMarketingQuery> implements
    SalesOrderMarketingService {

  @Autowired
  private SalesOrderMarketingDao dao;
  @Resource
  private IdGenerator salesOrderMarketingIdGenerator;

  @Override
  protected QueryDao<SalesOrderMarketing, SalesOrderMarketingQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return salesOrderMarketingIdGenerator;
  }

  @Override
  public List<SalesOrderMarketing> listBySalesOrderId(Long salesOrderId) {
    SalesOrderMarketing eg = new SalesOrderMarketing();
    eg.setSalesOrderId(salesOrderId);
    return listExample(eg);
  }

  @Override
  public int batchCreate(Collection<? extends SalesOrderMarketing> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends SalesOrderMarketing> collection) {
    return updateBatch(collection);
  }
}