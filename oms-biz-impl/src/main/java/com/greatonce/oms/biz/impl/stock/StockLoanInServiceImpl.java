package com.greatonce.oms.biz.impl.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.stock.StockLoanInDetailService;
import com.greatonce.oms.biz.stock.StockLoanInService;
import com.greatonce.oms.biz.stock.StockLoanOutDetailService;
import com.greatonce.oms.biz.stock.StockLoanOutService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.stock.StockLoanInCancelBO;
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
import com.greatonce.oms.dao.stock.StockLoanInDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.stock.StockLoanInStatus;
import com.greatonce.oms.domain.enums.stock.StockType;
import com.greatonce.oms.domain.stock.StockLoanIn;
import com.greatonce.oms.domain.stock.StockLoanInDetail;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.stock.StockLoanInQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
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
 * 还入单 StockLoanIn <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-07
 */

@Service
public class StockLoanInServiceImpl extends
    AbstractVersionService<StockLoanIn, StockLoanInQuery> implements StockLoanInService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.STOCK_LOAN_IN);
  private static final Logger LOGGER = LoggerFactory.getLogger(StockLoanInServiceImpl.class);

  @Autowired
  private StockLoanInDao dao;
  @Resource
  private IdGenerator stockLoanInIdGenerator;
  @Resource
  private CodeGenerator stockLoanInCodeGenerator;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private StockLoanInDetailService stockLoanInDetailService;
  @Autowired
  private StockLoanOutService stockLoanOutService;
  @Autowired
  private StockLoanOutDetailService stockLoanOutDetailService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private StockService stockService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;


  @Override
  protected QueryDao<StockLoanIn, StockLoanInQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.stockLoanInIdGenerator;
  }

  @Override
  protected void initDefaultValue(StockLoanIn record) {
    super.initDefaultValue(record);
    record.setVersion(0);
    record.setStatus(StockLoanInStatus.CREATED);
    record.setInStatus(InStatus.NO_IN);
    record.setCreator(BizContext.getNickname());
    record.setStockLoanInCode(stockLoanInCodeGenerator.next());
    record.getDetails().forEach(detail -> {
      detail.setStockLoanInId(record.getStockLoanInId());
    });
  }


  @Override
  public void audit(StockLoanIn stockLoanIn, VersionBO bo) {
    if (StockLoanInStatus.CREATED != stockLoanIn.getStatus()) {
      throw new OmsException("只有新建的还入单才可以审核");
    }
    StockLoanInDetail eg = new StockLoanInDetail();
    eg.setStockLoanInId(stockLoanIn.getStockLoanInId());
    for (StockLoanInDetail detail : stockLoanInDetailService.listExample(eg)) {
      if (detail.getNoticeQuantity() <= 0) {
        throw new OmsException("还入单通知数量必须大于0");
      }
    }
    Warehouse warehouse = warehouseService.getByKey(stockLoanIn.getWarehouseId());
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(stockLoanIn.getVirtualWarehouseId());
    if (!Assert.isNull(warehouse) && !Assert.isNull(virtualWarehouse)) {
      stockLoanIn.setStatus(StockLoanInStatus.AUDITED);
      stockLoanIn.setVersion(bo.getVersion());
      stockLoanIn.setAuditor(BizContext.getNickname());
      stockLoanIn.setAuditedTime(LocalDateTime.now());
      update(stockLoanIn);
      getMqProducer()
          .send(new GeneralMessage(OmsModule.STOCK_LOAN_IN, stockLoanIn.getStockLoanInId(),
              EventType.AUDITED));
      BIZ_LOGGER.log(stockLoanIn.getStockLoanInId(), BizLogger.AUDIT);
    } else {
      throw new OmsException("无效仓库");
    }
  }

  @Override
  public void cancel(StockLoanIn stockLoanIn, StockLoanInCancelBO bo) {
    if (InStatus.NO_IN != stockLoanIn.getInStatus()) {
      throw new OmsException("还入单已入库不能取消");
    }
    if (stockLoanIn.getStatus() == StockLoanInStatus.CANCEL) {
      return;
    }
    if (stockLoanIn.getStatus() == StockLoanInStatus.CREATED) {
      cancelOms(stockLoanIn, bo);
      return;
    }
    if (stockLoanIn.getStatus() == StockLoanInStatus.AUDITED) {
      cancelOms(stockLoanIn, bo);
      return;
    }
    if (StockLoanInStatus.NOTICE_FAILED == stockLoanIn.getStatus()
        || StockLoanInStatus.NOTICED == stockLoanIn.getStatus()) {
      Warehouse warehouse = warehouseService.getByKey(stockLoanIn.getWarehouseId());
      StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
          .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
      if (StockLoanInStatus.NOTICE_FAILED == stockLoanIn.getStatus()) {
        StockInOrderQueryRequest request = new StockInOrderQueryRequest(warehouse);
        request.setOmsCode(stockLoanIn.getStockLoanInCode());
        request.setOrderType(OrderType.IN_ORDER);
        final StockInOrderQueryResponse response = stockInOrderBridge
            .queryOrder(request);
        if (!response.isExists() || response.getStatus() == WmsStockInOrderStatus.CANCELED) {
          cancelOms(stockLoanIn, bo);
          return;
        }
      }
      StockInOrderCancelRequest request = new StockInOrderCancelRequest(warehouse);
      request.setOmsCode(stockLoanIn.getStockLoanInCode());
      request.setOrderType(OrderType.IN_ORDER);
      StockInOrderCancelResponse response = stockInOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(stockLoanIn, bo);
      } else {
        BIZ_LOGGER.log(stockLoanIn.getStockLoanInId(), BizLogger.CANCEL, "WMS取消失败：{}",
            response.getMessage());
        throw new OmsException(response.getMessage());
      }
    }
  }

  private void cancelOms(StockLoanIn stockLoanIn, StockLoanInCancelBO bo) {
    stockLoanIn.setStatus(StockLoanInStatus.CANCEL);
    stockLoanIn.setVersion(bo.getVersion());
    try {
      getTransactionTemplate().execute(() -> {
        update(stockLoanIn);
      });
      BIZ_LOGGER.log(stockLoanIn.getStockLoanInId(), BizLogger.CANCEL, "原因:{}", bo.getReason());
    } catch (Exception e) {
      LOGGER.error("还入单取消失败,借出单信息: {}", JsonUtil.toJson(stockLoanIn));
      LOGGER.error("还入单取消失败,堆栈信息: ", e);
      throw new OmsException("还入单取消失败");
    }
  }

  @Override
  public int create(StockLoanIn stockLoanIn) {
    initDefaultValue(stockLoanIn);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        stockLoanInDetailService.batchCreate(stockLoanIn.getDetails());
        return insert(stockLoanIn);
      });
      BIZ_LOGGER.log(stockLoanIn.getStockLoanInId(), BizLogger.ADD);
      return count;
    } catch (Exception e) {
      LOGGER.error("新增还入单失败,还入单信息: {}", JsonUtil.toJson(stockLoanIn));
      LOGGER.error("新增还入单失败,堆栈信息: {}", e);
      throw new OmsException("新增还入单失败");
    }
  }

  @Override
  public void noticeWms(StockLoanIn stockLoanIn) {
    if (StockLoanInStatus.AUDITED != stockLoanIn.getStatus()
        && StockLoanInStatus.NOTICE_FAILED != stockLoanIn.getStatus()) {
      throw new OmsException("只有已审核或推送失败的还入单才能推送给仓库");
    }
    Warehouse warehouse = warehouseService.getByKey(stockLoanIn.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");

    StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
        .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
    StockInOrderCreateRequest request = new StockInOrderCreateRequest(warehouse);
    List<StockLoanInDetail> inDetails = stockLoanInDetailService
        .listDetails(stockLoanIn.getStockLoanInId());

    request.setOmsCode(stockLoanIn.getStockLoanInCode());
    request.setOrderType(OrderType.LOAN_OUT_ORDER);
    request.setCreatedTime(stockLoanIn.getCreatedTime());

    //仓库信息
    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setSenderArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());
    //拼接请求明细数据
    List<StockInOrderCreateRequest.StockInOrderDetail> stockInOrderDetails = new ArrayList<>();
    for (StockLoanInDetail stockInOrderDetail : inDetails) {
      StockInOrderCreateRequest.StockInOrderDetail detail = new StockInOrderCreateRequest.StockInOrderDetail();
      detail.setQuantity(stockInOrderDetail.getNoticeQuantity());
      detail.setProductCode(stockInOrderDetail.getProductCode());
      detail.setProductName(stockInOrderDetail.getProductName());
      detail.setSkuCode(stockInOrderDetail.getSkuCode());
      detail.setWmsSkuId(FormatUtil.formatLong(stockInOrderDetail.getSkuId()));
      stockInOrderDetails.add(detail);
    }
    request.setDetails(stockInOrderDetails);

    StockInOrderCreateResponse response
        = stockInOrderBridge.createOrder(request);
    StockLoanIn updateInfo = new StockLoanIn();
    updateInfo.setStockLoanInId(stockLoanIn.getStockLoanInId());
    updateInfo.setVersion(stockLoanIn.getVersion());
    if (response.isSuccess()) {
      updateInfo.setStatus(StockLoanInStatus.NOTICED);
    } else {
      updateInfo.setStatus(StockLoanInStatus.NOTICE_FAILED);
    }
    modify(updateInfo);
    BIZ_LOGGER
        .log(stockLoanIn.getStockLoanInId(), BizLogger.NOTICE_WMS,
            response.isSuccess() ? "成功" : response.getMessage());
  }

  @Override
  public StockLoanIn getByCode(String orderCode) {
    StockLoanIn eg = new StockLoanIn();
    eg.setStockLoanInCode(orderCode);
    return getByExample(eg);
  }

  /**
   * 接收WMS回传的入库信息，修改入库单状态和入库数量， 修改借出单还入状态，还入数量，剩余数量；
   */
  public void wmsAutoIn(StockLoanIn stockLoanIn, WmsAutoInBO wmsAutoInBO) {
    if (StockLoanInStatus.CANCEL == stockLoanIn.getStatus()) {
      throw new OmsException("单据已取消");
    }
    if (stockLoanIn.getInStatus() == InStatus.ALL_IN) {
      LOGGER.info("单据已全部入库，不再处理，WMS单号：{}", wmsAutoInBO.getWmsOrderCode());
      return;
    }
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(stockLoanIn.getVirtualWarehouseId());
    VirtualWarehouse substandardWarehouse = virtualWarehouseService
        .getSubstandardWarehouse(stockLoanIn.getWarehouseId());
    List<StockLoanInDetail> details = stockLoanInDetailService
        .listDetails(stockLoanIn.getStockLoanInId());
    List<StockLoanInDetail> updateDetails = new ArrayList<>(details.size());

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
    for (StockLoanInDetail detail : details) {
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

    stockLoanIn.setOuterCode(wmsAutoInBO.getWmsOrderCode());
    stockLoanIn.setVersion(wmsAutoInBO.getVersion());
    stockLoanIn
        .setInStatus(totalInQuantity < totalNoticeQuantity ? InStatus.PART_IN : InStatus.ALL_IN);
    if (stockLoanIn.getStatus() == StockLoanInStatus.NOTICE_FAILED) {
      stockLoanIn.setStatus(StockLoanInStatus.NOTICED);
    }
    try {
      getTransactionTemplate().execute(() -> {
        stockLoanInDetailService.batchModify(updateDetails);
        for (WmsAutoInDetail inDetail : wmsAutoInBO.getDetails()) {
          if (inDetail.getStockType() == StockType.QUALIFIED) {
            stockService
                .adjustQuantity(stockLoanIn.getStockLoanInCode(),
                    OrderType.LOAN_IN_ORDER,
                    inDetail.getSkuId(), inDetail.getSkuCode(), virtualWarehouse,
                    inDetail.getInQuantity());
          } else {
            Assert.notNull(substandardWarehouse, "系统未配置次品仓，不接收次品库存");
            stockService
                .adjustQuantity(stockLoanIn.getStockLoanInCode(),
                    OrderType.LOAN_IN_ORDER,
                    inDetail.getSkuId(), inDetail.getSkuCode(), substandardWarehouse,
                    inDetail.getPendingQuantity());
          }
        }
        update(stockLoanIn);
      });
      updateDetails.forEach(x -> {
        getMqProducer().send(
            new StockChangedMessage(stockLoanIn.getStockLoanInCode(), x.getSkuId(),
                BizContext.getNickname(),
                "还入单入库"));
      });
      getMqProducer().send(
          new GeneralMessage(OmsModule.STOCK_LOAN_IN, stockLoanIn.getStockLoanInId(),
              EventType.IN));
      BIZ_LOGGER.log(stockLoanIn.getStockLoanInCode(), BizLogger.ENTRY);
    } catch (Exception e) {
      LOGGER.error("还入单入库异常,还入单信息: {}", JsonUtil.toJson(stockLoanIn));
      LOGGER.error("还入单入库异常,堆栈信息: ", e);
      throw new OmsException("还入单入库异常");
    }
  }
}