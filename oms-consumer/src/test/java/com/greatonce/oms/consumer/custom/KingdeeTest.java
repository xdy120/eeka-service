package com.greatonce.oms.consumer.custom;

import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.WebUtil;
import com.greatonce.oms.consumer.Application;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest.Fentity;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest.SalesStockOut;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest.SubHeadEntity;
import com.greatonce.oms.custom.kingdee.response.K3CloudStockOutSaveResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("dev")
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class KingdeeTest {

  private static String LOGIN_URL = "http://gushanggu.gnway.cc:8090/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc";
  private static String DATA_URL = "http://gushanggu.gnway.cc:8090/K3Cloud/Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc";
  CookieManager cookieManager = new CookieManager();
  @Resource
  private K3CloudClient k3CloudClient;

  @Test
  public void login() throws IOException {
    List<Object> parameters = new ArrayList<>();
    parameters.add("5b56945f7176da");
    parameters.add("administrator");
    parameters.add("888888");
    parameters.add(2052);

    Map<String, Object> content = new HashMap<>();
    content.put("format", 1);
    content.put("useragent", "ApiClient");
    //content.put("rid", Guid.NewGuid().ToString().GetHashCode().ToString());
    content.put("parameters", JsonUtil.toJson(parameters));
    content.put("timestamp", LocalDateTime.now());
    content.put("v", "1.0");

    String res = WebUtil.doPostJson(LOGIN_URL, JsonUtil.toJson(content));
    System.out.println(res);
    JSONObject jsonObject = JsonUtil.toJSONObject(res);
    Integer loginResultType = jsonObject.getInteger("LoginResultType");
    if (!Assert.isNull(loginResultType)) {
      if (loginResultType.equals(1)) {
        System.out.println("success");
      } else if (loginResultType.equals(0)) {
        System.out.println("error");
      } else {
        System.out.println("fail");
      }
    }
  }

  @Test
  public void sendData() throws IOException {
    K3CloudStockOutSaveRequest request = new K3CloudStockOutSaveRequest();
    SalesStockOut salesStockOut = new SalesStockOut();
    salesStockOut.setFStockOrgId(new FNumber("100"));
    salesStockOut.setFBillTypeID(new FNumber("RKD01_SYS"));
    salesStockOut.setFCustomerID(new FNumber("CUST0001"));
    salesStockOut.setSubHeadEntity(new SubHeadEntity());
    List<Fentity> list = new ArrayList<>();
    Fentity fentity = new Fentity();
    fentity.setFMATERIALID(new FNumber("CH4441"));
    fentity.setFStockID(new FNumber("CK001"));
    fentity.setFTaxRate(16.000000);
    fentity.setFRealQty(5);
    fentity.setFTaxPrice(20.0);
    list.add(fentity);
    salesStockOut.setFentity(list);
    request.setSalesStockOut(salesStockOut);

    Map<String, Object> content = new HashMap<>();
    content.put("format", 1);
    content.put("useragent", "ApiClient");
    //content.put("rid", Guid.NewGuid().ToString().GetHashCode().ToString());
    content.put("parameters", request.parameters());
    content.put("timestamp", LocalDateTime.now());
    content.put("v", "1.0");

    Map<String, String> headerMap = new HashMap<>();
//    if (cookieManager.getCookieStore().getCookies().size() > 0) {
//      List<String> collect = new ArrayList<>();
//      for (HttpCookie httpCookie : cookieManager.getCookieStore().getCookies()) {
//        collect.add(httpCookie.getName() + "=" + httpCookie.getValue());
//      }
//      headerMap.put("Cookie", StringUtil.join(collect, ";"));
//    }
    headerMap.put("Cookie", "ASP.NET_SessionId=xfeyafyj1pehg0kdny0vfyxc;kdservice-sessionid=b12fe780-173a-4fee-95c4-69b9694ddfda");

    String res = WebUtil.doPostJson(DATA_URL, JsonUtil.toJson(content), "UTF-8", 60 * 1000, 60 * 1000, headerMap);
    System.out.println(res);
  }

  @Test
  public void buildConn() throws IOException {
    List<Object> parameters = new ArrayList<>();
    parameters.add("5b56945f7176da");
    parameters.add("administrator");
    parameters.add("888888");
    parameters.add(2052);

    Map<String, Object> content = new HashMap<>();
    content.put("format", 1);
    content.put("useragent", "ApiClient");
    //content.put("rid", Guid.NewGuid().ToString().GetHashCode().ToString());
    content.put("parameters", JsonUtil.toJson(parameters));
    content.put("timestamp", LocalDateTime.now());
    content.put("v", "1.0");

    String ctype = "application/json;charset=UTF-8";
    byte[] contentJson = JsonUtil.toJson(content).getBytes("UTF-8");

//    CookieHandler.setDefault(cookieManager);
    URL url = new URL(LOGIN_URL);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
    conn.setRequestMethod("POST");
    conn.setDoInput(true);
    conn.setDoOutput(true);
    conn.setRequestProperty("Host", url.getHost());
    conn.setRequestProperty("User-Agent", "greatonce-base");
    conn.setRequestProperty("Content-Type", ctype);

    OutputStream out = conn.getOutputStream();
    out.write(contentJson);

    Map<String, List<String>> headerFields = conn.getHeaderFields();
    List<String> cookiesHeader = headerFields.get("Set-Cookie");
    if (!Assert.isEmpty(cookiesHeader)) {
      cookiesHeader.forEach(x -> {
        cookieManager.getCookieStore().add(null, HttpCookie.parse(x).get(0));
      });
    }

    for (HttpCookie httpCookie : cookieManager.getCookieStore().getCookies()) {
      System.out.println(
          httpCookie.getName() + " " + httpCookie.getValue() + " " + httpCookie.getMaxAge());
    }

//    CookieStore cookieStore = cookieManager.getCookieStore();
//    for (HttpCookie httpCookie : cookieStore.getCookies()) {
//      System.out.println(httpCookie);
//    }
//    String cookieVal = conn.getHeaderField("Set-Cookie");
//    System.out.println(cookieVal);

//    sendData();
  }

  @Test
  public void testClient() {
    K3CloudStockOutSaveRequest request = new K3CloudStockOutSaveRequest();
    SalesStockOut salesStockOut = new SalesStockOut();
    salesStockOut.setFStockOrgId(new FNumber("100"));
    salesStockOut.setFBillTypeID(new FNumber("RKD01_SYS"));
    salesStockOut.setFCustomerID(new FNumber("CUST0001"));
    salesStockOut.setSubHeadEntity(new SubHeadEntity());
    List<Fentity> list = new ArrayList<>();
    Fentity fentity = new Fentity();
    fentity.setFMATERIALID(new FNumber("CH4441"));
    fentity.setFStockID(new FNumber("CK001"));
    fentity.setFTaxRate(16.000000);
    fentity.setFRealQty(5);
    fentity.setFTaxPrice(20.0);
    list.add(fentity);
    salesStockOut.setFentity(list);
    request.setSalesStockOut(salesStockOut);

    K3CloudStockOutSaveResponse response = k3CloudClient.execute(request);
    System.out.println(request.parameters());
    System.out.println(JsonUtil.toJson(response));
  }
}
