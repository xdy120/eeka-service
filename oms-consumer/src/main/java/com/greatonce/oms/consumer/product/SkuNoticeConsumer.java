package com.greatonce.oms.consumer.product;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.impl.product.ProductMqConfiguration;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.base.setting.ProductSetting;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.GeneralMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 商品推送.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-14
 */
@Component
@RabbitListener(queues = ProductMqConfiguration.QUEUE_PRODUCT_SKU_NOTICE_WMS, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class SkuNoticeConsumer extends AbstractConsumer {

  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private SettingService settingService;

  private ProductSetting productSetting;

  @RabbitHandler
  void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      if (productSetting == null) {
        productSetting = settingService.getProductSetting();
      }
      if (productSetting != null && Assert.isTrue(productSetting.isAutoPushWms())) {
        ProductSku sku = productSkuService.getEffectiveById(message.getDataId());
        productSkuService.noticeWms(sku);
      }
    });
  }
}
