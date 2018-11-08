package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.GiftStrategyDao;
import com.greatonce.oms.domain.marketing.GiftStrategy;
import com.greatonce.oms.query.marketing.GiftStrategyQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * GiftStrategy <br/>
 * 赠品策略
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class GiftStrategyDaoImpl extends AbstractOmsDao<GiftStrategy, GiftStrategyQuery> implements
    GiftStrategyDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.marketing.GiftStrategyMapper";
  }

  @Override
  public List<GiftStrategy> listEffectiveActivity() {
    return getSqlSessionDecorator().selectList(getStatement("listEffectiveActivity"));
  }

  @Override
  public PageList<GiftStrategy> listPage(GiftStrategyQuery giftStrategyQuery, int page,
      int pageSize) {
    return getSqlSessionDecorator()
        .selectList("advanceListPage", giftStrategyQuery, page, pageSize);
  }
}