package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipScheduleDao;
import com.greatonce.oms.domain.vip.VipSchedule;
import com.greatonce.oms.query.vip.VipScheduleQuery;
import org.springframework.stereotype.Repository;

/**
 * VipSchedule <br/>
 * 唯品档期
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipScheduleDaoImpl extends AbstractOmsDao<VipSchedule, VipScheduleQuery> implements
    VipScheduleDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipScheduleMapper";
  }

}