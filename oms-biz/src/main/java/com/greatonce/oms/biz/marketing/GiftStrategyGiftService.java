package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.query.marketing.GiftStrategyGiftQuery;

/**
 * GiftStrategyGift <br/>
 * 赠品策略赠品
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */
public interface GiftStrategyGiftService extends
    BatchBizService<GiftStrategyGift,GiftStrategyGiftQuery> {

  void removeByRuleId(Long giftStrategyRuleId);
}