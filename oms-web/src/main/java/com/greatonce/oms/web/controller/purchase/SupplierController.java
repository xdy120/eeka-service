package com.greatonce.oms.web.controller.purchase;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.purchase.SupplierService;
import com.greatonce.oms.domain.purchase.Supplier;
import com.greatonce.oms.query.purchase.SupplierQuery;
import com.greatonce.oms.web.controller.PageListController;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangc on 2017/11/14.
 */
@RestController
@RequestMapping("purchase/supplier")
@CrossOrigin
public class SupplierController implements PageListController<Supplier, SupplierQuery> {

  @Resource
  SupplierService supplierService;


  @RequestMapping(path = "/{id}/audit", method = RequestMethod.PUT)
  public void audit(@PathVariable("id") Long id) {
    Supplier supplier = supplierService.getByKey(id);
    supplierService.audit(supplier);
  }

  @RequestMapping(path = "/{id}/resetAudit", method = RequestMethod.PUT)
  public void resetAudit(@PathVariable("id") Long id) {
    Supplier supplier = supplierService.getByKey(id);
    supplierService.resetAudit(supplier);
  }

  @Override
  public BizService<Supplier, SupplierQuery> getBizService() {
    return supplierService;
  }

}
