package com.greatonce.oms.biz.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.purchase.Supplier;
import com.greatonce.oms.query.purchase.SupplierQuery;

/**
 * Supplier <br/>
 * 供应商
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface SupplierService extends BizService<Supplier, SupplierQuery> {

  void audit(Supplier supplier);

  void resetAudit(Supplier supplier);
}