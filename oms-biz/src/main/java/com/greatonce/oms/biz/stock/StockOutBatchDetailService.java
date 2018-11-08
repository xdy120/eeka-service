package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.query.stock.StockOutBatchDetailQuery;
import java.util.List;

/**
 * 出库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockOutBatchDetailService extends
    DetailService<StockOutBatch, StockOutBatchDetail, StockOutBatchDetailQuery> {

  List<StockOutBatchDetail> listDetails(Long stockOutBatchId);
}