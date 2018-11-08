package com.greatonce.oms.consumer.base.store;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.DownloadType;
import com.greatonce.oms.domain.enums.MallType;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 京东店铺初始化.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
@Component
public class JdStoreInitialization extends DefaultStoreInitializer {

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.JD};
  }

  @Override
  protected List<StoreDownloadConfig> initDownloadConfig(Store store) {
    List<StoreDownloadConfig> list = super.initDownloadConfig(store);
    StoreDownloadConfig config = createDownloadConfig(store, DownloadType.REFUND_ORDER,
        "com.greatonce.oms.job.executor.download.RefundDownloader");
    list.add(config);
    return list;
  }
}
