package com.greatonce.oms.consumer.trade.translator.order;

import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.impl.trade.SalesOrderUtil;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.domain.Constants;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.message.trade.SalesOrderAuditMessage;
import com.greatonce.oms.message.trade.SalesOrderDeliveryMessage;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateOrderLogger;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/26
 */
public class OrderTranslator {

  private static final TranslateOrderLogger TRANSLATE_ORDER_LOGGER = OmsLoggerFactory
      .getTranslateOrderLogger();
  private static final Logger LOGGER = LoggerFactory.getLogger(OrderTranslator.class);
  private PlatformTransactionManager transactionManager;
  private final List<OrderTranslatable> translators = new ArrayList<>(10);

  @Autowired
  private MqProducer mqProducer;
  @Autowired
  private SalesOrderService salesOrderService;

  public OrderTranslator(PlatformTransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  void registerTranslator(OrderTranslatable translator) {
    translators.add(translator);
  }

  public void translate(OrderTranslatableContext context) {
    for (OrderTranslatable translator : translators) {
      translator.translate(context);
    }
    if (context.getMode().equals(OrderTranslatableMode.ERROR)) {
      return;
    }
    DefaultTransactionDefinition definition = new DefaultTransactionDefinition();
    TransactionStatus status = transactionManager.getTransaction(definition);

    try {
      for (OrderTranslatable translator : translators) {
        translator.save(context);
      }
      transactionManager.commit(status);
    } catch (Exception e) {
      TRANSLATE_ORDER_LOGGER.error(context.getSerialNo(), context.getStore(),
          context.getMallSalesOrder().getTradeId(), "销售订单转化保存失败:{}", e.getMessage());
      LOGGER.error("销售订单转化保存失败，堆栈信息:" + e);
      for (OrderTranslatable translator : translators) {
        translator.rollback(context);
      }
      transactionManager.rollback(status);
      return;
    }

    SalesOrder salesOrder = context.getSalesOrder();
    switch (context.getMode()) {
      case CREATE:
        if (context.isNeedDelivery()) {
          //费用订单直接发货，否则判断是否审核通过需要配货
          if (salesOrder.getDetails()
              .stream()
              .filter(x -> x.getProductType() != null)
              .anyMatch(detail -> detail.getProductType().equals(ProductType.VIRTUAL))
              && salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXPENSE) {
            mqProducer.send(new SalesOrderDeliveryMessage(salesOrder.getSalesOrderId()));
          } else if (context.isPassAudited()) {
            if (salesOrder.getDispatchStatus() != DispatchStatus.ALL) {
              mqProducer.send(new SalesOrderAuditMessage(salesOrder.getSalesOrderId(),
                  context.getStore().getSetting().getDelayMinutes()));
            }
          } else {
            salesOrder.setStatus(SalesOrderStatus.AUDITED_ABNORMAL);
          }
          //发消息更新库存
          salesOrder.getDetails().stream()
              .filter(x -> x.getSkuId() != null && x.getStatus() == SalesOrderDetailStatus.WAITING)
              .forEach(x -> mqProducer.send(new StockChangedMessage(salesOrder.getSalesOrderCode(),
                  x.getSkuId(), Constants.SYSTEM_OPERATOR, "订单转化")));
        } else {
          salesOrderService.getBizLogger()
              .log(salesOrder.getSalesOrderId(), BizLogger.ADD, "该订单为非发货订单");
        }
        mqProducer.send(new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
            EventType.CREATED));
        salesOrderService.getBizLogger().log(salesOrder.getSalesOrderId(), BizLogger.ADD,
            salesOrder.getSub().getCreateType().caption());
        break;
      case MODIFY:
        salesOrderService.getBizLogger()
            .log(salesOrder.getSalesOrderId(), BizLogger.UPDATE, "转化修改订单");
        mqProducer.send(new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
            EventType.MODIFIED)
        );
        break;
      case CLOSE:
        salesOrderService.getBizLogger()
            .log(salesOrder.getSalesOrderId(), BizLogger.UPDATE, "订单关闭，作废订单");
        break;
      case FINISH:
        salesOrderService.getBizLogger()
            .log(salesOrder.getSalesOrderId(), BizLogger.UPDATE, "订单平台交易完成");
        break;
      case ABANDON:
        break;
    }
  }
}
