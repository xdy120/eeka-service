package com.greatonce.oms.job.executor.vip;

import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderQueryRequest;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.job.executor.AbstractDownloader;
import java.time.LocalDateTime;
import java.util.Map;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@DisallowConcurrentExecution
public class VipPickOrderDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(VipPickOrderDownloader.class);
  @Autowired
  private VipDispatchService vipDispatchService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;
  @Autowired
  private DataDictItemService dataDictItemService;


  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    StockDispatchStrategy setting = stockDispatchStrategyService
        .getByKey(store.getSetting().getStockStrategy());
    VipPickOrderQueryRequest request = new VipPickOrderQueryRequest(store);
    request.setBeginTime(config.getBeginTime());
    request.setEndTime(endTime);
    request.setPage(1);
    request.setStoreDownloadConfig(config);
    Map<String, String> itemMap = dataDictItemService.listMapByDictId(10804L);
    vipDispatchService.download(store, request, setting, itemMap);
  }
}
