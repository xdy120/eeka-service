package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品退供单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipReturnDetailQuery extends Query {
  /**
   * 箱码.
   */
  private String boxNumber;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
  /**
   * 是否异常.
   */
  private Boolean abnormal;
  /**
   * 唯品价异常.
   */
  private Boolean vipPriceAbnormal;
  /**
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
  /**
   * 通知数量.
   */
  private Integer noticeQuantity;
  /**
   * po编码.
   */
  private String poCode;
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
   * 商品名称.
   */
  private String productName;
  /**
   * 退货数量.
   */
  private Integer returnQuantity;
  /**
   * 扫描数量.
   */
  private Integer scanQuantity;
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
   * 商品规格名称.
   */
  private String skuName;
  /**
   * 金额.
   */
  private Double vipAmount;
  /**
   * 唯品商品编码.
   */
  private String vipBarcode;
  /**
   * 唯品价.
   */
  private Double vipPrice;
  /**
   * 退供单明细id.
   */
  private Long vipReturnDetailId;
  /**
   * 唯品退供单id.
   */
  private Long vipReturnId;


  /**
   * 箱码.
   * @param boxNumber 箱码
   */
  public void setBoxNumber(String boxNumber) {
    this.boxNumber = boxNumber == null ? null : boxNumber.trim();
  }

  /**
   * 箱码.
   * @return 箱码
   */
  public String getBoxNumber() {
      return this.boxNumber;
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
   * 是否异常.
   * @param abnormal 是否异常
   */
  public void setAbnormal(Boolean abnormal) {
    this.abnormal = abnormal;
  }

  /**
   * 是否异常.
   * @return 是否异常
   */
  public Boolean isAbnormal() {
      return this.abnormal;
  }

  /**
   * 唯品价异常.
   * @param vipPriceAbnormal 唯品价异常
   */
  public void setVipPriceAbnormal(Boolean vipPriceAbnormal) {
    this.vipPriceAbnormal = vipPriceAbnormal;
  }

  /**
   * 唯品价异常.
   * @return 唯品价异常
   */
  public Boolean isVipPriceAbnormal() {
      return this.vipPriceAbnormal;
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
   * 通知数量.
   * @param noticeQuantity 通知数量
   */
  public void setNoticeQuantity(Integer noticeQuantity) {
    this.noticeQuantity = noticeQuantity;
  }

  /**
   * 通知数量.
   * @return 通知数量
   */
  public Integer getNoticeQuantity() {
      return this.noticeQuantity;
  }

  /**
   * po编码.
   * @param poCode po编码
   */
  public void setPoCode(String poCode) {
    this.poCode = poCode == null ? null : poCode.trim();
  }

  /**
   * po编码.
   * @return po编码
   */
  public String getPoCode() {
      return this.poCode;
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
   * 退货数量.
   * @param returnQuantity 退货数量
   */
  public void setReturnQuantity(Integer returnQuantity) {
    this.returnQuantity = returnQuantity;
  }

  /**
   * 退货数量.
   * @return 退货数量
   */
  public Integer getReturnQuantity() {
      return this.returnQuantity;
  }

  /**
   * 扫描数量.
   * @param scanQuantity 扫描数量
   */
  public void setScanQuantity(Integer scanQuantity) {
    this.scanQuantity = scanQuantity;
  }

  /**
   * 扫描数量.
   * @return 扫描数量
   */
  public Integer getScanQuantity() {
      return this.scanQuantity;
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
   * 金额.
   * @param vipAmount 金额
   */
  public void setVipAmount(Double vipAmount) {
    this.vipAmount = vipAmount;
  }

  /**
   * 金额.
   * @return 金额
   */
  public Double getVipAmount() {
      return this.vipAmount;
  }

  /**
   * 唯品商品编码.
   * @param vipBarcode 唯品商品编码
   */
  public void setVipBarcode(String vipBarcode) {
    this.vipBarcode = vipBarcode == null ? null : vipBarcode.trim();
  }

  /**
   * 唯品商品编码.
   * @return 唯品商品编码
   */
  public String getVipBarcode() {
      return this.vipBarcode;
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
   * 退供单明细id.
   * @param vipReturnDetailId 退供单明细id
   */
  public void setVipReturnDetailId(Long vipReturnDetailId) {
    this.vipReturnDetailId = vipReturnDetailId;
  }

  /**
   * 退供单明细id.
   * @return 退供单明细id
   */
  public Long getVipReturnDetailId() {
      return this.vipReturnDetailId;
  }

  /**
   * 唯品退供单id.
   * @param vipReturnId 唯品退供单id
   */
  public void setVipReturnId(Long vipReturnId) {
    this.vipReturnId = vipReturnId;
  }

  /**
   * 唯品退供单id.
   * @return 唯品退供单id
   */
  public Long getVipReturnId() {
      return this.vipReturnId;
  }
}