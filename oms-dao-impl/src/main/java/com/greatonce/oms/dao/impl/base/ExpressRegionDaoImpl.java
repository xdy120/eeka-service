package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.ExpressRegionDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.ExpressRegion;
import com.greatonce.oms.query.base.ExpressRegionQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ExpressRegion <br/>
 * 快递区域
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class ExpressRegionDaoImpl extends
    AbstractOmsDao<ExpressRegion, ExpressRegionQuery> implements ExpressRegionDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.base.ExpressRegionMapper";
  }

  public List<ExpressRegion> listRegion(ExpressRegionQuery expressRegionQuery) {
    return getSqlSessionDecorator().selectList(getStatement("listColumns"), expressRegionQuery);
  }

  @Override
  public ExpressRegion getByRegionIdAndExpressId(Long regionId, Long expressId) {
    ExpressRegion eg = new ExpressRegion();
    eg.setRegionId(regionId);
    eg.setExpressId(expressId);
    return getSqlSessionDecorator().selectOne("getByRegionIdAndExpressId", eg);
  }
}