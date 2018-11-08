package com.greatonce.oms.dao.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.product.ProductCombination;
import com.greatonce.oms.query.product.ProductCombinationQuery;

import java.util.List;

/**
 * ProductCombination <br/>
 * 组合商品明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface ProductCombinationDao extends QueryDao<ProductCombination,ProductCombinationQuery>{

    boolean exists(String skuCode);

    List<ProductCombination> listMainDetail();
}