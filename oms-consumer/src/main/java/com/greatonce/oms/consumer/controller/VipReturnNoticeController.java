package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.message.vip.VipReturnNoticeCreateMessage;
import com.greatonce.oms.message.vip.VipReturnNoticeEntryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/25
 */
@RestController
@RequestMapping("/vip/return/notice")
public class VipReturnNoticeController {

  @Autowired
  MqProducer mqProducer;
  @Autowired
  VipReturnNoticeService vipReturnNoticeService;

  @PostMapping(value = "/create/{code}")
  public void create(@PathVariable("code") String code) {
    VipReturnNotice vipReturnNotice = vipReturnNoticeService.getByCode(code);
    VipReturnNoticeCreateMessage message = new VipReturnNoticeCreateMessage(
        vipReturnNotice.getVipReturnNoticeId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/entry/{code}")
  public void entry(@PathVariable("code") String code) {
    VipReturnNotice vipReturnNotice = vipReturnNoticeService.getByCode(code);
    VipReturnNoticeEntryMessage message = new VipReturnNoticeEntryMessage(
        vipReturnNotice.getVipReturnNoticeId());
    mqProducer.send(message);
  }
}
