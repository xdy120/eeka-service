package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.vip.VipDispatchScheduleService;
import com.greatonce.oms.dao.vip.VipDispatchScheduleDao;
import com.greatonce.oms.domain.vip.VipDispatchSchedule;
import com.greatonce.oms.query.vip.VipDispatchScheduleQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 唯品拣货档期.
 * VipDispatchSchedule <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-19
 */

@Service
public class VipDispatchScheduleServiceImpl extends AbstractService
    <VipDispatchSchedule, VipDispatchScheduleQuery> implements VipDispatchScheduleService {

  @Autowired
  VipDispatchScheduleDao dao;

  @Override
  protected QueryDao<VipDispatchSchedule, VipDispatchScheduleQuery> getDAO() {
    return this.dao;
  }

}