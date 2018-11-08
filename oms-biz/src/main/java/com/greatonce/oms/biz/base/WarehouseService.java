package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.query.base.WarehouseQuery;
import java.util.Map;

/**
 * Warehouse <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-30
 */
public interface WarehouseService extends BizService<Warehouse, WarehouseQuery>,
    EnableBizService<Warehouse> {

  /**
   * 获取启用的仓库.
   */
  Warehouse getEffectiveByCode(String warehouseCode);

  /**
   * 获取仓库.
   *
   * @return key为仓库对照编码
   */
  Map<String, Warehouse> listEffective();
}