package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;
import java.util.List;

public class EekaReturnOrderNoticeRequest extends OmsQimenCustomRequest {

  private Long  virtualWarehouseId;

  private List<String> skuCodes;

  private List<String> boxNos;

  private List<String> brandCodes;

  private List<String> unpackers;

  private String unpackedTimeBegin;

  private String unpackedTimeEnd;

  private String operator;

  public List<String> getSkuCodes() {
    return skuCodes;
  }

  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }


  public List<String> getUnpackers() {
    return unpackers;
  }

  public void setUnpackers(List<String> unpackers) {
    this.unpackers = unpackers;
  }

  public String getUnpackedTimeBegin() {
    return unpackedTimeBegin;
  }

  public void setUnpackedTimeBegin(String unpackedTimeBegin) {
    this.unpackedTimeBegin = unpackedTimeBegin;
  }

  public String getUnpackedTimeEnd() {
    return unpackedTimeEnd;
  }

  public void setUnpackedTimeEnd(String unpackedTimeEnd) {
    this.unpackedTimeEnd = unpackedTimeEnd;
  }

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  public List<String> getBoxNos() {
    return boxNos;
  }

  public void setBoxNos(List<String> boxNos) {
    this.boxNos = boxNos;
  }

  public List<String> getBrandCodes() {
    return brandCodes;
  }

  public void setBrandCodes(List<String> brandCodes) {
    this.brandCodes = brandCodes;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }
}
