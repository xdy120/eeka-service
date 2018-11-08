package com.greatonce.oms.bridge.mall.impl.jd;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.bridge.mall.impl.AbstractProductBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.request.ProductListingRequest;
import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.ProductQueryResponse;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.jd.open.api.sdk.domain.ware.SkuReadService.Prop;
import com.jd.open.api.sdk.domain.ware.SkuReadService.Sku;
import com.jd.open.api.sdk.domain.ware.WareReadService.Page;
import com.jd.open.api.sdk.domain.ware.WareReadService.Ware;
import com.jd.open.api.sdk.request.ware.SkuReadSearchSkuListRequest;
import com.jd.open.api.sdk.request.ware.StockWriteUpdateSkuStockRequest;
import com.jd.open.api.sdk.request.ware.WareReadFindWareByIdRequest;
import com.jd.open.api.sdk.request.ware.WareReadSearchWare4ValidRequest;
import com.jd.open.api.sdk.response.ware.SkuReadSearchSkuListResponse;
import com.jd.open.api.sdk.response.ware.WareReadFindWareByIdResponse;
import com.jd.open.api.sdk.response.ware.WareReadSearchWare4ValidResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * 京东商品接口.
 *
 * @author ginta
 */
@Component
public class JdProductBridge extends AbstractProductBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(JdProductBridge.class);
  private static final String BASIC_FIELDS = "saleAttrs,jdPrice,stockNum";
  private static final int DEFAULT_PAGE_SIZE = 10;
  @Autowired
  private JdMall mall;

  @Override
  protected void doListing(ProductListingRequest request) {

  }

  @Override
  public SkuQuantityUploadResponse doUploadQuantity(SkuQuantityUploadRequest request) {
    try {
      StockWriteUpdateSkuStockRequest updateSkuStockRequest = new StockWriteUpdateSkuStockRequest();
      updateSkuStockRequest.setSkuId(Long.parseLong(request.getMapping().getMallSkuId()));
      updateSkuStockRequest.setStockNum((long) request.getQuantity());
      LOGGER.debug("京东上传：{},数量：{}", request.getMapping().getSkuCode(), request.getQuantity());
      mall.call(request.getStore(), updateSkuStockRequest, false);
      return new SkuQuantityUploadResponse(request);
    } catch (Exception ex) {
      return new SkuQuantityUploadResponse(request, false, ex.getMessage());
    }
  }


  private Ware getWare(Store store, String wareId) {
    WareReadFindWareByIdRequest request = new WareReadFindWareByIdRequest();
    request.setWareId(Long.parseLong(wareId));
    request.setField("ware_id,itemNum,wareStatus,stockNum");
    WareReadFindWareByIdResponse response = mall.call(store, request, false);
    return response.getWare();
  }

  @Override
  public ProductQueryResponse queryProduct(ProductQueryRequest request) {
    WareReadSearchWare4ValidRequest ware4ValidRequest = new WareReadSearchWare4ValidRequest();
    if (request.getBeginTime() != null) {
      ware4ValidRequest
          .setStartModifiedTime(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
    }
    if (request.getEndTime() != null) {
      ware4ValidRequest.setEndModifiedTime(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
    }
    if (!Assert.isNull(request.getMallProductStatus())) {
      ware4ValidRequest.setWareStatusValue(
          request.getMallProductStatus() == MallProductStatus.ONSALE ? "8" : "2,4");
    }
    ware4ValidRequest.setPageNo(request.getPage());
    ware4ValidRequest.setItemNum(request.getCode());
    ware4ValidRequest.setPageSize(DEFAULT_PAGE_SIZE);
    WareReadSearchWare4ValidResponse ware4ValidResponse = mall
        .call(request.getStore(), ware4ValidRequest, false);
    ProductQueryResponse response = new ProductQueryResponse(request);
    Page page = ware4ValidResponse.getPage();
    parseWarePage(request, response, page);
    return response;
  }

  @Override
  public ProductQueryResponse queryProductById(ProductQueryRequest request) {
    WareReadFindWareByIdRequest wareByIdRequest = new WareReadFindWareByIdRequest();
    List<String> ids = StringUtil.words(request.getId());
    List<ProductMallMapping> mappings = new ArrayList<>();
    for (String id : ids) {
      wareByIdRequest.setWareId(Long.parseLong(id));
      WareReadFindWareByIdResponse wareByIdResponse = mall
          .call(request.getStore(), wareByIdRequest, false);
      parseItem(mappings, request.getStore(), wareByIdResponse.getWare());
    }
    ProductQueryResponse response = new ProductQueryResponse(request);
    response.setMappings(mappings);
    return response;
  }

  @Override
  public ProductQueryResponse queryProductByCode(ProductQueryRequest request) {
    List<String> codes = StringUtil.words(request.getCode());
    List<ProductMallMapping> mappings = new ArrayList<>();
    ProductQueryResponse response;
    for (String code : codes) {
      request.setCode(code);
      response = queryProduct(request);
      if (response.isSuccess() && !Assert.isEmpty(response.getMappings())) {
        mappings.addAll(response.getMappings());
      }
    }
    if (Assert.isEmpty(mappings)) {
      return new ProductQueryResponse(request, false, "未找到商品");
    }
    response = new ProductQueryResponse(request);
    response.setMappings(mappings);
    return response;
  }

  @Override
  public boolean isSupportMultiStatus() {
    return true;
  }

  @Override
  public String getMallProductUrl(String mallProductId) {
    return "https://item.jd.com/" + mallProductId + ".html";
  }

  private void parseWarePage(ProductQueryRequest request, ProductQueryResponse response,
      Page page) {
    if (page.getTotalItem() > 0) {
      response.setHasNext(MallUtil
          .calcHasNext(page.getPageSize(), page.getPageNo(), Math.toIntExact(page.getTotalItem())));
      List<ProductMallMapping> mappings = new ArrayList<>(page.getData().size());
      for (Ware ware : page.getData()) {
        parseItem(mappings, request.getStore(), ware);
      }
      response.setMappings(mappings);
    }
  }


  private void parseItem(List<ProductMallMapping> mappings, Store store, Ware ware) {
    List<Sku> skus = getSkus(store, String.valueOf(ware.getWareId()));
    ProductMallMapping mapping;
    if (!skus.isEmpty()) {
      for (Sku sku : skus) {
        mapping = createBySPU(ware);
        mapping.setStoreId(store.getStoreId());
        mapping.setStoreName(store.getStoreName());
        mapping.setMallSkuId(String.valueOf(sku.getSkuId()));
        mapping.setMallSkuName(sku.getSkuName());
        mapping.setMallSkuOutCode(sku.getOuterId());
        mapping.setMallSkuName(parseSkuName(sku.getSaleAttrs()));
        mapping.setMallSkuQuantity(Math.toIntExact(sku.getStockNum()));
        mapping.setMallSkuPrice(Double.parseDouble(sku.getJdPrice().toString()));
        if (!Assert.isEmpty(sku.getLogo())) {
          mapping.setImageUrl(sku.getLogo());
        }
        setMatchCode(store, mapping);
        mappings.add(mapping);
      }
    } else {
      mapping = createBySPU(ware);
      setMatchCode(store, mapping);
      mappings.add(mapping);
    }


  }

  private String parseSkuName(Set<Prop> saleAttrs) {
    if (saleAttrs != null && saleAttrs.size() > 0) {
      StringJoiner joiner = new StringJoiner(" ");
      for (Prop saleAttr : saleAttrs) {
        for (String value : saleAttr.getAttrValueAlias()) {
          joiner.add(value);
        }
      }
      return joiner.toString();
    }
    return "";
  }

  private List<Sku> getSkus(Store store, String wareId) {
    SkuReadSearchSkuListRequest request = new SkuReadSearchSkuListRequest();
    request.setWareId(wareId);
    request.setPageNo(1);
    request.setPageSize(DEFAULT_PAGE_SIZE);
    request.setField(BASIC_FIELDS);
    SkuReadSearchSkuListResponse response = mall.call(store, request, false);
    com.jd.open.api.sdk.domain.ware.SkuReadService.Page page = response.getPage();
    List<Sku> skus = new ArrayList<>();
    if (page.getTotalItem() > 0) {
      skus.addAll(page.getData());
      if (page.getTotalItem() > page.getPageSize()) {
        int currentPage = page.getPageNo();
        int totalPages = MallUtil.calcTotalPage(DEFAULT_PAGE_SIZE, page.getTotalItem());
        while (currentPage++ <= totalPages) {
          request.setPageNo(currentPage);
          response = mall.call(store, request, false);
          skus.addAll(response.getPage().getData());
        }
      }
    }
    return skus;
  }

  private ProductMallMapping createBySPU(Ware ware) {
    ProductMallMapping mapping = new ProductMallMapping();
    mapping.setMallProductId(String.valueOf(ware.getWareId()));
    mapping.setMallProductName(ware.getTitle());
    mapping.setMallProductOutCode(ware.getItemNum());
    mapping.setMallProductStatus(
        ware.getWareStatus().equals(8) ? MallProductStatus.ONSALE : MallProductStatus.INSTOCK);
    mapping.setImageUrl(ware.getLogo());
    if (!Assert.isNull(ware.getStockNum())) {
      mapping.setMallSkuQuantity(Math.toIntExact(ware.getStockNum()));
    }
    if (!Assert.isNull(ware.getJdPrice())) {
      mapping.setMallSkuPrice(Double.parseDouble(ware.getJdPrice().toString()));
    }
    return mapping;
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.JD};
  }
}
