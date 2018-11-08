package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.PresellStoreDao;
import com.greatonce.oms.domain.marketing.PresellStore;
import com.greatonce.oms.query.marketing.PresellStoreQuery;
import org.springframework.stereotype.Repository;

/**
 * PresellStore <br/>
 * 预售店铺
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class PresellStoreDaoImpl extends AbstractOmsDao<PresellStore,PresellStoreQuery> implements PresellStoreDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.PresellStoreMapper";
    }
    
}