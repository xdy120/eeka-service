package com.greatonce.oms.biz.impl.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.impl.RabbitMqProducer;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.vip.VipReturnDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.biz.vip.VipReturnService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO.WmsAutoInDetail;
import com.greatonce.oms.bo.vip.VipReturnNoticeCancelBO;
import com.greatonce.oms.bo.vip.VipReturnNoticeExportBO;
import com.greatonce.oms.bridge.wms.StockInOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.StockInOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.StockInOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.StockInOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.StockInOrderQueryResponse.WmsStockInOrderStatus;
import com.greatonce.oms.dao.vip.VipReturnNoticeDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.stock.StockType;
import com.greatonce.oms.domain.enums.vip.VipReturnNoticeStatus;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.domain.vip.VipReturnDetail;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.message.vip.VipReturnNoticeEntryMessage;
import com.greatonce.oms.query.vip.VipReturnNoticeDetailQuery;
import com.greatonce.oms.query.vip.VipReturnNoticeQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 唯品退货通知单. VipReturnNotice <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-03-20
 */

@Service
public class VipReturnNoticeServiceImpl extends
    AbstractVersionService<VipReturnNotice, VipReturnNoticeQuery> implements
    VipReturnNoticeService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.VIP_RETURN_NOTICE);
  private static final Logger LOGGER = LoggerFactory.getLogger(VipReturnNoticeServiceImpl.class);
  @Autowired
  private VipReturnNoticeDao dao;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private VipReturnNoticeDetailService vipReturnNoticeDetailService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private RabbitMqProducer producer;
  @Autowired
  private VipReturnDetailService vipReturnDetailService;
  @Autowired
  private ProductSkuService productSkuService;
  @Resource
  private IdGenerator vipReturnIdGenerator;
  @Resource
  private CodeGenerator vipReturnNoticeCodeGenerator;
  @Autowired
  private VipReturnService vipReturnService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private StockService stockService;
  @Autowired
  private MessageExporter messageExporter;

  @Override
  protected QueryDao<VipReturnNotice, VipReturnNoticeQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return vipReturnIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public void noticeWms(VipReturnNotice vipReturnNotice) {
    if (vipReturnNotice.getStatus() != VipReturnNoticeStatus.CREATED
        && vipReturnNotice.getStatus() != VipReturnNoticeStatus.NOTICE_FAILED) {
      throw new OmsException("只有新建或推送失败的通知单才能推送给仓库");
    }
    Warehouse warehouse = warehouseService.getByKey(vipReturnNotice.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");
    StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
        .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
    StockInOrderCreateRequest request = new StockInOrderCreateRequest(warehouse);
    Store store = storeService.getByKey(vipReturnNotice.getStoreId());
    VipReturnNoticeDetailQuery vipReturnNoticeDetailFilter = new VipReturnNoticeDetailQuery();
    vipReturnNoticeDetailFilter.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
    List<VipReturnNoticeDetail> stockInOrderDetailList = vipReturnNoticeDetailService
        .list(vipReturnNoticeDetailFilter);

    request.setOmsCode(vipReturnNotice.getVipReturnNoticeCode());
    request.setOrderType(OrderType.VIP_RETURN_NOTICE_ORDER);
    request.setCreatedTime(vipReturnNotice.getCreatedTime());

    request.setReceiverName(warehouse.getContact());
    request.setReceiverTelephone(warehouse.getTelephone());
    request.setReceiverMobile(warehouse.getMobile());
    request.setReceiverProvince(warehouse.getProvinceName());
    request.setReceiverCity(warehouse.getCityName());
    request.setReceiverArea(warehouse.getDistrictName());
    request.setReceiverAddress(warehouse.getAddress());

    request.setSenderName(warehouse.getContact());
    request.setSenderMobile(warehouse.getMobile());
    request.setSenderProvince(warehouse.getProvinceName());
    request.setSenderCity(warehouse.getCityName());
    request.setReceiverArea(warehouse.getDistrictName());
    request.setSenderAddress(warehouse.getAddress());

    request.setStoreCode(store.getStoreCode());
    request.setStoreName(store.getStoreName());

    List<StockInOrderCreateRequest.StockInOrderDetail> detailList = new ArrayList<>();
    for (VipReturnNoticeDetail orderDetail : stockInOrderDetailList) {
      StockInOrderCreateRequest.StockInOrderDetail detail = new
          StockInOrderCreateRequest.StockInOrderDetail();
      detail.setSkuCode(orderDetail.getSkuCode());
      detail.setWmsSkuId("");
      detail.setProductCode(orderDetail.getProductCode());
      detail.setProductName(orderDetail.getProductName());
      detail.setQuantity(orderDetail.getNoticeQuantity());
      detailList.add(detail);
    }
    request.setDetails(detailList);
    StockInOrderCreateResponse response = stockInOrderBridge.createOrder(request);
    VipReturnNotice updateInfo = new VipReturnNotice();
    updateInfo.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
    updateInfo.setVersion(vipReturnNotice.getVersion());

    if (response.isSuccess()) {
      updateInfo.setStatus(VipReturnNoticeStatus.NOTICED);
    } else {
      updateInfo.setStatus(VipReturnNoticeStatus.NOTICE_FAILED);
    }
    update(updateInfo);
    BIZ_LOGGER.log(vipReturnNotice.getVipReturnNoticeId(), BizLogger.NOTICE_WMS,
        response.getMessage());
  }

  @Override
  public VipReturnNotice getByCode(String orderCode) {
    VipReturnNotice eg = new VipReturnNotice();
    eg.setVipReturnNoticeCode(orderCode);
    return getByExample(eg);
  }

  @Override
  public void wmsAutoIn(VipReturnNotice vipReturnNotice, WmsAutoInBO wmsAutoInBO) {
    if (VipReturnNoticeStatus.CANCELED == vipReturnNotice.getStatus()) {
      throw new OmsException("当前退货通知单已作废");
    }
    if (InStatus.ALL_IN == vipReturnNotice.getInStatus()) {
      throw new OmsException("当前退货通知单已全部入库");
    }

    List<VipReturnNoticeDetail> details = vipReturnNoticeDetailService
        .listByReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
    List<VipReturnNoticeDetail> updateDetails = new ArrayList<>(details.size());

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
    int totalInQuantity = vipReturnNotice.getInQuantity();
    double totalAmount = vipReturnNotice.getVipAmount();
    boolean match;
    Long vipReturnId = null;
    InStatus inStatus = InStatus.ALL_IN;
    for (VipReturnNoticeDetail detail : details) {
      match = false;
      if (vipReturnId == null) {
        vipReturnId = detail.getVipReturnId();
      }
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
        detail.setVipAmount(
            detail.getVipPrice() * (detail.getInQuantity() + detail.getInSubstandardQuantity()));
        totalInQuantity += detail.getInQuantity() + detail.getInSubstandardQuantity();
        totalAmount += detail.getVipAmount();
        updateDetails.add(detail);
      }
    }

    if (totalInQuantity == 0) {
      throw new OmsException("入库数量不能为0");
    }
    if (vipReturnNotice.getNoticeQuantity() < totalInQuantity) {
      throw new OmsException("入库数量不能大于通知数量");
    }

    final VipReturn vipReturn = vipReturnService.getByKey(vipReturnId);
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(vipReturn.getVirtualWarehouseId());
    VirtualWarehouse substandardWarehouse = virtualWarehouseService
        .getSubstandardWarehouse(virtualWarehouse.getWarehouseId());
    VipReturnNotice updateInfo = new VipReturnNotice();
    updateInfo.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
    updateInfo.setVersion(wmsAutoInBO.getVersion());
    updateInfo.setLastInTime(wmsAutoInBO.getInTime());
    updateInfo.setOuterCode(wmsAutoInBO.getWmsOrderCode());
    updateInfo.setInQuantity(totalInQuantity);
    updateInfo.setVipAmount(totalAmount);
    if (vipReturnNotice.getStatus() == VipReturnNoticeStatus.NOTICE_FAILED) {
      vipReturnNotice.setStatus(VipReturnNoticeStatus.NOTICED);
    }
    updateInfo.setInStatus(
        vipReturnNotice.getNoticeQuantity().equals(totalInQuantity) ? InStatus.ALL_IN
            : InStatus.PART_IN);
    try {
      getTransactionTemplate().execute(() -> {
        //更新退货通知单明细
        vipReturnNoticeDetailService.batchModify(updateDetails);
        for (WmsAutoInDetail inDetail : wmsAutoInBO.getDetails()) {
          if (inDetail.getStockType() == StockType.QUALIFIED) {
            stockService
                .adjustQuantity(vipReturnNotice.getVipReturnNoticeCode(),
                    OrderType.VIP_RETURN_NOTICE_ORDER,
                    inDetail.getSkuId(), inDetail.getSkuCode(), virtualWarehouse,
                    inDetail.getInQuantity());
          } else {
            Assert.notNull(substandardWarehouse, "系统未配置次品仓，不接收次品库存");
            stockService
                .adjustQuantity(vipReturnNotice.getVipReturnNoticeCode(),
                    OrderType.VIP_RETURN_NOTICE_ORDER,
                    inDetail.getSkuId(), inDetail.getSkuCode(), substandardWarehouse,
                    inDetail.getPendingQuantity());
          }
        }
        update(updateInfo);
      });
      BIZ_LOGGER.log(updateInfo.getVipReturnNoticeId(), BizLogger.UPDATE, "唯品退货通知单入库成功");
      details.forEach(x -> getMqProducer().send(
          new StockChangedMessage(x.getSkuId(), BizContext.getNickname(),
              "退货通知单入库")));
      producer.send(new VipReturnNoticeEntryMessage(vipReturnNotice.getVipReturnNoticeId()));
    } catch (
        Exception e) {
      LOGGER.error("唯品退货通知单入库异常,退货通知单信息: {}", JsonUtil.toJson(vipReturnNotice));
      LOGGER.error("唯品退货通知单入库异常,堆栈信息: ", e);
      throw new OmsException("唯品退货通知单入库异常");
    }

  }

  @Override
  public void cancel(VipReturnNotice vipReturnNotice, VipReturnNoticeCancelBO bo) {
    if (vipReturnNotice.getInStatus() != InStatus.NO_IN) {
      throw new OmsException("单据已入库不能取消！");
    }
    if (vipReturnNotice.getStatus() == VipReturnNoticeStatus.NOTICE_FAILED
        || vipReturnNotice.getStatus() == VipReturnNoticeStatus.NOTICED) {
      Warehouse warehouse = warehouseService
          .getByKey(vipReturnNotice.getWarehouseId());
      StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
          .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
      if (vipReturnNotice.getStatus() == VipReturnNoticeStatus.NOTICE_FAILED) {
        StockInOrderQueryRequest queryRequest = new StockInOrderQueryRequest(warehouse);
        queryRequest.setOmsCode(vipReturnNotice.getVipReturnNoticeCode());
        queryRequest.setOrderType(OrderType.VIP_RETURN_NOTICE_ORDER);
        final StockInOrderQueryResponse queryResponse = stockInOrderBridge
            .queryOrder(queryRequest);
        //如果WMS单据不存在直接取消
        if (!queryResponse.isExists()
            || queryResponse.getStatus() == WmsStockInOrderStatus.CANCELED) {
          cancelOms(vipReturnNotice, bo);
          return;
        }
      }
      StockInOrderCancelRequest request = new StockInOrderCancelRequest(warehouse);
      request.setOmsCode(vipReturnNotice.getVipReturnNoticeCode());
      request.setOrderType(OrderType.VIP_RETURN_NOTICE_ORDER);
      StockInOrderCancelResponse response = stockInOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(vipReturnNotice, bo);
      } else {
        throw new OmsException(response.getMessage());
      }
    } else {
      cancelOms(vipReturnNotice, bo);
    }
  }

  private void adjustNoticeQuantity(VipReturnNotice vipReturnNotice) {
    //取消通知单时，退货单明细的扫描数量增加
    VipReturnNoticeDetail eg = new VipReturnNoticeDetail();
    eg.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
    List<VipReturnNoticeDetail> details = vipReturnNoticeDetailService
        .listExample(eg);
    details.forEach(x -> {
      VipReturnDetail vipReturnDetail = new VipReturnDetail();
      vipReturnDetail.setVipReturnDetailId(x.getVipReturnDetailId());
      vipReturnDetail
          .setNoticeQuantity(vipReturnDetail.getNoticeQuantity() - x.getNoticeQuantity());
      vipReturnDetailService.modify(vipReturnDetail);
    });
  }

  private void cancelOms(VipReturnNotice vipReturnNotice, VipReturnNoticeCancelBO bo) {
    try {
      VipReturnNotice updateInfo = new VipReturnNotice();
      updateInfo.setVersion(bo.getVersion());
      updateInfo.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
      updateInfo.setStatus(VipReturnNoticeStatus.CANCELED);
      adjustNoticeQuantity(vipReturnNotice);
      update(updateInfo);
      BIZ_LOGGER.log(vipReturnNotice.getVipReturnNoticeId(), BizLogger.CANCEL, "唯品退货通知单取消成功");
    } catch (Exception e) {
      LOGGER.error("唯品退货通知单取消失败,退货通知单信息: {}", JsonUtil.toJson(vipReturnNotice));
      LOGGER.error("唯品退货通知单取消失败,堆栈信息: ", e);
      throw new OmsException("唯品退货通知单取消失败");
    }
  }

  @Override
  protected void initDefaultValue(VipReturnNotice vipReturnNotice) {
    super.initDefaultValue(vipReturnNotice);
    vipReturnNotice.setPostStatus(PostStatus.UN_POST);
    vipReturnNotice.setVersion(0);
    vipReturnNotice.setInStatus(InStatus.NO_IN);
    vipReturnNotice.setStatus(VipReturnNoticeStatus.CREATED);
    vipReturnNotice.setInQuantity(0);
    vipReturnNotice.setVipAmount(0D);
    vipReturnNotice.setVipReturnNoticeCode(vipReturnNoticeCodeGenerator.next());
    if (Assert.isNull(vipReturnNotice.isVipPriceAbnormal())) {
      vipReturnNotice.setVipPriceAbnormal(false);
    }
    for (VipReturnNoticeDetail detail : vipReturnNotice.getDetails()) {
      detail.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
    }
    if (Assert.isNull(vipReturnNotice.getCreator())) {
      vipReturnNotice.setCreator(BizContext.getNickname());
    }
  }

  @Override
  public int create(VipReturnNotice vipReturnNotice) {
    initDefaultValue(vipReturnNotice);
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        //添加退货单明细
        vipReturnNoticeDetailService.batchCreate(vipReturnNotice.getDetails());
        return insert(vipReturnNotice);
      });
      BIZ_LOGGER.log(vipReturnNotice.getVipReturnNoticeId(), BizLogger.ADD, "唯品退货通知单新增成功");
      return count;
    } catch (Exception e) {
      LOGGER.error("新增唯品退货通知单失败,退货通知单信息: {}", JsonUtil.toJson(vipReturnNotice));
      LOGGER.error("新增唯品退货通知单失败,堆栈信息: ", e);
      throw new OmsException("新增唯品退货通知单失败");
    }
  }

  /**
   * 重新计算唯品退货通知单下明细的金额，如果当前明细金额为空，则重新进行计算
   */
  @Override
  public void recalculateAmount(VipReturnNotice vipReturnNotice, VersionBO bo) {
    VipReturnNoticeDetailQuery query = new VipReturnNoticeDetailQuery();
    query.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
    query.setVipPriceAbnormal(true);
    List<VipReturnNoticeDetail> vipReturnNoticeDetails = vipReturnNoticeDetailService.list(query);
    if (!Assert.isEmpty(vipReturnNoticeDetails)) {
      VipReturnNotice updateInfo = new VipReturnNotice();
      updateInfo.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
      updateInfo.setVipPriceAbnormal(false);
      updateInfo.setVersion(bo.getVersion());

      double totalAmount = 0D;
      ProductSku sku;
      for (VipReturnNoticeDetail detail : vipReturnNoticeDetails) {
        sku = productSkuService.getEffectiveById(detail.getSkuId());
        if (!Assert.isNull(sku.getVipPrice()) && sku.getVipPrice() > 0) {
          detail.setVipPrice(sku.getVipPrice());
          detail.setVipAmount(sku.getVipPrice() * detail.getInQuantity());
          detail.setVipPriceAbnormal(false);
          totalAmount += detail.getVipAmount();
        } else {
          updateInfo.setVipPriceAbnormal(true);
        }
      }
      if (totalAmount > 0) {
        updateInfo.setVipAmount(vipReturnNotice.getVipAmount() + totalAmount);
      }
      try {
        getTransactionTemplate().execute(() -> {
          vipReturnNoticeDetailService.batchModify(vipReturnNoticeDetails);
          update(updateInfo);
        });
        BIZ_LOGGER.log(vipReturnNotice.getVipReturnNoticeId(), BizLogger.UPDATE, "重新计算唯品金额");
        getMqProducer()
            .send(new DataRepostingMessage(vipReturnNotice.getVipReturnNoticeId(),
                "vip.return.notice"));
      } catch (Exception e) {
        LOGGER.error("重新计算失败,退货通知单明细信息: {}", JsonUtil.toJson(vipReturnNoticeDetails));
        LOGGER.error("重新计算失败,堆栈信息: ", e);
        throw new OmsException("重新计算失败");
      }
    }
  }

  @Override
  public void finish(VipReturnNotice notice, VersionBO bo) {
    if (notice.getInStatus() != InStatus.PART_IN) {
      throw new OmsException("只有部分入库的通知单才能完结！");
    }
    notice.setStatus(VipReturnNoticeStatus.FINISH);
    notice.setVersion(bo.getVersion());
    try {
      update(notice);
      BIZ_LOGGER.log(notice.getVipReturnNoticeId(), BizLogger.FINISH);
    } catch (Exception e) {
      LOGGER.error("入库单完结失败，出库单：{}", JsonUtil.toJson(notice));
      LOGGER.error("入库单完结失败，堆栈信息：", e);
      throw new OmsException("入库单完结失败！");
    }
  }

  @Override
  public void reposting(VipReturnNotice vipReturnNotice) {
    if (vipReturnNotice.getInStatus() != InStatus.NO_IN) {
      getMqProducer()
          .send(new DataRepostingMessage(vipReturnNotice.getVipReturnNoticeId(),
              "vip.return.notice"));
    }
  }

  @Override
  public void exportVipReturnNotice(String fileName, VipReturnNoticeQuery query) {
    ExcelHeaderCollection<VipReturnNoticeExportBO> headers = new ExcelHeaderCollection<>();
    headers.add("退货通知单号", VipReturnNoticeExportBO::getVipReturnNoticeCode);
    headers.add("唯品退供单号", VipReturnNoticeExportBO::getVipReturnCode);
    headers.add("状态", x -> x.getStatus().caption());
    headers.add("入库状态", x -> x.getInStatus().caption());
    headers.add("店铺", VipReturnNoticeExportBO::getStoreName);
    headers.add("退入仓库", VipReturnNoticeExportBO::getWarehouseName);
    headers.add("规格编码（SKU）", VipReturnNoticeExportBO::getSkuCode);
    headers.add("规格名称", VipReturnNoticeExportBO::getSkuName);
    headers.add("唯品价", x -> FormatUtil.formatDouble(x.getVipPrice()));
    headers.add("通知数量", x -> FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("入库数量", x -> FormatUtil.formatInteger(x.getInQuantity()));
    headers.add("唯品入库金额", x -> FormatUtil.formatDouble(x.getVipAmount()));
    headers.add("入库时间", x -> FormatUtil.formatLocalDateTime(x.getLastInTime()));
    headers.add("制单人", VipReturnNoticeExportBO::getCreator);
    headers.add("制单时间", x -> FormatUtil.formatLocalDateTime(x.getCreatedTime()));
    headers.add("更新时间", x -> FormatUtil.formatLocalDateTime(x.getModifiedTime()));
    messageExporter.exportExcel(this::listExportVipReturnNotice, headers, query, fileName);
  }

  @Override
  public PageList<VipReturnNoticeExportBO> listExportVipReturnNotice(VipReturnNoticeQuery query,
      int page,
      int pageSize) {
    return dao.listExportVipReturnNotice(query, page, pageSize);
  }
}