package com.greatonce.oms.bridge.mall.impl;

import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.domain.base.Store;
import java.util.List;
import java.util.Map;

/**
 * 安全接口抽象类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/23
 */
public abstract class AbstractSecurityBridge extends AbstractBridge implements SecurityBridge {

  @Override
  public String decrypt(Store store, String value, DataType dataType) {
    return value;
  }

  @Override
  public Map<String, String> decrypt(Store store, List<String> values, DataType dataType) {
    return CollectionUtil.listToMap(values, x -> x);
  }

  @Override
  public String encrypt(Store store, String value, DataType dataType) {
    return value;
  }

  @Override
  public Map<String, String> encrypt(Store store, List<String> values, DataType dataType) {
    return CollectionUtil.listToMap(values, x -> x);
  }
}
