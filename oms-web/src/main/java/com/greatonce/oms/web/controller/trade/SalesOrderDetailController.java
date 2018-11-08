package com.greatonce.oms.web.controller.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.impl.trade.SalesOrderUtil;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.trade.SalesOrderDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailSplitBO;
import com.greatonce.oms.bo.trade.SalesOrderReplaceDetailBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/sales/{salesOrderId}/detail")
@CrossOrigin
public class SalesOrderDetailController {

  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;

  @GetMapping
  public List<SalesOrderDetail> list(@PathVariable("salesOrderId") Long salesOrderId) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrderId);
    eg.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    return salesOrderDetailService.listExample(eg);
  }

  @GetMapping(path = "/delivered")
  public List<SalesOrderDetail> listDelivered(@PathVariable("salesOrderId") Long salesOrderId) {
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrderId);
    eg.setStatus(SalesOrderDetailStatus.DELIVERED);
    eg.setSalesOrderDetailType(SalesOrderDetailType.NORMAL);
    return salesOrderDetailService.listExample(eg);
  }

  @PutMapping(path = "/{salesOrderDetailId}/invalid")
  public void invalid(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderDetail detail = salesOrderDetailService.getByKey(salesOrderDetailId);
    Assert.notNull(detail, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);
    bo.setDetail(detail);
    salesOrderService.invalid(salesOrder, bo);
  }


  @PutMapping(path = "/{salesOrderDetailId}/applyRefund")
  public void applyRefund(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    SalesOrderDetail detail = salesOrderDetailService.getByKey(salesOrderDetailId);
    Assert.notNull(detail, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);
    SalesOrderUtil.mustBeforeDispatch(detail);
    bo.setDetail(detail);
    salesOrderService.applyRefund(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderDetailId}/refund")
  public void refund(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderDetailBO bo) {
    SalesOrderDetail detail = salesOrderDetailService.getByKey(salesOrderDetailId);
    SalesOrderUtil.mustBeforeDispatch(detail);

    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderUtil.mustBeforeAllDelivery(salesOrder);
    Assert.notNull(detail, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);

    bo.setDetail(detail);
    salesOrderService.refund(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderDetailId}/cancelRefund")
  public void cancelRefund(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderDetail detail = salesOrderDetailService.getByKey(salesOrderDetailId);
    Assert.notNull(detail, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);
    bo.setDetail(detail);
    salesOrderService.cancelRefund(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderDetailId}/replace")
  public void replace(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderReplaceDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    SalesOrderDetail detail = salesOrderDetailService.getByKey(salesOrderDetailId);
    Assert.notNull(detail, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);
    bo.setSourceDetail(detail);
    salesOrderService.replaceDetail(salesOrder, bo, false);
  }

  @PutMapping(path = "/{salesOrderDetailId}/matchProduct")
  public void matchProduct(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderDetail detail = salesOrderDetailService.getByKey(salesOrderDetailId);
    List<SalesOrderDetail> details = new ArrayList<>(1);
    details.add(detail);
    salesOrder.setDetails(details);
    salesOrderService.matchProduct(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderDetailId}/manualDropShipping")
  public void manualDropShipping(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderDetail detail = salesOrderDetailService.getByKey(salesOrderDetailId);
    Assert.notNull(detail, SysExceptions.DATA_NOT_FOUND);
    List<SalesOrderDetail> details = new ArrayList<>(1);
    details.add(detail);
    bo.setDetail(detail);
    salesOrder.setDetails(details);
    salesOrderService.manualDetailDropShipping(salesOrder, bo);
  }

  @PostMapping(path = "/{salesOrderDetailId}/split")
  public void split(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("salesOrderDetailId") Long salesOrderDetailId,
      @RequestBody SalesOrderDetailSplitBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderDetail sourceDetail = salesOrderDetailService.getByKey(salesOrderDetailId);
    Assert.notNull(sourceDetail, SysExceptions.DATA_NOT_FOUND);
    int count = 0;
    for (SalesOrderDetail splitDetail : bo.getSplitDetails()) {
      if (splitDetail.getQuantity() == 0) {
        throw new OmsException("明细数量不能为0！");
      }
      count += splitDetail.getQuantity();
    }
    if (!sourceDetail.getQuantity().equals(count)) {
      throw new OmsException("拆分出来的明细总数量与被拆分明细数量不相等！");
    }
    if (bo.getSplitDetails().stream()
        .noneMatch(x -> x.getSkuId().equals(sourceDetail.getSkuId()))) {
      throw new OmsException("明细拆分时不能删除原明细！");
    }
    List<SalesOrderDetail> details = salesOrderDetailService.listSimpleBySalesOrderId(salesOrderId);
    boolean duplicating = bo.getSplitDetails().stream()
        .filter(x -> !x.getSkuId().equals(sourceDetail.getSkuId()))
        .anyMatch(z -> details.stream().anyMatch(a -> z.getSkuId().equals(a.getSkuId())));
    if (duplicating) {
      throw new OmsException("拆分的明细在订单明细中已存在！");
    }
    salesOrderService.splitDetail(salesOrder, sourceDetail, bo);
  }
}
