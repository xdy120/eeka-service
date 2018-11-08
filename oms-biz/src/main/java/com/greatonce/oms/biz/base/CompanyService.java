package com.greatonce.oms.biz.base;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.base.Company;
import com.greatonce.oms.query.base.CompanyQuery;

import java.util.List;

/**
 * Company <br/>
 * ??
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-10-30
 */
public interface CompanyService extends BizService<Company,CompanyQuery>{
    List<Company> listCompanyAndDepartment();
}