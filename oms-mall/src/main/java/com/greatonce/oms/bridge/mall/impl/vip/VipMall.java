package com.greatonce.oms.bridge.mall.impl.vip;

import com.greatonce.oms.bridge.mall.impl.AbstractMall;
import com.greatonce.oms.domain.base.Store;
import com.vip.osp.sdk.context.InvocationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * VipMall
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/3
 */
@Component
public class VipMall extends AbstractMall {

  public static final Logger logger = LoggerFactory.getLogger(VipMall.class);

  //默认地址
  @Override
  protected String getDefaultUrl() {
    return "http://vipapis.com/";
  }

  /**
   * 初始化唯品上下文.
   */
  public void initContext(Store store) {
    //单例对象
    InvocationContext context = InvocationContext.Factory.getInstance();
    context.setAppKey(store.getMallApp().getAppKey());
    context.setAppSecret(store.getMallApp().getAppSecret());
    context.setAccessToken(store.getAccessToken());
    context.setAppURL(getUrl(store));
  }
}
