package com.greatonce.oms.bo.stock;

import com.greatonce.oms.domain.enums.stock.StockUploadType;

/**
 * 同步上传SKU业务类.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-08-27
 */
public class SyncStockUploadUploadSkuBO extends StockUploadSkuBO {

  private Long detailId;
  private StockUploadType uploadType;
  private String msg;
  private String mallProductId;
  private Boolean success;

  public Long getDetailId() {
    return detailId;
  }

  public void setDetailId(Long detailId) {
    this.detailId = detailId;
  }

  public StockUploadType getUploadType() {
    return uploadType;
  }

  public void setUploadType(StockUploadType uploadType) {
    this.uploadType = uploadType;
  }

  public Boolean isSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public String getMallProductId() {
    return mallProductId;
  }

  public void setMallProductId(String mallProductId) {
    this.mallProductId = mallProductId;
  }
}
