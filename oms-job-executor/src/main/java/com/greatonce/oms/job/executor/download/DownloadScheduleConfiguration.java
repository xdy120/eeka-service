package com.greatonce.oms.job.executor.download;

import com.greatonce.core.supports.quartz.AutowireBeanJobFactory;
import com.greatonce.core.supports.quartz.QuartzTool;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 下载配置.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/17
 */
@Configuration
public class DownloadScheduleConfiguration {

  @Autowired
  private AutowireBeanJobFactory autowireBeanJobFactory;

  @Bean
  public Scheduler downloadScheduler() throws Exception {
    return QuartzTool.scheduler("downloadScheduler", autowireBeanJobFactory);
  }
}
