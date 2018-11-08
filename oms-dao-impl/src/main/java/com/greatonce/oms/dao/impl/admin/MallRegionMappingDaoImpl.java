package com.greatonce.oms.dao.impl.admin;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.admin.MallRegionMappingDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.MallRegionMapping;
import com.greatonce.oms.query.admin.MallRegionMappingQuery;
import org.springframework.stereotype.Repository;

/**
 * MallRegionMapping <br/>
 * 商城区域映射
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class MallRegionMappingDaoImpl extends
    AbstractAdminDao<MallRegionMapping, MallRegionMappingQuery> implements MallRegionMappingDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.admin.MallRegionMappingMapper";
  }

  @Override
  public PageList<MallRegionMapping> listPage(MallRegionMappingQuery mallRegionMappingQuery,
      int page, int pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("customListPage"), mallRegionMappingQuery, page, pageSize);
  }
}