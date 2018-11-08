package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.SmsAccountService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.SmsAccountDao;
import com.greatonce.oms.domain.base.SmsAccount;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.SmsAccountQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * SmsAccount <br/> 短信帐号.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class SmsAccountServiceImpl extends AbstractService<SmsAccount, SmsAccountQuery> implements
    SmsAccountService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_SMS_TEMPLATE);

  private static final String CACHE_NAME = "SMS";

  @Autowired
  private SmsAccountDao dao;

  @Override
  protected QueryDao<SmsAccount, SmsAccountQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'ACCOUNT'+#entity.smsAccountId")
  public int modify(SmsAccount entity) {
    return super.modify(entity);
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'ACCOUNT'+#entity.smsAccountId")
  public int remove(SmsAccount entity) {
    return super.remove(entity);
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'ACCOUNT'+#id")
  public SmsAccount getByKey(Long id) {
    return super.getByKey(id);
  }
}