package com.greatonce.oms.dao.impl.product;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.product.ProductWmsMappingDao;
import com.greatonce.oms.domain.product.ProductWmsMapping;
import com.greatonce.oms.query.product.ProductWmsMappingQuery;
import org.springframework.stereotype.Repository;

/**
 * ProductWmsMapping <br/>
 * 商品仓库映射
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ProductWmsMappingDaoImpl extends AbstractOmsDao<ProductWmsMapping,ProductWmsMappingQuery> implements ProductWmsMappingDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.product.ProductWmsMappingMapper";
    }
    
}