package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.oms.bridge.mall.impl.AbstractStoreBridge;
import com.greatonce.oms.bridge.mall.request.StoreAddressQueryRequest;
import com.greatonce.oms.bridge.mall.response.StoreAddressQueryResponse;
import com.greatonce.oms.domain.enums.MallType;
import com.taobao.api.domain.AddressResult;
import com.taobao.api.request.LogisticsAddressSearchRequest;
import com.taobao.api.response.LogisticsAddressSearchResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TaobaoStoreBridge extends AbstractStoreBridge {

  @Autowired
  private TaobaoMall mall;

  @Override
  protected StoreAddressQueryResponse doQueryAddress(StoreAddressQueryRequest request) {
    LogisticsAddressSearchRequest request1 = new LogisticsAddressSearchRequest();
    LogisticsAddressSearchResponse addressSearchResponse = mall.call(request.getStore(), request1);
    List<AddressResult> addresses = addressSearchResponse.getAddresses();
    return new StoreAddressQueryResponse(request, addresses);
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TMALL, MallType.TMALL_HK, MallType.TAOBAO_FX,
        MallType.TAOBAO_JX};
  }
}
