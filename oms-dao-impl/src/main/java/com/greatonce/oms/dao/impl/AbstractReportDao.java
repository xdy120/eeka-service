package com.greatonce.oms.dao.impl;

import com.greatonce.core.database.AbstractCommonDao;
import com.greatonce.core.database.mybatis.SqlSessionDecorator;
import javax.annotation.Resource;

/**
 * 报表数据源.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/22
 */
public abstract class AbstractReportDao extends AbstractCommonDao {

  @Resource
  private SqlSessionDecorator reportSqlSessionDecorator;

  @Override
  protected SqlSessionDecorator getSqlSessionDecorator() {
    return reportSqlSessionDecorator;
  }
}
