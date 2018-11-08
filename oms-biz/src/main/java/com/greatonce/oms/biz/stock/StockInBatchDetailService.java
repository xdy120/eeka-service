package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.stock.StockInBatch;
import com.greatonce.oms.domain.stock.StockInBatchDetail;
import com.greatonce.oms.query.stock.StockInBatchDetailQuery;
import java.util.List;

/**
 * 入库批次明细表.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockInBatchDetailService extends
    DetailService<StockInBatch, StockInBatchDetail, StockInBatchDetailQuery> {

  List<StockInBatchDetail> listDetails(Long stockInBatchId);
}