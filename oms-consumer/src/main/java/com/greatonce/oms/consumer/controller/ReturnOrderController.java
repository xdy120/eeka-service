package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.message.trade.MallRefundOrderDownloadMessage;
import com.greatonce.oms.message.trade.ReturnNoticeOrderCreateMessage;
import com.greatonce.oms.message.trade.ReturnNoticeOrderEntryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/trade/return/notice")
public class ReturnOrderController {

  @Autowired
  MqProducer mqProducer;
  @Autowired
  ReturnNoticeOrderService returnNoticeOrderService;


  @PostMapping(value = "/create/{code}")
  public void create(@PathVariable("code") String code) {
    ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService.getByCode(code);
    ReturnNoticeOrderCreateMessage message = new ReturnNoticeOrderCreateMessage(
        returnNoticeOrder.getReturnNoticeOrderId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/entry/{code}")
  public void entry(@PathVariable("code") String code) {
    ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService.getByCode(code);
    ReturnNoticeOrderEntryMessage message = new ReturnNoticeOrderEntryMessage(
        returnNoticeOrder.getReturnNoticeOrderId());
    mqProducer.send(message);
  }
}
