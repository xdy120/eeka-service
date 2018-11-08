package com.greatonce.oms.custom.eeka.qimen.custom;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.core.util.Assert;
import com.greatonce.oms.biz.base.WarehouseService;
import com.greatonce.oms.biz.product.ProductSkuService;
import com.greatonce.oms.biz.stock.StockService;
import com.greatonce.oms.custom.QimenCustomController;
import com.greatonce.oms.custom.eeka.qimen.EekaApiCondition;
import com.greatonce.oms.custom.eeka.qimen.custom.request.EekaSkuSellRatioSynchronizeRequest;
import com.greatonce.oms.custom.eeka.qimen.custom.response.EekaSkuSellRatioSynchronizeResponse;
import com.greatonce.oms.domain.base.Warehouse;
import com.greatonce.oms.domain.product.ProductSku;
import com.greatonce.oms.domain.stock.Stock;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ginta
 * @author Shenzhen Greatonce Co Ltd
 * @version 2018/4/21
 */
@RestController
@EekaApiCondition
public class EekaSkuSellRatioSynchronizeController extends QimenCustomController {

  static final Logger LOGGER = LoggerFactory
      .getLogger(EekaSkuSellRatioSynchronizeController.class);

  @Autowired
  StockService stockService;
  @Autowired
  WarehouseService warehouseService;
  @Autowired
  ProductSkuService productSkuService;
  @Resource
  IdGenerator apiIdGenerator;

  /**
   * 商品动销比同步
   */
  @PostMapping(params = "method=83dv4ure31.greatonce.oms.sku.sell.ratio.synchronization")
  public EekaSkuSellRatioSynchronizeResponse synchronize(HttpServletRequest servletRequest) {
    EekaSkuSellRatioSynchronizeRequest request = checkSign(servletRequest,
        EekaSkuSellRatioSynchronizeRequest.class);
    Warehouse warehouse = warehouseService.getEffectiveByCode(request.getWarehouseCode());
    if (Assert.isNull(warehouse)) {
      EekaSkuSellRatioSynchronizeResponse response = new EekaSkuSellRatioSynchronizeResponse(
          apiIdGenerator.next(), "仓库编号为空！");
      return response;
    }
    List<EekaSkuSellRatioSynchronizeRequest.SkuRatio> skuRatioList = request.getSkus();
    List<Stock> stockList = new ArrayList<>();
    if (!Assert.isEmpty(skuRatioList)) {
      for (EekaSkuSellRatioSynchronizeRequest.SkuRatio skuRatio : skuRatioList) {
        Stock stock = new Stock();
        stock.setWarehouseId(warehouse.getWarehouseId());
        ProductSku sku = productSkuService.getEffectiveByCode(skuRatio.getSkuCode());
        stock.setSkuId(sku.getSkuId());
        Stock stockQuery = stockService.getByExample(stock);
        Stock stockUpdate = new Stock();
        stockUpdate.setRatio(skuRatio.getRatio());
        stockUpdate.setStockId(stockQuery.getStockId());
        stockList.add(stockUpdate);
      }
//      stockService.batchModify(stockList);
    }
    EekaSkuSellRatioSynchronizeResponse response = new EekaSkuSellRatioSynchronizeResponse(
        apiIdGenerator.next());
    return response;
  }
}
