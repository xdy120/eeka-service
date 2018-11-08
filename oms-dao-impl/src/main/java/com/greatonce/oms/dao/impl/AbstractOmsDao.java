package com.greatonce.oms.dao.impl;

import com.greatonce.core.database.AbstractQueryDao;
import com.greatonce.core.database.Query;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import javax.annotation.Resource;

/**
 * OMS数据库抽象类.
 *
 * @param <T> 实体类型
 * @author buer
 * @version 2017/5/8
 */
public abstract class AbstractOmsDao<T, Q extends Query> extends AbstractQueryDao<T, Q> {

  @Resource
  private SqlSessionDecorator omsSqlSessionDecorator;

  @Override
  protected SqlSessionDecorator getSqlSessionDecorator() {
    return omsSqlSessionDecorator;
  }
}
