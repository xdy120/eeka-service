package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.marketing.GiftStrategyGiftService;
import com.greatonce.oms.biz.marketing.GiftStrategyService;
import com.greatonce.oms.biz.product.ProductCombinationService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderMarketingService;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.GiftStrategyTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.GroupGiftStrategyTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.NormalGiftStrategyTranslator;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.StrategyMatchResult;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.StrategyRuleMatchResult;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyGiftWrapper;
import com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper.GiftStrategyWrapper;
import com.greatonce.oms.domain.enums.MarketingType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyRuleType;
import com.greatonce.oms.domain.enums.marketing.TimeType;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderMarketing;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lcc
 */
@Component("orderMarketingTranslator")
@TranslatorOrderCondition
public class MarketingTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.GIFT_STRATEGY);
  private static final String MARKETING_GIFT = "MARKETING_GIFT";
  private final Map<Long, GiftStrategyWrapper> strategyMap = new ConcurrentHashMap<>();
  /**
   * 福袋
   */
  private final Map<Long, GiftStrategyWrapper> luckbagStrategyMap = new ConcurrentHashMap<>();

  @Autowired
  private GroupGiftStrategyTranslator groupGiftStrategyTranslator;
  @Autowired
  private NormalGiftStrategyTranslator normalGiftStrategyTranslator;
  @Autowired
  private GiftStrategyService giftStrategyService;
  @Autowired
  private GiftStrategyGiftService giftStrategyGiftService;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private ProductCombinationService productCombinationService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private SalesOrderMarketingService salesOrderMarketingService;

  @Override
  public void translate(OrderTranslatableContext context) {
    if (context.getMode() == OrderTranslatableMode.CREATE
        || context.getMode() == OrderTranslatableMode.MODIFY) {
      if (validate(context)) {
        reloadStrategy(context);
        switch (context.getMode()) {
          case CREATE:
            parseStrategy(context);
            break;
          case MODIFY:
            if (context.isPrepayFinalPaid()) {
              parseStrategy(context);
            }
            break;
          default:
            break;
        }
      }
    }
  }

  @Override
  public void save(OrderTranslatableContext context) {
    //拿到送出赠品数量
    Collection<StrategyRuleMatchResult> gifts = context.getData(MARKETING_GIFT);
    if (!Assert.isEmpty(gifts)) {
      //用来持久化的集合
      List<GiftStrategyGift> giftList = new ArrayList<>(1);
      for (StrategyRuleMatchResult gift : gifts) {
        if (gift.getQuantity() != 0) {
          GiftStrategyGiftWrapper strategyGiftWrapper = gift.getGift();
          GiftStrategyGift sendProduct = strategyGiftWrapper.getSendProduct();
          //设置送出数
          sendProduct.setSentQuantity(
              sendProduct.getPlanQuantity() - strategyGiftWrapper.getSurplusQuantity());
          giftList.add(sendProduct);
        }
      }
      //持久化
      giftStrategyGiftService.batchModify(giftList);
      if (context.isPrepayFinalPaid()) {
        List<SalesOrderMarketing> marketings = context.getSalesOrder().getMarketings().stream()
            .filter(x -> x.getSalesOrderMarketingId() == null).collect(Collectors.toList());
        salesOrderMarketingService.batchCreate(marketings);
      }
    }
  }

  @Override
  public void rollback(OrderTranslatableContext context) {
    List<StrategyRuleMatchResult> gifts = context.getData(MARKETING_GIFT);
    if (gifts != null && gifts.size() > 0) {
      for (StrategyRuleMatchResult gift : gifts) {
        gift.getGift().withdraw(gift.getQuantity());
      }
    }
  }

  /**
   * 校验订单是否参与营销活动.
   */
  private boolean validate(OrderTranslatableContext context) {
    if (context.getSalesOrder().getSub().getSalesOrderType() != SalesOrderType.SALES) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "非销售订单不匹配活动");
      return false;
    }
    //如果订单不需发货不参与活动
    return context.isNeedDelivery();
  }

  /**
   * 加载策略
   */
  private void reloadStrategy(OrderTranslatableContext context) {
    strategyMap.clear();
    luckbagStrategyMap.clear();
    //获取存在的活动
    List<GiftStrategy> strategies = giftStrategyService.listEffectiveActivity();
    for (GiftStrategy strategy : strategies) {
      //活动是否正在进行
      if (validateStrategy(strategy, context)) {
        //获取活动详情
        strategy = giftStrategyService.getFullInfo(strategy.getGiftStrategyId());
        //判断是福袋还是普通赠送
        if (strategy.getRules().size() == 1
            && strategy.getRules().get(0).getRuleType() == GiftStrategyRuleType.LUCK_BAG) {
          if (!luckbagStrategyMap.containsKey(strategy.getGiftStrategyId())) {
            luckbagStrategyMap.put(strategy.getGiftStrategyId(), new GiftStrategyWrapper(strategy));
          }
        } else {
          if (!strategyMap.containsKey(strategy.getGiftStrategyId())) {
            strategyMap.put(strategy.getGiftStrategyId(), new GiftStrategyWrapper(strategy));
          }
        }
      }
    }
  }

  /**
   * 赠送结果解析
   */
  private void parseGiftGiveResult(Long giftStrategyId, OrderTranslatableContext context,
      StrategyMatchResult result) {
    if (result.hasGift()) {
      SalesOrder salesOrder = context.getSalesOrder();
      if (result.getExpress() != null) {
        salesOrder.setSuggestExpressId(result.getExpress().getExpressId());
        salesOrder.setSuggestExpressName(result.getExpress().getExpressName());

        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "赠送快递：{}，{}",
            result.getExpress().getExpressCode(), result.getExpress().getExpressName());
        BIZ_LOGGER.log(giftStrategyId, "送快递", "送出赠品快递：{}", result.getExpress().getExpressName());
      } else if (!Assert.isEmpty(result.getGifts())) {
        for (StrategyRuleMatchResult strategyRuleMatchResult : result.getGifts()) {
          createOrderDetail(salesOrder, result, strategyRuleMatchResult, context);

          LOGGER.info(context.getSerialNo(), context.getStore(),
              context.getMallSalesOrder().getTradeId(),
              "交易号：{}赠送赠品：第{}件，名称：{}，规格：{}，数量：{}件", context.getMallSalesOrder().getTradeId(),
              strategyRuleMatchResult.getGift().getSentQuantity() + strategyRuleMatchResult
                  .getQuantity(),
              strategyRuleMatchResult.getGift().getProductName(),
              strategyRuleMatchResult.getGift().getSkuName(),
              strategyRuleMatchResult.getQuantity());
          BIZ_LOGGER.log(giftStrategyId, "送赠品", "交易号：{}赠送赠品：第{}件，名称：{}，规格：{}，数量：{}件",
              context.getMallSalesOrder().getTradeId(),
              strategyRuleMatchResult.getGift().getSentQuantity() + strategyRuleMatchResult
                  .getQuantity(), strategyRuleMatchResult.getGift().getProductName(),
              strategyRuleMatchResult.getGift().getSkuName(),
              strategyRuleMatchResult.getQuantity());
        }
      }
    }
  }

  /**
   * 创建赠品明细
   */
  private void createOrderDetail(SalesOrder salesOrder, StrategyMatchResult result,
      StrategyRuleMatchResult strategyRuleMatchResult, OrderTranslatableContext context) {
    ProductSku sku =
        productSkuService.getEffectiveById(strategyRuleMatchResult.getGift().getSkuId());
    SalesOrderDetail orderDetail = createDetail(sku);
    orderDetail.setSalesOrderId(salesOrder.getSalesOrderId());
    orderDetail.setQuantity(strategyRuleMatchResult.getQuantity());
    orderDetail.setGiftStrategyId(result.getGiftStrategy().getGiftStrategyId());
    orderDetail.setGiftStrategyName(result.getGiftStrategy().getGiftStrategyName());
    if (sku.isCombination()) {
      parseCombination(salesOrder, orderDetail, context);
    } else {
      salesOrder.setQuantity(salesOrder.getQuantity() + orderDetail.getQuantity());
    }
    //推荐仓库
    orderDetail.setSuggestWarehouseId(salesOrder.getSuggestWarehouseId());
    orderDetail.setSuggestWarehouseName(salesOrder.getSuggestWarehouseName());
    orderDetail.setSuggestVirtualWarehouseId(salesOrder.getSuggestVirtualWarehouseId());
    orderDetail.setSuggestVirtualWarehouseName(salesOrder.getSuggestVirtualWarehouseName());
    salesOrder.getDetails().add(orderDetail);
  }

  /**
   * 解析赠品：赠品为组合商品
   */
  private void parseCombination(SalesOrder salesOrder, SalesOrderDetail detail,
      OrderTranslatableContext context) {
    detail.setSalesOrderDetailId(salesOrderDetailService.getIdGenerator().next());
    detail.setSalesOrderId(salesOrder.getSalesOrderId());
    detail.setSalesOrderDetailType(SalesOrderDetailType.ORIGINAL);
    detail.setCombination(true);
    List<ProductCombination> combinations = productCombinationService
        .listByCombination(detail.getSkuId());
    for (ProductCombination combination : combinations) {
      ProductSku sku = productSkuService.getByKey(combination.getSkuId());
      SalesOrderDetail combinationDetail = createDetail(sku);
      combinationDetail.setStatus(sku.getProduct().getProductType() == ProductType.VIRTUAL ?
          SalesOrderDetailStatus.DELIVERED : SalesOrderDetailStatus.WAITING);
      combinationDetail.setQuantity(combination.getQuantity() * detail.getQuantity());
      combinationDetail.setOriginalDetailid(detail.getSalesOrderDetailId());
      combinationDetail.setActivityId(detail.getActivityId());
      combinationDetail.setCombinationDetail(true);
      combinationDetail.setPrepackage(detail.isPrepackage());
      combinationDetail.setSuggestWarehouseId(salesOrder.getSuggestWarehouseId());
      combinationDetail.setSuggestWarehouseName(salesOrder.getSuggestWarehouseName());
      combinationDetail.setSuggestVirtualWarehouseId(salesOrder.getSuggestVirtualWarehouseId());
      combinationDetail.setSuggestVirtualWarehouseName(salesOrder.getSuggestVirtualWarehouseName());
      salesOrder.getDetails().add(combinationDetail);
      salesOrder.setQuantity(salesOrder.getQuantity() + combinationDetail.getQuantity());
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "赠品为组合商品：规格：{},数量：{}件", sku.getSkuName(), combinationDetail.getQuantity());
    }
  }

  private SalesOrderDetail createDetail(ProductSku sku) {
    SalesOrderDetail orderDetail = new SalesOrderDetail();
    orderDetail.setStatus(
        sku.getProduct().getProductType() == ProductType.VIRTUAL ? SalesOrderDetailStatus.DELIVERED
            : SalesOrderDetailStatus.WAITING);
    orderDetail.setPrepackage(sku.isPrepackage());
    orderDetail.setCombination(sku.isCombination());
    orderDetail.setCombinationDetail(false);
    orderDetail.setSellingPrice(sku.getSellingPrice());
    orderDetail.setCostPrice(sku.getCostPrice());
    orderDetail.setDistributionPrice(sku.getDistributionPrice());
    orderDetail.setSettlementPrice(0D);
    orderDetail.setActualSellingPrice(0D);
    orderDetail.setActualAmount(0D);
    orderDetail.setDiscountAmount(0D);
    orderDetail.setDistributionAmount(0D);
    orderDetail.setSellingAmount(0D);
    orderDetail.setSettlementAmount(0D);
    orderDetail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
    orderDetail.setLockStock(false);
    orderDetail.setOos(false);
    orderDetail.setManualAdd(false);
    orderDetail.setSeparate(false);
    orderDetail.setNeedReturnGoods(false);
    orderDetail.setProductId(sku.getProductId());
    orderDetail.setProductCode(sku.getProductCode());
    orderDetail.setProductName(sku.getProductName());
    orderDetail.setSkuId(sku.getSkuId());
    orderDetail.setSkuCode(sku.getSkuCode());
    orderDetail.setSkuName(sku.getSkuName());
    orderDetail.setWeight(sku.getWeight());
    orderDetail.setProductType(sku.getProduct().getProductType());
    orderDetail.setMallSalesOrderDetailStatus(MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS);
    orderDetail.setGift(true);
    orderDetail.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    return orderDetail;
  }

  /**
   * 解析活动
   * 活动店铺不匹配不送赠品
   * 赠送结果没有赠品不解析
   */
  private void parseStrategy(OrderTranslatableContext context) {
    GiftStrategyTranslator translator;
    StrategyMatchResult result;
    SalesOrder salesOrder = context.getSalesOrder();
    if (salesOrder.getMarketings() == null) {
      salesOrder.setMarketings(new ArrayList<>(6));
    }
    //先赠送福袋
    for (Map.Entry<Long, GiftStrategyWrapper> entry : luckbagStrategyMap.entrySet()) {
      if (entry.getValue().getStores().stream()
          .noneMatch(x -> x.getStoreId().equals(context.getStore().getStoreId()))) {
        continue;
      }
      context.setMarketingCheckOrder(createCheckOrder(salesOrder));
      //送赠品
      translator = getTranslator(entry.getValue());
      result = translator.giveGift(context, entry.getValue());
      if (result == null || !result.hasGift()) {
        continue;
      }
      parseGiftGiveResult(entry.getValue().getGiftStrategyId(), context, result);
      context.setData(MARKETING_GIFT, result.getGifts());
      SalesOrderMarketing orderMarketing = createOrderMarketing(entry.getValue(), salesOrder);
      salesOrder.getMarketings().add(orderMarketing);
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "赠品类型：福袋");
      return;
    }
    //叠加活动
    for (Map.Entry<Long, GiftStrategyWrapper> entry : strategyMap.entrySet()) {
      if (entry.getValue().getStores().stream()
          .noneMatch(x -> x.getStoreId().equals(context.getStore().getStoreId()))) {
        continue;
      }
      context.setMarketingCheckOrder(createCheckOrder(salesOrder));
      translator = getTranslator(entry.getValue());
      //送赠品
      result = translator.giveGift(context, entry.getValue());
      if (result == null || !result.hasGift()) {
        continue;
      }
      //解析封装结果
      parseGiftGiveResult(entry.getValue().getGiftStrategyId(), context, result);
      if (context.getData(MARKETING_GIFT) == null) {
        context.setData(MARKETING_GIFT, new ArrayList<>(6));
      }
      Collection<StrategyRuleMatchResult> gifts = context.getData(MARKETING_GIFT);
      gifts.addAll(result.getGifts());
      SalesOrderMarketing orderMarketing = createOrderMarketing(entry.getValue(), salesOrder);
      salesOrder.getMarketings().add(orderMarketing);
    }
  }

  private SalesOrder createCheckOrder(SalesOrder salesOrder) {
    SalesOrder marketingCheckOrder = new SalesOrder();
    marketingCheckOrder.setQuantity(salesOrder.getQuantity());
    marketingCheckOrder.setActualAmount(salesOrder.getActualAmount());
    marketingCheckOrder.setPayStatus(salesOrder.getPayStatus());
    marketingCheckOrder.setSub(salesOrder.getSub());
    marketingCheckOrder.setDetails(new ArrayList<>(salesOrder.getDetails()));
    return marketingCheckOrder;
  }

  /**
   * 生成订单参加活动的信息
   */
  private SalesOrderMarketing createOrderMarketing(GiftStrategy strategy, SalesOrder salesOrder) {
    SalesOrderMarketing orderMarketing = new SalesOrderMarketing();
    orderMarketing.setMarketingId(strategy.getGiftStrategyId());
    orderMarketing.setMarketingCode(strategy.getGiftStrategyCode());
    orderMarketing.setMarketingName(strategy.getGiftStrategyName());
    orderMarketing.setMarketingType(MarketingType.GIFT);
    orderMarketing.setSalesOrderId(salesOrder.getSalesOrderId());
    return orderMarketing;
  }

  /**
   * 获取转化器.
   */
  private GiftStrategyTranslator getTranslator(GiftStrategy strategy) {
    switch (strategy.getGiftStrategyType()) {
      case GROUP:
        return groupGiftStrategyTranslator;
      default:
        return normalGiftStrategyTranslator;
    }
  }

  /**
   * 校验活动的有效性：
   * 根据策略中时间类型判断（下单时间或者支付时间）
   * 开始时间在策略时间之前，且结束时间在策略时间之后有效
   */
  private boolean validateStrategy(GiftStrategy strategy, OrderTranslatableContext context) {
    SalesOrderSub sub = context.getSalesOrder().getSub();
    if (strategy.getTimeType() == TimeType.PAID_TIME) {
      return !strategy.getBeginTime().isAfter(sub.getMallPaidTime()) &&
          !strategy.getEndTime().isBefore(sub.getMallPaidTime());
    } else {
      return !strategy.getBeginTime().isAfter(sub.getMallCreatedTime()) &&
          !strategy.getEndTime().isBefore(sub.getMallCreatedTime());
    }
  }
}
