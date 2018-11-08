package com.greatonce.oms.biz.impl.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.MathUtil;
import com.greatonce.core.util.SecurityUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.admin.GlobalExpressMallMappingService;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.biz.product.ProductCombinationService;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.product.ProductService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.stock.StockOccupancyService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderDiscountService;
import com.greatonce.oms.biz.trade.SalesOrderInvoiceService;
import com.greatonce.oms.biz.trade.SalesOrderMarketingService;
import com.greatonce.oms.biz.trade.SalesOrderPaymentService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.biz.trade.SalesOrderSubService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.mall.MallSalesOrderDetailInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderInvoiceInfo;
import com.greatonce.oms.bo.mall.MallSalesOrderPaymentInfo;
import com.greatonce.oms.bo.stock.StockCheckResultBO;
import com.greatonce.oms.bo.trade.DownloadSalesOrderBO;
import com.greatonce.oms.bo.trade.MallDeliveryBO;
import com.greatonce.oms.bo.trade.MallExpressResetBo;
import com.greatonce.oms.bo.trade.MallFinishBO;
import com.greatonce.oms.bo.trade.ManualDispatchBO;
import com.greatonce.oms.bo.trade.ManualDispatchDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderAddDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderBO;
import com.greatonce.oms.bo.trade.SalesOrderCancelDispatchBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailBatchBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailSplitBO;
import com.greatonce.oms.bo.trade.SalesOrderDispatchBO;
import com.greatonce.oms.bo.trade.SalesOrderExpressNoticeBo;
import com.greatonce.oms.bo.trade.SalesOrderHoldBO;
import com.greatonce.oms.bo.trade.SalesOrderImportBO;
import com.greatonce.oms.bo.trade.SalesOrderManualBO;
import com.greatonce.oms.bo.trade.SalesOrderModifyReceiverInfoBO;
import com.greatonce.oms.bo.trade.SalesOrderRemarkBo;
import com.greatonce.oms.bo.trade.SalesOrderReplaceDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderResetDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderWmsDeliveryBO;
import com.greatonce.oms.bo.trade.SuggestExpressBO;
import com.greatonce.oms.bo.trade.SuggestWarehouseBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.OrderBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest;
import com.greatonce.oms.bridge.mall.request.OrderDeliveryRequest.OrderDeliveryDetail;
import com.greatonce.oms.bridge.mall.request.OrderGetRequest;
import com.greatonce.oms.bridge.mall.response.OrderDeliveryResponse;
import com.greatonce.oms.bridge.mall.response.OrderGetResponse;
import com.greatonce.oms.dao.trade.SalesOrderDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.admin.GlobalExpressMallMapping;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.setting.SalesOrderSetting;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.mall.MallDataProcessStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.enums.marketing.PresellType;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.InvoiceType;
import com.greatonce.oms.domain.enums.trade.RefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderCreateType;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailRefundStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.enums.trade.SourceType;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderDiscount;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import com.greatonce.oms.domain.trade.SalesOrderMarketing;
import com.greatonce.oms.domain.trade.SalesOrderPayment;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.GeneralMessage.EventType;
import com.greatonce.oms.message.Message;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.message.trade.SalesOrderAuditMessage;
import com.greatonce.oms.message.trade.SalesOrderAutoDispatchMessage;
import com.greatonce.oms.message.trade.SalesOrderDeliveryFailMessage;
import com.greatonce.oms.message.trade.SalesOrderDeliveryMessage;
import com.greatonce.oms.message.trade.SalesOrderDetailChangeMessage;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.SalesOrderDetailQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.stereotype.Service;

/**
 * 销售订单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class SalesOrderServiceImpl
    extends AbstractVersionService<SalesOrder, SalesOrderQuery>
    implements SalesOrderService {

  private static final String CACHE_NAME = "BATCH_BOOKMARK";
  private static final Logger LOGGER = LoggerFactory.getLogger(SalesOrderServiceImpl.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.SALES_ORDER);
  @Autowired
  private SalesOrderDao dao;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private SalesOrderDiscountService salesOrderDiscountService;
  @Autowired
  private SalesOrderSubService salesOrderSubService;
  @Autowired
  private SalesOrderMarketingService salesOrderMarketingService;
  @Autowired
  private SalesOrderInvoiceService salesOrderInvoiceService;
  @Autowired
  private SalesOrderPaymentService salesOrderPaymentService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private StockOccupancyService stockOccupancyService;
  @Autowired
  private StockService stockService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private SettingService settingService;
  @Autowired
  private MallSalesOrderService mallSalesOrderService;
  @Autowired
  private DispatchOrderDeliveryService dispatchOrderDeliveryService;
  @Resource
  private IdGenerator salesOrderIdGenerator;
  @Resource
  private CodeGenerator salesOrderCodeGenerator;
  @Autowired
  private ExpressService expressService;
  @Autowired
  private GlobalExpressMallMappingService globalExpressMallMappingService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private ProductService productService;
  @Autowired
  private ProductSkuService productSkuService;
  @Autowired
  private ProductCombinationService productCombinationService;
  @Autowired
  @Qualifier("bizExecutor")
  private AsyncTaskExecutor bizExecutor;

  @Override
  protected QueryDao<SalesOrder, SalesOrderQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return salesOrderIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected int insert(SalesOrder salesOrder) {
    initDefaultValue(salesOrder);
    //设置合单标记
    salesOrder.setMergeFlag(buildMergeFlag(salesOrder));
    int result;
    try {
      //声明式事务
      result = getTransactionTemplate().executeWithResult(() -> {
        salesOrderSubService.create(salesOrder.getSub());
        //创建订单各明细
        if (!Assert.isEmpty(salesOrder.getDetails())) {
          salesOrderDetailService.batchCreate(salesOrder.getDetails());
        }
        if (!Assert.isEmpty(salesOrder.getPayments())) {
          salesOrderPaymentService.batchCreate(salesOrder.getPayments());
        }
        if (!Assert.isEmpty(salesOrder.getDiscounts())) {
          salesOrderDiscountService.batchCreate(salesOrder.getDiscounts());
        }
        if (!Assert.isEmpty(salesOrder.getInvoices())) {
          salesOrderInvoiceService.batchCreate(salesOrder.getInvoices());
        }
        if (!Assert.isEmpty(salesOrder.getMarketings())) {
          salesOrderMarketingService.batchCreate(salesOrder.getMarketings());
        }

        //添加占用
        Store store = storeService.getByKey(salesOrder.getStoreId());
        List<StockOccupancy> stockOccupancies =
            SalesOrderUtil.buildOccupancy(salesOrder, salesOrder.getDetails(), store);
        if (!Assert.isEmpty(stockOccupancies)) {
          stockOccupancyService.batchCreate(stockOccupancies);
        }
        return super.insert(salesOrder);
      });
    } catch (Exception e) {
      LOGGER.error("订单新增失败，订单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("订单新增失败，堆栈信息：", e);
      throw new OmsException("订单新增失败");
    }
    return result;
  }

  @Override
  public int create(SalesOrder salesOrder) {
    int result = insert(salesOrder);
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.CREATED)
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
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.ADD,
        salesOrder.getSub().getCreateType().caption());
    return result;
  }

  @Override
  public int insertByOther(SalesOrder salesOrder) {
    return insert(salesOrder);
  }

  @Override
  protected void initDefaultValue(SalesOrder salesOrder) {
    super.initDefaultValue(salesOrder);
    if (Assert.isNull(salesOrder.getSalesOrderCode())) {
      salesOrder.setSalesOrderCode(salesOrderCodeGenerator.next());
    }
    if (Assert.isEmpty(salesOrder.getCreator())) {
      salesOrder.setCreator(BizContext.getNickname());
    }
    if (salesOrder.getSub().getSalesOrderId() == null) {
      salesOrder.getSub().setPrimaryKey(salesOrder.getSalesOrderId());
    }
    if (!Assert.isEmpty(salesOrder.getDetails())) {
      salesOrder.getDetails().forEach(x -> x.setSalesOrderId(salesOrder.getSalesOrderId()));
    }
    if (salesOrder.getPayments() != null) {
      salesOrder.getPayments().forEach(x -> x.setSalesOrderId(salesOrder.getSalesOrderId()));
    }
    if (salesOrder.getDiscounts() != null) {
      salesOrder.getDiscounts().forEach(x -> x.setSalesOrderId(salesOrder.getSalesOrderId()));
    }
    if (salesOrder.getInvoices() != null) {
      salesOrder.getInvoices().forEach(x -> x.setSalesOrderId(salesOrder.getSalesOrderId()));
    }
    if (salesOrder.getMarketings() != null) {
      salesOrder.getMarketings().forEach(x -> x.setSalesOrderId(salesOrder.getSalesOrderId()));
    }
    salesOrder.setOos(Assert.isTrue(salesOrder.isOos()));
    salesOrder.setHold(Assert.isTrue(salesOrder.isHold()));
    salesOrder.setUrgent(Assert.isTrue(salesOrder.isUrgent()));
    salesOrder.setManual(Assert.isTrue(salesOrder.isManual()));
    salesOrder.setForceSplit(Assert.isTrue(salesOrder.isForceSplit()));
    salesOrder.setProductAbnormal(Assert.isTrue(salesOrder.isProductAbnormal()));
    salesOrder.setPrerefund(false);
  }

  /**
   * 修改订单信息.
   */
  @Override
  public int modify(SalesOrder salesOrder) {
    salesOrder.setMergeFlag(buildMergeFlag(salesOrder));

    //明细中的推荐仓库也要随订单中推荐仓库修改
    VirtualWarehouse warehouse =
        virtualWarehouseService.getByKey(salesOrder.getSuggestVirtualWarehouseId());
    salesOrder.getDetails().forEach(detail -> {
      if (!detail.getSuggestVirtualWarehouseId()
          .equals(salesOrder.getSuggestVirtualWarehouseId())) {
        detail.setSuggestWarehouseId(salesOrder.getSuggestWarehouseId());
        detail.setSuggestWarehouseName(salesOrder.getSuggestWarehouseName());
        detail.setSuggestVirtualWarehouseId(salesOrder.getSuggestVirtualWarehouseId());
        detail.setSuggestVirtualWarehouseName(salesOrder.getSuggestVirtualWarehouseName());
      }
    });

    //编程式事务
    try {
      int count = getTransactionTemplate().executeWithResult(() -> {
        salesOrderDetailService.batchModify(salesOrder.getDetails());
        stockOccupancyService.updateOccupancy(salesOrder.getSalesOrderId(),
            StockOccupancyType.SALES_ORDER, warehouse);
        if (!Assert.isNull(salesOrder.getSub())) {
          salesOrderSubService.modify(salesOrder.getSub());
        }
        return update(salesOrder);
      });
      validateChangedCount(count);
      getMqProducer().send(
          new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
              EventType.MODIFIED)
      );
      return count;
    } catch (OmsException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("订单修改失败，订单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("订单修改失败，堆栈信息：", e);
      throw new OmsException("订单修改失败");
    }
  }

  /**
   * 单条删除（不允许）.
   */
  @Override
  public int remove(SalesOrder salesOrder) {
    throw SysExceptions.DATA_NOT_ALLOW_DELETE;
  }

  @Override
  public SalesOrder getFullInfo(Long salesOrderId) {
    SalesOrder order = getByKey(salesOrderId);
    if (order != null) {
      order.setSub(salesOrderSubService.getByKey(salesOrderId));

      SalesOrderDetail detailEg = new SalesOrderDetail();
      detailEg.setSalesOrderId(salesOrderId);
      order.setDetails(salesOrderDetailService.listExample(detailEg));

      SalesOrderDiscount discountEg = new SalesOrderDiscount();
      discountEg.setSalesOrderId(salesOrderId);
      order.setDiscounts(salesOrderDiscountService.listExample(discountEg));

      SalesOrderInvoice invoiceEg = new SalesOrderInvoice();
      invoiceEg.setSalesOrderId(salesOrderId);
      order.setInvoices(salesOrderInvoiceService.listExample(invoiceEg));

      SalesOrderMarketing marketingEg = new SalesOrderMarketing();
      marketingEg.setSalesOrderId(salesOrderId);
      order.setMarketings(salesOrderMarketingService.listExample(marketingEg));

      SalesOrderPayment paymentEg = new SalesOrderPayment();
      paymentEg.setSalesOrderId(salesOrderId);
      order.setPayments(salesOrderPaymentService.listExample(paymentEg));
    }
    return order;
  }

  @Override
  public SalesOrder getDetailInfo(Long salesOrderId) {
    SalesOrder salesOrder = getByKey(salesOrderId);
    if (salesOrder == null) {
      return null;
    }
    salesOrder.setDetails(salesOrderDetailService.listBySalesOrderId(salesOrderId));
    return salesOrder;
  }

  @Override
  public SalesOrder getSimpleInfo(Long salesOrderId) {
    return dao.getSimpleInfo(salesOrderId);
  }

  @Override
  public SalesOrder getDispatchInfo(Long salesOrderId) {
    return dao.getDispatchInfo(salesOrderId);
  }

  /**
   * 计算合单标记.
   * @param salesOrder 对象里必须传入sub对象，且会员名称不能为null.
   */
  @Override
  public String buildMergeFlag(SalesOrder salesOrder) {
    if (Assert.isTrue(salesOrder.getSub().isCod())) {
      return SecurityUtil.md5Hex(String.valueOf(salesOrder.getSalesOrderId()));
    }
    SalesOrderSetting setting = settingService.getSalesOrderSetting();
    if (setting.getOrderMergeRule() == SalesOrderSetting.OrderMergeRule.SAME_STORE) {
      String toMD5 = salesOrder.getStoreId().toString()
          + salesOrder.getContact()
          + salesOrder.getCountryName()
          + salesOrder.getProvinceName()
          + salesOrder.getCityName()
          + salesOrder.getDistrictName()
          + salesOrder.getAddress()
          + salesOrder.getMobile()
          + salesOrder.getTelephone()
          + salesOrder.getSub().getMemberName();
      return SecurityUtil.md5Hex(toMD5);
    }
    return SecurityUtil.md5Hex(String.valueOf(salesOrder.getSalesOrderId()));
  }

  /**
   * 根据MD5值获取待配货的订单，包含明细
   */
  @Override
  public List<SalesOrder> listWaitingDispatchOrder(String mergeMD5) {
    return dao.listWaitingDispatchByMD5(mergeMD5);
  }

  @Override
  public List<SalesOrder> listDetail(SalesOrderQuery filter) {
    return dao.listDetail(filter);
  }

  /**
   * 订单转化的创建订单方法.
   */
  @Override
  public void autoCreate(SalesOrder salesOrder) {
    insert(salesOrder);
  }

  /**
   * 创建订单.
   * <p/>
   * 包括复制订单和新建订单
   */
  @Override
  public void manualCreate(SalesOrder salesOrder) {
    //解析明细中的组合商品
    parseCombination(salesOrder, salesOrder.getDetails());
    if (salesOrder.getSub().getCreateType() == SalesOrderCreateType.COPY) {
      salesOrder.getDetails().forEach(detail -> {
        //修改了推荐仓库或添加了明细时要修改或增加明细里的推荐仓库
        if (detail.getSuggestVirtualWarehouseId() == null || !detail.getSuggestVirtualWarehouseId()
            .equals(salesOrder.getSuggestVirtualWarehouseId())) {
          detail.setSuggestVirtualWarehouseId(salesOrder.getSuggestVirtualWarehouseId());
        }
        if (detail.getSuggestVirtualWarehouseName() == null || !detail
            .getSuggestVirtualWarehouseName().equals(salesOrder.getSuggestVirtualWarehouseName())) {
          detail.setSuggestVirtualWarehouseName(salesOrder.getSuggestVirtualWarehouseName());
        }
        if (detail.getSuggestWarehouseId() == null || !detail.getSuggestWarehouseId()
            .equals(salesOrder.getSuggestWarehouseId())) {
          detail.setSuggestWarehouseId(salesOrder.getSuggestWarehouseId());
        }
        if (detail.getSuggestWarehouseName() == null || !detail.getSuggestWarehouseName()
            .equals(salesOrder.getSuggestWarehouseName())) {
          detail.setSuggestWarehouseName(salesOrder.getSuggestWarehouseName());
        }
      });
      //复制订单修改订单编号和id
      salesOrder.setSalesOrderId(getIdGenerator().next());
      salesOrder.setSalesOrderCode(salesOrderCodeGenerator.next());
    } else {
      salesOrder.setStatus(SalesOrderStatus.CREATED);
      salesOrder.getDetails().forEach(detail -> {
        detail.setNeedReturnGoods(false);
        detail.setSuggestWarehouseId(salesOrder.getSuggestWarehouseId());
        detail.setSuggestWarehouseName(salesOrder.getSuggestWarehouseName());
        detail.setSuggestVirtualWarehouseId(salesOrder.getSuggestVirtualWarehouseId());
        detail.setSuggestVirtualWarehouseName(salesOrder.getSuggestVirtualWarehouseName());
      });
    }
    insert(salesOrder);
    if (salesOrder.getDeliveryStatus() != DeliveryStatus.ALL) {
      for (SalesOrderDetail detail : salesOrder.getDetails()) {
        if (detail.getSkuId() != null && detail.getStatus() == SalesOrderDetailStatus.WAITING) {
          getMqProducer().send(
              new StockChangedMessage(salesOrder.getSalesOrderCode(), detail.getSkuId(),
                  salesOrder.getStoreId(), BizContext.getNickname(), "新建订单"));
        }
      }
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.CREATED)
    );
    //订单日志
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.ADD,
        salesOrder.getSub().getCreateType().caption());
  }

  /**
   * 作废订单.
   * <p/>
   * 已配货不允许作废 作废时删除占用
   *
   * @param salesOrder 订单，需要带version
   */
  @Override
  public void invalid(SalesOrder salesOrder, VersionBO bo) {
    SalesOrderUtil.mustBeforeDispatch(salesOrder);
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.invalid(salesOrder);
        stockOccupancyService
            .deleteOccupancy(salesOrder.getSalesOrderId(), StockOccupancyType.SALES_ORDER);
        SalesOrder update = new SalesOrder();
        update.setSalesOrderId(salesOrder.getSalesOrderId());
        update.setVersion(bo.getVersion());
        update.setStatus(SalesOrderStatus.INVALID);
        update.setOos(false);
        update(update);
      });
    } catch (Exception e) {
      LOGGER.error("销售单作废失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("销售单作废失败，堆栈信息：", e);
      throw new OmsException("销售单作废失败");
    }
    for (SalesOrderDetail detail : salesOrder.getDetails()) {
      getMqProducer().send(
          new StockChangedMessage(salesOrder.getSalesOrderCode(), detail.getSkuId(),
              detail.getSuggestVirtualWarehouseId(), salesOrder.getStoreId(),
              BizContext.getNickname(), "整单作废"));
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.INVALID)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.INVALID);
  }

  /**
   * 订单作废明细.
   * <p/>
   * 未配货的可以作废
   *
   * @param salesOrder 订单，需要带version
   * @param bo 明细
   */
  @Override
  public void invalid(SalesOrder salesOrder, SalesOrderDetailBO bo) {
    SalesOrderUtil.mustBeforeDispatch(bo.getDetail());

    salesOrder.setQuantity(salesOrder.getQuantity() - bo.getDetail().getQuantity());
    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.invalid(salesOrder, bo.getDetail());
        stockOccupancyService.deleteOccupancy(salesOrder.getSalesOrderId(),
            bo.getDetail().getSalesOrderDetailId(), StockOccupancyType.SALES_ORDER);
        resetStatus(salesOrder, bo);
      });
    } catch (OmsException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("订单作废明细失败，销售单：{}，明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(bo.getDetail()));
      LOGGER.error("订单作废明细失败，堆栈信息：", e);
      throw new OmsException("订单作废明细失败");
    }
    getMqProducer().send(
        new StockChangedMessage(salesOrder.getSalesOrderCode(), bo.getDetail().getSkuId(),
            salesOrder.getStoreId(), BizContext.getNickname(), "作废明细"));
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.INVALID)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "作废明细", bo.getDetail().getSkuCode());
  }

  /**
   * 审核订单.
   * <p/>
   * 1、新建或异常的才可以审核 2、非费用订单需校验地址 3、校验商品金额与支付金额 4、商品是否异常
   *
   * @param salesOrder 订单，需要带version
   */
  @Override
  public void audit(SalesOrder salesOrder, SalesOrderBO bo) {
    SalesOrderUtil
        .mustStatus(salesOrder, SalesOrderStatus.CREATED, SalesOrderStatus.AUDITED_ABNORMAL);
    if (salesOrder.getSub().getSalesOrderType() != SalesOrderType.EXPENSE) {
      SalesOrderUtil.validateReceiverInfo(salesOrder);
    }
    SalesOrderUtil.validateAbnormalDetail(salesOrder);
    SalesOrder updateInfo = new SalesOrder();
    updateInfo.setSalesOrderId(salesOrder.getSalesOrderId());
    updateInfo.setVersion(bo.getVersion());
    updateInfo.setManual(false);
    updateInfo.setStatus(SalesOrderStatus.AUDITED);
    updateInfo.setAuditedTime(LocalDateTime.now());
    updateInfo.setAuditor(BizContext.getNickname());
    update(updateInfo);
    Store store = storeService.getByKey(salesOrder.getStoreId());
    getMqProducer().send(new SalesOrderAuditMessage(salesOrder.getSalesOrderId(),
        store.getSetting().getDelayMinutes()));
    // 发送消息，更新es索引
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.AUDITED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.AUDIT);
  }

  /**
   * 手动下载订单.
   * <p/>
   * 根据传入的tradeId逐个下载订单
   *
   * @param bo 下载对象
   */
  @Override
  public void download(DownloadSalesOrderBO bo) {
    Store store = storeService.getByKey(bo.getStoreId());
    if (bo.getTradeIds().size() == 0) {
      throw new OmsException("请输入需要下载的交易号！");
    }
    for (String tradeId : bo.getTradeIds()) {
      bizExecutor.execute(() -> {
        OrderGetRequest request = new OrderGetRequest(store, tradeId);
        OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
        OrderGetResponse response = orderBridge.getOrder(request);

        if (response.isSuccess()) {
          MallSalesOrderInfo info = response.getOrder();
          info.setCreateType(SalesOrderCreateType.MANUAL_DOWNLOAD);
          MallSalesOrder mallSalesOrder = new MallSalesOrder();
          mallSalesOrder.setStoreId(store.getStoreId());
          mallSalesOrder.setMallSalesOrderStatus(info.getStatus());
          mallSalesOrder.setStatus(MallDataProcessStatus.WAITING);
          mallSalesOrder.setCreatedTime(LocalDateTime.now());
          mallSalesOrder.setTradeId(tradeId);
          mallSalesOrder.setOrderJson(JsonUtil.toJson(info));
          mallSalesOrder.setStoreName(store.getStoreName());
          mallSalesOrderService.create(mallSalesOrder);
          BIZ_LOGGER.log("下载订单", BizContext.getNickname(), "{}：{}",
              store.getStoreName(), StringUtil.join(bo.getTradeIds()));
        }
      });
    }
  }

  /**
   * 设置留单.
   */
  @Override
  public void hold(SalesOrder salesOrder, SalesOrderHoldBO bo) {
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    SalesOrder order = new SalesOrder();
    order.setSalesOrderId(salesOrder.getSalesOrderId());
    order.setVersion(bo.getVersion());
    order.setHold(true);
    order.setHoldDate(bo.getHoldDate());
    order.setModifiedTime(LocalDateTime.now());
    validateChangedCount(dao.updateHold(order));
    BIZ_LOGGER.log(order.getSalesOrderId(), "留单");
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
  }

  /**
   * 设置取消留单.
   */
  @Override
  public void cancelHold(SalesOrder salesOrder, VersionBO bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    if (salesOrder.isHold()) {
      SalesOrder order = new SalesOrder();
      order.setSalesOrderId(salesOrder.getSalesOrderId());
      order.setVersion(bo.getVersion());
      order.setHold(false);
      order.setHoldDate(null);
      order.setModifiedTime(LocalDateTime.now());
      validateChangedCount(dao.updateHold(order));
      BIZ_LOGGER.log(order.getSalesOrderId(), "取消留单");
      getMqProducer().send(
          new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
              EventType.MODIFIED)
      );
    }
  }

  /**
   * 设置订单加急.
   */
  @Override
  public void urgent(SalesOrder salesOrder, VersionBO bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    if (!salesOrder.isUrgent()) {
      SalesOrder order = new SalesOrder();
      order.setSalesOrderId(salesOrder.getSalesOrderId());
      order.setVersion(bo.getVersion());
      order.setUrgent(true);
      order.setModifiedTime(LocalDateTime.now());
      validateChangedCount(dao.updateUrgent(order));
      BIZ_LOGGER.log(order.getSalesOrderId(), "加急");
      getMqProducer().send(
          new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
              EventType.MODIFIED)
      );
    }
  }

  /**
   * 设置取消订单加急.
   */
  @Override
  public void cancelUrgent(SalesOrder salesOrder, VersionBO bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    if (salesOrder.isUrgent()) {
      SalesOrder order = new SalesOrder();
      order.setSalesOrderId(salesOrder.getSalesOrderId());
      order.setVersion(bo.getVersion());
      order.setUrgent(false);
      order.setModifiedTime(LocalDateTime.now());
      validateChangedCount(dao.updateUrgent(order));
      BIZ_LOGGER.log(order.getSalesOrderId(), "取消加急");
      getMqProducer().send(
          new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
              EventType.MODIFIED)
      );
    }
  }

  /**
   * 设置订单人工处理.
   */
  @Override
  public void manual(SalesOrder salesOrder, SalesOrderManualBO bo) {
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    if (!salesOrder.isManual()) {
      SalesOrder order = new SalesOrder();
      order.setSalesOrderId(salesOrder.getSalesOrderId());
      order.setVersion(bo.getVersion());
      order.setManual(true);
      order.setModifiedTime(LocalDateTime.now());
      order.setManualUser(BizContext.getNickname());
      validateChangedCount(dao.updateManual(order));
      BIZ_LOGGER.log(order.getSalesOrderId(), BizLogger.UPDATE, bo.getReason());
      getMqProducer().send(
          new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
              EventType.MODIFIED)
      );
    }
  }

  /**
   * 设置取消订单人工处理.
   */
  @Override
  public void cancelManual(SalesOrder salesOrder, VersionBO bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    if (salesOrder.isManual()) {
      SalesOrder order = new SalesOrder();
      order.setSalesOrderId(salesOrder.getSalesOrderId());
      order.setVersion(bo.getVersion());
      order.setManual(false);
      order.setModifiedTime(LocalDateTime.now());
      order.setManualUser(null);
      validateChangedCount(dao.updateManual(order));
      BIZ_LOGGER.log(order.getSalesOrderId(), "取消人工处理");
      getMqProducer().send(
          new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
              EventType.MODIFIED)
      );
    }
  }

  /**
   * 重置订单.
   */
  @Override
  public void reset(SalesOrder salesOrder, VersionBO bo) {
    if (salesOrder.getStatus() == SalesOrderStatus.AUDITED_ABNORMAL) {
      throw new OmsException("异常订单不允许重置！");
    }
    SalesOrderUtil.mustBeforeDispatch(salesOrder);
    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setVersion(bo.getVersion());
    update.setStatus(SalesOrderStatus.CREATED);
    update.setOos(false);
    update.setManual(true);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrder.getDetails().forEach(x -> {
          if (x.isOos()) {
            x.setOos(false);
            salesOrderDetailService.modify(x);
          }
        });
        update(update);
      });
    } catch (OmsException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("销售单重置失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("销售单重置失败，堆栈信息：", e);
      throw new OmsException("销售单重置失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "重置订单");
  }

  /**
   * 重置订单明细.
   *
   * @param salesOrder 订单
   * @param bo 明细重置对象
   */
  @Override
  public void reset(SalesOrder salesOrder, SalesOrderResetDetailBO bo) {
    SalesOrderDetailQuery filter = new SalesOrderDetailQuery();
    filter.setSalesOrderDetailIds(bo.getSalesOrderDetailIds());
    List<SalesOrderDetail> details = salesOrderDetailService.list(filter);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.reset(salesOrder, details);
        resetStatus(salesOrder, bo, true);
      });
    } catch (Exception e) {
      LOGGER.error("销售单明细重置失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("销售单明细重置失败，堆栈信息：", e);
      throw new OmsException("销售单明细重置失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER
        .log(salesOrder.getSalesOrderId(), "重置明细", SalesOrderUtil.buildDetailMessage(details));
  }

  /**
   * 修改推荐仓库.
   */
  @Override
  public void suggestWarehouse(SalesOrder salesOrder, SuggestWarehouseBO bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    Assert.notNull(bo.getVirtualWarehouseId(), "仓库不能为空！");
    VirtualWarehouse warehouse = virtualWarehouseService.getByKey(bo.getVirtualWarehouseId());
    Assert.notNull(warehouse, SysExceptions.DATA_NOT_FOUND);
    Assert.notTrue(warehouse.isEnable(), new OmsException("仓库已禁用！"));
    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setVersion(bo.getVersion());
    update.setSuggestWarehouseId(warehouse.getWarehouseId());
    update.setSuggestWarehouseName(warehouse.getWarehouseName());
    update.setSuggestVirtualWarehouseId(warehouse.getVirtualWarehouseId());
    update.setSuggestVirtualWarehouseName(warehouse.getVirtualWarehouseName());

    //明细中的推荐仓库也要随订单中推荐仓库修改
    salesOrder.getDetails().forEach(detail -> {
      if (!detail.getSuggestVirtualWarehouseId().equals(bo.getVirtualWarehouseId())) {
        detail.setSuggestWarehouseId(warehouse.getWarehouseId());
        detail.setSuggestWarehouseName(warehouse.getWarehouseName());
        detail.setSuggestVirtualWarehouseId(warehouse.getVirtualWarehouseId());
        detail.setSuggestVirtualWarehouseName(warehouse.getVirtualWarehouseName());
      }
    });

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        stockOccupancyService.updateOccupancy(salesOrder.getSalesOrderId(),
            StockOccupancyType.SALES_ORDER, warehouse);
        salesOrderDetailService.batchModify(salesOrder.getDetails());
        update(update);
      });
    } catch (Exception e) {
      LOGGER.error("销售单修改推荐仓库失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("销售单修改推荐仓库失败，堆栈信息：", e);
      throw new OmsException("销售单修改推荐仓库失败");
    }
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.UPDATE, "修改推荐仓库：{}==>{}",
        salesOrder.getSuggestVirtualWarehouseName(), warehouse.getVirtualWarehouseName());
  }

  /**
   * 修改推荐快递.
   */
  @Override
  public void suggestExpress(SalesOrder salesOrder, SuggestExpressBO bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    SalesOrder update = new SalesOrder();
    update.setVersion(bo.getVersion());
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setSuggestExpressNo(bo.getExpressNo() == null ? "" : bo.getExpressNo());
    Express express = null;
    if (!Assert.isNull(bo.getExpressId())) {
      express = expressService.getByKey(bo.getExpressId());
      Assert.notTrue(express.isEnable(), new OmsException("快递已禁用！"));
      update.setSuggestExpressId(express.getExpressId());
      update.setSuggestExpressName(express.getExpressName());
    }
    dao.updateSuggestExpress(update);
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "修改推荐快递",
        "快递：{}，单号：{}", express != null ? express.getExpressName() : "无",
        bo.getExpressNo() != null ? bo.getExpressNo() : "无");
  }

  @Override
  public void matchProduct(SalesOrder salesOrder, VersionBO bo) {
    List<SalesOrderDetail> updateDetails = new ArrayList<>(salesOrder.getDetails().size());
    //铺货关系匹配异常的明细
    List<SalesOrderDetail> alertDetails = new ArrayList<>(salesOrder.getDetails().size());
    salesOrder.getDetails().stream().filter(x -> Assert.isTrue(x.isProductAbnormal()))
        .forEach(z -> {
          String mallProductId = z.getMallProductId();
          String mallProductOutCode = z.getMallProductOutCode();
          String mallSkuId = z.getMallSkuId();
          String mallSkuOutCode = z.getMallSkuOutCode();

          ProductMallMapping eg = new ProductMallMapping();
          eg.setStoreId(salesOrder.getStoreId());
          if (!Assert.isEmpty(mallSkuId)) {
            eg.setMallSkuId(mallSkuId);
          } else if (!Assert.isEmpty(mallSkuOutCode)) {
            eg.setMallSkuOutCode(mallSkuOutCode);
          } else if (!Assert.isEmpty(mallProductId)) {
            eg.setMallProductId(mallProductId);
          } else if (!Assert.isEmpty(mallProductOutCode)) {
            eg.setMallProductOutCode(mallProductOutCode);
          }
          ProductMallMapping productMallMapping = productMallMappingService.getByExample(eg);
          if (productMallMapping != null && productMallMapping.isMatched()) {
            Product product = productService.getProductByCode(productMallMapping.getProductCode());
            SalesOrderDetail updateDetail = new SalesOrderDetail();
            updateDetail.setSalesOrderDetailId(z.getSalesOrderDetailId());
            updateDetail.setProductAbnormal(false);
            updateDetail.setStatus(product.getProductType() == ProductType.PHYSICAL ?
                SalesOrderDetailStatus.WAITING : SalesOrderDetailStatus.DELIVERED);
            updateDetail.setProductType(product.getProductType());
            updateDetail.setProductId(productMallMapping.getProductId());
            updateDetail.setProductCode(productMallMapping.getProductCode());
            updateDetail.setProductName(productMallMapping.getProductName());
            updateDetail.setSkuId(productMallMapping.getSkuId());
            updateDetail.setSkuCode(productMallMapping.getSkuCode());
            updateDetail.setSkuName(productMallMapping.getSkuName());
            updateDetails.add(updateDetail);
          } else if (productMallMapping == null) {
            LOGGER.info("订单{}匹配异常商品失败，明细商品{},规格{}无铺货关系",
                salesOrder.getSalesOrderCode(), z.getProductName(), z.getSkuName());
            alertDetails.add(z);
          } else {
            LOGGER.info("订单{}匹配异常商品失败，铺货关系未关联", salesOrder.getSalesOrderCode());
            alertDetails.add(z);
          }
        });
    SalesOrder update = new SalesOrder();
    update.setVersion(bo.getVersion());
    update.setProductAbnormal(false);
    update.setSalesOrderId(salesOrder.getSalesOrderId());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        if (updateDetails.size() > 0) {
          salesOrderDetailService.batchModify(updateDetails);
          update(update);
        }
      });
    } catch (OmsException e) {
      throw e;
    } catch (Exception e) {
      LOGGER.error("匹配异常商品操作失败，销售单：{}，更新明细：{}",
          JsonUtil.toJson(salesOrder), JsonUtil.toJson(updateDetails));
      LOGGER.error("匹配异常商品操作失败，堆栈信息：", e);
      throw new OmsException("匹配异常商品操作失败");
    }

    if (alertDetails.size() > 0) {
      StringBuilder builder = new StringBuilder();
      alertDetails.forEach(x -> builder.append(x.getMallDetailId()).append(","));
      throw new OmsException("子订单号为:" + builder.toString() + "的明细无铺货关系或铺货关系未关联，请处理");
    }
    getMqProducer().send(
        new SalesOrderDetailChangeMessage(salesOrder.getSalesOrderId())
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "匹配异常商品", "成功");
  }

  /**
   * 添加赠品.
   */
  @Override
  public void addGift(SalesOrder salesOrder, SalesOrderAddDetailBO bo) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrder.getSalesOrderId());
    eg.setGift(true);
    List<SalesOrderDetail> gifts = salesOrderDetailService.listExample(eg);
    //已作废的赠品可以重新添加
    gifts.removeIf(x -> SalesOrderDetailStatus.INVALID.equals(x.getStatus()));
    //剔除订单中已有的赠品
    gifts.forEach(x -> bo.getDetails().removeIf(z -> x.getSkuId().equals(z.getSkuId())));
    if (bo.getDetails().size() == 0) {
      throw new OmsException("未选择赠品，或赠品已存在");
    }
    bo.getDetails().forEach(x -> {
      x.setActualAmount(0D);
      x.setDistributionPrice(0D);
      x.setSettlementAmount(0D);
      x.setSettlementPrice(0D);
      x.setDiscountAmount(0D);
    });
    addDetail(salesOrder, bo, true);
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "添加赠品",
        SalesOrderUtil.buildDetailMessage(bo.getDetails()));
  }

  /**
   * 替换赠品.
   */
  @Override
  public void replaceGift(SalesOrder salesOrder, SalesOrderReplaceDetailBO bo) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrder.getSalesOrderId());
    eg.setGift(true);
    eg.setSkuId(bo.getSourceDetail().getSkuId());
    eg.setStatus(SalesOrderDetailStatus.WAITING);
    SalesOrderDetail detail = salesOrderDetailService.getByExample(eg);
    if (detail == null) {
      throw new OmsException("订单不包含此赠品");
    }
    if (!Assert.isNull(bo.getSourceDetail().getQuantity())) {
      if (!detail.getQuantity().equals(bo.getSourceDetail().getQuantity())) {
        throw new OmsException("赠品数量不匹配");
      }
      if (bo.getTargetDetail().getQuantity() == null || bo.getTargetDetail().getQuantity() == 0) {
        throw new OmsException("替换赠品数量不能为0");
      }
    } else {
      bo.getSourceDetail().setQuantity(detail.getQuantity());
      bo.getTargetDetail().setQuantity(detail.getQuantity());
    }
    bo.setSourceDetail(detail);
    doReplaceDetail(salesOrder, bo);
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(),
        "替换赠品", "sku编码：{}，数量：{} ---> sku编码：{}，数量：{}",
        bo.getSourceDetail().getSkuCode(), bo.getSourceDetail().getQuantity(),
        bo.getTargetDetail().getSkuCode(), bo.getTargetDetail().getQuantity());
  }

  /**
   * 删除赠品.
   */
  @Override
  public void removeGift(SalesOrder salesOrder, SalesOrderDetailBO bo) {
    bo.getDetail().setSalesOrderId(salesOrder.getSalesOrderId());
    bo.getDetail().setGift(true);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.remove(bo.getDetail());
        stockOccupancyService
            .deleteOccupancy(salesOrder.getSalesOrderId(), bo.getDetail().getSalesOrderDetailId(),
                StockOccupancyType.SALES_ORDER);
        dao.updateVersion(salesOrder.getSalesOrderId(), bo.getVersion());
      });
    } catch (Exception e) {
      LOGGER.error("销售单删除赠品失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("销售单删除赠品失败，堆栈信息：", e);
      throw new OmsException("销售单删除赠品失败");
    }
    getMqProducer().send(
        new SalesOrderDetailChangeMessage(salesOrder.getSalesOrderId())
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "删除赠品", "{}，数量：{}", bo.getDetail().getSkuCode(),
        bo.getDetail().getQuantity());
  }

  @Override
  public List<SalesOrder> list(SalesOrderQuery filter) {
    return dao.listSubInfo(filter);
  }

  @Override
  public PageList<SalesOrder> listPage(SalesOrderQuery filter, int page, int pageSize) {
    return dao.listSubInfoPage(filter, page, pageSize);
  }

  @Override
  public SalesOrder getByKey(Long id) {
    return dao.getSubInfo(id);
  }

  /**
   * 修改收货人信息.
   */
  @Override
  public void modifyReceiverInfo(SalesOrder salesOrder, SalesOrderModifyReceiverInfoBO bo) {
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    Assert.notNull(bo.getContact(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notFalse(Assert.isEmpty(bo.getMobile()) && Assert.isEmpty(bo.getTelephone()),
        TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notNull(bo.getCountryId(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notNull(bo.getProvinceId(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notNull(bo.getCityId(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notNull(bo.getDistrictId(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    Assert.notNull(bo.getAddress(), TradeExceptions.SALES_ORDER_RECV_INFO_ABNORMAL);
    SalesOrder order = new SalesOrder();
    order.setSub(salesOrder.getSub());
    order.setSalesOrderId(salesOrder.getSalesOrderId());
    order.setStoreId(salesOrder.getStoreId());
    order.setVersion(bo.getVersion());
    order.setContact(bo.getEncryptContact());
    order.setMobile(bo.getEncryptMobile());
    order.setTelephone(bo.getEncryptTelephone());
    order.setCountryId(bo.getCountryId());
    order.setProvinceId(bo.getProvinceId());
    order.setCityId(bo.getCityId());
    order.setDistrictId(bo.getDistrictId());
    order.setCountryName(bo.getCountryName());
    order.setProvinceName(bo.getProvinceName());
    order.setCityName(bo.getCityName());
    order.setDistrictName(bo.getDistrictName());
    order.setAddress(bo.getAddress());
    //修改收货人信息需重新计算合单标记
    order.setMergeFlag(buildMergeFlag(order));
    update(order);
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "修改收货人信息",
        "{}/{}/{}，地址：{}/{}/{}/{}/{}", bo.getContact(), bo.getTelephone(),
        bo.getMobile(), bo.getCountryName(), bo.getProvinceName(), bo.getCityName(),
        bo.getDistrictName(), bo.getAddress());
    LOGGER.info("订单{}，交易号{}，修改收货人信息{},{},{},地址{}/{}/{}/{}/{}",
        salesOrder.getSalesOrderCode(), salesOrder.getTradeId(), bo.getContact(),
        bo.getTelephone(), bo.getMobile(), bo.getCountryName(),
        bo.getProvinceName(), bo.getCityName(), bo.getDistrictName(), bo.getAddress());
  }

  /**
   * 整单退款.
   */
  @Override
  public void refund(SalesOrder salesOrder, VersionBO bo) {
    SalesOrderUtil.mustBeforeDispatch(salesOrder);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.refund(salesOrder);
        stockOccupancyService
            .deleteOccupancy(salesOrder.getSalesOrderId(), StockOccupancyType.SALES_ORDER);
        SalesOrder update = new SalesOrder();
        update.setSalesOrderId(salesOrder.getSalesOrderId());
        update.setVersion(bo.getVersion());
        update.setRefundStatus(RefundStatus.ALL);
        update(update);
      });
    } catch (Exception e) {
      LOGGER.error("销售单整单退款失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("销售单整单退款失败，堆栈信息：", e);
      throw new OmsException("销售单整单退款失败");
    }
    for (SalesOrderDetail detail : salesOrder.getDetails()) {
      getMqProducer().send(
          new StockChangedMessage(salesOrder.getSalesOrderCode(), detail.getSkuId(),
              detail.getSuggestVirtualWarehouseId(), salesOrder.getStoreId(),
              BizContext.getNickname(), "整单退款"));
    }
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "整单退款");
  }

  /**
   * 明细退款.
   */
  @Override
  public void refund(SalesOrder salesOrder, SalesOrderDetailBO bo) {
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.refund(salesOrder, bo.getDetail());
        stockOccupancyService
            .deleteOccupancy(salesOrder.getSalesOrderId(), bo.getDetail().getSalesOrderDetailId(),
                StockOccupancyType.SALES_ORDER);
        resetStatus(salesOrder, bo);
      });
    } catch (Exception e) {
      LOGGER.error("订单明细退款失败，销售单：{}，明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(bo.getDetail()));
      LOGGER.error("订单明细退款失败，堆栈信息：", e);
      throw new OmsException("订单明细退款失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "明细退款", bo.getDetail().getSkuCode());
  }

  @Override
  public void refund(SalesOrder salesOrder, SalesOrderDetailBatchBO cancelBO) {
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.refund(salesOrder, cancelBO.getDetails());
        for (SalesOrderDetail detail : cancelBO.getDetails()) {
          stockOccupancyService
              .deleteOccupancy(salesOrder.getSalesOrderId(), detail.getSalesOrderDetailId(),
                  StockOccupancyType.SALES_ORDER);
        }

        resetStatus(salesOrder, cancelBO);
      });
    } catch (Exception e) {
      LOGGER.error("订单明细退款失败，销售单：{}，明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(cancelBO.getDetails()));
      LOGGER.error("订单明细退款失败，堆栈信息：", e);
      throw new OmsException("订单明细退款失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "明细退款",
        StringUtil.join(cancelBO.getDetails(), SalesOrderDetail::getSkuCode));
  }

  @Override
  public void cancelRefund(SalesOrder salesOrder, VersionBO bo) {
    Assert.notTrue(salesOrder.getRefundStatus() == RefundStatus.ALL,
        TradeExceptions.SALES_ORDER_NOT_ALLOW_ACTION);
    salesOrderDetailService.cancelRefund(salesOrder);
    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setVersion(bo.getVersion());
    update.setRefundStatus(RefundStatus.NONE);
    update(update);
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "取消整单退款");
  }

  @Override
  public void cancelRefund(SalesOrder salesOrder, SalesOrderDetailBO bo) {
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.cancelRefund(salesOrder, bo.getDetail());
        resetStatus(salesOrder, bo);
      });
    } catch (Exception e) {
      LOGGER.error("订单明细取消退款失败，销售单：{}，明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(bo.getDetail()));
      LOGGER.error("订单明细取消退款失败，堆栈信息：", e);
      throw new OmsException("订单明细取消退款失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "明细取消退款", bo.getDetail().getSkuCode());
  }

  @Override
  public void cancelRefund(SalesOrder salesOrder, SalesOrderDetailBatchBO detailBO) {
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.cancelRefund(salesOrder, detailBO.getDetails());
        resetStatus(salesOrder, detailBO);
      });
    } catch (Exception e) {
      LOGGER.error("订单明细取消退款失败，销售单：{}，明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(detailBO.getDetails()));
      LOGGER.error("订单明细取消退款失败，堆栈信息：", e);
      throw new OmsException("订单明细取消退款失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "明细取消退款",
        StringUtil.join(detailBO.getDetails(), SalesOrderDetail::getSkuCode));
  }

  @Override
  public void addDetail(SalesOrder salesOrder, SalesOrderAddDetailBO bo) {
    addDetail(salesOrder, bo, false);
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "添加明细",
        SalesOrderUtil.buildDetailMessage(bo.getDetails()));
  }

  /**
   * 添加明细.
   */
  private void addDetail(SalesOrder salesOrder, SalesOrderAddDetailBO bo, boolean isGift) {
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    for (SalesOrderDetail detail : bo.getDetails()) {
      detail.setSalesOrderId(salesOrder.getSalesOrderId());
      detail.setGift(isGift);
      detail.setNeedReturnGoods(false);
      detail.setSuggestWarehouseId(salesOrder.getSuggestWarehouseId());
      detail.setSuggestWarehouseName(salesOrder.getSuggestWarehouseName());
      detail.setSuggestVirtualWarehouseId(salesOrder.getSuggestVirtualWarehouseId());
      detail.setSuggestVirtualWarehouseName(salesOrder.getSuggestVirtualWarehouseName());
      detail.setStatus(SalesOrderDetailStatus.WAITING);
      detail.setMallSalesOrderDetailStatus(MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS);
    }

    parseCombination(salesOrder, bo.getDetails());
    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.batchCreate(bo.getDetails());
        List<StockOccupancy> stockOccupancies =
            SalesOrderUtil.buildOccupancy(salesOrder, bo.getDetails(), null);
        stockOccupancyService.batchCreate(stockOccupancies);
        resetStatus(salesOrder, bo);
      });
    } catch (Exception e) {
      LOGGER.error("订单添加明细失败，销售单：{}，明细：{}",
          JsonUtil.toJson(salesOrder), JsonUtil.toJson(bo.getDetails()));
      LOGGER.error("订单添加明细失败，堆栈信息：", e);
      throw new OmsException("订单添加明细失败");
    }
    bo.getDetails().forEach(detail -> getMqProducer().send(
        new StockChangedMessage(salesOrder.getSalesOrderCode(), detail.getSkuId(),
            salesOrder.getStoreId(), BizContext.getNickname(), "添加明细")));
    getMqProducer().send(new SalesOrderDetailChangeMessage(salesOrder.getSalesOrderId()));
  }

  @Override
  public void replaceDetail(SalesOrder salesOrder, SalesOrderReplaceDetailBO bo,
      boolean isBatchReplace) {
    SalesOrderDetail sourceDetail = bo.getSourceDetail();
    SalesOrderDetail targetDetail = bo.getTargetDetail();
    if (isBatchReplace) {
      SalesOrderDetail eg = new SalesOrderDetail();
      eg.setSalesOrderId(salesOrder.getSalesOrderId());
      eg.setGift(false);
      eg.setDeleted(false);
      eg.setSkuId(sourceDetail.getSkuId());
      eg.setStatus(SalesOrderDetailStatus.WAITING);
      eg.setQuantity(sourceDetail.getQuantity());
      SalesOrderDetail detail = salesOrderDetailService.getByExample(eg);
      if (detail == null) {
        throw new OmsException(String.format("订单中无数量为%s的明细%s", sourceDetail.getQuantity(),
            sourceDetail.getSkuCode()));
      }
      bo.setSourceDetail(detail);
      targetDetail.setQuantity(detail.getQuantity());
    }
    doReplaceDetail(salesOrder, bo);
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "明细替换",
        "sku编号：{}，数量：{}--->sku编号：{}，数量：{}，替换方式：{}",
        sourceDetail.getSkuCode(), sourceDetail.getQuantity(),
        targetDetail.getSkuCode(), targetDetail.getQuantity(),
        isBatchReplace ? "批量" : "单条");
  }

  private void doReplaceDetail(SalesOrder salesOrder, SalesOrderReplaceDetailBO bo) {
    SalesOrderDetail sourceDetail = bo.getSourceDetail();
    SalesOrderDetail targetDetail = bo.getTargetDetail();
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    SalesOrderUtil.mustBeforeDispatch(sourceDetail);

    targetDetail.setSalesOrderId(sourceDetail.getSalesOrderId());
    targetDetail.setActivityId(sourceDetail.getActivityId());
    targetDetail.setPresellId(sourceDetail.getPresellId());
    targetDetail.setPresellDeliveryDate(sourceDetail.getPresellDeliveryDate());
    targetDetail.setSuggestVirtualWarehouseId(sourceDetail.getSuggestVirtualWarehouseId());
    targetDetail.setSuggestVirtualWarehouseName(sourceDetail.getSuggestVirtualWarehouseName());
    targetDetail.setSuggestWarehouseId(sourceDetail.getSuggestWarehouseId());
    targetDetail.setSuggestWarehouseName(sourceDetail.getSuggestWarehouseName());
    targetDetail.setSellingPrice(sourceDetail.getSellingPrice());
    targetDetail.setSellingAmount(sourceDetail.getSellingAmount());
    targetDetail.setActualSellingPrice(sourceDetail.getActualSellingPrice());
    targetDetail.setActualAmount(sourceDetail.getActualAmount());
    targetDetail.setDiscountAmount(sourceDetail.getDiscountAmount());
    targetDetail.setDistributionPrice(sourceDetail.getDistributionPrice());
    targetDetail.setDistributionAmount(sourceDetail.getDistributionAmount());
    targetDetail.setSettlementPrice(sourceDetail.getSettlementPrice());
    targetDetail.setSettlementAmount(sourceDetail.getSettlementAmount());
    targetDetail.setMallDetailId(sourceDetail.getMallDetailId());
    targetDetail.setMallPresellDeliveryRequiring(sourceDetail.getMallPresellDeliveryRequiring());
    targetDetail.setMallSalesOrderDetailStatus(sourceDetail.getMallSalesOrderDetailStatus());
    targetDetail.setMallProductId(sourceDetail.getMallProductId());
    targetDetail.setMallProductOutCode(sourceDetail.getMallProductOutCode());
    targetDetail.setMallProductName(sourceDetail.getMallProductName());
    targetDetail.setMallSkuId(sourceDetail.getMallSkuId());
    targetDetail.setMallSkuOutCode(sourceDetail.getMallSkuOutCode());
    targetDetail.setMallSkuName(sourceDetail.getMallSkuName());
    targetDetail.setPresellPriority(sourceDetail.isPresellPriority());
    targetDetail.setGiftStrategyId(sourceDetail.getGiftStrategyId());
    targetDetail.setGiftStrategyName(sourceDetail.getGiftStrategyName());
    targetDetail.setGift(sourceDetail.isGift());
    targetDetail.setManualAdd(true);

    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setSalesOrderCode(salesOrder.getSalesOrderCode());
    update.setVersion(bo.getVersion());
    update.setQuantity(salesOrder.getQuantity() - sourceDetail.getQuantity());

    List<SalesOrderDetail> targetDetails = new ArrayList<>(4);
    targetDetails.add(targetDetail);
    parseCombination(update, targetDetails);

    //原始明细不创建占用
    List<SalesOrderDetail> occupancyTargetDetails = targetDetails.stream()
        .filter(x -> x.getSalesOrderDetailType() == SalesOrderDetailType.NORMAL)
        .collect(Collectors.toList());

    Store store = storeService.getByKey(salesOrder.getStoreId());
    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        if (Assert.isTrue(sourceDetail.isCombinationDetail())
            && Assert.isTrue(sourceDetail.isPrepackage())) {
          List<SalesOrderDetail> updateDetails = salesOrder.getDetails().stream()
              .filter(x -> x.getOriginalDetailid() != null
                  && x.getOriginalDetailid().equals(sourceDetail.getOriginalDetailid()))
              .map(c -> {
                SalesOrderDetail detail = new SalesOrderDetail();
                detail.setSalesOrderDetailId(c.getSalesOrderDetailId());
                detail.setPrepackage(false);
                return detail;
              }).collect(Collectors.toList());
          salesOrderDetailService.batchModify(updateDetails);
        }
        salesOrderDetailService.replace(salesOrder, sourceDetail, targetDetails);
        //重建占用
        stockOccupancyService.deleteOccupancy(salesOrder.getSalesOrderId(),
            sourceDetail.getSalesOrderDetailId(), StockOccupancyType.SALES_ORDER);
        List<StockOccupancy> stockOccupancies = SalesOrderUtil
            .buildOccupancy(salesOrder, occupancyTargetDetails, store);
        stockOccupancyService.batchCreate(stockOccupancies);

        update(update);
      });
    } catch (Exception e) {
      LOGGER.error("订单替换明细失败，销售单：{}，原始明细：{}，替换明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(sourceDetail), JsonUtil.toJson(targetDetail));
      LOGGER.error("订单替换明细失败，堆栈信息：", e);
      throw new OmsException("订单替换明细失败");
    }
    List<Message> messages = new ArrayList<>(8);
    messages.add(new StockChangedMessage(salesOrder.getSalesOrderCode(),
        sourceDetail.getSkuId(), salesOrder.getStoreId(), BizContext.getNickname(),
        "替换明细"));
    occupancyTargetDetails.forEach(x -> messages.add(
        new StockChangedMessage(salesOrder.getSalesOrderCode(), x.getSkuId(),
            BizContext.getNickname(), "替换明细")));
    messages.add(new SalesOrderDetailChangeMessage(salesOrder.getSalesOrderId()));
    getMqProducer().send(messages);
  }

  /**
   * 明细申请退款.
   */
  @Override
  public void applyRefund(SalesOrder salesOrder, SalesOrderDetailBO bo) {
    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.applyRefund(salesOrder, bo.getDetail());
        resetStatus(salesOrder, bo);
      });
    } catch (Exception e) {
      LOGGER.error("订单明细申请退款失败，销售单：{}，明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(bo.getDetail()));
      LOGGER.error("订单明细申请退款失败，堆栈信息：", e);
      throw new OmsException("订单明细申请退款失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "明细申请退款", bo.getDetail().getSkuCode());
  }

  @Override
  public void applyRefund(SalesOrder salesOrder, SalesOrderDetailBatchBO detailBO) {
    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.applyRefund(salesOrder, detailBO.getDetails());
        resetStatus(salesOrder, detailBO);
      });
    } catch (Exception e) {
      LOGGER.error("订单明细申请退款失败，销售单：{}，明细：{}", JsonUtil.toJson(salesOrder),
          JsonUtil.toJson(detailBO.getDetails()));
      LOGGER.error("订单明细申请退款失败，堆栈信息：", e);
      throw new OmsException("订单明细申请退款失败");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "明细申请退款",
        StringUtil.join(detailBO.getDetails(), SalesOrderDetail::getSkuCode));
  }

  @Override
  public List<ManualDispatchDetailBO> checkDispatchStock(ManualDispatchBO bo) {
    SalesOrderSetting salesOrderSetting = settingService.getSalesOrderSetting();
    List<ManualDispatchDetailBO> list = new ArrayList<>();
    HashMap<String, Integer> quantityMap = new HashMap<>();
    for (ManualDispatchDetailBO detail : bo.getDetails()) {
      String key = detail.getSkuId() + "" + detail.getVirtualWarehouseId();
      StockCheckResultBO result =
          stockService.getStockCheckResult(detail.getSkuId(), detail.getVirtualWarehouseId(),
              detail.getPaidTime());
      if (result != null) {
        Integer countQuantity;
        if (salesOrderSetting.isManualPrepareFirst()) {
          //订单配置为手工配货抢占库存，占用数 = 包含订单的占用数
          countQuantity = result.getLessLockQuantity();
        } else {
          //非手工配货抢占库存时，占用数 = 不含订单的占用数
          countQuantity = result.getLockQuantity();
        }
        if (!quantityMap.containsKey(key)) {
          quantityMap.put(key, result.getQuantity() - countQuantity);
        }
        if (result.getQuantity() < countQuantity) {
          list.add(detail);
          continue;
        }
        quantityMap.put(key, quantityMap.get(key) - detail.getQuantity());
        if (quantityMap.get(key) < 0) {
          list.add(detail);
        }
      } else {
        list.add(detail);
      }
    }
    return list;
  }

  /**
   * 订单发货状态回传平台.
   */
  @Override
  public void mallDelivery(SalesOrder salesOrder, MallDeliveryBO bo) {
    Store store = storeService.getByKey(salesOrder.getStoreId());
    List<SalesOrderDetail> details = salesOrder.getDetails();
    OrderDeliveryRequest request = new OrderDeliveryRequest(store);
    request.setSalesOrder(salesOrder);
    OrderDeliveryResponse response;
    if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXPENSE) {
      //虚拟发货
      List<OrderDeliveryDetail> virtualDeliveryDetails = new ArrayList<>(details.size());
      details.forEach(detail -> {
        //封装发货明细
        OrderDeliveryRequest.OrderDeliveryDetail deliveryDetail = new OrderDeliveryDetail();
        deliveryDetail.setSalesOrderDetail(detail);
        virtualDeliveryDetails.add(deliveryDetail);
      });
      request.setDetails(virtualDeliveryDetails);
      OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
      response = orderBridge.orderDelivery(request);
    } else {
      //仓库发货
      List<OrderDeliveryDetail> physicalDeliveryDetails = new ArrayList<>(details.size());
      //找到发货记录，然后根据发货记录拿快递信息
      DispatchOrderDelivery eg = new DispatchOrderDelivery();
      eg.setSalesOrderDetailId(details.get(0).getSalesOrderDetailId());
      //发货明细
      DispatchOrderDelivery delivery = dispatchOrderDeliveryService.getByExample(eg);
      if (delivery == null) {
        details.forEach(detail ->
            BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY,
                "平台发货的明细无发货记录，可能是代发商品，明细sku信息：{}，{}",
                detail.getSkuCode(), detail.getSkuName()));
        return;
      }
      Express express = expressService.getByKey(delivery.getExpressId());
      GlobalExpressMallMapping mapping = globalExpressMallMappingService
          .getMallExpressMapping(express.getGlobalExpressId(), store.getMallType());
      for (SalesOrderDetail salesOrderDetail : details) {
        //校验明细：系统已发货但平台未发货的订单明细才能平台发货
        if (salesOrderDetail.getMallSalesOrderDetailStatus()
            == MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS
            && salesOrderDetail.getStatus() == SalesOrderDetailStatus.DELIVERED) {
          //封装发货明细
          OrderDeliveryRequest.OrderDeliveryDetail detail = new OrderDeliveryDetail();
          detail.setSalesOrderDetail(salesOrderDetail);
          detail.setMallExpressName(express.getGlobalExpressName());
          detail.setMallExpressCode(mapping.getOuterCode());
          detail.setExpressNo(delivery.getExpressNo());
          physicalDeliveryDetails.add(detail);
          salesOrderDetail.setExpressNo(express.getExpressName());
          salesOrderDetail.setExpressName(delivery.getExpressNo());
        } else {
          LOGGER.error("平台发货明细异常，订单编号：{}，明细信息：{}",
              salesOrder.getSalesOrderCode(), JsonUtil.toJson(salesOrderDetail));
        }
      }
      request.setDetails(physicalDeliveryDetails);
      request.setMallExpressId(mapping.getOuterId());
      request.setMallExpressCode(mapping.getOuterCode());
      request.setMallExpressName(mapping.getOuterName());
      request.setExpressNo(delivery.getExpressNo());
      request.setPartDelivery(salesOrder.getDeliveryStatus() == DeliveryStatus.PART);

      OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
      if (bo.isExpressNoUpdated()) {
        response = orderBridge.orderResetExpress(request);
      } else {
        response = orderBridge.orderDelivery(request);
      }
    }

    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setVersion(bo.getVersion());
    if (response.isSuccess() || "delivered".equals(response.getResult())) {
      if (salesOrder.getStatus() == SalesOrderStatus.DELIVERY_ABNORMAL) {
        update.setStatus(SalesOrderStatus.DELIVERED);
      }
      List<SalesOrderDetail> updateDetails = new ArrayList<>(details.size());
      details.forEach(x -> {
        SalesOrderDetail updateDetail = new SalesOrderDetail();
        updateDetail.setSalesOrderDetailId(x.getSalesOrderDetailId());
        updateDetail.setExpressName(x.getExpressName());
        updateDetail.setExpressNo(x.getExpressNo());
        updateDetail
            .setMallSalesOrderDetailStatus(MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS);
        updateDetails.add(updateDetail);
      });

      //编程式事务
      try {
        getTransactionTemplate().execute(() -> {
          salesOrderDetailService.batchModify(updateDetails);
          update.setMallSalesOrderStatus(MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS);
          update(update);
        });
      } catch (Exception e) {
        if (bo.isExpressNoUpdated()) {
          BIZ_LOGGER
              .log(salesOrder.getSalesOrderId(), BizLogger.MALL_RESET_EXPRESS,
                  "修改平台快递信息成功，更新订单状态失败");
          LOGGER.error("修改平台快递信息成功，更新订单状态失败，销售单：{}", JsonUtil.toJson(salesOrder));
          LOGGER.error("修改平台快递信息成功，更新订单状态失败，堆栈信息：", e);
        } else if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXCHANGE
            && details.stream().noneMatch(x -> x.getExchangeId() != null)) {
          BIZ_LOGGER
              .log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY, "明细无换货id的换货订单不进行平台发货");
        } else {
          BIZ_LOGGER
              .log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY, "回传平台发货成功，更新订单状态失败");
          LOGGER.error("订单发货上传到平台失败，销售单：{}", JsonUtil.toJson(salesOrder));
          LOGGER.error("订单发货上传到平台失败，堆栈信息：", e);
        }
        return;
      }
      if (bo.isExpressNoUpdated()) {
        BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_RESET_EXPRESS, "修改平台快递信息成功");
      } else if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXCHANGE
          && details.stream().noneMatch(x -> x.getExchangeId() != null)) {
        BIZ_LOGGER
            .log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY, "明细无换货id的换货订单不进行平台发货");
      } else {
        BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY, "平台发货成功");
      }
    } else if (response.getResult().contains("Connection timed out")) {
      getMqProducer().send(
          new SalesOrderDeliveryFailMessage(salesOrder.getSalesOrderId(), bo.isExpressNoUpdated()));
    } else {
      update.setStatus(SalesOrderStatus.DELIVERY_ABNORMAL);
      update(update);
      if (bo.isExpressNoUpdated()) {
        BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_RESET_EXPRESS,
            "修改平台快递信息失败：{}", response.getResult());
      } else {
        BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY,
            "平台发货失败：{}", response.getResult());
      }
    }
    getMqProducer().send(new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
        EventType.DELIVERED));
  }

  /**
   * 仅回传平台物流通知（非发货）.
   *
   * @param salesOrder salesOrder中明细即为该订单下进行物流通知的明细（可能是整单或拆单）.
   * @param bo 包含物流通知所需的物流信息.
   */
  @Override
  public void mallExpressNotice(SalesOrder salesOrder, SalesOrderExpressNoticeBo bo) {
    String expressNo = bo.getExpressNo();
    String mallExpressId = bo.getMallExpressId();
    String mallExpressCode = bo.getMallExpressCode();
    String mallExpressName = bo.getMallExpressName();
    String expressName = bo.getExpressName();
    List<SalesOrderDetail> details = salesOrder.getDetails();
    Store store = storeService.getByKey(salesOrder.getStoreId());

    //封装请求
    OrderDeliveryRequest request = new OrderDeliveryRequest(store);
    request.setSalesOrder(salesOrder);
    request.setMallExpressId(mallExpressId);
    request.setMallExpressCode(mallExpressCode);
    request.setMallExpressName(mallExpressName);
    request.setExpressNo(expressNo);
    request.setPartDelivery(bo.isSplit());
    if (bo.isSplit()) {
      List<OrderDeliveryDetail> deliveryDetails = new ArrayList<>(details.size());
      for (SalesOrderDetail noticeDetail : details) {
        OrderDeliveryDetail deliveryDetail = new OrderDeliveryDetail();
        deliveryDetail.setExpressNo(expressNo);
        deliveryDetail.setMallExpressCode(mallExpressCode);
        deliveryDetail.setMallExpressName(mallExpressName);
        deliveryDetail.setSalesOrderDetail(noticeDetail);
        deliveryDetails.add(deliveryDetail);
      }
      request.setDetails(deliveryDetails);
    }

    //调用接口
    OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
    OrderDeliveryResponse response = orderBridge.orderDelivery(request);
    if (response.isSuccess()) {
      SalesOrder update = new SalesOrder();
      update.setSalesOrderId(salesOrder.getSalesOrderId());
      update.setMallSalesOrderStatus(MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS);
      update.setLastDeliveryTime(LocalDateTime.now());
      update.setLastExpressName(expressName);
      update.setLastExpressNo(expressNo);
      update.setVersion(bo.getVersion());

      List<SalesOrderDetail> updateDetails = new ArrayList<>(details.size());
      details.forEach(x -> {
        if (x.getMallSalesOrderDetailStatus()
            == MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS) {
          SalesOrderDetail updateDetail = new SalesOrderDetail();
          updateDetail.setSalesOrderDetailId(x.getSalesOrderDetailId());
          updateDetail
              .setMallSalesOrderDetailStatus(MallSalesOrderDetailStatus.WAIT_BUYER_CONFIRM_GOODS);
          updateDetail.setExpressName(expressName);
          updateDetail.setExpressNo(expressNo);
          updateDetails.add(updateDetail);
        } else {
          LOGGER.error("物流通知平台发货明细异常，订单编号：{}，明细信息：{}",
              salesOrder.getSalesOrderCode(), JsonUtil.toJson(x));
        }
      });

      try {
        getTransactionTemplate().execute(() -> {
          salesOrderDetailService.batchModify(updateDetails);
          update(update);
        });
      } catch (Exception e) {
        BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "物流通知回传平台成功，更新订单状态失败");
        LOGGER.error("物流通知回传平台失败，销售单：{}", JsonUtil.toJson(salesOrder));
        LOGGER.error("物流通知回传平台失败，堆栈信息：", e);
        return;
      }
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY, "平台物流通知成功");
      LOGGER.info("交易号：{}的订单{}平台物流通知成功", salesOrder.getTradeId(), salesOrder.getSalesOrderCode());
    } else {
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_DELIVERY,
          "平台物流通知失败:" + response.getResult());
    }
  }

  /**
   * 仓库发货.
   */
  @Override
  public void wmsDelivery(SalesOrder salesOrder, SalesOrderWmsDeliveryBO bo) {
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    DispatchOrderDelivery firstDeliveryDetail = bo.getDeliveries().get(0);

    SalesOrder updateInfo = new SalesOrder();
    updateInfo.setSalesOrderId(salesOrder.getSalesOrderId());
    updateInfo.setLastDeliveryTime(bo.getDispatchOrder().getLastDeliveryTime());
    updateInfo.setLastExpressName(firstDeliveryDetail.getExpressName());
    updateInfo.setLastExpressNo(firstDeliveryDetail.getExpressNo());
    updateInfo.setVersion(bo.getVersion());
    if (salesOrder.getFirstDeliveryTime() == null) {
      updateInfo.setFirstDeliveryTime(bo.getDispatchOrder().getLastDeliveryTime());
    }
    //此订单中发货的明细id
    List<Long> detailIds = bo.getDeliveries().stream()
        .map(DispatchOrderDelivery::getSalesOrderDetailId)
        .collect(Collectors.toList());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.wmsDelivery(salesOrder, detailIds);
        detailIds.forEach(id -> stockOccupancyService
            .deleteOccupancy(salesOrder.getSalesOrderId(), id, StockOccupancyType.SALES_ORDER));
        List<SalesOrderDetail> simpleDetails =
            salesOrderDetailService.listSimpleNormal(salesOrder.getSalesOrderId());
        SalesOrderUtil.recountStatus(updateInfo, simpleDetails);
        update(updateInfo);
      });
    } catch (Exception e) {
      LOGGER.error("订单仓库发货失败，销售单：{}，配货单：{}，配货单明细：{}，发货信息：{}", JsonUtil.toJson(salesOrder),
          bo.getDispatchOrder(), bo.getDetails(), bo.getDeliveries());
      LOGGER.error("订单仓库发货失败，堆栈信息：", e);
      throw new OmsException("订单仓库发货失败");
    }
    this.getMqProducer().send(new SalesOrderDeliveryMessage(salesOrder.getSalesOrderId(),
        bo.isExpressNoUpdated()));
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.DELIVERED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), "仓库发货", "快递公司：{}，单号：{}",
        firstDeliveryDetail.getExpressName(), firstDeliveryDetail.getExpressNo());
  }

  /**
   * 订单导入
   */
  @Override
  public void importSalesOrder(SalesOrderImportBO bo) {
    Map<String, MallSalesOrderInfo> orderMap = new HashMap<>(bo.getList().size());
    Map<MallSalesOrderInfo, Map<String, MallSalesOrderDetailInfo>> orderDetailMap
        = new HashMap<>(orderMap.size());
    Map<String, MallSalesOrderDetailInfo> detailMap;
    MallSalesOrderInfo order;
    MallSalesOrderDetailInfo detail;
    Store store = storeService.getByKey(bo.getStoreId());
    for (Map<String, String> map : bo.getList()) {
      String tradeId = map.get("交易号");
      String oid = !Assert.isEmpty(map.get("子订单号")) ?
          map.get("子订单号") : String.valueOf(salesOrderDetailService.getIdGenerator().next());
      if (orderMap.containsKey(tradeId)) {
        order = orderMap.get(tradeId);
      } else {
        order = new MallSalesOrderInfo();
        //必填项
        order.setStatus(bo.isNeedDelivery() ? MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS
            : MallSalesOrderStatus.TRADE_FINISHED);
        order.setTradeId(tradeId);
        order.setBuyerNick(map.get("会员昵称"));
        order.setContact(map.get("收货人"));
        order.setTelephone(map.get("电话"));
        order.setMobile(map.get("手机"));
        order.setProvince(map.get("省"));
        order.setCity(map.get("市"));
        order.setDistrict(map.get("区"));
        order.setAddress(map.get("地址"));
        if (!Assert.isEmpty(map.get("下单时间"))) {
          order.setCreatedTime(DateTimeUtil.parserLocalDateTime(map.get("下单时间")));
        }
        if (!Assert.isEmpty(map.get("支付时间"))) {
          order.setPaidTime(DateTimeUtil.parserLocalDateTime(map.get("支付时间")));
        }

        //选填项
        order.setCountry(map.get("国家"));
        if (!Assert.isEmpty(map.get("修改时间"))) {
          order.setModifiedTime(DateTimeUtil.parserLocalDateTime(map.get("修改时间")));
        } else {
          order.setModifiedTime(order.getPaidTime());
        }
        if (!Assert.isEmpty(map.get("完成时间"))) {
          order.setFinishedTime(DateTimeUtil.parserLocalDateTime(map.get("完成时间")));
        }
        order.setBuyerMemo(map.get("买家备注"));
        order.setSellerMemo(map.get("卖家备注"));
        if (!Assert.isEmpty(map.get("运费险"))) {
          order.setFreightRiskFee(Double.parseDouble(map.get("运费险")));
        }
        if (!Assert.isEmpty(map.get("邮费"))) {
          order.setExpressFee(Double.parseDouble(map.get("邮费")));
        }
        order.setDistributionTradeId(map.get("分销单号"));
        order.setExpressCode(map.get("建议快递编码"));
        order.setExpressNo(map.get("建议快递单号"));
        order.setCod("是".equals(map.get("到付")));
        if (!Assert.isEmpty(map.get("到付金额"))) {
          order.setCodAmount(Double.parseDouble(map.get("到付金额")));
        }

        //默认项
        order.setCreator(BizContext.getNickname());
        order.setCreateType(SalesOrderCreateType.IMPORT);
        order.setSourceType(SourceType.OTHER);
        order.setOrderType(bo.getOrderType());
        order.setExchangeOrder(false);
        order.setThirdDelivery(false);
        order.setSettlementAmount(0D);
        order.setActualAmount(0D);
        order.setSellingAmount(0D);
        order.setDistributionAmount(0D);

        //发票选填部分
        order.setHasInvoice("是".equals(map.get("发票")));
        if (order.isHasInvoice()) {
          MallSalesOrderInvoiceInfo invoiceInfo = new MallSalesOrderInvoiceInfo(map.get("发票抬头"),
              map.get("发票内容"), order.getActualAmount());
          invoiceInfo.setInvoiceType("增票".equals(map.get("发票类型")) ?
              InvoiceType.VALUE_ADD : InvoiceType.NORMAL);
          invoiceInfo.setTaxpayerId(map.get("纳税人识别号"));
          invoiceInfo.setGmfAddress(map.get("买方地址"));
          invoiceInfo.setGmfBankName(map.get("买方银行"));
          invoiceInfo.setGmfBankNo(map.get("买方银行帐号"));
          invoiceInfo.setGmfMobile(map.get("买方电话"));
          order.setInvoice(invoiceInfo);
        }
        order.setDetails(new ArrayList<>(4));
        orderMap.put(tradeId, order);
        orderDetailMap.put(order, new HashMap<>(10));

        //支付信息
        List<MallSalesOrderPaymentInfo> payments = new ArrayList<>(4);
        if (order.getExpressFee() != null && order.getExpressFee() > 0) {
          MallSalesOrderPaymentInfo paymentInfo =
              new MallSalesOrderPaymentInfo(order.getExpressFee(),
                  store.getSetting().getDefaultPayType(), order.getPaidTime());
          payments.add(paymentInfo);
        }
        order.setPayments(payments);
      }
      detailMap = orderDetailMap.get(order);
      if (!detailMap.containsKey(oid)) {
        detail = new MallSalesOrderDetailInfo();
        //必填项
        detail.setStatus(bo.isNeedDelivery() ? MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS
            : MallSalesOrderDetailStatus.TRADE_FINISHED);
        detail.setOuterSkuCode(map.get("规格编码"));
        detail.setQuantity(Integer.parseInt(map.get("数量")));
        detail.setActualSellingPrice(Double.parseDouble(map.get("成交价")));
        detail.setSettlementAmount(Double.parseDouble(map.get("结算金额")));
        detail.setDeliveried(!bo.isNeedDelivery());
        //选填项
        detail.setOid(oid);
        if (!Assert.isEmpty(map.get("分销价"))) {
          detail.setDistributionPrice(Double.parseDouble(map.get("分销价")));
        }
        if (!Assert.isEmpty(map.get("分销金额"))) {
          detail.setDistributionAmount(Double.parseDouble(map.get("分销金额")));
          order.setDistributionAmount(order.getDistributionAmount() + detail.getDiscountAmount());
        }

        //计算金额
        detail.setActualSellingAmount(
            MathUtil.multiply(detail.getActualSellingPrice(), detail.getQuantity()));
        detail.setDiscountAmount(detail.getActualSellingAmount() - detail.getSettlementAmount());
        order.setActualAmount(order.getActualAmount() + detail.getActualSellingAmount());
        order.setSettlementAmount(order.getSettlementAmount() + detail.getSettlementAmount());
        ProductSku sku = productSkuService.getEffectiveByCode(detail.getOuterSkuCode());
        if (sku != null) {
          detail.setSellingPrice(sku.getSellingPrice());
          detail
              .setSellingAmount(MathUtil.multiply(detail.getSellingPrice(), detail.getQuantity()));
          order.setSellingAmount(order.getSellingAmount() + detail.getSellingAmount());
        } else {
          detail.setSellingPrice(0D);
          detail.setSellingAmount(0D);
        }
        //支付信息
        MallSalesOrderPaymentInfo paymentInfo =
            new MallSalesOrderPaymentInfo(order.getSettlementAmount(),
                store.getSetting().getDefaultPayType(), order.getPaidTime());
        order.getPayments().add(paymentInfo);
        order.getDetails().add(detail);
        detailMap.put(oid, detail);
      }
    }
    mallSalesOrderService.importSalesOrder(store, orderMap.values());
  }

  @Override
  public void export(String name, SalesOrderQuery filter) {
    //todo 导出订单
  }

  @Override
  public SalesOrder getByTradeId(Long storeId, String tradeId) {
    SalesOrderQuery query = new SalesOrderQuery();
    query.setStoreId(storeId);
    query.setTradeId(tradeId);
    final List<SalesOrder> salesOrders = dao.listSubInfo(query);
    return Assert.isEmpty(salesOrders) ? null : salesOrders.get(0);
  }

  /**
   * 订单完成.
   */
  @Override
  public void mallFinish(SalesOrder salesOrder, MallFinishBO finishBO) {
    SalesOrder updateOrder = new SalesOrder();
    updateOrder.setSalesOrderId(salesOrder.getSalesOrderId());
    updateOrder.setMallSalesOrderStatus(MallSalesOrderStatus.TRADE_FINISHED);
    updateOrder.setVersion(finishBO.getVersion());
    SalesOrderSub updateSub = new SalesOrderSub();
    updateSub.setSalesOrderId(salesOrder.getSalesOrderId());
    updateSub.setMallFinishedTime(finishBO.getFinishTime());

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderSubService.modify(updateSub);
        update(updateOrder);
      });
    } catch (Exception e) {
      LOGGER.error("订单完成修改状态失败，销售单：{}，完成时间：{}", JsonUtil.toJson(salesOrder),
          finishBO.getFinishTime().toString());
      LOGGER.error("订单完成修改状态失败，堆栈信息：", e);
      throw new OmsException("订单完成修改状态失败");
    }
  }

  @Override
  public List<Long> listMergeOrderId(String mergeFlag) {
    return dao.listMergeOrderId(mergeFlag);
  }

  /**
   * 订单配货后操作.
   *
   * 逻辑：
   * 1.更改明细状态和占用
   * 2.根据明细状态来更改订单状态
   *
   * 注意：
   * 1.调用此方法外部需要try-catch。
   * 2.此方法可能加入外层事务，不要在这个方法内发送消息。
   */
  @Override
  public void dispatch(SalesOrder salesOrder, SalesOrderDispatchBO bo) {
    //编程式事务
    salesOrder.setVersion(bo.getVersion());
    getTransactionTemplate().execute(() -> {
      salesOrderDetailService.dispatch(bo.getDetails());
      for (SalesOrderDetail detail : bo.getDetails()) {
        stockOccupancyService
            .updateOccupancy(detail.getSalesOrderId(), detail.getSalesOrderDetailId(),
                StockOccupancyType.SALES_ORDER, StockOccupancyStatus.NONE);
      }
      List<SalesOrderDetail> simpleDetails = salesOrderDetailService
          .listSimpleNormal(salesOrder.getSalesOrderId());

      SalesOrderUtil.recountStatus(salesOrder, simpleDetails);
      if (salesOrder.getDispatchStatus() == DispatchStatus.PART) {
        //如果是部分配货，且拆单后自动配货则非手工处理
        Boolean isAutoSplit = settingService.getSalesOrderSetting().getAutoPrepareOnSplit();
        salesOrder.setManual(!isAutoSplit);
      } else {
        if (Assert.isTrue(salesOrder.isOos())) {
          salesOrder.setOos(false);
        }
      }
      update(salesOrder);
    });
  }

  /**
   * 取消配货.
   */
  @Override
  public void cancelDispatch(SalesOrder salesOrder, SalesOrderCancelDispatchBO bo) {
    salesOrderDetailService.cancelDispatch(salesOrder, bo.getDetails());
    for (SalesOrderDetail detail : bo.getDetails()) {
      stockOccupancyService
          .updateOccupancy(detail.getSalesOrderId(), detail.getSalesOrderDetailId(),
              StockOccupancyType.SALES_ORDER, StockOccupancyStatus.UNLOCK);
    }
    resetStatus(salesOrder, bo);
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
  }

  @Override
  public SalesOrder copySalesOrder(Long salesOrderId) {
    //销售订单
    SalesOrder salesOrder = getByKey(salesOrderId);
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    salesOrder.setCreator(BizContext.getNickname());
    salesOrder.setContact(
        securityBridge.decrypt(store, salesOrder.getContact(), DataType.NAME));
    salesOrder.setTelephone(securityBridge
        .decrypt(store, salesOrder.getTelephone(), DataType.TELEPHONE));
    salesOrder.setMobile(
        securityBridge.decrypt(store, salesOrder.getMobile(), DataType.MOBILE));
    salesOrder.setSalesOrderId(null);
    salesOrder.setStatus(SalesOrderStatus.CREATED);
    salesOrder.setDeliveryStatus(DeliveryStatus.NONE);
    salesOrder.setRefundStatus(RefundStatus.NONE);
    salesOrder.setMallSalesOrderStatus(MallSalesOrderStatus.WAIT_SELLER_SEND_GOODS);
    salesOrder.setDispatchStatus(DispatchStatus.NONE);
    salesOrder.setPayStatus(SalesOrderPayStatus.PAID);
    salesOrder.setOos(false);
    salesOrder.setProductAbnormal(false);
    //销售订单副表
    SalesOrderSub salesOrderSub = salesOrderSubService.getByKey(salesOrderId);
    salesOrderSub.setSalesOrderId(null);
    salesOrderSub.setCreateType(SalesOrderCreateType.COPY);
    salesOrderSub.setPresellType(PresellType.NONE);
    salesOrderSub.setSalesOrderType(SalesOrderType.SALES);
    salesOrderSub.setSourceType(SourceType.PC);
    salesOrder.setSub(salesOrderSub);
    //销售订单明细
    SalesOrderDetail detailEg = new SalesOrderDetail();
    detailEg.setSalesOrderId(salesOrderId);
    detailEg.setCombinationDetail(false);
    List<SalesOrderDetail> details = salesOrderDetailService.listExample(detailEg);
    for (SalesOrderDetail detail : details) {
      detail.setSalesOrderId(null);
      detail.setSalesOrderDetailId(null);
      detail.setMallSalesOrderDetailStatus(MallSalesOrderDetailStatus.WAIT_SELLER_SEND_GOODS);
      detail.setSalesOrderDetailRefundStatus(SalesOrderDetailRefundStatus.NONE);
      detail.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
      detail.setStatus(SalesOrderDetailStatus.WAITING);
      detail.setDropShipping(false);
    }
    salesOrder.setDetails(details);
    //销售订单优惠明细
    SalesOrderDiscount discountEg = new SalesOrderDiscount();
    discountEg.setSalesOrderId(salesOrderId);
    SalesOrderDiscount salesOrderDiscount = salesOrderDiscountService
        .getByExample(discountEg);
    if (!Assert.isNull(salesOrderDiscount)) {
      salesOrderDiscount.setSalesOrderId(null);
      salesOrderDiscount.setSalesOrderDiscountId(null);
      List<SalesOrderDiscount> discounts = new ArrayList<>();
      discounts.add(salesOrderDiscount);
      salesOrder.setDiscounts(discounts);
    }
    //销售订单发票明细
    SalesOrderInvoice invoiceEg = new SalesOrderInvoice();
    invoiceEg.setSalesOrderId(salesOrderId);
    List<SalesOrderInvoice> salesOrderInvoices = salesOrderInvoiceService
        .listExample(invoiceEg);
    if (!Assert.isEmpty(salesOrderInvoices)) {
      List<SalesOrderInvoice> invoices = new ArrayList<>();
      salesOrderInvoices.forEach(salesOrderInvoice -> {
        salesOrderInvoice.setSalesOrderId(null);
        salesOrderInvoice.setSalesOrderInvoiceId(null);
        invoices.add(salesOrderInvoice);
      });
      salesOrder.setInvoices(invoices);
    }

    //销售订单活动表
    SalesOrderMarketing marketingEg = new SalesOrderMarketing();
    marketingEg.setSalesOrderId(salesOrderId);
    SalesOrderMarketing salesOrderMarketing = salesOrderMarketingService
        .getByExample(marketingEg);
    if (!Assert.isNull(salesOrderMarketing)) {
      salesOrderMarketing.setSalesOrderId(null);
      salesOrderMarketing.setSalesOrderMarketingId(null);
      List<SalesOrderMarketing> marketings = new ArrayList<>();
      marketings.add(salesOrderMarketing);
      salesOrder.setMarketings(marketings);
    }

    //销售订单支付明细
    SalesOrderPayment paymentEg = new SalesOrderPayment();
    paymentEg.setSalesOrderId(salesOrderId);
    SalesOrderPayment salesOrderPayment = salesOrderPaymentService
        .getByExample(paymentEg);
    if (!Assert.isNull(salesOrderPayment)) {
      salesOrderPayment.setSalesOrderId(null);
      salesOrderPayment.setSalesOrderPaymentId(null);
      List<SalesOrderPayment> payments = new ArrayList<>();
      payments.add(salesOrderPayment);
      salesOrder.setPayments(payments);
    }

    return salesOrder;
  }

  protected void resetStatus(SalesOrder salesOrder, VersionBO bo) {
    resetStatus(salesOrder, bo, null);
  }

  /**
   * 重设状态.
   *
   * @param salesOrder 原始订单
   * @param bo 版本
   * @param manual 是否要标记手工处理
   */
  protected void resetStatus(SalesOrder salesOrder, VersionBO bo, Boolean manual) {
    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setManual(manual);
    if (salesOrder.getQuantity() != null) {
      update.setQuantity(salesOrder.getQuantity());
    }
    update.setVersion(bo.getVersion());

    List<SalesOrderDetail> simpleDetails =
        salesOrderDetailService.listSimpleNormal(salesOrder.getSalesOrderId());
    SalesOrderUtil.recountStatus(update, simpleDetails);
    if (salesOrder.getStatus() == SalesOrderStatus.DISPATCHED
        && update.getDispatchStatus() == DispatchStatus.NONE) {
      update.setStatus(SalesOrderStatus.AUDITED);
    }
    if (simpleDetails.stream().noneMatch(x -> Assert.isTrue(x.isOos()))) {
      update.setOos(false);
    }
    update(update);
  }

  @Override
  public PageList<SalesOrder> getStockOccupancyDetail(StockQuery stockFilter, int pageNo,
      int pageSize) {
    validatePageParameter(pageNo, pageSize);
    return dao.stockOccupancyDetail(stockFilter, pageNo, pageSize);
  }

  /**
   * 配货失败处理.
   */
  @Override
  public void setDispatchFailingStatus(SalesOrder salesOrder, VersionBO bo) {
    SalesOrder resetOrder = new SalesOrder();
    resetOrder.setSalesOrderId(salesOrder.getSalesOrderId());
    resetOrder.setVersion(bo.getVersion());
    resetOrder.setStatus(SalesOrderStatus.DISPATCHED_ABNORMAL);
    update(resetOrder);
    getMqProducer().send(new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(),
        EventType.MODIFIED)
    );
  }

  /**
   * 配货缺货处理.
   */
  @Override
  public void autoDispatchOOS(SalesOrder salesOrder, List<SalesOrderDetail> oosDetails) {
    SalesOrder resetOrder = new SalesOrder();
    resetOrder.setSalesOrderId(salesOrder.getSalesOrderId());
    resetOrder.setOos(true);
    resetOrder.setVersion(salesOrder.getVersion());
    resetOrder.setStatus(SalesOrderStatus.DISPATCHED_ABNORMAL);
    List<SalesOrderDetail> updateDetails = new ArrayList<>(oosDetails.size());
    oosDetails.forEach(x -> {
      SalesOrderDetail update = new SalesOrderDetail();
      update.setSalesOrderDetailId(x.getSalesOrderDetailId());
      update.setOos(true);
      updateDetails.add(update);
    });
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.batchModify(updateDetails);
        update(resetOrder);
      });
    } catch (Exception e) {
      LOGGER.error("订单配货缺货修改失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("订单配货缺货修改失败，堆栈信息：", e);
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
  }

  @Override
  public void noReturnGood(SalesOrder salesOrder, VersionBO<SalesOrderDetail> versionBO) {
    SalesOrderDetail salesOrderDetail = versionBO.getDomain();

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        if (salesOrderDetail != null) {
          salesOrderDetail.setNeedReturnGoods(false);
          salesOrderDetailService.modify(salesOrderDetail);
        }
        salesOrder.setVersion(versionBO.getVersion());
        update(salesOrder);
      });
    } catch (Exception e) {
      LOGGER.error("修改明细状态失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("修改明细状态失败，堆栈信息：", e);
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
  }

  @Override
  public void needReturnGood(SalesOrder salesOrder, VersionBO<SalesOrderDetail> versionBO) {
    SalesOrderDetail salesOrderDetail = versionBO.getDomain();
    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        if (salesOrderDetail != null) {
          salesOrderDetail.setNeedReturnGoods(true);
          salesOrderDetailService.modify(salesOrderDetail);
        }
        salesOrder.setVersion(versionBO.getVersion());
        update(salesOrder);
      });
    } catch (Exception e) {
      LOGGER.error("修改明细状态失败，销售单：{}", JsonUtil.toJson(salesOrder));
      LOGGER.error("修改明细状态失败，堆栈信息：", e);
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
  }

  /**
   * 修改平台快递.
   *
   * @param salesOrder 如果拆单，必须保证salesOrder中的明细有值.
   * @param bo 储存平台快递信息.
   */
  @Override
  public void mallResetExpress(SalesOrder salesOrder, MallExpressResetBo bo) {
    Store store = storeService.getByKey(salesOrder.getStoreId());
    String mallExpressId = bo.getMallExpressId();
    String mallExpressCode = bo.getMallExpressCode();
    String mallExpressName = bo.getMallExpressName();
    String resetExpressNo = bo.getResetExpressNo();

    List<SalesOrderDetail> resetDetails = salesOrder.getDetails();
    List<SalesOrderDetail> resetOrderFullDetails = salesOrderDetailService
        .listNormalBySalesOrderId(salesOrder.getSalesOrderId());
    boolean isSplit =
        !Assert.isEmpty(resetDetails) && resetOrderFullDetails.size() != resetDetails.size();

    //封装请求
    OrderDeliveryRequest request = new OrderDeliveryRequest(store);
    request.setSalesOrder(salesOrder);
    request.setMallExpressId(mallExpressId);
    request.setMallExpressCode(mallExpressCode);
    request.setMallExpressName(mallExpressName);
    request.setExpressNo(resetExpressNo);
    request.setPartDelivery(isSplit);
    if (isSplit) {
      request.setDetails(new ArrayList<>(resetDetails.size()));
      for (SalesOrderDetail resetDetail : resetDetails) {
        //封装发货明细
        OrderDeliveryRequest.OrderDeliveryDetail deliveryDetail = new OrderDeliveryDetail();
        deliveryDetail.setMallExpressName(mallExpressName);
        deliveryDetail.setSalesOrderDetail(resetDetail);
        deliveryDetail.setMallExpressCode(mallExpressCode);
        deliveryDetail.setExpressNo(resetExpressNo);
        request.getDetails().add(deliveryDetail);
      }
    }

    //发送平台修改发货请求
    OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
    OrderDeliveryResponse orderDeliveryResponse = orderBridge.orderResetExpress(request);
    if (orderDeliveryResponse.isSuccess()) {
      LOGGER.info("订单{}平台修改发货快递信息成功", salesOrder.getSalesOrderCode());
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_RESET_EXPRESS, "修改平台快递信息成功");
    } else {
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.MALL_RESET_EXPRESS, "修改平台快递信息失败");
      LOGGER.error("订单{}平台修改发货快递信息成功,错误信息:{}", salesOrder.getSalesOrderCode(),
          orderDeliveryResponse.getResult());
    }
  }

  /**
   * 自动配货.
   */
  @Override
  public void autoDispatch(SalesOrder salesOrder, SalesOrderDispatchBO bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    if (Assert.isTrue(salesOrder.isManual())) {
      throw new OmsException("订单为手工处理");
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    SalesOrderAutoDispatchMessage dispatchMessage =
        new SalesOrderAutoDispatchMessage(salesOrder.getSalesOrderId());
    getMqProducer().send(dispatchMessage);
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.DISPATCH, "批量自动配货");
    LOGGER.info("订单{}发送自动配货消息", salesOrder.getSalesOrderCode());
  }

  /**
   * 修改仓库备注.
   */
  @Override
  public void modifyRemark(SalesOrder salesOrder, SalesOrderRemarkBo bo) {
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);

    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(bo.getSalesOrderId());
    update.setVersion(bo.getVersion());
    update.setRemark(bo.getRemark());
    update(update);
    BIZ_LOGGER.log(bo.getSalesOrderId(), BizLogger.UPDATE, "修改仓库备注");
  }

  @Override
  public List<Long> listOOSRedispatchSalesOrderIds() {
    return dao.listOOSRedispatchSalesOrderIds();
  }

  @Override
  public void clearOos() {
    dao.clearOos();
    salesOrderDetailService.clearOos();
  }

  /**
   * 解析套装明细.
   * <p/>
   * 目前只支持解析一次组合明细
   */
  private void parseCombination(SalesOrder salesOrder, List<SalesOrderDetail> details) {
    List<SalesOrderDetail> copyDetails = new ArrayList<>(details);
    ProductSku sku;
    List<ProductCombination> combinations;
    List<SalesOrderDetail> combinationDetails;
    for (SalesOrderDetail detail : copyDetails) {
      if (detail.getSalesOrderDetailId() == null) {
        detail.setSalesOrderDetailId(salesOrderDetailService.getIdGenerator().next());
      }
      if (Assert.isTrue(detail.isCombination())) {
        sku = productSkuService.getEffectiveById(detail.getSkuId());
        if (Assert.isTrue(sku.isGiftBox())) {
          salesOrder.setQuantity(salesOrder.getQuantity() + detail.getQuantity());
          continue;
        }
        combinations = productCombinationService.listByCombination(detail.getSkuId());
        combinationDetails = new ArrayList<>(combinations.size());

        detail.setSalesOrderDetailType(SalesOrderDetailType.ORIGINAL);
        for (ProductCombination combination : combinations) {
          SalesOrderDetail combinationDetail = new SalesOrderDetail();
          BeanUtils.copyProperties(detail, combinationDetail);
          combinationDetail.setSalesOrderDetailId(null);
          combinationDetail.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
          combinationDetail.setSeparate(false);
          combinationDetail.setCombination(false);
          combinationDetail.setCombinationDetail(true);
          combinationDetail.setGift(Assert.isTrue(detail.isGift()));
          combinationDetail.setManualAdd(true);
          combinationDetail.setOos(false);
          combinationDetail.setNeedReturnGoods(false);
          combinationDetail.setPrepackage(Assert.isTrue(detail.isPrepackage()));

          sku = productSkuService.getEffectiveById(combination.getSkuId());
          combinationDetail.setWeight(sku.getWeight());
          combinationDetail.setProductId(sku.getProductId());
          combinationDetail.setProductCode(sku.getProductCode());
          combinationDetail.setProductName(sku.getProductName());
          combinationDetail.setSkuId(sku.getSkuId());
          combinationDetail.setSkuCode(sku.getSkuCode());
          combinationDetail.setSkuName(sku.getSkuName());
          combinationDetail.setProductType(sku.getProduct().getProductType());
          combinationDetail.setOriginalDetailid(detail.getSalesOrderDetailId());
          combinationDetail.setCostPrice(sku.getCostPrice());
          combinationDetail.setSellingPrice(sku.getSellingPrice());
          combinationDetail.setSettlementPrice(sku.getSellingPrice());
          combinationDetail.setDistributionPrice(sku.getDistributionPrice());
          combinationDetail.setQuantity(detail.getQuantity() * combination.getQuantity());
          combinationDetail.setActualSellingPrice(0D);
          combinationDetail.setSellingAmount(MathUtil
              .multiply(combinationDetail.getSellingPrice(), combinationDetail.getQuantity()));
          combinationDetail.setActualAmount(0D);
          combinationDetail.setSettlementAmount(0D);
          combinationDetail.setDiscountAmount(0D);
          combinationDetail.setDistributionAmount(0D);
          combinationDetails.add(combinationDetail);
          salesOrder.setQuantity(salesOrder.getQuantity() +
              detail.getQuantity() * combination.getQuantity());
        }
        boolean result = SalesOrderUtil.recountDetailsAmount(detail, combinationDetails);
        if (!result) {
          BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.UPDATE,
              "订单{}明细组合套装{},{}组合商品子明细商品的销售价格都为0，分摊金额无法计算！",
              salesOrder.getSalesOrderCode(), detail.getSkuCode(), detail.getSkuName());
        }
        details.addAll(combinationDetails);
      } else {
        salesOrder.setQuantity(salesOrder.getQuantity() + detail.getQuantity());
      }
    }
  }


  @Override
  public List<SalesOrder> listForBatch(SalesOrderQuery filter) {
    return dao.listForBatch(filter);
  }

  @Override
  public Long getSalesOrderIdByStoreIdAndTradeId(Long storeId, String tradeId) {
    return dao.getSalesOrderIdByStoreIdAndTradeId(storeId, tradeId);
  }

  @Override
  public void manualDetailDropShipping(SalesOrder salesOrder, SalesOrderDetailBO bo) {
    SalesOrderDetail detail = bo.getDetail();
    SalesOrderDetail updateDetail = new SalesOrderDetail();
    updateDetail.setSalesOrderDetailId(detail.getSalesOrderDetailId());
    updateDetail.setStatus(SalesOrderDetailStatus.DELIVERED);
    updateDetail.setDropShipping(true);
    updateDetail.setOos(false);

    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setVersion(bo.getVersion());
    update.setOos(false);

    //编程式事务
    try {
      getTransactionTemplate().execute(() -> {
        salesOrderDetailService.modify(updateDetail);
        stockOccupancyService
            .deleteOccupancy(salesOrder.getSalesOrderId(), detail.getSalesOrderDetailId(),
                StockOccupancyType.SALES_ORDER);
        List<SalesOrderDetail> details = salesOrderDetailService
            .listSimpleBySalesOrderId(salesOrder.getSalesOrderId());
        SalesOrderUtil.recountStatus(update, details);
        update(update);
      });
    } catch (Exception e) {
      LOGGER.error("明细手工代发失败，堆栈信息：", e);
    }
    getMqProducer().send(
        new GeneralMessage(OmsModule.SALES_ORDER, salesOrder.getSalesOrderId(), EventType.MODIFIED)
    );
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), updateDetail.getSalesOrderDetailId(),
        BizLogger.UPDATE, "明细（商品：{}，规格：{}）设置手工代发",
        detail.getProductName(), detail.getSkuName());
  }

  @Override
  public void splitDetail(SalesOrder salesOrder, SalesOrderDetail sourceDetail,
      SalesOrderDetailSplitBO bo) {
    bo.getSplitDetails().forEach(x -> {
      x.setSalesOrderId(sourceDetail.getSalesOrderId());
      x.setMallDetailId(sourceDetail.getMallDetailId());
      x.setMallSkuId(sourceDetail.getMallSkuId());
      x.setMallSkuName(sourceDetail.getMallSkuName());
      x.setMallSkuOutCode(sourceDetail.getMallSkuOutCode());
      x.setMallProductId(sourceDetail.getMallProductId());
      x.setMallProductName(sourceDetail.getMallProductName());
      x.setMallProductOutCode(sourceDetail.getMallProductOutCode());
      x.setPresellId(sourceDetail.getPresellId());
      x.setPresellPriority(sourceDetail.isPresellPriority());
      x.setMallPresellDeliveryRequiring(sourceDetail.getMallPresellDeliveryRequiring());
      x.setActivityId(sourceDetail.getActivityId());
      x.setExchangeId(sourceDetail.getExchangeId());
      x.setManualAdd(true);
      x.setSeparate(sourceDetail.isSeparate());
      x.setOriginalDetailid(sourceDetail.getOriginalDetailid());
      x.setCombinationDetail(sourceDetail.isCombinationDetail());
      x.setSuggestVirtualWarehouseId(sourceDetail.getSuggestVirtualWarehouseId());
      x.setSuggestVirtualWarehouseName(sourceDetail.getSuggestVirtualWarehouseName());
      x.setSuggestWarehouseId(sourceDetail.getSuggestWarehouseId());
      x.setSuggestWarehouseName(sourceDetail.getSuggestWarehouseName());

    });
    boolean result = SalesOrderUtil.recountDetailsAmount(sourceDetail, bo.getSplitDetails());
    if (!result) {
      BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.UPDATE,
          "订单{}拆分明细的子明细的销售价格都为0，分摊金额无法计算！");
    }
    parseCombination(salesOrder, bo.getSplitDetails());
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SalesOrder updateOrder = new SalesOrder();
    updateOrder.setSalesOrderId(salesOrder.getSalesOrderId());
    updateOrder.setVersion(bo.getVersion());
    updateOrder.setQuantity(salesOrder.getQuantity());
    getTransactionTemplate().execute(() -> {
      salesOrderDetailService.remove(sourceDetail);
      salesOrderDetailService.batchCreate(bo.getSplitDetails());
      List<StockOccupancy> stockOccupancies = SalesOrderUtil
          .buildOccupancy(salesOrder, bo.getSplitDetails(), store);
      stockOccupancyService.deleteOccupancy(salesOrder.getSalesOrderId(),
          sourceDetail.getSalesOrderDetailId(), StockOccupancyType.SALES_ORDER);
      stockOccupancyService.batchCreate(stockOccupancies);
      update(updateOrder);
    });
    bo.getSplitDetails().forEach(detail -> getMqProducer().send(
        new StockChangedMessage(salesOrder.getSalesOrderCode(), detail.getSkuId(),
            salesOrder.getStoreId(), BizContext.getNickname(), "拆分明细")));
    getMqProducer().send(new SalesOrderDetailChangeMessage(salesOrder.getSalesOrderId()));
    BIZ_LOGGER.log(salesOrder.getSalesOrderId(), BizLogger.UPDATE,
        "拆分明细");
  }

  /**
   * 预付款订单付尾款后更新（此方法只能在预付款订单付尾款修改转化）.
   */
  @Override
  public void finalPaidUpdateOrder(SalesOrder salesOrder) {
    VirtualWarehouse warehouse =
        virtualWarehouseService.getByKey(salesOrder.getSuggestVirtualWarehouseId());
    getTransactionTemplate().execute(() -> {
      stockOccupancyService.updateOccupancy(salesOrder.getSalesOrderId(),
          StockOccupancyType.SALES_ORDER, warehouse);
      if (!Assert.isNull(salesOrder.getSub())) {
        salesOrderSubService.modify(salesOrder.getSub());
      }
      update(salesOrder);
    });
  }

  @Override
  public void prerefund(SalesOrder salesOrder) {
    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setVersion(salesOrder.getVersion());
    update.setPrerefund(true);
    update(update);
  }

  @Override
  public void cancelPrerefund(SalesOrder salesOrder, VersionBO bo) {
    SalesOrder update = new SalesOrder();
    update.setSalesOrderId(salesOrder.getSalesOrderId());
    update.setVersion(bo.getVersion());
    update.setPrerefund(false);
    update(update);
  }
}