package com.greatonce.oms.biz.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.admin.GlobalExpressMallMapping;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.query.admin.GlobalExpressMallMappingQuery;

/**
 * 全局快递商城映射.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface GlobalExpressMallMappingService extends
    BizService<GlobalExpressMallMapping, GlobalExpressMallMappingQuery> {

  /**
   * 获取商城的快递编码.
   *
   * @param globalExpressId 全局快递id
   * @param mallType 商城类型
   * @return 商城编码
   */
  GlobalExpressMallMapping getMallExpressMapping(Long globalExpressId, MallType mallType);
}