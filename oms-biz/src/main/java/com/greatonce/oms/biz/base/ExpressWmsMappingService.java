package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.ExpressWmsMapping;
import com.greatonce.oms.query.base.ExpressWmsMappingQuery;

/**
 * ExpressWmsMapping <br/>
 * 仓库快递映射
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */
public interface ExpressWmsMappingService extends BizService<ExpressWmsMapping,ExpressWmsMappingQuery>{
    /**
     * 获取仓库快递
     *
     * @param outerCode
     * @return
     */
    ExpressWmsMapping getByOuterCode(String outerCode);
}