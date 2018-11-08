package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.trade.DispatchOrderDeliveryMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/k3cloud")
public class K3CloudController {

  @Autowired
  private MqProducer mqProducer;

  @PostMapping(value = "/b2c/stock/out/{dispatchOrderId}")
  public void sendDispatchOrderDeliveryMessage(
      @PathVariable("dispatchOrderId") Long dispatchOrderId) {
    mqProducer.send(new DispatchOrderDeliveryMessage(dispatchOrderId));
  }

  @PostMapping(value = "/stock/out/{stockOutOrderId}")
  public void sendStockOutOrderGeneralOutMessage(
      @PathVariable("stockOutOrderId") Long stockOutOrderId) {
    mqProducer.send(new GeneralMessage(OmsModule.STOCK_OUT, stockOutOrderId, EventType.OUT));
  }

  @PostMapping(value = "/stock/return/{returnOrderId}")
  public void sendReturnOrderGeneralAuditedMessage(
      @PathVariable("returnOrderId") Long returnOrderId) {
    mqProducer.send(new GeneralMessage(OmsModule.RETURN_ORDER, returnOrderId, EventType.AUDITED));
  }

  @PostMapping(value = "/material/{skuId}")
  public void sendProductSkuGeneralCreatedMessage(
      @PathVariable("skuId") Long skuId) {
    mqProducer.send(new GeneralMessage(OmsModule.PRODUCT_SKU, skuId, EventType.CREATED));
  }

  @PostMapping(value = "/receive/bill/{alipayRecordId}")
  public void sendAlipayRecordGeneralCreatedMessage(
      @PathVariable("alipayRecordId") Long alipayRecordId) {
    mqProducer.send(new GeneralMessage(OmsModule.ALIPAY_RECORD, alipayRecordId, EventType.CREATED));
  }
}
