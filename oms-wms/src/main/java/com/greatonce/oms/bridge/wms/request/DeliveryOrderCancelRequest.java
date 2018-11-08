package com.greatonce.oms.bridge.wms.request;

import com.greatonce.oms.domain.base.Warehouse;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 发货单取消
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/2
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "request")
public class DeliveryOrderCancelRequest extends WmsOrderCancelRequest {

  private Map extendProps;

  public DeliveryOrderCancelRequest(Warehouse warehouse) {
    super(warehouse);
  }

  @Override
  public Map getExtendProps() {
    return extendProps;
  }

  @Override
  public void setExtendProps(Map extendProps) {
    this.extendProps = extendProps;
  }
}
