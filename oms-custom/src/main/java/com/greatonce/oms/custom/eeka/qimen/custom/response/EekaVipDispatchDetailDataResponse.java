package com.greatonce.oms.custom.eeka.qimen.custom.response;

import com.greatonce.oms.bridge.wms.qimen.response.OmsQimenCustomResponse;
import java.util.List;

public class EekaVipDispatchDetailDataResponse extends OmsQimenCustomResponse {

  public EekaVipDispatchDetailDataResponse(Long requestId) {
    super(requestId);
  }

  public EekaVipDispatchDetailDataResponse(Long requestId,String message) {
    super(requestId,message);
  }

  private String content;

  private String totalSize;

  private List<String> omsData;

  public List<String> getOmsData() {
    return omsData;
  }

  public void setOmsData(List<String> omsData) {
    this.omsData = omsData;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getTotalSize() {
    return totalSize;
  }

  public void setTotalSize(String totalSize) {
    this.totalSize = totalSize;
  }
}
