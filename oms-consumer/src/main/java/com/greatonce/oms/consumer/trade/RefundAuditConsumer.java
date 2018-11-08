package com.greatonce.oms.consumer.trade;

import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.trade.TradeMqConfiguration;
import com.greatonce.oms.biz.trade.RefundApplyOrderService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.RefundBridge;
import com.greatonce.oms.bridge.mall.request.RefundAuditRequest;
import com.greatonce.oms.bridge.mall.response.RefundAuditResponse;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.message.trade.RefundAuditMessage;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author Shenzhen cca
 * @version 2018/9/20
 */
@Component
@RabbitListener(queues = TradeMqConfiguration.QUEUE_TRADE_REFUND_AUDIT,containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class RefundAuditConsumer extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(RefundAuditConsumer.class);

  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private RefundApplyOrderService refundApplyOrderService;
  @Autowired
  private StoreService storeService;

  @RabbitHandler
  void process(RefundAuditMessage message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    run(message,channel,tag,()->{
      RefundApplyOrder refundApplyOrder = refundApplyOrderService.getByKey(message.getRefundApplyOrderId());
      Store store = storeService.getByKey(refundApplyOrder.getStoreId());
      RefundBridge refundBridge = mallBridgeFactory.getRefundBridge(store.getMallType());

      RefundAuditRequest request = new RefundAuditRequest(store);
      request.setRefundApplyOrder(refundApplyOrder);
      request.setReason("退货入库");
      request.setOperator("SYSTEM");
      RefundAuditResponse response = refundBridge.audit(request);
      if (response.isSuccess()){
        LOGGER.info("自动审核退款成功");
      }else {
        LOGGER.info("自动审核退款失败,申请单:{},错误信息:{}",message.getRefundApplyOrderId(),response.getResult());
      }
    });
  }
}
