package com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper;


import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import java.time.LocalDateTime;


/**
 * 活动商品包装类
 *
 * @author ginta
 */
public class GiftStrategyGiftWrapper extends GiftStrategyGift {

  /**
   * 赠品
   */
  private final GiftStrategyGift sendProduct;
  /**
   * 赠品剩余数量
   */
  private int surplusQuantity;

  public GiftStrategyGiftWrapper(GiftStrategyGift sendProduct) {
    this.sendProduct = sendProduct;
    surplusQuantity = sendProduct.getPlanQuantity() - sendProduct.getSentQuantity();
  }

  /**
   * 赠送赠品，减少剩余数量，增加已赠送数量
   */
  public int give(int quantity) {
    synchronized (this) {
      int min = Math.min(quantity, surplusQuantity);
      this.surplusQuantity -= min;
      super.setSentQuantity(this.getSentQuantity() + min);
      return min;
    }
  }

  /**
   * 回收赠品，增加剩余数量，减少已赠送数量
   */
  public void withdraw(int quantity) {
    synchronized (this) {
      this.surplusQuantity += quantity;
      super.setAlertQuantity(this.getAlertQuantity() - quantity);
    }
  }

  public GiftStrategyGift getSendProduct() {
    return sendProduct;
  }


  public int getSurplusQuantity() {
    return surplusQuantity;
  }

  @Override
  public Integer getQuantity() {
    synchronized (this) {
      return sendProduct.getQuantity();
    }
  }

  @Override
  public void setSentQuantity(Integer sentQuantity) {
    sendProduct.setSentQuantity(sentQuantity);
  }

  @Override
  public Integer getSentQuantity() {
    return sendProduct.getSentQuantity();
  }

  @Override
  public void setSkuCode(String skuCode) {
    sendProduct.setSkuCode(skuCode);
  }

  @Override
  public String getSkuCode() {
    return sendProduct.getSkuCode();
  }

  @Override
  public void setSkuId(Long skuId) {
    sendProduct.setSkuId(skuId);
  }

  @Override
  public Long getSkuId() {
    return sendProduct.getSkuId();
  }

  @Override
  public void setSkuName(String skuName) {
    sendProduct.setSkuName(skuName);
  }

  @Override
  public String getSkuName() {
    return sendProduct.getSkuName();
  }

  @Override
  public void setQuantity(Integer quantity) {
    synchronized (this) {
      sendProduct.setQuantity(quantity);
    }
  }

  @Override
  public void setAlertQuantity(Integer alertQuantity) {
    sendProduct.setAlertQuantity(alertQuantity);
  }

  @Override
  public Integer getAlertQuantity() {
    return sendProduct.getAlertQuantity();
  }

  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    sendProduct.setCreatedTime(createdTime);
  }

  @Override
  public LocalDateTime getCreatedTime() {
    return sendProduct.getCreatedTime();
  }

  @Override
  public void setGiftStrategyGiftId(Long giftStrategyGiftId) {
    sendProduct.setGiftStrategyGiftId(giftStrategyGiftId);
  }

  @Override
  public Long getGiftStrategyGiftId() {
    return sendProduct.getGiftStrategyGiftId();
  }

  @Override
  public void setGiftStrategyRuleId(Long giftStrategyRuleId) {
    sendProduct.setGiftStrategyRuleId(giftStrategyRuleId);
  }

  @Override
  public Long getGiftStrategyRuleId() {
    return sendProduct.getGiftStrategyRuleId();
  }

  @Override
  public void setCombination(Boolean combination) {
    sendProduct.setCombination(combination);
  }

  @Override
  public Boolean isCombination() {
    return sendProduct.isCombination();
  }

  @Override
  public void setLuckyCode(String luckyCode) {
    sendProduct.setLuckyCode(luckyCode);
  }

  @Override
  public String getLuckyCode() {
    return sendProduct.getLuckyCode();
  }

  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    sendProduct.setModifiedTime(modifiedTime);
  }

  @Override
  public LocalDateTime getModifiedTime() {
    return sendProduct.getModifiedTime();
  }

  @Override
  public void setPlanQuantity(Integer planQuantity) {
    sendProduct.setPlanQuantity(planQuantity);
  }

  @Override
  public Integer getPlanQuantity() {
    return sendProduct.getPlanQuantity();
  }

  @Override
  public void setProductCode(String productCode) {
    sendProduct.setProductCode(productCode);
  }

  @Override
  public String getProductCode() {
    return sendProduct.getProductCode();
  }

  @Override
  public void setProductId(Long productId) {
    sendProduct.setProductId(productId);
  }

  @Override
  public Long getProductId() {
    return sendProduct.getProductId();
  }

  @Override
  public void setProductName(String productName) {
    sendProduct.setProductName(productName);
  }

  @Override
  public String getProductName() {
    return sendProduct.getProductName();
  }

  @Override
  public void setSize(String size) {
    sendProduct.setSize(size);
  }

  @Override
  public String getSize() {
    return sendProduct.getSize();
  }
}
