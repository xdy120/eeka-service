package com.greatonce.oms.custom.eeka.dao;

import com.greatonce.oms.custom.eeka.EekaCondition;
import com.greatonce.oms.custom.eeka.bo.StockChange;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.stock.Stock;
import com.greatonce.oms.query.stock.StockQuery;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
@EekaCondition
public class StockChangeDao extends
    AbstractOmsDao<Stock, StockQuery> {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.custom.eeka.dao.StockChangeDao";
  }

  public List<StockChange> stockChangeList(Map<String, Object> params) {
    return getSqlSessionDecorator().selectList(getStatement("getStockChange"), params);
  }

}
