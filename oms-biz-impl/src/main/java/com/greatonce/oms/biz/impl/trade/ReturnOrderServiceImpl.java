package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.MathUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.impl.MessageExporter;
import com.greatonce.oms.biz.impl.RabbitMqProducer;
import com.greatonce.oms.biz.marketing.MemberService;
import com.greatonce.oms.biz.product.ProductService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.trade.ExchangeApplyOrderService;
import com.greatonce.oms.biz.trade.RefundApplyOrderService;
import com.greatonce.oms.biz.trade.RefundOrderService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderOutDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.biz.trade.ReturnSignService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.CreateReturnNoticeOrderBO;
import com.greatonce.oms.bo.trade.ExchangeOccupancyBO;
import com.greatonce.oms.bo.trade.ImageBO;
import com.greatonce.oms.bo.trade.InDetail;
import com.greatonce.oms.bo.trade.OutDetail;
import com.greatonce.oms.bo.trade.RelateSourceOrderBO;
import com.greatonce.oms.bo.trade.ReturnOrderExportBO;
import com.greatonce.oms.bo.trade.ReturnOrderSaveBO;
import com.greatonce.oms.bo.trade.ReturnOrderSavePrepareBO;
import com.greatonce.oms.bo.trade.ScanExpressBO;
import com.greatonce.oms.bo.trade.SourceOrderFilterBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.dao.trade.ReturnOrderDao;
import com.greatonce.oms.domain.DomainUtil;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WmsNoticeStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.marketing.MemberStatus;
import com.greatonce.oms.domain.enums.marketing.PresellType;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.RefundApplyOrderType;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.RefundType;
import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderCreateType;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.enums.trade.SourceType;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.domain.trade.RefundOrderDetail;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.domain.trade.ReturnNoticeOrderDetail;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.domain.trade.ReturnOrderOutDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.message.trade.RefundAuditMessage;
import com.greatonce.oms.message.trade.ReturnNoticeOrderCreateMessage;
import com.greatonce.oms.message.trade.ReturnOrderReviewMessage;
import com.greatonce.oms.query.product.ProductSkuQuery;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.ReturnOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 退换货单. ReturnOrder <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class ReturnOrderServiceImpl extends
    AbstractVersionService<ReturnOrder, ReturnOrderQuery> implements ReturnOrderService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.RETURN_ORDER);
  private static final Logger LOGGER = LoggerFactory.getLogger(ReturnOrderServiceImpl.class);
  @Autowired
  private ReturnOrderDao dao;
  @Resource
  private IdGenerator returnOrderIdGenerator;
  @Resource
  private CodeGenerator returnOrderCodeGenerator;
  @Resource
  private ReturnOrderOutDetailService returnOrderOutDetailService;
  @Resource
  private ReturnOrderDetailService returnOrderDetailService;
  @Autowired
  private ReturnNoticeOrderService returnNoticeOrderService;
  @Autowired
  private RefundOrderService refundOrderService;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private MemberService memberService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private RabbitMqProducer mqProducer;
  @Autowired
  private ReturnOrderService returnOrderService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private RefundApplyOrderService refundApplyOrderService;
  @Autowired
  private ExchangeApplyOrderService exchangeApplyOrderService;
  @Autowired
  private ReturnSignService returnSignService;
  @Autowired
  private MessageExporter messageExporter;


  @Override
  protected QueryDao<ReturnOrder, ReturnOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.returnOrderIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(ReturnOrder entity) {
    super.initDefaultValue(entity);
    entity.setPostStatus(PostStatus.UN_POST);
    if (Assert.isNull(entity.getReturnOrderCode())) {
      entity.setReturnOrderCode(returnOrderCodeGenerator.next());
    }
    if (Assert.isNull(entity.getVersion())) {
      entity.setVersion(0);
    }
    if (Assert.isNull(entity.getStatus())) {
      entity.setStatus(ReturnOrderStatus.CREATED);
    }
    if (Assert.isNull(entity.getNoticeStatus())) {
      entity.setNoticeStatus(WmsNoticeStatus.NONE);
    }

    if (Assert.isNull(entity.isAbnormal())) {
      entity.setAbnormal(false);
    }
    //换货
    if (entity.isExchange() == null) {
      entity.setExchange(false);
    }
    //退款类型
    if (entity.getRefundType() == null) {
      entity.setRefundType(RefundType.RETURN_AND_REFUND);
    }

    //退入
    if (!Assert.isEmpty(entity.getDetails())) {
      entity.getDetails().forEach(in -> {
        //主单退入数量
        if (entity.getQuantity() == null) {
          entity.setQuantity(in.getQuantity());
        } else {
          entity.setQuantity(entity.getQuantity() + in.getQuantity());
        }
        //主单应退金额
        if (entity.getRefundableAmount() == null) {
          entity.setRefundableAmount(
              in.getRefundableAmount() != null ? in.getRefundableAmount() : 0.0D);
        } else {
          entity
              .setRefundableAmount(entity.getRefundableAmount() + in.getRefundableAmount());
        }
      });
    }
    if (!Assert.isEmpty(entity.getOutDetails())) {
      entity.getOutDetails().forEach(out -> {
        entity.setExchange(true);
        if (!Assert.isNull(entity.getRefundableAmount())) {
          entity.setRefundableAmount(entity.getRefundableAmount() - out.getActualAmount());
        }
        out.setReturnOrderId(entity.getReturnOrderId());
      });
    }
  }

  /**
   * 只要新增就用明细加换货占用.
   *
   * @param record 退换货单
   */
  @Override
  public int create(ReturnOrder record) {
    initDefaultValue(record);
    //保存退换货单
    if (record.getDetails() != null && record.getDetails().size() > 0) {
      for (ReturnOrderDetail detail : record.getDetails()) {
        detail.setInQuantity(0);
        detail.setNoticed(false);
        detail.setReturnOrderId(record.getReturnOrderId());
      }
    } else {
      //没退入,退入的数量给 0
      record.setQuantity(0);
    }
    record.setCreator(BizContext.getNickname());
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(record.getOutVirtualWarehouseId());
    try {
      int result = getTransactionTemplate().executeWithResult(() -> {
        if (!Assert.isEmpty(record.getDetails())) {
          returnOrderDetailService.batchCreate(record.getDetails());
        }
        if (!Assert.isEmpty(record.getOutDetails())) {
          returnOrderOutDetailService.batchCreate(record.getOutDetails());
        }
        //增加换出商品的库存占用
        if (!Assert.isEmpty(record.getOutDetails())) {
          record.getOutDetails().forEach(out -> {
            StockOccupancy stockOccupancy = new StockOccupancy();
            stockOccupancy.setVirtualWarehouseId(virtualWarehouse.getVirtualWarehouseId());
            stockOccupancy.setVirtualWarehouseName(virtualWarehouse.getVirtualWarehouseName());
            stockOccupancy.setWarehouseId(virtualWarehouse.getWarehouseId());
            stockOccupancy.setWarehouseName(virtualWarehouse.getWarehouseName());
            stockOccupancy.setSkuId(out.getSkuId());
            stockOccupancy.setSkuCode(out.getSkuCode());
            stockOccupancy.setQuantity(out.getQuantity());
            stockOccupancy.setStockOccupancyType(StockOccupancyType.RETURN_ORDER);
            stockOccupancy.setMainId(record.getReturnOrderId());
            stockOccupancy.setDetailId(out.getReturnOrderOutDetailId());
            stockOccupancy.setStatus(StockOccupancyStatus.LOCK);
            stockOccupancyService.create(stockOccupancy);
            getMqProducer().send(
                new StockChangedMessage(String.valueOf(getIdGenerator().next()), out.getSkuId(),
                    virtualWarehouse.getVirtualWarehouseId(), record.getStoreId(),
                    BizContext.getNickname(), "手动新建换货单"));
          });
        }
        return insert(record);
      });
      BIZ_LOGGER.log(record.getReturnOrderId(), "手动新增");
      return result;
    } catch (Exception e) {
      LOGGER.error("手动新增退换货单失败,退换货单：{}", JsonUtil.toJson(record));
      LOGGER.error("手动新增退换货单失败,堆栈信息：", e);
      throw new OmsException("手动新增退换货单失败！");
    }
  }


  /**
   * 分页查询 带出退入的明细.
   *
   * @param returnOrderFilter 分页查询的条件
   * @param page              页数
   * @param pageSize          页大小
   */
  @Override
  public PageList<ReturnOrder> listPage(ReturnOrderQuery returnOrderFilter, int page,
      int pageSize) {
    PageList<ReturnOrder> returnOrderPageList = super.listPage(returnOrderFilter, page, pageSize);
    returnOrderPageList.getData().forEach(ro -> {
      ReturnOrderDetail orderDetailEg = new ReturnOrderDetail();
      orderDetailEg.setReturnOrderId(ro.getReturnOrderId());
      List<ReturnOrderDetail> returnOrderDetails = returnOrderDetailService
          .listExample(orderDetailEg);
      ro.setDetails(returnOrderDetails);
    });
    return returnOrderPageList;
  }

  @Override
  public int modify(ReturnOrder returnOrder) {
    int count = super.update(returnOrder);
    validateChangedCount(count);
    return count;
  }

  @Override
  public void audit(ReturnOrder returnOrder, VersionBO bo) {
    if (returnOrder.getStatus() != ReturnOrderStatus.CREATED) {
      throw new OmsException("只能审核新建的退换货单");
    }
    returnOrder.setStatus(ReturnOrderStatus.AUDITED);
    returnOrder.setAuditor(BizContext.getNickname());
    returnOrder.setAuditedTime(LocalDateTime.now());
    returnOrder.setVersion(bo.getVersion());
    update(returnOrder);
    BIZ_LOGGER.log(returnOrder.getReturnOrderId(), BizLogger.AUDIT);
  }

  @Override
  public void invalid(ReturnOrder returnOrder, VersionBO bo) {
    if (returnOrder.getStatus() == ReturnOrderStatus.INVALID) {
      throw new OmsException("退换货单已作废，不可再次作废");
    }
    ReturnOrderOutDetail outDetailEg = new ReturnOrderOutDetail();
    outDetailEg.setReturnOrderId(returnOrder.getReturnOrderId());
    List<ReturnOrderOutDetail> returnOrderOutDetails = returnOrderOutDetailService
        .listExample(outDetailEg);
    returnOrder.setStatus(ReturnOrderStatus.INVALID);
    returnOrder.setReturnOrderId(returnOrder.getReturnOrderId());
    returnOrder.setVersion(bo.getVersion());
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService
            .deleteOccupancy(returnOrder.getReturnOrderId(), StockOccupancyType.RETURN_ORDER);
        update(returnOrder);
      });
      if (!Assert.isEmpty(returnOrderOutDetails)) {
        for (ReturnOrderOutDetail out : returnOrderOutDetails) {
          getMqProducer().send(
              new StockChangedMessage(String.valueOf(getIdGenerator().next()), out.getSkuId(),
                  returnOrder.getOutVirtualWarehouseId(), returnOrder.getStoreId(),
                  BizContext.getNickname(), "退换货单作废"));
        }
      }
      BIZ_LOGGER.log(returnOrder.getReturnOrderId(), BizLogger.INVALID);
    } catch (Exception e) {
      LOGGER.error("退换货单作废失败，退换货单：{}", JsonUtil.toJson(returnOrder));
      LOGGER.error("退换货单作废失败，堆栈信息：", e);
      throw new OmsException("退换货单作废失败！");
    }
  }


  @Override
  public List<ReturnOrderDetail> checkReview(ReturnOrder returnOrder) {
    List<ReturnOrderDetail> details = returnOrder.getDetails();
    List<ReturnOrderDetail> returnOrderDetails = new ArrayList<>();
    if (!Assert.isEmpty(details)) {
      ReturnOrderDetail detailEg = new ReturnOrderDetail();
      details.stream().forEach(x -> {
        detailEg.setTradeId(x.getTradeId());
        detailEg.setSkuId(x.getSkuId());
        List<ReturnOrderDetail> detailList = returnOrderDetailService.listExample(detailEg);
        if (!Assert.isEmpty(detailList)) {
          List<ReturnOrderDetail> list = detailList.stream()
              .filter(y -> !y.getReturnOrderId().equals(returnOrder.getReturnOrderId()))
              .collect(Collectors.toList());
          if (!Assert.isEmpty(list)) {
            returnOrderDetails.addAll(list);
          }
        }
      });
    }
    return returnOrderDetails;
  }

  @Override
  public PageList<ExchangeOccupancyBO> getStockOccupancyDetail(StockQuery stockQuery, int page,
      int pageSize) {
    new HashMap<>(10);
    return dao.getStockOccupancyDetail(stockQuery, page, pageSize);
  }

  @Override
  public void exportReturn(String fileName, ReturnOrderQuery filter) {
    ExcelHeaderCollection<ReturnOrderExportBO> headers = new ExcelHeaderCollection<>();
    headers.add("退货单编号", ReturnOrderExportBO::getReturnOrderCode);
    headers.add("交易号", ReturnOrderExportBO::getTradeId);
    headers.add("订单编号", ReturnOrderExportBO::getSalesOrderCode);
    headers.add("状态", x -> x.getStatus().caption());
    headers.add("店铺", ReturnOrderExportBO::getStoreName);
    headers.add("退换标识", x -> Assert.isTrue(x.getExchange()) ? "换货" : "退货");
    headers.add("箱码", ReturnOrderExportBO::getBoxNo);
    headers.add("规格编码（SKU）", ReturnOrderExportBO::getSkuCode);
    headers.add("规格名称", ReturnOrderExportBO::getSkuName);
    headers.add("申请数量", x -> FormatUtil.formatInteger(x.getApplyQuantity()));
    headers.add("退回数量", x -> FormatUtil.formatInteger(x.getQuantity()));
    headers.add("应退金额", x -> FormatUtil.formatDouble(x.getRefundableAmount()));
    headers.add("备注", ReturnOrderExportBO::getRemark);
    headers.add("生成通知单状态", x -> Assert.isTrue(x.getNoticed()) ? "已通知" : "未通知");
    headers.add("通知单号", ReturnOrderExportBO::getReturnNoticeOrderCode);
    headers.add("退入仓库", ReturnOrderExportBO::getInVirtualWarehouseName);
    headers.add("会员昵称", ReturnOrderExportBO::getMemberName);
    headers.add("退货原因", ReturnOrderExportBO::getReturnReasonType);
    headers.add("退换类型", ReturnOrderExportBO::getReturnType);
    headers.add("退回快递", ReturnOrderExportBO::getExpressName);
    headers.add("快递单号", ReturnOrderExportBO::getExpressNo);
    headers.add("制单人", ReturnOrderExportBO::getCreator);
    headers.add("制单时间", x -> FormatUtil.formatLocalDateTime(x.getCreatedTime()));
    headers.add("拆包人", ReturnOrderExportBO::getUnpacker);
    headers.add("拆包时间", x -> FormatUtil.formatLocalDateTime(x.getUnpackedTime()));
    headers.add("审核人", ReturnOrderExportBO::getAuditor);
    headers.add("审核时间", x -> FormatUtil.formatLocalDateTime(x.getAuditedTime()));
    headers.add("复核人", ReturnOrderExportBO::getAuditor);
    headers.add("复核时间", x -> FormatUtil.formatLocalDateTime(x.getAuditedTime()));
    messageExporter.exportExcel(this::listExportReturn, headers, filter, fileName);
  }

  @Override
  public PageList<ReturnOrderExportBO> listExportReturn(ReturnOrderQuery query, int page,
      int pageSize) {
    return dao.listExportReturn(query, page, pageSize);
  }

  @Override
  public void review(ReturnOrder returnOrder, VersionBO bo) {
    returnOrder.setStatus(ReturnOrderStatus.REVIEWED);
    returnOrder.setReviewer(BizContext.getNickname());
    returnOrder.setReviewTime(LocalDateTime.now());
    returnOrder.setVersion(bo.getVersion());
    ReturnOrder ro = dao.getOrderAndDetailByKey(returnOrder.getReturnOrderId());

    if (Assert.isEmpty(ro.getDetails())) {
      throw new OmsException("无退入商品不能复核!");
    }
    //销售单
    SalesOrder salesOrder;
    if (ro.getOutDetails().size() > 0 && ro.getOutVirtualWarehouseId() != null) {
      salesOrder = convertSalesOrder(ro);
    } else {
      salesOrder = null;
    }
    //退款单
    RefundOrder refundOrder;
    if (ro.getRefundableAmount() > 0) {
      refundOrder = convertRefundOrder(ro);
    } else {
      refundOrder = null;
    }

    try {
      getTransactionTemplate().execute(() -> {
        if (!Assert.isNull(salesOrder)) {
          //删掉之前的库存占用
          stockOccupancyService
              .deleteOccupancy(ro.getReturnOrderId(), StockOccupancyType.RETURN_ORDER);
          //保存 (里面有明细的占用)
          salesOrderService.insertByOther(salesOrder);
        }
        if (!Assert.isNull(refundOrder)) {
          refundOrderService.create(refundOrder);
        }
        update(returnOrder);
      });
      BIZ_LOGGER.log(returnOrder.getReturnOrderId(), BizLogger.REVIEW);
      mqProducer.send(new ReturnOrderReviewMessage(returnOrder.getReturnOrderId()));

      if (!Assert.isNull(salesOrder)) {
        getMqProducer().send(
            new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
                EventType.CREATED)
        );
        if (salesOrder.getDeliveryStatus() != DeliveryStatus.ALL) {
          for (SalesOrderDetail detail : salesOrder.getDetails()) {
            if (detail.getSkuId() != null && detail.getStatus() == SalesOrderDetailStatus.WAITING) {
              getMqProducer().send(
                  new StockChangedMessage(salesOrder.getSalesOrderCode(), detail.getSkuId(),
                      salesOrder.getStoreId(), BizContext.getNickname(), "新建订单"));
            }
          }
        }
        //订单日志
        salesOrderService.getBizLogger().log(salesOrder.getSalesOrderId(), BizLogger.ADD,
            salesOrder.getSub().getCreateType().caption());
      }
    } catch (Exception e) {
      LOGGER.error("退换货单复核失败，退换货单：{}", JsonUtil.toJson(returnOrder));
      LOGGER.error("退换货单复核失败，堆栈信息：", e);
      throw new OmsException("退换货单复核失败！");
    }
  }

  private SalesOrder convertSalesOrder(ReturnOrder returnOrder) {
    Store store = storeService.getByKey(returnOrder.getStoreId());
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(returnOrder.getOutVirtualWarehouseId());

    Member member = memberService.getByKey(returnOrder.getMemberId());
    Assert.notNull(member, "未找到会员：" + returnOrder.getMemberName());
    SalesOrder salesOrder = new SalesOrder();

    salesOrder.setSalesOrderId(getIdGenerator().next());
    salesOrder.setDistributionTradeId(returnOrder.getDistributionTradeId());
    salesOrder.setCountryId(returnOrder.getCountryId());
    salesOrder.setCountryName(returnOrder.getCountryName());
    salesOrder.setProvinceId(returnOrder.getProvinceId());
    salesOrder.setProvinceName(returnOrder.getProvinceName());
    salesOrder.setCityId(returnOrder.getCityId());
    salesOrder.setCityName(returnOrder.getCityName());
    salesOrder.setDistrictId(returnOrder.getDistrictId());
    salesOrder.setDistrictName(returnOrder.getDistrictName());
    salesOrder.setAddress(returnOrder.getAddress());
    salesOrder.setMobile(returnOrder.getMobile());
    salesOrder.setTelephone(returnOrder.getTelephone());
    salesOrder.setContact(returnOrder.getContact());
    salesOrder.setRemark(returnOrder.getRemark());

    salesOrder.setDeliveryStatus(DeliveryStatus.NONE);
    salesOrder.setDispatchStatus(DispatchStatus.NONE);
    salesOrder.setMallSalesOrderStatus(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS);
    salesOrder.setPayStatus(SalesOrderPayStatus.PAID);
    salesOrder.setRefundStatus(RefundStatus.NONE);
    salesOrder.setStatus(SalesOrderStatus.CREATED);

    salesOrder.setActualAmount(0.00);
    salesOrder.setSellingAmount(0.00);
    salesOrder.setSettlementAmount(0.00);
    salesOrder.setCodAmount(0.00D);
    salesOrder.setDiscountAmount(0.00);
    salesOrder.setDistributionAmount(0.00);

    salesOrder.setOos(false);
    salesOrder.setForceSplit(false);
    salesOrder.setHold(false);
    salesOrder.setManual(false);
    salesOrder.setUrgent(false);

    salesOrder.setQuantity(
        returnOrder.getOutDetails().stream().map(ReturnOrderOutDetail::getQuantity)
            .reduce(0, Integer::sum));

    salesOrder.setAuditor(BizContext.getNickname());
    salesOrder.setAuditedTime(LocalDateTime.now());
    salesOrder.setStoreId(returnOrder.getStoreId());
    salesOrder.setStoreName(returnOrder.getStoreName());
    salesOrder.setSuggestVirtualWarehouseId(returnOrder.getOutVirtualWarehouseId());
    salesOrder.setSuggestVirtualWarehouseName(returnOrder.getOutVirtualWarehouseName());
    salesOrder.setSuggestWarehouseId(virtualWarehouse.getWarehouseId());
    salesOrder.setSuggestWarehouseName(virtualWarehouse.getWarehouseName());
    salesOrder.setTradeId(returnOrder.getTradeId());
    //订单子表
    SalesOrderSub sub = new SalesOrderSub();
    sub.setMallType(store.getMallType());
    sub.setBlacklist(MemberStatus.BLACK_LIST == member.getStatus());
    sub.setMallCreatedTime(LocalDateTime.now());
    sub.setMallPaidTime(LocalDateTime.now());
    sub.setMallModifiedTime(LocalDateTime.now());
    sub.setMemberId(returnOrder.getMemberId());
    sub.setMemberName(returnOrder.getMemberName());
    sub.setCreateType(SalesOrderCreateType.MANUAL);
    sub.setCurrencyCode(
        !Assert.isEmpty(store.getSetting().getDefaultCurrencyCode()) ? store.getSetting()
            .getDefaultCurrencyCode() : "RMB");
    sub.setPresellType(PresellType.NONE);
    sub.setSalesOrderType(SalesOrderType.EXCHANGE);
    sub.setSellerMemo(returnOrder.getRemark());
    sub.setSourceType(SourceType.PC);
    sub.setNewMember(false);
    sub.setPrepay(false);
    sub.setThirdDelivery(false);
    sub.setCod(false);
    sub.setHasInvoice(false);
    sub.setExpressFee(0.00D);
    sub.setFreightRiskFee(0.00D);
    sub.setWeight(0.00);
    salesOrder.setSub(sub);
    //订单明细
    List<SalesOrderDetail> details = returnOrder.getOutDetails().stream().map(item -> {
      SalesOrderDetail detail = new SalesOrderDetail();
      ProductSku sku = productSkuService.getEffectiveById(item.getSkuId());
      detail.setSalesOrderDetailId(getIdGenerator().next());
      //实际销售的
      detail.setActualAmount(item.getActualAmount());
      detail.setActualSellingPrice(new BigDecimal(item.getActualAmount())
          .divide(new BigDecimal(item.getQuantity()), 2, BigDecimal.ROUND_HALF_UP).doubleValue());
      salesOrder.setActualAmount(salesOrder.getActualAmount() + detail.getActualAmount());

      //吊牌
      if (!Assert.isNull(sku.getMarkedPrice())) {
        detail.setSellingPrice(sku.getMarkedPrice());
        detail.setSellingAmount(
            new BigDecimal(sku.getMarkedPrice()).multiply(new BigDecimal(item.getQuantity()))
                .doubleValue());
        salesOrder.setSellingAmount(salesOrder.getSellingAmount() + detail.getSellingAmount());
      } else {
        detail.setSellingAmount(0.00D);
        detail.setSellingPrice(0.00D);
      }
      //成本
      if (!Assert.isNull(sku.getCostPrice())) {
        detail.setCostPrice(sku.getCostPrice());
      } else {
        detail.setCostPrice(0.00D);
      }
      //分销
      if (!Assert.isNull(sku.getDistributionPrice())) {
        detail.setDistributionPrice(sku.getDistributionPrice());
        detail.setDistributionAmount(
            new BigDecimal(sku.getDistributionPrice()).multiply(new BigDecimal(item.getQuantity()))
                .doubleValue());
      } else {
        detail.setDistributionPrice(0.00D);
        detail.setDistributionAmount(0.00D);
      }
      //结算
      detail.setSettlementPrice(detail.getActualSellingPrice());
      detail.setSettlementAmount(detail.getActualAmount());
      salesOrder
          .setSettlementAmount(salesOrder.getSettlementAmount() + detail.getSettlementAmount());
      //折扣
      detail.setDiscountAmount(0.00D);

      detail.setWeight(0.00D);
      detail.setExchangeId(item.getExchangeId());
      detail.setCombination(false);
      detail.setGift(false);
      detail.setLockStock(true);
      detail.setCombinationDetail(sku.isCombination());
      detail.setNeedReturnGoods(false);
      detail.setManualAdd(false);
      detail.setOos(false);
      detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
      detail.setSeparate(false);
      detail.setMallSalesOrderDetailStatus(MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS);
      detail.setProductId(item.getProductId());
      detail.setProductCode(item.getProductCode());
      detail.setProductName(item.getProductName());
      detail.setSkuId(item.getSkuId());
      detail.setSkuName(item.getSkuName());
      detail.setSkuCode(item.getSkuCode());
      detail.setProductType(item.getProductType());
      detail.setQuantity(item.getQuantity());
      detail.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
      detail.setStatus(SalesOrderDetailStatus.WAITING);
      detail.setSuggestVirtualWarehouseId(returnOrder.getOutVirtualWarehouseId());
      detail.setSuggestVirtualWarehouseName(returnOrder.getOutVirtualWarehouseName());
      detail.setSuggestWarehouseId(virtualWarehouse.getWarehouseId());
      detail.setSuggestWarehouseName(virtualWarehouse.getWarehouseName());
      return detail;
    }).collect(Collectors.toList());
    salesOrder.setDetails(details);
    return salesOrder;
  }

  private RefundOrder convertRefundOrder(ReturnOrder ro) {
    RefundOrder refundOrder = DomainUtil
        .createRefundOrder(ro.getTradeId(), ro.getStoreName(), ro.getStoreId(), "线上退款",
            ro.getSalesOrderId(), ro.getSalesOrderCode(), ro.getReturnReasonType(),
            ro.getReturnOrderId(), ro.getReturnOrderCode(), 0.00, RefundType.RETURN_AND_REFUND,
            0.00, false, false,
            null, null, "");
    refundOrder.setMallType(ro.getMallType());
    refundOrder.setMemberId(ro.getMemberId());
    refundOrder.setMemberName(ro.getMemberName());
    refundOrder.setCreator(BizContext.getNickname());
    List<RefundOrderDetail> refundOrderDetails = new ArrayList<RefundOrderDetail>();
    if (ro.getDetails().size() > 0) {
      ro.getDetails().forEach(inDetail -> {
        refundOrderDetails.add(DomainUtil
            .createRefundOrderDetail(inDetail.getReturnOrderId(), inDetail.getRefundableAmount(),
                inDetail.getQuantity(),
                inDetail.getProductId(), inDetail.getProductName(), inDetail.getProductCode(),
                inDetail.getSkuId(), inDetail.getSkuName(), inDetail.getSkuCode()));
      });
    }
    //换出商品 数量 应退 实退价格变- 全放进明细表
    if (ro.getOutDetails().size() > 0 && ro.isExchange()) {
      ro.getOutDetails().forEach(outDetail -> {
        refundOrderDetails.add(DomainUtil
            .createRefundOrderDetail(outDetail.getReturnOrderId(), 0D, outDetail.getQuantity(),
                outDetail.getProductId(), outDetail.getProductName(), outDetail.getProductCode(),
                outDetail.getSkuId(), outDetail.getSkuName(), outDetail.getSkuCode()));
      });
    }
    refundOrder.setDetails(refundOrderDetails);
    return refundOrder;
  }

  /**
   * 生成通知单.
   *
   * @param warehouse 仓库
   * @param bo        生成通知单的参数
   */
  public void createNoticeOrder(Warehouse warehouse, CreateReturnNoticeOrderBO bo,
      List<ReturnOrderDetail> details) {
    if (Assert.isEmpty(details)) {
      throw new OmsException("未找到要通知的明细");
    }
    Map<Long, List<ReturnOrderDetail>> noticeDetails = CollectionUtil
        .listToMapList(details, x -> x.getReturnOrderId());
    List<ReturnOrder> updateList = new ArrayList<>();
    //主单 判断是否全部通知
    for (Map.Entry<Long, List<ReturnOrderDetail>> order : noticeDetails.entrySet()) {
      ReturnOrder returnOrder = returnOrderService.getOrderAndDetail(order.getKey());

      //当前单 要通知的明细
      List<ReturnOrderDetail> returnOrderDetails = noticeDetails.get(order.getKey());
      if (Assert.isEmpty(returnOrderDetails)) {
        continue;
      }
      //当前单 要通知的总数量
      Integer quantity = returnOrderDetails.stream().map(ReturnOrderDetail::getQuantity)
          .reduce((a, b) -> a + b).get();
      //已通知的总数量
      Integer noticeQuantity = 0;
      List<ReturnOrderDetail> noticedDetail = returnOrder.getDetails().stream()
          .filter(x -> Assert.isTrue(x.isNoticed()))
          .collect(Collectors.toList());
      if (!Assert.isEmpty(noticedDetail)) {
        noticeQuantity = noticedDetail.stream().map(x -> x.getQuantity()).reduce((a, b) -> a + b)
            .get();
      }

      //如果已通知数量 + 要通知数量 = 等于退换货单的退入数量  全部通知，否则反之
      if (returnOrder.getQuantity().equals(quantity + noticeQuantity)) {
        returnOrder.setNoticeStatus(WmsNoticeStatus.ALL);
      } else {
        returnOrder.setNoticeStatus(WmsNoticeStatus.PART);
      }
      returnOrder.setVersion(returnOrder.getVersion());
      updateList.add(returnOrder);
    }

    ArrayList<ReturnOrderDetail> updateDetails = new ArrayList<>(details.size());

    ReturnNoticeOrder noticeOrder = new ReturnNoticeOrder();
    noticeOrder.setWarehouseId(warehouse.getWarehouseId());
    noticeOrder.setWarehouseName(warehouse.getWarehouseName());
    if (!Assert.isEmpty(bo.getBoxNos())) {
      String boxNos = bo.getBoxNos().toString();
      noticeOrder.setBoxNo(boxNos.substring(1, boxNos.length() - 1));
    }
    if (Assert.isNull(bo.getOperator())) {
      noticeOrder.setCreator(BizContext.getNickname());
    } else {
      noticeOrder.setCreator(bo.getOperator());
    }
    noticeOrder.setRemark(bo.getRemark());

    ReturnOrder returnOrder = new ReturnOrder();
    List<ReturnNoticeOrderDetail> noticeOrderDetails = new ArrayList<>(details.size());
    for (ReturnOrderDetail detail : details) {
      if (!detail.getReturnOrderId().equals(returnOrder.getReturnOrderId())) {
        returnOrder = returnOrderService.getByKey(detail.getReturnOrderId());
      }
      //通知单
      ReturnNoticeOrderDetail noticeOrderDetail = new ReturnNoticeOrderDetail();
      noticeOrderDetail.setReturnOrderDetailId(detail.getReturnOrderDetailId());
      noticeOrderDetail.setReturnOrderId(detail.getReturnOrderId());
      noticeOrderDetail.setReturnOrderCode(returnOrder.getReturnOrderCode());
      noticeOrderDetail.setNoticeQuantity(detail.getQuantity());
      noticeOrderDetail.setProductId(detail.getProductId());
      noticeOrderDetail.setProductCode(detail.getProductCode());
      noticeOrderDetail.setProductName(detail.getProductName());
      noticeOrderDetail.setSkuId(detail.getSkuId());
      noticeOrderDetail.setSkuCode(detail.getSkuCode());
      noticeOrderDetail.setSkuName(detail.getSkuName());
      noticeOrderDetails.add(noticeOrderDetail);
      //修改退换货单明细状态
      ReturnOrderDetail returnOrderDetail = new ReturnOrderDetail();
      returnOrderDetail.setReturnOrderDetailId(detail.getReturnOrderDetailId());
      returnOrderDetail.setNoticed(true);
      updateDetails.add(returnOrderDetail);
    }
    noticeOrder.setDetails(noticeOrderDetails);
    Integer noticeQuantity = noticeOrderDetails.stream().map(x -> x.getNoticeQuantity())
        .reduce((a, b) -> a + b).get();
    noticeOrder.setNoticeQuantity(noticeQuantity);
    try {
      getTransactionTemplate().execute(() -> {
        returnNoticeOrderService.create(noticeOrder);
        returnOrderDetailService.batchModify(updateDetails);
        super.updateBatch(updateList);
      });
      BIZ_LOGGER.log(noticeOrder.getReturnNoticeOrderId(), "通知单创建");
      mqProducer.send(new ReturnNoticeOrderCreateMessage(noticeOrder.getReturnNoticeOrderId()));
    } catch (Exception e) {
      LOGGER.error("创建通知单失败，通知单：{},{},{}", JsonUtil.toJson(warehouse), JsonUtil.toJson(bo),
          JsonUtil.toJson(details));
      LOGGER.error("创建通知单失败，堆栈信息：", e);
      throw new OmsException("创建通知单失败！");
    }
  }

  @Override
  public ReturnOrder getOrderAndDetail(String tradeId) {
    ReturnOrder orderEg = new ReturnOrder();
    orderEg.setTradeId(tradeId);
    ReturnOrder returnOrder = dao.getByExample(orderEg);
    if (returnOrder != null) {
      ReturnOrderDetail orderDetailEg = new ReturnOrderDetail();
      orderDetailEg.setReturnOrderId(returnOrder.getReturnOrderId());
      List<ReturnOrderDetail> returnOrderDetails = returnOrderDetailService
          .listExample(orderDetailEg);
      ReturnOrderOutDetail outDetailEg = new ReturnOrderOutDetail();
      List<ReturnOrderOutDetail> returnOrderOutDetails = returnOrderOutDetailService
          .listExample(outDetailEg);
      returnOrder.setDetails(returnOrderDetails);
      returnOrder.setOutDetails(returnOrderOutDetails);
    }
    return returnOrder;
  }

  @Override
  public ReturnOrder getOrderAndDetail(Long returnOrderId) {
    ReturnOrder returnOrder = returnOrderService.getByKey(returnOrderId);
    if (!Assert.isNull(returnOrder)) {
      ReturnOrderDetail detailEg = new ReturnOrderDetail();
      detailEg.setReturnOrderId(returnOrderId);
      List<ReturnOrderDetail> details = returnOrderDetailService.listExample(detailEg);
      returnOrder.setDetails(details);
    }
    return returnOrder;
  }

  @Override
  public ReturnOrder getOrderAndDetailByApplyId(Long salesOrderId, String outSkuCode) {
    List<ReturnOrder> returnOrders = dao.getBySalesOrderIdAndSkuCode(salesOrderId);
    ReturnOrder returnOrder = null;
    for (ReturnOrder order : returnOrders) {
      List<ReturnOrderOutDetail> collect = order.getOutDetails().stream()
          .filter(x -> x.getSkuCode().equals(outSkuCode)).collect(Collectors.toList());
      if (collect != null && collect.size() > 0) {
        returnOrder = order;
      }
    }
    if (returnOrder != null) {
      ReturnOrderOutDetail outDetailEg = new ReturnOrderOutDetail();
      outDetailEg.setReturnOrderId(returnOrder.getReturnOrderId());
      List<ReturnOrderOutDetail> returnOrderOutDetails = returnOrderOutDetailService
          .listExample(outDetailEg);
      returnOrder.setOutDetails(returnOrderOutDetails);
    }
    return returnOrder;
  }

  @Override
  public void updateOrderAndDetail(ReturnOrder returnOrder, VersionBO versionBO) {
    //获得原来的退入明细
    ReturnOrderDetail returnOrderDetailEg = new ReturnOrderDetail();
    returnOrderDetailEg.setReturnOrderId(returnOrder.getReturnOrderId());
    List<ReturnOrderDetail> oldDetails = returnOrderDetailService.listExample(returnOrderDetailEg);
    //现在的退入明细
    List<ReturnOrderDetail> inDetails = returnOrder.getDetails();

    //更新 更新拆包人 和 版本
    returnOrder.setVersion(versionBO.getVersion());
    returnOrder.setUnpacker(BizContext.getNickname());
    returnOrder.setUnpackedTime(LocalDateTime.now());
    try {
      getTransactionTemplate().execute(() -> {
        //将原退入的删除
        if (!Assert.isEmpty(oldDetails)) {
          oldDetails.forEach(x -> returnOrderDetailService.remove(x));
        }
        //插入现在 退入的明细
        returnOrderDetailService.batchCreate(inDetails);
        //更新主表
        returnOrderService.modify(returnOrder);
      });
      BIZ_LOGGER.log(returnOrder.getReturnOrderId(), BizLogger.UPDATE);
    } catch (Exception e) {
      LOGGER.error("更新退换货单失败，退换货单：{}", JsonUtil.toJson(returnOrder));
      LOGGER.error("更新退换货单失败，堆栈信息：", e);
      throw new OmsException("更新退换货单失败！");
    }
  }

  private void insertOrderAndDetail(ReturnOrder returnOrder) {
    List<ReturnOrderDetail> inDetails = returnOrder.getDetails();
    List<ReturnOrderOutDetail> outDetails = returnOrder.getOutDetails();

    //新建的 拆包人和创建人是一样的
    if (Assert.isNull(returnOrder.getCreator())) {
      returnOrder.setCreator(BizContext.getNickname());
      returnOrder.setUnpacker(BizContext.getNickname());
    }
    returnOrder.setCreatedTime(LocalDateTime.now());
    returnOrder.setUnpackedTime(LocalDateTime.now());
    try {
      getTransactionTemplate().execute(() -> {
        if (inDetails != null) {
          returnOrderDetailService.batchCreate(inDetails);
        }
        if (outDetails != null) {
          returnOrderOutDetailService.batchCreate(outDetails);
        }
        insert(returnOrder);
      });
    } catch (Exception e) {
      LOGGER.error("扫描保存退换货单失败，通知单：{}", JsonUtil.toJson(returnOrder));
      LOGGER.error("扫描保存退换货单失败，堆栈信息：", e);
      throw new OmsException("扫描保存退换货单失败！");
    }
  }

  @Override
  public void unpack(ReturnOrder returnOrder) {
    //1、明细中有不同订单的，拆分为多个退换货单

    //1、
    //查找原单，如果有未审核的就替换
    //删掉原有退入明细，新增拆包的明细
  }

  @Override
  public void addReturnOrder(ReturnOrderSaveBO returnOrderSaveBO) {
    //准备数据
    List<ReturnOrderSavePrepareBO> returnOrderBoList = prepareData(returnOrderSaveBO);
    //创建退换货单
    List<ReturnOrderSavePrepareBO> returnOrderBo = createReturnOrder(returnOrderBoList);
    List<StockChangedMessage> stockChangedMessageList = new ArrayList<>();
    List<RefundAuditMessage> auditMessageList = new ArrayList<>();
    //执行保存
    try {
      getTransactionTemplate().execute(() -> {
        //签收快递
        if (!Assert.isEmpty(returnOrderSaveBO.getExpressNo())) {
          returnSignService.confirmUnpack(returnOrderSaveBO.getExpressNo());
        }
        for (ReturnOrderSavePrepareBO returnOrderSavePrepareBO : returnOrderBo) {
          ReturnOrder returnOrder = returnOrderSavePrepareBO.getReturnOrder();
          getAuditRefundMessage(auditMessageList, returnOrderSavePrepareBO);
          //修改
          if (!Assert.isNull(returnOrderSavePrepareBO.getOldReturnOrder())) {
            updateOldReturnOrder(returnOrderSavePrepareBO.getOldReturnOrder(), returnOrder);
            continue;
          }
          //保存主单和明细
          returnOrder.setCreator(returnOrderSaveBO.getCreator());
          insertOrderAndDetail(returnOrder);
          //如果有换出，修改库存占用
          List<ReturnOrderOutDetail> outDetails = returnOrder.getOutDetails();
          if (!Assert.isEmpty(outDetails)) {
            VirtualWarehouse virtualWarehouse = virtualWarehouseService
                .getByKey(returnOrder.getOutVirtualWarehouseId());
            outDetails.forEach(out -> {
              ExchangeApplyOrder exchangeApplyOrder = returnOrderSavePrepareBO.getExchangeApplys()
                  .get(out.getSkuCode());
              stockOccupancyService.deleteOccupancy(exchangeApplyOrder.getExchangeApplyOrderId(),
                  StockOccupancyType.RETURN_ORDER);
              StockOccupancy stockOccupancy = new StockOccupancy();
              stockOccupancy.setVirtualWarehouseId(virtualWarehouse.getVirtualWarehouseId());
              stockOccupancy.setVirtualWarehouseName(virtualWarehouse.getVirtualWarehouseName());
              stockOccupancy.setWarehouseId(virtualWarehouse.getWarehouseId());
              stockOccupancy.setWarehouseName(virtualWarehouse.getWarehouseName());
              stockOccupancy.setSkuId(out.getSkuId());
              stockOccupancy.setSkuCode(out.getSkuCode());
              stockOccupancy.setQuantity(out.getQuantity());
              stockOccupancy.setStockOccupancyType(StockOccupancyType.RETURN_ORDER);
              stockOccupancy.setMainId(returnOrder.getReturnOrderId());
              stockOccupancy.setDetailId(out.getReturnOrderOutDetailId());
              stockOccupancy.setStatus(StockOccupancyStatus.LOCK);
              stockOccupancyService.create(stockOccupancy);
              stockChangedMessageList
                  .add(new StockChangedMessage(exchangeApplyOrder.getExchangeApplyOrderCode(),
                      exchangeApplyOrder.getOutSkuId(),
                      BizContext.getNickname(), "新建退换货单"));
            });
          }
          BIZ_LOGGER.log(returnOrder.getReturnOrderId(), "扫描新增");
        }
      });
      mqProducer.send(stockChangedMessageList);
      mqProducer.send(auditMessageList);
    } catch (Exception e) {
      LOGGER.error("退换货单扫描新增失败，退换货单：{}", JsonUtil.toJson(returnOrderSaveBO));
      LOGGER.error("退换货单扫描新增失败，堆栈信息：", e);
      throw new OmsException("退换货单扫描新增失败！");
    }
  }

  private void getAuditRefundMessage(List<RefundAuditMessage> list,
      ReturnOrderSavePrepareBO orderSavePrepareBO) {
    Store store = orderSavePrepareBO.getStore();
    if (Assert.isNull(store) || !Assert.isTrue(store.getSetting().isTaobaoAutoAuditRefund())) {
      return;
    }
    ReturnOrder returnOrder = orderSavePrepareBO.getReturnOrder();
    Map<String, RefundApplyOrder> refundApplys = orderSavePrepareBO.getRefundApplys();
    if (!Assert.isEmpty(returnOrder.getDetails()) && !Assert.isEmpty(refundApplys)) {
      for (ReturnOrderDetail detail : returnOrder.getDetails()) {
        RefundApplyOrder refundApplyOrder = refundApplys.get(detail.getSkuCode());
        if (!Assert.isNull(refundApplyOrder)) {
          if (!detail.getSkuCode().equals(refundApplyOrder.getInSkuCode())) {
            continue;
          }
          if (!detail.getQuantity().equals(refundApplyOrder.getApplyQuantity())) {
            continue;
          }
          if (!detail.getRefundableAmount().equals(refundApplyOrder.getApplyAmount())) {
            continue;
          }
          list.add(new RefundAuditMessage(refundApplyOrder.getRefundApplyOrderId()));
        }
      }
    }
  }

  //异常sku匹配
  private void matchAbnormal(ReturnOrderSaveBO returnOrderSaveBO) {

    List<InDetail> scanInDetail = returnOrderSaveBO.getInDetails();
    //取出无 salesOrderId的退入
    List<InDetail> noIdForInDetail = scanInDetail.stream()
        .filter(x -> Assert.isNull(x.getSalesOrderId())).collect(Collectors.toList());
    if (!Assert.isEmpty(noIdForInDetail)) {

      //取出有申请 没有被扫描的
      List<RefundApplyOrder> refundNoScan = new ArrayList<>();
      List<ExchangeApplyOrder> exchangeNoScan = new ArrayList<>();

      //看申请的的sku 如果不在退入里面, 或者申请的数量大于扫描数量 , 直接将这个退入的无头件关联这个申请
      //遍历退货的 , 匹配退货
      List<RefundApplyOrder> refundApplyOrders = returnOrderSaveBO.getRefundApplyOrders();
      if (!Assert.isEmpty(refundApplyOrders)) {
        for (RefundApplyOrder refund : refundApplyOrders) {
          // 判断申请退的在不在扫描里面
          List<InDetail> collect = scanInDetail.stream().filter(x ->
              // 申请退的sku 在 扫描里面 , 数量还一直 , 说明改申请 , 不能在被处理了
              (x.getSkuCode().equals(refund.getInSkuCode())) && (x.getQuantity()
                  .equals(refund.getApplyQuantity()))

          ).collect(Collectors.toList());

          if (Assert.isEmpty(collect)) {
            //说明有这个申请 , 但是没有被扫
            refundNoScan.add(refund);
          }
        }
      }
      //遍历换货的
      List<ExchangeApplyOrder> exchangeApplyOrders = returnOrderSaveBO.getExchangeApplyOrders();
      if (!Assert.isEmpty(exchangeApplyOrders)) {
        for (ExchangeApplyOrder exchange : exchangeApplyOrders) {
          List<InDetail> collect = scanInDetail.stream().filter(x ->

              (x.getSkuCode().equals(exchange.getInSkuCode())) && (x.getQuantity()
                  .equals(exchange.getQuantity()))

          ).collect(Collectors.toList());

          if (Assert.isEmpty(collect)) {
            //有换货申请 , 没有退入
            exchangeNoScan.add(exchange);

          }
        }
      }

      noIdForInDetail.stream().sorted(Comparator.comparing(InDetail::getQuantity).reversed());
      refundNoScan.stream()
          .sorted(Comparator.comparing(RefundApplyOrder::getApplyQuantity).reversed());
      //再把 找出来的申请 , 依次附到 退入里面去
      if (!Assert.isEmpty(refundNoScan)) {
        for (int i = 0; i < noIdForInDetail.size(); i++) {
          if (!Assert.isEmpty(refundNoScan)) {
            //拿到退入的
            InDetail inDetail = noIdForInDetail.get(i);

            //找出此退入 所满足---> 扫描数量小于或等于申请数量的退货申请 的退货申请
            List<RefundApplyOrder> satisfyRefund = refundNoScan.stream()
                .filter(x -> {
                  if (Assert.isNull(x.getApplyQuantity())) {
                    return false;
                  } else {
                    return x.getApplyQuantity() >= inDetail.getQuantity();
                  }
                })
                .collect(Collectors.toList());

            if (!Assert.isEmpty(satisfyRefund)) {
              //拿到 此退入的无头件 的 退货申请
              RefundApplyOrder refundApplyOrder = satisfyRefund.get(0);
              //这个处理了的就不算 申请扫描了
              refundNoScan.remove(refundApplyOrder);

              //根据申请找到 原单明细
              List<SalesOrderDetail> sourceDetails = returnOrderSaveBO.getSourceDetail()
                  .stream()
                  .filter(x -> {
                    if (Assert.isNull(refundApplyOrder.getInSkuCode())) {
                      return false;
                    } else {
                      return refundApplyOrder.getInSkuCode().equals(x.getSkuCode());
                    }
                  }).collect(Collectors.toList());

              if (!Assert.isEmpty(sourceDetails)) {
                SalesOrderDetail sourceDetail = sourceDetails.get(0);
                inDetail.setApplyQuantity(refundApplyOrder.getApplyQuantity());
                inDetail.setSalesOrderId(sourceDetail.getSalesOrderId());
                //应退金额 , 取关联上的 申请的金额
                inDetail.setRefundableAmount(refundApplyOrder.getApplyAmount());
                inDetail.setApplyQuantity(refundApplyOrder.getApplyQuantity());

                //标记退入的sku异常
                inDetail.setSkuAbnormal(true);

                //使用真实的sku 替换 申请的(发错货的)sku , 为后面skuCode 找到申请
                refundApplyOrder.setInSkuCode(inDetail.getSkuCode());
              }
            }
          }
        }
      }

      exchangeNoScan.stream()
          .sorted(Comparator.comparing(ExchangeApplyOrder::getQuantity).reversed());
      //过滤剩下退入的 , 还没有主单id 的
      noIdForInDetail.stream().filter(x -> Assert.isNull(x.getSalesOrderId()))
          .collect(Collectors.toList());
      //处理换货的
      if (!Assert.isEmpty(exchangeNoScan)) {
        for (int i = 0; i < noIdForInDetail.size(); i++) {
          if (!Assert.isEmpty(exchangeNoScan)) {
            //拿到退入的
            InDetail inDetail = noIdForInDetail.get(i);

            //找出此退入 所满足---> 扫描数量小于或等于换货数量  的换货申请
            List<ExchangeApplyOrder> satisfyExchange = exchangeNoScan.stream()
                .filter(x -> x.getQuantity() >= inDetail.getQuantity())
                .collect(Collectors.toList());

            if (!Assert.isEmpty(satisfyExchange)) {
              //拿出换货数最多的换货申请 , 因为排序了
              ExchangeApplyOrder exchangeApplyOrder = satisfyExchange.get(0);
              //删掉
              satisfyExchange.remove(exchangeApplyOrder);

              //处理退入的--------------
              //根据换货申请找到 退入的订单明细
              List<SalesOrderDetail> sourceDetails = returnOrderSaveBO.getSourceDetail()
                  .stream()
                  .filter(x -> {
                    if (Assert.isNull(exchangeApplyOrder.getInSkuCode())) {
                      return false;
                    } else {
                      return exchangeApplyOrder.getInSkuCode().equals(x.getSkuCode());
                    }
                  }).collect(Collectors.toList());

              if (!Assert.isEmpty(sourceDetails)) {
                SalesOrderDetail sourceDetail = sourceDetails.get(0);
                inDetail.setApplyQuantity(exchangeApplyOrder.getQuantity());
                inDetail.setSalesOrderId(sourceDetail.getSalesOrderId());

                //计算原单的明细的单价
                double refundAbleAmount = new BigDecimal(
                    (sourceDetail.getActualAmount() / sourceDetail.getQuantity()) * inDetail
                        .getQuantity())
                    .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                //换货的应退 取的是实际销售的金额
                inDetail.setRefundableAmount(refundAbleAmount);
                inDetail.setApplyQuantity(exchangeApplyOrder.getQuantity());

                //标记退入的sku异常
                inDetail.setSkuAbnormal(true);

                //使用真实的skuCode 替换 申请的(发错货的)skuCode , 为后面skuCode 找到申请
                exchangeApplyOrder.setInSkuCode(inDetail.getSkuCode());
              }
              //处理换出的-----------------
              OutDetail outDetail = new OutDetail();
              outDetail.setProductCode(exchangeApplyOrder.getOutProductCode());
              outDetail.setProductId(exchangeApplyOrder.getOutProductId());
              outDetail.setProductName(exchangeApplyOrder.getOutProductName());
              outDetail.setQuantity(exchangeApplyOrder.getQuantity());
              outDetail.setSkuCode(exchangeApplyOrder.getOutSkuCode());
              outDetail.setSkuId(exchangeApplyOrder.getOutSkuId());
              outDetail.setSkuName(exchangeApplyOrder.getOutSkuName());
              outDetail.setSourceOrderId(exchangeApplyOrder.getSalesOrderId());
              outDetail.setSourceSkuCode(exchangeApplyOrder.getInSkuCode());
              //关联了 换货申请, 创建除换出商品
              returnOrderSaveBO.getOutDetails().add(outDetail);
            }
          }
        }
      }
    }
  }

  /**
   * 封装 returnOrder 的数据准备.
   *
   * @param returnOrderSaveBO 要封装的数据
   */
  private List<ReturnOrderSavePrepareBO> prepareData(ReturnOrderSaveBO returnOrderSaveBO) {
    ArrayList<ReturnOrderSavePrepareBO> listData = new ArrayList<>();

    //异常匹配
    matchAbnormal(returnOrderSaveBO);

    List<Long> salesOrderIds = returnOrderSaveBO.getInDetails().stream()
        .map(x -> x.getSalesOrderId()).distinct().collect(Collectors.toList());

    Store store = null;
    if (returnOrderSaveBO.getStoreId() != null) {
      store = storeService.getByKey(returnOrderSaveBO.getStoreId());
    }
    for (Long salesOrderId : salesOrderIds) {
      //准备的数据
      ReturnOrderSavePrepareBO prepareBO = new ReturnOrderSavePrepareBO();

      prepareBO.setStore(store);
      //备注
      prepareBO.setRemark(returnOrderSaveBO.getRemark());
      prepareBO.setReturnType(returnOrderSaveBO.getReturnType());
      prepareBO.setInVirtualWarehouseId(returnOrderSaveBO.getInVirtualWarehouseId());
      prepareBO.setInVirtualWarehouseName(returnOrderSaveBO.getInVirtualWarehouseName());
      prepareBO.setExpressNo(returnOrderSaveBO.getExpressNo());
      prepareBO.setBoxNo(returnOrderSaveBO.getBoxNo());
      prepareBO.setNickName(returnOrderSaveBO.getCreator());
      prepareBO.setRefund(false);
      prepareBO.setExchange(false);

      //有订单
      if (!Assert.isNull(salesOrderId)) {
        // 此单是否为更新
        ReturnOrder returnOrderEg = new ReturnOrder();
        returnOrderEg.setSalesOrderId(salesOrderId);
        returnOrderEg.setStatus(ReturnOrderStatus.CREATED);
        ReturnOrder old = dao.getByExample(returnOrderEg);
        if (!Assert.isNull(old)) {
          prepareBO.setOldReturnOrder(old);
        } else {
          prepareBO.setOldReturnOrder(null);
        }

        //订单的信息
        SalesOrder salesOrder = salesOrderService.getFullInfo(salesOrderId);
        if (!Assert.isNull(salesOrder)) {
          prepareBO.setSalesOrder(salesOrder);
        }
        //退入的
        List<InDetail> inDetails = returnOrderSaveBO.getInDetails().stream()
            .filter(x -> salesOrder.getSalesOrderId().equals(x.getSalesOrderId()))
            .collect(Collectors.toList());
        prepareBO.setInDetails(inDetails);
        //退货申请
        if (!Assert.isEmpty(returnOrderSaveBO.getRefundApplyOrders())) {
          //找到当前订单的售后申请
          List<RefundApplyOrder> refundApplyOrders = returnOrderSaveBO.getRefundApplyOrders()
              .stream()
              .filter(x ->
                  (x.getTradeId().equals(salesOrder.getTradeId()) && x.getStoreId()
                      .equals(salesOrder.getStoreId()))).collect(Collectors.toList());

          //封装每个退入对应的退款申请
          Map<String, RefundApplyOrder> refundApplys = new HashMap<>();
          if (!Assert.isEmpty(refundApplyOrders) && !Assert.isEmpty(inDetails)) {
            inDetails.forEach(inDetail -> {
              refundApplyOrders.forEach(refundApply -> {
                if (inDetail.getSkuCode().equals(refundApply.getInSkuCode())) {
                  refundApplys.put(inDetail.getSkuCode(), refundApply);
                }
              });
            });
          }
          if (!Assert.isEmpty(refundApplyOrders)) {
            prepareBO.setRefund(true);
            prepareBO.setRefundApplys(refundApplys);
          }
        }

        //换货单
        if (!Assert.isEmpty(returnOrderSaveBO.getExchangeApplyOrders())) {
          //找到这个订单的换货申请
          List<ExchangeApplyOrder> exchange = returnOrderSaveBO.getExchangeApplyOrders().stream()
              .filter(x -> x.getSalesOrderId().equals(salesOrderId)).collect(Collectors.toList());

          if (!Assert.isEmpty(exchange)) {

            Map<String, ExchangeApplyOrder> exchangeApplys = new HashMap<>();

            //找到退入所对应的换货申请
            if (!Assert.isEmpty(inDetails)) {
              inDetails.forEach(inDetail -> {
                exchange.forEach(e -> {
                  if (inDetail.getSkuCode().equals(e.getInSkuCode())) {
                    exchangeApplys.put(inDetail.getSkuCode(), e);
                  }
                });
              });
            }

            //换出的
            List<OutDetail> outDetails = null;
            if (!Assert.isEmpty(returnOrderSaveBO.getOutDetails())) {
              outDetails = returnOrderSaveBO.getOutDetails().stream()
                  .filter(x -> x.getSourceOrderId().equals(salesOrderId))
                  .collect(Collectors.toList());
              prepareBO.setOutDetails(outDetails);
            }

            //封装换出-->对应的换货申请
            if (!Assert.isEmpty(outDetails)) {
              outDetails.forEach(outDetail -> {
                exchange.forEach(exchangeApply -> {
                  if (outDetail.getSkuCode().equals(exchangeApply.getOutSkuCode())) {
                    exchangeApplys.put(outDetail.getSkuCode(), exchangeApply);
                  }
                });
              });
            }

            prepareBO.setExchange(true);
            prepareBO.setExchangeApplys(exchangeApplys);

          }
        }
      } else {
        List<InDetail> inDetails = returnOrderSaveBO.getInDetails().stream()
            .filter(x -> x.getSalesOrderId() == null).collect(Collectors.toList());
        prepareBO.setInDetails(inDetails);
      }
      listData.add(prepareBO);
    }
    return listData;
  }

  private List<ReturnOrderSavePrepareBO> createReturnOrder(
      List<ReturnOrderSavePrepareBO> returnOrderBoList) {

    for (ReturnOrderSavePrepareBO prepareBO : returnOrderBoList) {
      ReturnOrder returnOrder = new ReturnOrder();
      returnOrder.setRemark(prepareBO.getRemark());
      returnOrder.setBoxNo(prepareBO.getBoxNo());
      returnOrder.setReturnOrderId(getIdGenerator().next());
      //封装订单的信息
      returnOrder.setAbnormal(true);
      if (!Assert.isNull(prepareBO.getSalesOrder())) {
        //正常单 自动审核
        returnOrder.setStatus(ReturnOrderStatus.AUDITED);
        returnOrder.setAuditedTime(LocalDateTime.now());
        if (Assert.isNull(prepareBO.getNickName())) {
          returnOrder.setAuditor(BizContext.getNickname());
        } else {
          returnOrder.setAuditor(prepareBO.getNickName());
        }
        //将退换货单  是否为异常标记为：否
        returnOrder.setAbnormal(false);
        SalesOrder salesOrder = prepareBO.getSalesOrder();
        returnOrder.setTradeId(salesOrder.getTradeId());
        returnOrder.setSalesOrderId(salesOrder.getSalesOrderId());
        returnOrder.setSalesOrderCode(salesOrder.getSalesOrderCode());
        returnOrder.setMemberId(salesOrder.getSub().getMemberId());
        returnOrder.setMemberName(salesOrder.getSub().getMemberName());
      }
      //店铺信息
      if (!Assert.isNull(prepareBO.getStore())) {
        Store store = prepareBO.getStore();
        returnOrder.setStoreId(store.getStoreId());
        returnOrder.setStoreName(store.getStoreName());
        returnOrder.setOutVirtualWarehouseId(store.getSetting().getDefaultWarehouse());
        returnOrder.setOutVirtualWarehouseName(store.getSetting().getDefaultWarehouseName());
      }
      returnOrder.setReturnType(prepareBO.getReturnType());
      returnOrder.setInVirtualWarehouseId(prepareBO.getInVirtualWarehouseId());
      returnOrder.setInVirtualWarehouseName(prepareBO.getInVirtualWarehouseName());
      if (Assert.isNull(prepareBO.getNickName())) {
        returnOrder.setUnpacker(BizContext.getNickname());
      } else {
        returnOrder.setUnpacker(prepareBO.getNickName());
      }
      returnOrder.setUnpackedTime(LocalDateTime.now());
      prepareBO.setReturnOrder(returnOrder);
      //退入的
      inDetails(prepareBO);
      //修改的
      if (!Assert.isNull(prepareBO.getOldReturnOrder())) {
        initDefaultValue(returnOrder);
        //如果是修改的，那就不修改 是退还是换，原本是什么就是什么
        prepareBO.setExchange(null);
        continue;
      }
      //换货
      if (prepareBO.getExchange()) {
        outDetails(prepareBO);
      }
      initDefaultValue(returnOrder);
    }
    return returnOrderBoList;
  }

  private void inDetails(ReturnOrderSavePrepareBO returnOrderBo) {
    List<ReturnOrderDetail> returnOrderDetails = new ArrayList<>();
    ReturnOrder returnOrder = returnOrderBo.getReturnOrder();

    List<InDetail> inDetails = returnOrderBo.getInDetails();
    for (InDetail in : inDetails) {
      ReturnOrderDetail returnOrderDetail = new ReturnOrderDetail();
      RefundApplyOrder refundApplyOrder = null;
      if (!Assert.isEmpty(returnOrderBo.getRefundApplys())) {
        refundApplyOrder = returnOrderBo.getRefundApplys().get(in.getSkuCode());
      }
      ExchangeApplyOrder exchangeApplyOrder = null;
      if (!Assert.isEmpty(returnOrderBo.getExchangeApplys())) {
        exchangeApplyOrder = returnOrderBo.getExchangeApplys().get(in.getSkuCode());
      }

      //找出退入 sku 的product
      Product product = productService.getProductByCode(in.getProductCode());
      /**这里面只有三种  退款 换货 无头件*/
      //换货单
      if (!Assert.isNull(exchangeApplyOrder)) {
        returnOrder.setReturnReasonType(exchangeApplyOrder.getReason());
        returnOrderDetail.setReturnReasonType(exchangeApplyOrder.getReason());
        //此明细申请换的数量
        returnOrderDetail.setApplyQuantity(exchangeApplyOrder.getQuantity());
        returnOrderDetail.setApplyId(exchangeApplyOrder.getExchangeApplyOrderId());
        returnOrder.setRefundType(RefundType.RETURN_AND_REFUND);
        returnOrder.setExpressName(exchangeApplyOrder.getInExpressName());
        returnOrder.setExpressNo(exchangeApplyOrder.getInExpressNo());
        returnOrder.setOuterCode(exchangeApplyOrder.getMallExchangeId());
        //退换货单总的申请数量
        if (Assert.isNull(returnOrder.getApplyQuantity())) {
          returnOrder.setApplyQuantity(exchangeApplyOrder.getQuantity());
        } else {
          returnOrder
              .setApplyQuantity(returnOrder.getApplyQuantity() + exchangeApplyOrder.getQuantity());
        }
        //退货单
      } else if (!Assert.isNull(refundApplyOrder)) {
        returnOrder.setReturnReasonType(refundApplyOrder.getReason());
        returnOrderDetail.setReturnReasonType(refundApplyOrder.getReason());
        returnOrderDetail.setApplyId(refundApplyOrder.getRefundApplyOrderId());
        //此明细申请退的数量
        returnOrderDetail.setApplyQuantity(refundApplyOrder.getApplyQuantity());
        //申请退的金额
        returnOrderDetail.setRefundableAmount(
            refundApplyOrder.getApplyAmount() != null ? refundApplyOrder.getApplyAmount() : 0.0D);
        returnOrder.setExpressName(refundApplyOrder.getExpressName());
        returnOrder.setExpressNo(refundApplyOrder.getExpressNo());
        returnOrder.setOuterCode(refundApplyOrder.getMallRefundId());
        if (refundApplyOrder.getRefundApplyOrderType() == RefundApplyOrderType.REFUND) {
          returnOrder.setRefundType(RefundType.ONLY_REFUND);
        } else {
          returnOrder.setRefundType(RefundType.RETURN_AND_REFUND);
        }
        //总的申请数量
        if (Assert.isNull(returnOrder.getApplyQuantity())) {
          returnOrder.setApplyQuantity(refundApplyOrder.getApplyQuantity());
        } else {
          returnOrder.setApplyQuantity(
              returnOrder.getApplyQuantity() + refundApplyOrder.getApplyQuantity());
        }
        //无头件
      } else {
        returnOrder.setReturnReasonType("其他");
        returnOrderDetail.setReturnReasonType("其他");
        returnOrder.setRefundType(RefundType.RETURN_AND_REFUND);
        returnOrder.setExpressNo(returnOrderBo.getExpressNo());
      }
      //sku是否异常
      if (in.getSkuAbnormal()) {
        returnOrder.setSkuAbnormal(true);
      }
      returnOrderDetail.setInQuantity(0);
      returnOrderDetail.setReturnOrderId(returnOrder.getReturnOrderId());
      if (!Assert.isNull(returnOrderBo.getSalesOrder())) {
        SalesOrder salesOrder = returnOrderBo.getSalesOrder();
        returnOrderDetail.setSalesOrderId(salesOrder.getSalesOrderId());
        returnOrderDetail.setSalesOrderCode(salesOrder.getSalesOrderCode());
        returnOrderDetail.setTradeId(salesOrder.getTradeId());
        List<SalesOrderDetail> sourceDetails = salesOrder.getDetails().stream()
            .filter(x -> in.getSkuCode().equals(x.getSkuCode())).collect(Collectors.toList());
        if (sourceDetails != null && sourceDetails.size() > 0) {
          returnOrderDetail.setSalesOrderDetailId(sourceDetails.get(0).getSalesOrderDetailId());
        }
      }
      returnOrderDetail.setProductCode(in.getProductCode());
      returnOrderDetail.setProductId(in.getProductId());
      returnOrderDetail.setProductName(in.getProductName());
      returnOrderDetail.setQuantity(in.getQuantity());
      //如果 没有申请的应退金额 , 那就取订单明细的实付金额
      if (Assert.isNull(returnOrderDetail.getRefundableAmount())) {
        returnOrderDetail.setRefundableAmount(
            in.getRefundableAmount() != null ? in.getRefundableAmount() : 0.0D);
      }
      returnOrderDetail.setReturnOrderDetailId(getIdGenerator().next());
      returnOrderDetail.setSkuCode(in.getSkuCode());
      returnOrderDetail.setSkuId(in.getSkuId());
      returnOrderDetail.setSkuName(in.getSkuName());
      returnOrderDetail.setProductType(in.getProductType());
      returnOrderDetail.setBrandName(product.getBrandName());
      returnOrderDetail.setBrandCode(product.getBrandCode());
      returnOrderDetail.setNoticed(false);
      returnOrderDetails.add(returnOrderDetail);
    }
    returnOrder.setDetails(returnOrderDetails);
  }

  private void outDetails(ReturnOrderSavePrepareBO returnOrderBo) {
    ReturnOrder returnOrder = returnOrderBo.getReturnOrder();
    List<ReturnOrderOutDetail> returnOutDetails = new ArrayList<>();
    List<OutDetail> outDetails = returnOrderBo.getOutDetails();
    if (Assert.isEmpty(outDetails)) {
      return;
    }
    returnOrder.setExchange(true);
    for (OutDetail outDetail : outDetails) {
      ExchangeApplyOrder exchange = returnOrderBo.getExchangeApplys().get(outDetail.getSkuCode());
      ReturnOrderOutDetail detail = new ReturnOrderOutDetail();
      Product product = productService.getProductByCode(outDetail.getProductCode());
      detail.setReturnOrderId(returnOrder.getReturnOrderId());
      detail.setProductCode(outDetail.getProductCode());
      detail.setProductId(outDetail.getProductId());
      detail.setProductName(outDetail.getProductName());
      detail.setProductType(product.getProductType());
      detail.setQuantity(outDetail.getQuantity());
      detail.setSkuCode(outDetail.getSkuCode());
      detail.setSkuId(outDetail.getSkuId());
      detail.setSkuName(outDetail.getSkuName());
      detail.setExchangeId(exchange.getMallExchangeId());
      //找到此换货申请 所对应的退入商品
      List<ReturnOrderDetail> in = returnOrder.getDetails().stream()
          .filter(x -> x.getSkuCode().equals(exchange.getInSkuCode())).collect(Collectors.toList());
      //换出金额 取 换货申请所对应的 退入商品的实付金额
      detail.setActualAmount(in.get(0).getRefundableAmount());
      detail.setReturnOrderDetailId(in.get(0).getReturnOrderDetailId());
      //设置换出的地址
      returnOrder.setProvinceId(exchange.getProvinceId());
      returnOrder.setProvinceName(exchange.getProvinceName());
      returnOrder.setCityId(exchange.getCityId());
      returnOrder.setCityName(exchange.getCityName());
      returnOrder.setCountryId(exchange.getCountryId());
      returnOrder.setCountryName(exchange.getCountryName());
      returnOrder.setDistrictId(exchange.getDistrictId());
      returnOrder.setDistrictName(exchange.getDistrictName());
      returnOrder.setAddress(exchange.getAddress());
      returnOrder.setContact(exchange.getContact());
      returnOrder.setMobile(exchange.getMobile());
      returnOutDetails.add(detail);
    }
    returnOrder.setOutDetails(returnOutDetails);
  }

  private void updateOldReturnOrder(ReturnOrder oldReturnOrder, ReturnOrder returnOrder) {
    //将主单的主键设为之前的
    returnOrder.setReturnOrderId(oldReturnOrder.getReturnOrderId());
    //将新退入的关联之前的主单
    returnOrder.getDetails().forEach(x -> {
      //明细表放主表的id
      x.setReturnOrderId(oldReturnOrder.getReturnOrderId());
    });
    if (!Assert.isEmpty(oldReturnOrder.getOutDetails())) {
      //总退入的 减去 换出的实际金额 = 应退的金额
      oldReturnOrder.getOutDetails().forEach(out -> {
        returnOrder.setRefundableAmount(returnOrder.getRefundableAmount() - out.getActualAmount());
      });
    }
    //更新
    VersionBO versionBO = new VersionBO();
    versionBO.setVersion(oldReturnOrder.getVersion());
    updateOrderAndDetail(returnOrder, versionBO);
  }

  @Override
  public List<ReturnOrder> listReturnOrder(CreateReturnNoticeOrderBO bo) {
    ReturnOrderQuery filter = new ReturnOrderQuery();
    List<ReturnOrderStatus> statuses = new ArrayList<>(2);
    //statuses.add(ReturnOrderStatus.AUDITED);
    statuses.add(ReturnOrderStatus.REVIEWED);
    ArrayList<WmsNoticeStatus> noticeStatuses = new ArrayList<>();
    noticeStatuses.add(WmsNoticeStatus.NONE);
    noticeStatuses.add(WmsNoticeStatus.PART);
    filter.setStatuses(statuses);
    filter.setNoticeStatuses(noticeStatuses);
    filter.setUnpacker(bo.getNickname());
    filter.setUnpackedTimeBegin(bo.getBeginTime());
    filter.setUnpackedTimeEnd(bo.getEndTime());
    filter.setInVirtualWarehouseId(bo.getVirtualWarehouseId());
    filter.setBoxNos(bo.getBoxNos());
    if (!Assert.isEmpty(bo.getSkuCodes()) || !Assert.isEmpty(bo.getBrandCodes())) {
      filter.setReturnType(bo.getReturnType());
    }
    List<ReturnOrder> returnOrders = returnOrderService.list(filter);
    if (!Assert.isEmpty(returnOrders)) {
      //要通知的明细
      List<Long> ids = returnOrders.stream().map(x -> x.getReturnOrderId())
          .collect(Collectors.toList());
      ReturnOrderDetailQuery filterQuery = new ReturnOrderDetailQuery();
      filterQuery.setReturnOrderIds(ids);
      filterQuery.setBrandCodes(bo.getBrandCodes());
      filterQuery.setSkuCodes(bo.getSkuCodes());
      filterQuery.setNoticed(false);
      List<ReturnOrderDetail> details = returnOrderDetailService.list(filterQuery);
      if (!Assert.isEmpty(details)) {
        returnOrders.forEach(x -> {
          List<ReturnOrderDetail> returnOrderDetails = new ArrayList<>();
          details.forEach(y -> {
            if (x.getReturnOrderId().equals(y.getReturnOrderId())) {
              returnOrderDetails.add(y);
            }
          });
          x.setDetails(returnOrderDetails);
        });
      }
      returnOrders = returnOrders.stream().filter(order -> !Assert.isEmpty(order.getDetails()))
          .collect(Collectors.toList());
    }
    return returnOrders;
  }


  @Override
  public void createNotice(CreateReturnNoticeOrderBO bo) {
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(bo.getVirtualWarehouseId());
    Warehouse warehouse = warehouseService.getByKey(virtualWarehouse.getWarehouseId());
    int limit = 100;
    if (bo.getQuantity() != null) {
      limit = bo.getQuantity();
    }

    ReturnOrderQuery filter = new ReturnOrderQuery();
    if (Assert.isEmpty(bo.getStatuses())) {
      List<ReturnOrderStatus> statuses = new ArrayList<>(2);
      statuses.add(ReturnOrderStatus.REVIEWED);
      statuses.add(ReturnOrderStatus.AUDITED);
      filter.setStatuses(statuses);
    } else {
      filter.setStatuses(bo.getStatuses());
    }
    ArrayList<WmsNoticeStatus> noticeStatuses = new ArrayList<>();
    noticeStatuses.add(WmsNoticeStatus.NONE);
    noticeStatuses.add(WmsNoticeStatus.PART);
    filter.setNoticeStatuses(noticeStatuses);
    filter.setUnpacker(bo.getNickname());
    filter.setUnpackedTimeBegin(bo.getBeginTime());
    filter.setUnpackedTimeEnd(bo.getEndTime());
    filter.setInVirtualWarehouseId(bo.getVirtualWarehouseId());
    filter.setBoxNos(bo.getBoxNos());
    filter.setSkuCodes(bo.getSkuCodes());
    filter.setBrandCodes(bo.getBrandCodes());
    filter.setDetailNotice(false);
    if (!Assert.isEmpty(bo.getSkuCodes()) || !Assert.isEmpty(bo.getBrandCodes())) {
      filter.setReturnType(bo.getReturnType());
    }
    PageList<ReturnOrderDetail> pageList = returnOrderDetailService
        .detailListPage(filter, 1, limit);
    if (pageList.getTotal() > 0) {
      createNoticeOrder(warehouse, bo, pageList.getData());
      int page = (pageList.getTotal() + limit - 1) / limit;
      if (page > 1) {
        for (int i = 2; i <= page; i++) {
          pageList = returnOrderDetailService.detailListPage(filter, i, limit);
          createNoticeOrder(warehouse, bo, pageList.getData());
        }
      }
    } else {
      throw new OmsException("未找到可以生成通知单的退换货单！");
    }
  }

  @Override
  public RelateSourceOrderBO addSourceOrder(VersionBO<ReturnOrder> returnOrderBO) {
    ReturnOrder returnOrder = returnOrderBO.getDomain();

    Long salesOrderId = returnOrder.getSalesOrderId();
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    //原退换货 主单
    ReturnOrder oldMain = returnOrderService.getByKey(returnOrder.getReturnOrderId());
    //判断退换货主单是否有 原单，并且新添加的原单还和之前的不一样
    if (!Assert.isNull(oldMain.getSalesOrderId()) && !salesOrder.getSalesOrderId()
        .equals(oldMain.getSalesOrderId())) {
      throw new OmsException("无法关联原单，需拆分退换货单明细！");
    }

    Long storeId = returnOrder.getStoreId();
    Store store = null;
    if (storeId != null) {
      store = storeService.getByKey(storeId);
      returnOrder.setOutVirtualWarehouseId(store.getSetting().getDefaultWarehouse());
      returnOrder.setOutVirtualWarehouseName(store.getSetting().getDefaultWarehouseName());
    }

    //解密 联系人 和 手机号
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    String contact = securityBridge
        .decrypt(store, salesOrder.getContact(), DataType.NAME);
    String mobile = securityBridge
        .decrypt(store, salesOrder.getMobile(), DataType.MOBILE);

    //地址
    StringBuffer address = new StringBuffer();
    if (!Assert.isEmpty(salesOrder.getProvinceName())) {
      address.append(salesOrder.getProvinceName() + "/");
    }
    if (!Assert.isEmpty(salesOrder.getCityName())) {
      address.append(salesOrder.getCityName() + "/");
    }
    if (!Assert.isEmpty(salesOrder.getDistrictName())) {
      address.append(salesOrder.getDistrictName() + "/");
    }
    address.append(salesOrder.getAddress());

    salesOrder.setContact(contact);
    salesOrder.setMobile(mobile);
    salesOrder.setAddress(address.toString());

    returnOrder.setVersion(returnOrderBO.getVersion());
    //更新 主单
    //修改 补充退入的信息
    returnOrder.setMemberId(salesOrder.getSub().getMemberId());
    returnOrder.setMemberName(salesOrder.getSub().getMemberName());
    returnOrder.setContact(salesOrder.getContact());
    returnOrder.setMobile(salesOrder.getMobile());
    List<ReturnOrderDetail> inDetails = returnOrder.getDetails();
    List<SalesOrderDetail> salesOrderDetails = setDetails(inDetails, salesOrder, returnOrder);
    //如果都能关联上,那么就不是异常单
    if (Assert.isEmpty(salesOrderDetails)) {
      returnOrder.setStatus(ReturnOrderStatus.AUDITED);
      returnOrder.setAbnormal(false);
    }

    RelateSourceOrderBO orderBO = new RelateSourceOrderBO();
    orderBO.setSalesOrder(salesOrder);
    orderBO.setReturnOrder(returnOrder);
    return orderBO;
  }

  /**
   * 添加原单后，完善 主单 和 退入的信息.
   *
   * @param inDetails   退入的订单
   * @param salesOrder  原销售单
   * @param returnOrder 退换货单
   */
  private List<SalesOrderDetail> setDetails(List<ReturnOrderDetail> inDetails,
      SalesOrder salesOrder,
      ReturnOrder returnOrder) {
    if (Assert.isEmpty(inDetails)) {
      return null;
    }
    ArrayList<SalesOrderDetail> salesOrderDetails = new ArrayList<>();
    returnOrder.setRefundableAmount(0D);
    inDetails.forEach(inDetail -> {
      inDetail.setSalesOrderCode(null);
      inDetail.setSalesOrderId(null);
      inDetail.setSalesOrderDetailId(null);
      inDetail.setRefundableAmount(0.0D);
      //拿到无头件 对应原单明细 如果找不到,则说明:此商品不一定是该订单的
      List<SalesOrderDetail> sourceDetails = salesOrder.getDetails()
          .stream()
          .filter(y -> {
                if (!Assert.isNull(inDetail.getSkuCode())) {
                  return inDetail.getSkuCode().equals(y.getSkuCode());
                } else {
                  return false;
                }
              }
          ).collect(Collectors.toList());

      //判断退入的无头件 在原单中是否存在 , 如果不存在 将原单的明细 返回页面,让用户强行关联
      if (sourceDetails != null && sourceDetails.size() > 0) {

        inDetail.setSalesOrderId(salesOrder.getSalesOrderId());
        inDetail.setSalesOrderCode(salesOrder.getSalesOrderCode());
        inDetail.setTradeId(salesOrder.getTradeId());
        // 退回的无头件 对应 销售单的明细
        inDetail.setSalesOrderDetailId(sourceDetails.get(0).getSalesOrderDetailId());
        //明细的应退金额
        inDetail.setRefundableAmount(sourceDetails.get(0).getActualAmount());

        //主单的应退金额
        if (returnOrder.getRefundableAmount() == null) {
          returnOrder.setRefundableAmount(sourceDetails.get(0).getActualAmount());
        } else {
          returnOrder.setRefundableAmount(
              returnOrder.getRefundableAmount() + sourceDetails.get(0).getActualAmount());
        }
      } else {
        if (Assert.isEmpty(salesOrderDetails)) {
          salesOrderDetails.addAll(salesOrder.getDetails());
        }
      }
    });
    return salesOrderDetails;
  }

  @Override
  public void updateAbnormal(VersionBO<ReturnOrder> bo) {
    ReturnOrder returnOrder = bo.getDomain();
    returnOrder.setVersion(bo.getVersion());
    if (returnOrder.getStatus() == ReturnOrderStatus.AUDITED) {
      returnOrder.setAuditedTime(LocalDateTime.now());
      returnOrder.setAuditor(BizContext.getNickname());
    }
    try {
      getTransactionTemplate().execute(() -> {
        returnOrderDetailService.batchModify(returnOrder.getDetails());
        super.update(returnOrder);
      });
      mqProducer.send(new GeneralMessage(OmsModule.RETURN_ORDER, returnOrder.getReturnOrderId(),
          EventType.AUDITED));
      BIZ_LOGGER.log(returnOrder.getReturnOrderId(), "关联");
    } catch (Exception e) {
      LOGGER.error("无头件关联保存失败，退换货单：{}", JsonUtil.toJson(returnOrder));
      LOGGER.error("无头件关联保存失败，堆栈信息：", e);
      throw new OmsException("无头件关联保存失败！");
    }
  }

  @Override
  public void splitUnknownOrder(ReturnOrder returnOrderBO) {
    //原异常单 里要分出来的明细  修改拆分后原来的退换货单的数量和金额
    ReturnOrder updateReturnOrder = new ReturnOrder();
    ArrayList<ReturnOrderDetail> updateDetails = new ArrayList<>();
    updateReturnOrder.setReturnOrderId(returnOrderBO.getReturnOrderId());
    updateReturnOrder.setVersion(returnOrderBO.getVersion());
    updateReturnOrder.setQuantity(returnOrderBO.getQuantity());
    updateReturnOrder.setRefundableAmount(returnOrderBO.getRefundableAmount());

    List<ReturnOrderDetail> createDetail = new ArrayList<>();
    returnOrderBO.setReturnOrderId(getIdGenerator().next());
    returnOrderBO.setReturnOrderCode(returnOrderCodeGenerator.next());
    returnOrderBO.setQuantity(0);
    returnOrderBO.setRefundableAmount(0D);
    //清除上一次关联的 主单信息
    clearOtherMessage(returnOrderBO);
    returnOrderBO.setCreator(BizContext.getNickname());
    //设置要修改的明细
    returnOrderBO.getDetails().forEach(x -> {
      ReturnOrderDetail oldDetail = returnOrderDetailService.getByKey(x.getReturnOrderDetailId());

      if (oldDetail.getQuantity().equals(x.getQuantity())) {
        //设置明细的 主单id
        oldDetail.setReturnOrderId(returnOrderBO.getReturnOrderId());
      } else {
        //拆分后的金额
        double singlePrice = MathUtil
            .divide(oldDetail.getRefundableAmount(), oldDetail.getQuantity());
        double splitAmount = MathUtil.multiply(x.getQuantity(), singlePrice);
        oldDetail.setQuantity(oldDetail.getQuantity() - x.getQuantity());
        oldDetail.setRefundableAmount(MathUtil.minus(oldDetail.getRefundableAmount(), splitAmount));
        x.setReturnOrderDetailId(getIdGenerator().next());
        x.setRefundableAmount(splitAmount);
        x.setReturnOrderId(returnOrderBO.getReturnOrderId());
        x.setSalesOrderCode(null);
        x.setSalesOrderId(null);
        x.setSalesOrderDetailId(null);
        x.setTradeId(null);
        createDetail.add(x);
      }
      //修改拆分后原来 退换货单的数量 和 金额
      updateReturnOrder.setQuantity(updateReturnOrder.getQuantity() - x.getQuantity());
      updateReturnOrder
          .setRefundableAmount(updateReturnOrder.getRefundableAmount() - x.getRefundableAmount());
      updateDetails.add(oldDetail);
    });
    //设置 拆出的明细
    updateReturnOrder.setDetails(updateDetails);

    //判断原异常单 拆完后是否还有 无头件
    if (Assert.isEmpty(createDetail)) {
      ReturnOrderDetail detailEg = new ReturnOrderDetail();
      detailEg.setReturnOrderId(updateReturnOrder.getReturnOrderId());
      List<ReturnOrderDetail> details = returnOrderDetailService.listExample(detailEg);
      //拆完后剩下来的
      List<ReturnOrderDetail> collect = details.stream()
          .filter(x -> {
            List<Long> detailIds = returnOrderBO.getDetails().stream()
                .map(y -> y.getReturnOrderDetailId()).collect(Collectors.toList());
            return !detailIds.contains(x.getReturnOrderDetailId());
          }).collect(Collectors.toList());
      updateReturnOrder.setAbnormal(false);
      updateReturnOrder.setStatus(ReturnOrderStatus.AUDITED);
      updateReturnOrder.setAuditor(BizContext.getNickname());
      updateReturnOrder.setAuditedTime(LocalDateTime.now());
      collect.forEach(x -> {
        if (Assert.isNull(x.getSalesOrderId())) {
          updateReturnOrder.setAbnormal(true);
          updateReturnOrder.setStatus(ReturnOrderStatus.CREATED);
          updateReturnOrder.setAuditor(null);
          updateReturnOrder.setAuditedTime(null);
        }
      });
    }
    try {
      getTransactionTemplate().execute(() -> {
        //插入拆出的主单和明细
        returnOrderDetailService.batchCreate(createDetail);
        super.create(returnOrderBO);
        //修改之前主单的明细 到 现在的主单名下
        returnOrderDetailService.modifyDetail(updateReturnOrder);
      });
      BIZ_LOGGER.log(returnOrderBO.getReturnOrderId(), "拆分");
    } catch (Exception e) {
      LOGGER.error("拆分退换货单失败，退换货单：{}", JsonUtil.toJson(returnOrderBO));
      LOGGER.error("拆分退换货单失败，堆栈信息：", e);
      throw new OmsException("拆分退换货单失败！");
    }
  }


  /**
   * 适用情况： 异常退换货单，多个退入明细，并且这些明细还不是同一个订单的，主单已经关联过 作用： 此退换货主单已经关联，拆分时应将主单关联的信息清除掉，生成一个新的异常主单
   */
  private void clearOtherMessage(ReturnOrder returnOrder) {
    //这个要拆分的主单没有原单信息，
    if (Assert.isNull(returnOrder.getSalesOrderId())) {
      return;
    }
    //将原单上次关联的信息清除
    returnOrder.setTradeId(null);
    returnOrder.setSalesOrderId(null);
    returnOrder.setSalesOrderCode(null);
    returnOrder.setContact(null);
    returnOrder.setMobile(null);
    returnOrder.setMemberId(null);
    returnOrder.setMemberName(null);
    returnOrder.setStatus(ReturnOrderStatus.CREATED);
    returnOrder.setStoreId(null);
    returnOrder.setStoreName(null);
    returnOrder.setOutVirtualWarehouseId(null);
    returnOrder.setOutVirtualWarehouseName(null);
    returnOrder.setVersion(0);
    returnOrder.setAbnormal(true);
    returnOrder.setCreator(null);
    returnOrder.setCreatedTime(null);
  }

  @Override
  public void addOutDetail(ReturnOrder returnOrder) {
    //1.如果换出的商品 已经存在，则加数量
    ReturnOrderOutDetail outDetail = returnOrder.getOutDetails().get(0);
    ReturnOrderOutDetail detailEg = new ReturnOrderOutDetail();
    detailEg.setReturnOrderId(returnOrder.getReturnOrderId());
    List<ReturnOrderOutDetail> returnOrderOutDetails = returnOrderOutDetailService
        .listExample(detailEg);
    List<ReturnOrderOutDetail> collect = returnOrderOutDetails.stream()
        .filter(x -> x.getSkuCode().equals(outDetail.getSkuCode())).collect(Collectors.toList());
    if (collect != null && collect.size() > 0) {
      ReturnOrderOutDetail oldDetail = collect.get(0);
      //累加数量
      oldDetail.setQuantity(oldDetail.getQuantity() + outDetail.getQuantity());

      BigDecimal oldRefundAmount = new BigDecimal(returnOrder.getRefundableAmount());
      BigDecimal outAmount = new BigDecimal(outDetail.getActualAmount());
      BigDecimal refundableAmount = oldRefundAmount.subtract(outAmount);

      returnOrder.setRefundableAmount(
          refundableAmount.doubleValue() > 0 ? refundableAmount.doubleValue() : 0D);

      oldDetail.setActualAmount(
          oldDetail.getActualAmount() + outDetail.getQuantity() * outDetail.getActualAmount());
      try {
        //保证更新  原子性
        getTransactionTemplate().execute(() -> {
          //修改库存占用数量
          stockOccupancyService
              .adjustQuantity(returnOrder.getReturnOrderId(), oldDetail.getReturnOrderOutDetailId(),
                  StockOccupancyType.RETURN_ORDER, outDetail.getQuantity());
          //更新 换出信息
          returnOrderOutDetailService.modify(oldDetail);
          update(returnOrder);
        });
        BIZ_LOGGER.log(returnOrder.getReturnOrderId(), "添加换出");
        getMqProducer().send(
            new StockChangedMessage(String.valueOf(getIdGenerator().next()), oldDetail.getSkuId(),
                returnOrder.getOutVirtualWarehouseId(), returnOrder.getStoreId(),
                BizContext.getNickname(), "添加换出商品"));
      } catch (Exception e) {
        LOGGER.error("修改退换货单换出商品数量失败，退换货单：{}", JsonUtil.toJson(returnOrder));
        LOGGER.error("修改退换货单换出商品数量失败，堆栈信息：", e);
        throw new OmsException("新增退换货单换出商品数量失败！");
      }
    } else {
      // 新增 换出信息
      SalesOrder salesOrder = salesOrderService.getByKey(returnOrder.getSalesOrderId());
      outDetail.setReturnOrderOutDetailId(returnOrderIdGenerator.next());
      //计算主单的应退
      BigDecimal oldRefundAmount = new BigDecimal(returnOrder.getRefundableAmount());
      BigDecimal outAmount = new BigDecimal(outDetail.getActualAmount());
      BigDecimal refundableAmount = oldRefundAmount.subtract(outAmount);

      returnOrder.setRefundableAmount(
          refundableAmount.doubleValue() > 0 ? refundableAmount.doubleValue() : 0D);
      returnOrder.setExchange(true);
      //设置地址
      returnOrder.setProvinceId(salesOrder.getProvinceId());
      returnOrder.setProvinceName(salesOrder.getProvinceName());
      returnOrder.setCityId(salesOrder.getCityId());
      returnOrder.setCityName(salesOrder.getCityName());
      returnOrder.setCountryId(salesOrder.getCountryId());
      returnOrder.setCountryName(salesOrder.getCountryName());
      returnOrder.setDistrictId(salesOrder.getDistrictId());
      returnOrder.setDistrictName(salesOrder.getDistrictName());
      returnOrder.setAddress(salesOrder.getAddress());
      returnOrder.setContact(salesOrder.getContact());
      returnOrder.setMobile(salesOrder.getMobile());
      //查仓库 新增库存占用
      VirtualWarehouse virtualWarehouse = virtualWarehouseService
          .getByKey(returnOrder.getOutVirtualWarehouseId());
      //库存占用
      StockOccupancy stockOccupancy = new StockOccupancy();
//      stockOccupancy.setStockOccupancyId(returnOrderIdGenerator.next());
      stockOccupancy.setVirtualWarehouseId(virtualWarehouse.getVirtualWarehouseId());
      stockOccupancy.setVirtualWarehouseName(virtualWarehouse.getVirtualWarehouseName());
      stockOccupancy.setWarehouseId(virtualWarehouse.getWarehouseId());
      stockOccupancy.setWarehouseName(virtualWarehouse.getWarehouseName());
      stockOccupancy.setSkuId(outDetail.getSkuId());
      stockOccupancy.setSkuCode(outDetail.getSkuCode());
      stockOccupancy.setQuantity(outDetail.getQuantity());
      stockOccupancy.setStockOccupancyType(StockOccupancyType.RETURN_ORDER);
      stockOccupancy.setMainId(returnOrder.getReturnOrderId());
      stockOccupancy.setDetailId(outDetail.getReturnOrderOutDetailId());
      stockOccupancy.setStatus(StockOccupancyStatus.LOCK);
      try {
        //新增保证 原子性
        getTransactionTemplate().execute(() -> {
          //更新 主单信息
          returnOrderOutDetailService.create(outDetail);
          //新增库存占用
          stockOccupancyService.create(stockOccupancy);
          update(returnOrder);
        });
        BIZ_LOGGER.log(returnOrder.getReturnOrderId(), "添加换出");
        getMqProducer().send(
            new StockChangedMessage(String.valueOf(getIdGenerator().next()), outDetail.getSkuId(),
                returnOrder.getOutVirtualWarehouseId(), returnOrder.getStoreId(),
                BizContext.getNickname(), "添加换出商品"));
      } catch (Exception e) {
        LOGGER.error("新增退换货单换出商品失败，退换货单：{}", JsonUtil.toJson(returnOrder));
        LOGGER.error("新增退换货单换出商品失败，堆栈信息：", e);
        throw new OmsException("新增退换货单换出商品失败！");
      }
    }
  }


  @Override
  public ScanExpressBO getSourceOrder(SourceOrderFilterBO filterBO) {
    String expressNo = filterBO.getExpressNo();
    SalesOrderQuery filter = filterBO.getSalesOrderQuery();

    List<RefundApplyOrder> refundApplyOrders = new ArrayList<>();
    List<ExchangeApplyOrder> exchangeApplyOrders = new ArrayList<>();

    //快递号
    if (!Assert.isNull(expressNo)) {
      //找售后单
      refundApplyOrders = refundApplyOrderService
          .getRefundApplyOrderByExpressNo(expressNo);
      //如果售后单 为空就找 换货单申请
      exchangeApplyOrders = exchangeApplyOrderService
          .getByExpressNo(expressNo);

      // 售后单 和 换货单 都找不到
      if (Assert.isEmpty(refundApplyOrders) && Assert.isEmpty(exchangeApplyOrders)) {
        return null;
      }
      // 根据 售后 或者 换货 里的 tradeId （交易的id） 找到销售单
      List<String> tradeIds = new ArrayList<>();
      Long storeId = null;
      if (refundApplyOrders != null && refundApplyOrders.size() > 0) {
        refundApplyOrders.forEach(x -> tradeIds.add(x.getTradeId()));
        storeId = refundApplyOrders.get(0).getStoreId();
      }
      if (exchangeApplyOrders != null && exchangeApplyOrders.size() > 0) {
        exchangeApplyOrders.forEach(x -> tradeIds.add(x.getTradeId()));
        storeId = exchangeApplyOrders.get(0).getStoreId();
      }
      //为销售单去重  有可能  一个订单两件商品 售后就是两条，那两个售后找到的 订单其实是同一个
      List<String> ids = tradeIds.stream().distinct().collect(Collectors.toList());

      filter.setTradeIds(ids);
      filter.setStoreId(storeId);
    }

    //原单
    List<SalesOrder> salesOrders = salesOrderService.listDetail(filter);
    if (Assert.isEmpty(salesOrders)) {
      return null;
    }

    //过滤出 主单是发货的 , 明细是发货的
    List<SalesOrder> orders = filterDelivery(salesOrders);//todo 为方便测试 , 暂时注释
    if (Assert.isEmpty(orders)) {
      return null;
    }

    //拿到原单明细图片
    List<ImageBO> images = getImages(orders);

    //店铺
    Store store = storeService.getByKey(orders.get(0).getStoreId());

    //解密 手机号 和 联系人
    batchDecrypt(orders, store);

    return new ScanExpressBO(refundApplyOrders, exchangeApplyOrders,
        orders, store, images);
  }

  @Override
  public Integer modifyAmount(VersionBO<ReturnOrder> returnOrderVersionBO) {
    ReturnOrder returnOrder = returnOrderVersionBO.getDomain();
    Double inAmount = 0.0D;
    Double outAmount = 0.0D;
    //计算总的退入的金额
    List<ReturnOrderDetail> details = returnOrder.getDetails();
    //退入的为空 , 那就是从换出提交,修改了换出的金额,查退入的
    if (Assert.isEmpty(details)) {
      ReturnOrderDetail detailEg = new ReturnOrderDetail();
      detailEg.setReturnOrderId(returnOrder.getReturnOrderId());
      details = returnOrderDetailService.listExample(detailEg);
    }
    if (!Assert.isEmpty(details)) {
      inAmount = details.stream().map(x -> x.getRefundableAmount()).reduce((a, b) -> a + b)
          .get();
    }
    //计算总的换出金额
    List<ReturnOrderOutDetail> outDetails = returnOrder.getOutDetails();
    //如果换出的为空,那可能是从退入修改 , 修改换出的金额,查换出的金额
    if (Assert.isEmpty(outDetails)) {
      ReturnOrderOutDetail outDetailEg = new ReturnOrderOutDetail();
      outDetailEg.setReturnOrderId(returnOrder.getReturnOrderId());
      outDetails = returnOrderOutDetailService.listExample(outDetailEg);
      returnOrder.setOutDetails(outDetails);
    }
    if (!Assert.isEmpty(outDetails)) {
      outAmount = outDetails.stream().map(x -> x.getActualAmount()).reduce((a, b) -> a + b).get();
    }
    //修改主单的应退金额
    double refundableAmount = new BigDecimal(inAmount).subtract(new BigDecimal(outAmount))
        .doubleValue();
    returnOrder.setRefundableAmount(refundableAmount > 0 ? refundableAmount : 0.0D);
    //修改
    getTransactionTemplate().execute(() -> {
      returnOrderDetailService.modifyDetail(returnOrder);
      BIZ_LOGGER.log(returnOrder.getReturnOrderId(), "修改应退金额");
      if (!Assert.isEmpty(returnOrder.getOutDetails())) {
        returnOrderOutDetailService.batchModify(returnOrder.getOutDetails());
        BIZ_LOGGER.log(returnOrder.getReturnOrderId(), "修改应付金额");
      }
    });
    ReturnOrder newReturnOrder = returnOrderService.getByKey(returnOrder.getReturnOrderId());
    return newReturnOrder.getVersion();
  }

  private void batchDecrypt(List<SalesOrder> salesOrders, Store store) {
    salesOrders.forEach(so -> {
      SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
      String contact = securityBridge.decrypt(store, so.getContact(), DataType.NAME);
      String mo = securityBridge.decrypt(store, so.getMobile(), DataType.MOBILE);
      so.setContact(contact);
      so.setMobile(mo);
    });
  }

  private List<ImageBO> getImages(SalesOrder salesOrder) {
    List<String> codes = salesOrder.getDetails().stream().map(x -> x.getSkuCode()).distinct()
        .collect(Collectors.toList());
    if (Assert.isEmpty(codes)) {
      return null;
    }
    ProductSkuQuery eg = new ProductSkuQuery();
    eg.setSkuCodes(codes);
    List<ProductSku> productSkus = productSkuService.listFullInfo(eg);
    ArrayList<ImageBO> images = new ArrayList<>(codes.size());
    productSkus.forEach(x -> {
      ImageBO colorAndImage = new ImageBO();
      colorAndImage.setSkuCode(x.getSkuCode());
      colorAndImage.setImageUrl(x.getProduct().getImageUrl());
      images.add(colorAndImage);
    });
    return images;
  }

  private List<ImageBO> getImages(List<SalesOrder> salesOrders) {
    List<ImageBO> list = new ArrayList<>();
    salesOrders.forEach(x -> {
      List<ImageBO> images = getImages(x);
      if (!Assert.isEmpty(images)) {
        list.addAll(images);
      }
    });
    return list;
  }

  //只查找已经发货单的
  private List<SalesOrder> filterDelivery(List<SalesOrder> salesOrders) {
    //主单是发货了的
    List<SalesOrder> salesOrderList = salesOrders.stream()
        .filter(salesOrder -> {
          if (DeliveryStatus.ALL == salesOrder.getDeliveryStatus()
              || DeliveryStatus.PART == salesOrder.getDeliveryStatus()) {
            return true;
          } else {
            return false;
          }
        }).collect(Collectors.toList());
    //明细是发货了的
    for (SalesOrder salesOrder : salesOrderList) {
      List<SalesOrderDetail> details = salesOrder.getDetails();
      if (!Assert.isEmpty(details)) {
        List<SalesOrderDetail> collect = details.stream()
            .filter(x -> x.getStatus() == SalesOrderDetailStatus.DELIVERED
                && x.getSalesOrderDetailType() == SalesOrderDetailType.NORMAL)
            .collect(Collectors.toList());
        salesOrder.setDetails(collect);
      }
    }
    return salesOrderList;
  }

  @Override
  public void reposting(ReturnOrder returnOrder) {
    if (returnOrder.getStatus() == ReturnOrderStatus.REVIEWED) {
      getMqProducer()
          .send(new DataRepostingMessage(returnOrder.getReturnOrderId(), "trade.return.order"));
    }
  }
}