package com.greatonce.oms.api.qimen.impl;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.bo.trade.WmsAutoInBO;
import com.greatonce.oms.bo.trade.WmsAutoInBO.WmsAutoInDetail;
import com.greatonce.oms.bridge.wms.qimen.QimenCustomResponseUtil;
import com.greatonce.oms.bridge.wms.qimen.QimenReturnOrderConfirmStrategy;
import com.greatonce.oms.bridge.wms.qimen.request.OmsReturnOrderConfirmRequest;
import com.greatonce.oms.bridge.wms.qimen.request.OmsReturnOrderConfirmRequest.OmsReturnOrder;
import com.greatonce.oms.bridge.wms.qimen.request.OmsReturnOrderConfirmRequest.OmsReturnOrderOrderLine;
import com.greatonce.oms.domain.enums.WmsType;
import com.greatonce.oms.domain.enums.stock.StockType;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.qimen.api.response.ReturnorderConfirmResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * B2C退货入库确认.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/23
 */
@Component
public class QimenReturnOrderConfirmImpl implements QimenReturnOrderConfirmStrategy {

  private static final Logger LOGGER = LoggerFactory.getLogger(QimenReturnOrderConfirmImpl.class);
  @Autowired
  private ReturnNoticeOrderService returnNoticeOrderService;

  @Override
  public ReturnorderConfirmResponse confirm(@RequestBody OmsReturnOrderConfirmRequest request) {
    try {
      final OmsReturnOrder returnOrder = request.getReturnOrder();

      Assert.notEmpty(request.getOrderCode(), "单据编码不能为空");
      Assert.notEmpty(returnOrder.getOrderType(), "单据类型不能为空");
      ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService
          .getByCode(request.getOrderCode());
      Assert.notNull(returnNoticeOrder, "未找到单据：" + request.getOrderCode());

      WmsAutoInBO bo = new WmsAutoInBO();
      bo.setWmsOrderCode(request.getOutCode());
      if (!Assert.isEmpty(returnOrder.getOrderConfirmTime())) {
        bo.setInTime(DateTimeUtil.parserLocalDateTime(returnOrder.getOrderConfirmTime()));
      } else {
        bo.setInTime(LocalDateTime.now());
      }
      List<OmsReturnOrderOrderLine> details = request.getOrderLines();
      List<WmsAutoInDetail> wmsAutoInDetails = new ArrayList<>();
      for (OmsReturnOrderOrderLine orderLine : details) {
        WmsAutoInDetail inDetail = new WmsAutoInDetail();
        inDetail.setSkuCode(orderLine.getItemCode());
        if (!Assert.isEmpty(orderLine.getStockInQty())) {
          inDetail.setInQuantity(Integer.parseInt(orderLine.getStockInQty()));
        } else if (!Assert.isEmpty(orderLine.getActualQty())) {
          inDetail.setInQuantity(Integer.parseInt(orderLine.getActualQty()));
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
        inDetail.setRemark(orderLine.getRemark());
        wmsAutoInDetails.add(inDetail);
      }
      bo.setDetails(wmsAutoInDetails);
      bo.setVersion(returnNoticeOrder.getVersion());
      returnNoticeOrderService.wmsAutoIn(returnNoticeOrder, bo);
      return QimenCustomResponseUtil
          .resultSuccessResponse(new ReturnorderConfirmResponse(), request.getOrderCode());
    } catch (Exception e) {
      LOGGER.error("单据编号：" + request.getOrderCode() + "，B2C退货入库确认失败！", e);
      return QimenCustomResponseUtil
          .resultFailureResponse(new ReturnorderConfirmResponse(), e.getMessage());
    }
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
