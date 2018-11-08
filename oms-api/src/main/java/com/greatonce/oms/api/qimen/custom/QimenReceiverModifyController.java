package com.greatonce.oms.api.qimen.custom;

import com.greatonce.core.database.ManualTransactionTemplate;
import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.api.qimen.custom.request.OmsReceiverModifyRequest;
import com.greatonce.oms.api.qimen.custom.response.OmsReceiverModifyResponse;
import com.greatonce.oms.biz.admin.RegionService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.bo.admin.RegionMatchBO;
import com.greatonce.oms.bo.trade.DispatchOrderCancelBO;
import com.greatonce.oms.bo.trade.SalesOrderModifyReceiverInfoBO;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.OrderBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.bridge.mall.request.OrderReceiverInfoUpdateRequest;
import com.greatonce.oms.bridge.mall.response.OrderReceiverInfoUpdateResponse;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.enums.trade.DeliveryStatus;
import com.greatonce.oms.domain.enums.trade.DispatchStatus;
import com.greatonce.oms.domain.enums.trade.SalesOrderStatus;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.SalesOrder;
import com.greatonce.oms.query.trade.SalesOrderQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 收货人信息修改
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author skp
 * @version 2018/9/27
 */
@RestController
public class QimenReceiverModifyController extends QimenCustomController {

  private static Logger LOGGER = LoggerFactory.getLogger(QimenReceiverModifyController.class);
  private static final String CACHE_NAME = "RECEIVER_INFO";
  @Autowired
  private RedisTemplate redisTemplate;
  @Autowired
  private ManualTransactionTemplate transactionTemplate;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private RegionService regionService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;

  @PostMapping(params = "method=order.receiver.modify")
  public OmsReceiverModifyResponse modifyReceiverInfo(HttpServletRequest servletRequest) {
    OmsReceiverModifyRequest request = checkSign(servletRequest, OmsReceiverModifyRequest.class);
    return process(request);
  }

  @PostMapping(params = "method=170")
  public OmsReceiverModifyResponse receiverModifyInfo170(@Param("method") String content) {
    OmsReceiverModifyRequest request = JsonUtil
        .toObject(content, OmsReceiverModifyRequest.class);
    return process(request);
  }

  private OmsReceiverModifyResponse process(OmsReceiverModifyRequest request) {
    Store store = storeService.getByKey(request.getStoreId());
    if (Assert.isNull(store)) {
      LOGGER.error("奇门调用收货人信息修改接口失败。原因：数据库中查询不到相关的店铺。店铺id：{}", request.getStoreId());
      return new OmsReceiverModifyResponse(1, null, "系统中不存在该店铺");
    }
    SalesOrderQuery query = new SalesOrderQuery();
    query.setTradeId(request.getTid());
    query.setStoreId(request.getStoreId());
    List<SalesOrder> salesOrders = salesOrderService.list(query);
    if (Assert.isEmpty(salesOrders)) {
      saveReceiverInfo(request);
      LOGGER.info("奇门调用收货人信息修改接口，数据已缓存，等待订单转化修改。原因：目前数据库中查询不到相关的订单。交易号：{}；商店id：{}",
          request.getTid(), request.getStoreId());
      return new OmsReceiverModifyResponse(0, null, "成功，等待订单转化修改");
    }
    RegionMatchBO regionMatchBO = regionService
        .matchRegion(request.getCountry(), request.getProvinceName(), request.getCityName(),
            request.getCountyName());
    if (!regionMatchBO.isMatch()) {
      LOGGER.error("奇门调用收货人信息修改接口失败。原因：匹配不到对应的地址。请求：{}", JsonUtil.toJson(request));
      return new OmsReceiverModifyResponse(1, null, "系统匹配不到对应的地址");
    }
    SalesOrderModifyReceiverInfoBO modifyBO = buildReceiverInfoBO(request, store, regionMatchBO);

    boolean isSendRequest = transactionTemplate.executeWithResult(() -> {
      boolean isValid = false;
      for (SalesOrder order : salesOrders) {
        modifyBO.setVersion(order.getVersion());
        if (order.getStatus() == SalesOrderStatus.DELIVERED
            || order.getStatus() == SalesOrderStatus.INVALID
            || (order.getDispatchStatus() != DispatchStatus.NONE
            && order.getDeliveryStatus() != DeliveryStatus.NONE)) {
          continue;
        }
        if (order.getDispatchStatus() == DispatchStatus.NONE) {
          salesOrderService.modifyReceiverInfo(order, modifyBO);
        } else if (order.getDispatchStatus() != DispatchStatus.NONE
            && order.getDeliveryStatus() == DeliveryStatus.NONE) {
          cancelDispatchOrder(order);
          salesOrderService.modifyReceiverInfo(order, modifyBO);
        }
        isValid = true;
      }
      return isValid;
    });
    if (!isSendRequest) {
      LOGGER.error("系统中的订单为已发货或作废，无法修改。交易号：{}；商店id：{}",
          request.getTid(), request.getStoreId());
      return new OmsReceiverModifyResponse(1, null, "系统中的订单为已发货或作废，无法修改");
    }

    OrderReceiverInfoUpdateResponse mallResponse = updateMallReceiverInfo(request, store, modifyBO);
    if (!mallResponse.isSuccess()) {
      LOGGER.error("调用平台修改收货人信息接口失败。交易号：{}；商店id：{}；错误信息：{}", request.getTid(),
          request.getStoreId(), mallResponse.getResult());
      return new OmsReceiverModifyResponse(1, null,
          "调用平台修改收货人信息接口失败。错误信息：" + mallResponse.getResult());
    }
    return new OmsReceiverModifyResponse(0, null, "成功");
  }

  private OrderReceiverInfoUpdateResponse updateMallReceiverInfo(OmsReceiverModifyRequest request,
      Store store, SalesOrderModifyReceiverInfoBO modifyBO) {
    OrderReceiverInfoUpdateRequest mallRequest = new OrderReceiverInfoUpdateRequest(store);
    mallRequest.setTradeId(request.getTid());
    mallRequest.setContact(modifyBO.getContact());
    mallRequest.setTelephone(modifyBO.getTelephone());
    mallRequest.setMobile(modifyBO.getMobile());
    mallRequest.setProvinceName(modifyBO.getProvinceName());
    mallRequest.setCityName(modifyBO.getCityName());
    mallRequest.setDistrictName(modifyBO.getDistrictName());
    mallRequest.setAddress(modifyBO.getAddress());
    OrderBridge orderBridge = mallBridgeFactory.getOrderBridge(store.getMallType());
    return orderBridge.updateOrderReceiverInfo(mallRequest);
  }

  private void cancelDispatchOrder(SalesOrder order) {
    List<DispatchOrder> dispatchOrders = dispatchOrderService
        .listBySalesOrderId(order.getSalesOrderId());
    if (!Assert.isEmpty(dispatchOrders)) {
      for (DispatchOrder dispatchOrder : dispatchOrders) {
        DispatchOrderCancelBO cancelBO = new DispatchOrderCancelBO();
        cancelBO.setVersion(dispatchOrder.getVersion());
        cancelBO.setReason("收货人信息修改");
        dispatchOrderService.cancel(dispatchOrder, cancelBO);
      }
    }
  }

  private SalesOrderModifyReceiverInfoBO buildReceiverInfoBO(OmsReceiverModifyRequest request,
      Store store, RegionMatchBO regionMatchBO) {
    SalesOrderModifyReceiverInfoBO modifyBO = new SalesOrderModifyReceiverInfoBO();
    if (regionMatchBO.isMatchMapping()) {
      modifyBO.setCountryId(regionMatchBO.getMapping().getCountryId());
      modifyBO.setCountryName(regionMatchBO.getMapping().getCountryName());
      modifyBO.setProvinceId(regionMatchBO.getMapping().getProvinceId());
      modifyBO.setProvinceName(regionMatchBO.getMapping().getProvinceName());
      modifyBO.setCityId(regionMatchBO.getMapping().getCityId());
      modifyBO.setCityName(regionMatchBO.getMapping().getCityName());
      modifyBO.setDistrictId(regionMatchBO.getMapping().getDistrictId());
      modifyBO.setDistrictName(regionMatchBO.getMapping().getDistrictName());
    } else {
      modifyBO.setCountryId(regionMatchBO.getCountry().getRegionId());
      modifyBO.setCountryName(regionMatchBO.getCountry().getRegionName());
      modifyBO.setProvinceId(regionMatchBO.getProvince().getRegionId());
      modifyBO.setProvinceName(regionMatchBO.getProvince().getRegionName());
      modifyBO.setCityId(regionMatchBO.getCity().getRegionId());
      modifyBO.setCityName(regionMatchBO.getCity().getRegionName());
      modifyBO.setDistrictId(regionMatchBO.getDistrict().getRegionId());
      modifyBO.setDistrictName(regionMatchBO.getDistrict().getRegionName());
    }
    modifyBO.setAddress(request.getAddress());
    SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
    modifyBO
        .setEncryptContact(securityBridge.encrypt(store, request.getConsignee(), DataType.NAME));
    modifyBO.setEncryptMobile(securityBridge.encrypt(store, request.getMobile(), DataType.MOBILE));
    modifyBO.setContact(request.getConsignee());
    modifyBO.setMobile(request.getMobile());
    return modifyBO;
  }

  private void saveReceiverInfo(OmsReceiverModifyRequest request) {
    Map<String, String> map = new HashMap<>(11);
    map.put("tid", request.getTid());
    map.put("storeId", String.valueOf(request.getStoreId()));
    map.put("country", request.getCountry());
    map.put("provinceName", request.getProvinceName());
    map.put("cityName", request.getCityName());
    map.put("countyName", request.getCountyName());
    map.put("town", request.getTown());
    map.put("address", request.getAddress());
    map.put("postCode", request.getPostCode());
    map.put("consignee", request.getConsignee());
    map.put("mobile", request.getMobile());

    redisTemplate.opsForValue().set(CACHE_NAME + "::TRADE_ID_" + request.getTid(), map);
  }

}
