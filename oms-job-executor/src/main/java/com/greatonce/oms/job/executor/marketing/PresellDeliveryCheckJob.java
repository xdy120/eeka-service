package com.greatonce.oms.job.executor.marketing;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.marketing.PresellService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.Constants;
import com.greatonce.oms.domain.enums.marketing.PresellStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.message.trade.SalesOrderPresellDeliveryMessage;
import com.greatonce.oms.query.marketing.PresellQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 预售检查任务.
 *
 * @author Lcc
 * @version 2018/7/2 13:55
 */
@DisallowConcurrentExecution
public class PresellDeliveryCheckJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(PresellDeliveryCheckJob.class);

  @Autowired
  private PresellService presellService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private ManualTransactionTemplate transactionTemplate;
  @Autowired
  private MqProducer mqProducer;

  @Override
  public void execute(JobExecutionContext context) {
    SalesOrderDetail egDetail = new SalesOrderDetail();
    egDetail.setPresellDeliveryDate(LocalDate.now());
    egDetail.setStatus(SalesOrderDetailStatus.WAITING);
    List<SalesOrderDetail> presellDetails = salesOrderDetailService.listExample(egDetail);
    dispatchPresellDetails(presellDetails);

    PresellQuery egPresell = new PresellQuery();
    egPresell.setStatus(PresellStatus.BEGIN);
    egPresell.setEndTimeBegin(LocalDateTime.now().minusDays(1L));
    egPresell.setEndTimeEnd(LocalDateTime.now());
    List<Presell> expiredPresells = presellService.list(egPresell);
    endExpiredPresells(expiredPresells);
  }

  /**
   * 自动结束到期预售.
   */
  private void endExpiredPresells(List<Presell> expiredPresells) {
    if (Assert.isEmpty(expiredPresells)) {
      LOGGER.debug("无到期预售");
      return;
    }

    try {
      expiredPresells.forEach(x -> {
        VersionBO bo = new VersionBO();
        bo.setVersion(x.getVersion());
        presellService.end(x, bo);
      });
    } catch (Exception e) {
      LOGGER.error("预售到期任务结束到期预售失败");
      LOGGER.error("错误堆栈信息", e);
    }
  }

  /**
   * 预售到期明细配货.
   */
  private void dispatchPresellDetails(List<SalesOrderDetail> presellDetails) {
    if (Assert.isEmpty(presellDetails)) {
      LOGGER.debug("无到发货日期的预售明细");
      return;
    }

    try {
      transactionTemplate.execute(() ->
          presellDetails.forEach(x ->
              stockOccupancyService
                  .updateOccupancy(x.getSalesOrderId(), x.getSalesOrderDetailId(),
                      StockOccupancyType.SALES_ORDER, StockOccupancyStatus.UNLOCK)));
    } catch (Exception e) {
      LOGGER.error("预售到期定时任务更新占用状态失败");
      LOGGER.error("错误堆栈信息", e);
    }

    presellDetails.stream().map(x -> x.getSalesOrderId()).distinct().forEach(x -> {
      SalesOrderPresellDeliveryMessage message = new SalesOrderPresellDeliveryMessage(x);
      mqProducer.send(message);
    });
    presellDetails.forEach(detail -> mqProducer
        .send(new StockChangedMessage(detail.getSkuId(), Constants.SYSTEM_OPERATOR, "预售到期")));
  }
}
