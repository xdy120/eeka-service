package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.message.trade.SalesOrderDispatchedMessage;
import com.greatonce.oms.message.trade.SalesOrderMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 配货单转化消费者.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/20
 */
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_SALES_ORDER_DISPATCH, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class DispatchConsumer extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(DispatchConsumer.class);
  @Autowired
  private IdGenerator idGenerator;
  @Autowired
  private Dispatchable dispatchable;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;

  @RabbitHandler
  void processPartDispatch(SalesOrderDispatchedMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      if (!message.isPartDispatched()) {
        LOGGER.debug("订单非部分配货，订单id：{}", message.getSalesOrderId());
        return;
      }
      SalesOrderSetting salesOrderSetting = settingService.getSalesOrderSetting();
      if (!Assert.isTrue(salesOrderSetting.getAutoPrepareOnSplit())) {
        LOGGER.debug("订单配置为订单拆分后不进行自动配货，订单id：{}", message.getSalesOrderId());
        return;
      }
      SalesOrder salesOrder = salesOrderService.getByKey(message.getSalesOrderId());
      if (salesOrder == null) {
        LOGGER.error("配货失败，订单不存在！消息来源：{}", message.routingKey());
        throw new OmsException("配货失败，订单不存在！");
      }
      salesOrder.setDetails(
              salesOrderDetailService.listDispatchableBySalesOrderId(message.getSalesOrderId()));
      salesOrder.getDetails().removeIf(x -> !x.getStatus().equals(SalesOrderDetailStatus.WAITING));
      Store store = storeService.getByKey(salesOrder.getStoreId());
      DispatchContext context = new DispatchContext(idGenerator.next(), store, salesOrder);
      context.setSalesOrderSetting(salesOrderSetting);
      context.setDispatchStrategy(stockDispatchStrategyService
          .getByKey(context.getStore().getSetting().getStockStrategy()));
      dispatchable.dispatch(context);
    });
  }

  @RabbitHandler(isDefault = true)
  void process(SalesOrderMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      SalesOrder salesOrder = salesOrderService.getByKey(message.getSalesOrderId());
      if (salesOrder == null) {
        LOGGER.error("配货失败，订单不存在！消息来源：{}", message.routingKey());
        throw new OmsException("配货失败，订单不存在！");
      }
      salesOrder.setDetails(
          salesOrderDetailService.listDispatchableBySalesOrderId(message.getSalesOrderId()));
      Store store = storeService.getByKey(salesOrder.getStoreId());
      DispatchContext context = new DispatchContext(idGenerator.next(), store, salesOrder);
      context.setSalesOrderSetting(settingService.getSalesOrderSetting());
      context.setDispatchStrategy(stockDispatchStrategyService
          .getByKey(context.getStore().getSetting().getStockStrategy()));
      dispatchable.dispatch(context);
    });
  }
}
