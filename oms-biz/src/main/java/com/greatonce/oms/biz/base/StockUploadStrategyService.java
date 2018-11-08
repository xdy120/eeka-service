package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.domain.base.StockUploadWarehouse;
import com.greatonce.oms.query.base.StockUploadStrategyQuery;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * StockUploadStrategy <br/>
 * 库存上传策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockUploadStrategyService extends BizService<StockUploadStrategy, StockUploadStrategyQuery> {
    Map<Long, StockUploadWarehouse> getSettingStockUploadWarehouseMap(Long id);

    Set<Long> getUploadWarehouseIds();

    List<StockUploadStrategy> listByStoreId(Long storeId);
}