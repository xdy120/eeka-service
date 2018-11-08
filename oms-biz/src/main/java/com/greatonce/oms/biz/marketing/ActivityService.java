package com.greatonce.oms.biz.marketing;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.marketing.BeginActivityDetailBO;
import com.greatonce.oms.bo.marketing.EndActivityDetailBO;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.query.marketing.ActivityQuery;

/**
 * Activity <br/>
 * 活动报名
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ActivityService extends
    com.greatonce.oms.biz.BizService<Activity, ActivityQuery> {

  /**
   * 开始活动
   */
  void begin(Activity activity, VersionBO bo);

  /**
   * 结束活动
   */
  void end(Activity activity, VersionBO bo);

  /**
   * 开始明细
   */
  void beginDetail(Activity activity, BeginActivityDetailBO bo);

  /**
   * 结束明细
   */
  void endDetail(Activity activity, EndActivityDetailBO bo);

  /**
   * 审核
   */
  void audit(Activity activity, VersionBO bo);

  /**
   * 占用
   */
  void occupancy(Activity activity, VersionBO bo);

  /**
   * 作废
   */
  void invalid(Activity activity, VersionBO bo);
}