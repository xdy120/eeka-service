package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.trade.WmsAutoOutBO;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutDetail;
import com.greatonce.oms.bo.trade.WmsAutoOutBO.WmsAutoOutPackage;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest.OmsStockOutOrder;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest.OmsStockOutOrderLine;
import com.greatonce.oms.bridge.wms.qimen.request.OmsStockOutConfirmRequest.OmsStockOutOrderPackage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * 出库单抽象实现.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-08-21
 */
public abstract class AbstractOutOrderHandler extends AbstractOrderHandler {

  /**
   * 解析Request.
   */
  public WmsAutoOutBO parseRequest(OmsStockOutConfirmRequest request) {
    Assert.notNull(request.getOrderLines(), "出库明细不能为空");
    OmsStockOutOrder deliveryOrder = request.getDeliveryOrder();

    WmsAutoOutBO bo = new WmsAutoOutBO();
    bo.setWmsOrderCode(request.getOutCode());
    bo.setOmsOrderCode(request.getOrderCode());

    if (!Assert.isEmpty(deliveryOrder.getOrderConfirmTime())) {
      bo.setOutTime(DateTimeUtil.parserLocalDateTime(deliveryOrder.getOrderConfirmTime()));
    } else if (!Assert.isEmpty(deliveryOrder.getOperateTime())) {
      bo.setOutTime(DateTimeUtil.parserLocalDateTime(deliveryOrder.getOperateTime()));
    } else if (!Assert.isEmpty(deliveryOrder.getOperateTime())) {
      bo.setOutTime(DateTimeUtil.parserLocalDateTime(deliveryOrder.getModifiedTime()));
    } else {
      bo.setOutTime(LocalDateTime.now());
    }

    List<WmsAutoOutPackage> packages = new ArrayList<>(1);
    if (Assert.isNull(request.getPackages())) {

      WmsAutoOutBO.WmsAutoOutPackage outPackage = new WmsAutoOutBO.WmsAutoOutPackage();
      outPackage.setWmsExpressCode(deliveryOrder.getLogisticsCode());
      outPackage.setExpressNo(deliveryOrder.getExpressCode());
      packages.add(outPackage);
    } else {
      List<OmsStockOutOrderPackage> orderPackages = request.getPackages();
      for (OmsStockOutOrderPackage orderPackage : orderPackages) {
        WmsAutoOutBO.WmsAutoOutPackage outPackage = new WmsAutoOutBO.WmsAutoOutPackage();
        outPackage.setWmsExpressCode(orderPackage.getLogisticsCode());
        outPackage.setExpressNo(orderPackage.getExpressCode());
        packages.add(outPackage);
      }
    }
    bo.setPackages(packages);
    List<OmsStockOutOrderLine> details = request.getOrderLines();
    List<WmsAutoOutDetail> outDetails = new ArrayList<>();
    for (OmsStockOutOrderLine orderLine : details) {
      WmsAutoOutDetail outDetail = new WmsAutoOutDetail();
      outDetail.setSkuCode(orderLine.getItemCode());
      if (!Assert.isEmpty(orderLine.getActualQty())) {
        outDetail.setOutQuantity(Integer.parseInt(orderLine.getActualQty()));
      } else if (!Assert.isEmpty(orderLine.getStockOutQty())) {
        outDetail.setOutQuantity(Integer.parseInt(orderLine.getStockOutQty()));
      } else {
        outDetail.setOutQuantity(0);
      }
      onParseOutDetail(request, orderLine, outDetail);
      outDetails.add(outDetail);
    }
    bo.setDetails(outDetails);
    return bo;
  }

  protected void onParseOutDetail(OmsStockOutConfirmRequest request,
      OmsStockOutOrderLine orderLine, WmsAutoOutDetail outDetail) {
    //sub class do
  }
}
