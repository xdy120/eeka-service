package com.greatonce.oms.web.controller.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.CreateReturnNoticeOrderBO;
import com.greatonce.oms.bo.trade.ExchangeOccupancyBO;
import com.greatonce.oms.bo.trade.RelateSourceOrderBO;
import com.greatonce.oms.bo.trade.ReturnOrderSaveBO;
import com.greatonce.oms.bo.trade.ScanExpressBO;
import com.greatonce.oms.bo.trade.SourceOrderFilterBO;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import com.greatonce.oms.query.trade.ReturnSignQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author buer
 * @version 2017-12-08 9:45
 */
@RestController
@RequestMapping(value = "/trade/return")
@CrossOrigin
public class ReturnOrderController implements PageListController<ReturnOrder, ReturnOrderQuery> {

  @Autowired
  private ReturnOrderService returnOrderService;
  @Autowired
  private ControllerUtil controllerUtil;
  @Autowired
  private StoreService storeService;

  @Override
  public BizService<ReturnOrder, ReturnOrderQuery> getBizService() {
    return returnOrderService;
  }

  @Override
  public PageList<ReturnOrder> listPage(ReturnOrderQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    Assert.notNull(filter, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);

    controllerUtil.addUserStoreIds(filter, ReturnOrderQuery::getStoreIds, filter::setStoreIds);
    //smartKey处理
    parseSmartKey(filter);
    //手机号处理
    parseMobile(filter);
    return returnOrderService.listPage(filter, page, pageSize);
  }

  /**
   * 将 smartKey 转换成相应的字段
   * @param filter
   */
  private void parseSmartKey(ReturnOrderQuery filter){
    if (!Assert.isEmpty(filter.getSmartKeys())) {
      List<String> keys = StringUtil.words(filter.getSmartKeys());
      for (String key : keys) {
        if (Pattern.matches(TradeConstants.RETURN_ORDER_CODE_PATTERN, key)) {
          if (filter.getReturnOrderCodes() == null) {
            filter.setReturnOrderCodes(new ArrayList<String>() {{
              add(key);
            }});
          } else {
            filter.getReturnOrderCodes().add(key);
          }
          continue;
        }
        if (Pattern.matches(TradeConstants.SALES_ORDER_CODE_PATTERN, key)) {
          if (filter.getSalesOrderCodes() == null) {
            filter.setSalesOrderCodes(new ArrayList<String>() {{
              add(key);
            }});
          } else {
            filter.getSalesOrderCodes().add(key);
          }
          continue;
        }
        if (filter.getTradeIds() == null) {
          filter.setTradeIds(new ArrayList<String>(1) {{
            add(key);
          }});
        } else {
          filter.getTradeIds().add(key);
        }
      }
    }
  }

  /**
   * 将手机号明文 转 密文
   * @param filter
   */
  private void parseMobile(ReturnOrderQuery filter){
    if (!Assert.isEmpty(filter.getMobiles())) {
      ArrayList<String> mobiles = new ArrayList<>();
      for (String mobile : filter.getMobiles()) {
        List<String> mobileEn =
            controllerUtil.encryptKeyByStoreId(mobile, filter.getStoreIds(), DataType.MOBILE);
        mobileEn.add(mobile);
        mobiles.addAll(mobileEn);
      }
      filter.getMobiles().addAll(mobiles);
    }
  }

  @PutMapping(path = "/{id}/audit")
  public void audit(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    ReturnOrder returnOrder = returnOrderService.getByKey(id);
    returnOrderService.audit(returnOrder, bo);
  }

  @PutMapping(path = "/{id}/invalid")
  public void invalid(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    ReturnOrder returnOrder = returnOrderService.getByKey(id);
    returnOrderService.invalid(returnOrder, bo);
  }

  @PutMapping(path = "/{id}/review")
  public void review(@PathVariable("id") Long id, @RequestBody VersionBO bo) {
    ReturnOrder returnOrder = returnOrderService.getByKey(id);
    returnOrderService.review(returnOrder, bo);
  }

  @PostMapping(path = "/createNotice")
  public void createNotice(@RequestBody CreateReturnNoticeOrderBO bo) {
    returnOrderService.createNotice(bo);
  }

  @RequestMapping(path = "/addReturn", method = RequestMethod.POST)
  public void addReturnOrder(@RequestBody ReturnOrderSaveBO returnOrderSaveBO) {
    returnOrderService.addReturnOrder(returnOrderSaveBO);
  }

  @RequestMapping(path = "/autoRelate", method = RequestMethod.POST)
  public RelateSourceOrderBO addSourceOrder(@RequestBody VersionBO<ReturnOrder> returnOrderBO) {
    return returnOrderService.addSourceOrder(returnOrderBO);
  }

  @PostMapping(path = "/{id}/updateAbnormal")
  public void updateAbnormal(@PathVariable("id") Long id,
      @RequestBody VersionBO<ReturnOrder> versionBO) {
    returnOrderService.updateAbnormal(versionBO);
  }

  @RequestMapping(path = "/saveUnknownOrder", method = RequestMethod.POST)
  public void splitUnknownOrder(@RequestBody ReturnOrder returnOrderBO) {
    returnOrderService.splitUnknownOrder(returnOrderBO);
  }

  @PostMapping(path = "/out/details/add")
  public void addOutDetail(@RequestBody ReturnOrder returnOrder) {
    returnOrderService.addOutDetail(returnOrder);
  }

  @PostMapping(path = "/checkReview")
  public List<ReturnOrderDetail> checkReview(@RequestBody ReturnOrder returnOrder){
    return returnOrderService.checkReview(returnOrder);
  }

  @RequestMapping(path = "{keyWord}/sourceOrder")
  public ScanExpressBO getSourceOrderByKeyWord(@PathVariable("keyWord") String keyWord) {
    SourceOrderFilterBO filterBO = new SourceOrderFilterBO();
    SalesOrderQuery filter = new SalesOrderQuery();
    //配货号
    if (Pattern.matches(TradeConstants.DISPATCH_ORDER_CODE_PATTERN, keyWord)) {
      ArrayList<String> dispatchCode = new ArrayList<>();
      dispatchCode.add(keyWord);
      filter.setDispatchOrderCodes(dispatchCode);
      filterBO.setSalesOrderQuery(filter);
      return returnOrderService.getSourceOrder(filterBO);
    } else if (Pattern.matches(TradeConstants.MOBILE_PATTERN, keyWord)) {
      //拿店铺
      List<Store> stores = storeService.listEnableStore();
      List<Long> storeIds = stores.stream().map(Store::getStoreId).collect(Collectors.toList());
      //用拿到的店铺加密手机
      List<String> mobiles = controllerUtil
          .encryptKeyByStoreId(keyWord, storeIds, DataType.MOBILE);
      mobiles.add(keyWord);
      filter.setMobiles(mobiles);
      filterBO.setSalesOrderQuery(filter);
      return returnOrderService.getSourceOrder(filterBO);
    } else {
      filterBO.setSalesOrderQuery(filter);
      filterBO.setExpressNo(keyWord);
      return returnOrderService.getSourceOrder(filterBO);
    }
  }

  @PostMapping(path = "/modifyAmount")
  public Integer modifyAmount(@RequestBody VersionBO<ReturnOrder> returnOrderVersionBO) {
    return returnOrderService.modifyAmount(returnOrderVersionBO);
  }

  @GetMapping(path = "/occupancyDetailQuery")
  public PageList<ExchangeOccupancyBO> occupancyDetail(StockQuery stockQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    return returnOrderService.getStockOccupancyDetail(stockQuery, page, pageSize);
  }

  @PutMapping(path = "/{id}/reposting")
  public void reposting(@PathVariable("id") Long id) {
    ReturnOrder returnOrder = returnOrderService.getByKey(id);
    returnOrderService.reposting(returnOrder);
  }

  @GetMapping("exportReturn/{fileName}")
  public void exportReturn(@PathVariable("fileName") String fileName, ReturnOrderQuery filter){
    controllerUtil.addUserStoreIds(filter, ReturnOrderQuery::getStoreIds, filter::setStoreIds);
    //smartKey处理
    parseSmartKey(filter);
    //手机号处理
    parseMobile(filter);
    returnOrderService.exportReturn(fileName,filter);
  }
}
