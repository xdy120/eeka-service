package com.greatonce.oms.consumer.base.store;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.DownloadType;
import com.greatonce.oms.domain.enums.MallType;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * 阿里店铺初始化.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/16
 */
@Component
public class AliStoreInitialization extends DefaultStoreInitializer {

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TAOBAO_JX, MallType.TAOBAO_FX, MallType.TMALL,
        MallType.TMALL_HK};
  }

  @Override
  protected List<StoreDownloadConfig> initDownloadConfig(Store store) {
    List<StoreDownloadConfig> list = super.initDownloadConfig(store);
    list.add(createDownloadConfig(store, DownloadType.REFUND_ORDER,
        "com.greatonce.oms.job.executor.download.RefundDownloader"));
    list.add(createDownloadConfig(store, DownloadType.EXCHANGE_ORDER,
        "com.greatonce.oms.job.executor.download.ExchangeDownloader"));
    list.add(createDownloadConfig(store, DownloadType.ALIPAY_RECORD,
        "com.greatonce.oms.job.executor.download.AlipayRecordDownloader"));
    return list;
  }
}
