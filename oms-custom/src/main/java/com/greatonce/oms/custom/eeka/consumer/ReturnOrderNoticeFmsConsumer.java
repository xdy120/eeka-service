package com.greatonce.oms.custom.eeka.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.ExpressService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnNoticeOrderService;
import com.greatonce.oms.biz.trade.ReturnOrderDetailService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.wms.ReturnOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.ReturnOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.ReturnOrderCreateResponse;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WarehouseType;
import com.greatonce.oms.domain.trade.ReturnNoticeOrder;
import com.greatonce.oms.domain.trade.ReturnNoticeOrderDetail;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.trade.ReturnNoticeOrderEntryMessage;
import com.greatonce.oms.message.trade.ReturnOrderReviewMessage;
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
 * B2C退货单过账回传.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@Component
@EekaConsumerCondition
@RabbitListener(queues = EekaConstants.QUEUE_RETURN_ORDER_NOTICE, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class ReturnOrderNoticeFmsConsumer extends AbstractCustomConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReturnOrderNoticeFmsConsumer.class);
  @Autowired
  private ReturnOrderService returnOrderService;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private ExpressService expressService;
  @Autowired
  private ReturnOrderDetailService returnOrderDetailService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private EekaHelper eekaHelper;
  @Autowired
  private ReturnNoticeOrderDetailService returnNoticeOrderDetailService;
  @Autowired
  private ReturnNoticeOrderService returnNoticeOrderService;


  @RabbitHandler
  void process(ReturnOrderReviewMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      //复核的消息暂不处理
    });
  }

  @RabbitHandler
  void process(ReturnNoticeOrderEntryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      //过账给FMS
      if (!Assert.isNull(message.getReturnNoticeOrderId())) {
        ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService.getByKey(message.getReturnNoticeOrderId());
        if(!Assert.isNull(returnNoticeOrder)){
          posting(returnNoticeOrder);
        }
      }
    });
  }

  @RabbitHandler
  void process(DataRepostingMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      ReturnNoticeOrder returnNoticeOrder = returnNoticeOrderService.getByKey(message.getDataId());
      if(!Assert.isNull(returnNoticeOrder)){
        posting(returnNoticeOrder);
      }
    });
  }

  private void posting(ReturnNoticeOrder returnNoticeOrder) {

    ReturnNoticeOrderDetail returnNoticeOrderDetail = new ReturnNoticeOrderDetail();
    returnNoticeOrderDetail.setReturnNoticeOrderId(returnNoticeOrder.getReturnNoticeOrderId());
    List<ReturnNoticeOrderDetail> returnNoticeOrderDetails = returnNoticeOrderDetailService
        .listExample(returnNoticeOrderDetail);
    if (!Assert.isEmpty(returnNoticeOrderDetails)) {
      ReturnNoticeOrderDetail rnDetail = returnNoticeOrderDetails.get(0);
      ReturnOrder returnOrder = returnOrderService.getByKey(rnDetail.getReturnOrderId());
      if(!Assert.isNull(returnOrder)){
        VirtualWarehouse virtualWarehouse = virtualWarehouseService
            .getByKey(returnOrder.getInVirtualWarehouseId());
        Warehouse warehouse = warehouseService.getByKey(virtualWarehouse.getWarehouseId());
        //只有总仓才需要通知FMS
        if (warehouse.getWarehouseType() == WarehouseType.OWN) {
          Store store = storeService.getByKey(returnOrder.getStoreId());
          warehouse.setWmsApp(eekaHelper.getFmsApp());
          ReturnOrderBridge returnOrderBridge = wmsBridgeFactory
              .getReturnOrderBridge(warehouse.getWmsApp().getWmsType());
          ReturnOrderCreateRequest request = new ReturnOrderCreateRequest(warehouse);
          request.setOmsCode(returnNoticeOrder.getReturnNoticeOrderCode());
          request.setOrderType(OrderType.B2C_RETURN_ORDER);
          request.setCreatedTime(LocalDateTime.now());
          request.setModifiedTime(LocalDateTime.now());
          request.setStoreCode(store.getStoreCode());
          request.setStoreName(store.getStoreName());
          List<ReturnOrderCreateRequest.ReturnOrderDetail> details = new ArrayList<>();
          returnNoticeOrderDetails.stream().forEach(x -> {
            ReturnOrderDetail returnOrderDetail = returnOrderDetailService.getByKey(x.getReturnOrderDetailId());
            if (!Assert.isNull(returnOrderDetail)) {
              ReturnOrderCreateRequest.ReturnOrderDetail detail = new ReturnOrderCreateRequest.ReturnOrderDetail();
              detail.setTradeId(returnOrder.getTradeId());
              detail.setSkuCode(returnOrderDetail.getSkuCode());
              detail.setWmsSkuId(String.valueOf(warehouse.getWarehouseId()));
              detail.setProductName(returnOrderDetail.getProductName());
              detail.setQuantity(x.getInQuantity());
              detail.setAmount(returnOrderDetail.getRefundableAmount());
              details.add(detail);
            }
          });
          request.setDetails(details);

          Map<String,String> extendMap = new HashMap<>();
          if(!Assert.isNull(returnNoticeOrder)){
            extendMap.put("lastInTime", DateTimeUtil.format(returnNoticeOrder.getLastInTime()));
            request.setWmsCode(returnNoticeOrder.getOuterCode());
          }
          extendMap.put("createTime",DateTimeUtil.format(returnNoticeOrder.getCreatedTime()));
          request.setExtendProps(extendMap);
          ReturnOrderCreateResponse response = returnOrderBridge.createOrder(request);
          if (!response.isSuccess()) {
            LOGGER
                .error("退换货单推送FMS过账失败！{},{}", returnNoticeOrder.getReturnNoticeOrderCode(),
                    response.getMessage());
            returnNoticeOrderService.getBizLogger()
                .log(returnNoticeOrder.getReturnNoticeOrderId(), "过账", "失败！" + response.getMessage());
          } else {
            ReturnNoticeOrder rnOrder = new ReturnNoticeOrder();
            rnOrder.setReturnNoticeOrderId(returnNoticeOrder.getReturnNoticeOrderId());
            rnOrder.setPostStatus(PostStatus.POSTED);
            rnOrder.setVersion(returnNoticeOrder.getVersion());
            returnNoticeOrderService.modify(rnOrder);
            returnNoticeOrderService.getBizLogger()
                .log(returnNoticeOrder.getReturnNoticeOrderId(), "过账", "成功");
          }
        }
      }
    }
  }
}
