package com.greatonce.oms.dao.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.query.marketing.GiftStrategyQuery;

import java.util.List;

/**
 * GiftStrategy <br/>
 * 赠品策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface GiftStrategyDao extends QueryDao<GiftStrategy, GiftStrategyQuery> {

    /**
     * 获取有效活动
     *
     * @return
     */
    List<GiftStrategy> listEffectiveActivity();
}