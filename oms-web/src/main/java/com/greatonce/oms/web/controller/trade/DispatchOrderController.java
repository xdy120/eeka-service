package com.greatonce.oms.web.controller.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.DispatchOrderCancelBO;
import com.greatonce.oms.bo.trade.ManualDeliveryBO;
import com.greatonce.oms.bo.trade.WmsLogBO;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.enums.trade.DispatchOrderStatus;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.query.trade.DispatchOrderQuery;
import com.greatonce.oms.web.controller.CommonController;
import com.greatonce.oms.web.controller.ControllerUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-12-08 9:45
 */
@RestController
@RequestMapping(value = "/trade/dispatch")
@CrossOrigin
public class DispatchOrderController implements
    CommonController<DispatchOrder, DispatchOrderQuery> {

  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private ControllerUtil controllerUtil;

  @Override
  public BizService<DispatchOrder, DispatchOrderQuery> getBizService() {
    return dispatchOrderService;
  }

  @SuppressWarnings("Duplicates")
  @GetMapping
  public PageList<DispatchOrder> listPage(DispatchOrderQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    Assert.notNull(filter, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);

    controllerUtil.addUserStoreIds(filter, DispatchOrderQuery::getStoreIds, filter::setStoreIds);
    if (!Assert.isEmpty(filter.getSmartKeys())) {
      List<String> keys = StringUtil.words(filter.getSmartKeys());
      for (String key : keys) {
        if (Pattern.matches(TradeConstants.DISPATCH_ORDER_CODE_PATTERN, key)) {
          if (filter.getDispatchOrderCodes() == null) {
            ArrayList<String> list = new ArrayList<>(keys.size());
            list.add(key);
            filter.setDispatchOrderCodes(list);
          } else {
            filter.getDispatchOrderCodes().add(key);
          }
          continue;
        }
        if (Pattern.matches(TradeConstants.SALES_ORDER_CODE_PATTERN, key)) {
          if (filter.getSalesOrderCodes() == null) {
            ArrayList<String> list = new ArrayList<>(keys.size());
            list.add(key);
            filter.setSalesOrderCodes(list);
          } else {
            filter.getSalesOrderCodes().add(key);
          }
          continue;
        }
        if (Pattern.matches(TradeConstants.MOBILE_PATTERN, key)) {
          if (filter.getMobiles() == null) {
            ArrayList<String> list = new ArrayList<>(keys.size());
            list.add(key);
            filter.setMobiles(list);
          } else {
            filter.getMobiles().add(key);
          }
          List<String> mobiles = controllerUtil
              .encryptKeyByStoreId(key, filter.getStoreIds(), DataType.MOBILE);
          filter.getMobiles().addAll(mobiles);
          continue;
        }
        if (filter.getTradeIds() == null) {
          ArrayList<String> list = new ArrayList<>(keys.size());
          list.add(key);
          filter.setTradeIds(list);
        } else {
          filter.getTradeIds().add(key);
        }
      }
    }
    if (!Assert.isEmpty(filter.getContacts())) {
      List<String> contacts = controllerUtil
          .encryptKeysByStoreId(filter.getContacts(), filter.getStoreIds(),
              DataType.NAME);
      filter.getContacts().addAll(contacts);
    }
    return dispatchOrderService.listPageByConditions(filter, page, pageSize);
  }

  @PutMapping(path = "/{dispatchOrderId}/cancel")
  public void cancel(@PathVariable("dispatchOrderId") Long dispatchOrderId,
      @RequestBody DispatchOrderCancelBO bo) {
    DispatchOrder dispatchOrder = dispatchOrderService.getSimpleInfo(dispatchOrderId);
    dispatchOrderService.cancel(dispatchOrder, bo);
  }

  @PutMapping(path = "/{dispatchOrderId}/noticeWms")
  public void retryNotice(@PathVariable("dispatchOrderId") Long dispatchOrderId,
      @RequestBody VersionBO bo) {
    DispatchOrder dispatchOrder = dispatchOrderService.getByKey(dispatchOrderId);
    dispatchOrderService.noticeWms(dispatchOrder);
    if (dispatchOrder.getStatus() == DispatchOrderStatus.NOTICE_FAILED) {
      throw new OmsException("通知WMS失败！");
    }
  }

  @PutMapping(path = "/{dispatchOrderId}/manualDelivery")
  public void manualDelivery(@PathVariable("dispatchOrderId") Long dispatchOrderId,
      @RequestBody ManualDeliveryBO bo) {
    DispatchOrder dispatchOrder = dispatchOrderService.getByKey(dispatchOrderId);
    dispatchOrderService.manualDelivery(dispatchOrder, bo);
  }

  @GetMapping(path = "/{dispatchOrderId}/listWmsLog")
  public List<WmsLogBO> listWmsLog(@PathVariable("dispatchOrderId") Long dispatchOrderId) {
    return dispatchOrderService.listWmsLog(dispatchOrderId);
  }

  @PutMapping(path = "/{id}/reposting")
  public void reposting(@PathVariable("id") Long id) {
    DispatchOrder dispatchOrder = dispatchOrderService.getByKey(id);
    dispatchOrderService.reposting(dispatchOrder);
  }
}
