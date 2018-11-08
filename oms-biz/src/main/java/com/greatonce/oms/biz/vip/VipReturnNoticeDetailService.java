package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.query.vip.VipReturnNoticeDetailQuery;
import java.util.List;

/**
 * VipReturnNoticeDetail <br/>
 * 唯品退供通知单明细
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipReturnNoticeDetailService extends DetailService<VipReturnNotice,VipReturnNoticeDetail,VipReturnNoticeDetailQuery> {

  List<VipReturnNoticeDetail> listByReturnNoticeId(Long vipReturnNoticeId);
}