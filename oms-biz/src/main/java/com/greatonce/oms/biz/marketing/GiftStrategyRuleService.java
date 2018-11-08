package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.query.marketing.GiftStrategyRuleQuery;
import java.util.List;

/**
 * GiftStrategyRule <br/>
 * 赠品规则
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface GiftStrategyRuleService extends
    BatchBizService<GiftStrategyRule, GiftStrategyRuleQuery> {

  void addProducts(GiftStrategyRule rule, List<GiftStrategyProduct> products);

  void addGifts(GiftStrategyRule rule, List<GiftStrategyGift> gifts);

  void updateProduct(GiftStrategyRule rule, GiftStrategyProduct product);

  void updateGift(GiftStrategyRule rule, GiftStrategyGift gift);

  void removeProduct(GiftStrategyRule rule, GiftStrategyProduct product);

  void removeGift(GiftStrategyRule rule, GiftStrategyGift gift);

  List<GiftStrategyRule> listFullInfo(Long strategyId);
}