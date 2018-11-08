package com.greatonce.oms.bridge.mall.impl.vip.request;

import com.greatonce.oms.bridge.mall.request.OrderQueryRequest;
import com.greatonce.oms.domain.base.Store;

/**
 * @author buer
 * @version 2017-08-24 15:01
 */
public class VipOrderQueryRequest extends OrderQueryRequest {


  //通过店铺和tradeId:交易ID来获取VIP订单查询请求
  public VipOrderQueryRequest(Store store, String tradeId) {
    super(store, tradeId);
  }

  public VipOrderQueryRequest(Store store) {
    super(store);
  }
}
