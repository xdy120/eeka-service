package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.StockDispatchStrategy;
import com.greatonce.oms.query.base.StockDispatchStrategyQuery;

/**
* StockDispatchStrategy <br/>
* 库存配货策略
*
* @author code-generator
* @author Shenzhen Greatonce Co Ltd
* @version 3.0
*/

public interface StockDispatchStrategyDao extends QueryDao<StockDispatchStrategy,StockDispatchStrategyQuery> {

  int customUpdate(StockDispatchStrategy record);
}
