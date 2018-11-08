package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.bridge.mall.impl.AbstractProductBridge;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.domain.JdpTbItem;
import com.greatonce.oms.bridge.mall.request.ProductListingRequest;
import com.greatonce.oms.bridge.mall.request.ProductQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.request.ProductQueryRequest;
import com.greatonce.oms.bridge.mall.request.SkuQuantityUploadRequest;
import com.greatonce.oms.bridge.mall.response.ProductQuantityUploadResponse;
import com.greatonce.oms.bridge.mall.response.ProductQueryResponse;
import com.greatonce.oms.bridge.mall.response.SkuQuantityUploadResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreSetting;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.taobao.api.ApiException;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import com.taobao.api.domain.Item;
import com.taobao.api.domain.Sku;
import com.taobao.api.internal.util.TaobaoUtils;
import com.taobao.api.request.ItemQuantityUpdateRequest;
import com.taobao.api.request.ItemSellerGetRequest;
import com.taobao.api.request.ItemUpdateListingRequest;
import com.taobao.api.request.ItemsCustomGetRequest;
import com.taobao.api.request.ItemsInventoryGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.request.ItemsSellerListGetRequest;
import com.taobao.api.request.SkusQuantityUpdateRequest;
import com.taobao.api.response.ItemQuantityUpdateResponse;
import com.taobao.api.response.ItemSellerGetResponse;
import com.taobao.api.response.ItemUpdateListingResponse;
import com.taobao.api.response.ItemsCustomGetResponse;
import com.taobao.api.response.ItemsInventoryGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;
import com.taobao.api.response.ItemsSellerListGetResponse;
import com.taobao.api.response.SkusQuantityUpdateResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 淘宝商品接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
@Component
public class TaobaoProductBridge extends AbstractProductBridge {

  private static final Logger LOGGER = LoggerFactory.getLogger(TaobaoProductBridge.class);
  private static final int ERROR_SLEEP_MS = 1000;
  private static final String BASIC_FIELDS = "num_iid,nick,title,price,outer_id,approve_status,pic_url,sku,property_alias,props_name,num";
  private static final Set<String> RETRY_ERROR = new HashSet<String>(1);
  private static final int PAGE_SIZE = 100;
  private static final String QUERY_ITEM_FIELDS = "num_iid";
  private static final int MAX_IDS_SIZE = 20;
  private static final int MAX_CODES_SIZE = 40;
  private static final Pattern ALIAS_REGEX = Pattern
      .compile("(?<value>[^:;]*:[^:;]*):(?<name>[^:;]*)");
  private static final Pattern PROPERTY_REGEX = Pattern
      .compile("(?<value>[^:;]*:[^:;]*):[^:;]*:(?<name>[^:;]*)");


  static {
    RETRY_ERROR.add("isv.item-quantity-item-update-service-error");
  }

  @Autowired
  private TaobaoMall mall;
  @Resource
  private SqlSessionDecorator rdsSqlSessionDecorator;

  public <T extends TaobaoResponse> T retry(Store store, TaobaoRequest<T> request, T response) {
    if (!response.isSuccess()) {
      if (RETRY_ERROR.contains(response.getSubCode().toLowerCase())) {
        LOGGER.debug("淘宝错误重试：{},{}", response.getSubCode(), response.getSubMsg());
        return mall.call(store, request);
      } else {
        throw mall.fillException(response);
      }
    }
    return response;
  }

  @Override
  protected void doListing(ProductListingRequest request) {
    try {
      ItemSellerGetRequest itemSellerGetRequest = new ItemSellerGetRequest();
      itemSellerGetRequest.setFields("num_iid,num,approve_status,is_timing");
      itemSellerGetRequest.setNumIid(Long.parseLong(request.getMallProductId()));
      final ItemSellerGetResponse itemSellerGetResponse = mall
          .call(request.getStore(), itemSellerGetRequest);
      final Item item = itemSellerGetResponse.getItem();
      if (Assert.isTrue(item.getIsTiming()) || "onsale".equalsIgnoreCase(item.getApproveStatus())) {
        LOGGER.info("商品{}，编码：{}已是上架状态或是定时上架，不进行上架操作", request.getMallProductId(),
            request.getMallProductOutCode());
      } else {
        ItemUpdateListingRequest listingRequest = new ItemUpdateListingRequest();
        listingRequest.setNumIid(item.getNumIid());
        listingRequest.setNum(item.getNum());
        final ItemUpdateListingResponse listingResponse = mall
            .call(request.getStore(), listingRequest);
        LOGGER.info("商品{}，编码{}上架结果：{}", request.getMallProductId(), request.getMallProductOutCode(),
            listingResponse.isSuccess());
      }
    } catch (Exception ex) {
      LOGGER.error("商品{}，编码{}上架失败：{}", request.getMallProductId(), request.getMallProductOutCode(),
          ex.getMessage());
    }
  }

  @Override
  public SkuQuantityUploadResponse doUploadQuantity(SkuQuantityUploadRequest request) {
    ProductMallMapping mapping = request.getMapping();
    try {
      LOGGER.debug("淘系上传：{},数量：{}", request.getMapping().getSkuCode(), request.getQuantity());
      synchronized (("TM_STOCK_UPLOAD_" + mapping.getMallProductId()).intern()) {
        if (Assert.isEmpty(mapping.getMallSkuOutCode()) && Assert
            .isEmpty(mapping.getMallSkuId())) {
          ItemQuantityUpdateRequest itemQuantityUpdateRequest = new ItemQuantityUpdateRequest();
          itemQuantityUpdateRequest.setQuantity((long) request.getQuantity());
          itemQuantityUpdateRequest.setNumIid(Long.parseLong(mapping.getMallProductId()));
          itemQuantityUpdateRequest
              .setType(request.getUploadType() == StockUploadType.COVER ? 1L : 2L);
          ItemQuantityUpdateResponse response = mall
              .call(request.getStore(), itemQuantityUpdateRequest, false);
          retry(request.getStore(), itemQuantityUpdateRequest, response);
        } else {
          SkusQuantityUpdateRequest skusQuantityUpdateRequest = new SkusQuantityUpdateRequest();
          skusQuantityUpdateRequest.setNumIid(Long.parseLong(mapping.getMallProductId()));
          skusQuantityUpdateRequest
              .setType(request.getUploadType() == StockUploadType.COVER ? 1L : 2L);
          if (!Assert.isEmpty(mapping.getMallProductOutCode())) {
            skusQuantityUpdateRequest
                .setOuteridQuantities(mapping.getMallSkuOutCode() + ":" + request.getQuantity());
          } else {
            skusQuantityUpdateRequest
                .setSkuidQuantities(mapping.getMallSkuId() + ":" + request.getQuantity());
          }
          SkusQuantityUpdateResponse response = mall
              .call(request.getStore(), skusQuantityUpdateRequest, false);
          retry(request.getStore(), skusQuantityUpdateRequest, response);
        }
      }
      listing(request);
      return new SkuQuantityUploadResponse(request);
    } catch (Exception ex) {
      return new SkuQuantityUploadResponse(request, false, ex.getMessage());
    }
  }

  @Override
  public ProductQuantityUploadResponse doUploadQuantity(ProductQuantityUploadRequest request) {
    try {
      LOGGER.debug("淘系上传：{}", request);
      SkuQuantityUploadRequest skuQuantityUploadInfo = request.getSkus().get(0);
      SkusQuantityUpdateRequest skusQuantityUpdateRequest = new SkusQuantityUpdateRequest();
      skusQuantityUpdateRequest
          .setNumIid(Long.parseLong(skuQuantityUploadInfo.getMapping().getMallSkuId()));
      skusQuantityUpdateRequest
          .setType(skuQuantityUploadInfo.getUploadType() == StockUploadType.COVER ? 1L : 2L);
      final String quantities = request.getSkus().stream()
          .map(x -> x.getMapping().getMallSkuOutCode() + ":" + x.getQuantity())
          .collect(Collectors.joining(","));
      skusQuantityUpdateRequest.setOuteridQuantities(quantities);
      synchronized (skusQuantityUpdateRequest.getNumIid()) {
        SkusQuantityUpdateResponse response = mall
            .call(request.getStore(), skusQuantityUpdateRequest, false);
        retry(request.getStore(), skusQuantityUpdateRequest, response);
      }
      listing(request);
      return new ProductQuantityUploadResponse(request);
    } catch (Exception ex) {
      return new ProductQuantityUploadResponse(request, false, ex.getMessage());
    }
  }

  /**
   * 批量查询.
   */
  @Override
  public ProductQueryResponse queryProduct(ProductQueryRequest request) {
    StoreSetting setting = request.getStore().getSetting();
    if (Assert.isTrue(setting.isTaobaoProductCloudPush())) {
      return queryProductByCloudPush(request);
    } else {
      return queryProductByApi(request);
    }
  }

  /**
   * 根据id查询商品.
   */
  @Override
  public ProductQueryResponse queryProductById(ProductQueryRequest request) {
    ProductQueryResponse response = new ProductQueryResponse(request);
    String ids = request.getId();
    List<String> words = StringUtil.words(ids);
    int totalPage = MallUtil.calcTotalPage(MAX_IDS_SIZE, words.size());
    List<ProductMallMapping> mappings = new ArrayList<>(words.size());
    // 一次带20个id请求淘宝api
    for (int page = 1; page <= totalPage; page++) {
      final String limitCodes = words.stream().skip((page - 1) * MAX_IDS_SIZE).limit(MAX_IDS_SIZE)
          .collect(Collectors.joining(","));
      ItemsSellerListGetResponse listGetResponse = queryProductFullInfoByIdApi(request.getStore(),
          limitCodes);

      if (listGetResponse.isSuccess() && !Assert.isEmpty(listGetResponse.getItems())) {
        for (Item item : listGetResponse.getItems()) {
          parseItem(request.getStore(), item, mappings);
        }
      }
    }
    response.setMappings(mappings);
    return response;
  }

  /**
   * 淘宝接口根据外部编码查找商品.
   * <p/>
   * api规定code不能超过40个
   */
  @Override
  public ProductQueryResponse queryProductByCode(ProductQueryRequest request) {
    ProductQueryResponse response = new ProductQueryResponse(request);
    String codes = request.getCode();
    List<String> words = StringUtil.words(codes);
    int totalPage = MallUtil.calcTotalPage(MAX_CODES_SIZE, words.size());
    List<ProductMallMapping> mappings = new ArrayList<>(words.size());
    // 一次带40个code请求淘宝api
    for (int page = 1; page <= totalPage; page++) {
      String limitCodes = words.stream().skip((page - 1) * MAX_CODES_SIZE).limit(MAX_CODES_SIZE)
          .collect(Collectors.joining(","));
      ItemsCustomGetResponse customGetResponse = queryProductFullInfoByCodeApi(request.getStore(),
          limitCodes);
      if (customGetResponse.isSuccess() && !Assert.isEmpty(customGetResponse.getItems())) {
        for (Item item : customGetResponse.getItems()) {
          parseItem(request.getStore(), item, mappings);
        }
      }
    }
    response.setMappings(mappings);
    return response;
  }

  /**
   * 根据code查询商品详情
   */
  private ItemsCustomGetResponse queryProductFullInfoByCodeApi(Store store, String codesString) {
    ItemsCustomGetRequest getRequest = new ItemsCustomGetRequest();
    getRequest.setOuterId(codesString);
    getRequest.setFields(BASIC_FIELDS);
    return mall.call(store, getRequest);
  }

  /**
   * 云推方式查商品.
   */
  private ProductQueryResponse queryProductByCloudPush(ProductQueryRequest request) {
    ProductQueryResponse response = new ProductQueryResponse(request);
    Map<String, Object> map = new HashMap<>(4);
    map.put("jdpModifiedBegin", request.getBeginTime());
    map.put("jdpModifiedEnd", request.getEndTime());
    map.put("approveStatus", request.getMallProductStatus().toString().toLowerCase());
    map.put("nick", request.getStore().getNickname());
    PageList<JdpTbItem> jdpTbItemResultList = rdsSqlSessionDecorator.selectList(
        "com.greatonce.oms.bridge.mall.impl.taobao.cloudpush.mappers.JdpTbItemMapper.listByConditions",
        map, request.getPage(), PAGE_SIZE);
    List<JdpTbItem> resultList = jdpTbItemResultList.getData();
    response.setHasNext(
        MallUtil.calcHasNext(PAGE_SIZE, request.getPage(), jdpTbItemResultList.getTotal()));
    if (Assert.isEmpty(resultList)) {
      return new ProductQueryResponse(request);
    }
    List<ProductMallMapping> mappings = new ArrayList<>(resultList.size());
    //如果有商品则创建映射关系，没有则返回空的mapping集合
    for (JdpTbItem jdpTbItem : resultList) {
      try {
        parseItemJson(request.getStore(), jdpTbItem.getJdpResponse(), mappings);
      } catch (Exception e) {
        LOGGER.error("淘宝商品解析失败：{}，{}", e.getMessage(), jdpTbItem.getJdpResponse());
      }
    }
    response.setMappings(mappings);
    return response;
  }

  /**
   * 通过接口全店下载商品.
   */
  private ProductQueryResponse queryProductByApi(ProductQueryRequest request) {
    ProductQueryResponse response = new ProductQueryResponse(request);
    List<ProductMallMapping> mappings;
    if (request.getMallProductStatus() == MallProductStatus.ONSALE) {
      mappings = queryOnSaleByApi(request);
    } else {
      mappings = queryInStockByApi(request);
    }
    response.setHasNext(!Assert.isEmpty(mappings));
    response.setMappings(mappings);
    return response;
  }

  /**
   * 接口查询非在售但在库的商品.
   */
  private List<ProductMallMapping> queryInStockByApi(ProductQueryRequest request) {
    Store store = request.getStore();
    ItemsInventoryGetRequest req = new ItemsInventoryGetRequest();
    req.setFields(QUERY_ITEM_FIELDS);
    req.setPageNo((long) request.getPage());
    req.setPageSize((long) PAGE_SIZE);
    if (request.getBeginTime() != null) {
      req.setStartModified(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
    }
    if (request.getEndTime() != null) {
      req.setEndModified(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
    }
    ItemsInventoryGetResponse resp = mall.call(request.getStore(), req);
    return queryItems(store, resp.getItems());
  }

  /**
   * 淘宝接口查询在售商品.
   */
  private List<ProductMallMapping> queryOnSaleByApi(ProductQueryRequest request) {
    Store store = request.getStore();
    ItemsOnsaleGetRequest req = new ItemsOnsaleGetRequest();
    req.setFields(QUERY_ITEM_FIELDS);
    req.setPageNo((long) request.getPage());
    req.setPageSize((long) PAGE_SIZE);
    if (request.getBeginTime() != null) {
      req.setStartModified(DateTimeUtil.localDateTimeToDate(request.getBeginTime()));
    }
    if (request.getEndTime() != null) {
      req.setEndModified(DateTimeUtil.localDateTimeToDate(request.getEndTime()));
    }
    ItemsOnsaleGetResponse resp = mall.call(store, req);
    if (!resp.isSuccess()) {
      return null;
    }
    return queryItems(store, resp.getItems());
  }

  /**
   * 接口批量根据ID查商品详细信息.
   */
  private ItemsSellerListGetResponse queryProductFullInfoByIdApi(Store store, String idsString) {
    ItemsSellerListGetRequest listGetRequest = new ItemsSellerListGetRequest();
    listGetRequest.setFields(BASIC_FIELDS);
    listGetRequest.setNumIids(idsString);
    return mall.call(store, listGetRequest);
  }

  private List<ProductMallMapping> queryItems(Store store, List<Item> items) {
    if (Assert.isEmpty(items)) {
      return null;
    }
    int pageSize = 20;
    int pageCount = MallUtil.calcTotalPage(pageSize, items.size());
    List<ProductMallMapping> mappings = new ArrayList<>(items.size());
    for (int page = 1; page <= pageCount; page++) {
      final String limitNumIds = items.stream().skip((page - 1) * pageSize).limit(pageSize)
          .map(x -> x.getNumIid().toString()).collect(
              Collectors.joining(","));
      ItemsSellerListGetResponse getResponse = queryProductFullInfoByIdApi(store, limitNumIds);
      if (!getResponse.isSuccess()) {
        return null;
      }
      for (Item item : getResponse.getItems()) {
        parseItem(store, item, mappings);
      }
    }
    return mappings;
  }

  /**
   * 解析itemJson
   */
  private void parseItemJson(Store store, String itemJson, List<ProductMallMapping> mappings)
      throws ApiException {
    ItemSellerGetResponse itemSellerGetResponse = TaobaoUtils
        .parseResponse(itemJson, ItemSellerGetResponse.class);
    Item item = itemSellerGetResponse.getItem();
    parseItem(store, item, mappings);
  }

  /**
   * 解析item.
   * <p/>
   * 1.如果有sku则对每个sku建立映射关系 2.如果没有sku则只添加product信息
   */
  private void parseItem(Store store, Item item, List<ProductMallMapping> mappings) {
    if (Assert.isEmpty(item.getSkus())) {
      //如果sku为空只保存product信息
      ProductMallMapping mapping = createMapping(item, null);
      setMatchCode(store, mapping);
      mappings.add(mapping);
    } else {
      for (Sku sku : item.getSkus()) {
        ProductMallMapping mapping = createMapping(item, sku);
        setMatchCode(store, mapping);
        mappings.add(mapping);
      }
    }
  }

  private ProductMallMapping createMapping(Item item, Sku sku) {
    ProductMallMapping mapping = new ProductMallMapping();
    mapping.setMallProductId(String.valueOf(item.getNumIid()));
    mapping.setMallProductName(item.getTitle());
    mapping.setMallProductOutCode(item.getOuterId());
    mapping.setMallProductStatus("onsale".equalsIgnoreCase(item.getApproveStatus())
        ? MallProductStatus.ONSALE : MallProductStatus.INSTOCK);
    mapping.setMallSkuQuantity(Math.toIntExact(item.getNum()));
    mapping.setMallSkuPrice(Double.valueOf(item.getPrice()));
    mapping.setImageUrl(item.getPicUrl());
    if (sku != null) {
      final Map<String, String> aliasMap = parsePropertyAlias(item);
      mapping.setMallSkuId(sku.getSkuId().toString());
      mapping.setMallSkuName(parseSkuName(sku, aliasMap));
      mapping.setMallSkuOutCode(sku.getOuterId());
      mapping.setMallSkuQuantity(Math.toIntExact(sku.getQuantity()));
      mapping.setMallSkuPrice(Double.valueOf(sku.getPrice()));
    }
    return mapping;
  }

  private String parseSkuName(Sku sku, Map<String, String> aliasMap) {
    final Matcher matcher = PROPERTY_REGEX.matcher(sku.getPropertiesName());
    StringJoiner joiner = new StringJoiner(" ");
    while (matcher.find()) {
      final String value = matcher.group("value");
      if (aliasMap != null && aliasMap.containsKey(value)) {
        joiner.add(aliasMap.get(value));
      } else {
        joiner.add(matcher.group("name"));
      }
    }
    return joiner.toString();
  }

  protected Map<String, String> parsePropertyAlias(Item item) {
    Map<String, String> map = new HashMap<>();
    if (!Assert.isEmpty(item.getPropertyAlias())) {
      final Matcher matcher = ALIAS_REGEX.matcher(item.getPropertyAlias());
      while (matcher.find()) {
        map.put(matcher.group("value"), matcher.group("name"));
      }
    }
    return map;
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TMALL, MallType.TMALL_HK};
  }

  @Override
  public boolean isSupportMultiStatus() {
    return false;
  }

  @Override
  public String getMallProductUrl(String mallProductId) {
    return "https://item.taobao.com/item.htm?id=" + mallProductId;
  }
}
