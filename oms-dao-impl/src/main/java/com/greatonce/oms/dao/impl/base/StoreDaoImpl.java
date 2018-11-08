package com.greatonce.oms.dao.impl.base;

import com.greatonce.oms.dao.base.StoreDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.base.Store;
import com.greatonce.oms.query.base.StoreQuery;
import org.springframework.stereotype.Repository;

/**
 * Store <br/>
 * 店铺
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Repository
public class StoreDaoImpl extends AbstractOmsDao<Store,StoreQuery> implements StoreDao{

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix(){
        return "com.greatonce.oms.dao.base.StoreMapper";
    }

    @Override
    public boolean exists(String storeCode){
        return getSqlSessionDecorator().selectOne(getStatement("exists"), storeCode) != null;
    }
}