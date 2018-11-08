package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.StockQueryRequest;
import java.util.List;

/**
 * StockQueryResponse
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public class StockQueryResponse extends WmsResponse<StockQueryRequest> {

  public StockQueryResponse(StockQueryRequest request) {
    super(request);
  }

  public StockQueryResponse(StockQueryRequest request, List<SkuStock> stocks) {
    super(request);
    this.stocks = stocks;
  }

  public StockQueryResponse(StockQueryRequest request, boolean success, String message) {
    super(request, success, message);
  }

  private List<SkuStock> stocks;

  public List<SkuStock> getStocks() {
    return stocks;
  }

  public void setStocks(List<SkuStock> stocks) {
    this.stocks = stocks;
  }

  public static class SkuStock {

    /**
     * 未冻结库存数量
     */
    private Integer quantity;
    /**
     * 冻结库存数量
     */
    private Integer lockQuantity;
    private String skuCode;
    private String wmsSkuId;

    public Integer getQuantity() {
      return quantity;
    }

    public void setQuantity(Integer quantity) {
      this.quantity = quantity;
    }

    public Integer getLockQuantity() {
      return lockQuantity;
    }

    public void setLockQuantity(Integer lockQuantity) {
      this.lockQuantity = lockQuantity;
    }

    public String getSkuCode() {
      return skuCode;
    }

    public void setSkuCode(String skuCode) {
      this.skuCode = skuCode;
    }

    public String getWmsSkuId() {
      return wmsSkuId;
    }

    public void setWmsSkuId(String wmsSkuId) {
      this.wmsSkuId = wmsSkuId;
    }
  }
}
