package com.greatonce.oms.biz.marketing;

import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.query.marketing.MemberQuery;

/**
 * Member <br/>
 * 会员
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface MemberService extends BizService<Member, MemberQuery> {

  Member getByName(Long storeId, String name);

  Member decrypt(Member member);

  IdGenerator getIdGenerator();

  /**
   * 校验会员是否存在
   */
  boolean checkExist(String memberName, Long storeId);
}