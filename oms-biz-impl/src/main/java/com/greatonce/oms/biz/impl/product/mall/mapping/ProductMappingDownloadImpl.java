package com.greatonce.oms.biz.impl.product.mall.mapping;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.biz.message.MessageService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.product.mall.mapping.ProductMappingDownload;
import com.greatonce.oms.bo.product.ProductMallMappingDownloadBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.ProductBridge;
import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.bridge.mall.response.ProductQueryResponse;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.enums.message.MessageType;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import com.greatonce.oms.domain.message.Message;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.util.BizContext;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 默认铺货下载器.
 *
 * @author zhangqin
 * @version 2016/11/16
 */
@Service
public class ProductMappingDownloadImpl implements ProductMappingDownload {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductMappingDownloadImpl.class);
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private ProductSkuService productService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private MqProducer mqProducer;
  @Autowired
  private MessageService messageService;
  @Autowired
  private UserService userService;


  @Override
  @Async("bizExecutor")
  public void asyncDownload(ProductMallMappingDownloadBO downloadBO) {
    BizContext.setNickname(downloadBO.getOperator());
    download(downloadBO);
  }

  @Override
  public void download(ProductMallMappingDownloadBO downloadBO) {
    Store store = storeService.getByKey(downloadBO.getStoreId());
    ProductBridge productBridge = mallBridgeFactory.getProductBridge(store.getMallType());
    if (productBridge == null) {
      throw new OmsException(1, "所选店铺不支持商品下载！");
    }
    downloadBO.setStore(store);
    if (!Assert.isEmpty(downloadBO.getMallProductId())) {
      downloadById(productBridge, downloadBO);
    } else if (!Assert.isEmpty(downloadBO.getMallProductOutCode())) {
      downloadByCode(productBridge, downloadBO);
    } else {
      //全店下载，如果有指定商品状态或平台支持全类型商品，则不需要分批
      if (downloadBO.getMallProductStatus() != null || productBridge.isSupportMultiStatus()) {
        downloadAll(productBridge, downloadBO);
      } else {
        downloadBO.setMallProductStatus(MallProductStatus.ONSALE);
        downloadAll(productBridge, downloadBO);
        downloadBO.setMallProductStatus(MallProductStatus.INSTOCK);
        downloadAll(productBridge, downloadBO);
      }
    }

    createMessage(downloadBO.getOperator());
  }

  /**
   * 创建消息通知铺货关系下载完成
   */
  private void createMessage(String operator) {
    User user = new User();
    user.setUserName(operator);
    user = userService.getByExample(user);

    if (!Assert.isNull(user)) {
      Message message = new Message();
      message.setContent("铺货关系下载完成！");
      message.setMessageType(MessageType.NOTIFICATION);
      message.setUserId(user.getUserId());
      message.setExpirationTime(LocalDateTime.now().plusMonths(1));
      messageService.create(message);
    }
  }

  protected void downloadById(ProductBridge productBridge,
      ProductMallMappingDownloadBO downloadBO) {
    LOGGER.info("店铺：{}通过ID：{}下载铺货", downloadBO.getStore().getStoreName(),
        downloadBO.getMallProductId());
    ProductQueryRequest request = buildProductQueryRequest(downloadBO);
    ProductQueryResponse response = productBridge.queryProductById(request);
    process(downloadBO, response.getMappings(), null);
  }

  protected void downloadByCode(ProductBridge productBridge,
      ProductMallMappingDownloadBO downloadBO) {
    LOGGER.info("店铺：{}通过Code：{}下载铺货", downloadBO.getStore().getStoreName(),
        downloadBO.getMallProductOutCode());
    ProductQueryRequest request = buildProductQueryRequest(downloadBO);
    ProductQueryResponse response = productBridge.queryProductByCode(request);
    process(downloadBO, response.getMappings(), null);

  }

  protected void downloadAll(ProductBridge productBridge, ProductMallMappingDownloadBO downloadBO) {
    LOGGER.info("店铺：{}全店铺货，状态：{}", downloadBO.getStore().getStoreName(),
        downloadBO.getMallProductStatus());
    ProductQueryRequest request = buildProductQueryRequest(downloadBO);
    ProductQueryResponse response = productBridge.queryProduct(request);
    if (!response.isSuccess()) {
      LOGGER.error("下载【{}】铺货失败，原因：{}", downloadBO.getStore().getStoreName(), response.getResult());
    }
    if (Assert.isEmpty(response.getMappings())) {
      LOGGER.error("下载【{}】铺货,未找到数据", downloadBO.getStore().getStoreName());
      return;
    }
    LOGGER
        .info("店铺：{}全店铺货第1页，{}条数据", downloadBO.getStore().getStoreName(),
            response.getMappings().size());
    Map<String, Long> map = productMallMappingService.listStoreMapping(downloadBO.getStoreId());
    LOGGER.info("店铺：{}已存在全店铺货{}条", downloadBO.getStore().getStoreName(), map.size());
    process(downloadBO, response.getMappings(), map);
    while (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      try {
        LOGGER.info("店铺：{}全店铺货第{}页", downloadBO.getStore().getStoreName(), request.getPage());
        response = productBridge.queryProduct(request);
        process(downloadBO, response.getMappings(), map);
      } catch (Exception ex) {
        LOGGER.error("下载{}店铺第{}页商品出错！{}", downloadBO.getStore().getStoreName(), request.getPage(),
            ex.getMessage());
      }
    }
  }

  protected void process(ProductMallMappingDownloadBO downloadBO, List<ProductMallMapping> mappings,
      Map<String, Long> oldMap) {
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("下载参数：{}，数据：{}", JsonUtil.toJson(downloadBO), JsonUtil.toJson(mappings));
    }
    if (mappings != null && !mappings.isEmpty()) {
      pretreatment(downloadBO, mappings, oldMap);
      save(downloadBO, mappings);
    }
  }

  private ProductQueryRequest buildProductQueryRequest(ProductMallMappingDownloadBO downloadBO) {
    ProductQueryRequest request = new ProductQueryRequest(downloadBO.getStore());
    request.setCode(downloadBO.getMallProductOutCode());
    request.setId(downloadBO.getMallProductId());
    request.setBeginTime(downloadBO.getBeginTime());
    request.setEndTime(downloadBO.getEndTime());
    request.setMallProductStatus(downloadBO.getMallProductStatus());
    request.setPage(1);
    return request;
  }


  private void save(ProductMallMappingDownloadBO downloadBO, List<ProductMallMapping> list) {
    List<ProductMallMapping> insertList = new ArrayList<>(list.size());
    List<ProductMallMapping> updateList = new ArrayList<>(list.size());
    list.forEach(mapping -> {
      if (mapping.getProductMallMappingId() != null) {
        updateList.add(mapping);
      } else {
        insertList.add(mapping);
      }
    });
    if (!insertList.isEmpty()) {
      productMallMappingService.batchCreate(insertList);
    }
    if (!updateList.isEmpty()) {
      productMallMappingService.batchModify(updateList);
    }
    if (Assert.isTrue(downloadBO.isUploadStockAfterDownload())) {
      list.forEach(x -> {
        if (!Assert.isNull(x.getSkuId())) {
          mqProducer.send(
              new StockChangedMessage(null, x.getSkuId(), x.getStoreId(),
                  downloadBO.getOperator(), "铺货下载"));
        }
      });
    }
  }

  /**
   * 预处理，检查重复、设置覆盖.
   */
  protected void pretreatment(ProductMallMappingDownloadBO downloadBO,
      List<ProductMallMapping> list, Map<String, Long> oldMap) {
    Store store = downloadBO.getStore();
    for (ProductMallMapping mapping : list) {
      mapping.setStoreId(store.getStoreId());
      mapping.setStoreName(store.getStoreName());
      matchOldMapping(downloadBO, mapping, oldMap);
      matchSku(mapping);
    }
  }

  /**
   * 匹配旧的铺货.
   */
  protected void matchOldMapping(ProductMallMappingDownloadBO downloadBO,
      ProductMallMapping mapping, Map<String, Long> oldMap) {
    ProductMallMapping old;
    if (oldMap != null) {
      if (oldMap.containsKey(mapping.getUniqueId())) {
        mapping.setProductMallMappingId(oldMap.get(mapping.getUniqueId()));
      }
    } else {
      old = getOld(mapping);
      if (old != null) {
        mapping.setProductMallMappingId(old.getProductMallMappingId());
      }
    }
    if (!Assert.isNull(mapping.getProductMallMappingId())) {
      if (Assert.isTrue(downloadBO.isCover())) {
        mapping.setAutoListing(Assert.isTrue(downloadBO.isAutoListing()));
        mapping.setAutoDelisting(Assert.isTrue(downloadBO.isAutoDelisting()));
        mapping.setAutoUpload(Assert.isTrue(downloadBO.isAutoUpload()));
      }
    } else {
      mapping.setManual(false);
      mapping.setMatched(false);
      mapping.setCombination(false);
      mapping.setAutoUpload(Assert.isTrue(downloadBO.isAutoUpload()));
      mapping.setAutoListing(Assert.isTrue(downloadBO.isAutoListing()));
      mapping.setAutoDelisting(Assert.isTrue(downloadBO.isAutoDelisting()));
    }
  }


  /**
   * 匹配系统SKU. 如果已经手工关联了就不再匹配商品
   */
  protected void matchSku(ProductMallMapping mapping) {
    if (!Assert.isTrue(mapping.isManual())) {
      if (!Assert.isEmpty(mapping.getMatchCode())) {
        ProductSku sku = productService.getEffectiveByCode(mapping.getMatchCode());
        if (sku != null) {
          mapping.setMatched(true);
          mapping.setProductId(sku.getProductId());
          mapping.setProductCode(sku.getProductCode());
          mapping.setProductName(sku.getProductName());
          mapping.setSkuId(sku.getSkuId());
          mapping.setSkuCode(sku.getSkuCode());
          mapping.setSkuName(sku.getSkuName());
          mapping.setBrandName(sku.getProduct().getBrandName());
          mapping.setCombination(sku.isCombination());
        }
      }
    }
  }

  protected ProductMallMapping getOld(ProductMallMapping mapping) {
    ProductMallMapping eg = new ProductMallMapping();
    eg.setUniqueId(mapping.getUniqueId());
    return productMallMappingService.getByExample(eg);
  }
}
