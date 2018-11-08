package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.domain.marketing.GiftStrategyStore;
import com.greatonce.oms.query.marketing.GiftStrategyQuery;
import java.util.List;

/**
 * GiftStrategy <br/>
 * 赠品策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface GiftStrategyService extends BizService<GiftStrategy, GiftStrategyQuery>,
    EnableBizService<GiftStrategy> {

  void addProducts(GiftStrategy strategy, GiftStrategyRule rule,
      List<GiftStrategyProduct> products);

  void addGifts(GiftStrategy strategy, GiftStrategyRule rule, List<GiftStrategyGift> gifts);

  void addStores(GiftStrategy strategy, List<GiftStrategyStore> stores);

  void updateProduct(GiftStrategy strategy, GiftStrategyRule rule, GiftStrategyProduct product);

  void updateGift(GiftStrategy strategy, GiftStrategyRule rule, GiftStrategyGift gift);

  void removeProduct(GiftStrategy strategy, GiftStrategyRule rule, GiftStrategyProduct product);

  void removeGift(GiftStrategy strategy, GiftStrategyRule rule, GiftStrategyGift gift);

  void removeStore(GiftStrategy strategy, GiftStrategyStore product);

  void addRule(GiftStrategy strategy, GiftStrategyRule rule);

  void updateRule(GiftStrategy strategy, GiftStrategyRule rule);

  void removeRule(GiftStrategy strategy, GiftStrategyRule rule);

  /**
   * 获取有效活动
   */
  List<GiftStrategy> listEffectiveActivity();

  GiftStrategy getFullInfo(Long strategyId);
}