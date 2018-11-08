package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipDispatchDetailDao;
import com.greatonce.oms.domain.vip.VipDispatchDetail;
import com.greatonce.oms.query.vip.VipDispatchDetailQuery;
import java.util.Collection;
import org.springframework.stereotype.Repository;

/**
 * VipDispatchDetail <br/> 唯品配货单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipDispatchDetailDaoImpl extends
    AbstractOmsDao<VipDispatchDetail, VipDispatchDetailQuery> implements VipDispatchDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipDispatchDetailMapper";
  }
}