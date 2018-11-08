package com.greatonce.oms.domain.base.setting;

import java.io.Serializable;

/**
 * 商品配置.
 */
public class ProductSetting implements Serializable {

  /**
   * 自动生成规格编码时，在款号和规格之间加上分隔符.
   */
  private String girardSpecSeparator;

  /**
   * 商品资料在审核时自动推送给WMS系统.
   */
  private Boolean autoPushWms;

  /**
   * 商品图片来源店铺.
   */
  private Long productPicOriginShop;

  public String getGirardSpecSeparator() {
    return girardSpecSeparator;
  }

  public void setGirardSpecSeparator(String girardSpecSeparator) {
    this.girardSpecSeparator = girardSpecSeparator;
  }

  public Boolean isAutoPushWms() {
    return autoPushWms;
  }

  public void setAutoPushWms(Boolean autoPushWms) {
    this.autoPushWms = autoPushWms;
  }

  public Long getProductPicOriginShop() {
    return productPicOriginShop;
  }

  public void setProductPicOriginShop(Long productPicOriginShop) {
    this.productPicOriginShop = productPicOriginShop;
  }
}
