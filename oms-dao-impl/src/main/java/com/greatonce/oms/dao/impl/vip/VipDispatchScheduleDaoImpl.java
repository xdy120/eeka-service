package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipDispatchScheduleDao;
import com.greatonce.oms.domain.vip.VipDispatchSchedule;
import com.greatonce.oms.query.vip.VipDispatchScheduleQuery;
import org.springframework.stereotype.Repository;

/**
 * VipDispatchSchedule <br/>
 * 唯品配货档期
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipDispatchScheduleDaoImpl extends AbstractOmsDao<VipDispatchSchedule,VipDispatchScheduleQuery> implements VipDispatchScheduleDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.vip.VipDispatchScheduleMapper";
    }

}