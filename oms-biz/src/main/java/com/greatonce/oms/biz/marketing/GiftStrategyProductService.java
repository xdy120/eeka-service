package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.query.marketing.GiftStrategyProductQuery;

/**
 * GiftStrategyProduct <br/>
 * 赠品策略活动商品
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface GiftStrategyProductService extends
    BatchBizService<GiftStrategyProduct, GiftStrategyProductQuery> {

  void removeByRuleId(Long giftStrategyRuleId);
}