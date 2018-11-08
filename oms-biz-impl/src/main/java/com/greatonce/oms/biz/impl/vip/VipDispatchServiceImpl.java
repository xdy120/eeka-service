package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.stock.StockOutBatchService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.vip.VipDeliveryService;
import com.greatonce.oms.biz.vip.VipDispatchDetailService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.biz.vip.VipSalesOrderDetailService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutDetail;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutPackage;
import com.greatonce.oms.bo.vip.VipDispatchBindDeliveryBO;
import com.greatonce.oms.bo.vip.VipDispatchCancelBO;
import com.greatonce.oms.bo.vip.VipDispatchOrderBO;
import com.greatonce.oms.bo.vip.VipPickOrderDownloadBO;
import com.greatonce.oms.bridge.mall.impl.vip.VipPickOrderBridge;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipOrderInPickQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderDetailQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.request.VipPickOrderQueryRequest;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipOrderInPickQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipPickOrderDetailQueryResponse;
import com.greatonce.oms.bridge.mall.impl.vip.response.VipPickOrderQueryResponse;
import com.greatonce.oms.bridge.wms.StockOutOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest.StockOutOrderDetail;
import com.greatonce.oms.bridge.wms.request.StockOutOrderQueryRequest;
import com.greatonce.oms.bridge.wms.request.VipDeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.StockOutOrderQueryResponse.WmsStockOutOrderStatus;
import com.greatonce.oms.dao.vip.VipDispatchDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.StockDispatchRule;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.base.setting.IoBillSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.vip.VipDispatchStatus;
import com.greatonce.oms.domain.enums.vip.VipJitType;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.message.vip.VipDispatchBindMessage;
import com.greatonce.oms.message.vip.VipDispatchDeliveryMessage;
import com.greatonce.oms.query.vip.VipDispatchDetailQuery;
import com.greatonce.oms.query.vip.VipDispatchQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 唯品拣货单. VipDispatch <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-12-05
 */

@Service
public class VipDispatchServiceImpl extends
    AbstractVersionService<VipDispatch, VipDispatchQuery> implements VipDispatchService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.VIP_DISPATCH);
  private static final Logger LOGGER = LoggerFactory.getLogger(VipDispatchServiceImpl.class);
  @Resource
  private IdGenerator vipDispatchIdGenerator;
  @Resource
  private CodeGenerator vipDispatchCodeGenerator;
  @Resource
  private VipDispatchDetailService vipDispatchDetailService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Resource
  private VipDispatchDao dao;
  @Autowired
  private StoreService storeService;
  @Autowired
  private StockService stockService;
  @Autowired
  private VipDeliveryService vipDeliveryService;
  @Autowired
  private VipSalesOrderDetailService vipSalesOrderDetailService;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private MessageExporter messageExporter;
  @Autowired
  private VipPickOrderBridge vipPickOrderBridge;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private DataDictItemService dataDictItemService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private StockOutBatchService stockOutBatchService;

  @Override
  protected QueryDao<VipDispatch, VipDispatchQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipDispatchIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(VipDispatch vipDispatch) {
    super.initDefaultValue(vipDispatch);
    vipDispatch.setVipDispatchCode(vipDispatchCodeGenerator.next());
    if (Assert.isNull(vipDispatch.isAbnormal())) {
      vipDispatch.setAbnormal(false);
    }
    if (Assert.isNull(vipDispatch.getStatus())) {
      vipDispatch.setStatus(VipDispatchStatus.CREATED);
    }
    vipDispatch.setPostStatus(PostStatus.UN_POST);
    vipDispatch.setOutStatus(OutStatus.NO_OUT);
    vipDispatch.setCreator(BizContext.getNickname());
    vipDispatch.setOutQuantity(0);
    vipDispatch.setVipAmount(0D);
    vipDispatch.setVipPriceAbnormal(false);
    int noticeQuantity = 0;
    for (VipDispatchDetail detail : vipDispatch.getDetails()) {
      detail.setVipDispatchId(vipDispatch.getVipDispatchId());
      noticeQuantity += detail.getNoticeQuantity();
      if (Assert.isNull(detail.getVipPrice()) || detail.getVipPrice() <= 0) {
        vipDispatch.setVipPriceAbnormal(true);
      }
    }
    vipDispatch.setNoticeQuantity(noticeQuantity);
  }

  @Override
  public int create(VipDispatch vipDispatch) {
    Assert.notNull(vipDispatch.getDetails(), "明细不能为空");
    try {
      initDefaultValue(vipDispatch);
      int count = getTransactionTemplate().executeWithResult(() -> {
        vipDispatchDetailService.batchCreate(vipDispatch.getDetails());
        return insert(vipDispatch);
      });
      BIZ_LOGGER.log(vipDispatch.getVipDispatchId(), BizLogger.ADD, "手工生成");
      return count;
    } catch (Exception e) {
      LOGGER.error("新建唯品拣货单失败,拣货单信息: {}", JsonUtil.toJson(vipDispatch));
      LOGGER.error("新建唯品拣货单失败,堆栈信息: ", e);
      throw new OmsException("新建唯品拣货单失败");
    }
  }

  /**
   * 只有新增与已生成状态才能绑定出仓单
   */
  @Override
  public void bindDelivery(VipDispatch vipDispatch, VipDispatchBindDeliveryBO bo) {
    IoBillSetting ioBillSetting = settingService.getIoBillSetting();
    VipDelivery vipDelivery = vipDeliveryService.getByKey(bo.getVipDeliveryId());
    if (!vipDelivery.getVipWarehouseCode().equals(vipDispatch.getVipWarehouseCode())) {
      throw new OmsException("拣货单与出仓单唯品仓库不一致");
    }
    if (!vipDelivery.getStoreId().equals(vipDispatch.getStoreId())) {
      throw new OmsException("拣货单与出仓单店铺不一致");
    }
    if (VipDispatchStatus.AUDITED != vipDispatch.getStatus()) {
      throw new OmsException("只有已审核的拣货单才能绑定出仓单");
    }
    if (Assert.isTrue(ioBillSetting.isVipPriceAbnormalIntercept())
        && Assert.isTrue(vipDispatch.isVipPriceAbnormal())) {
      throw new OmsException("请先维护唯品价后再绑定出仓单");
    }
    if (!vipDispatch.getPickingCode().startsWith("FPICK")
        && vipDelivery.getJitType() == VipJitType.JIT) {
      throw new OmsException("普通拨拣货单不能绑定预调拨的出仓单");
    }
    if (vipDispatch.getPickingCode().startsWith("FPICK")
        && vipDelivery.getJitType() != VipJitType.JIT) {
      throw new OmsException("预调拨拣货单只能绑定预调拨的出仓单");
    }

    VipDispatch updateInfo = new VipDispatch();
    updateInfo.setVipDispatchId(vipDispatch.getVipDispatchId());
    updateInfo.setVipDeliveryId(bo.getVipDeliveryId());
    updateInfo.setVipDeliveryCode(bo.getVipDeliveryCode());
    updateInfo.setStatus(VipDispatchStatus.BIND);
    updateInfo.setVersion(vipDispatch.getVersion());
    update(updateInfo);
    BIZ_LOGGER.log(vipDispatch.getVipDispatchId(), "绑定出仓单", bo.getVipDeliveryCode());
    getMqProducer().send(new VipDispatchBindMessage(vipDispatch.getVipDispatchId()));
  }

  /**
   * 现在与档期没关系 先不要转移到档期占用
   */
  @Override
  public void finish(VipDispatch vipDispatch, VersionBO bo) {
    if (vipDispatch.getOutStatus() != OutStatus.PART_OUT) {
      throw new OmsException("只有部分出库的拣货单才能完结");
    }
    VipDispatch updateInfo = new VipDispatch();
    updateInfo.setVipDispatchId(vipDispatch.getVipDispatchId());
    updateInfo.setStatus(VipDispatchStatus.FINISH);
    updateInfo.setVersion(bo.getVersion());
    List<VipDispatchDetail> vipDispatchDetails = vipDispatchDetailService
        .listDetails(vipDispatch.getVipDispatchId());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(vipDispatch.getVipDispatchId(), StockOccupancyType.VIP_DISPATCH);
        update(updateInfo);
      });
      vipDispatchDetails.forEach(x -> {
        if (x.getOutQuantity() < x.getNoticeQuantity()) {
          getMqProducer().send(
              new StockChangedMessage(vipDispatch.getVipDispatchCode(), x.getSkuId(),
                  BizContext.getNickname(),
                  "唯品拣货单完结"));
        }
      });
      getMqProducer().send(new VipDispatchBindMessage(vipDispatch.getVipDispatchId()));
      BIZ_LOGGER.log(updateInfo.getVipDispatchId(), BizLogger.FINISH, "完结唯品拣货单成功");
    } catch (Exception e) {
      LOGGER.error("完结唯品拣货单失败,拣货单信息: {}", JsonUtil.toJson(vipDispatch));
      LOGGER.error("完结唯品拣货单失败,堆栈信息: ", e);
      throw new OmsException("完结唯品拣货单失败");
    }
  }

  /**
   * 再次匹配铺货关系和系统商品
   */
  @Override
  public void match(VipDispatch dispatch) {
    VipDispatchDetail eg = new VipDispatchDetail();
    eg.setAbnormal(true);
    eg.setVipDispatchId(dispatch.getVipDispatchId());
    List<VipDispatchDetail> details = vipDispatchDetailService.listExample(eg);
    dispatch.setDetails(details);
    matchProduct(dispatch);
    try {
      getTransactionTemplate().execute(() -> {
        vipDispatchDetailService.batchModify(details);
        insertStockOccupancy(dispatch);
        update(dispatch);
      });
      BIZ_LOGGER.log(dispatch.getVipDispatchId(), BizLogger.UPDATE, "重新匹配异常商品");
      details.forEach(x -> {
        if (!x.isAbnormal()) {
          getMqProducer().send(
              new StockChangedMessage(dispatch.getVipDispatchCode(), x.getSkuId(),
                  BizContext.getNickname(),
                  "拣货单重新匹配异常商品"));
        }
      });
    } catch (Exception e) {
      LOGGER.error("唯品拣货单匹配异常失败,拣货单信息: {}", JsonUtil.toJson(dispatch));
      LOGGER.error("唯品拣货单匹配异常失败,堆栈信息: ", e);
      throw new OmsException("唯品拣货单匹配异常失败");
    }
  }

  @Override
  public List<VipDispatchOrderBO> queryDispatch(Long vipDeliveryId) {
    return dao.queryDispatch(vipDeliveryId);
  }

  @Override
  public void noticeWms(VipDispatch vipDispatch) {
    if (vipDispatch.getStatus() != VipDispatchStatus.BIND
        && vipDispatch.getStatus() != VipDispatchStatus.NOTICE_FAILED) {
      throw new OmsException("只有已绑定出仓单或推送失败的拣货单才能推送给仓库");
    }
    Assert.notNull(vipDispatch.getVipDeliveryId(), "拣货单未关联出仓单");
    Warehouse warehouse = warehouseService.getByKey(vipDispatch.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");

    StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
        .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());
    VipDeliveryOrderCreateRequest request = new VipDeliveryOrderCreateRequest(warehouse);
    VipDelivery vipDelivery = vipDeliveryService.getByKey(vipDispatch.getVipDeliveryId());
    Store store = storeService.getByKey(vipDispatch.getStoreId());
    VipDispatchDetailQuery vipDispatchDetailFilter = new VipDispatchDetailQuery();
    vipDispatchDetailFilter.setVipDispatchId(vipDispatch.getVipDispatchId());
    List<VipDispatchDetail> details = vipDispatchDetailService
        .list(vipDispatchDetailFilter);

    request.setOmsCode(vipDispatch.getVipDispatchCode());
    request.setOrderType(OrderType.VIP_DISPATCH_ORDER);
    request.setCreatedTime(vipDispatch.getCreatedTime());
    request.setStoreCode(store.getStoreCode());
    request.setStoreName(store.getStoreName());

    request.setReceiverName("");
    request.setReceiverTelephone("");
    request.setReceiverMobile("");
    request.setReceiverProvince("");
    request.setReceiverCity("");
    request.setReceiverAddress("");

    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setSenderArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());

    List<StockOutOrderDetail> detailList = new ArrayList<>(details.size());
    for (VipDispatchDetail orderDetail : details) {
      StockOutOrderDetail detail = new StockOutOrderDetail();
      detail.setSkuCode(orderDetail.getSkuCode());
      detail.setWmsSkuId("");
      detail.setProductCode(orderDetail.getProductCode());
      detail.setProductName(orderDetail.getProductName());
      detail.setQuantity(orderDetail.getNoticeQuantity());
      detailList.add(detail);
    }
    request.setDetails(detailList);

    request.setExpressNo(vipDelivery.getWaybillNumber());
    request.setVipArrivalTime(vipDelivery.getArrivalDate() + " " + vipDelivery.getArrivalTime());
    request.setVipPickingCode(vipDispatch.getPickingCode());
    request.setVipStorageNo(vipDelivery.getStorageNo());
    request.setVipCarrierName(vipDelivery.getCarrierName());
    request.setVipDeliveryMethod(vipDelivery.getDeliveryMethodName());
    request.setBrandCode(vipDelivery.getBrandCode());
    request.setBrandName(vipDelivery.getBrandName());
    request.setVipWarehouseCode(vipDelivery.getVipWarehouseCode());
    request.setVipWarehouseName(vipDelivery.getVipWarehouseName());

    StockOutOrderCreateResponse response = stockOutOrderBridge.createOrder(request);
    VipDispatch updateInfo = new VipDispatch();
    updateInfo.setVipDispatchId(vipDispatch.getVipDispatchId());
    updateInfo.setVersion(vipDispatch.getVersion());
    if (response.isSuccess()) {
      updateInfo.setStatus(VipDispatchStatus.NOTICED);
    } else {
      updateInfo.setStatus(VipDispatchStatus.NOTICE_FAILED);
    }
    update(updateInfo);
    BIZ_LOGGER
        .log(vipDispatch.getVipDispatchId(), BizLogger.NOTICE_WMS, response.getMessage());
  }

  @Override
  public VipDispatch getByCode(String orderCode) {
    VipDispatch eg = new VipDispatch();
    eg.setVipDispatchCode(orderCode);
    return getByExample(eg);
  }

  @Override
  public VipDispatch getByPickCode(String pickCode) {
    VipDispatch eg = new VipDispatch();
    eg.setPickingCode(pickCode);
    return getByExample(eg);
  }

  @Override
  public void cancel(VipDispatch vipDispatch, VipDispatchCancelBO bo) {
    if (vipDispatch.getOutStatus() != OutStatus.NO_OUT) {
      throw new OmsException("单据已经出库，不能取消！");
    }
    if (vipDispatch.getStatus() == VipDispatchStatus.CANCELED) {
      throw new OmsException("单据已取消");
    }
    if (vipDispatch.getStatus() == VipDispatchStatus.NOTICED
        || vipDispatch.getStatus() == VipDispatchStatus.NOTICE_FAILED) {
      Warehouse warehouse = warehouseService.getByKey(vipDispatch.getWarehouseId());
      StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
          .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());

      if (vipDispatch.getStatus() == VipDispatchStatus.NOTICE_FAILED) {
        StockOutOrderQueryRequest queryRequest = new StockOutOrderQueryRequest(warehouse);
        queryRequest.setOmsCode(vipDispatch.getVipDispatchCode());
        final StockOutOrderQueryResponse queryResponse = stockOutOrderBridge
            .queryOrder(queryRequest);
        //如果WMS单据不存在直接取消
        if (!queryResponse.isExists()
            || queryResponse.getStatus() == WmsStockOutOrderStatus.CANCELED) {
          cancelOms(vipDispatch, bo);
          return;
        }
      }

      StockOutOrderCancelRequest request = new StockOutOrderCancelRequest(warehouse);
      request.setOmsCode(vipDispatch.getVipDispatchCode());
      request.setOrderType(OrderType.VIP_DISPATCH_ORDER);
      StockOutOrderCancelResponse response = stockOutOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(vipDispatch, bo);
      } else {
        throw new OmsException(response.getMessage());
      }
    } else {
      cancelOms(vipDispatch, bo);
    }
  }

  private void cancelOms(VipDispatch vipDispatch, VipDispatchCancelBO bo) {

    VipDispatch updateInfo = new VipDispatch();
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setVipDispatchId(vipDispatch.getVipDispatchId());
    updateInfo.setStatus(VipDispatchStatus.CANCELED);

    List<VipDispatchDetail> vipDispatchDetails = vipDispatchDetailService
        .listDetails(vipDispatch.getVipDispatchId());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(vipDispatch.getVipDispatchId(), StockOccupancyType.VIP_DISPATCH);
        update(updateInfo);
      });

      vipDispatchDetails.forEach(x -> {
        getMqProducer().send(
            new StockChangedMessage(x.getSkuId(), BizContext.getNickname(),
                "取消唯品拣货单成功，删除占用"));
      });
      BIZ_LOGGER
          .log(vipDispatch.getVipDispatchId(), BizLogger.CANCEL, "唯品拣货单取消成功");
    } catch (Exception e) {
      LOGGER.error("唯品拣货单取消失败,拣货单信息: {}", JsonUtil.toJson(vipDispatch));
      LOGGER.error("唯品拣货单取消失败,堆栈信息: ", e);
      throw new OmsException("唯品拣货单取消失败");
    }
  }

  @Override
  public void wmsAutoDelivery(VipDispatch vipDispatch, WmsAutoOutBO wmsAutoOutBO) {
    if (vipDispatch.getStatus() == VipDispatchStatus.FINISH) {
      throw new OmsException("单据已完结");
    }
    if (vipDispatch.getStatus() == VipDispatchStatus.CANCELED) {
      throw new OmsException("单据已取消");
    }
    if (vipDispatch.getOutStatus() == OutStatus.ALL_OUT) {
      LOGGER.info("单据已全部出库，不再处理，WMS单号：{}", wmsAutoOutBO.getWmsOrderCode());
      return;
    }
    if (vipDispatch.getStatus() == VipDispatchStatus.NOTICE_FAILED) {
      vipDispatch.setStatus(VipDispatchStatus.NOTICED);
    }

    vipDispatch.setLastOutTime(LocalDateTime.now());
    //获取快递包裹中第一个快递单号
    final Optional<WmsAutoOutPackage> firstPackage = wmsAutoOutBO.getPackages().stream()
        .findFirst();
    VipDelivery vipDelivery = vipDeliveryService.getByKey(vipDispatch.getVipDeliveryId());
    if (firstPackage.isPresent()) {
      final WmsAutoOutPackage outPackage = firstPackage.get();
      if (!Assert.isEmpty(outPackage.getExpressNo()) && !outPackage.getExpressNo()
          .equals(vipDelivery.getWaybillNumber())) {
        vipDelivery.setWaybillNumber(outPackage.getExpressNo());
        vipDeliveryService.modify(vipDelivery);
      }
      if (outPackage.getDeliveryTime() != null) {
        vipDispatch.setLastOutTime(outPackage.getDeliveryTime());
      }
    }

    StockOutBatch stockOutBatch = new StockOutBatch();
    stockOutBatch.setNoticeOrderId(vipDispatch.getVipDispatchId());
    stockOutBatch.setNoticeOrderCode(vipDispatch.getVipDispatchCode());
    stockOutBatch.setNoticeOrderType(OrderType.VIP_DISPATCH_ORDER);
    stockOutBatch.setOutTime(vipDispatch.getLastOutTime());
    stockOutBatch.setWmsOrderCode(wmsAutoOutBO.getWmsOrderCode());
    stockOutBatch.setPostStatus(PostStatus.UN_POST);

    List<VipDispatchDetail> details = vipDispatchDetailService
        .listDetails(vipDispatch.getVipDispatchId());
    List<VipDispatchDetail> updateDetails = new ArrayList<>(details.size());
    List<DetailStockChange> stockChanges = new ArrayList<>(details.size());
    Map<String, StockOutBatchDetail> outBatchDetailMap = new HashMap<>(
        wmsAutoOutBO.getDetails().size());
    Map<String, WmsAutoOutDetail> outDetailMap = new HashMap<>(wmsAutoOutBO.getDetails().size());
    for (WmsAutoOutDetail detail : wmsAutoOutBO.getDetails()) {
      outDetailMap.put(detail.getSkuCode(), detail);
      StockOutBatchDetail batchDetail = new StockOutBatchDetail();
      batchDetail.setOutQuantity(detail.getOutQuantity());
      batchDetail.setSkuCode(detail.getSkuCode());
      outBatchDetailMap.put(detail.getSkuCode(), batchDetail);
    }
    stockOutBatch.setDetails(new ArrayList<>(outBatchDetailMap.values()));

    int totalQuantity = 0;
    double totalAmount = 0D;
    WmsAutoOutDetail outDetail;
    for (VipDispatchDetail detail : details) {
      if (outDetailMap.containsKey(detail.getSkuCode())) {
        StockOutBatchDetail batchDetail = outBatchDetailMap.get(detail.getSkuCode());
        batchDetail.setProductId(detail.getProductId());
        batchDetail.setProductCode(detail.getProductCode());
        batchDetail.setSkuId(detail.getSkuId());
        batchDetail.setSkuCode(detail.getSkuCode());
        batchDetail.setOutAmount(batchDetail.getOutQuantity() * detail.getVipPrice());

        if (detail.getNoticeQuantity() > detail.getOutQuantity()) {
          int balance = detail.getNoticeQuantity() - detail.getOutQuantity();
          int actual;
          outDetail = outDetailMap.get(detail.getSkuCode());
          if (outDetail.getOutQuantity() > balance) {
            actual = balance;
            outDetail.setOutQuantity(outDetail.getOutQuantity() - balance);
          } else {
            actual = outDetail.getOutQuantity();
          }
          if (actual > 0) {
            detail.setOutQuantity(detail.getOutQuantity() + actual);
            detail.setVipAmount(detail.getVipPrice() * detail.getOutQuantity());
            stockChanges.add(new DetailStockChange(detail, actual));
            updateDetails.add(detail);
          }
        }
      }
      totalQuantity += detail.getOutQuantity();
      totalAmount += detail.getVipAmount();
    }
    if (totalQuantity == 0) {
      throw new OmsException("出库数量不能为0");
    }
    if (vipDispatch.getNoticeQuantity() < totalQuantity) {
      throw new OmsException("出库数量不能大于通知数量");
    }

    vipDispatch.setOuterCode(wmsAutoOutBO.getWmsOrderCode());
    vipDispatch.setOutStatus(
        totalQuantity < vipDispatch.getNoticeQuantity() ? OutStatus.PART_OUT : OutStatus.ALL_OUT);
    vipDispatch.setOutQuantity(totalQuantity);
    vipDispatch.setVipAmount(totalAmount);
    vipDispatch.setVersion(wmsAutoOutBO.getVersion());

    try {
      getTransactionTemplate().execute(() -> {
        stockOutBatchService.create(stockOutBatch);
        vipDispatchDetailService.batchModify(updateDetails);
        for (DetailStockChange change : stockChanges) {
          stockService
              .adjustQuantity(vipDispatch.getVipDispatchCode(), OrderType.VIP_DISPATCH_ORDER,
                  change.detail.getSkuId(), change.detail.getSkuCode(),
                  vipDispatch.getWarehouseId(),
                  vipDispatch.getWarehouseName(), vipDispatch.getVirtualWarehouseId(),
                  vipDispatch.getVirtualWarehouseName(), -change.quantity);
          if (change.detail.getNoticeQuantity().equals(change.detail.getOutQuantity())) {
            //删除库存占用
            stockOccupancyService
                .deleteOccupancy(change.detail.getVipDispatchId(),
                    change.detail.getVipDispatchDetailId(),
                    StockOccupancyType.VIP_DISPATCH);
          } else {
            //调整数量
            stockOccupancyService
                .adjustQuantity(change.detail.getVipDispatchId(),
                    change.detail.getVipDispatchDetailId(),
                    StockOccupancyType.VIP_DISPATCH, -change.quantity);
          }
        }
        update(vipDispatch);
      });
      BIZ_LOGGER.log(vipDispatch.getVipDispatchId(), "WMS自动发货");

      getMqProducer().send(
          new VipDispatchDeliveryMessage(vipDispatch.getVipDispatchId(),
              vipDelivery.getVipDeliveryId(), vipDispatch.getOutStatus()));
    } catch (Exception e) {
      LOGGER.error("唯品拣货单出库异常,拣货单信息: {}", JsonUtil.toJson(vipDispatch));
      LOGGER.error("唯品拣货单出库异常,堆栈信息: ", e);
      throw new OmsException("唯品拣货单出库异常");
    }
  }

  /**
   * 自动创建拣货单，用于从唯品接口下载. 1、通过调用唯品接口获取拣货单信息 2、匹配商品明细，优先使用铺货关系，没有铺货关系使用SKU
   * 3、获取拣货单对应的销售单，将销售单占用转为拣货单占用（删除销售单占用，增加拣货单占用） <p> 本期不做 4、如果配货仓库对应的实体仓库不支持此品牌，需要拆为多个拣货单
   */
  @Override
  public void autoCreate(Store store, List<VipDispatch> orders, List<String> outCodes) {
    for (VipDispatch vipDispatch : orders) {
      matchProduct(vipDispatch);
      initDefaultValue(vipDispatch);
    }
    try {
      getTransactionTemplate().execute(() -> {
        vipSalesOrderDetailService.deleteByDispatch(store, outCodes);
        orders.forEach(vipDispatch -> {
          vipDispatchDetailService.batchCreate(vipDispatch.getDetails());
          insertStockOccupancy(vipDispatch);
          insert(vipDispatch);
        });
      });
      orders.forEach(x -> {
        BIZ_LOGGER.log(x.getVipDispatchId(), BizLogger.ADD, "自动下载");
        x.getDetails().forEach(detail -> getMqProducer().send(
            new StockChangedMessage(x.getVipDispatchCode(), detail.getSkuId(),
                BizContext.getNickname(), "唯品拣货单自动下载")));
      });
    } catch (Exception e) {
      LOGGER.error("新增唯品拣货单失败,拣货单信息: {}", JsonUtil.toJson(orders));
      LOGGER.error("新增唯品拣货单失败,堆栈信息: ", e);
      throw new OmsException("新增唯品拣货单失败");
    }

  }

  @Override
  public void exportDispatch(String fileName, VipDispatchQuery vipDispatchQuery) {
    ExcelHeaderCollection<VipDispatch> headers = new ExcelHeaderCollection<>();
    headers.add("拣货单号", VipDispatch::getVipDispatchCode);
    headers.add("状态", x -> FormatUtil.formatEnum(x.getStatus()));
    headers.add("出库状态", x -> FormatUtil.formatEnum(x.getOutStatus()));
    headers.add("唯品拣货单", VipDispatch::getPickingCode);
    headers.add("出仓单编码", VipDispatch::getVipDeliveryCode);
    headers.add("店铺名称", VipDispatch::getStoreName);
    headers.add("仓库名称", VipDispatch::getVirtualWarehouseName);
    headers.add("店铺名称", VipDispatch::getStoreName);
    headers.add("通知数量", x -> FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("出库数量", x -> FormatUtil.formatInteger(x.getOutQuantity()));
    headers.add("差异数", x -> FormatUtil.formatInteger(x.getNoticeQuantity() - x.getOutQuantity()));
    headers.add("制单时间", x -> DateTimeUtil.format(x.getCreatedTime()));
    headers.add("备注", VipDispatch::getRemark);
    messageExporter.exportExcel(this, headers, vipDispatchQuery, fileName);
  }

  private void matchProduct(VipDispatch vipDispatch) {
    vipDispatch.setAbnormal(false);
    vipDispatch.setVipPriceAbnormal(false);
    for (VipDispatchDetail vipDispatchDetail : vipDispatch.getDetails()) {
      vipDispatchDetail.setAbnormal(false);
      vipDispatchDetail.setVipPriceAbnormal(false);
      ProductMallMapping mapping = productMallMappingService
          .getByMallSkuOutCode(vipDispatchDetail.getVipBarcode(), vipDispatch.getStoreId());
      ProductSku sku;
      if (!Assert.isNull(mapping) && Assert.isTrue(mapping.isMatched())) {
        sku = productSkuService.getEffectiveById(mapping.getSkuId());
      } else {
        sku = productSkuService.getEffectiveByCode(vipDispatchDetail.getVipBarcode());
      }
      if (!Assert.isNull(sku)) {
        vipDispatchDetail.setProductId(sku.getProductId());
        vipDispatchDetail.setProductCode(sku.getProductCode());
        vipDispatchDetail.setProductName(sku.getProductName());
        vipDispatchDetail.setSkuId(sku.getSkuId());
        vipDispatchDetail.setSkuCode(sku.getSkuCode());
        vipDispatchDetail.setSkuName(sku.getSkuName());
        vipDispatchDetail.setVipPrice(sku.getVipPrice());
      } else {
        vipDispatchDetail.setAbnormal(true);
        vipDispatch.setAbnormal(true);
      }
    }
  }

  @Override
  public void audit(VipDispatch vipDispatch, VersionBO bo) {
    if (VipDispatchStatus.CREATED != vipDispatch.getStatus()) {
      throw new OmsException("只有新建的拣货单才能审核");
    }
    List<VipDispatchDetail> details = vipDispatchDetailService
        .listDetails(vipDispatch.getVipDispatchId());
    vipDispatch.setDetails(details);
    vipDispatch.setVersion(bo.getVersion());
    vipDispatch.setStatus(VipDispatchStatus.AUDITED);

    Store store = storeService.getByKey(vipDispatch.getStoreId());
    List<String> outCodes = parseOrderInPack(store, vipDispatch.getPickingCode(),
        details.stream().map(VipDispatchDetail::getPoCode).collect(
            Collectors.toSet()));
    try {
      getTransactionTemplate().execute(() -> {
        vipSalesOrderDetailService.deleteByDispatch(store, outCodes);
        insertStockOccupancy(vipDispatch);
        update(vipDispatch);
      });
      vipDispatch.getDetails().forEach(x -> getMqProducer().send(
          new StockChangedMessage(x.getSkuId(), BizContext.getNickname(),
              "唯品拣货单审核")));
      BIZ_LOGGER.log(vipDispatch.getVipDeliveryId(), BizLogger.AUDIT);
    } catch (Exception e) {
      LOGGER.error("唯品拣货单审核失败,拣货单明细信息: {}", JsonUtil.toJson(vipDispatch.getDetails()));
      LOGGER.error("唯品拣货单审核失败,堆栈信息: ", e);
      throw new OmsException("唯品拣货单审核失败");
    }
  }


  /**
   * 配货单占用库存.
   *
   * @param vipDispatch not null
   */
  public void insertStockOccupancy(VipDispatch vipDispatch) {
    List<StockOccupancy> list = new ArrayList<>(vipDispatch.getDetails().size());
    vipDispatch.getDetails().forEach(detail -> {
      if (detail.getSkuId() != null && detail.getNoticeQuantity() > 0) {
        list.add(DomainUtil
            .createStockOccupancy(vipDispatch.getWarehouseId(), vipDispatch.getWarehouseName(),
                vipDispatch.getVirtualWarehouseId(), vipDispatch.getVirtualWarehouseName(),
                StockOccupancyType.VIP_DISPATCH, detail.getSkuId(), detail.getSkuCode(),
                detail.getNoticeQuantity(), detail.getVipDispatchId(),
                detail.getVipDispatchDetailId(), StockOccupancyStatus.LOCK));
      }
    });
    stockOccupancyService.batchCreate(list);
  }


  /**
   * 1.保存拣货单 2、没有档期时匹配铺货，没有铺货匹配系统商品 3、有档期使用档期仓库，没有档期按品牌计算占用仓库
   */
  public void savePicks(Store store, VipPickOrderQueryResponse response,
      StockDispatchStrategy dispatchStrategy, Map<String, String> itemMap) {
    if (response.isSuccess() && !Assert.isEmpty(response.getOrders())) {
      for (VipPickOrderQueryResponse.VipPickOrder vipPickOrder : response.getOrders()) {
        VipDispatch dispatchOrder = getByPickCode(vipPickOrder.getPickNo());
        if (dispatchOrder == null || dispatchOrder.getStatus() == VipDispatchStatus.CANCELED) {
          List<String> outCodes = parseOrderInPack(store, vipPickOrder.getPickNo(),
              StringUtil.words(vipPickOrder.getPo()));
          List<VipDispatch> orders = parseDispatchOrder(store, vipPickOrder, dispatchStrategy,
              itemMap);
          autoCreate(store, orders, outCodes);
        } else {
          LOGGER.info("拣货单：{}已存在，跳过处理...", vipPickOrder.getPickNo());
        }
      }
    }
  }

  /**
   * 解析拣货单中的订单.
   */
  protected List<String> parseOrderInPack(Store store, String pickNo, Collection<String> poCodes) {
    VipOrderInPickQueryRequest request = new VipOrderInPickQueryRequest(store);
    request.setPage(1);
    request.setPickNo(pickNo);
    List<String> outCodes = new ArrayList<>();
    for (String po : poCodes) {
      request.setPo(po);
      downloadOrderInPick(store, outCodes, request);
    }
    return outCodes;
  }

  protected void downloadOrderInPick(Store store, List<String> outCodes,
      VipOrderInPickQueryRequest request) {
    VipOrderInPickQueryResponse response = vipPickOrderBridge.queryOrderInPick(request);
    if (response.isSuccess() && response.getOrderNos() != null) {
      outCodes.addAll(response.getOrderNos());
    }
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      downloadOrderInPick(store, outCodes, request);
    }
  }

  /**
   * 解析拣货单.
   *
   * @param store 店铺
   * @param vipPickOrder 原始拣货单
   * @return 系统拣货单
   */
  protected List<VipDispatch> parseDispatchOrder(Store store,
      VipPickOrderQueryResponse.VipPickOrder vipPickOrder, StockDispatchStrategy dispatchStrategy,
      Map<String, String> itemMap) {
    StockDispatchRule dispatchRule = dispatchStrategy.getRule();
    Assert.notNull(dispatchRule, "店铺配货策略为空");
    Assert.notEmpty(dispatchRule.getWarehouses(), "店铺配货仓库为空");
    VipDispatch vipDispatch = new VipDispatch();
    if (itemMap.containsKey(vipPickOrder.getWarehouse())) {
      vipDispatch.setVipWarehouseCode(vipPickOrder.getWarehouse());
      vipDispatch.setVipWarehouseName(itemMap.get(vipPickOrder.getWarehouse()));
    }
    vipDispatch.setCreatedTime(vipPickOrder.getCreateTime());
    vipDispatch.setStoreId(store.getStoreId());
    vipDispatch.setStoreName(store.getStoreName());
    vipDispatch.setPickingCode(vipPickOrder.getPickNo());
    VipPickOrderDetailQueryRequest request = new VipPickOrderDetailQueryRequest(store);
    request.setPage(1);
    request.setPickNo(vipPickOrder.getPickNo());
    downloadPickDetail(store, vipDispatch, request);
    return splitVipDispatchOrder(store, vipDispatch, dispatchRule);
  }

  /**
   * 非同一实体仓的拣货单进行拆分
   *
   * @param store 店铺
   * @param vipDispatch 配货单
   * @return 拆分后的拣货单
   */
  protected List<VipDispatch> splitVipDispatchOrder(Store store, VipDispatch vipDispatch,
      StockDispatchRule dispatchRule) {
    StockDispatchWarehouse dispatchWarehouse = dispatchRule.getWarehouses().get(0);
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(dispatchWarehouse.getVirtualWarehouseId());
    Assert.notNull(virtualWarehouse, "未找到配货虚拟仓");

    vipDispatch.setWarehouseId(virtualWarehouse.getWarehouseId());
    vipDispatch.setWarehouseName(virtualWarehouse.getWarehouseName());
    vipDispatch.setVirtualWarehouseId(dispatchWarehouse.getVirtualWarehouseId());
    vipDispatch.setVirtualWarehouseName(dispatchWarehouse.getVirtualWarehouseName());
    vipDispatch.setStatus(VipDispatchStatus.AUDITED);
    return Collections.singletonList(vipDispatch);
  }

  protected void downloadPickDetail(Store store, VipDispatch vipDispatch,
      VipPickOrderDetailQueryRequest request) {
    VipPickOrderDetailQueryResponse response = vipPickOrderBridge.queryPickOrderDetail(request);
    parseDetail(store, vipDispatch, response);
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      downloadPickDetail(store, vipDispatch, request);
    }
  }

  /**
   * 新建拣货单明细
   */
  protected void parseDetail(Store store, VipDispatch vipDispatch,
      VipPickOrderDetailQueryResponse response) {
    if (response.isSuccess() && !Assert.isEmpty(response.getDetails())) {
      if (vipDispatch.getDetails() == null) {
        vipDispatch.setDetails(new ArrayList<>(response.getDetails().size()));
      }
      for (VipPickOrderDetailQueryResponse.VipPickOrderDetail vipPickOrderDetail : response
          .getDetails()) {
        //创建一个新的拣货单明细
        VipDispatchDetail dispatchDetail = new VipDispatchDetail();
        dispatchDetail.setCreatedTime(LocalDateTime.now());
        dispatchDetail.setVipBarcode(vipPickOrderDetail.getBarcode());
        dispatchDetail.setPoCode(vipPickOrderDetail.getPo());
        dispatchDetail.setNoticeQuantity(vipPickOrderDetail.getQuantity());
        vipDispatch.getDetails().add(dispatchDetail);
      }
    }
  }

  @Override
  public void automaticDownload(VipPickOrderDownloadBO bo) {
    Store store = storeService.getByKey(bo.getStoreId());
    StockDispatchStrategy dispatchStrategy = stockDispatchStrategyService
        .getByKey(store.getSetting().getStockStrategy());
    Map<String, String> itemMap = dataDictItemService.listMapByDictId(10804L);
    VipPickOrderQueryRequest request = new VipPickOrderQueryRequest(store);
    if (Assert.isNull(bo.getPickNos())) {
      request.setBeginTime(bo.getCreatedTimeBegin());
      request.setEndTime(bo.getCreatedTimeEnd());
    } else {
      request.setPickNos(StringUtil.join(StringUtil.words(bo.getPickNos())));
    }
    request.setPage(1);
    download(store, request, dispatchStrategy, itemMap);
  }

  @Override
  public void download(Store store, VipPickOrderQueryRequest request,
      StockDispatchStrategy setting, Map<String, String> itemMap) {
    VipPickOrderQueryResponse response = vipPickOrderBridge.queryPickOrder(request);
    LOGGER.info("下载【{}】店铺{}~{}第{}页拣货单共{}条数据...", request.getStore().getStoreName(),
        request.getBeginTime(), request.getEndTime(), request.getPage(), response.getCount());
    savePicks(store, response, setting, itemMap);
    if (response.isHasNext()) {
      request.setPage(request.getPage() + 1);
      download(store, request, setting, itemMap);
    }
  }

  /**
   * 重新计算唯品退货拣货单下明细的金额，如果当前明细金额为空，则重新进行计算
   */
  @Override
  public void recalculateAmount(VipDispatch vipDispatch, VersionBO bo) {
    VipDispatchDetailQuery query = new VipDispatchDetailQuery();
    query.setVipDispatchId(vipDispatch.getVipDispatchId());
    query.setVipPriceAbnormal(true);
    List<VipDispatchDetail> vipDispatchDetails = vipDispatchDetailService.list(query);
    if (!Assert.isEmpty(vipDispatchDetails)) {
      VipDispatch updateInfo = new VipDispatch();
      updateInfo.setVipDispatchId(vipDispatch.getVipDispatchId());
      updateInfo.setVipPriceAbnormal(false);
      updateInfo.setVersion(bo.getVersion());
      double totalAmount = 0D;
      ProductSku sku;
      for (VipDispatchDetail detail : vipDispatchDetails) {
        sku = productSkuService.getEffectiveById(detail.getSkuId());
        if (!Assert.isNull(sku.getVipPrice()) && sku.getVipPrice() > 0) {
          detail.setVipPrice(sku.getVipPrice());
          detail.setVipAmount(sku.getVipPrice() * detail.getOutQuantity());
          detail.setVipPriceAbnormal(false);
          totalAmount += detail.getVipAmount();
        } else {
          updateInfo.setVipPriceAbnormal(true);
        }
      }
      if (totalAmount > 0) {
        updateInfo.setVipAmount(vipDispatch.getVipAmount() + totalAmount);
      }
      try {
        getTransactionTemplate().execute(() -> {
          vipDispatchDetailService.batchModify(vipDispatchDetails);
          update(updateInfo);
        });
        BIZ_LOGGER.log(vipDispatch.getVipDispatchId(), BizLogger.UPDATE, "重新计算唯品金额");
        getMqProducer()
            .send(new DataRepostingMessage(vipDispatch.getVipDispatchId(), "vip.dispatch"));
      } catch (Exception e) {
        LOGGER.error("重新计算失败,拣货单明细信息: {}", JsonUtil.toJson(vipDispatchDetails));
        LOGGER.error("重新计算失败,堆栈信息: ", e);
        throw new OmsException("重新计算失败");
      }
    }
  }

  @Override
  public void reposting(VipDispatch vipDispatch) {
    if (vipDispatch.getOutStatus() != OutStatus.NO_OUT) {
      getMqProducer()
          .send(new DataRepostingMessage(vipDispatch.getVipDispatchId(), "vip.dispatch"));
    }
  }

  @Override
  public VipDispatch replenishment(VipDispatch vipDispatch) {
    if (vipDispatch.getStatus() != VipDispatchStatus.FINISH) {
      throw new OmsException("拣货单状态非完结，不能补货！");
    }
    List<VipDispatchDetail> details = vipDispatchDetailService
        .listDetails(vipDispatch.getVipDispatchId());
    details.removeIf(x -> {
      if (x.getNoticeQuantity().equals(x.getOutQuantity())) {
        return true;
      } else {
        x.setVipDispatchDetailId(null);
        x.setCreatedTime(null);
        x.setModifiedTime(null);
        x.setVipDispatchId(null);
        return false;
      }
    });
    VipDispatch newVipDispatch = new VipDispatch();
    newVipDispatch.setDetails(details);
    newVipDispatch.setVipDispatchCode(vipDispatch.getVipDispatchCode());
    newVipDispatch.setStoreId(vipDispatch.getStoreId());
    newVipDispatch.setStoreName(vipDispatch.getStoreName());
    newVipDispatch.setWarehouseId(vipDispatch.getWarehouseId());
    newVipDispatch.setWarehouseName(vipDispatch.getWarehouseName());
    newVipDispatch.setVipWarehouseCode(vipDispatch.getVipWarehouseCode());
    newVipDispatch.setVipWarehouseName(vipDispatch.getVipWarehouseName());
    newVipDispatch.setVirtualWarehouseId(vipDispatch.getVirtualWarehouseId());
    newVipDispatch.setVirtualWarehouseName(vipDispatch.getVirtualWarehouseName());
    newVipDispatch.setPickingCode(vipDispatch.getPickingCode());
    newVipDispatch.setOuterCode(vipDispatch.getOuterCode());
    newVipDispatch.setRemark(vipDispatch.getRemark());
    return newVipDispatch;
  }

  static class DetailStockChange {

    private VipDispatchDetail detail;
    private int quantity;

    public DetailStockChange(VipDispatchDetail detail, int quantity) {
      this.detail = detail;
      this.quantity = quantity;
    }
  }
}