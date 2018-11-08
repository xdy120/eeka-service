package com.greatonce.oms.dao.impl.vip;

import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.dao.vip.VipSalesOrderDetailDao;
import com.greatonce.oms.domain.vip.VipSalesOrderDetail;
import com.greatonce.oms.query.vip.VipSalesOrderDetailQuery;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 * VipSalesOrderDetail <br/> 唯品销售单明细
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class VipSalesOrderDetailDaoImpl extends
    AbstractOmsDao<VipSalesOrderDetail, VipSalesOrderDetailQuery> implements
    VipSalesOrderDetailDao {

  /**
   * 获取XML名称
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.vip.VipSalesOrderDetailMapper";
  }

  @Override
  public List<String> listTradeIdsByStoreId(Long storeId) {
    return getSqlSessionDecorator().selectList("listTradeIdsByStoreId", storeId);
  }
}