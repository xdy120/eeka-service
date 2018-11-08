package com.greatonce.oms.dao.impl.mall;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.mall.MallRefundOrderDao;
import com.greatonce.oms.domain.mall.MallRefundOrder;
import com.greatonce.oms.query.mall.MallRefundOrderQuery;
import org.springframework.stereotype.Repository;

/**
 * MallRefundOrder <br/>
 * 商城退单
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class MallRefundOrderDaoImpl extends
    AbstractOmsDao<MallRefundOrder, MallRefundOrderQuery> implements MallRefundOrderDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.mall.MallRefundOrderMapper";
  }

}