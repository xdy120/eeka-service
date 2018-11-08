package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.StoreDownloadConfigService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.dao.base.StoreDownloadConfigDao;
import com.greatonce.oms.domain.base.StoreDownloadConfig;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.StoreDownloadConfigQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * StoreDownloadConfig <br/> 下载配置.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-14
 */

@Service
public class StoreDownloadConfigServiceImpl extends
    AbstractVersionService<StoreDownloadConfig, StoreDownloadConfigQuery> implements
    StoreDownloadConfigService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.BASE_STORE_DOWNLOAD_CONFIG);
  @Autowired
  private StoreDownloadConfigDao dao;

  @Override
  protected QueryDao<StoreDownloadConfig, StoreDownloadConfigQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public int enable(StoreDownloadConfig config) {
    return setEnable(config, true);
  }

  @Override
  public int disable(StoreDownloadConfig config) {
    return setEnable(config, false);
  }

  protected int setEnable(StoreDownloadConfig config, boolean enable) {
    config.setEnable(enable);
    int count = modify(config);
    getBizLogger().log(config.getPrimaryKey(), enable ? BizLogger.ENABLE : BizLogger.DISABLE);
    return count;
  }

  @Override
  public int batchCreate(Collection<? extends StoreDownloadConfig> collection) {
    collection.forEach(this::initDefaultValue);
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends StoreDownloadConfig> collection) {
    return updateBatch(collection);
  }

  @Override
  public void removeByStoreId(Long storeId) {
    Assert.notNull(storeId, "店铺ID不能为空");
    StoreDownloadConfig eg = new StoreDownloadConfig();
    eg.setStoreId(storeId);
    deleteByExample(eg);
  }

  @Override
  public int enableSupplement(Long id, VersionBO bo) {
    StoreDownloadConfig storeDownloadConfig = new StoreDownloadConfig();
    storeDownloadConfig.setSupplement(true);
    storeDownloadConfig.setVersion(bo.getVersion());
    storeDownloadConfig.setStoreDownloadConfigId(id);
    return update(storeDownloadConfig);
  }

  @Override
  public int disableSupplement(Long id, VersionBO bo) {
    StoreDownloadConfig storeDownloadConfig = new StoreDownloadConfig();
    storeDownloadConfig.setSupplement(false);
    storeDownloadConfig.setVersion(bo.getVersion());
    storeDownloadConfig.setStoreDownloadConfigId(id);
    return update(storeDownloadConfig);
  }
}