package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipReturnNoticeDetailDao;
import com.greatonce.oms.domain.vip.VipReturnNoticeDetail;
import com.greatonce.oms.query.vip.VipReturnNoticeDetailQuery;
import java.util.Collection;
import org.springframework.stereotype.Repository;

/**
 * VipReturnNoticeDetail <br/> 唯品退供通知单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipReturnNoticeDetailDaoImpl extends
    AbstractOmsDao<VipReturnNoticeDetail, VipReturnNoticeDetailQuery> implements
    VipReturnNoticeDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipReturnNoticeDetailMapper";
  }
}