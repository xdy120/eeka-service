package com.greatonce.oms.web.controller.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.marketing.GiftStrategyGiftService;
import com.greatonce.oms.biz.marketing.GiftStrategyProductService;
import com.greatonce.oms.biz.marketing.GiftStrategyService;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.query.marketing.GiftStrategyQuery;
import com.greatonce.oms.query.marketing.GiftStrategyGiftQuery;
import com.greatonce.oms.query.marketing.GiftStrategyProductQuery;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/marketing/gift/strategy")
@CrossOrigin
public class GiftStrategyController implements EnableController<GiftStrategy, GiftStrategyQuery>,
    PageListController<GiftStrategy, GiftStrategyQuery> {

  @Autowired
  private GiftStrategyService giftStrategyService;

  @Autowired
  private GiftStrategyProductService giftStrategyProductService;

  @Autowired
  private GiftStrategyGiftService giftStrategyGiftService;

  @Override
  public BizService<GiftStrategy, GiftStrategyQuery> getBizService() {
    return giftStrategyService;
  }

  @PostMapping(path = "{strategyId}/copy")
  public GiftStrategy copy(@PathVariable("strategyId") Long strategyId) {
    GiftStrategy example = giftStrategyService.getByKey(strategyId);
    if (example != null) {
      giftStrategyService.create(example);
    }
    return example;
  }

  @GetMapping(path = "{giftStrategyRuleId}/product")
  public PageList<GiftStrategyProduct> listProduct(
      @PathVariable("giftStrategyRuleId") Long giftStrategyRuleId, GiftStrategyProductQuery filter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    filter.setGiftStrategyRuleId(giftStrategyRuleId);
    return giftStrategyProductService.listPage(filter, page, pageSize);
  }

  @GetMapping(path = "{giftStrategyRuleId}/gift")
  public PageList<GiftStrategyGift> listGift(
      @PathVariable("giftStrategyRuleId") Long giftStrategyRuleId, GiftStrategyGiftQuery filter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    filter.setGiftStrategyRuleId(giftStrategyRuleId);
    return giftStrategyGiftService.listPage(filter, page, pageSize);
  }


  @Override
  @DeleteMapping(path = "{id}")
  public void delete(@PathVariable("id") Long id) {
    giftStrategyProductService.remove(giftStrategyProductService.getByKey(id));
  }

}