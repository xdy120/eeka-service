package com.greatonce.oms.bridge.mall.impl.taobao.fx;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.mall.impl.AbstractProductBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.taobao.TaobaoMall;
import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.ProductQueryResponse;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.taobao.api.domain.FenxiaoSku;
import com.taobao.api.request.FenxiaoProductQuantityUpdateRequest;
import com.taobao.api.request.FenxiaoProductsGetRequest;
import com.taobao.api.response.FenxiaoProductQuantityUpdateResponse;
import com.taobao.api.response.FenxiaoProductsGetResponse;
import com.taobao.api.response.FenxiaoProductsGetResponse.FenxiaoProduct;
import com.taobao.api.response.FenxiaoProductsGetResponse.SkuList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FenxiaoProductBridge extends AbstractProductBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(FenxiaoProductBridge.class);

  @Autowired
  private TaobaoMall mall;

  private static final Map<MallProductStatus, String> MALL_PRODUCT_STATUS_STRING_MAP_STRING = InitEnumMap.MALL_PRODUCT_STATUS_STRING_MAP_STRING;
  private static final Map<String, MallProductStatus> MALL_PRODUCT_STATUS_STRING_MAP_MENU = InitEnumMap.MALL_PRODUCT_STATUS_STRING_MAP_MENU;


  @Override
  public ProductQueryResponse queryProduct(ProductQueryRequest request) {
    final long pageSize = 50L;
    ProductQueryResponse response = new ProductQueryResponse(request);
    try {
      FenxiaoProductsGetRequest getRequest = new FenxiaoProductsGetRequest();
      getRequest.setPageNo((long) request.getPage());
      getRequest.setPageSize(pageSize);
      getRequest.setStartModified(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
      getRequest.setEndModified(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
      FenxiaoProductsGetResponse getResponse = mall.call(request.getStore(), getRequest);
      if (getResponse.getTotalResults() == 0) {
        LOGGER.debug("下载分销铺货失败,找不到或无铺货关系");
        response.setResult("找不到或无铺货关系");
        return response;
      }
      response.setHasNext(
          request.getPage() < MallUtil.calcTotalPage(pageSize, getResponse.getTotalResults()));
      List<FenxiaoProduct> products = getResponse.getProducts();
      List<ProductMallMapping> mappings = new ArrayList<>(products.size());
      for (FenxiaoProduct product : products) {
        mappings.addAll(convertProduct(request.getStore(), product));
      }
      if (Assert.isEmpty(mappings)) {
        LOGGER.debug("解析下载product失败");
        response.setResult("解析下载product失败");
        response.setSuccess(false);
        return response;
      }

      response.setMappings(mappings);
    } catch (Exception e) {
      LOGGER.error("分销铺货下载访问平台接口异常:{}", e.getMessage());
      response.setSuccess(false);
      response.setResult("分销铺货下载访问平台接口异常");
      return response;
    }
    return response;
  }

  private List<ProductMallMapping> convertProduct(Store store, FenxiaoProduct product) {
    ArrayList<ProductMallMapping> mappings = new ArrayList<>();
    List<SkuList> skus = product.getSkus();
    if (!Assert.isEmpty(skus)) {
      for (SkuList sku : skus) {
        ProductMallMapping mapping = createMapping(product);
        //设置sku的信息
        setSku(mapping, sku);

        setMatchCode(store, mapping);

        mappings.add(mapping);
      }
    } else {
      //没有sku的只保存product信息
      ProductMallMapping mapping = createMapping(product);
      mappings.add(mapping);
    }
    return mappings;
  }

  private void setSku(ProductMallMapping mapping, SkuList sku) {
    mapping.setMallSkuOutCode(sku.getOuterId());
    mapping.setMallSkuId(sku.getProperties());
    mapping.setMallSkuName(sku.getName());
  }

  private ProductMallMapping createMapping(FenxiaoProduct product) {
    ProductMallMapping mapping = new ProductMallMapping();
    mapping.setMallProductOutCode(product.getOuterId());
    mapping.setMallProductId(product.getPid().toString());
    mapping.setMallProductName(product.getName());
    mapping.setMallProductStatus(MALL_PRODUCT_STATUS_STRING_MAP_MENU.get(product.getStatus()));
    mapping.setImageUrl(product.getPictures());
    return mapping;
  }

  @Override
  public ProductQueryResponse queryProductById(ProductQueryRequest request) {
    ProductQueryResponse response = new ProductQueryResponse(request);
    try {

      FenxiaoProductsGetRequest req = new FenxiaoProductsGetRequest();
      req.setPids(request.getId());

      FenxiaoProductsGetResponse getResponse = mall.call(request.getStore(), req);

      List<FenxiaoProduct> products = getResponse.getProducts();

      if (Assert.isEmpty(products)) {
        LOGGER.debug("下载分销铺货失败,找不到或无铺货关系");
        response.setResult("找不到或无铺货关系");
        return response;
      }

      List<ProductMallMapping> mappings = convertProduct(request.getStore(), products.get(0));
      if (Assert.isEmpty(mappings)) {
        LOGGER.debug("解析下载product失败,mallProductId:{}", request.getId());
        response.setResult("解析下载product失败");
        response.setSuccess(false);
        return response;
      }
      response.setMappings(mappings);
    } catch (Exception e) {
      LOGGER.error("通过平台商品id查询商品信息异常,平台商品id:{}", request.getId());
      response.setResult("通过平台商品id查询商品信息异常");
      response.setSuccess(false);
      return response;
    }
    return response;
  }

  @Override
  public ProductQueryResponse queryProductByCode(ProductQueryRequest request) {
    ProductQueryResponse response = new ProductQueryResponse(request);
    try {

      FenxiaoProductsGetRequest req = new FenxiaoProductsGetRequest();
      //商家编码
      req.setOuterId(request.getCode());

      FenxiaoProductsGetResponse getResponse = mall.call(request.getStore(), req);

      List<FenxiaoProduct> products = getResponse.getProducts();

      if (Assert.isEmpty(products)) {
        LOGGER.debug("下载分销铺货失败,找不到或无铺货关系");
        response.setResult("找不到或无铺货关系");
        return response;
      }
      List<ProductMallMapping> mappings = convertProduct(request.getStore(), products.get(0));
      if (Assert.isEmpty(mappings)) {
        LOGGER.debug("解析下载product失败,mallProductOutCode:{}", request.getCode());
        response.setResult("解析下载product失败");
        response.setSuccess(false);
        return response;
      }
      response.setMappings(mappings);
    } catch (Exception e) {
      LOGGER.error("通过平台商品编码查询商品信息异常,平台商品编码:{}", request.getCode());
      response.setResult("通过平台商品编码查询商品信息异常");
      response.setSuccess(false);
      return response;
    }
    return response;
  }

  @Override
  public boolean isSupportMultiStatus() {
    return true;
  }

  //TODO
  @Override
  public String getMallProductUrl(String mallProductId) {
    return null;
  }


  @Override
  protected SkuQuantityUploadResponse doUploadQuantity(SkuQuantityUploadRequest request) {
    SkuQuantityUploadResponse response = new SkuQuantityUploadResponse(request);
    ProductMallMapping mapping = request.getMapping();
    try {
      FenxiaoProductQuantityUpdateRequest req = new FenxiaoProductQuantityUpdateRequest();
      req.setProductId(mapping.getProductId());
      req.setQuantity(String.valueOf(request.getQuantity()));
      req.setType(request.getUploadType() == StockUploadType.COVER ? 1L : 2L);
      req.setProperties(mapping.getMallSkuId());
      FenxiaoProductQuantityUpdateResponse fxResponse = mall.call(request.getStore(), req);
      if (!fxResponse.getResult()) {
        response.setSuccess(false);
        response.setResult("库存上传失败");
        LOGGER.debug("库存上传失败,店铺:{},商品:{},属性:{}", request.getStore().getStoreName(),
            mapping.getProductName(), mapping.getMallSkuId());
        return response;
      }
    } catch (Exception e) {
      response.setSuccess(false);
      response.setResult("库存上传异常");
      LOGGER.error("库存上传失败,店铺:{},商品:{},属性:{}", request.getStore().getStoreName(),
          mapping.getProductName(), mapping.getMallSkuId());
      return response;
    }
    return response;
  }


  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO_FX};
  }


}
