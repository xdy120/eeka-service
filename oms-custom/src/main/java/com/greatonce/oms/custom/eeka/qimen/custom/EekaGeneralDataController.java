package com.greatonce.oms.custom.eeka.qimen.custom;

import com.alibaba.fastjson.JSON;
import com.greatonce.core.database.PageList;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.DataDictItemService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.stock.StockOutBatchDetailService;
import com.greatonce.oms.biz.stock.StockOutBatchService;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.biz.trade.SalesOrderDetailService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.biz.trade.SalesOrderSubService;
import com.greatonce.oms.biz.vip.VipDeliveryService;
import com.greatonce.oms.biz.vip.VipDispatchDetailService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.biz.vip.VipReturnNoticeDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaDataDictItemDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaDataListQueryRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaDispatchOrderDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaDispatchOrderDeliveryDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaDispatchOrderDetailDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaReturnNoticeOrderDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaReturnNoticeOrderDetailDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaReturnOrderDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaReturnOrderDetailDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaSalesOrderDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaSalesOrderDetailDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaSalesOrderSubDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaStoreDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipDeliveryDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipDispatchDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipDispatchDetailDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipReturnNoticeDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaVipReturnNoticeDetailDataRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaDataDictItemDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaDataListResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaDispatchOrderDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaDispatchOrderDeliveryDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaDispatchOrderDetailDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaReturnNoticeOrderDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaReturnNoticeOrderDetailDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaReturnOrderDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaReturnOrderDetailDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaSalesOrderDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaSalesOrderDetailDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaSalesOrderSubDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaStoreDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipDeliveryDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipDispatchDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipDispatchDetailDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipReturnNoticeDataResponse;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaVipReturnNoticeDetailDataResponse;
import com.greatonce.oms.domain.base.DataDictItem;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.domain.trade.ReturnNoticeOrderDetail;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.domain.trade.SalesOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.query.base.DataDictItemQuery;
import com.greatonce.oms.query.base.StoreQuery;
import com.greatonce.oms.query.stock.StockOutBatchDetailQuery;
import com.greatonce.oms.query.stock.StockOutBatchQuery;
import com.greatonce.oms.query.trade.DispatchOrderDeliveryQuery;
import com.greatonce.oms.query.trade.DispatchOrderDetailQuery;
import com.greatonce.oms.query.trade.DispatchOrderQuery;
import com.greatonce.oms.query.trade.ReturnNoticeOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnNoticeOrderQuery;
import com.greatonce.oms.query.trade.ReturnOrderDetailQuery;
import com.greatonce.oms.query.trade.ReturnOrderQuery;
import com.greatonce.oms.query.trade.SalesOrderDetailQuery;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import com.greatonce.oms.query.trade.SalesOrderSubQuery;
import com.greatonce.oms.query.vip.VipDeliveryQuery;
import com.greatonce.oms.query.vip.VipDispatchDetailQuery;
import com.greatonce.oms.query.vip.VipDispatchQuery;
import com.greatonce.oms.query.vip.VipReturnNoticeDetailQuery;
import com.greatonce.oms.query.vip.VipReturnNoticeQuery;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EekaApiCondition
public class EekaGeneralDataController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory.getLogger(EekaGeneralDataController.class);

  @Autowired
  private ReturnOrderService returnOrderService;
  @Autowired
  private ReturnOrderDetailService returnOrderDetailService;
  @Autowired
  private ReturnNoticeOrderService returnNoticeOrderService;
  @Autowired
  private ReturnNoticeOrderDetailService returnNoticeOrderDetailService;
  @Autowired
  private DataDictItemService dataDictItemService;
  @Resource
  private IdGenerator apiIdGenerator;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private SalesOrderDetailService salesOrderDetailService;
  @Autowired
  private SalesOrderSubService salesOrderSubService;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private DispatchOrderDetailService dispatchOrderDetailService;
  @Autowired
  private DispatchOrderDeliveryService dispatchOrderDeliveryService;
  @Autowired
  private VipDispatchService vipDispatchService;
  @Autowired
  private VipDispatchDetailService vipDispatchDetailService;
  @Autowired
  private VipDeliveryService vipDeliveryService;
  @Autowired
  private VipReturnNoticeService vipReturnNoticeService;
  @Autowired
  private VipReturnNoticeDetailService vipReturnNoticeDetailService;
  @Autowired
  private StockOutBatchService stockOutBatchService;
  @Autowired
  private StockOutBatchDetailService stockOutBatchDetailService;
  /**
   * 查询销售订单
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.sales.order.list")
  public EekaSalesOrderDataResponse salesOrderData(HttpServletRequest servletRequest) {
    EekaSalesOrderDataRequest request = checkSign(servletRequest, EekaSalesOrderDataRequest.class);
    EekaSalesOrderDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaSalesOrderDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      SalesOrderQuery salesOrderQuery = new SalesOrderQuery();
      salesOrderQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      salesOrderQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<SalesOrder> salesOrders = salesOrderService.listPage(salesOrderQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(salesOrders)){
        response = new EekaSalesOrderDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(salesOrders.getData())){
          List<String> dataList = new ArrayList<>(salesOrders.getData().size());
          salesOrders.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(salesOrders.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaSalesOrderDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 查询销售订单明细
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.sales.order.detail.list")
  public EekaSalesOrderDetailDataResponse salesOrderDetailData(HttpServletRequest servletRequest) {
    EekaSalesOrderDetailDataRequest request = checkSign(servletRequest, EekaSalesOrderDetailDataRequest.class);
    EekaSalesOrderDetailDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaSalesOrderDetailDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      SalesOrderDetailQuery salesOrderDetailQuery = new SalesOrderDetailQuery();
      salesOrderDetailQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      salesOrderDetailQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<SalesOrderDetail> salesOrderDetails = salesOrderDetailService.listPage(salesOrderDetailQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(salesOrderDetails)){
        response = new EekaSalesOrderDetailDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(salesOrderDetails.getData())){
          List<String> dataList = new ArrayList<>(salesOrderDetails.getData().size());
          salesOrderDetails.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(salesOrderDetails.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaSalesOrderDetailDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 查询销售附表
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.sales.order.sub.list")
  public EekaSalesOrderSubDataResponse salesOrderSubData(HttpServletRequest servletRequest) {
    EekaSalesOrderSubDataRequest request = checkSign(servletRequest, EekaSalesOrderSubDataRequest.class);
    EekaSalesOrderSubDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaSalesOrderSubDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      SalesOrderSubQuery salesOrderSubQuery = new SalesOrderSubQuery();
      salesOrderSubQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      salesOrderSubQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<SalesOrderSub> salesOrderSubs = salesOrderSubService.listPage(salesOrderSubQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(salesOrderSubs)){
        response = new EekaSalesOrderSubDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(salesOrderSubs.getData())){
          List<String> dataList = new ArrayList<>(salesOrderSubs.getData().size());
          salesOrderSubs.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(salesOrderSubs.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaSalesOrderSubDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 查询配货订单
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.dispatch.order.list")
  public EekaDispatchOrderDataResponse dispatchOrderData(HttpServletRequest servletRequest) {
    EekaDispatchOrderDataRequest request = checkSign(servletRequest, EekaDispatchOrderDataRequest.class);
    EekaDispatchOrderDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaDispatchOrderDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      DispatchOrderQuery dispatchOrderQuery = new DispatchOrderQuery();
      dispatchOrderQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      dispatchOrderQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<DispatchOrder> dispatchOrders = dispatchOrderService.listPage(dispatchOrderQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(dispatchOrders)){
        response = new EekaDispatchOrderDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(dispatchOrders.getData())){
          List<String> dataList = new ArrayList<>(dispatchOrders.getData().size());
          dispatchOrders.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(dispatchOrders.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaDispatchOrderDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 查询配货单发货信息
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.dispatch.order.det.list")
  public EekaDispatchOrderDetailDataResponse dispatchOrderDetailData(HttpServletRequest servletRequest) {
    EekaDispatchOrderDetailDataRequest request = checkSign(servletRequest, EekaDispatchOrderDetailDataRequest.class);
    EekaDispatchOrderDetailDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaDispatchOrderDetailDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      DispatchOrderDetailQuery dispatchOrderDetailQuery = new DispatchOrderDetailQuery();
      dispatchOrderDetailQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      dispatchOrderDetailQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<DispatchOrderDetail> dispatchOrderDetails = dispatchOrderDetailService.listPage(dispatchOrderDetailQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(dispatchOrderDetails)){
        response = new EekaDispatchOrderDetailDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(dispatchOrderDetails.getData())){
          List<String> dataList = new ArrayList<>(dispatchOrderDetails.getData().size());
          dispatchOrderDetails.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(dispatchOrderDetails.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaDispatchOrderDetailDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 查询配货单发货信息
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.dispatch.delivery.list")
  public EekaDispatchOrderDeliveryDataResponse dispatchOrderDeliveryData(HttpServletRequest servletRequest) {
    EekaDispatchOrderDeliveryDataRequest request = checkSign(servletRequest, EekaDispatchOrderDeliveryDataRequest.class);
    EekaDispatchOrderDeliveryDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaDispatchOrderDeliveryDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      DispatchOrderDeliveryQuery dispatchOrderDeliveryQuery = new DispatchOrderDeliveryQuery();
      dispatchOrderDeliveryQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      dispatchOrderDeliveryQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<DispatchOrderDelivery> dispatchOrderDeliverys = dispatchOrderDeliveryService.listPage(dispatchOrderDeliveryQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(dispatchOrderDeliverys)){
        response = new EekaDispatchOrderDeliveryDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(dispatchOrderDeliverys.getData())){
          List<String> dataList = new ArrayList<>(dispatchOrderDeliverys.getData().size());
          dispatchOrderDeliverys.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(dispatchOrderDeliverys.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaDispatchOrderDeliveryDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 查询退货单信息
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.return.order.list")
  public EekaReturnOrderDataResponse returnOrderData(HttpServletRequest servletRequest) {
    EekaReturnOrderDataRequest request = checkSign(servletRequest, EekaReturnOrderDataRequest.class);
    EekaReturnOrderDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaReturnOrderDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      ReturnOrderQuery returnOrderQuery = new ReturnOrderQuery();
      returnOrderQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      returnOrderQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<ReturnOrder> returnOrders = returnOrderService.listPage(returnOrderQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(returnOrders)){
        response = new EekaReturnOrderDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(returnOrders.getData())){
          List<String> dataList = new ArrayList<>(returnOrders.getData().size());
          returnOrders.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(returnOrders.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaReturnOrderDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 查询退货单明细信息
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.return.order.det.list")
  public EekaReturnOrderDetailDataResponse returnOrderDetailData(HttpServletRequest servletRequest) {
    EekaReturnOrderDetailDataRequest request = checkSign(servletRequest, EekaReturnOrderDetailDataRequest.class);
    EekaReturnOrderDetailDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaReturnOrderDetailDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      ReturnOrderDetailQuery returnOrderDetailQuery = new ReturnOrderDetailQuery();
      returnOrderDetailQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      returnOrderDetailQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<ReturnOrderDetail> returnOrderDetails = returnOrderDetailService.listPage(returnOrderDetailQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(returnOrderDetails)){
        response = new EekaReturnOrderDetailDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(returnOrderDetails.getData())){
          List<String> dataList = new ArrayList<>(returnOrderDetails.getData().size());
          returnOrderDetails.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(returnOrderDetails.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaReturnOrderDetailDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 唯品销售订单
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.vip.dispatch.order.list")
  public EekaVipDispatchDataResponse vipDispatchData(HttpServletRequest servletRequest) {
    EekaVipDispatchDataRequest request = checkSign(servletRequest, EekaVipDispatchDataRequest.class);
    EekaVipDispatchDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaVipDispatchDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      VipDispatchQuery vipDispatchQuery = new VipDispatchQuery();
      vipDispatchQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      vipDispatchQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<VipDispatch> vipDispatchs = vipDispatchService.listPage(vipDispatchQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(vipDispatchs)){
        response = new EekaVipDispatchDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(vipDispatchs.getData())){
          List<String> dataList = new ArrayList<>(vipDispatchs.getData().size());
          vipDispatchs.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(vipDispatchs.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaVipDispatchDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 唯品销售订单明细
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.vip.dispatch.det.list")
  public EekaVipDispatchDetailDataResponse vipDispatchDetailData(HttpServletRequest servletRequest) {
    EekaVipDispatchDetailDataRequest request = checkSign(servletRequest, EekaVipDispatchDetailDataRequest.class);
    EekaVipDispatchDetailDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaVipDispatchDetailDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      VipDispatchDetailQuery vipDispatchDetailQuery = new VipDispatchDetailQuery();
      vipDispatchDetailQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      vipDispatchDetailQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<VipDispatchDetail> vipDispatchDetails = vipDispatchDetailService.listPage(vipDispatchDetailQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(vipDispatchDetails)){
        response = new EekaVipDispatchDetailDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(vipDispatchDetails.getData())){
          List<String> dataList = new ArrayList<>(vipDispatchDetails.getData().size());
          vipDispatchDetails.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(vipDispatchDetails.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaVipDispatchDetailDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 唯品发货信息
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.vip.delivery.list")
  public EekaVipDeliveryDataResponse vipDeliveryData(HttpServletRequest servletRequest) {
    EekaVipDeliveryDataRequest request = checkSign(servletRequest, EekaVipDeliveryDataRequest.class);
    EekaVipDeliveryDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaVipDeliveryDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      VipDeliveryQuery vipDeliveryQuery = new VipDeliveryQuery();
      vipDeliveryQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      vipDeliveryQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<VipDelivery> vipDeliverys = vipDeliveryService.listPage(vipDeliveryQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(vipDeliverys)){
        response = new EekaVipDeliveryDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(vipDeliverys.getData())){
          List<String> dataList = new ArrayList<>(vipDeliverys.getData().size());
          vipDeliverys.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(vipDeliverys.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaVipDeliveryDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 唯品退货通知订单
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.vip.return.notice.list")
  public EekaVipReturnNoticeDataResponse vipReturnNoticeData(HttpServletRequest servletRequest) {
    EekaVipReturnNoticeDataRequest request = checkSign(servletRequest, EekaVipReturnNoticeDataRequest.class);
    EekaVipReturnNoticeDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaVipReturnNoticeDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      VipReturnNoticeQuery vipReturnNoticeQuery = new VipReturnNoticeQuery();
      vipReturnNoticeQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      vipReturnNoticeQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<VipReturnNotice> vipReturnNotices = vipReturnNoticeService.listPage(vipReturnNoticeQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(vipReturnNotices)){
        response = new EekaVipReturnNoticeDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(vipReturnNotices.getData())){
          List<String> dataList = new ArrayList<>(vipReturnNotices.getData().size());
          vipReturnNotices.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(vipReturnNotices.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaVipReturnNoticeDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 唯品退货通知明细
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.vip.return.notice.det.list")
  public EekaVipReturnNoticeDetailDataResponse vipReturnNoticeDetailData(HttpServletRequest servletRequest) {
    EekaVipReturnNoticeDetailDataRequest request = checkSign(servletRequest, EekaVipReturnNoticeDetailDataRequest.class);
    EekaVipReturnNoticeDetailDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaVipReturnNoticeDetailDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      VipReturnNoticeDetailQuery vipReturnNoticeDetailQuery = new VipReturnNoticeDetailQuery();
      vipReturnNoticeDetailQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      vipReturnNoticeDetailQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<VipReturnNoticeDetail> vipReturnNoticeDetails = vipReturnNoticeDetailService.listPage(vipReturnNoticeDetailQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(vipReturnNoticeDetails)){
        response = new EekaVipReturnNoticeDetailDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(vipReturnNoticeDetails.getData())){
          List<String> dataList = new ArrayList<>(vipReturnNoticeDetails.getData().size());
          vipReturnNoticeDetails.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(vipReturnNoticeDetails.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaVipReturnNoticeDetailDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 唯品退货通知明细
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.store.list")
  public EekaStoreDataResponse storeData(HttpServletRequest servletRequest) {
    EekaStoreDataRequest request = checkSign(servletRequest, EekaStoreDataRequest.class);
    EekaStoreDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaStoreDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      StoreQuery storeQuery = new StoreQuery();
      storeQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      storeQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<Store> stores= storeService.listPage(storeQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(stores)){
        response = new EekaStoreDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(stores.getData())){
          List<String> dataList = new ArrayList<>(stores.getData().size());
          stores.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(stores.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaStoreDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * B2C退货通知明细
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.return.notice.detail.list")
  public EekaReturnNoticeOrderDetailDataResponse returnNoticeOrderDetailData(HttpServletRequest servletRequest) {
    EekaReturnNoticeOrderDetailDataRequest request = checkSign(servletRequest, EekaReturnNoticeOrderDetailDataRequest.class);
    EekaReturnNoticeOrderDetailDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaReturnNoticeOrderDetailDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      ReturnNoticeOrderDetailQuery returnNoticeOrderDetailQuery = new ReturnNoticeOrderDetailQuery();
      returnNoticeOrderDetailQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      returnNoticeOrderDetailQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<ReturnNoticeOrderDetail> returnNoticeOrderDetails= returnNoticeOrderDetailService.listPage(returnNoticeOrderDetailQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(returnNoticeOrderDetails)){
        response = new EekaReturnNoticeOrderDetailDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(returnNoticeOrderDetails.getData())){
          List<String> dataList = new ArrayList<>(returnNoticeOrderDetails.getData().size());
          returnNoticeOrderDetails.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(returnNoticeOrderDetails.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaReturnNoticeOrderDetailDataResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * B2C退货通知明细
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.return.notice.list")
  public EekaReturnNoticeOrderDataResponse returnNoticeOrderData(HttpServletRequest servletRequest) {
    EekaReturnNoticeOrderDataRequest request = checkSign(servletRequest, EekaReturnNoticeOrderDataRequest.class);
    EekaReturnNoticeOrderDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaReturnNoticeOrderDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      ReturnNoticeOrderQuery returnNoticeOrderQuery = new ReturnNoticeOrderQuery();
      returnNoticeOrderQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      returnNoticeOrderQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<ReturnNoticeOrder> returnNoticeOrders= returnNoticeOrderService.listPage(returnNoticeOrderQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(returnNoticeOrders)){
        response = new EekaReturnNoticeOrderDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(returnNoticeOrders.getData())){
          List<String> dataList = new ArrayList<>(returnNoticeOrders.getData().size());
          returnNoticeOrders.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(returnNoticeOrders.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaReturnNoticeOrderDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * 字典信息
   */
  @PostMapping(params = "method=18o1796dfy.greatonce.oms.general.data.dict.item.list")
  public EekaDataDictItemDataResponse dataDictItemData(HttpServletRequest servletRequest) {
    EekaDataDictItemDataRequest request = checkSign(servletRequest, EekaDataDictItemDataRequest.class);
    EekaDataDictItemDataResponse response = null;
    if (!Assert.isNull(request)) {
      String message = validate(request.getBeginTime(),request.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaDataDictItemDataResponse(apiIdGenerator.next(),message);
        return response;
      }
      DataDictItemQuery dataDictItemQuery = new DataDictItemQuery();
      dataDictItemQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(request.getBeginTime()));
      dataDictItemQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(request.getEndTime()));
      PageList<DataDictItem> dataDictItems= dataDictItemService.listPage(dataDictItemQuery,request.getPage(),request.getPageSize());
      if(!Assert.isNull(dataDictItems)){
        response = new EekaDataDictItemDataResponse(apiIdGenerator.next());
        if(!Assert.isEmpty(dataDictItems.getData())){
          List<String> dataList = new ArrayList<>(dataDictItems.getData().size());
          dataDictItems.getData().forEach(x->{
            dataList.add(JSON.toJSONString(x));
          });
          response.setOmsData(dataList);
        }
        response.setTotalSize(String.valueOf(dataDictItems.getTotal()));
      }
    }
    if(Assert.isNull(response)){
      response = new EekaDataDictItemDataResponse(apiIdGenerator.next());
    }
    return response;
  }


  /**
   * sku查询
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.data.list")
  public EekaDataListResponse productSkuQuery(HttpServletRequest servletRequest) {
    EekaDataListQueryRequest request = checkSign(servletRequest,
        EekaDataListQueryRequest.class);
    EekaDataListResponse response = null;
    if (!Assert.isNull(request.getQuery())) {
      EekaDataListQueryRequest.DataQuery dataQuery = JsonUtil.toObject(request.getQuery(),EekaDataListQueryRequest.DataQuery.class);
      String message = validate(dataQuery.getBeginTime(),dataQuery.getEndTime(),request.getPage(),request.getPageSize());
      if(!Assert.isEmpty(message)){
        response = new EekaDataListResponse(apiIdGenerator.next(),message);
        return response;
      }
      if(!Assert.isNull(request.getDataType())){
        if("stockOutBatch".equals(request.getDataType())){
          StockOutBatchQuery stockOutBatchQuery = new StockOutBatchQuery();
          stockOutBatchQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(dataQuery.getBeginTime()));
          stockOutBatchQuery.setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(dataQuery.getEndTime()));
          PageList<StockOutBatch> stockOutBatchs = stockOutBatchService.listPage(stockOutBatchQuery,request.getPage(),request.getPageSize());
          if(!Assert.isNull(stockOutBatchs)){
            response = new EekaDataListResponse(apiIdGenerator.next());
            if(!Assert.isEmpty(stockOutBatchs.getData())){
              List<String> dataList = new ArrayList<>(stockOutBatchs.getData().size());
              stockOutBatchs.getData().forEach(x->{
                dataList.add(JSON.toJSONString(x));
              });
              response.setData(dataList);
            }
            response.setTotalSize(String.valueOf(stockOutBatchs.getTotal()));
          }
        }else if ("stockOutBatchDetail".equals(request.getDataType())) {
          StockOutBatchDetailQuery stockOutBatchDetailQuery = new StockOutBatchDetailQuery();
          stockOutBatchDetailQuery.setModifiedTimeBegin(DateTimeUtil.parserLocalDateTime(dataQuery.getBeginTime()));
          stockOutBatchDetailQuery
              .setModifiedTimeEnd(DateTimeUtil.parserLocalDateTime(dataQuery.getEndTime()));
          PageList<StockOutBatchDetail> stockOutBatchDetails = stockOutBatchDetailService.listPage(stockOutBatchDetailQuery,request.getPage(),request.getPageSize());
          if(!Assert.isNull(stockOutBatchDetails)){
            response = new EekaDataListResponse(apiIdGenerator.next());
            if(!Assert.isEmpty(stockOutBatchDetails.getData())){
              List<String> dataList = new ArrayList<>(stockOutBatchDetails.getData().size());
              stockOutBatchDetails.getData().forEach(x->{
                dataList.add(JSON.toJSONString(x));
              });
              response.setData(dataList);
            }
            response.setTotalSize(String.valueOf(stockOutBatchDetails.getTotal()));
          }
        }
      }
    }
    if(Assert.isNull(response)){
      response = new EekaDataListResponse(apiIdGenerator.next());
    }
    return response;
  }

  /**
   * 校验数据
   * @return
   */
  private String validate(String beginDate,String endDate,int page,int pageSize){
    String message = null;
    if(Assert.isEmpty(beginDate)){
      message = "开始时间不能为空";
      return message;
    }
    if(Assert.isEmpty(endDate)){
      message = "结束时间不能为空";
      return message;
    }
    if(Assert.isNull(page) || Assert.isEmpty(String.valueOf(page))){
      message = "当前页不能为空";
      return message;
    }
    if(Assert.isNull(pageSize) || Assert.isEmpty(String.valueOf(pageSize))){
      message = "每页总数不能为空";
      return message;
    }
    return message;
  }
}
