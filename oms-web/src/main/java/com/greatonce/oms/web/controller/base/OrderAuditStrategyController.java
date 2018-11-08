package com.greatonce.oms.web.controller.base;

import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.domain.base.Setting;
import com.greatonce.oms.domain.base.setting.OrderAuditStrategy;
import com.greatonce.oms.domain.enums.SettingType;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/setting/order_audit_strategy")
@CrossOrigin
public class OrderAuditStrategyController {

  @Autowired
  private IdGenerator idGenerator;

  @Autowired
  private SettingService settingService;

  @GetMapping
  public List<Setting> list() {
    Setting example = new Setting();
    example.setSettingType(SettingType.ORDER_AUDIT_STRATEGY);
    return settingService.listExample(example);
  }

  @PostMapping
  public Setting add(@RequestBody OrderAuditStrategy oas) {
    Setting example = new Setting();
    example.setSettingId(idGenerator.next());
    example.setSettingType(SettingType.ORDER_AUDIT_STRATEGY);
    example.setSettingJson(JSONObject.toJSONString(oas));
    settingService.create(example);
    return example;
  }

  @GetMapping(path = "{id}")
  public Setting get(@PathVariable("id") Long id) {
    Setting example = new Setting();
    example.setSettingId(id);
    example.setSettingType(SettingType.ORDER_AUDIT_STRATEGY);
    return settingService.getByExample(example);
  }

  @PutMapping(path = "{id}")
  public Setting update(@PathVariable("id") Long id, @RequestBody OrderAuditStrategy oas) {
    Setting example = new Setting();
    example.setSettingId(id);
    example.setSettingType(SettingType.ORDER_AUDIT_STRATEGY);
    example.setSettingJson(JSONObject.toJSONString(oas));
    settingService.modify(example);
    return example;
  }

  @DeleteMapping(path = "{id}")
  public void del(@PathVariable("id") Long id) {
    settingService.remove(settingService.getByKey(id));
  }

  @PutMapping(path = "{id}/disable")
  public void disable(@PathVariable("id") Long id) {
    modifyEnable(id, false);
  }

  @PutMapping(path = "{id}/enable")
  public void enable(@PathVariable("id") Long id) {
    modifyEnable(id, true);
  }

  private void modifyEnable(Long id, Boolean enable) {
    Setting setting = settingService.getByKey(id);
    OrderAuditStrategy oas =
        JSONObject.parseObject(setting.getSettingJson(), OrderAuditStrategy.class);
    oas.setEnable(enable);
    setting.setSettingJson(JSONObject.toJSONString(oas));
    settingService.modify(setting);
  }
}
