package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.purchase.PurchaseOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseOrderService;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudPurchaseInSaveRequest;
import com.greatonce.oms.custom.kingdee.response.K3CloudPurchaseInSaveResponse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.purchase.PurchaseOrder;
import com.greatonce.oms.domain.purchase.PurchaseOrderDetail;
import com.greatonce.oms.message.GeneralMessage;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Shenzhen cca
 * @version 2018/9/18
 */
@Component
@GusguConsumerCondition
@RabbitListener(queues = GusguConstants.QUEUE_PURCHASE_IN_NOTICE, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class K3CloudPurchaseInSaveConsumer extends AbstractCustomConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(K3CloudPurchaseInSaveConsumer.class);

  @Autowired
  private K3CloudClient k3CloudClient;
  @Autowired
  private PurchaseOrderService purchaseOrderService;
  @Autowired
  private PurchaseOrderDetailService purchaseOrderDetailService;
  @Autowired
  private WarehouseService warehouseService;

  @RabbitHandler
  void process(GeneralMessage message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    run(message, channel, tag, () -> {

      PurchaseOrder purchaseOrder = purchaseOrderService.getByKey(message.getDataId());
      if (!Assert.isNull(purchaseOrder)) {
        PurchaseOrderDetail eg = new PurchaseOrderDetail();
        eg.setPurchaseOrderId(purchaseOrder.getPurchaseOrderId());
        List<PurchaseOrderDetail> orderDetails = purchaseOrderDetailService.listExample(eg);
        Warehouse warehouse = warehouseService.getByKey(purchaseOrder.getWarehouseId());

        K3CloudPurchaseInSaveRequest request = new K3CloudPurchaseInSaveRequest();
        K3CloudPurchaseInSaveRequest.PurchaseIn purchaseIn = new K3CloudPurchaseInSaveRequest.PurchaseIn();

        purchaseIn.setFPurchaseOrgId(new FNumber("100"));
        purchaseIn.setFStockOrgId(new FNumber("100"));
        purchaseIn.setFSupplierId(new FNumber(purchaseOrder.getSupplierCode()));
        purchaseIn.setFBillTypeID(new FNumber("RKD01_SYS"));
        purchaseIn.setSubHeadEntity(new K3CloudPurchaseInSaveRequest.SubHeadEntity(1));
        List<K3CloudPurchaseInSaveRequest.FInStockEntry> list = new ArrayList<>();
        if (!Assert.isEmpty(orderDetails)) {
          for (PurchaseOrderDetail detail : orderDetails) {
            Integer quantity = 0;
            if (!Assert.isNull(detail.getInQuantity())) {
              quantity += detail.getInQuantity();
            }
            if (!Assert.isNull(detail.getInSubstandardQuantity())) {
              quantity += detail.getInSubstandardQuantity();
            }
            if (quantity > 0) {
              K3CloudPurchaseInSaveRequest.FInStockEntry fInStockEntry = new K3CloudPurchaseInSaveRequest.FInStockEntry();
              fInStockEntry.setFMATERIALID(new FNumber(detail.getSkuCode()));
              fInStockEntry.setFStockID(new FNumber(warehouse.getWarehouseCode()));
              fInStockEntry.setFTaxRate(16);
              fInStockEntry.setFTaxPrice(detail.getActualPrice());
              fInStockEntry.setFRealQty(quantity);
              list.add(fInStockEntry);
            }
          }
        }
        if (!Assert.isEmpty(list)){
          purchaseIn.setFInStockEntry(list);
          request.setPurchaseIn(purchaseIn);
          K3CloudPurchaseInSaveResponse response = k3CloudClient.execute(request);
          if (response.getResult().getStatus().isSuccess()) {
            LOGGER.info("采购入库单推送金蝶成功,采购单:{}", purchaseOrder.getPurchaseOrderCode());
          } else {
            LOGGER.info("采购入库单推送金蝶失败,采购单:{},错误信息:{}", purchaseOrder.getPurchaseOrderCode(),
                JsonUtil.toJson(response.getResult().getStatus().getErrors()));
          }
        }else {
          LOGGER.info("采购入库单推送明细为空,采购单:{}", purchaseOrder.getPurchaseOrderCode());
        }
      }else {
        LOGGER.info("采购入库单:{} 不存在", message.getDataId());
      }
    });
  }
}
