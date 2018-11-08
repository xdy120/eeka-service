package com.greatonce.oms.dao.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.query.marketing.ActivityQuery;

/**
 * Activity <br/>
 * 活动报名
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface ActivityDao extends QueryDao<Activity,ActivityQuery>{

    /**
     * 查询活动占用明细与可占用库存数
     * @param activityQuery
     * @return
     */
    Activity listAvailable(ActivityQuery activityQuery);
}