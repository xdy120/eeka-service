package com.greatonce.oms.bo.trade;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca
 * Date: 2018-06-28
 * Time: 17:40
 * Description:
 */
public class ImageBO {

  private String skuCode;
  private String imageUrl;

  public String getImageUrl() {
    return imageUrl;
  }

  public String getSkuCode() {
    return skuCode;
  }

  public void setSkuCode(String skuCode) {
    this.skuCode = skuCode;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
