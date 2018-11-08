package com.greatonce.oms.dao.stock;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.StockUploadWarehouse;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.bo.stock.StockQuantityBO;
import com.greatonce.oms.query.stock.StockQuery;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface StockUploadDao extends QueryDao<Stock,StockQuery> {


    List<StockQuantityBO> listQuantity(Long skuId, Collection<Long> warehouseIds);

    /**
     * 计算普通上传数量
     * @param productMallMapping       铺货
     */
    BigDecimal calcQuantity(ProductMallMapping productMallMapping, Map<Long, StockUploadWarehouse> stockUploadWarehouseMap);

    /**
     * 计算预售上传数量
     * @param productMallMapping 铺货
     */
    int calcPresellQuantity(ProductMallMapping productMallMapping);

    /**
     * 计算总可销
     * @param productMallMapping 铺货
     */
    BigDecimal calcCanSaleTotal(ProductMallMapping productMallMapping, Collection<Long> warehouseIds);
}
