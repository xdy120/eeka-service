package com.greatonce.oms.dao.impl.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.vip.VipReturnNoticeExportBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipReturnNoticeDao;
import com.greatonce.oms.domain.vip.VipReturnNotice;
import com.greatonce.oms.query.vip.VipReturnNoticeQuery;
import org.springframework.stereotype.Repository;

/**
 * VipReturnNotice <br/> 唯品退供通知单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipReturnNoticeDaoImpl extends
    AbstractOmsDao<VipReturnNotice, VipReturnNoticeQuery> implements VipReturnNoticeDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipReturnNoticeMapper";
  }

  @Override
  public PageList<VipReturnNotice> listPage(VipReturnNoticeQuery vipReturnNoticeQuery, int page, int pageSize) {
    return getSqlSessionDecorator().selectList(getStatement("listPageCustom"),vipReturnNoticeQuery,page,pageSize);
  }

  @Override
  public PageList<VipReturnNoticeExportBO> listExportVipReturnNotice(VipReturnNoticeQuery query, int page,
      int pageSize) {
    return getSqlSessionDecorator().selectList(getStatement("listExportVipReturnNotice"),query,page,pageSize);
  }
}