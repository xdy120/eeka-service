package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.ExpressRegion;
import com.greatonce.oms.query.base.ExpressRegionQuery;
import java.util.List;

/**
 * ExpressRegion <br/>
 * 快递区域
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-03
 */
public interface ExpressRegionService extends BizService<ExpressRegion, ExpressRegionQuery> {

  void saveExpressRegions(Long expressId, List<ExpressRegion> regions);

  List<ExpressRegion> listRegion(ExpressRegionQuery filter);

  ExpressRegion getByRegionIdAndExpressId(Long regionId, Long expressId);
}