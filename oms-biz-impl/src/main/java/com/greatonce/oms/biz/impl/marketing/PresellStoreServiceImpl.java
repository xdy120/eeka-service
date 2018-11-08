package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.marketing.PresellStoreService;
import com.greatonce.oms.dao.marketing.PresellStoreDao;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.marketing.PresellStore;
import com.greatonce.oms.query.marketing.PresellStoreQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 预售店铺.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class PresellStoreServiceImpl extends
    AbstractService<PresellStore, PresellStoreQuery> implements PresellStoreService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.MARKETING_PRESELL);
  @Autowired
  private PresellStoreDao dao;
  @Resource
  private IdGenerator presellStoreIdGenerator;

  @Override
  protected QueryDao<PresellStore, PresellStoreQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return presellStoreIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public List<PresellStore> listStores(Long presellId) {
    PresellStore eg = new PresellStore();
    eg.setPresellId(presellId);
    return listExample(eg);
  }

  @Override
  public int batchCreate(Collection<? extends PresellStore> collection) {
    collection.forEach(x -> initDefaultValue(x));
    return getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
  }

  @Override
  public int batchModify(Collection<? extends PresellStore> collection) {
    return getTransactionTemplate().executeWithResult(() -> updateBatch(collection));
  }
}