package com.greatonce.oms.dao.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.query.admin.RegionQuery;
import java.util.List;

/**
 * Region <br/>
 * 区域表
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface RegionDao extends QueryDao<Region,RegionQuery>{
  List<Region> listSimple(RegionQuery filter);

  /**
   * 查找区域.
   * <p/>
   * 传入的region必须有parentId，name，level
   */
  Region getTranslateRegionInfo(Region eg);

  int updateChildrenQuantity(Long id, int quantity);
}