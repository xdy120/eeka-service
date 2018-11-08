package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.stock.StockInOrderDetailService;
import com.greatonce.oms.biz.stock.StockInOrderService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockInOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO.WmsAutoInDetail;
import com.greatonce.oms.bridge.wms.StockInOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.StockInOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockInOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse.WmsStockInOrderStatus;
import com.greatonce.oms.dao.stock.StockInOrderDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.stock.StockInOrderStatus;
import com.greatonce.oms.domain.enums.stock.StockType;
import com.greatonce.oms.domain.stock.StockInOrder;
import com.greatonce.oms.domain.stock.StockInOrderDetail;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.stock.StockInOrderDetailQuery;
import com.greatonce.oms.query.stock.StockInOrderQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 入库单. StockInOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class StockInOrderServiceImpl extends
    AbstractVersionService<StockInOrder, StockInOrderQuery> implements StockInOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK_IN);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockInOrderServiceImpl.class);
  @Autowired
  private StockInOrderDao dao;
  @Autowired
  private StockInOrderDetailService stockInOrderDetailService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Resource
  private IdGenerator stockInIdGenerator;
  @Resource
  private CodeGenerator stockInCodeGenerator;
  @Autowired
  private StockService stockService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;

  @Override
  protected QueryDao<StockInOrder, StockInOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.stockInIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(StockInOrder record) {
    super.initDefaultValue(record);
    record.setVersion(0);
    record.setStatus(StockInOrderStatus.CREATED);
    record.setInStatus(InStatus.NO_IN);
    record.setCreator(BizContext.getNickname());
    record.setStockInOrderCode(stockInCodeGenerator.next());
    record.getDetails().forEach(detail -> {
      detail.setStockInOrderId(record.getStockInOrderId());
    });
  }

  @Override
  public int create(StockInOrder record) {
    initDefaultValue(record);
    try {
      int result = getTransactionTemplate().executeWithResult(() -> {
        stockInOrderDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getStockInOrderId(), BizLogger.ADD, "入库单保存");
      return result;
    } catch (Exception e) {
      LOGGER.error("入库单保存失败，入库单：{}", JsonUtil.toJson(record));
      LOGGER.error("入库单保存失败，堆栈信息：", e);
      throw new OmsException("入库单保存失败！");
    }
  }

  @Override
  public void audit(StockInOrder record, VersionBO bo) {
    if (record.getStatus() != StockInOrderStatus.CREATED) {
      throw SysExceptions.STATUS_ERROR;
    }
    record.setStatus(StockInOrderStatus.AUDITED);
    record.setAuditor(BizContext.getNickname());
    record.setAuditedTime(LocalDateTime.now());
    record.setVersion(bo.getVersion());
    update(record);
    getMqProducer().send(
        new GeneralMessage(OmsModule.STOCK_IN, record.getStockInOrderId(), EventType.AUDITED));
    BIZ_LOGGER.log(record.getStockInOrderId(), BizLogger.AUDIT, "入库单审核");
  }

  @Override
  public void cancel(StockInOrder stockInOrder, StockInOrderCancelBO bo) {
    if (InStatus.NO_IN != stockInOrder.getInStatus()) {
      throw new OmsException("入库单已入库不能取消");
    }
    if (stockInOrder.getStatus() == StockInOrderStatus.CANCEL) {
      return;
    }
    if (stockInOrder.getStatus() == StockInOrderStatus.CREATED) {
      cancelOms(stockInOrder, bo);
      return;
    }
    if (stockInOrder.getStatus() == StockInOrderStatus.AUDITED) {
      cancelOms(stockInOrder, bo);
      return;
    }
    if (StockInOrderStatus.NOTICED == stockInOrder.getStatus()
        || StockInOrderStatus.NOTICE_FAILED == stockInOrder.getStatus()) {
      Warehouse warehouse = warehouseService.getByKey(stockInOrder.getWarehouseId());
      StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
          .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
      if (StockInOrderStatus.NOTICE_FAILED == stockInOrder.getStatus()) {
        StockInOrderQueryRequest request = new StockInOrderQueryRequest(warehouse);
        request.setOmsCode(stockInOrder.getStockInOrderCode());
        request.setOrderType(OrderType.IN_ORDER);
        final StockInOrderQueryResponse response = stockInOrderBridge
            .queryOrder(request);
        if (!response.isExists() || response.getStatus() == WmsStockInOrderStatus.CANCELED) {
          cancelOms(stockInOrder, bo);
          return;
        }
      }

      StockInOrderCancelRequest request = new StockInOrderCancelRequest(warehouse);
      request.setOmsCode(stockInOrder.getStockInOrderCode());
      request.setOrderType(OrderType.IN_ORDER);
      StockInOrderCancelResponse response = stockInOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(stockInOrder, bo);
      } else {
        BIZ_LOGGER.log(stockInOrder.getStockInOrderId(), BizLogger.CANCEL, "WMS取消失败：{}",
            response.getMessage());
        throw new OmsException(response.getMessage());
      }
    }
  }

  private void cancelOms(StockInOrder stockInOrder, StockInOrderCancelBO bo) {
    stockInOrder.setStatus(StockInOrderStatus.CANCEL);
    stockInOrder.setVersion(bo.getVersion());
    try {
      getTransactionTemplate().execute(() -> {
        update(stockInOrder);
      });
      BIZ_LOGGER.log(stockInOrder.getStockInOrderId(), BizLogger.CANCEL, "原因:{}", bo.getReason());
    } catch (Exception e) {
      LOGGER.error("出库单取消失败,借出单信息: {}", JsonUtil.toJson(stockInOrder));
      LOGGER.error("出库单取消失败,堆栈信息: ", e);
      throw new OmsException("出库单取消失败");
    }
  }


  @Override
  public void noticeWms(StockInOrder stockInOrder, VersionBO bo) {
    if (stockInOrder.getStatus() != StockInOrderStatus.AUDITED
        && stockInOrder.getStatus() != StockInOrderStatus.NOTICE_FAILED) {
      throw new OmsException("只有已审核或推送失败的入库单才能推送给仓库");
    }
    Warehouse warehouse = warehouseService.getByKey(stockInOrder.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");

    StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
        .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
    StockInOrderCreateRequest request = new StockInOrderCreateRequest(warehouse);
    StockInOrderDetailQuery stockInOrderDetailQuery = new StockInOrderDetailQuery();
    stockInOrderDetailQuery.setStockInOrderId(stockInOrder.getStockInOrderId());
    List<StockInOrderDetail> stockInOrderDetailList = stockInOrderDetailService
        .list(stockInOrderDetailQuery);

    request.setOmsCode(stockInOrder.getStockInOrderCode());
    request.setOrderType(OrderType.IN_ORDER);
    request.setCreatedTime(stockInOrder.getCreatedTime());

    //仓库信息
    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setSenderArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());
    //拼接请求明细数据
    List<StockInOrderCreateRequest.StockInOrderDetail> stockInOrderDetails = new ArrayList<>();
    for (StockInOrderDetail stockInOrderDetail : stockInOrderDetailList) {
      StockInOrderCreateRequest.StockInOrderDetail detail = new StockInOrderCreateRequest.StockInOrderDetail();
      detail.setQuantity(stockInOrderDetail.getNoticeQuantity());
      detail.setPurchasePrice(stockInOrderDetail.getPrice());
      detail.setProductCode(stockInOrderDetail.getProductCode());
      detail.setProductName(stockInOrderDetail.getProductName());
      detail.setSkuCode(stockInOrderDetail.getSkuCode());
      detail.setWmsSkuId("");
      stockInOrderDetails.add(detail);
    }
    request.setDetails(stockInOrderDetails);

    StockInOrderCreateResponse response = stockInOrderBridge.createOrder(request);
    StockInOrder updateInfo = new StockInOrder();
    updateInfo.setStockInOrderId(stockInOrder.getStockInOrderId());
    updateInfo.setVersion(bo.getVersion());
    if (response.isSuccess()) {
      updateInfo.setStatus(StockInOrderStatus.NOTICED);
    } else {
      updateInfo.setStatus(StockInOrderStatus.NOTICE_FAILED);
    }
    modify(updateInfo);
    BIZ_LOGGER
        .log(stockInOrder.getStockInOrderId(), BizLogger.NOTICE_WMS,
            response.isSuccess() ? "成功" : response.getMessage());
  }

  @Override
  public StockInOrder getByCode(String orderCode) {
    StockInOrder eg = new StockInOrder();
    eg.setStockInOrderCode(orderCode);
    return getByExample(eg);
  }

  /**
   * OMS接收WMS回传的入库信息,修改入库单主单入库状态，修改入库单明细正次品数量
   *
   * @param stockInOrder OMS入库单
   * @param wmsAutoInBO WMS入库信息
   */
  @Override
  public void wmsAutoIn(StockInOrder stockInOrder, WmsAutoInBO wmsAutoInBO) {
    if (StockInOrderStatus.CANCEL == stockInOrder.getStatus()) {
      throw new OmsException("单据已取消");
    }

    if (stockInOrder.getInStatus() == InStatus.ALL_IN) {
      LOGGER.info("单据已全部入库，不再处理，WMS单号：{}", wmsAutoInBO.getWmsOrderCode());
      return;
    }

    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(stockInOrder.getVirtualWarehouseId());
    VirtualWarehouse substandardWarehouse = virtualWarehouseService
        .getSubstandardWarehouse(stockInOrder.getWarehouseId());
    List<StockInOrderDetail> details = stockInOrderDetailService
        .listDetails(stockInOrder.getStockInOrderId());

    Map<String, WmsAutoInDetail> qualifiedInDetailMap = new HashMap<>(
        wmsAutoInBO.getDetails().size());
    Map<String, WmsAutoInDetail> defectiveInDetailMap = new HashMap<>();
    for (WmsAutoInDetail inDetail : wmsAutoInBO.getDetails()) {
      if (inDetail.getStockType() == StockType.QUALIFIED) {
        if (!qualifiedInDetailMap.containsKey(inDetail.getSkuCode())) {
          qualifiedInDetailMap.put(inDetail.getSkuCode(), inDetail);
        }
      } else {
        if (!defectiveInDetailMap.containsKey(inDetail.getSkuCode())) {
          defectiveInDetailMap.put(inDetail.getSkuCode(), inDetail);
        }
      }
    }
    boolean match;
    int totalNoticeQuantity = 0;
    int totalInQuantity = 0;
    List<StockInOrderDetail> updateDetails = new ArrayList<>(details.size());
    for (StockInOrderDetail detail : details) {
      match = false;
      totalNoticeQuantity += detail.getNoticeQuantity();
      WmsAutoInDetail inDetail = qualifiedInDetailMap.get(detail.getSkuCode());
      if (inDetail != null && inDetail.getPendingQuantity() > 0) {
        inDetail.setSkuId(detail.getSkuId());
        //剩余入库数量=通知数量-正品入库数-次品入库数
        int balance =
            detail.getNoticeQuantity() - detail.getInQuantity() - detail.getInSubstandardQuantity();
        if (inDetail.getPendingQuantity() > balance) {
          detail.setInQuantity(detail.getInQuantity() + balance);
          inDetail.setPendingQuantity(inDetail.getPendingQuantity() - balance);
        } else {
          detail.setInQuantity(detail.getInQuantity() + inDetail.getPendingQuantity());
          inDetail.setPendingQuantity(0);
        }
        match = true;
      }
      //正品入不够从次品入
      if (inDetail == null || detail.getNoticeQuantity() > (detail.getInQuantity())) {
        inDetail = defectiveInDetailMap.get(detail.getSkuCode());
        if (inDetail != null && inDetail.getPendingQuantity() > 0) {
          inDetail.setSkuId(detail.getSkuId());
          int balance = detail.getNoticeQuantity() - detail.getInQuantity() - detail
              .getInSubstandardQuantity();
          if (inDetail.getPendingQuantity() > balance) {
            detail.setInSubstandardQuantity(detail.getInSubstandardQuantity() + balance);
            inDetail.setPendingQuantity(inDetail.getPendingQuantity() - balance);
          } else {
            detail.setInSubstandardQuantity(
                detail.getInSubstandardQuantity() + inDetail.getPendingQuantity());
            inDetail.setPendingQuantity(0);
          }
          match = true;
        }
      }
      if (match) {
        totalInQuantity += detail.getInQuantity() + detail.getInSubstandardQuantity();
        updateDetails.add(detail);
      }
    }

    stockInOrder.setOuterCode(wmsAutoInBO.getWmsOrderCode());
    stockInOrder.setVersion(wmsAutoInBO.getVersion());
    stockInOrder.setLastInTime(wmsAutoInBO.getInTime());
    stockInOrder
        .setInStatus(totalInQuantity < totalNoticeQuantity ? InStatus.PART_IN : InStatus.ALL_IN);
    if (stockInOrder.getStatus() == StockInOrderStatus.NOTICE_FAILED){
      stockInOrder.setStatus(StockInOrderStatus.NOTICED);
    }
    try {
      getTransactionTemplate().execute(() -> {
        stockInOrderDetailService.batchModify(updateDetails);
        for (WmsAutoInDetail inDetail : wmsAutoInBO.getDetails()) {
          if (inDetail.getStockType() == StockType.QUALIFIED) {
            stockService
                .adjustQuantity(stockInOrder.getStockInOrderCode(),
                    OrderType.IN_ORDER,
                    inDetail.getSkuId(), inDetail.getSkuCode(), virtualWarehouse,
                    inDetail.getInQuantity());
          } else {
            Assert.notNull(substandardWarehouse, "系统未配置次品仓，不接收次品库存");
            stockService
                .adjustQuantity(stockInOrder.getStockInOrderCode(),
                    OrderType.IN_ORDER,
                    inDetail.getSkuId(), inDetail.getSkuCode(), substandardWarehouse,
                    inDetail.getPendingQuantity());
          }
        }
        update(stockInOrder);
      });
      BIZ_LOGGER.log(stockInOrder.getStockInOrderId(), BizLogger.ENTRY, "入库单入库");
      updateDetails.forEach(x -> {
        getMqProducer().send(
            new StockChangedMessage(stockInOrder.getStockInOrderCode(), x.getSkuId(),
                BizContext.getNickname(), "入库单入库"));
      });
    } catch (Exception e) {
      LOGGER.error("入库单入库异常,入库单信息: {}", JsonUtil.toJson(stockInOrder));
      LOGGER.error("入库单入库异常,堆栈信息: ", e);
      throw new OmsException("入库单入库异常");
    }
  }

}