package com.greatonce.oms.biz.vip;

import com.greatonce.oms.biz.DetailService;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.query.vip.VipDispatchDetailQuery;
import java.util.List;

/**
 * VipDispatchDetail <br/> 唯品配货单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipDispatchDetailService extends
    DetailService<VipDispatch, VipDispatchDetail, VipDispatchDetailQuery> {

  List<VipDispatchDetail> listDetails(Long vipDispatchId);
}