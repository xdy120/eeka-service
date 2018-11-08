package com.greatonce.oms.biz.impl.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.ProductBridge;
import com.greatonce.oms.dao.product.ProductMallMappingDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MarketingType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.query.product.ProductMallMappingQuery;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.ProductMallMappingLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * ProductMallMapping <br/> 铺货关系
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ProductMallMappingServiceImpl extends
    AbstractService<ProductMallMapping, ProductMallMappingQuery> implements
    ProductMallMappingService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.PRODUCT_MALL_MAPPING);
  private static final ProductMallMappingLogger MAPPING_LOGGER = OmsLoggerFactory
      .getProductMallMappingLogger();
  @Resource
  private IdGenerator productMallMappingIdGenerator;
  @Autowired
  private ProductMallMappingDao dao;
  @Autowired
  private MessageExporter messageExporter;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  @Override
  protected QueryDao<ProductMallMapping, ProductMallMappingQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return productMallMappingIdGenerator;
  }

  @Override
  public int create(ProductMallMapping record) {
    initDefaultValue(record);
    int count = insert(record);
    MAPPING_LOGGER.log(record);
    return count;
  }

  @Override
  public int modify(ProductMallMapping record) {
    int count = update(record);
    MAPPING_LOGGER.log(record);
    BIZ_LOGGER.log(record.getProductMallMappingId(), BizLogger.UPDATE,
        "设置状态：关联：{}, 自动上架：{}, 自动下架：{}， 自动上传库存：{}",
        record.isMatched(), record.isAutoListing(),
        record.isAutoDelisting(), record.isAutoUpload());
    return count;
  }

  @Override
  public List<ProductMallMapping> listMapping(Long skuId, Long storeId) {
    return listMapping(Collections.singleton(skuId), storeId);
  }

  @Override
  public List<ProductMallMapping> listMapping(Collection<Long> skuIds, Long storeId) {
    Set<Long> storeIds = null;
    if (!Assert.isNull(storeId)) {
      storeIds = Collections.singleton(storeId);
    }
    return listMapping(skuIds, storeIds);
  }

  @Override
  public List<ProductMallMapping> listMapping(Collection<Long> skuIds, Collection<Long> storeIds) {
    Assert.notNull(skuIds, "skuId不能为空");
    Map<String, Object> map = new HashMap<>();
    map.put("storeIds", storeIds);
    if (skuIds.size() > 500) {
      final int size = 500;
      final int pages = (skuIds.size() + size - 1) / size;
      List<ProductMallMapping> list = new ArrayList<>(size);
      for (int i = 0; i < pages; i++) {
        Set<Long> ids = skuIds.stream().skip(i * size).limit(size).collect(Collectors.toSet());
        map.put("skuIds", ids);
        list.addAll(dao.listFull(map));
      }
      return list;
    } else {
      map.put("skuIds", skuIds);
      return dao.listFull(map);
    }
  }

  @Override
  public ProductMallMapping getByMallSkuOutCode(String skuCode, Long storeId) {
    ProductMallMapping eg = new ProductMallMapping();
    eg.setStoreId(storeId);
    eg.setMallSkuOutCode(skuCode);
    return getByExample(eg);
  }

  @Override
  public Map<String, Long> listStoreMapping(Long storeId) {
    List<ProductMallMapping> mappings = dao.listStoreMapping(storeId);
    Map<String, Long> map = new HashMap<>(mappings.size());
    for (ProductMallMapping mapping : mappings) {
      map.put(mapping.getUniqueId(), mapping.getProductMallMappingId());
    }
    return map;
  }

  @Override
  public List<ProductMallMapping> listAutoUploadMapping(Long storeId) {
    return dao.listAutoUploadMapping(storeId);
  }

  @Override
  public void beginMarketing(Long storeId, Long marketingId, MarketingType marketingType,
      boolean autoUpload, Long skuId, String mallProductId) {
    ProductMallMapping mapping = new ProductMallMapping();
    mapping.setMallProductId(mallProductId);
    mapping.setAutoUpload(autoUpload);
    mapping.setMarketingId(marketingId);
    mapping.setMarketingType(marketingType);
    mapping.setStoreId(storeId);
    mapping.setSkuId(skuId);

    dao.beginMarketing(mapping);
  }

  @Override
  public void endMarketing(Long storeId, boolean autoUpload, Long skuId, String mallProductId) {
    ProductMallMapping mapping = new ProductMallMapping();
    mapping.setMallProductId(mallProductId);
    mapping.setAutoUpload(autoUpload);
    mapping.setStoreId(storeId);
    mapping.setSkuId(skuId);
    dao.endMarketing(mapping);
  }

  @Override
  public void export(String fileName, ProductMallMappingQuery productMallMappingQuery) {
    ExcelHeaderCollection<ProductMallMapping> headers = new ExcelHeaderCollection<>();
    headers.add("店铺", ProductMallMapping::getStoreName);
    headers.add("平台商品ID", ProductMallMapping::getMallProductId);
    headers.add("商家商品编码", ProductMallMapping::getMallProductOutCode);
    headers.add("商家规格编码", ProductMallMapping::getMallSkuOutCode);
    headers.add("平台商品名称", ProductMallMapping::getMallProductName);
    headers.add("平台库存", x -> FormatUtil.formatInteger(x.getMallSkuQuantity()));
    headers.add("平台价格", x -> FormatUtil.formatDouble(x.getMallSkuPrice()));
    headers.add("平台状态", x -> FormatUtil.formatEnum(x.getMallProductStatus()));
    messageExporter.exportExcel(this::listPage, headers, productMallMappingQuery, fileName);
  }

  @Override
  public void setDropShipping(ProductMallMapping mapping) {
    dropShippingChange(mapping, true);
  }

  @Override
  public void cancelDropShipping(ProductMallMapping mapping) {
    dropShippingChange(mapping, false);
  }

  @Override
  public String getMallProductUrl(ProductMallMapping mapping) {
    Store store = storeService.getByKey(mapping.getStoreId());
    ProductBridge productBridge = mallBridgeFactory.getProductBridge(store.getMallType());
    String matchCode;
    switch (store.getMallType()) {
      case TMALL:
        matchCode = mapping.getMallProductId();
        break;
      case TAOBAO:
        matchCode = mapping.getMallProductId();
        break;
      case TAOBAO_FX:
        matchCode = mapping.getMallProductId();
        break;
      case JD:
        matchCode = mapping.getMallSkuId();
        break;
      default:
        matchCode = mapping.getMallProductId();
    }
    return productBridge.getMallProductUrl(matchCode);
  }

  private void dropShippingChange(ProductMallMapping mapping, Boolean change) {
    mapping.setDropShipping(change);
    update(mapping);
    BIZ_LOGGER.log(mapping.getProductMallMappingId(), BizLogger.UPDATE, "设置代发为：{}", change);
  }

  @Override
  public int batchCreate(Collection<? extends ProductMallMapping> collection) {
    collection.forEach(this::initDefaultValue);
    int count = getTransactionTemplate().executeWithResult(() -> insertBatch(collection));
    collection.forEach(MAPPING_LOGGER::log);
    return count;
  }

  @Override
  public int batchModify(Collection<? extends ProductMallMapping> collection) {
    int count = getTransactionTemplate().executeWithResult(() -> updateBatch(collection));
    collection.forEach(MAPPING_LOGGER::log);
    return count;
  }

  @Override
  public int remove(ProductMallMapping entity) {
    if (Assert.isTrue(entity.isDropShipping())
        || entity.getMarketingType() == MarketingType.ACTIVITY
        || entity.getMarketingType() == MarketingType.PRE_SELL) {
      throw new OmsException("该铺货关系设置了代发、活动报名或预售，不可删除");
    }
    return super.remove(entity);
  }
}