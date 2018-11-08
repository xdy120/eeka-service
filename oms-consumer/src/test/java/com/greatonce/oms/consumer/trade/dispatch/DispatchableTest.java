package com.greatonce.oms.consumer.trade.dispatch;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.base.SettingService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.trade.SalesOrderService;
import com.greatonce.oms.consumer.ConsumerTest;
import com.greatonce.oms.consumer.trade.dispatch.wrapper.StockDispatchStrategyWrapper;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.trade.SalesOrder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018-09-12
 */

public class DispatchableTest extends ConsumerTest {

  @Autowired
  private IdGenerator idGenerator;
  @Autowired
  private Dispatchable dispatchable;
  @Autowired
  private SalesOrderService salesOrderService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;
  @Autowired
  private SettingService settingService;

  @Test
  public void test() {
    SalesOrder salesOrder = salesOrderService.getDetailInfo(13907933797909504L);
    if (salesOrder == null) {
      throw new OmsException("配货失败，订单不存在！");
    }
    Store store = storeService.getByKey(salesOrder.getStoreId());
    DispatchContext context = new DispatchContext(idGenerator.next(), store, salesOrder);
    context.setDispatchStrategy(new StockDispatchStrategyWrapper(
        stockDispatchStrategyService.getByKey(store.getSetting().getStockStrategy())));
    context.setSalesOrderSetting(settingService.getSalesOrderSetting());
    dispatchable.dispatch(context);
  }
}