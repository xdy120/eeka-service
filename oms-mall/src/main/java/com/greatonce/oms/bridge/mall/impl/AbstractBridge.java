package com.greatonce.oms.bridge.mall.impl;

import org.springframework.beans.factory.annotation.Value;

/**
 * 抽象.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-27
 */
public abstract class AbstractBridge {

  @Value("${oms.testing:false}")
  private boolean testing;

  public boolean isTesting() {
    return testing;
  }
}
