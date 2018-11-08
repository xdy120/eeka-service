package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.marketing.GiftStrategyGiftService;
import com.greatonce.oms.biz.marketing.GiftStrategyProductService;
import com.greatonce.oms.biz.marketing.GiftStrategyRuleService;
import com.greatonce.oms.dao.marketing.GiftStrategyRuleDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyGiftType;
import com.greatonce.oms.domain.enums.marketing.GiftStrategyRuleType;
import com.greatonce.oms.domain.enums.marketing.ProductRange;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.domain.marketing.GiftStrategyRuleSetting;
import com.greatonce.oms.query.marketing.GiftStrategyRuleQuery;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 赠品规则.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-01-27
 */
@Service
public class GiftStrategyRuleServiceImpl extends
    AbstractService<GiftStrategyRule, GiftStrategyRuleQuery> implements GiftStrategyRuleService {

  private static final Logger LOGGER = LoggerFactory.getLogger(GiftStrategyRuleServiceImpl.class);
  @Autowired
  private GiftStrategyRuleDao dao;
  @Autowired
  private GiftStrategyProductService giftStrategyProductService;
  @Autowired
  private GiftStrategyGiftService giftStrategyGiftService;

  @Override
  protected QueryDao<GiftStrategyRule, GiftStrategyRuleQuery> getDAO() {
    return this.dao;
  }

  @Override
  public int create(GiftStrategyRule record) {
    initDefaultValue(record);

    //编程式事务
    try {
      return getTransactionTemplate().executeWithResult(() -> {
        if (!Assert.isEmpty(record.getSkus())) {
          giftStrategyProductService.batchCreate(record.getSkus());
        }
        if (!Assert.isEmpty(record.getGifts())) {
          giftStrategyGiftService.batchCreate(record.getGifts());
        }
        return insert(record);
      });
    } catch (Exception e) {
      LOGGER.error("赠品规则插入失败，赠品规则：{}", JsonUtil.toJson(record));
      LOGGER.error("赠品规则插入失败，堆栈信息：", e);
      throw new OmsException("添加赠品规则失败");
    }
  }

  @Override
  protected void initDefaultValue(GiftStrategyRule entity) {
    super.initDefaultValue(entity);
    if (!Assert.isEmpty(entity.getSkus())) {
      entity.getSkus().forEach(x -> x.setGiftStrategyRuleId(entity.getGiftStrategyRuleId()));
    }
    if (!Assert.isEmpty(entity.getGifts())) {
      entity.getGifts().forEach(x -> x.setGiftStrategyRuleId(entity.getGiftStrategyRuleId()));
    }
  }

  @Override
  public void addProducts(GiftStrategyRule rule, List<GiftStrategyProduct> products) {
    products.forEach(x -> x.setGiftStrategyRuleId(rule.getGiftStrategyRuleId()));
    giftStrategyProductService.batchCreate(products);
  }

  @Override
  public void addGifts(GiftStrategyRule rule, List<GiftStrategyGift> gifts) {
    gifts.forEach(x -> x.setGiftStrategyRuleId(rule.getGiftStrategyRuleId()));
    giftStrategyGiftService.batchCreate(gifts);
  }

  @Override
  public void updateProduct(GiftStrategyRule rule, GiftStrategyProduct product) {
    giftStrategyProductService.modify(product);
  }

  @Override
  public void updateGift(GiftStrategyRule rule, GiftStrategyGift gift) {
    giftStrategyGiftService.modify(gift);
  }

  @Override
  public void removeProduct(GiftStrategyRule rule, GiftStrategyProduct product) {
    giftStrategyProductService.remove(product);
  }

  @Override
  public void removeGift(GiftStrategyRule rule, GiftStrategyGift gift) {
    giftStrategyGiftService.remove(gift);
  }

  @Override
  public List<GiftStrategyRule> listFullInfo(Long strategyId) {
    GiftStrategyRule eg = new GiftStrategyRule();
    eg.setGiftStrategyId(strategyId);
    List<GiftStrategyRule> list = listExample(eg);
    GiftStrategyGift gift = new GiftStrategyGift();
    GiftStrategyProduct product = new GiftStrategyProduct();
    for (GiftStrategyRule rule : list) {
      rule.setSetting(JsonUtil.toObject(rule.getRuleSetting(), GiftStrategyRuleSetting.class));
      if (rule.getRuleType() != GiftStrategyRuleType.LUCK_BAG) {
        if (rule.getSetting().getProductRange() != ProductRange.ALL) {
          product.setGiftStrategyRuleId(rule.getGiftStrategyRuleId());
          rule.setSkus(giftStrategyProductService.listExample(product));
        }
        if (rule.getSetting().getGiftType() == GiftStrategyGiftType.GOODS) {
          gift.setGiftStrategyRuleId(rule.getGiftStrategyRuleId());
          rule.setGifts(giftStrategyGiftService.listExample(gift));
        }
      } else {
        gift.setGiftStrategyRuleId(rule.getGiftStrategyRuleId());
        rule.setGifts(giftStrategyGiftService.listExample(gift));
      }
    }
    return list;
  }

  @Override
  public int remove(GiftStrategyRule entity) {
    try {
      return getTransactionTemplate().executeWithResult(() -> {
        giftStrategyProductService.removeByRuleId(entity.getGiftStrategyRuleId());
        giftStrategyGiftService.removeByRuleId(entity.getGiftStrategyRuleId());
        return delete(entity.getGiftStrategyRuleId());
      });
    } catch (Exception e) {
      LOGGER.error("赠品规则删除失败，赠品规则id：{}", entity.getGiftStrategyRuleId());
      LOGGER.error("赠品规则删除失败，堆栈信息：", e);
      throw new OmsException("删除赠品规则失败");
    }
  }

  @Override
  public int batchCreate(Collection<? extends GiftStrategyRule> list) {
    List<GiftStrategyProduct> skus = new ArrayList<>();
    List<GiftStrategyGift> gifts = new ArrayList<>();
    list.forEach(this::initDefaultValue);
    //编程式事务
    try {
      return getTransactionTemplate().executeWithResult(() -> {
        list.forEach(x -> {
          if (!Assert.isEmpty(x.getSkus())) {
            skus.addAll(x.getSkus());
          }
          if (!Assert.isEmpty(x.getGifts())) {
            gifts.addAll(x.getGifts());
          }
        });
        if (!skus.isEmpty()) {
          giftStrategyProductService.batchCreate(skus);
        }
        if (!gifts.isEmpty()) {
          giftStrategyGiftService.batchCreate(gifts);
        }
        return insertBatch(list);
      });
    } catch (Exception e) {
      LOGGER.error("赠品规则批量插入失败，集合信息：{}", JsonUtil.toJson(list));
      LOGGER.error("赠品规则批量插入失败，堆栈信息：", e);
      throw new OmsException("批量添加赠品规则失败");
    }
  }

  @Override
  public int batchModify(Collection<? extends GiftStrategyRule> collection) {
    return updateBatch(collection);
  }
}