package com.greatonce.oms.query.product;

import com.greatonce.core.database.Query;
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
public class ProductQuery extends Query {
  /**
   * 属性.
   */
  private String attributesJson;
  /**
   * 条码.
   */
  private String barcode;
  /**
   * .
   */
  private List<String> barcodes;
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
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * .
   */
  private Boolean prepackage;
  /**
   * 是否单个发货.
   */
  private Boolean singleDelivery;
  /**
   * 是否系统.
   */
  private Boolean system;
  /**
   * 上新日期开始.
   */
  private LocalDate listingDateBegin;
  /**
   * 上新日期结束.
   */
  private LocalDate listingDateEnd;
  /**
   * 年份.
   */
  private String listingYear;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * .
   */
  private List<String> productCodes;
  /**
   * 商品id.
   */
  private Long productId;
  /**
   * .
   */
  private List<Long> productIds;
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
   * .
   */
  private List<String> skuCodes;
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
   * 属性.
   * @param attributesJson 属性
   */
  public void setAttributesJson(String attributesJson) {
    this.attributesJson = attributesJson == null ? null : attributesJson.trim();
  }

  /**
   * 属性.
   * @return 属性
   */
  public String getAttributesJson() {
      return this.attributesJson;
  }

  /**
   * 条码.
   * @param barcode 条码
   */
  public void setBarcode(String barcode) {
    this.barcode = barcode == null ? null : barcode.trim();
  }

  /**
   * 条码.
   * @return 条码
   */
  public String getBarcode() {
      return this.barcode;
  }

  /**
   * .
   * @param barcodes 
   */
  public void setBarcodes(List<String> barcodes) {
    this.barcodes = barcodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getBarcodes() {
      return this.barcodes;
  }

  /**
   * 品牌编码.
   * @param brandCode 品牌编码
   */
  public void setBrandCode(String brandCode) {
    this.brandCode = brandCode == null ? null : brandCode.trim();
  }

  /**
   * 品牌编码.
   * @return 品牌编码
   */
  public String getBrandCode() {
      return this.brandCode;
  }

  /**
   * 品牌名称.
   * @param brandName 品牌名称
   */
  public void setBrandName(String brandName) {
    this.brandName = brandName == null ? null : brandName.trim();
  }

  /**
   * 品牌名称.
   * @return 品牌名称
   */
  public String getBrandName() {
      return this.brandName;
  }

  /**
   * 公司id.
   * @param companyId 公司id
   */
  public void setCompanyId(Long companyId) {
    this.companyId = companyId;
  }

  /**
   * 公司id.
   * @return 公司id
   */
  public Long getCompanyId() {
      return this.companyId;
  }

  /**
   * 公司名称.
   * @param companyName 公司名称
   */
  public void setCompanyName(String companyName) {
    this.companyName = companyName == null ? null : companyName.trim();
  }

  /**
   * 公司名称.
   * @return 公司名称
   */
  public String getCompanyName() {
      return this.companyName;
  }

  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 图片链接.
   * @param imageUrl 图片链接
   */
  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl == null ? null : imageUrl.trim();
  }

  /**
   * 图片链接.
   * @return 图片链接
   */
  public String getImageUrl() {
      return this.imageUrl;
  }

  /**
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
  }

  /**
   * 是否赠品.
   * @param gift 是否赠品
   */
  public void setGift(Boolean gift) {
    this.gift = gift;
  }

  /**
   * 是否赠品.
   * @return 是否赠品
   */
  public Boolean isGift() {
      return this.gift;
  }

  /**
   * .
   * @param prepackage 
   */
  public void setPrepackage(Boolean prepackage) {
    this.prepackage = prepackage;
  }

  /**
   * .
   * @return 
   */
  public Boolean isPrepackage() {
      return this.prepackage;
  }

  /**
   * 是否单个发货.
   * @param singleDelivery 是否单个发货
   */
  public void setSingleDelivery(Boolean singleDelivery) {
    this.singleDelivery = singleDelivery;
  }

  /**
   * 是否单个发货.
   * @return 是否单个发货
   */
  public Boolean isSingleDelivery() {
      return this.singleDelivery;
  }

  /**
   * 是否系统.
   * @param system 是否系统
   */
  public void setSystem(Boolean system) {
    this.system = system;
  }

  /**
   * 是否系统.
   * @return 是否系统
   */
  public Boolean isSystem() {
      return this.system;
  }

  /**
   * 上新日期开始.
   * @param listingDateBegin 开始.
   */
  public void setListingDateBegin(LocalDate listingDateBegin) {
    this.listingDateBegin = listingDateBegin;
  }

  /**
   * 上新日期开始.
   * @return 上新日期开始
   */
  public LocalDate getListingDateBegin() {
    return this.listingDateBegin;
  }

  /**
   * 上新日期结束.
   * @param listingDateEnd 结束
   */
  public void setListingDateEnd(LocalDate listingDateEnd) {
    this.listingDateEnd = listingDateEnd;
  }

  /**
   * 上新日期结束.
   * @return 上新日期结束
   */
  public LocalDate getListingDateEnd() {
    return this.listingDateEnd;
  }

  /**
   * 年份.
   * @param listingYear 年份
   */
  public void setListingYear(String listingYear) {
    this.listingYear = listingYear == null ? null : listingYear.trim();
  }

  /**
   * 年份.
   * @return 年份
   */
  public String getListingYear() {
      return this.listingYear;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * 分类id.
   * @param productCategoryId 分类id
   */
  public void setProductCategoryId(Long productCategoryId) {
    this.productCategoryId = productCategoryId;
  }

  /**
   * 分类id.
   * @return 分类id
   */
  public Long getProductCategoryId() {
      return this.productCategoryId;
  }

  /**
   * 分类名称.
   * @param productCategoryName 分类名称
   */
  public void setProductCategoryName(String productCategoryName) {
    this.productCategoryName = productCategoryName == null ? null : productCategoryName.trim();
  }

  /**
   * 分类名称.
   * @return 分类名称
   */
  public String getProductCategoryName() {
      return this.productCategoryName;
  }

  /**
   * 商品编码.
   * @param productCode 商品编码
   */
  public void setProductCode(String productCode) {
    this.productCode = productCode == null ? null : productCode.trim();
  }

  /**
   * 商品编码.
   * @return 商品编码
   */
  public String getProductCode() {
      return this.productCode;
  }

  /**
   * .
   * @param productCodes 
   */
  public void setProductCodes(List<String> productCodes) {
    this.productCodes = productCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getProductCodes() {
      return this.productCodes;
  }

  /**
   * 商品id.
   * @param productId 商品id
   */
  public void setProductId(Long productId) {
    this.productId = productId;
  }

  /**
   * 商品id.
   * @return 商品id
   */
  public Long getProductId() {
      return this.productId;
  }

  /**
   * .
   * @param productIds 
   */
  public void setProductIds(List<Long> productIds) {
    this.productIds = productIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getProductIds() {
      return this.productIds;
  }

  /**
   * 商品名称.
   * @param productName 商品名称
   */
  public void setProductName(String productName) {
    this.productName = productName == null ? null : productName.trim();
  }

  /**
   * 商品名称.
   * @return 商品名称
   */
  public String getProductName() {
      return this.productName;
  }

  /**
   * 商品简称.
   * @param productShortName 商品简称
   */
  public void setProductShortName(String productShortName) {
    this.productShortName = productShortName == null ? null : productShortName.trim();
  }

  /**
   * 商品简称.
   * @return 商品简称
   */
  public String getProductShortName() {
      return this.productShortName;
  }

  /**
   * 商品推送状态.
   * @param productSyncStatus 商品推送状态
   */
  public void setProductSyncStatus(ProductSyncStatus productSyncStatus) {
    this.productSyncStatus = productSyncStatus;
  }

  /**
   * 商品推送状态.
   * @return 商品推送状态
   */
  public ProductSyncStatus getProductSyncStatus() {
      return this.productSyncStatus;
  }

  /**
   * 商品类型.
   * @param productType 商品类型
   */
  public void setProductType(ProductType productType) {
    this.productType = productType;
  }

  /**
   * 商品类型.
   * @return 商品类型
   */
  public ProductType getProductType() {
      return this.productType;
  }

  /**
   * 季节.
   * @param season 季节
   */
  public void setSeason(String season) {
    this.season = season == null ? null : season.trim();
  }

  /**
   * 季节.
   * @return 季节
   */
  public String getSeason() {
      return this.season;
  }

  /**
   * .
   * @param skuCodes 
   */
  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> getSkuCodes() {
      return this.skuCodes;
  }

  /**
   * 供应商id.
   * @param supplierId 供应商id
   */
  public void setSupplierId(Long supplierId) {
    this.supplierId = supplierId;
  }

  /**
   * 供应商id.
   * @return 供应商id
   */
  public Long getSupplierId() {
      return this.supplierId;
  }

  /**
   * 供应商名称.
   * @param supplierName 供应商名称
   */
  public void setSupplierName(String supplierName) {
    this.supplierName = supplierName == null ? null : supplierName.trim();
  }

  /**
   * 供应商名称.
   * @return 供应商名称
   */
  public String getSupplierName() {
      return this.supplierName;
  }

  /**
   * 单位.
   * @param unit 单位
   */
  public void setUnit(String unit) {
    this.unit = unit == null ? null : unit.trim();
  }

  /**
   * 单位.
   * @return 单位
   */
  public String getUnit() {
      return this.unit;
  }

  /**
   * 保质期.
   * @param warrantyPeriod 保质期
   */
  public void setWarrantyPeriod(Integer warrantyPeriod) {
    this.warrantyPeriod = warrantyPeriod;
  }

  /**
   * 保质期.
   * @return 保质期
   */
  public Integer getWarrantyPeriod() {
      return this.warrantyPeriod;
  }

  /**
   * 波段.
   * @param waveband 波段
   */
  public void setWaveband(String waveband) {
    this.waveband = waveband == null ? null : waveband.trim();
  }

  /**
   * 波段.
   * @return 波段
   */
  public String getWaveband() {
      return this.waveband;
  }
}