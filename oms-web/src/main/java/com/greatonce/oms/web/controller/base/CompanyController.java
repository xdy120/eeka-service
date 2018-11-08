package com.greatonce.oms.web.controller.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.biz.base.CompanyService;
import com.greatonce.oms.domain.base.Company;
import com.greatonce.oms.query.base.CompanyQuery;
import com.greatonce.oms.web.controller.FullListController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("base/company")
@CrossOrigin
public class CompanyController implements FullListController<Company, CompanyQuery> {

  @Resource
  CompanyService companyService;

  @Override
  public BizService<Company, CompanyQuery> getBizService() {
    return companyService;
  }

  @RequestMapping(method = RequestMethod.GET, value = "/departments")
  @Deprecated
  public List<Company> getCompanyAndDepartment() {
    return companyService.listCompanyAndDepartment();
  }
}
