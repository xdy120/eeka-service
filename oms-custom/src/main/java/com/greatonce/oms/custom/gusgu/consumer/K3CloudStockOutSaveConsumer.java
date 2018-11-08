package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.stock.StockOutOrderDetailService;
import com.greatonce.oms.biz.stock.StockOutOrderService;
import com.greatonce.oms.biz.trade.DispatchOrderDeliveryService;
import com.greatonce.oms.biz.trade.DispatchOrderDetailService;
import com.greatonce.oms.biz.trade.DispatchOrderService;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.custom.gusgu.GusguUtil;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest.Fentity;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest.SalesStockOut;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockOutSaveRequest.SubHeadEntity;
import com.greatonce.oms.custom.kingdee.response.K3CloudStockOutSaveResponse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.stock.StockOutOrder;
import com.greatonce.oms.domain.stock.StockOutOrderDetail;
import com.greatonce.oms.domain.trade.DispatchOrder;
import com.greatonce.oms.domain.trade.DispatchOrderDelivery;
import com.greatonce.oms.domain.trade.DispatchOrderDetail;
import com.greatonce.oms.message.GeneralMessage;
import com.greatonce.oms.message.trade.DispatchOrderDeliveryMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@GusguConsumerCondition
@RabbitListener(queues = GusguConstants.QUEUE_DELIVERY_ORDER_PUSH, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class K3CloudStockOutSaveConsumer extends AbstractCustomConsumer {

  private static Logger LOGGER = LoggerFactory.getLogger(K3CloudStockOutSaveConsumer.class);
  @Value("${stock-out-customer-id:100}")
  private String stockOutCustomerId;
  @Resource
  private K3CloudClient k3CloudClient;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private DispatchOrderService dispatchOrderService;
  @Autowired
  private DispatchOrderDetailService dispatchOrderDetailService;
  @Autowired
  private DispatchOrderDeliveryService dispatchOrderDeliveryService;
  @Autowired
  private StockOutOrderService stockOutOrderService;
  @Autowired
  private StockOutOrderDetailService stockOutOrderDetailService;
  @Autowired
  private StoreService storeService;
  @Resource
  private GusguUtil gusguUtil;

  /**
   * b2c销售，配货单
   */
  @RabbitHandler
  public void process(DispatchOrderDeliveryMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      DispatchOrder dispatchOrder = dispatchOrderService.getByKey(message.getDispatchOrderId());
      if (!Assert.isNull(dispatchOrder)) {
        List<DispatchOrderDetail> dispatchOrderDetails = dispatchOrderDetailService
            .listByDispatchOrderId(message.getDispatchOrderId());
        dispatchOrder.setDetails(dispatchOrderDetails);
        sendData(dispatchOrder);
      } else {
        LOGGER.error("推送金蝶，数据库找不到相关的配货单。配货单id：{}", message.getDispatchOrderId());
      }
    });
  }

  /**
   * 调拨出库
   */
  @RabbitHandler
  public void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      StockOutOrder stockOutOrder = stockOutOrderService.getByKey(message.getDataId());
      if (!Assert.isNull(stockOutOrder)) {
        List<StockOutOrderDetail> details = stockOutOrderDetailService
            .listDetails(message.getDataId());
        stockOutOrder.setDetails(details);
        sendData(stockOutOrder);
      } else {
        LOGGER.error("推送金蝶，数据库找不到相关的出库单。出库单id：{}", message.getDataId());
      }
    });
  }

  private void sendData(StockOutOrder stockOutOrder) {
    K3CloudStockOutSaveRequest request = new K3CloudStockOutSaveRequest();
    SalesStockOut salesStockOut = new SalesStockOut();

    salesStockOut.setFStockOrgId(new FNumber("100"));
    salesStockOut.setFCustomerID(new FNumber(stockOutCustomerId));
    salesStockOut.setFBillTypeID(new FNumber("XSCKD01_SYS"));

    SubHeadEntity subHeadEntity = new SubHeadEntity();
    subHeadEntity.setFExchangeRate(16);
    salesStockOut.setSubHeadEntity(subHeadEntity);

    Warehouse warehouse = warehouseService.getByKey(stockOutOrder.getWarehouseId());
    if (!Assert.isEmpty(stockOutOrder.getDetails())) {
      List<Fentity> fentities = new ArrayList<>(stockOutOrder.getDetails().size());
      for (StockOutOrderDetail detail : stockOutOrder.getDetails()) {
        Fentity fentity = new Fentity();
        fentity.setFMATERIALID(new FNumber(detail.getSkuCode()));
        fentity.setFStockID(new FNumber(warehouse.getWarehouseCode()));
        fentity.setFTaxRate(1.000000);
        fentity.setFRealQty(detail.getOutQuantity());
        fentity.setFTaxPrice(detail.getPrice());
        fentities.add(fentity);
      }
      salesStockOut.setFentity(fentities);
    }
    request.setSalesStockOut(salesStockOut);

    K3CloudStockOutSaveResponse response = k3CloudClient.execute(request);
    if (Assert.isNull(response) || Assert.isNull(response.getResult())) {
      LOGGER.error("推送金蝶出库单的响应结果为空。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (!Assert.isTrue(response.getResult().getStatus().isSuccess())) {
      LOGGER.error("推送金蝶出库单失败。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("推送金蝶出库单成功。出库单id：{}", stockOutOrder.getStockOutOrderId());
    }
  }

  private void sendData(DispatchOrder dispatchOrder) {
    String orgId = gusguUtil.convertToOrgId(dispatchOrder.getStoreId());
    if (Assert.isNull(orgId)) {
      return;
    }
    Store store = storeService.getByKey(dispatchOrder.getStoreId());
    List<DispatchOrderDelivery> dispatchOrderDeliveries = dispatchOrderDeliveryService
        .listByDispatchOrderId(dispatchOrder.getDispatchOrderId());

    K3CloudStockOutSaveRequest request = new K3CloudStockOutSaveRequest();
    SalesStockOut salesStockOut = new SalesStockOut();
    salesStockOut.setFStockOrgId(new FNumber(orgId));
    salesStockOut.setFCustomerID(new FNumber(String.valueOf(dispatchOrder.getMemberId())));
    salesStockOut.setFBillTypeID(new FNumber("XSCKD01_SYS"));
    if (!Assert.isEmpty(dispatchOrderDeliveries)) {
      salesStockOut.setFCarriageNO(dispatchOrderDeliveries.get(0).getExpressNo());
    }
    if (!Assert.isNull(store)) {
      salesStockOut.setF_PAEZ_Assistant(new FNumber(store.getStoreCode()));
    }

    SubHeadEntity subHeadEntity = new SubHeadEntity();
    subHeadEntity.setFExchangeRate(16);
    salesStockOut.setSubHeadEntity(subHeadEntity);

    if (!Assert.isEmpty(dispatchOrder.getDetails())) {
      Set<String> tradeIds = new HashSet<>();
      List<Fentity> fentities = new ArrayList<>(dispatchOrder.getDetails().size());
      for (DispatchOrderDetail detail : dispatchOrder.getDetails()) {
        tradeIds.add(detail.getTradeId());
        Fentity fentity = new Fentity();
        VirtualWarehouse virtualWarehouse = virtualWarehouseService
            .getByKey(detail.getVirtualWarehouseId());
        if (!Assert.isNull(virtualWarehouse)) {
          Warehouse warehouse = warehouseService.getByKey(virtualWarehouse.getWarehouseId());
          if (!Assert.isNull(warehouse)) {
            fentity.setFStockID(new FNumber(warehouse.getWarehouseCode()));
          }
        }
        fentity.setFMATERIALID(new FNumber(detail.getSkuCode()));
        fentity.setFTaxRate(1.000000);
        fentity.setFRealQty(detail.getOutQuantity());
        fentity.setFTaxPrice(detail.getActualAmount());
        fentities.add(fentity);
      }
      StringJoiner sj = new StringJoiner(",");
      for (String tradeId : tradeIds) {
        sj.add(tradeId);
      }
      salesStockOut.setF_PAEZ_Text(sj.toString());
      salesStockOut.setFentity(fentities);
    }
    request.setSalesStockOut(salesStockOut);

    K3CloudStockOutSaveResponse response = k3CloudClient.execute(request);
    if (Assert.isNull(response) || Assert.isNull(response.getResult())) {
      LOGGER.error("推送金蝶销售出库的响应结果为空。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (!Assert.isTrue(response.getResult().getStatus().isSuccess())) {
      LOGGER.error("推送金蝶销售出库失败。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("推送金蝶销售出库成功。配货单id：{}", dispatchOrder.getDispatchOrderId());
    }
  }

}