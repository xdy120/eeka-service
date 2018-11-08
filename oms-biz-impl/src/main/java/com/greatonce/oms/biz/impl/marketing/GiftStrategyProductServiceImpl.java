package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.marketing.GiftStrategyProductService;
import com.greatonce.oms.dao.marketing.GiftStrategyProductDao;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.query.marketing.GiftStrategyProductQuery;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GiftStrategyProduct <br/>
 * 赠品策略活动商品
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class GiftStrategyProductServiceImpl extends
    AbstractService<GiftStrategyProduct, GiftStrategyProductQuery> implements
    GiftStrategyProductService {

  @Autowired
  private GiftStrategyProductDao dao;

  @Override
  protected QueryDao<GiftStrategyProduct, GiftStrategyProductQuery> getDAO() {
    return this.dao;
  }


  @Override
  public int batchCreate(Collection<? extends GiftStrategyProduct> collection) {
    collection.forEach(this::initDefaultValue);
    return super.insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends GiftStrategyProduct> collection) {
    return super.updateBatch(collection);
  }

  @Override
  public void removeByRuleId(Long giftStrategyRuleId) {
    GiftStrategyProduct eg = new GiftStrategyProduct();
    eg.setGiftStrategyRuleId(giftStrategyRuleId);
    super.deleteByExample(eg);
  }
}