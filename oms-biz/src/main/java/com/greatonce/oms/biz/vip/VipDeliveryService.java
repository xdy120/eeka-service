package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.vip.VipDispatchOrderBO;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.query.vip.VipDeliveryQuery;
import java.util.List;

/**
 * VipDelivery <br/> 唯品发货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipDeliveryService extends BizService<VipDelivery, VipDeliveryQuery> {

  /**
   * 发货.
   */
  void delivery(VipDelivery vipDelivery, VersionBO bo);

  /**
   * 审核.
   */
  void audit(VipDelivery vipDelivery, VersionBO bo);

  /**
   * 作废.
   */
  void invalid(VipDelivery vipDelivery, VersionBO bo);

  /**
   * 汇总发货单下所有配货单通知数量 出库数量.
   */
  List<VipDispatchOrderBO> queryDispatch(VipDeliveryQuery filter);

  /**
   * 导出唯品出仓单.
   */
  void exportDelivery(String fileName, VipDeliveryQuery vipDeliveryQuery);

  /**
   * 取消唯品出仓单
   */
  void cancel(VipDelivery vipDelivery, VersionBO bo);

  /**
   * 手工发货
   */
  void manualDelivery(VipDelivery vipDelivery, VersionBO bo);
}