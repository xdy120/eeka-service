package com.greatonce.oms.bo.trade;

import java.time.LocalDateTime;

/**
 * @author buer
 * @version 2018-01-10 15:12
 */
public class WmsLogBO {

  private String operatorName;

  private String operateTime;

  private String operateInfo;

  private String remark;

  public String getOperatorName() {
    return operatorName;
  }

  public void setOperatorName(String operatorName) {
    this.operatorName = operatorName;
  }

  public String getOperateTime() {
    return operateTime;
  }

  public void setOperateTime(String operateTime) {
    this.operateTime = operateTime;
  }

  public String getOperateInfo() {
    return operateInfo;
  }

  public void setOperateInfo(String operateInfo) {
    this.operateInfo = operateInfo;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }
}
