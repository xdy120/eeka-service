package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.base.StoreService;
import com.greatonce.oms.biz.base.VirtualWarehouseService;
import com.greatonce.oms.domain.base.ExpressStrategyRule.StrategyExpress;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.domain.base.VirtualWarehouse;
import com.greatonce.oms.query.base.StockDispatchStrategyQuery;
import com.greatonce.oms.web.controller.FullListController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author buer
 * @version 2018-01-31 19:09
 */
@RestController
@RequestMapping("/base/setting/stock/dispatch")
@CrossOrigin
public class StockDispatchStrategyController implements
    FullListController<StockDispatchStrategy, StockDispatchStrategyQuery> {

  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;
  @Autowired
  private StoreService storeService;
  @Autowired
  private VirtualWarehouseService virtualWarehouseService;

  @Override
  public BizService<StockDispatchStrategy, StockDispatchStrategyQuery> getBizService() {
    return stockDispatchStrategyService;
  }

  /**
   * 返回该店铺下库存策略的仓库
   */
  @GetMapping("/virtualWarehouses/{storeId}")
  public List<VirtualWarehouse> listStrategyWarehouse(
      @PathVariable("storeId") Long storeId) {
    Store store = storeService.getByKey(storeId);
    StockDispatchStrategy stockDispatchStrategy = stockDispatchStrategyService
        .getByKey(store.getSetting().getStockStrategy());
    List<StockDispatchWarehouse> warehouses = stockDispatchStrategy.getRule().getWarehouses();
    List<VirtualWarehouse> virtualWarehouseList = new ArrayList<>();
    warehouses.stream().map(StockDispatchWarehouse::getVirtualWarehouseId)
        .forEach(x -> virtualWarehouseList.add(virtualWarehouseService.getByKey(x)));
    return virtualWarehouseList;
  }

  @GetMapping("/expresses/{storeId}")
  public List<StrategyExpress> listExpress(@PathVariable("storeId") Long storeId) {
    final Store store = storeService.getByKey(storeId);
    return stockDispatchStrategyService.listExpresses(store.getSetting().getStockStrategy());
  }
}
