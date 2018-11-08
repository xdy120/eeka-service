package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyRuleWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * 分组策略匹配
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/13
 */
@Component
@TranslatorOrderCondition
public class GroupGiftStrategyTranslator extends GiftStrategyTranslator {

  @Autowired
  RuleTranslatorFactory ruleTranslatorFactory;

  /**
   * 修改：当组内规则有多个，则按优先级选择一个赠品，只送1个
   */
  @Override
  public StrategyMatchResult giveGift(OrderTranslatableContext context,
      GiftStrategyWrapper strategy) {
    StrategyMatchResult result = new StrategyMatchResult(context.getSalesOrder(), strategy);
    //遍历组规则
    for (GiftStrategyRuleWrapper ruleWrapper : strategy.getRuleWrappers()) {
      RuleTranslator ruleTranslator = ruleTranslatorFactory.getTranslator(ruleWrapper);
      if (ruleTranslator.match(context, strategy, ruleWrapper)) {
        StrategyMatchResult ruleResult = ruleTranslator.giveGift(context, strategy, ruleWrapper);
        if (!Assert.isEmpty(ruleResult.getGifts()) && result.getGifts() == null) {
          if (result.getGifts() == null) {
            result.setGifts(new ArrayList<>(1));
          }
          if (result.getGifts().size() == 0) {
            result.getGifts().addAll(ruleResult.getGifts());
          }
        }
        if (ruleResult.getExpress() != null) {
          ruleResult.setExpress(ruleResult.getExpress());
        }
      }
    }
    return result;
  }
}
