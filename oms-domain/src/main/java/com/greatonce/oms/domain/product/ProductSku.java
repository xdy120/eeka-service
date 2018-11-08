package com.greatonce.oms.domain.product;

import com.greatonce.oms.domain.EnableDO;
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
public class ProductSku extends EnableDO {
  /**
   * 条码.
   */
  private String barcode;
  /**
   * 颜色.
   */
  private String color;
  /**
   * 成本价.
   */
  private Double costPrice;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
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
   * 商品规格id.
   */
  private Long skuId;
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
   * 套装明细.
   */
  private List<ProductCombination> details;
  /**
   * 商品信息.
   */
  private Product product;

  @Override
  public void setPrimaryKey(Long pk) {
    this.skuId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.skuId;
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
   * 颜色.
   */
  public void setColor(String color) {
    this.color = color == null ? null : color.trim();
  }

  /**
   * 颜色.
   */
  public String getColor() {
    return this.color;
  }

  /**
   * 成本价.
   */
  public void setCostPrice(Double costPrice) {
    this.costPrice = costPrice;
  }

  /**
   * 成本价.
   */
  public Double getCostPrice() {
    return this.costPrice;
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
   * 分销价.
   */
  public void setDistributionPrice(Double distributionPrice) {
    this.distributionPrice = distributionPrice;
  }

  /**
   * 分销价.
   */
  public Double getDistributionPrice() {
    return this.distributionPrice;
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
   * 是否礼盒.
   */
  public void setGiftBox(Boolean giftBox) {
    this.giftBox = giftBox;
  }

  /**
   * 是否礼盒.
   */
  public Boolean isGiftBox() {
    return this.giftBox;
  }

  /**
   * 是否锁定库存.
   */
  public void setLockStock(Boolean lockStock) {
    this.lockStock = lockStock;
  }

  /**
   * 是否锁定库存.
   */
  public Boolean isLockStock() {
    return this.lockStock;
  }

  /**
   * 是否预包装.
   */
  public void setPrepackage(Boolean prepackage) {
    this.prepackage = prepackage;
  }

  /**
   * 是否预包装.
   */
  public Boolean isPrepackage() {
    return this.prepackage;
  }

  /**
   * 物流保价.
   */
  public void setLogisticsInsurance(Double logisticsInsurance) {
    this.logisticsInsurance = logisticsInsurance;
  }

  /**
   * 物流保价.
   */
  public Double getLogisticsInsurance() {
    return this.logisticsInsurance;
  }

  /**
   * 吊牌价.
   */
  public void setMarkedPrice(Double markedPrice) {
    this.markedPrice = markedPrice;
  }

  /**
   * 吊牌价.
   */
  public Double getMarkedPrice() {
    return this.markedPrice;
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
   * 采购价.
   */
  public void setPurchasePrice(Double purchasePrice) {
    this.purchasePrice = purchasePrice;
  }

  /**
   * 采购价.
   */
  public Double getPurchasePrice() {
    return this.purchasePrice;
  }

  /**
   * 箱规.
   */
  public void setQuantityOfBox(Integer quantityOfBox) {
    this.quantityOfBox = quantityOfBox;
  }

  /**
   * 箱规.
   */
  public Integer getQuantityOfBox() {
    return this.quantityOfBox;
  }

  /**
   * 销售价.
   */
  public void setSellingPrice(Double sellingPrice) {
    this.sellingPrice = sellingPrice;
  }

  /**
   * 销售价.
   */
  public Double getSellingPrice() {
    return this.sellingPrice;
  }

  /**
   * 尺码.
   */
  public void setSize(String size) {
    this.size = size == null ? null : size.trim();
  }

  /**
   * 尺码.
   */
  public String getSize() {
    return this.size;
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
   * 唯品价.
   */
  public void setVipPrice(Double vipPrice) {
    this.vipPrice = vipPrice;
  }

  /**
   * 唯品价.
   */
  public Double getVipPrice() {
    return this.vipPrice;
  }

  /**
   * 体积.
   */
  public void setVolume(Double volume) {
    this.volume = volume;
  }

  /**
   * 体积.
   */
  public Double getVolume() {
    return this.volume;
  }

  /**
   * 重量.
   */
  public void setWeight(Double weight) {
    this.weight = weight;
  }

  /**
   * 重量.
   */
  public Double getWeight() {
    return this.weight;
  }

  /**
   * 套装明细.
   */
  public void setDetails(List<ProductCombination> details) {
    this.details = details;
  }

  /**
   * 套装明细.
   */
  public List<ProductCombination> getDetails() {
    return this.details;
  }

  /**
   * 商品信息.
   */
  public void setProduct(Product product) {
    this.product = product;
  }

  /**
   * 商品信息.
   */
  public Product getProduct() {
    return this.product;
  }
}