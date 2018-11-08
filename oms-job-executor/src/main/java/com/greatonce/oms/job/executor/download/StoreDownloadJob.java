package com.greatonce.oms.job.executor.download;

import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.job.executor.TriggerType;
import java.util.List;
import javax.annotation.Resource;

import com.greatonce.oms.query.base.StoreDownloadConfigQuery;
import org.quartz.CronScheduleBuilder;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/17
 */
@DisallowConcurrentExecution
public class StoreDownloadJob implements Job {

  public static final String DOWNLOAD_CONFIG_KEY = "DOWNLOAD_CONFIG_KEY";
  private static final Logger LOGGER = LoggerFactory.getLogger(StoreDownloadJob.class);
  @Resource
  private Scheduler downloadScheduler;
  @Autowired
  private StoreDownloadConfigService storeDownloadConfigService;

  @SuppressWarnings("unchecked")
  private void begin(StoreDownloadConfig config) throws SchedulerException {
    JobKey jobKey = buildJobKey(config);
    if (!downloadScheduler.checkExists(jobKey)) {
      try {
        Class<?> clazz = Class.forName(config.getJobClass());
        if (Job.class.isAssignableFrom(clazz)) {
          JobDetail jobDetail = JobBuilder.newJob((Class<? extends Job>) clazz)
              .withIdentity(jobKey).build();
          jobDetail.getJobDataMap().put(DOWNLOAD_CONFIG_KEY, config.getStoreDownloadConfigId());
          Trigger trigger;
          if (config.getJobTriggerType().toString().equals(TriggerType.SIMPLE.toString())) {
            trigger = TriggerBuilder.newTrigger()
                .withIdentity(
                    "DL_" + config.getDownloadType() + "_" + config.getStoreDownloadConfigId())
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInSeconds(config.getJobInterval()).repeatForever())
                .startNow()
                .build();
          } else {
            trigger = TriggerBuilder.newTrigger()
                .withIdentity(
                    "DL_" + config.getDownloadType() + "_" + config.getStoreDownloadConfigId())
                .withSchedule(CronScheduleBuilder
                    .cronSchedule(config.getJobCron()))
                .startNow()
                .build();
          }
          downloadScheduler.scheduleJob(jobDetail, trigger);
          LOGGER.info("店铺：{}{}启动下载", config.getStoreName(), config.getDownloadType());
        } else {
          LOGGER
              .error("店铺：{}{}启动下载失败，任务类没有实现Job接口", config.getStoreName(), config.getDownloadType());
        }
      } catch (Exception e) {

        LOGGER.error("店铺：{}{}启动下载失败：{}", config.getStoreName(), config.getDownloadType(),
            e.getMessage());
        LOGGER.error("下载任务启动失败", e);
      }
    }
  }

  private JobKey buildJobKey(StoreDownloadConfig config) {
    return JobKey.jobKey(config.getStoreName() + config.getDownloadType().caption(),
        config.getDownloadType().toString());
  }

  private void finish(StoreDownloadConfig config) throws SchedulerException {
    JobKey jobKey = buildJobKey(config);
    if (downloadScheduler.checkExists(jobKey)) {
      try {
        downloadScheduler.deleteJob(jobKey);
        LOGGER.info("店铺：{}{}停止下载...", config.getStoreName(), config.getDownloadType());
      } catch (SchedulerException e) {
        e.printStackTrace();
      }

    }
  }

  private void parse(StoreDownloadConfig config) throws SchedulerException {
    if (config.isEnable()) {
      begin(config);
    } else {
      finish(config);
    }
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    List<StoreDownloadConfig> jobs = storeDownloadConfigService.list(null);
    for (StoreDownloadConfig job : jobs) {
      try {
        parse(job);
      } catch (SchedulerException e) {
        LOGGER.error("下载服务处理异常!", e);
      }
    }
  }
}
