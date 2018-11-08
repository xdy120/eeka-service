package com.greatonce.oms.bridge.wms.impl.qimen;

import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.impl.AbstractWms;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.WmsLogger;
import com.qimen.api.DefaultQimenClient;
import com.qimen.api.QimenRequest;
import com.qimen.api.QimenResponse;
import org.springframework.stereotype.Component;

/**
 * 奇门WMS接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/5/10
 */
@Component
public class QimenWms extends AbstractWms {

  static final WmsLogger WMS_LOGGER = OmsLoggerFactory.getWmsLogger();

  @Override
  protected String getDefaultUrl() {
    return "http://qimen.api.taobao.com/router/qimen/service";
  }


  public <T extends QimenResponse> T call(Warehouse warehouse, QimenRequest<T> request)
      throws WmsException {
    return call(warehouse, request, true);
  }

  /**
   * 请求奇门.
   *
   * @param warehouse 仓库
   * @param request 请求
   * @param <T> 响应
   * @param checkResponse 自动校验结果
   * @return 响应
   * @throws WmsException 异常
   */
  public <T extends QimenResponse> T call(Warehouse warehouse, QimenRequest<T> request,
      boolean checkResponse)
      throws WmsException {
    T t;
    try {
      request.setCustomerId(warehouse.getWmsApp().getCustomerId());
      DefaultQimenClient client = new DefaultQimenClient(getUrl(warehouse),
          warehouse.getWmsApp().getAppKey(), warehouse.getWmsApp().getAppSecret());
      t = client.execute(request);
    } catch (Exception ex) {
      throw new WmsException("请求异常！" + ex.getMessage(), ex);
    }
    WMS_LOGGER.info(warehouse, "{}：request：{}，response：{}", request.getApiMethodName(),
        JsonUtil.toJson(request), t.getBody());
    if (checkResponse && !t.isSuccess()) {
      throw new WmsException("请求失败！" + t.getMessage());
    }
    return t;
  }
}
