package com.greatonce.oms.custom.eeka.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.admin.RegionService;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.biz.trade.SalesOrderSubService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.bridge.wms.DeliveryOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest;
import com.greatonce.oms.bridge.wms.request.DeliveryOrderCreateRequest.DeliveryOrderDetail;
import com.greatonce.oms.bridge.wms.response.DeliveryOrderCreateResponse;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.domain.admin.Region;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WarehouseType;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.domain.trade.SalesOrderSub;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.trade.DispatchOrderDeliveryMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

/**
 * B2C发货单过账回传.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@Component
@EekaConsumerCondition
@RabbitListener(queues = EekaConstants.QUEUE_DELIVERY_ORDER_NOTICE, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class DispatchOrderNoticeFmsConsumer extends AbstractCustomConsumer {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(DispatchOrderNoticeFmsConsumer.class);

  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private DispatchOrderDetailService dispatchOrderDetailService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private ExpressService expressService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private StoreService storeService;
  @Autowired
  private RegionService regionService;
  @Autowired
  private EekaHelper eekaHelper;
  @Autowired
  private DispatchOrderDeliveryService dispatchOrderDeliveryService;
  @Autowired
  private SalesOrderSubService salesOrderSubService;

  @RabbitHandler
  void process(DispatchOrderDeliveryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      DispatchOrder dispatchOrder = dispatchOrderService.getByKey(message.getDispatchOrderId());
      posting(dispatchOrder);
    });
  }

  @RabbitHandler
  void process(DataRepostingMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      DispatchOrder dispatchOrder = dispatchOrderService.getByKey(message.getDataId());
      posting(dispatchOrder);
    });
  }

  private void posting(DispatchOrder dispatchOrder) {
    if (dispatchOrder.isDeliveryFinish()) {
      List<DispatchOrderDetail> dispatchOrderDetails = dispatchOrderDetailService
          .listByDispatchOrderId(dispatchOrder.getDispatchOrderId());
      Warehouse warehouse = warehouseService.getByKey(dispatchOrder.getWarehouseId());
      //只有总仓才需要通知FMS
      if (warehouse.getWarehouseType() == WarehouseType.OWN) {
        warehouse.setWmsApp(eekaHelper.getFmsApp());
        Store store = storeService.getByKey(dispatchOrder.getStoreId());
        SecurityBridge securityBridge = mallBridgeFactory
            .getSecurityBridge(store.getMallType());
        DeliveryOrderBridge deliveryOrderBridge = wmsBridgeFactory
            .getDeliveryOrderBridge(warehouse.getWmsApp().getWmsType());
        DeliveryOrderCreateRequest request = new DeliveryOrderCreateRequest(warehouse);
        request.setOmsCode(dispatchOrder.getDispatchOrderCode());
        List<DispatchOrderDelivery> dispatchOrderDeliveries = dispatchOrderDeliveryService.listByDispatchOrderId(dispatchOrder.getDispatchOrderId());
        if(!Assert.isEmpty(dispatchOrderDeliveries)){
          request.setWmsCode(dispatchOrderDeliveries.get(0).getOuterCode());
          Map<String,String> extendMap = new HashMap<>();
          extendMap.put("deliveryTime", DateTimeUtil.format(dispatchOrderDeliveries.get(0).getDeliveryTime()));
          extendMap.put("createTime",DateTimeUtil.format(dispatchOrder.getCreatedTime()));
          request.setExtendProps(extendMap);
        }
        request.setTotalAmount(dispatchOrder.getSettlementAmount());
        request.setPaidAmount(dispatchOrder.getActualAmount());

        request.setOrderType(OrderType.OUT_ORDER);
        request.setCreatedTime(LocalDateTime.now());
        request.setMallCreatedTime(LocalDateTime.now());
        request.setStoreCode(store.getStoreCode());
        request.setStoreName(store.getStoreName());
        request.setMallType(store.getMallType());

        request.setExpressCode(dispatchOrder.getSuggestExpressId() == null ?
            null : expressService.getWmsExpressCode(warehouse.getWmsAppId(),
            dispatchOrder.getSuggestExpressId()));
        request.setExpressName(dispatchOrder.getSuggestExpressName());
        request.setExpressNo(dispatchOrder.getSuggestExpressNo());

        request.setSenderName(warehouse.getWarehouseName());
        request.setSenderTelephone(warehouse.getTelephone());
        request.setSenderMobile(warehouse.getMobile());
        request.setSenderCountry(warehouse.getCountryName());
        request.setSenderProvince(warehouse.getProvinceName());
        request.setSenderCity(warehouse.getCityName());
        request.setSenderDistrict(warehouse.getDistrictName());
        request.setSenderAddress(warehouse.getAddress());

        request.setReceiverName(securityBridge
            .decrypt(store, dispatchOrder.getContact(), DataType.NAME));
        request.setReceiverTelephone(securityBridge
            .decrypt(store, dispatchOrder.getTelephone(),
                DataType.TELEPHONE));
        request.setReceiverMobile(securityBridge
            .decrypt(store, dispatchOrder.getMobile(), DataType.MOBILE));

        request.setReceiverCountry(dispatchOrder.getCountryName());
        request.setReceiverProvince(dispatchOrder.getProvinceName());
        request.setReceiverCity(dispatchOrder.getCityName());
        request.setReceiverDistrict(dispatchOrder.getDistrictName());
        request.setReceiverAddress(dispatchOrder.getAddress());

        if (Assert.isTrue(dispatchOrder.isCod())) {
          request.setCodAmount(dispatchOrder.getCodAmount());
        }

        Region provinceRegion = regionService.getByKey(dispatchOrder.getProvinceId());
        if (provinceRegion != null) {
          request.setReceiverProvinceCode(provinceRegion.getRegionCode());
        }

        List<DeliveryOrderDetail> details = new ArrayList<>(dispatchOrderDetails.size());
        if (!Assert.isEmpty(dispatchOrderDetails)) {
          for (DispatchOrderDetail dispatchOrderDetail : dispatchOrderDetails) {
            DeliveryOrderDetail detail = new DeliveryOrderDetail();
            detail.setTradeId(dispatchOrderDetail.getTradeId());
            detail.setSkuCode(dispatchOrderDetail.getSkuCode());
            detail.setWmsSkuId(String.valueOf(warehouse.getWarehouseId()));
            detail.setProductName(dispatchOrderDetail.getProductName());
            detail.setQuantity(dispatchOrderDetail.getNoticeQuantity());
            detail.setPrice(dispatchOrderDetail.getSettlementPrice());
            detail.setAmount(dispatchOrderDetail.getSettlementAmount());
            details.add(detail);
          }
          SalesOrderSub salesOrderSub = salesOrderSubService.getByKey(dispatchOrderDetails.get(0).getSalesOrderId());
          if(!Assert.isNull(salesOrderSub)){
            request.setMallPaidTime(salesOrderSub.getMallPaidTime());
          }
        }
        request.setDetails(details);

        DeliveryOrderCreateResponse response = deliveryOrderBridge.createOrder(request);
        if (!response.isSuccess()) {
          LOGGER.error("发货单推送FMS过账失败！{},{}", dispatchOrder.getDispatchOrderCode(),
              response.getMessage());
          dispatchOrderService.getBizLogger()
              .log(dispatchOrder.getDispatchOrderId(), "过账", "失败！" + response.getMessage());
        } else {
          DispatchOrder disOrder = new DispatchOrder();
          disOrder.setDispatchOrderId(dispatchOrder.getDispatchOrderId());
          disOrder.setPostStatus(PostStatus.POSTED);
          disOrder.setVersion(dispatchOrder.getVersion());
          dispatchOrderService.modify(disOrder);
          dispatchOrderService.getBizLogger()
              .log(dispatchOrder.getDispatchOrderId(), "过账", "成功");
        }
      }
    } else {
      LOGGER.debug("发货单未完全发货，不推送过账", dispatchOrder.getDispatchOrderCode());
    }
  }
}
