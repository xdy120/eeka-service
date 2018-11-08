package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.GiftStrategyRuleDao;
import com.greatonce.oms.domain.marketing.GiftStrategyRule;
import com.greatonce.oms.query.marketing.GiftStrategyRuleQuery;
import org.springframework.stereotype.Repository;

/**
 * GiftStrategyRule <br/>
 * 赠品规则
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class GiftStrategyRuleDaoImpl extends AbstractOmsDao<GiftStrategyRule, GiftStrategyRuleQuery> implements GiftStrategyRuleDao {

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.GiftStrategyRuleMapper";
    }
}