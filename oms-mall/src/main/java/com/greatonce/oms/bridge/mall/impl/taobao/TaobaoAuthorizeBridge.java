package com.greatonce.oms.bridge.mall.impl.taobao;

import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.core.util.WebUtil;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractAuthorizeBridge;
import com.greatonce.oms.bridge.mall.request.AuthorizeRequest;
import com.greatonce.oms.bridge.mall.response.AuthorizeResponse;
import com.greatonce.oms.domain.admin.MallApp;
import com.greatonce.oms.domain.enums.MallType;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 淘宝授权接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
@Component
public class TaobaoAuthorizeBridge extends AbstractAuthorizeBridge {

  static final Logger LOGGER = LoggerFactory.getLogger(TaobaoAuthorizeBridge.class);

  @Override
  public String authorizeUrl(AuthorizeRequest request) {
    MallApp mallApp = request.getStore().getMallApp();
    return StringUtil.format(
        "https://oauth.taobao.com/authorize?response_type=code&client_id={0}&redirect_uri={1}",
        mallApp.getAppKey(), getCallbackUrl(request));
  }

  @Override
  public AuthorizeResponse accessToken(AuthorizeRequest request) {
    MallApp mallApp = request.getStore().getMallApp();
    String url = "https://oauth.taobao.com/token";
    Map<String, String> map = new HashMap<>(5);
    map.put("grant_type", "authorization_code");
    map.put("code", request.getCode());
    map.put("client_id", mallApp.getAppKey());
    map.put("client_secret", mallApp.getAppSecret());
    map.put("redirect_uri", getCallbackUrl(request));
    try {
      String result = WebUtil.doPost(url, map);
      LOGGER.info("淘宝授权：code：{},{}", request.getCode(), result);
      JSONObject jsonObject = JsonUtil.toJSONObject(result);
      AuthorizeResponse response = new AuthorizeResponse(request);
      response.setAccessToken(jsonObject.getString("access_token"));
      response.setAccessExpire(LocalDateTime.now().plusSeconds(jsonObject.getLong("expires_in")));
      response.setRefreshToken(jsonObject.getString("refresh_token"));
      response
          .setRefreshExpire(LocalDateTime.now().plusSeconds(jsonObject.getLong("re_expires_in")));
      return response;
    } catch (IOException e) {
      LOGGER.error("淘宝授权错误!", e);
      throw new MallException("淘宝授权失败！" + e.getMessage());
    }
  }

  @Override
  public AuthorizeResponse refreshToken(AuthorizeRequest request) {
    MallApp mallApp = request.getStore().getMallApp();
    String url = "https://oauth.taobao.com/token";
    Map<String, String> map = new HashMap<>(4);
    map.put("grant_type", "refresh_token");
    map.put("client_id", mallApp.getAppKey());
    map.put("client_secret", mallApp.getAppSecret());
    map.put("refresh_token", request.getStore().getRefreshToken());
    try {
      String result = WebUtil.doPost(url, map);
      LOGGER.info("淘宝刷新令牌：refresh_token：{},{}", request.getStore().getRefreshToken(), result);
      JSONObject jsonObject = JsonUtil.toJSONObject(result);
      AuthorizeResponse response = new AuthorizeResponse(request);
      response.setAccessToken(jsonObject.getString("access_token"));
      response.setAccessExpire(LocalDateTime.now().plusSeconds(jsonObject.getLong("expires_in")));
      return response;
    } catch (IOException e) {
      LOGGER.error("淘宝授权错误!", e);
      throw new MallException("淘宝授权失败！" + e.getMessage());
    }
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.TAOBAO, MallType.TMALL, MallType.TMALL_HK, MallType.TAOBAO_FX,
        MallType.TAOBAO_JX};
  }
}
