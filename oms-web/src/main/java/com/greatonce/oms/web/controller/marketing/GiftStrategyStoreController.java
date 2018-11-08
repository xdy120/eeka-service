package com.greatonce.oms.web.controller.marketing;

import com.greatonce.oms.biz.marketing.GiftStrategyService;
import com.greatonce.oms.biz.marketing.GiftStrategyStoreService;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyStore;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/marketing/gift/strategy/{strategyId}/store")
@CrossOrigin
public class GiftStrategyStoreController {

  @Autowired
  private GiftStrategyService giftStrategyService;
  @Autowired
  private GiftStrategyStoreService giftStrategyStoreService;

  @GetMapping
  public List<GiftStrategyStore> list(@PathVariable("strategyId") Long strategyId) {
    GiftStrategyStore example = new GiftStrategyStore();
    example.setGiftStrategyId(strategyId);
    return giftStrategyStoreService.listExample(example);
  }

  @PostMapping
  public void insert(@PathVariable("strategyId") Long strategyId,
      @RequestBody List<GiftStrategyStore> stores) {
    GiftStrategy strategy = giftStrategyService.getByKey(strategyId);
    giftStrategyService.addStores(strategy, stores);
  }

  @DeleteMapping(path = "/{giftStrategyStoreId}")
  public void remove(@PathVariable("strategyId") Long strategyId,
      @PathVariable("giftStrategyStoreId") Long giftStrategyStoreId) {
    giftStrategyStoreService.remove(giftStrategyStoreService.getByKey(giftStrategyStoreId));
  }
}
