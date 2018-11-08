package com.greatonce.oms.job.executor;

import com.greatonce.core.util.Assert;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/17
 */
@RestController
@RequestMapping("/quartz")
@CrossOrigin
public class QuartzController {

  @Resource
  private Scheduler globalScheduler;

  @GetMapping("/jobs")
  public List<JobInfo> list() throws SchedulerException {
    List<JobInfo> jobInfos = new ArrayList<>();
    List<String> triggerGroupNames = globalScheduler.getTriggerGroupNames();
    for (String triggerGroupName : triggerGroupNames) {
      Set<TriggerKey> triggerKeySet = globalScheduler
          .getTriggerKeys(GroupMatcher.triggerGroupEquals(triggerGroupName));
      for (TriggerKey triggerKey : triggerKeySet) {
        Trigger trigger = globalScheduler.getTrigger(triggerKey);
        JobKey jobKey = trigger.getJobKey();
        JobInfo jobInfo = getJobInfo(jobKey, triggerKey);
        jobInfos.add(jobInfo);
      }
    }
    return jobInfos;
  }

  @PostMapping
  @SuppressWarnings("unchecked")
  public JobInfo addJob(@RequestBody JobInfo jobInfo)
      throws SchedulerException, ClassNotFoundException {
    addJobInfo(jobInfo);
    return jobInfo;
  }

  @PostMapping("/{jobGroup}/{jobName}/pause")
  public void pauseJob(@PathVariable("jobGroup") String jobGroup,
      @PathVariable("jobName") String jobName) throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
    if (globalScheduler.checkExists(jobKey)) {
      globalScheduler.pauseJob(jobKey);
    }
  }

  @PostMapping("/{jobGroup}/{jobName}/continue")
  public void continueJob(@PathVariable("jobGroup") String jobGroup,
      @PathVariable("jobName") String jobName) throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
    if (globalScheduler.checkExists(jobKey)) {
      globalScheduler.resumeJob(jobKey);
    }
  }

  @PostMapping("/{jobGroup}/{jobName}/delete")
  public void deleteJob(@PathVariable("jobGroup") String jobGroup,
      @PathVariable("jobName") String jobName) throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
    deleteJobInfo(jobKey);
  }

  @GetMapping("/{jobGroup}/{jobName}")
  public JobInfo getJobInfo(@PathVariable("jobGroup") String jobGroup,
      @PathVariable("jobName") String jobName) throws SchedulerException {
    JobKey jobKey = JobKey.jobKey(jobName, jobGroup);
    if (!globalScheduler.checkExists(jobKey)) {
      return null;
    }

    List<? extends Trigger> triggers = globalScheduler.getTriggersOfJob(jobKey);
    Assert.notEmpty(triggers, "查询不到相关的触发器");
    Trigger trigger = triggers.get(0);
    TriggerKey triggerKey = trigger.getKey();

    return getJobInfo(jobKey, triggerKey);
  }

  @PutMapping("/{oldJobGroup}/{oldJobName}")
  public JobInfo edit(@RequestBody JobInfo jobInfo,
      @PathVariable("oldJobGroup") String oldJobGroup,
      @PathVariable("oldJobName") String oldJobName)
      throws SchedulerException, ClassNotFoundException {
    JobKey jobKey = JobKey.jobKey(oldJobName, oldJobGroup);

    deleteJobInfo(jobKey);
    addJobInfo(jobInfo);
    return jobInfo;
  }

  @SuppressWarnings("unchecked")
  private void addJobInfo(JobInfo jobInfo) throws SchedulerException, ClassNotFoundException {
    Assert.notNull(jobInfo, "入参jobInfo为空");
    JobKey jobKey = JobKey.jobKey(jobInfo.getJobName(), jobInfo.getJobGroup());
    if (!globalScheduler.checkExists(jobKey)) {
      Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(jobInfo.getClassName());
      JobDetail jobDetail = JobBuilder.newJob(clazz)
          .withIdentity(jobKey)
          .withIdentity(jobInfo.getJobName(), jobInfo.getJobGroup())
          .withDescription(jobInfo.getJobName())
          .build();
      if (!Assert.isEmpty(jobInfo.getMapList())) {
        for (Map<String, String> map : jobInfo.getMapList()) {
          jobDetail.getJobDataMap().put(map.get("key"), map.get("value"));
        }
      }
      Trigger trigger;
      TriggerKey triggerKey = TriggerKey
          .triggerKey(jobInfo.getTriggerName(), jobInfo.getTriggerGroup());
      if (globalScheduler.checkExists(triggerKey)) {
        trigger = globalScheduler.getTrigger(triggerKey);
      } else {
        if (jobInfo.getTriggerType() == TriggerType.SIMPLE) {
          trigger = TriggerBuilder.newTrigger()
              .withIdentity(triggerKey)
              .withSchedule(SimpleScheduleBuilder
                  .simpleSchedule()
                  .withIntervalInSeconds(jobInfo.getInterval())
                  .repeatForever())
              .build();
        } else {
          trigger = TriggerBuilder.newTrigger()
              .withIdentity(triggerKey)
              .withSchedule(CronScheduleBuilder
                  .cronSchedule(jobInfo.getCron()))
              .build();
        }
      }
      globalScheduler.scheduleJob(jobDetail, trigger);
    }
  }

  private JobInfo getJobInfo(JobKey jobKey, TriggerKey triggerKey)
      throws SchedulerException {
    List<? extends Trigger> triggers = globalScheduler.getTriggersOfJob(jobKey);
    Trigger trigger = triggers.get(0);
    Trigger.TriggerState triggerState = globalScheduler.getTriggerState(triggerKey);
    JobDetail jobDetail = globalScheduler.getJobDetail(jobKey);

    JobInfo jobInfo = new JobInfo();
    jobInfo.setJobName(jobKey.getName());
    jobInfo.setJobGroup(jobKey.getGroup());
    jobInfo.setTriggerName(triggerKey.getName());
    jobInfo.setTriggerGroup(triggerKey.getGroup());
    jobInfo.setClassName(jobDetail.getJobClass().getName());
    jobInfo.setStatus(triggerState.toString());

    ArrayList<Map<String, String>> mapList = new ArrayList<>();
    String[] keys = jobDetail.getJobDataMap().getKeys();
    for (String key : keys) {
      Map<String, String> map = new HashMap<>();
      map.put("key", key);
      map.put("value", jobDetail.getJobDataMap().getString(key));
      mapList.add(map);
    }
    jobInfo.setMapList(mapList);

    if (trigger instanceof CronTrigger) {
      CronTrigger theTrigger = (CronTrigger) trigger;
      jobInfo.setCron(theTrigger.getCronExpression());
      jobInfo.setTriggerType(TriggerType.CRON);
    } else if (trigger instanceof SimpleTrigger) {
      SimpleTrigger theTrigger = (SimpleTrigger) trigger;
      jobInfo.setInterval((new Long(theTrigger.getRepeatInterval()).intValue()) / 1000);
      jobInfo.setTriggerType(TriggerType.SIMPLE);
    }
    return jobInfo;
  }

  private void deleteJobInfo(JobKey jobKey) throws SchedulerException {
    if (globalScheduler.checkExists(jobKey)) {
      globalScheduler.deleteJob(jobKey);
    }
  }

}
