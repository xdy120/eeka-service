package com.greatonce.oms.custom.gusgu.consumer;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.JsonUtil;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.custom.AbstractCustomConsumer;
import com.greatonce.oms.custom.kingdee.K3CloudClient;
import com.greatonce.oms.custom.kingdee.entity.FNumber;
import com.greatonce.oms.custom.kingdee.request.K3CloudMaterialSaveRequest;
import com.greatonce.oms.custom.kingdee.request.K3CloudMaterialSaveRequest.Material;
import com.greatonce.oms.custom.kingdee.request.K3CloudMaterialSaveRequest.SubHeadEntity;
import com.greatonce.oms.custom.kingdee.response.K3CloudMaterialSaveResponse;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.GeneralMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
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
@RabbitListener(queues = GusguConstants.QUEUE_PRODUCT_SKU_PUSH, containerFactory = AbstractCustomConsumer.RABBITMQ_CONTAINER_FACTORY)
public class K3CloudMaterialSaveConsumer extends AbstractCustomConsumer {

  private static Logger LOGGER = LoggerFactory.getLogger(K3CloudMaterialSaveConsumer.class);
  @Resource
  private K3CloudClient k3CloudClient;
  @Autowired
  private ProductSkuService productSkuService;

  @RabbitHandler
  public void process(GeneralMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      ProductSku productSku = productSkuService.getByKey(message.getDataId());
      if (Assert.isNull(productSku)) {
        LOGGER.error("推送金蝶商品资料失败！原因：数据库查询不到相关的商品规格。id：{}", message.getDataId());
        return;
      }
      sendData(productSku);
    });
  }

  private void sendData(ProductSku productSku) {
    K3CloudMaterialSaveRequest request = new K3CloudMaterialSaveRequest();
    Material material = new Material();
    material.setFCreateOrgId(new FNumber("100"));
    material.setFUseOrgId(new FNumber("100"));
    material.setFNumber(productSku.getSkuCode());
    material.setFName(productSku.getSkuName());

    SubHeadEntity subHeadEntity = new SubHeadEntity();
    subHeadEntity.setFErpClsID("默认");
    subHeadEntity.setFCategoryID(new FNumber("CHLB05_SYS"));
    subHeadEntity.setFBaseUnitId(new FNumber("Pcs"));
    subHeadEntity.setFBARCODE("default");
    subHeadEntity.setFTaxRateId(new FNumber("SL04_SYS"));
    material.setSubHeadEntity(subHeadEntity);
    request.setMaterial(material);

    K3CloudMaterialSaveResponse response = k3CloudClient.execute(request);
    if (Assert.isNull(response) || Assert.isNull(response.getResult())) {
      LOGGER.error("推送金蝶物料保存的响应结果为空。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (!Assert.isTrue(response.getResult().getStatus().isSuccess())) {
      LOGGER.error("推送金蝶物料保存失败。请求参数: {}; 响应: {}", request.parameters(),
          JsonUtil.toJson(response));
      return;
    }
    if (LOGGER.isDebugEnabled()) {
      LOGGER.debug("推送金蝶物料保存成功。商品规格id：{}", productSku.getSkuId());
    }
  }

}