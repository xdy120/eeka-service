package com.greatonce.oms.web.controller.trade;

import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.bo.trade.DispatchOrderCancelDetailBO;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/dispatch/{dispatchOrderId}/detail")
@CrossOrigin
public class DispatchOrderDetailController {

  @Autowired
  DispatchOrderService dispatchOrderService;
  @Autowired
  DispatchOrderDetailService dispatchOrderDetailService;

  @GetMapping
  public List<DispatchOrderDetail> list(@PathVariable("dispatchOrderId") Long dispatchOrderId) {
    return dispatchOrderDetailService.listExample(new DispatchOrderDetail() {{
      setDispatchOrderId(dispatchOrderId);
    }});
  }

  @PutMapping(path = "/{dispatchOrderDetailId}/invalid")
  public void cancel(@PathVariable("dispatchOrderId") Long dispatchOrderId,
      @PathVariable("dispatchOrderDetailId") Long dispatchOrderDetailId,
      @RequestBody DispatchOrderCancelDetailBO bo) {
    DispatchOrder dispatchOrder = dispatchOrderService.getSimpleInfo(dispatchOrderId);
    DispatchOrderDetail detail = dispatchOrderDetailService.getByKey(dispatchOrderDetailId);
    Assert.notNull(detail, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);
    bo.setDetail(detail);
    dispatchOrderService.cancel(dispatchOrder, bo);
  }
}
