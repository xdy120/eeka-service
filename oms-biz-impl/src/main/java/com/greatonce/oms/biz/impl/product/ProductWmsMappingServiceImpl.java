package com.greatonce.oms.biz.impl.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.product.ProductWmsMappingService;
import com.greatonce.oms.dao.product.ProductWmsMappingDao;
import com.greatonce.oms.domain.product.ProductWmsMapping;
import com.greatonce.oms.query.product.ProductWmsMappingQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ProductWmsMapping <br/>
 * 商品仓库映射
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Service
public class ProductWmsMappingServiceImpl extends AbstractService<ProductWmsMapping,ProductWmsMappingQuery> implements ProductWmsMappingService {

    @Autowired
    ProductWmsMappingDao dao;
    
    @Override
    protected QueryDao<ProductWmsMapping,ProductWmsMappingQuery> getDAO() {
        return this.dao;
    }
    
}