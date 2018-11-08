package com.greatonce.oms.custom.eeka.job;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.custom.eeka.service.StockChangeService;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 铺货自动下载.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author cca
 * @version 2018-07-06
 */
@DisallowConcurrentExecution
public class SynchronizeVipStockToFMSJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(SynchronizeVipStockToFMSJob.class);

  private static final String LAST_DATE_TO_FMS = "lastDateToFMS";
  @Resource
  private StockChangeService stockChangeService;
  @Autowired
  private RedisTemplate redisTemplate;


  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    LOGGER.info("====同步唯品库存FMS开始====");
    if(!Assert.isNull(jobExecutionContext)){
      Trigger triger = jobExecutionContext.getTrigger();
      int priority = 0;
      if(!Assert.isNull(triger.getNextFireTime()) && !Assert.isNull(triger.getPreviousFireTime())){
        long time = TimeUnit.MILLISECONDS.toSeconds(triger.getNextFireTime().getTime() - triger.getPreviousFireTime().getTime());
        priority = Long.valueOf(time).intValue()/60;
      }
      //时间间隔(分钟)
      if(!Assert.isNull(triger) && priority > 0){
        Object lastDateToFMS = redisTemplate.opsForValue().get(LAST_DATE_TO_FMS);
        String beginDate = null;
        String endDate = null;
        if(Assert.isNull(lastDateToFMS)){
          endDate = DateTimeUtil.nowTime();
          LocalDateTime startTime = DateTimeUtil.parserLocalDateTime(endDate).minusMinutes(priority);
          beginDate = DateTimeUtil.format(startTime);
        }else{
          LocalDateTime endTime = DateTimeUtil.parserLocalDateTime(lastDateToFMS.toString()).plusMinutes(priority);
          beginDate = String.valueOf(lastDateToFMS);
          endDate = DateTimeUtil.format(endTime);
        }
        stockChangeService.getStockChange(beginDate,endDate);
        redisTemplate.opsForValue().set(LAST_DATE_TO_FMS,endDate);
      }
    }
  }
}
