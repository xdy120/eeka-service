package com.greatonce.oms.consumer.base.store;

import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.base.BaseMqConfiguration;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.base.Store;
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
 * 店铺消息消费队列.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
@Component
@RabbitListener(queues = BaseMqConfiguration.QUEUE_STORE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class StoreConsumer extends AbstractConsumer {

  @Autowired
  private StoreInitializationFactory initializationFactory;
  @Autowired
  private StoreService storeService;

  @RabbitHandler
  void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      Store store = storeService.getByKey(message.getDataId());
      StoreInitialization initialization = initializationFactory
          .getInitializer(store.getMallType());
      switch (message.getEventType()) {
        case ENABLED:
          initialization.init(store);
          break;
        case DISABLED:
          initialization.destroy(store);
          break;
        default:
          break;
      }
    });
  }
}