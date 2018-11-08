package com.greatonce.oms.biz.vip;

import com.greatonce.oms.bo.VersionBO;
import com.greatonce.oms.domain.vip.VipAdjust;
import com.greatonce.oms.query.vip.VipAdjustQuery;

/**
 * VipAdjust <br/>
 * 唯品调整单
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface VipAdjustService extends
    com.greatonce.oms.biz.BizService<VipAdjust, VipAdjustQuery> {
	
	void audit(VipAdjust vipAdjust,VersionBO bo);
	
	void end(VipAdjust vipAdjust);
	
}