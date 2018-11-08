package com.greatonce.oms.bridge.mall;

import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.domain.base.Store;
import java.util.List;
import java.util.Map;

/**
 * 加解密接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/28
 */
public interface SecurityBridge extends MallBridge {

  /**
   * 解密.
   *
   * @param store 店铺
   * @param value 字段值
   * @param dataType 数据类型
   */
  default String decrypt(Store store, String value, DataType dataType) {
    return value;
  }


  /**
   * 批量解密.
   *
   * @param store 店铺
   * @param values 字段值
   * @param dataType 数据类型
   */
  default Map<String, String> decrypt(Store store, List<String> values, DataType dataType) {
    return CollectionUtil.listToMap(values, x -> x);
  }

  /**
   * 加密.
   *
   * @param store 店铺
   * @param value 字段值
   * @param dataType 数据类型
   */
  default String encrypt(Store store, String value, DataType dataType) {
    return value;
  }

  /**
   * 批量加密.
   */
  default Map<String, String> encrypt(Store store, List<String> values, DataType dataType) {
    return CollectionUtil.listToMap(values, x -> x);
  }


  /**
   * 加密字段类型.
   */
  enum DataType {
    MOBILE,
    TELEPHONE,
    NICK,
    NAME,
    EMAIL,
  }
}
