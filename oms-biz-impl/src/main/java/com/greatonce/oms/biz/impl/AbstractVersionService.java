package com.greatonce.oms.biz.impl;

import com.greatonce.core.database.Query;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.VersionDO;

/**
 * 带有版本管理的Service.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 6/15/2018
 */
public abstract class AbstractVersionService<T extends VersionDO, Q extends Query> extends
    AbstractService<T, Q> {

  @Override
  protected void initDefaultValue(T entity) {
    super.initDefaultValue(entity);
    entity.setVersion(1);
  }

  @Override
  protected int update(T entity) {
    int count = super.update(entity);
    if (count == 0) {
      throw SysExceptions.VERSION_CHANGED;
    }
    return count;
  }
}
