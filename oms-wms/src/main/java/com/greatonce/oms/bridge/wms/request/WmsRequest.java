package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import java.util.Map;

/**
 * 仓库系统请求
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/2
 */
public abstract class WmsRequest {

  final Warehouse warehouse;
  private String batchCode;
  private Map extendProps;

  public WmsRequest(Warehouse warehouse) {
    this.warehouse = warehouse;
  }

  public Warehouse getWarehouse() {
    return warehouse;
  }

  public String getBatchCode() {
    return batchCode;
  }

  public void setBatchCode(String batchCode) {
    this.batchCode = batchCode;
  }

  public Map getExtendProps() {
    return extendProps;
  }

  public void setExtendProps(Map extendProps) {
    this.extendProps = extendProps;
  }

}
