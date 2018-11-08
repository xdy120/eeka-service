package com.greatonce.oms.bridge.mall.impl.jd.entity;

import java.util.List;

/**
 * 京东响应数据反序列化对象.
 *
 * @author Lcc
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2018/8/24
 */
public class JdResponseData {

  /**
   * 平台单号.
   */
  private String platformOrderNo;
  /**
   * 电子面单列表.
   */
  private List<String> waybillCodeList;


  public String getPlatformOrderNo() {
    return platformOrderNo;
  }

  public void setPlatformOrderNo(String platformOrderNo) {
    this.platformOrderNo = platformOrderNo;
  }

  public List<String> getWaybillCodeList() {
    return waybillCodeList;
  }

  public void setWaybillCodeList(List<String> waybillCodeList) {
    this.waybillCodeList = waybillCodeList;
  }
}
