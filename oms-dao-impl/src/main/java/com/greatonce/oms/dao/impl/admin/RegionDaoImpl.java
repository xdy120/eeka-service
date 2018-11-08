package com.greatonce.oms.dao.impl.admin;

import com.greatonce.oms.dao.admin.RegionDao;
import com.greatonce.oms.dao.impl.AbstractAdminDao;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.query.admin.RegionQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 * Region <br/> 区域表
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class RegionDaoImpl extends AbstractAdminDao<Region, RegionQuery> implements RegionDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.admin.RegionMapper";
  }

  @Override
  public List<Region> listSimple(RegionQuery regionQuery) {
    return getSqlSessionDecorator().selectList(getStatement("listColumns"), regionQuery);
  }

  /**
   * 查找区域.
   * <p/>
   * 传入的region必须有parentId，name，level
   */
  @Override
  public Region getTranslateRegionInfo(Region eg) {
    return getSqlSessionDecorator().selectOne(getStatement("getTranslateRegionInfo"), eg);
  }

  @Override
  public int updateChildrenQuantity(Long id, int quantity) {
    Map<String, Object> map = new HashMap<>(2);
    map.put("regionId", id);
    map.put("quantity", quantity);
    return getSqlSessionDecorator().update(getStatement("updateChildrenQuantity"), map);
  }
}