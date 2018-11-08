package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.UserService;
import com.greatonce.oms.biz.mall.MallRefundOrderService;
import com.greatonce.oms.biz.trade.ExchangeApplyOrderService;
import com.greatonce.oms.biz.trade.RefundApplyOrderService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.bo.mall.MallRefundOrderInfo;
import com.greatonce.oms.bo.trade.ReturnOrderSaveBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.RefundBridge;
import com.greatonce.oms.bridge.mall.request.RefundAgreeRequest;
import com.greatonce.oms.bridge.mall.response.RefundAgreeResponse;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaReturnOrderRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaReturnOrderResponse;
import com.greatonce.oms.custom.eeka.service.EekaReturnOrderService;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.User;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.domain.trade.RefundApplyOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.util.BizContext;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EekaApiCondition
public class EekaReturnOrderController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaReturnOrderController.class);
  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.RETURN_ORDER);

  @Autowired
  private EekaReturnOrderService eekaReturnOrderService;
  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private UserService userService;
  @Autowired
  private ExchangeApplyOrderService exchangeApplyOrderService;
  @Autowired
  private RefundApplyOrderService refundApplyOrderService;
  @Autowired
  private IdGenerator idGenerator;
  @Autowired
  private DataDictItemService dataDictItemService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private MallRefundOrderService mallRefundOrderService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private ReturnOrderDetailService returnOrderDetailService;

  /**
   * 退换货单创建
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.return.order.create")
  public EekaReturnOrderResponse returnOrderCreate(HttpServletRequest servletRequest) {
    EekaReturnOrderRequest request = checkSign(servletRequest, EekaReturnOrderRequest.class);
    EekaReturnOrderResponse response = null;
    if (!Assert.isNull(request)) {
      ReturnOrderSaveBO returnOrderSaveBO = new ReturnOrderSaveBO();
      BeanUtils.copyProperties(request, returnOrderSaveBO);
      if (!Assert.isNull(returnOrderSaveBO.getCreator())) {
        User userEg = new User();
        userEg.setLoginName(returnOrderSaveBO.getCreator());
        userEg.setEnable(true);
        User user = userService.getByExample(userEg);
        if (!Assert.isNull(user)) {
          returnOrderSaveBO.setCreator(user.getNickname());
        }
      }
      try {
        //根据换货申请id获取换货申请信息
        if (!Assert.isEmpty(returnOrderSaveBO.getExchangeApplyOrders())) {
          List<ExchangeApplyOrder> exchangeApplyOrders = new ArrayList<>(
              returnOrderSaveBO.getExchangeApplyOrders().size());
          for (ExchangeApplyOrder order : returnOrderSaveBO.getExchangeApplyOrders()) {
            ExchangeApplyOrder exchangeApplyOrder = exchangeApplyOrderService
                .getByKey(order.getExchangeApplyOrderId());
            if (!Assert.isNull(exchangeApplyOrder)) {
              exchangeApplyOrders.add(exchangeApplyOrder);
            }
          }
          returnOrderSaveBO.setExchangeApplyOrders(exchangeApplyOrders);
        }
        //根据退款申请id获取退款申请信息
        if (!Assert.isEmpty(returnOrderSaveBO.getRefundApplyOrders())) {
          List<RefundApplyOrder> refundApplyOrders = new ArrayList<>(
              returnOrderSaveBO.getRefundApplyOrders().size());
          for (RefundApplyOrder order : returnOrderSaveBO.getRefundApplyOrders()) {
            RefundApplyOrder refundApplyOrder = refundApplyOrderService
                .getByKey(order.getRefundApplyOrderId());
            if (!Assert.isNull(refundApplyOrder)) {
              refundApplyOrders.add(refundApplyOrder);
            }
          }
          returnOrderSaveBO.setRefundApplyOrders(refundApplyOrders);
        }
        if(Assert.isEmpty(returnOrderSaveBO.getOutDetails())){
          returnOrderSaveBO.setOutDetails(new ArrayList<>());
        }
        eekaReturnOrderService.addReturnOrder(returnOrderSaveBO);
        //通过快递单号查询退换货单
        /*if (!Assert.isEmpty(request.getExpressNo())) {
          ReturnOrderQuery returnOrderQuery = new ReturnOrderQuery();
          returnOrderQuery.setExpressNo(request.getExpressNo());
          returnOrderQuery.setUnpacker(returnOrderSaveBO.getCreator());
          List<ReturnOrder> returnOrders = returnOrderService.list(returnOrderQuery);
          if (!Assert.isEmpty(returnOrders)) {
            for (ReturnOrder returnOrder : returnOrders) {
              final Map<String,String> returnTypeMap = dataDictItemService.listMapByDictId(10301L);
              if (returnTypeMap.get("001").equals(returnOrder.getReturnType()) && !ReturnOrderStatus.REVIEWED.equals(returnOrder.getStatus())) {
                //检查是否出现重复扫描
                returnOrder.setDetails(returnOrderDetailService.listByReturnOrderId(returnOrder.getReturnOrderId()));
                List<ReturnOrderDetail> returnOrderDetails = returnOrderService
                    .checkReview(returnOrder);
                if (Assert.isEmpty(returnOrderDetails)) {
                  //自动复核
                  VersionBO bo = new VersionBO();
                  bo.setVersion(returnOrder.getVersion());
                  returnOrderService.review(returnOrder, bo);
                  //退款的需要自动
                  List<SalesOrderDetail> salesOrderDetails = new ArrayList<>();
                  if (!Assert.isEmpty(returnOrderSaveBO.getRefundApplyOrders())) {
                    if (!Assert.isEmpty(returnOrderSaveBO.getSourceDetail())) {
                      returnOrderSaveBO.getSourceDetail().stream()
                          .map(SalesOrderDetail::getSalesOrderId).distinct().forEach(x -> {
                        List<SalesOrderDetail> salesOrderDetailList = salesOrderDetailService
                            .listBySalesOrderId(x);
                        if (!Assert.isEmpty(salesOrderDetailList)) {
                          salesOrderDetails.addAll(salesOrderDetailList);
                        }
                      });
                    }
                    for (RefundApplyOrder refundApplyOrder : returnOrderSaveBO
                        .getRefundApplyOrders()) {
                      MallRefundOrderQuery mallRefundOrderQuery = new MallRefundOrderQuery();
                      mallRefundOrderQuery.setTradeId(refundApplyOrder.getTradeId());
                      mallRefundOrderQuery.setStoreId(returnOrder.getStoreId());
                      List<MallRefundOrder> mallRefundOrders = mallRefundOrderService
                          .list(mallRefundOrderQuery);
                      if (!Assert.isEmpty(mallRefundOrders)) {
                        MallRefundOrder mallRefundOrder = mallRefundOrders.get(0);
                        MallRefundOrderInfo mallRefundOrderInfo = JsonUtil
                            .toObject(mallRefundOrder.getOrderJson(), MallRefundOrderInfo.class);
                        Store store = storeService.getByKey(returnOrder.getStoreId());
                        agreeRefund(mallRefundOrderInfo, salesOrderDetails, store,
                            refundApplyOrder);
                      }
                    }
                  }
                }
              }
            }
          }
        }*/
      } catch (Exception e) {
        LOGGER.error("生成退换货单失败,堆栈信息:", e);
        return new EekaReturnOrderResponse(apiIdGenerator.next(), e.getMessage());
      }
      response = new EekaReturnOrderResponse(apiIdGenerator.next());
    }
    if (Assert.isNull(response)) {
      response = new EekaReturnOrderResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 同意退款.
   */
  protected void agreeRefund(MallRefundOrderInfo mallRefundOrderInfo,
      List<SalesOrderDetail> salesOrderDetails, Store store, RefundApplyOrder applyRefundOrder) {
    final Optional<SalesOrderDetail> optional = salesOrderDetails.stream()
        .filter(x -> x.getMallDetailId().equals(mallRefundOrderInfo.getOid()))
        .findFirst();
    if (!optional.isPresent()) {
      writeLog(applyRefundOrder.getRefundApplyOrderId(), "未找到匹配明细，不自动同意，OID:{},mallSkuId",
          mallRefundOrderInfo.getOid());
      return;
    }
    SalesOrderDetail detail = optional.get();
    if (!detail.getQuantity().equals(mallRefundOrderInfo.getQuantity())) {
      writeLog(detail.getSalesOrderId(), "明细数量：{}，退款数量：{}不相等，不自动同意", detail.getQuantity(),
          mallRefundOrderInfo.getQuantity());
      return;
    }
    if (!detail.getSettlementAmount().equals(mallRefundOrderInfo.getRefundAmount())) {
      writeLog(detail.getSalesOrderId(), "明细金额：{}，退款金额：{}不相等，不自动同意", detail.getSellingAmount(),
          mallRefundOrderInfo.getRefundAmount());
      return;
    }

    final Set<String> reasons = dataDictItemService.listSetByDictId(10304L);
    if (Assert.isEmpty(reasons) || !reasons.contains(mallRefundOrderInfo.getReason())) {
      writeLog(detail.getSalesOrderId(), "退款原因不符合自动同意配置，{}", mallRefundOrderInfo.getReason());
      return;
    }
    RefundBridge refundBridge = mallBridgeFactory.getRefundBridge(store.getMallType());
    RefundAgreeRequest agreeRequest = new RefundAgreeRequest(store);
    agreeRequest.setRefundApplyOrder(applyRefundOrder);
    agreeRequest.setOperator(BizContext.getNickname());
    agreeRequest.setReason("OMS审核可批量退款");
    final RefundAgreeResponse response = refundBridge.agree(agreeRequest);
    if (response.isSuccess()) {
      writeLog(detail.getSalesOrderId(), "自动同意平台退款");
    } else {
      writeLog(detail.getSalesOrderId(), "自动同意平台退款失败！{}", response.getRequest());
    }
  }

  private void writeLog(Long id, String message,
      Object... args) {
    BIZ_LOGGER.log(id, "自动同意平台退款",message, args);
  }
}
