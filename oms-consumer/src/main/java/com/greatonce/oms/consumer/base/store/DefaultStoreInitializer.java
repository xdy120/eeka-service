package com.greatonce.oms.consumer.base.store;

import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.DownloadType;
import com.greatonce.oms.domain.enums.JobTriggerType;
import com.greatonce.oms.domain.enums.MallType;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * 默认店铺初始化.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-05
 */
@Primary
@Component
public class DefaultStoreInitializer implements StoreInitialization {

  @Autowired
  private StoreDownloadConfigService storeDownloadConfigService;

  protected StoreDownloadConfig createDownloadConfig(Store store, DownloadType downloadType,
      String className) {
    return createDownloadConfig(store, downloadType, className, 10, 60, 60);
  }

  protected StoreDownloadConfig createDownloadConfig(Store store, DownloadType downloadType,
      String className, Integer delay, Integer interval, Integer triggerInterval) {
    StoreDownloadConfig config = new StoreDownloadConfig();
    config.setBeginTime(LocalDateTime.now());
    config.setDelayMinutes(delay);
    config.setIntervalSeconds(interval);
    config.setDownloadType(downloadType);
    config.setEnable(false);
    config.setStoreId(store.getStoreId());
    config.setStoreName(store.getStoreName());
    config.setJobTriggerType(JobTriggerType.SIMPLE);
    config.setJobInterval(triggerInterval);
    config.setJobClass(className);
    config.setSupplement(false);
    return config;
  }

  protected StoreDownloadConfig createDownloadConfig(Store store, DownloadType downloadType,
      String className, Integer delay, Integer interval, String cron) {
    StoreDownloadConfig config = new StoreDownloadConfig();
    config.setBeginTime(LocalDateTime.now());
    config.setDelayMinutes(delay);
    config.setIntervalSeconds(interval);
    config.setDownloadType(downloadType);
    config.setEnable(false);
    config.setStoreId(store.getStoreId());
    config.setStoreName(store.getStoreName());
    config.setJobTriggerType(JobTriggerType.CORN);
    config.setJobCron(cron);
    config.setJobClass(className);
    config.setSupplement(false);
    return config;
  }

  protected List<StoreDownloadConfig> initDownloadConfig(Store store) {
    List<StoreDownloadConfig> list = new ArrayList<>(1);
    list.add(createDownloadConfig(store, DownloadType.SALES_ORDER,
        "com.greatonce.oms.job.executor.download.OrderDownloader"));
    return list;
  }

  @Override
  public MallType[] supports() {
    return null;
  }

  /**
   * 初始化店铺.
   */
  public void init(Store store) {
    List<StoreDownloadConfig> list = initDownloadConfig(store);
    storeDownloadConfigService.batchCreate(list);
  }

  /**
   * 删除卸载信息.
   */
  public void destroy(Store store) {
    storeDownloadConfigService.removeByStoreId(store.getStoreId());
  }
}
