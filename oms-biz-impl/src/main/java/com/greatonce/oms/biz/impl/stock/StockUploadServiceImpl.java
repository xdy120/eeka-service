package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StockUploadStrategyService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.marketing.PresellService;
import com.greatonce.oms.biz.message.MessageService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.stock.StockUploadService;
import com.greatonce.oms.bo.stock.AsyncStockUploadBO;
import com.greatonce.oms.bo.stock.StockUploadSkuBO;
import com.greatonce.oms.bo.stock.SyncStockUploadBO;
import com.greatonce.oms.bo.stock.SyncStockUploadUploadSkuBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.ProductBridge;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.dao.stock.StockUploadDao;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.domain.base.StockUploadWarehouse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.enums.MarketingType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.message.MessageType;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import io.netty.util.internal.ConcurrentSet;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;


/**
 * 库存上传. StockUploadServiceImpl
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author cca
 * @version 2018/5/16
 */
@Service
public class StockUploadServiceImpl extends AbstractService<Stock, StockQuery> implements
    StockUploadService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(StockUploadServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK_UPLOAD);

  @Autowired
  private StockUploadDao stockUploadDao;
  @Autowired
  private StockUploadStrategyService stockUploadStrategyService;
  @Autowired
  private PresellService presellService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  @Qualifier("bizExecutor")
  private AsyncTaskExecutor bizExecutor;
  @Autowired
  private MessageService messageService;

  @Override
  public Long allUpload() {
    Long batchNo = getIdGenerator().next();
    BIZ_LOGGER.log(batchNo, "全店初始化");
    return batchNo;
  }

  @Override
  public Long storeUpload(Long strategyId) {
    Long batchNo = getIdGenerator().next();
    StockUploadStrategy setting = stockUploadStrategyService.getByKey(strategyId);

    final List<ProductMallMapping> mappings = productMallMappingService
        .listAutoUploadMapping(setting.getStoreId());
    for (ProductMallMapping mapping : mappings) {
      getMqProducer().send(
          new StockChangedMessage(batchNo.toString(), mapping.getSkuId(), mapping.getStoreId(),
              BizContext.getNickname(), "店铺初始化"));
    }
    BIZ_LOGGER.log(batchNo, "单店初始化", setting.getStockUploadStrategyName());
    return batchNo;
  }

  @Override
  protected QueryDao<Stock, StockQuery> getDAO() {
    return stockUploadDao;
  }

  /**
   * 计算上传的数量.
   *
   * @param mapping 铺货
   * @param stockSetting 仓库配置
   */
  @Override
  public int calcUploadQuantity(ProductMallMapping mapping, StockUploadStrategy strategy,
      StockSetting stockSetting) {
    BigDecimal finalValue = null;
    if (!Assert.isNull(mapping.getMarketingId())
        && mapping.getMarketingType() == MarketingType.PRE_SELL) {
      // 要有一个 根据 预售id 和 店铺id 找到一个预售计划
      Integer rate = presellService
          .getStoreRate(mapping.getMarketingId(), mapping.getStoreId());
      LOGGER.debug("{},预售店铺比例：{}", mapping, rate);
      if (rate != null) {
        int total = stockUploadDao.calcPresellQuantity(mapping);
        finalValue = new BigDecimal(total * rate / 100.00);
        LOGGER.debug("预售库存：{},{}", mapping, finalValue);
      }
    }
    if (finalValue == null) {
      //根据 设置库存上传的id 查询所有仓库
      Map<Long, StockUploadWarehouse> warehouseMap = stockUploadStrategyService
          .getSettingStockUploadWarehouseMap(strategy.getStockUploadStrategyId());

      //库存总量
      BigDecimal value = stockUploadDao.calcQuantity(mapping, warehouseMap);
      //库存的最小值  否则存到指定的店铺
      BigDecimal warn = BigDecimal.valueOf(stockSetting.getOnlyToShopMin());
      //可销商品的数量
      BigDecimal canSaleTotal = stockUploadDao
          .calcCanSaleTotal(mapping, stockUploadStrategyService.getUploadWarehouseIds());
      //拿出两者最小值
      value = canSaleTotal.compareTo(value) > 0 ? value : canSaleTotal;
      LOGGER
          .debug("铺货：{}，可销：{}，总可销：{}，预警：{}，最终：{}", mapping, value, canSaleTotal, warn);

      if (warn.compareTo(value) >= 0 && !Assert.isEmpty(stockSetting.getOnlyToShop())
          && stockSetting.getOnlyToShop()
          .contains(mapping.getStoreId().toString())) {
        finalValue = BigDecimal.ZERO;
      } else {
        finalValue = value;
      }
    }
    if (finalValue.compareTo(BigDecimal.ZERO) < 0) {
      return 0;
    }
    return finalValue.setScale(0, 6).intValue();
  }

  @Override
  public void asyncManualUpload(AsyncStockUploadBO stockUploadBO, StockUploadType uploadType,
      boolean calcStock, String batchNo, String operator, String reason) {
    final StockUploadStrategy strategy = stockUploadStrategyService
        .getByKey(stockUploadBO.getConfigId());
    final StockSetting stockSetting = settingService.getInventorySetting();
    final Map<Long, StockUploadSkuBO> skuMap = new HashMap<>(stockUploadBO.getSkus().size());
    stockUploadBO.getSkus().forEach(sku -> {
      sku.setAutoCalcQuantity(calcStock);
      skuMap.put(sku.getSkuId(), sku);
    });
    final List<ProductMallMapping> mappings = productMallMappingService
        .listMapping(skuMap.keySet(), strategy.getStoreId());
    asyncManualUpload(strategy, stockSetting, skuMap, mappings, uploadType, batchNo,
        operator, reason);
  }

  @Override
  public void asyncManualUpload(StockUploadStrategy strategy, StockSetting stockSetting,
      Map<Long, StockUploadSkuBO> skuMap, List<ProductMallMapping> mappings,
      StockUploadType uploadType, String batchNo, String operator, String reason) {
    Store store = storeService.getByKey(strategy.getStoreId());
    LOGGER.debug("全量上传，SKU数量：{}，铺货数量：{}", skuMap.keySet().size(), mappings.size());
    if (!Assert.isEmpty(mappings)) {
      ProductBridge productBridge = mallBridgeFactory.getProductBridge(store.getMallType());
      Set<Long> processedSkuIds = new ConcurrentSet<>();
      CountDownLatch countDownLatch = new CountDownLatch(mappings.size());
      for (ProductMallMapping mapping : mappings) {
        bizExecutor.execute(() -> {
          try {
            if (!Assert.isTrue(strategy.isManualUpload())) {
              StockUploadLogUtil.log(store, mapping, batchNo, operator, "{}，未开启手工上传：{}！", reason,
                  strategy.getStockUploadStrategyName());
              return;
            }
            if (!Assert.isEmpty(strategy.getRule().getBrandNames()) && !Assert
                .isEmpty(mapping.getBrandName()) && !strategy.getRule().getBrandNames()
                .contains(mapping.getBrandName())) {
              StockUploadLogUtil.log(store, mapping, batchNo, operator, "{}，店铺不上传品牌：{}！", reason,
                  strategy.getRule().getBrandNames());
              return;
            }
            StockUploadSkuBO stockUploadSkuBo = skuMap.get(mapping.getSkuId());
            SkuQuantityUploadRequest request = new SkuQuantityUploadRequest(store);
            request.setMapping(mapping);
            request.setUploadConfig(strategy);
            request.setUploadType(uploadType);
            if (stockUploadSkuBo.isAutoCalcQuantity()) {
              request.setQuantity(calcUploadQuantity(mapping, strategy, stockSetting));
            } else {
              request.setQuantity(stockUploadSkuBo.getQuantity());
            }
            SkuQuantityUploadResponse response = productBridge.uploadQuantity(request);
            StockUploadLogUtil.log(response, batchNo, operator, reason);
          } finally {
            countDownLatch.countDown();
            processedSkuIds.add(mapping.getSkuId());
          }
        });
      }
      try {
        countDownLatch.await();
      } catch (InterruptedException e) {
        LOGGER.error("库存上传等待队列上传失败！", e);
      }
      processedSkuIds.forEach(skuMap::remove);
      createMessage(batchNo, reason);
    }
    skuMap.forEach((k, v) -> StockUploadLogUtil
        .log(store, v.getProductCode(), v.getSkuCode(), batchNo, operator, "{}，未找到铺货关系", reason));
  }

  @Override
  public void autoUpload(StockUploadStrategy strategy, StockSetting stockSetting,
      List<ProductMallMapping> mappings, String batchNo, String operator, String reason) {
    Store store = storeService.getByKey(strategy.getStoreId());
    if (!Assert.isEmpty(mappings)) {
      ProductBridge productBridge = mallBridgeFactory.getProductBridge(store.getMallType());
      for (ProductMallMapping mapping : mappings) {
        try {
          if (!Assert.isTrue(strategy.isAutoUpload())) {
            StockUploadLogUtil.log(store, mapping, batchNo, operator, "{}，未开启自动上传：{}！", reason,
                strategy.getStockUploadStrategyName());
            continue;
          }
          if (!Assert.isEmpty(strategy.getRule().getBrandNames()) && !Assert
              .isEmpty(mapping.getBrandName()) && !strategy.getRule().getBrandNames()
              .contains(mapping.getBrandName())) {
            StockUploadLogUtil.log(store, mapping, batchNo, operator, "{}，店铺不上传品牌：{}！", reason,
                strategy.getRule().getBrandNames());
            continue;
          }
          SkuQuantityUploadRequest request = new SkuQuantityUploadRequest(store);
          request.setMapping(mapping);
          request.setUploadConfig(strategy);
          request.setUploadType(StockUploadType.COVER);
          request.setQuantity(calcUploadQuantity(mapping, strategy, stockSetting));
          SkuQuantityUploadResponse response = productBridge.uploadQuantity(request);
          StockUploadLogUtil.log(response, batchNo, operator, reason);
        } catch (Exception ex) {
          LOGGER.error("库存上传异常：{},{},{},{}", mapping.getMallSkuId(), mapping.getSkuCode(), batchNo,
              mapping.getStoreName());
          LOGGER.error("库存上传异常", ex);
          StockUploadLogUtil
              .log(store, mapping, batchNo, operator, "{}，{}", reason, ex.getMessage());
        }
      }
    }
  }


  /**
   * 套装的处理.
   *
   * @param batchNo 批次号
   * @param operator 操作人
   * @param request 封装上传的请求
   * @param productBridge 上传的bridge
   * @param uploadConfig 上传的配置
   * @param comboMap 组合套装
   */
  private void coverUploadCombo(String batchNo, String operator, SkuQuantityUploadRequest request,
      ProductBridge productBridge, StockUploadStrategy uploadConfig,
      Map<Long, List<ProductCombination>> comboMap) {
    //拿出 当前铺货关系商品的 skuCode 为 comboMap的key 的  List
    List<ProductCombination> details = comboMap.get(request.getMapping().getSkuId());

    //获取这个 组合套装里 商品 skuId
    Map<Long, ProductCombination> detailMap = CollectionUtil
        .listToMap(details, x -> x.getCombinationId());
    //根据这些 skuId 和 storeId 找出 所有的铺货关系
    List<ProductMallMapping> productMallMappingList = productMallMappingService
        .listMapping(detailMap.keySet(), uploadConfig.getStoreId());

    //1.套装明细 和 铺货关系 之间的关系一一对应
    for (ProductMallMapping mapping : productMallMappingList) {
      ProductCombination detail = detailMap.get(mapping.getSkuId());
      request.setMapping(mapping);
      // 就是 套装卖了  那么套装里的商品的 库存也要减
      //套装的 库存量
      //套装的库存数量 是通过明细 来算出来的   那么只用管 扣除明细的数量即可
      request.setQuantity(calcComboQuantity(detail, request.getQuantity()));
      try {
        //重新上传 套装明细里的商品库存
//        SkuQuantityUploadResponse response = productBridge.uploadQuantity(request);
//        StockLogUtil.log(response, batchNo, operator, "套装");
      } catch (MallException ex) {
//        StockLogUtil.log(request, batchNo, operator, "套装上传失败：{}", ex.getMessage());
      }
    }
  }

  /**
   * 套装的计算.
   *
   * @param detail 组合套装信息
   * @param skuQuantity 规格数量
   */
  @Override
  public int calcComboQuantity(ProductCombination detail, int skuQuantity) {
    if (detail.getQuantity() <= 1) {
      return skuQuantity;
    }
    return skuQuantity / detail.getQuantity();
  }


  /**
   * 同步手工上传.
   *
   * @param stockUploadBo 前台传的BO
   * @param batchNo 批次号
   * @param operator 操作者
   * @param reason 理由
   */
  @Override
  public Map<Long, SyncStockUploadUploadSkuBO> syncManualUpload(SyncStockUploadBO stockUploadBo,
      String batchNo, String operator, String reason) {
    Store store = storeService.getByKey(stockUploadBo.getStoreId());
    Map<Long, SyncStockUploadUploadSkuBO> skuMap = new HashMap<>(
        stockUploadBo.getSkus().size());
    stockUploadBo.getSkus().forEach(x -> {
      x.setSuccess(false);
      x.setMsg("未找到铺货关系！");
      skuMap.put(x.getSkuId(), x);
    });
    List<ProductMallMapping> mappings = productMallMappingService
        .listMapping(skuMap.keySet(), store.getStoreId());
    CountDownLatch countDownLatch = new CountDownLatch(mappings.size());
    for (ProductMallMapping mapping : mappings) {
      bizExecutor.execute(() -> {
        SyncStockUploadUploadSkuBO info = skuMap.get(mapping.getSkuId());
        if (!Assert.isEmpty(info.getMallProductId()) && !mapping.getMallProductId()
            .equals(info.getMallProductId())) {
          countDownLatch.countDown();
          return;
        }
        mapping.setAutoListing(stockUploadBo.isListing());
        SkuQuantityUploadRequest request = new SkuQuantityUploadRequest(store);
        request.setMapping(mapping);
        request.setUploadType(info.getUploadType());
        request.setQuantity(info.getQuantity());
        request.setStore(store);
        try {
          ProductBridge productBridge = mallBridgeFactory.getProductBridge(store.getMallType());
          SkuQuantityUploadResponse response = productBridge.uploadQuantity(request);
          info.setSuccess(response.isSuccess());
          info.setMsg(response.isSuccess() ? "成功" : response.getResult());
          StockUploadLogUtil.log(response, batchNo, operator, reason);
        } catch (MallException ex) {
          StockUploadLogUtil.log(request, batchNo, operator, "{}，上传失败：{}", reason, ex.getMessage());
        } finally {
          countDownLatch.countDown();
        }
      });
    }
    try {
      countDownLatch.await();
    } catch (InterruptedException e) {
      LOGGER.error("库存上传等待队列上传失败！", e);
    }
    createMessage(batchNo, reason);
    return skuMap;
  }

  /**
   * 创建消息通知库存上传完成
   */
  private void createMessage(String batchNo, String reason) {
    Message message = new Message();
    message.setContent("库存上传完成！批次号：" + batchNo);
    message.setMessageType(MessageType.NOTIFICATION);
    message.setUserId(BizContext.getUserId());
    message.setExpirationTime(LocalDateTime.now().plusMonths(1));
    messageService.create(message);
  }
}
