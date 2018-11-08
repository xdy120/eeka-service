package com.greatonce.oms.api.qimen.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.api.qimen.impl.handler.OrderHandlerFactory;
import com.greatonce.oms.api.qimen.impl.handler.StockOutHandler;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.QimenStockOutConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.WmsType;
import com.qimen.api.response.StockoutConfirmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QimenStockOutConfirmImpl implements QimenStockOutConfirmStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(QimenStockOutConfirmImpl.class);

  @Autowired
  private OrderHandlerFactory orderHandlerFactory;

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }

  @Override
  public StockoutConfirmResponse confirm(OmsStockOutConfirmRequest request) {
    try {
      Assert.notEmpty(request.getOrderCode(), "单据编码不能为空");
      Assert.notEmpty(request.getDeliveryOrder().getOrderType(), "单据类型不能为空");

      OrderType orderType = QimenConverter
          .reverseOutOrderType(request.getDeliveryOrder().getOrderType(), request.getOrderCode());
      StockOutHandler stockOutHandler = orderHandlerFactory.getStockOutHandler(orderType);
      Assert.notNull(stockOutHandler, "未知单据类型：" + request.getDeliveryOrder().getOrderType());
      stockOutHandler.confirm(request);
      return QimenCustomResponseUtil
          .resultSuccessResponse(new StockoutConfirmResponse(), request.getOrderCode());
    } catch (Exception e) {
      LOGGER.error("单据编号：" + request.getOrderCode() + "，奇门出库单处理失败！", e);
      return QimenCustomResponseUtil
          .resultFailureResponse(new StockoutConfirmResponse(), e.getMessage());
    }
  }
}
