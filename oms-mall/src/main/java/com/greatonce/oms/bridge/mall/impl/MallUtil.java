package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.SecurityUtil;
import com.greatonce.oms.bridge.mall.MallException;
import com.greatonce.oms.domain.base.Store;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 商城工具类
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/6/2
 */
public abstract class MallUtil {

  private static String MUNICIPALITY_REGEX = "(重庆)+市?|(上海)+市?|(北京)+市?|(天津)+市?|";

  public static boolean calcHasNext(int pageSize, int page, int total) {
    return page < calcTotalPage(pageSize, total);
  }

  /**
   * 计算分页
   */
  public static int calcTotalPage(int pageSize, int total) {
    return (total + pageSize - 1) / pageSize;
  }

  /**
   * 计算分页
   */
  public static int calcTotalPage(long pageSize, long total) {
    return (int) ((total + pageSize - 1) / pageSize);
  }

  /**
   * 获取时间戳 unix格式
   */
  public static long getUnixTimestamp() {
    return System.currentTimeMillis() / 1000;
  }

  /**
   * 获取时间戳 yyyy-MM-dd HH:mm:ss格式
   */
  public static String getTimestamp() {
    return DateTimeUtil.nowTime();
  }

  /**
   * 获取时间戳 yyyyMMddHHmmss格式
   */
  public static String getShortTimestamp() {
    return DateTimeUtil.format(new Date(), DateTimeUtil.NO_SPLIT_FORMATTER);
  }

  /**
   * 创建签名
   *
   * @param store 店铺
   * @param map 系统参数
   */
  public static String createSignBySecret(Store store, Map<String, String> map) {
    return createSign(map, store.getMallApp().getAppSecret(), store.getMallApp().getAppSecret());
  }

  /**
   * 创建签名
   *
   * @param store 店铺
   * @param map 系统参数
   */
  public static String createSignByToken(Store store, Map<String, String> map) {
    return createSign(map, store.getAccessToken(), store.getAccessToken());
  }

  /**
   * 创建签名
   *
   * @param map 参数
   * @param prefix 前缀
   * @param suffixs 后缀
   */
  public static String createSign(Map<String, String> map, String prefix, String... suffixs) {
    Map<String, String> sortedParams = new TreeMap<>(map);
    StringBuilder query = new StringBuilder(prefix);
    for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
      String val = entry.getKey() == null ? "" : String.valueOf(entry.getKey());
      query.append(entry.getKey()).append(val);
    }
    if (suffixs != null) {
      for (String suffix : suffixs) {
        query.append(suffix);
      }
    }
    return SecurityUtil.md5Hex(query.toString()).toUpperCase();
  }

  /**
   * 创建签名
   *
   * @param map 参数
   * @param prefix 前缀
   * @param suffixs 后缀
   */
  public static String xiaohongshuCreateSign(Map<String, String> map, String prefix,
      String... suffixs) {
    Map<String, String> sortedParams = new TreeMap<>(map);
    StringBuilder query = new StringBuilder(prefix);
    query.append("?");
    for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
      String val = entry.getKey() == null ? "" : String.valueOf(entry.getKey());
      query.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
    }
    query.deleteCharAt(query.length() - 1);
    if (suffixs != null) {
      for (String suffix : suffixs) {
        query.append(suffix);
      }
    }
    return SecurityUtil.md5Hex(query.toString()).toUpperCase();
  }

  /**
   * 创建签名.
   * alibaba与aliexpress使用
   */
  public static String createSignByPath(Store store, Map<String, String> map, String urlPath) {
    List<String> paramValueList = new ArrayList<>();
    if (map != null) {
      for (Map.Entry<String, String> entry : map.entrySet()) {
        paramValueList.add(entry.getKey() + entry.getValue());
      }
    }
    final String[] datas = new String[1 + paramValueList.size()];
    datas[0] = urlPath;
    Collections.sort(paramValueList);
    for (int i = 0; i < paramValueList.size(); i++) {
      datas[i + 1] = paramValueList.get(i);
    }
    try {
      return SecurityUtil.hmacSha1Hex(store.getMallApp().getAppSecret(), datas);
    } catch (Exception e) {
      throw new MallException(store.getStoreName() + "签名失败！");
    }
  }

  /**
   * 匹配是否直辖市.
   */
  public static boolean isMunicipality(String province) {
    return province.matches(MUNICIPALITY_REGEX);
  }
}
