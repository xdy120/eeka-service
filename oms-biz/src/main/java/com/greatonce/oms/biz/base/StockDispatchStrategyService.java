package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.EnableBizService;
import com.greatonce.oms.domain.base.ExpressStrategyRule.StrategyExpress;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.query.base.StockDispatchStrategyQuery;
import java.util.List;

/**
 * 库存配货策略.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
public interface StockDispatchStrategyService extends
    BizService<StockDispatchStrategy, StockDispatchStrategyQuery>,
    EnableBizService<StockDispatchStrategy> {

  List<StrategyExpress> listExpresses(Long strategyId);
}