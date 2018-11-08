package com.greatonce.oms.custom.kingdee.request;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.custom.kingdee.response.K3CloudResponse;

/**
 * K3请求.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-11
 */
public abstract class K3CloudRequest<T extends K3CloudResponse> {

  @JSONField(name = "Model")
  private String jsonContent;

  @JSONField(name = "formid")
  public abstract String formid();

  public abstract Class<T> getResponseClass();

  public abstract Object content();

  /**
   * 组装json请求
   */
  public String parameters() {
    JSONObject modelMap = new JSONObject();
    modelMap.put("Model", content());
    JSONArray list = new JSONArray();
    list.add(formid());
    list.add(JsonUtil.toJsonInPascalName(modelMap));
    return JsonUtil.toJson(list);
  }

  /**
   * 获取json内容.
   */
  public String getJsonContent() {
    if (jsonContent == null) {
      jsonContent = JsonUtil.toJson(content());
    }
    return jsonContent;
  }
}