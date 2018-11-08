package com.greatonce.oms.query.product;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 商品规格.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class ProductSkuQuery extends Query {
  /**
   * 条码.
   */
  private String barcode;
  /**
   * .
   */
  private List<String> barcodes;
  /**
   * 颜色.
   */
  private String color;
  /**
   * 成本价.
   */
  private Double costPrice;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 分销价.
   */
  private Double distributionPrice;
  /**
   * 是否组合商品.
   */
  private Boolean combination;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 是否礼盒.
   */
  private Boolean giftBox;
  /**
   * 是否锁定库存.
   */
  private Boolean lockStock;
  /**
   * 是否预包装.
   */
  private Boolean prepackage;
  /**
   * 物流保价.
   */
  private Double logisticsInsurance;
  /**
   * 吊牌价.
   */
  private Double markedPrice;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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
   * 采购价.
   */
  private Double purchasePrice;
  /**
   * 箱规.
   */
  private Integer quantityOfBox;
  /**
   * 销售价.
   */
  private Double sellingPrice;
  /**
   * 尺码.
   */
  private String size;
  /**
   * 商品规格编码.
   */
  private String skuCode;
  /**
   * .
   */
  private List<String> skuCodes;
  /**
   * 商品规格id.
   */
  private Long skuId;
  /**
   * .
   */
  private List<Long> skuIds;
  /**
   * 商品规格名称.
   */
  private String skuName;
  /**
   * 唯品价.
   */
  private Double vipPrice;
  /**
   * 体积.
   */
  private Double volume;
  /**
   * 重量.
   */
  private Double weight;


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
   * 颜色.
   * @param color 颜色
   */
  public void setColor(String color) {
    this.color = color == null ? null : color.trim();
  }

  /**
   * 颜色.
   * @return 颜色
   */
  public String getColor() {
      return this.color;
  }

  /**
   * 成本价.
   * @param costPrice 成本价
   */
  public void setCostPrice(Double costPrice) {
    this.costPrice = costPrice;
  }

  /**
   * 成本价.
   * @return 成本价
   */
  public Double getCostPrice() {
      return this.costPrice;
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
   * 分销价.
   * @param distributionPrice 分销价
   */
  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  /**
   * 分销价.
   * @return 分销价
   */
  public Double getDistributionPrice() {
      return this.distributionPrice;
  }

  /**
   * 是否组合商品.
   * @param combination 是否组合商品
   */
  public void setCombination(Boolean combination) {
    this.combination = combination;
  }

  /**
   * 是否组合商品.
   * @return 是否组合商品
   */
  public Boolean isCombination() {
      return this.combination;
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
   * 是否礼盒.
   * @param giftBox 是否礼盒
   */
  public void setGiftBox(Boolean giftBox) {
    this.giftBox = giftBox;
  }

  /**
   * 是否礼盒.
   * @return 是否礼盒
   */
  public Boolean isGiftBox() {
      return this.giftBox;
  }

  /**
   * 是否锁定库存.
   * @param lockStock 是否锁定库存
   */
  public void setLockStock(Boolean lockStock) {
    this.lockStock = lockStock;
  }

  /**
   * 是否锁定库存.
   * @return 是否锁定库存
   */
  public Boolean isLockStock() {
      return this.lockStock;
  }

  /**
   * 是否预包装.
   * @param prepackage 是否预包装
   */
  public void setPrepackage(Boolean prepackage) {
    this.prepackage = prepackage;
  }

  /**
   * 是否预包装.
   * @return 是否预包装
   */
  public Boolean isPrepackage() {
      return this.prepackage;
  }

  /**
   * 物流保价.
   * @param logisticsInsurance 物流保价
   */
  public void setLogisticsInsurance(Double logisticsInsurance) {
    this.logisticsInsurance = logisticsInsurance;
  }

  /**
   * 物流保价.
   * @return 物流保价
   */
  public Double getLogisticsInsurance() {
      return this.logisticsInsurance;
  }

  /**
   * 吊牌价.
   * @param markedPrice 吊牌价
   */
  public void setMarkedPrice(Double markedPrice) {
    this.markedPrice = markedPrice;
  }

  /**
   * 吊牌价.
   * @return 吊牌价
   */
  public Double getMarkedPrice() {
      return this.markedPrice;
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
   * 采购价.
   * @param purchasePrice 采购价
   */
  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  /**
   * 采购价.
   * @return 采购价
   */
  public Double getPurchasePrice() {
      return this.purchasePrice;
  }

  /**
   * 箱规.
   * @param quantityOfBox 箱规
   */
  public void setQuantityOfBox(Integer quantityOfBox) {
    this.quantityOfBox = quantityOfBox;
  }

  /**
   * 箱规.
   * @return 箱规
   */
  public Integer getQuantityOfBox() {
      return this.quantityOfBox;
  }

  /**
   * 销售价.
   * @param sellingPrice 销售价
   */
  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  /**
   * 销售价.
   * @return 销售价
   */
  public Double getSellingPrice() {
      return this.sellingPrice;
  }

  /**
   * 尺码.
   * @param size 尺码
   */
  public void setSize(String size) {
    this.size = size == null ? null : size.trim();
  }

  /**
   * 尺码.
   * @return 尺码
   */
  public String getSize() {
      return this.size;
  }

  /**
   * 商品规格编码.
   * @param skuCode 商品规格编码
   */
  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode == null ? null : skuCode.trim();
  }

  /**
   * 商品规格编码.
   * @return 商品规格编码
   */
  public String getSkuCode() {
      return this.skuCode;
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
   * 商品规格id.
   * @param skuId 商品规格id
   */
  public void setSkuId(Long skuId) {
    this.skuId = skuId;
  }

  /**
   * 商品规格id.
   * @return 商品规格id
   */
  public Long getSkuId() {
      return this.skuId;
  }

  /**
   * .
   * @param skuIds 
   */
  public void setSkuIds(List<Long> skuIds) {
    this.skuIds = skuIds;
  }

  /**
   * .
   * @return 
   */
  public List<Long> getSkuIds() {
      return this.skuIds;
  }

  /**
   * 商品规格名称.
   * @param skuName 商品规格名称
   */
  public void setSkuName(String skuName) {
    this.skuName = skuName == null ? null : skuName.trim();
  }

  /**
   * 商品规格名称.
   * @return 商品规格名称
   */
  public String getSkuName() {
      return this.skuName;
  }

  /**
   * 唯品价.
   * @param vipPrice 唯品价
   */
  public void setVipPrice(Double vipPrice) {
    this.vipPrice = vipPrice;
  }

  /**
   * 唯品价.
   * @return 唯品价
   */
  public Double getVipPrice() {
      return this.vipPrice;
  }

  /**
   * 体积.
   * @param volume 体积
   */
  public void setVolume(Double volume) {
    this.volume = volume;
  }

  /**
   * 体积.
   * @return 体积
   */
  public Double getVolume() {
      return this.volume;
  }

  /**
   * 重量.
   * @param weight 重量
   */
  public void setWeight(Double weight) {
    this.weight = weight;
  }

  /**
   * 重量.
   * @return 重量
   */
  public Double getWeight() {
      return this.weight;
  }
}