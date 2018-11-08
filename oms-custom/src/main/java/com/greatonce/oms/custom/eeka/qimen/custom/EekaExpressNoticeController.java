package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.bo.trade.WmsExpressNoticeBo;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaExpressNoticeRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaExpressNoticeResponse;
import com.greatonce.oms.domain.base.Express;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.trade.DispatchOrder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lcc
 * @version 2018/7/3 18:26
 */
@RestController
@EekaApiCondition
public class EekaExpressNoticeController extends QimenCustomController {

  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private ExpressService expressService;
  @Autowired
  private DispatchOrderDetailService dispatchOrderDetailService;

  /**
   * 物流通知.
   */
  @PostMapping(params = "method=qok9k0wo6r.greatonce.oms.dispatch.order.logistics.confirm")
  public EekaExpressNoticeResponse expressNotice(HttpServletRequest servletRequest) {
    EekaExpressNoticeRequest request = checkSign(servletRequest,
        EekaExpressNoticeRequest.class);
    Assert.notNull(request, "请求体不能为空");
    Assert.notEmpty(request.getDispatchOrderCode(), "配货单号不能为空");
    Assert.notEmpty(request.getExpressCode(), "快递公司编码不能为空");
    Assert.notEmpty(request.getExpressNo(), "快递单号不能为空");
    Assert.notEmpty(request.getWarehouseCode(), "仓库编号不能为空");

    DispatchOrder dispatchOrder = dispatchOrderService.getByCode(request.getDispatchOrderCode());
    if (!Assert.isEmpty(dispatchOrder.getSuggestExpressNo())
        && dispatchOrder.getSuggestExpressNo().equals(request.getExpressNo())) {
      return new EekaExpressNoticeResponse(apiIdGenerator.next());
    }
    dispatchOrder
        .setDetails(dispatchOrderDetailService.listSimple(dispatchOrder.getDispatchOrderId()));
    Warehouse warehouse = warehouseService.getEffectiveByCode(request.getWarehouseCode());
    Express express =
        expressService.getByWmsExpressCode(warehouse.getWmsAppId(), request.getExpressCode());

    WmsExpressNoticeBo bo = new WmsExpressNoticeBo();
    bo.setExpress(express);
    bo.setExpressNo(request.getExpressNo());
    bo.setVersion(dispatchOrder.getVersion());
    dispatchOrderService.wmsExpressNotice(dispatchOrder, bo);
    return new EekaExpressNoticeResponse(apiIdGenerator.next());
  }
}
