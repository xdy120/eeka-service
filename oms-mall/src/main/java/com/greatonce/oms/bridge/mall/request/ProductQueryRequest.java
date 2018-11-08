package com.greatonce.oms.bridge.mall.request;

import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.product.MallProductStatus;

import java.time.LocalDateTime;

/**
 * 铺货下载参数
 *
 * @author zhangqin
 * @version 2016/11/16
 */
public class ProductQueryRequest extends MallRequest {

  private String id;
  private String code;
  private LocalDateTime beginTime;
  private LocalDateTime endTime;
  private MallProductStatus mallProductStatus;
  private int page;

  public ProductQueryRequest(Store store) {
    super(store);
    this.page = 1;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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

  public MallProductStatus getMallProductStatus() {
    return mallProductStatus;
  }

  public void setMallProductStatus(MallProductStatus mallProductStatus) {
    this.mallProductStatus = mallProductStatus;
  }

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }
}
