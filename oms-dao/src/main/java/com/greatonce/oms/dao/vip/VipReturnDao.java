package com.greatonce.oms.dao.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.vip.VipReturnExportBO;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.query.vip.VipReturnQuery;

/**
 * VipReturn <br/>
 * 唯品退供单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */

public interface VipReturnDao extends QueryDao<VipReturn, VipReturnQuery> {

  PageList<VipReturnExportBO> exportListVipReturn(VipReturnQuery query, Integer page,
      Integer pageSize);
}
