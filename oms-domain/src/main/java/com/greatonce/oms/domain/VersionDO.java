package com.greatonce.oms.domain;

/**
 * 版本数据.
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-07-02
 */
public abstract class VersionDO extends BaseDO {

  public abstract Integer getVersion();

  public abstract void setVersion(Integer version);
}
