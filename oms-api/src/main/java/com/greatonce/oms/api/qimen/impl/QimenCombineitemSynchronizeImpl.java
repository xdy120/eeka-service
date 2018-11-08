package com.greatonce.oms.api.qimen.impl;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.product.ProductAttributeService;
import com.greatonce.oms.biz.product.ProductCategoryService;
import com.greatonce.oms.biz.product.ProductCombinationService;
import com.greatonce.oms.biz.product.ProductService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.bridge.wms.qimen.QimenCombineitemSynchronizeStrategy;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.request.OmsCombineitemSynchronizeRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsCombineitemSynchronizeRequest.Item;
import com.greatonce.oms.domain.Constants;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.domain.product.ProductSku;
import com.qimen.api.response.CombineitemSynchronizeResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QimenCombineitemSynchronizeImpl implements QimenCombineitemSynchronizeStrategy {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(QimenCombineitemSynchronizeImpl.class);
  @Autowired
  ProductService productService;
  @Autowired
  ProductSkuService productSkuService;
  @Autowired
  ProductAttributeService productAttributeService;
  @Autowired
  ProductCategoryService productCategoryService;
  @Autowired
  private ProductCombinationService productCombinationService;

  @Autowired
  private ManualTransactionTemplate transactionTemplate;

  @Override
  public CombineitemSynchronizeResponse synchronize(OmsCombineitemSynchronizeRequest request) {
    //检核组合商品是否存在
    Iterator<Item> it = request.getItems().iterator();
    while (it.hasNext()) {
      Item item = it.next();
      ProductSku sku = productSkuService.getEffectiveByCode(item.getItemCode());
      if (sku == null) {
        it.remove();
      }
    }
    if (Assert.isEmpty(request.getItems())) {
      return QimenCustomResponseUtil
          .resultFailureResponse(new CombineitemSynchronizeResponse(), "商品信息为空！");
    }
    ProductSku comSku = productSkuService.getEffectiveByCode(request.getItemCode());
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("商品信息：" + comSku != null ? JsonUtil.toJson(comSku) : "为空");
    }
    ProductSku productSku = null;
    if (comSku == null) {
      List<ProductSku> skuList = new ArrayList<>(1);
      productSku = new ProductSku();
      Item item = request.getItems().get(0);
      getProductSku(request.getItemCode(), item, productSku);
      productSku.setProductId(Constants.COMBINATION_PRODUCT_ID);
      productSku.setProductCode(Constants.COMBINATION_PRODUCT_CODE);
      productSku.setProductName(Constants.COMBINATION_PRODUCT_NAME);
      List<ProductCombination> details = new ArrayList<>(request.getItems().size());
      for (Item im : request.getItems()) {
        ProductSku sku = productSkuService.getEffectiveByCode(im.getItemCode());
        //新增组合商品
        ProductCombination pc = addProductCombination(sku.getProductCode(), sku,
            productSku.getSkuId());
        details.add(pc);
      }
      productSku.setDetails(details);
      skuList.add(productSku);
      transactionTemplate.execute(() -> {
        productSkuService.insertCombinationBatch(skuList);
      });
    } else {
      List<ProductCombination> details = new ArrayList<>(request.getItems().size());
      for (Item im : request.getItems()) {
        ProductSku sku = productSkuService.getEffectiveByCode(im.getItemCode());
        //新增组合商品
        ProductCombination pc = addProductCombination(sku.getProductCode(), sku, comSku.getSkuId());
        details.add(pc);
      }
      transactionTemplate.execute(() -> {
        //组合商品已存在 1.先删除原组合商品数据 2.新增新的组合商品数据
        productCombinationService.removeByCombinationId(comSku.getSkuId());
        productCombinationService.batchCreate(details);
      });
    }
    return QimenCustomResponseUtil
        .resultSuccessResponse(new CombineitemSynchronizeResponse(), request.getOwnerCode());
  }

  private ProductCombination addProductCombination(String productCode, ProductSku sku, Long skuId) {
    ProductCombination pc = new ProductCombination();
    pc.setCombinationId(skuId);
    pc.setCreatedTime(LocalDateTime.now());
    pc.setMainSku(true);
    pc.setModifiedTime(LocalDateTime.now());
    Product product = productService.getProductByCode(productCode);
    pc.setProductCode(product.getProductCode());
    pc.setProductId(product.getProductId());
    pc.setProductName(product.getProductName());
    pc.setQuantity(1);
    pc.setSkuCode(sku.getSkuCode());
    pc.setSkuId(sku.getSkuId());
    pc.setSkuName(sku.getSkuName());
    return pc;
  }

  private void getProductSku(String comskuCode, Item item,
      ProductSku productSku) {
    productSku.setSkuCode(comskuCode);
    productSku.setModifiedTime(LocalDateTime.now());
    productSku.setSize(item.getSize());
    productSku.setColor(item.getColor());
    productSku.setBarcode(item.getBarCode());
    productSku.setSkuName(
        (item.getColor() == null ? "" : item.getColor()) + " " + (item.getSize() == null ? ""
            : item.getSize()));
    productSku.setProductCode(item.getGoodsCode());
    productSku.setProductName(Constants.COMBINATION_PRODUCT_NAME);
    productSku.setCreatedTime(LocalDateTime.now());
    productSku.setEnable(true);
    productSku.setCombination(true);
    productSku.setGiftBox(false);
    productSku.setLockStock(false);
    productSku.setPurchasePrice(0.00);
    productSku.setSellingPrice(0.00);
    productSku.setDistributionPrice(0.00);
    productSku.setLogisticsInsurance(0.00);
    productSku.setWeight(0.00);
    productSku.setCostPrice(ConvertUtil.toDouble(item.getTagPrice(), 0));
    productSku.setMarkedPrice(ConvertUtil.toDouble(item.getTagPrice(), 0));
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
