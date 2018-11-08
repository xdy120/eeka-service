package com.greatonce.oms.bridge.wms;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.SecurityUtil;
import com.greatonce.oms.bridge.wms.request.WmsRequest;
import com.greatonce.oms.domain.base.Warehouse;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * WMS工具类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/30
 */
public abstract class WmsUtil {

  /**
   * 如果value不为空则使用value，否则使用空字符串.
   */
  public static String valueOrEmpty(String value) {
    return Assert.isEmpty(value) ? "" : value;
  }

  /**
   * 获取WMS仓库编码，如果外部编码不为空取外部编码，否则去外部编码.
   */
  public static String getWmsWarehouseCode(Warehouse warehouse) {
    return Assert.isEmpty(warehouse.getOuterCode()) ? warehouse.getWarehouseCode()
        : warehouse.getOuterCode();
  }

  /**
   * 获取货主编码.
   */
  public static String getWmsOwnerCode(Warehouse warehouse) {
    return Assert.isEmpty(warehouse.getOwnerCode()) ? warehouse.getWmsApp().getCustomerId()
        : warehouse.getOwnerCode();
  }


  /**
   * 创建签名.
   *
   * @param map 参数
   * @param prefix 前缀
   * @param suffixs 后缀
   */
  public static String createSign(Map<String, String> map, String prefix, String... suffixs) {
    Map<String, String> sortedParams = new TreeMap<>(map);
    StringBuilder query = new StringBuilder(prefix);
    for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
      String val = sortedParams.get(entry.getKey()) == null ? ""
          : String.valueOf(sortedParams.get(entry.getKey()));
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
   * 设置扩展字段.
   */
  @SuppressWarnings("unchecked")
  public static void putExtendProp(WmsRequest request, String key, String value) {
    if (!Assert.isEmpty(value)) {
      Map map = request.getExtendProps();
      if (map == null) {
        map = new HashMap(1);
        request.setExtendProps(map);
      }
      map.put(key, value);
    }
  }
}
