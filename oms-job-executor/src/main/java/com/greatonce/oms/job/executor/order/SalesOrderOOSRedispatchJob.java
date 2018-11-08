package com.greatonce.oms.job.executor.order;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.message.trade.SalesOrderOOSRedispatchMessage;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/7/18
 */
@DisallowConcurrentExecution
public class SalesOrderOOSRedispatchJob implements Job {

  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private MqProducer mqProducer;

  @Override
  public void execute(JobExecutionContext context) {
    List<Long> oosIds = salesOrderService.listOOSRedispatchSalesOrderIds();
    if (!Assert.isEmpty(oosIds)) {
      salesOrderService.clearOos();
      oosIds.forEach(x -> {
        SalesOrderOOSRedispatchMessage oosRedispatchMessage = new SalesOrderOOSRedispatchMessage(x);
        mqProducer.send(oosRedispatchMessage);
      });
    }
  }
}
