package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaDataDictQueryRequest extends OmsQimenCustomRequest {

  private Long dataDictId;

  public Long getDataDictId() {
    return dataDictId;
  }

  public void setDataDictId(Long dataDictId) {
    this.dataDictId = dataDictId;
  }

  @Override
  public String toString() {
    return "QimenDataDictQueryRequest{" +
        "dataDictId='" + dataDictId + '\'' +
        '}';
  }
}
