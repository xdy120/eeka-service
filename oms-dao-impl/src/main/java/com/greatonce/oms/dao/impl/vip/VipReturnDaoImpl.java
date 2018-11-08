package com.greatonce.oms.dao.impl.vip;

import com.greatonce.core.database.PageList;
import com.greatonce.oms.bo.vip.VipReturnExportBO;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipReturnDao;
import com.greatonce.oms.domain.vip.VipReturn;
import com.greatonce.oms.query.vip.VipReturnQuery;
import org.springframework.stereotype.Repository;

/**
 * VipReturn <br/> 唯品退供单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipReturnDaoImpl extends AbstractOmsDao<VipReturn, VipReturnQuery> implements
    VipReturnDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipReturnMapper";
  }

  @Override
  public PageList<VipReturnExportBO> exportListVipReturn(VipReturnQuery query, Integer page,
      Integer pageSize) {
    return getSqlSessionDecorator()
        .selectList(getStatement("exportListVipReturn"), query, page, pageSize);
  }
}