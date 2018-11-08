package com.greatonce.oms.domain;

/**
 * 可启用/禁用数据.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 6/13/2018
 */
public abstract class EnableDO extends BaseDO {

  public abstract void setEnable(Boolean enable);

  public abstract Boolean isEnable();
}
