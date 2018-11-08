package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.PresellDao;
import com.greatonce.oms.domain.marketing.Presell;
import com.greatonce.oms.query.marketing.PresellQuery;
import org.springframework.stereotype.Repository;

/**
 * Presell <br/> 预售
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Repository
public class PresellDaoImpl extends AbstractOmsDao<Presell, PresellQuery> implements PresellDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.marketing.PresellMapper";
  }

  @Override
  public PageList<Presell> listPage(PresellQuery presellQuery, int page, int pageSize) {
    return this.getSqlSessionDecorator()
        .selectList(this.getStatement("listPageByConditions"), presellQuery, page, pageSize);
  }
}