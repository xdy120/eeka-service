package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.bo.vip.VipDispatchOrderBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipDispatchDao;
import com.greatonce.oms.domain.vip.VipDispatch;
import com.greatonce.oms.query.vip.VipDispatchQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * VipDispatch <br/> 唯品配货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipDispatchDaoImpl extends AbstractOmsDao<VipDispatch, VipDispatchQuery> implements
    VipDispatchDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipDispatchMapper";
  }

  @Override
  public List<VipDispatchOrderBO> queryDispatch(Long vipDeliveryId) {
    return getSqlSessionDecorator().selectList(getStatement("queryDispatch"), vipDeliveryId);
  }
}