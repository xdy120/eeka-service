package com.greatonce.oms.dao.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.domain.marketing.ActivityDetail;
import com.greatonce.oms.query.marketing.ActivityDetailQuery;

import java.util.List;

/**
 * ActivityDetail <br/>
 * 活动报名明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

public interface ActivityDetailDao extends QueryDao<ActivityDetail, ActivityDetailQuery> {

    /**
     * 更新锁定数量
     *
     * @param detail
     */
    void updateLockQuantity(ActivityDetail detail);

    /**
     * 获取可锁定库存
     *
     * @param activity
     * @return
     */
    List<ActivityDetail> listAvailable(Activity activity);
}