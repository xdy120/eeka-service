package com.greatonce.oms.domain.trade;

import java.io.Serializable;

/**
 * 电子面单信息.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/24
 */
public class WaybillInfo implements Serializable {

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
  /**
   * 平台快递id(用来物流通知).
   */
  private String mallExpressId;
  /**
   * 平台快递编码(用来物流通知).
   */
  private String mallExpressCode;
  /**
   * 平台快递名称(用来物流通知).
   */
  private String mallExpressName;

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

  public String getMallExpressCode() {
    return mallExpressCode;
  }

  public void setMallExpressCode(String mallExpressCode) {
    this.mallExpressCode = mallExpressCode;
  }

  public String getMallExpressName() {
    return mallExpressName;
  }

  public void setMallExpressName(String mallExpressName) {
    this.mallExpressName = mallExpressName;
  }

  public String getThirdSectionCode() {
    return thirdSectionCode;
  }

  public void setThirdSectionCode(String thirdSectionCode) {
    this.thirdSectionCode = thirdSectionCode;
  }

  public String getMallExpressId() {
    return mallExpressId;
  }

  public void setMallExpressId(String mallExpressId) {
    this.mallExpressId = mallExpressId;
  }
}
