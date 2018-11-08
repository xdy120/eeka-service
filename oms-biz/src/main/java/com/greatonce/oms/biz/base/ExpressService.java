package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.query.base.ExpressQuery;

/**
 * Express <br/>
 * ???
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-10-30
 */
public interface ExpressService extends BizService<Express, ExpressQuery>,
    EnableBizService<Express> {

  /**
   * 根据WMS快递编码获取系统快递.
   *
   * @param wmsAppId wms应用ID
   * @param wmsExpressCode wms快递编码
   */
  Express getByWmsExpressCode(Long wmsAppId, String wmsExpressCode);

  /**
   * 根据名称获取快递.
   */
  Express getByName(String name);

  /**
   * 获取仓库的快递编码.
   *
   * @param wmsAppId wms应用ID
   * @param suggestExpressId 快递ID
   */
  String getWmsExpressCode(Long wmsAppId, Long suggestExpressId);

  /**
   * 获取快递.
   */
  Express getEffectiveByCode(String code);
}