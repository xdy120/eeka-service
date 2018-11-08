package com.greatonce.oms.biz.impl.base;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.base.CompanyService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.base.CompanyDao;
import com.greatonce.oms.domain.base.Company;
import com.greatonce.oms.domain.enums.OmsModule;
import com.greatonce.oms.query.base.CompanyQuery;
import com.greatonce.oms.util.logging.BizLogger;
import com.greatonce.oms.util.logging.OmsLoggerFactory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Company <br/> 公司.
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class CompanyServiceImpl extends AbstractService<Company, CompanyQuery> implements
    CompanyService {

  private static final BizLogger BIZ_LOGGER = OmsLoggerFactory.getLogger(OmsModule.BASE_COMPANY);

  @Autowired
  private CompanyDao dao;

  @Override
  protected QueryDao<Company, CompanyQuery> getDAO() {
    return this.dao;
  }

  @Override
  public BizLogger getBizLogger() {
    return BIZ_LOGGER;
  }

  @Override
  public List<Company> listCompanyAndDepartment() {
    return dao.listCompanyAndDepartment();
  }
}