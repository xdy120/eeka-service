package com.greatonce.oms.consumer.purchase;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.purchase.PurchaseMqConfiguration;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.consumer.OrderNoticeCondition;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
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
 * 采购退货通知.
 *
 * @author yiyang
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-17
 */
@Component
@OrderNoticeCondition
@RabbitListener(queues = PurchaseMqConfiguration.QUEUE_PURCHASE_RETURN_NOTICE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class PurchaseReturnNoticeConsumer extends AbstractConsumer {

  @Autowired
  private PurchaseReturnOrderService purchaseReturnOrderService;

  @RabbitHandler
  void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      PurchaseReturnOrder purchaseNoticeOrder = purchaseReturnOrderService
          .getByKey(message.getDataId());
      if (!Assert.isNull(purchaseNoticeOrder)) {
        VersionBO versionBO = new VersionBO();
        versionBO.setVersion(purchaseNoticeOrder.getVersion());
        purchaseReturnOrderService.noticeWms(purchaseNoticeOrder, versionBO);
      }
    });
  }
}