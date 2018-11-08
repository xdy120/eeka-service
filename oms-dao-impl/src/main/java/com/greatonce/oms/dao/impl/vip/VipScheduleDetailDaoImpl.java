package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipScheduleDetailDao;
import com.greatonce.oms.domain.vip.VipScheduleDetail;
import com.greatonce.oms.query.vip.VipScheduleDetailQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * VipScheduleDetail <br/>
 * 唯品档期明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipScheduleDetailDaoImpl extends
    AbstractOmsDao<VipScheduleDetail, VipScheduleDetailQuery> implements VipScheduleDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipScheduleDetailMapper";
  }

  @Override
  public List<VipScheduleDetail> listAvailable(Long vipScheduleId) {
    return getSqlSessionDecorator().selectList(getStatement("listAvailable"), vipScheduleId);
  }

  @Override
  public List<VipScheduleDetail> listSaleable(Long vipScheduleId) {
    return getSqlSessionDecorator().selectList(getStatement("listSaleable"), vipScheduleId);
  }
}