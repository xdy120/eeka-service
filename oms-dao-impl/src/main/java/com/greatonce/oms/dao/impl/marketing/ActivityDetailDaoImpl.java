package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.ActivityDetailDao;
import com.greatonce.oms.domain.marketing.Activity;
import com.greatonce.oms.domain.marketing.ActivityDetail;
import com.greatonce.oms.query.marketing.ActivityDetailQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ActivityDetail <br/>
 * 活动报名明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class ActivityDetailDaoImpl extends AbstractOmsDao<ActivityDetail, ActivityDetailQuery> implements ActivityDetailDao {

    /**
     * 获取XML名称
     */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.marketing.ActivityDetailMapper";
    }


    @Override
    public void updateLockQuantity(ActivityDetail detail) {
        this.getSqlSessionDecorator().update(getStatement("updateLockQuantity"), detail);
    }

    @Override
    public List<ActivityDetail> listAvailable(Activity activity) {
        return this.getSqlSessionDecorator().selectList(getStatement("listAvailable"), activity);
    }
}