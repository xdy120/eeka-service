package com.greatonce.oms.consumer.controller;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.trade.MallSalesOrderDownloadMessage;
import com.greatonce.oms.message.trade.SalesOrderAuditMessage;
import com.greatonce.oms.message.trade.SalesOrderDeliveryMessage;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/26
 */
@RestController
@RequestMapping("/trade/sales/order")
public class SalesOrderMessageController {

  private static int PAGE_MAX_SIZE = 500;
  @Autowired
  private MqProducer mqProducer;
  @Autowired
  private MallSalesOrderService mallSalesOrderService;
  @Autowired
  private SalesOrderService salesOrderService;

  @PostMapping(value = "/download/{mallSalesOrderId}")
  public void sendMallSalesOrderDownloadedMessage(
      @PathVariable("mallSalesOrderId") Long mallSalesOrderId) {
    MallSalesOrder salesOrder = mallSalesOrderService.getByKey(mallSalesOrderId);
    MallSalesOrderDownloadMessage message = new MallSalesOrderDownloadMessage(mallSalesOrderId,
        salesOrder.getStoreId(), salesOrder.getTradeId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/download/top/{num}")
  public void sendMallSalesOrderDownloadedMessage(@PathVariable("num") int num) {
    PageList<MallSalesOrder> orders = mallSalesOrderService.listPage(null, 1, num);
    for (MallSalesOrder mallSalesOrder : orders.getData()) {
      MallSalesOrderDownloadMessage message = new MallSalesOrderDownloadMessage(
          mallSalesOrder.getMallSalesOrderId(), mallSalesOrder.getStoreId(),
          mallSalesOrder.getTradeId());
      mqProducer.send(message);
    }
  }

  @PostMapping(value = "/audit/{code}")
  public void sendSalesOrderAuditedMessage(@PathVariable("code") String code) {
    SalesOrder salesOrder = new SalesOrder();
    salesOrder.setSalesOrderCode(code);
    salesOrder = salesOrderService.getByExample(salesOrder);
    SalesOrderAuditMessage message = new SalesOrderAuditMessage(salesOrder.getSalesOrderId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/virtualDelivery/{code}")
  public void sendSalesOrderVirtualProductMessage(@PathVariable("code") String code) {
    SalesOrder salesOrder = new SalesOrder();
    salesOrder.setSalesOrderCode(code);
    salesOrder = salesOrderService.getByExample(salesOrder);
    SalesOrderDeliveryMessage message = new SalesOrderDeliveryMessage(
        salesOrder.getSalesOrderId());
    mqProducer.send(message);
  }

  @PostMapping(value = "/index/sync/period")
  public void syncPeriodSalesOrderIndexMessage(@RequestParam("begin") String begin,
      @RequestParam("end") String end) {
    SalesOrderQuery query = new SalesOrderQuery();
    query.setCreatedTimeBegin(DateTimeUtil.parserLocalDateTime(begin));
    query.setCreatedTimeEnd(DateTimeUtil.parserLocalDateTime(end));
    PageList<SalesOrder> pageList = salesOrderService.listPage(query, 1, PAGE_MAX_SIZE);
    List<GeneralMessage> messages = pageList.getData().stream()
        .map(SalesOrder::getSalesOrderId)
        .map(x -> new GeneralMessage(OmsModule.SALES_ORDER, x, EventType.CREATED))
        .collect(Collectors.toList());
    mqProducer.send(messages);

    if (pageList.getTotal() > PAGE_MAX_SIZE) {
      int totalPage = MallUtil.calcTotalPage(PAGE_MAX_SIZE, pageList.getTotal());
      for (int i = 2; i <= totalPage; i++) {
        PageList<SalesOrder> otherPageList = salesOrderService.listPage(query, i, PAGE_MAX_SIZE);
        List<GeneralMessage> otherMessages = otherPageList.getData().stream()
            .map(SalesOrder::getSalesOrderId)
            .map(x -> new GeneralMessage(OmsModule.SALES_ORDER, x, EventType.CREATED))
            .collect(Collectors.toList());
        mqProducer.send(otherMessages);
      }
    }
  }

  @PostMapping(value = "/index/{salesOrderCode}")
  public void syncSalesOrderIndexMessage(@PathVariable("salesOrderCode") String salesOrderCode) {
    SalesOrder salesOrder = new SalesOrder();
    salesOrder.setSalesOrderCode(salesOrderCode);
    salesOrder = salesOrderService.getByExample(salesOrder);
    if (!Assert.isNull(salesOrder)) {
      mqProducer.send(
          new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.CREATED)
      );
    }
  }
}
