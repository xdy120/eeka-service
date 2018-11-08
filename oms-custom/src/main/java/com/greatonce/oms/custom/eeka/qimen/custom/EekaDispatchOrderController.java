package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaDispatchOrderCancelRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaDispatchOrderCancelResponse;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.bo.trade.DispatchOrderCancelBO;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import com.greatonce.oms.domain.trade.DispatchOrder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
@RestController
@EekaApiCondition
public class EekaDispatchOrderController extends QimenCustomController {

  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Resource
  private IdGenerator apiIdGenerator;

  /**
   * 配货单取消
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.dispatch.order.cancel")
  public EekaDispatchOrderCancelResponse cancel(HttpServletRequest servletRequest) {
    EekaDispatchOrderCancelRequest request = checkSign(servletRequest,
        EekaDispatchOrderCancelRequest.class);
    if (request == null || request.getDispatchOrderCode() == null) {
      return new EekaDispatchOrderCancelResponse(apiIdGenerator.next(), "单号为空！");
    }
    DispatchOrder dispatchOrder = dispatchOrderService.getByCode(request.getDispatchOrderCode());
    if (dispatchOrder.getStatus() == DispatchOrderStatus.CANCELED) {
      return new EekaDispatchOrderCancelResponse(apiIdGenerator.next());
    }
    if (dispatchOrder.getStatus() == DispatchOrderStatus.DELIVERED) {
      return new EekaDispatchOrderCancelResponse(apiIdGenerator.next(), "配货单已发货，不能取消！");
    }
    DispatchOrderCancelBO bo = new DispatchOrderCancelBO();
    bo.setVersion(dispatchOrder.getVersion());
    dispatchOrderService.cancelOms(dispatchOrder, bo);
    return new EekaDispatchOrderCancelResponse(apiIdGenerator.next());
  }
}
