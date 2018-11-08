package com.greatonce.oms.consumer.controller;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.query.product.ProductSkuQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SKP
 */
@RestController
@RequestMapping("/product/sku")
public class ProductSkuMessageController {

  @Autowired
  private MqProducer mqProducer;
  @Autowired
  private ProductSkuService productSkuService;

  @PostMapping("/index/all")
  public void sendImportAllProductSkuIndexMessage() {
    ProductSkuQuery query = new ProductSkuQuery();
    int PAGE_MAX_SIZE = 500;
    PageList<ProductSku> pageList = productSkuService.listPage(query, 1, PAGE_MAX_SIZE);
    List<GeneralMessage> messages = pageList.getData().stream()
        .map(ProductSku::getSkuId)
        .map(x -> new GeneralMessage(OmsModule.PRODUCT_SKU, x, EventType.CREATED))
        .collect(Collectors.toList());
    mqProducer.send(messages);

    if (pageList.getTotal() > PAGE_MAX_SIZE) {
      int totalPage = MallUtil.calcTotalPage(PAGE_MAX_SIZE, pageList.getTotal());
      for (int i = 2; i <= totalPage; i++) {
        PageList<ProductSku> otherPageList = productSkuService.listPage(query, i, PAGE_MAX_SIZE);
        List<GeneralMessage> otherMessages = otherPageList.getData().stream()
            .map(ProductSku::getSkuId)
            .map(x -> new GeneralMessage(OmsModule.PRODUCT_SKU, x, EventType.CREATED))
            .collect(Collectors.toList());
        mqProducer.send(otherMessages);
      }
    }
  }

}
