package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.trade.ReturnOrderService;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.custom.gusgu.GusguUtil;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockReturnSaveRequest;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockReturnSaveRequest.Fentity;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockReturnSaveRequest.SalesStockReturn;
import com.greatonce.oms.custom.kingdee.request.K3CloudStockReturnSaveRequest.SubHeadEntity;
import com.greatonce.oms.custom.kingdee.response.K3CloudStockReturnSaveResponse;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.trade.ReturnOrder;
import com.greatonce.oms.domain.trade.ReturnOrderDetail;
import com.greatonce.oms.message.GeneralMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

@Component
@GusguConsumerCondition
@RabbitListener(queues = GusguConstants.QUEUE_RETURN_ORDER_PUSH, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class K3CloudStockReturnSaveConsumer extends AbstractCustomConsumer {

  private static Logger LOGGER = LoggerFactory.getLogger(K3CloudStockReturnSaveConsumer.class);
  @Resource
  private K3CloudClient k3CloudClient;
  @Autowired
  private WarehouseService warehouseService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;
  @Autowired
  private ReturnOrderService returnOrderService;
  @Resource
  private GusguUtil gusguUtil;

  @RabbitHandler
  public void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      ReturnOrder returnOrder = returnOrderService.getOrderAndDetail(message.getDataId());
      if (!Assert.isNull(returnOrder)) {
        sendData(returnOrder);
      } else {
        LOGGER.error("推送金蝶，数据库找不到相关的配货单。退换货单id：{}", message.getDataId());
      }
    });
  }

  private void sendData(ReturnOrder returnOrder) {
    String orgId = gusguUtil.convertToOrgId(returnOrder.getStoreId());
    if (Assert.isNull(orgId)) {
      return;
    }

    K3CloudStockReturnSaveRequest request = new K3CloudStockReturnSaveRequest();
    SalesStockReturn salesStockReturn = new SalesStockReturn();
    salesStockReturn.setFRetcustId(new FNumber(String.valueOf(returnOrder.getMemberId())));
    salesStockReturn.setFBillTypeID(new FNumber("XSTHD01_SYS"));
    salesStockReturn.setFSettleCurrId(new FNumber("PRE001"));
    salesStockReturn.setFStockOrgId(new FNumber(orgId));

    SubHeadEntity subHeadEntity = new SubHeadEntity();
    subHeadEntity.setFExchangeRate(16);
    salesStockReturn.setSubHeadEntity(subHeadEntity);

    Warehouse warehouse = null;
    VirtualWarehouse virtualWarehouse = virtualWarehouseService
        .getByKey(returnOrder.getInVirtualWarehouseId());
    if (!Assert.isNull(virtualWarehouse)) {
      warehouse = warehouseService.getByKey(virtualWarehouse.getWarehouseId());
    }
    if (!Assert.isEmpty(returnOrder.getDetails())) {
      List<Fentity> fentities = new ArrayList<>();
      for (ReturnOrderDetail detail : returnOrder.getDetails()) {
        Fentity fentity = new Fentity();
        fentity.setFMaterialID(new FNumber(detail.getSkuCode()));
        fentity.setFTaxRate(1.000000);
        fentity.setFTaxPrice(detail.getRefundableAmount());
        fentity.setFRealQty(detail.getQuantity());
        fentity.setFReturnType(new FNumber("THLX01_SYS"));
        fentity.setFDeliveryDate(returnOrder.getAuditedTime());
        if (!Assert.isNull(warehouse)) {
          fentity.setFStockID(new FNumber(warehouse.getWarehouseCode()));
        }
        fentities.add(fentity);
      }
      salesStockReturn.setFentity(fentities);
    }
    request.setSalesStockReturn(salesStockReturn);

    K3CloudStockReturnSaveResponse response = k3CloudClient.execute(request);
    if (Assert.isNull(response) || Assert.isNull(response.getResult())) {
      LOGGER.error("推送金蝶销售退货的响应结果为空。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (!Assert.isTrue(response.getResult().getStatus().isSuccess())) {
      LOGGER.error("推送金蝶销售退货失败。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("推送金蝶销售退货成功。退换货单id：{}", returnOrder.getReturnOrderId());
    }
  }

}