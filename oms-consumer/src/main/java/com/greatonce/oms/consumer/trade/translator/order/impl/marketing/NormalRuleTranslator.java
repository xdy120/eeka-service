package com.greatonce.oms.consumer.trade.translator.order.impl.marketing;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.MathUtil;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyGiftWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyRuleWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyDoublyType;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyGiftType;
import com.greatonce.oms.domain.enums.marketing.PresellType;
import com.greatonce.oms.domain.enums.marketing.ProductRange;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.marketing.GiftStrategyRuleSetting;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * NormalGiftStrategyTranslator
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author 普通活动（买送、满送）
 * @version 2018/3/13
 */
public abstract class NormalRuleTranslator extends RuleTranslator {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  @Autowired
  private ExpressService expressService;

  /**
   * 计算订单是否满足活动条件，此处处理普通活动条件
   */
  @Override
  public boolean match(OrderTranslatableContext context, GiftStrategyWrapper strategyWrapper,
      GiftStrategyRuleWrapper ruleWrapper) {
    if (!super.match(context, strategyWrapper, ruleWrapper)) {
      return false;
    }
    GiftStrategyRuleSetting setting = ruleWrapper.getSetting();
    SalesOrder marketingCheckOrder = context.getMarketingCheckOrder();
    if (setting.isCod() != null || setting.isPresell() != null || setting.isPrepay() != null) {
      if (!matchOrderType(marketingCheckOrder, setting)) {
        return false;
      }
    }
    List<SalesOrderDetail> details = marketingCheckOrder.getDetails();
    if (setting.getProductRange() == ProductRange.EXCLUDE) {
      details.removeIf(x -> {
        if (Assert.isTrue(x.isCombinationDetail()) || ruleWrapper.getSkuIds()
            .contains(x.getSkuId())) {
          if (ruleWrapper.getSkuIds().contains(x.getSkuId())) {
            marketingCheckOrder.setActualAmount(
                MathUtil.minus(marketingCheckOrder.getActualAmount(), x.getActualAmount()));
          }
          marketingCheckOrder.setQuantity(marketingCheckOrder.getQuantity() - x.getQuantity());
          LOGGER.info(context.getSerialNo(), context.getStore(),
              context.getMallSalesOrder().getTradeId(), "订单中的{},{}不参加活动{}",
              x.getSkuCode(), x.getSkuName(), strategyWrapper.getGiftStrategyCode());
          return true;
        } else {
          return false;
        }
      });
      if (details.size() < 1) {
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "订单中的明细全部不参加活动{}",
            strategyWrapper.getGiftStrategyCode());
        return false;
      }
    } else if (setting.getProductRange() == ProductRange.INCLUDE) {
      details.removeIf(x -> {
        //是否没有活动商品和购买数量都匹配的明细
        boolean match = ruleWrapper.getSkus().stream().noneMatch(
            z -> z.getSkuId().equals(x.getSkuId()) && x.getQuantity() >= z.getQuantity());
        if (Assert.isTrue(x.isCombinationDetail()) || match) {
          if (match) {
            marketingCheckOrder.setActualAmount(
                MathUtil.minus(marketingCheckOrder.getActualAmount(), x.getActualAmount()));
          }
          marketingCheckOrder.setQuantity(marketingCheckOrder.getQuantity() - x.getQuantity());
          return true;
        } else {
          return false;
        }
      });
      if (details.size() < 1) {
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "订单中没有包含活动商品，不参加活动{}",
            strategyWrapper.getGiftStrategyCode());
        return false;
      }
    }
    return true;
  }

  /**
   * 判断订单类型是否满足规则.
   */
  private boolean matchOrderType(SalesOrder salesOrder, GiftStrategyRuleSetting setting) {
    //到付订单赠品规则
    if (setting.isCod() != null) {
      if (setting.isCod()) {
        if (!Assert.isTrue(salesOrder.getSub().isCod())) {
          return false;
        }
      } else {
        if (Assert.isTrue(salesOrder.getSub().isCod())) {
          return false;
        }
      }
    }
    //预售订单赠品规则
    if (setting.isPresell() != null) {
      if (setting.isPresell()) {
        if (salesOrder.getSub().getPresellType() == PresellType.NONE) {
          return false;
        }
      } else {
        if (salesOrder.getSub().getPresellType() != PresellType.NONE) {
          return false;
        }
      }
    }
    //预付订单赠品规则
    if (setting.isPrepay() != null) {
      if (setting.isPrepay()) {
        if (!Assert.isTrue(salesOrder.getSub().isPrepay())) {
          return false;
        }
        if (Assert.isTrue(setting.isPrepayFirst())) {
          if (salesOrder.getPayStatus() != SalesOrderPayStatus.PREPAY) {
            return false;
          }
        } else {
          if (salesOrder.getPayStatus() != SalesOrderPayStatus.PAID) {
            return false;
          }
        }
      } else {
        if (Assert.isTrue(salesOrder.getSub().isPrepay())) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * 赠送赠品
   */
  @Override
  public StrategyMatchResult giveGift(OrderTranslatableContext context,
      GiftStrategyWrapper strategyWrapper, GiftStrategyRuleWrapper ruleWrapper) {
    StrategyMatchResult result = new StrategyMatchResult(context.getSalesOrder(), strategyWrapper);
    if (ruleWrapper.getSetting().getGiftType() == GiftStrategyGiftType.EXPRESS) {
      result.setExpress(giveExpress(ruleWrapper));
    } else {
      result.setGifts(giveNormal(context, strategyWrapper, ruleWrapper));
    }
    return result;
  }

  private Express giveExpress(GiftStrategyRuleWrapper ruleWrapper) {
    if (!Assert.isNull(ruleWrapper.getSetting().getExpressId())) {
      return expressService.getByKey(ruleWrapper.getSetting().getExpressId());
    }
    return null;
  }

  /**
   * 正常赠送赠品
   */
  private Collection<StrategyRuleMatchResult> giveNormal(OrderTranslatableContext context,
      GiftStrategyWrapper strategyWrapper, GiftStrategyRuleWrapper ruleWrapper) {
    return pickGift(context, strategyWrapper, ruleWrapper);
  }

  /**
   * 挑选赠品
   */
  private Collection<StrategyRuleMatchResult> pickGift(OrderTranslatableContext context,
      GiftStrategyWrapper strategyWrapper, GiftStrategyRuleWrapper ruleWrapper) {
    return randomPickGift(context, strategyWrapper, ruleWrapper);
  }

  /**
   * 计算翻倍数
   */
  private int calcDoubly(OrderTranslatableContext context, GiftStrategyRuleWrapper ruleWrapper) {
    GiftStrategyRuleSetting setting = ruleWrapper.getSetting();
    int finalDoubly = 1;
    if (setting.isDoubly()) {
      List<SalesOrderDetail> details = context.getSalesOrder().getDetails();
      double doubly = 0;
      switch (ruleWrapper.getRuleType()) {
        case PRODUCT_QUANTITY:
          Set<Long> productIds = new HashSet<>(details.size());
          for (SalesOrderDetail detail : details) {
            if (ruleWrapper.getSkuIds().contains(detail.getSkuId())) {
              productIds.add(detail.getProductId());
            }
          }
          doubly =
              (productIds.size() - setting.getMinProductQuantity()) / setting.getDoublyNumber();
          break;
        case QUANTITY:
          int count = 0;
          for (SalesOrderDetail detail : details) {
            if (ruleWrapper.getSkuIds().contains(detail.getSkuId())) {
              count += detail.getQuantity();
            }
          }
          doubly = (count - setting.getMinQuantity()) / setting.getDoublyNumber();
          break;
        case MONEY:
          doubly = (context.getSalesOrder().getActualAmount() - setting.getMinAmount()) / setting
              .getDoublyNumber();
          break;
        default:
          break;
      }
      if (doubly > 0) {
        finalDoubly += Math.min((int) doubly, setting.getMaxDoubly());
      }
    }
    return finalDoubly;
  }


  /**
   * 随机选择赠品.
   */
  private Collection<StrategyRuleMatchResult> randomPickGift(OrderTranslatableContext context,
      GiftStrategyWrapper strategyWrapper, GiftStrategyRuleWrapper ruleWrapper) {
    List<SalesOrderDetail> details = context.getMarketingCheckOrder().getDetails();
    List<GiftStrategyGiftWrapper> collect;
    Map<Long, ProductSku> skuMap = context.getSkuMap();
    //启用匹配尺码且明细有尺码
    if (Assert.isTrue(ruleWrapper.getSetting().isMatchSize())) {
      if (details.stream().anyMatch(x -> !Assert.isEmpty(skuMap.get(x.getSkuId()).getSize()))) {
        collect = ruleWrapper.getGiftWrappers().stream().filter(
            x -> x.getSurplusQuantity() > 0 && !Assert.isEmpty(x.getSize()) && details.stream()
                .anyMatch(z -> !Assert.isEmpty(skuMap.get(z.getSkuId()).getSize()) &&
                    skuMap.get(z.getSkuId()).getSize().equals(x.getSize())))
            .collect(Collectors.toList());
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "活动：{}，{}，匹配尺码成功",
            ruleWrapper.getGiftStrategyRuleName(), strategyWrapper.getGiftStrategyCode());
      } else {
        collect = ruleWrapper.getGiftWrappers().stream()
            .filter(x -> x.getSurplusQuantity() > 0).collect(Collectors.toList());
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "活动：{}，{}，匹配尺码失败，订单明细无尺码信息",
            ruleWrapper.getGiftStrategyRuleName(), strategyWrapper.getGiftStrategyCode());
      }
    } else {
      collect = ruleWrapper.getGiftWrappers().stream()
          .filter(x -> x.getSurplusQuantity() > 0).collect(Collectors.toList());
    }
    if (collect.size() > 0) {
      //赠送赠品集合
      List<StrategyRuleMatchResult> gaveGifts = new ArrayList<>(4);
      int doubly = calcDoubly(context, ruleWrapper);
      Random random = new Random();
      GiftStrategyGiftWrapper giftWrapper;
      //赠送次数
      int needTimes = ruleWrapper.getSetting().getGiveQuantity();
      if (ruleWrapper.getSetting().getDoublyType() == GiftStrategyDoublyType.PRODUCT) {
        needTimes *= doubly;
      }
      //已送次数
      int gaveTimes = 0;
      while (true) {
        giftWrapper = collect.get(random.nextInt(doubly));
        int gaveQuantity = giftWrapper.give(giftWrapper.getQuantity());
        if (gaveQuantity > 0) {
          //赠送成功
          gaveGifts.add(new StrategyRuleMatchResult(giftWrapper, gaveQuantity));
          gaveTimes++;
        } else {
          collect.remove(giftWrapper);
          //没有赠品时不送了
          if (collect.isEmpty()) {
            strategyWrapper.setOos(true);
            LOGGER.debug(context.getSerialNo(), context.getStore(),
                context.getMallSalesOrder().getTradeId(), "营销活动：{}，{}的赠品：{}，{}已送完",
                ruleWrapper.getGiftStrategyRuleName(), strategyWrapper.getGiftStrategyCode(),
                giftWrapper.getSkuName(), giftWrapper.getSkuCode());
            break;
          }
          continue;
        }
        if (gaveTimes >= needTimes) {
          break;
        }
      }
      return gaveGifts;
    }
    return null;
  }
}
