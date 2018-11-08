package com.greatonce.oms.dao.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.bo.vip.VipReturnNoticeExportBO;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.query.vip.VipReturnNoticeQuery;

/**
* VipReturnNotice <br/>
* 唯品退供通知单
*
* @author code-generator
* @author Shenzhen Greatonce Co Ltd
* @version 3.0
*/

public interface VipReturnNoticeDao extends QueryDao<VipReturnNotice,VipReturnNoticeQuery> {

  PageList<VipReturnNoticeExportBO> listExportVipReturnNotice(VipReturnNoticeQuery query, int page, int pageSize);
}
