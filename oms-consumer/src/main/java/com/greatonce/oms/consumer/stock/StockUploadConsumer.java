package com.greatonce.oms.consumer.stock;

import com.greatonce.core.util.Assert;
import com.greatonce.core.util.CollectionUtil;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StockUploadStrategyService;
import com.greatonce.oms.biz.impl.stock.StockMqConfiguration;
import com.greatonce.oms.biz.product.ProductMallMappingService;
import com.greatonce.oms.biz.stock.StockUploadService;
import com.greatonce.oms.consumer.AbstractConsumer;
import com.greatonce.oms.consumer.Constants;
import com.greatonce.oms.domain.base.StockUploadStrategy;
import com.greatonce.oms.domain.base.setting.StockSetting;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.message.stock.StockChangedMessage;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Header;

/**
 * 库存上传消息队列.
 *
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/3/10
 */
@RabbitListener(queues = StockMqConfiguration.QUEUE_STOCK_UPLOAD, containerFactory = Constants.RABBITMQ_CONTAINER_FACTORY)
public class StockUploadConsumer extends AbstractConsumer {

  private static final Logger LOGGER = LoggerFactory.getLogger(StockUploadConsumer.class);

  @Value("${oms.consumer.stock-upload.ignore:false}")
  private boolean ignore;

  @Autowired
  private StockUploadService stockUploadService;
  @Autowired
  private ProductMallMappingService productMallMappingService;
  @Autowired
  private StockUploadStrategyService settingStockUploadService;
  @Autowired
  private SettingService settingService;

  @RabbitHandler
  void process(StockChangedMessage message, Channel channel,
      @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
    run(message, channel, tag, () -> {
      if (!ignore) {
        if (Assert.isNull(message.getSkuId())) {
          LOGGER.warn("库存异动没有SKU！{},", message);
          return;
        }

        ProductMallMapping mapping = new ProductMallMapping();
        mapping.setSkuId(message.getSkuId());
        mapping.setStoreId(message.getStoreId());
        final StockSetting stockSetting = settingService.getInventorySetting();
        List<ProductMallMapping> mappings = productMallMappingService.listExample(mapping);
        if (Assert.isEmpty(mappings)) {
          LOGGER.warn("库存异动未找到铺货关系 ！{},", message);
          return;
        }
        final Map<Long, List<ProductMallMapping>> listMap = CollectionUtil
            .listToMapList(mappings, ProductMallMapping::getStoreId);
        listMap.forEach((k, v) -> {
          List<StockUploadStrategy> strategies = settingStockUploadService
              .listByStoreId(k);
          for (StockUploadStrategy strategy : strategies) {
            stockUploadService
                .autoUpload(strategy, stockSetting, v, message.getBatchId(), message.getOperator(),
                    message.getReason());
          }
        });
      }
    });
  }
}
