package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.stock.StockLoanOutDetail;
import com.greatonce.oms.domain.vip.VipSchedule;
import com.greatonce.oms.domain.vip.VipScheduleDetail;
import com.greatonce.oms.query.vip.VipScheduleDetailQuery;
import java.util.Collection;
import java.util.List;

/**
 * VipScheduleDetail <br/>
 * 唯品档期明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipScheduleDetailService extends
    DetailService<VipSchedule, VipScheduleDetail, VipScheduleDetailQuery> {

  List<VipScheduleDetail> listInSales(String storeId, Collection<String> skuCodes);

  /**
   * 查询可用数.
   */
  List<VipScheduleDetail> listAvailable(Long vipScheduleId);

  /**
   * 查询可销数.
   */
  List<VipScheduleDetail> listSaleable(Long vipScheduleId);
}