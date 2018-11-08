package com.greatonce.oms.consumer.trade.translator.refund;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.mall.MallRefundOrderService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.mall.MallRefundOrder;
import com.greatonce.oms.message.trade.MallRefundOrderDownloadMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 退款单转化队列消费.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-05
 */
@TranslatorRefundCondition
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_REFUND_ORDER_TRANSLATE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class RefundTranslatorConsumer extends AbstractConsumer {

  @Autowired
  private IdGenerator idGenerator;
  @Autowired
  private MallRefundOrderService mallRefundOrderService;
  @Autowired
  private RefundTranslator refundTranslator;


  @RabbitHandler
  void process(MallRefundOrderDownloadMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      MallRefundOrder mallRefundOrder = mallRefundOrderService
          .getByKey(message.getMallRefundOrderId());
      RefundTranslatableContext context = new RefundTranslatableContext(idGenerator.next(),
          mallRefundOrder);
      refundTranslator.translate(context);
    });
  }
}
