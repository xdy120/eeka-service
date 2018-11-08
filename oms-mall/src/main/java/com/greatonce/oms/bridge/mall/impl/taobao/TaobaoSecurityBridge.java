package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.bridge.mall.impl.AbstractSecurityBridge;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.SecretException;
import com.taobao.api.security.SecurityClient;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 淘宝安全接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/28
 */
@Component
public class TaobaoSecurityBridge extends AbstractSecurityBridge {

  private static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  private final Map<String, SecurityClient> clientMap = new ConcurrentHashMap<>(1);

  @Value("${oms.mall.tb.stress-testing:false}")
  private boolean stressTesting;

  @Override
  public String decrypt(Store store, String value, DataType dataType) {
    SecurityClient securityClient = getClient(store);
    try {
      if (stressTesting) {
        return securityClient.decrypt(value, convert(dataType), store.getAccessToken());
      } else {
        return securityClient.decrypt(value, convert(dataType));
      }
    } catch (SecretException e) {
      MALL_LOGGER.error(store, "淘宝解密失败！", e);
      return value;
    }
  }

  @Override
  public String encrypt(Store store, String value, DataType dataType) {
    SecurityClient securityClient = getClient(store);
    try {
      if (stressTesting) {
        return securityClient.encrypt(value, convert(dataType), store.getAccessToken());
      } else {
        return securityClient.encrypt(value, convert(dataType));
      }
    } catch (SecretException e) {
      MALL_LOGGER.error(store, "淘宝加密失败！", e);
      return value;
    }
  }

  /**
   * 批量解密.
   *
   * @return Map（key=未解密的值，value=解密后的值）
   */
  @Override
  public Map<String, String> decrypt(Store store, List<String> values, DataType dataType) {
    SecurityClient securityClient = getClient(store);
    try {
      if (stressTesting) {
        return securityClient.decrypt(values, convert(dataType), store.getAccessToken());
      } else {
        return securityClient.decrypt(values, convert(dataType));
      }
    } catch (SecretException e) {
      MALL_LOGGER.error(store, "淘宝解密失败！", e);
      return CollectionUtil.listToMap(values, x -> x);
    }
  }

  /**
   * 批量加密.
   */
  @Override
  public Map<String, String> encrypt(Store store, List<String> values, DataType dataType) {
    SecurityClient securityClient = getClient(store);
    try {
      if (stressTesting) {
        return securityClient.encrypt(values, convert(dataType), store.getAccessToken());
      } else {
        return securityClient.encrypt(values, convert(dataType));
      }
    } catch (SecretException e) {
      MALL_LOGGER.error(store, "淘宝加密失败！", e);
      return CollectionUtil.listToMap(values, x -> x);
    }
  }

  private SecurityClient getClient(Store store) {
    SecurityClient client = clientMap.get(store.getMallApp().getAppKey());
    if (client == null) {
      synchronized (this) {
        DefaultTaobaoClient taobaoClient = new DefaultTaobaoClient(
            "https://eco.taobao.com/router/rest", store.getMallApp().getAppKey(),
            store.getMallApp().getAppSecret());
        client = new SecurityClient(taobaoClient, store.getMallApp().getSafetyCertificate());
        clientMap.put(store.getMallApp().getAppKey(), client);
      }
    }
    return client;
  }

  private String convert(DataType dataType) {
    switch (dataType) {
      case MOBILE:
        return "phone";
      default:
        return "nick";

    }
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TMALL, MallType.TMALL_HK, MallType.TAOBAO_FX,
        MallType.TAOBAO_JX};
  }
}
