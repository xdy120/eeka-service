package com.greatonce.oms.dao.vip;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.vip.VipReturnDetail;
import com.greatonce.oms.query.vip.VipReturnDetailQuery;
import java.util.List;

/**
 * 唯品退供单明细.
 * VipReturnDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

public interface VipReturnDetailDao extends QueryDao<VipReturnDetail, VipReturnDetailQuery> {

  List<VipReturnDetail> listCanNotice(Long vipReturnId);
}
