package com.greatonce.oms.consumer.base.store;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.DownloadType;
import com.greatonce.oms.domain.enums.MallType;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 唯品会初始化.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
@Component
public class VipStoreInitialization extends DefaultStoreInitializer {

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.VIP};
  }

  @Override
  protected List<StoreDownloadConfig> initDownloadConfig(Store store) {
    List<StoreDownloadConfig> list = new ArrayList<>(3);
    list.add(createDownloadConfig(store, DownloadType.VIP_ORDER,
        "com.greatonce.oms.job.executor.vip.VipOrderDownloader", 5, 120, 60));
    list.add(createDownloadConfig(store, DownloadType.VIP_CANCEL_ORDER,
        "com.greatonce.oms.job.executor.vip.VipCancelOrderDownloader", 5, 120, 60));
    list.add(createDownloadConfig(store, DownloadType.VIP_PICK_ORDER,
        "com.greatonce.oms.job.executor.vip.VipPickOrderDownloader", 5, 300, 120));
    list.add(createDownloadConfig(store, DownloadType.VIP_RETURN_ORDER,
        "com.greatonce.oms.job.executor.vip.VipRefundOrderDownloader", 5, 300, 120));
    return list;
  }
}
