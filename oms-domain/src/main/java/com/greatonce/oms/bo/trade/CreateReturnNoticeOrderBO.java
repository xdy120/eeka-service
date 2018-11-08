package com.greatonce.oms.bo.trade;

import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/5/14
 */
public class CreateReturnNoticeOrderBO {

  private Long userId;
  private String nickname;
  private Long virtualWarehouseId;
  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private Integer quantity;
  private List<String> boxNos;
  private List<String> skuCodes;
  private List<String> brandCodes;
  private String remark;
  private String operator;
  private List<ReturnOrderStatus> statuses;
  private String returnType;


  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public Long getVirtualWarehouseId() {
    return virtualWarehouseId;
  }

  public void setVirtualWarehouseId(Long virtualWarehouseId) {
    this.virtualWarehouseId = virtualWarehouseId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }


  public LocalDateTime getBeginTime() {
    return beginTime;
  }

  public void setBeginTime(LocalDateTime beginTime) {
    this.beginTime = beginTime;
  }

  public LocalDateTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalDateTime endTime) {
    this.endTime = endTime;
  }

  public List<String> getBoxNos() {
    return boxNos;
  }

  public void setBoxNos(List<String> boxNos) {
    this.boxNos = boxNos;
  }

  public List<String> getSkuCodes() {
    return skuCodes;
  }

  public void setSkuCodes(List<String> skuCodes) {
    this.skuCodes = skuCodes;
  }

  public List<String> getBrandCodes() {
    return brandCodes;
  }

  public void setBrandCodes(List<String> brandCodes) {
    this.brandCodes = brandCodes;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public List<ReturnOrderStatus> getStatuses() {
    return statuses;
  }

  public void setStatuses(List<ReturnOrderStatus> statuses) {
    this.statuses = statuses;
  }

  public String getReturnType() {
    return returnType;
  }

  public void setReturnType(String returnType) {
    this.returnType = returnType;
  }
}
