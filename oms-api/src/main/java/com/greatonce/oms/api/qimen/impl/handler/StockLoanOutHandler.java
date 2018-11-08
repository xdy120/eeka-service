package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.bridge.wms.qimen.request.OmsOrderProcessReportRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.greatonce.oms.biz.stock.StockLoanOutService;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.stock.StockLoanOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 借调借出单出库.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-06-27
 */
@Component
public class StockLoanOutHandler extends AbstractOutOrderHandler implements StockOutHandler {

  @Autowired
  private StockLoanOutService stockLoanOutService;

  @Override
  public OrderType[] supports() {
    return new OrderType[]{OrderType.LOAN_OUT_ORDER};
  }

  @Override
  public void reportOrderProcess(OmsOrderProcessReportRequest request) {
    StockLoanOut stockLoanOut = stockLoanOutService.getByCode(request.getOrderCode());
    log(stockLoanOutService.getBizLogger(), stockLoanOut.getStockLoanOutId(), request);
  }

  @Override
  public void confirm(OmsStockOutConfirmRequest request) {
    StockLoanOut stockLoanOut = stockLoanOutService.getByCode(request.getOrderCode());
    Assert.notNull(stockLoanOut, "单据未找到:" + request.getOrderCode());
    WmsAutoOutBO bo = parseRequest(request);
    bo.setVersion(stockLoanOut.getVersion());
    stockLoanOutService.wmsAutoOut(stockLoanOut, bo);
  }
}
