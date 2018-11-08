package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.stock.StockLoanInDetailService;
import com.greatonce.oms.biz.stock.StockLoanOutDetailService;
import com.greatonce.oms.biz.stock.StockLoanOutService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockLoanOutBO;
import com.greatonce.oms.bo.stock.StockLoanOutCancelBO;
import com.greatonce.oms.bo.stock.StockLoanOutVerificationBO;
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
import com.greatonce.oms.dao.stock.StockLoanOutDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.base.setting.StockSetting.AllocationType;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.stock.StockLoanOutStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.stock.StockLoanInDetail;
import com.greatonce.oms.domain.stock.StockLoanOut;
import com.greatonce.oms.domain.stock.StockLoanOutDetail;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.stock.StockLoanOutDetailQuery;
import com.greatonce.oms.query.stock.StockLoanOutQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDate;
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
 * 借出单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */

@Service
public class StockLoanOutServiceImpl extends
    AbstractVersionService<StockLoanOut, StockLoanOutQuery> implements StockLoanOutService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK_LOAN_OUT);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockLoanOutServiceImpl.class);

  @Autowired
  private StockLoanOutDao dao;
  @Resource
  private IdGenerator stockLoanOutIdGenerator;
  @Resource
  private CodeGenerator stockLoanOutCodeGenerator;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private StockLoanOutDetailService stockLoanOutDetailService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private StockService stockService;
  @Autowired
  private StockLoanInDetailService stockLoanInDetailService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private MessageExporter messageExporter;
  @Autowired
  private SettingService settingService;

  @Override
  protected QueryDao<StockLoanOut, StockLoanOutQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.stockLoanOutIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockLoanOut record) {
    super.initDefaultValue(record);
    record.setVersion(0);
    record.setStatus(StockLoanOutStatus.CREATED);
    record.setOutStatus(OutStatus.NO_OUT);
    record.setCreator(BizContext.getNickname());
    if (Assert.isNull(record.isNeedReturn())) {
      record.setNeedReturn(false);
    }
    record.setStockLoanOutCode(stockLoanOutCodeGenerator.next());
    record.getDetails().forEach(detail -> {
      detail.setStockLoanOutId(record.getStockLoanOutId());
    });
  }

  @Override
  public int create(StockLoanOut stockLoanOut) {
    initDefaultValue(stockLoanOut);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        int i = insert(stockLoanOut);
        stockLoanOutDetailService.batchCreate(stockLoanOut.getDetails());
        return i;
      });
      BIZ_LOGGER.log(stockLoanOut.getStockLoanOutId(), BizLogger.ADD, "新增借出单");
      return count;
    } catch (Exception e) {
      LOGGER.error("新增借出单失败,借出单信息: {}", JsonUtil.toJson(stockLoanOut));
      LOGGER.error("新增借出单失败,堆栈信息: {}", e);
      throw new OmsException("新增借出单失败");
    }
  }


  @Override
  public void audit(StockLoanOut stockLoanOut, VersionBO bo) {
    if (StockLoanOutStatus.CREATED != stockLoanOut.getStatus()) {
      throw new OmsException("只有新建的借出单才可以审核");
    }
    StockLoanOutDetail eg = new StockLoanOutDetail();
    eg.setStockLoanOutId(stockLoanOut.getStockLoanOutId());
    stockLoanOut.setDetails(stockLoanOutDetailService.listExample(eg));
    for (StockLoanOutDetail detail : stockLoanOut.getDetails()) {
      if (detail.getPlanQuantity() <= 0) {
        throw new OmsException("借出单计划数量必须大于0");
      }
    }
    Warehouse warehouse = warehouseService.getByKey(stockLoanOut.getWarehouseId());
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(stockLoanOut.getVirtualWarehouseId());
    final StockSetting stockSetting = settingService.getInventorySetting();
    stockLoanOut.setStatus(StockLoanOutStatus.AUDITED);
    stockLoanOut.setVersion(bo.getVersion());
    stockLoanOut.setAuditor(BizContext.getNickname());
    stockLoanOut.setAuditedTime(LocalDateTime.now());

    List<StockLoanOutDetail> outDetails;
    if (stockSetting.getDeliveryAllocationType() == AllocationType.SOLD_OUT) {
      outDetails = stockLoanOutDetailService
          .listSaleable(stockLoanOut.getStockLoanOutId());
    } else {
      outDetails = stockLoanOutDetailService
          .listAvailable(stockLoanOut.getStockLoanOutId());
    }
    List<StockOccupancy> occupancies = new ArrayList<>(outDetails.size());
    outDetails.forEach(detail -> {
      detail.setStockLoanOutId(stockLoanOut.getStockLoanOutId());
      if (detail.getNoticeQuantity() > 0) {
        int qty = Math.min(detail.getNoticeQuantity(), detail.getPlanQuantity());
        detail.setNoticeQuantity(qty);
        occupancies.add(DomainUtil
            .createStockOccupancy(stockLoanOut.getWarehouseId(), stockLoanOut.getWarehouseName(),
                stockLoanOut.getVirtualWarehouseId(), stockLoanOut.getVirtualWarehouseName(),
                StockOccupancyType.LOAN_OUT, detail.getSkuId(), detail.getSkuCode(),
                qty, stockLoanOut.getStockLoanOutId(),
                detail.getStockLoanOutDetailId(), StockOccupancyStatus.LOCK));
      } else {
        detail.setNoticeQuantity(0);
      }
    });
    stockLoanOut.setDetails(outDetails);
    try {
      getTransactionTemplate().execute(() -> {
        stockLoanOutDetailService.batchModify(outDetails);
        stockOccupancyService.batchCreate(occupancies);
        update(stockLoanOut);
      });
      occupancies.forEach(occupancy -> getMqProducer()
          .send(new StockChangedMessage(stockLoanOut.getStockLoanOutCode(),
              occupancy.getSkuId(), BizContext.getNickname(), "借出单审核")));
      getMqProducer()
          .send(new GeneralMessage(OmsModule.STOCK_LOAN_OUT, stockLoanOut.getStockLoanOutId(),
              EventType.AUDITED));
      BIZ_LOGGER.log(stockLoanOut.getStockLoanOutId(), BizLogger.AUDIT);
    } catch (Exception e) {
      LOGGER.error("审核借出单失败,借出单明细信息: {}", JsonUtil.toJson(stockLoanOut.getDetails()));
      LOGGER.error("审核借出单失败,堆栈信息: ", e);
      throw new OmsException("审核借出单失败");
    }
  }


  @Override
  public void noticeWms(StockLoanOut stockLoanOut) {
    if (StockLoanOutStatus.AUDITED != stockLoanOut.getStatus()
        && StockLoanOutStatus.NOTICE_FAILED != stockLoanOut.getStatus()) {
      throw new OmsException("只有已审核或推送失败的借出单才能推送给仓库");
    }
    Warehouse warehouse = warehouseService.getByKey(stockLoanOut.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");

    StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
        .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());
    StockOutOrderCreateRequest request = new StockOutOrderCreateRequest(warehouse);
    StockLoanOutDetailQuery stockLoanOutDetailQuery = new StockLoanOutDetailQuery();
    stockLoanOutDetailQuery.setStockLoanOutId(stockLoanOut.getStockLoanOutId());
    List<StockLoanOutDetail> details = stockLoanOutDetailService
        .list(stockLoanOutDetailQuery);

    request.setOmsCode(stockLoanOut.getStockLoanOutCode());
    request.setOrderType(OrderType.LOAN_OUT_ORDER);
    request.setCreatedTime(stockLoanOut.getCreatedTime());

    //仓库信息
    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setSenderArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());
    //拼接请求明细数据
    List<StockOutOrderCreateRequest.StockOutOrderDetail> stockOutOrderDetails = new ArrayList<>();
    for (StockLoanOutDetail stockOutOrderDetail : details) {
      StockOutOrderCreateRequest.StockOutOrderDetail detail = new StockOutOrderCreateRequest.StockOutOrderDetail();
      if (stockOutOrderDetail.getNoticeQuantity() > 0) {
        detail.setQuantity(stockOutOrderDetail.getNoticeQuantity());
        detail.setProductCode(stockOutOrderDetail.getProductCode());
        detail.setProductName(stockOutOrderDetail.getProductName());
        detail.setSkuCode(stockOutOrderDetail.getSkuCode());
        detail.setWmsSkuId(FormatUtil.formatLong(stockOutOrderDetail.getSkuId()));
        stockOutOrderDetails.add(detail);
      }
    }
    request.setDetails(stockOutOrderDetails);

    StockOutOrderCreateResponse response = stockOutOrderBridge.createOrder(request);
    StockLoanOut updateInfo = new StockLoanOut();
    updateInfo.setStockLoanOutId(stockLoanOut.getStockLoanOutId());
    updateInfo.setVersion(stockLoanOut.getVersion());
    updateInfo.setStatus(
        response.isSuccess() ? StockLoanOutStatus.NOTICED : StockLoanOutStatus.NOTICE_FAILED);
    try {
      getTransactionTemplate().execute(() -> {
        update(updateInfo);
      });
      BIZ_LOGGER
          .log(stockLoanOut.getStockLoanOutId(), BizLogger.NOTICE_WMS,
              response.isSuccess() ? "成功" : response.getMessage());
    } catch (Exception e) {
      LOGGER.error("借出单通知WMS失败,借出单信息: {}", JsonUtil.toJson(stockLoanOut));
      LOGGER.error("借出单通知WMS失败,堆栈信息: ", e);
      throw new OmsException("借出单通知WMS失败");
    }
  }

  /**
   * 取消借出单.
   */
  @Override
  public void cancel(StockLoanOut stockLoanOut, StockLoanOutCancelBO bo) {
    if (OutStatus.NO_OUT != stockLoanOut.getOutStatus()) {
      throw new OmsException("借出单已出库不能取消");
    }
    if (stockLoanOut.getStatus() == StockLoanOutStatus.CANCEL) {
      return;
    }
    if (stockLoanOut.getStatus() == StockLoanOutStatus.CREATED) {
      stockLoanOut.setStatus(StockLoanOutStatus.CANCEL);
      stockLoanOut.setVersion(bo.getVersion());
      update(stockLoanOut);
      BIZ_LOGGER.log(stockLoanOut.getStockLoanOutId(), BizLogger.CANCEL);
      return;
    }
    if (stockLoanOut.getStatus() == StockLoanOutStatus.AUDITED) {
      cancelOms(stockLoanOut, bo);
      return;
    }
    if (StockLoanOutStatus.NOTICE_FAILED == stockLoanOut.getStatus()
        || StockLoanOutStatus.NOTICED == stockLoanOut.getStatus()) {
      Warehouse warehouse = warehouseService.getByKey(stockLoanOut.getWarehouseId());
      StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
          .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());
      if (StockLoanOutStatus.NOTICE_FAILED == stockLoanOut.getStatus()) {
        StockOutOrderQueryRequest request = new StockOutOrderQueryRequest(warehouse);
        request.setOmsCode(stockLoanOut.getStockLoanOutCode());
        request.setOrderType(OrderType.LOAN_OUT_ORDER);
        final StockOutOrderQueryResponse response = stockOutOrderBridge
            .queryOrder(request);
        if (!response.isExists() || response.getStatus() == WmsStockOutOrderStatus.CANCELED) {
          cancelOms(stockLoanOut, bo);
          return;
        }
      }
      StockOutOrderCancelRequest request = new StockOutOrderCancelRequest(warehouse);
      request.setOmsCode(stockLoanOut.getStockLoanOutCode());
      request.setOrderType(OrderType.LOAN_OUT_ORDER);
      StockOutOrderCancelResponse response = stockOutOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(stockLoanOut, bo);
      } else {
        BIZ_LOGGER.log(stockLoanOut.getStockLoanOutId(), BizLogger.CANCEL, "WMS取消失败：{}",
            response.getMessage());
        throw new OmsException(response.getMessage());
      }
    }
  }

  private void cancelOms(StockLoanOut stockLoanOut, StockLoanOutCancelBO bo) {
    StockLoanOut updateInfo = new StockLoanOut();
    updateInfo.setStockLoanOutId(stockLoanOut.getStockLoanOutId());
    updateInfo.setStatus(StockLoanOutStatus.CANCEL);
    updateInfo.setVersion(bo.getVersion());
    final List<StockLoanOutDetail> details = stockLoanOutDetailService
        .listDetails(stockLoanOut.getStockLoanOutId());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(stockLoanOut.getStockLoanOutId(), StockOccupancyType.LOAN_OUT);
        update(updateInfo);
      });
      for (StockLoanOutDetail detail : details) {
        if (detail.getNoticeQuantity() > 0) {
          getMqProducer().send(
              new StockChangedMessage(stockLoanOut.getStockLoanOutCode(), detail.getSkuId(),
                  BizContext.getNickname(), "借出单取消"));
        }
      }
      BIZ_LOGGER.log(stockLoanOut.getStockLoanOutId(), BizLogger.CANCEL, "原因：{}", bo.getReason());
    } catch (Exception e) {
      LOGGER.error("借出单取消失败,借出单信息: {}", JsonUtil.toJson(stockLoanOut));
      LOGGER.error("借出单取消失败,堆栈信息: ", e);
      throw new OmsException("借出单取消失败");
    }
  }

  public PageList<StockLoanOutBO> listStatistics(StockLoanOutQuery stockLoanOutQuery, int page,
      int pageSize) {
    return dao.listStatistics(stockLoanOutQuery, page, pageSize);
  }

  public void exportLoan(StockLoanOutQuery stockLoanOutQuery, String fileName) {
    ExcelHeaderCollection<StockLoanOutBO> headers = new ExcelHeaderCollection<>();
    headers.add("借用人", StockLoanOutBO::getLoanUser);
    headers.add("商品编码", StockLoanOutBO::getProductCode);
    headers.add("商品名称", StockLoanOutBO::getProductName);
    headers.add("规格编码", StockLoanOutBO::getSkuCode);
    headers.add("规格名称", StockLoanOutBO::getSkuName);
    headers.add("预计归还日期", x -> DateTimeUtil.format(x.getExpectReturnDate()));
    headers.add("借出数量", x -> FormatUtil.formatInteger(x.getPlanQuantity()));
    headers.add("已还数量", StockLoanOutBO::getReturnQuantity);
    headers.add("未还数量", StockLoanOutBO::getSurplusQuantity);
    headers.add("超期数量", x -> FormatUtil.formatInteger(
        LocalDate.now().isAfter(x.getExpectReturnDate())
            ? x.getOutQuantity() - Integer.parseInt(x.getReturnQuantity()) : 0)
    );
    messageExporter.exportExcel(this::listStatistics, headers, stockLoanOutQuery, fileName);
  }

  @Override
  public StockLoanOut getByCode(String orderCode) {
    StockLoanOut eg = new StockLoanOut();
    eg.setStockLoanOutCode(orderCode);
    return getByExample(eg);
  }

  @Override
  public void wmsAutoOut(StockLoanOut stockLoanOut, WmsAutoOutBO wmsAutoOutBO) {
    if (StockLoanOutStatus.CANCEL == stockLoanOut.getStatus()) {
      throw new OmsException("当前借出单已取消");
    }
    if (OutStatus.ALL_OUT == stockLoanOut.getOutStatus()) {
      throw new OmsException("当前借出单已全部出库");
    }

    List<StockLoanOutDetail> details = stockLoanOutDetailService
        .listDetails(stockLoanOut.getStockLoanOutId());
    Map<String, WmsAutoOutDetail> map = CollectionUtil
        .listToMap(wmsAutoOutBO.getDetails(), WmsAutoOutDetail::getSkuCode);
    List<StockLoanOutDetail> updateDetails = new ArrayList<>(details.size());
    List<DetailStockChange> stockChanges = new ArrayList<>(details.size());
    OutStatus outStatus = OutStatus.ALL_OUT;
    WmsAutoOutDetail wmsAutoOutDetail;
    for (StockLoanOutDetail detail : details) {
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
        outStatus = OutStatus.PART_OUT;
      }
    }
    //根据明细数量修改主单状态
    StockLoanOut updateInfo = new StockLoanOut();
    updateInfo.setStockLoanOutId(stockLoanOut.getStockLoanOutId());
    updateInfo.setVersion(wmsAutoOutBO.getVersion());
    updateInfo.setOutStatus(outStatus);
    if (stockLoanOut.getStatus() == StockLoanOutStatus.NOTICE_FAILED) {
      updateInfo.setStatus(StockLoanOutStatus.NOTICED);
    }
    try {
      getTransactionTemplate().execute(() -> {
        stockLoanOutDetailService.batchModify(updateDetails);
        for (DetailStockChange change : stockChanges) {
          stockService
              .adjustQuantity(stockLoanOut.getStockLoanOutCode(),
                  OrderType.LOAN_OUT_ORDER,
                  change.detail.getSkuId(), change.detail.getSkuCode(),
                  stockLoanOut.getWarehouseId(),
                  stockLoanOut.getWarehouseName(),
                  stockLoanOut.getVirtualWarehouseId(),
                  stockLoanOut.getVirtualWarehouseName(), -change.quantity);
          if (change.detail.getNoticeQuantity().equals(change.detail.getOutQuantity())) {
            //删除库存占用
            stockOccupancyService
                .deleteOccupancy(change.detail.getStockLoanOutId(),
                    change.detail.getStockLoanOutDetailId(),
                    StockOccupancyType.LOAN_OUT);
          } else {
            //调整数量
            stockOccupancyService
                .adjustQuantity(change.detail.getStockLoanOutId(),
                    change.detail.getStockLoanOutDetailId(),
                    StockOccupancyType.LOAN_OUT, -change.quantity);
          }
        }
        update(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("借出单出库异常,借出单信息: {}", JsonUtil.toJson(stockLoanOut));
      LOGGER.error("借出单出库异常,堆栈信息: ", e);
      throw new OmsException("借出单出库异常");
    }
  }

  @Override
  public void returnBack(StockLoanOut stockLoanOut, StockLoanIn stockLoanIn) {
    if (Assert.isFalse(stockLoanOut.isNeedReturn())) {
      return;
    }
    List<StockLoanInDetail> inDetails = stockLoanIn.getDetails();
    List<StockLoanOutDetail> details = stockLoanOut.getDetails();
    if (Assert.isEmpty(details)) {
      details = stockLoanOutDetailService.listDetails(stockLoanOut.getStockLoanOutId());
    }
    if (Assert.isEmpty(inDetails)) {
      inDetails = stockLoanInDetailService.listDetails(stockLoanIn.getStockLoanInId());
    }
    final Map<Long, StockLoanInDetail> inDetailMap = CollectionUtil
        .listToMap(inDetails, StockLoanInDetail::getSkuId);
    StockLoanInDetail inDetail;
    StockLoanOutStatus status = StockLoanOutStatus.ALL_RETURN;
    final List<StockLoanOutDetail> updateDetails = new ArrayList<>(details.size());
    for (StockLoanOutDetail detail : details) {
      inDetail = inDetailMap.get(detail.getSkuId());
      if (inDetail != null) {
        detail.setReturnQuantity(detail.getReturnQuantity() + inDetail.getInQuantity() + inDetail
            .getInSubstandardQuantity());
        detail.setSurplusQuantity(detail.getOutQuantity() - detail.getReturnQuantity());
        if (detail.getSurplusQuantity() < 0) {
          detail.setSurplusQuantity(0);
        }
        updateDetails.add(detail);
      }
      if (detail.getOutQuantity() > detail.getReturnQuantity()) {
        status = StockLoanOutStatus.PART_RETURN;
      }
    }
    stockLoanOut.setStatus(status);
    getTransactionTemplate().execute(() -> {
      stockLoanOutDetailService.batchModify(updateDetails);
      update(stockLoanOut);
    });
    BIZ_LOGGER
        .log(stockLoanOut.getStockLoanOutId(), "归还", "还入单：{}", stockLoanIn.getStockLoanInCode());
  }

  @Override
  public void verificationDetail(StockLoanOut stockLoanOut, StockLoanOutVerificationBO bo) {
    StockLoanOutDetail detail = stockLoanOutDetailService.getByKey(bo.getStockLoanOutDetailId());
    if (detail.getSurplusQuantity() == 0) {
      throw new OmsException("此商品已经全部归还");
    }
    detail.setStockLoanOutDetailId(bo.getStockLoanOutDetailId());
    detail.setSurplusQuantity(0);
    detail.setVerificationUser(BizContext.getNickname());
    detail.setVerificationReason(bo.getVerificationReason());

    stockLoanOut.setVersion(bo.getVersion());
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        stockLoanOutDetailService.modify(detail);
        return update(stockLoanOut);
      });
      BIZ_LOGGER.log(stockLoanOut.getStockLoanOutId(), "核销", "{}", detail.getSkuCode());
    } catch (Exception e) {
      LOGGER.error("核销借出单明细失败,借出单明细信息: {}", JsonUtil.toJson(bo));
      LOGGER.error("核销借出单明细失败,堆栈信息: ", e);
      throw new OmsException("核销借出单明细失败");
    }
  }


  static class DetailStockChange {

    private StockLoanOutDetail detail;
    private int quantity;

    public DetailStockChange(StockLoanOutDetail detail, int quantity) {
      this.detail = detail;
      this.quantity = quantity;
    }
  }
}