package com.greatonce.oms.job.executor.vip;

import com.greatonce.oms.biz.vip.VipSalesOrderDetailService;
import com.greatonce.oms.bridge.mall.impl.vip.VipOrderBridge;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipCancelOrderQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipCancelOrderQueryResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.job.executor.AbstractDownloader;
import java.time.LocalDateTime;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 唯品取消单.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-06
 */
@DisallowConcurrentExecution
public class VipCancelOrderDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(VipCancelOrderDownloader.class);
  @Autowired
  private VipOrderBridge vipOrderBridge;
  @Autowired
  private VipSalesOrderDetailService vipSalesOrderDetailService;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    VipCancelOrderQueryRequest request = new VipCancelOrderQueryRequest(store);
    request.setBeginTime(config.getBeginTime());
    request.setEndTime(endTime);
    request.setPage(1);
    request.setStoreDownloadConfig(config);
    download(store, request);
  }


  protected void download(Store store, VipCancelOrderQueryRequest request) {
    VipCancelOrderQueryResponse response = vipOrderBridge.queryCanceledOrders(request);
    LOGGER.info("下载【{}】{}~{}第{}页取消单共{}条数据...", store.getStoreName(), request.getBeginTime(),
        request.getEndTime(), request.getPage(), response.getCount());
    cancelOccupation(store, response);
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      download(store, request);
    }

  }

  protected void cancelOccupation(Store store, VipCancelOrderQueryResponse response) {
    if (response.isSuccess() && response.getOrders() != null) {
      vipSalesOrderDetailService.cancelByBuyer(store, response.getOrders());
    }
  }
}
