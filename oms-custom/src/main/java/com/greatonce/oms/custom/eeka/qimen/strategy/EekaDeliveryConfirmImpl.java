package com.greatonce.oms.custom.eeka.qimen.strategy;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.ConvertUtil;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutDetail;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutPackage;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.QimenDeliveryConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsDeliveryOrderConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsDeliveryOrderConfirmRequest.OmsDeliveryOrder;
import com.greatonce.oms.bridge.wms.qimen.request.OmsDeliveryOrderConfirmRequest.OmsDeliveryOrderOrderLine;
import com.greatonce.oms.bridge.wms.qimen.request.OmsDeliveryOrderConfirmRequest.OmsDeliveryOrderPackage;
import com.greatonce.oms.bridge.wms.qimen.request.OmsDeliveryOrderConfirmRequest.OmsDeliveryOrderPackageItem;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.qimen.api.response.DeliveryorderConfirmResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EekaApiCondition
public class EekaDeliveryConfirmImpl implements QimenDeliveryConfirmStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(EekaDeliveryConfirmImpl.class);
  @Autowired
  private DispatchOrderService dispatchOrderService;

  @Override
  public DeliveryorderConfirmResponse confirm(OmsDeliveryOrderConfirmRequest request) {
    try {
      final OmsDeliveryOrder deliveryOrder = request.getDeliveryOrder();
      Assert.notEmpty(request.getOrderCode(), "单据编码不能为空");
      Assert.notEmpty(request.getDeliveryOrder().getOrderType(), "单据类型不能为空");

      String dispatchOrderCode = deliveryOrder.getDeliveryOrderCode();
      DispatchOrder dispatchOrder = dispatchOrderService.getByCode(dispatchOrderCode);
      Assert.notNull(dispatchOrder, "单据未找到：" + dispatchOrderCode);
      if (dispatchOrder.isOfflineDelivery()) {
        Assert.notNull(request.getWarehouseCode(), "线下发货仓库编码不能为空！");
      }
      WmsAutoOutBO bo = new WmsAutoOutBO();
      bo.setDeliveryFinish(
          deliveryOrder.getConfirmType() == null || deliveryOrder.getConfirmType().equals(0L));
      bo.setWmsOrderCode(request.getOutCode());
      bo.setDeliveryWarehouseCode(deliveryOrder.getWarehouseCode());
      bo.setVersion(dispatchOrder.getVersion());
      List<WmsAutoOutPackage> packages = new ArrayList<>(request.getPackages().size());
      List<OmsDeliveryOrderPackage> omsDeliveryOrderPackages = request.getPackages();
      boolean usePackageDelivery = false;
      for (OmsDeliveryOrderPackage orderPackage : omsDeliveryOrderPackages) {
        Assert.notNull(orderPackage.getLogisticsCode(), "快递公司不能为空");
        Assert.notNull(orderPackage.getExpressCode(), "快递编码不能为空");
        WmsAutoOutPackage outPackage = new WmsAutoOutPackage();
        outPackage.setDeliveryTime(Assert.isNull(request.getDeliveryOrder()) ? LocalDateTime.now() : DateTimeUtil
            .parserLocalDateTime(request.getDeliveryOrder().getOrderConfirmTime()));
        outPackage.setWmsExpressCode(orderPackage.getLogisticsCode());
        outPackage.setExpressNo(orderPackage.getExpressCode());
        packages.add(outPackage);

        if (!Assert.isEmpty(orderPackage.getItems())) {
          List<WmsAutoOutDetail> outDetails = new ArrayList<>(orderPackage.getItems().size());
          for (OmsDeliveryOrderPackageItem item : orderPackage.getItems()) {
            WmsAutoOutDetail detail = new WmsAutoOutDetail();
            detail.setSkuCode(item.getItemCode());
            detail.setOutQuantity(ConvertUtil.toInt(item.getQuantity()));
            outDetails.add(detail);
          }
          outPackage.setDetails(outDetails);
          usePackageDelivery = true;
        }
      }
      if (!usePackageDelivery && !Assert.isEmpty(request.getOrderLines())) {
        List<WmsAutoOutDetail> details = new ArrayList<>(request.getOrderLines().size());
        for (OmsDeliveryOrderOrderLine orderLine : request.getOrderLines()) {
          WmsAutoOutDetail detail = new WmsAutoOutDetail();
          detail.setSkuCode(orderLine.getItemCode());
          detail.setOutQuantity(ConvertUtil.toInt(orderLine.getStockOutQty()));
          details.add(detail);
        }
        bo.setDetails(details);
      }
      bo.setPackages(packages);
      dispatchOrderService.wmsAutoDelivery(dispatchOrder, bo);
      return QimenCustomResponseUtil
          .resultSuccessResponse(new DeliveryorderConfirmResponse(), request.getOwnerCode());
    } catch (Exception ex) {
      LOGGER.error("配货单发货失败：{},{}", request.getOrderCode(), JsonUtil.toJson(request));
      LOGGER.error("配货单发货失败：堆栈", ex);
      return QimenCustomResponseUtil
          .resultFailureResponse(new DeliveryorderConfirmResponse(), ex.getMessage());
    }
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN_EEKA};
  }
}
