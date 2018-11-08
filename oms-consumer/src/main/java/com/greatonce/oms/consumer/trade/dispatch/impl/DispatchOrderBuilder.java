package com.greatonce.oms.consumer.trade.dispatch.impl;

import com.greatonce.core.sequence.CodeGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.consumer.trade.dispatch.DispatchContext;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderBuildable;
import com.greatonce.oms.consumer.trade.dispatch.DispatchOrderCondition;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderDetailWrapper;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.DispatchOrderWrapper;
import com.greatonce.oms.domain.enums.OutStatus;
import com.greatonce.oms.domain.enums.WmsOrderStatus;
import com.greatonce.oms.domain.enums.trade.DispatchOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.InvoiceInfo;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderInvoice;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.DispatchLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 配货单生成器
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/24
 */
@Component
@DispatchOrderCondition
public class DispatchOrderBuilder implements DispatchOrderBuildable {

  private static final DispatchLogger LOGGER = OmsLoggerFactory.getDispatchLogger();
  @Resource
  private CodeGenerator dispatchOrderCodeGenerator;
  @Autowired
  private SalesOrderService salesOrderService;

  @Override
  public DispatchOrderWrapper build(DispatchContext context) {
    SalesOrder salesOrder = context.getMainSalesOrder();
    DispatchOrderWrapper orderWrapper = createOrder(salesOrder);
    orderWrapper.setDetails(createDetails(orderWrapper, context));
    setAdditionalDispatchInfo(context, orderWrapper);
    if (context.isHasDesignatedExpress()) {
      LOGGER.info(context.getSerialNo(), context.getStore(),
          context.getMainSalesOrder().getSalesOrderCode(),
          context.getMainSalesOrder().getTradeId(), "配货单有指定快递：{}",
          orderWrapper.getSuggestExpressName());
    }
    return orderWrapper;
  }

  /**
   * 生成配货单主信息.
   */
  private DispatchOrderWrapper createOrder(SalesOrder salesOrder) {
    SalesOrderSub sub = salesOrder.getSub();
    DispatchOrder dispatchOrder = new DispatchOrder();

    dispatchOrder.setDispatchOrderCode(dispatchOrderCodeGenerator.next());
    dispatchOrder.setOfflineDelivery(false);
    dispatchOrder.setStoreId(salesOrder.getStoreId());
    dispatchOrder.setStoreName(salesOrder.getStoreName());
    dispatchOrder.setBuyerMemo(sub.getBuyerMemo());
    dispatchOrder.setSellerMemo(sub.getSellerMemo());
    dispatchOrder.setMemberId(sub.getMemberId());
    dispatchOrder.setMemberName(sub.getMemberName());
    //收货信息
    dispatchOrder.setContact(salesOrder.getContact());
    dispatchOrder.setTelephone(salesOrder.getTelephone());
    dispatchOrder.setMobile(salesOrder.getMobile());
    dispatchOrder.setCountryId(salesOrder.getCountryId());
    dispatchOrder.setProvinceId(salesOrder.getProvinceId());
    dispatchOrder.setCityId(salesOrder.getCityId());
    dispatchOrder.setDistrictId(salesOrder.getDistrictId());
    dispatchOrder.setCountryName(salesOrder.getCountryName());
    dispatchOrder.setProvinceName(salesOrder.getProvinceName());
    dispatchOrder.setCityName(salesOrder.getCityName());
    dispatchOrder.setDistrictName(salesOrder.getDistrictName());
    dispatchOrder.setAddress(salesOrder.getAddress());
    dispatchOrder.setZipcode(sub.getZipcode());
    dispatchOrder.setSuggestExpressId(salesOrder.getSuggestExpressId());
    dispatchOrder.setSuggestExpressName(salesOrder.getSuggestExpressName());
    dispatchOrder.setSuggestExpressNo(salesOrder.getSuggestExpressNo());
    //金额初始化
    dispatchOrder.setActualAmount(0D);
    dispatchOrder.setDiscountAmount(0D);
    dispatchOrder.setDistributionAmount(0D);
    dispatchOrder.setSellingAmount(0D);
    dispatchOrder.setSettlementAmount(0D);
    dispatchOrder.setCod(salesOrder.getSub().isCod());
    dispatchOrder.setCodAmount(salesOrder.getCodAmount());
    //状态初始化
    dispatchOrder.setCod(sub.isCod());
    dispatchOrder.setUrgent(salesOrder.isUrgent());
    dispatchOrder.setMerge(false);
    dispatchOrder.setHasExchange(sub.getSalesOrderType() == SalesOrderType.EXCHANGE);
    dispatchOrder.setWmsCancel(false);
    dispatchOrder.setDeliveryFinish(false);
    dispatchOrder.setStatus(DispatchOrderStatus.CREATED);
    dispatchOrder.setOutStatus(OutStatus.NO_OUT);
    dispatchOrder.setWmsStatus(WmsOrderStatus.ACCEPT);
    //封装包装实例
    return new DispatchOrderWrapper(dispatchOrder, salesOrder);
  }

  /**
   * 生成配货单明细列表.
   */
  private List<DispatchOrderDetail> createDetails(DispatchOrderWrapper dispatchOrder,
      DispatchContext context) {
    SalesOrder mainSalesOrder = context.getMainSalesOrder();
    List<SalesOrder> salesOrders = context.getSalesOrders();
    List<DispatchOrderDetail> details = new ArrayList<>(mainSalesOrder.getDetails().size());
    //主订单明细创建配货单明细
    double weights = 0D;
    int quantity = 0;
    for (SalesOrderDetail mainSalesOrderDetail : mainSalesOrder.getDetails()) {
      DispatchOrderDetail mainDispatchOrderDetail = createDetail(mainSalesOrderDetail,
          mainSalesOrder, mainSalesOrder.getSub(), true);
      weights +=
          Assert.isNull(mainSalesOrderDetail.getWeight()) ? 0D : mainSalesOrderDetail.getWeight();
      quantity += mainDispatchOrderDetail.getNoticeQuantity();
      details.add(mainDispatchOrderDetail);
    }

    if (!Assert.isEmpty(salesOrders)) {
      dispatchOrder.setMerged(true);
      //合单订单创建配货单明细
      for (SalesOrder salesOrder : salesOrders) {
        for (SalesOrderDetail salesOrderDetail : salesOrder.getDetails()) {
          DispatchOrderDetail mergeDetail = createDetail(salesOrderDetail, salesOrder,
              salesOrder.getSub(), false);
          quantity += mergeDetail.getNoticeQuantity();
          weights +=
              Assert.isNull(salesOrderDetail.getWeight()) ? 0D : salesOrderDetail.getWeight();
          details.add(mergeDetail);
        }
        if (salesOrder.getSub().getSalesOrderType() == SalesOrderType.EXCHANGE
            && Assert.isFalse(dispatchOrder.isHasExchange())) {
          dispatchOrder.setHasExchange(true);
        }
      }
    }
    dispatchOrder.setQuantity(quantity);
    dispatchOrder.setWeight(weights);
    return details;
  }

  /**
   * 生成配货单明细.
   *
   */
  private DispatchOrderDetail createDetail(SalesOrderDetail salesOrderDetail, SalesOrder salesOrder,
      SalesOrderSub salesOrderSub, boolean isMainOrderDetail) {
    DispatchOrderDetail dispatchOrderDetail = new DispatchOrderDetail();

    dispatchOrderDetail.setStatus(DispatchOrderDetailStatus.WAITING);
    //配货单信息
    dispatchOrderDetail.setSalesOrderCode(salesOrder.getSalesOrderCode());
    dispatchOrderDetail.setTradeId(salesOrder.getTradeId());
    dispatchOrderDetail.setSalesOrderId(salesOrder.getSalesOrderId());
    dispatchOrderDetail.setSalesOrderDetailId(salesOrderDetail.getSalesOrderDetailId());
    dispatchOrderDetail.setHasInvoice(salesOrderSub.isHasInvoice());
    //商品信息
    dispatchOrderDetail.setProductId(salesOrderDetail.getProductId());
    dispatchOrderDetail.setProductCode(salesOrderDetail.getProductCode());
    dispatchOrderDetail.setProductName(salesOrderDetail.getProductName());
    dispatchOrderDetail.setSkuId(salesOrderDetail.getSkuId());
    dispatchOrderDetail.setSkuCode(salesOrderDetail.getSkuCode());
    dispatchOrderDetail.setSkuName(salesOrderDetail.getSkuName());
    dispatchOrderDetail.setNoticeQuantity(salesOrderDetail.getQuantity());
    dispatchOrderDetail.setOutQuantity(0);
    //价格信息
    dispatchOrderDetail.setSellingPrice(salesOrderDetail.getSellingPrice());
    dispatchOrderDetail.setDistributionPrice(salesOrderDetail.getDistributionPrice());
    dispatchOrderDetail.setSettlementPrice(salesOrderDetail.getSettlementPrice());
    //金额信息
    dispatchOrderDetail.setSellingAmount(salesOrderDetail.getSellingAmount());
    dispatchOrderDetail.setDiscountAmount(salesOrderDetail.getDiscountAmount());
    dispatchOrderDetail.setActualAmount(salesOrderDetail.getActualAmount());
    dispatchOrderDetail.setDistributionAmount(salesOrderDetail.getDistributionAmount());
    dispatchOrderDetail.setSettlementAmount(salesOrderDetail.getSettlementAmount());
    dispatchOrderDetail.setVirtualWarehouseId(salesOrderDetail.getSuggestVirtualWarehouseId());
    dispatchOrderDetail.setVirtualWarehouseName(salesOrderDetail.getSuggestVirtualWarehouseName());
    return new DispatchOrderDetailWrapper(dispatchOrderDetail, salesOrder, salesOrderDetail,
        isMainOrderDetail);
  }

  /**
   * 完善配货信息.
   */
  private void setAdditionalDispatchInfo(DispatchContext context,
      DispatchOrderWrapper dispatchOrder) {
    dispatchOrder.getDetails().forEach(detail -> {
      //金额信息
      dispatchOrder.setActualAmount(dispatchOrder.getActualAmount() + detail.getActualAmount());
      dispatchOrder
          .setDiscountAmount(dispatchOrder.getDiscountAmount() + detail.getDiscountAmount());
      dispatchOrder.setDistributionAmount(
          dispatchOrder.getDistributionAmount() + detail.getDistributionAmount());
      dispatchOrder.setSellingAmount(dispatchOrder.getSellingAmount() + detail.getSellingAmount());
      dispatchOrder
          .setSettlementAmount(dispatchOrder.getSettlementAmount() + detail.getSettlementAmount());
    });

    //发票信息
    SalesOrder invoiceOrder = null;
    if (!Assert.isEmpty(context.getMainSalesOrder().getInvoices())) {
      invoiceOrder = context.getMainSalesOrder();
    } else {
      for (SalesOrder salesOrder : context.getSalesOrders()) {
        if (!Assert.isEmpty(salesOrder.getInvoices())) {
          invoiceOrder = salesOrder;
          break;
        }
      }
    }
    if (invoiceOrder != null) {
      if (invoiceOrder.getInvoices().size() > 1) {
        salesOrderService.getBizLogger().log(context.getMainSalesOrder().getSalesOrderId(),
            BizLogger.DISPATCH, "订单的发票数大于1张，配货目前只支持1张发票。");
      }
      SalesOrderInvoice salesOrderInvoice = invoiceOrder.getInvoices().get(0);
      InvoiceInfo invoiceInfo = new InvoiceInfo();
      invoiceInfo.setModifiedTime(salesOrderInvoice.getModifiedTime());
      invoiceInfo.setCreatedTime(salesOrderInvoice.getCreatedTime());
      invoiceInfo.setInvoiceTitle(salesOrderInvoice.getInvoiceTitle());
      invoiceInfo.setInvoiceContent(salesOrderInvoice.getInvoiceContent());
      invoiceInfo.setInvoiceType(salesOrderInvoice.getInvoiceType());
      invoiceInfo.setActualAmount(dispatchOrder.getActualAmount());
      invoiceInfo.setTaxpayerId(salesOrderInvoice.getTaxpayerId());
      invoiceInfo.setGmfAddress(salesOrderInvoice.getGmfAddress());
      invoiceInfo.setGmfBankName(salesOrderInvoice.getGmfBankName());
      invoiceInfo.setGmfBankNo(salesOrderInvoice.getGmfBankNo());
      invoiceInfo.setGmfMobile(salesOrderInvoice.getGmfMobile());
      dispatchOrder.setNeedInvoice(true);
      dispatchOrder.setInvoiceInfo(invoiceInfo);
    }
  }
}
