package com.greatonce.oms.dao.marketing;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.marketing.Member;
import com.greatonce.oms.query.marketing.MemberQuery;

/**
 * Member <br/>
 * 会员
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-07
 */

public interface MemberDao extends QueryDao<Member,MemberQuery>{

  int checkExist(Member member);
}