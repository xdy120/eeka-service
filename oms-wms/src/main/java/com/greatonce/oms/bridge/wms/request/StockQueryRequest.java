package com.greatonce.oms.bridge.wms.request;


import com.greatonce.oms.domain.base.Warehouse;
import java.util.Collection;
import java.util.List;

/**
 * 查询库存请求
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class StockQueryRequest extends WmsRequest {

  private Collection<String> skuCodes;
  private Collection<String> wmsSkuIds;

  public StockQueryRequest(Warehouse warehouse) {
    super(warehouse);
  }

  public Collection<String> getSkuCodes() {
    return skuCodes;
  }

  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  public Collection<String> getWmsSkuIds() {
    return wmsSkuIds;
  }

  public void setWmsSkuIds(List<String> wmsSkuIds) {
    this.wmsSkuIds = wmsSkuIds;
  }
}
