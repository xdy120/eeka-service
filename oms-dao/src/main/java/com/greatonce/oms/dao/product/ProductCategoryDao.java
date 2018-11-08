package com.greatonce.oms.dao.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.product.ProductCategory;
import com.greatonce.oms.query.product.ProductCategoryQuery;

/**
 * ProductCategory <br/>
 * 商品分类
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface ProductCategoryDao extends QueryDao<ProductCategory,ProductCategoryQuery>{
    int updateChildrenQuantity(Long id, int quantity);
}