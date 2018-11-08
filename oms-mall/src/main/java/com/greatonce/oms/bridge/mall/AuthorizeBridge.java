package com.greatonce.oms.bridge.mall;

import com.greatonce.oms.bridge.mall.request.AuthorizeRequest;
import com.greatonce.oms.bridge.mall.response.AuthorizeResponse;

/**
 * 授权接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
public interface AuthorizeBridge extends MallBridge {

  String authorizeUrl(AuthorizeRequest request);

  AuthorizeResponse accessToken(AuthorizeRequest request);

  AuthorizeResponse refreshToken(AuthorizeRequest request);
}
