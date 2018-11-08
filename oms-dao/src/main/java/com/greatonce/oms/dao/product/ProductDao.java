package com.greatonce.oms.dao.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.product.Product;
import com.greatonce.oms.query.product.ProductQuery;

/**
 * Product <br/>
 * 商品
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface ProductDao extends QueryDao<Product, ProductQuery> {

  boolean exists(String productCode);

  Product getByCode(String productCode);

}