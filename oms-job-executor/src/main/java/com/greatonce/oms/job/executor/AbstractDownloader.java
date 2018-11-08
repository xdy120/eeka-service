package com.greatonce.oms.job.executor;

import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.job.executor.download.StoreDownloadJob;
import java.time.LocalDateTime;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 下载抽象类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-22
 */
public abstract class AbstractDownloader implements Job {

  private final Logger LOGGER = LoggerFactory.getLogger(AbstractDownloader.class);
  @Autowired
  private StoreService storeService;
  @Autowired
  private StoreDownloadConfigService storeDownloadConfigService;

  protected abstract Logger getLogger();

  protected abstract void doDownload(StoreDownloadConfig config, Store store,
      LocalDateTime endTime);

  /**
   * 下载.
   */
  public void download(StoreDownloadConfig config) {
    Store store = storeService.getByKey(config.getStoreId());
    if (store == null || !store.isEnable()) {
      getLogger().debug("店铺不存在或已禁用：{}", config.getStoreName());
      return;
    }
    //截止时间
    LocalDateTime deadline = LocalDateTime.now().minusMinutes(config.getDelayMinutes());
    if (config.getBeginTime().isAfter(deadline)) {
      if (getLogger().isDebugEnabled()) {
        getLogger()
            .debug("未到延迟时间：{},{},{}", config.getStoreName(), config.getDownloadType().caption(),
                config.getBeginTime());
      }
      return;
    }
    LocalDateTime endTime = config.getBeginTime().plusSeconds(config.getIntervalSeconds());
    if (endTime.isAfter(deadline)) {
      if (getLogger().isDebugEnabled()) {
        getLogger()
            .debug("结束时间大于截止时间：{},{},截止时间：{}，结束时间：{}", config.getStoreName(),
                config.getDownloadType().caption(),
                deadline, endTime);
      }
      return;
    }
    doDownload(config, store, endTime);
    config.setBeginTime(endTime);
    storeDownloadConfigService.modify(config);
  }


  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    try {
      Long configId = jobExecutionContext.getJobDetail().getJobDataMap()
          .getLong(StoreDownloadJob.DOWNLOAD_CONFIG_KEY);
      StoreDownloadConfig config = storeDownloadConfigService.getByKey(configId);
      download(config);
    } catch (Exception e) {
      LOGGER.info("下载异常,堆栈信息:", e);
    }
  }
}
