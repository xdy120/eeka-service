package com.greatonce.oms.biz.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.admin.WmsApp;
import com.greatonce.oms.query.admin.WmsAppQuery;

/**
 * WmsApp <br/>
 * 仓库应用
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-03
 */
public interface WmsAppService extends BizService<WmsApp,WmsAppQuery>{

  WmsApp getByCustomerId(String customerId);
}