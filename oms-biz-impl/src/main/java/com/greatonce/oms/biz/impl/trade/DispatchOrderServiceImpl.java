package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.MathUtil;
import com.greatonce.oms.biz.ActionFunction;
import com.greatonce.oms.biz.admin.GlobalExpressMallMappingService;
import com.greatonce.oms.biz.admin.WmsAppService;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.stock.StockOutBatchService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.biz.trade.SalesOrderSubService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.AutoDispatchBO;
import com.greatonce.oms.bo.trade.DispatchBO;
import com.greatonce.oms.bo.trade.DispatchDetailBO;
import com.greatonce.oms.bo.trade.DispatchOrderCancelBO;
import com.greatonce.oms.bo.trade.DispatchOrderCancelDetailBO;
import com.greatonce.oms.bo.trade.ManualDeliveryBO;
import com.greatonce.oms.bo.trade.ManualDispatchBO;
import com.greatonce.oms.bo.trade.ManualDispatchDetailBO;
import com.greatonce.oms.bo.trade.OfflineDeliveryBO;
import com.greatonce.oms.bo.trade.SalesOrderCancelDispatchBO;
import com.greatonce.oms.bo.trade.SalesOrderDispatchBO;
import com.greatonce.oms.bo.trade.SalesOrderResetDetailBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.trade.WmsExpressNoticeBo;
import com.greatonce.oms.bo.trade.WmsLogBO;
import com.greatonce.oms.bridge.mall.LogisticsBridge;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.bridge.mall.request.WaybillGetRequest;
import com.greatonce.oms.bridge.mall.response.WaybillGetResponse;
import com.greatonce.oms.bridge.wms.DeliveryOrderBridge;
import com.greatonce.oms.bridge.wms.DeliveryOrderFilter;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.WmsFilterFactory;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest.DeliveryOrderDetail;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderQueryRequest;
import com.greatonce.oms.bridge.wms.request.OrderProcessQueryRequest;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderQueryResponse.WmsDeliveryOrderStatus;
import com.greatonce.oms.bridge.wms.response.OrderProcessQueryResponse;
import com.greatonce.oms.dao.trade.DispatchOrderDao;
import com.greatonce.oms.domain.Constants;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.admin.GlobalExpressMallMapping;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.base.setting.IoBillSetting;
import com.greatonce.oms.domain.enums.MallType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WaybillStrategy;
import com.greatonce.oms.domain.enums.WaybillType;
import com.greatonce.oms.domain.enums.WmsOrderStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.DispatchOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.InvoiceInfo;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.domain.trade.WaybillInfo;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.Message;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.message.trade.DispatchOrderCreateMessage;
import com.greatonce.oms.message.trade.DispatchOrderDeliveryMessage;
import com.greatonce.oms.message.trade.DispatchOrderExpressNoticeMessage;
import com.greatonce.oms.message.trade.MallDeliveryResetExpressMessage;
import com.greatonce.oms.message.trade.SalesOrderDispatchedMessage;
import com.greatonce.oms.query.trade.DispatchOrderQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 配货单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-08
 */
@Service
public class DispatchOrderServiceImpl extends
    AbstractVersionService<DispatchOrder, DispatchOrderQuery> implements DispatchOrderService {

  private static final Logger LOGGER = LoggerFactory.getLogger(DispatchOrderServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.DISPATCH_ORDER);

  @Resource
  private IdGenerator dispatchOrderIdGenerator;
  @Resource
  private CodeGenerator dispatchOrderCodeGenerator;
  @Autowired
  private DispatchOrderDao dao;
  @Autowired
  private DispatchOrderDetailService dispatchOrderDetailService;
  @Autowired
  private DispatchOrderDeliveryService dispatchOrderDeliveryService;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private StockService stockService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private ExpressService expressService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private WmsFilterFactory wmsFilterFactory;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private WmsAppService wmsAppService;
  @Autowired
  private GlobalExpressMallMappingService globalExpressMallMappingService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private SalesOrderSubService salesOrderSubService;

  @Override
  protected QueryDao<DispatchOrder, DispatchOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return dispatchOrderIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Resource
  private IdGenerator stockOutBatchIdGenerator;

  @Resource
  private IdGenerator stockOutBatchDetailIdGenerator;

  @Autowired
  private StockOutBatchService stockOutBatchService;

  @Override
  public int create(DispatchOrder record) {
    Assert.notNull(record.getDetails(), "配货单没有明细");
    initDefaultValue(record);

    //编程式事务
    int count = getTransactionTemplate().executeWithResult(() -> {
      dispatchOrderDetailService.batchCreate(record.getDetails());
      if (Assert.isFalse(record.isOfflineDelivery())) {
        List<StockOccupancy> occupancies = DispatchOrderUtil
            .buildOccupancy(record, record.getDetails());
        stockOccupancyService.batchCreate(occupancies);
      }
      return insert(record);
    });
    record.getDetails().forEach(detail -> getMqProducer().send(
        new StockChangedMessage(record.getDispatchOrderCode(), detail.getSkuId(),
            record.getStoreId(), record.getCreator(), "创建配货单")));
    return count;
  }

  /**
   * 设置初始值.
   * <p/>
   * 配货单不能复制，所以不需要空值判断
   */
  @Override
  protected void initDefaultValue(DispatchOrder dispatchOrder) {
    super.initDefaultValue(dispatchOrder);
    dispatchOrder.setNeedInvoice(Assert.isTrue(dispatchOrder.isNeedInvoice()));
    if (Assert.isEmpty(dispatchOrder.getDispatchOrderCode())) {
      dispatchOrder.setDispatchOrderCode(dispatchOrderCodeGenerator.next());
    }
    if (dispatchOrder.getWaybillInfo() != null) {
      dispatchOrder.setWaybillInfoJson(JsonUtil.toJson(dispatchOrder.getWaybillInfo()));
    }
    if (dispatchOrder.getInvoiceInfo() != null) {
      dispatchOrder.setInvoiceInfoJson(JsonUtil.toJson(dispatchOrder.getInvoiceInfo()));
    }
    dispatchOrder.setVersion(Constants.DEFAULT_DATA_VERSION);
    dispatchOrder.setPostStatus(PostStatus.UN_POST);
    dispatchOrder.getDetails()
        .forEach(x -> x.setDispatchOrderId(dispatchOrder.getDispatchOrderId()));
  }

  @Override
  public List<DispatchOrder> listBySalesOrderId(Long salesOrderId) {
    return dao.listBySalesOrderId(salesOrderId);
  }

  @Override
  public List<DispatchOrder> listBySalesOrderId(List<Long> salesOrderId) {
    ArrayList<DispatchOrder> dispatchOrders = new ArrayList<>();
    salesOrderId.forEach(x -> {
      List<DispatchOrder> dispatchOrder = dao.listBySalesOrderId(x);
      dispatchOrders.addAll(dispatchOrder);
    });
    return dispatchOrders;
  }

  @Override
  public List<DispatchOrder> listBySalesOrderDetailId(Long salesOrderId,
      Collection<Long> salesOrderIds) {
    return dao.listBySalesOrderDetailId(salesOrderId, salesOrderIds);
  }

  /**
   * 取消配货单. 主入口
   * <p/>
   * 逻辑： 如果通知了WMS，先调用WMS取消
   *
   * @param dispatchOrder 配货单
   * @param bo 取消对象
   */
  @Override
  public void cancel(DispatchOrder dispatchOrder, DispatchOrderCancelBO bo) {
    DispatchOrderUtil.isBeforeAllDelivery(dispatchOrder);
    if (dispatchOrder.getStatus() == DispatchOrderStatus.NOTIFIED
        || dispatchOrder.getStatus() == DispatchOrderStatus.NOTICE_FAILED) {
      Warehouse warehouse;
      if (Assert.isTrue(dispatchOrder.isOfflineDelivery())) {
        //线下发货是没有仓库的，封装信息，供奇门调用
        warehouse = new Warehouse();
        IoBillSetting ioBillSetting = settingService.getIoBillSetting();
        warehouse
            .setWmsApp(
                wmsAppService.getByKey(Long.valueOf(ioBillSetting.getOfflineDeliveryAppId())));
      } else {
        warehouse = warehouseService.getByKey(dispatchOrder.getWarehouseId());
      }
      DeliveryOrderBridge deliveryOrderBridge = wmsBridgeFactory
          .getDeliveryOrderBridge(warehouse.getWmsApp().getWmsType());

      if (dispatchOrder.getStatus() == DispatchOrderStatus.NOTICE_FAILED) {
        DeliveryOrderQueryRequest queryRequest = new DeliveryOrderQueryRequest(warehouse);
        queryRequest.setOmsCode(dispatchOrder.getDispatchOrderCode());
        final DeliveryOrderQueryResponse queryResponse = deliveryOrderBridge
            .queryOrder(queryRequest);
        //如果WMS单据不存在直接取消
        if (!queryResponse.isExists()
            || queryResponse.getStatus() == WmsDeliveryOrderStatus.CANCELED) {
          cancelOms(dispatchOrder, bo);
          return;
        }
      }

      DeliveryOrderCancelRequest request = new DeliveryOrderCancelRequest(warehouse);
      request.setOmsCode(dispatchOrder.getDispatchOrderCode());
      request.setOrderType(OrderType.B2C_DISPATCH_ORDER);
      DeliveryOrderCancelResponse response = deliveryOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        if (Assert.isTrue(response.isAsync())) {
          BIZ_LOGGER
              .log(dispatchOrder.getDispatchOrderId(), BizLogger.CANCEL,
                  "已通知仓库取消，是否取消成功以仓库取消结果为最终结果");
        } else {
          cancelOms(dispatchOrder, bo);
        }
      } else {
        throw new OmsException(response.getMessage());
      }
    } else {
      cancelOms(dispatchOrder, bo);
    }
  }

  /**
   * 取消配货单明细. 主入口
   *
   * @param dispatchOrder 配货单
   * @param bo 明细对象
   */
  @Override
  public void cancel(DispatchOrder dispatchOrder, DispatchOrderCancelDetailBO bo) {
    cancelOmsDetail(dispatchOrder, bo);
  }

  /**
   * 取消OMS配货单.
   */
  @Override
  public void cancelOms(DispatchOrder dispatchOrder, DispatchOrderCancelBO bo) {
    DispatchOrder updateInfo = new DispatchOrder();
    updateInfo.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    updateInfo.setStatus(DispatchOrderStatus.CANCELED);
    updateInfo.setVersion(bo.getVersion());
    List<DispatchOrderDetail> details = dispatchOrderDetailService
        .listSimple(dispatchOrder.getDispatchOrderId());
    Map<Long, List<DispatchOrderDetail>> map = details.stream()
        .collect(Collectors.groupingBy(DispatchOrderDetail::getSalesOrderId));

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        dispatchOrderDetailService.cancel(dispatchOrder);
        stockOccupancyService
            .deleteOccupancy(dispatchOrder.getDispatchOrderId(), StockOccupancyType.DISPATCH_ORDER);
        update(updateInfo);

        for (Map.Entry<Long, List<DispatchOrderDetail>> entry : map.entrySet()) {
          SalesOrder salesOrder = salesOrderService.getSimpleInfo(entry.getKey());
          SalesOrderCancelDispatchBO cancelDispatchBO = new SalesOrderCancelDispatchBO();
          cancelDispatchBO.setVersion(salesOrder.getVersion());
          cancelDispatchBO.setDetails(entry.getValue().stream().map(x -> {
            SalesOrderDetail detail = new SalesOrderDetail();
            detail.setSalesOrderId(x.getSalesOrderId());
            detail.setSalesOrderDetailId(x.getSalesOrderDetailId());
            return detail;
          }).collect(Collectors.toList()));
          salesOrderService.cancelDispatch(salesOrder, cancelDispatchBO);
        }
      });
    } catch (OmsException e) {
      LOGGER.error("取消配货单失败，配货单信息:{}", JsonUtil.toJson(dispatchOrder));
      LOGGER.error("取消配货单失败，堆栈信息:", e);
      BIZ_LOGGER
          .log(dispatchOrder.getDispatchOrderId(), BizLogger.CANCEL_DISPATCH,
              "配货单取消失败，原因" + e.getMessage());
      throw e;
    } catch (Exception e) {
      LOGGER.error("取消配货单失败，配货单信息:{}", JsonUtil.toJson(dispatchOrder));
      LOGGER.error("取消配货单失败，堆栈信息:", e);
      BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.CANCEL_DISPATCH, "配货单取消失败！");
      throw new OmsException("系统取消配货单失败");
    }
    BIZ_LOGGER
        .log(dispatchOrder.getDispatchOrderId(), BizLogger.CANCEL, "取消配货单，原因:{}", bo.getReason());
    details.forEach(x -> {
      StockChangedMessage message = new StockChangedMessage(dispatchOrder.getDispatchOrderCode(),
          x.getSkuId(), BizContext.getNickname(), "配货单取消");
      getMqProducer().send(message);
      salesOrderService.getBizLogger()
          .log(x.getSalesOrderId(), x.getSalesOrderDetailId(), BizLogger.CANCEL_DISPATCH,
              "商品规格{}取消配货，原因：{}", x.getSkuCode(), bo.getReason());
    });
  }

  /**
   * 取消配货单明细. 私有方法
   */
  private void cancelOmsDetail(DispatchOrder dispatchOrder, DispatchOrderCancelDetailBO bo) {
    try {
      getTransactionTemplate().execute(() -> {
        dispatchOrderDetailService.cancel(dispatchOrder, bo.getDetail());
        resetStatus(dispatchOrder, bo);
        SalesOrder salesOrder = salesOrderService.getSimpleInfo(bo.getDetail().getSalesOrderId());

        SalesOrderResetDetailBO resetDetailBO = new SalesOrderResetDetailBO();
        resetDetailBO.setVersion(salesOrder.getVersion());
        ArrayList<Long> longs = new ArrayList<>(1);
        longs.add(bo.getDetail().getSalesOrderDetailId());
        resetDetailBO.setSalesOrderDetailIds(longs);

        salesOrderService.reset(salesOrder, resetDetailBO);
      });
      BIZ_LOGGER
          .log(dispatchOrder.getDispatchOrderId(), bo.getDetail().getDispatchOrderDetailId(),
              BizLogger.CANCEL, bo.getDetail().getSkuCode());
    } catch (Exception e) {
      LOGGER.error("取消配货单明细失败，配货单信息:{}，明细：{}", JsonUtil.toJson(dispatchOrder), bo.getDetail());
      LOGGER.error("取消配货单明细失败，堆栈信息:", e);
      throw new OmsException("取消配货单明细失败");
    }
  }

  /**
   * 手工发货.
   * <p/>
   * 本方法中包含了线下配货的逻辑，如果是线下发货需要添加仓库信息，明细虚拟仓为对应实体仓的共享仓
   *
   * @param dispatchOrder 配货单
   * @param bo 手工发货对象
   */
  @Override
  public void manualDelivery(DispatchOrder dispatchOrder, ManualDeliveryBO bo) {
    DispatchOrderUtil.isBeforeDelivery(dispatchOrder);
    List<DispatchOrderDetail> details = dispatchOrderDetailService
        .listSimple(dispatchOrder.getDispatchOrderId());
    List<DispatchOrderDelivery> deliveries = new ArrayList<>(details.size());
    for (DispatchOrderDetail detail : details) {
      DispatchOrderDelivery delivery = new DispatchOrderDelivery();
      delivery.setDispatchOrderId(detail.getDispatchOrderId());
      delivery.setDispatchOrderDetailId(detail.getDispatchOrderDetailId());
      delivery.setSalesOrderId(detail.getSalesOrderId());
      delivery.setSalesOrderDetailId(detail.getSalesOrderDetailId());
      delivery.setProductId(detail.getProductId());
      delivery.setProductCode(detail.getProductCode());
      delivery.setProductName(detail.getProductName());
      delivery.setSkuId(detail.getSkuId());
      delivery.setSkuCode(detail.getSkuCode());
      delivery.setSkuName(detail.getSkuName());
      delivery.setQuantity(detail.getNoticeQuantity());
      delivery.setBoxNo(bo.getBoxNo());
      delivery.setOuterCode(bo.getOutCode());
      delivery.setExpressId(bo.getExpressId());
      delivery.setExpressName(bo.getExpressName());
      delivery.setExpressNo(bo.getExpressNo());
      delivery.setDeliveryTime(LocalDateTime.now());

      deliveries.add(delivery);
      detail.setOutQuantity(detail.getNoticeQuantity());
    }

    DispatchOrder updateInfo = new DispatchOrder();
    updateInfo.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    updateInfo.setOutStatus(OutStatus.ALL_OUT);
    updateInfo.setStatus(DispatchOrderStatus.DELIVERED);
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setLastDeliveryTime(LocalDateTime.now());

    //线下发货
    if (dispatchOrder.isOfflineDelivery()) {
      Warehouse warehouse = warehouseService.getByKey(bo.getWarehouseId());
      VirtualWarehouse shareWarehouse = virtualWarehouseService
          .getShareWarehouse(bo.getWarehouseId());
      updateInfo.setWarehouseId(warehouse.getWarehouseId());
      updateInfo.setWarehouseName(warehouse.getWarehouseName());
      details.forEach(x -> {
        x.setVirtualWarehouseId(shareWarehouse.getVirtualWarehouseId());
        x.setVirtualWarehouseName(shareWarehouse.getVirtualWarehouseName());
      });
    }

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        dispatchOrderDeliveryService.batchCreate(deliveries);
        dispatchOrderDetailService.delivery(dispatchOrder, details);
        if (!dispatchOrder.isOfflineDelivery()) {
          for (DispatchOrderDetail detail : details) {
            stockService
                .adjustQuantity(dispatchOrder.getDispatchOrderCode(),
                    OrderType.B2C_DISPATCH_ORDER,
                    detail.getSkuId(), detail.getSkuCode(), dispatchOrder.getWarehouseId(),
                    dispatchOrder.getWarehouseName(), detail.getVirtualWarehouseId(),
                    detail.getVirtualWarehouseName(), -detail.getOutQuantity());
            stockOccupancyService
                .deleteOccupancy(detail.getDispatchOrderId(), detail.getDispatchOrderDetailId(),
                    StockOccupancyType.DISPATCH_ORDER);
          }
        }
        modify(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("手工发货失败，配货单信息:{}", JsonUtil.toJson(dispatchOrder));
      LOGGER.error("手工发货失败，堆栈信息:", e);
      throw new OmsException("手工发货失败");
    }

    getMqProducer().send(new DispatchOrderDeliveryMessage(dispatchOrder.getDispatchOrderId()));
    BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.DELIVERY, "手工发货");
  }

  /**
   * 仓库通知发货.
   */
  @Override
  public void wmsAutoDelivery(DispatchOrder dispatchOrder, WmsAutoOutBO wmsAutoOutBO) {

    if (dispatchOrder.getStatus() == DispatchOrderStatus.CANCELED) {
      throw new OmsException("单据已取消");
    }
    if (dispatchOrder.getOutStatus() == OutStatus.ALL_OUT) {
      LOGGER.info("单据已全部出库，不再处理，WMS单号：{}", wmsAutoOutBO.getWmsOrderCode());
      return;
    }

    DispatchOrder updateInfo = new DispatchOrder();
    updateInfo.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    updateInfo.setVersion(wmsAutoOutBO.getVersion());
    updateInfo.setOutStatus(OutStatus.ALL_OUT);
    updateInfo.setDeliveryFinish(true);
    updateInfo.setStatus(DispatchOrderStatus.DELIVERED);
    Warehouse warehouse;
    VirtualWarehouse virtualWarehouse = null;

    if (Assert.isTrue(dispatchOrder.isOfflineDelivery())) {
      warehouse = warehouseService.getEffectiveByCode(wmsAutoOutBO.getDeliveryWarehouseCode());
      Assert.notNull(warehouse, "未找到仓库：" + wmsAutoOutBO.getDeliveryWarehouseCode());
      updateInfo.setWarehouseId(warehouse.getWarehouseId());
      updateInfo.setWarehouseName(warehouse.getWarehouseName());
      virtualWarehouse = virtualWarehouseService.getShareWarehouse(warehouse.getWarehouseId());
      Assert.notNull(virtualWarehouse, "未找到所属共享仓库：" + wmsAutoOutBO.getDeliveryWarehouseCode());
    } else {
      warehouse = warehouseService.getByKey(dispatchOrder.getWarehouseId());
    }

    WmsAutoOutBO.WmsAutoOutPackage outPackage = wmsAutoOutBO.getPackages().get(0);
    updateInfo.setLastDeliveryTime(wmsAutoOutBO.getOutTime());
    Express express = expressService
        .getByWmsExpressCode(warehouse.getWmsAppId(), outPackage.getWmsExpressCode());
    Assert.notNull(express, "此快递编码未找到对应的系统快递：" + outPackage.getWmsExpressCode());

    List<DispatchOrderDetail> details = dispatchOrderDetailService
        .listSimple(dispatchOrder.getDispatchOrderId());

    List<DispatchOrderDelivery> deliveries = new ArrayList<>(details.size());
    List<StockOutBatchDetail> stockOutBatchDetails = new ArrayList<>(details.size());
    Integer totalOutQuantity = 0;

    for (DispatchOrderDetail detail : details) {
      DispatchOrderDelivery delivery = new DispatchOrderDelivery();
      delivery.setDispatchOrderId(detail.getDispatchOrderId());
      delivery.setDispatchOrderDetailId(detail.getDispatchOrderDetailId());
      delivery.setSalesOrderId(detail.getSalesOrderId());
      delivery.setSalesOrderDetailId(detail.getSalesOrderDetailId());
      delivery.setProductId(detail.getProductId());
      delivery.setProductCode(detail.getProductCode());
      delivery.setProductName(detail.getProductName());
      delivery.setSkuId(detail.getSkuId());
      delivery.setSkuCode(detail.getSkuCode());
      delivery.setSkuName(detail.getSkuName());
      delivery.setQuantity(detail.getNoticeQuantity());
      delivery.setOuterCode(wmsAutoOutBO.getWmsOrderCode());
      delivery.setExpressId(express.getExpressId());
      delivery.setExpressName(express.getExpressName());
      delivery.setExpressNo(outPackage.getExpressNo());
      delivery.setDeliveryTime(outPackage.getDeliveryTime());
      detail.setStatus(DispatchOrderDetailStatus.DELIVERED);
      detail.setOutQuantity(detail.getNoticeQuantity());
      if (Assert.isTrue(dispatchOrder.isOfflineDelivery())) {
        detail.setVirtualWarehouseId(virtualWarehouse.getVirtualWarehouseId());
        detail.setVirtualWarehouseName(virtualWarehouse.getVirtualWarehouseName());
      }
      deliveries.add(delivery);
      totalOutQuantity += detail.getOutQuantity();
    }

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        dispatchOrderDeliveryService.batchCreate(deliveries);
        dispatchOrderDetailService.delivery(dispatchOrder, details);
        if (!Assert.isTrue(dispatchOrder.isOfflineDelivery())) {
          for (DispatchOrderDetail detail : details) {
            stockService.adjustQuantity(dispatchOrder.getDispatchOrderCode(),
                OrderType.B2C_DISPATCH_ORDER,
                detail.getSkuId(), detail.getSkuCode(), dispatchOrder.getWarehouseId(),
                dispatchOrder.getWarehouseName(), detail.getVirtualWarehouseId(),
                detail.getVirtualWarehouseName(), -detail.getOutQuantity());
            stockOccupancyService
                .deleteOccupancy(detail.getDispatchOrderId(), detail.getDispatchOrderDetailId(),
                    StockOccupancyType.DISPATCH_ORDER);
          }
        }
        update(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("仓库通知发货，配货单信息:{}", JsonUtil.toJson(dispatchOrder));
      LOGGER.error("仓库通知发货，堆栈信息:", e);
      throw new OmsException("仓库通知发货失败");
    }

    getMqProducer().send(new DispatchOrderDeliveryMessage(dispatchOrder.getDispatchOrderId()));
    if (!Assert.isEmpty(dispatchOrder.getSuggestExpressNo()) && !dispatchOrder.getSuggestExpressNo()
        .equals(outPackage.getExpressNo())) {
      BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.DELIVERY,
          "仓库发货，出库快递信息与推荐不同，推荐快递：{}，推荐快递单号：{} ---> 出库快递：{}，快递单号：{}",
          dispatchOrder.getSuggestExpressName(), dispatchOrder.getSuggestExpressNo(),
          express.getExpressName(), outPackage.getExpressNo());
    } else if (Assert.isEmpty(dispatchOrder.getSuggestExpressNo())) {
      BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.DELIVERY,
          "仓库发货，配货单无推荐快递，出库快递：{}，快递单号：{}",
          express.getExpressName(), outPackage.getExpressNo());
    } else {
      BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.DELIVERY,
          "仓库发货，出库快递信息与推荐一致，出库快递：{}，快递单号：{}",
          express.getExpressName(), outPackage.getExpressNo());
    }

  }


  @Override
  public DispatchOrder getSimpleInfo(Long dispatchOrderId) {
    return dao.getSimpleInfo(dispatchOrderId);
  }

  @Override
  public DispatchOrder getByCode(String dispatchOrderCode) {
    DispatchOrder eg = new DispatchOrder();
    eg.setDispatchOrderCode(dispatchOrderCode);
    return getByExample(eg);
  }

  /**
   * 通知wms主入口方法.
   * <p/>
   * 封装request,调用奇门接口向wms推送配货单数据
   */
  @Override
  public void noticeWms(DispatchOrder dispatchOrder) {
    DispatchOrderUtil
        .inStatus(dispatchOrder, DispatchOrderStatus.CREATED, DispatchOrderStatus.NOTICE_FAILED);

    if (dispatchOrder.getStatus() != DispatchOrderStatus.CREATED
        && dispatchOrder.getStatus() != DispatchOrderStatus.NOTICE_FAILED) {
      throw new OmsException("只有新建或推送失败的配货单才能推送给仓库");
    }

    Warehouse warehouse;
    Store store = storeService.getByKey(dispatchOrder.getStoreId());
    Assert.notNull(store, "未找到店铺");
    //判断是否线下发货
    if (Assert.isTrue(dispatchOrder.isOfflineDelivery())) {
      //线下发货是没有仓库的，封装信息，供奇门调用
      warehouse = new Warehouse();
      IoBillSetting ioBillSetting = settingService.getIoBillSetting();
      warehouse
          .setWmsApp(wmsAppService.getByKey(Long.valueOf(ioBillSetting.getOfflineDeliveryAppId())));
    } else {
      warehouse = warehouseService.getByKey(dispatchOrder.getWarehouseId());
      Assert.notNull(warehouse, "未找到仓库");
      Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");
    }
    //封装request
    DeliveryOrderCreateRequest request = new DeliveryOrderCreateRequest(warehouse);
    if (!Assert.isNull(warehouse) && !Assert.isNull(warehouse.getWarehouseId())) {
      request.getWarehouse().setWarehouseCode(warehouse.getWarehouseCode());

      request.setExpressCode(expressService
          .getWmsExpressCode(warehouse.getWmsAppId(), dispatchOrder.getSuggestExpressId()));
      request.setExpressName(dispatchOrder.getSuggestExpressName());
      request.setExpressNo(dispatchOrder.getSuggestExpressNo());
      request.setSenderName(warehouse.getContact());
      request.setSenderMobile(warehouse.getMobile());
      request.setSenderCountry(warehouse.getCountryName());
      request.setSenderProvince(warehouse.getProvinceName());
      request.setSenderCity(warehouse.getCityName());
      request.setSenderDistrict(warehouse.getDistrictName());
      request.setSenderAddress(warehouse.getAddress());
    }

    request.setUrgency(dispatchOrder.isUrgent());
    request.setOmsCode(dispatchOrder.getDispatchOrderCode());
    request.setOrderType(OrderType.B2C_DISPATCH_ORDER);
    request.setMallType(MallType.TMALL);
    request.setCreatedTime(dispatchOrder.getCreatedTime());
    request.setMallPaidTime(dispatchOrder.getCreatedTime());
    request.setMallCreatedTime(dispatchOrder.getCreatedTime());

    request.setTotalAmount(dispatchOrder.getSettlementAmount());
    request.setPaidAmount(dispatchOrder.getActualAmount());
    request.setStoreCode(store.getStoreCode());
    request.setStoreName(store.getStoreName());
    request.setBuyer(dispatchOrder.getMemberName());
    request.setBuyerMemo(dispatchOrder.getBuyerMemo());
    request.setSellerMemo(dispatchOrder.getSellerMemo());
    request.setRemark(dispatchOrder.getRemark());

    if (Assert.isTrue(dispatchOrder.isCod())) {
      request.setCodAmount(dispatchOrder.getCodAmount());
    }

    if (Assert.isTrue(dispatchOrder.isGetWaybill())) {
      WaybillInfo waybillInfo = JsonUtil
          .toObject(dispatchOrder.getWaybillInfoJson(), WaybillInfo.class);
      request.setConsolidationCode(waybillInfo.getPackageCenterCode());
      request.setConsolidationName(waybillInfo.getPackageCenterName());
      request.setSortation(waybillInfo.getBigShortName());
      request.setRouteCode(waybillInfo.getThirdSectionCode());
    }

    //加密收货人联系信息
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    request.setReceiverName(
        securityBridge.decrypt(store, dispatchOrder.getContact(), DataType.NAME));
    request.setReceiverMobile(
        securityBridge.decrypt(store, dispatchOrder.getMobile(), DataType.MOBILE));
    request.setReceiverTelephone(securityBridge
        .decrypt(store, dispatchOrder.getTelephone(), DataType.TELEPHONE));

    request.setReceiverCountry(dispatchOrder.getCountryName());
    request.setReceiverProvince(dispatchOrder.getProvinceName());
    request.setReceiverCity(dispatchOrder.getCityName());
    request.setReceiverDistrict(dispatchOrder.getDistrictName());
    request.setReceiverAddress(dispatchOrder.getAddress());

    List<DispatchOrderDetail> dispatchOrderDetailList = dispatchOrderDetailService
        .listByDispatchOrderId(dispatchOrder.getDispatchOrderId());

    List<DeliveryOrderDetail> details = new ArrayList<>(dispatchOrderDetailList.size());
    for (DispatchOrderDetail orderDetail : dispatchOrderDetailList) {
      DeliveryOrderDetail detail = new DeliveryOrderDetail();
      detail.setTradeId(orderDetail.getTradeId());
      detail.setSkuCode(orderDetail.getSkuCode());
      detail.setWmsSkuId("");
      detail.setProductName(orderDetail.getProductName());
      detail.setQuantity(orderDetail.getNoticeQuantity());
      detail.setSellingPrice(orderDetail.getSellingPrice());
      detail.setSettlementPrice(orderDetail.getSettlementPrice());
      detail.setAmount(orderDetail.getSettlementAmount());
      details.add(detail);
    }
    if (!Assert.isEmpty(dispatchOrderDetailList)) {
      SalesOrderSub salesOrderSub = salesOrderSubService
          .getByKey(dispatchOrderDetailList.get(0).getSalesOrderId());
      if (!Assert.isNull(salesOrderSub)) {
        request.setMallPaidTime(salesOrderSub.getMallPaidTime());
      }
    }
    request.setDetails(details);
    DeliveryOrderBridge deliveryOrderBridge = wmsBridgeFactory
        .getDeliveryOrderBridge(warehouse.getWmsApp().getWmsType());
    DeliveryOrderFilter deliveryOrderFilter = wmsFilterFactory
        .getDeliveryOrderFilter(warehouse.getWmsApp().getWmsType());
    deliveryOrderFilter.execute(request, dispatchOrder);
    DeliveryOrderCreateResponse response = deliveryOrderBridge.createOrder(request);

    DispatchOrder updateInfo = new DispatchOrder();
    updateInfo.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    updateInfo.setVersion(dispatchOrder.getVersion());
    if (response.isSuccess()) {
      dispatchOrder.setStatus(DispatchOrderStatus.NOTIFIED);
      updateInfo.setStatus(DispatchOrderStatus.NOTIFIED);
      update(updateInfo);
      BIZ_LOGGER
          .log(dispatchOrder.getDispatchOrderId(), BizLogger.NOTICE_WMS, "成功！");
    } else {
      dispatchOrder.setStatus(DispatchOrderStatus.NOTICE_FAILED);
      updateInfo.setStatus(DispatchOrderStatus.NOTICE_FAILED);
      update(updateInfo);
      BIZ_LOGGER
          .log(dispatchOrder.getDispatchOrderId(), BizLogger.NOTICE_WMS, "失败！{}",
              response.getMessage());
    }
  }

  /**
   * 手工配货主入口方法.
   */
  @Override
  public void manualDispatch(SalesOrder salesOrder, ManualDispatchBO bo) {
    //校验：bo中的明细至少要有一项是属于salesOrder的
    boolean noneMatch = bo.getDetails().stream()
        .noneMatch(x -> x.getSalesOrderId().equals(salesOrder.getSalesOrderId()));
    if (noneMatch) {
      throw new OmsException("合单配货时必须包含主单中的明细");
    }

    //构建配货单
    DispatchOrder dispatchOrder = buildDispatchOrder(salesOrder, bo);
    VirtualWarehouse vWarehouse = virtualWarehouseService.getByKey(bo.getVirtualWarehouseId());
    if (!Assert.isNull(vWarehouse)) {
      dispatchOrder.setWarehouseId(vWarehouse.getWarehouseId());
      dispatchOrder.setWarehouseName(vWarehouse.getWarehouseName());
    }
    dispatchOrder.setSuggestExpressId(bo.getExpressId());
    dispatchOrder.setSuggestExpressName(bo.getExpressName());

    /*
      构建配货单明细
      返回结果：
      Map<Long, List<SalesOrderDetail>>
      key：订单的ID, value：订单下配货的明细集合
     */
    Map<Long, List<SalesOrderDetail>> map = buildDispatchOrderDetail(dispatchOrder, salesOrder, bo,
        (detailBO, detail) -> {
          ManualDispatchDetailBO manualDispatchDetailBO = (ManualDispatchDetailBO) detailBO;
          detail.setVirtualWarehouseId(manualDispatchDetailBO.getVirtualWarehouseId());
          detail.setVirtualWarehouseName(manualDispatchDetailBO.getVirtualWarehouseName());
        });
    Warehouse warehouse = warehouseService.getByKey(vWarehouse.getWarehouseId());
    Express express = expressService.getByKey(dispatchOrder.getSuggestExpressId());
    if (warehouse.getWaybillStrategy() == WaybillStrategy.OMS &&
        express.getWaybillType() != WaybillType.NONE) {
      getWayBill(dispatchOrder, express);
      if (Assert.isEmpty(dispatchOrder.getSuggestExpressNo())) {
        throw new OmsException("获取电子面单失败，配货失败！");
      }
    }
    dispatchOrder.setContact(bo.getEncryptContact());
    dispatchOrder.setMobile(bo.getEncryptMobile());
    dispatchOrder.setTelephone(bo.getEncryptTelephone());
    //合单
    mergeOrder(salesOrder, bo, dispatchOrder, map);

    List<Long> noticeDetailIds = new ArrayList<>(map.size());
    List<Long> resetDetailIds = new ArrayList<>(map.size());
    map.forEach((x, y) -> y.forEach(z -> {
      SalesOrderDetail detail = salesOrderDetailService.getByKey(z.getSalesOrderDetailId());
      if (!Assert.isEmpty(detail.getExpressNo()) && detail.getMallSalesOrderDetailStatus()
          == MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS) {
        resetDetailIds.add(detail.getSalesOrderDetailId());
      } else if (detail.getMallSalesOrderDetailStatus()
          == MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS) {
        noticeDetailIds.add(detail.getSalesOrderDetailId());
      } else {
        salesOrderService.getBizLogger().log(detail.getSalesOrderId(), BizLogger.DISPATCH,
            "订单明细平台状态异常，回传失败，sku编码：{}", detail.getSkuCode());
      }
    }));

    if (Assert.isTrue(dispatchOrder.isGetWaybill())) {
      WaybillInfo waybillInfo = dispatchOrder.getWaybillInfo();
      List<Message> messages = new ArrayList<>(map.size() + 1);
      if (!Assert.isEmpty(noticeDetailIds)) {
        messages.add(new DispatchOrderExpressNoticeMessage(dispatchOrder.getDispatchOrderId(),
            waybillInfo.getMallExpressId(), waybillInfo.getMallExpressCode(),
            waybillInfo.getMallExpressName(), waybillInfo.getWaybillCode(),
            dispatchOrder.getSuggestExpressName(), noticeDetailIds));
      }
      if (!Assert.isEmpty(resetDetailIds)) {
        messages.add(new MallDeliveryResetExpressMessage(dispatchOrder.getDispatchOrderId(),
            waybillInfo.getWaybillCode(), waybillInfo.getMallExpressId(),
            waybillInfo.getMallExpressCode(), waybillInfo.getMallExpressName(), resetDetailIds));
      }
      messages.add(new DispatchOrderCreateMessage(dispatchOrder.getDispatchOrderId()));
      getMqProducer().send(messages);
    } else {
      getMqProducer().send(new DispatchOrderCreateMessage(dispatchOrder.getDispatchOrderId()));
    }
    BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.ADD, "手工配货");
    map.keySet().forEach(salesOrderId -> {
      if (Assert.isTrue(dispatchOrder.isGetWaybill())) {
        salesOrderService.getBizLogger().log(salesOrder.getSalesOrderId(), BizLogger.DISPATCH,
            "面单策略为由OMS获取面单");
      }
      salesOrderService.getBizLogger().log(salesOrderId, BizLogger.DISPATCH, "手工配货");
    });
  }

  /**
   * 获取电子面单.
   */
  @Override
  public void getWayBill(DispatchOrder dispatchOrder, Express express) {
    if (express.getWaybillType() == null || express.getWaybillType() == WaybillType.NONE) {
      dispatchOrder.getDetails().stream().map(DispatchOrderDetail::getSalesOrderId).distinct()
          .forEach(id -> salesOrderService.getBizLogger()
              .log(id, BizLogger.DISPATCH, "未获取面单，原因：配货快递无面单配置或非电子面单。"));
      return;
    }
    Store expressStore = storeService.getByKey(express.getSetting().getStoreId());
    Store orderStore = storeService.getByKey(dispatchOrder.getStoreId());
    GlobalExpressMallMapping mapping = globalExpressMallMappingService
        .getMallExpressMapping(express.getGlobalExpressId(), expressStore.getMallType());
    //封装获取面单请求
    WaybillGetRequest request = new WaybillGetRequest(expressStore);
    request.setOrderStore(orderStore);
    request.setExpress(express);
    request.setDispatchOrder(dispatchOrder);
    request.setGlobalExpressCode(mapping.getOuterCode());
    request.setTradeId(dispatchOrder.getDetails().get(0).getTradeId());

    //发送请求返回结果
    LogisticsBridge logisticsBridge =
        mallBridgeFactory.getLogisticsBridge(expressStore.getMallType());
    WaybillGetResponse response = logisticsBridge.getWaybill(request);
    if (!response.isSuccess()) {
      LOGGER.error("交易号{}获取快递{}电子面单失败，错误信息：{}",
          request.getTradeId(), express.getExpressName(), response.getResult());
      return;
    }
    WaybillInfo info = new WaybillInfo();
    info.setWaybillCode(response.getWaybillCode());
    info.setBigShortName(response.getBigShortName());
    info.setPackageCenterCode(response.getPackageCenterCode());
    info.setPackageCenterName(response.getPackageCenterName());
    info.setSecondSectionCode(response.getSecondSectionCode());
    info.setThirdSectionCode(response.getThirdSectionCode());
    info.setMallExpressId(mapping.getOuterId());
    info.setMallExpressCode(mapping.getOuterCode());
    info.setMallExpressName(mapping.getOuterName());
    dispatchOrder.setWaybillInfo(info);
    dispatchOrder.setSuggestExpressNo(response.getWaybillCode());
    dispatchOrder.setGetWaybill(true);
  }

  /**
   * 线下发货.
   */
  @Override
  public void offlineDelivery(SalesOrder salesOrder, OfflineDeliveryBO bo) {
    //校验明细不能为空
    if (Assert.isEmpty(bo.getDetails())) {
      throw new OmsException("没有可正常配货的明细");
    }
    //校验：bo中的明细至少要有一项是属于salesOrder的
    boolean noneMatch = bo.getDetails().stream()
        .noneMatch(x -> x.getSalesOrderId().equals(salesOrder.getSalesOrderId()));
    if (noneMatch) {
      throw new OmsException("合单配货时必须包含主单中的明细");
    }
    DispatchOrder dispatchOrder = buildDispatchOrder(salesOrder, bo);
    /*
      构建配货单明细
      返回结果：
      Map<Long, List<SalesOrderDetail>>
      key：订单的ID, value：订单下配货的明细集合
     */
    Map<Long, List<SalesOrderDetail>> map = buildDispatchOrderDetail(dispatchOrder, salesOrder, bo,
        null);
    mergeOrder(salesOrder, bo, dispatchOrder, map);
    getMqProducer().send(new DispatchOrderCreateMessage(dispatchOrder.getDispatchOrderId()));
    BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.ADD, "线下发货");
    map.keySet().forEach(salesOrderId ->
        salesOrderService.getBizLogger().log(salesOrderId, BizLogger.DISPATCH, "线下发货"));
  }

  /**
   * wms物流通知.
   */
  @Override
  public void wmsExpressNotice(DispatchOrder dispatchOrder, WmsExpressNoticeBo bo) {
    Express express = bo.getExpress();
    String noticeExpressNo = bo.getExpressNo();
    Store store = storeService.getByKey(dispatchOrder.getStoreId());
    List<Long> resetDetailIds = new ArrayList<>(dispatchOrder.getDetails().size());
    List<Long> noticeDetailIds = new ArrayList<>(dispatchOrder.getDetails().size());
    SalesOrderDetail detail;
    for (DispatchOrderDetail dispatchOrderDetail : dispatchOrder.getDetails()) {
      detail = salesOrderDetailService.getByKey(dispatchOrderDetail.getSalesOrderDetailId());
      if (detail.getMallSalesOrderDetailStatus()
          == MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS &&
          !Assert.isEmpty(detail.getExpressNo()) && !detail.getExpressNo()
          .equals(noticeExpressNo)) {
        resetDetailIds.add(detail.getSalesOrderDetailId());
      } else if (Assert.isEmpty(detail.getExpressNo()) || detail.getMallSalesOrderDetailStatus()
          == MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS) {
        noticeDetailIds.add(detail.getSalesOrderDetailId());
      } else {
        salesOrderService.getBizLogger().log(detail.getSalesOrderId(), BizLogger.DISPATCH,
            "订单明细平台状态异常，回传失败，sku编码：{}", detail.getSkuCode());
      }
    }

    DispatchOrder update = new DispatchOrder();
    update.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    update.setSuggestExpressId(express.getExpressId());
    update.setSuggestExpressName(express.getExpressName());
    update.setSuggestExpressNo(noticeExpressNo);
    update.setVersion(bo.getVersion());
    update(update);

    GlobalExpressMallMapping mapping = globalExpressMallMappingService
        .getMallExpressMapping(express.getGlobalExpressId(), store.getMallType());
    if (mapping == null) {
      throw new OmsException("快递异常，无对应标准快递，物流通知失败！");
    }

    if (!Assert.isEmpty(noticeDetailIds)) {
      //配货单中无推荐快递，需要物流通知
      getMqProducer().send(new DispatchOrderExpressNoticeMessage(dispatchOrder.getDispatchOrderId(),
          mapping.getOuterId(), mapping.getOuterCode(), mapping.getOuterName(), noticeExpressNo,
          express.getExpressName(), noticeDetailIds));
      BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.UPDATE,
          "仓库物流通知回传，建议快递单号：{}，建议快递名称：{}",
          noticeExpressNo, express.getExpressName());
    }
    if (!Assert.isEmpty(resetDetailIds)) {
      //快递单号不同，修改平台快递单号
      getMqProducer().send(new MallDeliveryResetExpressMessage(dispatchOrder.getDispatchOrderId(),
          noticeExpressNo, mapping.getOuterId(), mapping.getOuterCode(), mapping.getOuterName(),
          resetDetailIds));
      BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.UPDATE,
          "仓库物流通知修改，建议快递单号：{}，建议快递名称：{}",
          noticeExpressNo, express.getExpressName());
    }
  }

  @Override
  public void sinpleUpdate(DispatchOrder updateInfo) {
    update(updateInfo);
  }

  @Override
  public void reposting(DispatchOrder dispatchOrder) {
    if (dispatchOrder.getStatus() == DispatchOrderStatus.DELIVERED) {
      getMqProducer()
          .send(new DataRepostingMessage(dispatchOrder.getDispatchOrderId(),
              "trade.dispatch.order"));
    }
  }

  /**
   * 配货合单. 私有方法
   */
  private void mergeOrder(SalesOrder salesOrder, DispatchBO bo, DispatchOrder dispatchOrder,
      Map<Long, List<SalesOrderDetail>> map) {
    //合单
    if (map.size() > 1) {
      Map<Long, SalesOrder> orderMap = new HashMap<>(map.size() + 1);
      orderMap.put(salesOrder.getSalesOrderId(), salesOrder);
      StringBuilder sellerMemo = new StringBuilder();
      StringBuilder buyerMemo = new StringBuilder();
      if (!Assert.isEmpty(dispatchOrder.getSellerMemo())) {
        sellerMemo.append(dispatchOrder.getSellerMemo());
      }
      if (!Assert.isEmpty(dispatchOrder.getBuyerMemo())) {
        buyerMemo.append(dispatchOrder.getBuyerMemo());
      }
      SalesOrder invoiceOrder = null;
      if (!Assert.isEmpty(salesOrder.getInvoices())) {
        invoiceOrder = salesOrder;
      }
      for (Long salesOrderId : map.keySet()) {
        if (!salesOrderId.equals(salesOrder.getSalesOrderId())) {
          SalesOrder order = salesOrderService.getDispatchInfo(salesOrderId);
          if (!Assert.isEmpty(order.getSub().getSellerMemo())) {
            sellerMemo.append(order.getSub().getSellerMemo());
          }
          if (!Assert.isEmpty(order.getSub().getBuyerMemo())) {
            buyerMemo.append(order.getSub().getBuyerMemo());
          }
          if (order.getSub().getSalesOrderType() == SalesOrderType.EXCHANGE
              && Assert.isFalse(dispatchOrder.isHasExchange())) {
            dispatchOrder.setHasExchange(true);
          }
          if (invoiceOrder == null && !Assert.isEmpty(order.getInvoices())) {
            invoiceOrder = order;
          }
          orderMap.put(salesOrderId, order);
        }
      }
      dispatchOrder.setSellerMemo(sellerMemo.toString());
      dispatchOrder.setBuyerMemo(buyerMemo.toString());
      dispatchOrder.setMerge(true);
      if (invoiceOrder != null) {
        if (invoiceOrder.getInvoices().size() > 1) {
          salesOrderService.getBizLogger().log(invoiceOrder.getSalesOrderId(),
              BizLogger.DISPATCH, "订单的发票数大于1张，配货目前只支持1张发票。");
        }
        SalesOrderInvoice salesOrderInvoice = invoiceOrder.getInvoices().get(0);
        InvoiceInfo invoiceInfo = new InvoiceInfo();
        invoiceInfo.setModifiedTime(salesOrderInvoice.getModifiedTime());
        invoiceInfo.setCreatedTime(salesOrderInvoice.getCreatedTime());
        invoiceInfo.setInvoiceTitle(salesOrderInvoice.getInvoiceTitle());
        invoiceInfo.setInvoiceContent(salesOrderInvoice.getInvoiceContent());
        invoiceInfo.setInvoiceType(salesOrderInvoice.getInvoiceType());
        invoiceInfo.setActualAmount(dispatchOrder.getActualAmount());
        invoiceInfo.setTaxpayerId(salesOrderInvoice.getTaxpayerId());
        invoiceInfo.setGmfAddress(salesOrderInvoice.getGmfAddress());
        invoiceInfo.setGmfBankName(salesOrderInvoice.getGmfBankName());
        invoiceInfo.setGmfBankNo(salesOrderInvoice.getGmfBankNo());
        invoiceInfo.setGmfMobile(salesOrderInvoice.getGmfMobile());
        dispatchOrder.setNeedInvoice(true);
        dispatchOrder.setInvoiceInfo(invoiceInfo);
      }

      //编程式事务
      try {
        getTransactionTemplate().execute(() -> {
          for (SalesOrder order : orderMap.values()) {
            SalesOrderDispatchBO salesOrderDispatchBO = new SalesOrderDispatchBO();
            salesOrderDispatchBO.setVersion(order.getVersion());
            salesOrderDispatchBO.setDetails(map.get(order.getSalesOrderId()));
            salesOrderService.dispatch(order, salesOrderDispatchBO);
          }
          create(dispatchOrder);
        });
      } catch (Exception e) {
        LOGGER.error("配货合单失败，配货单信息:{}，合单订单:{}", JsonUtil.toJson(dispatchOrder),
            JsonUtil.toJson(orderMap.values()));
        LOGGER.error("配货合单失败，堆栈信息:", e);
        throw new OmsException("创建配货单失败");
      }
    } else {
      SalesOrderDispatchBO salesOrderDispatchBO = new SalesOrderDispatchBO();
      salesOrderDispatchBO.setVersion(bo.getVersion());
      salesOrderDispatchBO.setDetails(map.get(salesOrder.getSalesOrderId()));

      //编程式事务
      try {
        getTransactionTemplate().execute(() -> {
          create(dispatchOrder);
          salesOrderService.dispatch(salesOrder, salesOrderDispatchBO);
        });
      } catch (OmsException e) {
        throw e;
      } catch (Exception e) {
        LOGGER.error("配货单创建失败，配货单信息:{}，订单:{}", JsonUtil.toJson(dispatchOrder),
            JsonUtil.toJson(salesOrder));
        LOGGER.error("配货单创建失败，堆栈信息:", e);
        throw new OmsException("创建配货单失败");
      }
    }
    getMqProducer().send(new SalesOrderDispatchedMessage(salesOrder.getSalesOrderId(),
        salesOrder.getDispatchStatus() == DispatchStatus.PART, true));
    getMqProducer().send(new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
        EventType.MODIFIED));
  }

  /**
   * 创建配货单.私有方法
   */
  private DispatchOrder buildDispatchOrder(SalesOrder salesOrder, DispatchBO bo) {
    DispatchOrder dispatchOrder = new DispatchOrder();
    dispatchOrder.setDispatchOrderCode(dispatchOrderCodeGenerator.next());
    dispatchOrder.setActualAmount(0D);
    dispatchOrder.setDiscountAmount(0D);
    dispatchOrder.setDistributionAmount(0D);
    dispatchOrder.setSellingAmount(0D);
    dispatchOrder.setSettlementAmount(0D);
    dispatchOrder.setQuantity(0);
    dispatchOrder.setWeight(0D);
    dispatchOrder.setMerge(false);
    dispatchOrder
        .setHasExchange(salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXCHANGE);
    dispatchOrder.setWmsCancel(false);
    dispatchOrder.setWmsStatus(WmsOrderStatus.ACCEPT);
    dispatchOrder.setCreator(BizContext.getNickname());
    dispatchOrder.setCod(salesOrder.getSub().isCod());
    dispatchOrder.setCodAmount(salesOrder.getCodAmount());
    dispatchOrder.setCountryId(bo.getCountryId());
    dispatchOrder.setProvinceId(bo.getProvinceId());
    dispatchOrder.setCityId(bo.getCityId());
    dispatchOrder.setDistrictId(bo.getDistrictId());
    dispatchOrder.setCountryName(bo.getCountryName());
    dispatchOrder.setProvinceName(bo.getProvinceName());
    dispatchOrder.setCityName(bo.getCityName());
    dispatchOrder.setDistrictName(bo.getDistrictName());
    dispatchOrder.setContact(bo.getContact());
    dispatchOrder.setMobile(bo.getMobile());
    dispatchOrder.setTelephone(bo.getTelephone());
    dispatchOrder.setStoreId(salesOrder.getStoreId());
    dispatchOrder.setStoreName(salesOrder.getStoreName());
    dispatchOrder.setBuyerMemo(salesOrder.getSub().getBuyerMemo());
    dispatchOrder.setSellerMemo(salesOrder.getSub().getSellerMemo());
    dispatchOrder.setRemark(salesOrder.getRemark());
    if (!Assert.isEmpty(dispatchOrder.getRemark()) && !Assert.isEmpty(bo.getRemark())) {
      dispatchOrder.setRemark(
          dispatchOrder.getRemark().concat(System.lineSeparator())
              .concat(bo.getRemark()));
    }
    dispatchOrder.setSuggestExpressId(salesOrder.getSuggestExpressId());
    dispatchOrder.setSuggestExpressName(salesOrder.getSuggestExpressName());
    dispatchOrder.setSuggestExpressNo(salesOrder.getSuggestExpressNo());
    dispatchOrder.setMemberId(salesOrder.getSub().getMemberId());
    dispatchOrder.setMemberName(salesOrder.getSub().getMemberName());
    dispatchOrder.setPackageNo(salesOrder.getSub().getPackageNo());
    dispatchOrder.setUrgent(salesOrder.isUrgent());
    dispatchOrder.setZipcode(salesOrder.getSub().getZipcode());
    dispatchOrder.setAddress(bo.getAddress());
    dispatchOrder.setStatus(DispatchOrderStatus.CREATED);
    dispatchOrder.setOutStatus(OutStatus.NO_OUT);
    dispatchOrder.setSuggestExpressNo(salesOrder.getSuggestExpressNo());
    dispatchOrder.setDeliveryFinish(false);
    dispatchOrder.setWmsStatus(WmsOrderStatus.ACCEPT);
    if (!Assert.isNull(bo.getOfflineDelivery()) && bo.getOfflineDelivery()) {
      dispatchOrder.setOfflineDelivery(bo.getOfflineDelivery());
    } else {
      dispatchOrder.setOfflineDelivery(false);
    }
    return dispatchOrder;
  }

  /**
   * 创建配货单明细.私有方法
   */
  private Map<Long, List<SalesOrderDetail>> buildDispatchOrderDetail(DispatchOrder dispatchOrder,
      SalesOrder salesOrder, DispatchBO<?> bo,
      ActionFunction<DispatchDetailBO, DispatchOrderDetail> action) {
    List<DispatchOrderDetail> dispatchOrderDetails = new ArrayList<>(bo.getDetails().size());
    Map<Long, List<SalesOrderDetail>> map = new HashMap<>(bo.getDetails().size());
    for (DispatchDetailBO detailBO : bo.getDetails()) {
      dispatchOrder.setActualAmount(
          MathUtil.plus(detailBO.getActualAmount(), dispatchOrder.getActualAmount()));
      dispatchOrder.setDiscountAmount(
          MathUtil.plus(detailBO.getDiscountAmount(), dispatchOrder.getDiscountAmount()));
      dispatchOrder.setDistributionAmount(
          MathUtil.plus(detailBO.getDistributionAmount(), dispatchOrder.getDistributionAmount()));
      dispatchOrder.setSellingAmount(
          MathUtil.plus(detailBO.getSellingAmount(), dispatchOrder.getSellingAmount()));
      dispatchOrder.setSettlementAmount(
          MathUtil.plus(detailBO.getSettlementAmount(), dispatchOrder.getSettlementAmount()));
      dispatchOrder.setQuantity(detailBO.getQuantity() + dispatchOrder.getQuantity());

      //封装明细信息
      DispatchOrderDetail detail = new DispatchOrderDetail();
      detail.setNoticeQuantity(detailBO.getQuantity());
      detail.setOutQuantity(0);
      detail.setDistributionPrice(detailBO.getDistributionPrice());
      detail.setSellingPrice(detailBO.getSellingPrice());
      detail.setSettlementPrice(detailBO.getSettlementPrice());
      detail.setActualAmount(detailBO.getActualAmount());
      detail.setDiscountAmount(detailBO.getDiscountAmount());
      detail.setSellingAmount(detailBO.getSellingAmount());
      detail.setSettlementAmount(detailBO.getSettlementAmount());
      detail.setDistributionAmount(detailBO.getDistributionAmount());
      detail.setProductId(detailBO.getProductId());
      detail.setProductCode(detailBO.getProductCode());
      detail.setProductName(detailBO.getProductName());
      detail.setSkuId(detailBO.getSkuId());
      detail.setSkuCode(detailBO.getSkuCode());
      detail.setSkuName(detailBO.getSkuName());
      detail.setSalesOrderId(detailBO.getSalesOrderId());
      detail.setSalesOrderCode(detailBO.getSalesOrderCode());
      detail.setSalesOrderDetailId(detailBO.getSalesOrderDetailId());
      detail.setTradeId(detailBO.getTradeId());
      detail.setStatus(DispatchOrderDetailStatus.WAITING);
      detail.setHasInvoice(salesOrder.getSub().isHasInvoice()
          && salesOrder.getDispatchStatus() == DispatchStatus.NONE);
      if (action != null) {
        action.apply(detailBO, detail);
      }
      dispatchOrderDetails.add(detail);
      if (map.containsKey(detailBO.getSalesOrderId())) {
        map.get(detail.getSalesOrderId()).add(detailBO);
      } else {
        List<SalesOrderDetail> list = new ArrayList<>(bo.getDetails().size());
        list.add(detailBO);
        map.put(detailBO.getSalesOrderId(), list);
      }

    }
    dispatchOrder.setDetails(dispatchOrderDetails);
    return map;
  }

  /**
   * 自动配货. 自动配货的主入口
   */
  @Override
  public void autoDispatch(DispatchOrder dispatchOrder, AutoDispatchBO bo) {
    Map<Long, SalesOrderDispatchBO> map = new HashMap<>();
    SalesOrderDetail salesOrderDetail;
    SalesOrderDispatchBO dispatchBO;
    List<Long> resetDetailIds = new ArrayList<>(dispatchOrder.getDetails().size());
    List<Long> noticeDetailIds = new ArrayList<>(dispatchOrder.getDetails().size());
    for (DispatchOrderDetail detail : dispatchOrder.getDetails()) {
      dispatchBO = map.get(detail.getSalesOrderId());
      if (dispatchBO == null) {
        dispatchBO = new SalesOrderDispatchBO();
        dispatchBO.setVersion(bo.getVersionMap().get(detail.getSalesOrderId()));
        dispatchBO.setDetails(new ArrayList<>());
        map.put(detail.getSalesOrderId(), dispatchBO);
      }
      salesOrderDetail = salesOrderDetailService.getByKey(detail.getSalesOrderDetailId());
      dispatchBO.getDetails().add(salesOrderDetail);
      if (salesOrderDetail.getMallSalesOrderDetailStatus()
          == MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS &&
          !Assert.isEmpty(salesOrderDetail.getExpressNo())) {
        resetDetailIds.add(salesOrderDetail.getSalesOrderDetailId());
      } else if (salesOrderDetail.getMallSalesOrderDetailStatus()
          == MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS) {
        noticeDetailIds.add(salesOrderDetail.getSalesOrderDetailId());
      } else {
        salesOrderService.getBizLogger()
            .log(salesOrderDetail.getSalesOrderId(), BizLogger.DISPATCH,
                "订单明细平台状态异常，回传失败，sku编码：{}", salesOrderDetail.getSkuCode());
      }
    }
    Map<SalesOrder, SalesOrderDispatchBO> salesOrderMap = new HashMap<>(map.entrySet().size());
    //key：配货成功的销售订单的Id   value：该订单下配货成功的明细
    for (Map.Entry<Long, SalesOrderDispatchBO> entry : map.entrySet()) {
      SalesOrder salesOrder = salesOrderService.getDispatchInfo(entry.getKey());
      salesOrderMap.put(salesOrder, entry.getValue());
    }

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderMap.forEach((x, y) -> salesOrderService.dispatch(x, y));
        create(dispatchOrder);
      });
    } catch (Exception e) {
      LOGGER.error("自动配货失败，配货单信息:{}", JsonUtil.toJson(dispatchOrder));
      LOGGER.error("自动配货失败，堆栈信息:", e);
      throw new OmsException("自动配货失败");
    }

    if (Assert.isTrue(dispatchOrder.isGetWaybill())) {
      WaybillInfo waybillInfo = dispatchOrder.getWaybillInfo();
      List<Message> messages = new ArrayList<>(salesOrderMap.size() + 1);
      messages.add(new DispatchOrderCreateMessage(dispatchOrder.getDispatchOrderId()));
      if (!Assert.isEmpty(noticeDetailIds)) {
        messages.add(new DispatchOrderExpressNoticeMessage(dispatchOrder.getDispatchOrderId(),
            waybillInfo.getMallExpressId(), waybillInfo.getMallExpressCode(),
            waybillInfo.getMallExpressName(), waybillInfo.getWaybillCode(),
            dispatchOrder.getSuggestExpressName(), noticeDetailIds));
      }
      if (!Assert.isEmpty(resetDetailIds)) {
        messages.add(new MallDeliveryResetExpressMessage(dispatchOrder.getDispatchOrderId(),
            waybillInfo.getWaybillCode(), waybillInfo.getMallExpressId(),
            waybillInfo.getMallExpressCode(), waybillInfo.getMallExpressName(), resetDetailIds));
      }
      getMqProducer().send(messages);
    } else {
      getMqProducer().send(new DispatchOrderCreateMessage(dispatchOrder.getDispatchOrderId()));
    }

    BIZ_LOGGER.log(dispatchOrder.getDispatchOrderId(), BizLogger.ADD, BizLogger.AUTO_DISPATCH);
    salesOrderMap.forEach((x, y) -> {
      getMqProducer().send(new SalesOrderDispatchedMessage(x.getSalesOrderId(),
          x.getDispatchStatus() == DispatchStatus.PART, true));
      getMqProducer().send(new GeneralMessage(OmsModule.SALES_ORDER, x.getSalesOrderId(),
          EventType.MODIFIED));
      salesOrderService.getBizLogger().log(x.getSalesOrderId(), BizLogger.AUTO_DISPATCH);
    });
  }

  @Override
  public PageList<DispatchOrder> listPageByConditions(DispatchOrderQuery filter, int page,
      int pageSize) {
    validatePageParameter(page, pageSize);
    return dao.listPageByConditions(filter, page, pageSize);
  }

  @Override
  public List<WmsLogBO> listWmsLog(Long dispatchOrderId) {
    ArrayList<WmsLogBO> list = new ArrayList<>();
    DispatchOrder dispatchOrder = super.getByKey(dispatchOrderId);
    Warehouse warehouse = warehouseService.getByKey(dispatchOrder.getWarehouseId());
    if (Assert.isNull(warehouse)) {
      return null;
    }
    DeliveryOrderBridge deliveryOrderBridge = wmsBridgeFactory
        .getDeliveryOrderBridge(warehouse.getWmsApp().getWmsType());

    OrderProcessQueryRequest request = new OrderProcessQueryRequest(warehouse);
    request.setOmsCode(dispatchOrder.getDispatchOrderCode());
    request.setOrderType(OrderType.B2C_DISPATCH_ORDER);
    OrderProcessQueryResponse response = deliveryOrderBridge.queryOrderProcess(request);
    if (response.isSuccess()) {
      List<OrderProcessQueryResponse.Process> processes = response.getProcesses();
      if (!Assert.isEmpty(processes)) {
        for (OrderProcessQueryResponse.Process process : processes) {
          WmsLogBO wmsLogBO = new WmsLogBO();
          wmsLogBO.setOperatorName(process.getOperatorName());
          wmsLogBO.setOperateInfo(process.getOperateInfo());
          wmsLogBO.setRemark(process.getRemark());
          wmsLogBO.setOperateTime(process.getOperateTime());
          list.add(wmsLogBO);
        }
      }
      return list;
    } else {
      LOGGER.info("查询wms日志失败,堆栈信息:", response);
      throw new OmsException(response.getMessage());
    }
  }

  /**
   * 重设配货单状态.
   */
  private void resetStatus(DispatchOrder dispatchOrder, VersionBO bo) {
    List<DispatchOrderDetail> simpleDetails = dispatchOrderDetailService
        .listSimple(dispatchOrder.getDispatchOrderId());
    DispatchOrderUtil.recountStatus(dispatchOrder, simpleDetails);

    DispatchOrder order = new DispatchOrder();
    order.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
    order.setVersion(bo.getVersion());
    modify(order);
  }
}