package com.greatonce.oms.job.executor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/17
 */
public class JobInfo {

  private String jobName;
  private String jobGroup;
  private String triggerName;
  private String triggerGroup;
  private LocalDateTime nextTime;
  private LocalDateTime prevTime;
  private String status;
  private String className;
  private String cron;
  private Integer interval;
  private TriggerType triggerType;
  private List<Map<String, String>> mapList;

  public String getJobName() {
    return jobName;
  }

  public void setJobName(String jobName) {
    this.jobName = jobName;
  }

  public String getJobGroup() {
    return jobGroup;
  }

  public void setJobGroup(String jobGroup) {
    this.jobGroup = jobGroup;
  }

  public String getTriggerName() {
    return triggerName;
  }

  public void setTriggerName(String triggerName) {
    this.triggerName = triggerName;
  }

  public String getTriggerGroup() {
    return triggerGroup;
  }

  public void setTriggerGroup(String triggerGroup) {
    this.triggerGroup = triggerGroup;
  }

  public LocalDateTime getNextTime() {
    return nextTime;
  }

  public void setNextTime(LocalDateTime nextTime) {
    this.nextTime = nextTime;
  }

  public LocalDateTime getPrevTime() {
    return prevTime;
  }

  public void setPrevTime(LocalDateTime prevTime) {
    this.prevTime = prevTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }

  public Integer getInterval() {
    return interval;
  }

  public void setInterval(Integer interval) {
    this.interval = interval;
  }

  public TriggerType getTriggerType() {
    return triggerType;
  }

  public void setTriggerType(TriggerType triggerType) {
    this.triggerType = triggerType;
  }

  public List<Map<String, String>> getMapList() {
    return mapList;
  }

  public void setMapList(List<Map<String, String>> mapList) {
    this.mapList = mapList;
  }
}
