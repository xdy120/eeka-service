package com.greatonce.oms.api.qimen;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.api.qimen.impl.handler.OrderHandler;
import com.greatonce.oms.api.qimen.impl.handler.OrderHandlerFactory;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest.Order;
import com.greatonce.oms.domain.enums.OrderType;
import com.qimen.api.response.DeliveryorderConfirmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * 单据处理状态回传.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
@RestController

public class QimenOrderProcessReportController extends QimenController {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(QimenOrderProcessReportController.class);

  @Autowired
  private OrderHandlerFactory orderHandlerFactory;

  @PostMapping(params = "method=orderprocess.report")
  public DeliveryorderConfirmResponse dispatchConfirm(
      @RequestBody OmsOrderProcessReportRequest request) {
    try {
      final Order order = request.getOrder();
      Assert.notEmpty(request.getOrderCode(), "单据编码不能为空");
      Assert.notEmpty(order.getOrderType(), "单据类型不能为空");
      OrderType orderType = QimenConverter.reverseOrderType(order.getOrderType());
      OrderHandler orderHandler = orderHandlerFactory.getOrderHandler(orderType);
      if (orderHandler != null) {
        orderHandler.reportOrderProcess(request);
        return QimenCustomResponseUtil
            .resultSuccessResponse(new DeliveryorderConfirmResponse(), request.getOwnerCode());
      } else {
        return QimenCustomResponseUtil.resultFailureResponse(new DeliveryorderConfirmResponse(), "OMS暂不支持此类型单据");
      }
    } catch (Exception ex) {
      LOGGER.error("单据编号：" + request.getOrderCode() + "，单据处理状态回传失败！", ex);
      return QimenCustomResponseUtil.resultFailureResponse(new DeliveryorderConfirmResponse(), ex.getMessage());
    }

  }
}
