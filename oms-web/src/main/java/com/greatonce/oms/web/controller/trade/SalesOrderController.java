package com.greatonce.oms.web.controller.trade;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.core.util.StringUtil;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.impl.trade.SalesOrderUtil;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.biz.trade.SalesOrderSubService;
import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.bo.trade.DispatchDetailBO;
import com.greatonce.oms.bo.trade.DownloadSalesOrderBO;
import com.greatonce.oms.bo.trade.MallDeliveryBO;
import com.greatonce.oms.bo.trade.ManualDispatchBO;
import com.greatonce.oms.bo.trade.ManualDispatchDetailBO;
import com.greatonce.oms.bo.trade.OfflineDeliveryBO;
import com.greatonce.oms.bo.trade.SalesOrderAddDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderBO;
import com.greatonce.oms.bo.trade.SalesOrderDetailBO;
import com.greatonce.oms.bo.trade.SalesOrderDispatchBO;
import com.greatonce.oms.bo.trade.SalesOrderHoldBO;
import com.greatonce.oms.bo.trade.SalesOrderImportBO;
import com.greatonce.oms.bo.trade.SalesOrderManualBO;
import com.greatonce.oms.bo.trade.SalesOrderModifyReceiverInfoBO;
import com.greatonce.oms.bo.trade.SalesOrderRemarkBo;
import com.greatonce.oms.bo.trade.SalesOrderReplaceDetailBO;
import com.greatonce.oms.bo.trade.SuggestExpressBO;
import com.greatonce.oms.bo.trade.SuggestWarehouseBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderDetailType;
import com.greatonce.oms.domain.enums.trade.SalesOrderPayStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import com.greatonce.oms.query.trade.SalesOrderSubQuery;
import com.greatonce.oms.search.HttpElasticsearchTemplate;
import com.greatonce.oms.search.entity.AdvanceQuery;
import com.greatonce.oms.search.entity.AdvanceQuery.Field;
import com.greatonce.oms.web.controller.CommonController;
import com.greatonce.oms.web.controller.ControllerUtil;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2017-12-08 9:45
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/trade/sales")
public class SalesOrderController implements CommonController<SalesOrder, SalesOrderQuery> {

  private static Logger LOGGER = LoggerFactory.getLogger(SalesOrderController.class);
  private static final int ES_INDEX_MAX = 10000;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private SalesOrderSubService salesOrderSubService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private DispatchOrderDeliveryService dispatchOrderDeliveryService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private ControllerUtil controllerUtil;
  @Autowired
  private HttpElasticsearchTemplate httpElasticsearchTemplate;

  @Override
  public BizService<SalesOrder, SalesOrderQuery> getBizService() {
    return salesOrderService;
  }

  @Override
  @GetMapping(path = "/{id}")
  public SalesOrder get(@PathVariable("id") Long id) {
    SalesOrder order = salesOrderService.getByKey(id);
    if (order.getStoreId() != null) {
      Store store = storeService.getByKey(order.getStoreId());
      SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
      order.setContact(
          securityBridge.decrypt(store, order.getContact(), DataType.NAME));
      order.setTelephone(
          securityBridge.decrypt(store, order.getTelephone(), DataType.TELEPHONE));
      order.setMobile(
          securityBridge.decrypt(store, order.getMobile(), DataType.MOBILE));
    }
    return order;
  }

  @GetMapping(path = "/{id}/detailInfo")
  public SalesOrder getDetailInfo(@PathVariable("id") Long id) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(id);

    //解密 联系人 和 密码
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    String contact = securityBridge
        .decrypt(store, salesOrder.getContact(), DataType.NAME);
    String mobile = securityBridge
        .decrypt(store, salesOrder.getMobile(), DataType.MOBILE);

    //地址 全路径
    StringBuilder address = new StringBuilder();
    if (!Assert.isEmpty(salesOrder.getProvinceName())) {
      address.append(salesOrder.getProvinceName()).append("/");
    }
    if (!Assert.isEmpty(salesOrder.getCityName())) {
      address.append(salesOrder.getCityName()).append("/");
    }
    if (!Assert.isEmpty(salesOrder.getDistrictName())) {
      address.append(salesOrder.getDistrictName()).append("/");
    }
    address.append(salesOrder.getAddress());

    salesOrder.setContact(contact);
    salesOrder.setMobile(mobile);
    salesOrder.setAddress(address.toString());
    return salesOrder;
  }

  @Override
  @PostMapping
  public SalesOrder insert(@RequestBody SalesOrder salesOrder) {
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    salesOrder.setContact(
        securityBridge.encrypt(store, salesOrder.getContact(), DataType.NAME));
    salesOrder.setMobile(
        securityBridge.encrypt(store, salesOrder.getMobile(), DataType.MOBILE));
    salesOrder.setTelephone(securityBridge
        .encrypt(store, salesOrder.getTelephone(), DataType.TELEPHONE));
    salesOrderService.manualCreate(salesOrder);
    return salesOrder;
  }

  @Override
  @PutMapping(path = "{id}")
  public SalesOrder update(@PathVariable("id") Long id, @RequestBody SalesOrder salesOrder) {
    salesOrder
        .setDetails(salesOrderDetailService.getSalesOrderDetails(salesOrder.getSalesOrderId()));
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    salesOrder.setContact(
        securityBridge.encrypt(store, salesOrder.getContact(), DataType.NAME));
    salesOrder.setMobile(
        securityBridge.encrypt(store, salesOrder.getMobile(), DataType.MOBILE));
    salesOrder.setTelephone(securityBridge
        .encrypt(store, salesOrder.getTelephone(), DataType.TELEPHONE));
    salesOrderService.modify(salesOrder);
    return salesOrder;
  }

  @SuppressWarnings("Duplicates")
  @GetMapping
  public PageList<SalesOrder> listPage(SalesOrderQuery filter, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    Assert.notNull(filter, SysExceptions.PARAMETER_NOT_ALLOW_EMPTY);
    if (filter.getSub() == null) {
      filter.setSub(new SalesOrderSubQuery());
    }

    controllerUtil.addUserStoreIds(filter, SalesOrderQuery::getStoreIds, filter::setStoreIds);
    //处理key
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
      List<String> encryptContacts = controllerUtil
          .encryptKeysByStoreId(filter.getContacts(), filter.getStoreIds(), DataType.NAME);
      filter.getContacts().addAll(encryptContacts);
    }
    return salesOrderService.listPage(filter, page, pageSize);
  }

  @GetMapping("/advance")
  public PageList<SalesOrder> listPageForAdvance(@RequestParam("fields") String fieldsStr,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) throws IOException {
    if (page * pageSize > ES_INDEX_MAX) {
      throw new IllegalArgumentException("请增加查询条件，缩小结果范围");
    }
    List<Field> fields = JsonUtil.toArray(fieldsStr, Field.class);
    controllerUtil.addUserStoreIdsAndEncryptKey(fields);
    PageList<Map<String, Object>> pageList = httpElasticsearchTemplate
        .searchSource("sales_order", "doc", page, pageSize, new AdvanceQuery(fields),
            new String[]{"sales_order_id"}, new String[]{},
            SortBuilders.fieldSort("sales_order_id").order(SortOrder.DESC));
    if (!Assert.isEmpty(pageList.getData())) {
      SalesOrderQuery filter = new SalesOrderQuery();
      filter.setSalesOrderIds(new ArrayList<>(pageList.getTotal()));
      pageList.getData()
          .forEach(x -> filter.getSalesOrderIds()
              .add(Long.valueOf(String.valueOf(x.get("sales_order_id")))));
      PageList<SalesOrder> daoPageList = salesOrderService.listPage(filter, 1, pageSize);
      return new PageList<>(pageSize, page, pageList.getTotal(), daoPageList.getData());
    }
    return new PageList<>(pageSize, page, 0, new ArrayList<>());
  }

  @GetMapping(path = "/detail")
  public List<SalesOrder> listDetail(SalesOrderQuery filter) {
    return salesOrderService.listDetail(filter);
  }

  @PutMapping(path = "/{salesOrderId}/invalid")
  public void invalid(@PathVariable("salesOrderId") Long salesOrderId, @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    salesOrderService.invalid(salesOrder, bo);
  }


  @PutMapping(path = "/{salesOrderId}/refund")
  public void refund(@PathVariable("salesOrderId") Long salesOrderId, @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    salesOrderService.refund(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/cancelRefund")
  public void cancelRefund(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.cancelRefund(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/audit")
  public void audit(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderBO bo) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    salesOrderService.audit(salesOrder, bo);
  }

  @PostMapping(path = "/download")
  public void download(@RequestBody DownloadSalesOrderBO bo) {
    salesOrderService.download(bo);
  }

  @PutMapping(path = "/{salesOrderId}/hold")
  public void hold(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderHoldBO bo) {
    if (!bo.getHoldDate().isAfter(LocalDate.now())) {
      throw new OmsException("留单日期必须大于当前日期");
    }
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.hold(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/cancelHold")
  public void cancelHold(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.cancelHold(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/urgent")
  public void urgent(@PathVariable("salesOrderId") Long salesOrderId, @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.urgent(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/cancelUrgent")
  public void cancelUrgent(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.cancelUrgent(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/manual")
  public void manual(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderManualBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    bo.setReason("设置手工处理");
    salesOrderService.manual(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/cancelManual")
  public void cancelManual(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.cancelManual(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/reset")
  public void reset(@PathVariable("salesOrderId") Long salesOrderId, @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getFullInfo(salesOrderId);
    salesOrderService.reset(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/suggestWarehouse")
  public void suggestWarehouse(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SuggestWarehouseBO bo) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    salesOrderService.suggestWarehouse(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/suggestExpress")
  public void suggestExpress(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SuggestExpressBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.suggestExpress(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/matchProduct")
  public void matchProduct(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    salesOrderService.matchProduct(salesOrder, bo);
  }

  /**
   * 根据sku批量替换明细.
   */
  @PutMapping(path = "/{salesOrderId}/replaceDetail")
  public void replaceDetail(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderReplaceDetailBO bo) {
    if (bo.getSourceDetail().getQuantity().equals(0)) {
      throw new OmsException("明细数量不能为0");
    }
    if (bo.getSourceDetail().getSalesOrderDetailType() == SalesOrderDetailType.ORIGINAL) {
      throw new OmsException("不允许替换套装");
    }
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    salesOrderService.replaceDetail(salesOrder, bo, true);
  }

  @PostMapping(path = "/{salesOrderId}/addGift")
  public void addGift(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderAddDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.addGift(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/replaceGift")
  public void replaceGift(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderReplaceDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(salesOrderId);
    salesOrderService.replaceGift(salesOrder, bo);
  }

  /**
   * sku相同的有效赠品明细最多只存在一条 删除赠品方法暂时调用作废赠品
   * 明细中有相同sku的有效赠品才能作废，如果匹配数量则数量相同才作废
   */
  @PutMapping(path = "/{salesOrderId}/removeGift")
  public void removeGift(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderDetailBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    //skuId相同的赠品
    SalesOrderDetail eg = new SalesOrderDetail();
    eg.setSalesOrderId(salesOrderId);
    eg.setGift(true);
    eg.setSkuId(bo.getDetail().getSkuId());
    List<SalesOrderDetail> details = salesOrderDetailService.listExample(eg);
    //校验
    Iterator<SalesOrderDetail> iterator = details.iterator();
    while (iterator.hasNext()) {
      SalesOrderDetail next = iterator.next();
      if (SalesOrderDetailStatus.INVALID.equals(next.getStatus())) {
        iterator.remove();
        continue;
      }
      if (bo.getDetail().getQuantity() != null && !next.getQuantity()
          .equals(bo.getDetail().getQuantity())) {
        throw new OmsException("作废赠品数量不匹配");
      }
    }
    if (details.size() == 0) {
      throw new OmsException("订单没有此赠品");
    }

    bo.setDetail(details.get(0));
    salesOrderService.invalid(salesOrder, bo);
  }

  @PutMapping(path = "/{salesOrderId}/modifyReceiverInfo")
  public void modifyReceiverInfo(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderModifyReceiverInfoBO bo) {
    SalesOrder salesOrder = salesOrderService.getByKey(salesOrderId);
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    bo.setEncryptContact(
        securityBridge.encrypt(store, bo.getContact(), DataType.NAME));
    bo.setEncryptMobile(
        securityBridge.encrypt(store, bo.getMobile(), DataType.MOBILE));
    bo.setEncryptTelephone(securityBridge
        .encrypt(store, bo.getTelephone(), DataType.TELEPHONE));
    salesOrderService.modifyReceiverInfo(salesOrder, bo);
  }

  @GetMapping(path = "/listWaitingDispatch/{md5}")
  public List<SalesOrder> listWaitingDispatch(@PathVariable("md5") String md5) {
    return salesOrderService.listWaitingDispatchOrder(md5);
  }

  @PostMapping(path = "/checkDispatchStock")
  public List<ManualDispatchDetailBO> checkDispatchStock(@RequestBody ManualDispatchBO bo) {
    return salesOrderService.checkDispatchStock(bo);
  }

  @PostMapping(path = "/{salesOrderId}/manualDispatch")
  public void manualDispatch(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody ManualDispatchBO bo) {
    SalesOrder salesOrder = salesOrderService.getByKey(salesOrderId);
    if (Assert.isTrue(salesOrder.getSub().isPrepay())
        && salesOrder.getPayStatus() != SalesOrderPayStatus.PAID) {
      throw new OmsException("订单未付尾款，不能配货！");
    }
    Store store = storeService.getByKey(salesOrder.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    bo.setEncryptContact(securityBridge.encrypt(store, bo.getContact(), DataType.NAME));
    bo.setEncryptTelephone(
        securityBridge.encrypt(store, bo.getTelephone(), DataType.TELEPHONE));
    bo.setEncryptMobile(securityBridge.encrypt(store, bo.getMobile(), DataType.MOBILE));
    dispatchOrderService.manualDispatch(salesOrder, bo);
  }

  @PostMapping(path = "/{salesOrderId}/offlineDelivery")
  public void offlineDelivery(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody OfflineDeliveryBO bo) {
    SalesOrder salesOrder = salesOrderService.getDispatchInfo(salesOrderId);
    //批量线下发货时，没有明细，需封装明细信息 bo.getDetails()为null
    if (Assert.isEmpty(bo.getDetails()) && !Assert.isNull(salesOrder)) {
      SalesOrder order = salesOrderService.getByKey(salesOrderId);
      bo.setCountryId(order.getCountryId());
      bo.setProvinceId(order.getProvinceId());
      bo.setCityId(order.getCityId());
      bo.setDistrictId(order.getDistrictId());
      bo.setCountryName(order.getCountryName());
      bo.setProvinceName(order.getProvinceName());
      bo.setCityName(order.getCityName());
      bo.setDistrictName(order.getDistrictName());
      bo.setAddress(order.getAddress());
      bo.setVersion(order.getVersion());
      bo.setContact(order.getContact());
      bo.setMobile(order.getMobile());
      bo.setTelephone(order.getTelephone());
      List<SalesOrderDetail> salesOrderDetails = salesOrderDetailService
          .getSalesOrderDetails(salesOrderId);
      List<DispatchDetailBO> dispatchDetailBOList = new ArrayList<>(salesOrderDetails.size());
      //批量线下发货时，是否需要增加赠品
      if (!bo.isGiftFlag()) {
        salesOrderDetails = salesOrderDetails.stream().filter(item -> !item.isGift())
            .collect(Collectors.toList());
      }
      salesOrderDetails.forEach(item -> {
        DispatchDetailBO dispatchDetailBO = new DispatchDetailBO();
        BeanUtils.copyProperties(item, dispatchDetailBO);
        dispatchDetailBO.setTradeId(order.getTradeId());
        SalesOrderSub eg = new SalesOrderSub();
        eg.setSalesOrderId(salesOrderId);
        SalesOrderSub salesOrderSub = salesOrderSubService.getByExample(eg);
        dispatchDetailBO.setPaidTime(salesOrderSub.getMallPaidTime());
        dispatchDetailBO.setSalesOrderCode(order.getSalesOrderCode());
        dispatchDetailBOList.add(dispatchDetailBO);
      });
      bo.setOfflineDelivery(true);
      bo.setDetails(dispatchDetailBOList);
    } else {
      //前端传入的是明文，需要加密存储
      Store store = storeService.getByKey(salesOrder.getStoreId());
      SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
      bo.setContact(securityBridge.encrypt(store, bo.getContact(), DataType.NAME));
      bo.setTelephone(
          securityBridge.encrypt(store, bo.getTelephone(), DataType.TELEPHONE));
      bo.setMobile(securityBridge.encrypt(store, bo.getMobile(), DataType.MOBILE));
    }
    dispatchOrderService.offlineDelivery(salesOrder, bo);
  }

  @GetMapping(path = "/{salesOrderId}/delivery")
  public List<DispatchOrderDelivery> listDelivery(@PathVariable("salesOrderId") Long salesOrderId) {
    DispatchOrderDelivery eg = new DispatchOrderDelivery();
    eg.setSalesOrderId(salesOrderId);
    return dispatchOrderDeliveryService.listExample(eg);
  }

  @GetMapping(path = "/{salesOrderId}/dispatch")
  public List<DispatchOrder> listDispatch(@PathVariable("salesOrderId") Long salesOrderId) {
    return dispatchOrderService.listBySalesOrderId(salesOrderId);
  }

  @PostMapping(path = "/import")
  public void importSalesOrder(@RequestBody SalesOrderImportBO bo) {
    salesOrderService.importSalesOrder(bo);
  }

  @GetMapping(path = "/export")
  public void export(@RequestParam("name") String name, SalesOrderQuery filter) {
    salesOrderService.export(name, filter);
  }

  @GetMapping(path = "/{salesOrderId}/decrypt")
  public SalesOrder decryptOrderInfo(@PathVariable("salesOrderId") Long salesOrderId) {
    SalesOrder order = salesOrderService.getByKey(salesOrderId);
    Store store = storeService.getByKey(order.getStoreId());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    order.setContact(
        securityBridge.decrypt(store, order.getContact(), DataType.NAME));
    order.setTelephone(
        securityBridge.decrypt(store, order.getTelephone(), DataType.TELEPHONE));
    order.setMobile(
        securityBridge.decrypt(store, order.getMobile(), DataType.MOBILE));
    return order;
  }

  @RequestMapping(path = "/copySalesOrder/{salesOrderId}")
  public SalesOrder copySalesOrder(@PathVariable("salesOrderId") Long salesOrderId) {
    return salesOrderService.copySalesOrder(salesOrderId);
  }

  @GetMapping(path = "/occupancyDetailQuery")
  public PageList<SalesOrder> occupancyDetail(StockQuery stockQuery,
      @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
    return salesOrderService.getStockOccupancyDetail(stockQuery, page, pageSize);
  }

  @PutMapping("/{salesOrderId}/autoDispatch")
  public void autoDispatch(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderDispatchBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.autoDispatch(salesOrder, bo);
  }

  @PutMapping("/{salesOrderId}/modifyRemark")
  public void modifyWarehouseRemark(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody SalesOrderRemarkBo bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    salesOrderService.modifyRemark(salesOrder, bo);
  }

  @PutMapping("/{salesOrderId}/manualMallDelivery")
  public void manualMallDelivery(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody MallDeliveryBO bo) {
    SalesOrder salesOrder = salesOrderService.getByKey(salesOrderId);
    SalesOrderUtil.mustStatus(salesOrder, SalesOrderStatus.DELIVERY_ABNORMAL);
    List<SalesOrderDetail> details =
        salesOrderDetailService.listToMallDeliveryDetailsInfo(salesOrderId);
    if (Assert.isEmpty(details)) {
      throw new OmsException("无可平台发货明细");
    }
    salesOrder.setDetails(details);
    salesOrderService.mallDelivery(salesOrder, bo);
  }

  @GetMapping("/batch")
  public PageList<SalesOrder> listPageForBatch(@RequestParam("fields") String fieldsStr,
      @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) throws IOException {
    List<Field> fields = JsonUtil.toArray(fieldsStr, Field.class);
    if (page * pageSize > ES_INDEX_MAX) {
      throw new IllegalArgumentException("请增加查询条件，缩小结果范围");
    }
    controllerUtil.addUserStoreIdsAndEncryptKey(fields);
    PageList<Map<String, Object>> pageList = httpElasticsearchTemplate
        .searchSource("sales_order", "doc", page, pageSize, new AdvanceQuery(fields),
            new String[]{"sales_order_id"}, new String[]{},
            SortBuilders.fieldSort("sales_order_id").order(SortOrder.DESC));
    if (!Assert.isEmpty(pageList.getData())) {
      SalesOrderQuery filter = new SalesOrderQuery();
      filter.setSalesOrderIds(new ArrayList<>(pageList.getTotal()));
      pageList.getData()
          .forEach(x -> filter.getSalesOrderIds()
              .add(Long.valueOf(String.valueOf(x.get("sales_order_id")))));

      List<SalesOrder> salesOrders = salesOrderService.listForBatch(filter);
      return new PageList<>(pageSize, page, pageList.getTotal(), salesOrders);
    }
    return new PageList<>(pageSize, page, 0, new ArrayList<>());
  }

  @PutMapping("/{salesOrderId}/cancelPrerefund")
  public void cancelPrerefund(@PathVariable("salesOrderId") Long salesOrderId,
      @RequestBody VersionBO bo) {
    SalesOrder salesOrder = salesOrderService.getSimpleInfo(salesOrderId);
    SalesOrderUtil.mustBeforeAllDispatch(salesOrder);
    salesOrderService.cancelPrerefund(salesOrder, bo);
  }
}