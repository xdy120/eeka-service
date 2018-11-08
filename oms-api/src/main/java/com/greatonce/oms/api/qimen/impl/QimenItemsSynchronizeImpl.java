package com.greatonce.oms.api.qimen.impl;

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
public class QimenItemsSynchronizeImpl implements QimenItemsSynchronizeStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(QimenItemsSynchronizeImpl.class);

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
        buildProductSkuInfo(productSkuList, item, product, productSkuNewList);
        if (product != null) {
          getProduct(productSkuList, item, product, request, JSON.toJSONString(attributeMap));
        } else {
          product = new Product();
          getProduct(productSkuList, item, product, request, JSON.toJSONString(attributeMap));
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
      attributeMap.put(String.valueOf(resProductAttribute.getAttributeId()), attributeName);
    }
  }

  private void getProduct(List<ProductSku> productSkuList, OmsItemsSynchronizeRequest.Item item,
      Product product, OmsItemsSynchronizeRequest request, String attributJson) {
    if (product.getProductCode() == null) {
      product.setCreatedTime(LocalDateTime.now());
      product.setEnable(true);
      product.setGift(false);
      product.setListingDate(LocalDate.now());
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
      List<ProductSku> productSkuNewList) {
    ProductSku existSku = productSkuService.getEffectiveByCode(item.getItemCode());
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("商品信息：" + existSku != null ? JsonUtil.toJson(existSku) : "为空");
    }
    if (!Assert.isNull(existSku)) {
      /**
       * SKU存在，则需更新sku信息
       */
      getProductSku(item, product, existSku);
      productSkuList.add(existSku);
    } else {
      /**
       * SKU不存在，则新增SKU
       */
      ProductSku productSku = new ProductSku();
      getProductSku(item, product, productSku);
      productSkuList.add(productSku);
      productSkuNewList.add(productSku);
    }
  }

  private void getProductSku(OmsItemsSynchronizeRequest.Item item, Product product,
      ProductSku productSku) {
    if (product != null) {
      productSku.setProductId(product.getProductId());
      productSku.setProductCode(product.getProductCode());
      productSku.setProductName(product.getProductName());
    }
    if (productSku == null || productSku.getSkuCode() == null) {
      productSku.setCreatedTime(LocalDateTime.now());
      productSku.setEnable(true);
      productSku.setCombination(false);
      productSku.setGiftBox(false);
      productSku.setLockStock(false);
      productSku.setPurchasePrice(0.00);
      productSku.setDistributionPrice(0.00);
      productSku.setLogisticsInsurance(0.00);
      productSku.setWeight(0.00);
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
    return new WmsType[]{WmsType.QIMEN};
  }
}
