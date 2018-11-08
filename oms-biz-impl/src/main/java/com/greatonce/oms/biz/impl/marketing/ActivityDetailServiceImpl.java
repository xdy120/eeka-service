package com.greatonce.oms.biz.impl.marketing;

import com.greatonce.core.database.Query;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.impl.AbstractDetailService;
import com.greatonce.oms.biz.marketing.ActivityDetailService;
import com.greatonce.oms.biz.marketing.ActivityService;
import com.greatonce.oms.dao.marketing.ActivityDetailDao;
import com.greatonce.oms.domain.enums.marketing.ActivityDetailStatus;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.domain.marketing.ActivityDetail;
import com.greatonce.oms.query.marketing.ActivityDetailQuery;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ActivityDetail <br/>
 * 活动报名明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ActivityDetailServiceImpl extends
    AbstractDetailService<Activity, ActivityDetail, ActivityDetailQuery> implements
    ActivityDetailService {

  @Autowired
  private ActivityDetailDao dao;
  @Resource
  private IdGenerator activityDetailIdGenerator;
  @Autowired
  private ActivityService activityService;

  @Override
  protected QueryDao<ActivityDetail, ActivityDetailQuery> getDAO() {
    return this.dao;
  }

  @Override
  protected List<ActivityDetail> getDetails(Activity activity) {
    return activity.getDetails();
  }

  @Override
  protected BizService<Activity, ? extends Query> getMainService() {
    return activityService;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return activityDetailIdGenerator;
  }

  @Override
  protected void initDefaultValue(ActivityDetail activityDetail) {
    super.initDefaultValue(activityDetail);
    activityDetail.setLockQuantity(0);
    activityDetail.setStatus(ActivityDetailStatus.NOT_STARTED);
  }

  @Override
  public void begin(Activity activity, ActivityDetail activityDetail) {
    activityDetail.setStatus(ActivityDetailStatus.STARTED);
    update(activityDetail);
  }

  @Override
  public void end(Activity activity, ActivityDetail activityDetail) {
    activityDetail.setStatus(ActivityDetailStatus.FINISHED);
    update(activityDetail);
  }

  @Override
  public List<ActivityDetail> listAvailable(Activity activity) {
    return dao.listAvailable(activity);
  }

  @Override
  public List<ActivityDetail> listByActivityId(Long activityId) {
    ActivityDetail eg = new ActivityDetail();
    eg.setActivityId(activityId);
    return listExample(eg);
  }

  @Override
  public void updateLockQuantity(ActivityDetail detail) {
    dao.updateLockQuantity(detail);
  }
}