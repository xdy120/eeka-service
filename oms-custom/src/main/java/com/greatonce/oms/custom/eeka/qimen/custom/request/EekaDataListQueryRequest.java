package com.greatonce.oms.custom.eeka.qimen.custom.request;

import com.greatonce.oms.bridge.wms.qimen.request.OmsQimenCustomRequest;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
public class EekaDataListQueryRequest extends OmsQimenCustomRequest {

  private String dataType;
  private Integer pageSize;
  private Integer page;
  private String query;

  public String getDataType() {
    return dataType;
  }

  public void setDataType(String dataType) {
    this.dataType = dataType;
  }

  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
  }

  public String getQuery() {
    return query;
  }

  public void setQuery(
      String query) {
    this.query = query;
  }

  public static class DataQuery{
    private String beginTime;
    private String endTime;

    public String getBeginTime() {
      return beginTime;
    }

    public void setBeginTime(String beginTime) {
      this.beginTime = beginTime;
    }

    public String getEndTime() {
      return endTime;
    }

    public void setEndTime(String endTime) {
      this.endTime = endTime;
    }
  }

}
