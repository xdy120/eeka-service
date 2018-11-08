package com.greatonce.oms.web.controller.marketing;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.marketing.ActivityService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.query.marketing.ActivityQuery;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/marketing/activity")
@CrossOrigin
public class ActivityController implements PageListController<Activity, ActivityQuery> {

  @Autowired
  ActivityService activityService;

  @Override
  public BizService<Activity, ActivityQuery> getBizService() {
    return activityService;
  }


  @PutMapping(path = "{id}/begin")
  public void begin(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Activity activity = activityService.getByKey(id);
    activityService.begin(activity, bo);
  }

  @PutMapping(path = "{id}/end")
  public void end(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Activity activity = activityService.getByKey(id);
    activityService.end(activity, bo);
  }

  @PutMapping(path = "{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Activity activity = activityService.getByKey(id);
    activityService.audit(activity, bo);
  }

  @PutMapping(path = "{id}/occupancy")
  public void occupancy(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Activity activity = activityService.getByKey(id);
    activityService.occupancy(activity, bo);
  }

  @PutMapping(path = "/{id}/invalid")
  public void close(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    Activity activity = activityService.getByKey(id);
    activityService.invalid(activity, bo);
  }
}
