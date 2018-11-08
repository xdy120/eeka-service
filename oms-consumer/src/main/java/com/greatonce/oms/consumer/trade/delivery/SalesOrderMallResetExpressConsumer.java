package com.greatonce.oms.consumer.trade.delivery;

import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.trade.MallExpressResetBo;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.message.trade.MallDeliveryResetExpressMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * 订单发货后修改平台快递信息消费者.
 * @author Shenzhen Greatonce Co Ltd
 * @author Lcc
 * @version 2018-07-05
 */
@Component
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_MALL_RESET_EXPRESS, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class SalesOrderMallResetExpressConsumer extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(SalesOrderMallResetExpressConsumer.class);
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;

  @RabbitHandler
  void process(MallDeliveryResetExpressMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {

      List<SalesOrderDetail> details = new ArrayList<>(message.getResetDetailIds().size());
      message.getResetDetailIds().forEach(x -> details.add(salesOrderDetailService.getByKey(x)));

      Map<Long, List<SalesOrderDetail>> resetSalesOrders = CollectionUtil
          .listToMapList(details, SalesOrderDetail::getSalesOrderId);

      SalesOrder resetOrder;
      for (Entry<Long, List<SalesOrderDetail>> entry : resetSalesOrders.entrySet()) {
        resetOrder = salesOrderService.getByKey(entry.getKey());
        if (resetOrder.getSub().getSalesOrderType() == SalesOrderType.REISSUE
            || resetOrder.getSub().getSalesOrderType() == SalesOrderType.INVOICE) {
          LOGGER.info("订单{}为补发订单或补发票订单，不修改平台快递", resetOrder.getSalesOrderCode());
          continue;
        }

        resetOrder.setDetails(entry.getValue());
        MallExpressResetBo bo = new MallExpressResetBo();
        bo.setMallExpressCode(message.getMallExpressCode());
        bo.setMallExpressName(message.getMallExpressName());
        bo.setResetExpressNo(message.getResetExpressNo());
        bo.setMallExpressId(message.getMallExpressId());
        salesOrderService.mallResetExpress(resetOrder, bo);
      }
    });
  }
}
