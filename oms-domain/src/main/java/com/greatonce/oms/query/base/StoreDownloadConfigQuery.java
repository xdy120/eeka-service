package com.greatonce.oms.query.base;

import com.greatonce.core.database.Query;
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
public class StoreDownloadConfigQuery extends Query {
  /**
   * 开始时间开始.
   */
  private LocalDateTime beginTimeBegin;
  /**
   * 开始时间结束.
   */
  private LocalDateTime beginTimeEnd;
  /**
   * 创建时间开始.
   */
  private LocalDateTime createdTimeBegin;
  /**
   * 创建时间结束.
   */
  private LocalDateTime createdTimeEnd;
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
   * 更新时间开始.
   */
  private LocalDateTime modifiedTimeBegin;
  /**
   * 更新时间结束.
   */
  private LocalDateTime modifiedTimeEnd;
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


  /**
   * 开始时间开始.
   * @param beginTimeBegin 开始.
   */
  public void setBeginTimeBegin(LocalDateTime beginTimeBegin) {
    this.beginTimeBegin = beginTimeBegin;
  }

  /**
   * 开始时间开始.
   * @return 开始时间开始
   */
  public LocalDateTime getBeginTimeBegin() {
    return this.beginTimeBegin;
  }

  /**
   * 开始时间结束.
   * @param beginTimeEnd 结束
   */
  public void setBeginTimeEnd(LocalDateTime beginTimeEnd) {
    this.beginTimeEnd = beginTimeEnd;
  }

  /**
   * 开始时间结束.
   * @return 开始时间结束
   */
  public LocalDateTime getBeginTimeEnd() {
    return this.beginTimeEnd;
  }

  /**
   * 创建时间开始.
   * @param createdTimeBegin 开始.
   */
  public void setCreatedTimeBegin(LocalDateTime createdTimeBegin) {
    this.createdTimeBegin = createdTimeBegin;
  }

  /**
   * 创建时间开始.
   * @return 创建时间开始
   */
  public LocalDateTime getCreatedTimeBegin() {
    return this.createdTimeBegin;
  }

  /**
   * 创建时间结束.
   * @param createdTimeEnd 结束
   */
  public void setCreatedTimeEnd(LocalDateTime createdTimeEnd) {
    this.createdTimeEnd = createdTimeEnd;
  }

  /**
   * 创建时间结束.
   * @return 创建时间结束
   */
  public LocalDateTime getCreatedTimeEnd() {
    return this.createdTimeEnd;
  }

  /**
   * 延迟分钟.
   * @param delayMinutes 延迟分钟
   */
  public void setDelayMinutes(Integer delayMinutes) {
    this.delayMinutes = delayMinutes;
  }

  /**
   * 延迟分钟.
   * @return 延迟分钟
   */
  public Integer getDelayMinutes() {
      return this.delayMinutes;
  }

  /**
   * 下载类型.
   * @param downloadType 下载类型
   */
  public void setDownloadType(DownloadType downloadType) {
    this.downloadType = downloadType;
  }

  /**
   * 下载类型.
   * @return 下载类型
   */
  public DownloadType getDownloadType() {
      return this.downloadType;
  }

  /**
   * 间隔秒数.
   * @param intervalSeconds 间隔秒数
   */
  public void setIntervalSeconds(Integer intervalSeconds) {
    this.intervalSeconds = intervalSeconds;
  }

  /**
   * 间隔秒数.
   * @return 间隔秒数
   */
  public Integer getIntervalSeconds() {
      return this.intervalSeconds;
  }

  /**
   * 是否启用.
   * @param enable 是否启用
   */
  public void setEnable(Boolean enable) {
    this.enable = enable;
  }

  /**
   * 是否启用.
   * @return 是否启用
   */
  public Boolean isEnable() {
      return this.enable;
  }

  /**
   * 开启补单.
   * @param supplement 开启补单
   */
  public void setSupplement(Boolean supplement) {
    this.supplement = supplement;
  }

  /**
   * 开启补单.
   * @return 开启补单
   */
  public Boolean isSupplement() {
      return this.supplement;
  }

  /**
   * 任务执行类.
   * @param jobClass 任务执行类
   */
  public void setJobClass(String jobClass) {
    this.jobClass = jobClass == null ? null : jobClass.trim();
  }

  /**
   * 任务执行类.
   * @return 任务执行类
   */
  public String getJobClass() {
      return this.jobClass;
  }

  /**
   * 任务表达式.
   * @param jobCron 任务表达式
   */
  public void setJobCron(String jobCron) {
    this.jobCron = jobCron == null ? null : jobCron.trim();
  }

  /**
   * 任务表达式.
   * @return 任务表达式
   */
  public String getJobCron() {
      return this.jobCron;
  }

  /**
   * 任务间隔.
   * @param jobInterval 任务间隔
   */
  public void setJobInterval(Integer jobInterval) {
    this.jobInterval = jobInterval;
  }

  /**
   * 任务间隔.
   * @return 任务间隔
   */
  public Integer getJobInterval() {
      return this.jobInterval;
  }

  /**
   * 任务触发类型.
   * @param jobTriggerType 任务触发类型
   */
  public void setJobTriggerType(JobTriggerType jobTriggerType) {
    this.jobTriggerType = jobTriggerType;
  }

  /**
   * 任务触发类型.
   * @return 任务触发类型
   */
  public JobTriggerType getJobTriggerType() {
      return this.jobTriggerType;
  }

  /**
   * 更新时间开始.
   * @param modifiedTimeBegin 开始.
   */
  public void setModifiedTimeBegin(LocalDateTime modifiedTimeBegin) {
    this.modifiedTimeBegin = modifiedTimeBegin;
  }

  /**
   * 更新时间开始.
   * @return 更新时间开始
   */
  public LocalDateTime getModifiedTimeBegin() {
    return this.modifiedTimeBegin;
  }

  /**
   * 更新时间结束.
   * @param modifiedTimeEnd 结束
   */
  public void setModifiedTimeEnd(LocalDateTime modifiedTimeEnd) {
    this.modifiedTimeEnd = modifiedTimeEnd;
  }

  /**
   * 更新时间结束.
   * @return 更新时间结束
   */
  public LocalDateTime getModifiedTimeEnd() {
    return this.modifiedTimeEnd;
  }

  /**
   * 下载配置id.
   * @param storeDownloadConfigId 下载配置id
   */
  public void setStoreDownloadConfigId(Long storeDownloadConfigId) {
    this.storeDownloadConfigId = storeDownloadConfigId;
  }

  /**
   * 下载配置id.
   * @return 下载配置id
   */
  public Long getStoreDownloadConfigId() {
      return this.storeDownloadConfigId;
  }

  /**
   * 店铺id.
   * @param storeId 店铺id
   */
  public void setStoreId(Long storeId) {
    this.storeId = storeId;
  }

  /**
   * 店铺id.
   * @return 店铺id
   */
  public Long getStoreId() {
      return this.storeId;
  }

  /**
   * 店铺名称.
   * @param storeName 店铺名称
   */
  public void setStoreName(String storeName) {
    this.storeName = storeName == null ? null : storeName.trim();
  }

  /**
   * 店铺名称.
   * @return 店铺名称
   */
  public String getStoreName() {
      return this.storeName;
  }

  /**
   * 版本.
   * @param version 版本
   */
  public void setVersion(Integer version) {
    this.version = version;
  }

  /**
   * 版本.
   * @return 版本
   */
  public Integer getVersion() {
      return this.version;
  }
}