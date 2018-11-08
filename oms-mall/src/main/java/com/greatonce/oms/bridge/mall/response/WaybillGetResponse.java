package com.greatonce.oms.bridge.mall.response;

import com.greatonce.oms.bridge.mall.request.WaybillGetRequest;

/**
 * 电子面单获取响应.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-04
 */
public class WaybillGetResponse extends MallResponse<WaybillGetRequest> {

  /**
   * 运单号.
   */
  private String waybillCode;
  /**
   * 集包地编码.
   */
  private String packageCenterCode;
  /**
   * 集包地名称.
   */
  private String packageCenterName;
  /**
   * 大头笔名称.
   */
  private String bigShortName;
  /**
   * 二段码.
   */
  private String secondSectionCode;
  /**
   * 三段码.
   */
  private String thirdSectionCode;

  public WaybillGetResponse(WaybillGetRequest request) {
    super(request);
  }

  public WaybillGetResponse(WaybillGetRequest request, boolean isSuccess, String result) {
    super(request, isSuccess, result);
  }

  public String getWaybillCode() {
    return waybillCode;
  }

  public void setWaybillCode(String waybillCode) {
    this.waybillCode = waybillCode;
  }

  public String getPackageCenterCode() {
    return packageCenterCode;
  }

  public void setPackageCenterCode(String packageCenterCode) {
    this.packageCenterCode = packageCenterCode;
  }

  public String getPackageCenterName() {
    return packageCenterName;
  }

  public void setPackageCenterName(String packageCenterName) {
    this.packageCenterName = packageCenterName;
  }

  public String getBigShortName() {
    return bigShortName;
  }

  public void setBigShortName(String bigShortName) {
    this.bigShortName = bigShortName;
  }

  public String getSecondSectionCode() {
    return secondSectionCode;
  }

  public void setSecondSectionCode(String secondSectionCode) {
    this.secondSectionCode = secondSectionCode;
  }

  public String getThirdSectionCode() {
    return thirdSectionCode;
  }

  public void setThirdSectionCode(String thirdSectionCode) {
    this.thirdSectionCode = thirdSectionCode;
  }
}
