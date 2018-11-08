package com.greatonce.oms.biz.impl.product;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.product.ProductCombinationService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.bridge.wms.ProductBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.SkuCreateRequest;
import com.greatonce.oms.bridge.wms.response.SkuCreateResponse;
import com.greatonce.oms.dao.product.ProductSkuDao;
import com.greatonce.oms.domain.Constants;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.query.product.ProductSkuQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

/**
 * 商品规格.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class ProductSkuServiceImpl extends
    AbstractEnableService<ProductSku, ProductSkuQuery> implements ProductSkuService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.PRODUCT_SKU);
  private static final BizLogger PRODUCT_LOGGER = OmsLoggerFactory.getLogger(OmsModule.PRODUCT);
  private static final String CACHE_NAME = "PRODUCT_SKU";
  @Autowired
  private ProductSkuDao dao;
  @Resource
  private IdGenerator productSkuIdGenerator;
  @Autowired
  private ProductCombinationService productCombinationService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private MessageExporter messageExporter;

  @Override
  protected QueryDao<ProductSku, ProductSkuQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.productSkuIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(ProductSku entity) {
    super.initDefaultValue(entity);
    if (entity.isEnable() == null) {
      entity.setEnable(true);
    }
    if (entity.isPrepackage() == null) {
      entity.setPrepackage(false);
    }
  }

  @Override
  @SkuCacheEvict
  public int create(ProductSku sku) {
    int count = super.create(sku);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.CREATED));
    return count;
  }

  @Override
  public void importCombination(List<Map<String, String>> list) {
    Map<String, ProductSku> skuMap = new HashMap<>(list.size() / 10);
    Map<ProductSku, Map<String, ProductCombination>> skuCombinationMap = new HashMap<>(
        skuMap.size());
    Map<String, ProductCombination> combinationMap;
    ProductSku sku;
    ProductCombination combination;
    for (Map<String, String> map : list) {
      String code = map.get("套装编码");
      if (skuMap.containsKey(code)) {
        sku = skuMap.get(code);
      } else {
        sku = new ProductSku();
        sku.setProductId(Constants.COMBINATION_PRODUCT_ID);
        sku.setProductCode(Constants.COMBINATION_PRODUCT_CODE);
        sku.setProductName(Constants.COMBINATION_PRODUCT_NAME);
        sku.setSkuCode(code);
        sku.setSkuName(map.get("套装名称"));
        sku.setBarcode(map.get("条码"));
        sku.setEnable(false);
        sku.setCombination(true);
        sku.setGiftBox("是".equals(map.get("礼盒")));
        sku.setLockStock("是".equals(map.get("锁库存")));
        sku.setCostPrice(!Assert.isEmpty(map.get("成本价")) ? Double.parseDouble(map.get("成本价")) : 0D);
        sku.setMarkedPrice(
            !Assert.isEmpty(map.get("吊牌价")) ? Double.parseDouble(map.get("吊牌价")) : 0D);
        sku.setPurchasePrice(
            !Assert.isEmpty(map.get("采购价")) ? Double.parseDouble(map.get("采购价")) : 0D);
        sku.setSellingPrice(
            !Assert.isEmpty(map.get("销售价")) ? Double.parseDouble(map.get("销售价")) : 0D);
        sku.setDistributionPrice(
            !Assert.isEmpty(map.get("分销价")) ? Double.parseDouble(map.get("分销价")) : 0D);
        sku.setLogisticsInsurance(
            !Assert.isEmpty(map.get("物流保价")) ? Double.parseDouble(map.get("物流保价")) : 0D);
        sku.setWeight(!Assert.isEmpty(map.get("重量")) ? Double.parseDouble(map.get("重量")) : 0D);
        sku.setVolume(!Assert.isEmpty(map.get("体积")) ? Double.parseDouble(map.get("体积")) : 0D);
        if (!Assert.isEmpty(map.get("箱规"))) {
          sku.setQuantityOfBox(Integer.parseInt(map.get("箱规")));
        }
        sku.setDetails(new ArrayList<>(5));
        skuCombinationMap.put(sku, new HashMap<>(5));
        skuMap.put(code, sku);
      }
      String skuCode = map.get("规格编码");
      combinationMap = skuCombinationMap.get(sku);
      if (!combinationMap.containsKey(skuCode)) {
        ProductSku detailSku = getEffectiveByCode(skuCode);
        if (detailSku != null) {
          combination = new ProductCombination();
          combination.setProductId(detailSku.getProductId());
          combination.setProductCode(detailSku.getProductCode());
          combination.setProductName(detailSku.getProductName());
          combination.setSkuId(detailSku.getSkuId());
          combination.setSkuCode(detailSku.getSkuCode());
          combination.setSkuName(detailSku.getSkuName());
          combination
              .setQuantity(!Assert.isEmpty(map.get("数量")) ? Integer.parseInt(map.get("数量")) : 1);
          combinationMap.put(skuCode, combination);
          sku.getDetails().add(combination);
        }
      }
    }
    insertCombinationBatch(skuMap.values());
  }

  @Override
  public List<ProductSku> listFullInfo(ProductSkuQuery query) {
    return dao.listFullInfo(query);
  }

  @Override
  public PageList<ProductSku> listFullInfo(ProductSkuQuery query, int page, int pageSize) {
    return dao.listFullInfo(query, page, pageSize);
  }

  @Override
  public List<ProductSku> listEffectiveByProductId(Long productId) {
    ProductSkuQuery query = new ProductSkuQuery();
    query.setProductId(productId);
    query.setEnable(true);
    return list(query);
  }

  @Override
  public List<ProductSku> listEffectiveByProductCode(String productCode) {
    ProductSkuQuery query = new ProductSkuQuery();
    query.setProductCode(productCode);
    query.setEnable(true);
    return list(query);
  }

  @Override
  public List<ProductSku> listByProductId(Long productId) {
    ProductSkuQuery query = new ProductSkuQuery();
    query.setProductId(productId);
    return list(query);
  }

  @Override
  public List<ProductSku> listByProductCode(String productCode) {
    ProductSkuQuery query = new ProductSkuQuery();
    query.setProductCode(productCode);
    return list(query);
  }

  @Override
  public void exportCombination(String fileName, ProductSkuQuery productSkuQuery) {
    ExcelHeaderCollection<ProductSku> headers = new ExcelHeaderCollection<>();
    headers.add("套装编码", ProductSku::getSkuCode);
    headers.add("套装名称", ProductSku::getSkuName);
    headers.add("创建时间", x -> DateTimeUtil.format(x.getCreatedTime()));
    headers.add("修改时间", x -> DateTimeUtil.format(x.getModifiedTime()));
    headers.add("礼盒", x -> FormatUtil.formatBoolean(x.isGiftBox()));
    headers.add("销售价", x -> FormatUtil.formatDouble(x.getSellingPrice()));
    messageExporter.exportExcel(this, headers, productSkuQuery, fileName);
  }

  @Override
  public void noticeWms(ProductSku sku) {
    final Map<String, Warehouse> warehouseMap = warehouseService.listEffective();
    warehouseMap.forEach((k, v) -> {
      if (!Assert.isNull(v.getWmsApp())) {
        final ProductBridge productBridge = wmsBridgeFactory
            .getProductBridge(v.getWmsApp().getWmsType());

        SkuCreateRequest request = new SkuCreateRequest(v, sku);
        final SkuCreateResponse response = productBridge.createSku(request);
        if (response.isSuccess()) {
          PRODUCT_LOGGER
              .log(sku.getProductId(), sku.getSkuId(), BizLogger.NOTICE_WMS, "{}推送{}成功",
                  sku.getSkuCode(), v.getWarehouseName());
        } else {
          PRODUCT_LOGGER
              .log(sku.getProductId(), sku.getSkuId(), BizLogger.NOTICE_WMS, "{}推送{}失败：{}",
                  sku.getSkuCode(), v.getWarehouseName(), response.getMessage());
        }
      }
    });
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'CODE_'+#skuCode")
  public ProductSku getEffectiveByCode(String skuCode) {
    Assert.notNull(skuCode, "编码不能为空");
    ProductSkuQuery eg = new ProductSkuQuery();
    eg.setEnable(true);
    eg.setSkuCode(skuCode);
    List<ProductSku> productSkus = dao.listFullInfo(eg);
    if (!Assert.isEmpty(productSkus)) {
      return productSkus.get(0);
    } else {
      return null;
    }
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'ID_'+#skuId")
  public ProductSku getEffectiveById(Long skuId) {
    Assert.notNull(skuId, "规格ID不能为空！");
    ProductSkuQuery query = new ProductSkuQuery();
    query.setSkuId(skuId);
    query.setEnable(true);
    List<ProductSku> list = dao.listFullInfo(query);
    return Assert.isEmpty(list) ? null : list.get(0);
  }

  @Override
  @SkuCacheEvict
  public int enable(ProductSku sku) {
    int count = super.enable(sku);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.ENABLED));
    return count;
  }

  @Override
  @SkuCacheEvict
  public int disable(ProductSku sku) {
    int count = super.disable(sku);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.DISABLED));
    return count;
  }

  @Override
  @SkuCacheEvict
  public int modify(ProductSku sku) {
    int count = super.update(sku);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.MODIFIED));
    return count;
  }

  @Override
  @SkuCacheEvict
  public int remove(ProductSku sku) {
    int count = delete(sku.getSkuId());
    getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.REMOVED));
    return count;
  }

  @Override
  public int batchCreate(Collection<? extends ProductSku> collection) {
    List<GeneralMessage> messages = new ArrayList<>(collection.size());
    collection.forEach(sku -> {
      initDefaultValue(sku);
      messages.add(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.CREATED));
    });
    int count = getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
    getMqProducer().send(messages);
    return count;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int batchModify(Collection<? extends ProductSku> collection) {
    List<GeneralMessage> messages = new ArrayList<>(collection.size());
    collection.forEach(sku -> messages
        .add(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.MODIFIED)));
    int count = getTransactionTemplate().executeWithResult(() -> updateBatch(collection));
    getMqProducer().send(messages);
    return count;
  }

  //region 组合套装

  private void initCombinationDefaultValue(ProductSku sku) {
    sku.setSkuId(getIdGenerator().next());
    sku.setCombination(true);
    sku.setProductId(Constants.COMBINATION_PRODUCT_ID);
    sku.setProductCode(Constants.COMBINATION_PRODUCT_CODE);
    sku.setProductName(Constants.COMBINATION_PRODUCT_NAME);
    if (sku.isEnable() == null) {
      sku.setEnable(true);
    }
  }

  @Override
  public void insertCombinationBatch(Collection<ProductSku> skus) {
    skus.forEach(this::initCombinationDefaultValue);
    getTransactionTemplate().execute(() -> {
      for (ProductSku productSku : skus) {
        if (productSku.getDetails() != null) {
          productSku.getDetails().forEach(x -> x.setCombinationId(productSku.getSkuId()));
          productCombinationService.batchCreate(productSku.getDetails());
        }
      }
      super.insertBatch(skus);
    });
    for (ProductSku productSku : skus) {
      getMqProducer().send(
          new GeneralMessage(OmsModule.PRODUCT_SKU, productSku.getSkuId(), EventType.CREATED));
      BIZ_LOGGER.log(productSku.getSkuId(), "新增");
    }
  }

  @Override
  public void insertCombination(ProductSku sku) {
    initCombinationDefaultValue(sku);
    getTransactionTemplate().execute(() -> {
      if (sku.getDetails() != null) {
        for (ProductCombination combination : sku.getDetails()) {
          combination.setCombinationId(sku.getSkuId());
        }
        productCombinationService.batchCreate(sku.getDetails());
      }
      insert(sku);
    });
    getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.CREATED));
    BIZ_LOGGER.log(sku.getSkuId(), "新增");
  }

  @Override
  @SkuCacheEvict
  public void updateCombination(ProductSku sku) {
    update(sku);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, sku.getSkuId(), EventType.MODIFIED));
    BIZ_LOGGER.log(sku.getSkuId(), "修改");
  }

  @Override
  public boolean exists(String skuCode) {
    return dao.exists(skuCode);
  }
  //endregion


  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int batchModifyByCode(Collection<? extends ProductSku> skus) {
    int count = dao.updateByCode(skus);
    List<GeneralMessage> messages = new ArrayList<>(skus.size());
    skus.forEach(x -> messages
        .add(new GeneralMessage(OmsModule.PRODUCT_SKU, x.getSkuId(), EventType.MODIFIED)));
    getMqProducer().send(messages);
    return count;
  }

  @Override
  public List<Long> listSkuIdsByProductId(Long productId) {
    return dao.listSkuIdsByProductId(productId);
  }

  @Override
  public ProductSku getFullInfoById(Long skuId) {
    ProductSkuQuery query = new ProductSkuQuery();
    query.setSkuId(skuId);
    List<ProductSku> productSkus = dao.listFullInfo(query);
    if (Assert.isEmpty(productSkus)) {
      return null;
    } else {
      return productSkus.get(0);
    }
  }


  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'ID_'+#sku.skuId"),
      @CacheEvict(value = CACHE_NAME, key = "'CODE_'+#sku.skuCode")
  })
  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @interface SkuCacheEvict {

  }
}