package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.admin.MallAppService;
import com.greatonce.oms.biz.base.PrivilegeService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractEnableService;
import com.greatonce.oms.bridge.mall.AuthorizeBridge;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.request.AuthorizeRequest;
import com.greatonce.oms.bridge.mall.response.AuthorizeResponse;
import com.greatonce.oms.dao.base.StoreDao;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.StoreSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.PrivilegeType;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.query.base.StoreQuery;
import com.greatonce.oms.util.CacheableNotNull;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * 店铺.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class StoreServiceImpl extends AbstractEnableService<Store, StoreQuery> implements
    StoreService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_STORE);
  private static final String CACHE_NAME = "STORE";


  @Autowired
  private StoreDao dao;
  @Autowired
  private MallAppService mallAppService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private PrivilegeService privilegeService;

  @Override
  protected QueryDao<Store, StoreQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(Store entity) {
    super.initDefaultValue(entity);
    entity.setEnable(false);
  }

  @Override
  public int create(Store record) {
    int count = super.create(record);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.BASE_STORE, record.getStoreId(), EventType.CREATED));
    return count;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'STORE_'+#record.storeId")
  public int remove(Store record) {
    int count = super.remove(record);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.BASE_STORE, record.getStoreId(), EventType.REMOVED));
    return count;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'STORE_'+#record.storeId")
  public int modify(Store record) {
    int count = super.modify(record);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.BASE_STORE, record.getStoreId(), EventType.MODIFIED));
    return count;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'STORE_'+#store.storeId")
  public int enable(Store store) {
    int count = super.enable(store);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.BASE_STORE, store.getStoreId(), EventType.ENABLED));
    return count;
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'STORE_'+#store.storeId")
  public int disable(Store store) {
    int count = super.disable(store);
    getMqProducer()
        .send(new GeneralMessage(OmsModule.BASE_STORE, store.getStoreId(), EventType.DISABLED));
    return count;
  }

  @Override
  @CacheableNotNull(value = CACHE_NAME, key = "'STORE_'+#id")
  public Store getByKey(Long id) {
    Store store = super.getByKey(id);
    if (!Assert.isNull(store)){
      store.setSetting(JsonUtil.toObject(store.getSettingJson(), StoreSetting.class));
      store.setMallApp(mallAppService.getByKey(store.getMallAppId()));
    }
    return store;
  }

  @Override
  public boolean exists(String storeCode) {
    return dao.exists(storeCode);
  }

  @Override
  public String authUrl(Store store) {
    AuthorizeBridge authorizeBridge = mallBridgeFactory.getAuthorizeBridge(store.getMallType());
    return authorizeBridge.authorizeUrl(new AuthorizeRequest(store));
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'STORE_'+#store.storeId")
  public void assessToken(Store store, String code) {
    if (Assert.isEmpty(code)) {
      throw SysExceptions.PARAMETER_NOT_ALLOW_EMPTY;
    }
    AuthorizeBridge authorizeBridge = mallBridgeFactory.getAuthorizeBridge(store.getMallType());
    AuthorizeRequest authorizeRequest = new AuthorizeRequest(store, code);
    AuthorizeResponse response = authorizeBridge.accessToken(authorizeRequest);
    store.setAccessToken(response.getAccessToken());
    store.setAccessTokenExpirationTime(response.getAccessExpire());
    store.setRefreshToken(response.getRefreshToken());
    store.setRefreshTokenExpirationTime(response.getRefreshExpire());
    modify(store);
    BIZ_LOGGER.log(store.getStoreId(), "重新授权");
  }

  @Override
  @CacheEvict(value = CACHE_NAME, key = "'STORE_'+#store.storeId")
  public void refreshToken(Store store) {
    if (Assert.isEmpty(store.getRefreshToken())) {
      throw SysExceptions.PARAMETER_NOT_ALLOW_EMPTY;
    }
    AuthorizeBridge authorizeBridge = mallBridgeFactory.getAuthorizeBridge(store.getMallType());
    AuthorizeRequest authorizeRequest = new AuthorizeRequest(store);
    AuthorizeResponse response = authorizeBridge.refreshToken(authorizeRequest);
    store.setAccessToken(response.getAccessToken());
    store.setAccessTokenExpirationTime(response.getAccessExpire());
    modify(store);
    BIZ_LOGGER.log(store.getStoreId(), "刷新授权");
  }

  @Override
  public List<Store> listUserStore(Long userId) {
    Store eg = new Store();
    eg.setEnable(true);
    List<Store> stores = listExample(eg);
    privilegeService.filter(userId, stores, Store::getStoreId, PrivilegeType.STORE);
    return stores;
  }

  @Override
  public List<Store> listEnableStore() {
    StoreQuery eg = new StoreQuery();
    eg.setEnable(true);
    return dao.list(eg);
  }
}