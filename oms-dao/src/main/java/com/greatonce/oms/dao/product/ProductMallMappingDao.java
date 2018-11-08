package com.greatonce.oms.dao.product;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.product.ProductMallMapping;
import com.greatonce.oms.query.product.ProductMallMappingQuery;

import java.util.List;
import java.util.Map;

/**
 * ProductMallMapping <br/>
 * 铺货关系
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface ProductMallMappingDao extends QueryDao<ProductMallMapping,ProductMallMappingQuery>{

    List<ProductMallMapping> listFull(Map<String, Object> params);

    List<ProductMallMapping> listStoreMapping(Long storeId);

    void beginMarketing(ProductMallMapping mapping);

    void endMarketing(ProductMallMapping mapping);

    List<ProductMallMapping> listAutoUploadMapping(Long storeId);
}