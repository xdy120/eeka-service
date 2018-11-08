package com.greatonce.oms.dao.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.vip.VipScheduleDetail;
import com.greatonce.oms.query.vip.VipScheduleDetailQuery;
import java.util.List;

/**
* VipScheduleDetail <br/>
* 唯品档期明细
*
* @author code-generator
* @author Shenzhen Greatonce Co Ltd
* @version 3.0
*/

public interface VipScheduleDetailDao extends QueryDao<VipScheduleDetail,VipScheduleDetailQuery> {

  List<VipScheduleDetail> listAvailable(Long vipScheduleId);

  List<VipScheduleDetail> listSaleable(Long vipScheduleId);
}
