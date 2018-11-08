package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.marketing.GiftStrategyStoreService;
import com.greatonce.oms.dao.marketing.GiftStrategyStoreDao;
import com.greatonce.oms.domain.marketing.GiftStrategyStore;
import com.greatonce.oms.query.marketing.GiftStrategyStoreQuery;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GiftStrategyStore <br/> 赠品策略店铺
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class GiftStrategyStoreServiceImpl extends
    AbstractService<GiftStrategyStore, GiftStrategyStoreQuery> implements GiftStrategyStoreService {

  @Autowired
  private GiftStrategyStoreDao dao;

  @Override
  protected QueryDao<GiftStrategyStore, GiftStrategyStoreQuery> getDAO() {
    return this.dao;
  }


  @Override
  public int batchCreate(Collection<? extends GiftStrategyStore> collection) {
    collection.forEach(x -> initDefaultValue(x));
    return getTransactionTemplate().executeWithResult(() -> super.insertBatch(collection));
  }

  @Override
  public int batchModify(Collection<? extends GiftStrategyStore> collection) {
    return getTransactionTemplate().executeWithResult(() -> super.updateBatch(collection));
  }
}