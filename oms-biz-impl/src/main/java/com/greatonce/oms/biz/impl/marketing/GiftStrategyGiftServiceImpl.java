package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.marketing.GiftStrategyGiftService;
import com.greatonce.oms.dao.marketing.GiftStrategyGiftDao;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.query.marketing.GiftStrategyGiftQuery;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * GiftStrategyGift <br/>
 * 赠品策略赠品
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class GiftStrategyGiftServiceImpl extends
    AbstractService<GiftStrategyGift, GiftStrategyGiftQuery> implements GiftStrategyGiftService {

  @Autowired
  private GiftStrategyGiftDao dao;

  @Override
  protected QueryDao<GiftStrategyGift, GiftStrategyGiftQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected void initDefaultValue(GiftStrategyGift record) {
    super.initDefaultValue(record);
    record.setSentQuantity(0);
  }

  @Override
  public int batchCreate(Collection<? extends GiftStrategyGift> collection) {
    collection.forEach(this::initDefaultValue);
    return super.insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends GiftStrategyGift> collection) {
    return super.updateBatch(collection);
  }

  @Override
  public void removeByRuleId(Long giftStrategyRuleId) {
    GiftStrategyGift eg = new GiftStrategyGift();
    eg.setGiftStrategyRuleId(giftStrategyRuleId);
    super.deleteByExample(eg);
  }
}