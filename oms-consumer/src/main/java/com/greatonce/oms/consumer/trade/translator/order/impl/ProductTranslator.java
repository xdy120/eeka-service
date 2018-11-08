package com.greatonce.oms.consumer.trade.translator.order.impl;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.MathUtil;
import com.greatonce.oms.biz.impl.trade.SalesOrderUtil;
import com.greatonce.oms.biz.marketing.ActivityService;
import com.greatonce.oms.biz.marketing.PresellService;
import com.greatonce.oms.biz.product.ProductCombinationService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.mall.MallSalesOrderDetailInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatable;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableContext;
import com.greatonce.oms.consumer.trade.translator.order.OrderTranslatableMode;
import com.greatonce.oms.consumer.trade.translator.order.TranslatorOrderCondition;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.MarketingType;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallStepOrderStatus;
import com.greatonce.oms.domain.enums.marketing.PresellType;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderMarketing;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 商品明细转化器
 *
 * @author:buer
 */
@Component("orderProductTranslator")
@TranslatorOrderCondition
public class ProductTranslator implements OrderTranslatable {

  private static final TranslateOrderLogger LOGGER = OmsLoggerFactory.getTranslateOrderLogger();
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private PresellService preSellPlanService;
  @Autowired
  private ActivityService activityService;
  @Autowired
  private ProductCombinationService combinedProductDetailService;
  @Resource
  private IdGenerator salesOrderDetailIdGenerator;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;

  @Override
  public void save(OrderTranslatableContext context) {
  }

  @Override
  public void rollback(OrderTranslatableContext context) {
  }

  /**
   * 商品转化主入口
   */
  @Override
  public void translate(OrderTranslatableContext context) {
    if (context.getMode() == OrderTranslatableMode.CREATE) {
      LOGGER.debug(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "订单明细转化");
      this.parseDetails(context, context.getMallSalesOrderInfo(), context.getSalesOrder());
      if (context.isNeedDelivery()) {
        setPresellStatus(context);
        setOrderType(context);
      } else {
        setOrderRefundStatus(context);
      }
    } else if (context.getMode() == OrderTranslatableMode.MODIFY) {
      if (context.isPrepayFinalPaid()) {
        LOGGER.debug(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "预付款订单付尾款，明细金额信息修改");
        resetDetailMoney(context);
      }
    }
  }

  /**
   * 解析订单逻辑： 根据每条平台明细进行解析，创建一条对应的oms订单明细
   */
  private void parseDetails(OrderTranslatableContext context, MallSalesOrderInfo mallSalesOrderInfo,
      SalesOrder salesOrder) {
    LOGGER
        .debug(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
            "订单明细数量：{}", mallSalesOrderInfo.getDetails().size());

    salesOrder.setDetails(new ArrayList<>(mallSalesOrderInfo.getDetails().size()));
    for (MallSalesOrderDetailInfo mallSalesOrderDetailInfo : mallSalesOrderInfo.getDetails()) {
      parseDetail(context, mallSalesOrderDetailInfo, salesOrder);
    }
  }

  /**
   * 解析明细.
   */
  private void parseDetail(OrderTranslatableContext context,
      MallSalesOrderDetailInfo mallSalesOrderDetailInfo, SalesOrder salesOrder) {
    //查找铺货关系
    ProductMatchInfo matchInfo = matchSku(context, mallSalesOrderDetailInfo);
    SalesOrderDetail detail = initDetail(salesOrder, mallSalesOrderDetailInfo, context);
    //异常商品标记并封装商品信息
    ProductSku productSku = matchInfo.getProductSku();
    ProductMallMapping productMallMapping = matchInfo.getProductMallMapping();
    if (productSku == null) {
      salesOrder.setProductAbnormal(true);
      detail.setProductAbnormal(true);
      salesOrder.getDetails().add(detail);
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(),
          "转化明细中商品异常：商品名:{}，商品编号:{}，规格名:{}，规格编号:{}，有无铺货:{}",
          mallSalesOrderDetailInfo.getMallProductName(),
          mallSalesOrderDetailInfo.getOuterCode(), mallSalesOrderDetailInfo.getMallSkuName(),
          mallSalesOrderDetailInfo.getOuterSkuCode(),
          productMallMapping == null ? "无" : "有");
      context.getLogs()
          .add("系统商品规格找不到，标记异常商品，外部规格编码：" + mallSalesOrderDetailInfo.getOuterSkuCode());
      return;
    }
    if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXCHANGE) {
      if (Assert.isTrue(productSku.isCombination())) {
        throw new OmsException("换货订单转化不支持组合套装");
      } else {
        parseSku(context, detail, productSku, salesOrder);
        setExchangeDetailInfo(context, detail, productSku);
      }
    } else {
      if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.SALES) {
        if (context.isNeedDelivery()) {
          if (!Assert.isNull(productMallMapping)) {
            if (!Assert.isNull(productMallMapping.getMarketingType())) {
              parseMarketing(context, detail, matchInfo);
            }
            //代发货明细在OMS中状态为已发货
            if (Assert.isTrue(productMallMapping.isDropShipping())) {
              detail.setDropShipping(true);
              detail.setStatus(SalesOrderDetailStatus.DELIVERED);
              context.getLogs().add("订单中包含代发商品");
              LOGGER.info(context.getSerialNo(), context.getStore(),
                  context.getMallSalesOrder().getTradeId(),
                  "订单中包含代发商品：{}，{}",
                  productMallMapping.getProductName(),
                  productMallMapping.getSkuName());
            }
          }
          //对预售明细判断是否预售有货先发
          if (!Assert.isNull(mallSalesOrderDetailInfo.getPresellDeliveryDate())
              || detail.getPresellId() != null) {
            detail.setPresellPriority(
                Assert.isTrue(context.getStore().getSetting().isPresellPriority()));
          }
          if (context.getSkuMap() == null) {
            context.setSkuMap(new HashMap<>(context.getMallSalesOrderInfo().getDetails().size()));
          }
          context.getSkuMap().put(productSku.getSkuId(), productSku);
        }
      }
      //解析明细商品信息
      if (Assert.isTrue(productSku.isCombination())) {
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "该明细为组合商品，sku:{},{}",
            productSku.getSkuCode(), productSku.getSkuName());
        parseComboSku(context, detail, productSku, salesOrder);
      } else {
        parseSku(context, detail, productSku, salesOrder);
      }
    }
  }

  /**
   * 换货订单金额设置.
   */
  private void setExchangeDetailInfo(OrderTranslatableContext context, SalesOrderDetail detail,
      ProductSku productSku) {
    Long salesOrderId =
        salesOrderService.getSalesOrderIdByStoreIdAndTradeId(context.getStore().getStoreId(),
            context.getMallSalesOrderInfo().getSourceTradeId());
    if (!Assert.isNull(salesOrderId)) {
      SalesOrderDetail eg = new SalesOrderDetail();
      eg.setSalesOrderId(salesOrderId);
      eg.setProductId(productSku.getProductId());
      SalesOrderDetail sourceDetail = salesOrderDetailService.getByExample(eg);
      if (!Assert.isNull(sourceDetail)) {
        detail.setActualAmount(sourceDetail.getActualAmount());
        detail.setDiscountAmount(sourceDetail.getDiscountAmount());
        detail.setDistributionAmount(sourceDetail.getDistributionAmount());
        detail.setDistributionPrice(sourceDetail.getDistributionPrice());
        detail.setSellingAmount(sourceDetail.getSellingAmount());
        detail.setActualSellingPrice(sourceDetail.getActualSellingPrice());
        detail.setSellingPrice(sourceDetail.getSellingPrice());
        detail.setSettlementAmount(sourceDetail.getSettlementAmount());
        detail.setSettlementPrice(sourceDetail.getSettlementPrice());
      }
    }
  }

  /**
   * 订单明细初始化
   */
  private SalesOrderDetail initDetail(SalesOrder salesOrder,
      MallSalesOrderDetailInfo mallSalesOrderDetailInfo, OrderTranslatableContext context) {
    //基本信息
    SalesOrderDetail salesOrderDetail = new SalesOrderDetail();
    salesOrderDetail.setSalesOrderDetailId(salesOrderDetailIdGenerator.next());
    salesOrderDetail.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    salesOrderDetail.setSalesOrderId(salesOrder.getSalesOrderId());
    salesOrderDetail.setMallDetailId(mallSalesOrderDetailInfo.getOid());
    salesOrderDetail.setCreatedTime(LocalDateTime.now());
    //状态
    salesOrderDetail
        .setSalesOrderDetailRefundStatus(getRefundStatus(mallSalesOrderDetailInfo));
    salesOrderDetail.setMallSalesOrderDetailStatus(mallSalesOrderDetailInfo.getStatus());
    salesOrderDetail.setStatus(toDetailStatus(mallSalesOrderDetailInfo.getStatus()));

    salesOrderDetail.setGift(false);
    salesOrderDetail.setOos(false);
    salesOrderDetail.setLockStock(false);
    salesOrderDetail.setManualAdd(false);
    salesOrderDetail.setSeparate(false);
    salesOrderDetail.setCombination(false);
    salesOrderDetail.setCombinationDetail(false);
    salesOrderDetail.setNeedReturnGoods(false);
    salesOrderDetail.setPresellDeliveryDate(mallSalesOrderDetailInfo.getPresellDeliveryDate());
    salesOrderDetail.setMallPresellDeliveryRequiring(
        mallSalesOrderDetailInfo.getMallPresellDeliveryRequiring());
    //金额信息
    setDetailMoney(salesOrderDetail, mallSalesOrderDetailInfo);
    //自动识别赠品配置
    if (Assert.isTrue(context.getStore().getSetting().isTaobaoAutoGiftChecking())) {
      if (salesOrderDetail.getActualAmount() == 0D
          && salesOrderDetail.getSettlementAmount() == 0D) {
        salesOrderDetail.setGift(true);
      }
    }
    //平台商品信息
    salesOrderDetail.setMallSkuId(mallSalesOrderDetailInfo.getMallSkuId());
    salesOrderDetail.setMallSkuOutCode(mallSalesOrderDetailInfo.getOuterSkuCode());
    salesOrderDetail.setMallProductId(mallSalesOrderDetailInfo.getMallProductId());
    salesOrderDetail.setMallProductOutCode(mallSalesOrderDetailInfo.getOuterCode());
    salesOrderDetail.setMallProductName(mallSalesOrderDetailInfo.getMallProductName());
    salesOrderDetail.setMallSkuName(mallSalesOrderDetailInfo.getMallSkuName());
    salesOrderDetail.setMallDetailId(mallSalesOrderDetailInfo.getOid());
    if (mallSalesOrderDetailInfo.isShopDelivery()) {
      LOGGER
          .info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
              "门店发货状态：{}，门店订单状态为全部发货", mallSalesOrderDetailInfo.isShopDelivery());
      salesOrderDetail.setStatus(SalesOrderDetailStatus.DELIVERED);
    }
    //推荐仓库
    salesOrderDetail.setSuggestWarehouseId(salesOrder.getSuggestWarehouseId());
    salesOrderDetail.setSuggestWarehouseName(salesOrder.getSuggestWarehouseName());
    salesOrderDetail.setSuggestVirtualWarehouseId(salesOrder.getSuggestVirtualWarehouseId());
    salesOrderDetail.setSuggestVirtualWarehouseName(salesOrder.getSuggestVirtualWarehouseName());
    return salesOrderDetail;
  }

  /**
   * 明细状态转化
   */
  private SalesOrderDetailStatus toDetailStatus(MallSalesOrderDetailStatus status) {
    switch (status) {
      case UNKNOWN:
        return SalesOrderDetailStatus.DELIVERED;
      case NEW_TRADE:
        return SalesOrderDetailStatus.INVALID;
      case WAIT_BUYER_PAY:
        return SalesOrderDetailStatus.WAITING;
      case WAIT_SELLER_SEND_GOODS:
        return SalesOrderDetailStatus.WAITING;
      case WAIT_BUYER_CONFIRM_GOODS:
        return SalesOrderDetailStatus.DELIVERED;
      case TRADE_FINISHED:
        return SalesOrderDetailStatus.DELIVERED;
      case TRADE_CLOSE:
        return SalesOrderDetailStatus.INVALID;
      default:
        return SalesOrderDetailStatus.INVALID;
    }
  }

  /**
   * 转化单件商品
   */
  private void parseSku(OrderTranslatableContext context, SalesOrderDetail detail,
      ProductSku productSku, SalesOrder salesOrder) {
    detail.setPrepackage(Assert.isTrue(productSku.isPrepackage()));
    //根据商品封装明细信息
    createProduct(detail, productSku);
    detail.setWeight(productSku.getWeight() != null ?
        productSku.getWeight() * detail.getQuantity() : 0);
    salesOrder.getSub().setWeight(salesOrder.getSub().getWeight() + detail.getWeight());
    //结算金额计算
    context.getSalesOrder().getDetails().add(detail);
    salesOrder.setQuantity(salesOrder.getQuantity() + detail.getQuantity());
    if (productSku.getProduct().getProductType().equals(ProductType.VIRTUAL)) {
      context.getVirtualDetails().add(detail);
    }
  }

  /**
   * 转化组合商品.
   */
  private void parseComboSku(OrderTranslatableContext context, SalesOrderDetail sourceDetail,
      ProductSku productSku, SalesOrder salesOrder) {
    sourceDetail.setPrepackage(Assert.isTrue(productSku.isPrepackage()));
    if (Assert.isTrue(productSku.isGiftBox())) {
      salesOrder.setQuantity(salesOrder.getQuantity() + sourceDetail.getQuantity());
      return;
    }
    //原始明细
    createProduct(sourceDetail, productSku);
    salesOrder.getDetails().add(sourceDetail);

    List<ProductCombination> comblist =
        combinedProductDetailService.listByCombination(productSku.getSkuId());
    List<SalesOrderDetail> combDetails = new ArrayList<>(comblist.size());
    ProductSku combinationSku;
    for (ProductCombination combination : comblist) {
      //复制基本信息
      SalesOrderDetail combDetail = (SalesOrderDetail) sourceDetail.clone();
      combDetail.setSalesOrderDetailId(salesOrderDetailIdGenerator.next());
      combDetail.setOriginalDetailid(sourceDetail.getSalesOrderDetailId());
      combinationSku = productSkuService.getEffectiveById(combination.getSkuId());
      //商品基本信息和封装
      createProduct(combDetail, combinationSku);
      combDetail.setCombination(false);
      combDetail.setPrepackage(Assert.isTrue(sourceDetail.isPrepackage()));
      combDetail.setCombinationDetail(true);
      combDetail.setQuantity(combination.getQuantity() * sourceDetail.getQuantity());
      combDetail.setWeight(combinationSku.getWeight() != null ?
          combinationSku.getWeight() * sourceDetail.getQuantity() : 0);
      //价格计算
      Double sellingPrice = combinationSku.getSellingPrice();
      combDetail.setCostPrice(combinationSku.getCostPrice());
      combDetail.setSellingPrice(combinationSku.getMarkedPrice());
      combDetails.add(combDetail);

      salesOrder.setQuantity(salesOrder.getQuantity() + combDetail.getQuantity());
      salesOrder.getSub().setWeight(combDetail.getWeight() + salesOrder.getSub().getWeight());
      if (sellingPrice == 0D) {
        context.getLogs().add(String.format("异常：组合商品明细 %s--%s 的价格为0，明细分摊金额可能计算异常！",
            combinationSku.getSkuCode(), combinationSku.getSkuName()));
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "组合商品{}中商品{}的价格为0",
            productSku.getSkuName(), combinationSku.getSkuName());
      }
    }

    boolean result = SalesOrderUtil.recountDetailsAmount(sourceDetail, combDetails);
    if (!result) {
      context.getLogs().add("异常：组合商品子明细商品的销售价格都为0，分摊金额无法计算！");
      LOGGER.error(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(),
          "转化组合商品{}明细异常，套装中的商品价格未维护，导致组合商品明细总价为0，无法计算分摊金额！",
          productSku.getSkuName());
    }

    //添加到订单明细列表
    context.getSalesOrder().getDetails().addAll(combDetails);
  }

  /**
   * 转化预售.
   * 需要明细有预售信息
   */
  private void parsePresell(OrderTranslatableContext context, SalesOrderDetail detail,
      ProductMatchInfo matchInfo) {
    Long preSellId = matchInfo.getProductMallMapping().getMarketingId();
    Presell preSellPlan = preSellPlanService.getByKey(preSellId);
    if (preSellPlan == null || !preSellPlan.getEndTime().isAfter(LocalDateTime.now())) {
      return;
    }
    detail.setPresellId(preSellPlan.getPresellId());
    if (detail.getPresellDeliveryDate() == null) {
      detail.setPresellDeliveryDate(preSellPlan.getDeliveryDate());
    }
    context.getPreSellDetails().add(detail);
    LOGGER.info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
        "匹配预售：{}", preSellPlan.getPresellName());
  }

  /**
   * 转化活动报名.
   */
  private void parseActivity(OrderTranslatableContext context, SalesOrderDetail detail,
      ProductMatchInfo matchInfo) {
    //标记活动
    Long activityId = matchInfo.getProductMallMapping().getMarketingId();
    if (Assert.isNull(activityId)) {
      return;
    }
    //取得活动详情
    Activity activity = activityService.getByKey(activityId);
    if (activity == null) {
      return;
    }

    detail.setActivityId(activityId);
    if (!Assert.isNull(activity.getVirtualWarehouseId())) {
      detail.setSuggestVirtualWarehouseId(activity.getVirtualWarehouseId());
      detail.setSuggestVirtualWarehouseName(activity.getVirtualWarehouseName());
    }
    //封装活动报名信息
    SalesOrderMarketing salesOrderMarketing = new SalesOrderMarketing();
    salesOrderMarketing.setCreatedTime(DateTimeUtil.toLocalDateTime(new Date()));
    salesOrderMarketing.setSalesOrderId(context.getSalesOrder().getSalesOrderId());
    salesOrderMarketing.setMarketingId(activityId);
    salesOrderMarketing.setMarketingCode(activity.getActivityCode());
    salesOrderMarketing.setMarketingName(activity.getActivityName());
    salesOrderMarketing.setMarketingType(MarketingType.ACTIVITY);
    context.getSalesOrder().getMarketings().add(salesOrderMarketing);

    LOGGER.info(context.getSerialNo(), context.getStore(), context.getMallSalesOrder().getTradeId(),
        "匹配活动报名：{}", salesOrderMarketing.getMarketingName());
  }

  /**
   * 查找铺货关系.
   */
  private ProductMatchInfo matchSku(OrderTranslatableContext context,
      MallSalesOrderDetailInfo mallSalesOrderDetailInfo) {
    //商城订单明细中信息
    String mallProductId = mallSalesOrderDetailInfo.getMallProductId() == null ? ""
        : mallSalesOrderDetailInfo.getMallProductId();
    String mallSkuId = mallSalesOrderDetailInfo.getMallSkuId() == null ? ""
        : mallSalesOrderDetailInfo.getMallSkuId();
    String outerSkuCode = mallSalesOrderDetailInfo.getOuterSkuCode() == null ? ""
        : mallSalesOrderDetailInfo.getOuterSkuCode();
    String outerProductCode = mallSalesOrderDetailInfo.getOuterCode() == null ? ""
        : mallSalesOrderDetailInfo.getOuterCode();
    ProductMatchInfo productMatchInfo = new ProductMatchInfo();
    //匹配铺货关系
    ProductMallMapping productMallMapping = getProductMallMapping(mallSkuId, outerSkuCode,
        mallProductId, outerProductCode, context.getStore().getStoreId());
    if (productMallMapping != null && Assert.isTrue(productMallMapping.isMatched())) {
      //铺货关系不为空且关联
      Long skuId = productMallMapping.getSkuId();
      ProductSku productSku = productSkuService.getEffectiveById(skuId);
      if (productSku != null) {
        productMatchInfo.setProductSku(productSku);
        productMatchInfo.setProductMallMapping(productMallMapping);
        LOGGER.debug(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "匹配铺货关系成功，商品：{},{}，SKU：{},{}",
            productSku.getProductCode(), productSku.getProductName(), productSku.getSkuCode(),
            productSku.getSkuName());
      }
    } else {
      if (productMallMapping == null) {
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "SKU：{},{}的铺货未找到",
            mallSalesOrderDetailInfo.getMallSkuName(), mallSalesOrderDetailInfo.getOuterSkuCode());
      } else {
        LOGGER.info(context.getSerialNo(), context.getStore(),
            context.getMallSalesOrder().getTradeId(), "SKU：{},{}的铺货未关联",
            mallSalesOrderDetailInfo.getMallSkuName(), mallSalesOrderDetailInfo.getOuterSkuCode());
      }
      //按系统信息匹配商品
      if (!Assert.isEmpty(outerSkuCode)) {
        productMatchInfo.setProductSku(productSkuService.getEffectiveByCode(outerSkuCode));
      }
    }
    return productMatchInfo;
  }

  /**
   * 是否退款
   */
  private SalesOrderDetailRefundStatus getRefundStatus(
      MallSalesOrderDetailInfo mallSalesOrderDetailInfo) {
    switch (mallSalesOrderDetailInfo.getRefundStatus()) {
      case NO_REFUND:
        return SalesOrderDetailRefundStatus.NONE;
      case WAIT_SELLER_AGREE:
        return SalesOrderDetailRefundStatus.APPLY;
      case WAIT_BUYER_RETURN_GOODS:
        return SalesOrderDetailRefundStatus.APPLY;
      case WAIT_SELLER_CONFIRM_GOODS:
        return SalesOrderDetailRefundStatus.APPLY;
      case SELLER_REFUSE_BUYER:
        return SalesOrderDetailRefundStatus.APPLY;
      case CLOSED:
        return SalesOrderDetailRefundStatus.NONE;
      case SUCCESS:
        return SalesOrderDetailRefundStatus.REFUND;
      default:
        //退款状态未知按不退款处理
        return SalesOrderDetailRefundStatus.NONE;
    }
  }

  /**
   * 查铺货关系
   */
  private ProductMallMapping getProductMallMapping(String mallSkuId, String outerSkuCode,
      String mallProductId, String outerProductCode, Long storeId) {
    ProductMallMapping eg = new ProductMallMapping();
    eg.setStoreId(storeId);
    if (!Assert.isEmpty(mallSkuId)) {
      eg.setMallSkuId(mallSkuId);
    } else if (!Assert.isEmpty(outerSkuCode)) {
      eg.setMallSkuOutCode(outerSkuCode);
    } else if (!Assert.isEmpty(mallProductId)) {
      eg.setMallProductId(mallProductId);
    } else if (!Assert.isEmpty(outerProductCode)) {
      eg.setMallProductOutCode(outerProductCode);
    } else {
      return null;
    }
    return productMallMappingService.getByExample(eg);
  }

  /**
   * 封装基本信息
   */
  private void createProduct(SalesOrderDetail detail, ProductSku productSku) {
    Product product = productSku.getProduct();
    detail.setProductType(product.getProductType());
    detail.setProductId(product.getProductId());
    detail.setProductCode(product.getProductCode());
    detail.setProductName(product.getProductName());
    detail.setSkuId(productSku.getSkuId());
    detail.setSkuCode(productSku.getSkuCode());
    detail.setSkuName(productSku.getSkuName());
    detail.setPrepackage(productSku.isPrepackage());
    detail.setCombination(Assert.isTrue(productSku.isCombination()));
    detail.setSalesOrderDetailType(Assert.isTrue(productSku.isCombination()) ?
        SalesOrderDetailType.ORIGINAL : SalesOrderDetailType.NORMAL);
    detail.setCostPrice(FormatUtil.getOrZero(productSku.getCostPrice()));
    detail.setDistributionPrice(FormatUtil.getOrZero(productSku.getDistributionPrice()));
    if (detail.getProductType() == ProductType.VIRTUAL) {
      detail.setStatus(SalesOrderDetailStatus.DELIVERED);
    }
  }

  /**
   * 预售或活动报名转化
   */
  private void parseMarketing(OrderTranslatableContext context, SalesOrderDetail detail,
      ProductMatchInfo matchInfo) {
    MarketingType marketingType = matchInfo.getProductMallMapping().getMarketingType();
    //暂时只有预售和活动报名两种
    if (MarketingType.PRE_SELL.equals(marketingType)) {
      parsePresell(context, detail, matchInfo);
    } else if (MarketingType.ACTIVITY.equals(marketingType)) {
      parseActivity(context, detail, matchInfo);
    }
  }

  /**
   * 判断整单预售状态.
   */
  private void setPresellStatus(OrderTranslatableContext context) {
    List<SalesOrderDetail> preSellDetails = context.getPreSellDetails();
    SalesOrder salesOrder = context.getSalesOrder();
    if (preSellDetails.size() == 0) {
      salesOrder.getSub().setPresellType(PresellType.NONE);
      return;
    }
    if (preSellDetails.size() == salesOrder.getDetails().size()) {
      salesOrder.getSub().setPresellType(PresellType.ALL);
    } else {
      salesOrder.getSub().setPresellType(PresellType.PART);
    }

    LocalDate presellDeliveryDate;
    //选出最晚的预售日
    Optional<LocalDate> max =
        preSellDetails.stream().map(SalesOrderDetail::getPresellDeliveryDate)
            .max((date1, date2) -> {
              if (date1.isBefore(date2)) {
                return -1;
              } else if (date1.equals(date2)) {
                return 0;
              } else {
                return 1;
              }
            });
    presellDeliveryDate = max.orElseGet(() -> preSellDetails.get(0).getPresellDeliveryDate());
    salesOrder.getSub().setPresellDeliveryDate(presellDeliveryDate);
  }

  /**
   * 根据明细判断订单类型.
   */
  private void setOrderType(OrderTranslatableContext context) {
    List<SalesOrderDetail> virtualDetails = context.getVirtualDetails();
    if (virtualDetails.size() == context.getSalesOrder().getDetails().size()) {
      context.getSalesOrder().getSub().setSalesOrderType(SalesOrderType.EXPENSE);
    }
  }

  /**
   * 根据明细修改主单状态.
   */
  private void setOrderRefundStatus(OrderTranslatableContext context) {
    SalesOrder salesOrder = context.getSalesOrder();
    List<SalesOrderDetail> refunds = salesOrder.getDetails().stream()
        .filter(x -> x.getSalesOrderDetailRefundStatus() == SalesOrderDetailRefundStatus.REFUND)
        .collect(Collectors.toList());
    if (Assert.isEmpty(refunds)) {
      salesOrder.setRefundStatus(RefundStatus.NONE);
    } else if (refunds.size() == salesOrder.getDetails().size()) {
      salesOrder.setRefundStatus(RefundStatus.ALL);
    } else {
      salesOrder.setRefundStatus(RefundStatus.PART);
    }
  }

  /**
   * 重设明细金额.
   */
  private void resetDetailMoney(OrderTranslatableContext context) {
    SalesOrder salesOrder = context.getSalesOrder();
    MallSalesOrderInfo mallSalesOrderInfo = context.getMallSalesOrderInfo();
    //更新非套装明细金额
    salesOrder.getDetails().stream().filter(x -> !Assert.isTrue(x.isCombinationDetail())
        && !Assert.isEmpty(x.getMallDetailId()))
        .forEach(z -> mallSalesOrderInfo.getDetails().forEach(a -> {
          if (z.getMallSkuId().equals(a.getMallSkuId())) {
            setDetailMoney(z, a);
          }
        }));
    //更新套装明细金额
    salesOrder.getDetails().stream()
        .filter(x -> x.getSalesOrderDetailType() == SalesOrderDetailType.ORIGINAL).forEach(z -> {
      List<SalesOrderDetail> combinationDetails = salesOrder.getDetails().stream()
          .filter(c -> c.getOriginalDetailid().equals(z.getSalesOrderDetailId()))
          .collect(Collectors.toList());
      SalesOrderUtil.recountDetailsAmount(z, combinationDetails);
    });

  }

  /**
   * 设置明细金额.
   */
  private void setDetailMoney(SalesOrderDetail salesOrderDetail,
      MallSalesOrderDetailInfo mallSalesOrderDetailInfo) {
    salesOrderDetail.setActualAmount(mallSalesOrderDetailInfo.getActualSellingAmount());
    salesOrderDetail.setDiscountAmount(mallSalesOrderDetailInfo.getDiscountAmount());
    salesOrderDetail.setDistributionAmount(mallSalesOrderDetailInfo.getDistributionAmount());
    salesOrderDetail.setDistributionPrice(mallSalesOrderDetailInfo.getDistributionPrice());
    salesOrderDetail.setSellingAmount(mallSalesOrderDetailInfo.getSellingAmount());
    salesOrderDetail.setActualSellingPrice(mallSalesOrderDetailInfo.getActualSellingPrice());
    salesOrderDetail.setSellingPrice(mallSalesOrderDetailInfo.getSellingPrice());
    salesOrderDetail.setSettlementAmount(mallSalesOrderDetailInfo.getSettlementAmount());
    salesOrderDetail.setQuantity(mallSalesOrderDetailInfo.getQuantity());
    salesOrderDetail.setSettlementPrice(
        MathUtil.divide(salesOrderDetail.getSettlementAmount(), salesOrderDetail.getQuantity()));
  }
}