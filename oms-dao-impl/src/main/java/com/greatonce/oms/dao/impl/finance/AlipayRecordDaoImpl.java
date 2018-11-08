package com.greatonce.oms.dao.impl.finance;

import com.greatonce.oms.dao.finance.AlipayRecordDao;
import com.greatonce.oms.dao.impl.AbstractOmsDao;
import com.greatonce.oms.domain.finance.AlipayRecord;
import com.greatonce.oms.query.finance.AlipayRecordQuery;
import org.springframework.stereotype.Repository;

/**
 * 支付宝账单.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 */
@Repository
public class AlipayRecordDaoImpl extends AbstractOmsDao<AlipayRecord,AlipayRecordQuery> implements AlipayRecordDao {

  /**
   * 获取XML名称.
   */
  @Override
  protected String getStatementPrefix() {
    return "com.greatonce.oms.dao.finance.AlipayRecordMapper";
  }

}