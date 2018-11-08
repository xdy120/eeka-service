package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.ActivityDao;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.query.marketing.ActivityQuery;
import org.springframework.stereotype.Repository;

/**
 * Activity <br/>
 * 活动报名
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class ActivityDaoImpl extends AbstractOmsDao<Activity,ActivityQuery> implements ActivityDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.ActivityMapper";
    }

    @Override
    public Activity listAvailable(ActivityQuery filter) {
        return this.getSqlSessionDecorator().selectOne(getStatement("listAvailable"),filter);
    }

}