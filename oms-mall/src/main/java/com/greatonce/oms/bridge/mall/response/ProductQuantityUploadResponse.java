package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.ProductQuantityUploadRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品上传.
 *
 * @author ginta
 */
public class ProductQuantityUploadResponse extends MallResponse<ProductQuantityUploadRequest> {

  private List<SkuQuantityUploadResponse> skus;

  public ProductQuantityUploadResponse(ProductQuantityUploadRequest request) {
    super(request);
  }

  public ProductQuantityUploadResponse(ProductQuantityUploadRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
    if (request.getSkus() != null && !request.getSkus().isEmpty()) {
      this.setSkus(
          request.getSkus().stream().map(x -> new SkuQuantityUploadResponse(x, isSuccess, result))
              .collect(Collectors.toList()));
    }
  }

  public List<SkuQuantityUploadResponse> getSkus() {
    return skus;
  }

  public void setSkus(List<SkuQuantityUploadResponse> skus) {
    this.skus = skus;
  }
}
