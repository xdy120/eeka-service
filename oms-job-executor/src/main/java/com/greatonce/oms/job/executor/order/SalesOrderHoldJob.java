package com.greatonce.oms.job.executor.order;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.message.trade.SalesOrderHoldExpiredMessage;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 留单检查任务.
 *
 * @author Lcc
 * @version 2018/6/30 16:03
 */
@DisallowConcurrentExecution
public class SalesOrderHoldJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderHoldJob.class);

  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private MqProducer mqProducer;

  @Override
  public void execute(JobExecutionContext context) {
    List<DispatchStatus> dispatchStatuses = new ArrayList<>(2);
    dispatchStatuses.add(DispatchStatus.NONE);
    dispatchStatuses.add(DispatchStatus.PART);

    SalesOrderQuery query = new SalesOrderQuery();
    query.setDispatchStatuses(dispatchStatuses);
    query.setHold(true);
    LocalDate now = LocalDate.now();
    query.setHoldDateBegin(now.minusDays(1));
    query.setHoldDateEnd(now.plusDays(1));
    List<SalesOrder> holdingSalesOrders = salesOrderService.list(query);
    if (Assert.isEmpty(holdingSalesOrders)) {
      LOGGER.info("当日前一天和后一天无未配货留单订单");
      return;
    }

    parseHodingOrders(holdingSalesOrders);
  }

  /**
   * 解析留单订单.
   */
  private void parseHodingOrders(List<SalesOrder> holdingSalesOrders) {
    holdingSalesOrders.forEach(x -> {
      if (!x.getHoldDate().isAfter(LocalDate.now())) {
        dispatchExpiredHoldingOrder(x);
      }
    });
  }

  /**
   * 自动配留单到期的订单.
   */
  private void dispatchExpiredHoldingOrder(SalesOrder expiredOrder) {
    SalesOrderHoldExpiredMessage message =
        new SalesOrderHoldExpiredMessage(expiredOrder.getSalesOrderId());
    mqProducer.send(message);
  }
}
