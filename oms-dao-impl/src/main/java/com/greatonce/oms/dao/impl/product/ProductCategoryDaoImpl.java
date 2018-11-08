package com.greatonce.oms.dao.impl.product;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.product.ProductCategoryDao;
import com.greatonce.oms.domain.product.ProductCategory;
import com.greatonce.oms.query.product.ProductCategoryQuery;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * ProductCategory <br/>
 * 商品分类
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class ProductCategoryDaoImpl extends AbstractOmsDao<ProductCategory,ProductCategoryQuery> implements ProductCategoryDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.product.ProductCategoryMapper";
    }

    @Override
    public int updateChildrenQuantity(Long id, int quantity){
        Map<String,Object> map = new HashMap<>(3);
        map.put("productCategoryId", id);
        map.put("quantity", quantity);
        map.put("modifiedTime", LocalDateTime.now());
        return getSqlSessionDecorator().update(getStatement("updateChildrenQuantity"), map);
    }
}