package com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyRuleType;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.domain.marketing.GiftStrategyRuleSetting;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 赠品策略规则包装类
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/13
 */
public class GiftStrategyRuleWrapper extends GiftStrategyRule {

  private final GiftStrategyRule rule;
  private final Set<Long> skuIds;
  private final Set<Long> giftIds;
  private final List<GiftStrategyGiftWrapper> giftWrappers;
  private final Map<String, List<GiftStrategyGiftWrapper>> luckBagMap;

  public GiftStrategyRuleWrapper(GiftStrategyRule rule) {
    this.rule = rule;
    skuIds = Assert.isEmpty(rule.getSkus()) ? Collections.emptySet()
        : rule.getSkus().stream().map(GiftStrategyProduct::getSkuId).collect(Collectors.toSet());
    if (!Assert.isEmpty(rule.getGifts())) {
      giftWrappers = new ArrayList<>(rule.getGifts().size());
      giftIds = new HashSet<>(rule.getGifts().size());
      for (GiftStrategyGift giftStrategyGift : rule.getGifts()) {
        giftWrappers.add(new GiftStrategyGiftWrapper(giftStrategyGift));
        giftIds.add(giftStrategyGift.getSkuId());
      }
    } else {
      giftIds = Collections.emptySet();
      giftWrappers = Collections.emptyList();
    }
    if (rule.getRuleType() == GiftStrategyRuleType.LUCK_BAG) {
      luckBagMap = new HashMap<>(2);
      for (GiftStrategyGift gift : rule.getGifts()) {
        if (luckBagMap.containsKey(gift.getLuckyCode())) {
          luckBagMap.get(gift.getLuckyCode()).add(new GiftStrategyGiftWrapper(gift));
        } else {
          luckBagMap.put(gift.getLuckyCode(), new ArrayList<GiftStrategyGiftWrapper>() {{
            add(new GiftStrategyGiftWrapper(gift));
          }});
        }
      }
    } else {
      luckBagMap = Collections.emptyMap();
    }
  }

  public Set<Long> getSkuIds() {
    return skuIds;
  }

  public Set<Long> getGiftIds() {
    return giftIds;
  }

  public Map<String, List<GiftStrategyGiftWrapper>> getLuckBagMap() {
    return luckBagMap;
  }

  public List<GiftStrategyGiftWrapper> getGiftWrappers() {
    return giftWrappers;
  }

  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    rule.setCreatedTime(createdTime);
  }

  @Override
  public LocalDateTime getCreatedTime() {
    return rule.getCreatedTime();
  }

  @Override
  public void setGiftStrategyId(Long giftStrategyId) {
    rule.setGiftStrategyId(giftStrategyId);
  }

  @Override
  public Long getGiftStrategyId() {
    return rule.getGiftStrategyId();
  }

  @Override
  public void setGiftStrategyRuleId(Long giftStrategyRuleId) {
    rule.setGiftStrategyRuleId(giftStrategyRuleId);
  }

  @Override
  public Long getGiftStrategyRuleId() {
    return rule.getGiftStrategyRuleId();
  }

  @Override
  public void setGiftStrategyRuleName(String giftStrategyRuleName) {
    rule.setGiftStrategyRuleName(giftStrategyRuleName);
  }

  @Override
  public String getGiftStrategyRuleName() {
    return rule.getGiftStrategyRuleName();
  }

  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    rule.setModifiedTime(modifiedTime);
  }

  @Override
  public LocalDateTime getModifiedTime() {
    return rule.getModifiedTime();
  }

  @Override
  public void setPriorityNo(Integer priorityNo) {
    rule.setPriorityNo(priorityNo);
  }

  @Override
  public Integer getPriorityNo() {
    return rule.getPriorityNo();
  }

  @Override
  public void setRuleSetting(String ruleSetting) {
    rule.setRuleSetting(ruleSetting);
  }

  @Override
  public String getRuleSetting() {
    return rule.getRuleSetting();
  }

  @Override
  public void setRuleType(GiftStrategyRuleType ruleType) {
    rule.setRuleType(ruleType);
  }

  @Override
  public GiftStrategyRuleType getRuleType() {
    return rule.getRuleType();
  }

  @Override
  public void setGifts(List<GiftStrategyGift> gifts) {
    rule.setGifts(gifts);
  }

  @Override
  public List<GiftStrategyGift> getGifts() {
    return rule.getGifts();
  }

  @Override
  public void setSetting(GiftStrategyRuleSetting setting) {
    rule.setSetting(setting);
  }

  @Override
  public GiftStrategyRuleSetting getSetting() {
    return rule.getSetting();
  }

  @Override
  public void setSkus(List<GiftStrategyProduct> skus) {
    rule.setSkus(skus);
  }

  @Override
  public List<GiftStrategyProduct> getSkus() {
    return rule.getSkus();
  }
}
