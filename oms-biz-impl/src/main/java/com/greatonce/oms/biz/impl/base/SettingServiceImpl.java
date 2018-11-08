package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.SettingDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Setting;
import com.greatonce.oms.domain.base.setting.IoBillSetting;
import com.greatonce.oms.domain.base.setting.OrderAuditStrategy;
import com.greatonce.oms.domain.base.setting.ProductSetting;
import com.greatonce.oms.domain.base.setting.ReturnOrderSetting;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.SettingType;
import com.greatonce.oms.query.base.SettingQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * Setting <br/> 基础配置.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class SettingServiceImpl extends AbstractService<Setting, SettingQuery> implements
    SettingService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_SETTING);
  private static final String CACHE_NAME = "SETTING";
  @Autowired
  SettingDao dao;

  @Override
  protected QueryDao<Setting, SettingQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int remove(Setting setting) {
    return super.remove(setting);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, allEntries = true)
  public int modify(Setting record) {
    return super.modify(record);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SETTING_'+#settingId")
  public OrderAuditStrategy getOrderAuditStrategy(Long settingId) {
    Setting example = new Setting();
    example.setSettingType(SettingType.ORDER_AUDIT_STRATEGY);
    example.setSettingId(settingId);
    Setting setting = getByExample(example);
    if (setting == null) {
      return null;
    }

    //格式转换
    OrderAuditStrategy orderAuditStrategy = JsonUtil
        .toObject(setting.getSettingJson(), OrderAuditStrategy.class);
    if (!Assert.isEmpty(orderAuditStrategy.getInterceptProduct())) {
      String productString = orderAuditStrategy.getInterceptProduct().get(0);
      orderAuditStrategy.setInterceptProduct(
          Arrays.stream(productString.split(";")).collect(Collectors.toList()));
    }
    if (!Assert.isEmpty(orderAuditStrategy.getInterceptRegion())) {
      String regionString = orderAuditStrategy.getInterceptRegion().get(0);
      orderAuditStrategy
          .setInterceptRegion(Arrays.stream(regionString.split(";")).collect(Collectors.toList()));
    }
    return orderAuditStrategy;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SETTING_SALES_ORDER'")
  public SalesOrderSetting getSalesOrderSetting() {
    return getSetting(SettingType.SALES_ORDER_CONFIG, SalesOrderSetting.class);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SETTING_RETURN_ORDER'")
  public ReturnOrderSetting getReturnOrderSetting() {
    return getSetting(SettingType.RETURN_ORDER_CONFIG, ReturnOrderSetting.class);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SETTING_STOCK'")
  public StockSetting getInventorySetting() {
    return getSetting(SettingType.INVENTORY_CONFIG, StockSetting.class);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SETTING_PRODUCT'")
  public ProductSetting getProductSetting() {
    return getSetting(SettingType.PRODUCT_CONFIG, ProductSetting.class);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SETTING_IO_BILL'")
  public IoBillSetting getIoBillSetting() {
    return getSetting(SettingType.RECEIPTS_IN_OUT_CONFIG, IoBillSetting.class);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'SETTING_'+#id")
  public Setting getByKey(Long id) {
    return super.getByKey(id);
  }

  private <T> T getSetting(SettingType settingType, Class<? extends T> clazz) {
    Setting example = new Setting();
    example.setSettingType(settingType);
    Setting setting = getByExample(example);
    if (setting == null) {
      try {
        return clazz.newInstance();
      } catch (Exception e) {
        throw new OmsException("获取系统配置异常！" + settingType.caption);
      }
    }
    return JsonUtil.toObject(setting.getSettingJson(), clazz);
  }
}