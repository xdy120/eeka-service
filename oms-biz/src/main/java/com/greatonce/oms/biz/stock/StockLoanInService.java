package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockLoanInCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.query.stock.StockLoanInQuery;

/**
 * 还入单. StockLoanIn <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockLoanInService extends BizService<StockLoanIn, StockLoanInQuery> {

  /**
   * 审核还入单.
   */
  void audit(StockLoanIn stockLoanIn, VersionBO bo);

  /**
   * 取消还入单.
   */
  void cancel(StockLoanIn stockLoanIn, StockLoanInCancelBO bo);

  /**
   * 通知wms.
   */
  void noticeWms(StockLoanIn stockLoanIn);

  /**
   * 通过code获取还入单.
   */
  StockLoanIn getByCode(String orderCode);

  /**
   * 入库回传.
   */
  void wmsAutoIn(StockLoanIn stockLoanIn, WmsAutoInBO bo);

}