package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.vip.VipSchedule;
import com.greatonce.oms.query.vip.VipScheduleQuery;

/**
 * VipSchedule <br/>
 * 唯品档期
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipScheduleService extends
    BizService<VipSchedule, VipScheduleQuery> {

  void audit(VipSchedule entity, VersionBO bo);

  void upload(VipSchedule entity);

  void end(VipSchedule entity);

}