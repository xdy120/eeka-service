package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipAdjustDao;
import com.greatonce.oms.domain.vip.VipAdjust;
import com.greatonce.oms.query.vip.VipAdjustQuery;
import org.springframework.stereotype.Repository;

/**
 * VipAdjust <br/>
 * 唯品调整单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipAdjustDaoImpl extends AbstractOmsDao<VipAdjust,VipAdjustQuery> implements VipAdjustDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.vip.VipAdjustMapper";
    }
}