package com.greatonce.oms.api.qimen.impl;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.QimenInventoryReportStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsInventoryReportRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsInventoryReportRequest.OmsInventoryReportItem;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.StockSyncLogger;
import com.qimen.api.response.InventoryReportResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 库存盘点接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 6/11/2018
 */
@Component
public class QimenInventoryReportImpl implements QimenInventoryReportStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(QimenInventoryReportImpl.class);
  private static final StockSyncLogger SYNC_LOGGER = OmsLoggerFactory.getStockSyncLogger();
  private static final String STOCK_SYNC_HISTORY = "INVENTORY_REPORT_HISTORY";
  @Autowired
  private StockService stockService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private ProductSkuService productSkuService;
  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private ManualTransactionTemplate transactionTemplate;
  @Autowired
  private MqProducer mqProducer;
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }

  static class InventoryReportBatch {

    Warehouse warehouse;
    VirtualWarehouse shareWarehouse;
    List<VirtualWarehouse> exclusiveWarehouses;
    List<InventoryReportBatchItem> items;
  }

  static class InventoryReportBatchItem {

    OmsInventoryReportItem item;
    ProductSku sku;

    public InventoryReportBatchItem(OmsInventoryReportItem item, ProductSku sku) {
      this.item = item;
      this.sku = sku;
    }
  }

  /**
   * 奇门库存异动确认接口. 如果是增加库存，直接加到实体仓对应的共享仓 如果是减少库存，先扣减共享仓，共享仓不足的扣减独立仓（随机扣）. 注意点：增加库存时需要判断是否有库存记录，没有需要新增
   */
  @Override
  public InventoryReportResponse report(@RequestBody OmsInventoryReportRequest request) {
    long batchId = apiIdGenerator.next();
    final String outBizCode = request.getOrderCode();
    synchronized (("INVENTORY_REPORT_LOCK_" + outBizCode).intern()) {
      if (!Assert.isEmpty(outBizCode)) {

        final Boolean processed = redisTemplate.opsForSet()
            .isMember(STOCK_SYNC_HISTORY, outBizCode);
        if (Assert.isTrue(processed)) {
          return QimenCustomResponseUtil
              .resultSuccessResponse(new InventoryReportResponse(), outBizCode);
        }
      }

      final Map<String, List<OmsInventoryReportItem>> itemMap = CollectionUtil
          .listToMapList(request.getItems(), OmsInventoryReportItem::getWarehouseCode, 2);
      List<InventoryReportBatch> batches = new ArrayList<>(itemMap.size());
      itemMap.forEach((k, v) -> {
        Warehouse warehouse = warehouseService.getEffectiveByCode(k);
        Assert.notNull(warehouse, "找到仓库：" + k);
        VirtualWarehouse shareWarehouse = virtualWarehouseService
            .getShareWarehouse(warehouse.getWarehouseId());
        Assert.notNull(shareWarehouse, "找到仓库：" + k + "下的共享仓");
        List<InventoryReportBatchItem> items = new ArrayList<>(v.size());
        ProductSku sku;
        InventoryReportBatchItem batchItem;
        for (OmsInventoryReportItem item : v) {
          sku = productSkuService.getEffectiveByCode(item.getItemCode());
          if (sku == null) {
            LOGGER.error("库存同步未找到商品：{},{}", item.getWarehouseCode(), item.getItemCode());
            throw new OmsException("库存同步未找到商品:" + item.getItemCode());
          }
          items.add(new InventoryReportBatchItem(item, sku));
        }

        InventoryReportBatch batch = new InventoryReportBatch();
        batch.warehouse = warehouse;
        batch.shareWarehouse = shareWarehouse;
        batch.exclusiveWarehouses = virtualWarehouseService
            .listExclusive(warehouse.getWarehouseId());
        batch.items = items;
        batches.add(batch);
      });

      transactionTemplate.execute(() -> {
        for (InventoryReportBatch batch : batches) {
          for (InventoryReportBatchItem item : batch.items) {
            int quantityValue = item.item.getQuantity().intValue();
            if (quantityValue > 0) {
              stockService
                  .adjustQuantity(outBizCode, OrderType.OTHER, item.sku.getSkuId(),
                      item.sku.getSkuCode(),
                      batch.shareWarehouse, quantityValue);
              LOGGER.debug("{}增加共享仓【{}】库存：{},{}", batchId,
                  batch.shareWarehouse.getVirtualWarehouseName(),
                  item.sku.getSkuCode(), quantityValue);
            } else {
              int stock = stockService
                  .getStockQuantity(item.sku.getSkuId(),
                      batch.shareWarehouse.getVirtualWarehouseId());
              int absValue = Math.abs(quantityValue);
              if (stock >= absValue) {
                stockService
                    .adjustQuantity(outBizCode, OrderType.OTHER, item.sku.getSkuId(),
                        item.sku.getSkuCode(),
                        batch.shareWarehouse, quantityValue);
                LOGGER
                    .debug("{}扣减共享仓【{}】库存：{},{}", batchId,
                        batch.shareWarehouse.getVirtualWarehouseName(),
                        item.sku.getSkuCode(), quantityValue);
              } else {
                stockService
                    .adjustQuantity(outBizCode, OrderType.OTHER, item.sku.getSkuId(),
                        item.sku.getSkuCode(),
                        batch.shareWarehouse, -stock);
                LOGGER
                    .debug("{}扣减共享仓【{}】库存：{},{}", batchId,
                        batch.shareWarehouse.getVirtualWarehouseName(),
                        item.sku.getSkuCode(), -stock);
                int residueValue = absValue - stock;
                if (!Assert.isEmpty(batch.exclusiveWarehouses)) {
                  for (VirtualWarehouse exclusiveWarehouse : batch.exclusiveWarehouses) {
                    stock = stockService
                        .getStockQuantity(item.sku.getSkuId(),
                            exclusiveWarehouse.getVirtualWarehouseId());
                    if (stock >= residueValue) {
                      stockService
                          .adjustQuantity(outBizCode, OrderType.OTHER, item.sku.getSkuId(),
                              item.sku.getSkuCode(),
                              exclusiveWarehouse, -residueValue);
                      LOGGER.debug("{}扣减独立仓【{}】库存：{},{}", batchId,
                          batch.shareWarehouse.getVirtualWarehouseName(), item.sku.getSkuCode(),
                          -residueValue);
                      residueValue = 0;
                      break;
                    } else {
                      stockService
                          .adjustQuantity(outBizCode, OrderType.OTHER, item.sku.getSkuId(),
                              item.sku.getSkuCode(),
                              exclusiveWarehouse, -stock);
                      LOGGER.debug("{}扣减独立仓【{}】库存：{},{}", batchId,
                          batch.shareWarehouse.getVirtualWarehouseName(), item.sku.getSkuCode(),
                          -stock);
                      residueValue -= stock;
                    }
                  }
                  if (residueValue > 0) {
                    throw new OmsException(item.item.getItemCode() + "调出库存超过现有库存");
                  }
                } else {
                  throw new OmsException(item.item.getItemCode() + "调出库存超过现有共享仓库存");
                }
              }
            }
          }
        }
        redisTemplate.opsForSet().add(STOCK_SYNC_HISTORY, outBizCode);
      });
      for (InventoryReportBatch batch : batches) {
        for (InventoryReportBatchItem item : batch.items) {
          mqProducer.send(
              new StockChangedMessage(outBizCode, item.sku.getSkuId(), BizContext.getNickname(),
                  "外部系统盘点"));
          SYNC_LOGGER.log(batchId, batch.warehouse, item.item.getItemCode(),
              item.item.getQuantity().intValue(), outBizCode);
        }
      }
    }
    return QimenCustomResponseUtil
        .resultSuccessResponse(new InventoryReportResponse(), request.getOrderCode());
  }
}
