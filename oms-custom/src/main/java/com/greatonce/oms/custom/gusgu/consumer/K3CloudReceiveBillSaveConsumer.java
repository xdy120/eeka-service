package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.finance.AlipayRecordService;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.custom.gusgu.GusguUtil;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudReceiveBillSaveRequest;
import com.greatonce.oms.custom.kingdee.request.K3CloudReceiveBillSaveRequest.FRECEIVEBILLENTRY;
import com.greatonce.oms.custom.kingdee.request.K3CloudReceiveBillSaveRequest.ReceiveBill;
import com.greatonce.oms.custom.kingdee.response.K3CloudReceiveBillSaveResponse;
import com.greatonce.oms.domain.finance.AlipayRecord;
import com.greatonce.oms.message.GeneralMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.ArrayList;
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
@RabbitListener(queues = GusguConstants.QUEUE_ALIPAY_RECORD_PUSH, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class K3CloudReceiveBillSaveConsumer extends AbstractCustomConsumer {

  private static Logger LOGGER = LoggerFactory.getLogger(K3CloudReceiveBillSaveConsumer.class);
  @Value("${oms.consumer.custom.bill-customer-id}")
  private String billCustomerId;
  @Resource
  private K3CloudClient k3CloudClient;
  @Resource
  private GusguUtil gusguUtil;
  @Autowired
  private AlipayRecordService alipayRecordService;

  @RabbitHandler
  public void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      AlipayRecord alipayRecord = alipayRecordService.getByKey(message.getDataId());
      if (Assert.isNull(alipayRecord)) {
        LOGGER.error("推送金蝶收款单失败！原因：数据库查询不到相关的收款单。alipayRecordId：{}", message.getDataId());
        return;
      }
      sendData(alipayRecord);
    });
  }

  private void sendData(AlipayRecord alipayRecord) {
    if (Assert.isNull(alipayRecord.getInAmount())) {
      // 支出数据不推
      return;
    }
    String orgId = gusguUtil.convertToOrgId(alipayRecord.getStoreId());
    if (Assert.isNull(orgId)) {
      return;
    }

    K3CloudReceiveBillSaveRequest request = new K3CloudReceiveBillSaveRequest();
    ReceiveBill receiveBill = new ReceiveBill();
    receiveBill.setFBillTypeID(new FNumber("SKDLX01_SYS"));
    receiveBill.setFSETTLECUR(new FNumber("PRE001"));
    receiveBill.setFDATE(alipayRecord.getAlipayCreatedTime());
    receiveBill.setFCONTACTUNITTYPE("BD_Customer");
    receiveBill.setFPAYUNITTYPE("BD_Customer");
    receiveBill.setFCONTACTUNIT(new FNumber(billCustomerId));
    receiveBill.setFPAYUNIT(new FNumber(billCustomerId));
    receiveBill.setFCURRENCYID(new FNumber("PRE001"));
    receiveBill.setFPAYORGID(new FNumber(orgId));

    FRECEIVEBILLENTRY freceivebillentry = new FRECEIVEBILLENTRY();
    freceivebillentry.setFSETTLETYPEID(new FNumber("JSFS32_SYS"));
    freceivebillentry.setFPURPOSEID(new FNumber("SFKYT01_SYS"));
    freceivebillentry.setFRECTOTALAMOUNTFOR(alipayRecord.getInAmount());
    freceivebillentry.setF_PAEZ_Text1(alipayRecord.getTradeId());
    freceivebillentry.setF_PAEZ_Text2(alipayRecord.getOptUserId());
    freceivebillentry.setFACCOUNTID(new FNumber(alipayRecord.getSelfUserId()));
    ArrayList<FRECEIVEBILLENTRY> list = new ArrayList<>();
    list.add(freceivebillentry);
    receiveBill.setFRECEIVEBILLENTRY(list);
    request.setReceiveBill(receiveBill);

    K3CloudReceiveBillSaveResponse response = k3CloudClient.execute(request);
    if (Assert.isNull(response) || Assert.isNull(response.getResult())) {
      LOGGER.error("推送金蝶收款单保存的响应结果为空。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (!Assert.isTrue(response.getResult().getStatus().isSuccess())) {
      LOGGER.error("推送金蝶收款单保存失败。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("推送金蝶收款单保存成功。支付宝账单id：{}", alipayRecord.getAlipayRecordId());
    }
  }

}