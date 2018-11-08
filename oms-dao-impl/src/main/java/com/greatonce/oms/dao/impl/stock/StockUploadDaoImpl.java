package com.greatonce.oms.dao.impl.stock;

import com.greatonce.oms.bo.stock.StockQuantityBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockUploadDao;
import com.greatonce.oms.domain.base.StockUploadWarehouse;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.query.stock.StockQuery;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class StockUploadDaoImpl extends AbstractOmsDao<Stock, StockQuery> implements
    StockUploadDao {

  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockUploadMapper";
  }

  @Override
  public List<StockQuantityBO> listQuantity(Long skuId, Collection<Long> warehouseIds) {
    Map<String, Object> map = new HashMap<>(2);
    map.put("skuId", skuId);
    map.put("warehouseIds", warehouseIds);
    return getSqlSessionDecorator().selectList(getStatement("listQuantity"), map);
  }

  @Override
  public BigDecimal calcQuantity(ProductMallMapping productMallMapping,
      Map<Long, StockUploadWarehouse> uploadWarehouseMap) {
    //根据 skuid 和  仓库的id  查询skuid 在各仓库之间的库存量
    List<StockQuantityBO> list = listQuantity(productMallMapping.getSkuId(),
        uploadWarehouseMap.keySet());
    double total = 0;

    for (StockQuantityBO stockQuantityBO : list) {
      //获得实体仓对应的配置
      StockUploadWarehouse warehouse = uploadWarehouseMap
          .get(stockQuantityBO.getVirtualWarehouseId());
      int quantity = stockQuantityBO.getQuantity();
      //  LockedQuantity 订单已生效 待发货的商品
      if (stockQuantityBO.getLockedQuantity() != null) {
        quantity = quantity - stockQuantityBO.getLockedQuantity();
      }
      if (quantity < 0) {
        total += quantity;
      } else {
        total += quantity * warehouse.getRatio() / 100.00;
      }
    }
    return new BigDecimal(total);
  }

  @Override
  public int calcPresellQuantity(ProductMallMapping productMallMapping) {
    Map<String, Object> map = new HashMap<>(2);
    map.put("presellId", productMallMapping.getMarketingId());
    map.put("skuId", productMallMapping.getSkuId());
    return getSqlSessionDecorator().selectOne(getStatement("selectPresellQuantity"), map);
  }

  @Override
  public BigDecimal calcCanSaleTotal(ProductMallMapping productMallMapping,
      Collection<Long> warehouseIds) {
    Map<String, Object> map = new HashMap<>(2);
    map.put("skuId", productMallMapping.getSkuId());
    map.put("warehouseIds", warehouseIds);
    Integer total = getSqlSessionDecorator().selectOne(getStatement("getCanSaleTotal"), map);
    return BigDecimal.valueOf(total);
  }
}
