package com.greatonce.oms.biz.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.bo.stock.DispatchStockInfoBO;
import com.greatonce.oms.bo.stock.StockCheckResultBO;
import com.greatonce.oms.bo.stock.StockQueryBO;
import com.greatonce.oms.bo.stock.StockQueryRatioBO;
import com.greatonce.oms.bo.stock.StockSumBO;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.query.stock.StockQuery;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * 库存.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface StockService extends BatchBizService<Stock, StockQuery> {


  /**
   * 获取SKU的库存信息，包括占用.
   *
   * @param skuId SKUID
   */
  List<StockSumBO> listSkuStockInfo(Long skuId, List<Long> virtualWarehouseIds);


  /**
   * 获取SKU的库存信息，包括占用.
   */
  PageList<StockQueryBO> stockQuery(StockQuery stockQuery, int pageNo, int pageSize);

  /**
   * 调整库存数量.
   *
   * @param orderCode 单据编码
   * @param orderType 单据类型
   * @param skuId 规格ID
   * @param skuCode 规格编码
   * @param virtualWarehouse 虚拟仓
   * @param quantity 正+负-
   * @return 影响行数
   */
  int adjustQuantity(String orderCode, OrderType orderType, Long skuId, String skuCode,
      VirtualWarehouse virtualWarehouse, int quantity);

  /**
   * 调整库存数量.
   *
   * @param orderCode 单据编码
   * @param orderType 单据类型
   * @param skuId 规格ID
   * @param skuCode 规格编码
   * @param warehouseId 实体仓ID
   * @param warehouseName 尸体仓名称
   * @param virtualWarehouseId 虚拟仓ID
   * @param virtualWarehouseName 虚拟仓名称
   * @param quantity 正+负-
   */
  int adjustQuantity(String orderCode, OrderType orderType, Long skuId, String skuCode,
      Long warehouseId, String warehouseName, Long virtualWarehouseId, String virtualWarehouseName,
      int quantity);

  /**
   * 覆盖库存.
   *
   * @param skuId 规格ID
   * @param virtualWarehouseId 虚拟仓ID
   * @param quantity 数量
   * @return 影响行数
   */
  int coverQuantity(Long skuId, Long virtualWarehouseId, int quantity);

  /**
   * 获取库存校验.
   */
  StockCheckResultBO getStockCheckResult(Long skuId, Long virtualWarehouseId,
      LocalDateTime sortTime);

  /**
   * 在途入库.
   */
  int transitIn(String orderCode, OrderType orderType, Long skuId, String skuCode, Long warehouseId,
      String warehouseName, Long virtualWarehouseId, String virtualWarehouseName,
      int quantity);

  /**
   * 在途入库.
   */
  int transitIn(String orderCode, OrderType orderType, Long skuId, String skuCode,
      VirtualWarehouse virtualWarehouse,
      int quantity);

  /**
   * 调整在途.
   */
  int adjustTransitQuantity(String orderCode, OrderType orderType, Long skuId, String skuCode,
      Long warehouseId,
      String warehouseName, Long virtualWarehouseId, String virtualWarehouseName,
      int quantity);

  /**
   * 获取可配货库存.
   */
  List<DispatchStockInfoBO> listDispatchStock(Collection<Long> skuIds,
      Collection<Long> virtualWarehouseIds, LocalDateTime paidTime);

  /**
   * 校验是否有库存记录.
   */
  boolean checkExists(Long skuId, Long virtualWarehouseId);

  /**
   * 获取某个仓下某个SKU的库存数量.
   */
  int getStockQuantity(Long skuId, Long virtualWarehouseId);

  /**
   * 根据订单号，查询订单商品明细，根据商品明细和数量，获取门店动销比.
   */
  StockQueryRatioBO ratioQuery(Long salesOrderId, Long type);

  /**
   * 导出库存.
   */
  void exportStock(String fileName, StockQuery stockQuery);
}