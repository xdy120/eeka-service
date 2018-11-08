package com.greatonce.oms.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.util.BizContext;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 高级查询书签Controller.
 */
@RestController
@CrossOrigin
@RequestMapping("/advance/query")
public class AdvanceQueryController {

  private static final String CACHE_NAME = "ADVANCE_QUERY_CONDITION";
  private static final String CACHE_NAME_NEW = "ADVANCE_QUERY";
  @Autowired
  private RedisTemplate redisTemplate;

  @DeleteMapping("/condition/{component}/{configName}")
  public void deleteConfig(@PathVariable("component") String component,
      @PathVariable("configName") String configName) {
    JSONObject configByUserId = getByComponent(component);
    configByUserId.remove(configName);
    redisTemplate.opsForHash()
        .put(CACHE_NAME_NEW + "::USER_ID_" + BizContext.getUserId(), component, configByUserId);
  }

  @PostMapping("/condition/{component}/{configName}")
  public void saveConfig(@PathVariable("component") String component,
      @PathVariable("configName") String configName, @RequestBody JSONObject config) {
    JSONObject configByUserId = getByComponent(component);
    configByUserId.put(configName, config);
    redisTemplate.opsForHash()
        .put(CACHE_NAME_NEW + "::USER_ID_" + BizContext.getUserId(), component, configByUserId);
  }

  @GetMapping("/condition/{component}/{configName}")
  public JSONObject getConfigByName(@PathVariable("component") String component,
      @PathVariable("configName") String configName) {
    JSONObject configByUserId = getByComponent(component);
    if (configByUserId.containsKey(configName)) {
      return configByUserId.getJSONObject(configName);
    }
    return new JSONObject();
  }

  @GetMapping("/condition/{component}")
  public JSONObject getConfigList(@PathVariable("component") String component) {
    JSONObject configByUserId = getByComponent(component);
    JSONObject data = new JSONObject();
    if (configByUserId.size() > 0) {
      data.put("configList", new ArrayList<>(configByUserId.keySet()));
    } else {
      data.put("configList", new ArrayList<>());
    }
    return data;
  }

  /**
   * ADVANCE_QUERY::USER_ID_1234 -> Map<模块名, Map<配置名, Map(mustNotVisible,shouldVisible,list)>>
   */
  private JSONObject getByComponent(String component) {
    HashOperations ops = redisTemplate.opsForHash();
    if (Assert
        .isTrue(ops.hasKey(CACHE_NAME_NEW + "::USER_ID_" + BizContext.getUserId(), component))) {
      return (JSONObject) ops
          .get(CACHE_NAME_NEW + "::USER_ID_" + BizContext.getUserId(), component);
    }
    return new JSONObject();
  }

  /**
   * --------------------------------重构后可以删除以下的代码----------------------------------
   * redis存储结构 JSONObject<UserId, JSONObject<ConfigName, JSONArray>>
   */
  @PostMapping("/condition/rebuild")
  public void rebuild() {
    List<String> components = Arrays.asList("SalesOrder", "SalesOrderBatch");
    for (String component : components) {
      JSONObject toRemove = getConfigByComponent(component);
      for (String userId : toRemove.keySet()) {
        JSONObject toSave = new JSONObject();
        JSONObject personalConfig = toRemove.getJSONObject(userId);
        for (String configName : personalConfig.keySet()) {
          JSONObject config = new JSONObject();
          JSONArray selectedFields = personalConfig.getJSONArray(configName);
          for (Object selectedField : selectedFields) {
            JSONObject o = (JSONObject) selectedField;
            o.put("boolType", "MUST");
          }
          config.put("config", selectedFields);
          config.put("mustNotVisible", false);
          config.put("shouldVisible", false);
          toSave.put(configName, config);
        }
        redisTemplate.opsForHash().put(CACHE_NAME_NEW + "::USER_ID_" + userId, component, toSave);
      }
    }
  }

  private JSONObject getConfigByComponent(String component) {
    HashOperations ops = redisTemplate.opsForHash();
    if (Assert.isTrue(ops.hasKey(CACHE_NAME, component))) {
      return (JSONObject) ops.get(CACHE_NAME, component);
    }
    return new JSONObject();
  }
}
