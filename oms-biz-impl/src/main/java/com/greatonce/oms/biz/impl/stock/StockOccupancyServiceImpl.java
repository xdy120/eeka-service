package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.impl.trade.SalesOrderServiceImpl;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.dao.stock.StockOccupancyDao;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.query.stock.StockOccupancyQuery;
import java.util.Collection;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 库占用. StockOccupancy <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class StockOccupancyServiceImpl extends
    AbstractService<StockOccupancy, StockOccupancyQuery> implements StockOccupancyService {

  private static final Logger LOGGER = LoggerFactory.getLogger(StockOccupancyServiceImpl.class);
  @Autowired
  private StockOccupancyDao dao;
  @Resource
  private IdGenerator stockOccupancyIdGenerator;

  @Override
  protected QueryDao<StockOccupancy, StockOccupancyQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockOccupancyIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockOccupancy record) {
    super.initDefaultValue(record);
  }

  @Override
  public void deleteOccupancy(Long mainId, StockOccupancyType type) {
    Assert.notNull(mainId, "单据ID不能为空");
    Assert.notNull(type, "占用不能为空");
    StockOccupancy eg = new StockOccupancy();
    eg.setMainId(mainId);
    eg.setStockOccupancyType(type);
    deleteByExample(eg);
    LOGGER.debug("删除占用：主ID：{},类型：{}", mainId, type.caption());
  }

  @Override
  public void deleteOccupancy(Long mainId, Long detailId, StockOccupancyType type) {
    Assert.notNull(mainId, "单据ID不能为空");
    Assert.notNull(detailId, "明细ID不能为空");
    Assert.notNull(type, "占用不能为空");

    StockOccupancy stockOccupancyEg = new StockOccupancy();
    stockOccupancyEg.setMainId(mainId);
    stockOccupancyEg.setDetailId(detailId);
    stockOccupancyEg.setStockOccupancyType(type);

    deleteByExample(stockOccupancyEg);
    LOGGER.debug("删除占用：主ID：{},明细ID：{}，类型：{}", mainId, detailId, type.caption());
  }

  @Override
  public void adjustQuantity(Long mainId, Long detailId, StockOccupancyType type, int quantity) {
    StockOccupancy occupancy = new StockOccupancy();
    occupancy.setMainId(mainId);
    occupancy.setDetailId(detailId);
    occupancy.setStockOccupancyType(type);
    occupancy.setQuantity(quantity);

    dao.adjustQuantity(occupancy);
    LOGGER.debug("修改数量：主ID：{},明细ID：{},数量：{},类型：{}", occupancy.getMainId(), occupancy.getDetailId(),
        occupancy.getQuantity(), occupancy.getStockOccupancyType());
  }


  @Override
  public void updateIdAndType(Long mainId, Long detailId, StockOccupancyType type, Long oldMainId,
      Long oldDetailId, StockOccupancyType oldType) {
    StockOccupancy stockOccupancy = new StockOccupancy();
    stockOccupancy.setMainId(mainId);
    stockOccupancy.setDetailId(detailId);
    stockOccupancy.setStockOccupancyType(type);

    StockOccupancy example = new StockOccupancy();
    example.setMainId(oldMainId);
    example.setDetailId(oldDetailId);
    example.setStockOccupancyType(oldType);
    dao.updateByExample(stockOccupancy, example);
    LOGGER.debug("修改占用：主ID：{},明细ID：{},仓库：{},类型：{}，状态：{}", stockOccupancy.getMainId(),
        stockOccupancy.getDetailId(), stockOccupancy.getVirtualWarehouseId(),
        stockOccupancy.getWarehouseId(), stockOccupancy
            .getStockOccupancyType());
  }


  @Override
  public void updateOccupancy(Long mainId, Long detailId, StockOccupancyType type,
      StockOccupancyStatus status, VirtualWarehouse warehouse) {
    StockOccupancy occupancy = new StockOccupancy();
    occupancy.setMainId(mainId);
    occupancy.setDetailId(detailId);
    occupancy.setStockOccupancyType(type);
    occupancy.setStatus(status);
    if (warehouse != null) {
      occupancy.setVirtualWarehouseId(warehouse.getVirtualWarehouseId());
      occupancy.setVirtualWarehouseName(warehouse.getVirtualWarehouseName());
      occupancy.setWarehouseId(warehouse.getWarehouseId());
      occupancy.setWarehouseName(warehouse.getWarehouseName());
    }
    dao.updateOccupancy(occupancy);
    LOGGER.debug("修改占用：主ID：{},明细ID：{},仓库：{},类型：{}，状态：{}", occupancy.getMainId(),
        occupancy.getDetailId(), occupancy.getVirtualWarehouseId(), occupancy.getWarehouseId(),
        occupancy
            .getStockOccupancyType());
  }


  @Override
  public void updateOccupancy(Long mainId, Long detailId, StockOccupancyType type,
      VirtualWarehouse warehouse) {
    updateOccupancy(mainId, detailId, type, null, warehouse);
  }

  @Override
  public void updateOccupancy(Long mainId, Long detailId, StockOccupancyType type,
      StockOccupancyStatus status) {
    updateOccupancy(mainId, detailId, type, status, null);
  }

  @Override
  public void updateOccupancy(Long mainId, StockOccupancyType type, StockOccupancyStatus status,
      VirtualWarehouse warehouse) {
    updateOccupancy(mainId, null, type, status, warehouse);
  }

  @Override
  public void updateOccupancy(Long mainId, StockOccupancyType type, VirtualWarehouse warehouse) {
    updateOccupancy(mainId, null, type, null, warehouse);
  }

  @Override
  public void updateOccupancy(Long mainId, StockOccupancyType type, StockOccupancyStatus status) {
    updateOccupancy(mainId, null, type, status, null);
  }


  @Override
  public void updateOccupancy(StockOccupancy record) {
    StockOccupancy eg = new StockOccupancy();
    eg.setMainId(record.getMainId());
    eg.setDetailId(record.getDetailId());
    eg.setStockOccupancyType(record.getStockOccupancyType());
    eg.setSkuId(record.getSkuId());
    eg.setSkuCode(record.getSkuCode());
    eg.setVirtualWarehouseId(record.getVirtualWarehouseId());
    StockOccupancy stockOccupancy = dao.getByExample(eg);
    if (stockOccupancy != null) {
      adjustQuantity(record.getMainId(), record.getDetailId(), record.getStockOccupancyType(),
          record.getQuantity());
    } else {
      initDefaultValue(record);
      insert(record);
    }
  }


  @Override
  public void updateSku(Long mainId, Long detailId, StockOccupancyType type, Long skuId,
      String skuCode, Integer quantity) {
    Assert.notNull(mainId, StockExceptions.STOCK_MAIN_ID_NOT_ALLOW_EMPTY);
    Assert.notNull(detailId, StockExceptions.STOCK_DETAIL_ID_NOT_ALLOW_EMPTY);
    Assert.notNull(type, StockExceptions.STOCK_OCCUPANCY_TYPE_NOT_ALLOW_EMPTY);
    Assert.notNull(skuId, StockExceptions.STOCK_SKU_ID_NOT_ALLOW_EMPTY);
    Assert.notNull(skuCode, StockExceptions.STOCK_SKU_CODE_NOT_ALLOW_EMPTY);
    StockOccupancy occupancy = new StockOccupancy();
    occupancy.setMainId(mainId);
    occupancy.setDetailId(detailId);
    occupancy.setStockOccupancyType(type);
    occupancy.setSkuId(skuId);
    occupancy.setSkuCode(skuCode);
    occupancy.setQuantity(quantity);
    dao.updateQuantity(occupancy);
    LOGGER.debug("修改占用：主ID：{},明细ID：{},类型：{},数量：{}", occupancy.getMainId(), occupancy.getDetailId(),
        occupancy.getStockOccupancyType(), occupancy.getQuantity());

  }


  @Override
  public int batchCreate(Collection<? extends StockOccupancy> collection) {
    collection.forEach(x -> initDefaultValue(x));
    return insertBatch(collection);
  }

  @Override
  public int batchModify(Collection<? extends StockOccupancy> collection) {
    return updateBatch(collection);
  }
}