package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.ExpressRegion;
import com.greatonce.oms.query.base.ExpressRegionQuery;
import java.util.List;

/**
 * ExpressRegion <br/>
 * 快递区域
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface ExpressRegionDao extends QueryDao<ExpressRegion, ExpressRegionQuery> {

  List<ExpressRegion> listRegion(ExpressRegionQuery expressRegionQuery);

  ExpressRegion getByRegionIdAndExpressId(Long regionId, Long expressId);
}