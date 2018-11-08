package com.greatonce.oms.consumer.trade.translator.order;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.MqProducer;
import com.greatonce.oms.biz.mall.MallSalesOrderService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.consumer.ConsumerTest;
import com.greatonce.oms.domain.enums.mall.MallSalesOrderStatus;
import com.greatonce.oms.domain.mall.MallSalesOrder;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.message.trade.MallSalesOrderDownloadMessage;
import com.greatonce.oms.query.mall.MallSalesOrderQuery;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-07-15
 */
public class OrderTranslatorTest extends ConsumerTest {

  @Autowired
  OrderTranslator orderTranslator;
  @Autowired
  MallSalesOrderService mallSalesOrderService;
  @Resource
  IdGenerator translatorIdGenerator;
  @Autowired
  ProductSkuService productSkuService;
  @Autowired
  MqProducer mqProducer;

  @Test
  public void test() {
    MallSalesOrderQuery query = new MallSalesOrderQuery();
    query.setMallSalesOrderStatus(MallSalesOrderStatus.WAIT_BUYER_CONFIRM_GOODS);
    List<MallSalesOrder> mallSalesOrders = mallSalesOrderService.list(query);
    for (MallSalesOrder mallSalesOrder : mallSalesOrders)
      mqProducer.send(new MallSalesOrderDownloadMessage(mallSalesOrder.getMallSalesOrderId(),
          mallSalesOrder.getStoreId(), mallSalesOrder.getTradeId()));
  }

  @Test
  public void test2() {
    final ProductSku sku = productSkuService.getEffectiveByCode("ABCD");
    System.out.println(sku);
  }
}