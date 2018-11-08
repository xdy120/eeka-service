package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.mall.AuthorizeBridge;
import com.greatonce.oms.bridge.mall.request.MallRequest;

/**
 * @author ginta
 */
public abstract class AbstractAuthorizeBridge extends AbstractBridge implements AuthorizeBridge {



  protected String getCallbackUrl(MallRequest request) {
    return Assert.isEmpty(request.getStore().getMallApp().getCallbackUrl())
        ? "http://www.greatonce.com" : request.getStore().getMallApp().getCallbackUrl();
  }
}
