package com.greatonce.oms.job.executor.vip;

import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.vip.VipReturnService;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipReturnQueryRequest;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.job.executor.AbstractDownloader;
import java.time.LocalDateTime;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@DisallowConcurrentExecution
public class VipRefundOrderDownloader extends AbstractDownloader {

  private static final Logger LOGGER = LoggerFactory.getLogger(VipRefundOrderDownloader.class);
  @Autowired
  private VipReturnService vipReturnService;
  @Autowired
  private DataDictItemService dataDictItemService;

  @Override
  protected Logger getLogger() {
    return LOGGER;
  }

  @Override
  protected void doDownload(StoreDownloadConfig config, Store store, LocalDateTime endTime) {
    List<DataDictItem> items = dataDictItemService.listByDictId(10804L);
    VipReturnQueryRequest queryRequest = new VipReturnQueryRequest(store);
    queryRequest.setBeginTime(config.getBeginTime());
    queryRequest.setEndTime(endTime);
    queryRequest.setPage(1);
    queryRequest.setStoreDownloadConfig(config);
    for (DataDictItem item : items) {
      queryRequest.setVipWarehouseCode(item.getDataDictItemCode());
      vipReturnService.download(queryRequest, store, item);
    }
  }
}
