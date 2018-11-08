package com.greatonce.oms.web.controller.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.ExchangeApplyOrderService;
import com.greatonce.oms.domain.trade.ExchangeApplyOrder;
import com.greatonce.oms.query.trade.ExchangeApplyOrderQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: cca
 * Date: 2018-04-02
 * Time: 14:32
 * Description:   换货单
 */
@RestController
@RequestMapping(value = "/trade/exchange")
@CrossOrigin
public class ExchangeOrderController implements
    PageListController<ExchangeApplyOrder, ExchangeApplyOrderQuery> {

  @Autowired
  ExchangeApplyOrderService exchangeApplyOrderService;
  @Autowired
  StoreService storeService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<ExchangeApplyOrder, ExchangeApplyOrderQuery> getBizService() {
    return exchangeApplyOrderService;
  }

  @Override
  @GetMapping
  public PageList<ExchangeApplyOrder> listPage(ExchangeApplyOrderQuery filter,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    controllerUtil
        .addUserStoreIds(filter, ExchangeApplyOrderQuery::getStoreIds, filter::setStoreIds);
    return exchangeApplyOrderService.listPage(filter, page, pageSize);
  }

  /**
   * @param exchangeId 同意换货
   */
  @RequestMapping(path = "/{exchangeId}/agree", method = RequestMethod.GET)
  public void agree(@PathVariable("exchangeId") Long exchangeId) {
    exchangeApplyOrderService.agree(exchangeId);
  }

  /**
   * @param exchangeId 拒绝换货
   */
  @RequestMapping(path = "/{exchangeId}/{reasonCode}/refuse", method = RequestMethod.GET)
  public void refuse(@PathVariable("exchangeId") Long exchangeId,
      @PathVariable("reasonCode") String reasonCode, HttpServletResponse response) {
    exchangeApplyOrderService.refuse(exchangeId, reasonCode);
  }

  @PostMapping(path = "/{exchangeId}/matchExchange")
  public void matchOutSku (@PathVariable("exchangeId") Long exchangeId,@RequestBody ExchangeApplyOrder exchangeApplyOrder){
    exchangeApplyOrderService.matchAbnormalApply(exchangeApplyOrder);
  }

  @GetMapping(path = "/{exchangeApplyId}/deleteOccupancy")
  public void deleteExchangeOccupancy(@PathVariable("exchangeApplyId") Long exchangeApplyId){
    exchangeApplyOrderService.deleteOccupancy(exchangeApplyId);
  }

}