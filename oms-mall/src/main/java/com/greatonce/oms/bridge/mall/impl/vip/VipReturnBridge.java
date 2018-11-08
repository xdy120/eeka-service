package com.greatonce.oms.bridge.mall.impl.vip;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.mall.impl.MallUtil;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipReturnQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipReturnQueryResponse;
import com.greatonce.oms.util.logging.MallLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.vip.osp.sdk.exception.OspException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vipapis.vreturn.GetReturnDetailResponse;
import vipapis.vreturn.ReturnDeliveryInfo;
import vipapis.vreturn.VendorReturnServiceHelper;

/**
 * 唯品退货接口.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/27
 */
@Component
public class VipReturnBridge {

  private static final MallLogger MALL_LOGGER = OmsLoggerFactory.getMallLogger();
  private static final VendorReturnServiceHelper.VendorReturnServiceClient RETURN_SERVICE_CLIENT = new VendorReturnServiceHelper.VendorReturnServiceClient();
  @Autowired
  private VipMall mall;

  /**
   * 查询退供单明细.
   */
  public VipReturnQueryResponse queryReturnDetail(VipReturnQueryRequest request) {
    try {
      int pageSize = 50;
      vipapis.common.Warehouse vipWarehouse = vipapis.common.Warehouse
          .valueOf(request.getVipWarehouseCode());
      mall.initContext(request.getStore());
      GetReturnDetailResponse returnDetailResponse;
      if (Assert.isNull(request.getOuterCode())) {
        returnDetailResponse = RETURN_SERVICE_CLIENT
            .getReturnDetail(request.getStore().getSetting().getVipVendorId(), vipWarehouse, null,
                DateTimeUtil.format(request.getBeginTime()),
                DateTimeUtil.format(request.getEndTime()), request.getPage(), pageSize);
      } else {
        returnDetailResponse = RETURN_SERVICE_CLIENT
            .getReturnDetail(request.getStore().getSetting().getVipVendorId(), vipWarehouse,
                request.getOuterCode(),
                null, null, request.getPage(), pageSize);
      }

      List<ReturnDeliveryInfo> returnDeliveryInfoList = returnDetailResponse
          .getReturnDeliveryInfos();
      VipReturnQueryResponse queryResponse = new VipReturnQueryResponse(request,
          returnDeliveryInfoList);
      queryResponse.setHasNext(
          MallUtil.calcHasNext(pageSize, request.getPage(), returnDetailResponse.getTotal()));
      return queryResponse;
    } catch (OspException e) {
      return new VipReturnQueryResponse(request, false, e.getMessage());
    }
  }
}
