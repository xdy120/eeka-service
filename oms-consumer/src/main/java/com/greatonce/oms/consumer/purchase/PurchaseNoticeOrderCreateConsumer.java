package com.greatonce.oms.consumer.purchase;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.impl.purchase.PurchaseMqConfiguration;
import com.greatonce.oms.biz.purchase.PurchaseOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.base.setting.IoBillSetting;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
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
 * @author yiyang
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-17
 */
@Component
@RabbitListener(queues = PurchaseMqConfiguration.QUEUE_PURCHASE_NOTICE_ORDER_CREATE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class PurchaseNoticeOrderCreateConsumer extends AbstractConsumer {

  @Autowired
  private PurchaseOrderService purchaseOrderService;

  @Autowired
  private SettingService settingService;

  @RabbitHandler
  void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      final IoBillSetting ioBillSetting = settingService.getIoBillSetting();
      if (ioBillSetting.isAutoGenPurchaseNotice()) {
        PurchaseOrder purchaseOrder = purchaseOrderService.getByKey(message.getDataId());
        if (!Assert.isNull(purchaseOrder)) {
          VersionBO versionBO = new VersionBO();
          versionBO.setVersion(purchaseOrder.getVersion());
          purchaseOrderService.createNotice(purchaseOrder, versionBO);
        }
      }
    });
  }
}