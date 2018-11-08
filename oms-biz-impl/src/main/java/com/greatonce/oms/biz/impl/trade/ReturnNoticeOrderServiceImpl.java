package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.ReturnNoticeOrderExportBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bridge.wms.ReturnOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderQueryResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderQueryResponse.WmsReturnOrderStatus;
import com.greatonce.oms.dao.trade.ReturnNoticeOrderDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.InStatus;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.trade.ReturnNoticeOrderStatus;
import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.domain.trade.ReturnNoticeOrderDetail;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.trade.ReturnNoticeOrderEntryMessage;
import com.greatonce.oms.query.trade.ReturnNoticeOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnNoticeOrderQuery;
import com.greatonce.oms.util.FormatUtil;
import com.greatonce.oms.util.excel.ExcelHeaderCollection;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 退货通知单. ReturnNoticeOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class ReturnNoticeOrderServiceImpl extends
    AbstractVersionService<ReturnNoticeOrder, ReturnNoticeOrderQuery> implements
    ReturnNoticeOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.RETURN_NOTICE);
  private static final Logger LOGGER = LoggerFactory.getLogger(ReturnNoticeOrderServiceImpl.class);
  private static final String RETURN_NOTICE = "RETURN_NOTICE";
  @Autowired
  private ReturnNoticeOrderDao dao;
  @Resource
  private IdGenerator returnNoticeOrderIdGenerator;
  @Resource
  private CodeGenerator returnNoticeOrderCodeGenerator;
  @Resource
  private ReturnNoticeOrderDetailService returnNoticeOrderDetailService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private StockService stockService;
  @Autowired
  private ReturnOrderService returnOrderService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private MessageExporter messageExporter;


  @Override
  protected QueryDao<ReturnNoticeOrder, ReturnNoticeOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.returnNoticeOrderIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public int create(ReturnNoticeOrder record) {
    record.setPostStatus(PostStatus.UN_POST);
    record.setInStatus(InStatus.NO_IN);
    record.setStatus(ReturnNoticeOrderStatus.CREATED);
    record.setReturnNoticeOrderId(getIdGenerator().next());
    record.setReturnNoticeOrderCode(returnNoticeOrderCodeGenerator.next());
    record.setVersion(0);
    record.getDetails().forEach(detail -> {
      detail.setReturnNoticeOrderId(record.getReturnNoticeOrderId());
      detail.setInQuantity(0);
      detail.setInSubstandardQuantity(0);
    });

    try {
      int result = getTransactionTemplate().executeWithResult(() -> {
        returnNoticeOrderDetailService.batchCreate(record.getDetails());
        return insert(record);
      });
      BIZ_LOGGER.log(record.getReturnNoticeOrderId(), BizLogger.ADD);
      return result;
    } catch (Exception e) {
      LOGGER.error("退换通知单保存失败，退换通知单：{}", JsonUtil.toJson(record));
      LOGGER.error("退换通知单保存失败，堆栈信息：", e);
      throw new OmsException("退换通知单保存失败!");
    }
  }


  @Override
  public void cancel(ReturnNoticeOrder returnNoticeOrder, VersionBO bo) {
    if (returnNoticeOrder.getInStatus() != InStatus.NO_IN) {
      throw new OmsException("单据已入库不能取消");
    }
    if (returnNoticeOrder.getStatus() == ReturnNoticeOrderStatus.NOTICE_FAILED
        || returnNoticeOrder.getStatus() == ReturnNoticeOrderStatus.NOTICED) {
      Warehouse warehouse = warehouseService
          .getByKey(returnNoticeOrder.getWarehouseId());
      ReturnOrderBridge returnOrderBridge = wmsBridgeFactory
          .getReturnOrderBridge(warehouse.getWmsApp().getWmsType());
      if (returnNoticeOrder.getStatus() == ReturnNoticeOrderStatus.NOTICE_FAILED) {
        ReturnOrderQueryRequest queryRequest = new ReturnOrderQueryRequest(warehouse);
        queryRequest.setOmsCode(returnNoticeOrder.getReturnNoticeOrderCode());
        final ReturnOrderQueryResponse queryResponse = returnOrderBridge
            .queryOrder(queryRequest);
        //如果WMS单据不存在直接取消
        if (!queryResponse.isExists()
            || queryResponse.getStatus() == WmsReturnOrderStatus.CANCELED) {
          cancelOms(returnNoticeOrder, bo);
          return;
        }
      }
      ReturnOrderCancelRequest request = new ReturnOrderCancelRequest(warehouse);
      request.setOmsCode(returnNoticeOrder.getReturnNoticeOrderCode());
      ReturnOrderCancelResponse response = returnOrderBridge.cancelOrder(request);
      if (response.isSuccess()) {
        cancelOms(returnNoticeOrder, bo);
      } else {
        throw new OmsException(response.getMessage());
      }
    } else {
      cancelOms(returnNoticeOrder, bo);
    }
  }

  private void cancelOms(ReturnNoticeOrder returnNoticeOrder, VersionBO bo) {
    ReturnNoticeOrder updateInfo = new ReturnNoticeOrder();
    updateInfo.setReturnNoticeOrderId(returnNoticeOrder.getReturnNoticeOrderId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setStatus(ReturnNoticeOrderStatus.CANCELED);
    modify(updateInfo);
    BIZ_LOGGER.log(returnNoticeOrder.getReturnNoticeOrderId(), BizLogger.CANCEL);
  }

  @Override
  public void noticeWms(ReturnNoticeOrder returnNoticeOrder, VersionBO bo) {
    if (returnNoticeOrder.getStatus() != ReturnNoticeOrderStatus.CREATED
        && returnNoticeOrder.getStatus() != ReturnNoticeOrderStatus.NOTICE_FAILED) {
      throw new OmsException("只有新建或推送失败的通知单才能推送给仓库");
    }

    Warehouse warehouse = warehouseService.getByKey(returnNoticeOrder.getWarehouseId());
    Assert.notNull(warehouse, "未找到仓库");
    Assert.notNull(warehouse.getWmsApp(), "仓库未配置WMS接口");
    ReturnOrderBridge returnOrderBridge = wmsBridgeFactory
        .getReturnOrderBridge(warehouse.getWmsApp().getWmsType());

    //todo 组装出库单数据
    ReturnNoticeOrderDetailQuery returnNoticeOrderDetailQuery = new ReturnNoticeOrderDetailQuery();
    returnNoticeOrderDetailQuery
        .setReturnNoticeOrderId(returnNoticeOrder.getReturnNoticeOrderId());
    List<ReturnNoticeOrderDetail> returnOrderDetailDetailList = returnNoticeOrderDetailService
        .list(returnNoticeOrderDetailQuery);

    ReturnOrderCreateRequest returnOrderCreateRequest = new ReturnOrderCreateRequest(warehouse);
    returnOrderCreateRequest.setOmsCode(returnNoticeOrder.getReturnNoticeOrderCode());
    returnOrderCreateRequest.setOrderType(OrderType.B2C_RETURN_ORDER);

    returnOrderCreateRequest.setSenderName(warehouse.getContact());
    returnOrderCreateRequest.setSenderMobile(warehouse.getMobile());
    returnOrderCreateRequest.setSenderProvince(String.valueOf(warehouse.getProvinceId()));
    returnOrderCreateRequest.setSenderCity(String.valueOf(warehouse.getCityId()));
    returnOrderCreateRequest.setSenderAddress(warehouse.getAddress());

    List<ReturnOrderCreateRequest.ReturnOrderDetail> detailList = new ArrayList<>();
    for (ReturnNoticeOrderDetail orderDetail : returnOrderDetailDetailList) {
      ReturnOrderCreateRequest.ReturnOrderDetail detail = new ReturnOrderCreateRequest.ReturnOrderDetail();
      detail.setTradeId("");
      detail.setOid("");
      detail.setSkuCode(orderDetail.getSkuCode());
      detail.setWmsSkuId("");
      detail.setProductName(orderDetail.getProductName());
      detail.setQuantity(orderDetail.getNoticeQuantity());
      detailList.add(detail);
    }
    returnOrderCreateRequest.setDetails(detailList);
    //todo 组装数据完成
    try {
      getTransactionTemplate().execute(() -> {
        ReturnOrderCreateResponse response = returnOrderBridge
            .createOrder(returnOrderCreateRequest);
        ReturnNoticeOrder updateInfo = new ReturnNoticeOrder();
        updateInfo.setReturnNoticeOrderId(returnNoticeOrder.getReturnNoticeOrderId());
        updateInfo.setVersion(bo.getVersion());
        if (response.isSuccess()) {
          returnNoticeOrder.setStatus(ReturnNoticeOrderStatus.NOTICED);
          updateInfo.setStatus(ReturnNoticeOrderStatus.NOTICED);
          modify(updateInfo);
          BIZ_LOGGER.log(returnNoticeOrder.getReturnNoticeOrderId(), BizLogger.NOTICE_WMS);
        } else {
          returnNoticeOrder.setStatus(ReturnNoticeOrderStatus.NOTICE_FAILED);
          updateInfo.setStatus(ReturnNoticeOrderStatus.NOTICE_FAILED);
          modify(updateInfo);
          BIZ_LOGGER.log(returnNoticeOrder.getReturnNoticeOrderId(), BizLogger.NOTICE_WMS,
              response.getMessage());
        }
      });
    } catch (Exception e) {
      LOGGER.error("退换货通知单通知wms失败，退换货单：{}", JsonUtil.toJson(returnNoticeOrder));
      LOGGER.error("退换货通知单通知wms失败，堆栈信息：", e);
      throw new OmsException("退换货通知单通知wms失败");
    }

  }

  @Override
  public void wmsAutoIn(ReturnNoticeOrder returnNoticeOrder, WmsAutoInBO wmsAutoInBO) {
    boolean allInFlag = true;
    ReturnNoticeOrderDetailQuery returnNoticeOrderDetailQuery = new ReturnNoticeOrderDetailQuery();
    returnNoticeOrderDetailQuery
        .setReturnNoticeOrderId(returnNoticeOrder.getReturnNoticeOrderId());
    List<ReturnNoticeOrderDetail> details = returnNoticeOrderDetailService
        .list(returnNoticeOrderDetailQuery);
    ReturnNoticeOrder noticeOrder = super.getByKey(returnNoticeOrder.getReturnNoticeOrderId());
    List<ReturnNoticeOrderDetail> updateDetails = new ArrayList<>(details.size());
    Map<String, WmsAutoInBO.WmsAutoInDetail> map = CollectionUtil
        .listToMap(wmsAutoInBO.getDetails(), x -> x.getSkuCode());
    int inQuantity = Assert.isNull(noticeOrder.getInQuantity()) ? 0 : noticeOrder.getInQuantity();
    for (ReturnNoticeOrderDetail detail : details) {
      WmsAutoInBO.WmsAutoInDetail wmsAutoInDetail = map.get(detail.getSkuCode());
      if (Assert.isNull(wmsAutoInDetail)) {
        allInFlag = false;
        continue;
      }
      wmsAutoInDetail.setSkuId(detail.getSkuId());
      if (wmsAutoInDetail.getPendingQuantity() >= detail.getNoticeQuantity()) {
        detail.setInQuantity(detail.getNoticeQuantity());
        wmsAutoInDetail
            .setPendingQuantity(wmsAutoInDetail.getPendingQuantity() - detail.getNoticeQuantity());
      } else {
        detail.setInQuantity(wmsAutoInDetail.getPendingQuantity());
        wmsAutoInDetail.setPendingQuantity(0);
      }
      inQuantity += detail.getInQuantity();
      updateDetails.add(detail);
      if (!detail.getNoticeQuantity().equals(detail.getInQuantity())) {
        allInFlag = false;
      }
    }

    ReturnNoticeOrderDetail noticeOrderDetail = details.get(0);
    Long returnOrderId = noticeOrderDetail.getReturnOrderId();
    ReturnOrder returnOrder = returnOrderService.getByKey(returnOrderId);
    Long inVirtualWarehouseId = returnOrder.getInVirtualWarehouseId();
    VirtualWarehouse virtualWarehouse = virtualWarehouseService.getByKey(inVirtualWarehouseId);

    ReturnNoticeOrder updateInfo = new ReturnNoticeOrder();
    updateInfo.setReturnNoticeOrderId(returnNoticeOrder.getReturnNoticeOrderId());
    updateInfo.setInStatus(allInFlag ? InStatus.ALL_IN : InStatus.PART_IN);
    updateInfo.setVersion(wmsAutoInBO.getVersion());
    updateInfo.setInQuantity(inQuantity);
    updateInfo.setLastInTime(wmsAutoInBO.getInTime());
    updateInfo.setOuterCode(wmsAutoInBO.getWmsOrderCode());
    if (returnNoticeOrder.getStatus() == ReturnNoticeOrderStatus.NOTICE_FAILED) {
      updateInfo.setStatus(ReturnNoticeOrderStatus.NOTICED);
    }
    try {
      getTransactionTemplate().execute(() -> {
        returnNoticeOrderDetailService.batchModify(updateDetails);
        update(updateInfo);
        map.forEach((k, v) -> {
          stockService
              .adjustQuantity(noticeOrder.getReturnNoticeOrderCode(),
                  OrderType.B2C_RETURN_NOTICE_ORDER,
                  v.getSkuId(), v.getSkuCode(), virtualWarehouse, v.getInQuantity());
        });
      });
      BIZ_LOGGER.log(returnNoticeOrder.getReturnNoticeOrderId(), "入库");
      getMqProducer()
          .send(new ReturnNoticeOrderEntryMessage(returnNoticeOrder.getReturnNoticeOrderId()));
    } catch (Exception e) {
      LOGGER.error("wms入库失败，入库通知单：{},{}", JsonUtil.toJson(returnNoticeOrder),
          JsonUtil.toJson(wmsAutoInBO));
      LOGGER.error("wms入库失败，堆栈信息：", e);
      throw new OmsException("wms入库失败！");
    }
  }


  @Override
  public ReturnNoticeOrder getByCode(String orderCode) {
    ReturnNoticeOrder eg = new ReturnNoticeOrder();
    eg.setReturnNoticeOrderCode(orderCode);
    return getByExample(eg);
  }

  @Override
  public void exportNoticeOrder(String fileName, ReturnNoticeOrderQuery query) {
    ExcelHeaderCollection<ReturnNoticeOrderExportBO> headers = new ExcelHeaderCollection<>();
    headers.add("通知单号",ReturnNoticeOrderExportBO::getReturnNoticeOrderCode);
    headers.add("状态",x->x.getStatus().caption());
    headers.add("入库状态",x->x.getInStatus().caption());
    headers.add("退入仓库",ReturnNoticeOrderExportBO::getWarehouseName);
    headers.add("箱码（包裹号）",ReturnNoticeOrderExportBO::getBoxNo);
    headers.add("退换货单号",ReturnNoticeOrderExportBO::getReturnOrderCode);
    headers.add("规格编码（SKU）",ReturnNoticeOrderExportBO::getSkuCode);
    headers.add("规格名称",ReturnNoticeOrderExportBO::getSkuName);
    headers.add("通知数量",x->FormatUtil.formatInteger(x.getNoticeQuantity()));
    headers.add("入库数量",x->FormatUtil.formatInteger(x.getInQuantity()));
    headers.add("入库时间",x->FormatUtil.formatLocalDateTime(x.getLastInTime()));
    headers.add("制单人",ReturnNoticeOrderExportBO::getCreator);
    headers.add("制单时间",x->FormatUtil.formatLocalDateTime(x.getCreatedTime()));
    headers.add("更新时间",x->FormatUtil.formatLocalDateTime(x.getModifiedTime()));
    messageExporter.exportExcel(this::listExportReturnNoticeOrder, headers, query, fileName);
  }

  @Override
  public PageList<ReturnNoticeOrderExportBO> listExportReturnNoticeOrder(ReturnNoticeOrderQuery query,
      int page, int pageSize) {
    return dao.listExportReturnNoticeOrder(query,page,pageSize);
  }

  @Override
  public void reposting(ReturnNoticeOrder returnNoticeOrder) {
    if (returnNoticeOrder.getStatus() == ReturnNoticeOrderStatus.NOTICED) {
      getMqProducer()
          .send(new DataRepostingMessage(returnNoticeOrder.getReturnNoticeOrderId(), "trade.return.notice.order"));
    }
  }
}