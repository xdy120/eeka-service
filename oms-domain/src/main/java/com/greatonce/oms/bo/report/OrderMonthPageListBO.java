package com.greatonce.oms.bo.report;

import java.util.List;
import java.util.Map;

/**
 * @author Shenzhen cca
 * @version 2018/7/23
 */
public class OrderMonthPageListBO {
  private int pageSize;
  private int page;
  private int total;
  private List columns;
  private List<Map> data;

  public OrderMonthPageListBO() {
  }

  public OrderMonthPageListBO(int pageSize, int page, int total) {
    this.pageSize = pageSize;
    this.page = page;
    this.total = total;
  }

  public int getPageSize() {
    return pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public List getColumns() {
    return columns;
  }

  public void setColumns(List columns) {
    this.columns = columns;
  }

  public List<Map> getData() {
    return data;
  }

  public void setData(List<Map> data) {
    this.data = data;
  }
}
