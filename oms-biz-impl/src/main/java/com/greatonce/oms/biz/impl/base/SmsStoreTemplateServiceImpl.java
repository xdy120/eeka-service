package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.SmsStoreTemplateService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.SmsStoreTemplateDao;
import com.greatonce.oms.domain.base.SmsStoreTemplate;
import com.greatonce.oms.query.base.SmsStoreTemplateQuery;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SmsStoreTemplate <br/> 短信店铺.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-13
 */

@Service
public class SmsStoreTemplateServiceImpl extends
    AbstractService<SmsStoreTemplate, SmsStoreTemplateQuery> implements SmsStoreTemplateService {

  @Autowired
  private SmsStoreTemplateDao dao;
  @Autowired
  private SmsStoreTemplateService smsStoreTemplateService;

  @Override
  protected QueryDao<SmsStoreTemplate, SmsStoreTemplateQuery> getDAO() {
    return this.dao;
  }

  @Override
  public int batchCreate(Collection<? extends SmsStoreTemplate> collection) {
    Long templateId = collection.stream().findFirst().get().getSmsTemplateId();
    SmsStoreTemplate eg = new SmsStoreTemplate();
    eg.setSmsTemplateId(templateId);
    List<SmsStoreTemplate> smsStoreTemplates = smsStoreTemplateService.listExample(eg);
    collection.removeIf(
        x -> smsStoreTemplates.stream().anyMatch(y -> x.getStoreId().equals(y.getStoreId())));
    collection.forEach(x -> initDefaultValue(x));
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends SmsStoreTemplate> collection) {
    return updateBatch(collection);
  }
}