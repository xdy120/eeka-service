package com.greatonce.oms.bo.product;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import java.time.LocalDateTime;

/**
 * 铺货下载BO.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/8
 */
public class ProductMallMappingDownloadBO {

  /**
   * 店铺ID.
   */
  private Long storeId;
  /**
   * 店铺.
   */
  private Store store;
  /**
   * 是否覆盖原有配置.
   */
  private Boolean cover;
  /**
   * 是否自动下架.
   **/
  private Boolean autoDelisting;
  /**
   * 是否自动上架.
   **/
  private Boolean autoListing;
  /**
   * 是否自动上传库存.
   **/
  private Boolean autoUpload;

  /**
   * 是否下载完上传库存.
   */
  private Boolean uploadStockAfterDownload;
  /**
   * 商城商品编码.
   **/
  private String mallProductId;
  /**
   * 商城商品外部编码.
   **/
  private String mallProductOutCode;
  /**
   * 商城商品状态.
   **/
  private MallProductStatus mallProductStatus;
  /**
   * 开始时间.
   */
  private LocalDateTime beginTime;
  /**
   * 结束时间.
   */
  private LocalDateTime endTime;
  /**
   * 操作人
   */
  private String operator;

  public Long getStoreId() {
    return storeId;
  }

  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public Boolean isCover() {
    return cover;
  }

  public void setCover(Boolean cover) {
    this.cover = cover;
  }

  public Boolean isAutoDelisting() {
    return autoDelisting;
  }

  public void setAutoDelisting(Boolean autoDelisting) {
    this.autoDelisting = autoDelisting;
  }

  public Boolean isAutoListing() {
    return autoListing;
  }

  public void setAutoListing(Boolean autoListing) {
    this.autoListing = autoListing;
  }

  public Boolean isAutoUpload() {
    return autoUpload;
  }

  public void setAutoUpload(Boolean autoUpload) {
    this.autoUpload = autoUpload;
  }

  public Boolean isUploadStockAfterDownload() {
    return uploadStockAfterDownload;
  }

  public void setUploadStockAfterDownload(Boolean uploadStockAfterDownload) {
    this.uploadStockAfterDownload = uploadStockAfterDownload;
  }

  public String getMallProductId() {
    return mallProductId;
  }

  public void setMallProductId(String mallProductId) {
    this.mallProductId = mallProductId;
  }

  public String getMallProductOutCode() {
    return mallProductOutCode;
  }

  public void setMallProductOutCode(String mallProductOutCode) {
    this.mallProductOutCode = mallProductOutCode;
  }

  public MallProductStatus getMallProductStatus() {
    return mallProductStatus;
  }

  public void setMallProductStatus(MallProductStatus mallProductStatus) {
    this.mallProductStatus = mallProductStatus;
  }

  public LocalDateTime getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(LocalDateTime beginTime) {
    this.beginTime = beginTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
