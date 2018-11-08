package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.bo.stock.AsyncStockUploadBO;
import com.greatonce.oms.bo.stock.StockUploadSkuBO;
import com.greatonce.oms.bo.stock.SyncStockUploadBO;
import com.greatonce.oms.bo.stock.SyncStockUploadUploadSkuBO;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.enums.stock.StockUploadType;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.query.stock.StockQuery;
import java.util.List;
import java.util.Map;

/**
 * 库存上传服务.
 *
 * @author 82743
 */
public interface StockUploadService extends BizService<Stock, StockQuery> {

  /**
   * 上传所有库存.
   */
  Long allUpload();

  /**
   * 上传店铺库存.
   *
   * @param strategyId 库存策略ID
   */
  Long storeUpload(Long strategyId);

  /**
   * 计算上传库存数量.
   * 有预售，计算预售可销数量
   * 没有预售按上传比例计算
   * 计算四舍六入
   * 库存预警机制
   * 总可销比对，取小
   * 最终数量为负取0
   *
   * @param mapping 铺货
   * @param strategy 库存配置
   * @param stockSetting 库存配置
   */
  int calcUploadQuantity(ProductMallMapping mapping, StockUploadStrategy strategy,
      StockSetting stockSetting);

  /**
   * 计算套装数量.
   *
   * @param detail 组合套装信息
   * @param skuQuantity 规格数量
   */
  int calcComboQuantity(ProductCombination detail, int skuQuantity);

  /**
   * 异步手工上传.
   */
  void asyncManualUpload(AsyncStockUploadBO stockUploadBO, StockUploadType uploadType,
      boolean calcStock, String batchNo, String operator, String reason);

  /**
   * 异步手工上传.
   */
  void asyncManualUpload(StockUploadStrategy strategy, StockSetting stockSetting,
      Map<Long, StockUploadSkuBO> skuMap, List<ProductMallMapping> mappings,
      StockUploadType uploadType,
      String batchNo, String operator, String reason);

  /**
   * 同步手工上传，即时返回结果.
   */
  Map<Long, SyncStockUploadUploadSkuBO> syncManualUpload(SyncStockUploadBO stockUploadBo,
      String batchNo, String operator, String reason);

  /**
   * 自动上传.
   */
  void autoUpload(StockUploadStrategy strategy, StockSetting stockSetting,
      List<ProductMallMapping> mappings, String batchNo, String operator, String reason);
}
