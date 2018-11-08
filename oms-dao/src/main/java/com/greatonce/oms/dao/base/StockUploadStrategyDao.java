package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.query.base.StockUploadStrategyQuery;

import java.util.List;

/**
 * StockUploadStrategy <br/>
 * 库存上传策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

public interface StockUploadStrategyDao extends QueryDao<StockUploadStrategy, StockUploadStrategyQuery> {
    List<StockUploadStrategy> listByIsUploadIsManualUpload();

    List<StockUploadStrategy> listSettingStockUpload(Long storeId);
}
