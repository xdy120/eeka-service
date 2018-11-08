package com.greatonce.oms.biz.admin;

import com.greatonce.oms.biz.BizService;
import com.greatonce.oms.domain.admin.Organization;
import com.greatonce.oms.query.admin.OrganizationQuery;

/**
 * Organization <br/>
 * 组织
 * 
 * @author code-generator
 * @author Shenzhen Greatonce Co Ltd
 * @version 3.0
 * @version 2017-11-03
 */
public interface OrganizationService extends BizService<Organization,OrganizationQuery>{
    Organization getSimpleByDomain(String domain);
}