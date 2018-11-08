package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.query.base.StoreDownloadConfigQuery;

/**
 * 下载配置.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StoreDownloadConfigService extends
    BatchBizService<StoreDownloadConfig, StoreDownloadConfigQuery> {

  void removeByStoreId(Long storeId);

  int enable(StoreDownloadConfig config);

  int disable(StoreDownloadConfig config);

  int enableSupplement(Long id, VersionBO bo);

  int disableSupplement(Long id, VersionBO bo);
}