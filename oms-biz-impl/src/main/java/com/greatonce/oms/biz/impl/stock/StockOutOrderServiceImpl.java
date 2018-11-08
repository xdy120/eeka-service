package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.ExportHelper;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.stock.StockOutOrderDetailService;
import com.greatonce.oms.biz.stock.StockOutOrderService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockOutExportBO;
import com.greatonce.oms.bo.stock.StockOutOrderCancelBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutDetail;
import com.greatonce.oms.bridge.wms.StockOutOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse.WmsStockOutOrderStatus;
import com.greatonce.oms.dao.stock.StockOutOrderDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.stock.StockOutOrderStatus;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.stock.StockOutOrderDetailQuery;
import com.greatonce.oms.query.stock.StockOutOrderQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
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
 * 出库单. StockOutOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class StockOutOrderServiceImpl extends
    AbstractVersionService<StockOutOrder, StockOutOrderQuery> implements StockOutOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK_OUT);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockOutOrderServiceImpl.class);

  @Autowired
  private StockOutOrderDao dao;
  @Autowired
  private StockOutOrderDetailService stockOutOrderDetailService;
  @Resource
  private IdGenerator stockOutIdGenerator;
  @Resource
  private CodeGenerator stockOutCodeGenerator;
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
  @Autowired
  private MessageExporter messageExporter;

  @Override
  protected QueryDao<StockOutOrder, StockOutOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.stockOutIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(StockOutOrder record) {
    super.initDefaultValue(record);
    record.setVersion(0);
    record.setStatus(StockOutOrderStatus.CREATED);
    record.setOutStatus(OutStatus.NO_OUT);
    record.setCreator(BizContext.getNickname());
    record.setStockOutOrderCode(stockOutCodeGenerator.next());
    record.getDetails().forEach(detail -> {
      detail.setStockOutOrderId(record.getStockOutOrderId());
    });
  }

  @Override
  public int create(StockOutOrder record) {
    initDefaultValue(record);
    try {
      Integer result = getTransactionTemplate().executeWithResult(() -> {
        stockOutOrderDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getStockOutOrderId(), BizLogger.ADD);
      return result;
    } catch (Exception e) {
      LOGGER.error("出库单新增失败，出库单：{}", JsonUtil.toJson(record));
      LOGGER.error("出库单新增失败，堆栈信息：", e);
      throw new OmsException("出库单新增失败！");
    }
  }

  @Override
  public int modify(StockOutOrder record) {
    BIZ_LOGGER.log(record.getStockOutOrderId(), BizLogger.UPDATE);
    return update(record);
  }


  @Override
  public void audit(StockOutOrder stockOutOrder, VersionBO versionBO) {
    if (stockOutOrder.getStatus() != StockOutOrderStatus.CREATED) {
      throw SysExceptions.STATUS_ERROR;
    }
    StockSetting stockSetting = settingService.getInventorySetting();
    List<StockOutOrderDetail> outDetails = new ArrayList<>();
    if (stockSetting.getDeliveryAllocationType() == StockSetting.AllocationType.AVAILABLE_BINS) {
      outDetails = stockOutOrderDetailService.listAvailable(stockOutOrder.getStockOutOrderId());
    } else if (stockSetting.getVirtualAllocationType() == StockSetting.AllocationType.SOLD_OUT) {
      outDetails = stockOutOrderDetailService.listSaleable(stockOutOrder.getStockOutOrderId());
    }
    List<StockOccupancy> occupancies = new ArrayList<>();
    try {
      for (StockOutOrderDetail detail : outDetails) {
        if (detail.getNoticeQuantity() > 0) {
          int qty = Math.min(detail.getNoticeQuantity(), detail.getPlanQuantity());
          detail.setNoticeQuantity(qty);
          occupancies.add(DomainUtil
              .createStockOccupancy(stockOutOrder.getWarehouseId(),
                  stockOutOrder.getWarehouseName(),
                  stockOutOrder.getVirtualWarehouseId(), stockOutOrder.getVirtualWarehouseName(),
                  StockOccupancyType.OUT_ORDER, detail.getSkuId(), detail.getSkuCode(),
                  qty, stockOutOrder.getStockOutOrderId(),
                  detail.getStockOutOrderDetailId(), StockOccupancyStatus.LOCK));
        } else {
          detail.setNoticeQuantity(0);
        }
      }
      stockOutOrder.setStatus(StockOutOrderStatus.AUDITED);
      stockOutOrder.setAuditor(BizContext.getNickname());
      stockOutOrder.setAuditedTime(LocalDateTime.now());
      stockOutOrder.setDetails(outDetails);
      getTransactionTemplate().execute(() -> {
        stockOutOrderDetailService.batchModify(stockOutOrder.getDetails());
        stockOccupancyService.batchCreate(occupancies);
        update(stockOutOrder);
      });
      occupancies.forEach(occupancy -> {
        getMqProducer().send(
            new StockChangedMessage(stockOutOrder.getStockOutOrderCode(), occupancy.getSkuId(),
                BizContext.getNickname(), "出库单审核"));
      });
      getMqProducer().send(
          new GeneralMessage(OmsModule.STOCK_OUT,
              stockOutOrder.getStockOutOrderId(),
              EventType.AUDITED));
      BIZ_LOGGER.log(stockOutOrder.getStockOutOrderId(), BizLogger.AUDIT, "出库单审核");
    } catch (Exception e) {
      LOGGER.error("出库单审核失败，出库单：{}", JsonUtil.toJson(stockOutOrder));
      LOGGER.error("出库单审核失败，堆栈信息：", e);
      throw new OmsException("出库单审核失败！");
    }

  }

  @Override
  public void cancel(StockOutOrder stockOutOrder, StockOutOrderCancelBO bo) {
    if (OutStatus.NO_OUT != stockOutOrder.getOutStatus()) {
      throw new OmsException("出库单已出库不能取消");
    }
    if (stockOutOrder.getStatus() == StockOutOrderStatus.CANCEL) {
      return;
    }
    if (stockOutOrder.getStatus() == StockOutOrderStatus.CREATED) {
      stockOutOrder.setStatus(StockOutOrderStatus.CANCEL);
      stockOutOrder.setVersion(bo.getVersion());
      update(stockOutOrder);
      BIZ_LOGGER.log(stockOutOrder.getStockOutOrderId(), BizLogger.CANCEL);
      return;
    }
    if (stockOutOrder.getStatus() == StockOutOrderStatus.AUDITED) {
      cancelOms(stockOutOrder, bo);
      return;
    }
    if (StockOutOrderStatus.NOTICE_FAILED == stockOutOrder.getStatus()
        || StockOutOrderStatus.NOTICED == stockOutOrder.getStatus()) {
      Warehouse warehouse = warehouseService.getByKey(stockOutOrder.getWarehouseId());
      StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
          .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());
      if (StockOutOrderStatus.NOTICE_FAILED == stockOutOrder.getStatus()) {
        StockOutOrderQueryRequest request = new StockOutOrderQueryRequest(warehouse);
        request.setOmsCode(stockOutOrder.getStockOutOrderCode());
        request.setOrderType(OrderType.OUT_ORDER);
        final StockOutOrderQueryResponse response = stockOutOrderBridge
            .queryOrder(request);
        if (!response.isExists() || response.getStatus() == WmsStockOutOrderStatus.CANCELED) {
          cancelOms(stockOutOrder, bo);
          return;
        }
      }
      StockOutOrderCancelRequest request = new StockOutOrderCancelRequest(warehouse);
      request.setOmsCode(stockOutOrder.getStockOutOrderCode());
      request.setOrderType(OrderType.OUT_ORDER);
      StockOutOrderCancelResponse response = stockOutOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(stockOutOrder, bo);
      } else {
        BIZ_LOGGER.log(stockOutOrder.getStockOutOrderId(), BizLogger.CANCEL, "WMS取消失败：{}",
            response.getMessage());
        throw new OmsException(response.getMessage());
      }
    }
  }

  private void cancelOms(StockOutOrder stockOutOrder, StockOutOrderCancelBO bo) {
    stockOutOrder.setStatus(StockOutOrderStatus.CANCEL);
    stockOutOrder.setVersion(bo.getVersion());
    final List<StockOutOrderDetail> details = stockOutOrderDetailService
        .listDetails(stockOutOrder.getStockOutOrderId());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(stockOutOrder.getStockOutOrderId(), StockOccupancyType.OUT_ORDER);
        update(stockOutOrder);
      });
      for (StockOutOrderDetail detail : details) {
        if (detail.getNoticeQuantity() > 0) {
          getMqProducer().send(
              new StockChangedMessage(stockOutOrder.getStockOutOrderCode(), detail.getSkuId(),
                  BizContext.getNickname(), "出库单取消"));
        }
      }
      BIZ_LOGGER.log(stockOutOrder.getStockOutOrderId(), BizLogger.CANCEL, "原因:{}", bo.getReason());
    } catch (Exception e) {
      LOGGER.error("出库单取消失败,借出单信息: {}", JsonUtil.toJson(stockOutOrder));
      LOGGER.error("出库单取消失败,堆栈信息: ", e);
      throw new OmsException("出库单取消失败");
    }
  }


  @Override
  public void finish(StockOutOrder record, VersionBO bo) {
    if (record.getOutStatus() != OutStatus.PART_OUT) {
      throw new OmsException("只有部分出库的出库单才能完结");
    }
    record.setStatus(StockOutOrderStatus.FINISH);
    record.setVersion(bo.getVersion());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(record.getStockOutOrderId(), StockOccupancyType.OUT_ORDER);
        update(record);
      });
      BIZ_LOGGER.log(record.getStockOutOrderId(), BizLogger.FINISH, "出库单完结");
    } catch (Exception e) {
      LOGGER.error("出库单完结失败，出库单：{}", JsonUtil.toJson(record));
      LOGGER.error("出库单完结失败，堆栈信息：", e);
      throw new OmsException("出库单完结失败！");
    }
  }

  @Override
  public void noticeWms(StockOutOrder stockOutOrder, VersionBO bo) {
    if (stockOutOrder.getStatus() != StockOutOrderStatus.AUDITED
        && stockOutOrder.getStatus() != StockOutOrderStatus.NOTICE_FAILED) {
      throw new OmsException("只有已审核或推送失败的出库单才能推送给仓库");
    }
    Warehouse warehouse = warehouseService.getByKey(stockOutOrder.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");

    StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
        .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());
    StockOutOrderCreateRequest request = new StockOutOrderCreateRequest(warehouse);
    StockOutOrderDetailQuery stockOutOrderDetailQuery = new StockOutOrderDetailQuery();
    stockOutOrderDetailQuery.setStockOutOrderId(stockOutOrder.getStockOutOrderId());
    List<StockOutOrderDetail> stockOutOrderDetailList = stockOutOrderDetailService
        .list(stockOutOrderDetailQuery);

    request.setOmsCode(stockOutOrder.getStockOutOrderCode());
    request.setOrderType(OrderType.OUT_ORDER);
    request.setCreatedTime(stockOutOrder.getCreatedTime());

    //仓库信息
    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setSenderArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());
    //拼接请求明细数据
    List<StockOutOrderCreateRequest.StockOutOrderDetail> stockOutOrderDetails = new ArrayList<>();
    for (StockOutOrderDetail stockOutOrderDetail : stockOutOrderDetailList) {
      StockOutOrderCreateRequest.StockOutOrderDetail detail = new StockOutOrderCreateRequest.StockOutOrderDetail();
      if (stockOutOrderDetail.getNoticeQuantity() > 0) {
        detail.setQuantity(stockOutOrderDetail.getNoticeQuantity());
        detail.setProductCode(stockOutOrderDetail.getProductCode());
        detail.setProductName(stockOutOrderDetail.getProductName());
        detail.setSkuCode(stockOutOrderDetail.getSkuCode());
        detail.setWmsSkuId("");
        stockOutOrderDetails.add(detail);
      }
    }
    request.setDetails(stockOutOrderDetails);

    StockOutOrderCreateResponse response = stockOutOrderBridge.createOrder(request);
    StockOutOrder updateInfo = new StockOutOrder();
    updateInfo.setStockOutOrderId(stockOutOrder.getStockOutOrderId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo
        .setStatus(
            response.isSuccess() ? StockOutOrderStatus.NOTICED : StockOutOrderStatus.NOTICE_FAILED);
    update(updateInfo);
    BIZ_LOGGER
        .log(stockOutOrder.getStockOutOrderId(), BizLogger.NOTICE_WMS,
            response.isSuccess() ? "成功" : response.getMessage());
  }

  @Override
  public StockOutOrder getByCode(String orderCode) {
    StockOutOrder eg = new StockOutOrder();
    eg.setStockOutOrderCode(orderCode);
    return getByExample(eg);
  }

  /**
   * 出库单接收WMS回传的出库信息，修改出库单出库状态，添加外部单号 修改明细的出库数量
   */
  public void wmsAutoOut(StockOutOrder stockOutOrder, WmsAutoOutBO wmsAutoOutBO) {
    if (OutStatus.ALL_OUT == stockOutOrder.getOutStatus()) {
      throw new OmsException("当前出库单已全部出库");
    }
    if (StockOutOrderStatus.CANCEL == stockOutOrder.getStatus()) {
      throw new OmsException("当前出库单已作废");
    }
    OutStatus outStatus = OutStatus.ALL_OUT;
    Map<String, WmsAutoOutDetail> map = CollectionUtil
        .listToMap(wmsAutoOutBO.getDetails(), WmsAutoOutDetail::getSkuCode);
    List<StockOutOrderDetail> details = stockOutOrderDetailService
        .listDetails(stockOutOrder.getStockOutOrderId());
    List<DetailStockChange> stockChanges = new ArrayList<>(details.size());
    List<StockOutOrderDetail> updateDetails = new ArrayList<>(details.size());
    WmsAutoOutDetail wmsAutoOutDetail;
    for (StockOutOrderDetail detail : details) {
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
      if (detail.getNoticeQuantity() > detail.getOutQuantity()) {
        outStatus = OutStatus.PART_OUT;
      }
    }

    StockOutOrder updateInfo = new StockOutOrder();
    updateInfo.setStockOutOrderId(stockOutOrder.getStockOutOrderId());
    updateInfo.setOutStatus(outStatus);
    updateInfo.setVersion(wmsAutoOutBO.getVersion());
    if (stockOutOrder.getStatus() ==StockOutOrderStatus.NOTICE_FAILED){
      updateInfo.setStatus(StockOutOrderStatus.NOTICED);
    }
    try {
      getTransactionTemplate().execute(() -> {
        stockOutOrderDetailService.batchModify(updateDetails);
        for (DetailStockChange change : stockChanges) {
          stockService
              .adjustQuantity(stockOutOrder.getStockOutOrderCode(), OrderType.OUT_ORDER,
                  change.detail.getSkuId(), change.detail.getSkuCode(),
                  stockOutOrder.getWarehouseId(),
                  stockOutOrder.getWarehouseName(), stockOutOrder.getVirtualWarehouseId(),
                  stockOutOrder.getVirtualWarehouseName(), -change.quantity);
          if (change.detail.getNoticeQuantity().equals(change.detail.getOutQuantity())) {
            //删除库存占用
            stockOccupancyService
                .deleteOccupancy(change.detail.getStockOutOrderId(),
                    change.detail.getStockOutOrderDetailId(),
                    StockOccupancyType.OUT_ORDER);
          } else {
            //调整数量
            stockOccupancyService
                .adjustQuantity(change.detail.getStockOutOrderId(),
                    change.detail.getStockOutOrderDetailId(),
                    StockOccupancyType.OUT_ORDER, -change.quantity);
          }
        }
        update(updateInfo);
      });
      BIZ_LOGGER.log(updateInfo.getStockOutOrderId(), BizLogger.UPDATE, "出库单出库");
      updateDetails.forEach(x -> {
        getMqProducer().send(
            new StockChangedMessage(x.getSkuId(), BizContext.getNickname(),
                "出库单商品出库"));
      });
      if (outStatus == OutStatus.ALL_OUT) {
        getMqProducer().send(
            new GeneralMessage(OmsModule.STOCK_OUT, stockOutOrder.getStockOutOrderId(),
                EventType.OUT));
      }
    } catch (Exception e) {
      LOGGER.error("出库单出库异常,出库单信息: {}", JsonUtil.toJson(stockOutOrder));
      LOGGER.error("出库单出库异常,堆栈信息: ", e);
      throw new OmsException("出库单出库异常");
    }
  }

  static class DetailStockChange {

    private StockOutOrderDetail detail;
    private int quantity;

    public DetailStockChange(StockOutOrderDetail detail, int quantity) {
      this.detail = detail;
      this.quantity = quantity;
    }
  }

  @Override
  public void exportStockOut(String fileName, StockOutOrderQuery query) {
    ExcelHeaderCollection<StockOutExportBO> headers = new ExcelHeaderCollection<>();
    headers.add("出库单编码",StockOutExportBO::getStockOutOrderCode);
    headers.add("出库数量", x->FormatUtil.formatInteger(x.getOutQuantity()));
    headers.add("出库类型",StockOutExportBO::getStockOutOrderType);
    headers.add("出库状态", x->Assert.isNull(x.getOutStatus()) ? "" : x.getOutStatus().caption());
    headers.add("备注",StockOutExportBO::getRemark);
    headers.add("创建人",StockOutExportBO::getCreator);
    headers.add("创建时间", x->FormatUtil.formatLocalDateTime(x.getCreatedTime()));
    headers.add("规格编码",StockOutExportBO::getSkuCode);
    headers.add("规格名称",StockOutExportBO::getSkuName);
    headers.add("商品编码",StockOutExportBO::getProductCode);
    headers.add("商品名称",StockOutExportBO::getProductName);
    messageExporter.exportExcel(this::exportListStockOut,headers,query,fileName);
  }

  private PageList<StockOutExportBO> exportListStockOut(StockOutOrderQuery query , Integer page,
      Integer pageSize){
    return dao.exportListStockOut(query,page,pageSize);
  }


}