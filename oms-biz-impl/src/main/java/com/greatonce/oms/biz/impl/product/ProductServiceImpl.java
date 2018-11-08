package com.greatonce.oms.biz.impl.product;

import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.CompanyService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.product.ProductAttributeService;
import com.greatonce.oms.biz.product.ProductCategoryService;
import com.greatonce.oms.biz.product.ProductService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.purchase.SupplierService;
import com.greatonce.oms.dao.product.ProductDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Company;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.product.ProductSyncStatus;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.enums.purchase.SupplierStatus;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductAttribute;
import com.greatonce.oms.domain.product.ProductCategory;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.purchase.Supplier;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.query.product.ProductAttributeQuery;
import com.greatonce.oms.query.product.ProductQuery;
import com.greatonce.oms.query.purchase.SupplierQuery;
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
 * 商品.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class ProductServiceImpl extends AbstractEnableService<Product, ProductQuery> implements
    ProductService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.PRODUCT);
  private static final String CACHE_NAME = "PRODUCT";

  @Autowired
  private ProductDao dao;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private ProductCategoryService productCategoryService;
  @Autowired
  private CompanyService companyService;
  @Autowired
  private SupplierService supplierService;
  @Autowired
  private ProductAttributeService productAttributeService;
  @Resource
  private IdGenerator productIdGenerator;
  @Autowired
  private MessageExporter messageExporter;

  @Override
  protected QueryDao<Product, ProductQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.productIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(Product product) {
    super.initDefaultValue(product);
    product.setSystem(false);
    if (Assert.isNull(product.isEnable())) {
      product.setEnable(true);
    }
    product.getSkus().forEach(x -> {
      x.setProductId(product.getProductId());
      x.setProductCode(product.getProductCode());
      x.setProductName(product.getProductName());
    });
  }

  @Override
  public int create(Product product) {
    initDefaultValue(product);
    int count = getTransactionTemplate().executeWithResult(() -> {
      productSkuService.batchCreate(product.getSkus());
      return insert(product);
    });
    BIZ_LOGGER.log(product.getProductId(), "新增");
    return count;
  }


  @Override
  @CacheEvict(value = CACHE_NAME, key = "'ID_'+#productId+'*'")
  public void modifySkuInfo(Long productId, ProductSku info) {
    final List<ProductSku> productSkus = productSkuService.listByProductId(productId);
    for (ProductSku sku : productSkus) {
      sku.setCostPrice(info.getCostPrice());
      sku.setMarkedPrice(info.getMarkedPrice());
      sku.setPurchasePrice(info.getPurchasePrice());
      sku.setSellingPrice(info.getSellingPrice());
      sku.setDistributionPrice(info.getDistributionPrice());
      sku.setLogisticsInsurance(info.getLogisticsInsurance());
      sku.setVipPrice(info.getVipPrice());
      sku.setQuantityOfBox(info.getQuantityOfBox());
      sku.setVolume(info.getVolume());
      sku.setWeight(info.getWeight());
      sku.setPrepackage(info.isPrepackage());
    }
    productSkuService.batchModify(productSkus);
    BIZ_LOGGER.log(productId, "批量修改规格信息");
  }

  @Override
  public boolean exists(String productCode) {
    return dao.exists(productCode);
  }

  @Override
  public void importProduct(List<Map<String, String>> list) {
    Map<String, Product> productMap = new HashMap<>(list.size() / 10);
    Map<Product, Map<String, ProductSku>> productSkuMap = new HashMap<>(productMap.size());
    Map<String, ProductSku> skuMap;
    Product product;
    ProductSku sku;
    ProductCategory category;
    Supplier supplier;
    Company company;
    List<Company> companies = companyService.list(null);
    SupplierQuery supplierQuery = new SupplierQuery();
    supplierQuery.setStatus(SupplierStatus.AUDITED);
    List<Supplier> suppliers = supplierService.list(supplierQuery);
    ProductAttributeQuery productAttributeQuery = new ProductAttributeQuery();
    productAttributeQuery.setSystem(false);
    List<ProductAttribute> attributes = productAttributeService.list(productAttributeQuery);
    List<ProductCategory> categories = productCategoryService.list(null);
    Map<String, Company> companyMap = CollectionUtil.listToMap(companies, Company::getCompanyName);
    Map<String, Supplier> supplierMap = CollectionUtil
        .listToMap(suppliers, Supplier::getSupplierName);
    Map<String, ProductAttribute> attributeMap = CollectionUtil
        .listToMap(attributes, ProductAttribute::getAttributeName);
    Map<String, ProductCategory> categoryMap = CollectionUtil
        .listToMap(categories, ProductCategory::getProductCategoryName);
    for (Map<String, String> map : list) {
      String productCode = map.get("商品编码");
      String skuCode = map.get("规格编码");
      if (productMap.containsKey(productCode)) {
        product = productMap.get(productCode);
      } else {
        product = new Product();
        product.setEnable(false);
        product.setSystem(false);
        product.setProductSyncStatus(ProductSyncStatus.NONE);
        product.setProductCode(productCode);
        product.setProductName(map.get("商品名称"));
        product.setBrandCode(map.get("品牌编码"));
        product.setBrandName(map.get("品牌名称"));
        product.setBarcode(map.get("条码"));
        product.setUnit(map.get("单位"));
        product.setListingYear(map.get("年份"));
        product.setSeason(map.get("季节"));
        product.setSingleDelivery("是".equals(map.get("单个发货")));
        product.setGift("是".equals(map.get("赠品")));
        product.setProductType(
            ProductType.VIRTUAL.caption().equals(map.get("商品类型")) ? ProductType.VIRTUAL
                : ProductType.PHYSICAL);
        if (!Assert.isEmpty(map.get("保质期"))) {
          product.setWarrantyPeriod(Integer.parseInt(map.get("保质期")));
        }
        if (!Assert.isEmpty(map.get("公司"))) {
          company = companyMap.get(map.get("公司"));
          if (company != null) {
            product.setCompanyId(company.getCompanyId());
            product.setCompanyName(company.getCompanyName());
          }
        }
        if (!Assert.isEmpty(map.get("供应商"))) {
          supplier = supplierMap.get(map.get("供应商"));
          if (supplier != null) {
            product.setSupplierId(supplier.getSupplierId());
            product.setSupplierName(supplier.getSupplierName());
          }
        }
        if (!Assert.isEmpty(map.get("商品分类"))) {
          category = categoryMap.get(map.get("商品分类"));
          if (category != null) {
            product.setProductCategoryId(category.getProductCategoryId());
          }
        }
        if (!Assert.isEmpty(map.get("上新日期"))) {
          product.setListingDate(DateTimeUtil.parserLocalDate(map.get("上新日期")));
        }
        fillAttribute(product, map, attributeMap);
        product.setSkus(new ArrayList<>(10));
        productMap.put(productCode, product);
        productSkuMap.put(product, new HashMap<>(10));
      }
      skuMap = productSkuMap.get(product);
      if (!skuMap.containsKey(skuCode)) {
        sku = new ProductSku();
        sku.setSkuCode(skuCode);
        sku.setSkuName(map.get("规格名称"));
        sku.setCombination(false);
        sku.setGiftBox(false);
        sku.setLockStock(false);
        sku.setEnable(true);
        sku.setProductCode(productCode);
        sku.setProductName(product.getProductName());
        sku.setColor(map.get("颜色"));
        sku.setSize(map.get("尺码"));
        sku.setBarcode(map.get("条码"));
        sku.setCostPrice(ConvertUtil.toDouble(map.get("成本价"), 0D));
        sku.setMarkedPrice(ConvertUtil.toDouble(map.get("吊牌价"), 0D));
        sku.setPurchasePrice(ConvertUtil.toDouble(map.get("采购价"), 0D));
        sku.setSellingPrice(ConvertUtil.toDouble(map.get("销售价"), 0D));
        sku.setDistributionPrice(ConvertUtil.toDouble(map.get("分销价"), 0D));
        sku.setLogisticsInsurance(ConvertUtil.toDouble(map.get("物流保价"), 0D));
        sku.setWeight(ConvertUtil.toDouble(map.get("重量"), 0D));
        sku.setVolume(ConvertUtil.toDouble(map.get("体积"), 0D));
        sku.setQuantityOfBox(ConvertUtil.toInt(map.get("箱规"), 0));
        product.getSkus().add(sku);
        skuMap.put(sku.getSkuCode(), sku);
      }
    }
    batchCreate(productMap.values());
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'CODE_'+#productCode")
  public Product getProductByCode(String productCode) {
    return dao.getByCode(productCode);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'ID_'+#id")
  public Product getByKey(Long id) {
    return super.getByKey(id);
  }

  @Override
  @ProductCacheEvict
  public int enable(Product product) {
    List<Long> skuIds = productSkuService.listSkuIdsByProductId(product.getProductId());
    skuIds.forEach(id -> getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, id, EventType.ENABLED)));
    return super.enable(product);
  }

  @Override
  @ProductCacheEvict
  public int disable(Product entity) {
    List<Long> skuIds = productSkuService.listSkuIdsByProductId(entity.getProductId());
    skuIds.forEach(id -> getMqProducer()
        .send(new GeneralMessage(OmsModule.PRODUCT_SKU, id, EventType.DISABLED)));
    return super.disable(entity);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'ID_'+#product.productId"),
      @CacheEvict(value = CACHE_NAME, key = "'CODE_'+#product.productCode")
  })
  public int modify(Product product) {
    final List<ProductSku> skus = productSkuService.listByProductId(product.getProductId());
    for (ProductSku sku : skus) {
      sku.setProductCode(product.getProductCode());
      sku.setProductName(product.getProductName());
    }
    int count = getTransactionTemplate().executeWithResult(() -> {
      for (ProductSku sku : skus) {
        productSkuService.modify(sku);
      }
      return update(product);
    });
    getBizLogger().log(product.getProductId(), BizLogger.UPDATE);
    return count;
  }

  @Override
  public void importProductPrice(List<Map<String, String>> list) {
    if (!Assert.isEmpty(list)) {
      List<ProductSku> skus = new ArrayList<>(list.size());
      for (Map<String, String> map : list) {
        if (Assert.isEmpty(map.get("商品编码")) && Assert.isEmpty(map.get("规格编码"))) {
          throw new OmsException("商品编码或规格编码不能都为空！");
        }
        if (!Assert.isEmpty(map.get("吊牌价")) && !Assert.isDecimal(map.get("吊牌价"))) {
          throw new OmsException("吊牌价输入错误！");
        }
        if (!Assert.isEmpty(map.get("唯品价")) && !Assert.isDecimal(map.get("唯品价"))) {
          throw new OmsException("唯品价输入错误！");
        }
        if (!Assert.isEmpty(map.get("销售价")) && !Assert.isDecimal(map.get("销售价"))) {
          throw new OmsException("销售价输入错误！");
        }
        if (!Assert.isEmpty(map.get("成本价")) && !Assert.isDecimal(map.get("成本价"))) {
          throw new OmsException("成本价输入错误！");
        }
        if (!Assert.isEmpty(map.get("采购价")) && !Assert.isDecimal(map.get("采购价"))) {
          throw new OmsException("采购价输入错误！");
        }
        if (!Assert.isEmpty(map.get("分销价")) && !Assert.isDecimal(map.get("分销价"))) {
          throw new OmsException("分销价输入错误！");
        }
        if (!Assert.isEmpty(map.get("物流保价")) && !Assert.isDecimal(map.get("物流保价"))) {
          throw new OmsException("物流保价输入错误！");
        }
        ProductSku resultSku = new ProductSku();
        resultSku.setProductCode(Assert.isEmpty(map.get("商品编码")) ? null : map.get("商品编码"));
        resultSku.setSkuCode(Assert.isEmpty(map.get("规格编码")) ? null : map.get("规格编码"));
        resultSku.setCostPrice(
            Assert.isEmpty(map.get("成本价")) ? null : ConvertUtil.toDouble(map.get("成本价")));
        resultSku.setDistributionPrice(
            Assert.isEmpty(map.get("分销价")) ? null : ConvertUtil.toDouble(map.get("分销价")));
        resultSku.setMarkedPrice(
            Assert.isEmpty(map.get("吊牌价")) ? null : ConvertUtil.toDouble(map.get("吊牌价")));
        resultSku.setPurchasePrice(
            Assert.isEmpty(map.get("采购价")) ? null : ConvertUtil.toDouble(map.get("采购价")));
        resultSku.setSellingPrice(
            Assert.isEmpty(map.get("销售价")) ? null : ConvertUtil.toDouble(map.get("销售价")));
        resultSku.setVipPrice(
            Assert.isEmpty(map.get("唯品价")) ? null : ConvertUtil.toDouble(map.get("唯品价")));
        resultSku.setLogisticsInsurance(
            Assert.isEmpty(map.get("物流保价")) ? null : ConvertUtil.toDouble(map.get("物流保价")));
        skus.add(resultSku);
      }
      if (!Assert.isEmpty(skus)) {
        getTransactionTemplate().execute(() -> {
          productSkuService.batchModifyByCode(skus);
        });
      } else {
        throw new OmsException("导入内容为空！");
      }
    }
  }

  @Override
  public void exportSku(String fileName, ProductQuery productQuery) {
    ExcelHeaderCollection<Product> headers = new ExcelHeaderCollection<>();
    headers.add("商品编码", Product::getProductCode);
    headers.add("商品名称", Product::getProductName);
    headers.add("商品条码", Product::getBarcode);
    headers.add("商品类型", x -> FormatUtil.formatEnum(x.getProductType()));
    headers.add("商品简介", Product::getProductShortName);
    headers.add("品牌", Product::getBrandName);
    headers.add("创建时间", x -> DateTimeUtil.format(x.getCreatedTime()));
    headers.add("修改时间", x -> DateTimeUtil.format(x.getModifiedTime()));
    headers.add("推送状态", x -> FormatUtil.formatEnum(x.getProductSyncStatus()));
    headers.add("独立发货", x -> FormatUtil.formatBoolean(x.isSingleDelivery()));
    headers.add("赠品", x -> FormatUtil.formatBoolean(x.isGift()));
    headers.add("状态", x -> FormatUtil.formatEnabled(x.isEnable()));
    messageExporter.exportExcel(this::listPage, headers, productQuery, fileName);
  }

  @Override
  public void noticeWms(Product product) {
    final List<ProductSku> productSkus = productSkuService.listByProductId(product.getProductId());
    productSkus.forEach(sku -> {
      sku.setProduct(product);
      productSkuService.noticeWms(sku);
    });
    product.setProductSyncStatus(ProductSyncStatus.ALL);
    update(product);
  }

  private void fillAttribute(Product product, Map<String, String> map,
      Map<String, ProductAttribute> attributeMap) {
    JSONObject json = new JSONObject();
    ProductAttribute attribute;
    boolean hasValue = false;
    if (Assert.isEmpty(attributeMap)) {
      return;
    }
    for (String key : map.keySet()) {
      if (attributeMap.containsKey(key) && !Assert.isEmpty(map.get(key))) {
        attribute = attributeMap.get(key);
        json.put(String.valueOf(attribute.getAttributeId()), map.get(key));
        hasValue = true;
      }
    }
    if (hasValue) {
      product.setAttributesJson(JsonUtil.toJson(json));
    }
  }

  @Override
  public int batchCreate(Collection<? extends Product> list) {
    List<ProductSku> skus = new ArrayList<>(list.size() * 10);
    for (Product product : list) {
      initDefaultValue(product);
      if (product.getSkus() != null) {
        for (ProductSku productSku : product.getSkus()) {
          productSku.setProductId(product.getProductId());
        }
        skus.addAll(product.getSkus());
      }
    }
    int count = getTransactionTemplate().executeWithResult(() -> {
      if (skus.size() > 0) {
        productSkuService.batchCreate(skus);
      }
      return insertBatch(list);

    });
    for (Product product : list) {
      BIZ_LOGGER.log(product.getProductId(), "新增");
    }
    return count;
  }

  @Override
  public int batchModify(Collection<? extends Product> collection) {
    return getTransactionTemplate().executeWithResult(() -> updateBatch(collection));
  }

  @Caching(evict = {
      @CacheEvict(value = CACHE_NAME, key = "'ID_'+#product.productId"),
      @CacheEvict(value = CACHE_NAME, key = "'CODE_'+#product.productCode")
  })
  @Target({ElementType.METHOD, ElementType.TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Inherited
  @interface ProductCacheEvict {

  }
}