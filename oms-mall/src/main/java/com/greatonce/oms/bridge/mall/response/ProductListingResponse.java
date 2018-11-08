package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.ProductListingRequest;

/**
 * 商品上架响应.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-26
 */
public class ProductListingResponse extends MallResponse<ProductListingRequest> {

  public ProductListingResponse(ProductListingRequest request) {
    super(request);
  }

  public ProductListingResponse(ProductListingRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }
}
