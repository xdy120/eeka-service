package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.purchase.PurchaseNoticeOrderService;
import com.greatonce.oms.biz.purchase.PurchaseOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseOrderService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.stock.StockTransitService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO.WmsAutoInDetail;
import com.greatonce.oms.dao.purchase.PurchaseOrderDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.purchase.PurchaseOrderNoticeStatus;
import com.greatonce.oms.domain.enums.purchase.PurchaseStatus;
import com.greatonce.oms.domain.enums.stock.StockType;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrder;
import com.greatonce.oms.domain.purchase.PurchaseNoticeOrderDetail;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.purchase.PurchaseOrderDetailQuery;
import com.greatonce.oms.query.purchase.PurchaseOrderQuery;
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
 * 采购订单. PurchaseOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class PurchaseOrderServiceImpl extends
    AbstractVersionService<PurchaseOrder, PurchaseOrderQuery> implements PurchaseOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.PURCHASE_ORDER);
  private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseOrderServiceImpl.class);

  @Autowired
  private PurchaseOrderDao dao;
  @Resource
  private IdGenerator purchaseOrderIdGenerator;
  @Resource
  private CodeGenerator purchaseOrderCodeGenerator;
  @Resource
  private PurchaseOrderDetailService purchaseOrderDetailService;
  @Resource
  private StockTransitService stockTransitService;
  @Resource
  private StockService stockService;
  @Resource
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private PurchaseNoticeOrderService purchaseNoticeOrderService;

  @Override
  protected QueryDao<PurchaseOrder, PurchaseOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.purchaseOrderIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(PurchaseOrder record) {
    super.initDefaultValue(record);
    record.setVersion(0);
    record.setStatus(PurchaseStatus.CREATED);
    record.setPurchaseOrderCode(purchaseOrderCodeGenerator.next());
    record.setInStatus(InStatus.NO_IN);
    record.setCreator(BizContext.getNickname());
    record.setNoticeOrderStatus(PurchaseOrderNoticeStatus.NO_NOTICE);
    for (PurchaseOrderDetail item : record.getDetails()) {
      item.setPurchaseOrderId(record.getPurchaseOrderId());
    }
  }

  @Override
  public int create(PurchaseOrder record) {
    initDefaultValue(record);
    try {
      Integer result = getTransactionTemplate().executeWithResult(() -> {
        purchaseOrderDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getPurchaseOrderId(), BizLogger.ADD, "新增采购单");
      return result;
    } catch (Exception e) {
      LOGGER.error("采购单新增失败，退换货单：{}", JsonUtil.toJson(record));
      LOGGER.error("采购单新增失败，堆栈信息：", e);
      throw new OmsException("采购单新增失败，请联系管理员！");
    }
  }


  @Override
  public void audit(PurchaseOrder record, VersionBO bo) {
    if (record.getStatus() != PurchaseStatus.CREATED) {
      throw SysExceptions.STATUS_ERROR;
    }
    final List<PurchaseOrderDetail> details = purchaseOrderDetailService
        .listDetails(record.getPurchaseOrderId());
    record.setStatus(PurchaseStatus.AUDITED);
    record.setVersion(bo.getVersion());
    record.setAuditedTime(LocalDateTime.now());
    record.setAuditor(BizContext.getNickname());
    try {
      getTransactionTemplate().execute(() -> {
        details.forEach(item -> {
          stockService
              .adjustTransitQuantity(record.getPurchaseOrderCode(), OrderType.PURCHASE_ORDER,
                  item.getSkuId(), item.getSkuCode(),
                  record.getWarehouseId(), record.getWarehouseName(),
                  record.getVirtualWarehouseId(), record.getVirtualWarehouseName(),
                  item.getQuantity());
        });
        update(record);
      });
      getMqProducer().send(new GeneralMessage(OmsModule.PURCHASE_ORDER, record.getPurchaseOrderId(),
          EventType.AUDITED));
      BIZ_LOGGER.log(record.getPurchaseOrderId(), BizLogger.AUDIT);
    } catch (Exception e) {
      LOGGER.error("审核采购单失败，采购单：{}", JsonUtil.toJson(record));
      LOGGER.error("审核采购单失败，堆栈信息：", e);
      throw new OmsException("审核采购单失败！");
    }
  }

  @Override
  public void finish(PurchaseOrder record, VersionBO bo) {
    if (record.getStatus() != PurchaseStatus.AUDITED) {
      throw SysExceptions.STATUS_ERROR;
    }
    record.setStatus(PurchaseStatus.FINISH);
    record.setVersion(bo.getVersion());
    final List<PurchaseOrderDetail> details = purchaseOrderDetailService
        .listDetails(record.getPurchaseOrderId());

    List<Stock> updateStocks = new ArrayList<>();
    for (PurchaseOrderDetail detail : details) {
      Stock eg = new Stock();
      eg.setSkuId(detail.getSkuId());
      eg.setVirtualWarehouseId(record.getVirtualWarehouseId());
      Stock stock = stockService.getByExample(eg);

      if (!Assert.isNull(stock)) {
        int adjustQuantity =
            detail.getQuantity() - (detail.getInQuantity() + detail.getInSubstandardQuantity());
        int resultQuantity = stock.getTransitQuantity() - adjustQuantity;
        if (resultQuantity < 0) {
          stock.setTransitQuantity(0);
        } else {
          stock.setTransitQuantity(resultQuantity);
        }
        updateStocks.add(stock);
      }
    }

    try {
      getTransactionTemplate().execute(() -> {
        stockService.batchModify(updateStocks);
        update(record);
      });
      getMqProducer().send(
          new GeneralMessage(OmsModule.PURCHASE_ORDER, record.getPurchaseOrderId(), EventType.IN));
      BIZ_LOGGER.log(record.getPurchaseOrderId(), BizLogger.FINISH);
    } catch (Exception e) {
      LOGGER.error("完结采购单失败，采购单：{}", JsonUtil.toJson(record));
      LOGGER.error("完结采购单失败，堆栈信息：", e);
      throw new OmsException("完结采购单失败！");
    }
  }

  @Override
  public void invalid(PurchaseOrder record, VersionBO bo) {
    if (record.getStatus() != PurchaseStatus.CREATED) {
      throw SysExceptions.STATUS_ERROR;
    }
    record.setStatus(PurchaseStatus.INVALID);
    record.setVersion(bo.getVersion());
    BIZ_LOGGER.log(record.getPurchaseOrderId(), BizLogger.INVALID);
    update(record);
  }

  @Override
  public void createNotice(PurchaseOrder purchaseOrder, VersionBO bo) {
    PurchaseNoticeOrder purchaseNoticeOrder = new PurchaseNoticeOrder();
    purchaseNoticeOrder.setWarehouseId(purchaseOrder.getWarehouseId());
    purchaseNoticeOrder.setWarehouseName(purchaseOrder.getWarehouseName());
    purchaseNoticeOrder.setVirtualWarehouseId(purchaseOrder.getVirtualWarehouseId());
    purchaseNoticeOrder.setVirtualWarehouseName(purchaseOrder.getVirtualWarehouseName());
    purchaseNoticeOrder.setPurchaseOrderCode(purchaseOrder.getPurchaseOrderCode());
    purchaseNoticeOrder.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
    purchaseNoticeOrder.setSupplierName(purchaseOrder.getSupplierName());
    purchaseNoticeOrder.setSupplierCode(purchaseOrder.getSupplierCode());
    PurchaseOrderDetailQuery purchaseOrderDetailFilter = new PurchaseOrderDetailQuery();
    purchaseOrderDetailFilter.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
    List<PurchaseOrderDetail> purchaseOrderDetails = purchaseOrderDetailService
        .list(purchaseOrderDetailFilter);
    List<PurchaseNoticeOrderDetail> purchaseNoticeOrderDetails = new ArrayList<>();
    List<PurchaseOrderDetail> orderDetails = new ArrayList<>();
    for (PurchaseOrderDetail detail : purchaseOrderDetails) {
      if (detail.getQuantity() > detail.getNoticeQuantity()) {
        PurchaseOrderDetail purchaseOrderDetail = new PurchaseOrderDetail();
        PurchaseNoticeOrderDetail noticeOrderDetail = new PurchaseNoticeOrderDetail();
        noticeOrderDetail.setPurchaseOrderDetailId(detail.getPurchaseOrderDetailId());
        noticeOrderDetail.setNoticeQuantity(detail.getQuantity() - detail.getNoticeQuantity());
        noticeOrderDetail.setProductCode(detail.getProductCode());
        noticeOrderDetail.setProductName(detail.getProductName());
        noticeOrderDetail.setProductId(detail.getProductId());
        noticeOrderDetail.setSkuCode(detail.getSkuCode());
        noticeOrderDetail.setSkuName(detail.getSkuName());
        noticeOrderDetail.setSkuId(detail.getSkuId());
        noticeOrderDetail.setNoticeQuantity(detail.getQuantity());
        purchaseOrderDetail.setNoticeQuantity(noticeOrderDetail.getNoticeQuantity());
        purchaseNoticeOrderDetails.add(noticeOrderDetail);
        orderDetails.add(purchaseOrderDetail);
      }
    }
    if (purchaseNoticeOrderDetails.size() <= 0) {
      throw new OmsException("当前采购单没有需要生成通知单的明细");
    }
    PurchaseOrder updateInfo = new PurchaseOrder();
    updateInfo.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
    updateInfo.setDetails(orderDetails);
    updateInfo.setVersion(bo.getVersion() + 1);
    updateInfo.setNoticeOrderStatus(PurchaseOrderNoticeStatus.ALL_NOTICE);
    purchaseNoticeOrder.setDetails(purchaseNoticeOrderDetails);
    try {
      getTransactionTemplate().execute(() -> {
        purchaseNoticeOrderService.create(purchaseNoticeOrder);
        update(updateInfo);
      });
      BIZ_LOGGER.log(purchaseOrder.getPurchaseOrderId(), BizLogger.ADD, "生成采购通知单");
      getMqProducer().send(new GeneralMessage(OmsModule.PURCHASE_NOTICE,
          purchaseNoticeOrder.getPurchaseNoticeOrderId(), EventType.CREATED));
    } catch (Exception e) {
      LOGGER.error("采购通知单生成失败，采购通知单：{}", JsonUtil.toJson(purchaseNoticeOrder));
      LOGGER.error("采购通知单生成失败，堆栈信息：", e);
      throw new OmsException("采购通知单生成失败！");
    }
  }

  @Override
  public void wmsAutoIn(PurchaseOrder purchaseOrder, WmsAutoInBO wmsAutoInBO) {
    if (PurchaseStatus.INVALID == purchaseOrder.getStatus()) {
      throw new OmsException("当前采购单已作废");
    }
    if (InStatus.ALL_IN == purchaseOrder.getInStatus()) {
      throw new OmsException("当前采购单已全部入库");
    }

    VirtualWarehouse substandardWarehouse = virtualWarehouseService
        .getSubstandardWarehouse(purchaseOrder.getWarehouseId());
    List<PurchaseOrderDetail> details = purchaseOrderDetailService
        .listDetails(purchaseOrder.getPurchaseOrderId());
    List<PurchaseOrderDetail> updateDetails = new ArrayList<>(details.size());
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
    for (PurchaseOrderDetail detail : details) {
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

    purchaseOrder
        .setInStatus(totalInQuantity < totalNoticeQuantity ? InStatus.PART_IN : InStatus.ALL_IN);
    purchaseOrder.setVersion(wmsAutoInBO.getVersion());
    purchaseOrder.setLastInTime(wmsAutoInBO.getInTime());

    try {
      getTransactionTemplate().execute(() -> {
        purchaseOrderDetailService.batchModify(updateDetails);
        update(purchaseOrder);
      });
      details.forEach(x -> {
        getMqProducer().send(
            new StockChangedMessage(x.getSkuId(), BizContext.getNickname(),
                "采购通知单入库"));
      });
      if (purchaseOrder.getInStatus() == InStatus.ALL_IN) {
        getMqProducer().send(
            new GeneralMessage(OmsModule.PURCHASE_ORDER, purchaseOrder.getPurchaseOrderId(),
                EventType.IN));
      }
      BIZ_LOGGER.log(purchaseOrder.getPurchaseOrderId(), BizLogger.ENTRY,
          "采购通知单：{}", wmsAutoInBO.getWmsOrderCode());
    } catch (Exception e) {
      LOGGER.error("采购订单入库失败，采购通知单：{}", JsonUtil.toJson(purchaseOrder));
      LOGGER.error("采购订单入库失败，堆栈信息：", e);
      throw new OmsException("采购订单入库失败！");
    }
  }
}