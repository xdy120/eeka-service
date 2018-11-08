package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipDeliveryDao;
import com.greatonce.oms.domain.vip.VipDelivery;
import com.greatonce.oms.query.vip.VipDeliveryQuery;
import org.springframework.stereotype.Repository;

/**
 * VipDelivery <br/>
 * 唯品发货单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipDeliveryDaoImpl extends AbstractOmsDao<VipDelivery, VipDeliveryQuery> implements
    VipDeliveryDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipDeliveryMapper";
  }

}