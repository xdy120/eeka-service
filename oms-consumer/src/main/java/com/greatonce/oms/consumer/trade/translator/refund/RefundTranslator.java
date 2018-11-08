package com.greatonce.oms.consumer.trade.translator.refund;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.RefundApplyOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.bo.trade.DispatchOrderCancelBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailBatchBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.RefundBridge;
import com.greatonce.oms.bridge.mall.request.RefundAgreeRequest;
import com.greatonce.oms.bridge.mall.response.RefundAgreeResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.RefundApplyOrderStatus;
import com.greatonce.oms.domain.enums.trade.RefundApplyOrderType;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.message.trade.SalesOrderAgreeRefundMessage;
import com.greatonce.oms.message.trade.SalesOrderCancelRefundMessage;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import com.greatonce.oms.util.logging.TranslateRefundLogger;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 退款单转化.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/26
 */
@Component
@TranslatorRefundCondition
public class RefundTranslator {

  private static final Logger LOGGER = LoggerFactory.getLogger(RefundTranslator.class);
  private static final TranslateRefundLogger TRANSLATE_REFUND_LOGGER = OmsLoggerFactory
      .getTranslateRefundLogger();

  @Autowired
  private MqProducer mqProducer;
  @Autowired
  private RefundApplyOrderService refundApplyOrderService;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private DataDictItemService dataDictItemService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  private void writeLog(RefundTranslatableContext context, String message, Object... args) {
    TRANSLATE_REFUND_LOGGER.log(context.getSerialNumber(), context.getStore(),
        context.getApplyRefundOrder().getTradeId(),
        context.getApplyRefundOrder().getMallRefundId(), message, args);
  }

  /**
   * 转化方法.
   */
  public void translate(RefundTranslatableContext context) {
    synchronized (context.getMallRefundOrderInfo().getTradeId() + context.getMallRefundOrder()
        .getStoreId()) {
      init(context);
      RefundApplyOrder applyOrder = refundApplyOrderService
          .getByMallRefundId(context.getStore().getStoreId(),
              context.getMallRefundOrderInfo().getRefundId());
      if (applyOrder != null) {
        context.setApplyRefundOrder(applyOrder);
        modify(context);
      } else {
        create(context);
      }
    }
  }

  protected void init(RefundTranslatableContext context) {
    MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    Long storeId = context.getMallRefundOrder().getStoreId();
    Store store = storeService.getByKey(storeId);
    context.setStore(store);

    if (!mallRefundOrderInfo.isWhole()) {
      SalesOrder salesOrder = null;
      SalesOrderDetail detailEg = new SalesOrderDetail();
      detailEg.setMallDetailId(mallRefundOrderInfo.getOid());
      SalesOrderDetail detail = salesOrderDetailService.getByExample(detailEg);

      if (!Assert.isNull(detail)) {
        salesOrder = salesOrderService.getByKey(detail.getSalesOrderId());
      }
      context.setSalesOrder(salesOrder);
      if (salesOrder != null) {
        if (!Assert.isEmpty(mallRefundOrderInfo.getOid())) {
          context.setSalesOrderDetails(salesOrderDetailService
              .listByMallDetailId(salesOrder.getSalesOrderId(), mallRefundOrderInfo.getOid()));
          //全部发货 或者 未配货的 就不用找配货单
          if (salesOrder.getDeliveryStatus() != DeliveryStatus.ALL
              && salesOrder.getDispatchStatus() != DispatchStatus.NONE) {
            Set<Long> detailIds = context.getSalesOrderDetails().stream()
                .map(SalesOrderDetail::getSalesOrderDetailId).collect(Collectors.toSet());
            context.setDispatchOrders(dispatchOrderService
                .listBySalesOrderDetailId(salesOrder.getSalesOrderId(), detailIds));
          }
        }
      }
    } else if (mallRefundOrderInfo.isWhole()) {
      SalesOrder salesOrder = salesOrderService
          .getByTradeId(store.getStoreId(), mallRefundOrderInfo.getTradeId());
      context.setSalesOrder(salesOrder);
      if (!Assert.isNull(salesOrder)) {
        context.setSalesOrderDetails(
            salesOrderDetailService.listNormalBySalesOrderId(salesOrder.getSalesOrderId()));
        if (salesOrder.getDispatchStatus() != DispatchStatus.ALL) {
          context.setDispatchOrders(
              dispatchOrderService.listBySalesOrderId(salesOrder.getSalesOrderId()));
        }
      }
    }
  }


  /**
   * 校验.
   */
  private boolean validate(RefundTranslatableContext context) {
    SalesOrder salesOrder = context.getSalesOrder();
    if (salesOrder == null || salesOrder.getStatus() == SalesOrderStatus.INVALID) {
      return false;
    }
    return !Assert.isEmpty(context.getSalesOrderDetails());
  }

  /**
   * 新建申请.
   * 等待卖家同意：
   * 拦截订单，标记退款
   * 等待买家退货：
   * 等待卖家确认收货:
   * 标记退款
   */
  public void create(RefundTranslatableContext context) {
    MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    createRefundApply(context);
    writeLog(context, "新增退货申请,平台退款状态:{}", mallRefundOrderInfo.getApplyStatus().caption());
    if (validate(context)) {
      switch (mallRefundOrderInfo.getApplyStatus()) {
        case WAIT_SELLER_AGREE:
          boolean isIntercept = interceptDelivery(context);
          signRefund(context, isIntercept);
          break;
        case WAIT_BUYER_RETURN_GOODS:
        case WAIT_SELLER_CONFIRM_GOODS:
          signRefund(context, false);
          break;
        case SUCCESS:
          interceptDelivery(context);
          confirmRefund(context);
          break;
        default:
          break;
      }
    }
  }

  /**
   * 修改申请.
   * 等待卖家退货就标记退款
   * 拒绝或关闭就取消
   * 成功就确认
   */
  public void modify(RefundTranslatableContext context) {
    MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    if (mallRefundOrderInfo.getApplyStatus() == context.getApplyRefundOrder()
        .getMallRefundStatus()) {
      writeLog(context, "订单:{},售后申请单:{},状态:{},已被处理,不重复处理", mallRefundOrderInfo.getTradeId(),
          mallRefundOrderInfo.getRefundId(), mallRefundOrderInfo.getApplyStatus().caption());
      return;
    }
    modifyRefundApply(context);
    if (validate(context)) {
      switch (mallRefundOrderInfo.getApplyStatus()) {
        case SELLER_REFUSE_BUYER:
        case CLOSED:
          cancelApply(context);
          break;
        case SUCCESS:
          confirmRefund(context);
          break;
        default:
          break;
      }
    }
  }

  /**
   * 确认退款.
   */
  private void confirmRefund(RefundTranslatableContext context) {
    final SalesOrder salesOrder = context.getSalesOrder();
    salesOrderService
        .refund(context.getSalesOrder(), buildSalesOrderDetailBatchBO(context, salesOrder));
    salesOrder.setVersion(salesOrder.getVersion() + 1);
    writeLog(context, "修改订单明细：已退款");
    mqProducer.send(new SalesOrderAgreeRefundMessage(context.getSalesOrder().getSalesOrderId()));
  }

  /**
   * 取消申请.
   */
  private void cancelApply(RefundTranslatableContext context) {
    final SalesOrder salesOrder = context.getSalesOrder();
    salesOrderService
        .cancelRefund(context.getSalesOrder(), buildSalesOrderDetailBatchBO(context, salesOrder));
    writeLog(context, "修改订单明细为：未退款");
    salesOrder.setVersion(salesOrder.getVersion() + 1);
    mqProducer.send(new SalesOrderCancelRefundMessage(context.getSalesOrder().getSalesOrderId()));
    writeLog(context, "取消退款申请");
  }

  /**
   * 申请退款.
   */
  protected void applyRefund(RefundTranslatableContext context) {
    final SalesOrder salesOrder = context.getSalesOrder();
    salesOrderService.applyRefund(context.getSalesOrder(),
        buildSalesOrderDetailBatchBO(context, salesOrder));
    salesOrder.setVersion(salesOrder.getVersion() + 1);
    writeLog(context, "标记订单明细为：已申请退款");
  }

  private SalesOrderDetailBatchBO buildSalesOrderDetailBatchBO(RefundTranslatableContext context,
      SalesOrder salesOrder) {
    SalesOrderDetailBatchBO batchBO = new SalesOrderDetailBatchBO();
    batchBO.setVersion(context.getSalesOrder().getVersion());
    batchBO.setDetails(context.getSalesOrderDetails());
    return batchBO;
  }

  /**
   * 修改申请.
   */
  private void modifyRefundApply(RefundTranslatableContext context) {
    final RefundApplyOrder refundApplyOrder = buildModifyApply(context);
    refundApplyOrderService.modify(refundApplyOrder);
    writeLog(context, "修改退款申请单，状态为：{}", refundApplyOrder.getMallRefundStatus().caption());
  }


  /**
   * 创建申请.
   */
  protected void createRefundApply(RefundTranslatableContext context) {
    RefundApplyOrder refundApplyOrder = buildNewApply(context);
    context.setApplyRefundOrder(refundApplyOrder);
    refundApplyOrderService.create(refundApplyOrder);
  }

  /**
   * 标记退款.
   */
  protected void signRefund(RefundTranslatableContext context, boolean isIntercept) {
    Store store = context.getStore();
    applyRefund(context);
    if (isIntercept) {
      if (Assert.isTrue(context.getMallRefundOrderInfo().isHasGoodReturn())) {
        if (Assert.isTrue(store.getSetting().isTaobaoAutoAgreeRefund())) {
          agreeReturn(context);
        }
      } else {
        if (Assert.isTrue(store.getSetting().isTaobaoAutoAuditRefund())) {
          agreeRefund(context);
        }
      }
    }
  }

  /**
   * 退货退款时自动同意申请.
   */
  protected void agreeReturn(RefundTranslatableContext context) {
    final MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    final Set<String> reasons = dataDictItemService.listSetByDictId(10308L);
    if (Assert.isEmpty(reasons) || !reasons.contains(mallRefundOrderInfo.getReason())) {
      writeLog(context, "自动同意退货原因不符合自动同意配置，{}", mallRefundOrderInfo.getReason());
      return;
    }
    agree(context, "OMS自动同意退货");
  }

  private void agree(RefundTranslatableContext context, String reason) {
    RefundBridge refundBridge = mallBridgeFactory.getRefundBridge(context.getStore().getMallType());
    RefundAgreeRequest agreeRequest = new RefundAgreeRequest(context.getStore());
    agreeRequest.setRefundApplyOrder(context.getApplyRefundOrder());
    agreeRequest.setOperator(BizContext.getNickname());
    agreeRequest.setReason(reason);
    final RefundAgreeResponse response = refundBridge.agree(agreeRequest);
    if (response.isSuccess()) {
      writeLog(context, reason);
    } else {
      writeLog(context, "{}失败！{}", reason, response.getRequest());
    }
  }

  /**
   * 仅退款时自动审核退款.
   */
  protected void agreeRefund(RefundTranslatableContext context) {
    final MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    final Optional<SalesOrderDetail> optional = context.getSalesOrderDetails().stream()
        .filter(x -> x.getMallDetailId().equals(mallRefundOrderInfo.getOid()))
        .findFirst();
    if (!optional.isPresent()) {
      writeLog(context, "未找到匹配明细，不自动同意，OID:{},mallSkuId", mallRefundOrderInfo.getOid(),
          mallRefundOrderInfo.getMallSkuId());
      return;
    }
    SalesOrderDetail detail = optional.get();
    if (!detail.getQuantity().equals(mallRefundOrderInfo.getQuantity())) {
      writeLog(context, "明细数量：{}，退款数量：{}不相等，不自动同意", detail.getQuantity(),
          mallRefundOrderInfo.getQuantity());
      return;
    }
    if (!detail.getSettlementAmount().equals(mallRefundOrderInfo.getRefundAmount())) {
      writeLog(context, "明细金额：{}，退款金额：{}不相等，不自动同意", detail.getSellingAmount(),
          mallRefundOrderInfo.getRefundAmount());
      return;
    }

    final Set<String> reasons = dataDictItemService.listSetByDictId(10304L);
    if (Assert.isEmpty(reasons) || !reasons.contains(mallRefundOrderInfo.getReason())) {
      writeLog(context, "退款原因不符合自动同意配置，{}", mallRefundOrderInfo.getReason());
      return;
    }
    agree(context, "OMS自动同意仅退款");
  }

  /**
   * 拦截发货.
   * 如果有未发货的配货单就取消配货
   */
  protected boolean interceptDelivery(RefundTranslatableContext context) {
    final MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    SalesOrder salesOrder = context.getSalesOrder();
    if (!Assert.isEmpty(context.getDispatchOrders())) {
      DispatchOrderCancelBO orderCancelBO = new DispatchOrderCancelBO();
      orderCancelBO.setReason(StringUtil
          .format("申请退货，退货申请：{0}，原因：{1}", mallRefundOrderInfo.getRefundId(),
              mallRefundOrderInfo.getReason()));
      for (DispatchOrder dispatchOrder : context.getDispatchOrders()) {
        if (LOGGER.isDebugEnabled()) {
          LOGGER.debug("转化：{}，处理配货单：{}，状态：{}", context, dispatchOrder.getDispatchOrderCode(),
              dispatchOrder.getStatus().caption());
        }
        if (dispatchOrder.getStatus() == DispatchOrderStatus.DELIVERED) {
          writeLog(context, "配货单已发货,无法拦截");
          return false;
        }
        if (dispatchOrder.getStatus() == DispatchOrderStatus.CANCELED) {
          writeLog(context, "配货单已取消");
          return true;
        }
        try {
          orderCancelBO.setVersion(dispatchOrder.getVersion());
          dispatchOrderService.cancel(dispatchOrder, orderCancelBO);
          salesOrder.setVersion(salesOrder.getVersion() + 1);
          writeLog(context, "配货单{}取消成功", dispatchOrder.getDispatchOrderCode());
          return true;
        } catch (Exception ex) {
          writeLog(context, "配货单{}取消失败:{}", dispatchOrder.getDispatchOrderCode(), ex.getMessage());
          return false;
        }
      }
    }
    writeLog(context, "没有找到配货单,无法拦截");
    return true;
  }

  protected RefundApplyOrder buildModifyApply(RefundTranslatableContext context) {
    RefundApplyOrder refundApplyOrder = context.getApplyRefundOrder();
    MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    refundApplyOrder.setMallRefundPhase(mallRefundOrderInfo.getRefundPhase());
    refundApplyOrder.setMallRefundStatus(mallRefundOrderInfo.getApplyStatus());
    refundApplyOrder.setMallRefundVersion(mallRefundOrderInfo.getVersion());
    refundApplyOrder.setExpressName(mallRefundOrderInfo.getExpressName());
    refundApplyOrder.setExpressNo(mallRefundOrderInfo.getExpressNo());
    return refundApplyOrder;
  }


  protected RefundApplyOrder buildNewApply(RefundTranslatableContext context) {

    MallRefundOrderInfo mallRefundOrderInfo = context.getMallRefundOrderInfo();
    RefundApplyOrder refundApplyOrder = new RefundApplyOrder();
    refundApplyOrder.setStoreId(context.getStore().getStoreId());
    refundApplyOrder.setMallType(context.getStore().getMallType());
    refundApplyOrder.setApplyQuantity(
        Assert.isNull(mallRefundOrderInfo.getQuantity()) ? 0 : mallRefundOrderInfo.getQuantity());
    refundApplyOrder.setStoreName(context.getStore().getStoreName());
    refundApplyOrder.setMallRefundId(mallRefundOrderInfo.getRefundId());
    refundApplyOrder.setMallRefundStatus(mallRefundOrderInfo.getApplyStatus());
    refundApplyOrder.setStatus(RefundApplyOrderStatus.CREATED);
    refundApplyOrder.setApplyAmount(mallRefundOrderInfo.getRefundAmount());
    refundApplyOrder.setActualAmount(mallRefundOrderInfo.getRefundAmount());
    refundApplyOrder.setExpressName(mallRefundOrderInfo.getExpressName());
    refundApplyOrder.setExpressNo(mallRefundOrderInfo.getExpressNo());
    refundApplyOrder.setReason(mallRefundOrderInfo.getReason());
    refundApplyOrder.setAppliedTime(mallRefundOrderInfo.getRefundTime());
    refundApplyOrder.setPayAccount(mallRefundOrderInfo.getPayNo());
    refundApplyOrder.setTradeId(mallRefundOrderInfo.getTradeId());
    refundApplyOrder.setMallRefundVersion(mallRefundOrderInfo.getVersion());
    refundApplyOrder.setMallRefundPhase(mallRefundOrderInfo.getRefundPhase());
    refundApplyOrder.setMallDetailId(mallRefundOrderInfo.getOid());
    refundApplyOrder.setRefundApplyOrderType(
        mallRefundOrderInfo.isHasGoodReturn() ? RefundApplyOrderType.RETURN_REFUND
            : RefundApplyOrderType.REFUND);
    refundApplyOrder.setWhole(mallRefundOrderInfo.isWhole());

    SalesOrder salesOrder = context.getSalesOrder();
    if (salesOrder != null) {
      refundApplyOrder.setMemberName(salesOrder.getSub().getMemberName());
      refundApplyOrder.setMemberId(salesOrder.getSub().getMemberId());

      if (!mallRefundOrderInfo.isWhole() && !Assert.isNull(mallRefundOrderInfo.getOid())) {
        if (context.getSalesOrderDetails().size() == 1) {
          SalesOrderDetail detail = context.getSalesOrderDetails().get(0);
          refundApplyOrder.setInProductId(detail.getProductId());
          refundApplyOrder.setInProductCode(detail.getProductCode());
          refundApplyOrder.setInProductName(detail.getProductName());
          refundApplyOrder.setInSkuCode(detail.getSkuCode());
          refundApplyOrder.setInSkuId(detail.getSkuId());
          refundApplyOrder.setInSkuName(detail.getSkuName());
        } else if (context.getSalesOrderDetails().size() > 1) {
          //如果是组合套装 , 那就取原始明细作为 退款申请单的退入信息   (原始明细就是组合套装)
          List<SalesOrderDetail> salesOrderDetails = context.getSalesOrderDetails();
          List<SalesOrderDetail> normalDetail = salesOrderDetails.stream()
              .filter(x -> SalesOrderDetailType.ORIGINAL == x.getSalesOrderDetailType())
              .collect(Collectors.toList());
          if (!Assert.isEmpty(normalDetail)) {
            SalesOrderDetail detail = normalDetail.get(0);
            refundApplyOrder.setInProductId(detail.getProductId());
            refundApplyOrder.setInProductCode(detail.getProductCode());
            refundApplyOrder.setInProductName(detail.getProductName());
            refundApplyOrder.setInSkuCode(detail.getSkuCode());
            refundApplyOrder.setInSkuId(detail.getSkuId());
            refundApplyOrder.setInSkuName(detail.getSkuName());
          }
        }
      } else {
        List<SalesOrderDetail> salesOrderDetails = context.getSalesOrderDetails();
        Integer quantity = salesOrderDetails.stream().map(x -> x.getQuantity())
            .reduce((a, b) -> a + b).get();
        refundApplyOrder.setApplyQuantity(quantity);
      }
    }
    return refundApplyOrder;
  }
}
