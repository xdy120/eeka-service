package com.greatonce.oms.consumer.trade.translator.order.impl.marketing.wrapper;

import com.greatonce.oms.domain.enums.marketing.GiftStrategyType;
import com.greatonce.oms.domain.enums.marketing.TimeType;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.domain.marketing.GiftStrategyStore;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 活动包装类
 * ActivityStrategyWrapper
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/13
 */
public class GiftStrategyWrapper extends GiftStrategy {

  private final GiftStrategy strategy;
  private final Set<Long> storeIds;
  private final List<GiftStrategyRuleWrapper> ruleWrappers;
  /**
   * 赠品缺货
   */
  private boolean oos;

  public GiftStrategyWrapper(GiftStrategy strategy) {
    this.strategy = strategy;
    this.oos = false;
    storeIds = strategy.getStores().stream().map(GiftStrategyStore::getStoreId)
        .collect(Collectors.toSet());
    ruleWrappers = strategy.getRules().stream()
        .sorted(Comparator.comparing(GiftStrategyRule::getPriorityNo))
        .map(GiftStrategyRuleWrapper::new).collect(Collectors.toList());
  }

  public Set<Long> getStoreIds() {
    return storeIds;
  }

  public List<GiftStrategyRuleWrapper> getRuleWrappers() {
    return ruleWrappers;
  }


  public boolean isOos() {
    return oos;
  }

  public void setOos(boolean oos) {
    this.oos = oos;
  }

  @Override
  public void setBeginTime(LocalDateTime beginTime) {
    strategy.setBeginTime(beginTime);
  }

  @Override
  public LocalDateTime getBeginTime() {
    return strategy.getBeginTime();
  }

  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    strategy.setCreatedTime(createdTime);
  }

  @Override
  public LocalDateTime getCreatedTime() {
    return strategy.getCreatedTime();
  }

  @Override
  public void setEndTime(LocalDateTime endTime) {
    strategy.setEndTime(endTime);
  }

  @Override
  public LocalDateTime getEndTime() {
    return strategy.getEndTime();
  }

  @Override
  public void setGiftStrategyCode(String giftStrategyCode) {
    strategy.setGiftStrategyCode(giftStrategyCode);
  }

  @Override
  public String getGiftStrategyCode() {
    return strategy.getGiftStrategyCode();
  }

  @Override
  public void setGiftStrategyId(Long giftStrategyId) {
    strategy.setGiftStrategyId(giftStrategyId);
  }

  @Override
  public Long getGiftStrategyId() {
    return strategy.getGiftStrategyId();
  }

  @Override
  public void setGiftStrategyName(String giftStrategyName) {
    strategy.setGiftStrategyName(giftStrategyName);
  }

  @Override
  public String getGiftStrategyName() {
    return strategy.getGiftStrategyName();
  }

  @Override
  public void setGiftStrategyType(GiftStrategyType giftStrategyType) {
    strategy.setGiftStrategyType(giftStrategyType);
  }

  @Override
  public GiftStrategyType getGiftStrategyType() {
    return strategy.getGiftStrategyType();
  }

  @Override
  public void setEnable(Boolean enable) {
    strategy.setEnable(enable);
  }

  @Override
  public Boolean isEnable() {
    return strategy.isEnable();
  }

  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    strategy.setModifiedTime(modifiedTime);
  }

  @Override
  public LocalDateTime getModifiedTime() {
    return strategy.getModifiedTime();
  }

  @Override
  public void setPriorityNo(Integer priorityNo) {
    strategy.setPriorityNo(priorityNo);
  }

  @Override
  public Integer getPriorityNo() {
    return strategy.getPriorityNo();
  }

  @Override
  public void setTimeType(TimeType timeType) {
    strategy.setTimeType(timeType);
  }

  @Override
  public TimeType getTimeType() {
    return strategy.getTimeType();
  }

  @Override
  public void setRules(List<GiftStrategyRule> rules) {
    strategy.setRules(rules);
  }

  @Override
  public List<GiftStrategyRule> getRules() {
    return strategy.getRules();
  }

  @Override
  public void setStores(List<GiftStrategyStore> stores) {
    strategy.setStores(stores);
  }

  @Override
  public List<GiftStrategyStore> getStores() {
    return strategy.getStores();
  }
}
