package com.greatonce.oms.domain.base;

import com.greatonce.oms.domain.VersionDO;
import com.greatonce.oms.domain.enums.JobTriggerType;
import com.greatonce.oms.domain.enums.DownloadType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 下载配置.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public class StoreDownloadConfig extends VersionDO {
  /**
   * 开始时间.
   */
  private LocalDateTime beginTime;
  /**
   * 创建时间.
   */
  private LocalDateTime createdTime;
  /**
   * 延迟分钟.
   */
  private Integer delayMinutes;
  /**
   * 下载类型.
   */
  private DownloadType downloadType;
  /**
   * 间隔秒数.
   */
  private Integer intervalSeconds;
  /**
   * 是否启用.
   */
  private Boolean enable;
  /**
   * 开启补单.
   */
  private Boolean supplement;
  /**
   * 任务执行类.
   */
  private String jobClass;
  /**
   * 任务表达式.
   */
  private String jobCron;
  /**
   * 任务间隔.
   */
  private Integer jobInterval;
  /**
   * 任务触发类型.
   */
  private JobTriggerType jobTriggerType;
  /**
   * 更新时间.
   */
  private LocalDateTime modifiedTime;
  /**
   * 下载配置id.
   */
  private Long storeDownloadConfigId;
  /**
   * 店铺id.
   */
  private Long storeId;
  /**
   * 店铺名称.
   */
  private String storeName;
  /**
   * 版本.
   */
  private Integer version;


  @Override
  public void setPrimaryKey(Long pk) {
    this.storeDownloadConfigId = pk;
  }

  @Override
  public Long getPrimaryKey() {
    return this.storeDownloadConfigId;
  }


  /**
   * 开始时间.
   */
  public void setBeginTime(LocalDateTime beginTime) {
    this.beginTime = beginTime;
  }

  /**
   * 开始时间.
   */
  public LocalDateTime getBeginTime() {
    return this.beginTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public void setCreatedTime(LocalDateTime createdTime) {
    this.createdTime = createdTime;
  }

  /**
   * 创建时间.
   */
  @Override
  public LocalDateTime getCreatedTime() {
    return this.createdTime;
  }

  /**
   * 延迟分钟.
   */
  public void setDelayMinutes(Integer delayMinutes) {
    this.delayMinutes = delayMinutes;
  }

  /**
   * 延迟分钟.
   */
  public Integer getDelayMinutes() {
    return this.delayMinutes;
  }

  /**
   * 下载类型.
   */
  public void setDownloadType(DownloadType downloadType) {
    this.downloadType = downloadType;
  }

  /**
   * 下载类型.
   */
  public DownloadType getDownloadType() {
    return this.downloadType;
  }

  /**
   * 间隔秒数.
   */
  public void setIntervalSeconds(Integer intervalSeconds) {
    this.intervalSeconds = intervalSeconds;
  }

  /**
   * 间隔秒数.
   */
  public Integer getIntervalSeconds() {
    return this.intervalSeconds;
  }

  /**
   * 是否启用.
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   */
  public Boolean isEnable() {
    return this.enable;
  }

  /**
   * 开启补单.
   */
  public void setSupplement(Boolean supplement) {
    this.supplement = supplement;
  }

  /**
   * 开启补单.
   */
  public Boolean isSupplement() {
    return this.supplement;
  }

  /**
   * 任务执行类.
   */
  public void setJobClass(String jobClass) {
    this.jobClass = jobClass == null ? null : jobClass.trim();
  }

  /**
   * 任务执行类.
   */
  public String getJobClass() {
    return this.jobClass;
  }

  /**
   * 任务表达式.
   */
  public void setJobCron(String jobCron) {
    this.jobCron = jobCron == null ? null : jobCron.trim();
  }

  /**
   * 任务表达式.
   */
  public String getJobCron() {
    return this.jobCron;
  }

  /**
   * 任务间隔.
   */
  public void setJobInterval(Integer jobInterval) {
    this.jobInterval = jobInterval;
  }

  /**
   * 任务间隔.
   */
  public Integer getJobInterval() {
    return this.jobInterval;
  }

  /**
   * 任务触发类型.
   */
  public void setJobTriggerType(JobTriggerType jobTriggerType) {
    this.jobTriggerType = jobTriggerType;
  }

  /**
   * 任务触发类型.
   */
  public JobTriggerType getJobTriggerType() {
    return this.jobTriggerType;
  }

  /**
   * 更新时间.
   */
  @Override
  public void setModifiedTime(LocalDateTime modifiedTime) {
    this.modifiedTime = modifiedTime;
  }

  /**
   * 更新时间.
   */
  @Override
  public LocalDateTime getModifiedTime() {
    return this.modifiedTime;
  }

  /**
   * 下载配置id.
   */
  public void setStoreDownloadConfigId(Long storeDownloadConfigId) {
    this.storeDownloadConfigId = storeDownloadConfigId;
  }

  /**
   * 下载配置id.
   */
  public Long getStoreDownloadConfigId() {
    return this.storeDownloadConfigId;
  }

  /**
   * 店铺id.
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   */
  public Long getStoreId() {
    return this.storeId;
  }

  /**
   * 店铺名称.
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   */
  public String getStoreName() {
    return this.storeName;
  }

  /**
   * 版本.
   */
  @Override
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   */
  @Override
  public Integer getVersion() {
    return this.version;
  }
}