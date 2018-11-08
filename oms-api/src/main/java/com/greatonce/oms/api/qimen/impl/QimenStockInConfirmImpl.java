package com.greatonce.oms.api.qimen.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.api.qimen.impl.handler.OrderHandlerFactory;
import com.greatonce.oms.api.qimen.impl.handler.StockInHandler;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.QimenStockInConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.WmsType;
import com.qimen.api.response.ReturnorderConfirmResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class QimenStockInConfirmImpl implements QimenStockInConfirmStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(QimenStockInConfirmImpl.class);
  @Autowired
  private OrderHandlerFactory orderHandlerFactory;

  @Override
  public ReturnorderConfirmResponse confirm(@RequestBody OmsEntryOrderConfirmRequest request) {
    try {
      Assert.notEmpty(request.getOrderCode(), "单据编码不能为空");
      Assert.notEmpty(request.getEntryOrder().getEntryOrderType(), "单据类型不能为空");

      OrderType orderType = QimenConverter
          .reverseInOrderType(request.getEntryOrder().getEntryOrderType(), request.getOrderCode());
      StockInHandler stockInHandler = orderHandlerFactory.getStockInHandler(orderType);
      Assert.notNull(stockInHandler, "未知单据类型：" + request.getEntryOrder().getEntryOrderType());
      stockInHandler.confirm(request);
      return QimenCustomResponseUtil
          .resultSuccessResponse(new ReturnorderConfirmResponse(), request.getOrderCode());
    } catch (Exception e) {
      LOGGER.error("单据编号：" + request.getOrderCode() + "，奇门入库单确认失败！", e);
      return QimenCustomResponseUtil
          .resultFailureResponse(new ReturnorderConfirmResponse(), e.getMessage());
    }
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
