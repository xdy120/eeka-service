package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.GiftStrategyStoreDao;
import com.greatonce.oms.domain.marketing.GiftStrategyStore;
import com.greatonce.oms.query.marketing.GiftStrategyStoreQuery;
import org.springframework.stereotype.Repository;

/**
 * GiftStrategyStore <br/>
 * 赠品策略店铺
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class GiftStrategyStoreDaoImpl extends AbstractOmsDao<GiftStrategyStore,GiftStrategyStoreQuery> implements GiftStrategyStoreDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.GiftStrategyStoreMapper";
    }
    
}