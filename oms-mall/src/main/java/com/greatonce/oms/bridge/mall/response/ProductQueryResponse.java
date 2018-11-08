package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.domain.product.ProductMallMapping;

import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/8
 */
public class ProductQueryResponse extends MallResponse<ProductQueryRequest> {

  private List<ProductMallMapping> mappings;

  private boolean hasNext;

  public ProductQueryResponse(ProductQueryRequest request) {
    super(request);
  }

  public ProductQueryResponse(ProductQueryRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public List<ProductMallMapping> getMappings() {
    return mappings;
  }

  public void setMappings(List<ProductMallMapping> mappings) {
    this.mappings = mappings;
  }

  public boolean isHasNext() {
    return hasNext;
  }

  public void setHasNext(boolean hasNext) {
    this.hasNext = hasNext;
  }
}
