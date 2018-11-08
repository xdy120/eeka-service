package com.greatonce.oms.biz.stock;

import com.greatonce.oms.biz.BatchBizService;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.query.stock.StockOccupancyQuery;

/**
 * StockOccupancy <br/>
 * 库存占用
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface StockOccupancyService extends
    BatchBizService<StockOccupancy, StockOccupancyQuery> {


  /**
   * 删除占用
   *
   * @param mainId 主ID
   * @param type 占用类型
   */
  void deleteOccupancy(Long mainId, StockOccupancyType type);

  /**
   * 删除占用
   *
   * @param mainId 主id
   * @param detailId 明细id
   * @param type 占用类型
   */
  void deleteOccupancy(Long mainId, Long detailId, StockOccupancyType type);

  /**
   * 修改占用数量,quantity减少传负数
   *
   * @param mainId 主id
   * @param detailId 明细id
   * @param type 占用类型
   * @param quantity 修改数量
   */
  void adjustQuantity(Long mainId, Long detailId, StockOccupancyType type, int quantity);


  /**
   * 修改占用仓库，需要传入虚拟仓及实体仓
   *
   * @param mainId 主id
   * @param detailId 明细id
   * @param type 占用类型
   * @param status 占用状态
   * @param warehouse 占用仓库
   */
  void updateOccupancy(Long mainId, Long detailId, StockOccupancyType type,
      StockOccupancyStatus status, VirtualWarehouse warehouse);


  /**
   * 修改占用的 mainId  detailId 和 占用的类型
   *
   * @param mainId 主id
   * @param detailId 明细 id
   * @param type 占用类型
   */
  void updateIdAndType(Long mainId, Long detailId, StockOccupancyType type, Long oldMainId,Long oldDetailId,StockOccupancyType oldType);

  /**
   * 修改占用仓库，需要传入虚拟仓及实体仓
   *
   * @param mainId 主id
   * @param detailId 明细id
   * @param type 占用类型
   * @param warehouse 占用仓库
   */
  void updateOccupancy(Long mainId, Long detailId, StockOccupancyType type,
      VirtualWarehouse warehouse);

  /**
   * 更新占用状态
   *
   * @param mainId 主id
   * @param detailId 单据明细ID
   * @param type 占用类型
   * @param status 占用状态
   */
  void updateOccupancy(Long mainId, Long detailId, StockOccupancyType type,
      StockOccupancyStatus status);


  /**
   * 修改占用仓库，需要传入虚拟仓及实体仓
   *
   * @param mainId 主id
   * @param type 占用类型
   * @param status 占用状态
   * @param warehouse 占用仓库
   */
  void updateOccupancy(Long mainId, StockOccupancyType type, StockOccupancyStatus status,
      VirtualWarehouse warehouse);

  /**
   * 修改占用仓库，需要传入虚拟仓及实体仓
   *
   * @param mainId 主id
   * @param type 占用类型
   * @param warehouse 占用仓库
   */
  void updateOccupancy(Long mainId, StockOccupancyType type, VirtualWarehouse warehouse);

  /**
   * 更新占用状态
   *
   * @param mainId 主id
   * @param type 占用类型
   * @param status 占用状态
   */
  void updateOccupancy(Long mainId, StockOccupancyType type, StockOccupancyStatus status);

  /**
   * 更新SKU占用
   *
   * @param mainId 主ID
   * @param detailId 单据明细ID
   * @param type 占用类型
   * @param skuId 规格ID
   * @param quantity 数量
   */
  void updateSku(Long mainId, Long detailId, StockOccupancyType type, Long skuId, String skuCode,
      Integer quantity);

  /**
   * 修改占用 先判定是否存在占用，不存在新增 存在调adjustQuantity方法
   */
  void updateOccupancy(StockOccupancy stockOccupancy);
}