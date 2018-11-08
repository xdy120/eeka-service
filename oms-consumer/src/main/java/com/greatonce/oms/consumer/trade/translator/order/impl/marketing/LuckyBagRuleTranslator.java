package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;


import com.greatonce.core.util.Assert;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyGiftWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyRuleWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * createOrder date:2017/7/26
 * remark:福袋赠送
 */
@Component
@TranslatorOrderCondition
public class LuckyBagRuleTranslator extends RuleTranslator {

  private static final Logger LOGGER = LoggerFactory.getLogger(LuckyBagRuleTranslator.class);

  @Override
  public boolean match(OrderTranslatableContext context, GiftStrategyWrapper giftStrategy,
      GiftStrategyRuleWrapper ruleWrapper) {
    if (context.getSalesOrder().getDetails().stream()
        .filter(z -> !Assert.isTrue(z.isCombinationDetail()))
        .noneMatch(x -> ruleWrapper.getLuckBagMap().containsKey(x.getSkuCode()))) {
      LOGGER.info("{},{},没有购买福袋中的商品,不参加活动", context, giftStrategy.getGiftStrategyCode());
      return false;
    }
    return true;
  }

  /**
   * 赠送福袋商品
   * <p>
   * 1、查找商品明细中的福袋商品
   * 2、赠送次数=福袋商品数量*福袋赠送数量
   * 3、随机选择一个福袋明细赠送
   */
  @Override
  public StrategyMatchResult giveGift(OrderTranslatableContext context,
      GiftStrategyWrapper giftStrategy, GiftStrategyRuleWrapper rule) {
    StrategyMatchResult result = new StrategyMatchResult(context.getSalesOrder(), giftStrategy);
    //赠送赠品集合
    Map<Long, StrategyRuleMatchResult> gifts = new HashMap<>(4);
    Random random = new Random();
    StrategyRuleMatchResult gift;
    GiftStrategyGiftWrapper productWapper;
    List<GiftStrategyGiftWrapper> list;
    //遍历明细
    for (SalesOrderDetail orderDetail : context.getSalesOrder().getDetails()) {
      if (rule.getLuckBagMap().containsKey(orderDetail.getSkuCode())) {
        list = rule.getLuckBagMap().get(orderDetail.getSkuCode());
        //计划赠送数量
        int needTimes =
            rule.getSetting().getLuckBagGiveProductQuantity() * orderDetail.getQuantity();
        int times = 0;
        while (true) {
          productWapper = list.get(random.nextInt(list.size()));
          int giveQuantity = productWapper.give(productWapper.getQuantity());
          if (giveQuantity == 0) {
            //没送到，计算总数还有没有
            if (list.stream().anyMatch(x -> x.getSurplusQuantity() > 0)) {
              continue;
            } else {
              LOGGER.debug("{},{}，福袋：{}商品已经送完。", context, giftStrategy.getGiftStrategyCode(),
                  orderDetail.getSkuCode());
              break;
            }
          } else {
            gift = gifts.get(productWapper.getGiftStrategyGiftId());
            if (gift == null) {
              gift = new StrategyRuleMatchResult(productWapper, giveQuantity);
              gifts.put(productWapper.getGiftStrategyGiftId(), gift);
            } else {
              //计划赠送数量 > 福袋商品数量 时可送重复商品，否则continue
              if (needTimes > list.size()) {
                gift.setQuantity(gift.getQuantity() + giveQuantity);
              } else {
                continue;
              }
            }
            times++;
          }
          if (times >= needTimes) {
            break;
          }
        }
      }
    }
    result.setGifts(gifts.values());
    return result;
  }
}
