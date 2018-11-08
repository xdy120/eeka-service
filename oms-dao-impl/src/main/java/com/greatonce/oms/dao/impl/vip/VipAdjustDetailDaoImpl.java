package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipAdjustDetailDao;
import com.greatonce.oms.domain.vip.VipAdjustDetail;
import com.greatonce.oms.query.vip.VipAdjustDetailQuery;
import org.springframework.stereotype.Repository;

/**
 * VipAdjustDetail <br/>
 * 唯品调整单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipAdjustDetailDaoImpl extends AbstractOmsDao<VipAdjustDetail,VipAdjustDetailQuery> implements VipAdjustDetailDao {

    /**
    * 获取XML名称
    */
    @Override
    protected String getStatementPrefix() {
        return "com.greatonce.oms.dao.vip.VipAdjustDetailMapper";
    }

}