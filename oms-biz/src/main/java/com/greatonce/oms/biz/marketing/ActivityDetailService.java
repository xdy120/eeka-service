package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.domain.marketing.ActivityDetail;
import com.greatonce.oms.query.marketing.ActivityDetailQuery;
import java.util.List;

/**
 * ActivityDetail <br/>
 * 活动报名明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ActivityDetailService extends
    DetailService<Activity, ActivityDetail, ActivityDetailQuery> {

  /**
   * 开始.
   */
  void begin(Activity activity, ActivityDetail activityDetail);

  /**
   * 结束.
   */
  void end(Activity activity, ActivityDetail activityDetail);

  /**
   * 获取活动的可占用明细.
   */
  List<ActivityDetail> listAvailable(Activity activity);

  /**
   * 获取活动的可占用明细.
   */
  List<ActivityDetail> listByActivityId(Long activityId);

  /**
   * 修改锁定数 只有+.
   */
  void updateLockQuantity(ActivityDetail detail);
}