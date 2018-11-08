package com.greatonce.oms.biz.impl.purchase;

import com.greatonce.core.database.QueryDao;
import com.greatonce.core.sequence.IdGenerator;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.biz.purchase.SupplierService;
import com.greatonce.oms.dao.purchase.SupplierDao;
import com.greatonce.oms.domain.SysExceptions;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.domain.enums.purchase.SupplierStatus;
import com.greatonce.oms.domain.purchase.Supplier;
import com.greatonce.oms.query.purchase.SupplierQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 供应商. Supplier <br/>
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */

@Service
public class SupplierServiceImpl extends AbstractService<Supplier, SupplierQuery> implements
    SupplierService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory
      .getLogger(OmsModule.PURCHASE_SUPPLIER);

  @Resource
  private IdGenerator supplierIdGenerator;
  @Autowired
  private SupplierDao dao;

  @Override
  protected QueryDao<Supplier, SupplierQuery> getDAO() {
    return this.dao;
  }

  @Override
  public IdGenerator getIdGenerator() {
    return this.supplierIdGenerator;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  protected void initDefaultValue(Supplier record) {
    super.initDefaultValue(record);
    record.setStatus(SupplierStatus.CREATED);
  }

  @Override
  public int create(Supplier record) {
    initDefaultValue(record);
    BIZ_LOGGER.log(record.getSupplierId(), BizLogger.ADD);
    return insert(record);
  }

  @Override
  public int modify(Supplier record) {
    BIZ_LOGGER.log(record.getSupplierId(), BizLogger.UPDATE);
    return update(record);
  }

  @Override
  public void audit(Supplier record) {
    if (record.getStatus() != SupplierStatus.CREATED) {
      throw SysExceptions.STATUS_ERROR;
    }
    record.setStatus(SupplierStatus.AUDITED);
    update(record);
    BIZ_LOGGER.log(record.getSupplierId(), BizLogger.AUDIT);
  }

  @Override
  public void resetAudit(Supplier record) {
    if (record.getStatus() != SupplierStatus.AUDITED) {
      throw SysExceptions.STATUS_ERROR;
    }
    record.setStatus(SupplierStatus.CREATED);
    super.update(record);
    BIZ_LOGGER.log(record.getSupplierId(), BizLogger.RESET_AUDIT);
  }
}