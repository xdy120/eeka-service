package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.StoreAddressQueryRequest;
import com.taobao.api.domain.AddressResult;

import java.util.List;

/**
 * company:Shenzhen Greatonce Co Ltd
 * author:buer
 * create date:2017/5/23
 * remark:
 */
public class StoreAddressQueryResponse extends MallResponse<StoreAddressQueryRequest> {

  private List<AddressResult> address;

  public StoreAddressQueryResponse(StoreAddressQueryRequest request) {
    super(request);
  }

  public StoreAddressQueryResponse(StoreAddressQueryRequest request, List<AddressResult> orders) {
    super(request);
    this.address = orders;
  }

  public StoreAddressQueryResponse(StoreAddressQueryRequest request, boolean isSuccess,
      String result) {
    super(request, isSuccess, result);
  }

  public List<AddressResult> getAddress() {
    return address;
  }

  public void setAddress(List<AddressResult> address) {
    this.address = address;
  }

  public Integer getCount() {
    return address == null ? 0 : address.size();
  }
}
