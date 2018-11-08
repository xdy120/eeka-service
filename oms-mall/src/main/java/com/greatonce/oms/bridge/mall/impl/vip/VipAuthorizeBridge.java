package com.greatonce.oms.bridge.mall.impl.vip;

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
 * 唯品授权接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/3/19
 */
@Component
public class VipAuthorizeBridge extends AbstractAuthorizeBridge {

  static final Logger LOGGER = LoggerFactory.getLogger(VipAuthorizeBridge.class);

  @Override
  public String authorizeUrl(AuthorizeRequest request) {
    MallApp mallApp = request.getStore().getMallApp();
    return StringUtil.format(
        "https://auth.vip.com/oauth2/authorize?client_id={0}&response_type=code&redirect_uri={1}",
        mallApp.getAppKey(), getCallbackUrl(request));
  }

  @Override
  public AuthorizeResponse accessToken(AuthorizeRequest request) {
    MallApp mallApp = request.getStore().getMallApp();
    String url = "https://auth.vip.com/oauth2/token";
    Map<String, String> map = new HashMap<>(5);
    map.put("grant_type", "authorization_code");
    map.put("code", request.getCode());
    map.put("client_id", mallApp.getAppKey());
    map.put("client_secret", mallApp.getAppSecret());
    map.put("redirect_uri", getCallbackUrl(request));
    map.put("request_client_ip", "0.0.0.0");
    try {
      String result = WebUtil.doPost(url, map);
      LOGGER.info("唯品授权：code：{}，result：{}", request.getCode(), result);
      JSONObject jsonObject = JsonUtil.toJSONObject(result);
      if (jsonObject.containsKey("code") && !"0".equals(jsonObject.getString("code"))) {
        throw new MallException("唯品会授权失败！" + jsonObject.getString("msg"));
      }
      AuthorizeResponse response = new AuthorizeResponse(request);
      response.setAccessToken(jsonObject.getString("access_token"));
      response.setAccessExpire(LocalDateTime.now().plusSeconds(jsonObject.getLong("expires_in")));
      return response;
    } catch (IOException e) {
      LOGGER.error("唯品会授权错误!", e);
      throw new MallException("唯品会授权失败！" + e.getMessage());
    }
  }

  @Override
  public AuthorizeResponse refreshToken(AuthorizeRequest request) {
    throw new MallException("唯品会不支持刷新授权");
  }

  @Override
  public MallType[] supports() {
    return new MallType[]{MallType.VIP};
  }
}
