package com.greatonce.oms.dao.impl.product;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.product.ProductAttributeDao;
import com.greatonce.oms.domain.product.ProductAttribute;
import com.greatonce.oms.query.product.ProductAttributeQuery;
import org.springframework.stereotype.Repository;

/**
 * ProductAttribute <br/>
 * 商品属性
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ProductAttributeDaoImpl extends AbstractOmsDao<ProductAttribute,ProductAttributeQuery> implements ProductAttributeDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.product.ProductAttributeMapper";
    }
    
}