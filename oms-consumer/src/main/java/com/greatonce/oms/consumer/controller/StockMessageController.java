package com.greatonce.oms.consumer.controller;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.domain.Constants;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.stock.StockChangedMessage;
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
@RequestMapping("/stock")
public class StockMessageController {

  @Autowired
  MqProducer mqProducer;
  @Autowired
  ProductSkuService productSkuService;

  @PostMapping(value = "/change/{skuCode}")
  public void sendMessage(@PathVariable("skuCode") String skuCode) {
    ProductSku sku = productSkuService.getEffectiveByCode(skuCode);
    StockChangedMessage message = new StockChangedMessage(sku.getProductCode(), sku.getSkuId(),
        Constants.SYSTEM_OPERATOR, "手工触发");
    mqProducer.send(message);
  }
}
