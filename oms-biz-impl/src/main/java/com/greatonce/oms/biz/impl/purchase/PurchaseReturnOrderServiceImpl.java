package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.purchase.PurchaseReturnOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutDetail;
import com.greatonce.oms.bridge.wms.ReturnOrderBridge;
import com.greatonce.oms.bridge.wms.StockOutOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.dao.purchase.PurchaseReturnOrderDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.purchase.PurchaseReturnStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrderDetail;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderDetailQuery;
import com.greatonce.oms.query.purchase.PurchaseReturnOrderQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 采购退货单. PurchaseReturnOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class PurchaseReturnOrderServiceImpl extends
    AbstractVersionService<PurchaseReturnOrder, PurchaseReturnOrderQuery> implements
    PurchaseReturnOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.PURCHASE_RETURN);
  private static final Logger LOGGER = LoggerFactory
      .getLogger(PurchaseReturnOrderServiceImpl.class);

  @Autowired
  private PurchaseReturnOrderDao dao;
  @Resource
  private IdGenerator purchaseReturnOrderIdGenerator;
  @Resource
  private CodeGenerator purchaseReturnOrderCodeGenerator;
  @Resource
  private PurchaseReturnOrderDetailService purchaseReturnOrderDetailService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private StockService stockService;


  @Override
  protected QueryDao<PurchaseReturnOrder, PurchaseReturnOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return purchaseReturnOrderIdGenerator;
  }

  @Override
  protected void initDefaultValue(PurchaseReturnOrder record) {
    super.initDefaultValue(record);
    record.setVersion(0);
    record.setStatus(PurchaseReturnStatus.CREATED);
    record.setPurchaseReturnOrderCode(purchaseReturnOrderCodeGenerator.next());
    record.setOutStatus(OutStatus.NO_OUT);
    record.setCreator(BizContext.getNickname());
    record.getDetails()
        .forEach(item -> item.setPurchaseReturnOrderId(record.getPurchaseReturnOrderId()));
  }

  @Override
  public int create(PurchaseReturnOrder record) {
    initDefaultValue(record);
    try {
      int result = getTransactionTemplate().executeWithResult(() -> {
        purchaseReturnOrderDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getPurchaseReturnOrderId(), BizLogger.ADD);
      return result;
    } catch (Exception e) {
      LOGGER.error("采购退货单保存失败，采购退货单：{}", JsonUtil.toJson(record));
      LOGGER.error("采购退货单保存失败，堆栈信息：", e);
      throw new OmsException("采购退货单保存失败！");
    }

  }

  /**
   * 用通知数量映射可用数 如果占用数量大于0 加占用 从配置中取全局出库单配置.
   *
   * @param purchaseReturnOrder 采购退货单
   * @param bo 版本号
   */
  @Override
  public void audit(PurchaseReturnOrder purchaseReturnOrder, VersionBO bo) {
    if (PurchaseReturnStatus.CREATED == purchaseReturnOrder.getStatus()) {
      StockSetting stockSetting = settingService.getInventorySetting();
      List<PurchaseReturnOrderDetail> outDetails = null;
      if (stockSetting.getDeliveryAllocationType() == StockSetting.AllocationType.AVAILABLE_BINS) {
        outDetails = purchaseReturnOrderDetailService
            .listAvailable(purchaseReturnOrder.getPurchaseReturnOrderId());
      } else if (stockSetting.getVirtualAllocationType() == StockSetting.AllocationType.SOLD_OUT) {
        outDetails = purchaseReturnOrderDetailService
            .listSaleable(purchaseReturnOrder.getPurchaseReturnOrderId());
      }
      List<StockOccupancy> occupancies = new ArrayList<>(outDetails.size());
      try {
        for (PurchaseReturnOrderDetail detail : outDetails) {
          if (detail.getNoticeQuantity() > 0) {
            int qty = Math.min(detail.getNoticeQuantity(), detail.getPlanQuantity());
            detail.setNoticeQuantity(qty);
            occupancies.add(DomainUtil
                .createStockOccupancy(purchaseReturnOrder.getWarehouseId(),
                    purchaseReturnOrder.getWarehouseName(),
                    purchaseReturnOrder.getVirtualWarehouseId(),
                    purchaseReturnOrder.getVirtualWarehouseName(),
                    StockOccupancyType.PURCHASE_RETURN, detail.getSkuId(),
                    detail.getSkuCode(), qty,
                    purchaseReturnOrder.getPurchaseReturnOrderId(),
                    detail.getPurchaseReturnOrderDetailId(),
                    StockOccupancyStatus.LOCK));
          } else {
            detail.setNoticeQuantity(0);
          }
        }
        purchaseReturnOrder.setStatus(PurchaseReturnStatus.AUDITED);
        purchaseReturnOrder.setAuditedTime(LocalDateTime.now());
        purchaseReturnOrder.setVersion(bo.getVersion());
        purchaseReturnOrder.setAuditor(BizContext.getNickname());
        purchaseReturnOrder.setDetails(outDetails);
        getTransactionTemplate().execute(() -> {
          purchaseReturnOrderDetailService.batchModify(purchaseReturnOrder.getDetails());
          stockOccupancyService.batchCreate(occupancies);
          update(purchaseReturnOrder);
        });
        getMqProducer().send(
            new GeneralMessage(OmsModule.PURCHASE_RETURN,
                purchaseReturnOrder.getPurchaseReturnOrderId(),
                EventType.AUDITED));
        occupancies.forEach(occupancy -> getMqProducer()
            .send(new StockChangedMessage(purchaseReturnOrder.getPurchaseReturnOrderCode(),
                occupancy.getSkuId(), BizContext.getNickname(), "采购退货审核")));
        BIZ_LOGGER.log(purchaseReturnOrder.getPurchaseReturnOrderId(), BizLogger.AUDIT);
      } catch (Exception e) {
        LOGGER.error("采购退货单审核失败，采购退货单：{}", JsonUtil.toJson(purchaseReturnOrder));
        LOGGER.error("采购退货单审核失败，堆栈信息：", e);
        throw new OmsException("采购退货单审核失败！");
      }
    } else {
      throw SysExceptions.STATUS_ERROR;
    }
  }

  @Override
  public void finish(PurchaseReturnOrder record, VersionBO bo) {
    if (record.getOutStatus() != OutStatus.PART_OUT) {
      throw new OmsException("只有部分出库的采购退货单才能完结");
    }
    record.setStatus(PurchaseReturnStatus.FINISH);
    record.setVersion(bo.getVersion());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(record.getPurchaseReturnOrderId(), StockOccupancyType.PURCHASE_RETURN);
        super.update(record);
      });
      BIZ_LOGGER.log(record.getPurchaseOrderId(), BizLogger.INVALID);
    } catch (Exception e) {
      LOGGER.error("采购退货单完成失败，采购退货单：{}", JsonUtil.toJson(record));
      LOGGER.error("采购退货单完成失败，堆栈信息：", e);
      throw new OmsException("采购退货单完成失败！");
    }
  }

  @Override
  public void noticeWms(PurchaseReturnOrder purchaseReturnOrder, VersionBO bo) {
    if (purchaseReturnOrder.getStatus() != PurchaseReturnStatus.AUDITED
        && purchaseReturnOrder.getStatus() != PurchaseReturnStatus.NOTICE_FAILED) {
      throw new OmsException("只有审核或推送失败的采购退货单才能推送给仓库");
    }

    Warehouse warehouse = warehouseService.getByKey(purchaseReturnOrder.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");
    StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
        .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());

    StockOutOrderCreateRequest request = new StockOutOrderCreateRequest(warehouse);
    PurchaseReturnOrderDetailQuery purchaseReturnOrderDetailQuery = new PurchaseReturnOrderDetailQuery();
    purchaseReturnOrderDetailQuery
        .setPurchaseReturnOrderId(purchaseReturnOrder.getPurchaseReturnOrderId());
    List<PurchaseReturnOrderDetail> purchaseReturnOrderDetails = purchaseReturnOrderDetailService
        .list(purchaseReturnOrderDetailQuery);

    request.setOmsCode(purchaseReturnOrder.getPurchaseReturnOrderCode());
    request.setOrderType(OrderType.PURCHASE_RETURN_ORDER);
    request.setCreatedTime(purchaseReturnOrder.getCreatedTime());
    request.setSupplierCode(purchaseReturnOrder.getSupplierCode());
    request.setSupplierName(purchaseReturnOrder.getSupplierName());

    //仓库信息
    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setSenderArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());
    //拼接请求明细数据
    List<StockOutOrderCreateRequest.StockOutOrderDetail> stockOutOrderDetails = new ArrayList<>();
    for (PurchaseReturnOrderDetail purchaseReturnOrderDetail : purchaseReturnOrderDetails) {
      StockOutOrderCreateRequest.StockOutOrderDetail detail = new StockOutOrderCreateRequest.StockOutOrderDetail();
      detail.setSkuCode(purchaseReturnOrderDetail.getSkuCode());
      detail.setWmsSkuId(FormatUtil.formatLong(purchaseReturnOrderDetail.getSkuId()));
      detail.setProductCode(purchaseReturnOrderDetail.getProductCode());
      detail.setProductName(purchaseReturnOrderDetail.getProductName());
      detail.setQuantity(purchaseReturnOrderDetail.getNoticeQuantity());
      stockOutOrderDetails.add(detail);
    }
    request.setDetails(stockOutOrderDetails);

    StockOutOrderCreateResponse response = stockOutOrderBridge.createOrder(request);
    PurchaseReturnOrder updateInfo = new PurchaseReturnOrder();
    updateInfo.setPurchaseReturnOrderId(purchaseReturnOrder.getPurchaseReturnOrderId());
    updateInfo.setVersion(purchaseReturnOrder.getVersion());
    if (response.isSuccess()) {
      updateInfo.setStatus(PurchaseReturnStatus.NOTICED);
    } else {
      updateInfo.setStatus(PurchaseReturnStatus.NOTICE_FAILED);
    }
    modify(updateInfo);
    BIZ_LOGGER
        .log(purchaseReturnOrder.getPurchaseReturnOrderId(), BizLogger.NOTICE_WMS,
            response.isSuccess() ? "成功" : response.getMessage());
  }

  @Override
  public void cancel(PurchaseReturnOrder purchaseReturnOrder, PurchaseReturnOrderCancelBO bo) {
    if (OutStatus.NO_OUT != purchaseReturnOrder.getOutStatus()) {
      throw new OmsException("当前通知单已入库");
    }
    if (PurchaseReturnStatus.NOTICED == purchaseReturnOrder.getStatus()) {
      Warehouse warehouse = warehouseService.getByKey(purchaseReturnOrder.getWarehouseId());
      ReturnOrderBridge returnOrderBridge = wmsBridgeFactory
          .getReturnOrderBridge(warehouse.getWmsApp().getWmsType());
      ReturnOrderCancelRequest request = new ReturnOrderCancelRequest(warehouse);
      request.setOmsCode(purchaseReturnOrder.getPurchaseReturnOrderCode());
      ReturnOrderCancelResponse response = returnOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(purchaseReturnOrder, bo);
      } else {
        throw new OmsException("仓库取消失败:" + response.getMessage());
      }
    } else {
      cancelOms(purchaseReturnOrder, bo);
    }
  }

  private void cancelOms(PurchaseReturnOrder purchaseReturnOrder, PurchaseReturnOrderCancelBO bo) {
    purchaseReturnOrder.setStatus(PurchaseReturnStatus.CANCELED);
    final List<PurchaseReturnOrderDetail> details = purchaseReturnOrderDetailService
        .listDetails(purchaseReturnOrder.getPurchaseReturnOrderId());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(purchaseReturnOrder.getPurchaseReturnOrderId(),
                StockOccupancyType.PURCHASE_RETURN);
        update(purchaseReturnOrder);
      });
      for (PurchaseReturnOrderDetail detail : details) {
        if (detail.getNoticeQuantity() > 0) {
          getMqProducer().send(
              new StockChangedMessage(purchaseReturnOrder.getPurchaseReturnOrderCode(),
                  detail.getSkuId(),
                  BizContext.getNickname(), "采购退货单取消"));
        }
      }
      BIZ_LOGGER.log(purchaseReturnOrder.getPurchaseReturnOrderId(), BizLogger.CANCEL, "原因：{}",
          bo.getReason());
    } catch (Exception e) {
      LOGGER.error("出库单取消失败,借出单信息: {}", JsonUtil.toJson(purchaseReturnOrder));
      LOGGER.error("出库单取消失败,堆栈信息: ", e);
      throw new OmsException("出库单取消失败");
    }
  }

  @Override
  public PurchaseReturnOrder getByCode(String orderCode) {
    PurchaseReturnOrder eg = new PurchaseReturnOrder();
    eg.setPurchaseReturnOrderCode(orderCode);
    return getByExample(eg);
  }

  /**
   * 采购退货单接收WMS回传的出库信息，修改采购退货单单出库状态，修改明细的出库数量
   */
  @Override
  public void wmsAutoOut(PurchaseReturnOrder purchaseReturnOrder, WmsAutoOutBO wmsAutoOutBO) {

    if (purchaseReturnOrder.getStatus() == PurchaseReturnStatus.CANCELED) {
      throw new OmsException("单据已取消");
    }
    if (purchaseReturnOrder.getOutStatus() == OutStatus.ALL_OUT) {
      LOGGER.info("单据已全部出库，不再处理，WMS单号：{}", wmsAutoOutBO.getWmsOrderCode());
      return;
    }

    boolean allOut = true;
    Map<String, WmsAutoOutDetail> map = CollectionUtil
        .listToMap(wmsAutoOutBO.getDetails(), WmsAutoOutDetail::getSkuCode);
    List<PurchaseReturnOrderDetail> details = purchaseReturnOrderDetailService
        .listDetails(purchaseReturnOrder.getPurchaseReturnOrderId());
    List<PurchaseReturnOrderDetail> updateDetails = new ArrayList<>(details.size());
    List<DetailStockChange> stockChanges = new ArrayList<>(details.size());
    WmsAutoOutDetail wmsAutoOutDetail;
    for (PurchaseReturnOrderDetail detail : details) {
      if (!detail.getNoticeQuantity().equals(detail.getOutQuantity())) {
        if (map.containsKey(detail.getSkuCode())) {
          int balance = detail.getNoticeQuantity() - detail.getOutQuantity();
          int actual = 0;
          wmsAutoOutDetail = map.get(detail.getSkuCode());
          if (wmsAutoOutDetail.getOutQuantity() > balance) {
            actual = balance;
            wmsAutoOutDetail.setOutQuantity(wmsAutoOutDetail.getOutQuantity() - balance);
          } else {
            actual = wmsAutoOutDetail.getOutQuantity();
          }
          detail.setOutQuantity(detail.getOutQuantity() + actual);
          stockChanges.add(new DetailStockChange(detail, actual));
          updateDetails.add(detail);
        }
      }
      if (detail.getOutQuantity() < detail.getNoticeQuantity()) {
        allOut = false;
      }
    }

    PurchaseReturnOrder updateInfo = new PurchaseReturnOrder();
    updateInfo.setPurchaseReturnOrderId(purchaseReturnOrder.getPurchaseReturnOrderId());
    updateInfo.setOutStatus(allOut ? OutStatus.ALL_OUT : OutStatus.PART_OUT);
    updateInfo.setVersion(wmsAutoOutBO.getVersion());
    if (purchaseReturnOrder.getStatus() == PurchaseReturnStatus.NOTICE_FAILED){
      updateInfo.setStatus(PurchaseReturnStatus.NOTICED);
    }
    try {
      getTransactionTemplate().execute(() -> {
        purchaseReturnOrderDetailService.batchModify(updateDetails);
        for (DetailStockChange change : stockChanges) {
          stockService
              .adjustQuantity(purchaseReturnOrder.getPurchaseReturnOrderCode(),
                  OrderType.PURCHASE_RETURN_ORDER,
                  change.detail.getSkuId(), change.detail.getSkuCode(),
                  purchaseReturnOrder.getWarehouseId(),
                  purchaseReturnOrder.getWarehouseName(),
                  purchaseReturnOrder.getVirtualWarehouseId(),
                  purchaseReturnOrder.getVirtualWarehouseName(), -change.quantity);
          if (change.detail.getNoticeQuantity().equals(change.detail.getOutQuantity())) {
            stockOccupancyService
                .deleteOccupancy(change.detail.getPurchaseReturnOrderId(),
                    change.detail.getPurchaseReturnOrderDetailId(),
                    StockOccupancyType.PURCHASE_RETURN);
          } else {
            //调整数量
            stockOccupancyService
                .adjustQuantity(change.detail.getPurchaseReturnOrderId(),
                    change.detail.getPurchaseReturnOrderDetailId(),
                    StockOccupancyType.PURCHASE_RETURN, -change.quantity);
          }
        }
        update(updateInfo);
      });
      if (updateInfo.getOutStatus() == OutStatus.ALL_OUT){
        getMqProducer().send(new GeneralMessage(OmsModule.PURCHASE_RETURN,updateInfo.getPurchaseReturnOrderId(),EventType.OUT));
      }
      BIZ_LOGGER.log(updateInfo.getPurchaseReturnOrderId(), BizLogger.DELIVERY, "采购退货单出库");
      stockChanges.forEach(x -> {
        getMqProducer().send(
            new StockChangedMessage(x.detail.getSkuId(), BizContext.getNickname(),
                "采购退货单商品出库"));
      });
    } catch (Exception e) {
      LOGGER.error("采购退货单出库异常,采购退货单信息: {}", JsonUtil.toJson(purchaseReturnOrder));
      LOGGER.error("采购退货单出库异常,堆栈信息: ", e);
      throw new OmsException("采购退货单出库异常");
    }
  }

  static class DetailStockChange {

    private PurchaseReturnOrderDetail detail;
    private int quantity;

    public DetailStockChange(PurchaseReturnOrderDetail detail, int quantity) {
      this.detail = detail;
      this.quantity = quantity;
    }
  }
}