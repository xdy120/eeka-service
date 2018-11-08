package com.greatonce.oms.domain.product;

import com.greatonce.oms.domain.BaseDO;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import com.greatonce.oms.domain.enums.MarketingType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 铺货关系.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ProductMallMapping extends BaseDO {
  /**
   * 品牌名称.
   */
  private String brandName;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 图片链接.
   */
  private String imageUrl;
  /**
   * 是否自动下架.
   */
  private Boolean autoDelisting;
  /**
   * 是否自动上架.
   */
  private Boolean autoListing;
  /**
   * 是否自动上传库存.
   */
  private Boolean autoUpload;
  /**
   * 是否组合商品.
   */
  private Boolean combination;
  /**
   * 是否代发商品.
   */
  private Boolean dropShipping;
  /**
   * 是否人工关联.
   */
  private Boolean manual;
  /**
   * 是否关联.
   */
  private Boolean matched;
  /**
   * 商城商品编码.
   */
  private String mallProductId;
  /**
   * 商城商品名称.
   */
  private String mallProductName;
  /**
   * 商城商品外部编码.
   */
  private String mallProductOutCode;
  /**
   * 商城商品状态.
   */
  private MallProductStatus mallProductStatus;
  /**
   * 商城规格编码.
   */
  private String mallSkuId;
  /**
   * 商城规格名称.
   */
  private String mallSkuName;
  /**
   * 商城规格外部编码.
   */
  private String mallSkuOutCode;
  /**
   * 商城规格价格.
   */
  private Double mallSkuPrice;
  /**
   * 商城规格库存.
   */
  private Integer mallSkuQuantity;
  /**
   * 商城专有信息.
   */
  private String mallSpecial;
  /**
   * 活动id.
   */
  private Long marketingId;
  /**
   * 活动类型.
   */
  private MarketingType marketingType;
  /**
   * 匹配码.
   */
  private String matchCode;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * 商品id.
   */
  private Long productId;
  /**
   * 商品商城映射id.
   */
  private Long productMallMappingId;
  /**
   * 商品名称.
   */
  private String productName;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * 商品规格名称.
   */
  private String skuName;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 唯一码.
   */
  private String uniqueId;


  @Override
  public void setPrimaryKey(Long pk) {
    this.productMallMappingId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.productMallMappingId;
  }


  /**
   * 品牌名称.
   */
  public void setBrandName(String brandName) {
    this.brandName = brandName == null ? null : brandName.trim();
  }

  /**
   * 品牌名称.
   */
  public String getBrandName() {
    return this.brandName;
  }

  /**
   * 创建时间.
   */
  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  /**
   * 图片链接.
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl == null ? null : imageUrl.trim();
  }

  /**
   * 图片链接.
   */
  public String getImageUrl() {
    return this.imageUrl;
  }

  /**
   * 是否自动下架.
   */
  public void setAutoDelisting(Boolean autoDelisting) {
    this.autoDelisting = autoDelisting;
  }

  /**
   * 是否自动下架.
   */
  public Boolean isAutoDelisting() {
    return this.autoDelisting;
  }

  /**
   * 是否自动上架.
   */
  public void setAutoListing(Boolean autoListing) {
    this.autoListing = autoListing;
  }

  /**
   * 是否自动上架.
   */
  public Boolean isAutoListing() {
    return this.autoListing;
  }

  /**
   * 是否自动上传库存.
   */
  public void setAutoUpload(Boolean autoUpload) {
    this.autoUpload = autoUpload;
  }

  /**
   * 是否自动上传库存.
   */
  public Boolean isAutoUpload() {
    return this.autoUpload;
  }

  /**
   * 是否组合商品.
   */
  public void setCombination(Boolean combination) {
    this.combination = combination;
  }

  /**
   * 是否组合商品.
   */
  public Boolean isCombination() {
    return this.combination;
  }

  /**
   * 是否代发商品.
   */
  public void setDropShipping(Boolean dropShipping) {
    this.dropShipping = dropShipping;
  }

  /**
   * 是否代发商品.
   */
  public Boolean isDropShipping() {
    return this.dropShipping;
  }

  /**
   * 是否人工关联.
   */
  public void setManual(Boolean manual) {
    this.manual = manual;
  }

  /**
   * 是否人工关联.
   */
  public Boolean isManual() {
    return this.manual;
  }

  /**
   * 是否关联.
   */
  public void setMatched(Boolean matched) {
    this.matched = matched;
  }

  /**
   * 是否关联.
   */
  public Boolean isMatched() {
    return this.matched;
  }

  /**
   * 商城商品编码.
   */
  public void setMallProductId(String mallProductId) {
    this.mallProductId = mallProductId == null ? null : mallProductId.trim();
  }

  /**
   * 商城商品编码.
   */
  public String getMallProductId() {
    return this.mallProductId;
  }


  /**
   * 商城商品名称.
   */
  public void setMallProductName(String mallProductName) {
    this.mallProductName = mallProductName == null ? null : mallProductName.trim();
  }

  /**
   * 商城商品名称.
   */
  public String getMallProductName() {
    return this.mallProductName;
  }

  /**
   * 商城商品外部编码.
   */
  public void setMallProductOutCode(String mallProductOutCode) {
    this.mallProductOutCode = mallProductOutCode == null ? null : mallProductOutCode.trim();
  }

  /**
   * 商城商品外部编码.
   */
  public String getMallProductOutCode() {
    return this.mallProductOutCode;
  }


  /**
   * 商城商品状态.
   */
  public void setMallProductStatus(MallProductStatus mallProductStatus) {
    this.mallProductStatus = mallProductStatus;
  }

  /**
   * 商城商品状态.
   */
  public MallProductStatus getMallProductStatus() {
    return this.mallProductStatus;
  }

  /**
   * 商城规格编码.
   */
  public void setMallSkuId(String mallSkuId) {
    this.mallSkuId = mallSkuId == null ? null : mallSkuId.trim();
  }

  /**
   * 商城规格编码.
   */
  public String getMallSkuId() {
    return this.mallSkuId;
  }


  /**
   * 商城规格名称.
   */
  public void setMallSkuName(String mallSkuName) {
    this.mallSkuName = mallSkuName == null ? null : mallSkuName.trim();
  }

  /**
   * 商城规格名称.
   */
  public String getMallSkuName() {
    return this.mallSkuName;
  }

  /**
   * 商城规格外部编码.
   */
  public void setMallSkuOutCode(String mallSkuOutCode) {
    this.mallSkuOutCode = mallSkuOutCode == null ? null : mallSkuOutCode.trim();
  }

  /**
   * 商城规格外部编码.
   */
  public String getMallSkuOutCode() {
    return this.mallSkuOutCode;
  }


  /**
   * 商城规格价格.
   */
  public void setMallSkuPrice(Double mallSkuPrice) {
    this.mallSkuPrice = mallSkuPrice;
  }

  /**
   * 商城规格价格.
   */
  public Double getMallSkuPrice() {
    return this.mallSkuPrice;
  }

  /**
   * 商城规格库存.
   */
  public void setMallSkuQuantity(Integer mallSkuQuantity) {
    this.mallSkuQuantity = mallSkuQuantity;
  }

  /**
   * 商城规格库存.
   */
  public Integer getMallSkuQuantity() {
    return this.mallSkuQuantity;
  }

  /**
   * 商城专有信息.
   */
  public void setMallSpecial(String mallSpecial) {
    this.mallSpecial = mallSpecial == null ? null : mallSpecial.trim();
  }

  /**
   * 商城专有信息.
   */
  public String getMallSpecial() {
    return this.mallSpecial;
  }

  /**
   * 活动id.
   */
  public void setMarketingId(Long marketingId) {
    this.marketingId = marketingId;
  }

  /**
   * 活动id.
   */
  public Long getMarketingId() {
    return this.marketingId;
  }

  /**
   * 活动类型.
   */
  public void setMarketingType(MarketingType marketingType) {
    this.marketingType = marketingType;
  }

  /**
   * 活动类型.
   */
  public MarketingType getMarketingType() {
    return this.marketingType;
  }

  /**
   * 匹配码.
   */
  public void setMatchCode(String matchCode) {
    this.matchCode = matchCode == null ? null : matchCode.trim();
  }

  /**
   * 匹配码.
   */
  public String getMatchCode() {
    return this.matchCode;
  }

  /**
   * 更新时间.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * 商品编码.
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   */
  public String getProductCode() {
    return this.productCode;
  }


  /**
   * 商品id.
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   */
  public Long getProductId() {
    return this.productId;
  }

  /**
   * 商品商城映射id.
   */
  public void setProductMallMappingId(Long productMallMappingId) {
    this.productMallMappingId = productMallMappingId;
  }

  /**
   * 商品商城映射id.
   */
  public Long getProductMallMappingId() {
    return this.productMallMappingId;
  }

  /**
   * 商品名称.
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   */
  public String getProductName() {
    return this.productName;
  }

  /**
   * 商品规格编码.
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   */
  public String getSkuCode() {
    return this.skuCode;
  }


  /**
   * 商品规格id.
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   */
  public Long getSkuId() {
    return this.skuId;
  }

  /**
   * 商品规格名称.
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   */
  public String getSkuName() {
    return this.skuName;
  }

  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }

  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }

  /**
   * 唯一码.
   */
  public void setUniqueId(String uniqueId) {
    this.uniqueId = uniqueId == null ? null : uniqueId.trim();
  }

  /**
   * 唯一码.
   */
  public String getUniqueId() {
    return this.uniqueId;
  }
}