package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipCancelOrderQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderQueryResponse.VipOrder;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.vip.VipSalesOrderDetail;
import com.greatonce.oms.query.vip.VipSalesOrderDetailQuery;
import java.util.List;

/**
 * VipSalesOrderDetail <br/> 唯品销售单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipSalesOrderDetailService extends
    BatchBizService<VipSalesOrderDetail, VipSalesOrderDetailQuery> {

  /**
   * 唯品拣货单删除销售明细.
   */
  void deleteByDispatch(Store store, List<String> outCodes);

  /**
   * 买家取消.
   */
  void cancelByBuyer(Store store, List<VipCancelOrderQueryResponse.VipOrder> orders);

  /**
   * 自动创建唯品销售单.
   */
  void autoCreate(List<VipSalesOrderDetail> detailList);

  /**
   * 同店铺相同交易号订单过滤.
   */
  void filterExistingOrder(Long storeId, List<VipOrder> orders);
}