package com.greatonce.oms.dao.admin;

import com.greatonce.core.database.QueryDao;
import com.greatonce.oms.domain.admin.Organization;
import com.greatonce.oms.query.admin.OrganizationQuery;

import java.util.List;

/**
 * Organization <br/>
 * 组织
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 2017-11-07
 */
public interface OrganizationDao extends QueryDao<Organization,OrganizationQuery>{

    Organization getSimpleByExample(Organization organization);
    List<Organization> listSimpleByExample(Organization organization);
}