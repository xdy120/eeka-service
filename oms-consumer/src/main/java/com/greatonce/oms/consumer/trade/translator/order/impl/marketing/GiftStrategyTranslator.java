package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import com.greatonce.oms.domain.enums.marketing.TimeType;
import com.greatonce.oms.domain.trade.SalesOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 赠品活动转化器
 *
 * @author ginta
 */
public abstract class GiftStrategyTranslator {

  private static final Logger LOGGER = LoggerFactory.getLogger(GiftStrategyTranslator.class);

  /**
   * 计算订单是否满足活动，此处处理通用条件
   */
  public boolean match(OrderTranslatableContext context, GiftStrategyWrapper strategy) {
    try {
      SalesOrder salesOrder = context.getSalesOrder();
      if (!strategy.getStoreIds().contains(context.getStore().getStoreId())) {
        throw new Exception("店铺不参加此活动");
      }
      if (strategy.getTimeType() == TimeType.PAID_TIME) {
        if (salesOrder.getSub().getMallPaidTime().isBefore(strategy.getBeginTime())) {
          throw new Exception("支付时间在活动开始时间之前");
        }
        if (salesOrder.getSub().getMallPaidTime().isAfter(strategy.getEndTime())) {
          throw new Exception("支付时间在活动开始时间之后");
        }
      } else {
        if (salesOrder.getSub().getMallCreatedTime().isBefore(strategy.getBeginTime())) {
          throw new Exception("下单时间在活动开始时间之前");
        }
        if (salesOrder.getSub().getMallCreatedTime().isAfter(strategy.getEndTime())) {
          throw new Exception("下单时间在活动开始时间之后");
        }
      }
    } catch (Exception e) {
      LOGGER.debug("{},{}，{}，不参加活动", context, strategy.getGiftStrategyCode(), e.getMessage());
      return false;
    }
    return true;
  }

  /**
   * 赠送赠品
   */
  public abstract StrategyMatchResult giveGift(OrderTranslatableContext context,
      GiftStrategyWrapper strategy);
}
