package com.greatonce.oms.consumer.trade.mall.notice;

import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.message.trade.ReturnOrderCreateMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 退货商品入库.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-24
 */
//@RabbitListener(queues = Constants.QUEUE_TRADE_REFUND_AGREE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class ReturnGoodsEntryConsumer extends AbstractConsumer {

  @Autowired
  private ReturnOrderDetailService returnOrderDetailService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private StoreService storeService;

  @RabbitHandler
  void process(ReturnOrderCreateMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    this.run(message, channel, tag, () -> {

//      List<ReturnOrderDetail> details = returnOrderDetailService
//          .listByReturnOrderId(message.getReturnOrderId());

    });
  }
}
