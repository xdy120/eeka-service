package com.greatonce.oms.biz.impl.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.biz.admin.OrganizationService;
import com.greatonce.oms.biz.impl.AbstractService;
import com.greatonce.oms.dao.admin.OrganizationDao;
import com.greatonce.oms.domain.admin.Organization;
import com.greatonce.oms.domain.enums.OrganizationStatus;
import com.greatonce.oms.query.admin.OrganizationQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Organization <br/>
 * 组织
 *
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
@Service
public class OrganizationServiceImpl extends
    AbstractService<Organization, OrganizationQuery> implements OrganizationService {

  @Autowired
  private OrganizationDao dao;

  @Override
  protected QueryDao<Organization, OrganizationQuery> getDAO() {
    return this.dao;
  }

  @Override
  public Organization getSimpleByDomain(String domain) {
    Organization eg = new Organization();
    eg.setDomain(domain);
    eg.setStatus(OrganizationStatus.ONLINE);
    return dao.getSimpleByExample(eg);
  }
}