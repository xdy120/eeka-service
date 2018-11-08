package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;
import java.util.List;

/**
 * 动销比请求
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaSkuSellRatioSynchronizeRequest extends OmsQimenCustomRequest {

  private String warehouseCode;
  private List<SkuRatio> skus;

  public String getWarehouseCode() {
    return warehouseCode;
  }

  public void setWarehouseCode(String warehouseCode) {
    this.warehouseCode = warehouseCode;
  }

  public List<SkuRatio> getSkus() {
    return skus;
  }

  public void setSkus(List<SkuRatio> skus) {
    this.skus = skus;
  }

  public static class SkuRatio {

    private String skuCode;
    private Integer ratio;

    public String getSkuCode() {
      return skuCode;
    }

    public void setSkuCode(String skuCode) {
      this.skuCode = skuCode;
    }

    public Integer getRatio() {
      return ratio;
    }

    public void setRatio(Integer ratio) {
      this.ratio = ratio;
    }

    @Override
    public String toString() {
      return "skus{" +
          ", skuCode='" + skuCode + '\'' +
          ", ratio=" + ratio +
          '}';
    }
  }

}
