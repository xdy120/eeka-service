package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.GiftStrategyGiftDao;
import com.greatonce.oms.domain.marketing.GiftStrategyGift;
import com.greatonce.oms.query.marketing.GiftStrategyGiftQuery;
import org.springframework.stereotype.Repository;

/**
 * GiftStrategyGift <br/>
 * 赠品策略赠品
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class GiftStrategyGiftDaoImpl extends AbstractOmsDao<GiftStrategyGift,GiftStrategyGiftQuery> implements GiftStrategyGiftDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.GiftStrategyGiftMapper";
    }
    
}