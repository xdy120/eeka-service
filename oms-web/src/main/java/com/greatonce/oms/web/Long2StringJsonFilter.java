package com.greatonce.oms.web;

import com.alibaba.fastjson.serializer.ValueFilter;

public class Long2StringJsonFilter implements ValueFilter {

  @Override
  public Object process(Object object, String name, Object value) {
    if (value instanceof Long) {
      return String.valueOf(value);
    }
    return value;
  }
}
