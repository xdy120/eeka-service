package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.mall.MallExchangeOrderService;
import com.greatonce.oms.biz.mall.MallRefundOrderService;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.domain.mall.MallRefundOrder;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.message.trade.MallExchangeOrderDownloadMessage;
import com.greatonce.oms.message.trade.MallRefundOrderDownloadMessage;
import com.greatonce.oms.message.trade.MallSalesOrderDownloadMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-19
 */
@RestController
@RequestMapping("/mall/download")
public class DownloadMessageController {

  @Autowired
  MqProducer mqProducer;
  @Autowired
  MallExchangeOrderService mallExchangeOrderService;
  @Autowired
  MallRefundOrderService mallRefundOrderService;
  @Autowired
  MallSalesOrderService mallSalesOrderService;

  @PostMapping(value = "/exchange/{id}")
  public void downloadExchange(@PathVariable("id") Long id) {
    MallExchangeOrder exchangeOrder = mallExchangeOrderService.getByKey(id);
    MallExchangeOrderDownloadMessage message = new MallExchangeOrderDownloadMessage(
        exchangeOrder.getMallExchangeOrderId(), exchangeOrder.getStoreId(),
        exchangeOrder.getTradeId(),
        exchangeOrder.getMallExchangeId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/refund/{id}")
  public void downloadRefund(@PathVariable("id") Long id) {
    MallRefundOrder refundOrder = mallRefundOrderService.getByKey(id);
    mqProducer.send(new MallRefundOrderDownloadMessage(refundOrder.getMallRefundOrderId(),
        refundOrder.getStoreId(), refundOrder.getTradeId(), refundOrder.getMallRefundId()));
  }


  @PostMapping(value = "/sales/{id}")
  public void downloadSalesOrder(@PathVariable("id") Long id) {
    MallSalesOrder mallSalesOrder = mallSalesOrderService.getByKey(id);
    mqProducer.send(new MallSalesOrderDownloadMessage(mallSalesOrder.getMallSalesOrderId(),
        mallSalesOrder.getStoreId(), mallSalesOrder.getTradeId()));
  }
}
