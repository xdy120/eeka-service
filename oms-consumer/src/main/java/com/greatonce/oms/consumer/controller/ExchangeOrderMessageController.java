package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.mall.MallExchangeOrderService;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.message.trade.MallExchangeOrderDownloadMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/7
 */
@RestController
@RequestMapping("/trade")
public class ExchangeOrderMessageController {

  @Autowired
  MqProducer mqProducer;
  @Autowired
  MallExchangeOrderService mallExchangeOrderService;

  @PostMapping(value = "/exchange/{id}")
  public void sendMessage(@PathVariable("id") Long id) {
    MallExchangeOrder exchangeOrder = mallExchangeOrderService.getByKey(id);
    MallExchangeOrderDownloadMessage message = new MallExchangeOrderDownloadMessage(
        exchangeOrder.getMallExchangeOrderId(), exchangeOrder.getStoreId(),
        exchangeOrder.getTradeId(),
        exchangeOrder.getMallExchangeId());
    mqProducer.send(message);
  }
}
