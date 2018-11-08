package com.greatonce.oms.custom.eeka.service;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.impl.AbstractVersionService;
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
import com.greatonce.oms.bo.trade.InDetail;
import com.greatonce.oms.bo.trade.OutDetail;
import com.greatonce.oms.bo.trade.ReturnOrderSaveBO;
import com.greatonce.oms.bo.trade.ReturnOrderSavePrepareBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.custom.eeka.EekaCondition;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.dao.trade.ReturnOrderDao;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.WmsNoticeStatus;
import com.greatonce.oms.domain.enums.product.ProductType;
import com.greatonce.oms.domain.enums.stock.StockOccupancyStatus;
import com.greatonce.oms.domain.enums.stock.StockOccupancyType;
import com.greatonce.oms.domain.enums.trade.RefundApplyOrderType;
import com.greatonce.oms.domain.enums.trade.RefundType;
import com.greatonce.oms.domain.enums.trade.ReturnOrderStatus;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.stock.StockOccupancy;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.domain.trade.ReturnOrderOutDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EekaCondition
public class EekaReturnOrderService extends
    AbstractVersionService<ReturnOrder, ReturnOrderQuery> {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.RETURN_ORDER);
  private static final Logger LOGGER = LoggerFactory.getLogger(EekaReturnOrderService.class);
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
  private ManualTransactionTemplate transactionTemplate;
  @Autowired
  private DataDictItemService dataDictItemService;

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

  public void addReturnOrder(ReturnOrderSaveBO returnOrderSaveBO) {
    //统计相同的销售单的申请总数量
    Map<String, Integer> quantityMap = new HashMap<>();
    if (!Assert.isEmpty(returnOrderSaveBO.getRefundApplyOrders())) {
      List<String> tradeIds = returnOrderSaveBO.getRefundApplyOrders().stream()
          .map(x -> x.getTradeId()).distinct().collect(Collectors.toList());
      if (!Assert.isEmpty(tradeIds)) {
        for (String tradeId : tradeIds) {
          Integer applyQuantity = 0;
          for (RefundApplyOrder refundApplyOrder : returnOrderSaveBO.getRefundApplyOrders()) {
            if (tradeId.equals(refundApplyOrder.getTradeId())) {
              applyQuantity += (refundApplyOrder.getApplyQuantity() == null ? 0
                  : refundApplyOrder.getApplyQuantity());
            }
          }
          quantityMap.put(tradeId, applyQuantity);
        }
      }
    }

    if (!Assert.isEmpty(returnOrderSaveBO.getExchangeApplyOrders())) {
      List<String> tradeIds = returnOrderSaveBO.getExchangeApplyOrders().stream()
          .map(x -> x.getTradeId()).distinct().collect(Collectors.toList());
      if (!Assert.isEmpty(tradeIds)) {
        for (String tradeId : tradeIds) {
          Integer applyQuantity = 0;
          for (ExchangeApplyOrder exchangeApplyOrder : returnOrderSaveBO.getExchangeApplyOrders()) {
            if (tradeId.equals(exchangeApplyOrder.getTradeId())) {
              applyQuantity += (exchangeApplyOrder.getQuantity() == null ? 0
                  : exchangeApplyOrder.getQuantity());
            }
          }
          quantityMap.put(tradeId, applyQuantity);
        }
      }
    }

    //准备数据
    List<ReturnOrderSavePrepareBO> returnOrderBoList = prepareData(returnOrderSaveBO);
    //创建退换货单
    List<ReturnOrderSavePrepareBO> returnOrderBo = createReturnOrder(returnOrderBoList,
        quantityMap);
    ArrayList<StockChangedMessage> messageList = new ArrayList<>();
    //执行保存
    try {
      transactionTemplate.execute(() -> {
        //签收快递
        if (!Assert.isEmpty(returnOrderSaveBO.getExpressNo())) {
          returnSignService.confirmUnpack(returnOrderSaveBO.getExpressNo());
        }
        for (ReturnOrderSavePrepareBO returnOrderSavePrepareBO : returnOrderBo) {
          ReturnOrder returnOrder = returnOrderSavePrepareBO.getReturnOrder();
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
                  .get(out.getSkuCode() + "O");
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
              messageList
                  .add(new StockChangedMessage(exchangeApplyOrder.getExchangeApplyOrderCode(),
                      exchangeApplyOrder.getOutSkuId(),
                      BizContext.getNickname(), "新建退换货单"));
            });
          }
          BIZ_LOGGER.log(returnOrder.getReturnOrderId(), "扫描新增");
        }
      });
      mqProducer.send(messageList);
    } catch (Exception e) {
      LOGGER.error("退换货单扫描新增失败，退换货单：{}", JsonUtil.toJson(returnOrderSaveBO));
      LOGGER.error("退换货单扫描新增失败，堆栈信息：", e);
      throw new OmsException("退换货单扫描新增失败！");
    }
  }

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
      transactionTemplate.execute(() -> {
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
      transactionTemplate.execute(() -> {
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
                    exchangeApplys.put(inDetail.getSkuCode() + "I", e);
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
                    exchangeApplys.put(outDetail.getSkuCode() + "O", exchangeApply);
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
                  <= (refund.getApplyQuantity())) && x.getQuantity() > 0

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
                  <= (exchange.getQuantity())) && x.getQuantity() > 0

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
              //扫描数量
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
                //如何获取原单的销售订单编号 1.inDetail中扫描数量已处理完，换另外一个销售订单
                //由于已经获取到退款申请单，根据退款申请单的tradeId和storeId获取销售单
                SalesOrder salesOrder = salesOrderService
                    .getByTradeId(refundApplyOrder.getStoreId(), refundApplyOrder.getTradeId());
                sourceDetails = sourceDetails.stream()
                    .filter(x -> x.getSalesOrderId().equals(salesOrder.getSalesOrderId()))
                    .collect(Collectors.toList());
                SalesOrderDetail sourceDetail = null;
                if (!Assert.isEmpty(sourceDetails)) {
                  sourceDetail = sourceDetails.get(0);
                }

                //使用真实的sku 替换 申请的(发错货的)sku , 为后面skuCode 找到申请
                //找出的refundApplyOrder可能会出现部分扫描问题，则判断没有扫描时，才替换
                List<InDetail> sourceIndetails = scanInDetail.stream()
                    .filter(x -> !Assert.isNull(x.getSalesOrderId())).collect(Collectors.toList());
                if (!Assert.isEmpty(sourceIndetails)) {
                  List<InDetail> resultIns = sourceIndetails.stream()
                      .filter(
                          x -> salesOrder.getSalesOrderId().equals(x.getSalesOrderId()) && inDetail
                              .getSkuCode().equals(x.getSkuCode()) && x.getQuantity()>0)
                      .collect(Collectors.toList());
                  if (Assert.isEmpty(resultIns)) {
                    refundApplyOrder.setInSkuCode(inDetail.getSkuCode());
                  }
                } else {
                  refundApplyOrder.setInSkuCode(inDetail.getSkuCode());
                }

                inDetail.setApplyQuantity(refundApplyOrder.getApplyQuantity());
                if (!Assert.isNull(sourceDetail)) {
                  inDetail.setSalesOrderId(sourceDetail.getSalesOrderId());
                }

                //应退金额 , 取关联上的 申请的金额
                inDetail.setRefundableAmount(refundApplyOrder.getApplyAmount());
                inDetail.setApplyQuantity(refundApplyOrder.getApplyQuantity());

                //标记退入的sku异常
                inDetail.setSkuAbnormal(true);
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
              //扫描数量
              //这个处理了的就不算 申请扫描了
              exchangeNoScan.remove(exchangeApplyOrder);

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
                //如何获取原单的销售订单编号 1.inDetail中扫描数量已处理完，换另外一个销售订单
                //由于已经获取到退款申请单，根据退款申请单的tradeId和storeId获取销售单
                SalesOrder salesOrder = salesOrderService
                    .getByKey(exchangeApplyOrder.getSalesOrderId());
                sourceDetails = sourceDetails.stream()
                    .filter(x -> x.getSalesOrderId().equals(salesOrder.getSalesOrderId()))
                    .collect(Collectors.toList());
                SalesOrderDetail sourceDetail = null;
                if (!Assert.isEmpty(sourceDetails)) {
                  sourceDetail = sourceDetails.get(0);
                }

                //使用真实的skuCode 替换 申请的(发错货的)skuCode , 为后面skuCode 找到申请
                //找出的refundApplyOrder可能会出现部分扫描问题，则判断没有扫描时，才替换
                List<InDetail> sourceIndetails = scanInDetail.stream()
                    .filter(x -> !Assert.isNull(x.getSalesOrderId())).collect(Collectors.toList());
                if (!Assert.isEmpty(sourceIndetails)) {
                  List<InDetail> resultIns = sourceIndetails.stream()
                      .filter(
                          x -> salesOrder.getSalesOrderId().equals(x.getSalesOrderId()) && inDetail
                              .getSkuCode().equals(x.getSkuCode()) && x.getQuantity()>0)
                      .collect(Collectors.toList());
                  if (Assert.isEmpty(resultIns)) {
                    exchangeApplyOrder.setInSkuCode(inDetail.getSkuCode());
                  }
                } else {
                  exchangeApplyOrder.setInSkuCode(inDetail.getSkuCode());
                }

                inDetail.setApplyQuantity(exchangeApplyOrder.getQuantity());
                if (!Assert.isNull(sourceDetail)) {
                  inDetail.setSalesOrderId(sourceDetail.getSalesOrderId());
                  //计算原单的明细的单价
                  double refundAbleAmount = new BigDecimal(
                      (sourceDetail.getActualAmount() / sourceDetail.getQuantity()) * inDetail
                          .getQuantity())
                      .setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                  //换货的应退 取的是实际销售的金额
                  inDetail.setRefundableAmount(refundAbleAmount);
                }

                inDetail.setApplyQuantity(exchangeApplyOrder.getQuantity());

                //标记退入的sku异常
                inDetail.setSkuAbnormal(true);
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
              if (Assert.isEmpty(returnOrderSaveBO.getOutDetails())) {
                returnOrderSaveBO.setOutDetails(new ArrayList<>());
              }
              returnOrderSaveBO.getOutDetails().add(outDetail);
            }
          }
        }
      }
    }
  }

  private List<ReturnOrderSavePrepareBO> createReturnOrder(
      List<ReturnOrderSavePrepareBO> returnOrderBoList, Map<String, Integer> quantityMap) {

    for (ReturnOrderSavePrepareBO prepareBO : returnOrderBoList) {
      ReturnOrder returnOrder = new ReturnOrder();
      returnOrder.setRemark(prepareBO.getRemark());
      returnOrder.setBoxNo(prepareBO.getBoxNo());
      returnOrder.setReturnOrderId(returnOrderIdGenerator.next());
      //封装订单的信息
      returnOrder.setAbnormal(true);
      if (!Assert.isNull(prepareBO.getSalesOrder())) {
        //正常单 自动审核
        /*final Map<String, String> returnTypeMap = dataDictItemService.listMapByDictId(10301L);
        if (prepareBO.getReturnType().equals(returnTypeMap.get("001"))) {
          returnOrder.setStatus(ReturnOrderStatus.AUDITED);
          if (Assert.isNull(prepareBO.getNickName())) {
            returnOrder.setAuditor(BizContext.getNickname());
          } else {
            returnOrder.setAuditor(prepareBO.getNickName());
          }
        } else {
          returnOrder.setStatus(ReturnOrderStatus.CREATED);
        }*/
        returnOrder.setStatus(ReturnOrderStatus.AUDITED);
        if (Assert.isNull(prepareBO.getNickName())) {
          returnOrder.setAuditor(BizContext.getNickname());
        } else {
          returnOrder.setAuditor(prepareBO.getNickName());
        }
        returnOrder.setAuditedTime(LocalDateTime.now());
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
      inDetails(prepareBO, quantityMap);
      //修改的
      if (!Assert.isNull(prepareBO.getOldReturnOrder())) {
        initDefaultValue(returnOrder);
        //如果是修改的，那就不修改 是退还是换，原本是什么就是什么
        prepareBO.setExchange(null);
        continue;
      }
      //换货
      if (prepareBO.getExchange()) {
        outDetails(prepareBO, quantityMap);
      }
      initDefaultValue(returnOrder);
    }
    return returnOrderBoList;
  }

  protected void initDefaultValue(ReturnOrder entity) {
    if (Assert.isNull(entity.getReturnOrderId())) {
      entity.setReturnOrderId(returnOrderIdGenerator.next());
    }
    entity.setVersion(1);
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

  private void inDetails(ReturnOrderSavePrepareBO returnOrderBo, Map<String, Integer> quantityMap) {
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
        exchangeApplyOrder = returnOrderBo.getExchangeApplys().get(in.getSkuCode() + "I");
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
        //退换货单总的申请数量 取同一个销售订单的换货申请的总数量
        if (!Assert.isEmpty(returnOrderBo.getExchangeApplys())) {
          //通过salesOrderId查询tradeId
          if (!Assert.isNull(in.getSalesOrderId())) {
            returnOrder.setApplyQuantity(
                quantityMap.get(salesOrderService.getByKey(in.getSalesOrderId()).getTradeId()));
          } else {
            returnOrder.setApplyQuantity(0);
          }
        } else {
          returnOrder.setApplyQuantity(0);
        }
        /*if (Assert.isNull(returnOrder.getApplyQuantity())) {
          returnOrder.setApplyQuantity(exchangeApplyOrder.getQuantity());
        } else {
          returnOrder
              .setApplyQuantity(returnOrder.getApplyQuantity() + (Assert.isNull(exchangeApplyOrder.getQuantity()) ? 0 : exchangeApplyOrder.getQuantity()));
        }*/

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
        //退换货单总的申请数量 取同一个销售订单的换货申请的总数量
        if (!Assert.isEmpty(returnOrderBo.getRefundApplys())) {
          //通过salesOrderId查询tradeId
          if (!Assert.isNull(in.getSalesOrderId())) {
            returnOrder.setApplyQuantity(
                quantityMap.get(salesOrderService.getByKey(in.getSalesOrderId()).getTradeId()));
          } else {
            returnOrder.setApplyQuantity(0);
          }
        } else {
          returnOrder.setApplyQuantity(0);
        }
        /*if (Assert.isNull(returnOrder.getApplyQuantity())) {
          returnOrder.setApplyQuantity(refundApplyOrder.getApplyQuantity());
        } else {
          returnOrder.setApplyQuantity(
              returnOrder.getApplyQuantity() + refundApplyOrder.getApplyQuantity());
        }*/
        //无头件
      } else {
        returnOrder.setReturnReasonType("其他");
        returnOrderDetail.setReturnReasonType("其他");
        returnOrder.setRefundType(RefundType.RETURN_AND_REFUND);
        returnOrder.setExpressNo(returnOrderBo.getExpressNo());
        //通过salesOrderId查询tradeId
        if (!Assert.isNull(in.getSalesOrderId())) {
          returnOrder.setApplyQuantity(
              quantityMap.get(salesOrderService.getByKey(in.getSalesOrderId()).getTradeId()));
        } else {
          returnOrder.setApplyQuantity(0);
        }
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
      returnOrderDetail.setSkuCode(in.getSkuCode());
      returnOrderDetail.setSkuId(in.getSkuId());
      returnOrderDetail.setSkuName(in.getSkuName());
      returnOrderDetail.setBrandName(product.getBrandName());
      returnOrderDetail.setBrandCode(product.getBrandCode());
      returnOrderDetail.setNoticed(false);
      ProductSku productSku = productSkuService.getEffectiveByCode(in.getSkuCode());
      if (!Assert.isNull(productSku)) {
        returnOrderDetail.setSkuCode(productSku.getSkuCode());
        returnOrderDetail.setSkuId(productSku.getSkuId());
        returnOrderDetail.setSkuName(productSku.getSkuName());
        returnOrderDetail.setProductCode(productSku.getProductCode());
        returnOrderDetail.setProductId(productSku.getProductId());
        returnOrderDetail.setProductName(productSku.getProductName());
        returnOrderDetail
            .setProductType(productService.getByKey(productSku.getProductId()).getProductType());
      } else {
        returnOrderDetail.setProductType(ProductType.PHYSICAL);
      }
      returnOrderDetails.add(returnOrderDetail);
    }
    returnOrder.setDetails(returnOrderDetails);
  }

  private void outDetails(ReturnOrderSavePrepareBO returnOrderBo, Map<String, Integer> quantityMap) {
    ReturnOrder returnOrder = returnOrderBo.getReturnOrder();
    List<ReturnOrderOutDetail> returnOutDetails = new ArrayList<>();
    List<OutDetail> outDetails = returnOrderBo.getOutDetails();
    if (Assert.isEmpty(outDetails)) {
      return;
    }
    returnOrder.setExchange(true);
    for (OutDetail outDetail : outDetails) {
      ExchangeApplyOrder exchange = returnOrderBo.getExchangeApplys()
          .get(outDetail.getSkuCode() + "O");
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
}
