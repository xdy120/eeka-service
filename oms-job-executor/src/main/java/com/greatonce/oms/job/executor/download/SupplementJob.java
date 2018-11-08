package com.greatonce.oms.job.executor.download;

import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yiyang
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/6/30
 */
@DisallowConcurrentExecution
public class SupplementJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(SupplementJob.class);

  @Autowired
  private StoreDownloadConfigService storeDownloadConfigService;

  private void execute(StoreDownloadConfig config) {
    try {
      //将开始时间设置为前一天
      config.setBeginTime(config.getBeginTime().minusDays(1));
      storeDownloadConfigService.modify(config);
      LOGGER.info("店铺：{} 开始补单", config.getStoreName());
      LOGGER.info("重置时间为：", config.getBeginTime());
    } catch (Exception e) {
      LOGGER.error("店铺：{} 开始补单失败", config.getStoreName());
    }
  }

  @Override
  public void execute(JobExecutionContext context) {
    StoreDownloadConfig config = new StoreDownloadConfig();
    config.setEnable(true);
    config.setSupplement(true);
    //获取到所有启动了并且开启自动补单的下载配置
    List<StoreDownloadConfig> configs = storeDownloadConfigService.listExample(config);
    for (StoreDownloadConfig storeDownloadConfig : configs) {
      execute(storeDownloadConfig);
    }
  }
}
