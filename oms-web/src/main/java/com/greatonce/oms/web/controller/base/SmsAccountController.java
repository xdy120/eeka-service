package com.greatonce.oms.web.controller.base;

import com.greatonce.core.database.PageList;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.base.SmsAccountService;
import com.greatonce.oms.domain.base.SmsAccount;
import com.greatonce.oms.query.base.SmsAccountQuery;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/sms/account")
@CrossOrigin
public class SmsAccountController {

  @Autowired
  IdGenerator idGenerator;

  @Autowired
  SmsAccountService smsAccountService;

  @GetMapping
  public List<SmsAccount> list() {
    return smsAccountService.listExample(null);
  }

  @GetMapping(path = "/paging")
  public PageList<SmsAccount> list(SmsAccountQuery smsAccountQuery, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    return smsAccountService.listPage(smsAccountQuery, page, pageSize);
  }

  @PostMapping
  public SmsAccount add(@RequestBody SmsAccount smsAccount) {
    smsAccount.setSmsAccountId(idGenerator.next());
    smsAccountService.create(smsAccount);
    return smsAccount;
  }

  @GetMapping(path = "{id}")
  public SmsAccount get(@PathVariable("id") Long id) {
    return smsAccountService.getByKey(id);
  }

  @PutMapping(path = "{id}")
  public SmsAccount update(@PathVariable("id") Long id, @RequestBody SmsAccount smsAccount) {
    smsAccount.setSmsAccountId(id);
    smsAccountService.modify(smsAccount);
    return smsAccount;
  }

  @DeleteMapping(path = "{id}")
  public void delete(@PathVariable("id") Long id) {
    smsAccountService.remove(smsAccountService.getByKey(id));
  }

  @GetMapping(path = "/{smsAccount}/account")
  public Integer countBySmsAccount(@PathVariable("smsAccount") String smsAccount) {
    SmsAccount accountExample = new SmsAccount() {{
      setSmsAccount(smsAccount);
    }};
    return smsAccountService.getByExample(accountExample) == null ? 0 : 1;
  }
}
