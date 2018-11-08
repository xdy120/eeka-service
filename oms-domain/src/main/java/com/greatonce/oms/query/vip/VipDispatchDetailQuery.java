package com.greatonce.oms.query.vip;

import com.greatonce.core.database.Query;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 唯品配货单明细.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class VipDispatchDetailQuery extends Query {
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
   * 出库数量.
   */
  private Integer outQuantity;
  /**
   * po编码.
   */
  private String poCode;
  /**
   * .
   */
  private List<String> poCodes;
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
   * 拣货单明细.
   */
  private Long vipDispatchDetailId;
  /**
   * 配货单id.
   */
  private Long vipDispatchId;
  /**
   * 唯品价.
   */
  private Double vipPrice;


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
   * 出库数量.
   * @param outQuantity 出库数量
   */
  public void setOutQuantity(Integer outQuantity) {
    this.outQuantity = outQuantity;
  }

  /**
   * 出库数量.
   * @return 出库数量
   */
  public Integer getOutQuantity() {
      return this.outQuantity;
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
   * .
   * @param poCodes 
   */
  public void setPoCodes(List<String> poCodes) {
    this.poCodes = poCodes;
  }

  /**
   * .
   * @return 
   */
  public List<String> setPoCodes() {
      return this.poCodes;
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
   * 拣货单明细.
   * @param vipDispatchDetailId 拣货单明细
   */
  public void setVipDispatchDetailId(Long vipDispatchDetailId) {
    this.vipDispatchDetailId = vipDispatchDetailId;
  }

  /**
   * 拣货单明细.
   * @return 拣货单明细
   */
  public Long getVipDispatchDetailId() {
      return this.vipDispatchDetailId;
  }

  /**
   * 配货单id.
   * @param vipDispatchId 配货单id
   */
  public void setVipDispatchId(Long vipDispatchId) {
    this.vipDispatchId = vipDispatchId;
  }

  /**
   * 配货单id.
   * @return 配货单id
   */
  public Long getVipDispatchId() {
      return this.vipDispatchId;
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
}