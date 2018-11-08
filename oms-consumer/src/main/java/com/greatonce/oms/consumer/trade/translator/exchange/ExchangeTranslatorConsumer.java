package com.greatonce.oms.consumer.trade.translator.exchange;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.mall.MallExchangeOrderService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.mall.MallExchangeOrder;
import com.greatonce.oms.message.trade.MallExchangeOrderDownloadMessage;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

import java.io.IOException;

/**
 * 换货单转化队列消费.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-05
 */
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_EXCHANGE_ORDER_TRANSLATE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class ExchangeTranslatorConsumer extends AbstractConsumer {

  @Autowired
  private IdGenerator idGenerator;
  @Autowired
  private MallExchangeOrderService mallExchangeOrderService;
  @Autowired
  private ExchangeTranslator exchangeTranslator;

  @RabbitHandler
  void process(MallExchangeOrderDownloadMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      MallExchangeOrder mallExchangeOrder = mallExchangeOrderService
          .getByKey(message.getMallExchangeOrderId());
      ExchangeTranslatableContext context = new ExchangeTranslatableContext(idGenerator.next(),
          mallExchangeOrder);
      exchangeTranslator.translate(context);
    });
  }
}
