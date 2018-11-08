package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.biz.stock.StockLoanInService;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.stock.StockLoanIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 借调还入单入库.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
@Component
public class StockLoanInHandler extends AbstractInOrderHandler implements StockInHandler {

  @Autowired
  private StockLoanInService stockLoanInService;

  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.LOAN_IN_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    StockLoanIn stockLoanIn = stockLoanInService.getByCode(request.getOrderCode());
    log(stockLoanInService.getBizLogger(), stockLoanIn.getStockLoanInId(), request);
  }

  @Override
  public void confirm(OmsEntryOrderConfirmRequest request) {
    StockLoanIn stockLoanIn = stockLoanInService.getByCode(request.getOrderCode());
    Assert.notNull(stockLoanIn, "单据未找到:" + request.getOrderCode());
    WmsAutoInBO bo = parseRequest(request);
    bo.setVersion(stockLoanIn.getVersion());
    stockLoanInService.wmsAutoIn(stockLoanIn, bo);
  }
}
