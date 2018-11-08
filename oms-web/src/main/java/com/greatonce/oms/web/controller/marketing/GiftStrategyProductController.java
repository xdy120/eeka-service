package com.greatonce.oms.web.controller.marketing;

import com.greatonce.oms.biz.marketing.GiftStrategyProductService;
import com.greatonce.oms.biz.marketing.GiftStrategyRuleService;
import com.greatonce.oms.biz.marketing.GiftStrategyService;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
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
@RequestMapping("/marketing/gift/strategy/{strategyId}/rule/{ruleId}/product")
@CrossOrigin
public class GiftStrategyProductController {

  @Autowired
  private GiftStrategyService giftStrategyService;
  @Autowired
  private GiftStrategyRuleService giftStrategyRuleService;
  @Autowired
  private GiftStrategyProductService giftStrategyProductService;

  @GetMapping
  public List<GiftStrategyProduct> list(@PathVariable("ruleId") Long ruleId) {
    GiftStrategyProduct example = new GiftStrategyProduct();
    example.setGiftStrategyRuleId(ruleId);
    return giftStrategyProductService.listExample(example);
  }

  @GetMapping("/{ruleGiftId}")
  public GiftStrategyProduct get(@PathVariable("ruleProductId") Long ruleProductId) {
    return giftStrategyProductService.getByKey(ruleProductId);
  }

  @PostMapping
  public void insert(@PathVariable("strategyId") Long strategyId,
      @PathVariable("ruleId") Long ruleId, @RequestBody List<GiftStrategyProduct> products) {
    GiftStrategy strategy = giftStrategyService.getByKey(strategyId);
    GiftStrategyRule rule = giftStrategyRuleService.getByKey(ruleId);
    giftStrategyService.addProducts(strategy, rule, products);
  }

  @PutMapping(path = "{giftStrategyProductId}")
  public void modify(@PathVariable("giftStrategyProductId") Long ruleProductId,
      @RequestBody GiftStrategyProduct product) {
    product.setGiftStrategyProductId(ruleProductId);
    giftStrategyProductService.modify(product);
  }

  @DeleteMapping(path = "{giftStrategyProductId}")
  public void remove(@PathVariable("giftStrategyProductId") Long giftStrategyProductId) {
    giftStrategyProductService.remove(giftStrategyProductService.getByKey(giftStrategyProductId));
  }

}
