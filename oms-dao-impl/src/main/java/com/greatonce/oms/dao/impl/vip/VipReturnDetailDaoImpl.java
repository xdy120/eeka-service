package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipReturnDetailDao;
import com.greatonce.oms.domain.vip.VipReturnDetail;
import com.greatonce.oms.query.vip.VipReturnDetailQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * 唯品退货单明细
 * VipReturnDetail <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipReturnDetailDaoImpl extends
    AbstractOmsDao<VipReturnDetail, VipReturnDetailQuery> implements VipReturnDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipReturnDetailMapper";
  }

  @Override
  public List<VipReturnDetail> listCanNotice(Long vipReturnId) {
    return getSqlSessionDecorator().selectList(getStatement("listCanNotice"), vipReturnId);
  }
}