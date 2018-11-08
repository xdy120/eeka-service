package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.stock.StockLoanInDetail;
import com.greatonce.oms.query.stock.StockLoanInDetailQuery;
import java.util.List;

/**
 * 还入单明细. StockLoanInDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockLoanInDetailService extends
    DetailService<StockLoanIn, StockLoanInDetail, StockLoanInDetailQuery> {

  /**
   * 获取还入单明细
   */
  List<StockLoanInDetail> listDetails(Long stockLoanInId);
}