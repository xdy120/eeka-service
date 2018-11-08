package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.domain.stock.StockLoanOutDetail;
import com.greatonce.oms.query.stock.StockLoanOutDetailQuery;
import java.util.List;

/**
 * 借出单明细. StockLoanOutDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockLoanOutDetailService extends
    DetailService<StockLoanOut, StockLoanOutDetail, StockLoanOutDetailQuery> {

  /**
   * 查询可用数.
   */
  List<StockLoanOutDetail> listAvailable(Long stockLoanOutId);

  /**
   * 查询可销数.
   */
  List<StockLoanOutDetail> listSaleable(Long stockLoanOutId);

  /**
   * 获取借出单明细.
   */
  List<StockLoanOutDetail> listDetails(Long stockLoanOutId);
}