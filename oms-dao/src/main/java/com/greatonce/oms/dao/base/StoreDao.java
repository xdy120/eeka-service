package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.query.base.StoreQuery;

/**
 * Store <br/>
 * 店铺
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface StoreDao extends QueryDao<Store,StoreQuery>{

    boolean exists(String storeCode);
}