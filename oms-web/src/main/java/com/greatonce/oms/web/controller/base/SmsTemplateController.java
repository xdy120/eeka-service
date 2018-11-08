package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.SmsStoreTemplateService;
import com.greatonce.oms.biz.base.SmsTemplateService;
import com.greatonce.oms.domain.base.SmsStoreTemplate;
import com.greatonce.oms.domain.base.SmsTemplate;
import com.greatonce.oms.query.base.SmsTemplateQuery;
import com.greatonce.oms.web.controller.EnableController;
import com.greatonce.oms.web.controller.FullListController;
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
@RequestMapping("/base/sms/template")
@CrossOrigin
public class SmsTemplateController implements FullListController<SmsTemplate, SmsTemplateQuery>,
    EnableController<SmsTemplate, SmsTemplateQuery> {

  @Autowired
  SmsTemplateService smsTemplateService;

  @Autowired
  SmsStoreTemplateService smsStoreTemplateService;

  @Override
  public BizService<SmsTemplate, SmsTemplateQuery> getBizService() {
    return smsTemplateService;
  }

  @PostMapping(path = "{id}/store")
  public void addStore(@PathVariable("id") Long smsTemplateId,
      @RequestBody List<SmsStoreTemplate> newStores) {
    smsStoreTemplateService.batchCreate(newStores);
  }

  @GetMapping(path = "{id}/store")
  public List<SmsStoreTemplate> listStore(@PathVariable("id") Long smsTemplateId) {
    SmsStoreTemplate example = new SmsStoreTemplate();
    example.setSmsTemplateId(smsTemplateId);
    return smsStoreTemplateService.listExample(example);
  }

  @DeleteMapping(path = "{id}/store/{sid}")
  public void removeStore(@PathVariable("sid") Long smsStoreTemplateId) {
    smsStoreTemplateService.remove(smsStoreTemplateService.getByKey(smsStoreTemplateId));
  }

}
