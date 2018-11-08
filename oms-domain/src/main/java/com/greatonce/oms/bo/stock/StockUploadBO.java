package com.greatonce.oms.bo.stock;

/**
 * 库存上传BO.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-08-27
 */
public class StockUploadBO {

  private Long configId;
  private String batchNo;

  public Long getConfigId() {
    return configId;
  }

  public void setConfigId(Long configId) {
    this.configId = configId;
  }

  public String getBatchNo() {
    return batchNo;
  }

  public void setBatchNo(String batchNo) {
    this.batchNo = batchNo;
  }
}
