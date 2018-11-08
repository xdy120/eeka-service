package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.domain.base.Setting;
import com.greatonce.oms.domain.enums.SettingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base/setting")
@CrossOrigin
public class SettingController {

  @Autowired
  private SettingService settingService;

  @GetMapping
  public Setting get(SettingType settingType) {
    Setting example = new Setting();
    example.setSettingType(settingType);
    return settingService.getByExample(example);
  }

  @PutMapping
  public void save(@RequestParam("settingType") SettingType settingType,
      @RequestBody String settingJson) {
    Setting example = new Setting();
    example.setSettingType(settingType);
    Setting setting = settingService.getByExample(example);
    if (setting == null) {
      example.setSettingJson(settingJson);
      settingService.create(example);
    } else {
      setting.setSettingJson(settingJson);
      settingService.modify(setting);
    }
  }
}