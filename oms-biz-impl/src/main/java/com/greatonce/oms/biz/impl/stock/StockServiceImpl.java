package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.stock.DispatchStockInfoBO;
import com.greatonce.oms.bo.stock.StockCheckResultBO;
import com.greatonce.oms.bo.stock.StockQueryBO;
import com.greatonce.oms.bo.stock.StockQueryRatioBO;
import com.greatonce.oms.bo.stock.StockRatio;
import com.greatonce.oms.bo.stock.StockSumBO;
import com.greatonce.oms.bo.trade.ManualDispatchDetailBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.dao.stock.StockDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.StockTrackLogger;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Stock <br/> 库存.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class StockServiceImpl extends AbstractService<Stock, StockQuery> implements StockService {

  static final StockTrackLogger TRACK_LOGGER = OmsLoggerFactory.getStockTrackLogger();
  @Autowired
  private StockDao dao;
  @Resource
  private IdGenerator stockIdGenerator;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private MessageExporter messageExporter;

  @Override
  protected QueryDao<Stock, StockQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return stockIdGenerator;
  }


  @Override
  public int create(Stock record) {
    initDefaultValue(record);
    int count = insert(record);
    TRACK_LOGGER
        .log(record.getVirtualWarehouseId(), record.getVirtualWarehouseName(), record.getSkuCode(),
            record.getQuantity());
    return count;
  }


  @Override
  public List<DispatchStockInfoBO> listDispatchStock(Collection<Long> skuIds,
      Collection<Long> virtualWarehouseIds, LocalDateTime paidTime) {
    return dao.listDispatchStock(skuIds, virtualWarehouseIds, paidTime);
  }

  @Override
  public boolean checkExists(Long skuId, Long virtualWarehouseId) {
    Stock eg = new Stock();
    eg.setSkuId(skuId);
    eg.setVirtualWarehouseId(virtualWarehouseId);
    return getByExample(eg) != null;
  }

  @Override
  public int getStockQuantity(Long skuId, Long virtualWarehouseId) {
    StockCheckResultBO stockCheckResultBO = dao.queryStockQuantity(skuId, virtualWarehouseId);
    if (stockCheckResultBO == null) {
      return 0;
    } else {
      return stockCheckResultBO.getQuantity();
    }
  }

  @Override
  public StockQueryRatioBO ratioQuery(Long salesOrderId, Long type) {
    SalesOrder salesOrder = salesOrderService.getByKey(salesOrderId);
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    salesOrder.setContact(
        securityBridge.decrypt(store, salesOrder.getContact(), DataType.NAME));
    salesOrder.setTelephone(securityBridge
        .decrypt(store, salesOrder.getTelephone(), DataType.TELEPHONE));
    salesOrder.setMobile(
        securityBridge.decrypt(store, salesOrder.getMobile(), DataType.MOBILE));
    List<SalesOrderDetail> salesOrderDetails = salesOrderDetailService
        .getSalesOrderDetails(salesOrderId);
    List<Stock> stocks = new ArrayList<>();
    StockQueryRatioBO result = null;
    if (!Assert.isEmpty(salesOrderDetails)) {
      for (SalesOrderDetail salesOrderDetail : salesOrderDetails) {
        Stock stock = new Stock();
        stock.setSkuId(salesOrderDetail.getSkuId());
        stock.setQuantity(salesOrderDetail.getQuantity());
        stocks.add(stock);
      }
    }
    if (!Assert.isEmpty(stocks)) {
      result = new StockQueryRatioBO();
      List<StockRatio> list = dao.queryStockRatio(stocks, type);
      Map<Long, List<StockRatio>> map = new HashMap<>();
      //所有不重复的
      Map<Long, Long> warehouseIdMap = new HashMap<>();
      if (!Assert.isEmpty(list)) {
        for (StockRatio bo : list) {
          List<StockRatio> ratioBOList = new ArrayList<>();
          warehouseIdMap.put(bo.getWarehouseId(), bo.getWarehouseId());
          for (StockRatio bo2 : list) {
            if (bo.getWarehouseId().equals(bo2.getWarehouseId())) {
              ratioBOList.add(bo2);
              map.put(bo.getWarehouseId(), ratioBOList);
            }
          }
        }
      }
      List<StockRatio> resultList = null;
      if (!Assert.isEmpty(warehouseIdMap)) {
        Set<Long> set = warehouseIdMap.keySet();
        Iterator<Long> iterator = set.iterator();
        resultList = new ArrayList<>();
        while (iterator.hasNext()) {
          Long warehouseId = iterator.next();
          //每个门店，对应商品的数量信息
          List<StockRatio> boList = map.get(warehouseId);
          if (boList.size() >= stocks.size()) {
            int i = 0;
            for (StockRatio rbo : boList) {
              for (Stock stock : stocks) {
                if (rbo.getSkuId().equals(stock.getSkuId()) && rbo.getQuantity() >= stock
                    .getQuantity()) {
                  i++;
                }
              }
            }
            if (i == stocks.size()) {
              //说明门店满足商品信息
              StockRatio stockRatio = new StockRatio();
              double ratio = 0.00;
              Integer qty = 0;
              for (StockRatio rbo : boList) {
                //计算动销比
                ratio += rbo.getRatio() == null ? 0D : rbo.getRatio();
                qty += rbo.getQuantity();
                stockRatio.setWarehouseId(rbo.getWarehouseId());
                stockRatio.setWarehouseName(rbo.getWarehouseName());
                stockRatio.setVirtualWarehouseId(rbo.getVirtualWarehouseId());
                stockRatio.setVirtualWarehouseName(rbo.getVirtualWarehouseName());
              }
              stockRatio.setQuantity(qty);
              BigDecimal b = new BigDecimal(ratio / 100 / boList.size());
              double f1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
              stockRatio.setRatio(f1);
              stockRatio.setStockRatios(boList);
              resultList.add(stockRatio);
            }
          }
        }
      }

      List<ManualDispatchDetailBO> details = new ArrayList<>(salesOrderDetails.size());
      for (SalesOrderDetail sod : salesOrderDetails) {
        ManualDispatchDetailBO detailBO = new ManualDispatchDetailBO();
        detailBO.setSalesOrderId(sod.getSalesOrderId());
        detailBO.setActualAmount(sod.getActualAmount());
        detailBO.setDiscountAmount(sod.getDiscountAmount());
        detailBO.setDistributionAmount(sod.getDistributionAmount());
        detailBO.setSellingAmount(sod.getSellingAmount());
        detailBO.setSettlementAmount(sod.getSettlementAmount());
        detailBO.setQuantity(sod.getQuantity());
        detailBO.setVirtualWarehouseId(null);
        detailBO.setVirtualWarehouseName(null);
        detailBO.setDistributionPrice(sod.getDistributionPrice());
        detailBO.setSellingPrice(sod.getSellingPrice());
        detailBO.setActualSellingPrice(sod.getActualSellingPrice());
        detailBO.setSettlementPrice(sod.getSettlementPrice());
        detailBO.setProductId(sod.getProductId());
        detailBO.setProductCode(sod.getProductCode());
        detailBO.setProductName(sod.getProductName());
        detailBO.setSkuId(sod.getSkuId());
        detailBO.setSkuCode(sod.getSkuCode());
        detailBO.setSkuName(sod.getSkuName());
        detailBO.setSalesOrderId(sod.getSalesOrderId());
        detailBO.setSalesOrderCode(salesOrder.getSalesOrderCode());
        detailBO.setSalesOrderDetailId(sod.getSalesOrderDetailId());
        detailBO.setTradeId(salesOrder.getTradeId());
        details.add(detailBO);
      }
      result.setSalesOrderDetails(details);
      result.setSalesOrder(salesOrder);
      result.setStockRatios(resultList);
    }
    return result;
  }

  @Override
  public void exportStock(String fileName, StockQuery stockQuery) {
    ExcelHeaderCollection<StockQueryBO> headers = new ExcelHeaderCollection<>();
    headers.add("实体仓名称", StockQueryBO::getWarehouseName);
    headers.add("虚拟仓名称", StockQueryBO::getVirtualWarehouseName);
    headers.add("商品编码", StockQueryBO::getProductCode);
    headers.add("规格编码", StockQueryBO::getSkuCode);
    headers.add("规格名称", StockQueryBO::getSkuName);
    headers.add("库存数", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("订单占用", x -> FormatUtil.formatInteger(x.getSalesOrderLockQuantity()));
    headers.add("配货占用", x -> FormatUtil.formatInteger(x.getDispatchOrderLockQuantity()));
    headers.add("唯品占用", x -> FormatUtil.formatInteger(x.getVipLockQuantity()));
    headers.add("其他占用", x -> FormatUtil.formatInteger(x.getOtherLockQuantity()));
    headers.add("可销数", x -> FormatUtil.formatInteger(x.getSaleable()));
    headers.add("可用数", x -> FormatUtil.formatInteger(x.getAvailable()));
    headers.add("在途数", x -> FormatUtil.formatInteger(x.getTransitQuantity()));
    messageExporter.exportExcel(this::stockQuery, headers, stockQuery, fileName);
  }

  @Override
  public List<StockSumBO> listSkuStockInfo(Long skuId, List<Long> virtualWarehouseIds) {
    return dao.listSkuStockInfo(skuId, virtualWarehouseIds);
  }

  @Override
  public PageList<StockQueryBO> stockQuery(StockQuery stockQuery, int pageNo, int pageSize) {
    validatePageParameter(pageNo, pageSize);
    return dao.queryStock(stockQuery, pageNo, pageSize);
  }

  @Override
  public int adjustQuantity(String orderCode, OrderType orderType, Long skuId, String skuCode,
      VirtualWarehouse virtualWarehouse, int quantity) {
    return adjustQuantity(orderCode, orderType, skuId, skuCode, virtualWarehouse.getWarehouseId(),
        virtualWarehouse.getWarehouseName(), virtualWarehouse.getVirtualWarehouseId(),
        virtualWarehouse.getVirtualWarehouseName(), quantity);
  }

  @Override
  public int adjustQuantity(String orderCode, OrderType orderType, Long skuId, String skuCode,
      Long warehouseId, String warehouseName, Long virtualWarehouseId, String virtualWarehouseName,
      int quantity) {
    int count = dao.adjustQuantity(skuId, virtualWarehouseId, quantity);
    if (count == 0) {
      create(DomainUtil
          .createStock(warehouseId, warehouseName, virtualWarehouseId, virtualWarehouseName,
              skuId, skuCode, quantity));
    } else {
      TRACK_LOGGER
          .log(orderCode, orderType, virtualWarehouseId, virtualWarehouseName, skuCode, quantity);
    }
    return count;
  }

  @Override
  public int coverQuantity(Long skuId, Long virtualWarehouseId, int quantity) {
    return dao.coverQuantity(skuId, virtualWarehouseId, quantity);
  }

  @Override
  public StockCheckResultBO getStockCheckResult(Long skuId, Long virtualWarehouseId,
      LocalDateTime sortTime) {
    return dao.getStockCheckResult(skuId, virtualWarehouseId, sortTime);
  }

  @Override
  public int adjustTransitQuantity(String orderCode, OrderType orderType, Long skuId,
      String skuCode, Long warehouseId, String warehouseName, Long virtualWarehouseId,
      String virtualWarehouseName, int quantity) {
    int count = dao.adjustTransitQuantity(skuId, virtualWarehouseId, quantity);
    if (count == 0) {
      create(DomainUtil
          .createStock(warehouseId, warehouseName, virtualWarehouseId, virtualWarehouseName,
              skuId, skuCode, 0, quantity, "在途初始化库存"));
    } else {
      TRACK_LOGGER
          .log(orderCode, orderType, virtualWarehouseId, virtualWarehouseName, skuCode, quantity);
    }
    return count;
  }

  @Override
  public int transitIn(String orderCode, OrderType orderType, Long skuId, String skuCode,
      Long warehouseId, String warehouseName, Long virtualWarehouseId, String virtualWarehouseName,
      int quantity) {
    int count = dao.transitIn(skuId, virtualWarehouseId, quantity);
    TRACK_LOGGER
        .log(orderCode, orderType, virtualWarehouseId, virtualWarehouseName, skuCode, quantity,
            "在途入库");
    return count;
  }

  @Override
  public int transitIn(String orderCode, OrderType orderType, Long skuId, String skuCode,
      VirtualWarehouse virtualWarehouse, int quantity) {
    return transitIn(orderCode, orderType, skuId, skuCode, virtualWarehouse.getWarehouseId(),
        virtualWarehouse.getWarehouseName(), virtualWarehouse.getVirtualWarehouseId(),
        virtualWarehouse.getVirtualWarehouseName(), quantity);
  }


  @Override
  public int batchCreate(Collection<? extends Stock> collection) {
    collection.forEach(this::initDefaultValue);
    int count = insertBatch(collection);
    collection.forEach(stock -> TRACK_LOGGER
        .log(stock.getVirtualWarehouseId(), stock.getVirtualWarehouseName(), stock.getSkuCode(),
            stock.getQuantity()));
    return count;
  }

  @Override
  public int batchModify(Collection<? extends Stock> collection) {
    return updateBatch(collection);
  }
}