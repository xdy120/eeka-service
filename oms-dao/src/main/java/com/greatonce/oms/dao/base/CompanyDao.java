package com.greatonce.oms.dao.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.base.Company;
import com.greatonce.oms.query.base.CompanyQuery;

import java.util.List;

/**
 * Company <br/>
 * 公司
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface CompanyDao extends QueryDao<Company,CompanyQuery>{

    List<Company> listCompanyAndDepartment();
}