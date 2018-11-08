package com.greatonce.oms.domain;

import com.alibaba.fastjson.annotation.JSONField;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 实体基类.
 *
 * @author Coby
 * @version 2017.10.19
 */
public abstract class BaseDO implements Serializable, Cloneable {

  @JSONField(serialize = false)
  public abstract void setPrimaryKey(Long pk);

  @JSONField(serialize = false)
  public abstract Long getPrimaryKey();

  /**
   * 设置创建时间.
   *
   * @param createdTime 创建时间
   */
  public abstract void setCreatedTime(LocalDateTime createdTime);

  /**
   * 获取创建时间.
   *
   * @return 创建时间
   */
  public abstract LocalDateTime getCreatedTime();

  /**
   * 设置修改时间.
   *
   * @param modifiedTime 修改时间
   */
  public abstract void setModifiedTime(LocalDateTime modifiedTime);

  /**
   * 获取修改时间.
   *
   * @return 修改时间
   */
  public abstract LocalDateTime getModifiedTime();

  @Override
  public Object clone() {
    try {
      return super.clone();
    } catch (CloneNotSupportedException e) {
      return null;
    }
  }
}
