package com.greatonce.oms.consumer.trade.delivery;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.trade.SalesOrderWmsDeliveryBO;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.message.trade.DispatchOrderDeliveryMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 订单WMS发货.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/7
 */
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_SALES_ORDER_WMS_DELIVERY, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class SalesOrderWmsDeliveryConsumer extends AbstractConsumer {

  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private DispatchOrderDeliveryService dispatchOrderDeliveryService;
  @Autowired
  private SalesOrderService salesOrderService;

  @RabbitHandler
  void process(DispatchOrderDeliveryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      SalesOrder salesOrder;
      SalesOrderWmsDeliveryBO bo;
      DispatchOrder dispatchOrder = dispatchOrderService.getByKey(message.getDispatchOrderId());
      List<DispatchOrderDelivery> deliveries = dispatchOrderDeliveryService
          .listByDispatchOrderId(message.getDispatchOrderId());

      //判断是否需要修改平台快递单号
      boolean expressNoUpdated = !Assert.isEmpty(dispatchOrder.getSuggestExpressNo()) &&
          !dispatchOrder.getSuggestExpressNo().equals(deliveries.get(0).getExpressNo());

      //把发货明细转成key-value，对应订单-订单下发货的明细
      Map<Long, List<DispatchOrderDelivery>> map = CollectionUtil
          .listToMapList(deliveries, DispatchOrderDelivery::getSalesOrderId);

      for (Map.Entry<Long, List<DispatchOrderDelivery>> entry : map.entrySet()) {
        Long salesOrderId = entry.getKey();
        salesOrder = salesOrderService.getByKey(salesOrderId);
        bo = new SalesOrderWmsDeliveryBO();
        bo.setDispatchOrder(dispatchOrder);
        bo.setVersion(salesOrder.getVersion());
        bo.setDeliveries(entry.getValue());
        bo.setExpressNoUpdated(expressNoUpdated);
        salesOrderService.wmsDelivery(salesOrder, bo);
      }
    });
  }
}
