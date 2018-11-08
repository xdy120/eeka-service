package com.greatonce.oms.biz.product;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.product.ProductAttribute;
import com.greatonce.oms.query.product.ProductAttributeQuery;

/**
 * ProductAttribute <br/>
 * 商品属性
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductAttributeService extends BizService<ProductAttribute, ProductAttributeQuery> {

    Boolean validateAttributeName(String attributeName, Long attributeid);
}