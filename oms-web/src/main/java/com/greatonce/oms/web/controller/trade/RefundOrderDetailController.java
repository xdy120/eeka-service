package com.greatonce.oms.web.controller.trade;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.biz.trade.RefundOrderDetailService;
import com.greatonce.oms.domain.trade.RefundOrder;
import com.greatonce.oms.domain.trade.RefundOrderDetail;
import com.greatonce.oms.query.trade.RefundOrderDetailQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author buer
 * @version 2017-12-08 9:46
 */
@RestController
@RequestMapping(value = "/trade/refund/{id}/detail")
@CrossOrigin
public class RefundOrderDetailController {

  @Autowired
  RefundOrderDetailService refundOrderDetailService;

  public DetailService<RefundOrder, RefundOrderDetail, RefundOrderDetailQuery> getBizService() {
    return refundOrderDetailService;
  }

  @RequestMapping(method = RequestMethod.GET)
  public List<RefundOrderDetail> list(@PathVariable("id") Long id) {
    return getBizService().list(new RefundOrderDetailQuery() {{
      setRefundOrderId(id);
    }});
  }
}
