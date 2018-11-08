package com.greatonce.oms.api.qimen.impl.handler;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO.WmsAutoInDetail;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest.OmsEntryOrder;
import com.greatonce.oms.bridge.wms.qimen.request.OmsEntryOrderConfirmRequest.OmsEntryOrderOrderLine;
import com.greatonce.oms.domain.enums.stock.StockType;
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
public abstract class AbstractInOrderHandler extends AbstractOrderHandler {

  /**
   * 解析Request.
   */
  public WmsAutoInBO parseRequest(OmsEntryOrderConfirmRequest request) {
    Assert.notNull(request.getOrderLines(), "出库明细不能为空");
    WmsAutoInBO bo = new WmsAutoInBO();
    bo.setWmsOrderCode(request.getOutCode());
    OmsEntryOrder entryOrder = request.getEntryOrder();
    if (!Assert.isEmpty(entryOrder.getOperateTime())) {
      bo.setInTime(DateTimeUtil.parserLocalDateTime(entryOrder.getOperateTime()));
    } else {
      bo.setInTime(LocalDateTime.now());
    }
    bo.setOmsOrderCode(request.getOrderCode());
    bo.setWmsOrderCode(request.getOutCode());
    List<OmsEntryOrderOrderLine> details = request.getOrderLines();
    List<WmsAutoInDetail> wmsAutoInDetails = new ArrayList<>(details.size());
    for (OmsEntryOrderOrderLine orderLine : details) {
      WmsAutoInDetail inDetail = new WmsAutoInBO.WmsAutoInDetail();
      inDetail.setSkuCode(orderLine.getItemCode());
      if (orderLine.getActualQty() != null) {
        inDetail.setInQuantity(orderLine.getActualQty().intValue());
      } else if (!Assert.isEmpty(orderLine.getStockInQty())) {
        inDetail.setInQuantity(Integer.parseInt(orderLine.getStockInQty()));
      } else {
        inDetail.setInQuantity(0);
      }
      inDetail.setPendingQuantity(inDetail.getInQuantity());
      if (Assert.isEmpty(orderLine.getInventoryType()) || "ZP"
          .equalsIgnoreCase(orderLine.getInventoryType())) {
        inDetail.setStockType(StockType.QUALIFIED);
      } else {
        inDetail.setStockType(StockType.DEFECTIVE);
      }
      onParseOutDetail(request, orderLine, inDetail);
      wmsAutoInDetails.add(inDetail);
    }
    bo.setDetails(wmsAutoInDetails);
    return bo;
  }

  protected void onParseOutDetail(OmsEntryOrderConfirmRequest request,
      OmsEntryOrderOrderLine orderLine, WmsAutoInDetail outDetail) {
    //sub class do
  }
}
