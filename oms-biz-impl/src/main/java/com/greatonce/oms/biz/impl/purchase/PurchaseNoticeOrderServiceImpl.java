package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderService;
import com.greatonce.oms.biz.purchase.PurchaseOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseOrderService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.stock.StockTransitService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.purchase.PurchaseNoticeOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO.WmsAutoInDetail;
import com.greatonce.oms.bridge.wms.StockInOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.StockInOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest.StockInOrderDetail;
import com.greatonce.oms.bridge.wms.response.StockInOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.dao.purchase.PurchaseNoticeOrderDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.purchase.PurchaseNoticeStatus;
import com.greatonce.oms.domain.enums.purchase.PurchaseOrderNoticeStatus;
import com.greatonce.oms.domain.enums.stock.StockType;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrderDetail;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderDetailQuery;
import com.greatonce.oms.query.purchase.PurchaseNoticeOrderQuery;
import com.greatonce.oms.query.purchase.PurchaseOrderDetailQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
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
 * 采购入库通知单. PurchaseNoticeOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class PurchaseNoticeOrderServiceImpl extends
    AbstractVersionService<PurchaseNoticeOrder, PurchaseNoticeOrderQuery> implements
    PurchaseNoticeOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.PURCHASE_NOTICE);
  private static final Logger LOGGER = LoggerFactory
      .getLogger(PurchaseNoticeOrderServiceImpl.class);
  @Autowired
  private PurchaseNoticeOrderDao dao;
  @Resource
  private IdGenerator purchaseNoticeOrderIdGenerator;
  @Resource
  private CodeGenerator purchaseNoticeOrderCodeGenerator;
  @Resource
  private PurchaseNoticeOrderDetailService purchaseNoticeOrderDetailService;
  @Resource
  private PurchaseOrderDetailService purchaseOrderDetailService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private PurchaseOrderService purchaseOrderService;
  @Autowired
  private StockTransitService stockTransitService;
  @Autowired
  private StockService stockService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;

  @Override
  protected QueryDao<PurchaseNoticeOrder, PurchaseNoticeOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.purchaseNoticeOrderIdGenerator;
  }

  @Override
  protected void initDefaultValue(PurchaseNoticeOrder record) {
    super.initDefaultValue(record);
    record.setVersion(0);
    record.setStatus(PurchaseNoticeStatus.CREATED);
    record.setPurchaseNoticeOrderCode(purchaseNoticeOrderCodeGenerator.next());
    record.setInStatus(InStatus.NO_IN);
    record.setCreator(BizContext.getNickname());
    record.getDetails()
        .forEach(item -> item.setPurchaseNoticeOrderId(record.getPurchaseNoticeOrderId()));
  }

  @Override
  public int create(PurchaseNoticeOrder record) {
    if (record.getDetails().size() <= 0) {
      throw new OmsException("当前没有可生成通知单的明细");
    }
    initDefaultValue(record);
    List<PurchaseOrderDetail> purchaseOrderDetailList = new ArrayList<>();
    record.getDetails().forEach(item -> {
      purchaseOrderDetailList.add(DomainUtil
          .createPurchaseOrderDetail(item.getPurchaseOrderDetailId(), item.getNoticeQuantity()));
    });
    try {
      int result = getTransactionTemplate().executeWithResult(() -> {
        purchaseNoticeOrderDetailService.batchCreate(record.getDetails());
        //处理采购订单通知数量
        purchaseOrderDetailService.batchModify(purchaseOrderDetailList);
        updateNoticeStatus(record);
        return insert(record);
      });
      getMqProducer().send(
          new GeneralMessage(OmsModule.PURCHASE_NOTICE, record.getPurchaseNoticeOrderId(),
              EventType.CREATED));
      BIZ_LOGGER.log(record.getPurchaseNoticeOrderId(), BizLogger.ADD, "新增采购通知单");
      return result;
    } catch (Exception e) {
      LOGGER.error("采购通知单保存失败，采购通知单：{}", JsonUtil.toJson(record));
      LOGGER.error("采购通知单保存失败，堆栈信息：", e);
      throw new OmsException("采购通知单保存失败！");
    }
  }

  /**
   * 调整采购单通知状态
   */
  public void updateNoticeStatus(PurchaseNoticeOrder record) {
    PurchaseOrder purchaseOrder = purchaseOrderService.getByKey(record.getPurchaseOrderId());
    if (Assert.isNull(purchaseOrder)) {
      throw new OmsException("未找到当前通知单对应的采购单");
    }
    PurchaseOrderDetailQuery orderDetailFilter = new PurchaseOrderDetailQuery();
    orderDetailFilter.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
    boolean allNotice = true;
    List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService
        .list(orderDetailFilter);
    for (PurchaseOrderDetail detail : purchaseOrderDetails) {
      if (detail.getQuantity() > detail.getNoticeQuantity()) {
        allNotice = false;
      }
    }
    PurchaseOrder updateInfo = new PurchaseOrder();
    updateInfo.setPurchaseOrderId(record.getPurchaseOrderId());
    updateInfo.setNoticeOrderStatus(
        allNotice ? PurchaseOrderNoticeStatus.ALL_NOTICE : PurchaseOrderNoticeStatus.PART_NOTICE);
    updateInfo.setVersion(purchaseOrder.getVersion());
    purchaseOrderService.modify(updateInfo);
  }

  @Override
  public void noticeWms(PurchaseNoticeOrder purchaseNoticeOrder, VersionBO bo) {
    if (purchaseNoticeOrder.getStatus() != PurchaseNoticeStatus.CREATED
        && purchaseNoticeOrder.getStatus() != PurchaseNoticeStatus.NOTICE_FAILED) {
      throw new OmsException("只有新建或推送失败的通知单才能推送给仓库");
    }

    Warehouse warehouse = warehouseService.getByKey(purchaseNoticeOrder.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");
    StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
        .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());

    StockInOrderCreateRequest request = new StockInOrderCreateRequest(warehouse);
    List<PurchaseNoticeOrderDetail> purchaseNoticeOrderDetails = purchaseNoticeOrderDetailService
        .listDetails(purchaseNoticeOrder.getPurchaseNoticeOrderId());

    request.setOmsCode(purchaseNoticeOrder.getPurchaseNoticeOrderCode());
    request.setOrderType(OrderType.PURCHASE_NOTICE_ORDER);
    request.setCreatedTime(purchaseNoticeOrder.getCreatedTime());
    request.setSupplierCode(purchaseNoticeOrder.getSupplierCode());
    request.setSupplierName(purchaseNoticeOrder.getSupplierName());
    request.setPurchaseOrderCode(purchaseNoticeOrder.getPurchaseOrderCode());

    //仓库信息
    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setSenderArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());

    //拼接请求明细数据
    List<StockInOrderDetail> stockInOrderDetails = new ArrayList<>(
        purchaseNoticeOrderDetails.size());
    for (PurchaseNoticeOrderDetail purchaseNoticeOrderDetail : purchaseNoticeOrderDetails) {
      StockInOrderDetail detail = new StockInOrderDetail();
      detail.setWmsSkuId(FormatUtil.formatLong(purchaseNoticeOrderDetail.getSkuId()));
      detail.setQuantity(purchaseNoticeOrderDetail.getNoticeQuantity());
      detail.setProductCode(purchaseNoticeOrderDetail.getProductCode());
      detail.setProductName(purchaseNoticeOrderDetail.getProductName());
      detail.setSkuCode(purchaseNoticeOrderDetail.getSkuCode());
      stockInOrderDetails.add(detail);
    }
    request.setDetails(stockInOrderDetails);

    StockInOrderCreateResponse response = stockInOrderBridge.createOrder(request);
    PurchaseNoticeOrder updateInfo = new PurchaseNoticeOrder();
    updateInfo.setPurchaseNoticeOrderId(purchaseNoticeOrder.getPurchaseNoticeOrderId());
    updateInfo.setVersion(purchaseNoticeOrder.getVersion());
    if (response.isSuccess()) {
      updateInfo.setStatus(PurchaseNoticeStatus.NOTICED);
    } else {
      updateInfo.setStatus(PurchaseNoticeStatus.NOTICE_FAILED);
    }
    purchaseNoticeOrder.setStatus(updateInfo.getStatus());
    modify(updateInfo);
    BIZ_LOGGER
        .log(purchaseNoticeOrder.getPurchaseNoticeOrderId(), BizLogger.NOTICE_WMS,
            response.isSuccess() ? "成功" : response.getMessage());
  }

  @Override
  public void cancel(PurchaseNoticeOrder purchaseNoticeOrder, PurchaseNoticeOrderCancelBO bo) {
    if (InStatus.NO_IN != purchaseNoticeOrder.getInStatus()) {
      throw new OmsException("当前通知单已入库");
    }
    if (PurchaseNoticeStatus.NOTICED == purchaseNoticeOrder.getStatus()) {
      Warehouse warehouse = warehouseService.getByKey(purchaseNoticeOrder.getWarehouseId());
      final StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
          .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
      StockInOrderCancelRequest request = new StockInOrderCancelRequest(warehouse);
      request.setOmsCode(purchaseNoticeOrder.getPurchaseNoticeOrderCode());
      request.setOrderType(OrderType.PURCHASE_NOTICE_ORDER);
      StockInOrderCancelResponse response = stockInOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(purchaseNoticeOrder, bo);
      } else {
        throw new OmsException("仓库取消失败:" + response.getMessage());
      }
    } else {
      cancelOms(purchaseNoticeOrder, bo);
    }
  }

  private void cancelOms(PurchaseNoticeOrder purchaseNoticeOrder, PurchaseNoticeOrderCancelBO bo) {
    PurchaseNoticeOrder updateInfo = new PurchaseNoticeOrder();
    updateInfo.setPurchaseNoticeOrderId(purchaseNoticeOrder.getPurchaseNoticeOrderId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(PurchaseNoticeStatus.CANCEL);

    PurchaseNoticeOrderDetailQuery detailFilter = new PurchaseNoticeOrderDetailQuery();
    detailFilter.setPurchaseNoticeOrderId(purchaseNoticeOrder.getPurchaseNoticeOrderId());
    List<PurchaseNoticeOrderDetail> noticeOrderDetails = purchaseNoticeOrderDetailService
        .list(detailFilter);

    List<PurchaseOrderDetail> purchaseOrderDetails = new ArrayList<>();
    for (PurchaseNoticeOrderDetail detail : noticeOrderDetails) {
      PurchaseOrderDetail orderDetail = purchaseOrderDetailService
          .getByKey(detail.getPurchaseOrderDetailId());
      PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
      purchaseOrderDetail.setPurchaseOrderDetailId(detail.getPurchaseOrderDetailId());
      purchaseOrderDetail
          .setNoticeQuantity(orderDetail.getNoticeQuantity() - detail.getNoticeQuantity());
      purchaseOrderDetails.add(purchaseOrderDetail);
    }
    try {
      getTransactionTemplate().execute(() -> {
        purchaseOrderDetailService.batchModify(purchaseOrderDetails);
        modify(updateInfo);
        updateNoticeStatus(purchaseNoticeOrder);
      });
      BIZ_LOGGER.log(purchaseNoticeOrder.getPurchaseNoticeOrderId(), BizLogger.CANCEL, "原因：{}",
          bo.getReason());
    } catch (Exception e) {
      LOGGER.error("取消采购通知单失败，采购通知单：{}", JsonUtil.toJson(updateInfo));
      LOGGER.error("取消采购通知单失败，堆栈信息：", e);
      throw new OmsException("取消采购通知单失败！");
    }
  }

  @Override
  public PurchaseNoticeOrder getByCode(String orderCode) {
    PurchaseNoticeOrder eg = new PurchaseNoticeOrder();
    eg.setPurchaseNoticeOrderCode(orderCode);
    return getByExample(eg);
  }

  /**
   * 仓库入库.
   */
  public void wmsAutoIn(PurchaseNoticeOrder purchaseNoticeOrder, WmsAutoInBO wmsAutoInBO) {
    if (PurchaseNoticeStatus.CANCEL == purchaseNoticeOrder.getStatus()) {
      throw new OmsException("单据已取消");
    }
    if (purchaseNoticeOrder.getInStatus() == InStatus.ALL_IN) {
      LOGGER.info("单据已全部入库，不再处理，WMS单号：{}", wmsAutoInBO.getWmsOrderCode());
      return;
    }

    VirtualWarehouse substandardWarehouse = virtualWarehouseService
        .getSubstandardWarehouse(purchaseNoticeOrder.getWarehouseId());
    List<PurchaseNoticeOrderDetail> details = purchaseNoticeOrderDetailService
        .listDetails(purchaseNoticeOrder.getPurchaseNoticeOrderId());
    List<PurchaseNoticeOrderDetail> updateDetails = new ArrayList<>(details.size());
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
    InStatus inStatus = InStatus.ALL_IN;
    for (PurchaseNoticeOrderDetail detail : details) {
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

    purchaseNoticeOrder
        .setInStatus(totalInQuantity < totalNoticeQuantity ? InStatus.PART_IN : InStatus.ALL_IN);
    purchaseNoticeOrder.setVersion(wmsAutoInBO.getVersion());
    purchaseNoticeOrder.setLastInTime(wmsAutoInBO.getInTime());
    purchaseNoticeOrder.setOuterCode(wmsAutoInBO.getWmsOrderCode());

    try {
      getTransactionTemplate().execute(() -> {
        purchaseNoticeOrderDetailService.batchModify(updateDetails);
        for (WmsAutoInDetail inDetail : wmsAutoInBO.getDetails()) {
          if (inDetail.getStockType() == StockType.QUALIFIED) {
            stockService.transitIn(purchaseNoticeOrder.getPurchaseNoticeOrderCode(),
                OrderType.PURCHASE_NOTICE_ORDER, inDetail.getSkuId(), inDetail.getSkuCode(),
                purchaseNoticeOrder.getWarehouseId(), purchaseNoticeOrder.getWarehouseName(),
                purchaseNoticeOrder.getVirtualWarehouseId(),
                purchaseNoticeOrder.getVirtualWarehouseName(),
                inDetail.getInQuantity());
          } else {
            Assert.notNull(substandardWarehouse, "系统未配置次品仓，不接收次品库存");
            stockService.transitIn(purchaseNoticeOrder.getPurchaseNoticeOrderCode(),
                OrderType.PURCHASE_NOTICE_ORDER, inDetail.getSkuId(), inDetail.getSkuCode(),
                substandardWarehouse, inDetail.getInQuantity());
          }
        }
        update(purchaseNoticeOrder);
      });
      details.forEach(x -> {
        getMqProducer().send(
            new StockChangedMessage(x.getSkuId(), BizContext.getNickname(),
                "采购通知单入库"));
      });
      getMqProducer().send(new GeneralMessage(OmsModule.PURCHASE_NOTICE,
          purchaseNoticeOrder.getPurchaseNoticeOrderId(), EventType.IN));
      BIZ_LOGGER.log(purchaseNoticeOrder.getPurchaseNoticeOrderId(), BizLogger.ENTRY, "WMS自动入库");
    } catch (Exception e) {
      LOGGER.error("采购通知单入库异常,退货通知单信息: {}", JsonUtil.toJson(purchaseNoticeOrder));
      LOGGER.error("采购通知单入库异常,堆栈信息: ", e);
      throw new OmsException("采购通知单入库异常");
    }

    PurchaseOrder purchaseOrder = purchaseOrderService
        .getByKey(purchaseNoticeOrder.getPurchaseOrderId());
    wmsAutoInBO.setVersion(purchaseOrder.getVersion());
    if (!Assert.isNull(wmsAutoInBO.getDetails())){
      wmsAutoInBO.getDetails().forEach(detail->detail.setPendingQuantity(detail.getInQuantity()));
    }
    purchaseOrderService.wmsAutoIn(purchaseOrder, wmsAutoInBO);
  }
}