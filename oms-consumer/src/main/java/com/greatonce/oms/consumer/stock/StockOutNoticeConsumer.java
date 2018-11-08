package com.greatonce.oms.consumer.stock;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.stock.StockMqConfiguration;
import com.greatonce.oms.biz.stock.StockOutOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.consumer.OrderNoticeCondition;
import com.greatonce.oms.domain.stock.StockOutOrder;
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
 * 出库单自动推送WMS队列.
 *
 * @author yiyang
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/8/24
 */
@Component
@OrderNoticeCondition
@RabbitListener(queues = StockMqConfiguration.QUEUE_STOCK_OUT_NOTICE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class StockOutNoticeConsumer extends AbstractConsumer {

  @Autowired
  private StockOutOrderService stockOutOrderService;

  @RabbitHandler
  void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      StockOutOrder stockOutOrder = stockOutOrderService.getByKey(message.getDataId());
      if (!Assert.isNull(stockOutOrder)) {
        VersionBO versionBO = new VersionBO();
        versionBO.setVersion(stockOutOrder.getVersion());
        stockOutOrderService.noticeWms(stockOutOrder, versionBO);
      }
    });
  }
}
