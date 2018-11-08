package com.greatonce.oms.dao.impl.marketing;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.marketing.MemberDao;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.query.marketing.MemberQuery;
import org.springframework.stereotype.Repository;

/**
 * Member <br/>
 * 会员
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

@Repository
public class MemberDaoImpl extends AbstractOmsDao<Member, MemberQuery> implements MemberDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.marketing.MemberMapper";
  }

  @Override
  public int checkExist(Member member) {
    return getSqlSessionDecorator().selectOne(getStatement("checkExist"), member);
  }
}