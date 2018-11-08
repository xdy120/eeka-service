package com.greatonce.oms.bridge.mall.impl.taobao;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractMall;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoRequest;
import com.taobao.api.TaobaoResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/**
 * 淘宝请求封装
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/16
 */
@Component
public class TaobaoMall extends AbstractMall {

  protected static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  protected static final Map<String, DefaultTaobaoClient> clientMap = new ConcurrentHashMap<>(1);

  @Override
  protected String getDefaultUrl() {
    return "https://eco.taobao.com/router/rest";
  }

  public DefaultTaobaoClient getClient(Store store) {
    DefaultTaobaoClient client = clientMap.get(store.getMallApp().getAppKey());
    if (client == null) {
      client = new DefaultTaobaoClient(getUrl(store), store.getMallApp().getAppKey(),
          store.getMallApp().getAppSecret());
      clientMap.put(store.getMallApp().getAppKey(), client);
    }
    return client;
  }

  public <T extends TaobaoResponse> T call(Store store, TaobaoRequest<T> request) {
    return call(store, request, true);
  }

  /**
   * 请求平台.
   */
  public <T extends TaobaoResponse> T call(Store store, TaobaoRequest<T> request,
      boolean isValidate) {
    try {
      DefaultTaobaoClient client = getClient(store);
      T t = client.execute(request, store.getAccessToken());
      MALL_LOGGER.info(store, "api:{}，request：{}，response：{}", request.getApiMethodName(),
          request.getTextParams(), t.getBody());
      if (isValidate && !t.isSuccess()) {
        throw fillException(t);
      }
      return t;
    } catch (ApiException ex) {
      MALL_LOGGER.info(store, "api:{}，request：{}，response：{}", request.getApiMethodName(),
          request.getTextParams(), ex.getMessage());
      throw new MallException("请求失败！" + ex.getErrMsg());
    }
  }

  protected MallException fillException(TaobaoResponse response) {
    if (!Assert.isEmpty(response.getSubMsg())) {
      return new MallException(response.getSubMsg());
    } else if (!Assert.isEmpty(response.getMsg())) {
      return new MallException(response.getMsg());
    } else {
      return new MallException("淘宝错误：" + response.getBody());
    }
  }
}
