package com.greatonce.oms.job.executor.download;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.product.mall.mapping.ProductMappingDownload;
import com.greatonce.oms.bo.product.ProductMallMappingDownloadBO;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreSetting;
import com.greatonce.oms.domain.enums.product.MallProductStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * 铺货自动下载.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author cca
 * @version 2018-07-06
 */
@DisallowConcurrentExecution
public class ProductMappingJob implements Job {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductMappingJob.class);

  @Value("${oms.job.product.mapping.days:2}")
  private Integer days;

  @Autowired
  private StoreService storeService;
  @Autowired
  private ProductMappingDownload productMappingDownload;

  /**
   * 下载铺货.
   */
  public void execute(Store store) {
    try {
      ProductMallMappingDownloadBO downloadBO = new ProductMallMappingDownloadBO();
      downloadBO.setStoreId(store.getStoreId());
      downloadBO.setStore(store);
      downloadBO.setMallProductStatus(MallProductStatus.ONSALE);
      downloadBO.setEndTime(LocalDateTime.now());
      downloadBO.setBeginTime(downloadBO.getEndTime().minusDays(days));
      productMappingDownload.download(downloadBO);
    } catch (Exception e) {
      LOGGER.error("店铺：{}下载铺货失败", store.getStoreName());
      LOGGER.error("下载铺货失败,堆栈信息:", e);
    }
  }

  @Override
  public void execute(JobExecutionContext jobExecutionContext) {
    Store eg = new Store();
    eg.setEnable(true);
    List<Store> stores = storeService.listExample(eg);

    for (Store store : stores) {
      final StoreSetting storeSetting = JsonUtil
          .toObject(store.getSettingJson(), StoreSetting.class);
      if (Assert.isTrue(storeSetting.isAutoProductMapping())) {
        execute(store);
      }
    }
  }
}
