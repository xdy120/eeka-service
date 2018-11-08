package com.greatonce.oms.consumer.trade.mall.notice;

import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.OrderBridge;
import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest;
import com.greatonce.oms.bridge.mall.request.OrderEventUpdateRequest.OrderEvent;
import com.greatonce.oms.bridge.mall.response.OrderEventUpdateResponse;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.message.Message;
import com.greatonce.oms.message.trade.MallSalesOrderDownloadMessage;
import com.greatonce.oms.message.trade.SalesOrderAuditMessage;
import com.greatonce.oms.message.trade.SalesOrderDispatchedMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 订单事件通知.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-04
 */
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_SALES_ORDER_EVENT_NOTICE, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class OrderEventConsumer extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderEventConsumer.class);
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  @Value("${oms.consumer.event-report.ignore:true}")
  private boolean ignore;


  @RabbitHandler
  void processDownload(MallSalesOrderDownloadMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      if (!ignore) {
        eventNotice(message.getStoreId(), message.getTradeId(), OrderEvent.DOWNLOAD);
      }
    });
  }

  @RabbitHandler
  void processAudit(SalesOrderAuditMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      if (!ignore) {
        SalesOrder salesOrder = salesOrderService.getSimpleInfo(message.getSalesOrderId());
        eventNotice(salesOrder.getStoreId(), salesOrder.getTradeId(), OrderEvent.AUDIT);
      }
    });
  }

  @RabbitHandler
  void processDispatch(SalesOrderDispatchedMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      if (!ignore) {
        SalesOrder salesOrder = salesOrderService.getSimpleInfo(message.getSalesOrderId());
        eventNotice(salesOrder.getStoreId(), salesOrder.getTradeId(), OrderEvent.DISPATCH);
      }
    });
  }

  @RabbitHandler(isDefault = true)
  void processDispatch(Message message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
    });
  }


  private void eventNotice(Long storeId, String tradeId, OrderEvent orderEvent) {
    Store store = storeService.getByKey(storeId);
    OrderEventUpdateRequest request = new OrderEventUpdateRequest(store);
    request.setTradeId(tradeId);
    request.setOrderEvent(orderEvent);
    OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
    final OrderEventUpdateResponse response = orderBridge.noticeOrderEvent(request);
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("{}订单:{}回传状态：{}：{}", store.getStoreName(), tradeId, orderEvent,
          response.isSuccess());
    }
  }
}
