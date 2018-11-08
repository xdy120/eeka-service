package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.GiftStrategyProductDao;
import com.greatonce.oms.domain.marketing.GiftStrategyProduct;
import com.greatonce.oms.query.marketing.GiftStrategyProductQuery;
import org.springframework.stereotype.Repository;

/**
 * GiftStrategyProduct <br/>
 * 赠品策略活动商品
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class GiftStrategyProductDaoImpl extends AbstractOmsDao<GiftStrategyProduct,GiftStrategyProductQuery> implements GiftStrategyProductDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.GiftStrategyProductMapper";
    }
    
}