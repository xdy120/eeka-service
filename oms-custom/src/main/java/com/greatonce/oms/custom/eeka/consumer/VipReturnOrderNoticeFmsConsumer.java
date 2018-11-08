package com.greatonce.oms.custom.eeka.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.vip.VipReturnNoticeDetailService;
import com.greatonce.oms.biz.vip.VipReturnNoticeService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.bridge.wms.StockInOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.StockInOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.StockInOrderCreateResponse;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WarehouseType;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.vip.VipReturnNoticeEntryMessage;
import com.greatonce.oms.query.vip.VipReturnNoticeDetailQuery;
import com.rabbitmq.client.Channel;
import java.io.IOException;
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
 * 唯品退货单过账回传.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@Component
@EekaConsumerCondition
@RabbitListener(queues = EekaConstants.QUEUE_VIP_RETURN_ORDER_NOTICE, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class VipReturnOrderNoticeFmsConsumer extends AbstractCustomConsumer {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(VipReturnOrderNoticeFmsConsumer.class);

  @Autowired
  private EekaHelper eekaHelper;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private VipReturnNoticeService vipReturnNoticeService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private VipReturnNoticeDetailService vipReturnNoticeDetailService;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private StoreService storeService;


  @RabbitHandler
  void process(DataRepostingMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      VipReturnNotice vipReturnNotice = vipReturnNoticeService
          .getByKey(message.getDataId());
      posting(vipReturnNotice);
    });
  }

  @RabbitHandler
  void process(VipReturnNoticeEntryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      VipReturnNotice vipReturnNotice = vipReturnNoticeService
          .getByKey(message.getVipReturnNoticeId());
      posting(vipReturnNotice);
    });
  }

  private void posting(VipReturnNotice vipReturnNotice) {
    Warehouse warehouse = warehouseService.getByKey(vipReturnNotice.getWarehouseId());
    //只有总仓才需要通知FMS
    if (warehouse.getWarehouseType() == WarehouseType.OWN) {
      warehouse.setWmsApp(eekaHelper.getFmsApp());
      StockInOrderBridge stockInOrderBridge = wmsBridgeFactory
          .getStockInOrderBridge(warehouse.getWmsApp().getWmsType());
      StockInOrderCreateRequest request = new StockInOrderCreateRequest(warehouse);
      Store store = storeService.getByKey(vipReturnNotice.getStoreId());
      SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
      VipReturnNoticeDetailQuery vipReturnNoticeDetailQuery = new VipReturnNoticeDetailQuery();
      vipReturnNoticeDetailQuery.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
      List<VipReturnNoticeDetail> stockInOrderDetailList = vipReturnNoticeDetailService
          .list(vipReturnNoticeDetailQuery);

      request.setStoreCode(store.getStoreCode());
      request.setStoreName(store.getStoreName());
      request.setOmsCode(vipReturnNotice.getVipReturnNoticeCode());
      request.setWmsCode(vipReturnNotice.getOuterCode());
      request.setOrderType(OrderType.VIP_RETURN_NOTICE_ORDER);
      request.setCreatedTime(vipReturnNotice.getLastInTime());

      request.setReceiverName(
          securityBridge.decrypt(store, warehouse.getContact(), DataType.NAME));
      request.setReceiverTelephone(
          securityBridge.decrypt(store, warehouse.getTelephone(), DataType.NAME));
      request.setReceiverMobile(
          securityBridge.decrypt(store, warehouse.getMobile(), DataType.NAME));
      request.setReceiverProvince(String.valueOf(warehouse.getProvinceId()));
      request.setReceiverCity(String.valueOf(warehouse.getCityId()));
      request.setReceiverAddress(warehouse.getAddress());

      request.setSenderName(warehouse.getContact());
      request.setSenderMobile(warehouse.getMobile());
      request.setSenderCountry(warehouse.getCountryName());
      request.setSenderProvince(warehouse.getProvinceName());
      request.setSenderCity(warehouse.getCityName());
      request.setSenderArea(warehouse.getDistrictName());
      request.setSenderAddress(warehouse.getAddress());

      List<StockInOrderCreateRequest.StockInOrderDetail> detailList = new ArrayList<>(
          stockInOrderDetailList.size());
      for (VipReturnNoticeDetail orderDetail : stockInOrderDetailList) {
        StockInOrderCreateRequest.StockInOrderDetail detail = new StockInOrderCreateRequest.StockInOrderDetail();
        detail.setSkuCode(orderDetail.getSkuCode());
        detail.setWmsSkuId(orderDetail.getSkuName());
        detail.setProductCode(orderDetail.getProductCode());
        detail.setProductName(orderDetail.getProductName());
        detail.setQuantity(orderDetail.getInQuantity());
        if (Assert.isNull(orderDetail.getVipPrice()) || orderDetail.getVipPrice() == 0) {
          vipReturnNoticeService.getBizLogger()
              .log(vipReturnNotice.getVipReturnNoticeId(), "过账", "过账金额不能为0");
          throw new RuntimeException("过账金额不能为0");
        } else {
          detail.setSellingPrice(orderDetail.getVipPrice());
          if (Assert.isNull(orderDetail.getVipAmount()) || orderDetail.getVipAmount() == 0) {
            vipReturnNoticeService.getBizLogger()
                .log(vipReturnNotice.getVipReturnNoticeId(), "过账", "过账金额不能为0");
            throw new RuntimeException("过账金额不能为0");
          }else{
            detail.setSellingAmount(orderDetail.getVipAmount());
          }
        }
        detailList.add(detail);
      }
      request.setDetails(detailList);
      Map<String,String> extendMap = new HashMap<>();
      extendMap.put("lastInTime", DateTimeUtil.format(vipReturnNotice.getLastInTime()));
      extendMap.put("createTime",DateTimeUtil.format(vipReturnNotice.getCreatedTime()));
      request.setExtendProps(extendMap);
      StockInOrderCreateResponse response = stockInOrderBridge.createOrder(request);
      if (!response.isSuccess()) {
        LOGGER.error("唯品退货单推送FMS过账失败！{},{}", vipReturnNotice.getVipReturnNoticeCode(),
            response.getMessage());
        vipReturnNoticeService.getBizLogger()
            .log(vipReturnNotice.getVipReturnNoticeId(), "过账", "失败！" + response.getMessage());
      } else {
        VipReturnNotice reOrder = new VipReturnNotice();
        reOrder.setVipReturnNoticeId(vipReturnNotice.getVipReturnNoticeId());
        reOrder.setPostStatus(PostStatus.POSTED);
        reOrder.setVersion(vipReturnNotice.getVersion());
        vipReturnNoticeService.modify(reOrder);
        vipReturnNoticeService.getBizLogger()
            .log(vipReturnNotice.getVipReturnNoticeId(), "过账", "成功");
      }
    }
  }
}
