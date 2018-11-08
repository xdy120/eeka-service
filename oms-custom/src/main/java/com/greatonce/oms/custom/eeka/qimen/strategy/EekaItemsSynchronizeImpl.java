package com.greatonce.oms.custom.eeka.qimen.strategy;

import com.alibaba.fastjson.JSON;
import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.DataDictService;
import com.greatonce.oms.biz.product.ProductAttributeService;
import com.greatonce.oms.biz.product.ProductCategoryService;
import com.greatonce.oms.biz.product.ProductService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.QimenItemsSynchronizeStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsItemsSynchronizeRequest;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.domain.base.DataDict;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.enums.product.ProductSyncStatus;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductAttribute;
import com.greatonce.oms.domain.product.ProductCategory;
import com.greatonce.oms.domain.product.ProductSku;
import com.qimen.api.response.ItemsSynchronizeResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@EekaApiCondition
public class EekaItemsSynchronizeImpl implements QimenItemsSynchronizeStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(EekaItemsSynchronizeImpl.class);

  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private ProductAttributeService productAttributeService;
  @Autowired
  private ProductCategoryService productCategoryService;
  @Autowired
  private DataDictService dataDictService;
  @Autowired
  private DataDictItemService dataDictItemService;
  @Autowired
  private ManualTransactionTemplate transactionTemplate;

  @Override
  public ItemsSynchronizeResponse synchronize(@RequestBody OmsItemsSynchronizeRequest request) {
    try {
      List<ProductSku> productSkuList = new ArrayList<>();
      List<ProductSku> productSkuNewList = new ArrayList<>();
      Map<String, String> attributeMap = new HashMap<>();
      //OmsItemsSynchronizeRequest.ExtendsMap map = new OmsItemsSynchronizeRequest.ExtendsMap();
      Map<String,String> map = request.getExtendProps();
      ProductAttribute productAttributeQuery = new ProductAttribute();
      productAttributeQuery.setAttributeName("系列");
      addProductAttribute(attributeMap, map.get("department"), productAttributeQuery);
      productAttributeQuery.setAttributeName("主题");
      addProductAttribute(attributeMap, map.get("themeCode"), productAttributeQuery);
      productAttributeQuery.setAttributeName("洗涤方式");
      StringBuffer coat = new StringBuffer("");
      if(!Assert.isEmpty(map.get("washingTypeCoat"))){
        String washingTypeCoat = getDictName("洗涤类型", map.get("washingTypeCoat"));
        coat.append(Assert.isNull(washingTypeCoat) ? "" : washingTypeCoat+";");
      }
      if(!Assert.isEmpty(map.get("washWaterCoat"))){
        String washWaterCoat = getDictName("洗水",map.get("washWaterCoat"));
        coat.append(Assert.isNull(washWaterCoat) ? "" : washWaterCoat+";");
      }
      if(!Assert.isEmpty(map.get("bleachCoat"))){
        String bleachCoat = getDictName("漂白",map.get("bleachCoat"));
        coat.append(Assert.isNull(bleachCoat) ? "" : bleachCoat+";");
      }
      if(!Assert.isEmpty(map.get("naturalDryingCoat"))){
        String naturalDryingCoat = getDictName("自然干燥",map.get("naturalDryingCoat"));
        coat.append(Assert.isNull(naturalDryingCoat) ? "" : naturalDryingCoat+";");
      }
      if(!Assert.isEmpty(map.get("flippingDryingCoat"))){
        String naturalDryingCoat = getDictName("翻转干燥",map.get("flippingDryingCoat"));
        coat.append(Assert.isNull(naturalDryingCoat) ? "" : naturalDryingCoat+";");
      }
      if(!Assert.isEmpty(map.get("ironingCoat"))){
        String ironingCoat = getDictName("熨烫",map.get("ironingCoat"));
        coat.append(Assert.isNull(ironingCoat) ? "" : ironingCoat+";");
      }
      if(!Assert.isEmpty(map.get("textileMaintenanceCoat"))){
        String textileMaintenanceCoat = getDictName("专业纺织品维护",map.get("textileMaintenanceCoat"));
        coat.append(Assert.isNull(textileMaintenanceCoat) ? "" : textileMaintenanceCoat+";");
      }
      addProductAttribute(attributeMap, Assert.isEmpty(coat) ? "" : coat.toString(), productAttributeQuery);
      productAttributeQuery.setAttributeName("成分");
      addProductAttribute(attributeMap, map.get("styleComposition"), productAttributeQuery);
      productAttributeQuery.setAttributeName("私人定制");
      addProductAttribute(attributeMap, "1".equals(map.get("isPersonalTailor")) ? "是" : "否", productAttributeQuery);

      List<OmsItemsSynchronizeRequest.Item> items = new ArrayList<>();
      items.addAll(request.getItems());

      Product product = null;
      for (int i = 0; i < items.size(); i++) {
        OmsItemsSynchronizeRequest.Item item = items.get(i);
        product = productService.getProductByCode(item.getProduceCode());
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("商品信息：" + product != null ? JsonUtil.toJson(product) : "为空");
        }
        /**
         * 构造ProductSku信息
         */
        buildProductSkuInfo(productSkuList, item, product, map, productSkuNewList);
        if (product != null) {
          getProduct(productSkuList, item, product, request, JSON.toJSONString(attributeMap), map);
        } else {
          product = new Product();
          getProduct(productSkuList, item, product, request, JSON.toJSONString(attributeMap), map);
        }
      }
      /**
       * 产品存在，则修改产品信息
       */
      Product addProduct = product;
      transactionTemplate.execute(() -> {
        if (addProduct != null && addProduct.getProductId() != null) {
          addProduct.setSkus(productSkuList);
          productService.modify(addProduct);
          productSkuService.batchModify(productSkuList);
          if (!Assert.isEmpty(productSkuNewList)) {
            productSkuService.batchCreate(productSkuNewList);
          }
        } else {
          /**
           * 产品不存在的话，则新增产品
           */
          productService.create(addProduct);
        }
      });
      return QimenCustomResponseUtil
          .resultSuccessResponse(new ItemsSynchronizeResponse(), request.getOwnerCode());
    } catch (Exception ex) {
      LOGGER.error("商品资料同步失败", ex);
      return QimenCustomResponseUtil
          .resultFailureResponse(new ItemsSynchronizeResponse(), ex.getMessage());
    }

  }

  private void addProductAttribute(Map<String, String> attributeMap, String attributeName,
      ProductAttribute productAttributeQuery) {
    ProductAttribute resProductAttribute = productAttributeService
        .getByExample(productAttributeQuery);
    if (!Assert.isNull(resProductAttribute)) {
      attributeMap.put(String.valueOf(resProductAttribute.getAttributeId()), Assert.isEmpty(attributeName) ? null : attributeName);
    }
  }

  private void getProduct(List<ProductSku> productSkuList, OmsItemsSynchronizeRequest.Item item,
      Product product, OmsItemsSynchronizeRequest request, String attributJson,
      Map<String,String> map) {
    if (product.getProductCode() == null) {
      product.setCreatedTime(LocalDateTime.now());
//            product.setCompanyId(null);
//            product.setCompanyName(null);
      product.setEnable(true);
      product.setGift(false);
      product.setListingDate(LocalDate.now());
      product.setListingYear(map.get("year"));
      product.setProductSyncStatus(ProductSyncStatus.NONE);
      product.setProductType(ProductType.PHYSICAL);
      product.setSingleDelivery(false);
      product.setSystem(false);
      product.setWarrantyPeriod(0);
    }
    product.setSkus(productSkuList);
    product.setAttributesJson(attributJson);
    product.setBarcode(item.getBarCode());
    product.setBrandCode(item.getBrandCode());
    product.setBrandName(getDictName("品牌", item.getBrandCode()));
    product.setModifiedTime(LocalDateTime.now());
    ProductCategory productCategoryEg = new ProductCategory();
    String category = null;
    if (!Assert.isEmpty(map.get("smallCategory"))) {
      category = map.get("smallCategory");
    } else if (!Assert.isEmpty(map.get("midCategory"))) {
      category = map.get("midCategory");
    } else if (!Assert.isEmpty(map.get("grandCategory"))) {
      category = map.get("grandCategory");
    }
    productCategoryEg.setProductCategoryCode(category);
    ProductCategory productCategory = productCategoryService.getByExample(productCategoryEg);
    if (!Assert.isNull(productCategory)) {
      product.setProductCategoryId(productCategory.getProductCategoryId());
      product.setProductCategoryName(productCategory.getProductCategoryName());
    }
    product.setProductCode(item.getGoodsCode());
    product.setProductName(item.getItemName());
    product.setProductShortName(item.getItemName());
    product.setSeason(getDictName("季节", item.getSeasonCode()));
    product
        .setSupplierId(item.getSupplierCode() == null ? 0 : Long.valueOf(item.getSupplierCode()));
    product.setSupplierName(item.getSupplierName());
    product.setUnit(item.getUnit());
    product.setModifiedTime(LocalDateTime.now());
    product.setImageUrl(map.get("imageUrl"));
    product.setWaveband(getDictName("波段", map.get("band")));
  }

  private String getDictName(String dictName, String dictItemCode) {
    //根据代码获取对应名称
    String name = null;
    DataDict dataDictEg = new DataDict();
    dataDictEg.setDataDictName(dictName);
    DataDict dataDict = dataDictService.getByExample(dataDictEg);
    if (!Assert.isNull(dataDict)) {
      DataDictItem dataDictItemEg = new DataDictItem();
      dataDictItemEg.setDataDictId(dataDict.getDataDictId());
      dataDictItemEg.setDataDictItemCode(dictItemCode);
      DataDictItem dataDictItem = dataDictItemService.getByExample(dataDictItemEg);
      if (!Assert.isNull(dataDictItem)) {
        name = dataDictItem.getDataDictItemName();
      }
    }
    return name;
  }

  /**
   * 构造ProductSku信息
   */
  private void buildProductSkuInfo(List<ProductSku> productSkuList,
      OmsItemsSynchronizeRequest.Item item, Product product,
      Map<String,String> map, List<ProductSku> productSkuNewList) {
    ProductSku existSku = productSkuService.getEffectiveByCode(item.getItemCode());
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("商品信息：" + existSku != null ? JsonUtil.toJson(existSku) : "为空");
    }
    if (!Assert.isNull(existSku)) {
      /**
       * SKU存在，则需更新sku信息
       */
      getProductSku(item, product, existSku, map);
      productSkuList.add(existSku);
    } else {
      /**
       * SKU不存在，则新增SKU
       */
      ProductSku productSku = new ProductSku();
      productSku.setCreatedTime(LocalDateTime.now());
      productSku.setEnable(true);
      productSku.setCombination(false);
      productSku.setGiftBox(false);
      productSku.setLockStock(false);
      productSku.setPurchasePrice(0.00);
      productSku.setDistributionPrice(0.00);
      productSku.setLogisticsInsurance(0.00);
      productSku.setWeight(0.00);
      getProductSku(item, product, productSku, map);
      productSkuList.add(productSku);
      productSkuNewList.add(productSku);
    }
  }

  private void getProductSku(OmsItemsSynchronizeRequest.Item item, Product product,
      ProductSku productSku, Map<String,String> map) {
    if (product != null) {
      productSku.setProductId(product.getProductId());
      productSku.setProductCode(product.getProductCode());
      productSku.setProductName(product.getProductName());
    }
    productSku.setSkuCode(item.getItemCode());
    productSku.setModifiedTime(LocalDateTime.now());
    productSku.setSize(item.getSize());
    productSku.setColor(getDictName("颜色", item.getColor()));
    productSku.setBarcode(item.getBarCode());
    productSku
        .setSkuName((productSku.getColor() == null ? "" : productSku.getColor()) + " " + (
            productSku.getSize() == null ? "" : productSku.getSize()));
    productSku.setProductCode(item.getGoodsCode());
    productSku.setProductName(item.getItemName());
    productSku.setCostPrice(ConvertUtil.toDouble(item.getCostPrice(), 0));
    productSku.setSellingPrice(ConvertUtil.toDouble(item.getTagPrice(), 0));
    productSku.setMarkedPrice(ConvertUtil.toDouble(item.getTagPrice(), 0));
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }
}
