package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.SecurityUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.bridge.mall.ProductBridge;
import com.greatonce.oms.bridge.mall.request.ProductListingRequest;
import com.greatonce.oms.bridge.mall.request.ProductQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.ProductQuantityUploadResponse;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.product.ProductMallMapping;
import java.util.ArrayList;
import java.util.List;

/**
 * 商品接口抽象类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
public abstract class AbstractProductBridge extends AbstractBridge implements ProductBridge {

  @Override
  public SkuQuantityUploadResponse uploadQuantity(SkuQuantityUploadRequest request) {
    if (isTesting()) {
      return new SkuQuantityUploadResponse(request);
    } else {
      return doUploadQuantity(request);
    }
  }

  @Override
  public ProductQuantityUploadResponse uploadQuantity(ProductQuantityUploadRequest request) {
    if (isTesting()) {
      return new ProductQuantityUploadResponse(request);
    } else {
      return doUploadQuantity(request);
    }
  }

  /**
   * 上架.
   */
  protected void listing(SkuQuantityUploadRequest request) {
    if (Assert.isTrue(request.getMapping().isAutoListing())) {
      ProductListingRequest listingRequest = new ProductListingRequest(request.getStore());
      listingRequest.setMallProductId(request.getMapping().getMallProductId());
      listingRequest.setMallSkuId(request.getMapping().getMallSkuId());
      listingRequest.setMallProductOutCode(request.getMapping().getMallProductOutCode());
      listingRequest.setMallSkuOutCode(request.getMapping().getMallSkuOutCode());
      doListing(listingRequest);
    }
  }

  /**
   * 上架.
   */
  protected void listing(ProductQuantityUploadRequest request) {
    listing(request.getSkus().get(0));
  }

  /**
   * 执行上架.
   */
  protected void doListing(ProductListingRequest request) {
  }

  /**
   * 设置SKU匹配码.
   * <p/>
   * 根据店铺配置的匹配码类型设置matchCode，如果没有配置，优先为mallSkuOuterCode。
   * 如果skuOuterCode为空则为mallProductOutCode
   */
  protected void setMatchCode(Store store, ProductMallMapping mapping) {
    String matchCode;
    if (store.getSetting().getProductMappingType() != null) {
      switch (store.getSetting().getProductMappingType()) {
        case SKU_CODE:
          matchCode = mapping.getMallSkuOutCode();
          break;
        case PRODUCT_CODE:
          matchCode = mapping.getMallProductOutCode();
          break;
        case PRODUCT_CODE_AND_SKU_CODE:
          matchCode = mapping.getMallProductOutCode() + mapping.getMallSkuOutCode();
          break;
        case PRODUCT_CODE_OR_SKU_CODE:
          matchCode = Assert.isNull(mapping.getMallSkuOutCode()) ? mapping.getMallProductOutCode()
              : mapping.getMallSkuOutCode();
          break;
        default:
          matchCode = Assert.isNull(mapping.getMallSkuOutCode()) ? mapping.getMallProductOutCode()
              : mapping.getMallSkuOutCode();
          break;
      }
    } else {
      matchCode = Assert.isNull(mapping.getMallSkuOutCode()) ? mapping.getMallProductOutCode()
          : mapping.getMallSkuOutCode();
    }
    mapping.setStoreId(store.getStoreId());
    mapping.setUniqueId(buildUniqueId(mapping));
    mapping.setMatchCode(matchCode);
  }

  /**
   * 生成唯一ID，默认使用mallProductId+mallSkuId进行MD5.
   */
  protected String buildUniqueId(ProductMallMapping mapping) {
    return SecurityUtil
        .md5Hex(StringUtil
            .append(String.valueOf(mapping.getStoreId()), mapping.getMallProductId(),
                mapping.getMallSkuId()));
  }


  protected abstract SkuQuantityUploadResponse doUploadQuantity(SkuQuantityUploadRequest request);

  protected ProductQuantityUploadResponse doUploadQuantity(ProductQuantityUploadRequest request) {
    List<SkuQuantityUploadResponse> skus = new ArrayList<>();
    for (SkuQuantityUploadRequest skuQuantityUploadInfo : request.getSkus()) {
      skus.add(uploadQuantity(skuQuantityUploadInfo));
    }
    ProductQuantityUploadResponse productQuantityUploadResponse = new ProductQuantityUploadResponse(
        request);
    productQuantityUploadResponse.setSkus(skus);
    return productQuantityUploadResponse;
  }
}
