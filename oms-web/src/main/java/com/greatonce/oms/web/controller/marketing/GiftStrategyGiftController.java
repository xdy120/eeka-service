package com.greatonce.oms.web.controller.marketing;

import com.greatonce.oms.biz.marketing.GiftStrategyGiftService;
import com.greatonce.oms.biz.marketing.GiftStrategyRuleService;
import com.greatonce.oms.biz.marketing.GiftStrategyService;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
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
@RequestMapping("/marketing/gift/strategy/{strategyId}/rule/{ruleId}/gift")
@CrossOrigin
public class GiftStrategyGiftController {

  @Autowired
  private GiftStrategyService giftStrategyService;
  @Autowired
  private GiftStrategyRuleService giftStrategyRuleService;
  @Autowired
  private GiftStrategyGiftService giftStrategyGiftService;

  @GetMapping
  public List<GiftStrategyGift> list(@PathVariable("ruleId") Long ruleId) {
    GiftStrategyGift example = new GiftStrategyGift();
    example.setGiftStrategyRuleId(ruleId);
    return giftStrategyGiftService.listExample(example);
  }

  @GetMapping("/{ruleGiftId}")
  public GiftStrategyGift get(@PathVariable("ruleGiftId") Long ruleGiftId) {
    return giftStrategyGiftService.getByKey(ruleGiftId);
  }

  @PostMapping
  public void insert(@PathVariable("strategyId") Long strategyId,
      @PathVariable("ruleId") Long ruleId, @RequestBody List<GiftStrategyGift> gifts) {
    GiftStrategy strategy = giftStrategyService.getByKey(strategyId);
    GiftStrategyRule rule = giftStrategyRuleService.getByKey(ruleId);
    giftStrategyService.addGifts(strategy, rule, gifts);
  }

  @PutMapping(path = "{giftStrategyGiftId}")
  public void modify(@PathVariable("giftStrategyGiftId") Long giftStrategyGiftId,
      @RequestBody GiftStrategyGift gift) {
    gift.setGiftStrategyGiftId(giftStrategyGiftId);
    giftStrategyGiftService.modify(gift);
  }

  @DeleteMapping(path = "{giftStrategyGiftId}")
  public void remove(@PathVariable("giftStrategyGiftId") Long giftStrategyGiftId) {
    giftStrategyGiftService.remove(giftStrategyGiftService.getByKey(giftStrategyGiftId));
  }
}
