package com.greatonce.oms.biz.product;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.TreeBizService;
import com.greatonce.oms.domain.product.ProductCategory;
import com.greatonce.oms.query.product.ProductCategoryQuery;

import java.util.List;

/**
 * ProductCategory <br/>
 * 商品分类
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface ProductCategoryService extends BizService<ProductCategory,ProductCategoryQuery>, TreeBizService<ProductCategory>{

    /**
     * 获取子数据
     * @param id
     */
    @Override
    List<ProductCategory> listChildren(Long id);
}