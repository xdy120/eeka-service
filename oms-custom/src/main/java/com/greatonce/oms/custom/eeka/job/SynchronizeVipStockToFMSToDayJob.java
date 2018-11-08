package com.greatonce.oms.custom.eeka.job;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.custom.eeka.service.StockChangeService;
import java.time.LocalDate;
import javax.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 铺货自动下载.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author cca
 * @version 2018-07-06
 */
@DisallowConcurrentExecution
public class SynchronizeVipStockToFMSToDayJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizeVipStockToFMSToDayJob.class);

  @Resource
  private StockChangeService stockChangeService;


  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    LOGGER.info("====按天同步唯品库存FMS开始====");
    if(!Assert.isNull(jobExecutionContext)){
      //当前时间
      String nowDate = DateTimeUtil.nowDate();
      LocalDate day = DateTimeUtil.parserLocalDate(nowDate).minusDays(1L);
      String beginDate = day.toString()+" 00:00:00";
      String endDate = day.toString()+" 23:59:59";
      stockChangeService.getStockChange(beginDate,endDate);
    }
  }
}
