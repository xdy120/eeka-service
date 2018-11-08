package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.biz.marketing.GiftStrategyProductService;
import com.greatonce.oms.biz.marketing.GiftStrategyRuleService;
import com.greatonce.oms.biz.marketing.GiftStrategyService;
import com.greatonce.oms.biz.marketing.GiftStrategyStoreService;
import com.greatonce.oms.dao.marketing.GiftStrategyDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.domain.marketing.GiftStrategyStore;
import com.greatonce.oms.query.marketing.GiftStrategyQuery;
import com.greatonce.oms.query.marketing.GiftStrategyStoreQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 赠品策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class GiftStrategyServiceImpl extends
    AbstractEnableService<GiftStrategy, GiftStrategyQuery> implements GiftStrategyService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GiftStrategyServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.GIFT_STRATEGY);
  @Resource
  private CodeGenerator giftStrategyCodeGenerator;
  @Autowired
  private GiftStrategyDao dao;
  @Autowired
  private GiftStrategyStoreService giftStrategyStoreService;
  @Autowired
  private GiftStrategyRuleService giftStrategyRuleService;
  @Autowired
  private GiftStrategyProductService giftStrategyProductService;

  @Override
  protected QueryDao<GiftStrategy, GiftStrategyQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public int create(GiftStrategy record) {
    initDefaultValue(record);
    try {
      int result = getTransactionTemplate().executeWithResult(() -> {
        if (!Assert.isEmpty(record.getStores())) {
          giftStrategyStoreService.batchCreate(record.getStores());
        }
        if (!Assert.isEmpty(record.getRules())) {
          giftStrategyRuleService.batchCreate(record.getRules());
        }
        return insert(record);
      });
      BIZ_LOGGER.log(record.getGiftStrategyId(), BizLogger.ADD);
      return result;
    } catch (Exception e) {
      LOGGER.error("策略插入失败，策略信息:{}", JsonUtil.toJson(record));
      LOGGER.error("策略插入失败，堆栈信息:", e);
      throw new OmsException("操作失败");
    }
  }

  @Override
  protected void initDefaultValue(GiftStrategy giftStrategy) {
    super.initDefaultValue(giftStrategy);
    giftStrategy.setGiftStrategyCode(giftStrategyCodeGenerator.next());
    giftStrategy.setEnable(false);

    if (!Assert.isEmpty(giftStrategy.getStores())) {
      giftStrategy.getStores().forEach(x -> x.setGiftStrategyId(giftStrategy.getGiftStrategyId()));
    }
    if (!Assert.isEmpty(giftStrategy.getRules())) {
      giftStrategy.getRules().forEach(x -> {
        x.setGiftStrategyId(giftStrategy.getGiftStrategyId());
        x.getGifts().stream().filter(gift -> gift.getAlertQuantity() == null)
            .forEach(gift -> gift.setAlertQuantity(0));
      });
    }
  }

  @Override
  public int remove(GiftStrategy record) {
    int result = super.delete(record.getGiftStrategyId());
    BIZ_LOGGER.log(record.getGiftStrategyId(), BizLogger.DELETE);
    return result;
  }

  private int setEnableAttr(Long id, boolean enable) {
    GiftStrategy example = new GiftStrategy();
    example.setGiftStrategyId(id);
    example.setEnable(enable);
    return getDAO().update(example);
  }

  @Override
  public void addProducts(GiftStrategy strategy, GiftStrategyRule rule,
      List<GiftStrategyProduct> products) {
    GiftStrategyProduct example = new GiftStrategyProduct();
    example.setGiftStrategyRuleId(rule.getGiftStrategyRuleId());
    List<GiftStrategyProduct> giftStrategyProducts = giftStrategyProductService
        .listExample(example);
    products.removeIf(
        x -> giftStrategyProducts.stream().anyMatch(y -> x.getSkuCode().equals(y.getSkuCode())));
    products.forEach(x -> {
      if (x.getQuantity() == null) {
        x.setQuantity(0);
      }
    });
    giftStrategyRuleService.addProducts(rule, products);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), rule.getGiftStrategyRuleId(), "添加活动商品");
  }

  @Override
  public void addGifts(GiftStrategy strategy, GiftStrategyRule rule, List<GiftStrategyGift> gifts) {
    giftStrategyRuleService.addGifts(rule, gifts);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), rule.getGiftStrategyRuleId(), "添加赠品");
  }

  @Override
  public void addStores(GiftStrategy strategy, List<GiftStrategyStore> stores) {
    GiftStrategyStoreQuery eg = new GiftStrategyStoreQuery();
    eg.setGiftStrategyId(strategy.getGiftStrategyId());
    List<GiftStrategyStore> strategyStores = giftStrategyStoreService.list(eg);

    for (GiftStrategyStore store : stores) {
      for (GiftStrategyStore strategyStore : strategyStores) {
        if (store.getStoreId().equals(strategyStore.getStoreId())) {
          throw new OmsException("店铺 " + strategyStore.getStoreName() + " 在策略中已关联");
        }
      }
    }

    stores.forEach(x -> x.setGiftStrategyId(strategy.getGiftStrategyId()));
    giftStrategyStoreService.batchCreate(stores);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), "添加活动店铺");
  }

  @Override
  public void updateProduct(GiftStrategy strategy, GiftStrategyRule rule,
      GiftStrategyProduct product) {
  }

  @Override
  public void updateGift(GiftStrategy strategy, GiftStrategyRule rule, GiftStrategyGift gift) {
  }

  @Override
  public void removeProduct(GiftStrategy strategy, GiftStrategyRule rule,
      GiftStrategyProduct product) {
    giftStrategyRuleService.removeProduct(rule, product);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), rule.getGiftStrategyRuleId(),
        "删除活动商品：" + product.getSkuCode());
  }

  @Override
  public void removeGift(GiftStrategy strategy, GiftStrategyRule rule, GiftStrategyGift gift) {
    giftStrategyRuleService.removeGift(rule, gift);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), rule.getGiftStrategyRuleId(),
        "删除赠品：" + gift.getSkuCode());
  }

  @Override
  public void removeStore(GiftStrategy strategy, GiftStrategyStore store) {
    giftStrategyStoreService.remove(store);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), "删除活动店铺：" + store.getStoreName());
  }

  @Override
  public void addRule(GiftStrategy strategy, GiftStrategyRule rule) {
    rule.setGiftStrategyId(strategy.getGiftStrategyId());
    giftStrategyRuleService.create(rule);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), "添加赠送规则：" + rule.getGiftStrategyRuleName());
  }

  @Override
  public void updateRule(GiftStrategy strategy, GiftStrategyRule rule) {
    giftStrategyRuleService.modify(rule);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), "修改赠送规则：" + rule.getGiftStrategyRuleName());
  }

  @Override
  public void removeRule(GiftStrategy strategy, GiftStrategyRule rule) {
    rule.setGiftStrategyId(strategy.getGiftStrategyId());
    giftStrategyRuleService.remove(rule);
    BIZ_LOGGER.log(strategy.getGiftStrategyId(), "删除赠送规则：" + rule.getGiftStrategyRuleName());
  }

  @Override
  public List<GiftStrategy> listEffectiveActivity() {
    return dao.listEffectiveActivity();
  }

  @Override
  public GiftStrategy getFullInfo(Long strategyId) {
    GiftStrategy strategy = getByKey(strategyId);
    GiftStrategyStoreQuery eg = new GiftStrategyStoreQuery();
    eg.setGiftStrategyId(strategyId);
    strategy.setStores(giftStrategyStoreService.list(eg));
    strategy.setRules(giftStrategyRuleService.listFullInfo(strategyId));
    return strategy;
  }
}