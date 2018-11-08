package com.greatonce.oms.dao.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.vip.VipDispatchOrderBO;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.query.vip.VipDispatchQuery;

import java.util.List;

/**
* VipDispatch <br/>
* 唯品配货单
*
* @author code-generator
* @author Shenzhen Greatonce Co Ltd
* @version 3.0
*/

public interface VipDispatchDao extends QueryDao<VipDispatch,VipDispatchQuery> {
    List<VipDispatchOrderBO> queryDispatch(Long vipDeliveryId);
}
