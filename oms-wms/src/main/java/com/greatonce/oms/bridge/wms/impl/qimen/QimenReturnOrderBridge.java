package com.greatonce.oms.bridge.wms.impl.qimen;

import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.bridge.wms.WmsException;
import com.greatonce.oms.bridge.wms.WmsUtil;
import com.greatonce.oms.bridge.wms.impl.AbstractReturnOrderBridge;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenConverter;
import com.greatonce.oms.bridge.wms.impl.qimen.convert.QimenReturnOrderType;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCancelRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.ReturnOrderQueryRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCancelResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCreateResponse;
import com.greatonce.oms.bridge.wms.response.ReturnOrderQueryResponse;
import com.greatonce.oms.domain.enums.WmsType;
import com.qimen.api.request.ReturnorderCreateRequest;
import com.qimen.api.request.ReturnorderCreateRequest.OrderLine;
import com.qimen.api.request.ReturnorderCreateRequest.ReturnOrder;
import com.qimen.api.request.ReturnorderCreateRequest.SenderInfo;
import com.qimen.api.request.ReturnorderQueryRequest;
import com.qimen.api.response.ReturnorderCreateResponse;
import com.qimen.api.response.ReturnorderQueryResponse;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 奇门退货单接口.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018-08-21
 */
@Component
public class QimenReturnOrderBridge extends AbstractReturnOrderBridge {

  @Autowired
  protected QimenWms qimenWms;

  /**
   * 查询退货单.
   *
   * @link taobao.qimen.returnorder.query
   */
  @Override
  protected ReturnOrderQueryResponse doQueryOrder(ReturnOrderQueryRequest request) {
    try {
      ReturnorderQueryRequest req = new ReturnorderQueryRequest();
      req.setReturnOrderCode(request.getOmsCode());
      req.setReturnOrderId(request.getWmsCode());
      final ReturnorderQueryResponse rsp = qimenWms.call(request.getWarehouse(), req);
      final ReturnOrderQueryResponse response = new ReturnOrderQueryResponse(request);
      if (rsp.getReturnOrder() != null) {
        response.setExists(true);
      }
      return response;
    } catch (Exception e) {
      return new ReturnOrderQueryResponse(request, false, e.getMessage());
    }
  }

  /**
   * 创建退货单.
   *
   * @link taobao.qimen.returnorder.create
   */
  @Override
  protected ReturnOrderCreateResponse doCreateOrder(ReturnOrderCreateRequest request) {
    try {
      ReturnorderCreateRequest req = new ReturnorderCreateRequest();
      ReturnOrder returnOrder = buildReturnOrder(request);
      req.setReturnOrder(returnOrder);
      req.setOrderLines(buildOrderLines(request));
      req.setExtendProps(buildExtendProps(request));
      ReturnorderCreateResponse res = qimenWms.call(request.getWarehouse(), req);
      return new ReturnOrderCreateResponse(request, res.getReturnOrderId());
    } catch (WmsException e) {
      return new ReturnOrderCreateResponse(request, false, e.getMessage());
    }
  }

  protected Map buildExtendProps(ReturnOrderCreateRequest request) {
    return request.getExtendProps();
  }

  protected ArrayList<OrderLine> buildOrderLines(ReturnOrderCreateRequest request) {
    ArrayList<OrderLine> orderLines = new ArrayList<>(request.getDetails().size());
    int index = 1;
    for (ReturnOrderCreateRequest.ReturnOrderDetail detail : request.getDetails()) {
      if (detail.getQuantity() == 0) {
        continue;
      }
      OrderLine orderLine = new OrderLine();
      orderLine.setOrderSourceCode(detail.getTradeId());
      orderLine.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      orderLine.setItemCode(detail.getSkuCode());
      orderLine.setItemId(detail.getWmsSkuId());
      orderLine.setPlanQty((long) detail.getQuantity());
      orderLine.setActualPrice(String.valueOf(detail.getPrice()));
      orderLine.setSettlementAmount(String.valueOf(detail.getAmount()));
      orderLine.setOrderLineNo(String.valueOf(index++));
      orderLines.add(orderLine);
    }
    return orderLines;
  }

  protected ReturnOrder buildReturnOrder(ReturnOrderCreateRequest request) {
    ReturnOrder returnOrder = new ReturnOrder();
    returnOrder.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
    returnOrder.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
    returnOrder.setReturnOrderCode(request.getOmsCode());
    returnOrder.setOrderType(QimenConverter.toReturnOrderTypeString(request.getOrderType()));
    returnOrder.setPreDeliveryOrderCode(request.getSourceCode());
    returnOrder.setPreDeliveryOrderId(request.getWmsCode());
    returnOrder.setLogisticsCode(request.getExpressCode());
    returnOrder.setLogisticsName(request.getExpressName());
    returnOrder.setExpressCode(request.getExpressNo());
    returnOrder.setReturnReason(request.getReason());
    returnOrder.setBuyerNick(request.getBuyerNick());
    returnOrder.setRemark(request.getRemark());
    returnOrder.setOrderConfirmTime(DateTimeUtil.format(request.getCreatedTime()));

    returnOrder.setSenderInfo(buildSenderInfo(request));
    return returnOrder;
  }

  protected SenderInfo buildSenderInfo(ReturnOrderCreateRequest request) {
    SenderInfo senderInfo = new SenderInfo();
    senderInfo.setRemark(WmsUtil.valueOrEmpty(request.getSenderRemark()));
    senderInfo.setCompany(WmsUtil.valueOrEmpty(request.getSenderCompany()));
    senderInfo.setName(WmsUtil.valueOrEmpty(request.getSenderName()));
    senderInfo.setZipCode(WmsUtil.valueOrEmpty(request.getSenderZipCode()));
    senderInfo.setTel(WmsUtil.valueOrEmpty(request.getSenderTelephone()));
    senderInfo.setMobile(WmsUtil.valueOrEmpty(request.getSenderMobile()));
    senderInfo.setEmail(WmsUtil.valueOrEmpty(request.getSenderEmail()));
    senderInfo.setCountryCode(WmsUtil.valueOrEmpty(request.getSenderCountry()));
    senderInfo.setProvince(request.getSenderProvince());
    senderInfo.setCity(WmsUtil.valueOrEmpty(request.getSenderCity()));
    senderInfo.setArea(WmsUtil.valueOrEmpty(request.getSenderArea()));
    senderInfo.setTown(WmsUtil.valueOrEmpty(request.getSenderTown()));
    senderInfo.setDetailAddress(request.getSenderAddress());
    return senderInfo;
  }

  /**
   * 取消退货单.
   *
   * @link taobao.qimen.order.cancel
   */
  @Override
  protected ReturnOrderCancelResponse doCancelOrder(ReturnOrderCancelRequest request) {
    try {
      com.qimen.api.request.OrderCancelRequest req = new com.qimen.api.request.OrderCancelRequest();
      req.setCustomerId(request.getWarehouse().getWmsApp().getCustomerId());
      req.setWarehouseCode(WmsUtil.getWmsWarehouseCode(request.getWarehouse()));
      req.setOwnerCode(WmsUtil.getWmsOwnerCode(request.getWarehouse()));
      req.setOrderCode(request.getOmsCode());
      req.setOrderId(request.getWmsCode());
      req.setOrderType(QimenReturnOrderType.THRK.toString());
      req.setCancelReason(request.getReason());
      qimenWms.call(request.getWarehouse(), req);
      return new ReturnOrderCancelResponse(request);
    } catch (WmsException e) {
      return new ReturnOrderCancelResponse(request, false, e.getMessage());
    }
  }

  @Override
  public WmsType[] supports() {
    return new WmsType[]{WmsType.QIMEN};
  }
}
