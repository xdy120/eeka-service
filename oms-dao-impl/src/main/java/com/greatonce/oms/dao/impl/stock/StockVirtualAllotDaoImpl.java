package com.greatonce.oms.dao.impl.stock;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.stock.StockVirtualAllotDao;
import com.greatonce.oms.domain.stock.StockVirtualAllot;
import com.greatonce.oms.query.stock.StockVirtualAllotQuery;
import org.springframework.stereotype.Repository;

/**
 * StockVirtualAllot <br/> 虚拟调拨
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class StockVirtualAllotDaoImpl extends
    AbstractOmsDao<StockVirtualAllot, StockVirtualAllotQuery> implements StockVirtualAllotDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.stock.StockVirtualAllotMapper";
  }

  @Override
  public int update(StockVirtualAllot record) {
    return this.getSqlSessionDecorator().update(this.getStatement("updateQuantity"), record);
  }

  @Override
  public PageList<StockVirtualAllot> listPage(StockVirtualAllotQuery q, int page, int pageSize) {
    return this.getSqlSessionDecorator()
        .selectList(this.getStatement("listPageCustom"), q, page, pageSize);
  }

}