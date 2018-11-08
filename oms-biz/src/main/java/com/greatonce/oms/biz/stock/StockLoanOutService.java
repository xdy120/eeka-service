package com.greatonce.oms.biz.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockLoanOutBO;
import com.greatonce.oms.bo.stock.StockLoanOutCancelBO;
import com.greatonce.oms.bo.stock.StockLoanOutVerificationBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.query.stock.StockLoanOutQuery;

/**
 * 借出单. StockLoanOut <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockLoanOutService extends BizService<StockLoanOut, StockLoanOutQuery> {

  /**
   * 审核借出单.
   */
  void audit(StockLoanOut stockLoanOut, VersionBO bo);

  /**
   * 取消借出单.
   */
  void cancel(StockLoanOut stockLoanOut, StockLoanOutCancelBO bo);

  /**
   * 通知wms.
   */
  void noticeWms(StockLoanOut stockLoanOut);

  /**
   * 借调统计查询.
   */
  PageList<StockLoanOutBO> listStatistics(StockLoanOutQuery stockLoanOutQuery, int page,
      int pageSize);

  /**
   * 导出借调统计.
   */
  void exportLoan(StockLoanOutQuery stockLoanOutQuery, String fileName);

  /**
   * 通过Code获取借出单.
   */
  StockLoanOut getByCode(String orderCode);

  /**
   * 出库回传.
   */
  void wmsAutoOut(StockLoanOut stockLoanOut, WmsAutoOutBO bo);

  /**
   * 归还.
   */
  void returnBack(StockLoanOut stockLoanOut, StockLoanIn stockLoanIn);

  /**
   * 核销.
   */
  void verificationDetail(StockLoanOut stockLoanOut, StockLoanOutVerificationBO bo);
}