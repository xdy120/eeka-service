package com.greatonce.oms.web.controller.marketing;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.marketing.ActivityDetailService;
import com.greatonce.oms.biz.marketing.ActivityService;
import com.greatonce.oms.bo.marketing.BeginActivityDetailBO;
import com.greatonce.oms.bo.marketing.EndActivityDetailBO;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.domain.marketing.ActivityDetail;
import com.greatonce.oms.query.marketing.ActivityDetailQuery;
import com.greatonce.oms.web.controller.DetailPageCommonController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/marketing/activity/{id}/detail")
@CrossOrigin
public class ActivityDetailController implements
    DetailPageCommonController<Activity, ActivityDetail, ActivityDetailQuery> {

  @Autowired
  ActivityDetailService activityDetailService;
  @Autowired
  ActivityService activityService;

  @Override
  public DetailService<Activity, ActivityDetail, ActivityDetailQuery> getBizService() {
    return activityDetailService;
  }

  @RequestMapping(method = RequestMethod.PUT, path = "{id}/begin")
  public void begin(@PathVariable("id") Long id, @RequestBody BeginActivityDetailBO bo) {
    Activity activity = activityService.getByKey(id);
    activityService.beginDetail(activity, bo);
  }

  @RequestMapping(method = RequestMethod.PUT, path = "{id}/end")
  public void end(@PathVariable("id") Long id, @RequestBody EndActivityDetailBO bo) {
    Activity activity = activityService.getByKey(id);
    activityService.endDetail(activity, bo);
  }
}
