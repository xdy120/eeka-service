package com.greatonce.oms.bridge.wms.response;

import com.greatonce.oms.bridge.wms.request.OrderProcessQueryRequest;
import java.util.List;

/**
 * @author Shenzhen cca
 * @version 2018/8/31
 */
public class OrderProcessQueryResponse extends WmsResponse<OrderProcessQueryRequest> {

  private List<Process> processes;

  public OrderProcessQueryResponse(OrderProcessQueryRequest request) {
    super(request);
  }

  public OrderProcessQueryResponse(OrderProcessQueryRequest request, boolean success,
      String message) {
    super(request, success, message);
  }

  public static class Process {

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

  public List<Process> getProcesses() {
    return processes;
  }

  public void setProcesses(List<Process> processes) {
    this.processes = processes;
  }
}
