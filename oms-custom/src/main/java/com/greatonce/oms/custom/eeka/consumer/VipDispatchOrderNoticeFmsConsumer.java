package com.greatonce.oms.custom.eeka.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.DateTimeUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.stock.StockOutBatchDetailService;
import com.greatonce.oms.biz.stock.StockOutBatchService;
import com.greatonce.oms.biz.vip.VipDeliveryService;
import com.greatonce.oms.biz.vip.VipDispatchDetailService;
import com.greatonce.oms.biz.vip.VipDispatchService;
import com.greatonce.oms.bridge.mall.MallBridgeFactory;
import com.greatonce.oms.bridge.mall.SecurityBridge;
import com.greatonce.oms.bridge.mall.SecurityBridge.DataType;
import com.greatonce.oms.bridge.wms.StockOutOrderBridge;
import com.greatonce.oms.bridge.wms.WmsBridgeFactory;
import com.greatonce.oms.bridge.wms.request.StockOutOrderCreateRequest;
import com.greatonce.oms.bridge.wms.response.StockOutOrderCreateResponse;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.enums.OrderType;
import com.greatonce.oms.domain.enums.PostStatus;
import com.greatonce.oms.domain.enums.WarehouseType;
import com.greatonce.oms.domain.stock.StockOutBatch;
import com.greatonce.oms.domain.stock.StockOutBatchDetail;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.message.DataRepostingMessage;
import com.greatonce.oms.message.vip.VipDispatchDeliveryMessage;
import com.greatonce.oms.query.vip.VipDispatchDetailQuery;
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
 * 唯品发货单过账回传.
 *
 * @author Shenzhen Greatonce Co Ltd
 * @author ginta
 * @version 2018/4/20
 */
@Component
@EekaConsumerCondition
@RabbitListener(queues = EekaConstants.QUEUE_VIP_DISPATCH_ORDER_DELIVERY_NOTICE, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class VipDispatchOrderNoticeFmsConsumer extends AbstractCustomConsumer {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(VipDispatchOrderNoticeFmsConsumer.class);

  @Autowired
  private EekaHelper eekaHelper;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private VipDispatchService vipDispatchService;
  @Autowired
  private WmsBridgeFactory wmsBridgeFactory;
  @Autowired
  private MallBridgeFactory mallBridgeFactory;
  @Autowired
  private StoreService storeService;
  @Autowired
  private VipDispatchDetailService vipDispatchDetailService;
  @Autowired
  private VipDeliveryService vipDeliveryService;
  @Autowired
  private StockOutBatchService stockOutBatchService;
  @Autowired
  private StockOutBatchDetailService stockOutBatchDetailService;


  @RabbitHandler
  void process(VipDispatchDeliveryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      VipDispatch vipDispatch = vipDispatchService.getByKey(message.getVipDispatchId());
      posting(vipDispatch);
    });
  }

  @RabbitHandler
  void process(DataRepostingMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      VipDispatch vipDispatch = vipDispatchService.getByKey(message.getDataId());
      posting(vipDispatch);
    });
  }


  private void posting(VipDispatch vipDispatch) {
    Warehouse warehouse = warehouseService.getByKey(vipDispatch.getWarehouseId());
    //只有总仓才需要通知FMS
    if (warehouse.getWarehouseType() == WarehouseType.OWN) {
      warehouse.setWmsApp(eekaHelper.getFmsApp());
      StockOutOrderBridge stockOutOrderBridge = wmsBridgeFactory
          .getStockOutOrderBridge(warehouse.getWmsApp().getWmsType());
      StockOutOrderCreateRequest request = new StockOutOrderCreateRequest(warehouse);
      Store store = storeService.getByKey(vipDispatch.getStoreId());
      SecurityBridge securityBridge = mallBridgeFactory.getSecurityBridge(store.getMallType());
      VipDispatchDetailQuery vipDispatchDetailQuery = new VipDispatchDetailQuery();
      vipDispatchDetailQuery.setVipDispatchId(vipDispatch.getVipDispatchId());
      List<VipDispatchDetail> stockOutOrderDetailList = vipDispatchDetailService
          .list(vipDispatchDetailQuery);

      VipDelivery vipDelivery = vipDeliveryService.getByKey(vipDispatch.getVipDeliveryId());

      request.setStoreCode(store.getStoreCode());
      request.setStoreName(store.getStoreName());
      request.setOmsCode(vipDispatch.getVipDispatchCode());
      request.setOrderType(OrderType.VIP_DISPATCH_ORDER);
      request.setCreatedTime(vipDispatch.getLastOutTime());

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

      Map<String, Object> map = new HashMap<>(11);
      map.put("arrivalTime", vipDelivery.getArrivalTime());
      map.put("waybillNumber", vipDelivery.getWaybillNumber());
      map.put("pickingCode", vipDispatch.getPickingCode());
      map.put("storageNo", vipDelivery.getStorageNo());
      map.put("carrierName", vipDelivery.getCarrierName());
      map.put("deliveryMethod", vipDelivery.getDeliveryMethodName());
      map.put("brandCode", vipDelivery.getBrandCode());
      map.put("brandName", vipDelivery.getBrandName());
      map.put("vipWarehouseCode", vipDelivery.getVipWarehouseCode());
      map.put("vipWarehouseName", vipDelivery.getVipWarehouseName());

      StockOutOrderCreateResponse response = null;
      synchronized (vipDispatch.getVipDispatchId()){
        StockOutBatch stockOutBatch = new StockOutBatch();
        stockOutBatch.setNoticeOrderCode(vipDispatch.getVipDispatchCode());
        stockOutBatch.setPostStatus(PostStatus.UN_POST);
        List<StockOutBatch> stockOutBatchs = stockOutBatchService.listExample(stockOutBatch);
        if (!Assert.isEmpty(stockOutBatchs)) {
          boolean flag = true;
          for (StockOutBatch sob : stockOutBatchs) {
            List<StockOutOrderCreateRequest.StockOutOrderDetail> detailList = new ArrayList<>(
                stockOutOrderDetailList.size());
            map.put("lastOutTime", DateTimeUtil.format(sob.getOutTime()));
            request.setWmsCode(sob.getWmsOrderCode());
            List<StockOutBatchDetail> stockOutBatchDetails = stockOutBatchDetailService
                .listDetails(sob.getStockOutBatchId());
            if(!Assert.isEmpty(stockOutBatchDetails)){
              for(StockOutBatchDetail stockOutBatchDetail : stockOutBatchDetails){
                StockOutOrderCreateRequest.StockOutOrderDetail detail = new StockOutOrderCreateRequest.StockOutOrderDetail();
                detail.setSkuCode(stockOutBatchDetail.getSkuCode());
                detail.setProductCode(stockOutBatchDetail.getProductCode());
                detail.setQuantity(stockOutBatchDetail.getOutQuantity());
                if (Assert.isNull(stockOutBatchDetail.getOutAmount()) || stockOutBatchDetail.getOutAmount() == 0) {
                  vipDispatchService.getBizLogger()
                      .log(vipDispatch.getVipDispatchId(), "过账", "过账金额不能为0");
                  throw new RuntimeException("过账金额不能为0");
                } else {
                  detail.setSellingPrice(stockOutBatchDetail.getOutAmount());
                  detail.setSellingAmount(stockOutBatchDetail.getOutAmount());
                }
                detailList.add(detail);
              }
            }
            request.setDetails(detailList);
            map.put("createTime", DateTimeUtil.format(vipDispatch.getCreatedTime()));
            request.setExtendProps(map);
            response = stockOutOrderBridge.createOrder(request);
            if (!response.isSuccess()) {
              flag = false;
              LOGGER
                  .error("唯品拣货单推送FMS过账失败！{},{}", sob.getStockOutBatchId(),
                      response.getMessage());
              vipDispatchService.getBizLogger()
                  .log(sob.getStockOutBatchId(), "过账", "失败！" + response.getMessage());
            } else {
              StockOutBatch soBatch = new StockOutBatch();
              soBatch.setStockOutBatchId(sob.getStockOutBatchId());
              soBatch.setPostStatus(PostStatus.POSTED);
              stockOutBatchService.modify(soBatch);
              vipDispatchService.getBizLogger()
                  .log(sob.getStockOutBatchId(), "过账", "成功");
            }
          }
          if(flag){
            VipDispatch vipOrder = new VipDispatch();
            vipOrder.setVipDispatchId(vipDispatch.getVipDispatchId());
            vipOrder.setPostStatus(PostStatus.POSTED);
            vipOrder.setVersion(vipDispatch.getVersion());
            vipDispatchService.modify(vipOrder);
            vipDispatchService.getBizLogger()
                .log(vipDispatch.getVipDispatchId(), "过账", "成功");
          }
        } else {
          StockOutBatch sOutBatch = new StockOutBatch();
          sOutBatch.setNoticeOrderCode(vipDispatch.getVipDispatchCode());
          List<StockOutBatch> sOutBatchs = stockOutBatchService.listExample(sOutBatch);
          if(Assert.isEmpty(sOutBatchs)){
            List<StockOutOrderCreateRequest.StockOutOrderDetail> detailList = new ArrayList<>(
                stockOutOrderDetailList.size());
            map.put("lastOutTime", DateTimeUtil.format(vipDispatch.getLastOutTime()));
            map.put("createTime", DateTimeUtil.format(vipDispatch.getCreatedTime()));
            request.setExtendProps(map);
            request.setWmsCode(vipDispatch.getOuterCode());
            for (VipDispatchDetail orderDetail : stockOutOrderDetailList) {
              StockOutOrderCreateRequest.StockOutOrderDetail detail = new StockOutOrderCreateRequest.StockOutOrderDetail();
              detail.setSkuCode(orderDetail.getSkuCode());
              detail.setWmsSkuId(orderDetail.getSkuName());
              detail.setProductCode(orderDetail.getProductCode());
              detail.setProductName(orderDetail.getProductName());
              detail.setQuantity(orderDetail.getOutQuantity());
              if (Assert.isNull(orderDetail.getVipPrice()) || orderDetail.getVipPrice() == 0) {
                vipDispatchService.getBizLogger()
                    .log(vipDispatch.getVipDispatchId(), "过账", "过账金额不能为0");
                throw new RuntimeException("过账金额不能为0");
              } else {
                detail.setSellingPrice(orderDetail.getVipPrice());
                detail.setSellingAmount(orderDetail.getVipAmount());
              }
              detailList.add(detail);
            }
            request.setDetails(detailList);
            response = stockOutOrderBridge.createOrder(request);
            if (!response.isSuccess()) {
              LOGGER
                  .error("唯品拣货单推送FMS过账失败！{},{}", vipDispatch.getVipDispatchCode(),
                      response.getMessage());
              vipDispatchService.getBizLogger()
                  .log(vipDispatch.getVipDispatchId(), "过账", "失败！" + response.getMessage());
            } else {
              VipDispatch vipOrder = new VipDispatch();
              vipOrder.setVipDispatchId(vipDispatch.getVipDispatchId());
              vipOrder.setPostStatus(PostStatus.POSTED);
              vipOrder.setVersion(vipDispatch.getVersion());
              vipDispatchService.modify(vipOrder);
              vipDispatchService.getBizLogger()
                  .log(vipDispatch.getVipDispatchId(), "过账", "成功");
            }
          }
        }
      }
    }
  }
}
