package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.vip.VipReturn;
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
public interface VipReturnDetailService extends
    DetailService<VipReturn, VipReturnDetail, VipReturnDetailQuery> {

  /**
   * 获取需要通知的明细.
   */
  List<VipReturnDetail> listCanNotice(Long vipReturnId);
}