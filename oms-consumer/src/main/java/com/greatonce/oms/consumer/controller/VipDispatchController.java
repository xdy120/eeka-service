package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.message.vip.VipDispatchBindMessage;
import com.greatonce.oms.message.vip.VipDispatchDeliveryMessage;
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
@RequestMapping("/vip/dispatch")
public class VipDispatchController {

  @Autowired
  private MqProducer mqProducer;
  @Autowired
  private VipDispatchService vipDispatchService;

  @PostMapping(value = "/bind/{code}")
  public void create(@PathVariable("code") String code) {
    VipDispatch vipDispatch = vipDispatchService.getByCode(code);
    VipDispatchBindMessage message = new VipDispatchBindMessage(vipDispatch.getVipDispatchId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/delivery/{code}")
  public void delivery(@PathVariable("code") String code) {
    VipDispatch vipDispatch = vipDispatchService.getByCode(code);
    VipDispatchDeliveryMessage message = new VipDispatchDeliveryMessage(
        vipDispatch.getVipDispatchId(), vipDispatch.getVipDeliveryId(), vipDispatch.getOutStatus());
    mqProducer.send(message);
  }
}
