package com.greatonce.oms.biz.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.TreeBizService;
import com.greatonce.oms.bo.admin.RegionMatchBO;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.query.admin.RegionQuery;
import java.util.List;

/**
 * Region <br/>
 * 区域表
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-03
 */
public interface RegionService extends BizService<Region, RegionQuery>, TreeBizService<Region> {

  Region getByNameAndLevel(Long parentId, String name, Integer level);

  List<Region> listAll();

  List<Region> listSimple();

  /**
   * 国内地址匹配.
   */
  RegionMatchBO matchRegion(String country, String province, String city, String district);
}