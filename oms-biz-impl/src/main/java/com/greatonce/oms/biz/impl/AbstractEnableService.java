package com.greatonce.oms.biz.impl;

import com.greatonce.core.database.Query;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.EnableDO;
import com.greatonce.oms.util.logging.BizLogger;

/**
 * 启用状态抽象类.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 6/15/2018
 */
public abstract class AbstractEnableService<T extends EnableDO, Q extends Query> extends
    AbstractService<T, Q> implements EnableBizService<T> {

  @Override
  public int enable(T entity) {
    return setEnable(entity, true);
  }

  @Override
  public int disable(T entity) {
    return setEnable(entity, false);
  }

  protected int setEnable(T t, boolean enable) {
    t.setEnable(enable);
    int count = update(t);
    getBizLogger().log(t.getPrimaryKey(), enable ? BizLogger.ENABLE : BizLogger.DISABLE);
    return count;
  }
}
