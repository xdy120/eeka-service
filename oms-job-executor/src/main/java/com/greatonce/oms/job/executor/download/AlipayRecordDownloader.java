package com.greatonce.oms.job.executor.download;

import com.greatonce.oms.biz.finance.AlipayRecordService;
import com.greatonce.oms.bo.finance.AlipayRecordDownloadBO;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.job.executor.AbstractDownloader;
import java.time.LocalDateTime;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class AlipayRecordDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(AlipayRecordDownloader.class);
  @Autowired
  private AlipayRecordService alipayRecordService;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    AlipayRecordDownloadBO downloadBO = new AlipayRecordDownloadBO();
    downloadBO.setStoreId(store.getStoreId());
    downloadBO.setStore(store);
    downloadBO.setBeginTime(config.getBeginTime());
    downloadBO.setEndTime(endTime);
    alipayRecordService.download(downloadBO);
  }
}
