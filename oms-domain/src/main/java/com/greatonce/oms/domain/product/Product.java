package com.greatonce.oms.domain.product;

import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.domain.enums.product.ProductSyncStatus;
import com.greatonce.oms.domain.enums.product.ProductType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class Product extends EnableDO {
  /**
   * 属性.
   */
  private String attributesJson;
  /**
   * 条码.
   */
  private String barcode;
  /**
   * 品牌编码.
   */
  private String brandCode;
  /**
   * 品牌名称.
   */
  private String brandName;
  /**
   * 公司id.
   */
  private Long companyId;
  /**
   * 公司名称.
   */
  private String companyName;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 图片链接.
   */
  private String imageUrl;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 是否赠品.
   */
  private Boolean gift;
  /**
   * 是否单个发货.
   */
  private Boolean singleDelivery;
  /**
   * 是否系统.
   */
  private Boolean system;
  /**
   * 上新日期.
   */
  private LocalDate listingDate;
  /**
   * 年份.
   */
  private String listingYear;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 分类id.
   */
  private Long productCategoryId;
  /**
   * 分类名称.
   */
  private String productCategoryName;
  /**
   * 商品编码.
   */
  private String productCode;
  /**
   * 商品id.
   */
  private Long productId;
  /**
   * 商品名称.
   */
  private String productName;
  /**
   * 商品简称.
   */
  private String productShortName;
  /**
   * 商品推送状态.
   */
  private ProductSyncStatus productSyncStatus;
  /**
   * 商品类型.
   */
  private ProductType productType;
  /**
   * 季节.
   */
  private String season;
  /**
   * 供应商id.
   */
  private Long supplierId;
  /**
   * 供应商名称.
   */
  private String supplierName;
  /**
   * 单位.
   */
  private String unit;
  /**
   * 保质期.
   */
  private Integer warrantyPeriod;
  /**
   * 波段.
   */
  private String waveband;

  /**
   * 规格信息.
   */
  private List<ProductSku> skus;

  @Override
  public void setPrimaryKey(Long pk) {
    this.productId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.productId;
  }


  /**
   * 属性.
   */
  public void setAttributesJson(String attributesJson) {
    this.attributesJson = attributesJson == null ? null : attributesJson.trim();
  }

  /**
   * 属性.
   */
  public String getAttributesJson() {
    return this.attributesJson;
  }

  /**
   * 条码.
   */
  public void setBarcode(String barcode) {
    this.barcode = barcode == null ? null : barcode.trim();
  }

  /**
   * 条码.
   */
  public String getBarcode() {
    return this.barcode;
  }


  /**
   * 品牌编码.
   */
  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode == null ? null : brandCode.trim();
  }

  /**
   * 品牌编码.
   */
  public String getBrandCode() {
    return this.brandCode;
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
   * 公司id.
   */
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  /**
   * 公司id.
   */
  public Long getCompanyId() {
    return this.companyId;
  }

  /**
   * 公司名称.
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName == null ? null : companyName.trim();
  }

  /**
   * 公司名称.
   */
  public String getCompanyName() {
    return this.companyName;
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
   * 是否启用.
   */
  @Override
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  @Override
  public Boolean isEnable() {
    return this.enable;
  }

  /**
   * 是否赠品.
   */
  public void setGift(Boolean gift) {
    this.gift = gift;
  }

  /**
   * 是否赠品.
   */
  public Boolean isGift() {
    return this.gift;
  }


  /**
   * 是否单个发货.
   */
  public void setSingleDelivery(Boolean singleDelivery) {
    this.singleDelivery = singleDelivery;
  }

  /**
   * 是否单个发货.
   */
  public Boolean isSingleDelivery() {
    return this.singleDelivery;
  }

  /**
   * 是否系统.
   */
  public void setSystem(Boolean system) {
    this.system = system;
  }

  /**
   * 是否系统.
   */
  public Boolean isSystem() {
    return this.system;
  }

  /**
   * 上新日期.
   */
  public void setListingDate(LocalDate listingDate) {
    this.listingDate = listingDate;
  }

  /**
   * 上新日期.
   */
  public LocalDate getListingDate() {
    return this.listingDate;
  }

  /**
   * 年份.
   */
  public void setListingYear(String listingYear) {
    this.listingYear = listingYear == null ? null : listingYear.trim();
  }

  /**
   * 年份.
   */
  public String getListingYear() {
    return this.listingYear;
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
   * 分类id.
   */
  public void setProductCategoryId(Long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  /**
   * 分类id.
   */
  public Long getProductCategoryId() {
    return this.productCategoryId;
  }

  /**
   * 分类名称.
   */
  public void setProductCategoryName(String productCategoryName) {
    this.productCategoryName = productCategoryName == null ? null : productCategoryName.trim();
  }

  /**
   * 分类名称.
   */
  public String getProductCategoryName() {
    return this.productCategoryName;
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
   * 商品简称.
   */
  public void setProductShortName(String productShortName) {
    this.productShortName = productShortName == null ? null : productShortName.trim();
  }

  /**
   * 商品简称.
   */
  public String getProductShortName() {
    return this.productShortName;
  }

  /**
   * 商品推送状态.
   */
  public void setProductSyncStatus(ProductSyncStatus productSyncStatus) {
    this.productSyncStatus = productSyncStatus;
  }

  /**
   * 商品推送状态.
   */
  public ProductSyncStatus getProductSyncStatus() {
    return this.productSyncStatus;
  }

  /**
   * 商品类型.
   */
  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  /**
   * 商品类型.
   */
  public ProductType getProductType() {
    return this.productType;
  }

  /**
   * 季节.
   */
  public void setSeason(String season) {
    this.season = season == null ? null : season.trim();
  }

  /**
   * 季节.
   */
  public String getSeason() {
    return this.season;
  }


  /**
   * 供应商id.
   */
  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  /**
   * 供应商id.
   */
  public Long getSupplierId() {
    return this.supplierId;
  }

  /**
   * 供应商名称.
   */
  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName == null ? null : supplierName.trim();
  }

  /**
   * 供应商名称.
   */
  public String getSupplierName() {
    return this.supplierName;
  }

  /**
   * 单位.
   */
  public void setUnit(String unit) {
    this.unit = unit == null ? null : unit.trim();
  }

  /**
   * 单位.
   */
  public String getUnit() {
    return this.unit;
  }

  /**
   * 保质期.
   */
  public void setWarrantyPeriod(Integer warrantyPeriod) {
    this.warrantyPeriod = warrantyPeriod;
  }

  /**
   * 保质期.
   */
  public Integer getWarrantyPeriod() {
    return this.warrantyPeriod;
  }

  /**
   * 波段.
   */
  public void setWaveband(String waveband) {
    this.waveband = waveband == null ? null : waveband.trim();
  }

  /**
   * 波段.
   */
  public String getWaveband() {
    return this.waveband;
  }

  /**
   * 规格信息.
   */
  public void setSkus(List<ProductSku> skus) {
    this.skus = skus;
  }

  /**
   * 规格信息.
   */
  public List<ProductSku> getSkus() {
    return this.skus;
  }
}