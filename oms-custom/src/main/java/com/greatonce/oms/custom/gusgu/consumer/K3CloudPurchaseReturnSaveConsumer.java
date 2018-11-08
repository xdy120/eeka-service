package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderDetailService;
import com.greatonce.oms.biz.purchase.PurchaseReturnOrderService;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudPurchaseReturnSaveRequest;
import com.greatonce.oms.custom.kingdee.response.K3CloudPurchaseReturnSaveResponse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrder;
import com.greatonce.oms.domain.purchase.PurchaseReturnOrderDetail;
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
@RabbitListener(queues = GusguConstants.QUEUE_PURCHASE_RETURN_OUT_NOTICE, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class K3CloudPurchaseReturnSaveConsumer extends AbstractCustomConsumer {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(K3CloudPurchaseReturnSaveConsumer.class);

  @Autowired
  private K3CloudClient k3CloudClient;
  @Autowired
  private PurchaseReturnOrderService purchaseReturnOrderService;
  @Autowired
  private PurchaseReturnOrderDetailService purchaseReturnOrderDetailService;
  @Autowired
  private WarehouseService warehouseService;

  @RabbitHandler
  public void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag)
      throws IOException {
    run(message, channel, tag, () -> {
      PurchaseReturnOrder purchaseReturnOrder = purchaseReturnOrderService.getByKey(message.getDataId());
      if (!Assert.isNull(purchaseReturnOrder)) {
        List<PurchaseReturnOrderDetail> details = purchaseReturnOrderDetailService
            .listDetails(purchaseReturnOrder.getPurchaseReturnOrderId());
        Warehouse warehouse = warehouseService.getByKey(purchaseReturnOrder.getWarehouseId());

        K3CloudPurchaseReturnSaveRequest request = new K3CloudPurchaseReturnSaveRequest();
        K3CloudPurchaseReturnSaveRequest.PurchaseReturnOut purchaseReturnOut = new K3CloudPurchaseReturnSaveRequest.PurchaseReturnOut();
        purchaseReturnOut.setFPurchaseOrgId(new FNumber("100"));
        purchaseReturnOut.setFStockOrgId(new FNumber("100"));
        purchaseReturnOut.setFSupplierId(new FNumber(purchaseReturnOrder.getSupplierCode()));
        purchaseReturnOut.setFBillTypeID(new FNumber("TLD01_SYS"));
        purchaseReturnOut.setSubHeadEntity(new K3CloudPurchaseReturnSaveRequest.SubHeadEntity(1));

        List<K3CloudPurchaseReturnSaveRequest.FPURMRBENTRY> list = new ArrayList<>();
        if (!Assert.isEmpty(details)) {
          for (PurchaseReturnOrderDetail detail : details) {
            K3CloudPurchaseReturnSaveRequest.FPURMRBENTRY fpurmrbentry = new K3CloudPurchaseReturnSaveRequest.FPURMRBENTRY();
            fpurmrbentry.setFMATERIALID(new FNumber(detail.getSkuCode()));
            fpurmrbentry.setFStockID(new FNumber(warehouse.getWarehouseCode()));
            fpurmrbentry.setFTaxRate(16);
            fpurmrbentry.setFTaxPrice(detail.getPurchaseReturnPrice());
            if (detail.getOutQuantity() > 0) {
              fpurmrbentry.setFRMREALQTY(detail.getOutQuantity());
            } else {
              continue;
            }
            list.add(fpurmrbentry);
          }
        }
        if (!Assert.isEmpty(list)){
          purchaseReturnOut.setFPURMRBENTRY(list);
          request.setPurchaseReturnOut(purchaseReturnOut);
          K3CloudPurchaseReturnSaveResponse response = k3CloudClient.execute(request);
          if (response.getResult().getStatus().isSuccess()) {
            LOGGER.info("采购退料单推送金蝶成功,采购退货单:{}", purchaseReturnOrder.getPurchaseReturnOrderCode());
          } else {
            LOGGER.info("采购退料单推送金蝶失败,采购退货单:{},错误信息:{}", purchaseReturnOrder.getPurchaseReturnOrderCode(),
                JsonUtil.toJson(response.getResult().getStatus().getErrors()));
          }
        } else {
          LOGGER.info("采购退货的推送明细为空,采购退货单:{}", purchaseReturnOrder.getPurchaseReturnOrderCode());
        }
      }else {
        LOGGER.info("采购退料单推送金蝶失败,采购退货单:{} 不存在", message.getDataId());
      }
    });
  }
}
