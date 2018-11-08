package com.greatonce.oms.web.controller.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.StockDispatchStrategyService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.bo.stock.StockQueryBO;
import com.greatonce.oms.bo.stock.StockQueryRatioBO;
import com.greatonce.oms.bo.stock.StockSumBO;
import com.greatonce.oms.domain.OmsException;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.domain.base.StockDispatchWarehouse;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.query.stock.StockQuery;
import com.greatonce.oms.search.HttpElasticsearchTemplate;
import com.greatonce.oms.search.entity.AdvanceQuery;
import com.greatonce.oms.search.entity.AdvanceQuery.Field;
import com.greatonce.oms.search.entity.BoolType;
import com.greatonce.oms.search.entity.FieldType;
import com.greatonce.oms.search.entity.Operator;
import com.greatonce.oms.web.controller.ControllerUtil;
import com.greatonce.oms.web.controller.PageListController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/30.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/stock")
public class StockController implements PageListController<Stock, StockQuery> {

  private Logger LOGGER = LoggerFactory.getLogger(StockController.class);
  @Resource
  private StockService stockService;
  @Autowired
  private StockDispatchStrategyService stockDispatchStrategyService;
  @Autowired
  private ControllerUtil controllerUtil;
  @Autowired
  private HttpElasticsearchTemplate httpElasticsearchTemplate;

  @Override
  public BizService<Stock, StockQuery> getBizService() {
    return stockService;
  }

  @GetMapping(path = "/{stockDispatchStrategyId}/sku/{skuId}")
  public List<StockSumBO> listSkuStockInfo(
      @PathVariable("stockDispatchStrategyId") Long stockDispatchStrategyId,
      @PathVariable("skuId") Long skuId) {
    StockDispatchStrategy strategy = stockDispatchStrategyService.getByKey(stockDispatchStrategyId);
    final List<Long> virtualWarehouseIds = strategy.getRule().getWarehouses().stream()
        .map(StockDispatchWarehouse::getVirtualWarehouseId).collect(Collectors.toList());
    return stockService.listSkuStockInfo(skuId, virtualWarehouseIds);
  }

  @GetMapping(path = "query")
  public PageList<StockQueryBO> stockQuery(StockQuery stockQuery, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    controllerUtil.addUserVirtualWarehouseIds(stockQuery, StockQuery::getVirtualWarehouseIds,
        stockQuery::setVirtualWarehouseIds);
    if (!Assert.isEmpty(stockQuery.getProductName())) {
      Field field = new Field();
      field.setField("product_name");
      field.setValue(stockQuery.getProductName());
      field.setType(FieldType.TEXT);
      field.setOperator(Operator.FUZZY);
      field.setBoolType(BoolType.MUST);
      PageList<Map<String, Object>> pageList = null;
      try {
        pageList = httpElasticsearchTemplate
            .searchSource("product_sku", "doc", 1, 10000, new AdvanceQuery(Arrays.asList(field)),
                new String[]{"sku_id"}, new String[]{});
      } catch (IOException e) {
        LOGGER.error("es商品查询出错，堆栈信息：{}", e);
        throw new OmsException("查询es索引出错，请联系系统管理员");
      }
      if (!Assert.isEmpty(pageList.getData())) {
        Set<Long> set = pageList.getData().stream()
            .map(x -> (Long) x.get("sku_id"))
            .collect(Collectors.toSet());
        stockQuery.setSkuIds(new ArrayList<>(set));
        stockQuery.setProductName(null);
      } else {
        return new PageList<>(pageSize, page, 0, null);
      }
    }
    return stockService.stockQuery(stockQuery, page, pageSize);
  }

  @GetMapping(path = "trace")
  public PageList<StockQueryBO> stockTrace(StockQuery stockQuery, @RequestParam("page") int page,
      @RequestParam("pageSize") int pageSize) {
    return stockService.stockQuery(stockQuery, page, pageSize);
  }

  @GetMapping(path = "/ratioQuery/{type}/{salesOrderId}")
  public StockQueryRatioBO ratioQuery(@PathVariable("salesOrderId") Long salesOrderId,
      @PathVariable("type") Long type) {
    return stockService.ratioQuery(salesOrderId, type);
  }

  @GetMapping(path = "/exportStock/{fileName}")
  public void exportStock(StockQuery stockQuery, @PathVariable("fileName") String fileName) {
    stockService.exportStock(fileName, stockQuery);
  }

}
