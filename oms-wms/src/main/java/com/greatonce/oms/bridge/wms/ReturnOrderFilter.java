package com.greatonce.oms.bridge.wms;

import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;

/**
 * 退货通知单过滤器.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-18
 */
public interface ReturnOrderFilter extends WmsFilter {

  void execute(ReturnOrderCreateRequest request, ReturnNoticeOrder returnNoticeOrder);
}
