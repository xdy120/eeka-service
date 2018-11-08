package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.SmsTemplateService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.dao.base.SmsTemplateDao;
import com.greatonce.oms.domain.base.SmsTemplate;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.SmsTemplateQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * SmsTemplate <br/> 短信模板.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class SmsTemplateServiceImpl extends
    AbstractEnableService<SmsTemplate, SmsTemplateQuery> implements SmsTemplateService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_SMS_TEMPLATE);

  private static final String CACHE_NAME = "SMS";

  @Autowired
  private SmsTemplateDao dao;

  @Override
  protected QueryDao<SmsTemplate, SmsTemplateQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected void initDefaultValue(SmsTemplate entity) {
    super.initDefaultValue(entity);
    entity.setEnable(true);
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'TEMPLATE'+#entity.smsAccountId")
  public int modify(SmsTemplate entity) {
    return super.modify(entity);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'TEMPLATE'+#entity.smsAccountId")
  public int remove(SmsTemplate entity) {
    return super.remove(entity);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'TEMPLATE'+#id")
  public SmsTemplate getByKey(Long id) {
    return super.getByKey(id);
  }
}