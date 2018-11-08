package com.greatonce.oms.custom.kingdee;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.core.util.WebUtil;
import com.greatonce.oms.custom.kingdee.request.K3CloudRequest;
import com.greatonce.oms.custom.kingdee.response.K3CloudResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.HttpCookie;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 金蝶K3云客户端.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public class K3CloudClient {

  private static Logger LOGGER = LoggerFactory.getLogger(K3CloudClient.class);
  /**
   * 配置项
   */
  private String url;
  private String loginUrl;
  private String acctId;
  private String username;
  private String password;
  private Integer lcid;

  private LocalDateTime lastLoginTime = LocalDateTime.now();
  private String cookie;


  public K3CloudClient(String url, String loginUrl, String acctId, String username,
      String password, Integer lcid) {
    this.url = url;
    this.loginUrl = loginUrl;
    this.acctId = acctId;
    this.username = username;
    this.password = password;
    this.lcid = lcid;
  }

  public <T extends K3CloudResponse> T execute(K3CloudRequest<T> request) {
    if (LocalDateTime.now().minusHours(1).isAfter(lastLoginTime) || Assert.isEmpty(cookie)) {
      String returnCookie = login();
      if (Assert.isEmpty(returnCookie)) {
        return null;
      } else {
        cookie = returnCookie;
      }
    }

    JSONObject content = new JSONObject();
    content.put("format", 1);
    content.put("useragent", "ApiClient");
    content.put("rid", UUID.randomUUID().toString().hashCode());
    content.put("parameters", chinaToUnicode(request.parameters()));
    content.put("timestamp", LocalDateTime.now());
    content.put("v", "1.0");

    Map<String, String> headerMap = new HashMap<>();
    headerMap.put("Cookie", cookie);

    String res = null;
    try {
      res = WebUtil.doPostJson(this.url, content.toString(), "UTF-8",
          60 * 1000, 60 * 1000, headerMap);
      return JsonUtil.toObject(res, request.getResponseClass());
    } catch (IOException e) {
      LOGGER.error("推送数据，金蝶接口异常。参数：{}；返回结果：{}；堆栈信息：{}",
          content.toString(), res, e);
    }
    return null;
  }

  private String login() {
    JSONArray parameters = new JSONArray();
    parameters.add(acctId);
    parameters.add(username);
    parameters.add(password);
    parameters.add(lcid);

    JSONObject content = new JSONObject();
    content.put("format", 1);
    content.put("useragent", "ApiClient");
    content.put("parameters", parameters.toString());
    content.put("timestamp", LocalDateTime.now());
    content.put("v", "1.0");

    List<HttpCookie> cookieContainer = new ArrayList<>();
    String res = null;
    try {
      lastLoginTime = LocalDateTime.now();
      res = WebUtil.doPostJson(loginUrl, content.toString(), cookieContainer);
      JSONObject jsonObject = JsonUtil.toJSONObject(res);
      Integer loginResultType = jsonObject.getInteger("LoginResultType");
      if (!Assert.isNull(loginResultType) && loginResultType.equals(1)) {
        return joinCookie(cookieContainer);
      } else {
        LOGGER.error("登录金蝶错误！返回结果：{}", res);
      }
    } catch (IOException e) {
      LOGGER.error("登录金蝶异常！返回结果：{}；堆栈信息：{}", res, e);
    }
    return null;
  }

  private String joinCookie(List<HttpCookie> cookieContainer) {
    if (!Assert.isEmpty(cookieContainer)) {
      List<String> collect = new ArrayList<>();
      for (HttpCookie httpCookie : cookieContainer) {
          collect.add(httpCookie.getName() + "=" + httpCookie.getValue());
      }
      return StringUtil.join(collect, ";");
    }
    return null;
  }

  /**
   * 把中文转成Unicode码
   */
  public static String chinaToUnicode(String str) {
    String result = "";
    for (int i = 0; i < str.length(); i++) {
      int chr1 = str.charAt(i);
      if (chr1 >= 19968 && chr1 <= 171941) {// 汉字范围 \u4e00-\u9fa5 (中文)
        result += "\\u" + Integer.toHexString(chr1);
      } else {
        result += str.charAt(i);
      }
    }
    return result;
  }

}
