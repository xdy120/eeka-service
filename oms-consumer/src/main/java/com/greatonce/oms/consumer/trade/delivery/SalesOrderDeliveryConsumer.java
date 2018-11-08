package com.greatonce.oms.consumer.trade.delivery;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.trade.MallDeliveryBO;
import com.greatonce.oms.bo.trade.SalesOrderExpressNoticeBo;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.message.trade.DispatchOrderExpressNoticeMessage;
import com.greatonce.oms.message.trade.SalesOrderDeliveryMessage;
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


/**
 * 订单发货.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/12
 */
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_SALES_ORDER_DELIVERY, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class SalesOrderDeliveryConsumer extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderDeliveryConsumer.class);
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;

  @RabbitHandler
  void process(SalesOrderDeliveryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      SalesOrder salesOrder = salesOrderService.getByKey(message.getSalesOrderId());
      if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.REISSUE
          || salesOrder.getSub().getSalesOrderType() == SalesOrderType.INVOICE) {
        LOGGER.info("订单{}为补发订单或补发票订单，不回传平台发货", salesOrder.getSalesOrderCode());
        return;
      }
      MallDeliveryBO bo = new MallDeliveryBO();
      bo.setVersion(salesOrder.getVersion());
      bo.setExpressNoUpdated(message.isExpressNoUpdated());
      List<SalesOrderDetail> details =
          salesOrderDetailService.listToMallDeliveryDetailsInfo(salesOrder.getSalesOrderId());
      if (Assert.isEmpty(details)) {
        LOGGER.info("订单{}无需回传发货明细", salesOrder.getSalesOrderCode());
        return;
      }
      salesOrder.setDetails(details);
      salesOrderService.mallDelivery(salesOrder, bo);
    });
  }

  @RabbitHandler
  public void process(DispatchOrderExpressNoticeMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      List<SalesOrderDetail> details = new ArrayList<>(message.getNoticeDetailIds().size());
      message.getNoticeDetailIds().forEach(x -> details.add(salesOrderDetailService.getByKey(x)));

      Map<Long, List<SalesOrderDetail>> noticeSalesOrders = CollectionUtil
          .listToMapList(details, SalesOrderDetail::getSalesOrderId);

      SalesOrder noticeOrder;
      SalesOrderExpressNoticeBo bo;
      List<SalesOrderDetail> noticeOrderFullDetails;
      boolean isSplit;
      for (Entry<Long, List<SalesOrderDetail>> entry : noticeSalesOrders.entrySet()) {
        noticeOrder = salesOrderService.getByKey(entry.getKey());
        if (noticeOrder.getSub().getSalesOrderType() == SalesOrderType.REISSUE
            || noticeOrder.getSub().getSalesOrderType() == SalesOrderType.INVOICE) {
          LOGGER.info("订单{}为补发订单或补发票订单，不回传平台物流通知", noticeOrder.getSalesOrderCode());
          return;
        }
        noticeOrderFullDetails = salesOrderDetailService.listNormalBySalesOrderId(entry.getKey());
        isSplit = !Assert.isEmpty(entry.getValue()) &&
            noticeOrderFullDetails.size() > entry.getValue().size();
        noticeOrder.setDetails(isSplit ? entry.getValue() : noticeOrderFullDetails);
        bo = new SalesOrderExpressNoticeBo();
        bo.setVersion(noticeOrder.getVersion());
        bo.setExpressNo(message.getExpressNo());
        bo.setMallExpressId(message.getMallExpressId());
        bo.setMallExpressCode(message.getMallExpressCode());
        bo.setMallExpressName(message.getMallExpressName());
        bo.setExpressName(message.getExpressName());
        bo.setSplit(isSplit);
        salesOrderService.mallExpressNotice(noticeOrder, bo);
      }
    });
  }
}
