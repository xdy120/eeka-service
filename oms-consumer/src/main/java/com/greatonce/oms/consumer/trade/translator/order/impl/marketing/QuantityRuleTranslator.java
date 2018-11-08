package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyRuleWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import com.greatonce.oms.domain.enums.marketing.ProductRange;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.domain.marketing.GiftStrategyRuleSetting;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 数量赠送规则解析
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
@Component
@TranslatorOrderCondition
public class QuantityRuleTranslator extends NormalRuleTranslator {

  private static final Logger LOGGER = LoggerFactory.getLogger(QuantityRuleTranslator.class);

  @Override
  public boolean match(OrderTranslatableContext context, GiftStrategyWrapper strategyWrapper,
      GiftStrategyRuleWrapper ruleWrapper) {
    if (!super.match(context, strategyWrapper, ruleWrapper)) {
      return false;
    }

    GiftStrategyRuleSetting setting = ruleWrapper.getSetting();
    SalesOrder marketingCheckOrder = context.getMarketingCheckOrder();
    if (marketingCheckOrder.getQuantity() < setting.getMinQuantity()) {
      LOGGER.info("{},{},购买件数不足，不参加活动，购买件数：{},最低件数：{}", context,
          strategyWrapper.getGiftStrategyCode(),
          marketingCheckOrder.getQuantity(), setting.getMinQuantity());
      return false;
    }
    if (setting.getProductRange() == ProductRange.ALL) {
      if (marketingCheckOrder.getDetails().size() < setting.getMinProductQuantity()) {
        LOGGER.info("{},{},购买款数不足，不参加活动，购买款数：{}，最低款数：{}", context,
            strategyWrapper.getGiftStrategyCode(), marketingCheckOrder.getDetails().size(),
            setting.getMinProductQuantity());
        return false;
      }
    }
    //购买指定款式送赠品校验
    if (setting.getProductRange() == ProductRange.INCLUDE) {
      //最低款数为1件
      if (setting.getMinProductQuantity() == 1) {
        if (!isGiftContainsSku(marketingCheckOrder, ruleWrapper.getSkus())) {
          LOGGER.info("{},{},没有购买指定款式，不参加活动", context, strategyWrapper.getGiftStrategyCode());
          return false;
        }
      }
      //最低款数大于1
      else if (setting.getMinProductQuantity() > 1) {
        //最低款数不能超过参与活动的商品款数
        if (setting.getMinProductQuantity() > ruleWrapper.getSkus().size()) {
          LOGGER.error("{},{}，策略异常！最低款数超过参加活动商品款数！", context,
              strategyWrapper.getGiftStrategyCode());
          return false;
        }
        if (!isMatchMinQuantity(setting.getMinProductQuantity(), marketingCheckOrder,
            ruleWrapper.getSkus())) {
          LOGGER.info("{},{},订单购买款式数量不够，不参加活动", context, strategyWrapper.getGiftStrategyCode());
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 判断是否达到购买活动商品款式数量的条件。
   */
  private boolean isMatchMinQuantity(Integer minProductQuantity, SalesOrder salesOrder,
      List<GiftStrategyProduct> skus) {
    Integer matchNum = 0;
    for (SalesOrderDetail detail : salesOrder.getDetails()) {
      for (GiftStrategyProduct giftStrategyProduct : skus) {
        if (detail.getSkuCode().equals(giftStrategyProduct.getSkuCode())) {
          matchNum++;
        }
        if (matchNum.equals(minProductQuantity)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * 判断商品中是否包含策略中的商品。
   *
   * @return boolean
   */
  private boolean isGiftContainsSku(SalesOrder salesOrder, List<GiftStrategyProduct> skus) {
    return skus.stream()
        .anyMatch(x -> salesOrder.getDetails().stream().map(SalesOrderDetail::getSkuCode)
            .anyMatch(a -> a.equals(x.getSkuCode())));
  }
}
