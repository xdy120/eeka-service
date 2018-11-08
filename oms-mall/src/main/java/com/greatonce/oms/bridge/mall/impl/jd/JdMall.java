package com.greatonce.oms.bridge.mall.impl.jd;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.bridge.mall.impl.AbstractMall;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.jd.open.api.sdk.JdException;
import com.jd.open.api.sdk.internal.parser.Parser;
import com.jd.open.api.sdk.internal.parser.ParserFactory;
import com.jd.open.api.sdk.internal.util.CodecUtil;
import com.jd.open.api.sdk.internal.util.StringUtil;
import com.jd.open.api.sdk.request.JdRequest;
import com.jd.open.api.sdk.request.JdUploadRequest;
import com.jd.open.api.sdk.response.AbstractResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * CREATED by Creoa on 2016/12/1.
 *
 * @author 82743
 */
@Component
public class JdMall extends AbstractMall {

  private static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  private static final String SUCCESS_CODE = "0";

  @Value("${oms.jd.proxy.host:jd.greatonce.com}")
  private String proxyHost = "jd.greatonce.com";
  @Value("${oms.jd.proxy.port:30001}")
  private int proxyPort = 30001;

  public static final String CHARSET_UTF8 = "UTF-8";
  private static final String JSON_PARAM_KEY = "360buy_param_json";
  private static final String OTHER_PARAM_KEY = "other";


  @Override
  protected String getDefaultUrl() {
    return "https://api.jd.com/routerjson";
  }

  /**
   * 调用平台.
   */
  public <T extends AbstractResponse> T call(Store store, JdRequest<T> request)
      throws MallException {
    return call(store, request, false);
  }

  /**
   * 调用平台.
   */
  public <T extends AbstractResponse> T call(Store store, JdRequest<T> request, boolean useProxy)
      throws MallException {
    try {
      T t = useProxy ? execute(store, request, proxyHost, proxyPort) : execute(store, request);
      if (!Assert.isEmpty(t.getCode()) && !SUCCESS_CODE.equals(t.getCode())) {
        //重复出库
        if ("10400001".equals(t.getCode())){
          return t;
        }
        throw new MallException(t.getMsg());
      }
      return t;
    } catch (JdException ex) {
      throw new MallException("请求失败！" + ex.getMessage());
    }
  }


  public <T extends AbstractResponse> T execute(Store store, JdRequest<T> jdRequest)
      throws JdException {
    return execute(store, jdRequest, null, 0);
  }

  @SuppressWarnings("unchecked")
  public <T extends AbstractResponse> T execute(Store store, JdRequest<T> request, String proxyHost,
      int proxyPort) throws JdException {
    String json = null;
    String rsp = null;
    try {
      String url = buildUrl(store, request);

      Map<String, String> params = new HashMap<String, String>();
      json = request.getAppJsonParams();
      params.put("360buy_param_json", json);
      if (request.getOtherParams() != null) {
        params.put("other", request.getOtherParams());
      }
      int connectTimeout = 0;
      int readTimeout = 0;
      if ((request instanceof JdUploadRequest)) {
        rsp = JdHttpUtil
            .doPost(url, params, ((JdUploadRequest) request).getFileParams(), connectTimeout,
                readTimeout, proxyHost, proxyPort);
      } else {
        rsp = JdHttpUtil
            .doPost(url, params, connectTimeout, readTimeout, proxyHost, proxyPort);
      }
      MALL_LOGGER.info(store, "api:{}，request：{}，response：{}", request.getApiMethod(),
          json, rsp);
      T resp = parse(store, rsp, request.getResponseClass());
      resp.setUrl(url + "&" + "360buy_param_json" + "=" + json);
      return resp;
    } catch (Exception e) {
      MALL_LOGGER
          .error(store, "api:{}，request：{}，response：{}", request.getApiMethod(), json, rsp);
      throw new JdException(e);
    }
  }

  private <T extends AbstractResponse> String buildUrl(Store store, JdRequest<T> request)
      throws Exception {
    Map<String, String> sysParams = request.getSysParams();

    Map<String, String> pmap = new TreeMap<>();
    pmap.put("360buy_param_json", request.getAppJsonParams());
    sysParams.put("method", request.getApiMethod());
    sysParams.put("access_token", store.getAccessToken());
    sysParams.put("app_key", store.getMallApp().getAppKey());
    pmap.putAll(sysParams);

    String sign = sign(pmap, store.getMallApp().getAppSecret());

    sysParams.put("sign", sign);
    return getUrl(store) + "?"
        + JdHttpUtil.buildQuery(sysParams, "UTF-8");
  }

  private <T extends AbstractResponse> T parse(Store store, String rsp, Class<T> responseClass)
      throws JdException {
    Parser parser;
    if (getUrl(store).endsWith("json")) {
      parser = ParserFactory.getJsonParser();
    } else {
      parser = ParserFactory.getXmlParser();
    }

    return parser.parse(rsp, responseClass);
  }

  private String sign(Map<String, String> pmap, String appSecret) throws Exception {
    StringBuilder sb = new StringBuilder(appSecret);

    for (Entry entry : pmap.entrySet()) {
      String name = (String) entry.getKey();
      String value = (String) entry.getValue();
      if (StringUtil.areNotEmpty(name, value)) {
        sb.append(name).append(value);
      }
    }
    sb.append(appSecret);
    return CodecUtil.md5(sb.toString());
  }
}
