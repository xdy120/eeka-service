package com.greatonce.oms.web.controller.marketing;

import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.marketing.GiftStrategyRuleService;
import com.greatonce.oms.biz.marketing.GiftStrategyService;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.domain.marketing.GiftStrategyRuleSetting;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketing/gift/strategy/{strategyId}/rule")
@CrossOrigin
public class GiftStrategyRuleController {

  @Autowired
  private GiftStrategyService giftStrategyService;
  @Autowired
  private GiftStrategyRuleService giftStrategyRuleService;

  @GetMapping
  public List<GiftStrategyRule> list(@PathVariable("strategyId") Long strategyId) {
    GiftStrategyRule eg = new GiftStrategyRule();
    eg.setGiftStrategyId(strategyId);
    List<GiftStrategyRule> giftStrategyRules = giftStrategyRuleService.listExample(eg);
    giftStrategyRules.forEach(
        x -> x.setSetting(JsonUtil.toObject(x.getRuleSetting(), GiftStrategyRuleSetting.class)));
    return giftStrategyRules;
  }

  @GetMapping("/{ruleId}")
  public GiftStrategyRule get(@PathVariable("ruleId") Long ruleId) {
    return giftStrategyRuleService.getByKey(ruleId);
  }

  @PostMapping
  public void insert(@PathVariable("strategyId") Long strategyId,
      @RequestBody GiftStrategyRule rule) {
    GiftStrategy strategy = giftStrategyService.getByKey(strategyId);
    giftStrategyService.addRule(strategy, rule);
  }

  @PutMapping("{ruleId}")
  public void update(@PathVariable("strategyId") Long strategyId,
      @RequestBody GiftStrategyRule rule) {
    GiftStrategy strategy = giftStrategyService.getByKey(strategyId);
    giftStrategyService.updateRule(strategy, rule);
  }

  @DeleteMapping("/{giftStrategyRuleId}")
  public void remove(@PathVariable("strategyId") Long strategyId,
      @PathVariable("giftStrategyRuleId") Long giftStrategyRuleId) {
    giftStrategyRuleService.remove(giftStrategyRuleService.getByKey(giftStrategyRuleId));
  }

}
